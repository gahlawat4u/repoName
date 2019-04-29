package com.gms.xms.txndb.vo;

/**
 * Posted from BatchProcessingFailDetailVo
 * <p>
 * Author TanDT Date May 6, 2015
 */
public class BatchProcessingFailDetailVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 5307730630256565997L;

    private Integer batchId;

    private String rowNo;

    private String requestType;

    private String errorDescription;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        return "BatchProcessingFailDetailVo [batchId=" + batchId + ", rowNo=" + rowNo + ", requestType=" + requestType + ", errorDescription=" + errorDescription + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
        result = prime * result + ((errorDescription == null) ? 0 : errorDescription.hashCode());
        result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
        result = prime * result + ((rowNo == null) ? 0 : rowNo.hashCode());
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
        BatchProcessingFailDetailVo other = (BatchProcessingFailDetailVo) obj;
        if (batchId == null) {
            if (other.batchId != null)
                return false;
        } else if (!batchId.equals(other.batchId))
            return false;
        if (errorDescription == null) {
            if (other.errorDescription != null)
                return false;
        } else if (!errorDescription.equals(other.errorDescription))
            return false;
        if (requestType == null) {
            if (other.requestType != null)
                return false;
        } else if (!requestType.equals(other.requestType))
            return false;
        if (rowNo == null) {
            if (other.rowNo != null)
                return false;
        } else if (!rowNo.equals(other.rowNo))
            return false;
        return true;
    }

}