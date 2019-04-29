<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0 table-pointer">
    <thead>
    <tr>
        <th>&nbsp;</th>
        <th><xms:localization text="Invoice No"/></th>
        <th class="text-right"><xms:localization text="Total"/></th>
        <th class="text-right"><xms:localization text="Late Fee"/></th>
        <th class="text-right"><xms:localization text="Total+Fees"/></th>
        <th class="text-right"><xms:localization text="Paid/Credit"/></th>
        <th class="text-right"><xms:localization text="Owed"/></th>
        <th class="text-right"><xms:localization text="Date"/></th>
        <th class="text-right"><xms:localization text="Due Date"/></th>
        <th class="text-right"><xms:localization text="Overdue"/></th>
        <th class="text-right"><xms:localization text="Airbill"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="invoices!=null && invoices.totalRecords>0">
        <s:iterator value="invoices.records">
            <tr onclick="showInvoiceInfo('<s:property value="invoiceCode"/>')">
                <td><s:if test="status!=0">
                    <a target="_blank"
                       href='<%=WebUtils.getContextPathURL(request)%>receive_payment.ix?receivePayment.customerOrInvoiceCode=<s:property value="invoiceCode" />&receivePayment.submitType=Go&receivePayment.searchOption=0'><i
                            class="fa fa-plus" aria-hidden="true"></i></a>
                    <a href="javascript:void(0)"
                       onclick='showAddInvoiceNoteDialog("<s:property value="invoiceCode"/>")'><i
                            class="fa fa-file-text-o" aria-hidden="true"></i></a>
                    <a href="javascript:void(0)"
                       onclick='showNotesAndFollowUpDialog("<s:property value="invoiceCode"/>")'><i class="fa fa-list"
                                                                                                    aria-hidden="true"></i></a>
                </s:if> <s:else>
                    <a href="javascript:void(0)"
                       onclick='showNotesAndFollowUpDialog("<s:property value="invoiceCode"/>")'><i class="fa fa-list"
                                                                                                    aria-hidden="true"></i></a>
                </s:else></td>
                <td><a href="javascript:void(0)"
                       onclick='viewInvoiceDetail(<s:property value="invoiceId"/>)'><s:property
                        value="invoiceCode"/></a></td>
                <td class="text-right"><s:property value="currencySymbol"/>
                    <s:property value="total"/></td>
                <td class="text-right"><s:property value="currencySymbol"/>
                    <s:property value="lateFee"/></td>
                <td class="text-right"><s:property value="currencySymbol"/>
                    <s:property value="totalCost"/></td>
                <td class="text-right" data-paid=""><s:property value="currencySymbol"/>
                    <s:property value="paid"/></td>
                <td class="text-right" data-owed=""><s:property value="currencySymbol"/>
                    <s:property value="owed"/></td>
                <td class="text-right"><s:property value="invoiceDate"/></td>
                <td class="text-right"><s:property value="dueDate"/></td>
                <td class="text-right"><s:if test="overDue!=null">
                    <s:property value="overDue"/>
                    <xms:localization text="days"/>
                </s:if></td>
                <td class="text-right"><s:property value="airbillCount"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="10"><xms:localization text="No data available"/>...</td>
        </tr>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $('table.table-pointer tbody tr').click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
        });
    });
</script>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="invoices.startRecord"/> <xms:localization
                    text="to"/> <s:property value="invoices.endRecord"/> <xms:localization text="of"/> <s:property
                    value="invoices.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!invoices.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:getInvoices(<s:property value="%{invoices.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="invoices.pageRange" status="count">
                <s:if test="%{invoices.pageRange[#count.index] == invoices.currentPage}">
                    <a class="paginate_button current"><s:property value="invoices.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:getInvoices(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!invoices.hasNext()">
                <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:getInvoices(<s:property value="%{invoices.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>