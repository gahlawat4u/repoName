package com.gms.xms.workflow.task2.dhl.international;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.persistence.dao.DhlCountryDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.helper.DhlHelper;
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
public class GetDhlIntlSurchargeTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetDhlIntlSurchargeTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        ShipmentInfoVo shipmentInfo = context.get(Attributes.SHIPMENT_INFO_VO);
        Double totalWeight = context.getDouble(Attributes.SHIPMENT_TOTAL_WEIGHT);
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

        Boolean hasWarranty = false;
        if (serviceAddCon.get("aglWarranty") != null && serviceAddCon.get("aglWarranty").getValue() != null && serviceAddCon.get("aglWarranty").getValue().equals("1")) {
            hasWarranty = true;
        }

        WebshipLoginVo webshipLoginInfo = context.get(Attributes.USER_LOGGIN_INFO);
        Double baseCharge = context.get(Attributes.CUSTOMER_COST);
        AccessorialFilter accessorialFilter = new AccessorialFilter();
        AccessorialDao accessorialDao = new AccessorialDao();
        DhlCountryDao dhlCountryDao = new DhlCountryDao();
        CountryDao countryDao = new CountryDao();
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
        Double totalCustomValue = context.getDouble(Attributes.TOTAL_CUSTOM_VALUE);
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

                CustomerBaseRateVo customerBaseRate = context.get(Attributes.CUSTOMER_BASE_RATE);
                Double franchiseMarkup = 0D;
                try {
                    franchiseMarkup = context.get(Attributes.FRANCHISE_MARKUP);
                } catch (Exception e) {
                }
                handlingChargeValue = MathUtils.round((handlingChargeValue + (handlingChargeValue * franchiseMarkup) / 100), 2);

                Double minimumBaseChargeRate = 0D;
                try {
                    minimumBaseChargeRate = context.get(Attributes.MINIMUM_BASECHARGE_RATE);
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

            // Remote surcharge
            Boolean senderRemote = DhlHelper.getInstance().hasRemoteSurcharge(senderAddress.getCountry(), senderAddress.getState(), senderAddress.getCity(), senderAddress.getPostalCode());
            Boolean recevierRemote = DhlHelper.getInstance().hasRemoteSurcharge(receiverAddress.getCountry(), receiverAddress.getState(), receiverAddress.getCity(), receiverAddress.getPostalCode());

            Double minRemoteSurcharge = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("Min DHL international remote area cost"));
            Double remoteSurchargeRate = Double.parseDouble(SystemSettingCache.getInstance().getValueByKey("DHL international remote area rate"));
            Double remoteSurchargeAmount = totalWeight * remoteSurchargeRate;
            if (remoteSurchargeAmount < minRemoteSurcharge) {
                remoteSurchargeAmount = minRemoteSurcharge;
            }
            accessorialFilter.setBaseCharge(baseCharge);
            accessorialFilter.setQuotable(null);
            accessorialFilter.setDescription("REMOTE AREA");
            AccessorialVo remoteSurcharge = new AccessorialVo();
            remoteSurcharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);

            if (senderRemote && recevierRemote) {
                remoteSurchargeAmount = 2 * remoteSurchargeAmount;
                remoteSurcharge.setValue(remoteSurchargeAmount);
                accessorialVos.add(remoteSurcharge);
                totalSurcharge += remoteSurchargeAmount;
            } else if (senderRemote || recevierRemote) {
                remoteSurcharge.setValue(remoteSurchargeAmount);
                accessorialVos.add(remoteSurcharge);
                totalSurcharge += remoteSurchargeAmount;
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
            Double nonStandardChargeValue = 0D;
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

            //Xi 52 find country to check surcharge's flag
            CountryVo countryVo = countryDao.selectCountryDetail(receiverAddress.getCountry());
            DhlCountryVo dhlCountryVo = dhlCountryDao.selectDhlCountryByCountryCode(countryVo.getCountryCode());
            Double restrictedDestinationDhlCharge = 0D;
            Double elevatedRisk= 0D;//code by ..
            // XI 52 for add $restricted_destination_dhl_charge to fuel surcharge
            
          /*  code by rakesh sir*/
            /*if( dhlCountryVo.getRestrictedDestination() != null ){
            	 if (shipmentInfo.getBound() == 0 && shipmentInfo.getContentType().equals("WPX") && dhlCountryVo.getRestrictedDestination() ) {
                     accessorialFilter.setDescription("RESTRICTED DESTINATION");
                     AccessorialVo accessorialVo = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                     restrictedDestinationDhlCharge += accessorialVo.getValue();
                 }
            }
           */
            
            //code by shahabuddin
            
            if( dhlCountryVo.getDhlApCode().equalsIgnoreCase("IQ") || dhlCountryVo.getDhlApCode().equalsIgnoreCase("CF")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("CD")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("ER")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("IR")
            		|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("KP")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("LY")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("SO") 
            		|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("SD") || dhlCountryVo.getDhlApCode().equalsIgnoreCase("SY") || dhlCountryVo.getDhlApCode().equalsIgnoreCase("YE")){
           	 if (shipmentInfo.getBound() == 0 && shipmentInfo.getContentType().equals("WPX") ) {
                    accessorialFilter.setDescription("RESTRICTED DESTINATION");
                    AccessorialVo restrictedDestinationDhlChargeValue = new AccessorialVo();
                    		
                    restrictedDestinationDhlChargeValue = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
                    restrictedDestinationDhlChargeValue.setDescription("RESTRICTED DESTINATION");
                    restrictedDestinationDhlChargeValue.setValue(restrictedDestinationDhlChargeValue.getValue());
                    accessorialVos.add(restrictedDestinationDhlChargeValue);
                 
                   restrictedDestinationDhlCharge += restrictedDestinationDhlChargeValue.getValue();
                   
                    
                }
           }
            //code  elevatedRisk //code by ..
            if( dhlCountryVo.getDhlApCode().equalsIgnoreCase("AP") || dhlCountryVo.getDhlApCode().equalsIgnoreCase("BI")||  dhlCountryVo.getDhlApCode().equalsIgnoreCase("IQ")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("LY")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("NE")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("SD")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("SY")|| dhlCountryVo.getDhlApCode().equalsIgnoreCase("YE")){  
             if (shipmentInfo.getBound() == 0 && shipmentInfo.getContentType().equals("WPX") ) {          
            		accessorialFilter.setDescription("ELEVATED RISK");            
            		
            		AccessorialVo elevatedRiskCharge = new AccessorialVo();
            		
            		elevatedRiskCharge = accessorialDao.getAccessorialValueByFilter(accessorialFilter);
            		elevatedRiskCharge.setDescription("ELEVATED RISK");
            		elevatedRiskCharge.setValue(elevatedRiskCharge.getValue());
                    accessorialVos.add(elevatedRiskCharge);
            		elevatedRisk += elevatedRiskCharge.getValue();       
            		}      
             }
        
             Double totalChargesWithBase = baseCharge + totalSurcharge + restrictedDestinationDhlCharge + elevatedRisk; //code by shahabuddin
            accessorialFilter.setCustomerCode(webshipLoginInfo.getCustomerCode());
            accessorialFilter.setBaseCharge(totalChargesWithBase);
           
            accessorialFilter.setCarrier(Long.parseLong(String.valueOf(shipmentInfo.getServiceId())));
            accessorialFilter.setQuotable(1);

            List<AccessorialVo> accessorialVosCheck = accessorialDao.selectSurChargeList(accessorialFilter);
            for (int i = 0; i < accessorialVosCheck.size(); i++) {
                AccessorialVo accessorialVo = accessorialVosCheck.get(i);
                Boolean isAddded = true;
                if (!showAdminFee && accessorialVo.getDescription().equalsIgnoreCase("DTP Admin Fee")) {
                    isAddded = false;
                }
                
                //code for DDP
                
               /* if (!showAdminFee && accessorialVo.getDescription().equalsIgnoreCase("DDP")) {
                    isAddded = false;
                }*/
                
                // end code for DDP
               
                
                if (!preClearance && accessorialVo.getDescription().equalsIgnoreCase("Pre-Clearance")) {
                    isAddded = false;
                }

                if( dhlCountryVo.getElevatedRisk() != null ){
                if ((!dhlCountryVo.getElevatedRisk() || shipmentInfo.getBound() != 0) && accessorialVo.getDescription().equalsIgnoreCase("ELEVATED RISK")) {
                    isAddded = false;
                }}

                if( dhlCountryVo.getRestrictedDestination() != null){
                if ((!dhlCountryVo.getRestrictedDestination() || shipmentInfo.getBound() != 0 || shipmentInfo.getContentType().equals("DOX")) && accessorialVo.getDescription().equalsIgnoreCase("RESTRICTED DESTINATION")) {
                    isAddded = false;
                }
                }  
                
              
                
                
               /* if( dhlCountryVo.getElevatedRisk() != null ){
                    if ((dhlCountryVo.getElevatedRisk().equals("ELEVATED RISK") || shipmentInfo.getBound() == 0 && shipmentInfo.getContentType().equals("WPX")) && accessorialVo.getDescription().equalsIgnoreCase("ELEVATED RISK")) {
                        isAddded = true;
                    }}

                    if( dhlCountryVo.getRestrictedDestination() != null){
                    if ((dhlCountryVo.getRestrictedDestination().equals("RESTRICTED DESTINATION") || shipmentInfo.getBound() == 0 && shipmentInfo.getContentType().equals("WPX")) && accessorialVo.getDescription().equalsIgnoreCase("RESTRICTED DESTINATION")) {
                        isAddded = true;
                    }
                    }
            
*/                    
                if (isAddded) {
                    accessorialVos.add(accessorialVo);
                    totalSurcharge += accessorialVo.getValue();
                }
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
            
            
           //gstValue = (totalSurcharge + baseCharge + insuranceValue) * (gstRate / 100);  //code by rakesh sir
            gstValue = (totalSurcharge + baseCharge + insuranceValue + restrictedDestinationDhlCharge + elevatedRisk) * (gstRate / 100);  //code by shahabuddin
            gstValue = MathUtils.round(gstValue, 2);
            gst.setDescription("GST");
            gst.setCode("GST");
            gst.setValue(gstValue);
            // accessorialVos.add(gst);
            if (shipmentInfo.getBound() == -1) {
                totalCharge = 0.0;
            } else {
                //totalCharge = totalSurcharge + baseCharge + gstValue + insuranceValue + warrantyChargeValue; //code by rakesh sir
            	totalCharge = totalSurcharge + baseCharge + gstValue + insuranceValue + warrantyChargeValue + restrictedDestinationDhlCharge + elevatedRisk; //code b y shahabuddin
            }
            totalCharge = MathUtils.round(totalCharge, 2);
            context.put(Attributes.TOTAL_CHARGE, totalCharge);
            context.put(Attributes.SURCHARGE_LIST, accessorialVos);
            context.put(Attributes.NON_STANDARD_CHARGE, nonStandardChargeValue);
            context.put(Attributes.INSURANCE_VALUE, insuranceValue);
           // context.put(Attributes.ACCESSORIAL_INFO_VO,accessorialFilter);
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return true;
    }
}
