package com.gms.xms.workflow.task.tnt.domestic;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.dao.webship.TntRemoteAreaDao;
import com.gms.xms.persistence.dao.webship.TntSurchargeAreaRangeDao;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.*;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core.Task;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Posted from GetTntDomesticSurchargeTask
 * <p>
 * Author HungNT Date Aug 12, 2015
 */
public class GetTntDomesticSurchargeTask implements Task {
    private static final Log log = LogFactory.getLog(GetTntDomesticSurchargeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        FranchiseDao franchiseDao = new FranchiseDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

        ShipmentInfoVo shipmentInfo = GsonUtils.fromGson(context.get(Attributes.SHIPMENT_INFO_VO), ShipmentInfoVo.class);
        WebshipLoginVo webshipLoginInfo = GsonUtils.fromGson(context.get(Attributes.USER_LOGGIN_INFO), WebshipLoginVo.class);
        String franchiseCode = String.valueOf(webshipLoginInfo.getCustomerCode());
        franchiseCode = franchiseCode.substring(0, 3);
        FranchiseInfoVo franchiseInfo = franchiseDao.getFranchiseInfoByCode(franchiseCode);

        Double baseCharge = Double.parseDouble(context.get(Attributes.CUSTOMER_COST));
        baseCharge = MathUtils.round(baseCharge, 2);
        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();
        String senderCountryId = String.valueOf(shipmentInfo.getSenderAddress().getCountry());
        String senderCity = shipmentInfo.getSenderAddress().getCity();
        String senderPostalCode = shipmentInfo.getSenderAddress().getPostalCode();

        String receiverCountryId = String.valueOf(shipmentInfo.getReceiverAddress().getCountry());
        String receiverCity = shipmentInfo.getReceiverAddress().getCity();
        String receiverPostalCode = shipmentInfo.getReceiverAddress().getPostalCode();

        // Check maximum custom value - 10000
        Double totalCustomValue = Double.parseDouble(context.get(Attributes.TOTAL_CUSTOM_VALUE));
        Integer maxCustomValue = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("TNT Domestic Maximum Shipment Value"));
        if (totalCustomValue > maxCustomValue) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "Maximum value is AUD$" + String.valueOf(maxCustomValue) + ". <br/>Written authorisation must Be Requested if valued over this.<br/>Please contact your freight consultant at (" + franchiseInfo.getPhone() + ") or (" + franchiseInfo.getEmail() + ").");
            return false;
        }

        Integer remoteAreaPickup = 0;
        Integer remoteAreaDelivery = 0;

        TntRemoteAreaDao tntRemoteAreaDao = new TntRemoteAreaDao();
        TntRemoteAreaVo tntRemoteAreaVo = new TntRemoteAreaVo();
        try {
            tntRemoteAreaVo.setPostCode(senderPostalCode);
            tntRemoteAreaVo.setTown(senderCity);
            remoteAreaPickup = tntRemoteAreaDao.selectCountRemoteAreaService(tntRemoteAreaVo);
        } catch (Exception e) {
        }

        try {
            tntRemoteAreaVo.setPostCode(receiverPostalCode);
            tntRemoteAreaVo.setTown(receiverCity);
            remoteAreaDelivery = tntRemoteAreaDao.selectCountRemoteAreaService(tntRemoteAreaVo);
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

        // Insurance
        String insurancePercentDefault = SystemSettingCache.getInstance().getValueByKey("TNT Domestic Insurance Percentage");
        String insuranceBaseChargeDefault = SystemSettingCache.getInstance().getValueByKey("TNT Domestic Insurance Base charge");

        Double insurancePercent = StringUtils.isBlank(insurancePercentDefault) ? 0D : Double.parseDouble(insurancePercentDefault);
        Double insuranceBaseCharge = StringUtils.isBlank(insuranceBaseChargeDefault) ? 0D : Double.parseDouble(insuranceBaseChargeDefault);
        Double insuranceValue = 0D;
        AccessorialVo insurance = new AccessorialVo();

        // Check service additional config
        Map<String, ServiceAddConVo> serviceAddCon = shipmentInfo.getServiceAddConMap();
        Boolean hasDangerousGoods = false;
        if (serviceAddCon.get("dangerousgoods") != null && serviceAddCon.get("dangerousgoods").getValue() != null && serviceAddCon.get("dangerousgoods").getValue().equals("1")) {
            hasDangerousGoods = true;
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
            List<AccessorialVo> accessorialList = accessorialDao.selectSurChargeList(accessorialFilter);
            List<AccessorialVo> accessorialVos = new ArrayList<>();
            ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
            ShipmentTypeVo shipmentType = shipmentTypeDao.selectById(shipmentInfo.getShipmentTypeId());
            String shipmentTypeName = shipmentType.getShipmentTypeName();
            for (int i = 0; i < accessorialList.size(); i++) {
                AccessorialVo accessorialVo = accessorialList.get(i);
                if (hasDangerousGoods && accessorialVo.getDescription().equalsIgnoreCase("Dangerous Goods")) {
                    if (shipmentTypeName.indexOf("Road Express") != -1) {
                        accessorialVos.add(accessorialVo);
                        totalSurcharge += accessorialVo.getValue();
                    } else {
                        Double dangerousGoodsCharge = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Dangerous Goods Cost"));
                        accessorialVo.setValue(dangerousGoodsCharge);
                        accessorialVos.add(accessorialVo);
                        totalSurcharge += dangerousGoodsCharge;
                    }

                }
                if (shipmentInfo.getResidentialDelivery() != null && shipmentInfo.getResidentialDelivery() && accessorialVo.getDescription().equalsIgnoreCase("Residential Delivery")) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
                if (shipmentInfo.getResidentialPickup() != null && shipmentInfo.getResidentialPickup() && accessorialVo.getDescription().equalsIgnoreCase("Residential Pickup")) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
                if (shipmentTypeName.indexOf("Road Express") != -1) {
                    if (remoteAreaDelivery > 0 && accessorialVo.getDescription().equalsIgnoreCase("Remote Area Delivery")) {
                        accessorialVos.add(accessorialVo);
                        totalSurcharge += accessorialVo.getValue();
                    }
                    if (remoteAreaPickup > 0 && accessorialVo.getDescription().equalsIgnoreCase("Remote Area Pickup")) {
                        accessorialVos.add(accessorialVo);
                        totalSurcharge += accessorialVo.getValue();
                    }
                }
                if (accessorialVo.getDescription().equalsIgnoreCase("FUEL SURCHARGE")) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
            }

            // Check WA Regional Surcharge
            TntSurchargeAreaRangeDao tntSurchargeAreaRangeDao = new TntSurchargeAreaRangeDao();
            TntSurchargeAreaRangeFilter filter = new TntSurchargeAreaRangeFilter();
            filter.setCarrierId(String.valueOf(shipmentInfo.getServiceId()));
            filter.setServiceId(String.valueOf(shipmentInfo.getShipmentTypeId()));

            // Check sender
            filter.setCountry(receiverCountryId);
            filter.setPostalCode(receiverPostalCode);
            TntSurchargeAreaRangeVo tntSurchargeAreaRangeVo = tntSurchargeAreaRangeDao.selectWARegionalSurcharge(filter);
            if (tntSurchargeAreaRangeVo == null) {
                filter.setCountry(senderCountryId);
                filter.setPostalCode(senderPostalCode);
                tntSurchargeAreaRangeVo = tntSurchargeAreaRangeDao.selectWARegionalSurcharge(filter);
            }
            if (tntSurchargeAreaRangeVo != null) {
                AccessorialVo wACharge = new AccessorialVo();
                accessorialFilter.setQuotable(0);
                accessorialFilter.setDescription("WA Regional Surcharge");
                wACharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                accessorialVos.add(wACharge);
                totalSurcharge += wACharge.getValue();
            }

            if (hasInsurance) {
                insuranceValue = (totalCustomValue * (insurancePercent / 100) + insuranceBaseCharge);
                insuranceValue = MathUtils.round(insuranceValue, 2);
                insurance.setValue(insuranceValue);
                insurance.setDescription("Additional protection");

                shipmentInfo.setWithInsurance(new BigDecimal(String.valueOf(insuranceValue)));
                accessorialVos.add(insurance);
                totalSurcharge += insuranceValue;
            }
            // Non standard shipping charge
            Double nonStandardChargeValue = 0D;
            try {
                nonStandardChargeValue = ShipmentUtils.getTntDomNonStandardCharge(shipmentInfo.getPieces(), shipmentInfo.getDimensionUnit());
            } catch (Exception e) {
            }
            if (nonStandardChargeValue > 0) {
                AccessorialVo nonStandardCharge = new AccessorialVo();
                nonStandardCharge.setDescription("Over-sized Charge");
                nonStandardCharge.setValue(nonStandardChargeValue);
                accessorialVos.add(nonStandardCharge);
                totalSurcharge += nonStandardChargeValue;
            }
            totalSurcharge = MathUtils.round(totalSurcharge, 2);
            gstValue = MathUtils.round((totalSurcharge + baseCharge) * (gstRate / 100), 2);
            gstValue = MathUtils.round(gstValue, 2);
            gst.setDescription("GST");
            gst.setCode("GST");
            gst.setValue(gstValue);
            accessorialVos.add(gst);
            totalSurcharge += gstValue;

            totalCharge = totalSurcharge + baseCharge;
            totalCharge = MathUtils.round(totalCharge, 2);
            context.put(Attributes.TOTAL_CHARGE, MathUtils.double2String(totalCharge, 2));
            context.put(Attributes.NON_STANDARD_CHARGE, MathUtils.double2String(nonStandardChargeValue, 2));
            context.put(Attributes.INSURANCE_VALUE, MathUtils.double2String(insuranceValue, 2));
            context.put(Attributes.SURCHARGE_LIST, GsonUtils.toGson(accessorialVos));
            context.put(Attributes.SHIPMENT_INFO_VO, GsonUtils.toGson(shipmentInfo));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
