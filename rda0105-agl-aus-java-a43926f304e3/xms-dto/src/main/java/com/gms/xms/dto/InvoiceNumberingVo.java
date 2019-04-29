package com.gms.xms.dto;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Aug 5, 2016 5:20:45 PM
 * <p>
 * Author dattrinh
 */
public class InvoiceNumberingVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer month;
    private Integer year;
    private Integer currentUniqueNumber;
    private String franchiseCode;
    private String invoiceCode;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCurrentUniqueNumber() {
        return currentUniqueNumber;
    }

    public void setCurrentUniqueNumber(Integer currentUniqueNumber) {
        this.currentUniqueNumber = currentUniqueNumber;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    @Override
    public String toString() {
        return "InvoiceNumberingVo [month=" + month + ", year=" + year + ", currentUniqueNumber=" + currentUniqueNumber + ", franchiseCode=" + franchiseCode + ", invoiceCode=" + invoiceCode + "]";
    }
}
