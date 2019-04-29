package com.gms.xms.weblib.controller.account.franchises.manage;

import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.customers.CustomerCollectionDao;
import com.gms.xms.persistence.service.customer.CustomerCollectionServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerCollectionService;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import org.apache.commons.lang3.StringUtils;

/**
 * File Name: ManageFranchiseCollectionsController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 24-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseCollectionsController
 */
public class ManageFranchiseCollectionsController extends ManageFranchiseAccountSetupController {

    private static final long serialVersionUID = 6946747730519357087L;
    protected CustomerCollectionModel collectionModel;

    public String indexCollections() {
        try {
            prepareFranchiseDetail();
            loadCollections();
            return "success";
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void loadCollections() throws Exception {
        if (StringUtils.isBlank(this.getFranchiseCode())) {
            throw new CustomException("No franchise code");
        }
        CustomerCollectionDao collectionDao = new CustomerCollectionDao();
        CustomerCollectionVo collectionVo = collectionDao.selectByFranCode(this.getFranchiseCode());
        if (collectionVo == null) {
            collectionVo = new CustomerCollectionVo();
            collectionVo.setFreightCreditLimit(0.0);
            collectionVo.setReminder(false);
            collectionVo.setReminderEmail(false);
            collectionVo.setReminderPrint(false);
            collectionVo.setReminderUseEmailInvoice(false);
        }
        collectionVo.setCustomerCode(this.getFranchiseCode());
        CustomerCollectionModel collectionModel = ModelUtils.createModelFromVo(collectionVo, CustomerCollectionModel.class);
        this.setCollectionModel(collectionModel);
    }

    public String save() {
        // Update customer collection
        try {
            if (validCustomerCollection()) {
                CustomerCollectionVo customerVo = ModelUtils.createVoFromModel(collectionModel, CustomerCollectionVo.class);
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

    public CustomerCollectionModel getCollectionModel() {
        return collectionModel;
    }

    protected boolean validCustomerCollection() {
        if (collectionModel == null) {
            addActionError("Invalid customer collection");
            return false;
        }
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

    public void setCollectionModel(CustomerCollectionModel collectionModel) {
        this.collectionModel = collectionModel;
    }

}