package com.gms.xms.workflow.task.tnt.international;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core.Task;
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
public class GetTntIntlSurchargeTask implements Task {
    private static final Log log = LogFactory.getLog(GetTntIntlSurchargeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentInfoVo shipmentInfo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
        Double rowName = Double.parseDouble(context.get(Attributes.SHIPMENT_TOTAL_WEIGHT));

        WebshipLoginVo webshipLoginInfo = GsonUtils.fromGson(context.get(Attributes.USER_LOGGIN_INFO), WebshipLoginVo.class);
        Double baseCharge = Double.parseDouble(context.get(Attributes.CUSTOMER_COST));
        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();

        // GST
        String detaultVatRate = SystemSettingCache.getInstance().getValueByKey("VAT percent based on Base Charge");
        Double gstRate = StringUtils.isBlank(detaultVatRate) ? 0D : Double.parseDouble(detaultVatRate);
        Double gstValue = 0D;
        AccessorialVo gst = new AccessorialVo();
        Double totalSurcharge = 0D;
        Double totalCharge = 0D;

        Double totalCustomValue = Double.parseDouble(context.get(Attributes.TOTAL_CUSTOM_VALUE));

        // Check service additional configs
        Map<String, ServiceAddConVo> serviceAddCon = shipmentInfo.getServiceAddConMap();
        Boolean showAdminFee = false;
        if (serviceAddCon.get("dtpfee") != null && serviceAddCon.get("dtpfee").getValue() != null && serviceAddCon.get("dtpfee").getValue().equals("1")) {
            showAdminFee = true;
        }

        Boolean hasInsurance = false;
        if (serviceAddCon.get("insurance") != null && serviceAddCon.get("insurance").getValue() != null && serviceAddCon.get("insurance").getValue().equals("1")) {
            hasInsurance = true;
        }

        try {
            accessorialFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            accessorialFilter.setBaseCharge(baseCharge);
            accessorialFilter.setCarrier(Long.parseLong(String.valueOf(shipmentInfo.getServiceId())));
            accessorialFilter.setQuotable(1);
            List<AccessorialVo> accessorialVosCheck = accessorialDao.selectSurChargeList(accessorialFilter);
            List<AccessorialVo> accessorialVos = new ArrayList<>();
            for (int i = 0; i < accessorialVosCheck.size(); i++) {
                AccessorialVo accessorialVo = accessorialVosCheck.get(i);
                Boolean isAddded = true;
                if (!showAdminFee && accessorialVo.getDescription().equalsIgnoreCase("DTP Admin Fee")) {
                    isAddded = false;
                }

                if (accessorialVo.getDescription().equalsIgnoreCase("Security Surcharge")) {
                    Double securitySurchargeMin = 0D;
                    Double securitySurchargeMax = 0D;
                    Double securitySurchargePerWeight = 0D;

                    securitySurchargeMin = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Security Surcharge Min"));
                    securitySurchargeMax = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Security Surcharge Max"));
                    securitySurchargePerWeight = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Security Surcharge Per kg"));

                    Double securitySurchageValue = rowName * securitySurchargePerWeight;
                    if (securitySurchageValue < securitySurchargeMin) {
                        securitySurchageValue = securitySurchargeMin;
                    } else if (securitySurchageValue > securitySurchargeMax) {
                        securitySurchageValue = securitySurchargeMax;
                    }
                    accessorialVo.setValue(securitySurchageValue);
                }

                if (isAddded) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
            }
            Double insuranceValue = 0D;
            if (hasInsurance) {
                // Check maximum custom value
                Integer maxCustomValue = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("TNT International Maximum Insurance Coverage"));
                String maxMessage = SystemSettingCache.getInstance().getValueByKey("tnt international insurance goods value above maximize message");
                if (totalCustomValue > maxCustomValue) {
                    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
                    context.put(Attributes.ERROR_MESSAGE, maxMessage);
                    return false;
                }

                // Insurance
                Double insuranceValuePerKg = 0D;
                try {
                    insuranceValuePerKg = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("TNT International Insurance per AUD 100"));
                } catch (Exception e) {
                    insuranceValuePerKg = 1.3;
                }

                Integer tntInsuranceKg = 0;
                try {
                    tntInsuranceKg = (int) (totalCustomValue / 100);
                    if ((totalCustomValue % 100) != 0) {
                        tntInsuranceKg++;
                    }
                } catch (Exception e) {
                }

                insuranceValue = tntInsuranceKg * insuranceValuePerKg;
                insuranceValue = MathUtils.round(insuranceValue, 2);
                AccessorialVo insurance = new AccessorialVo();
                insurance.setDescription("Additional protection");
                insurance.setValue(insuranceValue);
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
            context.put(Attributes.INSURANCE_VALUE, MathUtils.double2String(insuranceValue, 2));
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return true;
    }
}
