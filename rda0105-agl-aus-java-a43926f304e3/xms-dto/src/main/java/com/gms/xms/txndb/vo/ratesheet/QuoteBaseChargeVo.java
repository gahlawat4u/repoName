package com.gms.xms.txndb.vo.ratesheet;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from QuoteBaseChargeVo
 * <p>
 * Author TANDT
 */
public class QuoteBaseChargeVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 2258373711793170450L;
    private String value;
    private String rowName;
    private String charRowName;
    private String isChar;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getCharRowName() {
        return charRowName;
    }

    public void setCharRowName(String charRowName) {
        this.charRowName = charRowName;
    }

    public String getIsChar() {
        return isChar;
    }

    public void setIsChar(String isChar) {
        this.isChar = isChar;
    }

    @Override
    public String toString() {
        return "QuoteBaseChargeVo [value=" + value + ", rowName=" + rowName + ", charRowName=" + charRowName + ", isChar=" + isChar + "]";
    }

}
