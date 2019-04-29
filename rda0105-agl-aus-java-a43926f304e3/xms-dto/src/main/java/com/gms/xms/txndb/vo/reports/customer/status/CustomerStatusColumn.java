package com.gms.xms.txndb.vo.reports.customer.status;

/**
 * Posted from CustomerStatusColumnVo
 * <p>
 * Author DatTV Dec 16, 2015
 */
public class CustomerStatusColumn {
    private Integer group;
    private String columnName;
    private String fieldName;
    private Integer serviceId;

    @Override
    public String toString() {
        return "CustomerStatusColumn [group=" + group + ", columnName=" + columnName + ", fieldName=" + fieldName + ", serviceId=" + serviceId + "]";
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
