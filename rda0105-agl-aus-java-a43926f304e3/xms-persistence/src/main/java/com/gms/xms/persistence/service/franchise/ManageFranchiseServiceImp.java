package com.gms.xms.persistence.service.franchise;

import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.customers.CustomerCollectionDao;
import com.gms.xms.persistence.dao.franchise.FranchiseServiceMarkupDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.persistence.service.baserate.BaseRateServiceImp;
import com.gms.xms.persistence.service.baserate.IBaseRateService;
import com.gms.xms.persistence.service.customer.CustomerCollectionServiceImp;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerCollectionService;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.persistence.service.webship.IWebshipService;
import com.gms.xms.persistence.service.webship.WebshipServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerAddressVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerBaseRateVo;
import com.gms.xms.txndb.vo.account.customers.manage.ServiceSettingVo;
import com.gms.xms.txndb.vo.account.customers.manage.ShipmentTypeSettingVo;
import com.gms.xms.txndb.vo.account.franchises.AddFranchiseVo;
import com.gms.xms.txndb.vo.account.franchises.SaveManageFranchiseVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateDetailVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateVo;
import com.gms.xms.txndb.vo.admin.customerprofile.baserate.SaveCustomerProfileBaseRateVo;
import com.gms.xms.txndb.vo.admin.customerprofile.manage.AddNewCutomerProfileVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManageFranchiseServiceImp implements IManageFranchiseService {

    private static final Log log = LogFactory.getLog(ManageFranchiseServiceImp.class);

    @Override
    public void updateFranchise(Map<String, String> context, SaveManageFranchiseVo franchise) throws Exception {
        // TODO Auto-generated method stub
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        IFranchiseService franchiseService = new FranchiseServiceImp();
        ICustomerService customerService = new CustomerServiceImp();
        IBaseRateService baseRateService = new BaseRateServiceImp();
        ICustomerCollectionService collectionService = new CustomerCollectionServiceImp();
        IWebshipService webshipService = new WebshipServiceImp();
        FranchiseDao franchiseDao = new FranchiseDao();
        try {
            // Update account setup.
            if (franchise.getAccountSetup() != null) {
                ManageCustomerAddressVo customerAddress = (franchise.getCustomerAddress() == null) ? new ManageCustomerAddressVo() : franchise.getCustomerAddress();
                CustomerCollectionVo collection = (franchise.getCollection() == null) ? new CustomerCollectionVo() : franchise.getCollection();
                ManageCustomerBaseRateVo baseRate = (franchise.getBaseRate() == null) ? new ManageCustomerBaseRateVo() : franchise.getBaseRate();
                franchiseService.updateManageFranchise(context, franchise.getAccountSetup(), customerAddress, collection, baseRate, sessionClient);
            }
            // Update customer address.
            if (franchise.getCustomerAddress() != null) {
                customerService.saveCustomerAddress(context, franchise.getCustomerAddress(), sessionClient);
            }
            // Update base rates.
            if (franchise.getSaveFranBaseRate() != null) {
                baseRateService.saveFranBaseRates(context, franchise.getSaveFranBaseRate(), sessionClient);
            }
            // Update invoice options.
            if (franchise.getInvoiceOption() != null) {
                franchiseService.update(context, franchise.getInvoiceOption(), sessionClient);
            }
            // Update collections.
            if (franchise.getCollection() != null) {
                if (franchise.getCollection().getUserId() != null) {
                    // Update the customer collection if it existed.
                    collectionService.update(context, franchise.getCollection(), sessionClient);
                } else {
                    // Get user id from franchise code.
                    String franchiseCode = franchise.getCollection().getCustomerCode();
                    Long userId = franchiseDao.getUserIdByFranchiseCode(franchiseCode);
                    // Insert new customer collection.
                    CustomerCollectionDao customerCollectionDao = new CustomerCollectionDao(sessionClient);
                    franchise.getCollection().setUserId(userId);
                    franchise.getCollection().setUserType(2);
                    customerCollectionDao.insert(context, franchise.getCollection());
                }
            }
            // Update webship.
            if (franchise.getWebship() != null) {
                FranchiseVo franchiseVo = franchise.getFranchise();
                List<ServiceSettingVo> serviceSettings = franchise.getWebship().getServices();
                List<AccountServiceVo> accountServiceVos = new ArrayList<AccountServiceVo>();
                if (serviceSettings != null && serviceSettings.size() > 0) {
                    for (ServiceSettingVo serviceSettingVo : serviceSettings) {
                        if (serviceSettingVo.getShipmentTypes() != null && serviceSettingVo.getShipmentTypes().size() > 0) {
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
                    }
                }
                webshipService.saveFranchiseWebshipSettings(context, franchiseVo, accountServiceVos, sessionClient);
            }
            // Update franchise service markup
            if (franchise.getListServiceMarkup() != null) {
                FranchiseServiceMarkupDao franchiseServiceMarkupDao = new FranchiseServiceMarkupDao(sessionClient);
                for (FranchiseServiceMarkupVo serviceMarkupVo : franchise.getListServiceMarkup()) {
                    FranchiseServiceMarkupVo serviceMarkupCheck = franchiseServiceMarkupDao.selectFranchiseServiceMarkup(serviceMarkupVo);
                    if (serviceMarkupCheck != null) {
                        franchiseServiceMarkupDao.updateFranchiseServiceMarkup(context, serviceMarkupVo);
                    } else {
                        franchiseServiceMarkupDao.inserFranchiseServiceMarkup(context, serviceMarkupVo);
                    }
                }
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
    public void addFranchise(Map<String, String> context, AddFranchiseVo franchise) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        FranchiseDao franchiseDao = new FranchiseDao(sessionClient);
        CustomerAddressDao customerAddressDao = new CustomerAddressDao(sessionClient);
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao(sessionClient);
        CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao(sessionClient);
        CustomerBaseRateDetailDao customerBaseRateDetailDao = new CustomerBaseRateDetailDao(sessionClient);
        CustomerCollectionDao customerCollectionDao = new CustomerCollectionDao(sessionClient);
        NoteDao noteDao = new NoteDao(sessionClient);
        FranchiseServiceMarkupDao franchiseServiceMarkupDao = new FranchiseServiceMarkupDao(sessionClient);
        WebshipDao webshipDao = new WebshipDao(sessionClient);
        AccountServiceDao accountServiceDao = new AccountServiceDao(sessionClient);
        try {
            log.info("Start add franchise transaction...");
            // Insert franchise.
            franchiseDao.insert(context, franchise.getFranchise());
            log.info("Insert franchise: " + franchise.getFranchise());
            // Insert franchise address.
            customerAddressDao.insertCustomerAddress(context, franchise.getAddress());
            log.info("Insert customer address: " + franchise.getAddress());
            // Insert franchise billing address.
            billingAddressDao.insertCustomerBillingAddress(context, franchise.getBillingAddress());
            log.info("Insert customer billing address: " + franchise.getBillingAddress());
            // Insert customer collection.
            franchise.getCollection().setUserId(franchise.getFranchise().getId());
            customerCollectionDao.insert(context, franchise.getCollection());
            log.info("Insert customer collection: " + franchise.getCollection());
            // Insert customer base rates.
            if (franchise.getCustomerBaseRates() != null && franchise.getCustomerBaseRates().size() > 0) {
                for (CustomerBaseRateVo baseRate : franchise.getCustomerBaseRates()) {
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
            if (franchise.getNote() != null) {
                noteDao.insert(context, franchise.getNote());
                log.info("Insert note: " + franchise.getNote());
            }
            // Insert franchise service markup
            if (franchise.getListServiceMarkup() != null) {
                for (FranchiseServiceMarkupVo serviceMarkupVo : franchise.getListServiceMarkup()) {
                    serviceMarkupVo.setFranchiseCode(franchise.getFranchise().getFranchiseCode());
                    franchiseServiceMarkupDao.inserFranchiseServiceMarkup(context, serviceMarkupVo);
                    log.info("Insert franchise service markup: " + serviceMarkupVo);
                }
            }
            // Insert new webship user.
            webshipDao.insert(context, franchise.getWebship());
            log.info("Insert webship: " + franchise.getWebship());
            // Insert account service.
            if (franchise.getAccountServices() != null && franchise.getAccountServices().size() > 0) {
                for (AccountServiceVo accountService : franchise.getAccountServices()) {
                    accountServiceDao.insert(context, accountService);
                    log.info("Insert account service: " + accountService);
                }
            }
            // Add new customer profile default
            AddNewCutomerProfileVo addNewCutomerProfileVo = prepareNewCutomerProfileVo(franchise);
            ICustomerProfileService service = new CustomerProfileServiceImp();
            service.addSaveCustomerProfiles(context, addNewCutomerProfileVo);
            log.info("Insert new customer profile: " + addNewCutomerProfileVo);
            // Commit transaction.
            sessionClient.endTransaction();
            log.info("Commit add franchise transaction...");
        } catch (Exception e) {
            // Write error log.
            log.error(e.getMessage(), e);
            // Roll back transaction.
            sessionClient.rollback();
            log.info("Roll back add franchise transaction...");
            throw e;
        }
    }

    private AddNewCutomerProfileVo prepareNewCutomerProfileVo(AddFranchiseVo franchise) {
        AddNewCutomerProfileVo newCutomerProfileVo = new AddNewCutomerProfileVo();
        CustomerProfileVo customerProfile = new CustomerProfileVo();
        customerProfile.setProfileName("Default");
        String franchiseCode = String.valueOf(franchise.getFranchise().getFranchiseCode());
        customerProfile.setFranchiseCode(Long.valueOf(franchiseCode.substring(0, 3)));
        customerProfile.setInActive(false);
        Integer groupId = (franchise.getFranchise().getGroupId() == null) ? 0 : franchise.getFranchise().getGroupId();
        customerProfile.setGroupId(groupId);
        Integer webshipGroupId = (franchise.getFranchise().getWebshipGroupid() == null) ? 0 : franchise.getFranchise().getWebshipGroupid();
        customerProfile.setWebshipGroupId(webshipGroupId);
        customerProfile.setIndustryId(0L);
        Long salesRepId = (franchise.getFranchise().getSalesRepId() == null) ? 0 : franchise.getFranchise().getSalesRepId();
        customerProfile.setSalesRepId(salesRepId);
        customerProfile.setCollectorId(0);
        String registrationId = (StringUtils.isBlank(franchise.getFranchise().getRegistrationid()) || franchise.getFranchise().getRegistrationid() == null) ? "0" : franchise.getFranchise().getRegistrationid();
        customerProfile.setRegistrationId(Integer.valueOf(registrationId));
        Integer gstId = (franchise.getFranchise().getGstid() == null) ? 0 : franchise.getFranchise().getGstid();
        customerProfile.setGstId(gstId);
        customerProfile.setDhlAccount(franchise.getFranchise().getDhlAccount());
        customerProfile.setTntAccount(franchise.getFranchise().getTntAccount());
        customerProfile.setTollPriorityAccount(franchise.getFranchise().getTollPriorityAccount());
        customerProfile.setStartrackAccount(franchise.getFranchise().getStartrackAccount());
        customerProfile.setDispatchId(franchise.getFranchise().getDispatchId());
        customerProfile.setDhlDomesticAccount(franchise.getFranchise().getDhlDomesticAccount());
        customerProfile.setDhlInternationalAccount(franchise.getFranchise().getDhlInternationalAccount());
        customerProfile.setDhlInboundAccount(franchise.getFranchise().getDhlInboundAccount());
        customerProfile.setOtherAccount(franchise.getFranchise().getOtherAccount());
        customerProfile.setHubAccount(franchise.getFranchise().getHubAccount());
        customerProfile.setRejectionNote("");
        customerProfile.setMinimunBaseCharge(0D);
        customerProfile.setInvoiceSorting(Byte.valueOf("0"));
        customerProfile.setInvoiceTerms(Byte.valueOf("0"));
        customerProfile.setInvoiceToCustomerId(0L);
        customerProfile.setPickupFee(Byte.valueOf("0"));
        customerProfile.setInvoiceLateFee(0D);
        customerProfile.setEmailInvoice(false);
        customerProfile.setDownloadCsvInvoice(false);
        customerProfile.setPreviousCarrier(0);
        customerProfile.setBookingEmailNotification(franchise.getFranchise().getBookingEmailNotification());
        customerProfile.setEnableSi(franchise.getFranchise().getEnableSi());
        customerProfile.setAreaId(franchise.getFranchise().getAreaId());
        newCutomerProfileVo.setCustomerProfile(customerProfile);
        newCutomerProfileVo.setCollection(franchise.getCollection());
        SaveCustomerProfileBaseRateVo saveCustProfileBaseRate = new SaveCustomerProfileBaseRateVo();
        saveCustProfileBaseRate.setMinimunBaseCharge(0D);
        List<CustomerProfileBaseRateVo> customerProfileBaseRateVos = new ArrayList<CustomerProfileBaseRateVo>();
        CustomerProfileBaseRateVo customerProfileBaseRateVo;
        List<CustomerBaseRateVo> customerBaseRateVos = franchise.getCustomerBaseRates();
        for (CustomerBaseRateVo customerBaseRateVo : customerBaseRateVos) {
            customerProfileBaseRateVo = new CustomerProfileBaseRateVo();
            customerProfileBaseRateVo.setShipmentTypeId(customerBaseRateVo.getShipmentTypeId());
            customerProfileBaseRateVo.setRateType(customerBaseRateVo.getRateType());
            customerProfileBaseRateVo.setWeight(customerBaseRateVo.getWeight());
            customerProfileBaseRateVo.setRate(customerBaseRateVo.getRate());
            customerProfileBaseRateVo.setZoneCheck(customerBaseRateVo.getZoneCheck());
            customerProfileBaseRateVo.setContent(customerBaseRateVo.getContent());
            customerProfileBaseRateVo.setBound(customerBaseRateVo.getBound());
            customerProfileBaseRateVo.setBaseRateDescription(customerBaseRateVo.getBaserateDescription());
            customerProfileBaseRateVo.setCarrierId(customerBaseRateVo.getCarrierId());
            if (customerBaseRateVo.getZoneCheck() && customerBaseRateVo.getCustomerBaseRateDetails() != null && customerBaseRateVo.getCustomerBaseRateDetails().size() > 0) {
                List<CustomerProfileBaseRateDetailVo> customerProfileBaseRateDetail = new ArrayList<CustomerProfileBaseRateDetailVo>();
                List<CustomerBaseRateDetailVo> customerBaseRateDetails = customerBaseRateVo.getCustomerBaseRateDetails();
                for (CustomerBaseRateDetailVo customerBaseRateDetailVo : customerBaseRateDetails) {
                    CustomerProfileBaseRateDetailVo customerProfileBaseRateDetailVo = new CustomerProfileBaseRateDetailVo();
                    customerProfileBaseRateDetailVo.setRate(customerBaseRateDetailVo.getRate());
                    customerProfileBaseRateDetailVo.setZone(customerBaseRateDetailVo.getZone());
                    customerProfileBaseRateDetail.add(customerProfileBaseRateDetailVo);
                }
                customerProfileBaseRateVo.setCustomerProfileBaseRateDetail(customerProfileBaseRateDetail);
            }
            customerProfileBaseRateVos.add(customerProfileBaseRateVo);
        }
        saveCustProfileBaseRate.setCustomerProfileBaseRates(customerProfileBaseRateVos);
        newCutomerProfileVo.setSaveCustProfileBaseRate(saveCustProfileBaseRate);
        return newCutomerProfileVo;
    }
}
