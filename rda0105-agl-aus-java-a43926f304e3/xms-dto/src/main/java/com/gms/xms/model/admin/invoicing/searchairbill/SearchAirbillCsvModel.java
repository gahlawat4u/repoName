package com.gms.xms.model.admin.invoicing.searchairbill;

public class SearchAirbillCsvModel extends SearchAirbillModel {
    private static final long serialVersionUID = -8948170498207085984L;

    private String customerBaseCharge;
    private String franchiseBaseCharge;
    private String carrierBaseCharge;
    private String customerCreditTotal;
    private String carrierCreditTotal;

    public String getCustomerBaseCharge() {
        return customerBaseCharge;
    }

    public void setCustomerBaseCharge(String customerBaseCharge) {
        this.customerBaseCharge = customerBaseCharge;
    }

    public String getFranchiseBaseCharge() {
        return franchiseBaseCharge;
    }

    public void setFranchiseBaseCharge(String franchiseBaseCharge) {
        this.franchiseBaseCharge = franchiseBaseCharge;
    }

    public String getCarrierBaseCharge() {
        return carrierBaseCharge;
    }

    public void setCarrierBaseCharge(String carrierBaseCharge) {
        this.carrierBaseCharge = carrierBaseCharge;
    }

    public String getCustomerCreditTotal() {
        return customerCreditTotal;
    }

    public void setCustomerCreditTotal(String customerCreditTotal) {
        this.customerCreditTotal = customerCreditTotal;
    }

    public String getCarrierCreditTotal() {
        return carrierCreditTotal;
    }

    public void setCarrierCreditTotal(String carrierCreditTotal) {
        this.carrierCreditTotal = carrierCreditTotal;
    }

    @Override
    public String toString() {
        return "SearchAirbillCsvModel [customerBaseCharge=" + customerBaseCharge + ", franchiseBaseCharge=" + franchiseBaseCharge + ", carrierBaseCharge=" + carrierBaseCharge + ", customerCreditTotal=" + customerCreditTotal + ", carrierCreditTotal=" + carrierCreditTotal + "]";
    }
}
