package com.gms.xms.txndb.vo.adjustment;

import java.util.Date;
import java.util.List;

/**
 * Posted from AirbillAdjustmentDetailFilter
 * <p>
 * Author DatTV Date May 12, 2015 2:24:11 PM
 */
public class AirbillAdjustmentDetailFilter extends AirbillAdjustmentDetailVo {

    private static final long serialVersionUID = -1266011113634568570L;
    private String customerCode;
    private Date requestDateFrom;
    private Date requestDateTo;
    private Integer serviceId;
    private Date responseDateFrom;
    private Date responseDateTo;
    private String creditCode;
    private Integer pageSize;
    private Integer page;
    private String orderBy;
    private List<String> franchiseCodeList;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Date getRequestDateFrom() {
        return requestDateFrom;
    }

    public void setRequestDateFrom(Date requestDateFrom) {
        this.requestDateFrom = requestDateFrom;
    }

    public Date getRequestDateTo() {
        return requestDateTo;
    }

    public void setRequestDateTo(Date requestDateTo) {
        this.requestDateTo = requestDateTo;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Date getResponseDateFrom() {
        return responseDateFrom;
    }

    public void setResponseDateFrom(Date responseDateFrom) {
        this.responseDateFrom = responseDateFrom;
    }

    public Date getResponseDateTo() {
        return responseDateTo;
    }

    public void setResponseDateTo(Date responseDateTo) {
        this.responseDateTo = responseDateTo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStartRecord() {
        return (page - 1) * pageSize;
    }

    public List<String> getFranchiseCodeList() {
        return franchiseCodeList;
    }

    public void setFranchiseCodeList(List<String> franchiseCodeList) {
        this.franchiseCodeList = franchiseCodeList;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    @Override
    public String toString() {
        return "AirbillAdjustmentDetailFilter [customerCode=" + customerCode + ", requestDateFrom=" + requestDateFrom + ", requestDateTo=" + requestDateTo + ", serviceId=" + serviceId + ", responseDateFrom=" + responseDateFrom + ", responseDateTo=" + responseDateTo + ", creditCode=" + creditCode + ", pageSize=" + pageSize + ", page=" + page + ", orderBy=" + orderBy + ", franchiseCodeList=" + franchiseCodeList + "]";
    }
}
