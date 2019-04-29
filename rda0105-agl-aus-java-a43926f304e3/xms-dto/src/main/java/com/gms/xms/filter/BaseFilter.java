package com.gms.xms.filter;

import org.apache.commons.lang.StringUtils;

/**
 * Posted from BaseFilter
 * <p>
 * Author DatTV Sep 24, 2015
 */
public class BaseFilter {
    private Integer page;
    private Integer pageSize;
    private String orderField;
    private Integer orderType;

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

    public Integer getStartRecord() {
        if (page != null && pageSize != null) {
            return (page - 1) * pageSize;
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
