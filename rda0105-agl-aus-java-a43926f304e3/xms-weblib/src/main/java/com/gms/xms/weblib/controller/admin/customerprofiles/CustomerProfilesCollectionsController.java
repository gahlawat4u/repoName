package com.gms.xms.weblib.controller.admin.customerprofiles;

import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.customers.CustomerCollectionDao;
import com.gms.xms.persistence.service.customer.CustomerCollectionServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerCollectionService;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from CustomerProfilesCollectionsController
 * <p>
 * Author TANDT 21-10-2015
 */
public class CustomerProfilesCollectionsController extends CustomerProfilesAccountSetupController {
    private static final long serialVersionUID = -7110570240797000983L;

    protected CustomerCollectionModel collectionModel;

    public String indexCollections() {
        try {
            loadCollections();
            return "success";
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    @Override
    public String save() {
        // Update customer invoice options
        try {
            if (validCollectionProfile()) {
                CustomerCollectionVo customerCollectionVo = ModelUtils.createVoFromModel(collectionModel, CustomerCollectionVo.class);
                ICustomerCollectionService service = new CustomerCollectionServiceImp();
                service.update(this.getAddInfoMap(), customerCollectionVo);
                addActionMessage("Saved successfully");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    private boolean validCollectionProfile() {
        if (StringUtils.isBlank(collectionModel.getFreightCreditLimit())) {
            addFieldError("collectionModel.freightCreditLimit", "Freight Credit Limit cannot leave blank");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void loadCollections() throws Exception {
        CustomerCollectionDao collectionDao = new CustomerCollectionDao();
        CustomerCollectionVo collectionVo = collectionDao.selectByProfileId(this.getProfileId());
        collectionModel = ModelUtils.createModelFromVo(collectionVo, CustomerCollectionModel.class);
    }

    @Override
    public CustomerCollectionModel getCollectionModel() {
        return collectionModel;
    }

    @Override
    public void setCollectionModel(CustomerCollectionModel collectionModel) {
        this.collectionModel = collectionModel;
    }

}