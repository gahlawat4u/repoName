package com.gms.xms.txndb.vo.franchisepayable.sc;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from FranchisePayableSCTechFeeVo
 * <p>
 * Author DatTV Dec 9, 2015
 */
public class FranchisePayableSCTechFeeVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private Date importDate;
    private Long customerCode;
    private String customerName;
    private String invoiceCode;
    private String airbillNumber;
    private Boolean isDomestic;
    private Double intlShipmentFee;
    private Double domShipmentFee;

    @Override
    public String toString() {
        return "FranchisePayableSCTechFeeDetailVo [rptTxnId=" + rptTxnId + ", importDate=" + importDate + ", customerCode=" + customerCode + ", customerName=" + customerName + ", invoiceCode=" + invoiceCode + ", airbillNumber=" + airbillNumber + ", isDomestic=" + isDomestic + ", intlShipmentFee=" + intlShipmentFee + ", domShipmentFee=" + domShipmentFee + "]";
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
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

    public Boolean getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Boolean isDomestic) {
        this.isDomestic = isDomestic;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getIntlShipmentFee() {
        return intlShipmentFee;
    }

    public void setIntlShipmentFee(Double intlShipmentFee) {
        this.intlShipmentFee = intlShipmentFee;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDomShipmentFee() {
        return domShipmentFee;
    }

    public void setDomShipmentFee(Double domShipmentFee) {
        this.domShipmentFee = domShipmentFee;
    }
}
