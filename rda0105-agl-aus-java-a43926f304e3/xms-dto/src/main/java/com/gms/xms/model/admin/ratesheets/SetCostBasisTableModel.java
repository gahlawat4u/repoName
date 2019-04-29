/**
 *
 */
package com.gms.xms.model.admin.ratesheets;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.RateSheetModel;

import java.util.List;

/**
 * Posted from SetCostBasisTableModel
 *
 * @author HungNT - @since Oct 6, 2015
 */
public class SetCostBasisTableModel extends BaseModel {
    private static final long serialVersionUID = -423621436742657570L;

    private String serviceId;
    private String serviceName;
    private String shipmentTypeId;
    private String shipmentTypeName;
    private String packageTypeName;
    private String sheetId;
    private String sheetName;
    private String content;
    private String bound;
    private String isPerWeight;
    private List<RateSheetModel> rateSheets;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
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

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public String getIsPerWeight() {
        return isPerWeight;
    }

    public void setIsPerWeight(String isPerWeight) {
        this.isPerWeight = isPerWeight;
    }

    public List<RateSheetModel> getRateSheets() {
        return rateSheets;
    }

    public void setRateSheets(List<RateSheetModel> rateSheets) {
        this.rateSheets = rateSheets;
    }

    @Override
    public String toString() {
        return "SetCostBasisTableModel [serviceId=" + serviceId + ", serviceName=" + serviceName + ", shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", packageTypeName=" + packageTypeName + ", sheetId=" + sheetId + ", sheetName=" + sheetName + ", content=" + content + ", bound=" + bound + ", isPerWeight=" + isPerWeight + ", rateSheets=" + rateSheets + "]";
    }
}
