package com.gms.xms.txndb.vo.franchisepayable.sc;

/**
 * Posted from NullFranchisePayableSCShipmentVo
 * <p>
 * Author DatTV Dec 10, 2015
 */
public class NullFranchisePayableSCShipmentVo extends FranchisePayableSCShipmentVo {

    private static final long serialVersionUID = 1L;

    public NullFranchisePayableSCShipmentVo() {
        super();
        this.setCustCost(0.0);
        this.setCustTax(0.0);
        this.setCustMarginable(0.0);
        this.setCustMarginableTax(0.0);
        this.setFranCost(0.0);
        this.setFranTax(0.0);
        this.setGrossMargin(0.0);
        this.setGrossMarginTax(0.0);
        this.setManagementFee(0.0);
        this.setMarketingFee(0.0);
        this.setProfitShare(0.0);
        this.setFranCredit(0.0);
        this.setCustCredit(0.0);
    }
}
