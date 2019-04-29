package com.gms.xms.model.webship.history;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from HistoryFilterModel
 * <p>
 * Author TanDT Date Jul 9, 2015
 */
public class HistoryFilterModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 8770002259057982182L;

    private String toDate;
    private String defaultOriginCountry;
    private String fromDate;
    private String senderCity;
    private String reciverCity;
    private String senderName;
    private String reciverName;
    private String customerCode;
    private String totalDate;
    private String startRecord;
    private String recordSize;
    private String totalRecord;
    private String page;
    private String orderBy;
    private String connoteNumber;
    private List<String> listShipmentId;

    public List<String> getListShipmentId() {
        return listShipmentId;
    }

    public void setListShipmentId(List<String> listShipmentId) {
        this.listShipmentId = listShipmentId;
    }

    public String getDefaultOriginCountry() {
        return defaultOriginCountry;
    }

    public void setDefaultOriginCountry(String defaultOriginCountry) {
        this.defaultOriginCountry = defaultOriginCountry;
    }

    public String getConnoteNumber() {
        return connoteNumber;
    }

    public void setConnoteNumber(String connoteNumber) {
        this.connoteNumber = connoteNumber;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getReciverCity() {
        return reciverCity;
    }

    public void setReciverCity(String reciverCity) {
        this.reciverCity = reciverCity;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(String totalDate) {
        this.totalDate = totalDate;
    }

    public String getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(String startRecord) {
        this.startRecord = startRecord;
    }

    public String getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(String recordSize) {
        this.recordSize = recordSize;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "HistoryFilterModel [toDate=" + toDate + ", defaultOriginCountry=" + defaultOriginCountry + ", fromDate=" + fromDate + ", senderCity=" + senderCity + ", reciverCity=" + reciverCity + ", senderName=" + senderName + ", reciverName=" + reciverName + ", customerCode=" + customerCode + ", totalDate=" + totalDate + ", startRecord=" + startRecord + ", recordSize=" + recordSize + ", totalRecord=" + totalRecord + ", page=" + page + ", orderBy=" + orderBy + ", connoteNumber="
                + connoteNumber + ", listShipmentId=" + listShipmentId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((connoteNumber == null) ? 0 : connoteNumber.hashCode());
        result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
        result = prime * result + ((defaultOriginCountry == null) ? 0 : defaultOriginCountry.hashCode());
        result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
        result = prime * result + ((listShipmentId == null) ? 0 : listShipmentId.hashCode());
        result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
        result = prime * result + ((page == null) ? 0 : page.hashCode());
        result = prime * result + ((reciverCity == null) ? 0 : reciverCity.hashCode());
        result = prime * result + ((reciverName == null) ? 0 : reciverName.hashCode());
        result = prime * result + ((recordSize == null) ? 0 : recordSize.hashCode());
        result = prime * result + ((senderCity == null) ? 0 : senderCity.hashCode());
        result = prime * result + ((senderName == null) ? 0 : senderName.hashCode());
        result = prime * result + ((startRecord == null) ? 0 : startRecord.hashCode());
        result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
        result = prime * result + ((totalDate == null) ? 0 : totalDate.hashCode());
        result = prime * result + ((totalRecord == null) ? 0 : totalRecord.hashCode());
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
        HistoryFilterModel other = (HistoryFilterModel) obj;
        if (connoteNumber == null) {
            if (other.connoteNumber != null)
                return false;
        } else if (!connoteNumber.equals(other.connoteNumber))
            return false;
        if (customerCode == null) {
            if (other.customerCode != null)
                return false;
        } else if (!customerCode.equals(other.customerCode))
            return false;
        if (defaultOriginCountry == null) {
            if (other.defaultOriginCountry != null)
                return false;
        } else if (!defaultOriginCountry.equals(other.defaultOriginCountry))
            return false;
        if (fromDate == null) {
            if (other.fromDate != null)
                return false;
        } else if (!fromDate.equals(other.fromDate))
            return false;
        if (listShipmentId == null) {
            if (other.listShipmentId != null)
                return false;
        } else if (!listShipmentId.equals(other.listShipmentId))
            return false;
        if (orderBy == null) {
            if (other.orderBy != null)
                return false;
        } else if (!orderBy.equals(other.orderBy))
            return false;
        if (page == null) {
            if (other.page != null)
                return false;
        } else if (!page.equals(other.page))
            return false;
        if (reciverCity == null) {
            if (other.reciverCity != null)
                return false;
        } else if (!reciverCity.equals(other.reciverCity))
            return false;
        if (reciverName == null) {
            if (other.reciverName != null)
                return false;
        } else if (!reciverName.equals(other.reciverName))
            return false;
        if (recordSize == null) {
            if (other.recordSize != null)
                return false;
        } else if (!recordSize.equals(other.recordSize))
            return false;
        if (senderCity == null) {
            if (other.senderCity != null)
                return false;
        } else if (!senderCity.equals(other.senderCity))
            return false;
        if (senderName == null) {
            if (other.senderName != null)
                return false;
        } else if (!senderName.equals(other.senderName))
            return false;
        if (startRecord == null) {
            if (other.startRecord != null)
                return false;
        } else if (!startRecord.equals(other.startRecord))
            return false;
        if (toDate == null) {
            if (other.toDate != null)
                return false;
        } else if (!toDate.equals(other.toDate))
            return false;
        if (totalDate == null) {
            if (other.totalDate != null)
                return false;
        } else if (!totalDate.equals(other.totalDate))
            return false;
        if (totalRecord == null) {
            if (other.totalRecord != null)
                return false;
        } else if (!totalRecord.equals(other.totalRecord))
            return false;
        return true;
    }

}