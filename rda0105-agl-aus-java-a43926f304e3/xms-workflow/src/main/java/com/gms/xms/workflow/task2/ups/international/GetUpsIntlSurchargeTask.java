package com.gms.xms.workflow.task2.ups.international;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.persistence.dao.UpsAreaSurchargeDao;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.ups.UpsAreaSurchargeFilter;
import com.gms.xms.txndb.vo.ups.UpsAreaSurchargeVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import com.gms.xms.workflow.utils.weight.BaseGrossWeight;
import com.gms.xms.workflow.utils.weight.GrossWeightFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Posted from GetUpsIntlSurchargeTask
 * <p>
 * Author Thinhdd Date May 22, 2017
 */
public class GetUpsIntlSurchargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetUpsIntlSurchargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        ShipmentInfoVo shipmentInfo = context.get(Attributes.SHIPMENT_INFO_VO);
        Double totalWeight = context.get(Attributes.SHIPMENT_TOTAL_WEIGHT);

        StringBuilder shipmentSpecialSurchargeNames = new StringBuilder();
        StringBuilder shipmentSpecialSurchargeValues = new StringBuilder();
        WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);
        Double baseCharge = context.getDouble(Attributes.CUSTOMER_COST);
        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();

        AccessorialVo gst = new AccessorialVo();
        List<AccessorialVo> accessorialVos = new ArrayList<>();
        AccessorialVo securityCharge = new AccessorialVo();
        AccessorialVo additionalSurcharge = new AccessorialVo();

        String detaultVatRate = SystemSettingCache.getInstance().getValueByKey("UPS Int Tax Percentage");
        Double gstRate = StringUtils.isBlank(detaultVatRate) ? 0D : Double.parseDouble(detaultVatRate);
        Double gstValue = 0D;
        Double additionalHandlingValue = 0D;
        Double largePackageValue = 0D;
        Double remoteAreaValue = 0D;
        Double extendAreaValue = 0D;
        Double securityChargeValue = 0d;
        Double totalSurcharge = 0D;
        Double totalCharge = 0D;

        // Flag surcharge check
        Boolean enableHandling = false;
        Boolean enableLarge = false;

        // Remote area surcharge data condition
        Double remoteChargePer = 0D;
        Double remoteMinimumCharge = 0D;

        // Extend area surcharge data condition
        Double extendChargePer = 0D;
        Double extendMinimumCharge = 0D;

        // Additional handling surcharge data condition
        Double additionalMinDeadWeightApply = 0D;
        Double additional1ndDimensionApply = 0D;
        Double additional2ndDimensionApply = 0D;
        Double additionalChargeApply = 0D;

        // Large package surcharge data condition
        Double largeMinimumDimension = 0D;
        Double largeMaxDimension = 0D;
        Double largeMinCubicWeight = 0D;
        Double largeMinDeadWeight = 0D;
        Double largeMinLDimension = 0D;
        Double largeCharge = 0D;

        try {
            boolean isInbound = shipmentInfo.getBound() == 1;
            UpsAreaSurchargeDao upsAreaSurchargeDao = new UpsAreaSurchargeDao();
            UpsAreaSurchargeFilter upsAreaSurchargeSenderFilter = new UpsAreaSurchargeFilter();
            upsAreaSurchargeSenderFilter.setPostalCode(shipmentInfo.getSenderAddress().getPostalCode());
            upsAreaSurchargeSenderFilter.setCity(shipmentInfo.getSenderAddress().getCity());
            upsAreaSurchargeSenderFilter.setCountryId(shipmentInfo.getSenderAddress().getCountry());

            UpsAreaSurchargeFilter upsAreaSurchargeReceiverFilter = new UpsAreaSurchargeFilter();
            upsAreaSurchargeReceiverFilter.setPostalCode(shipmentInfo.getReceiverAddress().getPostalCode());
            upsAreaSurchargeReceiverFilter.setCity(shipmentInfo.getReceiverAddress().getCity());
            upsAreaSurchargeReceiverFilter.setCountryId(shipmentInfo.getReceiverAddress().getCountry());

            UpsAreaSurchargeVo upsAreaSenderSurchargeVo = upsAreaSurchargeDao.selectSurchargeArea(upsAreaSurchargeSenderFilter);
            UpsAreaSurchargeVo upsAreaReceiverSurchargeVo = upsAreaSurchargeDao.selectSurchargeArea(upsAreaSurchargeReceiverFilter);


            Map<String, ServiceAddConVo> serviceAddCon = shipmentInfo.getServiceAddConMap();

            Boolean hasWarranty = false;
            if (serviceAddCon.get("aglWarranty") != null && serviceAddCon.get("aglWarranty").getValue() != null && serviceAddCon.get("aglWarranty").getValue().equals("1")) {
                hasWarranty = true;
            }

            accessorialFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            accessorialFilter.setBaseCharge(baseCharge);
            accessorialFilter.setCarrier(Long.parseLong(String.valueOf(shipmentInfo.getServiceId())));
            accessorialFilter.setQuotable(1);
            accessorialFilter.setShipmentDate(shipmentInfo.getShipmentDate());
            List<AccessorialVo> accessorialVosCheck = accessorialDao.selectSurChargeList(accessorialFilter);

            for (AccessorialVo accessorialVo : accessorialVosCheck) {
                switch (accessorialVo.getCode()) {
                    case "RAS":
                        remoteChargePer = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Remote Area Surcharge UPS Charge Per Kg"));
                        remoteMinimumCharge = accessorialVo.getValue();
                        if (upsAreaSenderSurchargeVo != null) {
                            remoteAreaValue = addAreaSurcharge(accessorialVos, upsAreaSenderSurchargeVo, true,
                                    remoteMinimumCharge, remoteChargePer, totalWeight, shipmentSpecialSurchargeNames,
                                    shipmentSpecialSurchargeValues, UpsAreaSurchargeEnum.REMOTE_AREA_CHARGE, accessorialVo.getAccessorialId());
                            totalCharge += remoteAreaValue;
                        }
                        if (upsAreaReceiverSurchargeVo != null) {
                            remoteAreaValue = addAreaSurcharge(accessorialVos, upsAreaReceiverSurchargeVo, false,
                                    remoteMinimumCharge, remoteChargePer, totalWeight, shipmentSpecialSurchargeNames,
                                    shipmentSpecialSurchargeValues, UpsAreaSurchargeEnum.REMOTE_AREA_CHARGE, accessorialVo.getAccessorialId());
                            totalCharge += remoteAreaValue;
                        }
                        continue;
                    case "EAS":
                        extendChargePer = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Extend Area Surcharge UPS Charge Per Kg"));
                        extendMinimumCharge = accessorialVo.getValue();
                        if (upsAreaSenderSurchargeVo != null) {
                            extendAreaValue = addAreaSurcharge(accessorialVos, upsAreaSenderSurchargeVo, true,
                                    extendMinimumCharge, extendChargePer, totalWeight, shipmentSpecialSurchargeNames,
                                    shipmentSpecialSurchargeValues, UpsAreaSurchargeEnum.EXTEND_AREA_CHARGE, accessorialVo.getAccessorialId());
                            totalCharge += extendAreaValue;
                        }
                        if (upsAreaReceiverSurchargeVo != null) {
                            extendAreaValue = addAreaSurcharge(accessorialVos, upsAreaReceiverSurchargeVo, false,
                                    extendMinimumCharge, extendChargePer, totalWeight, shipmentSpecialSurchargeNames,
                                    shipmentSpecialSurchargeValues, UpsAreaSurchargeEnum.EXTEND_AREA_CHARGE, accessorialVo.getAccessorialId());
                            totalCharge += extendAreaValue;
                        }
                        continue;
                    case "AHS":
                        additionalMinDeadWeightApply = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Additional Handling Surcharge Minimum Weight Apply"));
                        additional1ndDimensionApply = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Additional Handling Surcharge 1nd Value Dimension Apply"));
                        additional2ndDimensionApply = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Additional Handling Surcharge 2nd Value Dimension Apply"));
                        additionalChargeApply = accessorialVo.getValue();
                        enableHandling = true;
                        additionalSurcharge.setAccessorialId(accessorialVo.getAccessorialId());
                        continue;
                    case "LPS":
                        largeMinimumDimension = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Large Package Surcharge Minimum Dimensions Data"));
                        largeMaxDimension = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Large Package Surcharge Maximum Dimensions Data"));
                        largeMinCubicWeight = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Large Package Surcharge Cubic Weight Apply"));
                        largeMinDeadWeight = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Large Package Surcharge Dead Weight Apply"));
                        largeMinLDimension = Double.valueOf(SystemSettingCache.getInstance().getValueByKey("Large Package Surcharge Dimensions L Apply"));
                        largeCharge = accessorialVo.getValue();
                        enableLarge = true;
                        continue;
                    case "UPS SECURITY INT" :
                        securityCharge.setAccessorialId(accessorialVo.getAccessorialId());
                        continue;
                    case "UPS GST" :
                        gst.setAccessorialId(accessorialVo.getAccessorialId());
                        continue;
                    default:
                        accessorialVos.add(accessorialVo);
                        totalSurcharge += accessorialVo.getValue();
                }
            }

            Double warrantyChargeValue = 0D;
            if (hasWarranty) {
                accessorialFilter.setCarrier(1L);
                accessorialFilter.setDescription("agl Warranty International");
                accessorialFilter.setQuotable(null);
                AccessorialVo warrantyCharge = new AccessorialVo();
                warrantyCharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                warrantyChargeValue = warrantyCharge.getValue();
                accessorialVos.add(warrantyCharge);
            }

            List<PieceVo> pieces = shipmentInfo.getPieces();
            BaseGrossWeight grossWeight = GrossWeightFactory.getGrossWeight(shipmentInfo.getServiceId(), shipmentInfo.getShipmentTypeId());

            for (PieceVo pieceVo : pieces) {
                Double largerPieceCharge = 0D;
                if (enableLarge) {
                    Boolean isLargeSurchargeApply = checkLargePackageSurcharge(shipmentInfo, pieceVo, largeMinimumDimension,
                            largeMinDeadWeight, largeMinLDimension
                            , shipmentInfo.getDimensionUnit(), shipmentInfo.getWeightUnit());
                    largerPieceCharge = pieceVo.getQuantity() * (isLargeSurchargeApply ? largeCharge : 0D);
                    largePackageValue += largerPieceCharge;
                }
                if (largerPieceCharge > 0D) {
                    break;
                }
                if (enableHandling) {
                    additionalHandlingValue += pieceVo.getQuantity() * getAdditionalHandlingCharge(pieceVo, additionalMinDeadWeightApply,
                            additional1ndDimensionApply, additional2ndDimensionApply, additionalChargeApply, shipmentInfo.getDimensionUnit(), shipmentInfo.getWeightUnit());
                }
            }
            if (largePackageValue.equals(0D)) {

                // security and gst surcharge
                securityCharge.setDescription("Security Surcharge");
                securityCharge.setCode("Security Surcharge");
                accessorialVos.add(securityCharge);

                if (isInbound) {
                    securityChargeValue = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("UPS Security Charge Inbound"));
                    gstValue = MathUtils.round(securityChargeValue * (gstRate / 100), 2);
                    gst.setDescription("GST");
                    gst.setCode("GST");
                    gst.setIsQuotable(1);
                    gst.setValue(gstValue);
                    accessorialVos.add(gst);
                } else {
                    securityChargeValue = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("UPS Security Charge Outbound"));
                }
                securityCharge.setValue(securityChargeValue);

                // additional handling surcharge
                if (additionalHandlingValue > 0D) {
                    additionalSurcharge.setDescription("Additional Handling Charge");
                    additionalSurcharge.setValue(additionalHandlingValue);
                    additionalSurcharge.setCode("Additional Handling Charge");
                    accessorialVos.add(additionalSurcharge);
                }

                totalCharge += totalSurcharge + baseCharge + gstValue + securityChargeValue + largePackageValue
                        + additionalHandlingValue + warrantyChargeValue;
            }else
            {
               // totalCharge = 0D;
            	
            	 totalCharge += totalSurcharge + baseCharge + gstValue  + warrantyChargeValue;
            }
            totalCharge = MathUtils.round(totalCharge, 2);
            context.put(Attributes.TOTAL_CHARGE, totalCharge);
            context.put(Attributes.SURCHARGE_LIST, accessorialVos);
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return true;
    }

    private Double addAreaSurcharge(List<AccessorialVo> accessorialVos, UpsAreaSurchargeVo upsAreaSurchargeVo,
                                    Boolean isOrigin, Double minimumCharge, Double chargePer, Double weight,
                                    StringBuilder shipmentSpecialSurchargeNames, StringBuilder shipmentSpecialSurchargeValues,
                                    UpsAreaSurchargeEnum upsAreaSurchargeEnum, Long accessorialId) {
        AccessorialVo accessorialVo = new AccessorialVo();
        String areaSurchargeName;
        String prefixByType;
        String description;
        int type = 0;
        if (isOrigin) {
            areaSurchargeName = upsAreaSurchargeVo.getOriginSurcharge();
            prefixByType = " - origin";
        } else {
            areaSurchargeName = upsAreaSurchargeVo.getDestinationSurcharge();
            prefixByType = " - destination";
            type = 1;
        }
        if (upsAreaSurchargeEnum.getNames().contains(areaSurchargeName)) {
            description = upsAreaSurchargeEnum.getDescription() + prefixByType;
            accessorialVo.setCode(description);
            accessorialVo.setDescription(description);
            accessorialVo.setAccessorialId(accessorialId);
            accessorialVo.setType(type);
        } else {
            return 0D;
        }
        weight = Math.ceil(weight);
        Double maxChargeBetweenMinimumAndChargePer = getMaxChargeBetweenMinimumAndChargePer(weight, chargePer, minimumCharge);
        accessorialVo.setValue(maxChargeBetweenMinimumAndChargePer);
        accessorialVos.add(accessorialVo);
        return maxChargeBetweenMinimumAndChargePer;
    }

    private Double getMaxChargeBetweenMinimumAndChargePer(Double weight, Double areaChargePer, Double minimumCharge) {

        return Math.max(weight * areaChargePer, minimumCharge);
    }

    public Boolean checkLargePackageSurcharge(ShipmentInfoVo shipmentInfoVo, PieceVo pieceVo, Double largeMinimumDimension,
                                              Double largeMinDeadWeight, Double largeMinLDimension,
                                              String dimensionUnit, String weightUnit) {

        if(shipmentInfoVo.getContents()==1)
        {
            Double dimensionValue = MathUtils.dimUnitConvert(pieceVo.getDimensionL(), dimensionUnit) +
                    2 * MathUtils.dimUnitConvert(pieceVo.getDimensionH(), dimensionUnit) +
                    2 * MathUtils.dimUnitConvert(pieceVo.getDimensionW(), dimensionUnit);
            if (largeMinimumDimension < dimensionValue ||
                    pieceVo.getDimensionL() > largeMinLDimension) {
                return true;
            }
        }

        Double actualWeight = ShipmentUtils.weightUnitConvert(pieceVo.getWeight(), weightUnit);
        return actualWeight > largeMinDeadWeight;
    }

    private Double getAdditionalHandlingCharge(PieceVo pieceVo, Double additionalMinDeadWeightApply,
                                               Double additional1ndDimensionApply, Double additional2ndDimensionApply,
                                               Double additionalChargeApply, String dimUnit, String weightUnit) {
        if (ShipmentUtils.weightUnitConvert(pieceVo.getWeight(), weightUnit) > additionalMinDeadWeightApply
                || (pieceVo.getNonStandardPackage() != null && pieceVo.getNonStandardPackage())) {
            return additionalChargeApply;
        }
        Double l = (pieceVo.getDimensionL() == null ? 0d : pieceVo.getDimensionL());
        Double h = (pieceVo.getDimensionH() == null ?  0d : pieceVo.getDimensionH() );
        Double w = (pieceVo.getDimensionW() == null ? 0d : pieceVo.getDimensionW());
        Double[] dimensionsArray = {l, h, w};
        Arrays.sort(dimensionsArray);

        if (MathUtils.dimUnitConvert(dimensionsArray[2], dimUnit) > additional1ndDimensionApply) {
            return additionalChargeApply;
        }
        if (MathUtils.dimUnitConvert(dimensionsArray[1], dimUnit) > additional2ndDimensionApply) {
            return additionalChargeApply;
        }
        return 0D;
    }
}
