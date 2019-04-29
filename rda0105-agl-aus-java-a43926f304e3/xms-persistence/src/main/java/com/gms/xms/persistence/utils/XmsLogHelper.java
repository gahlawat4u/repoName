package com.gms.xms.persistence.utils;

import com.gms.xms.common.config.dto.LogConfig;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.persistence.config.MyBatisSettings;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.admin.EventLogVo;
import com.gms.xms.txndb.vo.admin.WebshipLogVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmsLogHelper {
    public static final Log log = LogFactory.getLog(XmsLogHelper.class);
    private Map<String, String> context;
    private Object obj;
    private String sqlId;
    private SqlSessionClient sqlSessionClient;
    private final static String SURFIX = "_SelectForLog";
    private Boolean isInAdmin;

    public XmsLogHelper(SqlSessionClient sqlSessionClient, Map<String, String> context, Object obj, String sqlId) {
        super();
        this.context = context;
        this.obj = obj;
        this.sqlId = sqlId;
        this.sqlSessionClient = sqlSessionClient;
        this.isInAdmin = AppConstants.YES_FLAG.equalsIgnoreCase(context.get(Attributes.ADD_INFO_EXT_IS_ADMIN)) ? true : false;
    }

    public void logInsert() {
        try {
            LogConfig logConfig = AppConstants.APP_LOG_SETTINGS.get(sqlId);
            if (logConfig != null) {
                String actionField = logConfig.getActionfields();
                Pattern pattern = Pattern.compile("\\#\\{(.*?)\\}\\#");
                Matcher matcher = pattern.matcher(actionField);
                String propertyName;
                while (matcher.find()) {
                    propertyName = matcher.group(1);
                    actionField = StringUtils.replace(actionField, "#{" + propertyName + "}#", getPropertyValue(obj, propertyName));
                }
                pattern = Pattern.compile("\\#\\((.*?)\\)\\#");
                matcher = pattern.matcher(actionField);
                while (matcher.find()) {
                    propertyName = matcher.group(1);
                    actionField = StringUtils.replace(actionField, "#(" + propertyName + ")#", "");
                }
                log.info("actionField: " + actionField);
                pattern = Pattern.compile("\\#\\{(.*?)\\}\\#");
                String actionFilter = logConfig.getActionFilter();
                matcher = pattern.matcher(logConfig.getActionFilter());
                while (matcher.find()) {
                    propertyName = matcher.group(1);
                    actionFilter = StringUtils.replace(actionFilter, "#{" + propertyName + "}#", getPropertyValue(obj, propertyName));
                }
                log.info("actionFilter: " + actionFilter);
                if (isInAdmin) {
                    log2EventLog("insert", logConfig.getActionTable(), actionField, actionFilter);
                } else {
                    log2WebshipLog("insert", logConfig.getActionTable(), actionField, actionFilter);
                }
            } else {
                log.error("not found config for logging:" + sqlId);
            }

        } catch (Exception e) {
            log.error(e);
        }
    }

    public void logUpdate() {
        try {
            String select4LogId = sqlId + SURFIX;
            Collection<String> collection = MyBatisSettings.getSqlSessionFactory().getConfiguration().getMappedStatementNames();
            if (collection.contains(select4LogId)) {
                LogConfig logConfig = AppConstants.APP_LOG_SETTINGS.get(sqlId);
                if (logConfig != null) {
                    List<Object> list = sqlSessionClient.selectList(obj, sqlId + SURFIX);
                    for (Object oldObject : list) {
                        String actionField = logConfig.getActionfields();
                        Pattern pattern = Pattern.compile("\\#\\{(.*?)\\}\\#");
                        Matcher matcher = pattern.matcher(actionField);
                        String propertyName;
                        while (matcher.find()) {
                            propertyName = matcher.group(1);
                            actionField = StringUtils.replace(actionField, "#{" + propertyName + "}#", getPropertyValue(obj, propertyName));
                        }
                        pattern = Pattern.compile("\\#\\((.*?)\\)\\#");
                        matcher = pattern.matcher(actionField);
                        while (matcher.find()) {
                            propertyName = matcher.group(1);
                            actionField = StringUtils.replace(actionField, "#(" + propertyName + ")#", getPropertyValue(oldObject, propertyName));
                        }
                        log.info("actionField: " + actionField);
                        pattern = Pattern.compile("\\#\\{(.*?)\\}\\#");
                        String actionFilter = logConfig.getActionFilter();
                        matcher = pattern.matcher(logConfig.getActionFilter());
                        while (matcher.find()) {
                            propertyName = matcher.group(1);
                            actionFilter = StringUtils.replace(actionFilter, "#{" + propertyName + "}#", getPropertyValue(obj, propertyName));
                        }
                        log.info("actionFilter: " + actionFilter);
                        if (isInAdmin) {
                            log2EventLog("update", logConfig.getActionTable(), actionField, actionFilter);
                        } else {
                            log2WebshipLog("update", logConfig.getActionTable(), actionField, actionFilter);
                        }
                    }
                } else {
                    log.error("not found config for logging:" + sqlId);
                }
            } else {
                log.error("not found mapping for " + sqlId + SURFIX);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void logDelete() {
        try {
            String select4LogId = sqlId + SURFIX;
            Collection<String> collection = MyBatisSettings.getSqlSessionFactory().getConfiguration().getMappedStatementNames();
            if (collection.contains(select4LogId)) {
                LogConfig logConfig = AppConstants.APP_LOG_SETTINGS.get(sqlId);
                if (logConfig != null) {
                    List<Object> list = sqlSessionClient.selectList(obj, sqlId + SURFIX);
                    for (Object oldObject : list) {
                        String actionField = logConfig.getActionfields();
                        Pattern pattern = Pattern.compile("\\#\\{(.*?)\\}\\#");
                        Matcher matcher = pattern.matcher(actionField);
                        String propertyName;
                        while (matcher.find()) {
                            propertyName = matcher.group(1);
                            actionField = StringUtils.replace(actionField, "#{" + propertyName + "}#", getPropertyValue(obj, propertyName));
                        }
                        pattern = Pattern.compile("\\#\\((.*?)\\)\\#");
                        matcher = pattern.matcher(actionField);
                        while (matcher.find()) {
                            propertyName = matcher.group(1);
                            actionField = StringUtils.replace(actionField, "#(" + propertyName + ")#", getPropertyValue(oldObject, propertyName));
                        }
                        log.info("actionField: " + actionField);
                        pattern = Pattern.compile("\\#\\{(.*?)\\}\\#");
                        String actionFilter = logConfig.getActionFilter();
                        matcher = pattern.matcher(logConfig.getActionFilter());
                        while (matcher.find()) {
                            propertyName = matcher.group(1);
                            actionFilter = StringUtils.replace(actionFilter, "#{" + propertyName + "}#", getPropertyValue(obj, propertyName));
                        }
                        log.info("actionFilter: " + actionFilter);
                        if (isInAdmin) {
                            log2EventLog("delete", logConfig.getActionTable(), actionField, actionFilter);
                        } else {
                            log2WebshipLog("delete", logConfig.getActionTable(), actionField, actionFilter);
                        }
                    }
                } else {
                    log.error("not found config for logging:" + sqlId);
                }
            } else {
                log.error("not found mapping for " + sqlId + SURFIX);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    protected void log2EventLog(final String actionType, final String actionTable, final String actionField, final String actionFilter) throws Exception {
        EventLogVo eventLogVo = new EventLogVo();
        eventLogVo.setActionDate(new Date());
        eventLogVo.setActionTable(actionTable);
        eventLogVo.setActionType(actionType);
        eventLogVo.setChangesMode(actionField);
        eventLogVo.setFilter(actionFilter);
        eventLogVo.setIpAddress(context.get(Attributes.ADD_INFO_EXT_IP) == null ? "" : context.get(Attributes.ADD_INFO_EXT_IP));
        eventLogVo.setUserCode(Long.valueOf(context.get(Attributes.ADD_INFO_EXT_USER_CODE) == null ? "0" : context.get(Attributes.ADD_INFO_EXT_USER_CODE)));
        eventLogVo.setUserId(Long.valueOf(context.get(Attributes.ADD_INFO_EXT_USER_ID) == null ? "0" : context.get(Attributes.ADD_INFO_EXT_USER_ID)));
        eventLogVo.setUserName(context.get(Attributes.ADD_INFO_EXT_USERNAME) == null ? "" : context.get(Attributes.ADD_INFO_EXT_USERNAME));
        sqlSessionClient.insert(eventLogVo, "EventLog.insert");
    }

    protected void log2WebshipLog(final String actionType, final String actionTable, final String actionField, final String actionFilter) throws Exception {
        WebshipLogVo webshipLogVo = new WebshipLogVo();
        webshipLogVo.setActionDate(new Date());
        webshipLogVo.setActionTable(actionTable);
        webshipLogVo.setActionType(this.determineActionType(actionType));
        webshipLogVo.setChangesMode(actionField);
        webshipLogVo.setFilter(actionFilter);
        webshipLogVo.setIpAddress(context.get(Attributes.ADD_INFO_EXT_IP) == null ? "" : context.get(Attributes.ADD_INFO_EXT_IP));
        webshipLogVo.setUserCode(Long.valueOf(context.get(Attributes.ADD_INFO_EXT_USER_CODE) == null ? "0" : context.get(Attributes.ADD_INFO_EXT_USER_CODE)));
        webshipLogVo.setUserId(Long.valueOf(context.get(Attributes.ADD_INFO_EXT_USER_ID) == null ? "0" : context.get(Attributes.ADD_INFO_EXT_USER_ID)));
        webshipLogVo.setUserName(context.get(Attributes.ADD_INFO_EXT_USERNAME) == null ? "" : context.get(Attributes.ADD_INFO_EXT_USERNAME));
        webshipLogVo.setUserType(Integer.valueOf(context.get(Attributes.ADD_INFO_EXT_USER_TYPE) == null ? "0" : context.get(Attributes.ADD_INFO_EXT_USER_TYPE)));
        sqlSessionClient.insert(webshipLogVo, "WebshipLog.insert");
    }

    protected String determineActionType(final String actionType) {
        String action = actionType;
        if (context.get(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE) != null) {
            action = context.get(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE);
            context.remove(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE);
        }
        return action;
    }

    private String getPropertyValue(Object obj, String propertyName) {
        String propertyValue;
        try {
            propertyValue = BeanUtils.getProperty(obj, propertyName) == null ? "" : BeanUtils.getProperty(obj, propertyName);
        } catch (Exception e) {
            propertyValue = "";
        }
        return propertyValue;
    }
}
