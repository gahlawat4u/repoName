package com.gms.xms.txndb.vo.invoicing.searchairbill;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * File Name: TotalSearchAirbillVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 29-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing.searchairbill <br/>
 */
public class TotalSearchAirbillVo extends BaseVo {

    private static final long serialVersionUID = -4752187808087993430L;
    private Integer totalAirbills;
    private Double totalCustomerAmount;
    private Double totalFranchiseAmount;
    private Double totalMargin;

    public Integer getTotalAirbills() {
        return totalAirbills;
    }

    public void setTotalAirbills(Integer totalAirbills) {
        this.totalAirbills = totalAirbills;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalCustomerAmount() {
        return totalCustomerAmount;
    }

    public void setTotalCustomerAmount(Double totalCustomerAmount) {
        this.totalCustomerAmount = totalCustomerAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalFranchiseAmount() {
        return totalFranchiseAmount;
    }

    public void setTotalFranchiseAmount(Double totalFranchiseAmount) {
        this.totalFranchiseAmount = totalFranchiseAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalMargin() {
        return totalMargin;
    }

    public void setTotalMargin(Double totalMargin) {
        this.totalMargin = totalMargin;
    }

    @Override
    public String toString() {
        return "TotalSearchAirbillVo [totalAirbills=" + totalAirbills + ", totalCustomerAmount=" + totalCustomerAmount + ", totalFranchiseAmount=" + totalFranchiseAmount + ", totalMargin=" + totalMargin + "]";
    }

}
