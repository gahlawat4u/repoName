package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerCollectionServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerCollectionService;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

/**
 * Posted from ManageCustomerCollectionController
 * <p>
 * Author DatTV Sep 28, 2015
 */
public class ManageCustomerCollectionController extends JsonBaseController {

    private static final long serialVersionUID = -6185673201028268233L;

    private String customerCode;
    private CustomerCollectionModel customerCollection;

    public String show() {
        try {
            loadCustomerCollection();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String save() {
        // Update customer collection
        try {
            if (validCustomerCollection()) {
                CustomerCollectionVo customerVo = ModelUtils.createVoFromModel(customerCollection, CustomerCollectionVo.class);
                ICustomerCollectionService collectionService = new CustomerCollectionServiceImp();
                collectionService.update(this.getAddInfoMap(), customerVo);
                addActionMessage("Saved successfully");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    private boolean validCustomerCollection() {
        if (customerCollection == null) {
            addActionError("Invalid customer collection");
            return false;
        }
        if (StringUtils.isBlank(customerCollection.getFreightCreditLimit())) {
            addFieldError("customerCollection.freightCreditLimit", "Freight Credit Limit cannot leave blank");
        } else {
            try {
                Double freight = Double.valueOf(customerCollection.getFreightCreditLimit());
                if (freight < 0) {
                    addFieldError("customerCollection.freightCreditLimit", "Freight Credit Limit must be greater than zero");
                }
            } catch (Exception e) {
                addFieldError("customerCollection.freightCreditLimit", "Freight Credit Limit must be a number");
            }
        }
        if (!StringUtils.isBlank(customerCollection.getReminderEmailAddress()) && !EmailUtils.isValidEmail(customerCollection.getReminderEmailAddress())) {
            addFieldError("customerCollection.reminderEmailAddress", "Invalid reminder email address");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void loadCustomerCollection() throws Exception {
        if (StringUtils.isBlank(this.getCustomerCode())) {
            throw new Exception("No customer code");
        }
        ICustomerCollectionService collectionService = new CustomerCollectionServiceImp();
        CustomerCollectionVo customerCollectionVo = collectionService.selectByCustCode(this.getCustomerCode());
        if (customerCollectionVo == null) {
            customerCollectionVo = new CustomerCollectionVo();
            customerCollectionVo.setFreightCreditLimit(0.0);
            customerCollectionVo.setReminder(false);
            customerCollectionVo.setReminderEmail(false);
            customerCollectionVo.setReminderPrint(false);
            customerCollectionVo.setReminderUseEmailInvoice(false);
        }
        customerCollectionVo.setCustomerCode(this.getCustomerCode());
        CustomerCollectionModel customerCollectionModel = ModelUtils.createModelFromVo(customerCollectionVo, CustomerCollectionModel.class);
        this.setCustomerCollection(customerCollectionModel);
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public CustomerCollectionModel getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(CustomerCollectionModel customerCollection) {
        this.customerCollection = customerCollection;
    }
}
