package com.gms.xms.txndb.vo.invoicing.searchairbill;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * File Name: QuoteAirbillVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 21-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing.searchairbill <br/>
 */
public class QuoteAirbillVo extends BaseVo {

    private static final long serialVersionUID = 837454162788430584L;
    private Long quoteShipmentId;
    private Long accessorialId;
    private Double amount;
    private Double defaultCharge;
    private String quoteDescription;
    private String code;
    private Integer typeId;
    private Long carrierId;
    private Date applyStartDate;

    public Date getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(Date applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public Long getQuoteShipmentId() {
        return quoteShipmentId;
    }

    public void setQuoteShipmentId(Long quoteShipmentId) {
        this.quoteShipmentId = quoteShipmentId;
    }

    public Long getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(Long accessorialId) {
        this.accessorialId = accessorialId;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(Double defaultCharge) {
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    public String toString() {
        return "QuoteAirbillVo [quoteShipmentId=" + quoteShipmentId + ", accessorialId=" + accessorialId + ", amount=" + amount + ", defaultCharge=" + defaultCharge + ", quoteDescription=" + quoteDescription + ", code=" + code + ", typeId=" + typeId + ", carrierId=" + carrierId + ", applyStartDate=" + applyStartDate + "]";
    }
}
