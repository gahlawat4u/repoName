package com.gms.xms.model.admin.invoicing.searchairbill;

import com.gms.xms.model.BaseModel;

/**
 * File Name: QuoteAirbillModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 21-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.searchairbill <br/>
 */
public class QuoteAirbillModel extends BaseModel {
    private static final long serialVersionUID = 6573222318073337468L;
    private String quoteShipmentId;
    private String accessorialId;
    private String amount;
    private String defaultCharge;
    private String quoteDescription;
    private String code;
    private String typeId;
    private String carrierId;
    private String applyStartDate;

    public String getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(String applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public String getQuoteShipmentId() {
        return quoteShipmentId;
    }

    public void setQuoteShipmentId(String quoteShipmentId) {
        this.quoteShipmentId = quoteShipmentId;
    }

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(String defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    public String getQuoteDescription() {
        return quoteDescription;
    }

    public void setQuoteDescription(String quoteDescription) {
        this.quoteDescription = quoteDescription;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    public String toString() {
        return "QuoteAirbillModel [quoteShipmentId=" + quoteShipmentId + ", accessorialId=" + accessorialId + ", amount=" + amount + ", defaultCharge=" + defaultCharge + ", quoteDescription=" + quoteDescription + ", code=" + code + ", typeId=" + typeId + ", carrierId=" + carrierId + ", applyStartDate=" + applyStartDate + "]";
    }

}
