package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDoubleSerializer;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from AccessorialFilter
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class AccessorialFilter extends AccessorialVo {
    private static final long serialVersionUID = -1366174193059800848L;

    private Long customerCode;
    private Double baseCharge;
    private Integer quotable;
    private String carrierName;
    private Date shipmentDate;

    private Integer page;
    private Integer pageSize;
    private String orderField;
    private Integer orderType;

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }

    public Integer getQuotable() {
        return quotable;
    }

    public void setQuotable(Integer quotable) {
        this.quotable = quotable;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
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

    public String getOrderBy() {
        if (StringUtils.isBlank(orderField) || orderType == null) {
            return null;
        }
        return orderField + " " + (orderType == 0 || orderType == null ? "asc" : "desc");
    }


    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    @Override
    public String toString() {
        return "AccessorialFilter [customerCode=" + customerCode + ", baseCharge=" + baseCharge + ", quotable=" + quotable + ", carrierName=" + carrierName + ", shipmentDate=" + shipmentDate + ", page=" + page + ", pageSize=" + pageSize + ", orderField=" + orderField + ", orderType=" + orderType + "]";
    }
}
