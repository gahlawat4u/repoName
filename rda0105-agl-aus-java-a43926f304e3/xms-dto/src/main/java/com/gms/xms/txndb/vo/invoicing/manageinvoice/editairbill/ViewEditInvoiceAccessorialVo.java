package com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * @author TANDT
 */
public class ViewEditInvoiceAccessorialVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -5812235140504357460L;
    private String accessorialId;
    private String accessorialCode;
    private String accessorialName;
    private Date applyStartDate;
    private Boolean isQuoteAble;
    private Integer typeId;
    private Double defaultCharge;

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public String getAccessorialCode() {
        return accessorialCode;
    }

    public void setAccessorialCode(String accessorialCode) {
        this.accessorialCode = accessorialCode;
    }

    public String getAccessorialName() {
        return accessorialName;
    }

    public void setAccessorialName(String accessorialName) {
        this.accessorialName = accessorialName;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getApplyStartDate() {
        return applyStartDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setApplyStartDate(Date applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public Boolean getIsQuoteAble() {
        return isQuoteAble;
    }

    public void setIsQuoteAble(Boolean isQuoteAble) {
        this.isQuoteAble = isQuoteAble;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Double getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(Double defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    @Override
    public String toString() {
        return "ViewEditInvoiceAccessorialDetailVo [accessorialId=" + accessorialId + ", accessorialCode=" + accessorialCode + ", accessorialName=" + accessorialName + ", applyStartDate=" + applyStartDate + ", isQuoteAble=" + isQuoteAble + ", typeId=" + typeId + ", defaultCharge=" + defaultCharge + "]";
    }

}
