package com.gms.xms.txndb.vo.reports.customer.status;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from CustomerStatusVo
 * <p>
 * Author DatTV Nov 5, 2015
 */
public class CustomerStatusVo extends BaseVo {
    private static final long serialVersionUID = 8920933397621053161L;

    private Date period;
    private Long setups;
    private Long activations;
    private Long shipmentCount;
    private Double revenueExcGst;
    private Double revenueIncGst;
    private Double marginExcGst;
    private Double marginIncGst;

    @Override
    public String toString() {
        return "CustomerStatusVo [period=" + period + ", setups=" + setups + ", activations=" + activations + ", shipmentCount=" + shipmentCount + ", revenueExcGst=" + revenueExcGst + ", revenueIncGst=" + revenueIncGst + ", marginExcGst=" + marginExcGst + ", marginIncGst=" + marginIncGst + "]";
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getSetups() {
        return setups;
    }

    public void setSetups(Long setups) {
        this.setups = setups;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getActivations() {
        return activations;
    }

    public void setActivations(Long activations) {
        this.activations = activations;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getShipmentCount() {
        return shipmentCount;
    }

    public void setShipmentCount(Long shipmentCount) {
        this.shipmentCount = shipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRevenueExcGst() {
        return revenueExcGst;
    }

    public void setRevenueExcGst(Double revenueExcGst) {
        this.revenueExcGst = revenueExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRevenueIncGst() {
        return revenueIncGst;
    }

    public void setRevenueIncGst(Double revenueIncGst) {
        this.revenueIncGst = revenueIncGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginExcGst() {
        return marginExcGst;
    }

    public void setMarginExcGst(Double marginExcGst) {
        this.marginExcGst = marginExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginIncGst() {
        return marginIncGst;
    }

    public void setMarginIncGst(Double marginIncGst) {
        this.marginIncGst = marginIncGst;
    }
}
