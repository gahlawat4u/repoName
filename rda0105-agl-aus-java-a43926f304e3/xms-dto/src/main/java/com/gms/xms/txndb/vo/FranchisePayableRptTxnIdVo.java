package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from FranchisePayableRptTxnIdVo.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:24:56 PM
 */
public class FranchisePayableRptTxnIdVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private String rptTxnId;
    private Date createDate;
    private String description;

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FranchisePayableRptTxnIdVo [rptTxnId=" + rptTxnId + ", createDate=" + createDate + ", description=" + description + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((rptTxnId == null) ? 0 : rptTxnId.hashCode());
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
        FranchisePayableRptTxnIdVo other = (FranchisePayableRptTxnIdVo) obj;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (rptTxnId == null) {
            if (other.rptTxnId != null)
                return false;
        } else if (!rptTxnId.equals(other.rptTxnId))
            return false;
        return true;
    }
}
