package com.gms.xms.txndb.vo.invoicing.searchairbill;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class SearchAirbillCsvVo extends SearchAirbillVo {
    private static final long serialVersionUID = -8948170498207085984L;

    private Double customerBaseCharge;
    private Double franchiseBaseCharge;
    private Double carrierBaseCharge;
    private Double customerCreditTotal;
    private Double carrierCreditTotal;

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerBaseCharge() {
        return customerBaseCharge;
    }

    public void setCustomerBaseCharge(Double customerBaseCharge) {
        this.customerBaseCharge = customerBaseCharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseBaseCharge() {
        return franchiseBaseCharge;
    }

    public void setFranchiseBaseCharge(Double franchiseBaseCharge) {
        this.franchiseBaseCharge = franchiseBaseCharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierBaseCharge() {
        return carrierBaseCharge;
    }

    public void setCarrierBaseCharge(Double carrierBaseCharge) {
        this.carrierBaseCharge = carrierBaseCharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerCreditTotal() {
        return customerCreditTotal;
    }

    public void setCustomerCreditTotal(Double customerCreditTotal) {
        this.customerCreditTotal = customerCreditTotal;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCreditTotal() {
        return carrierCreditTotal;
    }

    public void setCarrierCreditTotal(Double carrierCreditTotal) {
        this.carrierCreditTotal = carrierCreditTotal;
    }

    @Override
    public String toString() {
        return "SearchAirbillCsvVo [customerBaseCharge=" + customerBaseCharge + ", franchiseBaseCharge=" + franchiseBaseCharge + ", carrierBaseCharge=" + carrierBaseCharge + ", customerCreditTotal=" + customerCreditTotal + ", carrierCreditTotal=" + carrierCreditTotal + "]";
    }
}
