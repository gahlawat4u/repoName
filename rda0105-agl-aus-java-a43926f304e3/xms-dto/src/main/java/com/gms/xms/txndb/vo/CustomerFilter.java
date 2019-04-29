package com.gms.xms.txndb.vo;

import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Posted from CustomerFilter
 * <p>
 * Author DatTV Sep 1, 2015
 */
public class CustomerFilter extends CustomerVo {

    private static final long serialVersionUID = -7753527890060842786L;

    private Date submitStartDate;
    private Date submitEndDate;
    private String salesRepName;
    private String customerName;
    private Integer page;
    private Integer pageSize;
    private List<String> franchiseCodeList;
    private String orderField;
    private Integer orderType;

    public Date getSubmitStartDate() {
        return submitStartDate;
    }

    public void setSubmitStartDate(Date submitStartDate) {
        this.submitStartDate = submitStartDate;
    }

    public Date getSubmitEndDate() {
        return submitEndDate;
    }

    public void setSubmitEndDate(Date submitEndDate) {
        this.submitEndDate = submitEndDate;
    }

    public String getSalesRepName() {
        return salesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getFranchiseCodeList() {
        return franchiseCodeList;
    }

    public void setFranchiseCodeList(List<String> franchiseCodeList) {
        this.franchiseCodeList = franchiseCodeList;
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

    public Integer getStartRecord() {
        if (page != null && pageSize != null) {
            return (page - 1) * pageSize;
        } else
            return null;
    }

    public String getOrderBy() {
        if (StringUtils.isBlank(orderField) || orderType == null) {
            return null;
        }
        return orderField + " " + (orderType == 0 || orderType == null ? "asc" : "desc");
    }

    @Override
    public String toString() {
        return "CustomerFilter [submitStartDate=" + submitStartDate + ", submitEndDate=" + submitEndDate + ", salesRepName=" + salesRepName + ", customerName=" + customerName + ", page=" + page + ", pageSize=" + pageSize + ", franchiseCodeList=" + franchiseCodeList + ", orderField=" + orderField + ", orderType=" + orderType + "]";
    }
}