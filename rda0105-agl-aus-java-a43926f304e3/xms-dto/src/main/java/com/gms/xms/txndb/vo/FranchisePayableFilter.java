package com.gms.xms.txndb.vo;

import com.gms.xms.common.constants.AppConstants;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Posted from FranchisePayableFilter.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:24:23 PM
 */
public class FranchisePayableFilter extends BaseVo {

    private static final long serialVersionUID = 1L;
    private Date startDate;
    private Date endDate;
    private Date date;
    private List<String> franchiseCodeList;
    private List<String> carierCentralizedList = Arrays.asList(AppConstants.APP_SETTINGS.getCarierCentralized().split(","));
    private Integer startRecord;
    private Integer recordSize;
    private String rptTxnId;
    private String franchiseCode;

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public Integer getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(Integer startRecord) {
        this.startRecord = startRecord;
    }

    public Integer getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(Integer recordSize) {
        this.recordSize = recordSize;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getFranchiseCodeList() {
        return franchiseCodeList;
    }

    public void setFranchiseCodeList(List<String> franchiseCodeList) {
        this.franchiseCodeList = franchiseCodeList;
    }

    public List<String> getCarierCentralizedList() {
        return carierCentralizedList;
    }

    public void setCarierCentralizedList(List<String> carierCentralizedList) {
        this.carierCentralizedList = carierCentralizedList;
    }

    public Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.endDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FranchisePayableFilter [startDate=" + startDate + ", endDate=" + endDate + ", date=" + date + ", franchiseCodeList=" + franchiseCodeList + ", carierCentralizedList=" + carierCentralizedList + ", startRecord=" + startRecord + ", recordSize=" + recordSize + ", rptTxnId=" + rptTxnId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carierCentralizedList == null) ? 0 : carierCentralizedList.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((franchiseCodeList == null) ? 0 : franchiseCodeList.hashCode());
        result = prime * result + ((recordSize == null) ? 0 : recordSize.hashCode());
        result = prime * result + ((rptTxnId == null) ? 0 : rptTxnId.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((startRecord == null) ? 0 : startRecord.hashCode());
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
        FranchisePayableFilter other = (FranchisePayableFilter) obj;
        if (carierCentralizedList == null) {
            if (other.carierCentralizedList != null)
                return false;
        } else if (!carierCentralizedList.equals(other.carierCentralizedList))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (franchiseCodeList == null) {
            if (other.franchiseCodeList != null)
                return false;
        } else if (!franchiseCodeList.equals(other.franchiseCodeList))
            return false;
        if (recordSize == null) {
            if (other.recordSize != null)
                return false;
        } else if (!recordSize.equals(other.recordSize))
            return false;
        if (rptTxnId == null) {
            if (other.rptTxnId != null)
                return false;
        } else if (!rptTxnId.equals(other.rptTxnId))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (startRecord == null) {
            if (other.startRecord != null)
                return false;
        } else if (!startRecord.equals(other.startRecord))
            return false;
        return true;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

}
