package com.gms.xms.model;

/**
 * Posted from SystemSettingDefaultValueModel
 * <p>
 * Author TANDT 19-10-2015
 */
public class SystemSettingDefaultValueModel extends BaseModel {
    private static final long serialVersionUID = -3220846554450055627L;

    private String key;

    private String value;

    private String dataSource;

    private String inputDisplay;

    private String textDisplay;

    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getTextDisplay() {
        return textDisplay;
    }

    public void setTextDisplay(String textDisplay) {
        this.textDisplay = textDisplay;
    }

    public String getInputDisplay() {
        return inputDisplay;
    }

    public void setInputDisplay(String inputDisplay) {
        this.inputDisplay = inputDisplay;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SystemSettingDefaultValueModel [key=" + key + ", value=" + value + ", dataSource=" + dataSource + ", inputDisplay=" + inputDisplay + ", textDisplay=" + textDisplay + ", filter=" + filter + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataSource == null) ? 0 : dataSource.hashCode());
        result = prime * result + ((filter == null) ? 0 : filter.hashCode());
        result = prime * result + ((inputDisplay == null) ? 0 : inputDisplay.hashCode());
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((textDisplay == null) ? 0 : textDisplay.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        SystemSettingDefaultValueModel other = (SystemSettingDefaultValueModel) obj;
        if (dataSource == null) {
            if (other.dataSource != null)
                return false;
        } else if (!dataSource.equals(other.dataSource))
            return false;
        if (filter == null) {
            if (other.filter != null)
                return false;
        } else if (!filter.equals(other.filter))
            return false;
        if (inputDisplay == null) {
            if (other.inputDisplay != null)
                return false;
        } else if (!inputDisplay.equals(other.inputDisplay))
            return false;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (textDisplay == null) {
            if (other.textDisplay != null)
                return false;
        } else if (!textDisplay.equals(other.textDisplay))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

}