package com.gms.xms.txndb.vo.webship.login;

import org.apache.commons.lang.StringUtils;

/**
 * Posted from WebshipDetailFilter
 * <p>
 * Author DatTV Date Jul 9, 2015 9:20:01 AM
 */
public class WebshipDetailFilter {
    private String customerCode;
    private String name;

    private String orderField;
    private Integer orderType;

    @Override
    public String toString() {
        return "WebshipDetailFilter [customerCode=" + customerCode + ", name=" + name + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderBy() {
        if (StringUtils.isBlank(orderField) || orderType == null) {
            return null;
        }
        return orderField + " " + (orderType == 0 || orderType == null ? "asc" : "desc");
    }
}