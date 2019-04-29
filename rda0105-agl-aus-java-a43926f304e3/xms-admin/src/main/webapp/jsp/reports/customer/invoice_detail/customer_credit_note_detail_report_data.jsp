<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<input id="creditRptTxnId" type="hidden" value="<s:property value="rptTxnId" />"/>
<input id="creditPage" type="hidden" value="<s:property value="page" />"/>
<input id="creditOrderField" type="hidden" value="<s:property value="orderField" />"/>
<input id="creditOrderType" type="hidden" value="<s:property value="orderType" />"/>
<table id="customer-credit-note-detail-table" class="table table-bordered mg0 table-hover">
    <thead>
    <tr>
        <th><xms:localization text="Credit Note Number"/></th>
        <th><xms:localization text="Customer"/></th>
        <th><xms:localization text="Credit Note Date"/></th>
        <th><xms:localization text="Amount"/></th>
        <th><xms:localization text="GST"/></th>
        <th><xms:localization text="Total"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="creditNoteDetailReport==null || creditNoteDetailReport.totalRecords==0">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="creditNoteDetailReport.records">
            <tr>
                <td><s:property value="creditCode"/></td>
                <td><s:property value="customerName"/></td>
                <td><s:property value="createDate"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="amount"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="gst"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="total"/></td>
            </tr>
        </s:iterator>
        <s:if test="creditNoteDetailSummary!=null">
            <tr>
                <th colspan="3" class="text-right"><xms:localization text="Total"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="creditNoteDetailSummary.amount"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="creditNoteDetailSummary.gst"/></th>
                <th class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="creditNoteDetailSummary.total"/></th>
            </tr>
        </s:if>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="creditNoteDetailReport.startRecord"/>
                <xms:localization text="to"/> <s:property value="creditNoteDetailReport.endRecord"/> <xms:localization
                        text="of"/> <s:property value="creditNoteDetailReport.totalRecords"/> <xms:localization
                        text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="creditNoteDetailReport!=null">
                <s:if test="creditNoteDetailReport.hasPrev()">
                    <a href="javascript:changePage(<s:property value="%{creditNoteDetailReport.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="creditNoteDetailReport.pageRange" status="count">
                    <s:if test="%{creditNoteDetailReport.pageRange[#count.index] == creditNoteDetailReport.currentPage}">
                        <a class="paginate_button current"><s:property value="creditNoteDetailReport.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="creditNoteDetailReport.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{creditNoteDetailReport.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#customer-credit-note-detail-table").tablesorter({
            sortFieldId: "creditOrderField",
            sortTypeId: "creditOrderType",
            fieldList: ["credit_code", "customer_name", "create_date", "amount", "gst", "total"],
            callback: refreshReport
        });
    });
    <s:if test="creditNoteDetailReport==null || creditNoteDetailReport.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>