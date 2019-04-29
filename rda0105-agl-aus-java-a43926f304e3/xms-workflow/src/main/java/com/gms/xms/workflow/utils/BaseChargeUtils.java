/**
 *
 */
package com.gms.xms.workflow.utils;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.persistence.dao.MultiZoneDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.TollIpecKgRateDao;
import com.gms.xms.persistence.dao.TollIpecTotalRateDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.dao.ratesheet.RateSheetDao;
import com.gms.xms.persistence.dao.webship.ship.UsedRateWebshipDao;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.dhl.DhlRateSheetsVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AirbillInfoEditVo;
import com.gms.xms.txndb.vo.ratesheet.CarrierRateFilterVo;
import com.gms.xms.txndb.vo.ratesheet.CarrierRateVo;
import com.gms.xms.txndb.vo.ratesheet.MaxMinWeightVo;
import com.gms.xms.txndb.vo.ratesheet.QuoteBaseChargeVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.ship.UsedRateWebshipFilter;
import com.gms.xms.txndb.vo.webship.ship.UsedRateWebshipVo;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from BaseChargeUtils
 * <p>
 * Author TANDT
 */
public class BaseChargeUtils {

    /***
     * This function reCalculate customer cost , franchise cost, carrier cost from form edit airbill <br>
     * when Shipment Info or Accessorial changed.
     *
     * @param com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill. AirbillInfoEditVo<br>
     * @return AirbillInfoEditVo<br>
     * @throws Exception<br>
     */
    public static CarrierRateVo reCalculateCustomerCost(AirbillInfoEditVo airbillInfoEdit) throws Exception {
        String defaultOriginCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
        String calServiceFlag = SystemSettingCache.getInstance().getValueByKey("Calculate Service Tax");
        String serviceCode = airbillInfoEdit.getServiceCode();
        String[] serviceCodeData = serviceCode.split(",");
        Integer shipmentTypeId = Integer.parseInt(serviceCodeData[0]);
        Integer contents = Integer.parseInt(serviceCodeData[1]);
        String boundStatus = serviceCodeData[2];
        Double defaulTaxPercent = airbillInfoEdit.getCarrierTaxPercent();
        Integer contentRateSheet = 0;
        CarrierRateVo carrierRateVo = new CarrierRateVo();
        DhlRateSheetsVo dhlRateSheetsVo = new DhlRateSheetsVo();
        Boolean useDhlDomAu = true;
        // Check Weight
        if (defaultOriginCountry.equals("91") && airbillInfoEdit.getWeight() > 30) {
            airbillInfoEdit.setWeight(Math.ceil(airbillInfoEdit.getWeight()));
        }
        // Check Pieces
        if (StringUtils.isEmpty(String.valueOf(airbillInfoEdit.getNoOfPieces()))) {
            airbillInfoEdit.setNoOfPieces(1);
        }

        // Check rate sheet
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);

        if (shipmentTypeVo.getServiceId() == 1) { // DHL Envelope
            Boolean checkEnvelope = StringUtils.contains(StringUtils.lowerCase(shipmentTypeVo.getShipmentTypeName()), "envelope");
            if (checkEnvelope) {
                airbillInfoEdit.setWeight(0.3);
                contents = 0;
            }
        }
        if (shipmentTypeVo.getServiceId() == 51) { // UPS for UK integration
            Boolean checkStandard = StringUtils.contains(StringUtils.lowerCase(shipmentTypeVo.getShipmentTypeName()), "standard");
            if (checkStandard) {
                contents = 1;
                contentRateSheet = 1;
            }
            Boolean checkExpedited = StringUtils.contains(StringUtils.lowerCase(shipmentTypeVo.getShipmentTypeName()), "expedited");
            if (checkExpedited) {
                contents = 1;
                contentRateSheet = 1;
            }
        }

        // UKMail for UK integration , HK dhl domestic
        if (shipmentTypeVo.getServiceId() == 50 || (shipmentTypeVo.getServiceId() == 15 && defaultOriginCountry.equals("91"))) { // UKMail
            contents = 1;
            contentRateSheet = 1;
            useDhlDomAu = false;
        }

        if (StringUtils.isEmpty(String.valueOf(defaulTaxPercent))) {
            // 0-international ,1-domestic ,2-both
            if (calServiceFlag.equals("1")) { // india site
                defaulTaxPercent = 0D;
            } else {
                IServiceService serviceService = new ServiceServiceImp();
                ServiceVo serviceVo = serviceService.selectById(airbillInfoEdit.getServiceId());
                Integer carrierType = serviceVo.getCarrierType();
                if (carrierType == 0) {
                    if (boundStatus.equals("EU0") || boundStatus.equals("EU1")) {
                        defaulTaxPercent = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Tax Percentage for EU"));
                    } else {
                        defaulTaxPercent = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Tax Percentage"));
                    }
                } else {
                    defaulTaxPercent = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Domestic Tax Percentage"));
                }
            }
        }

        // France EU
        if (boundStatus.equals("EU0")) {
            boundStatus = "0";
        }
        if (boundStatus.equals("EU0")) {
            boundStatus = "1";
        }
        if (boundStatus.equals("15")) {
            boundStatus = "0";
        }
        Double docOutBoundTax = shipmentTypeVo.getDocOutboundTax();
        Double nonDocOutBoundTax = shipmentTypeVo.getNonDocOutboundTax();
        Double docInBoundTax = shipmentTypeVo.getDocInboundTax();
        Double nonDocInboundTax = shipmentTypeVo.getNonDocInboundTax();

        if (boundStatus.equals("0")) // Outbound
        {
            if (contents == 0) // Document
            {
                contentRateSheet = 0;
                if (!StringUtils.isEmpty(String.valueOf(docOutBoundTax))) {
                    defaulTaxPercent = docOutBoundTax;
                }
            } else // Non Doc
            {
                contentRateSheet = 1;
                if (!StringUtils.isEmpty(String.valueOf(nonDocOutBoundTax))) {
                    defaulTaxPercent = nonDocOutBoundTax;
                }
            }
        } else // Inbound
        {
            if (contents == 0) // Document
            {
                contentRateSheet = 0;
                if (!StringUtils.isEmpty(String.valueOf(docInBoundTax))) {
                    defaulTaxPercent = docInBoundTax;
                }
            } else // Non Doc
            {
                contentRateSheet = 1;
                if (!StringUtils.isEmpty(String.valueOf(nonDocInboundTax))) {
                    defaulTaxPercent = nonDocInboundTax;
                }
            }
        }

        if (boundStatus.equals("0") || boundStatus.equals("-1") || boundStatus.equals("15") || boundStatus.equals("EU0")) {
            dhlRateSheetsVo.setCarrierPackageRate(shipmentTypeVo.getCarrierPackageRate());
            dhlRateSheetsVo.setCarrierPackagePerWeightRate(shipmentTypeVo.getCarrierPackagePerWeightRate());

            // Outbound Rate Sheet
            if (defaultOriginCountry.equals("257") && airbillInfoEdit.getServiceId() == 1) {
            }
            dhlRateSheetsVo.setNonCarrierDocumentRate(shipmentTypeVo.getNonCarrierDocumentRate());
            dhlRateSheetsVo.setNonCarrierPackageRate(shipmentTypeVo.getNonCarrierPackageRate());
            dhlRateSheetsVo.setNonCarrierPackagePerWeightRate(shipmentTypeVo.getNonCarrierPackagePerWeightRate());

            dhlRateSheetsVo.setCarrierPakRate(shipmentTypeVo.getCarrierPakRate());
            dhlRateSheetsVo.setNonCarrierPakRate(shipmentTypeVo.getNonCarrierPakPerWeightRate());
            dhlRateSheetsVo.setCarrierPakPerWeightRate(shipmentTypeVo.getCarrierPakPerWeightRate());
            dhlRateSheetsVo.setNonCarrierPakPerWeightRate(shipmentTypeVo.getNonCarrierPakPerWeightRate());
        } else {
            dhlRateSheetsVo.setCarrierDocumentRate(shipmentTypeVo.getCarrierDocumentRate());
            dhlRateSheetsVo.setCarrierPackageRate(shipmentTypeVo.getCarrierPackageRate());
            dhlRateSheetsVo.setCarrierPackagePerWeightRate(shipmentTypeVo.getCarrierPackagePerWeightRate());

            // Outbound Rate Sheet
            if (defaultOriginCountry.equals("257") && airbillInfoEdit.getServiceId() == 1) {
            }
            dhlRateSheetsVo.setNonCarrierDocumentRate(shipmentTypeVo.getNonCarrierDocumentRate());
            dhlRateSheetsVo.setNonCarrierPackageRate(shipmentTypeVo.getNonCarrierPackageRate());
            dhlRateSheetsVo.setNonCarrierPakPerWeightRate(shipmentTypeVo.getNonCarrierPackagePerWeightRate());

            dhlRateSheetsVo.setCarrierPakRate(shipmentTypeVo.getCarrierPakRate());
            dhlRateSheetsVo.setNonCarrierPakRate(shipmentTypeVo.getNonCarrierPakPerWeightRate());
            dhlRateSheetsVo.setCarrierPakPerWeightRate(shipmentTypeVo.getCarrierPakPerWeightRate());
            dhlRateSheetsVo.setNonCarrierPakPerWeightRate(shipmentTypeVo.getNonCarrierPakPerWeightRate());

            if (dhlRateSheetsVo.getNonCarrierDocumentRate() == 0L) {
                dhlRateSheetsVo.setNonCarrierDocumentRate(dhlRateSheetsVo.getCarrierDocumentRate());
            }
            if (dhlRateSheetsVo.getNonCarrierPackageRate() == 0L) {
                dhlRateSheetsVo.setNonCarrierPackageRate(dhlRateSheetsVo.getCarrierPackageRate());
            }
            if (dhlRateSheetsVo.getNonCarrierPakPerWeightRate() == 0L) {
                dhlRateSheetsVo.setNonCarrierPakPerWeightRate(dhlRateSheetsVo.getCarrierPakPerWeightRate());
            }
        }

        // South Africa use same calculation with "Deduct for Perweight"
        if (airbillInfoEdit.getServiceId() == 15 && shipmentTypeVo.getServiceCode().equals("Deduct for Perweight 1")) {
            useDhlDomAu = false;
        } else if (airbillInfoEdit.getServiceId() == 15) {
            String defZone = SystemSettingCache.getInstance().getValueByKey("DHL Domestic Default Zone").trim();
            if (!StringUtils.isEmpty(defZone)) {
                useDhlDomAu = false;
                contents = 1;
                String[] domZoneRateAry = defZone.split("-");
                if (domZoneRateAry.length > 1) {
                    defZone = domZoneRateAry[0];
                    contents = 0;
                }
                contentRateSheet = 1;
                if (defaultOriginCountry.equals("70")) {
                    contentRateSheet = 0;
                }
                boundStatus = "0";
                airbillInfoEdit.setZone(defZone);
            }
        }

        if (airbillInfoEdit.getServiceId() == 15 && useDhlDomAu) {
            if (shipmentTypeVo.getServiceGroup().equals("fixed price per kg")) {
                carrierRateVo = getDHLDomesticCarrierCost(airbillInfoEdit, dhlRateSheetsVo, carrierRateVo, shipmentTypeVo);
            } else {
                if (shipmentTypeVo.getServiceGroup().equals("ukmail_domestic")) {
                    carrierRateVo = getCarrierAndNonCarrierCostUK(airbillInfoEdit, dhlRateSheetsVo, carrierRateVo);
                    airbillInfoEdit.setZone("UD");
                    contentRateSheet = 1;
                    boundStatus = "0";
                    carrierRateVo.setNonCarrierRate(0D);
                } else {
                    carrierRateVo = getDHLDomesticCarrierCost(airbillInfoEdit, dhlRateSheetsVo, carrierRateVo, shipmentTypeVo);
                    airbillInfoEdit.setZone(airbillInfoEdit.getZone());
                    contentRateSheet = 1;
                    boundStatus = "0";
                    carrierRateVo.setNonCarrierRate(0D);
                }
            }
        } else if (airbillInfoEdit.getServiceId() == 59) {// toll ipec
            carrierRateVo = getCarrierCostForTollIpecService(airbillInfoEdit, dhlRateSheetsVo, carrierRateVo, shipmentTypeVo);
            airbillInfoEdit.setZone(airbillInfoEdit.getZone());
            contentRateSheet = 1;
            boundStatus = "0";
            carrierRateVo.setNonCarrierRate(0D);
        }

        UsedRateWebshipDao usedRateWebshipDao = new UsedRateWebshipDao();
        UsedRateWebshipFilter usedRateWebshipFilter = new UsedRateWebshipFilter();
        usedRateWebshipFilter.setBound(Integer.parseInt(boundStatus));
        usedRateWebshipFilter.setContent(contentRateSheet);
        usedRateWebshipFilter.setCustomerCode(airbillInfoEdit.getCustomerCode());
        usedRateWebshipFilter.setShipType(shipmentTypeVo.getShipmentTypeId());
        usedRateWebshipFilter.setWeight(airbillInfoEdit.getWeight());
        usedRateWebshipFilter.setZone(airbillInfoEdit.getZone());

        UsedRateWebshipVo usedWebshipVo = usedRateWebshipDao.selectUsedRateWebship(usedRateWebshipFilter);
        if (usedWebshipVo.getRate() == 0) {
            usedWebshipVo.setRate(Double.parseDouble(String.valueOf(usedWebshipVo.getCbRate())));
        }

        return carrierRateVo;
    }

    private static CarrierRateVo getCarrierCostForTollIpecService(AirbillInfoEditVo airbillInfoEdit, DhlRateSheetsVo dhlRateSheetsVo, CarrierRateVo carrierRateVo, ShipmentTypeVo shipmentTypeVo) throws Exception {
        String[] zoneTollIpec = airbillInfoEdit.getZone().split("-");
        String serviceCode = airbillInfoEdit.getServiceCode();
        String[] serviceCodeData = serviceCode.split(",");
        String boundStatus = serviceCodeData[2];
        Double conRate = 0D;
        if (zoneTollIpec.length < 2) {
            carrierRateVo.setFuelSurcharge(0D);
            carrierRateVo.setCarrierRate(0D);
            carrierRateVo.setNonCarrierRate(0D);
            return carrierRateVo;
        } else {
            String fromZone = zoneTollIpec[0].trim(); // from zone
            String toZone = zoneTollIpec[1].trim(); // to zone
            String shipmentTypeName = shipmentTypeVo.getShipmentTypeName().toUpperCase();

            if (shipmentTypeName.equals("PRIORITY") || shipmentTypeName.equals("ROAD EXPRESS")) {

                TollIpecKgRateDao tollIpecKdRateDao = new TollIpecKgRateDao();
                TollIpecKgRateFilter tollIpecKgRateFilter = new TollIpecKgRateFilter();
                tollIpecKgRateFilter.setFromZone(fromZone);
                tollIpecKgRateFilter.setToZone(toZone);
                tollIpecKgRateFilter.setServiceTypeName(shipmentTypeName);
                TollIpecKgRateVo tollIpecKgRateVo = tollIpecKdRateDao.selectByFilter(tollIpecKgRateFilter);

                if (tollIpecKgRateVo != null) {
                    Double basic = tollIpecKgRateVo.getBasic();
                    Double freightFactor = tollIpecKgRateVo.getFreight();
                    Double minimum = tollIpecKgRateVo.getMinimum();
                    Double fuelSurcharge = tollIpecKgRateVo.getSurcharge();
                    Double overweight = airbillInfoEdit.getWeight();
                    if (shipmentTypeName == "PRIORITY" && (fromZone == "SEQLD" || fromZone == "BNE" || toZone == "SEQLD" || toZone == "BNE")) {
                        if (airbillInfoEdit.getWeight() > 5) {
                            overweight = airbillInfoEdit.getWeight() - 5;
                        } else {
                            overweight = 0D;
                        }

                    }

                    Double freightCharge = (overweight * freightFactor);
                    freightCharge = (freightCharge / 100);
                    if ((basic + freightCharge) < minimum) {
                        freightCharge = 0D;
                        basic = minimum;
                    }
                    conRate = basic + freightCharge;
                    freightCharge = (basic + freightCharge) * (fuelSurcharge / 100);
                }
            } else if (shipmentTypeName == "TOLL DIRECT" || shipmentTypeName == "DIRECT") {
                conRate = 0D;
                Double perRate = 0D;
                TollIpecTotalRateDao tollIpecTotalRateDao = new TollIpecTotalRateDao();
                TollIpecTotalRateFilter tollIpecTotalRateFilter = new TollIpecTotalRateFilter();
                tollIpecTotalRateFilter.setFromZone(fromZone);
                tollIpecTotalRateFilter.setToZone(toZone);
                TollIpecTotalRateVo tollIpecTotalRateVo = tollIpecTotalRateDao.selectByFilter(tollIpecTotalRateFilter);
                if (tollIpecTotalRateVo != null) {
                    Double fuelSurcharge = tollIpecTotalRateVo.getSurcharge();
                    Integer range1 = tollIpecTotalRateVo.getRange1(); // 3
                    Integer range2 = tollIpecTotalRateVo.getRange2(); // 7
                    Integer range3 = tollIpecTotalRateVo.getRange3(); // 12
                    Integer range4 = tollIpecTotalRateVo.getRange4(); // 16
                    Integer range5 = tollIpecTotalRateVo.getRange5(); // 25
                    Integer range6 = tollIpecTotalRateVo.getRange6(); // 32
                    Integer range7 = tollIpecTotalRateVo.getRange7(); // 40
                    if (airbillInfoEdit.getWeight() <= range1) {
                        conRate = tollIpecTotalRateVo.getCharge1();
                    } else if (airbillInfoEdit.getWeight() > range1 && airbillInfoEdit.getWeight() <= range2) {
                        conRate = tollIpecTotalRateVo.getCharge2();
                    } else if (airbillInfoEdit.getWeight() > range2 && airbillInfoEdit.getWeight() <= range3) {
                        conRate = tollIpecTotalRateVo.getCharge3();
                    } else if (airbillInfoEdit.getWeight() > range3 && airbillInfoEdit.getWeight() <= range4) {
                        conRate = tollIpecTotalRateVo.getCharge4();
                    } else if (airbillInfoEdit.getWeight() > range4 && airbillInfoEdit.getWeight() <= range5) {
                        conRate = tollIpecTotalRateVo.getCharge5();
                    } else if (airbillInfoEdit.getWeight() > range5 && airbillInfoEdit.getWeight() <= range6) {
                        conRate = tollIpecTotalRateVo.getCharge6();
                    } else if (airbillInfoEdit.getWeight() > range6) {
                        conRate = tollIpecTotalRateVo.getCharge7();
                    }

                    if (airbillInfoEdit.getWeight() > range7) {
                        Double subWeight = airbillInfoEdit.getWeight() - range7;
                        perRate = (subWeight * tollIpecTotalRateVo.getAdditional()) / 100;
                        conRate = conRate + perRate;
                    }
                    fuelSurcharge = conRate * (fuelSurcharge / 100);
                }
            }

            carrierRateVo.setCarrierRate(conRate);

            UsedRateWebshipDao usedRateWebshipDao = new UsedRateWebshipDao();
            UsedRateWebshipFilter usedRateWebshipFilter = new UsedRateWebshipFilter();
            usedRateWebshipFilter.setBound(Integer.parseInt(boundStatus));
            usedRateWebshipFilter.setContent(0);
            usedRateWebshipFilter.setCustomerCode(airbillInfoEdit.getCustomerCode());
            usedRateWebshipFilter.setShipType(shipmentTypeVo.getShipmentTypeId());
            usedRateWebshipFilter.setWeight(airbillInfoEdit.getWeight());
            usedRateWebshipFilter.setZone(airbillInfoEdit.getZone());

            UsedRateWebshipVo usedWebshipVo = usedRateWebshipDao.selectUsedRateWebship(usedRateWebshipFilter);
            if (usedWebshipVo.getRate() == 0) {
                usedWebshipVo.setRate(Double.parseDouble(String.valueOf(usedWebshipVo.getCbRate())));
            }
            Double franchiseMarkup = 0D;
            FranchiseDao franchiseDao = new FranchiseDao();
            FranchiseVo franchiseVo = franchiseDao.selectFranchiseByFranchiseCodeExt(String.valueOf(airbillInfoEdit.getCustomerCode()));
            franchiseMarkup = franchiseVo.getTollIpecMarkupRate();
            carrierRateVo.setCarrierRate(carrierRateVo.getCarrierRate() + ((carrierRateVo.getCarrierRate() * franchiseMarkup) / 100));
            carrierRateVo.setCarrierRate(carrierRateVo.getCarrierRate() + ((carrierRateVo.getCarrierRate() * franchiseMarkup) / 100));

            CarrierRateVo carrierRateVoN = getCarrierCostMarkup(carrierRateVo, usedWebshipVo);
            carrierRateVo.setNonCarrierRate(carrierRateVoN.getNonCarrierRate());

        }
        return carrierRateVo;
    }

    public static CarrierRateVo getCarrierCostMarkup(CarrierRateVo carrierRateVo, UsedRateWebshipVo usedWebshipVo) throws Exception {
        Double mRate = 0D;
        Double mPercent = 0D;
        Double tmpRate = 0D;
        String isQuote = "";
        Double shCarrierCost = 0D;
        if (usedWebshipVo.getRateType() == 0) // DHL
        {
            if (StringUtils.isEmpty(String.valueOf(carrierRateVo.getNonCarrierRate()))) {
                carrierRateVo.setNonCarrierRate(0D);
            } else {
                Double nonCarrierRateN = (carrierRateVo.getNonCarrierRate() - ((carrierRateVo.getNonCarrierRate() * usedWebshipVo.getRate()) / 100));
                carrierRateVo.setNonCarrierRate(nonCarrierRateN);
                mRate = carrierRateVo.getNonCarrierRate() - carrierRateVo.getCarrierRate();
                if (carrierRateVo.getNonCarrierRate() == 0) {
                    mPercent = 0D;
                } else {
                    mPercent = (mRate / carrierRateVo.getCarrierRate()) / 100;
                }
                if (mPercent < usedWebshipVo.getMinimunBaseCharge()) {
                    usedWebshipVo.setRate(usedWebshipVo.getMinimunBaseCharge());
                    tmpRate = 1 - (usedWebshipVo.getRate() / 100);
                    if (tmpRate != 0) {
                        carrierRateVo.setNonCarrierRate(carrierRateVo.getCarrierRate() / tmpRate);
                    } else {
                        carrierRateVo.setNonCarrierRate(carrierRateVo.getCarrierRate() * usedWebshipVo.getRate());
                    }
                    mRate = carrierRateVo.getNonCarrierRate() - carrierRateVo.getCarrierRate();
                    if (carrierRateVo.getNonCarrierRate() == 0) {
                        mPercent = 0D;
                    } else {
                        mPercent = (mRate / carrierRateVo.getNonCarrierRate()) / 100;
                    }

                }
            }
        } else {
            if (usedWebshipVo.getRateType() == 3) { // %Topup
                if (isQuote == "") {
                    carrierRateVo.setNonCarrierPerWeightRate(shCarrierCost / (1 - (usedWebshipVo.getRate() / 100)));
                } else {
                    carrierRateVo.setNonCarrierPerWeightRate(carrierRateVo.getCarrierRate() / (1 - (usedWebshipVo.getRate() / 100)));
                    carrierRateVo.setNonCarrierPerWeightRate(carrierRateVo.getNonCarrierRate() * usedWebshipVo.getWeight());
                    carrierRateVo.setCarrierRate(carrierRateVo.getCarrierRate() * usedWebshipVo.getWeight());
                }

                mRate = carrierRateVo.getNonCarrierRate() - carrierRateVo.getCarrierRate();
                if (carrierRateVo.getNonCarrierRate() == 0) {
                    mPercent = 0D;
                } else {
                    mPercent = (mRate / carrierRateVo.getNonCarrierRate()) * 100;
                }
            } else if (usedWebshipVo.getRateType() == 1) // %Markup
            {
                if (carrierRateVo.getNonCarrierRate() == 0 || StringUtils.isEmpty(String.valueOf(carrierRateVo.getNonCarrierRate()))) {
                    carrierRateVo.setNonCarrierRate(0D);
                    mRate = 0D;
                    mPercent = 0D;
                } else {
                    carrierRateVo.setNonCarrierRate(carrierRateVo.getCarrierRate() + ((usedWebshipVo.getRate() / 100) * carrierRateVo.getCarrierRate()));
                    mRate = carrierRateVo.getNonCarrierRate() - carrierRateVo.getCarrierRate();
                    if (carrierRateVo.getNonCarrierRate() == 0) {
                        mPercent = 0D;
                    } else {
                        mPercent = (mRate / carrierRateVo.getNonCarrierPerWeightRate()) / 100;
                    }
                    if (mPercent < usedWebshipVo.getMinimunBaseCharge()) {
                        usedWebshipVo.setRate(usedWebshipVo.getMinimunBaseCharge());
                        tmpRate = 1 - (usedWebshipVo.getRate() / 100);
                        if (tmpRate != 0) {
                            carrierRateVo.setNonCarrierRate(carrierRateVo.getCarrierRate() / tmpRate);
                        } else {
                            carrierRateVo.setNonCarrierRate(carrierRateVo.getCarrierRate() * usedWebshipVo.getRate());
                        }
                    }
                }
            } else { // Margin
                if (carrierRateVo.getNonCarrierRate() == 0 || StringUtils.isEmpty(String.valueOf(carrierRateVo.getNonCarrierRate()))) {
                    carrierRateVo.setNonCarrierRate(0D);
                    mRate = 0D;
                    mPercent = 0D;
                } else {
                    if (usedWebshipVo.getRate() < usedWebshipVo.getMinimunBaseCharge()) {
                        usedWebshipVo.setRate(usedWebshipVo.getMinimunBaseCharge());
                    }

                    tmpRate = 1 - (usedWebshipVo.getRate() / 100);
                    if (tmpRate != 0) {
                        carrierRateVo.setNonCarrierRate(carrierRateVo.getCarrierRate() - tmpRate);
                    } else {
                        carrierRateVo.setNonCarrierRate(carrierRateVo.getCarrierRate() * usedWebshipVo.getRate());
                    }

                }
            }
        }
        return carrierRateVo;
    }

    public static CarrierRateVo getCarrierAndNonCarrierCostUK(AirbillInfoEditVo airbillInfoEdit, DhlRateSheetsVo dhlRateSheetsVo, CarrierRateVo carrierRateVo) throws Exception {

        Double substractionNcWeight = 0D;
        Double substractionCWeight = 0D;
        Double previousNcRate = 0D;
        Double previousCRate = 0D;
        CarrierRateFilterVo carrierRateFilterVo = new CarrierRateFilterVo();
        carrierRateFilterVo.setWeight(airbillInfoEdit.getWeight());
        carrierRateFilterVo.setZone(airbillInfoEdit.getZone());
        RateSheetDao rateSheetDao = new RateSheetDao();
        QuoteBaseChargeVo nonCarrierPackageRateVo = new QuoteBaseChargeVo();
        QuoteBaseChargeVo nonCarrierPackagePerweightRateVo = new QuoteBaseChargeVo();

        MaxMinWeightVo maxWeightVo = new MaxMinWeightVo();
        MaxMinWeightVo minWeightVo = new MaxMinWeightVo();

        carrierRateFilterVo.setType("");
        carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
        minWeightVo = rateSheetDao.getMaxMinWeight(carrierRateFilterVo);
        if (airbillInfoEdit.getWeight() < minWeightVo.getWeight()) {
            airbillInfoEdit.setWeight(minWeightVo.getWeight());
        }

        carrierRateFilterVo.setType("max");
        carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getNonCarrierPackageRate());
        maxWeightVo = rateSheetDao.getMaxMinWeight(carrierRateFilterVo);

        carrierRateFilterVo.setWeight(airbillInfoEdit.getWeight());
        if (airbillInfoEdit.getWeight() < maxWeightVo.getWeight()) // package
        {
            carrierRateFilterVo.setType("");
            carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getNonCarrierPackageRate());
            QuoteBaseChargeVo nonCarrierPackageRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
            previousNcRate = Double.parseDouble(nonCarrierPackageRate.getValue());
        } else // per kg rate
        {
            // "non carrier per kg case";
            carrierRateFilterVo.setType("");
            carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getNonCarrierPackageRate());
            QuoteBaseChargeVo nonCarrierPackageRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
            previousNcRate = Double.parseDouble(nonCarrierPackageRate.getValue());
            carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getNonCarrierPakPerWeightRate());
            carrierRateFilterVo.setWeight(99999D);
            QuoteBaseChargeVo nonCarrierPackagePerweightRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
            // "previous_nc_rate=".$previous_nc_rate;

            substractionNcWeight = airbillInfoEdit.getWeight() - maxWeightVo.getWeight();

            nonCarrierPackagePerweightRate.setValue(String.valueOf(substractionNcWeight * Double.parseDouble(nonCarrierPackagePerweightRate.getValue())));
            nonCarrierPackageRate.setValue(String.valueOf(nonCarrierPackageRateVo.getValue() + nonCarrierPackagePerweightRateVo.getValue()));
            previousNcRate = Double.parseDouble(nonCarrierPackageRate.getValue());

        }
        carrierRateVo.setNonCarrierRate(previousNcRate);

        QuoteBaseChargeVo carrierPackageRateVo = new QuoteBaseChargeVo();
        QuoteBaseChargeVo carrierPackagePerweightRateVo = new QuoteBaseChargeVo();

        carrierRateFilterVo.setType("");
        carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
        minWeightVo = rateSheetDao.getMaxMinWeight(carrierRateFilterVo);
        if (airbillInfoEdit.getWeight() < minWeightVo.getWeight()) {
            airbillInfoEdit.setWeight(minWeightVo.getWeight());
        }

        carrierRateFilterVo.setType("max");
        carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
        maxWeightVo = rateSheetDao.getMaxMinWeight(carrierRateFilterVo);

        carrierRateFilterVo.setWeight(airbillInfoEdit.getWeight());
        if (airbillInfoEdit.getWeight() < maxWeightVo.getWeight()) // package
        {
            carrierRateFilterVo.setType("");
            carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
            QuoteBaseChargeVo carrierPackageRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
            previousCRate = Double.parseDouble(carrierPackageRate.getValue());
        } else // per kg rate
        {
            // "non carrier per kg case";
            carrierRateFilterVo.setType("");
            carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
            QuoteBaseChargeVo carrierPackageRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
            previousCRate = Double.parseDouble(carrierPackageRate.getValue());
            carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getCarrierPackagePerWeightRate());
            carrierRateFilterVo.setWeight(99999D);
            QuoteBaseChargeVo carrierPackagePerweightRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
            // "previous_nc_rate=".$previous_nc_rate;

            substractionCWeight = airbillInfoEdit.getWeight() - maxWeightVo.getWeight();

            carrierPackagePerweightRate.setValue(String.valueOf(substractionCWeight * Double.parseDouble(carrierPackagePerweightRate.getValue())));
            carrierPackageRate.setValue(String.valueOf(carrierPackageRateVo.getValue() + carrierPackagePerweightRateVo.getValue()));
            previousCRate = Double.parseDouble(carrierPackageRate.getValue());

        }
        carrierRateVo.setCarrierRate(previousCRate);
        return carrierRateVo;
    }

    public static CarrierRateVo getDHLDomesticCarrierCost(AirbillInfoEditVo airbillInfoEdit, DhlRateSheetsVo dhlRateSheetsVo, CarrierRateVo carrierRateVo, ShipmentTypeVo shipmentType) throws Exception {
        Double perRate = 0D;
        Double maxWeight = 0D;
        Double maxWeightValue = 0D;
        Double roundWeight = 0D;
        MultiZoneDao multiZoneDao = new MultiZoneDao();
        RateSheetDao rateSheetDao = new RateSheetDao();

        MultiZoneFilter multiZoneFilter = new MultiZoneFilter();
        CarrierRateFilterVo carrierRateFilterVo = new CarrierRateFilterVo();

        multiZoneFilter.setSenderZoneCode(airbillInfoEdit.getOriginCity());
        multiZoneFilter.setReceiverZoneCode(airbillInfoEdit.getDestinationCity());
        MultiZoneVo multiZoneVo = multiZoneDao.selectDhlDomesticZone(multiZoneFilter);
        airbillInfoEdit.setZone(multiZoneVo.getZone());
        if (carrierRateVo.getCarrierRate() != 0) {
            carrierRateFilterVo.setWeight(airbillInfoEdit.getWeight());
            carrierRateFilterVo.setZone(airbillInfoEdit.getZone());
            carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
            carrierRateFilterVo.setType("max");
            MaxMinWeightVo maxMinWeightVo = rateSheetDao.getMaxMinWeight(carrierRateFilterVo);
            maxWeight = maxMinWeightVo.getWeight();
            maxWeightValue = Double.parseDouble(maxMinWeightVo.getRateValue());
            if (airbillInfoEdit.getWeight() > maxWeight) {
                carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getCarrierPackagePerWeightRate());
                QuoteBaseChargeVo carrierRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
                perRate = Double.parseDouble(carrierRate.getValue());
                roundWeight = airbillInfoEdit.getWeight() - maxWeight;
                if (shipmentType.getPerWeightStatus() == 1) {
                    carrierRateVo.setCarrierRate(maxWeightValue + (roundWeight * perRate));
                } else {
                    carrierRateVo.setCarrierRate(airbillInfoEdit.getWeight() * perRate);
                }
            } else {

                if (airbillInfoEdit.getWeight() < 1) {
                    airbillInfoEdit.setWeight(1D);
                }
                carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getCarrierPackageRate());
                QuoteBaseChargeVo carrierRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
                carrierRateVo.setCarrierRate(Double.parseDouble(carrierRate.getValue()));
            }
        }

        if (carrierRateVo.getNonCarrierRate() != 0) {
            carrierRateFilterVo.setWeight(airbillInfoEdit.getWeight());
            carrierRateFilterVo.setZone(airbillInfoEdit.getZone());
            carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getNonCarrierPackageRate());
            carrierRateFilterVo.setType("max");
            MaxMinWeightVo maxMinWeightVo = rateSheetDao.getMaxMinWeight(carrierRateFilterVo);
            maxWeight = maxMinWeightVo.getWeight();
            maxWeightValue = Double.parseDouble(maxMinWeightVo.getRateValue());
            if (airbillInfoEdit.getWeight() > maxWeight) {
                carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getNonCarrierPakPerWeightRate());
                QuoteBaseChargeVo carrierRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
                perRate = Double.parseDouble(carrierRate.getValue());
                roundWeight = airbillInfoEdit.getWeight() - maxWeight;
                if (shipmentType.getPerWeightStatus() == 1) {
                    carrierRateVo.setNonCarrierRate(maxWeightValue + (roundWeight * perRate));
                } else {
                    carrierRateVo.setNonCarrierRate(airbillInfoEdit.getWeight() * perRate);
                }
            } else {

                if (airbillInfoEdit.getWeight() < 1) {
                    airbillInfoEdit.setWeight(1D);
                }
                carrierRateFilterVo.setSheetId(dhlRateSheetsVo.getNonCarrierPackageRate());
                QuoteBaseChargeVo carrierRate = rateSheetDao.selectQuoteBaseChargeWebship(carrierRateFilterVo);
                carrierRateVo.setNonCarrierRate(Double.parseDouble(carrierRate.getValue()));
            }
        }
        return carrierRateVo;
    }
}
