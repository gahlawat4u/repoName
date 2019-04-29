package com.gms.xms.weblib.controller.account.franchises.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.CryptUtils;
import com.gms.xms.common.utils.PasswordUtils;
import com.gms.xms.filter.webship.WebshipFilter;
import com.gms.xms.model.FranchiseModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.account.customers.manage.ServiceSettingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.WebshipModel;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.webship.IWebshipService;
import com.gms.xms.persistence.service.webship.WebshipServiceImp;
import com.gms.xms.txndb.vo.AccountServiceVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.account.customers.manage.ServiceSettingVo;
import com.gms.xms.txndb.vo.account.customers.manage.ShipmentTypeSettingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;

/**
 * File Name: ManageFranchiseWebshipController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 24-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseWebshipController
 */
public class ManageFranchiseWebshipController extends JsonBaseController {

    private static final long serialVersionUID = 947508574751141942L;
    private String customerCode;
    private FranchiseModel franchise;
    private List<WebshipModel> webshipList;
    private Paging<WebshipModel> webships;
    private List<ServiceSettingModel> services;
    private String page;
    private String pageSize;
    private List<String> pageSizes;
    private String orderBy;
    private String webshipId;
    private WebshipModel webship;
    private File userImage ;
    private String userImageContentType;
    private String userImageFileName;
    
    public String show() {
        try {
            if (StringUtils.isBlank(customerCode)) {
                setErrorMessage("Please enter franchise name");
                setErrorCode(ErrorCode.ACTION_ERROR);
                addActionError("Please enter franchise name");
                return "error";
            }
            loadFranchise();
            loadWebshipList();
            loadWebships();
            loadServices();
            preparePageSizes();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String search() {
        try {
            loadWebships();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public void save() {
        try {
            IWebshipService webshipService = new WebshipServiceImp();
            FranchiseVo franchiseVo = ModelUtils.createVoFromModel(franchise, FranchiseVo.class);
            List<ServiceSettingVo> serviceSettings = ModelUtils.createListVoFromModel(services, ServiceSettingVo.class);
            List<AccountServiceVo> accountServiceVos = new ArrayList<AccountServiceVo>();
            for (ServiceSettingVo serviceSettingVo : serviceSettings) {
                for (ShipmentTypeSettingVo settingVo : serviceSettingVo.getShipmentTypes()) {
                    if (settingVo.getChecked()) {
                        AccountServiceVo accountServiceVo = new AccountServiceVo();
                        accountServiceVo.setCustomerCode(settingVo.getCustomerCode());
                        accountServiceVo.setUserType(settingVo.getUserType());
                        accountServiceVo.setServiceId(settingVo.getServiceId());
                        accountServiceVo.setShipmentTypeId(settingVo.getShipmentTypeId());
                        accountServiceVos.add(accountServiceVo);
                    }
                }
            }
            webshipService.saveFranchiseWebshipSettings(this.getAddInfoMap(), franchiseVo, accountServiceVos);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
    }
    
    public static byte[] readBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        
        // Get the size of the file
        long length = file.length();
    
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
          throw new IOException("Could not completely read file " + file.getName() + " as it is too long (" + length + " bytes, max supported " + Integer.MAX_VALUE + ")");
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
    
    
    public void uploadFile() {
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
            	
            	File file = new File(this.userImage.toString());
            	byte[] arrayFile = readBytesFromFile(file);
            	
            	FranchiseServiceImp franchiseServiceImp =new  FranchiseServiceImp();
            	FranchiseVo franchiseVo = new FranchiseVo();
            	franchiseVo.setFranchiseCode(Long.valueOf(customerCode));
            	franchiseVo.setProfileImage(arrayFile);
            	Map<String, String>context = new HashMap<String, String>();
            	franchiseServiceImp.updateFranchiseProfileImage(context, franchiseVo);
            	String filePath = WebUtils.getWebLogoPath(request);
            	String filePathNew= filePath.replace("admin", "webship");
                 File fileToCreate = new File(filePathNew, customerCode.substring(0, 3)+".png");
                 FileUtils.copyFile(this.userImage, fileToCreate);
              
            } catch (Exception e) {
                handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            }
        }
        //return "success";
    }

    public String load() {
        try {
            if (StringUtils.isBlank(customerCode)) {
                throw new Exception("No customer code");
            }
            // New webship user
            if (StringUtils.isBlank(webshipId)) {
                WebshipModel webshipModel = new WebshipModel();
                webshipModel.setCustomerCode(customerCode);
                webshipModel.setAllowExportAddressBook("false");
                webshipModel.setIsRequireChangePassword("false");
                webshipModel.setLanguage("0");
                this.setWebship(webshipModel);
            } else {
                // Load note info
                IWebshipService webshipService = new WebshipServiceImp();
                WebshipVo webshipVo = webshipService.selectWebshipById(Long.valueOf(webshipId));
                webshipVo.setPassword(CryptUtils.Decrypt(webshipVo.getPassword(), AppConstants.APP_SETTINGS.getEncryptionKey()));
                WebshipModel webshipModel = ModelUtils.createModelFromVo(webshipVo, WebshipModel.class);
                this.setWebship(webshipModel);
            }
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String edit() {
        try {
            if (!validWebship()) {
                setErrorCode(ErrorCode.FIELD_ERROR);
            } else {
                IWebshipService webshipService = new WebshipServiceImp();
                WebshipVo webshipVo = ModelUtils.createVoFromModel(webship, WebshipVo.class);
                webshipVo.setPassword(CryptUtils.Encrypt(webshipVo.getPassword(), AppConstants.APP_SETTINGS.getEncryptionKey()));
                if (webshipVo.getWebshipId() == null) {
                    if (checkDuplicate(webshipVo)) {
                        throw new CustomException("This alternate user already existed.");
                    }
                    webshipVo.setCreateDate(Calendar.getInstance().getTime());
                    webshipService.insertWebship(this.getAddInfoMap(), webshipVo);
                    log.info("Insert webship user" + webshipVo);
                } else {
                    webshipService.updateWebship(this.getAddInfoMap(), webshipVo);
                    log.info("Update webship user: " + webshipVo);
                }
            }
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    protected boolean checkDuplicate(WebshipVo webshipVo) throws Exception {
        WebshipDao webshipDao = new WebshipDao();
        int count = webshipDao.checkUser(webshipVo);
        return count > 0;
    }

    private boolean validWebship() {
        if (webship == null) {
            return false;
        }
        if (StringUtils.isBlank(webship.getCustomerCode())) {
            addFieldError("webship.customerCode", "No customer code");
        }
        if (StringUtils.isBlank(webship.getName())) {
            addFieldError("webship.name", "Alternate User cannot be blank");
        }
        if (StringUtils.isBlank(webship.getPassword())) {
            addFieldError("webship.password", "Password cannot be blank");
        } else if (!PasswordUtils.isValid(webship.getPassword())) {
            addFieldError("webship.password", "Password should contain minimum 8 characters, with at lease one letter and one number.");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void loadFranchise() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String franchiseCode = this.getCustomerCode().substring(0, 3);
        FranchiseVo franchiseVo = franchiseService.selectFranchiseByFranchiseCode(franchiseCode);
        FranchiseModel franchiseModel = ModelUtils.createModelFromVo(franchiseVo, FranchiseModel.class);
        this.setFranchise(franchiseModel);
    }

    private void loadWebshipList() throws Exception {
        IWebshipService webshipService = new WebshipServiceImp();
        List<WebshipVo> webshipVos = webshipService.selectByFilter(this.buildFilter());
        List<WebshipModel> webshipModels = ModelUtils.createListModelFromVo(webshipVos, WebshipModel.class);
        this.setWebshipList(webshipModels);
    }

    private void preparePageSizes() {
        this.setPageSizes(this.buildPageSizeList());
    }

    private void loadWebships() throws Exception {
        IWebshipService webshipService = new WebshipServiceImp();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        int pSize = 0;
        try {
            pSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pSize = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize());
        }

        int iPage = 0;
        try {
            iPage = Integer.parseInt(this.page);
        } catch (Exception ex) {
            iPage = 1;
        }
        WebshipFilter filter = this.buildFilter();
        long recordCount = webshipService.countByFilter(filter);
        Paging<WebshipModel> paging = new Paging<WebshipModel>(iPage, nLinks, pSize, recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        List<WebshipVo> webshipVos = webshipService.selectByFilter(filter);
        String encryptionKey = AppConstants.APP_SETTINGS.getEncryptionKey();
        for (WebshipVo webshipVo : webshipVos) {
            webshipVo.setPassword(CryptUtils.Decrypt(webshipVo.getPassword(), encryptionKey));
        }
        List<WebshipModel> webshipModels = ModelUtils.createListModelFromVo(webshipVos, WebshipModel.class);
        paging.setRecords(webshipModels);
        this.setWebships(paging);
    }

    private void loadServices() throws Exception {
        IServiceService service = new ServiceServiceImp();
        List<ServiceVo> serviceVos = service.getWebshipActiveServicesByCustCode(customerCode);
        // Create new Service Setting list
        Long lCustomerCode = Long.valueOf(customerCode);
        List<ServiceSettingVo> serviceSettingVos = new ArrayList<ServiceSettingVo>();
        for (ServiceVo serviceVo : serviceVos) {
            ServiceSettingVo serviceSettingVo = new ServiceSettingVo();
            // Get Service Setting info
            serviceSettingVo.setServiceId(serviceVo.getServiceId());
            serviceSettingVo.setServiceName(serviceVo.getServiceName());
            boolean checked = false;
            // Create new Shipment Type Setting list
            List<ShipmentTypeSettingVo> shipmentTypeSettingVos = new ArrayList<ShipmentTypeSettingVo>();
            for (ShipmentTypeVo shipmentTypeVo : serviceVo.getShipmentTypes()) {
                ShipmentTypeSettingVo shipmentTypeSettingVo = new ShipmentTypeSettingVo();
                // Get Shipment Type Setting info
                shipmentTypeSettingVo.setShipmentTypeId(shipmentTypeVo.getShipmentTypeId());
                shipmentTypeSettingVo.setShipmentTypeName(shipmentTypeVo.getShipmentTypeName());
                shipmentTypeSettingVo.setCustomerCode(lCustomerCode);
                shipmentTypeSettingVo.setUserType(1);
                shipmentTypeSettingVo.setServiceId(serviceVo.getServiceId());
                if (shipmentTypeVo.getAccountServices() != null && shipmentTypeVo.getAccountServices().size() > 0) {
                    shipmentTypeSettingVo.setChecked(true);
                    checked = true;
                } else {
                    shipmentTypeSettingVo.setChecked(false);
                }
                // Add Shipment Type Setting to list
                shipmentTypeSettingVos.add(shipmentTypeSettingVo);
            }
            serviceSettingVo.setShipmentTypes(shipmentTypeSettingVos);
            serviceSettingVo.setChecked(checked);
            // Add Service Setting to list
            serviceSettingVos.add(serviceSettingVo);
        }
        // Convert to model
        List<ServiceSettingModel> serviceSettingModels = ModelUtils.createListModelFromVo(serviceSettingVos, ServiceSettingModel.class);
        this.setServices(serviceSettingModels);
    }

    private WebshipFilter buildFilter() throws Exception {
        WebshipFilter filter = new WebshipFilter();
        if (StringUtils.isBlank(customerCode)) {
            throw new Exception("No customer code");
        }
        filter.setCustomerCode(customerCode);
        return filter;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public FranchiseModel getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseModel franchise) {
        this.franchise = franchise;
    }

    public List<WebshipModel> getWebshipList() {
        return webshipList;
    }

    public void setWebshipList(List<WebshipModel> webshipList) {
        this.webshipList = webshipList;
    }

    public Paging<WebshipModel> getWebships() {
        return webships;
    }

    public void setWebships(Paging<WebshipModel> webships) {
        this.webships = webships;
    }

    public List<ServiceSettingModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceSettingModel> services) {
        this.services = services;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(String webshipId) {
        this.webshipId = webshipId;
    }

    public WebshipModel getWebship() {
        return webship;
    }

    public void setWebship(WebshipModel webship) {
        this.webship = webship;
    }

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}
    
    
}
