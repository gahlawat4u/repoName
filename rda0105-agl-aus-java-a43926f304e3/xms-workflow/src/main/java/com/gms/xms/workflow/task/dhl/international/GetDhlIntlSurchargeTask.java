package com.gms.xms.workflow.task.dhl.international;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Posted from GetDhlDomesticSurchargeTask
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class GetDhlIntlSurchargeTask implements Task {
    private static final Log log = LogFactory.getLog(GetDhlIntlSurchargeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentInfoVo shipmentInfo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
        Map<String, ServiceAddConVo> serviceAddCon = shipmentInfo.getServiceAddConMap();
        Boolean showAdminFee = false;
        if (serviceAddCon.get("dtpfee") != null && serviceAddCon.get("dtpfee").getValue() != null && serviceAddCon.get("dtpfee").getValue().equals("1")) {
            showAdminFee = true;
        }

        Boolean preClearance = false;
        if (serviceAddCon.get("preclearance") != null && serviceAddCon.get("preclearance").getValue() != null && serviceAddCon.get("preclearance").getValue().equals("1")) {
            preClearance = true;
        }

        Boolean hasInsurance = false;
        if (serviceAddCon.get("insurance") != null && serviceAddCon.get("insurance").getValue() != null && serviceAddCon.get("insurance").getValue().equals("1")) {
            hasInsurance = true;
        }

        WebshipLoginVo webshipLoginInfo = GsonUtils.fromGson(context.get(Attributes.USER_LOGGIN_INFO), WebshipLoginVo.class);
        Double baseCharge = Double.parseDouble(context.get(Attributes.CUSTOMER_COST));
        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();
        String senderCountryId = String.valueOf(shipmentInfo.getSenderAddress().getCountry());
        String receiverCountryId = String.valueOf(shipmentInfo.getReceiverAddress().getCountry());

        // GST
        String detaultVatRate = SystemSettingCache.getInstance().getValueByKey("VAT percent based on Base Charge");
        Double gstRate = StringUtils.isBlank(detaultVatRate) ? 0D : Double.parseDouble(detaultVatRate);
        if (ShipmentUtils.isDomestic(senderCountryId, receiverCountryId)) {
            gstRate = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Domestic Tax Percentage"));
        }
        Double gstValue = 0D;
        AccessorialVo gst = new AccessorialVo();
        Double totalSurcharge = 0D;
        Double totalCharge = 0D;

        // Insurance
        String insuranceMinDefault = SystemSettingCache.getInstance().getValueByKey("DHL-Additional-Insurance Minum");
        String insurancePercentDefault = SystemSettingCache.getInstance().getValueByKey("DHL-Additional-Insurance Percentage");
        String insuranceBaseChargeDefault = SystemSettingCache.getInstance().getValueByKey("DHL-Additional-Insurance Base Charge");

        Double insuranceMin = StringUtils.isBlank(insuranceMinDefault) ? 0D : Double.parseDouble(insuranceMinDefault);
        Double insurancePercent = StringUtils.isBlank(insurancePercentDefault) ? 0D : Double.parseDouble(insurancePercentDefault);
        Double insuranceBaseCharge = StringUtils.isBlank(insuranceBaseChargeDefault) ? 0D : Double.parseDouble(insuranceBaseChargeDefault);
        Double insuranceValue = 0D;
        AccessorialVo insurance = new AccessorialVo();
        Double totalCustomValue = Double.parseDouble(context.get(Attributes.TOTAL_CUSTOM_VALUE));
        try {
            List<AccessorialVo> accessorialVos = new ArrayList<>();
            Double handlingChargeValue = 0D;
            accessorialFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            accessorialFilter.setCarrier(Long.parseLong(String.valueOf(shipmentInfo.getServiceId())));
            if (shipmentInfo.getShipmentTypeId() == 122) {
                accessorialFilter.setBaseCharge(baseCharge);
                accessorialFilter.setQuotable(null);
                accessorialFilter.setDescription("HANDLING CHARGE");
                AccessorialVo handlingCharge = new AccessorialVo();
                handlingCharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                handlingChargeValue = handlingCharge.getValue();

                CustomerBaseRateVo customerBaseRate = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_BASE_RATE), CustomerBaseRateVo.class);
                Double franchiseMarkup = 0D;
                try {
                    franchiseMarkup = Double.parseDouble(context.get(Attributes.FRANCHISE_MARKUP));
                } catch (Exception e) {
                }
                handlingChargeValue = MathUtils.round((handlingChargeValue + (handlingChargeValue * franchiseMarkup) / 100), 2);

                Double minimumBaseChargeRate = 0D;
                try {
                    minimumBaseChargeRate = Double.parseDouble(context.get(Attributes.MINIMUM_BASECHARGE_RATE));
                } catch (Exception e) {
                }
                Double baseRate = 0D;
                try {
                    baseRate = customerBaseRate.getRate();
                } catch (Exception e) {
                }

                switch (customerBaseRate.getRateType()) {
                    case 1:
                        if (baseRate > minimumBaseChargeRate) {
                            handlingChargeValue = MathUtils.round((handlingChargeValue + (handlingChargeValue * baseRate) / 100), 2);
                        } else {
                            handlingChargeValue = MathUtils.round((handlingChargeValue / (1 - (minimumBaseChargeRate / 100))), 2);
                        }
                        break;
                    case 2:
                        handlingChargeValue = MathUtils.round((handlingChargeValue / (1 - (baseRate / 100))), 2);
                        break;
                }
                totalSurcharge += handlingChargeValue;
                handlingCharge.setValue(handlingChargeValue);
                accessorialVos.add(handlingCharge);
            }

            accessorialFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            accessorialFilter.setBaseCharge(baseCharge + totalSurcharge);
            accessorialFilter.setCarrier(Long.parseLong(String.valueOf(shipmentInfo.getServiceId())));
            accessorialFilter.setQuotable(1);

            List<AccessorialVo> accessorialVosCheck = accessorialDao.selectSurChargeList(accessorialFilter);
            for (int i = 0; i < accessorialVosCheck.size(); i++) {
                AccessorialVo accessorialVo = accessorialVosCheck.get(i);
                Boolean isAddded = true;
                if (!showAdminFee && accessorialVo.getDescription().equalsIgnoreCase("DTP Admin Fee")) {
                    isAddded = false;
                }

                if (!preClearance && accessorialVo.getDescription().equalsIgnoreCase("Pre-Clearance")) {
                    isAddded = false;
                }

                if (isAddded) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
            }

            // Non standard shipping charge
            Boolean hasNonStandardCharge = false;
            Double dimL = 0D;
            Double dimH = 0D;
            Double dimW = 0D;

            Double nonStandardDimension = 0D;
            try {
                nonStandardDimension = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Non-standard Shipping Dimension"));
            } catch (Exception e) {
            }

            Double nonStandardWeight = 0D;
            try {
                nonStandardWeight = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Non-standard Shipping Weight"));
            } catch (Exception e) {
            }

            Double nonStandardChargeValue = 0D;
            Double weight = 0D;
            for (PieceVo pieceVo : shipmentInfo.getPieces()) {
                weight = ShipmentUtils.weightUnitConvert(pieceVo.getWeight(), shipmentInfo.getWeightUnit());
                dimL = pieceVo.getDimensionL();
                dimH = pieceVo.getDimensionH();
                dimW = pieceVo.getDimensionW();
                if (dimL != null && dimH != null && dimW != null) {
                    dimL = MathUtils.dimUnitConvert(dimL, shipmentInfo.getDimensionUnit());
                    dimH = MathUtils.dimUnitConvert(dimH, shipmentInfo.getDimensionUnit());
                    dimW = MathUtils.dimUnitConvert(dimW, shipmentInfo.getDimensionUnit());
                    if (dimL > nonStandardDimension || dimH > nonStandardDimension || dimW > nonStandardDimension) {
                        hasNonStandardCharge = true;
                    }
                }
                if (weight > nonStandardWeight) {
                    hasNonStandardCharge = true;
                }
            }

            if (hasNonStandardCharge) {
                try {
                    nonStandardChargeValue = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Non-standard Shipping Charge"));
                } catch (Exception e) {
                }
                accessorialFilter.setDescription("Non-standard Shipping Charge");
                accessorialFilter.setQuotable(null);
                AccessorialVo nonStandardCharge = new AccessorialVo();
                nonStandardCharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                nonStandardCharge.setDescription("Non-standard Shipping Charge");
                nonStandardCharge.setValue(nonStandardChargeValue);
                accessorialVos.add(nonStandardCharge);
                totalSurcharge += nonStandardChargeValue;
            }

            if (hasInsurance) {
                Integer maxCustomValue = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("Maximum Insurance Coverage"));
                if (totalCustomValue > maxCustomValue) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, "Maximum insurance coverage is " + String.valueOf(maxCustomValue) + ".<br/>Please put the shipment value lower than " + String.valueOf(maxCustomValue) + ".");
                    return false;
                }
                insuranceValue = (totalCustomValue * (insurancePercent / 100) + insuranceBaseCharge);
                insuranceValue = MathUtils.round(insuranceValue, 2);
                if (insuranceValue < insuranceMin) {
                    insuranceValue = insuranceMin;
                }
                insurance.setValue(insuranceValue);
                insurance.setDescription("Additional protection");

                accessorialVos.add(insurance);
            }
            gstValue = (totalSurcharge + baseCharge + insuranceValue) * (gstRate / 100);
            gst.setDescription("GST");
            gst.setCode("GST");
            gst.setValue(gstValue);
            // accessorialVos.add(gst);
            totalCharge = totalSurcharge + baseCharge + gstValue + insuranceValue;
            totalCharge = MathUtils.round(totalCharge, 2);
            context.put(Attributes.TOTAL_CHARGE, MathUtils.double2String(totalCharge, 2));
            context.put(Attributes.SURCHARGE_LIST, GsonUtils.toGson(accessorialVos));
            context.put(Attributes.NON_STANDARD_CHARGE, MathUtils.double2String(nonStandardChargeValue, 2));
            context.put(Attributes.INSURANCE_VALUE, MathUtils.double2String(insuranceValue, 2));
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return true;
    }
}
