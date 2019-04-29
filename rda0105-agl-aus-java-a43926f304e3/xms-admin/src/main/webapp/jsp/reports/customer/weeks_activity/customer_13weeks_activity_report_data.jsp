<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<table id="13-weeks-activity-table" class="table table-bordered mg0 table-hover">
    <thead>
    <tr>
        <th><xms:localization text="Customer #"/></th>
        <th><xms:localization text="Customer Name"/></th>
        <th><xms:localization text="Revenue"/></th>
        <th><xms:localization text="Franchise Cost"/></th>
        <th><xms:localization text="Carrier Cost"/></th>
        <th><xms:localization text="Gross Margin"/></th>
        <th><xms:localization text="% Gross Margin"/></th>
        <th><xms:localization text="#Docs"/></th>
        <th><xms:localization text="Doc Revenue"/></th>
        <th><xms:localization text="#Non Docs"/></th>
        <th><xms:localization text="Non Doc Revenue"/></th>
        <th><xms:localization text="Total Airbills"/></th>
        <th><xms:localization text="Gross Mgn Per AWB"/></th>
        <th><xms:localization text="% Gross Mgn Per AWB"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="weekActivityReport==null || weekActivityReport.totalRecords==0">
        <tr>
            <td colspan="14"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="weekActivityReport.records">
            <tr>
                <td><s:property value="customerCode"/></td>
                <td><s:property value="customerName"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="revenue"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="franchiseCost"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="carrierCost"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="grossMargin"/></td>
                <td class="text-right"><s:property value="grossMarginPct"/>%</td>
                <td class="text-right"><s:property value="documentShipmentCount"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="documentRevenue"/></td>
                <td class="text-right"><s:property value="packageShipmentCount"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="packageRevenue"/></td>
                <td class="text-right"><s:property value="totalAirbills"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="grossMarginPerAwb"/></td>
                <td class="text-right"><s:property value="grossMarginPerAwbPct"/>%</td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="weekActivityReport.startRecord"/> <xms:localization
                    text="to"/> <s:property value="weekActivityReport.endRecord"/> <xms:localization text="of"/>
                <s:property value="weekActivityReport.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="weekActivityReport!=null">
                <s:if test="weekActivityReport.hasPrev()">
                    <a href="javascript:doReport(<s:property value="%{weekActivityReport.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="weekActivityReport.pageRange" status="count">
                    <s:if test="%{weekActivityReport.pageRange[#count.index] == weekActivityReport.currentPage}">
                        <a class="paginate_button current"><s:property value="weekActivityReport.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:doReport(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="weekActivityReport.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:doReport(<s:property value="%{weekActivityReport.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(
            function () {
                $("#13-weeks-activity-table").tablesorter(
                        {
                            sortFieldId: "orderField",
                            sortTypeId: "orderType",
                            fieldList: ["customer_code", "customer_name", "revenue", "franchise_cost", "carrier_cost", "gross_margin", "gross_margin_pct", "document_shipment_count", "document_revenue", "package_shipment_count", "package_revenue", "total_airbills", "gross_margin_per_awb",
                                "gross_margin_per_awb_pct"],
                            callback: function () {
                                doReport(1);
                            }
                        });
            });
    <s:if test="weekActivityReport==null || weekActivityReport.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>