package com.gms.xms.workflow.task2.invoice.startrack;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDetailDao;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.WebshipRateSheetDetailVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Posted from Aug 12, 2016 10:49:03 AM
 * <p>
 * Author huynd
 */
public class GetStartrackBaseChargeBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetStartrackBaseChargeBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ShipmentBillingVo shipmentBilling = context.get(Attributes.SHIPMENT_BILLING_VO);
            ShipmentTypeVo shipmentTypeVo = context.get(Attributes.SHIPMENT_TYPE_RESULT);
            Long customerCode = context.get(Attributes.CUSTOMER_CODE);
            Double rowName = Math.ceil(shipmentBilling.getWeight());
            Double cRate = 0D;
            Double ncRate = 0D;
            // Franchise markup
            IFranchiseService franchiseService = new FranchiseServiceImp();
            FranchiseVo franchiseVo = franchiseService.getFranchiseInfoByCode(String.valueOf(customerCode).substring(0, 3));
            Double franchiseMarkup = 0D;
            if (franchiseVo != null) {
                franchiseMarkup = franchiseVo.getStartrackMarkupRate();
            }
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
            Double carrierBaseCharge = shipmentBilling.getCarrierCost();
            // Franchise cost for shipment billing
            Double franchiseCost = carrierBaseCharge + (carrierBaseCharge * franchiseMarkup) / 100;
            franchiseCost = MathUtils.round(franchiseCost, 2);

            RateSheetDetailDao rateSheetDetailDao = new RateSheetDetailDao();
            RateSheetDetailFilter rateSheetDetailFilter = new RateSheetDetailFilter();
            WebshipRateSheetDetailVo rateSheetDetailVo = new WebshipRateSheetDetailVo();
            if ("road express".equalsIgnoreCase(shipmentTypeVo.getShipmentTypeName()) || "premium air freight".equalsIgnoreCase(shipmentTypeVo.getShipmentTypeName())) {
                List<String> serviceAreaCode = context.get(Attributes.SERVICE_AREA_CODE);
                if (serviceAreaCode == null || serviceAreaCode.size() == 0) {
                    ShipmentBillingVo billingCost = new ShipmentBillingVo();
                    billingCost.setCalculatedCarrierCost(shipmentBilling.getCarrierCost());
                    billingCost.setFranchiseCost(shipmentBilling.getFranchiseCost());
                    billingCost.setCustomerCost(shipmentBilling.getCustomerCost());
                    billingCost.setCustomerTaxPercent(shipmentBilling.getCustomerTaxPercent());
                    billingCost.setCustomerTaxAmount(shipmentBilling.getCustomerTaxAmount());
                    context.put(Attributes.BILLING_COST, billingCost);
                    return true;
                }
                String originCode = "", destinationCode = "";
                Double baseCharge = 0D, minCharge = 0D, perKg = 0D;
                Double cBaseCharge = 0D, ncBaseCharge = 0D, cPerKg = 0D, ncPerKg = 0D, cMinCharge = 0D, ncMinCharge = 0D;
                List<Double> cRateArr = null;
                List<Double> ncRateArr = null;
                for (String areaCode : serviceAreaCode) {
                    String[] zoneArr = areaCode.split("###");
                    cRateArr = new ArrayList<Double>();
                    ncRateArr = new ArrayList<Double>();
                    originCode = (zoneArr[0].length() < 3) ? "" : zoneArr[0].substring(0, 3);
                    destinationCode = (zoneArr[1].length() < 3) ? "" : zoneArr[1].substring(0, 3);
                    if (shipmentTypeVo.getAllowCarrier()) {
                        rateSheetDetailFilter.setSheetId(shipmentTypeVo.getCarrierDocumentRate());
                        rateSheetDetailFilter.setColumnName(destinationCode);
                        rateSheetDetailFilter.setCharRowName(originCode);
                        WebshipRateSheetDetailVo startrackRateSheetDetail = rateSheetDetailDao.selectBaseChargeByCharRowAndCol(rateSheetDetailFilter);
                        if (startrackRateSheetDetail != null) {
                            baseCharge = startrackRateSheetDetail.getBaseCharge();
                            minCharge = startrackRateSheetDetail.getMinCharge();
                            perKg = startrackRateSheetDetail.getPerKg();
                        }
                        // Get TNT Weight Break
                        rateSheetDetailFilter.setRowName(rowName);
                        WebshipRateSheetDetailVo startrackWeightBreakDetail = rateSheetDetailDao.selectTntWeightBreak(rateSheetDetailFilter);
                        if (startrackWeightBreakDetail != null) {
                            if (startrackWeightBreakDetail.getRateSheetRow().getCharRowName().equalsIgnoreCase("D")) {
                                Double breakAmount = startrackWeightBreakDetail.getValue();
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
                                baseCharge = startrackWeightBreakDetail.getValue();
                            }
                        }
                    } else {
                        baseCharge = shipmentTypeVo.getBasicCharge();
                    }
                    // Get customer base rate
                    Double baseRate = 0D;
                    Integer rateType = 1;
                    if (customerBaseRateVo != null) {
                        if (customerBaseRateVo.getZoneCheck()) {
                            customerBaseRateFilter.setZone(destinationCode);
                            customerBaseRateFilter.setCustomerBaseRateId(customerBaseRateVo.getCustomerBaseRateId());
                            baseRate = customerBaseRateDao.selectCustomerBaseRateByZone(customerBaseRateFilter);
                        } else {
                            baseRate = customerBaseRateVo.getRate();
                        }
                        rateType = customerBaseRateVo.getRateType();
                    }
                    cBaseCharge = MathUtils.round((baseCharge + (baseCharge * franchiseMarkup) / 100), 2);
                    ncBaseCharge = ShipmentUtils.calculateCostByBaseRate(rateType, baseRate, miniumBaseChargePercent, cBaseCharge, ncBaseCharge);
                    ncBaseCharge = MathUtils.round(ncBaseCharge, 2);

                    cPerKg = MathUtils.round((perKg + (perKg * franchiseMarkup) / 100), 2);
                    ncPerKg = ShipmentUtils.calculateCostByBaseRate(rateType, baseRate, miniumBaseChargePercent, cPerKg, ncPerKg);
                    ncPerKg = MathUtils.round(ncPerKg, 2);

                    cMinCharge = MathUtils.round((minCharge + (minCharge * franchiseMarkup) / 100), 2);
                    ncMinCharge = ShipmentUtils.calculateCostByBaseRate(rateType, baseRate, miniumBaseChargePercent, cMinCharge, ncMinCharge);
                    ncMinCharge = MathUtils.round(ncMinCharge, 2);

                    cRate = cBaseCharge + (rowName * cPerKg);
                    if (cRate < cMinCharge) {
                        cRate = cMinCharge;
                    }
                    cRate = MathUtils.round(cRate, 2);
                    if (cRate != 0) {
                        cRateArr.add(cRate);
                    }
                    ncRate = ncBaseCharge + (rowName * ncPerKg);
                    if (ncRate < ncMinCharge) {
                        ncRate = ncMinCharge;
                    }
                    ncRate = MathUtils.round(ncRate, 2);
                    if (ncRate != 0) {
                        ncRateArr.add(ncRate);
                    }
                }
                cRate = (cRateArr != null && cRateArr.size() > 0) ? Collections.min(cRateArr) : 0.0;
                ncRate = (ncRateArr != null && ncRateArr.size() > 0) ? Collections.min(ncRateArr) : 0.0;
            } else {
                Double minDocWeightValueCarrier = 0D, minDocWeightCarrier = 0D, maxDocValueNonCarrier = 0D;
                Double maxDocWeightCarrier = 0D, maxDocValueCarrier = 0D, maxOverDocWeight = 0D;
                Double pakMaxOverDocWeight = 0D;
                Integer contents = 0;
                Boolean isDocMustBeCalByPackage = false;
                Double maxPackageWeight = 0D, maxPackageValue = 0D;
                Double overWeightAmount = 0D, overWeightValueAmount = 0D;
                String columnName = context.get(Attributes.STARTRACK_ZONE);
                if (columnName == null || StringUtils.isBlank(columnName)) {
                    ShipmentBillingVo billingCost = new ShipmentBillingVo();
                    billingCost.setCalculatedCarrierCost(shipmentBilling.getCarrierCost());
                    billingCost.setFranchiseCost(shipmentBilling.getFranchiseCost());
                    billingCost.setCustomerCost(shipmentBilling.getCustomerCost());
                    billingCost.setCustomerTaxPercent(shipmentBilling.getCustomerTaxPercent());
                    billingCost.setCustomerTaxAmount(shipmentBilling.getCustomerTaxAmount());
                    context.put(Attributes.BILLING_COST, billingCost);
                    return true;
                }
                rateSheetDetailFilter.setColumnName(columnName);
                rateSheetDetailFilter.setRowName(rowName);
                rateSheetDetailFilter.setSheetId(shipmentTypeVo.getCarrierDocumentRate());
                rateSheetDetailVo = rateSheetDetailDao.selectMinWeightAndMinValueByFilter(rateSheetDetailFilter);
                if (rateSheetDetailVo != null) {
                    minDocWeightCarrier = rateSheetDetailVo.getMinWeight();
                    minDocWeightValueCarrier = rateSheetDetailVo.getMinValue();
                }
                if (rowName <= minDocWeightCarrier) {
                    cRate = minDocWeightValueCarrier;
                    ncRate = cRate;
                } else {
                    // Max document
                    rateSheetDetailFilter.setSheetId(shipmentTypeVo.getNonCarrierDocumentRate());
                    rateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                    if (rateSheetDetailVo != null) {
                        maxDocValueNonCarrier = rateSheetDetailVo.getMaxValue();
                    }
                    rateSheetDetailFilter.setSheetId(shipmentTypeVo.getCarrierDocumentRate());
                    rateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                    if (rateSheetDetailVo != null) {
                        maxDocWeightCarrier = rateSheetDetailVo.getMaxWeight();
                        maxDocValueCarrier = rateSheetDetailVo.getMaxValue();
                    }
                    // Max document overweight
                    rateSheetDetailFilter.setSheetId(shipmentTypeVo.getCarrierPackageRate());
                    rateSheetDetailFilter.setMaxRowName(maxDocWeightCarrier);
                    rateSheetDetailVo = rateSheetDetailDao.selectMaxOverWeight(rateSheetDetailFilter);
                    if (rateSheetDetailVo != null) {
                        maxOverDocWeight = rateSheetDetailVo.getMaxWeight();
                    }
                    rateSheetDetailFilter.setSheetId(shipmentTypeVo.getCarrierPakRate());
                    rateSheetDetailVo = rateSheetDetailDao.selectMaxOverWeight(rateSheetDetailFilter);
                    if (rateSheetDetailVo != null) {
                        pakMaxOverDocWeight = rateSheetDetailVo.getMaxWeight();
                    }
                    if (rowName >= maxDocWeightCarrier && (rowName < maxOverDocWeight || rowName < pakMaxOverDocWeight)) {
                        ncRate = maxDocValueNonCarrier;
                        cRate = maxDocValueCarrier;
                    } else if (rowName >= maxDocWeightCarrier && (rowName > maxOverDocWeight || rowName < pakMaxOverDocWeight)) {
                        contents = 1;
                        isDocMustBeCalByPackage = true;
                    } else {
                        rateSheetDetailFilter.setSheetId(shipmentTypeVo.getNonCarrierDocumentRate());
                        rateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (rateSheetDetailVo != null) {
                            ncRate = rateSheetDetailVo.getValue();
                        }
                        rateSheetDetailFilter.setSheetId(shipmentTypeVo.getCarrierDocumentRate());
                        rateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (rateSheetDetailVo != null) {
                            cRate = rateSheetDetailVo.getValue();
                        }
                    }
                }
                if (contents == 1 || isDocMustBeCalByPackage) {
                    rateSheetDetailFilter.setSheetId(shipmentTypeVo.getCarrierPackageRate());
                    rateSheetDetailVo = rateSheetDetailDao.selectMaxWeightAndMaxValueByFilter(rateSheetDetailFilter);
                    if (rateSheetDetailVo != null) {
                        maxPackageWeight = rateSheetDetailVo.getMaxWeight();
                        maxPackageValue = rateSheetDetailVo.getMaxValue();
                    }
                    if (rowName > maxPackageWeight) {
                        overWeightAmount = rowName - maxPackageWeight;
                        overWeightAmount = Math.ceil(overWeightAmount);
                        // Get per weight rate
                        rateSheetDetailFilter.setSheetId(shipmentTypeVo.getCarrierPackagePerWeightRate());
                        rateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (rateSheetDetailVo != null) {
                            overWeightValueAmount = rateSheetDetailVo.getValue() * overWeightAmount;
                        }
                        cRate = maxPackageValue + overWeightValueAmount;
                        cRate = cRate + ((cRate * franchiseMarkup) / 100);
                        cRate = MathUtils.round(cRate, 2);
                    } else {
                        rateSheetDetailFilter.setSheetId(shipmentTypeVo.getCarrierPackageRate());
                        rateSheetDetailFilter.setType("upper");
                        rateSheetDetailVo = rateSheetDetailDao.selectBaseChargeWebShip(rateSheetDetailFilter);
                        if (rateSheetDetailVo != null) {
                            cRate = rateSheetDetailVo.getValue();
                        }
                        cRate = cRate + ((cRate * franchiseMarkup) / 100);
                        cRate = MathUtils.round(cRate, 2);
                    }
                }
                // Get customer base rate
                Double baseRate = 0D;
                Integer rateType = 0;
                if (customerBaseRateVo != null) {
                    baseRate = customerBaseRateVo.getRate();
                    rateType = customerBaseRateVo.getRateType();
                }
                cRate = cRate + (cRate * franchiseMarkup) / 100;
                cRate = MathUtils.round(cRate, 2);
                Map<String, Double> rate = ShipmentUtils.calculateBaseCharge(rateType, baseRate, miniumBaseChargePercent, rowName, cRate, ncRate);
                cRate = rate.get("cRate");
                ncRate = rate.get("ncRate");
            }
            // Carrier cost for shipment billing
            Double carrierCost = cRate == 0 ? carrierBaseCharge : cRate;
            // Customer cost for shipment billing
            Double customerCost = ncRate == 0 ? carrierBaseCharge : ncRate;
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
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }

        return true;
    }
}
