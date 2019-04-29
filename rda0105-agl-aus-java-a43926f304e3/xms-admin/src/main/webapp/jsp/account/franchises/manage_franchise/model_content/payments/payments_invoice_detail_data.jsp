<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="col-lg-4">
    <table class="table" style="font-size: 11px;">
        <tr>
            <td colspan="2" style="border-top: 0px !important">
                <div class="caption b17">
                    <xms:localization text="Invoice Detail"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="No"/> :</td>
            <td class="td2"><s:property value="invoiceDetail.invoiceCode"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Amount"/> :</td>
            <td class="td2"><s:property value="currencySymbol"/>
                <s:property value="invoiceDetail.total"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Paid"/> :</td>
            <td class="td2" data-detail-paid=""><s:property value="currencySymbol"/>
                <s:property value="invoiceDetail.customerPaid"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Credit"/> :</td>
            <td class="td2"><s:property value="currencySymbol"/>
                <s:property value="invoiceDetail.creditTotal"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Bad Debt"/> :</td>
            <td class="td2"><s:property value="currencySymbol"/>
                <s:property value="invoiceDetail.badDebt"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Satisfaction Credit"/> :</td>
            <td class="td2"><s:property value="currencySymbol"/>
                <s:property value="invoiceDetail.satisfactionCredit"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Owed"/> :</td>
            <td class="td2" data-detail-owed=""><s:property value="currencySymbol"/>
                <s:property value="invoiceDetail.owed"/></td>
        </tr>
    </table>
    <s:hidden id="total-paid-credit" value="%{invoiceDetail.totalPaidCredit}"/>
</div>
<div class="col-lg-4">
    <table class="table" style="font-size: 11px;">
        <tr>
            <td colspan="2" style="border-top: 0px !important">
                <div class="caption b17">&nbsp;</div>
            </td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Date"/> :</td>
            <td class="td2"><s:property value="invoiceDetail.invoiceDate"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Due Date"/> :</td>
            <td class="td2"><s:property value="invoiceDetail.dueDate"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Days Over"/> :</td>
            <td class="td2"><s:property value="invoiceDetail.overDue"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Airbills"/> :</td>
            <td class="td2"><s:property value="invoiceDetail.airbillCount"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Margin(Excl. Tax)"/> :</td>
            <td class="td2"><s:property value="currencySymbol"/>
                <s:property value="invoiceDetail.marginExcTax"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Late Fee"/> :</td>
            <td class="td2"><s:property value="currencySymbol"/>
                <s:property value="invoiceDetail.lateFee"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Paid Late Fee"/> :</td>
            <td class="td2"><s:property value="currencySymbol"/>
                <s:property value="invoiceDetail.paidLateFee"/></td>
        </tr>
    </table>
</div>
