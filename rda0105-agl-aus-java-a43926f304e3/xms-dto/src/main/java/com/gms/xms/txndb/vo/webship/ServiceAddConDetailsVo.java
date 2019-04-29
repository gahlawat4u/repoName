package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from ServiceAddConDetailsVo
 * <p>
 * Author HungNT Date Aug 31, 2015
 */
public class ServiceAddConDetailsVo extends BaseVo {
    private static final long serialVersionUID = -3149739865263709942L;

    private Integer addConDetailId;

    private String addConDetailName;

    private String addConDetailType;

    private String addConDetailCode;

    private Integer addConId;

    private String addConDetailListValue;

    private String value;

    public Integer getAddConDetailId() {
        return addConDetailId;
    }

    public void setAddConDetailId(Integer addConDetailId) {
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

    public Integer getAddConId() {
        return addConId;
    }

    public void setAddConId(Integer addConId) {
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

    @Override
    public String toString() {
        return "ServiceAddConDetailsVo [addConDetailId=" + addConDetailId + ", addConDetailName=" + addConDetailName + ", addConDetailType=" + addConDetailType + ", addConDetailCode=" + addConDetailCode + ", addConId=" + addConId + ", addConDetailListValue=" + addConDetailListValue + ", value=" + value + "]";
    }
}