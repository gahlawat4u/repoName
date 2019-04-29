package com.gms.xms.model.importfield;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.StateModel;

import java.util.List;
import java.util.Map;

/**
 * Posted from CustomerAddressBookImportModel
 * <p>
 * Author HungNT Date May 12, 2015
 */
public class CustomerAddressBookImportModel extends BaseModel {
    private static final long serialVersionUID = -7905344429823238874L;
    private List<String> mapFields;
    private Map<String, String> importSelectList;
    private String importJsonData;
    private String hasHeader;
    private Boolean isMapped = false;
    private List<StateModel> stateList;
    private String fieldMapJsonData;
    private String mappedJsonData;

    public List<String> getMapFields() {
        return mapFields;
    }

    public void setMapFields(List<String> mapFields) {
        this.mapFields = mapFields;
    }

    public Map<String, String> getImportSelectList() {
        return importSelectList;
    }

    public void setImportSelectList(Map<String, String> importSelectList) {
        this.importSelectList = importSelectList;
    }

    public String getImportJsonData() {
        return importJsonData;
    }

    public void setImportJsonData(String importJsonData) {
        this.importJsonData = importJsonData;
    }

    public String getHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(String hasHeader) {
        this.hasHeader = hasHeader;
    }

    public Boolean getIsMapped() {
        return isMapped;
    }

    public void setIsMapped(Boolean isMapped) {
        this.isMapped = isMapped;
    }

    public List<StateModel> getStateList() {
        return stateList;
    }

    public void setStateList(List<StateModel> stateList) {
        this.stateList = stateList;
    }

    public String getFieldMapJsonData() {
        return fieldMapJsonData;
    }

    public void setFieldMapJsonData(String fieldMapJsonData) {
        this.fieldMapJsonData = fieldMapJsonData;
    }

    public String getMappedJsonData() {
        return mappedJsonData;
    }

    public void setMappedJsonData(String mappedJsonData) {
        this.mappedJsonData = mappedJsonData;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fieldMapJsonData == null) ? 0 : fieldMapJsonData.hashCode());
        result = prime * result + ((hasHeader == null) ? 0 : hasHeader.hashCode());
        result = prime * result + ((importJsonData == null) ? 0 : importJsonData.hashCode());
        result = prime * result + ((importSelectList == null) ? 0 : importSelectList.hashCode());
        result = prime * result + ((isMapped == null) ? 0 : isMapped.hashCode());
        result = prime * result + ((mapFields == null) ? 0 : mapFields.hashCode());
        result = prime * result + ((mappedJsonData == null) ? 0 : mappedJsonData.hashCode());
        result = prime * result + ((stateList == null) ? 0 : stateList.hashCode());
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
        CustomerAddressBookImportModel other = (CustomerAddressBookImportModel) obj;
        if (fieldMapJsonData == null) {
            if (other.fieldMapJsonData != null)
                return false;
        } else if (!fieldMapJsonData.equals(other.fieldMapJsonData))
            return false;
        if (hasHeader == null) {
            if (other.hasHeader != null)
                return false;
        } else if (!hasHeader.equals(other.hasHeader))
            return false;
        if (importJsonData == null) {
            if (other.importJsonData != null)
                return false;
        } else if (!importJsonData.equals(other.importJsonData))
            return false;
        if (importSelectList == null) {
            if (other.importSelectList != null)
                return false;
        } else if (!importSelectList.equals(other.importSelectList))
            return false;
        if (isMapped == null) {
            if (other.isMapped != null)
                return false;
        } else if (!isMapped.equals(other.isMapped))
            return false;
        if (mapFields == null) {
            if (other.mapFields != null)
                return false;
        } else if (!mapFields.equals(other.mapFields))
            return false;
        if (mappedJsonData == null) {
            if (other.mappedJsonData != null)
                return false;
        } else if (!mappedJsonData.equals(other.mappedJsonData))
            return false;
        if (stateList == null) {
            if (other.stateList != null)
                return false;
        } else if (!stateList.equals(other.stateList))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CustomerAddressBookImportModel [mapFields=" + mapFields + ", importSelectList=" + importSelectList + ", importJsonData=" + importJsonData + ", hasHeader=" + hasHeader + ", isMapped=" + isMapped + ", stateList=" + stateList + ", fieldMapJsonData=" + fieldMapJsonData + ", mappedJsonData=" + mappedJsonData + "]";
    }
}
