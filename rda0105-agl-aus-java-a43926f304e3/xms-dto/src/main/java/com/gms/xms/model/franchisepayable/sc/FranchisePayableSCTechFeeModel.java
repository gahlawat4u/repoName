package com.gms.xms.model.franchisepayable.sc;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableSCTechFeeModel
 * <p>
 * Author DatTV Dec 9, 2015
 */
public class FranchisePayableSCTechFeeModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private String importDate;
    private String customerCode;
    private String customerName;
    private String invoiceCode;
    private String airbillNumber;
    private String isDomestic;
    private String intlShipmentFee;
    private String domShipmentFee;

    @Override
    public String toString() {
        return "FranchisePayableSCTechFeeDetailModel [rptTxnId=" + rptTxnId + ", importDate=" + importDate + ", customerCode=" + customerCode + ", customerName=" + customerName + ", invoiceCode=" + invoiceCode + ", airbillNumber=" + airbillNumber + ", isDomestic=" + isDomestic + ", intlShipmentFee=" + intlShipmentFee + ", domShipmentFee=" + domShipmentFee + "]";
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(String isDomestic) {
        this.isDomestic = isDomestic;
    }

    public String getIntlShipmentFee() {
        return intlShipmentFee;
    }

    public void setIntlShipmentFee(String intlShipmentFee) {
        this.intlShipmentFee = intlShipmentFee;
    }

    public String getDomShipmentFee() {
        return domShipmentFee;
    }

    public void setDomShipmentFee(String domShipmentFee) {
        this.domShipmentFee = domShipmentFee;
    }
}
