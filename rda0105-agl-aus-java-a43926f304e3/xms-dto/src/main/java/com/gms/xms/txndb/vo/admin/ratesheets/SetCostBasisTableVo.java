/**
 *
 */
package com.gms.xms.txndb.vo.admin.ratesheets;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.RateSheetVo;

import java.util.List;

/**
 * Posted from SetCostBasisTableVo
 *
 * @author HungNT - @since Oct 6, 2015
 */
public class SetCostBasisTableVo extends BaseVo {
    private static final long serialVersionUID = -6176992234159690788L;

    private Integer serviceId;
    private String serviceName;
    private Integer shipmentTypeId;
    private String shipmentTypeName;
    private String packageTypeName;
    private Long sheetId;
    private String sheetName;
    private Integer content;
    private Integer bound;
    private Integer isPerWeight;
    private List<RateSheetVo> rateSheets;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getPackageTypeName() {
        return packageTypeName;
    }

    public void setPackageTypeName(String packageTypeName) {
        this.packageTypeName = packageTypeName;
    }

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getBound() {
        return bound;
    }

    public void setBound(Integer bound) {
        this.bound = bound;
    }

    public Integer getIsPerWeight() {
        return isPerWeight;
    }

    public void setIsPerWeight(Integer isPerWeight) {
        this.isPerWeight = isPerWeight;
    }

    public List<RateSheetVo> getRateSheets() {
        return rateSheets;
    }

    public void setRateSheets(List<RateSheetVo> rateSheets) {
        this.rateSheets = rateSheets;
    }

    @Override
    public String toString() {
        return "SetCostBasisTableVo [serviceId=" + serviceId + ", serviceName=" + serviceName + ", shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", packageTypeName=" + packageTypeName + ", sheetId=" + sheetId + ", sheetName=" + sheetName + ", content=" + content + ", bound=" + bound + ", isPerWeight=" + isPerWeight + ", rateSheets=" + rateSheets + "]";
    }
}
