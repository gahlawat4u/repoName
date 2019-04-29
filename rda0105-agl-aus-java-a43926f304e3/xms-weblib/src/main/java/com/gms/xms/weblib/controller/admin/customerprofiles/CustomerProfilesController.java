package com.gms.xms.weblib.controller.admin.customerprofiles;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.admin.customerprofile.SaveCustomerProfileModel;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.customer.CustomerProfileModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.customers.CustomerProfileDao;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.admin.customerprofile.manage.SaveCustomerProfileVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Posted from CustomerProfilesController
 * <p>
 * Author TANDT 21-10-2015
 */
public class CustomerProfilesController extends JsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    protected List<FranchiseInfoModel> franchises;
    protected List<CustomerProfileModel> customers;
    protected String franchiseCode;
    protected String profileName;
    protected String profileCode;
    private CustomerProfileModel cusProfile;
    private CustomerCollectionModel collectionModel;
    private String profileId;

    private String profileNameOld;
    private SaveCustomerProfileModel saveCustomerProfile;

    public String index() {
        try {
            prepareListFranchise();
            detectProfileId();
            prepareListCustomerProfiles();
            return "success";
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getListCustomerProfiles() {
        try {
            prepareListCustomerProfiles();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String searchCustomerProfiles() {
        try {
            if (!searchCusProfile()) {
                return "error";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doSave() {
        try {
            // Valid saving data.
            SaveCustomerProfileModel saveCustomerProfileModel = this.getSaveCustomerProfile();
            if (saveCustomerProfileModel == null) {
                throw new CustomException("No data for saving.");
            }
            boolean isValid = true;
            if (saveCustomerProfileModel.getAccountSetup() != null) {
                isValid = isValid && isValidAccountSetup(saveCustomerProfileModel.getAccountSetup());
            }
            if (saveCustomerProfileModel.getInvoiceOptions() != null) {
                isValid = isValid && isValidInvoiceOptions(saveCustomerProfileModel.getInvoiceOptions());
            }
            if (saveCustomerProfileModel.getCollection() != null) {
                isValid = isValid && isValidCustomerCollection(saveCustomerProfileModel.getCollection());
            }
            if (isValid) {
                SaveCustomerProfileVo saveCustomerProfileVo = ModelUtils.createVoFromModel(this.getSaveCustomerProfile(), SaveCustomerProfileVo.class);
                ICustomerProfileService service = new CustomerProfileServiceImp();
                service.updateSaveCustomerProfiles(this.getAddInfoMap(), saveCustomerProfileVo);
            } else {
                setErrorCode(ErrorCode.FIELD_ERROR);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void detectProfileId() throws Exception {
        if (!StringUtils.isBlank(this.getProfileId())) {
            CustomerProfileDao profileDao = new CustomerProfileDao();
            CustomerProfileVo customerProfileVo = profileDao.selectByProfileId(Long.valueOf(this.getProfileId()));
            if (customerProfileVo == null) {
                throw new CustomException("No customer profile.");
            }
            this.setFranchiseCode(String.valueOf(customerProfileVo.getFranchiseCode()));
        } else {
            this.setProfileId("-1");
            this.setFranchiseCode(franchises.get(0).getCode());
        }
    }

    protected boolean isValidAccountSetup(CustomerProfileModel accountSetup) throws Exception {
        if (StringUtils.isEmpty(accountSetup.getProfileName())) {
            addFieldError("cusProfile.profileName", "Name profile is empty.");
        } else {
            CustomerProfileFilter filter = new CustomerProfileFilter();
            ICustomerProfileService service = new CustomerProfileServiceImp();
            filter.setFranchiseCode(accountSetup.getFranchiseCode());
            filter.setProfileName(accountSetup.getProfileName());
            if (accountSetup.getProfileId() == "0" || accountSetup.getProfileId() == null) {
                if (service.checkProfileName(filter) > 0) {
                    addFieldError("cusProfile.profileName", "Customer Profile Name Exist In system!");
                }
            } else {
                if (service.checkProfileName(filter) > 0 && !accountSetup.getProfileName().equals(this.getProfileNameOld())) {
                    addFieldError("cusProfile.profileName", "Customer Profile Name Exist In system!");
                }
            }
        }
        if (StringUtils.isEmpty(accountSetup.getGstId())) {
            addFieldError("cusProfile.gstId", "GST is empty.");
        } else {
            if (!StringUtils.isNumeric(accountSetup.getGstId())) {
                addFieldError("cusProfile.gstId", "GST is not numberic.");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected boolean isValidCustomerCollection(CustomerCollectionModel collection) {
        if (StringUtils.isBlank(collection.getFreightCreditLimit())) {
            addFieldError("collectionModel.freightCreditLimit", "Freight Credit Limit cannot leave blank");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected boolean isValidInvoiceOptions(CustomerProfileModel invoiceOptions) {
        if (StringUtils.isBlank(invoiceOptions.getInvoiceLateFee())) {
            addFieldError("customer.invoiceLateFee", "Invoice later fee cannot be blank (default=0)");
        } else {
            Double lateFee = 0.00;
            try {
                lateFee = Double.valueOf(invoiceOptions.getInvoiceLateFee());
                if (lateFee < 0) {
                    addFieldError("cusProfile.invoiceLateFee", "Invoice later fee must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("cusProfile.invoiceLateFee", "Invoice later fee must be a number");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void prepareListFranchise() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        for (FranchiseInfoModel model : franchiseInfoModels) {
            model.setName(model.getCode().concat(" - ").concat(model.getName()));
        }
        this.setFranchises(franchiseInfoModels);
    }

    protected void prepareListCustomerProfiles() throws Exception {
        ICustomerProfileService service = new CustomerProfileServiceImp();
        if (StringUtils.isNotEmpty(franchiseCode)) {
            List<CustomerProfileModel> customersN = ModelUtils.createListModelFromVo(service.selectByFranchiseCode(franchiseCode), CustomerProfileModel.class);
            buildListCustomers(removeNoPermissionProfiles(customersN));
        } else {
            setErrorMessage("Please choice a Franchise Code");
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("Please choice a Franchise Code");
        }
    }

    protected List<CustomerProfileModel> removeNoPermissionProfiles(final List<CustomerProfileModel> profiles) {
        // Get user id and user level.
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        String userLevel = (String) this.getSession("SESS_XMS_ADMIN_LEVEL");
        long id = Long.valueOf(userId);
        int level = Double.valueOf(userLevel).intValue();
        // If the user level is 6 then
        // remove the customer profile that have sales rep id equals with user id.
        List<CustomerProfileModel> result = new LinkedList<>();
        for (CustomerProfileModel profile : profiles) {
            if (level == 6) {
                if (id == Long.valueOf(profile.getSalesRepId())) {
                    result.add(profile);
                }
            } else {
                result.add(profile);
            }
        }
        return result;
    }

    protected boolean searchCusProfile() throws Exception {
        if (StringUtils.isNotEmpty(profileName)) {
            ICustomerProfileService service = new CustomerProfileServiceImp();
            CustomerProfileFilter filter = new CustomerProfileFilter();
            filter.setProfileName(profileName);
            filter.setFranchiseCode(franchiseCode);
            List<CustomerProfileModel> customersN = ModelUtils.createListModelFromVo(service.selectCustomerProfilesByFilter(filter), CustomerProfileModel.class);
            buildListCustomers(removeNoPermissionProfiles(customersN));
        } else {
            setErrorMessage("Please enter customer profiles name");
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("Please enter customer profiles name");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void buildListCustomers(List<CustomerProfileModel> listCustomers) throws Exception {
        customers = new ArrayList<CustomerProfileModel>();
        CustomerProfileModel customerProfileModelN = new CustomerProfileModel();
        customerProfileModelN.setProfileId("0");
        customerProfileModelN.setProfileName("Add new customer profile");
        customers.add(customerProfileModelN);
        for (CustomerProfileModel model : listCustomers) {
            customers.add(model);
        }
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public List<CustomerProfileModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerProfileModel> customers) {
        this.customers = customers;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileCode() {
        return profileCode;
    }

    public void setProfileCode(String profileCode) {
        this.profileCode = profileCode;
    }

    public CustomerProfileModel getCusProfile() {
        return cusProfile;
    }

    public void setCusProfile(CustomerProfileModel cusProfile) {
        this.cusProfile = cusProfile;
    }

    public CustomerCollectionModel getCollectionModel() {
        return collectionModel;
    }

    public void setCollectionModel(CustomerCollectionModel collectionModel) {
        this.collectionModel = collectionModel;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public SaveCustomerProfileModel getSaveCustomerProfile() {
        return saveCustomerProfile;
    }

    public void setSaveCustomerProfile(SaveCustomerProfileModel saveCustomerProfile) {
        this.saveCustomerProfile = saveCustomerProfile;
    }

    public String getProfileNameOld() {
        return profileNameOld;
    }

    public void setProfileNameOld(String profileNameOld) {
        this.profileNameOld = profileNameOld;
    }
}