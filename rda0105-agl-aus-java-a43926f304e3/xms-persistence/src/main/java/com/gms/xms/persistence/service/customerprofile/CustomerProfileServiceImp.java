package com.gms.xms.persistence.service.customerprofile;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.admin.CustomerProfileBaseRateDao;
import com.gms.xms.persistence.dao.admin.CustomerProfileBaseRateDetailDao;
import com.gms.xms.persistence.dao.admin.CustomerProfileBaseRateExtDao;
import com.gms.xms.persistence.dao.customers.CustomerCollectionDao;
import com.gms.xms.persistence.dao.customers.CustomerProfileDao;
import com.gms.xms.persistence.service.baserate.BaseRateServiceImp;
import com.gms.xms.persistence.service.baserate.IBaseRateService;
import com.gms.xms.persistence.service.customer.CustomerCollectionServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerCollectionService;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateDetailVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateExtVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateVo;
import com.gms.xms.txndb.vo.admin.customerprofile.manage.AddNewCutomerProfileVo;
import com.gms.xms.txndb.vo.admin.customerprofile.manage.SaveCustomerProfileVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerProfileServiceImp
 * <p>
 * Author DatTV Oct 12, 2015
 */
public class CustomerProfileServiceImp implements ICustomerProfileService {
    private static final Log log = LogFactory.getLog(CustomerProfileServiceImp.class);

    @Override
    public List<CustomerProfileVo> selectByFranchiseCode(String franchiseCode) throws DaoException {
        CustomerProfileDao profileDao = new CustomerProfileDao();
        return profileDao.selectByFranchiseCode(franchiseCode);
    }

    @Override
    public CustomerProfileVo selectByProfileId(Long profileId) throws DaoException {
        CustomerProfileDao profileDao = new CustomerProfileDao();
        return profileDao.selectByProfileId(profileId);
    }

    @Override
    public List<CustomerProfileVo> selectCustomerProfilesByFilter(CustomerProfileFilter filter) throws DaoException {
        CustomerProfileDao profileDao = new CustomerProfileDao();
        return profileDao.selectCustomerProfilesByFilter(filter);
    }

    @Override
    public void updateCustomerProfiles(Map<String, String> context, CustomerProfileVo customerProfileVo) throws DaoException {
        CustomerProfileDao profileDao = new CustomerProfileDao();
        profileDao.update(context, customerProfileVo);
    }

    @Override
    public void updateCustomerProfiles(Map<String, String> context, CustomerProfileVo customerProfileVo, SqlSessionClient sessionClient) throws DaoException {
        CustomerProfileDao profileDao = new CustomerProfileDao(sessionClient);
        profileDao.update(context, customerProfileVo);
    }

    @Override
    public void insertCustomerProfiles(Map<String, String> context, CustomerProfileVo customerProfileVo) throws DaoException {
        CustomerProfileDao profileDao = new CustomerProfileDao();
        profileDao.insert(context, customerProfileVo);
    }

    @Override
    public void insertCustomerProfiles(Map<String, String> context, CustomerProfileVo customerProfileVo, SqlSessionClient sessionClient) throws DaoException {
        CustomerProfileDao profileDao = new CustomerProfileDao(sessionClient);
        profileDao.insert(context, customerProfileVo);
    }

    @Override
    public Integer checkProfileName(CustomerProfileFilter filter) throws DaoException {
        CustomerProfileDao profileDao = new CustomerProfileDao();
        return profileDao.checkProfileName(filter);
    }

    @Override
    public CustomerProfileBaseRateVo selectByShipmentTypeProfileId(CustomerProfileFilter filter) throws DaoException {
        CustomerProfileBaseRateDao dao = new CustomerProfileBaseRateDao();
        return dao.selectByShipmentTypeProfileId(filter);
    }

    @Override
    public CustomerProfileBaseRateDetailVo selectByZoneAndShipmentId(CustomerProfileFilter filter) throws DaoException {
        CustomerProfileBaseRateDetailDao dao = new CustomerProfileBaseRateDetailDao();
        return dao.selectByZoneAndShipmentId(filter);
    }

    @Override
    public List<CustomerProfileBaseRateExtVo> selectBaseRateByShipment(CustomerProfileFilter filter) throws DaoException {
        CustomerProfileBaseRateExtDao dao = new CustomerProfileBaseRateExtDao();
        return dao.selectBaseRateByShipment(filter);
    }

    @Override
    public List<CustomerProfileBaseRateVo> selectByCarrierAndProfileId(CustomerProfileFilter filter) throws DaoException {
        CustomerProfileBaseRateDao dao = new CustomerProfileBaseRateDao();
        return dao.selectByCarrierAndProfileId(filter);
    }

    @Override
    public void updateSaveCustomerProfiles(Map<String, String> context, SaveCustomerProfileVo saveCustomerProfile) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        IBaseRateService baseRateService = new BaseRateServiceImp();
        ICustomerCollectionService collectionService = new CustomerCollectionServiceImp();
        try {
            if (saveCustomerProfile.getAccountSetup() != null) {
                this.updateCustomerProfiles(context, saveCustomerProfile.getAccountSetup(), sessionClient);
            }
            if (saveCustomerProfile.getSaveCustProfileBaseRate() != null) {
                baseRateService.saveCustProfileBaseRate(context, saveCustomerProfile.getSaveCustProfileBaseRate(), sessionClient);
            }
            if (saveCustomerProfile.getInvoiceOptions() != null) {
                this.updateCustomerProfiles(context, saveCustomerProfile.getInvoiceOptions(), sessionClient);
            }
            if (saveCustomerProfile.getCollection() != null) {
                collectionService.update(context, saveCustomerProfile.getCollection(), sessionClient);
            }
            // Commit transaction.
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Write log.
            log.error(e.getMessage(), e);
            // Rollback transaction.
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void addSaveCustomerProfiles(Map<String, String> context, AddNewCutomerProfileVo addNewCutomerProfileVo) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();

        IBaseRateService baseRateService = new BaseRateServiceImp();
        CustomerCollectionDao collectionDao = new CustomerCollectionDao(sessionClient);
        try {
            // Insert account setup + invoice options
            this.insertCustomerProfiles(context, addNewCutomerProfileVo.getCustomerProfile(), sessionClient);
            log.info("Insert customer profile: " + addNewCutomerProfileVo.getCustomerProfile());

            // Set back profileID
            Long profileId = addNewCutomerProfileVo.getCustomerProfile().getProfileId();
            addNewCutomerProfileVo.getSaveCustProfileBaseRate().setProfileId(profileId);
            for (CustomerProfileBaseRateVo baseRateVo : addNewCutomerProfileVo.getSaveCustProfileBaseRate().getCustomerProfileBaseRates()) {
                baseRateVo.setProfileId(profileId);
            }
            baseRateService.saveCustProfileBaseRate(context, addNewCutomerProfileVo.getSaveCustProfileBaseRate(), sessionClient);

            addNewCutomerProfileVo.getCollection().setUserId(profileId);
            collectionDao.insert(context, addNewCutomerProfileVo.getCollection());
            // Commit transaction.
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Write log.
            log.error(e.getMessage(), e);
            // Rollback transaction.
            sessionClient.rollback();
            throw e;
        }
    }
}
