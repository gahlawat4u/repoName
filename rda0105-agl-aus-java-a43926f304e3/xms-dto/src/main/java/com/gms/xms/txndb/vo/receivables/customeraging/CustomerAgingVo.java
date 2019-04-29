package com.gms.xms.txndb.vo.receivables.customeraging;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonInteger2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from CustomerAgingVo
 * <p>
 * Author DatTV Date Aug 10, 2015 10:58:12 AM
 */
public class CustomerAgingVo extends BaseVo {

    private static final long serialVersionUID = 4509736972412468979L;

    private String customerName;
    private String customerCode;
    private String salesRepName;
    private Double totalDue;
    private Double totalOverdue;
    private Double range0;
    private Double range1To15;
    private Double range16To30;
    private Double range31To45;
    private Double range46To60;
    private Double range61To90;
    private Double range91To120;
    private Double range120;
    private Double avgInvoiceAge;
    private Integer maxInvoiceAge;
    private Double avgDaysOverdue;
    private Integer maxDaysOverdue;
    private String unpaidInvoices;
    private Double totalOverpaid;
    private String terms;
    private Double avgDaysToPay;

    @Override
    public String toString() {
        return "CustomerAgingVo [customerName=" + customerName + ", customerCode=" + customerCode + ", salesRepName=" + salesRepName + ", totalDue=" + totalDue + ", totalOverdue=" + totalOverdue + ", range0=" + range0 + ", range1To15=" + range1To15 + ", range16To30=" + range16To30 + ", range31To45=" + range31To45 + ", range46To60=" + range46To60 + ", range61To90=" + range61To90 + ", range91To120=" + range91To120 + ", range120=" + range120 + ", avgInvoiceAge=" + avgInvoiceAge + ", maxInvoiceAge="
                + maxInvoiceAge + ", avgDaysOverdue=" + avgDaysOverdue + ", maxDaysOverdue=" + maxDaysOverdue + ", unpaidInvoices=" + unpaidInvoices + ", totalOverpaid=" + totalOverpaid + ", terms=" + terms + ", avgDaysToPay=" + avgDaysToPay + "]";
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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(Double totalDue) {
        this.totalDue = totalDue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalOverdue() {
        return totalOverdue;
    }

    public void setTotalOverdue(Double totalOverdue) {
        this.totalOverdue = totalOverdue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRange0() {
        return range0;
    }

    public void setRange0(Double range0) {
        this.range0 = range0;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRange1To15() {
        return range1To15;
    }

    public void setRange1To15(Double range1To15) {
        this.range1To15 = range1To15;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRange16To30() {
        return range16To30;
    }

    public void setRange16To30(Double range16To30) {
        this.range16To30 = range16To30;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRange31To45() {
        return range31To45;
    }

    public void setRange31To45(Double range31To45) {
        this.range31To45 = range31To45;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRange46To60() {
        return range46To60;
    }

    public void setRange46To60(Double range46To60) {
        this.range46To60 = range46To60;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRange61To90() {
        return range61To90;
    }

    public void setRange61To90(Double range61To90) {
        this.range61To90 = range61To90;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRange91To120() {
        return range91To120;
    }

    public void setRange91To120(Double range91To120) {
        this.range91To120 = range91To120;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRange120() {
        return range120;
    }

    public void setRange120(Double range120) {
        this.range120 = range120;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAvgInvoiceAge() {
        return avgInvoiceAge;
    }

    public void setAvgInvoiceAge(Double avgInvoiceAge) {
        this.avgInvoiceAge = avgInvoiceAge;
    }

    @JsonSerialize(using = JsonInteger2StringSerializer.class)
    public Integer getMaxInvoiceAge() {
        return maxInvoiceAge;
    }

    public void setMaxInvoiceAge(Integer maxInvoiceAge) {
        this.maxInvoiceAge = maxInvoiceAge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAvgDaysOverdue() {
        return avgDaysOverdue;
    }

    public void setAvgDaysOverdue(Double avgDaysOverdue) {
        this.avgDaysOverdue = avgDaysOverdue;
    }

    @JsonSerialize(using = JsonInteger2StringSerializer.class)
    public Integer getMaxDaysOverdue() {
        return maxDaysOverdue;
    }

    public void setMaxDaysOverdue(Integer maxDaysOverdue) {
        this.maxDaysOverdue = maxDaysOverdue;
    }

    public String getUnpaidInvoices() {
        return unpaidInvoices;
    }

    public void setUnpaidInvoices(String unpaidInvoices) {
        this.unpaidInvoices = unpaidInvoices;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalOverpaid() {
        return totalOverpaid;
    }

    public void setTotalOverpaid(Double totalOverpaid) {
        this.totalOverpaid = totalOverpaid;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAvgDaysToPay() {
        return avgDaysToPay;
    }

    public void setAvgDaysToPay(Double avgDaysToPay) {
        this.avgDaysToPay = avgDaysToPay;
    }
}
