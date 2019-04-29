package com.gms.xms.model.admin.imports;

import com.gms.xms.model.BaseModel;

import java.util.Map;

/**
 * Posted from May 25, 2016 9:15:30 AM
 * <p>
 * Author huynd
 */
public class ImportBillingFieldsModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Map<String, String> fieldName;

    private Map<String, String> accessorials;

    public Map<String, String> getFieldName() {
        return fieldName;
    }

    public void setFieldName(Map<String, String> fieldName) {
        this.fieldName = fieldName;
    }

    public Map<String, String> getAccessorials() {
        return accessorials;
    }

    public void setAccessorials(Map<String, String> accessorials) {
        this.accessorials = accessorials;
    }

    @Override
    public String toString() {
        return "ImportBillingFieldsModel [fieldName=" + fieldName + ", accessorials=" + accessorials + "]";
    }

}
