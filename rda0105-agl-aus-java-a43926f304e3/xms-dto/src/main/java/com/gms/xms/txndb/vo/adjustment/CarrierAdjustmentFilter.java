package com.gms.xms.txndb.vo.adjustment;

import org.apache.commons.lang.StringUtils;

/**
 * Posted from CarrierAdjustmentFilter
 * <p>
 * Author TanDT Date May 26, 2015
 */
public class CarrierAdjustmentFilter extends CarrierAdjustmentVo {

    private static final long serialVersionUID = -2303359884878779705L;

    private Integer page;
    private Integer recordSize;
    private Integer status;
    private String orderField;
    private Integer orderType;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(Integer recordSize) {
        this.recordSize = recordSize;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStartRecord() {
        if (page != null && recordSize != null) {
            return (page - 1) * recordSize;
        } else
            return null;
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
