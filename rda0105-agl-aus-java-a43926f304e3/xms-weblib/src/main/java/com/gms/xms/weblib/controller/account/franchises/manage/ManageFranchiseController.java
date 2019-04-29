package com.gms.xms.weblib.controller.account.franchises.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.model.CustomerBaseRateModel;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.FranchiseModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerAddressModel;
import com.gms.xms.model.account.franchises.SaveManageFranchiseModel;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.franchise.IManageFranchiseService;
import com.gms.xms.persistence.service.franchise.ManageFranchiseServiceImp;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.account.franchises.SaveManageFranchiseVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name: ManageFranchiseController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 17-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseController
 */
public class ManageFranchiseController extends JsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    protected List<FranchiseInfoModel> franchises;
    protected String franchiseCode;
    protected List<CustomerBaseRateModel> customerBaseRates;
    private String tabId;

    // For saving.
    private SaveManageFranchiseModel saveManageFranchiseModel;

    /**
     * Functions: index <br/>
     * Date Time Create: 17-11-2015 - 16:45:47 <br/>
     * Descriptions: Function index ....... <br/>
     *
     * @return String
     */
    public String index() {
        try {
            prepareListFranchise();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String loadTab() {
        try {
            if (StringUtils.isBlank(this.getTabId())) {
                throw new CustomException("No tab id for loading...");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String view() {
        try {
            if (StringUtils.isBlank(this.getFranchiseCode()) || StringUtils.isBlank(this.getTabId())) {
                throw new CustomException("Please input franchise code and tab id for loading franchise information.");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    /**
     * Functions: prepareListFranchise <br/>
     * Date Time Create: 17-11-2015 - 16:46:54 <br/>
     * Descriptions: Function prepareListFranchise ....... <br/>
     *
     * @throws Exception
     */
    protected void prepareListFranchise() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        FranchiseInfoModel franchiseInfoModelN = new FranchiseInfoModel();
        franchiseInfoModelN.setCode("0");
        franchiseInfoModelN.setName("Add new Franchise");
        franchises = new ArrayList<FranchiseInfoModel>();
        franchises.add(franchiseInfoModelN);
        franchiseInfoModels.add(franchiseInfoModelN);
        for (FranchiseInfoModel model : franchiseInfoModels) {
            if (model.getCode() != "0") {
                model.setCode(model.getCode().concat("00001"));
                model.setName(model.getCode().concat(" - ").concat(model.getName()));
                franchises.add(model);
            }
        }
    }

    public String save() {
        try {
            // Valid saving data.
            SaveManageFranchiseModel manageFranchiseModel = this.getSaveManageFranchiseModel();
            if (manageFranchiseModel == null) {
                throw new CustomException("No data for saving.");
            }
            boolean isValid = true;
            if (manageFranchiseModel.getAccountSetup() != null) {
                isValid = manageFranchiseModel.getAccountSetup() != null && validAccountSetup(manageFranchiseModel.getAccountSetup());
            }
            if (manageFranchiseModel.getCustomerAddress() != null) {
                isValid = isValid && manageFranchiseModel.getCustomerAddress() != null && isValidCustomerAddressInfo(manageFranchiseModel.getCustomerAddress());
            }
            if (manageFranchiseModel.getInvoiceOption() != null) {
                isValid = isValid && manageFranchiseModel.getInvoiceOption() != null && validInvoiceOptions(manageFranchiseModel.getInvoiceOption());
            }
            if (manageFranchiseModel.getCollection() != null) {
                isValid = isValid && manageFranchiseModel.getCollection() != null && validCustomerCollection(manageFranchiseModel.getCollection());
            }
            if (isValid) {
                SaveManageFranchiseVo manageFranchiseVo = ModelUtils.createVoFromModel(this.getSaveManageFranchiseModel(), SaveManageFranchiseVo.class);
                IManageFranchiseService manageFranchiseService = new ManageFranchiseServiceImp();
                manageFranchiseService.updateFranchise(this.getAddInfoMap(), manageFranchiseVo);
                prepareListFranchise();
            } else {
                setErrorCode(ErrorCode.FIELD_ERROR);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean validAccountSetup(FranchiseModel franchise) {
        if (franchise == null) {
            addFieldError("saveManageFranchiseModel.franchise", "Invalid Franchise account setup information");
        }
        if (!StringUtils.isNumeric(franchise.getFranchiseCode())) {
            addFieldError("saveManageFranchiseModel.franchise.franchiseCode", "Franchise Code Is Not Number.");
        }

        if (StringUtils.isBlank(franchise.getGstid())) {
            addFieldError("saveManageFranchiseModel.franchise.gstid", "GST # is required (default=0)");
        } else {
            Integer gstNumber = 0;
            try {
                gstNumber = Integer.valueOf(franchise.getGstid());
                if (gstNumber < 0) {
                    addFieldError("saveManageFranchiseModel.franchise.gstid", "GST # must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("saveManageFranchiseModel.franchise.gstid", "GST # must be a integer number");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected boolean isValidCustomerAddressInfo(ManageCustomerAddressModel customer) {
        if (customer.getAddress().getUserType() == null) {
            customer.getAddress().setUserType("2");
        }
        if (StringUtils.isBlank(customer.getAddress().getCustomerName())) {
            addFieldError("saveManageFranchiseModel.customer.address.customerName", "Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getContactName())) {
            addFieldError("saveManageFranchiseModel.customer.address.contactName", "Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getAddress1())) {
            addFieldError("saveManageFranchiseModel.customer.address.address1", "Address cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getCity())) {
            addFieldError("saveManageFranchiseModel.customer.address.city", "City cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getCountry())) {
            addFieldError("saveManageFranchiseModel.customer.address.country", "Please select a country.");
        } else {
            if ("0".equalsIgnoreCase(customer.getAddress().getCountry())) {
                addFieldError("saveManageFranchiseModel.customer.address.country", "Please select a country.");
            }
        }
        if (StringUtils.isBlank(customer.getAddress().getPhone())) {
            addFieldError("saveManageFranchiseModel.customer.address.phone", "Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customer.getAddress().getEmail())) {
            String[] emails = customer.getAddress().getEmail().split(";");
            for (String email : emails) {
                if (!EmailUtils.isValidEmail(email.trim())) {
                    addFieldError("saveManageFranchiseModel.customer.address.email", "Invalid email address.");
                    break;
                }
            }
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCustomerName())) {
            addFieldError("saveManageFranchiseModel.customer.billingAddress.billingCustomerName", "Billing Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingContactName())) {
            addFieldError("saveManageFranchiseModel.customer.billingAddress.billingContactName", "Billing Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingAddress1())) {
            addFieldError("saveManageFranchiseModel.customer.billingAddress.billingAddress1", "Billing Address cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCity())) {
            addFieldError("saveManageFranchiseModel.customer.billingAddress.billingCity", "Billing City cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCountry())) {
            addFieldError("saveManageFranchiseModel.customer.billingAddress.billingCountry", "Please select a billing country.");
        } else {
            if ("0".equalsIgnoreCase(customer.getBillingAddress().getBillingCountry())) {
                addFieldError("saveManageFranchiseModel.customer.billingAddress.billingCountry", "Please select a billing country.");
            }
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingPhone())) {
            addFieldError("saveManageFranchiseModel.customer.billingAddress.billingPhone", "Billing Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customer.getBillingAddress().getBillingEmail())) {
            String[] emails = customer.getBillingAddress().getBillingEmail().split(";");
            for (String email : emails) {
                if (!EmailUtils.isValidEmail(email.trim())) {
                    addFieldError("saveManageFranchiseModel.customer.billingAddress.billingEmail", "Invalid billing email address.");
                    break;
                }
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOwnerEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOwnerEmail())) {
                addFieldError("saveManageFranchiseModel.customer.address.ownerEmail", "Invalid owner email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getApEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getApEmail())) {
                addFieldError("saveManageFranchiseModel.customer.address.apEmail", "Invalid AP email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOtherEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOtherEmail())) {
                addFieldError("saveManageFranchiseModel.customer.address.otherEmail", "Invalid other email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOther2Email())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOther2Email())) {
                addFieldError("saveManageFranchiseModel.customer.address.other2Email", "Invalid other 2 email address.");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected boolean validCustomerCollection(CustomerCollectionModel collectionModel) {
        if (StringUtils.isBlank(collectionModel.getFreightCreditLimit())) {
            addFieldError("customerCollection.freightCreditLimit", "Freight Credit Limit cannot leave blank");
        } else {
            try {
                Double freight = Double.valueOf(collectionModel.getFreightCreditLimit());
                if (freight < 0) {
                    addFieldError("customerCollection.freightCreditLimit", "Freight Credit Limit must be greater than zero");
                }
            } catch (Exception e) {
                addFieldError("customerCollection.freightCreditLimit", "Freight Credit Limit must be a number");
            }
        }
        if (!StringUtils.isBlank(collectionModel.getReminderEmailAddress()) && !EmailUtils.isValidEmail(collectionModel.getReminderEmailAddress())) {
            addFieldError("customerCollection.reminderEmailAddress", "Invalid reminder email address");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected boolean validInvoiceOptions(FranchiseModel franchise) throws Exception {
        // If email invoice checked so need valid email address of billing email
        // under Address tab.
        if ("true".equalsIgnoreCase(franchise.getEmailInvoice())) {
            String billingEmail = "";
            CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
            CustomerBillingAddressVo billingAddressVo = null;
            if (this.getSaveManageFranchiseModel().getCustomerAddress() == null) {
                billingAddressVo = billingAddressDao.getByCustomerCode(franchise.getFranchiseCode());
                billingEmail = billingAddressVo == null ? "" : billingAddressVo.getBillingEmail();
            } else if (this.getSaveManageFranchiseModel().getCustomerAddress().getBillingAddress() == null) {
                billingAddressVo = billingAddressDao.getByCustomerCode(franchise.getFranchiseCode());
                billingEmail = billingAddressVo == null ? "" : billingAddressVo.getBillingEmail();
            } else {
                billingEmail = this.getSaveManageFranchiseModel().getCustomerAddress().getBillingAddress().getBillingEmail();
            }
            if (StringUtils.isBlank(billingEmail)) {
                addFieldError("saveManageFranchiseModel.customer.billingAddress.billingEmail", "The email for billing email is required.");
            } else {
                String[] emails = billingEmail.split(";");
                for (String email : emails) {
                    if (!EmailUtils.isValidEmail(email.trim())) {
                        addFieldError("saveManageFranchiseModel.customer.billingAddress.billingEmail", "Please enter valid email for billing email.");
                        break;
                    }
                }
            }
        }
        if (StringUtils.isBlank(franchise.getInvoiceLateFee())) {
            addFieldError("franchise.invoiceLateFee", "Invoice later fee cannot be blank (default=0)");
        } else {
            Double lateFee = 0.00;
            try {
                lateFee = Double.valueOf(franchise.getInvoiceLateFee());
                if (lateFee < 0) {
                    addFieldError("franchise.invoiceLateFee", "Invoice later fee must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("franchise.invoiceLateFee", "Invoice later fee must be a number");
            }
        }
        return !hasFieldErrors();
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public List<CustomerBaseRateModel> getCustomerBaseRates() {
        return customerBaseRates;
    }

    public void setCustomerBaseRates(List<CustomerBaseRateModel> customerBaseRates) {
        this.customerBaseRates = customerBaseRates;
    }

    public SaveManageFranchiseModel getSaveManageFranchiseModel() {
        return saveManageFranchiseModel;
    }

    public void setSaveManageFranchiseModel(SaveManageFranchiseModel saveManageFranchiseModel) {
        this.saveManageFranchiseModel = saveManageFranchiseModel;
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

}