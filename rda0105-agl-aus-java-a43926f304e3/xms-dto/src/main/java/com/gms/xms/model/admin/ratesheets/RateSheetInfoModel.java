package com.gms.xms.model.admin.ratesheets;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.RateSheetColumnModel;
import com.gms.xms.model.RateSheetDetailInfoModel;
import com.gms.xms.model.RateSheetRowModel;

import java.util.List;
import java.util.Map;

/**
 * Posted from Apr 8, 2016 11:55:21 AM
 * <p>
 * Author dattrinh
 */
public class RateSheetInfoModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String shipmentTypeId;
    private String maxWeight;
    private String title;
    private String senderSuburb;
    private List<RateSheetRowModel> rows;
    private List<RateSheetColumnModel> columns;
    private Map<String, RateSheetDetailInfoModel> data;

    public List<RateSheetRowModel> getRows() {
        return rows;
    }

    public void setRows(List<RateSheetRowModel> rows) {
        this.rows = rows;
    }

    public List<RateSheetColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<RateSheetColumnModel> columns) {
        this.columns = columns;
    }

    public Map<String, RateSheetDetailInfoModel> getData() {
        return data;
    }

    public void setData(Map<String, RateSheetDetailInfoModel> data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getSenderSuburb() {
        return senderSuburb;
    }

    public void setSenderSuburb(String senderSuburb) {
        this.senderSuburb = senderSuburb;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    @Override
    public String toString() {
        return "RateSheetInfoModel [maxWeight=" + maxWeight + ", title=" + title + ", senderSuburb=" + senderSuburb + ", rows=" + rows + ", columns=" + columns + ", data=" + data + "]";
    }
}
