package com.gms.xms.workflow.task2.invoice.dhl.domestic.downloadbilling;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from Jul 25, 2016 4:37:33 PM
 * <p>
 * Author huynd
 */
public class GetDhlDomesticDownloadBillingBaseChargeBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlDomesticDownloadBillingBaseChargeBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            ShipmentTypeVo shipmentTypeVo = context.get(Attributes.SHIPMENT_TYPE_RESULT);
            Double rowName = Math.ceil(Double.valueOf(shipmentBilling.getWeight()));
            Double cRate = 0D;
            Double ncRate = 0D;
            // Minium 1 KG
            if (rowName < 1) {
                rowName = 1D;
            }
            String columnName = shipmentBilling.getZone();
            if ("0".equalsIgnoreCase(columnName) || StringUtils.isBlank(columnName) || columnName == null) {
                ShipmentBillingVo billingCost = new ShipmentBillingVo();
                billingCost.setCarrierCost(shipmentBilling.getCarrierCost());
                billingCost.setFranchiseCost(shipmentBilling.getFranchiseCost());
                billingCost.setCustomerCost(shipmentBilling.getCustomerCost());
                billingCost.setCustomerTaxPercent(shipmentBilling.getCustomerTaxPercent());
                billingCost.setCustomerTaxAmount(shipmentBilling.getCustomerTaxAmount());
                context.put(Attributes.BILLING_COST, billingCost);
                return true;
            }
            Long sheetId = context.get(Attributes.DHL_DOMESTIC_RATE_SHEET_ID);
            Long perWeightSheetId = context.get(Attributes.DHL_DOMESTIC_PER_WEIGHT_RATE_SHEET_ID);

            // Get max weight and max value
            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            RateSheetDetailFilter rateSheetDetailFilter = new RateSheetDetailFilter();
            rateSheetDetailFilter.setSheetId(sheetId);
            rateSheetDetailFilter.setColumnName(columnName);
            WebshipRateSheetDetailVo rateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);

            Double maxWeight = rateSheetDetailVo.getMaxWeight();
            Double maxValue = rateSheetDetailVo.getMaxValue();

            // Get carrier cost
            if (rowName > maxWeight) {
                rateSheetDetailFilter.setRowName(rowName);
                rateSheetDetailFilter.setColumnName(columnName);
                rateSheetDetailFilter.setSheetId(perWeightSheetId);
                rateSheetDetailVo = rateSheetDetailDao.selectDhlDomesticBaseChargeRate(rateSheetDetailFilter);
                Double perWeightRate = rateSheetDetailVo.getValue();
                Double perWeight = Math.ceil(rowName - maxWeight);
                cRate = maxValue + (perWeight * perWeightRate);
            } else {
                rateSheetDetailFilter.setRowName(rowName);
                rateSheetDetailFilter.setColumnName(columnName);
                rateSheetDetailFilter.setSheetId(sheetId);
                rateSheetDetailVo = rateSheetDetailDao.selectDhlDomesticBaseChargeRate(rateSheetDetailFilter);
                cRate = rateSheetDetailVo.getValue();
            }

            Long customerCode = context.get(Attributes.CUSTOMER_CODE);
            // Franchise markup
            IFranchiseService franchiseService = new FranchiseServiceImp();
            FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(customerCode).substring(0, 3));
            Double franchiseMarkup = 0D;
            if (franchiseVo.getMarkupRate() != null) {
                franchiseMarkup = franchiseVo.getDhlDomMarkupRate();
            }

            cRate = cRate + (cRate * franchiseMarkup) / 100;
            cRate = MathUtils.round(cRate, 2);

            Double carrierBaseCharge = shipmentBilling.getCarrierCost();
            // Franchise cost for shipment billing
            Double franchiseCost = carrierBaseCharge + (carrierBaseCharge * franchiseMarkup) / 100;
            franchiseCost = MathUtils.round(franchiseCost, 2);

            // Get customer base rate
            CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
            CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();
            customerBaseRateFilter.setCustomerCode(customerCode);
            customerBaseRateFilter.setShipmentTypeId(shipmentTypeVo.getShipmentTypeId());
            customerBaseRateFilter.setWeight(rowName);
            CustomerBaseRateVo customerBaseRateVo = customerBaseRateDao.selectCustomerBaseRateByCustomerCodeAndShipmentTypeId(customerBaseRateFilter);

            // Get minimum base charge cost
            Double miniumBaseChargePercent = 0D;
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo = new CustomerVo();
            customerVo = customerDao.selectMinimumBaseChargeByCustomerCode(customerCode);
            if (customerVo != null) {
                miniumBaseChargePercent = customerVo.getMinimunBaseCharge();
            }

            // Get customer base rate
            Double baseRate = 0D;
            Integer rateType = 0;
            if (customerBaseRateVo != null) {
                if (customerBaseRateVo.getZoneCheck()) {
                    customerBaseRateFilter.setZone(columnName);
                    customerBaseRateFilter.setCustomerBaseRateId(customerBaseRateVo.getCustomerBaseRateId());
                    baseRate = customerBaseRateDao.selectCustomerBaseRateByZone(customerBaseRateFilter);
                } else {
                    baseRate = customerBaseRateVo.getRate();
                }
                rateType = customerBaseRateVo.getRateType();
            }

            Map<String, Double> rate = ShipmentUtils.calculateBaseCharge(rateType, baseRate, miniumBaseChargePercent, rowName, cRate, ncRate);
            cRate = rate.get("cRate");
            ncRate = rate.get("ncRate");

            cRate = (cRate == 0) ? shipmentBilling.getCarrierCost() : MathUtils.round(cRate, 2);
            ncRate = (ncRate == 0) ? shipmentBilling.getCustomerCost() : MathUtils.round(ncRate, 2);

            // Carrier cost for shipment billing
            Double carrierCost = cRate;
            // Customer cost for shipment billing
            Double customerCost = ncRate;
            // Customer tax percent for shipment billing
            Double customerTaxPercent = shipmentBilling.getCustomerTaxPercent();
            // Customer tax amount for shipment billing
            Double customerTaxAmount = (customerCost * customerTaxPercent) / 100;
            customerTaxAmount = MathUtils.round(customerTaxAmount, 2);

            ShipmentBillingVo billingCost = new ShipmentBillingVo();
            billingCost.setCarrierCost(carrierCost);
            billingCost.setFranchiseCost(franchiseCost);
            billingCost.setCustomerCost(customerCost);
            billingCost.setCustomerTaxPercent(customerTaxPercent);
            billingCost.setCustomerTaxAmount(customerTaxAmount);
            context.put(Attributes.BILLING_COST, billingCost);
        } catch (Exception e) {
            log.error(e);
            return false;
        }

        return true;
    }
}
