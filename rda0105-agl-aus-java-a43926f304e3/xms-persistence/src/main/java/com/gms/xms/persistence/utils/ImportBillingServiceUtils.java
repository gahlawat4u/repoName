package com.gms.xms.persistence.utils;

import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.*;

import java.util.Date;
import java.util.Map;

public class ImportBillingServiceUtils {
    public static void determineWarrantySerice(Map<String, String> context, Long customerCode, Long shipmentId, String airbillNumber, Integer serviceId, String description, SqlSessionClient sessionClient) throws Exception {
        Boolean enableSi = false;
        if (customerCode == null || customerCode == 0) {
            return;
        }
        String code = String.valueOf(customerCode);
        if (code.length() > 3 && code.substring(3, code.length()).equalsIgnoreCase("00001")) {
            FranchiseDao franchiseDao = new FranchiseDao();
            FranchiseVo franchiseVo = franchiseDao.selectByFranchiseCode(code);
            if (franchiseVo != null && franchiseVo.getEnableSi()) {
                enableSi = true;
            }
        } else {
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo = customerDao.selectByCode(code);
            if (customerVo != null && customerVo.getEnableSi()) {
                enableSi = true;
            }
        }
        if (serviceId == 1 || serviceId == 15) { // DHL Intl & DHL Domestic
            if ("NRI-NON REVENUE ITEM".equalsIgnoreCase(description) || "NON REVENUE ITEM".equalsIgnoreCase(description)) {
                return;
            }
        }
        WarrantyServiceDao warrantyServiceDao = new WarrantyServiceDao();
        ShipmentBillingFilter filter = new ShipmentBillingFilter();
        filter.setShipmentId(shipmentId);
        filter.setAirbillNumber(airbillNumber);
        filter.setCarrier(Long.valueOf(serviceId));
        Integer countWSShipment = warrantyServiceDao.countWarrantyServiceShipment(filter);
        Integer countWSShipmentBilling = warrantyServiceDao.countWarrantyServiceShipmentBilling(filter);
        if (countWSShipment == 0) {
            if (enableSi) {
                if (countWSShipmentBilling == 0) {
                    buildWarrantyServiceAccess(context, customerCode, shipmentId, airbillNumber, serviceId, sessionClient);
                }
            } else {
                if (countWSShipmentBilling > 0) {
                    ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
                    ShipmentBillingVo shipmentBillingVo = new ShipmentBillingVo();
                    shipmentBillingVo.setShipmentId(shipmentId);
                    shipmentBillingVo.setAirbillNumber(airbillNumber);
                    shipmentBillingDao.deleteWarrantyService(context, shipmentBillingVo);
                }
            }
        } else {
            if (countWSShipmentBilling == 0) {
                buildWarrantyServiceAccess(context, customerCode, shipmentId, airbillNumber, serviceId, sessionClient);
            }
        }
    }

    protected static void buildWarrantyServiceAccess(Map<String, String> context, Long customerCode, Long shipmentId, String airbillNumber, Integer serviceId, SqlSessionClient sessionClient) throws Exception {
        AccessorialDao accessorialDao = new AccessorialDao(sessionClient);
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao(sessionClient);
        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
        shipmentBillingFilter.setShipmentId(shipmentId);
        shipmentBillingFilter.setAirbillNumber(airbillNumber);
        ShipmentBillingVo shipmentBillingVo = shipmentBillingDao.selectIsBaseChargeByFilter(shipmentBillingFilter);
        Long senderCountryId = shipmentBillingVo.getOriginCountryId();
        Long receiverCountryId = shipmentBillingVo.getDestinationCountryId();
        Double domGST = 0D;
        String description = "agl Warranty";
        SystemSettingDao systemSettingDao = new SystemSettingDao();
        Long defaultCountry = systemSettingDao.getSystemSettingByName("Default Origin Country").getSettingValue() != null ? Long.valueOf(systemSettingDao.getSystemSettingByName("Default Origin Country").getSettingValue()) : 0;
        if (defaultCountry == senderCountryId && senderCountryId == receiverCountryId) {
            description += " Domestic";
            domGST = Double.valueOf(systemSettingDao.getSystemSettingByName("agl Warranty Domestic GST").getSettingValue());
        } else {
            description += " International";
        }

        AccessorialVo accessorialVo = new AccessorialVo();
        accessorialVo.setCarrier(Long.valueOf(serviceId));
        accessorialVo.setDescription(description);
        AccessorialVo accessorialVoCheck = new AccessorialVo();
        AccessorialDetailDao accessorialDetailDao = new AccessorialDetailDao(sessionClient);
        AccessorialDetailVo accessorialDetailVo = new AccessorialDetailVo();
        accessorialVoCheck = accessorialDao.checkAccessorialByDescription(accessorialVo);
        if (accessorialVoCheck == null) {
            accessorialVo.setCode(description);
            accessorialVo.setTypeId(1);
            accessorialVo.setIsQuotable(0);
            accessorialVo.setModifiedDate(new Date());
            accessorialDao.insertAccessorial(context, accessorialVo);
        } else {
            AccessorialDetailFilter accessorialDetailFilter = new AccessorialDetailFilter();
            accessorialDetailFilter.setAccessorialId(accessorialVoCheck.getAccessorialId());
            accessorialDetailVo = accessorialDetailDao.selectByFilter(accessorialDetailFilter);
        }

        Double customerCost = 0d;
        Double franchiseCost = 0d;
        if (accessorialDetailVo != null) {
            customerCost = accessorialDetailVo.getDefaultCharge() != null ? accessorialDetailVo.getDefaultCharge().doubleValue() : 0d;
            franchiseCost = accessorialDetailVo.getDefaultChargeCarrier() != null ? accessorialDetailVo.getDefaultChargeCarrier().doubleValue() : 0d;
        }

        // Recalculate tax
        Double franchiseTaxAmount = 0d;
        Double carrierTaxAmount = 0D;
        try {
            franchiseTaxAmount = franchiseCost * domGST / 100;
            carrierTaxAmount = franchiseCost * domGST / 100;
        } catch (Exception e) {
        }

        Double customerTaxAmt = customerCost * domGST / 100;
        customerTaxAmt = MathUtils.round(customerTaxAmt, 2);
        Integer accessorialCount = shipmentBillingDao.selectMaxAccessorialCount(shipmentBillingFilter) + 1;
        shipmentBillingVo.setIsBaseCharge(false);
        shipmentBillingVo.setAccessorialCount(accessorialCount);
        shipmentBillingVo.setDescription(description);
        shipmentBillingVo.setDisplayDescription(description);
        shipmentBillingVo.setCarrierCost(franchiseCost);
        shipmentBillingVo.setCarrierTaxPercent(domGST);
        shipmentBillingVo.setTaxAmount(carrierTaxAmount);
        shipmentBillingVo.setCustomerCost(customerCost);
        shipmentBillingVo.setFranchiseCost(franchiseCost);
        shipmentBillingVo.setFranchiseTaxAmount(franchiseTaxAmount);
        shipmentBillingVo.setCalculatedCarrierCost(franchiseCost);
        shipmentBillingVo.setCalculatedFranchiseCost(franchiseCost);
        shipmentBillingVo.setRequireCalculate(false);
        shipmentBillingVo.setCustomerTaxPercent(domGST);
        shipmentBillingVo.setCustomerTaxAmount(customerTaxAmt);
        shipmentBillingDao.insertShipmentBillingSurcharge(context, shipmentBillingVo);
    }
}
