<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <table class="table table-bordered mg0" id="payment_report_table">
        <thead>
        <tr>
            <th><xms:localization text="Amount"/></th>
            <th><xms:localization text="Cheque #"/></th>
            <th data-group="payment-date"><xms:localization text="Date"/></th>
            <th data-group="customer-code"><xms:localization text="Customer #"/></th>
            <th data-group="customer-name"><xms:localization text="Customer Name"/></th>
            <th data-group="invoices"><xms:localization text="Invoice(s)"/></th>
            <th data-group="note"><xms:localization text="Notes"/></th>
            <th data-group="over-payment-type"><xms:localization text="Types of Overpayment"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="report==null || report.totalRecords==0">
            <tr>
                <td colspan="8"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="report.records">
                <tr>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="amount"/></td>
                    <td><s:property value="cheque"/></td>
                    <td data-group="payment-date"><s:property value="paymentDate"/></td>
                    <td data-group="customer-code"><s:property value="customerCode"/></td>
                    <td data-group="customer-name"><s:property value="customerName"/></td>
                    <td data-group="invoices"><s:property value="invoiceList"/></td>
                    <td data-group="note"><s:property value="note"/></td>
                    <td data-group="over-payment-type"><s:property value="overPaymentType"/></td>
                </tr>
            </s:iterator>
            <tr>
                <th colspan="8"><xms:localization text="Showing"/> <s:property value="report.startRecord"/>
                    <xms:localization text="to"/> <s:property value="report.endRecord"/> <xms:localization text="of"/>
                    <s:property value="report.totalRecords"/></th>
            </tr>
        </s:else>
        <tr>
            <td colspan="8"><span class="b4"> <b><xms:localization text="Total: "/></b> <s:property
                    value="currencySymbol"/> <s:property value="summary.total"/>
				</span> <span class="b4"> <b>| <xms:localization text="Total Payment Received: "/></b> <s:property
                    value="currencySymbol"/> <s:property value="summary.totalReceived"/>
				</span></td>
        </tr>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate">
    <s:if test="report!=null">
        <s:if test="report.hasPrev()">
            <a href="javascript:changePage(<s:property value="%{report.currentPage - 1}"/>)"
               class="paginate_button previous"><xms:localization text="Previous"/></a>
        </s:if>
		<span> <s:iterator value="report.pageRange" status="count">
            <s:if test="%{report.pageRange[#count.index] == report.currentPage}">
                <a class="paginate_button current"><s:property value="report.currentPage"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
            </s:else>
        </s:iterator>
		</span>
        <s:if test="report.hasNext()">
            <a class="paginate_button next" href="javascript:changePage(<s:property value="%{report.currentPage+1}"/>)"><xms:localization
                    text="Next"/></a>
        </s:if>
    </s:if>
</div>

<script type="text/javascript">
    var fieldList = ["amount", "cheque", "payment_date", "customer_code", "customer_name", "invoice_list", "note", "over_payment_type"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#payment_report_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
        showHideColumns();
    });
    <s:if test="report==null || report.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

</script>