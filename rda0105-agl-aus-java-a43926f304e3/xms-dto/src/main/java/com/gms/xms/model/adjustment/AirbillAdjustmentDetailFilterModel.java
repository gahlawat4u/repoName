package com.gms.xms.model.adjustment;

/**
 * Posted from AirbillAdjustmentDetailFilterModel
 * <p>
 * Author DatTV Date May 12, 2015 2:24:01 PM
 */
public class AirbillAdjustmentDetailFilterModel extends AirbillAdjustmentDetailModel {

    private static final long serialVersionUID = -1266011113634568570L;
    private String requestDateFrom;
    private String requestDateTo;
    private String responseDateFrom;
    private String responseDateTo;
    private String creditCode;
    private String pageSize;
    private String page;
    private String orderBy;

    public String getRequestDateFrom() {
        return requestDateFrom;
    }

    public void setRequestDateFrom(String requestDateFrom) {
        this.requestDateFrom = requestDateFrom;
    }

    public String getRequestDateTo() {
        return requestDateTo;
    }

    public void setRequestDateTo(String requestDateTo) {
        this.requestDateTo = requestDateTo;
    }

    public String getResponseDateFrom() {
        return responseDateFrom;
    }

    public void setResponseDateFrom(String responseDateFrom) {
        this.responseDateFrom = responseDateFrom;
    }

    public String getResponseDateTo() {
        return responseDateTo;
    }

    public void setResponseDateTo(String responseDateTo) {
        this.responseDateTo = responseDateTo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
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

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }
}
