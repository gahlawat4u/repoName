package com.gms.xms.workflow.task2.toll.ipec;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.ServiceDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.TollIpecKgRateDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.dao.webship.ship.UsedRateWebshipDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.ship.UsedRateWebshipFilter;
import com.gms.xms.txndb.vo.webship.ship.UsedRateWebshipVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tkvcl
 */
public class GetTollIpecBaseChargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollIpecBaseChargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            UsedRateWebshipVo usedRateWebshipVo = new UsedRateWebshipVo();
            UsedRateWebshipDao dao = new UsedRateWebshipDao();
            UsedRateWebshipFilter filter = new UsedRateWebshipFilter();
            ShipmentInfoVo shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
            ShipmentTypeVo shipmentTypeVo = new ShipmentTypeVo();
            ShipmentTypeDao sDao = new ShipmentTypeDao();
            shipmentTypeVo = sDao.selectById(shipmentInfoVo.getShipmentTypeId());
            WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);
            Double quoteWeight = context.getDouble(Attributes.SHIPMENT_TOTAL_WEIGHT);
            Double rowName = Math.ceil(quoteWeight);
            TollIpecTotalRateVo tollIpecTotalRateVo = context.get(Attributes.TOLL_RATE_SHEET);
            TollIpecKgRateVo tollIpecKgRateVo = new TollIpecKgRateVo();
            TollIpecKgRateDao tDao = new TollIpecKgRateDao();
            TollIpecKgRateFilter tFilter = new TollIpecKgRateFilter();
            tFilter.setFromZone(context.getString(Attributes.SENDER_ZONE_CODE));
            tFilter.setToZone(context.getString(Attributes.RECEIVER_ZONE_CODE));
            tFilter.setServiceTypeName(shipmentTypeVo.getShipmentTypeName());
            Integer shipmentTypeId = shipmentTypeVo.getShipmentTypeId();
            Integer contents = shipmentInfoVo.getContents();
            String columnName = context.getString(Attributes.SENDER_ZONE_CODE).concat("-").concat(context.getString(Attributes.RECEIVER_ZONE_CODE));
            Double conRate = 0D;
            Map<String, Double> baseChargeResult = new HashMap<>();

            // INI ALL DAO
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ServiceDao serviceDao = new ServiceDao();

            // INI ALL VO
            ShipmentTypeVo shipmentType2Vo = new ShipmentTypeVo();
            shipmentType2Vo = shipmentTypeDao.selectByServiceType(shipmentTypeId);

            ServiceVo serviceVo = new ServiceVo();

            String defaultOriginCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
            String calServiceFlag = SystemSettingCache.getInstance().getValueByKey("Calculate Service Tax");
            Integer pieces = shipmentInfoVo.getPieces().size();
            Double defaultTaxPercent = 0D;
            String boundStatus = "0";
            if (defaultOriginCountry == "91" && rowName > 30) {
                rowName = MathUtils.shipmentWeightRound(rowName, true);
            }
            if (StringUtils.isEmpty(pieces.toString())) {
                pieces = 1;
            }

            if (StringUtils.isEmpty(defaultTaxPercent.toString())) {
                if (calServiceFlag == "1") {
                    defaultTaxPercent = 0D;
                } else {
                    serviceVo = serviceDao.selectById(shipmentType2Vo.getServiceId());
                    // 0-international ,1-domestic ,2-both
                    Integer carrierType = serviceVo.getCarrierType();
                    if (carrierType == 0) {
                        if (boundStatus.equals("EU0") || boundStatus.equals("EU0")) {
                            // EU Outbound and Inbound
                            defaultTaxPercent = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Tax Percentage for EU"));
                        } else {
                            defaultTaxPercent = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Tax Percentage"));
                        }
                    } else {
                        defaultTaxPercent = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Domestic Tax Percentage"));
                    }
                }
            }

            // France EU
            if (boundStatus.equals("EU0")) {
                boundStatus = "0";
            }
            if (boundStatus.equals("EU1")) {
                boundStatus = "1";
            }
            if (boundStatus.equals("15")) {
                boundStatus = "0";
            }

            // fix for Tax from Shipment Type
            Double docOutboundTax = shipmentType2Vo.getDocOutboundTax();
            Double nondocOutboundTax = shipmentType2Vo.getNonDocOutboundTax();
            Double docInboundTax = shipmentType2Vo.getDocInboundTax();
            Double nondocInboundTax = shipmentType2Vo.getNonDocInboundTax();

            if (boundStatus.equals("0")) { // Outbound
                if (contents == 0) // Document
                {
                    if (docOutboundTax != null) {
                        defaultTaxPercent = docOutboundTax;
                    }
                } else // Non Doc
                {
                    if (nondocOutboundTax != null) {
                        defaultTaxPercent = nondocOutboundTax;
                    }
                }
            } else { // Inbound

                if (contents == 0) { // Document
                    if (docInboundTax != null) {
                        defaultTaxPercent = docInboundTax;
                    }
                } else // Non Doc
                {
                    if (nondocInboundTax != null) {
                        defaultTaxPercent = nondocInboundTax;
                    }
                }

            }

            // forOutbound and Domestic
            Double carrierDocumentRate = (double) shipmentType2Vo.getCarrierDocumentInboundRate();
            Double carrierPackageRate = (double) shipmentType2Vo.getCarrierPackageInboundRate();
            Double carrierPackagePerweightRate = (double) shipmentType2Vo.getCarrierPakInboundPerWeightRate();
            Double nonCarrierDocumentRate = (double) shipmentType2Vo.getNonCarrierDocumentInboundRate();
            Double nonCarrierPackageRate = (double) shipmentType2Vo.getNonCarrierPackageInboundRate();
            Double nonCarrierPackagePerweightRate = (double) shipmentType2Vo.getNonCarrierPackageInboundPerWeightRate();
            if (nonCarrierDocumentRate == 0) {
                nonCarrierDocumentRate = carrierDocumentRate;
            }
            if (nonCarrierPackageRate == 0) {
                nonCarrierPackageRate = carrierPackageRate;
            }
            if (nonCarrierPackagePerweightRate == 0) {
                nonCarrierPackagePerweightRate = carrierPackagePerweightRate;
            }

            if (shipmentType2Vo.getServiceId() == 59) {// For Toll Ipec
                contents = 0;
                boundStatus = "0";
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
            }

            Double cRate = 0D;
            cRate = conRate;
            Integer rateType = 0;
            rateType = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("Default Base Rate Type"));
            filter.setRateType(rateType);
            filter.setBound(shipmentInfoVo.getBound());
            filter.setContent(shipmentInfoVo.getContents());
            filter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            filter.setShipType(shipmentInfoVo.getShipmentTypeId());
            filter.setWeight(rowName);
            filter.setZone(context.getString(Attributes.SENDER_ZONE_CODE).concat("-").concat(context.getString(Attributes.RECEIVER_ZONE_CODE)));
           
            //code by rakesh sir  (usedRateWebshipVo.getRate() == 0)
            usedRateWebshipVo = dao.selectUsedRateWebship(filter);

            if (usedRateWebshipVo.getRate() == 0) {
                usedRateWebshipVo.setRate((double) usedRateWebshipVo.getCbRate());
            }
                  // Base Rate Charge Calculation
            Double franchiseMarkup = 0D;
            FranchiseVo franchiseVo = new FranchiseVo();
            FranchiseDao fDao = new FranchiseDao();
            franchiseVo = fDao.selectFranchiseByFranchiseCode(webshipLoginInfo.getCustomerCode().toString().substring(0, 3));
            if (!StringUtils.isEmpty(franchiseVo.getTollIpecMarkupRate().toString())) {
                franchiseMarkup = franchiseVo.getTollIpecMarkupRate();
            }
            Double ncRate = 0D;
            cRate = cRate + ((cRate * franchiseMarkup) / 100);
            conRate = MathUtils.round(MathUtils.round(cRate, 3), 2);
           // ncRate = conRate; //code by shahabuddin it was not present
            ncRate = ncRate + ((ncRate * franchiseMarkup) / 100);
            Double minimunRate = 0D;
            if (franchiseVo.getMinimunBaseCharge() != null) {
                minimunRate = franchiseVo.getMinimunBaseCharge();
            }
            Map<String, Double> rate = ShipmentUtils.calculateBaseCharge(usedRateWebshipVo.getRateType(), usedRateWebshipVo.getRate(), minimunRate, rowName, cRate, ncRate);
            ncRate = rate.get("ncRate");
            cRate = rate.get("cRate");
            cRate = MathUtils.round(cRate, 2);
            ncRate = MathUtils.round(ncRate, 2);
            context.put(Attributes.CARRIER_COST, conRate);
            context.put(Attributes.CUSTOMER_COST, ncRate);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "There is an error in calculating base charge, please contact admin.");
            return false;
        }

        return true;
    }
}
