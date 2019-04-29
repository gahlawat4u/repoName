package com.gms.xms.model.receivables.customeraging;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerAgingVo
 * <p>
 * Author DatTV Date Aug 10, 2015 10:58:12 AM
 */
public class CustomerAgingModel extends BaseModel {

    private static final long serialVersionUID = 4509736972412468979L;

    private String customerName;
    private String customerCode;
    private String salesRepName;
    private String totalDue;
    private String totalOverdue;
    private String range0;
    private String range1To15;
    private String range16To30;
    private String range31To45;
    private String range46To60;
    private String range61To90;
    private String range91To120;
    private String range120;
    private String avgInvoiceAge;
    private String maxInvoiceAge;
    private String avgDaysOverdue;
    private String maxDaysOverdue;
    private String unpaidInvoices;
    private String totalOverpaid;
    private String terms;
    private String avgDaysToPay;

    @Override
    public String toString() {
        return "CustomerAgingModel [customerName=" + customerName + ", customerCode=" + customerCode + ", salesRepName=" + salesRepName + ", totalDue=" + totalDue + ", totalOverdue=" + totalOverdue + ", range0=" + range0 + ", range1To15=" + range1To15 + ", range16To30=" + range16To30 + ", range31To45=" + range31To45 + ", range46To60=" + range46To60 + ", range61To90=" + range61To90 + ", range91To120=" + range91To120 + ", range120=" + range120 + ", avgInvoiceAge=" + avgInvoiceAge
                + ", maxInvoiceAge=" + maxInvoiceAge + ", avgDaysOverdue=" + avgDaysOverdue + ", maxDaysOverdue=" + maxDaysOverdue + ", unpaidInvoices=" + unpaidInvoices + ", totalOverpaid=" + totalOverpaid + ", terms=" + terms + ", avgDaysToPay=" + avgDaysToPay + "]";
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getSalesRepName() {
        return salesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }

    public String getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(String totalDue) {
        this.totalDue = totalDue;
    }

    public String getTotalOverdue() {
        return totalOverdue;
    }

    public void setTotalOverdue(String totalOverdue) {
        this.totalOverdue = totalOverdue;
    }

    public String getRange0() {
        return range0;
    }

    public void setRange0(String range0) {
        this.range0 = range0;
    }

    public String getRange1To15() {
        return range1To15;
    }

    public void setRange1To15(String range1To15) {
        this.range1To15 = range1To15;
    }

    public String getRange16To30() {
        return range16To30;
    }

    public void setRange16To30(String range16To30) {
        this.range16To30 = range16To30;
    }

    public String getRange31To45() {
        return range31To45;
    }

    public void setRange31To45(String range31To45) {
        this.range31To45 = range31To45;
    }

    public String getRange46To60() {
        return range46To60;
    }

    public void setRange46To60(String range46To60) {
        this.range46To60 = range46To60;
    }

    public String getRange61To90() {
        return range61To90;
    }

    public void setRange61To90(String range61To90) {
        this.range61To90 = range61To90;
    }

    public String getRange91To120() {
        return range91To120;
    }

    public void setRange91To120(String range91To120) {
        this.range91To120 = range91To120;
    }

    public String getRange120() {
        return range120;
    }

    public void setRange120(String range120) {
        this.range120 = range120;
    }

    public String getAvgInvoiceAge() {
        return avgInvoiceAge;
    }

    public void setAvgInvoiceAge(String avgInvoiceAge) {
        this.avgInvoiceAge = avgInvoiceAge;
    }

    public String getMaxInvoiceAge() {
        return maxInvoiceAge;
    }

    public void setMaxInvoiceAge(String maxInvoiceAge) {
        this.maxInvoiceAge = maxInvoiceAge;
    }

    public String getAvgDaysOverdue() {
        return avgDaysOverdue;
    }

    public void setAvgDaysOverdue(String avgDaysOverdue) {
        this.avgDaysOverdue = avgDaysOverdue;
    }

    public String getMaxDaysOverdue() {
        return maxDaysOverdue;
    }

    public void setMaxDaysOverdue(String maxDaysOverdue) {
        this.maxDaysOverdue = maxDaysOverdue;
    }

    public String getUnpaidInvoices() {
        return unpaidInvoices;
    }

    public void setUnpaidInvoices(String unpaidInvoices) {
        this.unpaidInvoices = unpaidInvoices;
    }

    public String getTotalOverpaid() {
        return totalOverpaid;
    }

    public void setTotalOverpaid(String totalOverpaid) {
        this.totalOverpaid = totalOverpaid;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getAvgDaysToPay() {
        return avgDaysToPay;
    }

    public void setAvgDaysToPay(String avgDaysToPay) {
        this.avgDaysToPay = avgDaysToPay;
    }
}
