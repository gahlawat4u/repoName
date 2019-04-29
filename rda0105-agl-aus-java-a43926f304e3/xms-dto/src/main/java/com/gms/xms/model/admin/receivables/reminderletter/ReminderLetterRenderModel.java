package com.gms.xms.model.admin.receivables.reminderletter;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.statement.StatementInvoiceModel;
import com.gms.xms.model.receivables.customeraging.CustomerAgingModel;
import com.gms.xms.txndb.model.admin.receivables.reminderletter.ReminderInvoiceDetailModel;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementCustomerBillingAddressVo;

import java.util.List;

/**
 * Posted from Apr 1, 2016 9:47:52 AM
 * <p>
 * Author dattrinh
 */
public class ReminderLetterRenderModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String currentDate;
    private CustomerAgingModel customerAging;
    private String companyAddress;
    private String mailPaymentTo;
    private StatementCustomerBillingAddressVo billingAddressVo;
    private String siteAddress;
    private List<StatementInvoiceModel> invoices;
    private StatementInvoiceModel invoiceTotal;
    private ReminderInvoiceDetailModel invoiceDetail;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public CustomerAgingModel getCustomerAging() {
        return customerAging;
    }

    public void setCustomerAging(CustomerAgingModel customerAging) {
        this.customerAging = customerAging;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getMailPaymentTo() {
        return mailPaymentTo;
    }

    public void setMailPaymentTo(String mailPaymentTo) {
        this.mailPaymentTo = mailPaymentTo;
    }

    public StatementCustomerBillingAddressVo getBillingAddressVo() {
        return billingAddressVo;
    }

    public void setBillingAddressVo(StatementCustomerBillingAddressVo billingAddressVo) {
        this.billingAddressVo = billingAddressVo;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public List<StatementInvoiceModel> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<StatementInvoiceModel> invoices) {
        this.invoices = invoices;
    }

    public StatementInvoiceModel getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(StatementInvoiceModel invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public ReminderInvoiceDetailModel getInvoiceDetail() {
        return invoiceDetail;
    }

    public void setInvoiceDetail(ReminderInvoiceDetailModel invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }

    @Override
    public String toString() {
        return "ReminderLetterRenderModel [currentDate=" + currentDate + ", customerAging=" + customerAging + ", companyAddress=" + companyAddress + ", mailPaymentTo=" + mailPaymentTo + ", billingAddressVo=" + billingAddressVo + ", siteAddress=" + siteAddress + ", invoices=" + invoices + ", invoiceTotal=" + invoiceTotal + ", invoiceDetail=" + invoiceDetail + "]";
    }
}
