package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from TradeTypeVo
 * <p>
 * Author DatTV Date Mar 27, 2015
 */
public class TradeTypeVo extends BaseVo {

    private static final long serialVersionUID = 9082700247777194765L;

    private Integer tradeId;

    private String tradeName;

    private String tradeCode;

    private Long localizationId;

    @Override
    public String toString() {
        return "TradeTypeVo [tradeId=" + tradeId + ", tradeName=" + tradeName + ", tradeCode=" + tradeCode + ", localizationId=" + localizationId + "]";
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
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

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }
}