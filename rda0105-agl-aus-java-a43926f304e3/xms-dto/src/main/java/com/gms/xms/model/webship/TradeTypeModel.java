package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from TradeTypeModel
 * <p>
 * Author DatTV Date Mar 27, 2015
 */
public class TradeTypeModel extends BaseModel {

    private static final long serialVersionUID = 4750002412618054281L;

    private String tradeId;

    private String tradeName;

    private String tradeCode;

    private String localizationId;

    @Override
    public String toString() {
        return "TradeTypeModel [tradeId=" + tradeId + ", tradeName=" + tradeName + ", tradeCode=" + tradeCode + ", localizationId=" + localizationId + "]";
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }
}