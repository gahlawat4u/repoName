/**
 *
 */
package com.gms.xms.common.config;

import com.gms.xms.common.config.dto.EntryDto;
import com.gms.xms.common.config.dto.PaymentType;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Posted from AppSettings.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:49:10 PM
 */
@XmlRootElement(name = "app-setting")
public class AppSettings {
    private String defaultDateFormat;
    private String defaultDateTimeFormat;
    private String encryptionKey;
    private String senderEmailUser;
    private String senderEmailPassword;
    private String defaultLanguageCode = "en";
    private String defaultNumberFormat;
    private String defaultIntNumberFormat;
    private String appMode;
    private String developmentEmail;
    private String carierCentralized;
    private String franchisePayableStartDate;
    private String defaultPageSize;
    private String defaultLinksOnPage;
    private String passwordChars;
    private String passwordPattern;
    private String pageSizeList;
    private String enableNonCentralizedTab;
    private String customerAgingEndDate;
    private String activeCarriers;
    private String enablePak;
    private String ignoredCtxParamLog;
    private ArrayList<String> ignoredCtxParamLogList;
    private String enableShowLangcode;
    private List<PaymentType> payments;
    private Boolean fanchiseReceivePayment;
    private String appTmpPath;
    private String defaultTimeFormat;
    private String defaultNotifyEmail;
    private String defaultWebshipPassword;
    private String starTrackTrackingAPI_1_0;
    private String selfInsuranceStartDate;
    private Integer defaultProcessRecordSize;
    private Integer maxExportProcessRecord;
    private List<EntryDto> permissionEntryList;
    private List<EntryDto> actionConfigList;
    private String ediPath;
    private String enablePHPHome;
    private String phpHomePath;
    private String xms1Url;

    public String getXms1Url() {
        return xms1Url;
    }

    @XmlElement(name = "xms1-url")
    public void setXms1Url(String xms1Url) {
        this.xms1Url = xms1Url;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    @XmlElement(name = "encryption-key")
    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public String getDefaultWebshipPassword() {
        return defaultWebshipPassword;
    }

    @XmlElement(name = "default-webship-password")
    public void setDefaultWebshipPassword(String defaultWebshipPassword) {
        this.defaultWebshipPassword = defaultWebshipPassword;
    }

    public String getDefaultDateFormat() {
        return defaultDateFormat;
    }

    @XmlElement(name = "default-date-format")
    public void setDefaultDateFormat(String defaultDateFormat) {
        this.defaultDateFormat = defaultDateFormat;
    }

    public String getDefaultDateTimeFormat() {
        return defaultDateTimeFormat;
    }

    @XmlElement(name = "default-date-time-format")
    public void setDefaultDateTimeFormat(String defaultDateTimeFormat) {
        this.defaultDateTimeFormat = defaultDateTimeFormat;
    }

    public String getSenderEmailUser() {
        return senderEmailUser;
    }

    @XmlElement(name = "sender-email-user")
    public void setSenderEmailUser(String senderEmailUser) {
        this.senderEmailUser = senderEmailUser;
    }

    public String getSenderEmailPassword() {
        return senderEmailPassword;
    }

    @XmlElement(name = "sender-email-password")
    public void setSenderEmailPassword(String senderEmailPassword) {
        this.senderEmailPassword = senderEmailPassword;
    }

    public String getDefaultLanguageCode() {
        return defaultLanguageCode;
    }

    @XmlElement(name = "default-language-code")
    public void setDefaultLanguageCode(String defaultLanguageCode) {
        this.defaultLanguageCode = defaultLanguageCode;
    }

    public String getDefaultNumberFormat() {
        if (StringUtils.isBlank(defaultNumberFormat)) {
            defaultNumberFormat = "###,##0.00";
        }
        return defaultNumberFormat;
    }

    @XmlElement(name = "default-number-format")
    public void setDefaultNumberFormat(String defaultNumberFormat) {
        this.defaultNumberFormat = defaultNumberFormat;
    }

    public String getAppMode() {
        if (StringUtils.isBlank(appMode)) {
            appMode = "DEVELOPMENT";
        }
        return appMode;
    }

    @XmlElement(name = "app-mode")
    public void setAppMode(String appMode) {
        this.appMode = appMode;
    }

    public String getDevelopmentEmail() {
        if (StringUtils.isBlank(developmentEmail)) {
            developmentEmail = "nguyenthilekim@gmail.com";
        }
        return developmentEmail;
    }

    @XmlElement(name = "development-email")
    public void setDevelopmentEmail(String developmentEmail) {
        this.developmentEmail = developmentEmail;
    }

    public String getCarierCentralized() {
        if (carierCentralized == null) {
            return "";
        } else {
            return carierCentralized;
        }
    }

    public void setCarierCentralized(String carierCentralized) {
        this.carierCentralized = carierCentralized;
    }

    public String getFranchisePayableStartDate() {
        if (StringUtils.isBlank(franchisePayableStartDate)) {
            franchisePayableStartDate = "28-06-2015";
        }
        return franchisePayableStartDate;
    }

    @XmlElement(name = "franchise-payable-start-date")
    public void setFranchisePayableStartDate(String franchisePayableStartDate) {
        this.franchisePayableStartDate = franchisePayableStartDate;
    }

    public String getDefaultPageSize() {
        return defaultPageSize;
    }

    @XmlElement(name = "default-page-size")
    public void setDefaultPageSize(String defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public String getDefaultLinksOnPage() {
        return defaultLinksOnPage;
    }

    @XmlElement(name = "default-links-on-page")
    public void setDefaultLinksOnPage(String defaultLinksOnPage) {
        this.defaultLinksOnPage = defaultLinksOnPage;
    }

    public String getPasswordPattern() {
        return passwordPattern;
    }

    @XmlElement(name = "password-pattern")
    public void setPasswordPattern(String passwordPattern) {
        this.passwordPattern = passwordPattern;
    }

    public String getPasswordChars() {
        return passwordChars;
    }

    @XmlElement(name = "password-chars")
    public void setPasswordChars(String passwordChars) {
        this.passwordChars = passwordChars;
    }

    public String getPageSizeList() {
        return pageSizeList;
    }

    @XmlElement(name = "page-size-list")
    public void setPageSizeList(String pageSizeList) {
        this.pageSizeList = pageSizeList;
    }

    public String getEnableNonCentralizedTab() {
        return enableNonCentralizedTab;
    }

    @XmlElement(name = "enable-non-centralized-tab")
    public void setEnableNonCentralizedTab(String enableNonCentralizedTab) {
        this.enableNonCentralizedTab = enableNonCentralizedTab;
    }

    public String getCustomerAgingEndDate() {
        return customerAgingEndDate;
    }

    @XmlElement(name = "customer-aging-end-date")
    public void setCustomerAgingEndDate(String customerAgingEndDate) {
        this.customerAgingEndDate = customerAgingEndDate;
    }

    public String getDefaultTimeFormat() {
        return defaultTimeFormat;
    }

    @XmlElement(name = "default-time-format")
    public void setDefaultTimeFormat(String defaultTimeFormat) {
        this.defaultTimeFormat = defaultTimeFormat;
    }

    public String getActiveCarriers() {
        return activeCarriers;
    }

    @XmlElement(name = "active-carriers")
    public void setActiveCarriers(String activeCarriers) {
        this.activeCarriers = activeCarriers;
    }

    public String getDefaultIntNumberFormat() {
        if (StringUtils.isBlank(defaultIntNumberFormat)) {
            defaultIntNumberFormat = "###,##0";
        }
        return defaultIntNumberFormat;
    }

    public void setDefaultIntNumberFormat(String defaultIntNumberFormat) {
        this.defaultIntNumberFormat = defaultIntNumberFormat;
    }

    public String getIgnoredCtxParamLog() {
        return ignoredCtxParamLog;
    }

    @XmlElement(name = "ignored-ctx-param-log")
    public void setIgnoredCtxParamLog(String ignoredCtxParamLog) {
        this.ignoredCtxParamLog = ignoredCtxParamLog;
        if (ignoredCtxParamLog != null) {
            ignoredCtxParamLogList = new ArrayList<>();
            for (String param : ignoredCtxParamLog.split(",")) {
                ignoredCtxParamLogList.add(param.trim());
            }
        }
    }

    public List<String> getIgnoredCtxParamLogList() {
        if (ignoredCtxParamLogList == null) {
            ignoredCtxParamLogList = new ArrayList<>();
        }
        return ignoredCtxParamLogList;
    }

    public String getEnableShowLangcode() {
        if (StringUtils.isBlank(this.enableShowLangcode)) {
            this.setEnableShowLangcode("false");
        }
        return enableShowLangcode;
    }

    @XmlElement(name = "enable-show-langcode")
    public void setEnableShowLangcode(String enableShowLangcode) {
        this.enableShowLangcode = enableShowLangcode;
    }

    public List<PaymentType> getPayments() {
        return payments;
    }

    @XmlElementWrapper(name = "payment-methods")
    @XmlElement(name = "payment")
    public void setPayments(List<PaymentType> payments) {
        this.payments = payments;
    }

    public Boolean getFanchiseReceivePayment() {
        if (fanchiseReceivePayment == null) {
            fanchiseReceivePayment = false;
        }
        return fanchiseReceivePayment;
    }

    @XmlElement(name = "franchise-receive-payment")
    public void setFanchiseReceivePayment(Boolean fanchiseReceivePayment) {
        this.fanchiseReceivePayment = fanchiseReceivePayment;
    }

    public String getAppTmpPath() {
        if (appTmpPath == null)
            appTmpPath = "";
        return appTmpPath;
    }

    @XmlElement(name = "app-tmp-path")
    public void setAppTmpPath(String appTmpPath) {
        this.appTmpPath = appTmpPath;
    }

    public String getDefaultNotifyEmail() {
        if (defaultNotifyEmail == null) {
            defaultNotifyEmail = "gwtrms1@gmail.com";
        }
        return defaultNotifyEmail;
    }

    @XmlElement(name = "default-notify-email")
    public void setDefaultNotifyEmail(String defaultNotifyEmail) {
        this.defaultNotifyEmail = defaultNotifyEmail;
    }

    public String getStarTrackTrackingAPI_1_0() {
        if (StringUtils.isBlank(starTrackTrackingAPI_1_0)) {
            return "http://localhost/xms_au_dev/startrack/startrack_tracking_api.php";
        }
        return starTrackTrackingAPI_1_0;
    }

    @XmlElement(name = "star-track-tracking-api-1.0")
    public void setStarTrackTrackingAPI_1_0(String starTrackTrackingAPI_1_0) {
        this.starTrackTrackingAPI_1_0 = starTrackTrackingAPI_1_0;
    }

    public String getSelfInsuranceStartDate() {
        if (StringUtils.isBlank(this.selfInsuranceStartDate)) {
            this.selfInsuranceStartDate = "07-05-2016";
        }
        return selfInsuranceStartDate;
    }

    @XmlElement(name = "self-insurance-start-date")
    public void setSelfInsuranceStartDate(String selfInsuranceStartDate) {
        this.selfInsuranceStartDate = selfInsuranceStartDate;
    }

    public Integer getDefaultProcessRecordSize() {
        if (this.defaultProcessRecordSize == null) {
            this.defaultProcessRecordSize = 1000;
        }
        return defaultProcessRecordSize;
    }

    @XmlElement(name = "default-process-record-size")
    public void setDefaultProcessRecordSize(Integer defaultProcessRecordSize) {
        this.defaultProcessRecordSize = defaultProcessRecordSize;
    }

    public Integer getMaxExportProcessRecord() {
        if (this.maxExportProcessRecord == null) {
            this.maxExportProcessRecord = 20000;
        }
        return maxExportProcessRecord;
    }

    @XmlElement(name = "max-export-process-record")
    public void setMaxExportProcessRecord(Integer maxExportProcessRecord) {
        this.maxExportProcessRecord = maxExportProcessRecord;
    }

    @XmlElementWrapper(name = "permission-map")
    @XmlElement(name = "entry")
    public void setPermissionEntryList(List<EntryDto> permissionEntryList) {
        this.permissionEntryList = permissionEntryList;
    }

    public List<EntryDto> getPermissionEntryList() {
        return permissionEntryList;
    }

    public Map<String, List<String>> getPermissionMap() {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        if (permissionEntryList != null) {
            for (EntryDto entryDto : permissionEntryList) {
                if (entryDto != null && StringUtils.isNotBlank(entryDto.getKey())) {
                    result.put(entryDto.getKey(), entryDto.getValues());
                }
            }
        }
        if (result != null && result.size() <= 0) {
            result = null;
        }
        return result;
    }

    @XmlElementWrapper(name = "action-config-map")
    @XmlElement(name = "entry")
    public void setActionConfigList(List<EntryDto> actionConfigList) {
        this.actionConfigList = actionConfigList;
    }

    public List<EntryDto> getActionConfigList() {
        return actionConfigList;
    }

    public Map<String, List<String>> getActionConfigMap() {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        if (actionConfigList != null) {
            for (EntryDto entryDto : actionConfigList) {
                if (entryDto != null && StringUtils.isNotBlank(entryDto.getKey())) {
                    result.put(entryDto.getKey(), entryDto.getValues());
                }
            }
        }
        if (result != null && result.size() <= 0) {
            result = null;
        }
        return result;
    }

    public Map<String, List<String>> getActionMap() {
        Map<String, List<String>> actionMap = new HashMap<String, List<String>>();
        Map<String, List<String>> actionConfig = getActionConfigMap();
        if (actionConfig != null) {
            for (Entry<String, List<String>> entry : actionConfig.entrySet()) {
                for (String action : entry.getValue()) {
                    if (!actionMap.containsKey(action))
                        actionMap.put(action, new ArrayList<String>());
                    actionMap.get(action).add(entry.getKey());
                }
            }
        }
        return actionMap;
    }

    public String getEdiPath() {
        if (StringUtils.isBlank(ediPath)) {
            return "/edi_file";
        }
        return ediPath;
    }

    @XmlElement(name = "editPath")
    public void setEdiPath(String ediPath) {
        this.ediPath = ediPath;
    }

    public String getEnablePHPHome() {
        if (StringUtils.isBlank(this.enablePHPHome)) {
            this.enablePHPHome = "N";
        }
        return enablePHPHome;
    }

    @XmlElement(name = "enable-php-home")
    public void setEnablePHPHome(String enablePHPHome) {
        this.enablePHPHome = enablePHPHome;
    }

    public String getPhpHomePath() {
        if (StringUtils.isBlank(this.phpHomePath)) {
            this.phpHomePath = "/xms/admin";
        }
        return phpHomePath;
    }

    @XmlElement(name = "php-home-path")
    public void setPhpHomePath(String phpHomePath) {
        this.phpHomePath = phpHomePath;
    }

    @Override
    public String toString() {
        return "AppSettings [defaultDateFormat=" + defaultDateFormat + ", defaultDateTimeFormat=" + defaultDateTimeFormat + ", encryptionKey=" + encryptionKey + ", senderEmailUser=" + senderEmailUser + ", senderEmailPassword=" + senderEmailPassword + ", defaultLanguageCode=" + defaultLanguageCode + ", defaultNumberFormat=" + defaultNumberFormat + ", defaultIntNumberFormat=" + defaultIntNumberFormat + ", appMode=" + appMode + ", developmentEmail=" + developmentEmail + ", carierCentralized="
                + carierCentralized + ", franchisePayableStartDate=" + franchisePayableStartDate + ", defaultPageSize=" + defaultPageSize + ", defaultLinksOnPage=" + defaultLinksOnPage + ", passwordChars=" + passwordChars + ", passwordPattern=" + passwordPattern + ", pageSizeList=" + pageSizeList + ", enableNonCentralizedTab=" + enableNonCentralizedTab + ", customerAgingEndDate=" + customerAgingEndDate + ", activeCarriers=" + activeCarriers + ", enablePak=" + enablePak + ", ignoredCtxParamLog="
                + ignoredCtxParamLog + ", ignoredCtxParamLogList=" + ignoredCtxParamLogList + ", enableShowLangcode=" + enableShowLangcode + ", payments=" + payments + ", fanchiseReceivePayment=" + fanchiseReceivePayment + ", appTmpPath=" + appTmpPath + ", defaultTimeFormat=" + defaultTimeFormat + ", defaultNotifyEmail=" + defaultNotifyEmail + ", defaultWebshipPassword=" + defaultWebshipPassword + ", starTrackTrackingAPI_1_0=" + starTrackTrackingAPI_1_0 + ", selfInsuranceStartDate="
                + selfInsuranceStartDate + ", defaultProcessRecordSize=" + defaultProcessRecordSize + ", maxExportProcessRecord=" + maxExportProcessRecord + ", permissionEntryList=" + permissionEntryList + ", actionConfigList=" + actionConfigList + ", ediPath=" + ediPath + ", enablePHPHome=" + enablePHPHome + "]";
    }
}
