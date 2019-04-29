package com.gms.xms.weblib.controller.admin.administration;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.admin.AdministrationFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.SupplyModel;
import com.gms.xms.model.UserLevelModel;
import com.gms.xms.model.admin.administration.PermissionResultModel;
import com.gms.xms.model.admin.administration.UserLevelPermissionModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.supplies.SupplyServiceModel;
import com.gms.xms.persistence.service.admin.AdministrationServiceImp;
import com.gms.xms.persistence.service.admin.IAdministrationService;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.supply.ISupplyService;
import com.gms.xms.persistence.service.supply.SupplyServiceImp;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.SupplyVo;
import com.gms.xms.txndb.vo.admin.administration.PermissionResultVo;
import com.gms.xms.txndb.vo.admin.administration.UserLevelPermissionVo;
import com.gms.xms.txndb.vo.webship.supplies.SupplyServiceVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from AdminEmailSettingController
 * <p>
 * Author TANDT
 */
public class AdministrationController extends JsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    private String page;
    private String pageSize;
    private List<String> pageSizes;
    private Paging<PermissionResultModel> permissionModels;
    private UserLevelPermissionModel levelPermissionModel;
    private String statusUpdate;
    private List<UserLevelModel> userLevelModels;
    private List<SupplyModel> supplyModels;
    private List<SupplyServiceModel> serviceList;
    private SupplyModel supply;
    private String id;
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String isEditSupply;
    private String isAddSupply;
    private String isDeleteSupply;
    private String isLocalizationSupply;

    public String show() {
        try {
            pageSizes = this.buildPageSizeList();
            doSearch();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String showSupply() {
        try {
            loadSupply2Show();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String updateStatus() {
        try {
            IAdministrationService service = new AdministrationServiceImp();
            if (levelPermissionModel != null) {
                if (statusUpdate.equals("true")) {
                    service.insertUserLevelPermission(this.getAddInfoMap(), ModelUtils.createVoFromModel(levelPermissionModel, UserLevelPermissionVo.class));
                } else if (statusUpdate.equals("false")) {
                    service.deleteUserLevelPermission(this.getAddInfoMap(), ModelUtils.createVoFromModel(levelPermissionModel, UserLevelPermissionVo.class));
                }
                pageSizes = this.buildPageSizeList();
                doSearch();
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String showSupplyDetail() {
        ISupplyService supplyService = new SupplyServiceImp();
        try {
            if (isEditSupply != null) {
                if (!updateSupply()) {
                    return "error";
                } else {
                    loadSupply2Show();
                    return "save";
                }
            }
            Integer supplyId = Integer.valueOf(id);
            SupplyVo supplyVo = supplyService.selectById(supplyId);
            supply = ModelUtils.createModelFromVo(supplyVo, SupplyModel.class);
            loadSupplyList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String deleteSupply() {
        ISupplyService supplyService = new SupplyServiceImp();
        try {
            if (isDeleteSupply != null) {
                if (!isDeleteSupply()) {
                    return "error";
                } else {
                    loadSupply2Show();
                    return "delete";
                }
            }
            Integer supplyId = Integer.valueOf(id);
            SupplyVo supplyVo = supplyService.selectById(supplyId);
            supply = ModelUtils.createModelFromVo(supplyVo, SupplyModel.class);
            loadSupplyList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String addSupply() {
        try {
            if (isAddSupply != null) {
                if (!isAddSupply()) {
                    return "error";
                } else {
                    loadSupply2Show();
                    return "save";
                }
            }
            SupplyVo supplyVo = new SupplyVo();
            supply = ModelUtils.createModelFromVo(supplyVo, SupplyModel.class);
            loadSupplyList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String localizationSupply() {
        ISupplyService supplyService = new SupplyServiceImp();
        try {
            if (isLocalizationSupply != null) {
                loadSupply2Show();
                return "save";
            }
            Integer supplyId = Integer.valueOf(id);
            SupplyVo supplyVo = supplyService.selectById(supplyId);
            supply = ModelUtils.createModelFromVo(supplyVo, SupplyModel.class);
            loadSupplyList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String editImageSupply() {
        try {
            String imageName = this.getFileUploadFileName();
            String coverFilePath = request.getSession().getServletContext().getRealPath("") + "/images/supply/";
            File coverImg = new File(coverFilePath, imageName);
            FileUtils.copyFile(this.getFileUpload(), coverImg);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private boolean isAddSupply() throws Exception {
        if (this.getSupply() != null) {
            if (StringUtils.isEmpty(this.getSupply().getSupplyName().trim())) {
                setErrorMessage("Please enter a name for supply.");
                setErrorCode(ErrorCode.ACTION_ERROR);
                return false;
            }
            // Get file name.
            if (!StringUtils.isBlank(this.getSupply().getImage())) {
                String fileName = request.getSession().getServletContext().getRealPath("") + "/images/supply/" + this.getSupply().getImage();
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                this.getSupply().setFileType(fileType);
                // Convert image to base64 for storing.
                File file = new File(fileName);
                byte[] datas = AppUtils.readContentIntoByteArray(file);
                byte[] imgBytesAsBase64 = Base64.encodeBase64(datas);
                String imgDataAsBase64 = new String(imgBytesAsBase64);
                this.getSupply().setFileData(imgDataAsBase64);
            }
            this.getSupply().setLocalizationId("0");
            ISupplyService service = new SupplyServiceImp();
            service.insert(this.getAddInfoMap(), ModelUtils.createVoFromModel(this.getSupply(), SupplyVo.class));
            return true;
        }
        return false;
    }

    private boolean isDeleteSupply() throws Exception {
        if (supply != null) {
            ISupplyService service = new SupplyServiceImp();
            service.delete(this.getAddInfoMap(), ModelUtils.createVoFromModel(supply, SupplyVo.class));
            return true;
        } else {
            setErrorMessage("Not found supply.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
    }

    private boolean updateSupply() throws Exception {
        if (this.getSupply() != null) {
            if (StringUtils.isEmpty(this.getSupply().getSupplyName().trim())) {
                setErrorMessage("Please enter a name for supply.");
                setErrorCode(ErrorCode.ACTION_ERROR);
                return false;
            }
            // Get file name.
            if (!StringUtils.isBlank(this.getSupply().getImage())) {
                String fileName = request.getSession().getServletContext().getRealPath("") + "/images/supply/" + this.getSupply().getImage();
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                this.getSupply().setFileType(fileType);
                // Convert image to base64 for storing.
                File file = new File(fileName);
                byte[] datas = AppUtils.readContentIntoByteArray(file);
                byte[] imgBytesAsBase64 = Base64.encodeBase64(datas);
                String imgDataAsBase64 = new String(imgBytesAsBase64);
                this.getSupply().setFileData(imgDataAsBase64);
            }
            ISupplyService service = new SupplyServiceImp();
            service.update(this.getAddInfoMap(), ModelUtils.createVoFromModel(this.getSupply(), SupplyVo.class));
            return true;
        } else {
            return false;
        }
    }

    private void loadSupplyList() throws Exception {
        List<SupplyServiceVo> result = new ArrayList<SupplyServiceVo>();
        IServiceService service = new ServiceServiceImp();
        ISupplyService supplyService = new SupplyServiceImp();
        List<ServiceVo> serviceModels = service.selectAll();
        for (ServiceVo serviceVo : serviceModels) {
            SupplyServiceVo supplyServiceVo = new SupplyServiceVo();
            supplyServiceVo.setService(serviceVo);
            supplyServiceVo.setSupplies(supplyService.selectByServiceId(serviceVo.getServiceId()));
            result.add(supplyServiceVo);
        }
        serviceList = ModelUtils.createListModelFromVo(result, SupplyServiceModel.class);
    }

    private void loadSupply2Show() throws Exception {
        List<SupplyServiceVo> result = new ArrayList<SupplyServiceVo>();
        IServiceService service = new ServiceServiceImp();
        ISupplyService supplyService = new SupplyServiceImp();
        List<ServiceVo> serviceModels = service.selectBySupply();
        for (ServiceVo serviceVo : serviceModels) {
            SupplyServiceVo supplyServiceVo = new SupplyServiceVo();
            supplyServiceVo.setService(serviceVo);
            supplyServiceVo.setSupplies(supplyService.selectByServiceId(serviceVo.getServiceId()));
            result.add(supplyServiceVo);
        }
        serviceList = ModelUtils.createListModelFromVo(result, SupplyServiceModel.class);
    }

    private void doSearch() {
        IAdministrationService service = new AdministrationServiceImp();
        AdministrationFilter filter = null;
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

        try {
            filter = this.buildFilter();
            long recordCount = service.selectPermissionAllCount(filter);
            filter.setUserLevelVos(service.selectUserLevelForPermission());
            // Set paging info
            Paging<PermissionResultModel> paging = new Paging<PermissionResultModel>(iPage, nLinks, pSize, recordCount);
            filter.setPage(paging.getCurrentPage());
            filter.setPageSize(paging.getPageSize());
            userLevelModels = ModelUtils.createListModelFromVo(service.selectUserLevelForPermission(), UserLevelModel.class);
            // Get data of page
            List<PermissionResultVo> permissionVos = service.selectPermissionAll(filter);
            List<PermissionResultModel> permissionModels = ModelUtils.createListModelFromVo(permissionVos, PermissionResultModel.class);
            paging.setRecords(permissionModels);
            this.setPermissionModels(paging);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
    }

    private AdministrationFilter buildFilter() throws Exception {
        AdministrationFilter filter = new AdministrationFilter();
        return filter;
    }

    public String getPage() {
        return page;
    }

    public List<SupplyModel> getSupplyModels() {
        return supplyModels;
    }

    public void setSupplyModels(List<SupplyModel> supplyModels) {
        this.supplyModels = supplyModels;
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

    public Paging<PermissionResultModel> getPermissionModels() {
        return permissionModels;
    }

    public void setPermissionModels(Paging<PermissionResultModel> permissionModels) {
        this.permissionModels = permissionModels;
    }

    public UserLevelPermissionModel getLevelPermissionModel() {
        return levelPermissionModel;
    }

    public void setLevelPermissionModel(UserLevelPermissionModel levelPermissionModel) {
        this.levelPermissionModel = levelPermissionModel;
    }

    public String getStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(String statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

    public List<UserLevelModel> getUserLevelModels() {
        return userLevelModels;
    }

    public void setUserLevelModels(List<UserLevelModel> userLevelModels) {
        this.userLevelModels = userLevelModels;
    }

    public List<SupplyServiceModel> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<SupplyServiceModel> serviceList) {
        this.serviceList = serviceList;
    }

    public SupplyModel getSupply() {
        return supply;
    }

    public void setSupply(SupplyModel supply) {
        this.supply = supply;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getIsEditSupply() {
        return isEditSupply;
    }

    public void setIsEditSupply(String isEditSupply) {
        this.isEditSupply = isEditSupply;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getIsAddSupply() {
        return isAddSupply;
    }

    public void setIsAddSupply(String isAddSupply) {
        this.isAddSupply = isAddSupply;
    }

    public String getIsDeleteSupply() {
        return isDeleteSupply;
    }

    public void setIsDeleteSupply(String isDeleteSupply) {
        this.isDeleteSupply = isDeleteSupply;
    }

    public String getIsLocalizationSupply() {
        return isLocalizationSupply;
    }

    public void setIsLocalizationSupply(String isLocalizationSupply) {
        this.isLocalizationSupply = isLocalizationSupply;
    }
}