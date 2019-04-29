package com.gms.xms.model;

import com.gms.xms.model.franchisepayable.ShipmentInvoiceModel;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * Posted from InvoiceModel
 * <p>
 * Author DatTV Date Mar 30, 2015
 */
public class InvoiceModel extends BaseModel {

    private static final long serialVersionUID = -3971364737657619674L;

    private String invoiceId;

    private String invoiceCode;

    private String customerCode;

    private String invoiceDate;

    private String lateFee;

    private String status;

    private String paid;

    private String invCreateDate;

    private String totalAmount;

    private String totalPayment;

    private String totalAwbPayment;

    private String unreconcileAmount;

    private String dueDate;

    @JsonIgnore
    private String page;

    @JsonIgnore
    private String pageSize;

    private String remainningBalance;

    private String payment;

    private String awbLevel;

    private String appliedAmount;

    private String unappliedAmount;

    private List<ShipmentInvoiceModel> shipmentInvoices;

    @Override
    public String toString() {
        return "InvoiceModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", invoiceDate=" + invoiceDate + ", lateFee=" + lateFee + ", status=" + status + ", paid=" + paid + ", invCreateDate=" + invCreateDate + ", totalAmount=" + totalAmount + ", totalPayment=" + totalPayment + ", totalAwbPayment=" + totalAwbPayment + ", unreconcileAmount=" + unreconcileAmount + ", dueDate=" + dueDate + ", page=" + page + ", pageSize=" + pageSize
                + ", remainningBalance=" + remainningBalance + ", payment=" + payment + ", awbLevel=" + awbLevel + ", appliedAmount=" + appliedAmount + ", unappliedAmount=" + unappliedAmount + ", shipmentInvoices=" + shipmentInvoices + "]";
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getLateFee() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getInvCreateDate() {
        return invCreateDate;
    }

    public void setInvCreateDate(String invCreateDate) {
        this.invCreateDate = invCreateDate;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getRemainningBalance() {
        return remainningBalance;
    }

    public void setRemainningBalance(String remainningBalance) {
        this.remainningBalance = remainningBalance;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAwbLevel() {
        return awbLevel;
    }

    public void setAwbLevel(String awbLevel) {
        this.awbLevel = awbLevel;
    }

    public List<ShipmentInvoiceModel> getShipmentInvoices() {
        return shipmentInvoices;
    }

    public void setShipmentInvoices(List<ShipmentInvoiceModel> shipmentInvoices) {
        this.shipmentInvoices = shipmentInvoices;
    }

    public String getUnreconcileAmount() {
        return unreconcileAmount;
    }

    public void setUnreconcileAmount(String unreconcileAmount) {
        this.unreconcileAmount = unreconcileAmount;
    }

    public String getTotalAwbPayment() {
        return totalAwbPayment;
    }

    public void setTotalAwbPayment(String totalAwbPayment) {
        this.totalAwbPayment = totalAwbPayment;
    }

    public String getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(String appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public String getUnappliedAmount() {
        return unappliedAmount;
    }

    public void setUnappliedAmount(String unappliedAmount) {
        this.unappliedAmount = unappliedAmount;
    }
}