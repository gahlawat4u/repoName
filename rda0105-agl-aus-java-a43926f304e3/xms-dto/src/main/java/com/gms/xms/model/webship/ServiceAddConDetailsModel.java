package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Posted from ServiceAddConDetailsModel
 * <p>
 * Author HungNT Date Aug 31, 2015
 */
public class ServiceAddConDetailsModel extends BaseModel {
    private static final long serialVersionUID = 5776764703083137366L;

    private String addConDetailId;

    private String addConDetailName;

    private String addConDetailType;

    private String addConDetailCode;

    private String addConId;

    private String addConDetailListValue;

    private String value;

    public String getAddConDetailId() {
        return addConDetailId;
    }

    public void setAddConDetailId(String addConDetailId) {
        this.addConDetailId = addConDetailId;
    }

    public String getAddConDetailName() {
        return addConDetailName;
    }

    public void setAddConDetailName(String addConDetailName) {
        this.addConDetailName = addConDetailName;
    }

    public String getAddConDetailType() {
        return addConDetailType;
    }

    public void setAddConDetailType(String addConDetailType) {
        this.addConDetailType = addConDetailType;
    }

    public String getAddConDetailCode() {
        return addConDetailCode;
    }

    public void setAddConDetailCode(String addConDetailCode) {
        this.addConDetailCode = addConDetailCode;
    }

    public String getAddConId() {
        return addConId;
    }

    public void setAddConId(String addConId) {
        this.addConId = addConId;
    }

    public String getAddConDetailListValue() {
        return addConDetailListValue;
    }

    public void setAddConDetailListValue(String addConDetailListValue) {
        this.addConDetailListValue = addConDetailListValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonIgnore
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        if (!StringUtils.isBlank(this.addConDetailListValue)) {
            String[] stringList = this.addConDetailListValue.trim().split(",");
            list = Arrays.asList(stringList);
        }
        return list;
    }

    @Override
    public String toString() {
        return "ServiceAddConDetailsModel [addConDetailId=" + addConDetailId + ", addConDetailName=" + addConDetailName + ", addConDetailType=" + addConDetailType + ", addConDetailCode=" + addConDetailCode + ", addConId=" + addConId + ", addConDetailListValue=" + addConDetailListValue + ", value=" + value + "]";
    }
}