<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<span style="display: none" id='inv_<s:property value="invoice.invoiceCode" />'><s:property
        value="invoice.invoiceCode"/></span>
<table class="table" style="font-size: 11px; margin-bottom: 0px">
    <tr>
        <td class="td1"><xms:localization text="Invoice"/></td>
        <td class="td2"><s:property value="invoice.invoiceCode"/></td>
        <td class="td1"><xms:localization text="Total Unreconciled Amount"/></td>
        <td class="td2" align="right"><s:property value="invoice.unreconcileAmount"/></td>
    </tr>
    <tr>
        <td class="td1"><xms:localization text="Invoice Note"/></td>
        <td class="td2"></td>
        <td class="td1"><xms:localization text="Amount Applied"/></td>
        <td class="td2" align="right"><s:property value="invoice.appliedAmount"/></td>
    </tr>
    <tr>
        <td class="td1"><xms:localization text="Previously Reconciled"/></td>
        <td class="td2" align="right"><s:property value="invoice.totalAwbPayment"/></td>
        <td class="td1"><xms:localization text="Total Unpaid on invoice"/></td>
        <td class="td2" align="right"><s:property value="invoice.remainningBalance"/></td>
    </tr>
    <tr>
        <td class="td1"><xms:localization text="Remaining Unapplied Amount"/></td>
        <td class="td2" align="right"><s:property value="invoice.unappliedAmount"/></td>
        <td class="td2"></td>
        <td class="td2"></td>
    </tr>
</table>
<br/>
<table class="table table-hover table-bordeed mg0" id="datatable1">
    <thead>
    <tr>
        <th><xms:localization text="Airbill"/></th>
        <th><xms:localization text="Customer Total"/></th>
        <th><xms:localization text="Previously Paid"/></th>
        <th><xms:localization text="Adjustments Credits"/></th>
        <th><xms:localization text="Amount Due"/></th>
        <th><xms:localization text="Pending Adjustments"/></th>
        <th><xms:localization text="Denied Adjustments"/></th>
        <th><xms:localization text="Payments"/></th>
        <th><i class="fa fa-times-circle-o s10" style="font-size: 18px;"></i></th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="invoice.shipmentInvoices" status="awbrow">
        <input type="hidden" value='<s:property value="invoiceId" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].invoiceId"/>
        <input type="hidden" value='<s:property value="shipmentId" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].shipmentId"/>
        <input type="hidden" value='<s:property value="airbillNumber" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].airbillNumber"/>
        <input type="hidden" value='<s:property value="cusPaymentId" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].cusPaymentId"/>
        <input type="hidden" value='<s:property value="paidCustomerCost" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].paidCustomerCost"/>
        <input type="hidden" value='<s:property value="paidCarrierCost" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].paidCarrierCost"/>
        <input type="hidden" value='<s:property value="invoicePaymentId" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].invoicePaymentId"/>
        <input type="hidden" value='<s:property value="totalAmount" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].totalAmount"/>
        <input type="hidden" value='<s:property value="totalPayment" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].totalPayment"/>
        <input type="hidden" value='<s:property value="amountDue" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].amountDue"/>
        <input type="hidden" value='<s:property value="adjustmentCredit" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].adjustmentCredit"/>
        <input type="hidden" value='<s:property value="pendingAdjustment" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].pendingAdjustment"/>
        <input type="hidden" value='<s:property value="deniedAdjustment" />'
               name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].deniedAdjustment"/>
        <tr <s:if test="%{amountDue==0}">style="display: none"</s:if>>
            <td><s:property value="airbillNumber"/></td>
            <td align="right"><s:property value="totalAmount"/></td>
            <td align="right"><s:property value="totalPayment"/></td>
            <td align="right"><s:property value="adjustmentCredit"/></td>
            <td align="right"><s:property value="amountDue"/></td>
            <td align="right"><s:property value="pendingAdjustment"/></td>
            <td align="right"><s:property value="deniedAdjustment"/></td>
            <td align="right"><input type="text" value='<s:property value="payment" />'
                                     name="invoiceList[<s:property value="invoiceIndex" />].shipmentInvoices[<s:property value="%{#awbrow.index}" />].payment"
                                     class="form-control s50"/></td>
            <td><i class="fa fa-times-circle-o s10" style="font-size: 18px;"></i></td>
        </tr>
    </s:iterator>
    </tbody>
</table>
