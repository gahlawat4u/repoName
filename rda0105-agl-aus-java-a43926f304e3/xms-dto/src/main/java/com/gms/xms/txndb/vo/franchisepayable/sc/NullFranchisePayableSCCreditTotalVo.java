package com.gms.xms.txndb.vo.franchisepayable.sc;

/**
 * Posted from NullFranchisePayableSCCreditTotalVo
 * <p>
 * Author DatTV Dec 10, 2015
 */
public class NullFranchisePayableSCCreditTotalVo extends FranchisePayableSCCreditTotalVo {

    private static final long serialVersionUID = 1L;

    public NullFranchisePayableSCCreditTotalVo() {
        super();
        this.setAmountOutstanding(0.0);
        this.setCustomerTotalGst(0.0);
        this.setCustomerTotalExcGst(0.0);
        this.setFranchiseCostGst(0.0);
        this.setFranchiseCostExcGst(0.0);
        this.setGrossMarginGst(0.0);
        this.setGrossMarginExcGst(0.0);
        this.setPreviouslyPaid(0.0);
        this.setPaymentsReceived(0.0);
        this.setProfitShareGst(0.0);
        this.setProfitShareExcGst(0.0);
        this.setTotalProfitShare(0.0);
        this.setNewMarginExcGst(0.0);
        this.setNewMarginGst(0.0);
        this.setCreditsFranchiseCostExcGst(0.0);
        this.setCreditsFranchiseCostGst(0.0);
        this.setCreditsCustomerCostExcGst(0.0);
        this.setCreditsCustomerCostGst(0.0);
        this.setTaxableShipmentCount(0L);
        this.setNonTaxableShipmentCount(0L);
    }
}
