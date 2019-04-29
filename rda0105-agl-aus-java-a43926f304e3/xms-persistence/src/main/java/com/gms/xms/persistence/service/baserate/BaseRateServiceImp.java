package com.gms.xms.persistence.service.baserate;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerBaseRateDetailDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.admin.CustomerProfileBaseRateDao;
import com.gms.xms.persistence.dao.admin.CustomerProfileBaseRateDetailDao;
import com.gms.xms.persistence.dao.customers.CustomerProfileDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.CustomerBaseRateDetailVo;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.account.customers.manage.SaveCustomerBaseRateVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateDetailVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateVo;
import com.gms.xms.txndb.vo.admin.customerprofile.baserate.SaveCustomerProfileBaseRateVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from Apr 7, 2016 9:28:58 AM
 * <p>
 * Author dattrinh
 */
public class BaseRateServiceImp implements IBaseRateService {

    private static final Log log = LogFactory.getLog(BaseRateServiceImp.class);

    @Override
    public void saveCustBaseRates(Map<String, String> context, SaveCustomerBaseRateVo customerBaseRateVo) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        CustomerBaseRateDao baseRateDao = new CustomerBaseRateDao(sessionClient);
        CustomerBaseRateDetailDao baseRateDetailDao = new CustomerBaseRateDetailDao(sessionClient);
        CustomerDao customerDao = new CustomerDao(sessionClient);
        try {
            String customerCode = String.valueOf(customerBaseRateVo.getCustomerCode());
            // Save customer minimum base rate.
            CustomerVo customerVo = new CustomerVo();
            customerVo.setCustomerCode(customerBaseRateVo.getCustomerCode());
            customerVo.setMinimunBaseCharge(customerBaseRateVo.getMinimunBaseCharge());
            customerDao.updateCustomer(context, customerVo);
            log.info("Update customer [customer_code='" + String.valueOf(customerVo.getCustomerCode()) + "'].");
            // Delete all customer base rate of this customer.
            baseRateDetailDao.deleteByCustomerCode(context, customerCode);
            log.info("Delete customer base rate detail [customer_code='" + customerCode + "'].");
            baseRateDao.deleteByCustomerCode(context, customerCode);
            log.info("Delete customer base rate [customer_code='" + customerCode + "'].");
            for (CustomerBaseRateVo baseRate : customerBaseRateVo.getCustomerBaseRates()) {
                // Ignore null object.
                if (baseRate == null)
                    continue;
                // Set default zone check is false if it's null.
                if (baseRate.getZoneCheck() == null) {
                    baseRate.setZoneCheck(false);
                }
                if (baseRate.getContent() == -1) {
                    baseRate.setContent(0);
                }
                // Insert new customer base rate.
                baseRate.setCustomerBaseRateId(null);
                baseRateDao.insert(context, baseRate);
                log.info("Insert new customer base rate [customer_base_rate_id=" + String.valueOf(baseRate.getCustomerBaseRateId()) + "]");
                // Insert new customer base rate detail.
                if (baseRate.getZoneCheck() && baseRate.getCustomerBaseRateDetails() != null && baseRate.getCustomerBaseRateDetails().size() > 0) {
                    for (CustomerBaseRateDetailVo baseRateDetail : baseRate.getCustomerBaseRateDetails()) {
                        // Set new customer base rate id.
                        baseRateDetail.setCustomerBaseRateId(baseRate.getCustomerBaseRateId());
                        baseRateDetailDao.insert(context, baseRateDetail);
                        log.info("Insert new customer base rate detail [customer_base_rate_id=" + baseRateDetail.getCustomerBaseRateId());
                        log.info(", zone = " + baseRateDetail.getZone());
                        log.info(", rate = " + String.valueOf(baseRateDetail.getRate()) + "]");
                    }
                }
            }
            // Commit transaction.
            sessionClient.endTransaction();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void saveCustBaseRates(Map<String, String> context, SaveCustomerBaseRateVo customerBaseRateVo, SqlSessionClient sessionClient) throws DaoException {
        CustomerBaseRateDao baseRateDao = new CustomerBaseRateDao(sessionClient);
        CustomerBaseRateDetailDao baseRateDetailDao = new CustomerBaseRateDetailDao(sessionClient);
        CustomerDao customerDao = new CustomerDao(sessionClient);
        String customerCode = String.valueOf(customerBaseRateVo.getCustomerCode());
        // Save customer minimum base rate.
        CustomerVo customerVo = new CustomerVo();
        customerVo.setCustomerCode(customerBaseRateVo.getCustomerCode());
        customerVo.setMinimunBaseCharge(customerBaseRateVo.getMinimunBaseCharge());
        customerDao.updateCustomer(context, customerVo);
        log.info("Update customer [customer_code='" + String.valueOf(customerVo.getCustomerCode()) + "'].");
        // Delete all customer base rate of this customer.
        baseRateDetailDao.deleteByCustomerCode(context, customerCode);
        log.info("Delete customer base rate detail [customer_code='" + customerCode + "'].");
        baseRateDao.deleteByCustomerCode(context, customerCode);
        log.info("Delete customer base rate [customer_code='" + customerCode + "'].");
        for (CustomerBaseRateVo baseRate : customerBaseRateVo.getCustomerBaseRates()) {
            // Ignore null object.
            if (baseRate == null)
                continue;
            // Set default zone check is false if it's null.
            if (baseRate.getZoneCheck() == null) {
                baseRate.setZoneCheck(false);
            }
            if (baseRate.getContent() == -1) {
                baseRate.setContent(0);
            }
            // Insert new customer base rate.
            baseRate.setCustomerBaseRateId(null);
            baseRateDao.insert(context, baseRate);
            log.info("Insert new customer base rate [customer_base_rate_id=" + String.valueOf(baseRate.getCustomerBaseRateId()) + "]");
            // Insert new customer base rate detail.
            if (baseRate.getZoneCheck() && baseRate.getCustomerBaseRateDetails() != null && baseRate.getCustomerBaseRateDetails().size() > 0) {
                for (CustomerBaseRateDetailVo baseRateDetail : baseRate.getCustomerBaseRateDetails()) {
                    // Set new customer base rate id.
                    baseRateDetail.setCustomerBaseRateId(baseRate.getCustomerBaseRateId());
                    baseRateDetailDao.insert(context, baseRateDetail);
                    log.info("Insert new customer base rate detail [customer_base_rate_id=" + baseRateDetail.getCustomerBaseRateId());
                    log.info(", zone = " + baseRateDetail.getZone());
                    log.info(", rate = " + String.valueOf(baseRateDetail.getRate()) + "]");
                }
            }
        }
    }

    @Override
    public void saveFranBaseRates(Map<String, String> context, SaveCustomerBaseRateVo franchiseBaseRateVo) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        CustomerBaseRateDao baseRateDao = new CustomerBaseRateDao(sessionClient);
        CustomerBaseRateDetailDao baseRateDetailDao = new CustomerBaseRateDetailDao(sessionClient);
        FranchiseDao franchiseDao = new FranchiseDao(sessionClient);
        try {
            String customerCode = String.valueOf(franchiseBaseRateVo.getCustomerCode());
            // Save franchise minimum base rate.
            FranchiseVo franchiseVo = new FranchiseVo();
            franchiseVo.setFranchiseCode(franchiseBaseRateVo.getCustomerCode());
            franchiseVo.setMinimunBaseCharge(franchiseBaseRateVo.getMinimunBaseCharge());
            franchiseDao.update(context, franchiseVo);
            log.info("Update franchise [franchise_code='" + String.valueOf(franchiseVo.getFranchiseCode()) + "'].");
            // Delete all customer base rate of this customer.
            baseRateDetailDao.deleteByCustomerCode(context, customerCode);
            log.info("Delete customer base rate detail [customer_code='" + customerCode + "'].");
            baseRateDao.deleteByCustomerCode(context, customerCode);
            log.info("Delete customer base rate [customer_code='" + customerCode + "'].");
            for (CustomerBaseRateVo baseRate : franchiseBaseRateVo.getCustomerBaseRates()) {
                // Ignore null object.
                if (baseRate == null)
                    continue;
                // Set default zone check is false if it's null.
                if (baseRate.getZoneCheck() == null) {
                    baseRate.setZoneCheck(false);
                }
                if (baseRate.getContent() == -1) {
                    baseRate.setContent(0);
                }
                // Insert new customer base rate.
                baseRate.setCustomerBaseRateId(null);
                baseRateDao.insert(context, baseRate);
                log.info("Insert new customer base rate [customer_base_rate_id=" + String.valueOf(baseRate.getCustomerBaseRateId()) + "]");
                // Insert new customer base rate detail.
                if (baseRate.getZoneCheck() && baseRate.getCustomerBaseRateDetails() != null && baseRate.getCustomerBaseRateDetails().size() > 0) {
                    for (CustomerBaseRateDetailVo baseRateDetail : baseRate.getCustomerBaseRateDetails()) {
                        // Set new customer base rate id.
                        baseRateDetail.setCustomerBaseRateId(baseRate.getCustomerBaseRateId());
                        baseRateDetailDao.insert(context, baseRateDetail);
                        log.info("Insert new customer base rate detail [customer_base_rate_id=" + baseRateDetail.getCustomerBaseRateId());
                        log.info(", zone = " + baseRateDetail.getZone());
                        log.info(", rate = " + String.valueOf(baseRateDetail.getRate()) + "]");
                    }
                }
            }
            // Commit transaction.
            sessionClient.endTransaction();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void saveFranBaseRates(Map<String, String> context, SaveCustomerBaseRateVo franchiseBaseRateVo, SqlSessionClient sessionClient) throws DaoException {
        CustomerBaseRateDao baseRateDao = new CustomerBaseRateDao(sessionClient);
        CustomerBaseRateDetailDao baseRateDetailDao = new CustomerBaseRateDetailDao(sessionClient);
        FranchiseDao franchiseDao = new FranchiseDao(sessionClient);
        try {
            String customerCode = String.valueOf(franchiseBaseRateVo.getCustomerCode());
            // Save franchise minimum base rate.
            FranchiseVo franchiseVo = new FranchiseVo();
            franchiseVo.setFranchiseCode(franchiseBaseRateVo.getCustomerCode());
            franchiseVo.setMinimunBaseCharge(franchiseBaseRateVo.getMinimunBaseCharge());
            franchiseDao.update(context, franchiseVo);
            log.info("Update franchise [franchise_code='" + String.valueOf(franchiseVo.getFranchiseCode()) + "'].");
            // Delete all customer base rate of this customer.
            baseRateDetailDao.deleteByCustomerCode(context, customerCode);
            log.info("Delete customer base rate detail [customer_code='" + customerCode + "'].");
            baseRateDao.deleteByCustomerCode(context, customerCode);
            log.info("Delete customer base rate [customer_code='" + customerCode + "'].");
            for (CustomerBaseRateVo baseRate : franchiseBaseRateVo.getCustomerBaseRates()) {
                // Ignore null object.
                if (baseRate == null)
                    continue;
                // Set default zone check is false if it's null.
                if (baseRate.getZoneCheck() == null) {
                    baseRate.setZoneCheck(false);
                }
                if (baseRate.getContent() == -1) {
                    baseRate.setContent(0);
                }
                // Insert new customer base rate.
                baseRate.setCustomerBaseRateId(null);
                baseRateDao.insert(context, baseRate);
                log.info("Insert new customer base rate [customer_base_rate_id=" + String.valueOf(baseRate.getCustomerBaseRateId()) + "]");
                // Insert new customer base rate detail.
                if (baseRate.getZoneCheck() && baseRate.getCustomerBaseRateDetails() != null && baseRate.getCustomerBaseRateDetails().size() > 0) {
                    for (CustomerBaseRateDetailVo baseRateDetail : baseRate.getCustomerBaseRateDetails()) {
                        // Set new customer base rate id.
                        baseRateDetail.setCustomerBaseRateId(baseRate.getCustomerBaseRateId());
                        baseRateDetailDao.insert(context, baseRateDetail);
                        log.info("Insert new customer base rate detail [customer_base_rate_id=" + baseRateDetail.getCustomerBaseRateId());
                        log.info(", zone = " + baseRateDetail.getZone());
                        log.info(", rate = " + String.valueOf(baseRateDetail.getRate()) + "]");
                    }
                }
            }
            // Commit transaction.
            sessionClient.endTransaction();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void saveCustProfileBaseRate(Map<String, String> context, SaveCustomerProfileBaseRateVo profileBaseRateVo) throws Exception {

        SqlSessionClient sessionClient = new SqlSessionClient();
        CustomerProfileBaseRateDao baseRateDao = new CustomerProfileBaseRateDao(sessionClient);
        CustomerProfileBaseRateDetailDao baseRateDetailDao = new CustomerProfileBaseRateDetailDao(sessionClient);
        CustomerProfileDao customerProfileDao = new CustomerProfileDao(sessionClient);
        try {
            Long profileId = profileBaseRateVo.getProfileId();
            // Save customer minimum base rate.
            CustomerProfileVo customerProfileVo = new CustomerProfileVo();
            customerProfileVo.setProfileId(profileBaseRateVo.getProfileId());
            customerProfileVo.setMinimunBaseCharge(profileBaseRateVo.getMinimunBaseCharge());
            customerProfileDao.update(context, customerProfileVo);
            log.info("Update customer profile [customer_code='" + String.valueOf(customerProfileVo.getProfileId()) + "'].");
            // Delete all customer base rate of this customer.
            baseRateDetailDao.deleteByProfileId(context, profileId);
            log.info("Delete customer profile base rate detail [customer_code='" + profileId + "'].");
            baseRateDao.deleteByProfileId(context, profileId);
            log.info("Delete customer profile base rate [customer_code='" + profileId + "'].");
            for (CustomerProfileBaseRateVo baseRate : profileBaseRateVo.getCustomerProfileBaseRates()) {
                // Ignore null object.
                if (baseRate == null)
                    continue;
                // Set default zone check is false if it's null.
                if (baseRate.getZoneCheck() == null) {
                    baseRate.setZoneCheck(false);
                }
                if (baseRate.getContent() == -1) {
                    baseRate.setContent(0);
                }
                // Insert new customer base rate.
                baseRate.setCustomerProfileBaseRateId(null);
                baseRateDao.insert(context, baseRate);
                log.info("Insert new customer profile base rate [customer_profile_base_rate_id=" + String.valueOf(baseRate.getCustomerProfileBaseRateId()) + "]");
                // Insert new customer base rate detail.
                if (baseRate.getZoneCheck() && baseRate.getCustomerProfileBaseRateDetail() != null && baseRate.getCustomerProfileBaseRateDetail().size() > 0) {
                    for (CustomerProfileBaseRateDetailVo baseRateDetail : baseRate.getCustomerProfileBaseRateDetail()) {
                        // Set new customer base rate id.
                        baseRateDetail.setCustomerProfileBaseRateId(baseRate.getCustomerProfileBaseRateId());
                        baseRateDetailDao.insert(context, baseRateDetail);
                        log.info("Insert new customer base rate detail [customer_base_rate_id=" + String.valueOf(baseRateDetail.getCustomerProfileBaseRateId()));
                        log.info(", zone = " + baseRateDetail.getZone());
                        log.info(", rate = " + String.valueOf(baseRateDetail.getRate()) + "]");
                    }
                }
            }
            // Commit transaction.
            sessionClient.endTransaction();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            sessionClient.rollback();
            throw e;
        }

    }

    @Override
    public void saveCustProfileBaseRate(Map<String, String> context, SaveCustomerProfileBaseRateVo profileBaseRateVo, SqlSessionClient sessionClient) throws Exception {
        CustomerProfileBaseRateDao baseRateDao = new CustomerProfileBaseRateDao(sessionClient);
        CustomerProfileBaseRateDetailDao baseRateDetailDao = new CustomerProfileBaseRateDetailDao(sessionClient);
        CustomerProfileDao customerProfileDao = new CustomerProfileDao(sessionClient);
        try {
            Long profileId = profileBaseRateVo.getProfileId();
            // Save customer minimum base rate.
            CustomerProfileVo customerProfileVo = new CustomerProfileVo();
            customerProfileVo.setProfileId(profileBaseRateVo.getProfileId());
            customerProfileVo.setMinimunBaseCharge(profileBaseRateVo.getMinimunBaseCharge());
            customerProfileDao.update(context, customerProfileVo);
            log.info("Update customer profile [customer_code='" + String.valueOf(customerProfileVo.getProfileId()) + "'].");
            // Delete all customer base rate of this customer.
            baseRateDetailDao.deleteByProfileId(context, profileId);
            log.info("Delete customer profile base rate detail [customer_code='" + profileId + "'].");
            baseRateDao.deleteByProfileId(context, profileId);
            log.info("Delete customer profile base rate [customer_code='" + profileId + "'].");
            for (CustomerProfileBaseRateVo baseRate : profileBaseRateVo.getCustomerProfileBaseRates()) {
                // Ignore null object.
                if (baseRate == null)
                    continue;
                // Set default zone check is false if it's null.
                if (baseRate.getZoneCheck() == null) {
                    baseRate.setZoneCheck(false);
                }
                if (baseRate.getContent() == -1) {
                    baseRate.setContent(0);
                }
                // Insert new customer base rate.
                baseRate.setCustomerProfileBaseRateId(null);
                baseRateDao.insert(context, baseRate);
                log.info("Insert new customer profile base rate [customer_profile_base_rate_id=" + String.valueOf(baseRate.getCustomerProfileBaseRateId()) + "]");
                // Insert new customer base rate detail.
                if (baseRate.getZoneCheck() && baseRate.getCustomerProfileBaseRateDetail() != null && baseRate.getCustomerProfileBaseRateDetail().size() > 0) {
                    for (CustomerProfileBaseRateDetailVo baseRateDetail : baseRate.getCustomerProfileBaseRateDetail()) {
                        // Set new customer base rate id.
                        baseRateDetail.setCustomerProfileBaseRateId(baseRate.getCustomerProfileBaseRateId());
                        baseRateDetailDao.insert(context, baseRateDetail);
                        log.info("Insert new customer base rate detail [customer_base_rate_id=" + String.valueOf(baseRateDetail.getCustomerProfileBaseRateId()));
                        log.info(", zone = " + baseRateDetail.getZone());
                        log.info(", rate = " + String.valueOf(baseRateDetail.getRate()) + "]");
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            sessionClient.rollback();
            throw e;
        }

    }
}
