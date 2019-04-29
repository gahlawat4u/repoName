package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.ServiceVo;
import org.apache.commons.lang.StringUtils;

/**
 * Posted from ServiceFilter
 * <p>
 * Author HungNT Date Jul 16, 2015
 */
public class ServiceFilter extends ServiceVo {
    private static final long serialVersionUID = -8973146380385361563L;

    private Integer page;
    private Integer pageSize;
    private String orderField;
    private Integer orderType;
    private Long customerCode;
    private String listServices;

    public String getListServices() {
        return listServices;
    }

    public void setListServices(String listServices) {
        this.listServices = listServices;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
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
