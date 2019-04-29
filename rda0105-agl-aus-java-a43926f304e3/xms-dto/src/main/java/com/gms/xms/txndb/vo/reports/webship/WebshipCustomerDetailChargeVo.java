package com.gms.xms.txndb.vo.reports.webship;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from WebshipCustomerDetailChargeVo
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailChargeVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String description;
    private Double customerCost;
    private Double franchiseCost;
    private Double margin;
    private Boolean isBaseCharge;

    @Override
    public String toString() {
        return "WebshipCustomerDetailChargeVo [description=" + description + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", margin=" + margin + ", isBaseCharge=" + isBaseCharge + "]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(Double customerCost) {
        this.customerCost = customerCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(Double franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Boolean getIsBaseCharge() {
        return isBaseCharge;
    }

    public void setIsBaseCharge(Boolean isBaseCharge) {
        this.isBaseCharge = isBaseCharge;
    }
}
