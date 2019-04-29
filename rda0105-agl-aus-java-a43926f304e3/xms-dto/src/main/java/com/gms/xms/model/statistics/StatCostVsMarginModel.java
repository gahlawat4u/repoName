package com.gms.xms.model.statistics;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Aug 18, 2016 5:21:46 PM
 * <p>
 * Author dattrinh
 */
public class StatCostVsMarginModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String startDate;
    private String endDate;
    private String revenue;
    private String margin;
    private String cost;
    private String columnName;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String toString() {
        return "StatCostVsMarginModel [startDate=" + startDate + ", endDate=" + endDate + ", revenue=" + revenue + ", margin=" + margin + ", cost=" + cost + "]";
    }
}
