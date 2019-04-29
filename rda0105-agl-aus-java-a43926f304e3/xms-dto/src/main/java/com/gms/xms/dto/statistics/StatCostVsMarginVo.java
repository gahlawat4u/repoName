package com.gms.xms.dto.statistics;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from Aug 18, 2016 5:21:46 PM
 * <p>
 * Author dattrinh
 */
public class StatCostVsMarginVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Date startDate;
    private Date endDate;
    private Double revenue;
    private Double margin;
    private Double cost;
    private String columnName;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
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
        return "StatCostVsMarginVo [startDate=" + startDate + ", endDate=" + endDate + ", revenue=" + revenue + ", margin=" + margin + ", cost=" + cost + "]";
    }
}
