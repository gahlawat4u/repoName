package com.gms.xms.model.webship;

import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.BaseModel;

/**
 * Posted from WebshipQuoteLogDetailModel
 * <p>
 * Author HungNT Date Apr 7, 2015
 */
public class WebshipQuoteLogDetailModel extends BaseModel {
    private static final long serialVersionUID = -7697817738717807791L;

    private String quoteId;

    private String accessorialId;

    private String amount;

    private AccessorialModel accessorial;

    private Integer type;

    @Override
    public String toString() {
        return "WebshipQuoteLogDetailModel [quoteId=" + quoteId + ", accessorialId=" + accessorialId + ", amount=" + amount + ", accessorial=" + accessorial + ", type="  + type + "]";
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
        WebshipQuoteLogDetailModel other = (WebshipQuoteLogDetailModel) obj;
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

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
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

    public AccessorialModel getAccessorial() {
        return accessorial;
    }

    public void setAccessorial(AccessorialModel accessorial) {
        this.accessorial = accessorial;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}