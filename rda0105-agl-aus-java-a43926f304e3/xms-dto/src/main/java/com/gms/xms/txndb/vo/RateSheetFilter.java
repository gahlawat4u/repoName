package com.gms.xms.txndb.vo;

/**
 * Posted from RateSheetFilter
 * <p>
 * Author HungNT Date May 19, 2015
 */
public class RateSheetFilter extends RateSheetVo {
    private static final long serialVersionUID = 4922438935162790377L;

    private String columnName;
    private Integer serviceId;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "RateSheetFilter [columnName=" + columnName + ", serviceId=" + serviceId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
        return result;
    }
}
