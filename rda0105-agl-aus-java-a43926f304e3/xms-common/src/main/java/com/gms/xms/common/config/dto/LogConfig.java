package com.gms.xms.common.config.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class LogConfig {
    private String sqlId;
    private String cfgType;
    private String actionFilter;
    private String actionfields;
    private String actionTable;
    private String actionType;

    public String getSqlId() {
        return sqlId;
    }

    @XmlAttribute(name = "id")
    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getCfgType() {
        return cfgType;
    }

    @XmlAttribute(name = "cfg-type")
    public void setCfgType(String cfgType) {
        this.cfgType = cfgType;
    }

    public String getActionFilter() {
        return actionFilter;
    }

    @XmlElement(name = "action-filter")
    public void setActionFilter(String actionFilter) {
        this.actionFilter = actionFilter;
    }

    public String getActionfields() {
        return actionfields;
    }

    @XmlElement(name = "action-fields")
    public void setActionfields(String actionfields) {
        this.actionfields = actionfields;
    }

    public String getActionTable() {
        return actionTable;
    }

    @XmlAttribute(name = "action-table")
    public void setActionTable(String actionTable) {
        this.actionTable = actionTable;
    }

    public String getActionType() {
        return actionType;
    }

    @XmlAttribute(name = "action-type")
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "LogConfig [sqlId=" + sqlId + ", cfgType=" + cfgType + ", actionFilter=" + actionFilter
                + ", actionfields=" + actionfields + ", actionTable=" + actionTable + ", actionType=" + actionType
                + "]";
    }

}
