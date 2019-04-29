package com.gms.xms.workflow.task2.startrack;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 14, 2016 11:50:12 AM
 * <p>
 * Author huynd
 */
public class GetStartrackSurchargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetStartrackSurchargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        ShipmentInfoVo shipmentInfo = context.get(Attributes.SHIPMENT_INFO_VO);
        WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);
        Double baseCharge = context.getDouble(Attributes.CUSTOMER_COST);
        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();

        // GST
        String detaultVatRate = SystemSettingCache.getInstance().getValueByKey("Domestic Tax Percentage");
        Double gstRate = StringUtils.isBlank(detaultVatRate) ? 0D : Double.parseDouble(detaultVatRate);
        Double gstValue = 0D;
        AccessorialVo gst = new AccessorialVo();
        Double totalSurcharge = 0D;
        Double totalCharge = 0D;

        // Check service additional configs
        Map<String, ServiceAddConVo> serviceAddCon = shipmentInfo.getServiceAddConMap();
        Boolean hasDangerousGoods = false;
        if (serviceAddCon.get("dangerousgoods") != null && serviceAddCon.get("dangerousgoods").getValue() != null && serviceAddCon.get("dangerousgoods").getValue().equals("1")) {
            hasDangerousGoods = true;
        }

        Boolean hasWarranty = false;
        if (serviceAddCon.get("aglWarranty") != null && serviceAddCon.get("aglWarranty").getValue() != null && serviceAddCon.get("aglWarranty").getValue().equals("1")) {
            hasWarranty = true;
        }

        Boolean isPoBox = false;
        AddressVo receiverAddress = shipmentInfo.getReceiverAddress();
        if (receiverAddress.getAddress().toLowerCase().contains("po box") || receiverAddress.getAddress2().toLowerCase().contains("po box")) {
            isPoBox = true;
        }

        try {
            ShipmentTypeVo shipmentTypeVo = context.get(Attributes.SHIPMENT_TYPE_RESULT);
            accessorialFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            accessorialFilter.setBaseCharge(baseCharge);
            accessorialFilter.setCarrier(Long.parseLong(String.valueOf(shipmentInfo.getServiceId())));
            accessorialFilter.setQuotable(1);
            List<AccessorialVo> accessorialVosCheck = accessorialDao.selectSurChargeList(accessorialFilter);
            List<AccessorialVo> accessorialVos = new ArrayList<>();
            for (int i = 0; i < accessorialVosCheck.size(); i++) {
                AccessorialVo accessorialVo = accessorialVosCheck.get(i);
                if (accessorialVo.getDescription().equalsIgnoreCase("Fuel Surcharge") && accessorialVo.getAccessorialId() != 513) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
                if (accessorialVo.getDescription().equalsIgnoreCase("Po Box Delivery") && isPoBox) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
                if (accessorialVo.getDescription().equalsIgnoreCase("DG Surcharge") && hasDangerousGoods) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
                if (accessorialVo.getDescription().equalsIgnoreCase("Security Surcharge") && !"Road Express".equalsIgnoreCase(shipmentTypeVo.getShipmentTypeName())) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
            }
            Double insuranceValue = 0D;

            Double warrantyChargeValue = 0D;
            if (hasWarranty) {
                accessorialFilter.setCarrier(72L);
                accessorialFilter.setDescription("agl Warranty Domestic");
                accessorialFilter.setQuotable(null);
                AccessorialVo warrantyCharge = new AccessorialVo();
                warrantyCharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                warrantyChargeValue = warrantyCharge.getValue();
                accessorialVos.add(warrantyCharge);
            }

            gstValue = (totalSurcharge + baseCharge + warrantyChargeValue) * (gstRate / 100);
            gstValue = new BigDecimal(gstValue).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
            gst.setDescription("GST");
            gst.setCode("GST");
            gst.setValue(gstValue);
            accessorialVos.add(gst);
            totalCharge = totalSurcharge + baseCharge + gstValue + insuranceValue + warrantyChargeValue;
            totalCharge = MathUtils.round(totalCharge, 2);
            context.put(Attributes.TOTAL_CHARGE, totalCharge);
            context.put(Attributes.SURCHARGE_LIST, accessorialVos);
            context.put(Attributes.INSURANCE_VALUE, insuranceValue);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
