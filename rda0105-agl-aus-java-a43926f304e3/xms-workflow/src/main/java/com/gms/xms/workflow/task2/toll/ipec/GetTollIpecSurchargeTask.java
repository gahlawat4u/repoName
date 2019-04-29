package com.gms.xms.workflow.task2.toll.ipec;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.TollIpecPostcodeDao;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.TollIpecPostcodeVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.TollIpecZoneFilter;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.ShipmentUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author tkvcl
 */
public class GetTollIpecSurchargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetTollIpecSurchargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

        ShipmentInfoVo shipmentInfo = context.get(Attributes.SHIPMENT_INFO_VO);
        WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentTypeVo shipmentType = shipmentTypeDao.selectById(shipmentInfo.getShipmentTypeId());
        Double baseCharge = context.getDouble(Attributes.CUSTOMER_COST);   //code by rakesh sir
        //Double baseCharge = context.getDouble(Attributes.CARRIER_COST);   // code by shahabuddin
        baseCharge = MathUtils.round(baseCharge, 2);

        if (baseCharge < 0) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, "The selected service is not available for the given postal codes. Please kindly select other service type.");
        }

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
        String insurancePercentDefault = SystemSettingCache.getInstance().getValueByKey("TNT Domestic Insurance Percentage");
        String insuranceBaseChargeDefault = SystemSettingCache.getInstance().getValueByKey("TNT Domestic Insurance Base charge");

        Double insurancePercent = StringUtils.isBlank(insurancePercentDefault) ? 0D : Double.parseDouble(insurancePercentDefault);
        Double insuranceBaseCharge = StringUtils.isBlank(insuranceBaseChargeDefault) ? 0D : Double.parseDouble(insuranceBaseChargeDefault);
        Double insuranceValue = 0D;
        AccessorialVo insurance = new AccessorialVo();
        Double totalCustomValue = context.getDouble(Attributes.TOTAL_CUSTOM_VALUE);

        // Check service additional configs
        Map<String, ServiceAddConVo> serviceAddCon = shipmentInfo.getServiceAddConMap();
        Boolean hasInsurance = false;
        if (serviceAddCon.get("insurance") != null && serviceAddCon.get("insurance").getValue() != null && serviceAddCon.get("insurance").getValue().equals("1")) {
            hasInsurance = true;
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
            Map<String, String> arrayFuelSurcharge = new HashMap<String, String>();
            arrayFuelSurcharge.put("PRIORITY", "Priority Fuel Surcharge");
            arrayFuelSurcharge.put("ROAD EXPRESS", "Road Fuel Surcharge");
            arrayFuelSurcharge.put("DIRECT", "Direct Fuel Surcharge");
            arrayFuelSurcharge.put("TOLL DIRECT", "Direct Fuel Surcharge");
            List<AccessorialVo> accessorialVos = new ArrayList<>();
            for (int i = 0; i < accessorialList.size(); i++) {
                AccessorialVo accessorialVo = accessorialList.get(i);
                if (arrayFuelSurcharge.get(shipmentType.getShipmentTypeName()).equals(accessorialVo.getDescription())) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
            }

            List<TollIpecPostcodeVo> tollIpecPostcodeVoRm = new ArrayList<TollIpecPostcodeVo>();

            TollIpecPostcodeDao tollIpecZoneVoDao = new TollIpecPostcodeDao();
            TollIpecZoneFilter filterRemote = new TollIpecZoneFilter();
            String cityName = shipmentInfo.getSenderAddress().getCity();
            String cityNameR = shipmentInfo.getReceiverAddress().getCity();
            String postalCode = shipmentInfo.getSenderAddress().getPostalCode();
            String postalCodeR = shipmentInfo.getReceiverAddress().getPostalCode();

            filterRemote.setCityName(cityName);
            filterRemote.setCityNameR(cityNameR);
            filterRemote.setPostalCode(postalCode);
            filterRemote.setPostalCodeR(postalCodeR);

            tollIpecPostcodeVoRm = tollIpecZoneVoDao.selectForRemoteArea(filterRemote);

            String[] postalCodeRemote = {"0880", "880", "822", "0822", "2898", "2899", "7255", "7256", "7257"};

            Double quoteWeight = context.getDouble(Attributes.SHIPMENT_TOTAL_WEIGHT);
            Double rowName = Math.ceil(quoteWeight);
            AccessorialVo remoteAreaCharge = new AccessorialVo();
            remoteAreaCharge.setDescription("Remote Area Surcharge");

            if (shipmentType.getShipmentTypeId() == 216 && Arrays.asList(postalCodeRemote).contains(postalCode) && Arrays.asList(postalCodeRemote).contains(postalCodeR) && postalCodeR.equals(postalCode)) {
                remoteAreaCharge.setValue(3.64 * rowName);
                accessorialVos.add(remoteAreaCharge);
                totalSurcharge = totalSurcharge + (3.64 * rowName);
            } else if (shipmentType.getShipmentTypeId() == 216 && tollIpecPostcodeVoRm != null) {
                Double famount = 0D;
                for (TollIpecPostcodeVo tollIpecPostcodeVo : tollIpecPostcodeVoRm) {
                    if (famount != 0 && famount != tollIpecPostcodeVo.getAmount()) {

                    } else {
                        remoteAreaCharge.setValue(tollIpecPostcodeVo.getAmount());
                        accessorialVos.add(remoteAreaCharge);
                        totalSurcharge += tollIpecPostcodeVo.getAmount();
                    }
                }

            }

            if (hasInsurance) {
                insuranceValue = (totalCustomValue * (insurancePercent / 100) + insuranceBaseCharge);
                insuranceValue = MathUtils.round(insuranceValue, 2);
                insuranceValue = 0D;
                insurance.setValue(0D);
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
            context.put(Attributes.ERROR_MESSAGE, "Problem when caculate surcharge. Please contac Admin.");
            return false;
        }
        return true;
    }

}
