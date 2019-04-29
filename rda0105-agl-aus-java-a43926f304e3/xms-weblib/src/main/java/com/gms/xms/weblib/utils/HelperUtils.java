package com.gms.xms.weblib.utils;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Posted from HelperUtils.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:58:00 PM
 */
public class HelperUtils {

    /**
     * Uses GetUserById work flow to get User information by user id
     *
     * @param userId - string userid
     * @return - user information object
     * @throws Exception
     */
    public static UserVo getUserById(String userId) throws Exception {
        // Do GetFranchiseInfoList work flow to get franchise list
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase context = new ContextBase(addInfoMap);
        context.put(Attributes.WFP_NAME, "Wfl-GetUserById");

        // Must get USER_ID from SESSION (login)
        context.put(Attributes.USER_ID, userId);

        context = WorkFlowManager.getInstance().process(context);

        // Get result from the context and convert it to UserVo
        String result = context.get(Attributes.USER_INFO_RESULT);

        UserVo user = GsonUtils.fromGson(result, UserVo.class);
        return user;
    }

    // Uses GetFranchiseInfoList work flow to get all franchises that managed by
    // logged user

    /**
     * Uses GetFranchiseInfoList work flow to get all franchises that managed by logged user
     *
     * @return - list of franchise information
     * @throws Exception - on error
     */

    public static List<FranchiseInfoVo> getFranchiseListManagedByLoggedUser() throws Exception {
        // Do GetFranchiseInfoList work flow to get franchise list.
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase context = new ContextBase(addInfoMap);
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchiseListManagedByLoggedUser");
        context = WorkFlowManager.getInstance().process(context);
        String result = context.get(Attributes.FRANCHISE_LIST_RESULT);
        Type type = new TypeToken<List<FranchiseInfoVo>>() {
        }.getType();
        List<FranchiseInfoVo> franchiseList = GsonUtils.fromGson(result, type);

        return franchiseList;
    }

    public static float round_to_near_half(float number) {
        float result;
        if (number == 0) {
            result = 0.5F;
        }
        int roundNumber = Math.round(number);
        if (roundNumber <= number) {
            result = roundNumber + 0.5F;
        } else {
            result = roundNumber;
        }
        return result;
    }

    public static float get_quote_weight_by_piece(float weight, float w, float h, float l, String weightUnti, int forceVolWeight, String round_type) {
        String dimUnti = "";
        if (weightUnti.equals("kg")) {
            dimUnti = "cm";
        } else {
            dimUnti = "in";
        }
        weight = weight_unit_convert(weightUnti, weight);
        float gross_weight = get_gross_weight(w, h, l, dimUnti, forceVolWeight);
        if (round_type.equals("ceil")) {
            weight = (float) Math.ceil(weight);
            gross_weight = (float) Math.ceil(gross_weight);
        }

        if (weight < gross_weight) {
            return gross_weight;
        } else {
            return weight;
        }
    }

    // Overload getDimesionWeight
    public static float get_quote_weight_by_piece(float weight, float w, float h, float l, String weightUnti, int forceVolWeight) {
        String round_type = "";
        String dimUnti = "";
        if (weightUnti.equals("kg")) {
            dimUnti = "cm";
        } else {
            dimUnti = "in";
        }
        weight = weight_unit_convert(weightUnti, weight);
        float gross_weight = get_gross_weight(w, h, l, dimUnti, forceVolWeight);
        if (round_type.equals("ceil")) {
            weight = (float) Math.ceil(weight);
            gross_weight = (float) Math.ceil(gross_weight);
        }

        if (weight < gross_weight) {
            return gross_weight;
        } else {
            return weight;
        }
    }

    public static float get_gross_weight(float w, float h, float l, String dimUnit, int forceVolWeight) {
        float cm = 1F;
        SystemSettingDao systemSettingDao = new SystemSettingDao();
        float vol_weight = 5000F;
        try {
            vol_weight = Float.parseFloat(systemSettingDao.getSystemSettingByName("Volume Weight Divided By").getSettingValue().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (forceVolWeight > 0) {
            vol_weight = forceVolWeight;
        }
        float grossweight = (w * l * h) / vol_weight;
        if (dimUnit.equalsIgnoreCase("cm")) {
            cm = 1F;
        } else {
            cm = 2.54F; // 1in-> 2.54cm
            grossweight = grossweight * (cm * cm * cm);
        }
        BigDecimal beautiFloat = null;
        beautiFloat = new BigDecimal(grossweight).setScale(2, BigDecimal.ROUND_HALF_UP);
        return beautiFloat.floatValue();
    }

    public static float weight_unit_convert(String weightUnti, float weight) {
        float result = weight;
        if (weightUnti.equals("lb")) {
            // 1 kilogram = 2.20462262 pounds
            float conv = 2.20462262F;
            result = result / conv;
            BigDecimal beautiFloat = null;
            beautiFloat = new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP);
            return beautiFloat.floatValue();
        }
        return result;
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
        } else if (serviceId == 51) { // UPS
            forceVolWeight = Integer.parseInt(settingDao.getSystemSettingByName("Volume Weight Divided By (UPS)").getSettingValue().trim()); // 5000;
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

    public static Boolean isEmailAddress(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String formatCurrency(String number) {
        number = SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL) + " " + number;
        return number;
    }

    /**
     * @param valueSelected is value get from column pickup time EX: 06:30:00
     * @return list Option time
     * @throws Exception
     */
    public static Map<String, String> getPickupTimeMap() throws Exception {
        Map<String, String> mapPickupTime = new HashMap<String, String>();
        String amOrPm = " AM";
        Integer i = 12;
        for (i = 6; i < 24; i++) {
            String optVal = "";
            if (i >= 12) {
                amOrPm = " PM";
            }
            if (i % 12 == 0) {
                optVal = "12:00";
            } else {
                optVal = "" + (i % 12) + ":00";
            }
            String defaultTimeFormat = "";
            if (SystemSettingCache.getInstance().getValueByKey("Default Time Format") != null) {
                defaultTimeFormat = SystemSettingCache.getInstance().getValueByKey("Default Time Format");
            }

            if (defaultTimeFormat.trim() == "24:00") {
                optVal = i + ":00";
            }
            String si = String.valueOf(i);
            if (i < 10) {
                si = "0" + i;
            }
            mapPickupTime.put(si + ":00:00", optVal + amOrPm);
            si = String.valueOf(i);
            if (i % 12 == 0) {
                optVal = "12:30";
            } else {
                optVal = (i % 12) + ":30";
            }

            if (defaultTimeFormat.trim() == "24:00") {
                optVal = si + ":30";
            }
            if (i < 10) {
                si = "0" + i;
            }
            mapPickupTime.put(si + ":30:00", optVal + amOrPm);
        }
        Map<String, String> pickupTime = new TreeMap<String, String>(mapPickupTime);

        return pickupTime;
    }

    public static String getPickupTime(String valueSelected) throws Exception {
        valueSelected = valueSelected.replace("-", ":");
        if (valueSelected.length() > 19) {
            valueSelected = valueSelected.substring(11, 19);
        }
        String selectPickipTime = "";
        if (valueSelected.equals("")) {
            selectPickipTime = "<option value='0'>Select One</option>";
        }
        String amOrPm = " AM";
        Integer i = 12;
        for (i = 6; i < 24; i++) {
            String optVal = "";
            if (i >= 12) {
                amOrPm = " PM";
            }
            if (i % 12 == 0) {
                optVal = "12:00";
            } else {
                optVal = "" + (i % 12) + ":00";
            }
            String defaultTimeFormat = "";
            if (SystemSettingCache.getInstance().getValueByKey("Default Time Format") != null) {
                defaultTimeFormat = SystemSettingCache.getInstance().getValueByKey("Default Time Format");
            }

            if (defaultTimeFormat.trim() == "24:00") {
                optVal = i + ":00";
            }
            String si = String.valueOf(i);
            if (i < 10) {
                si = "0" + i;
            }
            String selected = "";
            if (valueSelected.equals(si + ":00:00")) {
                selected = " selected ='selected'";
            }
            selectPickipTime = selectPickipTime + "<option " + selected + " value='" + si + ":00:00'>" + optVal + amOrPm + "</option>";
            si = String.valueOf(i);
            if (i % 12 == 0) {
                optVal = "12:30";
            } else {
                optVal = (i % 12) + ":30";
            }

            if (defaultTimeFormat.trim() == "24:00") {
                optVal = si + ":30";
            }
            if (i < 10) {
                si = "0" + i;
            }
            if (valueSelected.equals(si + ":30:00")) {
                selected = " selected ='selected'";
            }
            selectPickipTime = selectPickipTime + "<option " + selected + " value='" + si + ":30:00'>" + optVal + amOrPm + "</option>";
        }

        return selectPickipTime;
    }

    /**
     * @param date   (14-08-1990)
     * @param format (dd-MM-yyy)
     * @return
     */
    public static boolean isValidDate(String date, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date testDate = null;
        try {
            testDate = sdf.parse(date);
        } catch (Exception e) {
            return false;
        }

        if (!sdf.format(testDate).equals(date)) {

            return false;
        }
        return true;

    }

    public static Set<String> getDuplicateField(List<String> fieldList) {
        final Set<String> setToReturn = new HashSet<String>();
        final Set<String> setCheck = new HashSet<String>();

        for (String field : fieldList) {
            if (!setCheck.add(field)) {
                setToReturn.add(field);
            }
        }
        return setToReturn;
    }

    public static Map<String, String> switchMapKeyAndValue(Map<String, String> map) {
        Map<String, String> newMap = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            newMap.put(entry.getValue(), entry.getKey());
        }
        return newMap;
    }
}
