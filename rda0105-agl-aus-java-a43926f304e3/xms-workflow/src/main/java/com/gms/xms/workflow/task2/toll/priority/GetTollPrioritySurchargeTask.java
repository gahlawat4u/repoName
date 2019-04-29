package com.gms.xms.workflow.task2.toll.priority;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.webship.TollRemoteAreaDao;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.toll.TollRemoteAreaFilter;
import com.gms.xms.txndb.vo.webship.toll.TollRemoteAreaVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Posted from GetTollPrioritySurchargeTask
 * <p>
 * Author HungNT Date Aug 26, 2015
 */
public class GetTollPrioritySurchargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollPrioritySurchargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

        ShipmentInfoVo shipmentInfo = context.get(Attributes.SHIPMENT_INFO_VO);
        WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentTypeVo shipmentType = shipmentTypeDao.selectById(shipmentInfo.getShipmentTypeId());
        Double baseCharge = context.getDouble(Attributes.CUSTOMER_COST);
        baseCharge = MathUtils.round(baseCharge, 2);

        if (baseCharge < 0) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "The selected service is not available for the given postal codes. Please kindly select other service type.");
        }

        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();
        String senderCountryId = String.valueOf(shipmentInfo.getSenderAddress().getCountry());

        String receiverCountryId = String.valueOf(shipmentInfo.getReceiverAddress().getCountry());
        String receiverCity = shipmentInfo.getReceiverAddress().getCity().trim();
        String receiverState = shipmentInfo.getReceiverAddress().getState().trim();
        Integer receiverPostalCode = 0;
        try {
            receiverPostalCode = Integer.parseInt(shipmentInfo.getReceiverAddress().getPostalCode());
        } catch (Exception e) {
        }

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

        // Check service additional configs
        Map<String, ServiceAddConVo> serviceAddCon = shipmentInfo.getServiceAddConMap();
        Boolean hasInsurance = false;
        if (serviceAddCon.get("insurance") != null && serviceAddCon.get("insurance").getValue() != null && serviceAddCon.get("insurance").getValue().equals("1")) {
            hasInsurance = true;
        }
        Boolean hasTimeCriticial = false;
        if (serviceAddCon.get("timecriticial") != null && serviceAddCon.get("timecriticial").getValue() != null && serviceAddCon.get("timecriticial").getValue().equals("1")) {
            hasTimeCriticial = true;
        }
        Boolean hasWarranty = false;
        if (serviceAddCon.get("aglWarranty") != null && serviceAddCon.get("aglWarranty").getValue() != null && serviceAddCon.get("aglWarranty").getValue().equals("1")) {
            hasWarranty = true;
        }
        try {
            accessorialFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            accessorialFilter.setBaseCharge(baseCharge);
            accessorialFilter.setCarrier(Long.parseLong(String.valueOf(shipmentInfo.getServiceId())));
            accessorialFilter.setQuotable(1);
            List<AccessorialVo> accessorialList = accessorialDao.selectSurChargeList(accessorialFilter);
            List<AccessorialVo> accessorialVos = new ArrayList<>();
            for (int i = 0; i < accessorialList.size(); i++) {
                AccessorialVo accessorialVo = accessorialList.get(i);
                if (accessorialVo.getDescription().equalsIgnoreCase("FUEL SURCHARGE")) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
                if (accessorialVo.getDescription().equalsIgnoreCase("Time Critical Surcharge")) {
                    if (hasTimeCriticial) {
                        accessorialVos.add(accessorialVo);
                        totalSurcharge += accessorialVo.getValue();
                    }
                }
            }

            TollRemoteAreaDao remoteAreaDao = new TollRemoteAreaDao();
            TollRemoteAreaFilter remoteAreaFilter = new TollRemoteAreaFilter();
            remoteAreaFilter.setTown(receiverCity);
            remoteAreaFilter.setPostCode(receiverPostalCode);

            TollRemoteAreaVo remoteAreaVo = remoteAreaDao.selectRemoteArea(remoteAreaFilter);

            if (remoteAreaVo != null) {
                String tollRemoteType = remoteAreaVo.getType();
                String remoteAreaDescription = "Remote Area Surcharge Tier " + tollRemoteType;
                accessorialFilter.setDescription(remoteAreaDescription);
                accessorialFilter.setQuotable(null);
                AccessorialVo remoteAreaCharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                accessorialVos.add(remoteAreaCharge);
                totalSurcharge += remoteAreaCharge.getValue();
            }

            String shipmentTypeName = shipmentType.getShipmentTypeName().trim();
            if (shipmentTypeName.equalsIgnoreCase("Parcels Overnight") || shipmentTypeName.equalsIgnoreCase("Parcels SameDay") || shipmentTypeName.equalsIgnoreCase("Auswide 1kg Satchel") || shipmentTypeName.equalsIgnoreCase("Auswide 3kg Satchel") || shipmentTypeName.equalsIgnoreCase("Auswide 5kg Satchel")) {
                if (shipmentTypeName.equalsIgnoreCase("Auswide 1kg Satchel") || shipmentTypeName.equalsIgnoreCase("Auswide 3kg Satchel") || shipmentTypeName.equalsIgnoreCase("Auswide 5kg Satchel")) {
                    if (receiverState.equalsIgnoreCase("NT") && receiverPostalCode >= 800 && receiverPostalCode <= 899) {
                        accessorialFilter.setDescription("Northern Territory Surcharge");
                        accessorialFilter.setQuotable(null);
                        AccessorialVo northernTerritorySurcharge = new AccessorialVo();
                        northernTerritorySurcharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                        accessorialVos.add(northernTerritorySurcharge);
                        totalSurcharge += northernTerritorySurcharge.getValue();
                    }
                }
                accessorialFilter.setDescription("Security");
                accessorialFilter.setQuotable(null);
                AccessorialVo securitySurcharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                accessorialVos.add(securitySurcharge);
                totalSurcharge += securitySurcharge.getValue();
            }

            // Insurance

            Double insuranceValue = 0D;
            AccessorialVo insurance = new AccessorialVo();
            Double totalCustomValue = context.getDouble(Attributes.TOTAL_CUSTOM_VALUE);
            if (hasInsurance) {
                insuranceValue = totalCustomValue;
                insuranceValue = MathUtils.round(insuranceValue, 2);
                insurance.setValue(insuranceValue);
                insurance.setDescription("Additional protection");

                shipmentInfo.setWithInsurance(new BigDecimal(String.valueOf(insuranceValue)));
                accessorialVos.add(insurance);
                totalSurcharge += insuranceValue;
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

            gstValue = (totalSurcharge + baseCharge + warrantyChargeValue) * (gstRate / 100);
            gstValue = MathUtils.round(gstValue, 2);
            gst.setDescription("GST");
            gst.setCode("GST");
            gst.setValue(gstValue);
            accessorialVos.add(gst);
            totalSurcharge += gstValue;

            totalCharge = totalSurcharge + baseCharge + warrantyChargeValue;
            totalCharge = MathUtils.round(totalCharge, 2);
            context.put(Attributes.TOTAL_CHARGE, totalCharge);
            context.put(Attributes.INSURANCE_VALUE, insuranceValue);
            context.put(Attributes.SURCHARGE_LIST, accessorialVos);
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
