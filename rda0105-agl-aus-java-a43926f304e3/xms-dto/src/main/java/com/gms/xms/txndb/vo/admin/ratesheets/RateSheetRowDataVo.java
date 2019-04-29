package com.gms.xms.txndb.vo.admin.ratesheets;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from May 7, 2016 9:08:33 AM
 * <p>
 * Author huynd
 */
public class RateSheetRowDataVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
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
        return "RateSheetRowDataModel [key=" + key + ", value=" + value + "]";
    }

}
