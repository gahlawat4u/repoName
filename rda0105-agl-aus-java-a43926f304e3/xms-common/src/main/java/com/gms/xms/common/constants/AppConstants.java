package com.gms.xms.common.constants;

import com.gms.xms.common.config.AppLogSettings;
import com.gms.xms.common.config.AppSettings;
import com.gms.xms.common.config.dto.LogConfig;
import com.gms.xms.common.utils.AppUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Posted from AppConstants.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:48:47 PM
 */
public class AppConstants {

    protected static final Log log = LogFactory.getLog(AppConstants.class);

    public static final String EXTERNAL_APP_SETTINGS_XML_FILE_PATH_LINUX = "/opt/config/au_app_settings.xml";
    public static final String EXTERNAL_APP_SETTINGS_XML_FILE_PATH_WIN = "C:/gms/config/au_app_settings.xml";

    public static final String YES_FLAG = "Y";
    public static final String NO_FLAG = "N";
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String REFUND_FLAG = "R";
    public static final String NOT_HANDLE_FLAG = "NH";
    public static final String FRANCHISE_PAYABLE_FROZEN_MESSAGE = "This report is frozen";
    public static final String FRANCHISE_PAYABLE_SEND_INVOICE = "FRANCHISE_PAYABLE_SEND_INVOICE";
    public static final String DOWNLOAD_DHL_CHECKING_START_DAY = "Download DHL Tracking Start Day";
    public static final String DEFAULT_LANG = "EN";
    public static final String DEFAULT_ORIGIN_COUNTRY = "Default Origin Country";
    public static final String SYSTEM_ERROR_MESSAGE = "System error. Please contact the administrator for supporting.";
    // Email setting names
    public static final String FROM_EMAIL = "From Email";
    public static final String FROM_NAME = "From Name";
    public static final String EMAIL_USER_NAME = "Email User Name";
    public static final String EMAIL_PASSWORD = "Email Password";
    public static final String POP_PORT_NUMBER = "POP Port Number";
    public static final String SMTP_SERVER_NAME = "SMTP Server Name";
    public static final String SMTP_PORT_NUMBER = "SMTP Port Number";
    public static final String SEND_FRANCHISE_PAYABLE_EMAIL = "Send Franchise Payable Email";
    public static final String RECEIVE_PAYMENT_SEARCH_BY_CUSTOMER_OR_INVOICE_CODE_OPTION = "RECEIVE_PAYMENT_SEARCH_BY_CUSTOMER_OR_INVOICE_CODE_OPTION";
    public static final String RECEIVE_PAYMENT_SEARCH_BY_CUSTOMER_NAME_OPTION = "RECEIVE_PAYMENT_SEARCH_BY_CUSTOMER_NAME_OPTION";
    public static final String JSON = "json";
    public static final String VAT_PERCENT_BASED_ON_BASE_CHARGE = "VAT percent based on Base Charge";
    public static final String CURRENCY_SYMBOL = "CurrencySymbol";
    public static final String DEFAULT_MINIMUM_PICKUP_HOUR = "Default Minimum Pickup Hour";
    public static final String PRODUCTION_MODE = "PRODUCTION";
    public static final String ADJUSTMENT_SUBSTATUS_REQUEST_FRANCHISE_COMMENT = "Request Franchise Comment";
    public static final String ADJUSTMENT_SUBSTATUS_UPDATE_TO_FSC = "Update to FSC";
    public static final String SEND_CREDIT_NOTES = "Send Credit Notes";
    public static final String INDIVIDUAL_SEND_EMAIL_INVOICE = "Individual Send Email Invoice";
    public static final String SEND_EMAIL_INVOICE = "Send Email Invoice";
    public static final String SENT_EMAIL_INVOICES_NOTIFY = "Sent E-mail Invoices Notify";
    public static final String CUSTOM_ERROR_MESSAGE = "Custom Error Message";
    public static final String TNT_DOMESTIC_VOID = "TNT Domestic Void";
    public static AppSettings APP_SETTINGS;
    public static Map<String, LogConfig> APP_LOG_SETTINGS = new HashMap<String, LogConfig>();
    public static String GLOBAL_DATE_FORMAT = "yyyyMMddHHmmss";
    public static Map<String, String> ADJUSTMENT_STATUS_MAP;

    public static enum ENCODE_TYPE {
        HTML, XML, JAVASCRIPT, CSV
    }

    ;

    public static enum PADDING_TYPE {
        LEFT, RIGHT
    }

    ;

    // public static Version FREEMARKER_VERSION = Configuration.VERSION_2_3_21;
    static {
        try {
            load();
        } catch (Exception e) {
            log.error("Fail to load config. ", e);
        }
    }

    public static void load() throws Exception {
        loadAppSettings();
        loadAppLogSettings();
        loadAdjustmentStatusMap();
    }

    private static void loadAdjustmentStatusMap() {
        ADJUSTMENT_STATUS_MAP = new HashMap<String, String>();
        ADJUSTMENT_STATUS_MAP.put("1", "Submitted");
        ADJUSTMENT_STATUS_MAP.put("2", "Pending");
        ADJUSTMENT_STATUS_MAP.put("3", "Disputed");
        ADJUSTMENT_STATUS_MAP.put("4", "Approved");
        ADJUSTMENT_STATUS_MAP.put("5", "Disputed Denied");
        ADJUSTMENT_STATUS_MAP.put("6", "Closed"); // Deleted
        ADJUSTMENT_STATUS_MAP.put("7", "Pending Delete");
    }

    private static void loadAppLogSettings() throws javax.xml.bind.JAXBException {
        AppLogSettings appLogSettings = AppUtils.loadConfig("/log_settings.xml", AppLogSettings.class);
        if (appLogSettings != null & appLogSettings.getLogConfigs() != null) {
            APP_LOG_SETTINGS.clear();
            for (LogConfig config : appLogSettings.getLogConfigs()) {
                APP_LOG_SETTINGS.put(config.getSqlId(), config);
            }
        }

        System.out.println(APP_LOG_SETTINGS);
    }

    private static void loadAppSettings() throws javax.xml.bind.JAXBException {
        AppSettings appSettings = AppUtils.loadConfig("/app_settings.xml", AppSettings.class);

        String externalConfigPath = SystemUtils.IS_OS_WINDOWS ? EXTERNAL_APP_SETTINGS_XML_FILE_PATH_WIN : EXTERNAL_APP_SETTINGS_XML_FILE_PATH_LINUX;
        AppSettings externalAppSettings = AppUtils.loadConfigFromExternalFileSystem(externalConfigPath, AppSettings.class);

        if (appSettings != null && externalAppSettings != null) {
            appSettings.setAppMode(externalAppSettings.getAppMode());
            appSettings.setDevelopmentEmail(externalAppSettings.getDevelopmentEmail());
            appSettings.setEnablePHPHome(externalAppSettings.getEnablePHPHome());
            appSettings.setPhpHomePath(externalAppSettings.getPhpHomePath());
        }

        if (appSettings != null) {
            APP_SETTINGS = appSettings;
        } else if (externalAppSettings != null) {
            APP_SETTINGS = externalAppSettings;
        }

        System.out.println(APP_SETTINGS);
        System.out.println(AppConstants.APP_SETTINGS.getPermissionMap());
        System.out.println(AppConstants.APP_SETTINGS.getActionConfigMap());
    }

    public static boolean reload() {
        return true;
    }

    public static void main(String[] args) {
        System.out.println(AppConstants.APP_SETTINGS.getActiveCarriers());
    }
}