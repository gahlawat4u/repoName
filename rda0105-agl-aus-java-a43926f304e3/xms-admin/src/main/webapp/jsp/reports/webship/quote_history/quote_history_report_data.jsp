<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:hidden id="page" name="page"/>
<s:hidden id="orderField" name="orderField"/>
<s:hidden id="orderType" name="orderType"/>
<table class="table table-bordered mg0 table-hover" id="webship_quote_history_report_table">
    <thead>
    <tr>
        <th><xms:localization text="Customer"/></th>
        <th><xms:localization text="Customer #"/></th>
        <th><xms:localization text="Quote Date"/></th>
        <th><xms:localization text="Sender Address"/></th>
        <th><xms:localization text="Receiver Address"/></th>
        <th><xms:localization text="Service"/></th>
        <th><xms:localization text="Package Type"/></th>
        <th><xms:localization text="Pieces Weight Dimensions"/></th>
        <th><xms:localization text="Contents"/></th>
        <th><xms:localization text="IP Address"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="report==null || report.totalRecords==0">
        <tr>
            <td colspan='10'><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="report.records">
            <tr>
                <td><s:property value="customerName"/></td>
                <td><s:property value="customerCode"/></td>
                <td><s:property value="quoteDate"/></td>
                <td><s:property value="senderCompanyName"/><br/> <s:property value="senderContactName"/><br/>
                    <s:property value="senderAddress1"/><br/> <s:property value="senderAddress2"/><br/> <s:property
                            value="senderCity"/> <s:property value="senderPostalCode"/> <s:property
                            value="senderState"/></td>
                <td><s:property value="receiverCompanyName"/><br/> <s:property value="receiverContactName"/><br/>
                    <s:property value="receiverAddress1"/><br/> <s:property value="receiverAddress2"/><br/> <s:property
                            value="receiverCity"/> <s:property value="receiverPostalCode"/> <s:property
                            value="receiverState"/></td>
                <td><s:property value="shipmentTypeName"/></td>
                <td><s:property value="packageName"/></td>
                <td><s:property value="noOfPieces"/><br/> <s:property value="weight"/> <s:property
                        value="weightUnit"/><br/> <s:property value="dimensionL"/>x<s:property
                        value="dimensionW"/>x<s:property value="dimensionH"/> <s:property value="dimensionUnit"/></td>
                <td><s:property value="content"/></td>
                <td><s:property value="ipAddress"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="report.startRecord"/> <xms:localization text="to"/>
                <s:property value="report.endRecord"/> <xms:localization text="of"/> <s:property
                        value="report.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
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
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{report.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>

<script type="text/javascript">
    var fieldList = ["customer_name", "customer_code", "quote_date", "sender_company_name", "receiver_company_name", "shipment_type_name", "package_name", "", "content", "ip_address"];
    $(document).ready(function () {
        $("#webship_quote_history_report_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doReport
        });
    });
    <s:if test="report==null || report.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>