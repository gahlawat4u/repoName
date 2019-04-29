package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDecimalString2DoubleDeserializer;
import com.gms.xms.common.json.JsonDoubleSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from WebshipQuoteLogDetailVo
 * <p>
 * Author HungNT Date Apr 6, 2015
 */
public class WebshipQuoteLogDetailVo extends BaseVo {
    private static final long serialVersionUID = -7697817738717807791L;

    private Long quoteId;

    private Long accessorialId;

    private Double amount;

    private AccessorialVo accessorial;

    private Integer type;

    @Override
    public String toString() {
        return "WebshipQuoteLogDetailVo [quoteId=" + quoteId + ", accessorialId=" + accessorialId + ", amount=" + amount + ", accessorial=" + accessorial + ", type="  + type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessorial == null) ? 0 : accessorial.hashCode());
        result = prime * result + ((accessorialId == null) ? 0 : accessorialId.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((quoteId == null) ? 0 : quoteId.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WebshipQuoteLogDetailVo other = (WebshipQuoteLogDetailVo) obj;
        if (accessorial == null) {
            if (other.accessorial != null)
                return false;
        } else if (!accessorial.equals(other.accessorial))
            return false;
        if (accessorialId == null) {
            if (other.accessorialId != null)
                return false;
        } else if (!accessorialId.equals(other.accessorialId))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (quoteId == null) {
            if (other.quoteId != null)
                return false;
        } else if (!quoteId.equals(other.quoteId))
            return false;

        if(type == null){
            if (other.type != null){
                return false;
            }
        }else if (!type.equals(other.type)){
            return false;
        }
        return true;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public Long getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(Long accessorialId) {
        this.accessorialId = accessorialId;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getAmount() {
        return amount;
    }

    @JsonDeserialize(using = JsonDecimalString2DoubleDeserializer.class)
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public AccessorialVo getAccessorial() {
        return accessorial;
    }

    public void setAccessorial(AccessorialVo accessorial) {
        this.accessorial = accessorial;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}