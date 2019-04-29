package com.gms.xms.workflow.task2.dhl.domestic;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.helper.DhlHelper;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Posted from GetDhlDomesticSurchargeTask
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class GetDhlDomesticSurchargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlDomesticSurchargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        ShipmentInfoVo shipmentInfo = context.get(Attributes.SHIPMENT_INFO_VO);
        Double totalWeight = context.getDouble(Attributes.SHIPMENT_TOTAL_WEIGHT);
        Map<String, ServiceAddConVo> serviceAddCon = shipmentInfo.getServiceAddConMap();
        WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);
        Double baseCharge = context.get(Attributes.CUSTOMER_COST);
        baseCharge = MathUtils.round(baseCharge, 2);
        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();
        AddressVo senderAddress = shipmentInfo.getSenderAddress();
        AddressVo receiverAddress = shipmentInfo.getReceiverAddress();
        String senderCountryId = String.valueOf(senderAddress.getCountry());
        String receiverCountryId = String.valueOf(receiverAddress.getCountry());

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
        Double totalCustomValue = context.get(Attributes.TOTAL_CUSTOM_VALUE);
        try {
            accessorialFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            accessorialFilter.setCarrier(Long.parseLong(String.valueOf(shipmentInfo.getServiceId())));
            List<AccessorialVo> accessorialVos = new ArrayList<>();

            // Remote surcharge
            Boolean senderRemote = DhlHelper.getInstance().hasRemoteSurcharge(senderAddress.getCountry(), senderAddress.getState(), senderAddress.getCity(), senderAddress.getPostalCode());
            Boolean recevierRemote = DhlHelper.getInstance().hasRemoteSurcharge(receiverAddress.getCountry(), receiverAddress.getState(), receiverAddress.getCity(), receiverAddress.getPostalCode());

            Double minRemoteSurcharge = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Min DHL domestic remote area cost"));
            Double remoteSurchargeRate = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("DHL domestic remote area rate"));
            Double remoteSurchargeAmount = totalWeight * remoteSurchargeRate;
            if (remoteSurchargeAmount < minRemoteSurcharge) {
                remoteSurchargeAmount = minRemoteSurcharge;
            }
            accessorialFilter.setBaseCharge(baseCharge);
            accessorialFilter.setQuotable(null);
            accessorialFilter.setDescription("Remote Area");
            AccessorialVo remoteSurcharge = new AccessorialVo();
            remoteSurcharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);

            if (senderRemote && recevierRemote) {
                remoteSurcharge.setValue(2 * remoteSurchargeAmount);
                accessorialVos.add(remoteSurcharge);
                totalSurcharge += remoteSurchargeAmount;
            } else if (senderRemote || recevierRemote) {
                remoteSurcharge.setValue(remoteSurchargeAmount);
                accessorialVos.add(remoteSurcharge);
                totalSurcharge += remoteSurchargeAmount;
            }

            accessorialFilter.setDescription(null);
            accessorialFilter.setBaseCharge(baseCharge + totalSurcharge);
            accessorialFilter.setQuotable(1);
            List<AccessorialVo> accessorialVosCheck = accessorialDao.selectSurChargeList(accessorialFilter);
            for (int i = 0; i < accessorialVosCheck.size(); i++) {
                AccessorialVo accessorialVo = accessorialVosCheck.get(i);
                if (accessorialVo.getDescription().equalsIgnoreCase("DTP Admin Fee")) {
                    if (serviceAddCon.get("dtpfee") != null && serviceAddCon.get("dtpfee").getValue() != null && serviceAddCon.get("dtpfee").getValue().equalsIgnoreCase("1")) {
                        accessorialVos.add(accessorialVo);
                    }
                } else {
                    totalSurcharge += accessorialVo.getValue();
                    accessorialVos.add(accessorialVo);
                }
            }

            if (serviceAddCon.get("insurance") != null && serviceAddCon.get("insurance").getValue() != null && serviceAddCon.get("insurance").getValue().equalsIgnoreCase("1")) {
                // Check maximum custom value
                Integer maxCustomValue = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("Maximum Insurance Coverage"));
                if (totalCustomValue > maxCustomValue) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, "Maximum insurance coverage is " + String.valueOf(maxCustomValue) + ".<br/>Please put the shipment value lower than " + String.valueOf(maxCustomValue) + ".");
                    return false;
                }

                insuranceValue = (totalCustomValue * (insurancePercent / 100) + insuranceBaseCharge);
                if (insuranceValue < insuranceMin) {
                    insuranceValue = insuranceMin;
                }
                insuranceValue = MathUtils.round(insuranceValue, 2);
                insurance.setValue(insuranceValue);
                insurance.setDescription("Additional protection");

                shipmentInfo.setWithInsurance(new BigDecimal(String.valueOf(insuranceValue)));
                accessorialVos.add(insurance);
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
                AccessorialVo nonStandardCharge = new AccessorialVo();
                nonStandardCharge.setDescription("Non-standard Shipping Charge");
                nonStandardCharge.setValue(nonStandardChargeValue);
                accessorialVos.add(nonStandardCharge);
                totalSurcharge += nonStandardChargeValue;
            }

            Boolean hasWarranty = false;
            if (serviceAddCon.get("aglWarranty") != null && serviceAddCon.get("aglWarranty").getValue() != null && serviceAddCon.get("aglWarranty").getValue().equals("1")) {
                hasWarranty = true;
            }

            Double warrantyChargeValue = 0D;
            if (hasWarranty) {
                accessorialFilter.setCarrier(15L);
                accessorialFilter.setDescription("agl Warranty Domestic");
                accessorialFilter.setQuotable(null);
                AccessorialVo warrantyCharge = new AccessorialVo();
                warrantyCharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                warrantyChargeValue = warrantyCharge.getValue();
                accessorialVos.add(warrantyCharge);
            }

            gstValue = (totalSurcharge + baseCharge + insuranceValue + warrantyChargeValue) * (gstRate / 100);
            gstValue = MathUtils.round(gstValue, 2);
            gst.setDescription("GST");
            gst.setCode("GST");
            gst.setValue(gstValue);
            accessorialVos.add(gst);

            totalCharge = totalSurcharge + baseCharge + gstValue + insuranceValue + warrantyChargeValue;
            totalCharge = MathUtils.round(totalCharge, 2);
            context.put(Attributes.TOTAL_CHARGE, totalCharge);
            context.put(Attributes.SURCHARGE_LIST, accessorialVos);
            context.put(Attributes.NON_STANDARD_CHARGE, nonStandardChargeValue);
            context.put(Attributes.INSURANCE_VALUE, insuranceValue);
            context.put(Attributes.SHIPMENT_INFO_VO, shipmentInfo);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
