package com.gms.xms.workflow.task2.invoice.tnt.domestic;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.TntDomesticRateSheetVo;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from May 19, 2016 5:23:04 PM
 * <p>
 * Author huynd
 */
public class GetTntDomesticBaseChargeBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTntDomesticBaseChargeBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentBillingVo shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
            ShipmentTypeVo shipmentTypeVo = context.get(Attributes.SHIPMENT_TYPE_RESULT);
            Long customerCode = context.get(Attributes.CUSTOMER_CODE);
            Double rowName = Math.ceil(shipmentBilling.getWeight());
            Double cRate = 0D;
            Double ncRate = 0D;
            // Minium 1 KG
            if (rowName < 1) {
                rowName = 1D;
            }

            String columnName = context.get(Attributes.RECEIVER_ZONE_CODE);
            String charRowName = context.get(Attributes.SENDER_ZONE_CODE);
            if (StringUtils.isBlank(columnName) || StringUtils.isBlank(charRowName)) {
                ShipmentBillingVo billingCost = new ShipmentBillingVo();
                billingCost.setCalculatedCarrierCost(shipmentBilling.getCarrierCost());
                billingCost.setFranchiseCost(shipmentBilling.getFranchiseCost());
                billingCost.setCustomerCost(shipmentBilling.getCustomerCost());
                billingCost.setCustomerTaxPercent(shipmentBilling.getCustomerTaxPercent());
                billingCost.setCustomerTaxAmount(shipmentBilling.getCustomerTaxAmount());
                context.put(Attributes.BILLING_COST, billingCost);
                return true;
            }
            TntDomesticRateSheetVo domesticRateSheetVo = context.get(Attributes.TNT_RATE_SHEET);
            Long sheetId = domesticRateSheetVo.getCarrierDocumentRate();

            // Get max weight and max value
            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            RateSheetDetailFilter rateSheetDetailFilter = new RateSheetDetailFilter();
            rateSheetDetailFilter.setSheetId(sheetId);
            rateSheetDetailFilter.setColumnName(columnName);
            rateSheetDetailFilter.setCharRowName(charRowName);

            Double baseCharge = 0D;
            Double minCharge = 0D;
            Double perKg = 0D;
            // Get TNT Weight Break
            rateSheetDetailFilter.setRowName(rowName);
            WebshipRateSheetDetailVo tntWeightBreakDetail = rateSheetDetailDao.selectTntWeightBreak(rateSheetDetailFilter);
            if (tntWeightBreakDetail != null) {
                if (tntWeightBreakDetail.getRateSheetRow().getCharRowName().equalsIgnoreCase("D")) {
                    // Get TNT Base charge
                    WebshipRateSheetDetailVo tntRateSheetDetail = rateSheetDetailDao.selectBaseChargeByCharRowAndCol(rateSheetDetailFilter);
                    if (tntRateSheetDetail != null) {
                        baseCharge = tntRateSheetDetail.getBaseCharge();
                        minCharge = tntRateSheetDetail.getMinCharge();
                        perKg = tntRateSheetDetail.getPerKg();
                    }
                    Double breakAmount = tntWeightBreakDetail.getValue();
                    Double discBaseCharge = baseCharge * (breakAmount / 100);
                    discBaseCharge = MathUtils.round(discBaseCharge, 2);
                    baseCharge = baseCharge - discBaseCharge;

                    Double discMinCharge = minCharge * (breakAmount / 100);
                    discMinCharge = MathUtils.round(discMinCharge, 2);
                    minCharge = minCharge - discMinCharge;

                    Double discPerKg = perKg * (breakAmount / 100);
                    discPerKg = MathUtils.round(discPerKg, 2);
                    perKg = perKg - discPerKg;
                } else {
                    baseCharge = tntWeightBreakDetail.getValue();
                }
            } else {
                // Get TNT Base charge
                WebshipRateSheetDetailVo tntRateSheetDetail = rateSheetDetailDao.selectBaseChargeByCharRowAndCol(rateSheetDetailFilter);
                if (tntRateSheetDetail != null) {
                    baseCharge = tntRateSheetDetail.getBaseCharge();
                    minCharge = tntRateSheetDetail.getMinCharge();
                    perKg = tntRateSheetDetail.getPerKg();
                }
            }
            // Franchise markup
            IFranchiseService franchiseService = new FranchiseServiceImp();
            FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(customerCode).substring(0, 3));
            Double franchiseMarkup = 0D;
            if (franchiseVo.getTntMarkupRate() != null) {
                franchiseMarkup = franchiseVo.getTntMarkupRate();
            }

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

            Double cBaseCharge = 0D;
            Double ncBaseCharge = 0D;
            Double cPerKg = 0D;
            Double ncPerKg = 0D;
            Double cMinCharge = 0D;
            Double ncMinCharge = 0D;

            cBaseCharge = MathUtils.round((baseCharge + (baseCharge * franchiseMarkup) / 100), 2);
            ncBaseCharge = ShipmentUtils.calculateCostByBaseRate(rateType, baseRate, miniumBaseChargePercent, cBaseCharge, ncBaseCharge);

            cPerKg = MathUtils.round((perKg + (perKg * franchiseMarkup) / 100), 2);
            ncPerKg = ShipmentUtils.calculateCostByBaseRate(rateType, baseRate, miniumBaseChargePercent, cPerKg, ncPerKg);

            cMinCharge = MathUtils.round((minCharge + (minCharge * franchiseMarkup) / 100), 2);
            ncMinCharge = ShipmentUtils.calculateCostByBaseRate(rateType, baseRate, miniumBaseChargePercent, cMinCharge, ncMinCharge);

            cRate = cBaseCharge + (rowName * cPerKg);
            if (cRate < cMinCharge) {
                cRate = cMinCharge;
            }
            cRate = (cRate == 0) ? shipmentBilling.getCarrierCost() : MathUtils.round(cRate, 2);

            ncRate = ncBaseCharge + (rowName * ncPerKg);
            if (ncRate < ncMinCharge) {
                ncRate = ncMinCharge;
            }
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
            billingCost.setCalculatedCarrierCost(carrierCost);
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
