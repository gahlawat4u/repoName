/**
 *
 */
package com.gms.xms.txndb.vo;

import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from ShipmentTypeFilter
 * <p>
 * Author HungNT Date Apr 16, 2015
 */
public class ShipmentTypeFilter extends ShipmentTypeVo {
    private static final long serialVersionUID = 4491391063688157360L;

    private long customerCode;
    private long webshipId;
    private String listService;
    private Integer page;
    private Integer pageSize;
    private String orderField;
    private Integer orderType;

    public String getListService() {
        return listService;
    }

    public void setListService(String listService) {
        this.listService = listService;
    }

    public long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(long customerCode) {
        this.customerCode = customerCode;
    }

    public long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(long webshipId) {
        this.webshipId = webshipId;
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
}