<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<input id="invoiceRptTxnId" type="hidden" value="<s:property value="rptTxnId" />"/>
<input id="invoicePage" type="hidden" value="<s:property value="page" />"/>
<input id="invoiceOrderField" type="hidden" value="<s:property value="orderField" />"/>
<input id="invoiceOrderType" type="hidden" value="<s:property value="orderType" />"/>
<table id="customer-invoice-detail-table" class="table table-bordered mg0 table-hover">
    <thead>
    <tr>
        <th><xms:localization text="Customer Inv #"/></th>
        <th><xms:localization text="Customer"/></th>
        <th><xms:localization text="Invoice Date"/></th>
        <th><xms:localization text="Invoice Amount"/></th>
        <th><xms:localization text="Invoice Credit"/></th>
        <th><xms:localization text="Net Amount"/></th>
        <th><xms:localization text="GST"/></th>
        <th><xms:localization text="GST Credits"/></th>
        <th><xms:localization text="Net GST"/></th>
        <th><xms:localization text="Total"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="invoiceDetailReport==null || invoiceDetailReport.totalRecords==0">
        <tr>
            <td colspan="10"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="invoiceDetailReport.records">
            <tr>
                <td><s:property value="invoiceCode"/></td>
                <td><s:property value="customerName"/></td>
                <td><s:property value="invoiceDate"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="invoiceAmount"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="invoiceCredit"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="netAmount"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="gst"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="creditGst"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="netGst"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="total"/></td>
            </tr>
        </s:iterator>
        <s:if test="invoiceDetailSummary!=null">
            <tr>
                <th colspan="3" class="text-right"><xms:localization text="Total"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceDetailSummary.invoiceAmount"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceDetailSummary.invoiceCredit"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceDetailSummary.netAmount"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceDetailSummary.gst"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceDetailSummary.creditGst"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceDetailSummary.netGst"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceDetailSummary.total"/></th>
            </tr>
        </s:if>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="invoiceDetailReport.startRecord"/>
                <xms:localization text="to"/> <s:property value="invoiceDetailReport.endRecord"/> <xms:localization
                        text="of"/> <s:property value="invoiceDetailReport.totalRecords"/> <xms:localization
                        text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="invoiceDetailReport!=null">
                <s:if test="invoiceDetailReport.hasPrev()">
                    <a href="javascript:changePage(<s:property value="%{invoiceDetailReport.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="invoiceDetailReport.pageRange" status="count">
                    <s:if test="%{invoiceDetailReport.pageRange[#count.index] == invoiceDetailReport.currentPage}">
                        <a class="paginate_button current"><s:property value="invoiceDetailReport.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="invoiceDetailReport.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{invoiceDetailReport.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#customer-invoice-detail-table").tablesorter({
            sortFieldId: "invoiceOrderField",
            sortTypeId: "invoiceOrderType",
            fieldList: ["invoice_code", "customer_name", "invoice_date", "invoice_amount", "invoice_credit", "net_amount", "gst", "credit_gst", "net_gst", "total"],
            callback: refreshReport
        });
    });
    <s:if test="invoiceDetailReport==null || invoiceDetailReport.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>