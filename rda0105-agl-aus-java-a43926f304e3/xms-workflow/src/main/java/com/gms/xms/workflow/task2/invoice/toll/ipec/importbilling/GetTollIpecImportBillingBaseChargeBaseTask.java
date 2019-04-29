package com.gms.xms.workflow.task2.invoice.toll.ipec.importbilling;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerBaseRateDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.TollIpecKgRateDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Jul 22, 2016 9:43:30 PM
 * <p>
 * Author huynd
 */
public class GetTollIpecImportBillingBaseChargeBaseTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollIpecImportBillingBaseChargeBaseTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            SaveImportBillingVo saveImportBillingVo = context.get(Attributes.IMPORT_BILLING_VO);
            ShipmentBillingVo shipmentBilling = saveImportBillingVo.getBillingBaseCharge();
            Long customerCode = context.get(Attributes.CUSTOMER_CODE);
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeFilter shipmentTypeFilter = new ShipmentTypeFilter();

            shipmentTypeFilter.setShipmentTypeName(shipmentBilling.getDescription());
            shipmentTypeFilter.setServiceId(Integer.valueOf(shipmentBilling.getCarrier().toString()));
            ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectByShipmentTypeNameAndCarrier(shipmentTypeFilter);

            Double rowName = Math.ceil(shipmentBilling.getWeight());
            TollIpecTotalRateVo tollIpecTotalRateVo = context.get(Attributes.TOLL_RATE_SHEET);
            TollIpecKgRateVo tollIpecKgRateVo = new TollIpecKgRateVo();
            TollIpecKgRateDao tDao = new TollIpecKgRateDao();
            TollIpecKgRateFilter tFilter = new TollIpecKgRateFilter();
            tFilter.setFromZone(context.getString(Attributes.SENDER_ZONE_CODE));
            tFilter.setToZone(context.getString(Attributes.RECEIVER_ZONE_CODE));
            tFilter.setServiceTypeName(shipmentTypeVo.getShipmentTypeName());
            String senderZoneCode = context.getString(Attributes.SENDER_ZONE_CODE);
            String receiverZoneCode = context.getString(Attributes.RECEIVER_ZONE_CODE);
            if (senderZoneCode == null || StringUtils.isBlank(senderZoneCode) || receiverZoneCode == null || StringUtils.isBlank(receiverZoneCode)) {
                ShipmentBillingVo billingCost = new ShipmentBillingVo();
                billingCost.setCarrierCost(shipmentBilling.getCarrierCost());
                billingCost.setFranchiseCost(shipmentBilling.getFranchiseCost());
                billingCost.setCustomerCost(shipmentBilling.getCustomerCost());
                billingCost.setCustomerTaxPercent(shipmentBilling.getCustomerTaxPercent());
                billingCost.setCustomerTaxAmount(shipmentBilling.getCustomerTaxAmount());
                context.put(Attributes.BILLING_COST, billingCost);
                return true;
            }
            String columnName = senderZoneCode.concat("-").concat(receiverZoneCode);
            Double conRate = 0D;
            Map<String, Double> baseChargeResult = new HashMap<>();

            String defaultOriginCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
            if (defaultOriginCountry == "91" && rowName > 30) {
                rowName = MathUtils.shipmentWeightRound(rowName, true);
            }

            Double carrierDocumentRate = (double) shipmentTypeVo.getCarrierDocumentInboundRate();
            Double carrierPackageRate = (double) shipmentTypeVo.getCarrierPackageInboundRate();
            Double carrierPackagePerweightRate = (double) shipmentTypeVo.getCarrierPakInboundPerWeightRate();
            Double nonCarrierDocumentRate = (double) shipmentTypeVo.getNonCarrierDocumentInboundRate();
            Double nonCarrierPackageRate = (double) shipmentTypeVo.getNonCarrierPackageInboundRate();
            Double nonCarrierPackagePerweightRate = (double) shipmentTypeVo.getNonCarrierPackageInboundPerWeightRate();
            if (nonCarrierDocumentRate == 0) {
                nonCarrierDocumentRate = carrierDocumentRate;
            }
            if (nonCarrierPackageRate == 0) {
                nonCarrierPackageRate = carrierPackageRate;
            }
            if (nonCarrierPackagePerweightRate == 0) {
                nonCarrierPackagePerweightRate = carrierPackagePerweightRate;
            }

            String findDash[] = columnName.split("-");
            if (columnName.equals("-") || findDash.length < 2 || columnName.equals("--")) {
                baseChargeResult.put("ncRate", 0D);
                baseChargeResult.put("cRate", 0D);
            } else {
                if (shipmentTypeVo.getShipmentTypeName().equals("PRIORITY") || shipmentTypeVo.getShipmentTypeName().equals("ROAD EXPRESS")) {
                    tollIpecKgRateVo = tDao.selectByFilter(tFilter);
                    if (tollIpecKgRateVo != null) {
                        Double basic = tollIpecKgRateVo.getBasic();
                        Double freightFactor = tollIpecKgRateVo.getFreight();
                        Double freightCharge = 0D;
                        Double minimum = tollIpecKgRateVo.getMinimum();
                        Double overweight = rowName;
                        if (shipmentTypeVo.getShipmentTypeName().equals("PRIORITY") && (tFilter.getFromZone().equals("SEQLD") || tFilter.getFromZone().equals("BNE") || tFilter.getToZone().equals("SEQLD") || tFilter.getToZone().equals("BNE"))) {
                            if (rowName > 5) {
                                overweight = (double) (rowName - 5);
                            } else {
                                overweight = 0D;
                            }
                        }
                        freightCharge = (overweight * freightFactor) / 100;
                        if ((basic + freightCharge) < minimum) {
                            freightCharge = 0D;
                            basic = minimum;
                        }
                        conRate = basic + freightCharge;
                    }
                } else if (shipmentTypeVo.getShipmentTypeName().equals("TOLL DIRECT") || shipmentTypeVo.getShipmentTypeName().equals("DIRECT")) {

                    Integer maximumWeight = tollIpecTotalRateVo.getRange7();
                    if (rowName <= tollIpecTotalRateVo.getRange1()) {
                        conRate = tollIpecTotalRateVo.getCharge1();
                    } else if (rowName > tollIpecTotalRateVo.getRange1() && rowName <= tollIpecTotalRateVo.getRange2()) {
                        conRate = tollIpecTotalRateVo.getCharge2();
                    } else if (rowName > tollIpecTotalRateVo.getRange2() && rowName <= tollIpecTotalRateVo.getRange3()) {
                        conRate = tollIpecTotalRateVo.getCharge3();
                    } else if (rowName > tollIpecTotalRateVo.getRange3() && rowName <= tollIpecTotalRateVo.getRange4()) {
                        conRate = tollIpecTotalRateVo.getCharge4();
                    } else if (rowName > tollIpecTotalRateVo.getRange4() && rowName <= tollIpecTotalRateVo.getRange5()) {
                        conRate = tollIpecTotalRateVo.getCharge5();
                    } else if (rowName > tollIpecTotalRateVo.getRange5() && rowName <= tollIpecTotalRateVo.getRange6()) {
                        conRate = tollIpecTotalRateVo.getCharge6();
                    } else if (rowName > tollIpecTotalRateVo.getRange6()) {
                        conRate = tollIpecTotalRateVo.getCharge7();
                    }
                    Double subWeight = 0D;
                    Double perRate = 0D;
                    if (rowName > maximumWeight) {
                        subWeight = rowName - maximumWeight;
                        perRate = (subWeight * tollIpecTotalRateVo.getAdditional()) / 100;
                        conRate = conRate + perRate;
                    }
                }
            }

            Double cRate = 0D;
            cRate = conRate;
            // Get customer base rate
            CustomerBaseRateDao customerBaseRateDao = new CustomerBaseRateDao();
            CustomerBaseRateFilter customerBaseRateFilter = new CustomerBaseRateFilter();

            customerBaseRateFilter.setCustomerCode(customerCode);
            customerBaseRateFilter.setShipmentTypeId(shipmentTypeVo.getShipmentTypeId());
            customerBaseRateFilter.setContent(1);
            customerBaseRateFilter.setBound(0);
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

            // Base Rate Charge Calculation
            Double franchiseMarkup = 0D;
            FranchiseVo franchiseVo = new FranchiseVo();
            FranchiseDao fDao = new FranchiseDao();
            franchiseVo = fDao.selectFranchiseByFranchiseCode(String.valueOf(customerCode).substring(0, 3));
            if (!StringUtils.isEmpty(franchiseVo.getTollIpecMarkupRate().toString())) {
                franchiseMarkup = franchiseVo.getTollIpecMarkupRate();
            }

            Double carrierBaseCharge = shipmentBilling.getCarrierCost();
            // Franchise cost for shipment billing
            Double franchiseCost = carrierBaseCharge + (carrierBaseCharge * franchiseMarkup) / 100;
            franchiseCost = MathUtils.round(franchiseCost, 2);

            Double ncRate = 0D;
            cRate = cRate + ((cRate * franchiseMarkup) / 100);
            conRate = MathUtils.round(MathUtils.round(cRate, 3), 2);
            ncRate = ncRate + ((ncRate * franchiseMarkup) / 100);

            Map<String, Double> rate = ShipmentUtils.calculateBaseCharge(rateType, baseRate, miniumBaseChargePercent, rowName, cRate, ncRate);
            ncRate = rate.get("ncRate");
            cRate = rate.get("cRate");

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
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "There is an error in calculating base charge, please contact admin.");
            return false;
        }

        return true;
    }
}
