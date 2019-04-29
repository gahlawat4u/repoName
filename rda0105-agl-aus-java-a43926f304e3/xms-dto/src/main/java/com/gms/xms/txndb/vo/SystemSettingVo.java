package com.gms.xms.txndb.vo;

public class SystemSettingVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -6421749478439652181L;
    private Integer settingId;
    private String settingName;
    private String settingValue;
    private String description;
    private Integer userLevel;
    private Integer allowNull;
    private String valueType;
    private String inputType;
    private String listValueDefault;

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getAllowNull() {
        return allowNull;
    }

    public void setAllowNull(Integer allowNull) {
        this.allowNull = allowNull;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getListValueDefault() {
        return listValueDefault;
    }

    public void setListValueDefault(String listValueDefault) {
        this.listValueDefault = listValueDefault;
    }

    @Override
    public String toString() {
        return "SystemSettingVo [settingId=" + settingId + ", settingName=" + settingName + ", settingValue=" + settingValue + ", description=" + description + ", userLevel=" + userLevel + ", allowNull=" + allowNull + ", valueType=" + valueType + ", inputType=" + inputType + ", listValueDefault=" + listValueDefault + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allowNull == null) ? 0 : allowNull.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((inputType == null) ? 0 : inputType.hashCode());
        result = prime * result + ((listValueDefault == null) ? 0 : listValueDefault.hashCode());
        result = prime * result + ((settingId == null) ? 0 : settingId.hashCode());
        result = prime * result + ((settingName == null) ? 0 : settingName.hashCode());
        result = prime * result + ((settingValue == null) ? 0 : settingValue.hashCode());
        result = prime * result + ((userLevel == null) ? 0 : userLevel.hashCode());
        result = prime * result + ((valueType == null) ? 0 : valueType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SystemSettingVo other = (SystemSettingVo) obj;
        if (allowNull == null) {
            if (other.allowNull != null)
                return false;
        } else if (!allowNull.equals(other.allowNull))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (inputType == null) {
            if (other.inputType != null)
                return false;
        } else if (!inputType.equals(other.inputType))
            return false;
        if (listValueDefault == null) {
            if (other.listValueDefault != null)
                return false;
        } else if (!listValueDefault.equals(other.listValueDefault))
            return false;
        if (settingId == null) {
            if (other.settingId != null)
                return false;
        } else if (!settingId.equals(other.settingId))
            return false;
        if (settingName == null) {
            if (other.settingName != null)
                return false;
        } else if (!settingName.equals(other.settingName))
            return false;
        if (settingValue == null) {
            if (other.settingValue != null)
                return false;
        } else if (!settingValue.equals(other.settingValue))
            return false;
        if (userLevel == null) {
            if (other.userLevel != null)
                return false;
        } else if (!userLevel.equals(other.userLevel))
            return false;
        if (valueType == null) {
            if (other.valueType != null)
                return false;
        } else if (!valueType.equals(other.valueType))
            return false;
        return true;
    }

}