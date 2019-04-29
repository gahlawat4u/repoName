package com.gms.xms.persistence.service.customer;

import com.gms.xms.common.exception.CustomException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.customers.CustomerAccessorialDao;
import com.gms.xms.persistence.dao.customers.CustomerCollectionDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.dao.markup.CustomerProfileAccessorialDao;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.persistence.service.baserate.BaseRateServiceImp;
import com.gms.xms.persistence.service.baserate.IBaseRateService;
import com.gms.xms.persistence.service.webship.IWebshipService;
import com.gms.xms.persistence.service.webship.WebshipServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.account.customers.manage.AddCustomerVo;
import com.gms.xms.txndb.vo.account.customers.manage.SaveManageCustomerVo;
import com.gms.xms.txndb.vo.account.customers.manage.ServiceSettingVo;
import com.gms.xms.txndb.vo.account.customers.manage.ShipmentTypeSettingVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from Apr 13, 2016 10:33:37 AM
 * <p>
 * Author dattrinh
 */
public class ManageCustomerServiceImp implements IManageCustomerService {

    private static final Log log = LogFactory.getLog(ManageCustomerServiceImp.class);

    @Override
    public void updateCustomer(Map<String, String> context, SaveManageCustomerVo customer) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        ICustomerService customerService = new CustomerServiceImp();
        IBaseRateService baseRateService = new BaseRateServiceImp();
        ICustomerCollectionService collectionService = new CustomerCollectionServiceImp();
        IWebshipService webshipService = new WebshipServiceImp();
        CustomerDao customerDao = new CustomerDao();
        try {
            // Update account setup.
            if (customer.getAccountSetup() != null) {
                customerService.updateCustomer(context, customer.getAccountSetup(), sessionClient);
            }
            // Update customer address.
            if (customer.getCustomerAddress() != null) {
                customerService.saveCustomerAddress(context, customer.getCustomerAddress(), sessionClient);
            }
            // Update base rates.
            if (customer.getSaveCustBaseRate() != null) {
                baseRateService.saveCustBaseRates(context, customer.getSaveCustBaseRate(), sessionClient);
            }
            // Update invoice options.
            if (customer.getInvoiceOption() != null) {
                customerService.updateCustomer(context, customer.getInvoiceOption(), sessionClient);
            }
            // Update collections.
            if (customer.getCollection() != null) {
                if (customer.getCollection().getUserId() != null) {
                    // Update the customer collection if it existed.
                    collectionService.update(context, customer.getCollection(), sessionClient);
                } else {
                    // Get user id from customer code.
                    String customerCode = customer.getCollection().getCustomerCode();
                    Long userId = customerDao.getUserIdByCustomerCode(customerCode);
                    // Insert new customer collection.
                    CustomerCollectionDao customerCollectionDao = new CustomerCollectionDao(sessionClient);
                    customer.getCollection().setUserId(userId);
                    customer.getCollection().setUserType(1);
                    customerCollectionDao.insert(context, customer.getCollection());
                }
            }
            // Update webship.
            if (customer.getWebship() != null) {
                CustomerVo customerVo = customer.getWebship().getCustomer();
                List<AccountServiceVo> accountServiceVos = new ArrayList<AccountServiceVo>();
                for (ServiceSettingVo serviceSettingVo : customer.getWebship().getServices()) {
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
                webshipService.saveCustomerWebshipSettings(context, customerVo, accountServiceVos, sessionClient);
            }
            // Commit transaction.
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Write error log.
            log.error(e.getMessage(), e);
            // Roll back transaction.
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void addCustomer(Map<String, String> context, AddCustomerVo customer, Long profileId) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        CustomerDao customerDao = new CustomerDao(sessionClient);
        CustomerAddressDao customerAddressDao = new CustomerAddressDao(sessionClient);
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao(sessionClient);
        CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao(sessionClient);
        CustomerBaseRateDetailDao customerBaseRateDetailDao = new CustomerBaseRateDetailDao(sessionClient);
        CustomerCollectionDao customerCollectionDao = new CustomerCollectionDao(sessionClient);
        NoteDao noteDao = new NoteDao(sessionClient);
        WebshipDao webshipDao = new WebshipDao(sessionClient);
        AccountServiceDao accountServiceDao = new AccountServiceDao(sessionClient);
        CustomerAccessorialDao customerAccessorialDao = new CustomerAccessorialDao(sessionClient);
        CustomerProfileAccessorialDao customerProfileAccessorialDao = new CustomerProfileAccessorialDao(sessionClient);
        try {
            log.info("Start add customer transaction...");
            // Insert customer.
            customerDao.insertCustomer(context, customer.getCustomer());
            log.info("Insert customer: " + customer.getCustomer());
            // Insert customer address.
            customerAddressDao.insertCustomerAddress(context, customer.getAddress());
            log.info("Insert customer address: " + customer.getAddress());
            // Insert customer billing address.
            billingAddressDao.insertCustomerBillingAddress(context, customer.getBillingAddress());
            log.info("Insert customer billing address: " + customer.getBillingAddress());
            // Insert customer collection.
            customer.getCollection().setUserId(customer.getCustomer().getId());
            customerCollectionDao.insert(context, customer.getCollection());
            log.info("Insert customer collection: " + customer.getCollection());
            // Insert customer base rates.
            if (customer.getCustomerBaseRates() != null && customer.getCustomerBaseRates().size() > 0) {
                for (CustomerBaseRateVo baseRate : customer.getCustomerBaseRates()) {
                    customerBaseRateDao.insert(context, baseRate);
                    log.info("Insert customer base rate: " + baseRate);
                    // Insert customer base rate detail if customer base rate
                    // zone check is checked.
                    if (baseRate.getZoneCheck() && baseRate.getCustomerBaseRateDetails() != null && baseRate.getCustomerBaseRateDetails().size() > 0) {
                        for (CustomerBaseRateDetailVo baseRateDetail : baseRate.getCustomerBaseRateDetails()) {
                            baseRateDetail.setCustomerBaseRateId(baseRate.getCustomerBaseRateId());
                            customerBaseRateDetailDao.insert(context, baseRateDetail);
                            log.info("Insert customer base rate detail: " + baseRateDetail);
                        }
                    }
                }
            }
            // Insert note.
            if (customer.getNote() != null) {
                noteDao.insert(context, customer.getNote());
                log.info("Insert note: " + customer.getNote());
            }
            // Insert new webship user.
            webshipDao.insert(context, customer.getWebship());
            log.info("Insert webship: " + customer.getWebship());
            // Insert account service.
            if (customer.getAccountServices() != null && customer.getAccountServices().size() > 0) {
                for (AccountServiceVo accountService : customer.getAccountServices()) {
                    accountServiceDao.insert(context, accountService);
                    log.info("Insert account service: " + accountService);
                }
            }
            // select customer profile accessorial
            List<CustomerProfileAccessorialVo> cusProAccessorialVos = customerProfileAccessorialDao.selectByProfileId(profileId);
            if (cusProAccessorialVos != null & cusProAccessorialVos.size() > 0) {
                CustomerAccessorialVo cusAccessorialVo = null;
                for (int i = 0; i < cusProAccessorialVos.size(); i++) {
                    cusAccessorialVo = new CustomerAccessorialVo();
                    cusAccessorialVo.setAccessorialid(cusProAccessorialVos.get(i).getAccessorialId());
                    cusAccessorialVo.setAmount(cusProAccessorialVos.get(i).getAmount());
                    cusAccessorialVo.setCustomerCode(customer.getCustomer().getCustomerCode());
                    customerAccessorialDao.insert(context, cusAccessorialVo);
                }
            }
            // Commit transaction.
            sessionClient.endTransaction();
            log.info("Commit add customer transaction...");
        } catch (Exception e) {
            // Write error log.
            log.error(e.getMessage(), e);
            // Roll back transaction.
            sessionClient.rollback();
            log.info("Roll back add customer transaction...");
            throw e;
        }
    }

    @Override
    public void updateCustomerActivationDate(Map<String, String> context, String customerCode, SqlSessionClient sessionClient) throws Exception {
        if (StringUtils.isBlank(customerCode)) {
            throw new CustomException("Invalid customer code.");
        }
        InvoiceDao invoiceDao;
        Date activateDate;
        boolean isFranchise = customerCode.endsWith("00001") ? true : false;
        if (isFranchise) {
            // Create franchise dao.
            FranchiseDao franchiseDao;
            if (sessionClient == null) {
                franchiseDao = new FranchiseDao();
                invoiceDao = new InvoiceDao();
            } else {
                franchiseDao = new FranchiseDao(sessionClient);
                invoiceDao = new InvoiceDao(sessionClient);
            }
            // Get activate date.
            activateDate = invoiceDao.getMinInvoiceDateByCustCode(customerCode);
            // Create franchise object to update.
            FranchiseVo franchiseVo = new FranchiseVo();
            franchiseVo.setFranchiseCode(Long.valueOf(customerCode));
            franchiseVo.setActivateDate(activateDate);
            // Do update.
            if (activateDate != null) {
                franchiseDao.update(context, franchiseVo);
            }
        } else {
            // Create customer dao.
            CustomerDao customerDao;
            if (sessionClient == null) {
                customerDao = new CustomerDao();
                invoiceDao = new InvoiceDao();
            } else {
                customerDao = new CustomerDao(sessionClient);
                invoiceDao = new InvoiceDao(sessionClient);
            }
            // Get activate date.
            activateDate = invoiceDao.getMinInvoiceDateByCustCode(customerCode);
            // Create franchise object to update.
            CustomerVo customerVo = new CustomerVo();
            customerVo.setCustomerCode(Long.valueOf(customerCode));
            customerVo.setActivateDate(activateDate);
            // Do update.
            if (activateDate != null) {
                customerDao.updateCustomer(context, customerVo);
            }
        }
    }

    @Override
    public void updateCustomerActivationDate(Map<String, String> context, String customerCode) throws Exception {
        updateCustomerActivationDate(context, customerCode, null);
    }
}
