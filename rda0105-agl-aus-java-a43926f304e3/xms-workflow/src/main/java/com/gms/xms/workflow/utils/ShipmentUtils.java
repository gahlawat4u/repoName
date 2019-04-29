package com.gms.xms.workflow.utils;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.RateSheetDao;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.CarrierSuburbFilter;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.RateSheetFilter;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.workflow.client.CarrierSuburbClient;
import com.gms.xms.workflow.client.integration.request.CarrierSuburbRequest;
import com.gms.xms.workflow.client.integration.response.CarrierSuburbResponse;
import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Posted from ShipmentUtils
 * <p>
 * Author HungNT Date Apr 17, 2015
 */
public class ShipmentUtils {
    public static float roundToNearHalf(float number) {
        float result;
        if (number == 0) {
            result = 0.5F;
        }
        int roundNumber = Math.round(number);
        if (roundNumber < number) {
            result = roundNumber + 0.5F;
        } else {
            result = roundNumber;
        }
        return result;
    }

    public static Double getGrossWeight(double w, double h, double l, String dimUnit, Integer forceVolWeight) {
        double vol_weight = 5000D;
        try {
            vol_weight = Float.parseFloat(SystemSettingCache.getInstance().getValueByKey("Volume Weight Divided By"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (forceVolWeight > 0) {
            vol_weight = forceVolWeight;
        }
        w = MathUtils.dimUnitConvert(w, dimUnit);
        l = MathUtils.dimUnitConvert(l, dimUnit);
        h = MathUtils.dimUnitConvert(h, dimUnit);
        double grossweight = (w * l * h) / vol_weight;
        grossweight = MathUtils.round(grossweight, 2);
        return grossweight;
    }

    public static Integer getForceVolWeight(Integer serviceId) throws DaoException {

        SystemSettingDao settingDao = new SystemSettingDao();

        Integer forceVolWeight = 0;
        String defaultOriginCountry = settingDao.getSystemSettingByName("Default Origin Country").getSettingValue().trim();

        if (serviceId == 1) { // DHL Domestic
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (DHL)").getSettingValue().trim()); // 5000;
        } else if (serviceId == 15) { // AAE
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (DHL Domestic)").getSettingValue().trim()); // 4000;
        } else if (serviceId == 2) { // AAE
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (AAE)").getSettingValue().trim()); // 6000;
        } else if (serviceId == 3) { // TNT
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (TNT)").getSettingValue().trim()); // 5000;
        } else if (serviceId == 52) { // Toll
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (TOLL)").getSettingValue().trim()); // 5000;
        } else if (serviceId == 59) { // TOll Ipec
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (TOLL IPEC)").getSettingValue().trim()); // 5000;
        } else if (defaultOriginCountry.equalsIgnoreCase("218") && (serviceId == 55 || serviceId == 54)) {
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (TNT)").getSettingValue().trim()); // 5000;
        } else if (serviceId == 54) { // TNT Int
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (TNT Int)").getSettingValue().trim()); // 6000;
        } else if (serviceId == 40) { // FedEx
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (FedEx)").getSettingValue().trim()); // 5000;
        } else if (serviceId == 50) { // UKMail
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (UKMail)").getSettingValue().trim()); // 4000;
        } else if (serviceId == 400) { // UPS
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (UPS)").getSettingValue().trim()); // 5000;
        }

        if (forceVolWeight == 0)
            forceVolWeight = 5000;

        return forceVolWeight;
    }

    public static Integer getForceVolWeightStartrack(String shipmentTypeName) throws DaoException {

        SystemSettingDao settingDao = new SystemSettingDao();

        Integer forceVolWeight = 0;

        if ("road express".equalsIgnoreCase(shipmentTypeName) || "premium air freight".equalsIgnoreCase(shipmentTypeName)) {
            // Road Express & Premium air freight
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (Startrack)").getSettingValue().trim()); // 4000;
        } else { // FPP
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (Startrack FPP)").getSettingValue().trim()); // 5263;
        }

        if (forceVolWeight == 0)
            forceVolWeight = 5000;

        return forceVolWeight;
    }

    public static Float roundFloatBy2(Float number) {
        Locale l = new Locale("en", "US");
        String s = String.format(l, "%.2f", number);
        Float floatNumber = Float.parseFloat(s);
        return floatNumber;
    }

    public static Boolean isDomestic(String senderCountryId, String receiverCountryId) throws Exception {
        String defaultOriginCountryId = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
        if (StringUtils.equalsIgnoreCase(senderCountryId, receiverCountryId) && StringUtils.equalsIgnoreCase(senderCountryId, defaultOriginCountryId) && StringUtils.equalsIgnoreCase(receiverCountryId, defaultOriginCountryId)) {
            return true;
        }
        return false;
    }

    public static Integer getBound(Long senderCountryId, Long receiverCountryId) {
        Integer bound = 0; // Outound
        Long defaultCountryId = Long.parseLong(SystemSettingCache.getInstance().getValueByKey("Default Origin Country"));

        if (!senderCountryId.equals(defaultCountryId)  && receiverCountryId.equals(defaultCountryId)) { // Inbound
            bound = 1;
        }

        if (senderCountryId.equals(receiverCountryId) && senderCountryId.equals(defaultCountryId) && receiverCountryId.equals(defaultCountryId)) { // domestic
            bound = 15;
        }

        if (!senderCountryId.equals(receiverCountryId) && !senderCountryId.equals(defaultCountryId) && !receiverCountryId.equals(defaultCountryId)) { // non-centralised
            bound = -1;
        }

        return bound;
    }

    public static String getDhlZone(String dhlApZone, Integer bound) {
        String dhlZoneArr[] = dhlApZone.split(",");
        if (bound == null || bound == 0) {
            return dhlZoneArr[0];
        } else if (bound != 0 && dhlZoneArr.length > 1) {
            return dhlZoneArr[1];
        } else {
            return dhlZoneArr[0];
        }
    }

    public static String encryptPasswordReset(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean checkColumnIsExist(Long sheetId, String columnName) {
        RateSheetDao rateSheetDao = new RateSheetDao();
        RateSheetFilter rateSheetFilter = new RateSheetFilter();
        rateSheetFilter.setSheetId(sheetId);
        rateSheetFilter.setColumnName(columnName);
        try {
            Integer totalColumn = rateSheetDao.selectCountColumn(rateSheetFilter);
            if (totalColumn >= 1) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static Double weightUnitConvert(Double weight, String weightUnit) {
        if (weightUnit.equalsIgnoreCase("lb")) {
            Double convertRate = 2.20462262;
            weight = weight / convertRate;
            weight = MathUtils.round(weight, 2);
        }
        return weight;
    }

    public static String checkRoundWeight(String weight, Integer serviceId, Integer originCountryId) {
        Locale.setDefault(Locale.US);
        Double weightD = Double.parseDouble(weight);
        if (originCountryId == 16 && (serviceId == 15 || serviceId == 2 || serviceId == 3 || serviceId == 52)) {
            weightD = Math.ceil(weightD);
            weight = MathUtils.double2String(weightD, 2);
        }
        return weight;
    }

    public static String getTollCheckDigit(String origino) {
        Integer productNo = 0; // type=integer
        Integer lengthNo = origino.length() - 1;

        Integer multiplier = 3;

        for (Integer i = lengthNo; i >= 0; i--) {
            Integer digit = 0;
            String chartocheck = origino.substring(i, i + 1);
            if (StringUtils.isNumeric(chartocheck)) {
                digit = chartocheck.charAt(0) - "0".charAt(0);
            } else {
                digit = (chartocheck.charAt(0) - "A".charAt(0)) % 10;
            }

            productNo = productNo + digit * multiplier;
            if (multiplier == 3) {
                multiplier = 1;
            } else {
                multiplier = 3;
            }
        }

        String checkDigitstr = productNo.toString();
        String checkDigit = String.valueOf(10 - checkDigitstr.substring(checkDigitstr.length() - 1).charAt(0) - "0".charAt(0));
        if (checkDigit.toString().length() > 1) {
            checkDigit = checkDigit.toString().substring(checkDigit.toString().length() - 1);
        }

        checkDigit = origino.toString().concat(checkDigit);
        return checkDigit;
    }

    /**
     * @param serviceId
     * @return Name file FTL or PDF or HTML for view airbill
     */
    public static String checkViewAirbill(Integer serviceId) {
        if (serviceId == 1 || serviceId == 15) {
            return "";
        }
        if (serviceId == 2) {
            return "aaeViewAirbill";
        } else if (serviceId == 3) {
            return "tntDomViewAirbill";
        } else if (serviceId == 40) {
            return "fedExViewAirbill";
        } else if (serviceId == 52) {
            return "tollPriorityViewAirbill";
        } else if (serviceId == 54) {
            return "tntIntViewAirbill";
        } else if (serviceId == 59) {
            return "tollIpecViewAirbill";
        }
        return "";
    }

    /**
     * @param serviceId
     * @return Name file FTL or PDF or HTML for view airbill
     */
    public static String checkViewManifest(Integer serviceId) {
        if (serviceId == 1 || serviceId == 15) {
            return "";
        }
        if (serviceId == 2) {
            return "aaeViewManifest";
        } else if (serviceId == 3) {
            return "tntDomViewManifest";
        } else if (serviceId == 40) {
            return "fedExViewManifest";
        } else if (serviceId == 52) {
            return "tollPriorityViewManifest";
        } else if (serviceId == 54) {
            return "tntIntViewManifest";
        } else if (serviceId == 59) {
            return "tollIpecViewManifest";
        }
        return "";
    }

    /**
     * @param itemPrefix
     * @param connNumber
     * @param count
     * @return ItemCode for view Airbill
     */
    public static String genTNTItemIdentifier(String itemPrefix, String connNumber, String count) {
        String connPrefix = connNumber.substring(0, 3);
        connNumber = connNumber.substring(3);
        if (itemPrefix.trim().equals("")) {
            itemPrefix = SystemSettingCache.getInstance().getValueByKey("TNT Domestic Item Identifier Prefix").trim();
        }
        Integer ch1 = (connPrefix.substring(0, 1).charAt(0)) - 55;
        Integer ch2 = (connPrefix.substring(1, 2).charAt(0)) - 55;
        Integer ch3 = (connPrefix.substring(2, 3).charAt(0)) - 55;
        connPrefix = ch1.toString().concat(ch2.toString()).concat(ch3.toString());
        if (connPrefix.length() >= 6) {
            connPrefix = connPrefix.substring(0, 6); // take 6 digits
        }
        count = AppUtils.leftPad(count, 3, "0".charAt(0)); // count(total 3, eg.
        // 007)
        return itemPrefix.concat(connPrefix).concat(connNumber).concat(count);
    }

    public static Map<String, Double> calculateBaseCharge(Integer rateType, Double baseRate, Double miniumBaseChargePercent, Double rowName, Double cRate, Double ncRate) {
        Double mRate = 0D;
        Double mPercent = 0D;
        Double tmpRate = 0D;
        switch (rateType) {
            case 0: // DHL
            	
            	System.out.println(" baseRate :"+baseRate+" cRate : "+cRate +" ncRate : "+ncRate);
                //ncRate = ncRate - ((ncRate * baseRate) / 100);  //code by rakesh sir
            	
            	ncRate = ncRate + ((ncRate * baseRate) / 100); //code by shahabuddin
            	
                System.out.println(" ncRate :::::: "+ncRate);
               mRate = ncRate - cRate; //code by rakesh sir
                
                //mRate = ncRate + cRate;   //code by shahabuddin
                
                if (ncRate == 0D) {
                    mPercent = 0D;
                } else {
                    mPercent = (mRate / ncRate) * 100;
                }

                if (mPercent < miniumBaseChargePercent) {
                    baseRate = miniumBaseChargePercent;
                    tmpRate = 1 - (baseRate / 100);
                    if (tmpRate != 0)
                        ncRate = cRate / tmpRate;
                    else
                        ncRate = cRate * baseRate;
                    mRate = ncRate - cRate;
                    if (ncRate == 0)
                        mPercent = 0D;
                    else
                        mPercent = (mRate / ncRate) * 100;
                }
                break;
            case 1: // Mark up
                ncRate = (cRate + ((baseRate / 100) * cRate));
                mRate = (ncRate - cRate);
                if (ncRate != 0) {
                    mPercent = (mRate / ncRate) * 100;
                } else {
                    mPercent = 0D;
                }
                if (mPercent < miniumBaseChargePercent) {
                    baseRate = miniumBaseChargePercent;
                    tmpRate = 1 - (baseRate / 100);
                    if (tmpRate != 0) {
                        ncRate = (cRate / tmpRate);
                    } else {
                        ncRate = cRate * baseRate;
                    }
                }
                break;
            case 2: // Margin
                if (baseRate < miniumBaseChargePercent) {
                    baseRate = miniumBaseChargePercent;
                }
                if (baseRate == 100) {
                    break;
                }
                tmpRate = 1 - (baseRate / 100);
                if (tmpRate != 0) {
                    ncRate = cRate / tmpRate;
                } else {
                    ncRate = cRate * baseRate;
                }
                break;
            case 3: // Topup
                ncRate = (cRate / (1 - (baseRate / 100)));
                ncRate = rowName * ncRate;
                cRate = rowName * cRate;
                break;
        }
        Map<String, Double> rate = new HashMap<>();
        rate.put("ncRate", ncRate);
        rate.put("cRate", cRate);
        return rate;
    }

    public static Double calculateCostByBaseRate(Integer rateType, Double baseRate, Double miniumBaseChargePercent, Double cRate, Double ncRate) {
        Double mRate = 0D;
        Double mPercent = 0D;
        Double tmpRate = 0D;
        switch (rateType) {
            case 0: // DHL
                ncRate = ncRate - ((ncRate * baseRate) / 100);

                mRate = ncRate - cRate;
                if (ncRate == 0D) {
                    mPercent = 0D;
                } else {
                    mPercent = (mRate / ncRate) * 100;
                }

                if (mPercent < miniumBaseChargePercent) {
                    baseRate = miniumBaseChargePercent;
                    tmpRate = 1 - (baseRate / 100);
                    if (tmpRate != 0)
                        ncRate = cRate / tmpRate;
                    else
                        ncRate = cRate * baseRate;
                    mRate = ncRate - cRate;
                    if (ncRate == 0)
                        mPercent = 0D;
                    else
                        mPercent = (mRate / ncRate) * 100;
                }
                break;
            case 1: // Mark up
                ncRate = (cRate + ((baseRate / 100) * cRate));
                mRate = (ncRate - cRate);
                if (ncRate != 0) {
                    mPercent = (mRate / ncRate) * 100;
                } else {
                    mPercent = 0D;
                }
                if (mPercent < miniumBaseChargePercent) {
                    baseRate = miniumBaseChargePercent;
                    tmpRate = 1 - (baseRate / 100);
                    if (tmpRate != 0) {
                        ncRate = (cRate / tmpRate);
                    } else {
                        ncRate = cRate * baseRate;
                    }
                }
                break;
            case 2: // Margin
                if (baseRate < miniumBaseChargePercent) {
                    baseRate = miniumBaseChargePercent;
                }
                if (baseRate == 100) {
                    break;
                }
                tmpRate = 1 - (baseRate / 100);
                if (tmpRate != 0) {
                    ncRate = cRate / tmpRate;
                } else {
                    ncRate = cRate * baseRate;
                }
                break;
        }
        return ncRate;
    }

    public static Boolean isCarrierSuburb(String suburbName, String postalCode) throws DaoException {
        CarrierSuburbClient carrierSuburbClient = new CarrierSuburbClient();
        CarrierSuburbRequest carrierSuburbRequest = new CarrierSuburbRequest();
        CarrierSuburbFilter carrierSuburbFilter = new CarrierSuburbFilter();
        carrierSuburbFilter.setSuburbName(suburbName);
        carrierSuburbFilter.setPostCode(postalCode);
        carrierSuburbRequest.setCarrierSuburbFilter(carrierSuburbFilter);
        CarrierSuburbResponse carrierSuburbResponse = carrierSuburbClient.getCarrierSuburbCount(carrierSuburbRequest);
        Integer count = carrierSuburbResponse.getCarrierSuburbCount();
        if (count > 0) {
            return true;
        }

        return false;
    }

    public static Boolean hasNonStandardCharge(List<PieceVo> pieces, String weightUnit, String dimUnit) {
        // Non standard shipping charge
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
        for (PieceVo pieceVo : pieces) {
            weight = ShipmentUtils.weightUnitConvert(pieceVo.getWeight(), weightUnit);
            dimL = pieceVo.getDimensionL();
            dimH = pieceVo.getDimensionH();
            dimW = pieceVo.getDimensionW();
            if (dimL != null && dimH != null && dimW != null) {
                dimL = MathUtils.dimUnitConvert(dimL, dimUnit);
                dimH = MathUtils.dimUnitConvert(dimH, dimUnit);
                dimW = MathUtils.dimUnitConvert(dimW, dimUnit);
                if (dimL > nonStandardDimension || dimH > nonStandardDimension || dimW > nonStandardDimension) {
                    return true;
                }
            }
            if (weight > nonStandardWeight) {
                return true;
            }
        }
        return false;
    }

    public static Double getTntDomNonStandardCharge(List<PieceVo> pieces, String dimUnit) {
        String tntDomOSCharge = SystemSettingCache.getInstance().getValueByKey("TNT Domestic Over Size Charges List");
        String tntDomOSDim = SystemSettingCache.getInstance().getValueByKey("TNT Domestic Over Size Dimensions List");

        String[] tntDomOSChargeStringArray = tntDomOSCharge.split(",");
        String[] tntDomOSDimStringArray = tntDomOSDim.split(",");

        List<Double> tntDomOSChargeList = AppUtils.convertStringArrayToDoubleList(tntDomOSChargeStringArray);
        List<Double> tntDomOSDimList = AppUtils.convertStringArrayToDoubleList(tntDomOSDimStringArray);
        Double maxValue = tntDomOSChargeList.get(tntDomOSChargeList.size() - 1);
        Double maxDim = tntDomOSDimList.get(tntDomOSDimList.size() - 1);
        List<Double> charge = new ArrayList<>();

        Double result = 0D;
        Double dimL = 0D;
        Double dimH = 0D;
        Double dimW = 0D;
        for (PieceVo pieceVo : pieces) {
            dimL = pieceVo.getDimensionL();
            dimH = pieceVo.getDimensionH();
            dimW = pieceVo.getDimensionW();
            if (dimL != null && dimH != null && dimW != null) {
                dimL = MathUtils.dimUnitConvert(dimL, dimUnit);
                dimH = MathUtils.dimUnitConvert(dimH, dimUnit);
                dimW = MathUtils.dimUnitConvert(dimW, dimUnit);

                for (int i = 0; i < tntDomOSDimList.size(); i++) {
                    Double d = tntDomOSDimList.get(i);
                    if (dimL >= d && dimL < maxDim) {
                        charge.add(tntDomOSChargeList.get(i));
                    } else if (dimL >= d && dimL >= maxDim) {
                        charge.add(maxValue);
                    }

                    if (dimH >= d && dimH < maxDim) {
                        charge.add(tntDomOSChargeList.get(i));
                    } else if (dimH >= d && dimH >= maxDim) {
                        charge.add(maxValue);
                    }

                    if (dimW >= d && dimW < maxDim) {
                        charge.add(tntDomOSChargeList.get(i));
                    } else if (dimW >= d && dimW >= maxDim) {
                        charge.add(maxValue);
                    }
                }
            }
        }
        result = Collections.max(charge);
        return result;
    }

    /***
     * Check result view continue booking page
     *
     * @param serviceId
     * @param content
     * @return
     */
    public static String resultViewContinueBooking(String serviceId, String content) {
        switch (serviceId) {
            case "15":
                if (content.equals("DOX")) {
                    return "dhl_dom_doc";
                } else {
                    return "dhl_dom_pac";
                }
            case "1":
                if (content.equals("DOX")) {
                    return "dhl_int_doc";
                } else {
                    return "dhl_int_pac";
                }
            case "54":
                if (content.equals("DOX")) {
                    return "tnt_int_doc";
                } else {
                    return "tnt_int_pac";
                }
            case "3":
                if (content.equals("DOX")) {
                    return "tnt_dom_doc";
                } else {
                    return "tnt_dom_pac";
                }
            case "59":
                if (content.equals("DOX")) {
                    return "toll_ipec_doc";
                } else {
                    return "toll_ipec_pac";
                }
            case "52":
                if (content.equals("DOX")) {
                    return "toll_priority_doc";
                } else {
                    return "toll_priority_pac";
                }
            case "72":
                if (content.equals("DOX")) {
                    return "startrack_doc";
                } else {
                    return "startrack_pac";
                }
            case "400":
                if (content.equals("DOX")) {
                    return "ups_doc";
                } else {
                    return "ups_pac";
                }
            default:
                if (content.equals("DOX")) {
                    return "other_doc";
                } else {
                    return "other_pac";
                }
        }
    }

    public static Long getSaleRepId(String customerCode) throws DaoException {
        Long saleRepId = 0L;
        FranchiseDao franchiseDao = new FranchiseDao();
        CustomerDao customerDao = new CustomerDao();
        if (StringUtils.right(customerCode, 5).equals("00001")) {
            saleRepId = franchiseDao.selectSaleRepByCustomerCode(customerCode);
        } else {
            saleRepId = customerDao.selectSaleRepByCustomerCode(customerCode);
        }
        return saleRepId == null ? 0L : saleRepId;
    }

    public static int getTotalQuantityOfPieces(List<PieceVo> pieces) {
        int noOfPieces = 0;
        for (PieceVo piece : pieces) {
            noOfPieces += piece.getQuantity() != null ? piece.getQuantity() : 1;
        }
        return noOfPieces;
    }

    public static int getNumberOfPiecesWithManualHandlingSurcharge(ShipmentInfoVo shipmentInfo) throws DaoException {
        int noOfPieces = 0;

        final String dimensionUnit = shipmentInfo.getDimensionUnit();
        final String weightUnit = shipmentInfo.getWeightUnit();

        for (PieceVo piece : shipmentInfo.getPieces()) {
            Integer quantity = piece.getQuantity();
            if (quantity == null || quantity == 0) {
                quantity = 1;
            }

            Boolean isNonStandardPackage = piece.getNonStandardPackage() != null ? piece.getNonStandardPackage() : false;
            if (isNonStandardPackage) {
                noOfPieces += quantity;
                continue;
            }

            Double actualWeight = ShipmentUtils.weightUnitConvert(piece.getWeight(), weightUnit);
            if (actualWeight < 0.25 || actualWeight > 30) {
                noOfPieces += quantity;
                continue;
            }

            Double dimL = piece.getDimensionL();
            Double dimH = piece.getDimensionH();
            Double dimW = piece.getDimensionW();

            if (dimW != null && dimH != null && dimL != null) {
                dimL = MathUtils.dimUnitConvert(dimL, dimensionUnit);
                dimH = MathUtils.dimUnitConvert(dimH, dimensionUnit);
                dimW = MathUtils.dimUnitConvert(dimW, dimensionUnit);
                Double diagonal = Math.sqrt(Math.pow(dimL, 2) + Math.pow(dimW, 2));

                if (dimL < 20 || dimL > 120
                        || dimW < 10 || dimW > 60
                        || dimH < 1.5 || dimH > 80
                        || diagonal > 120) {
                    noOfPieces += quantity;
                }
            }
        }

        return noOfPieces;
    }
}