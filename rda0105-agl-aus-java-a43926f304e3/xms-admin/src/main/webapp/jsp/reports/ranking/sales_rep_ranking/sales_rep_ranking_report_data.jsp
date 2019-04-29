<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:hidden id="rptTxnId" name="rptTxnId"/>
<s:hidden id="page" name="page"/>
<s:hidden id="orderField" name="orderField"/>
<s:hidden id="orderType" name="orderType"/>
<table id="sales-rep-ranking-table" class="table table-bordered mg0 table-hover">
    <thead>
    <tr>
        <th><xms:localization text="Rank"/></th>
        <th><xms:localization text="Sales Rep"/></th>
        <th><xms:localization text="Fran"/></th>
        <th><xms:localization text="Territory"/></th>
        <th><xms:localization text="Activations"/></th>
        <th><xms:localization text="Active Cust"/></th>
        <th><xms:localization text="Shipments"/></th>
        <th><xms:localization text="Weight"/></th>
        <th data-group="inc-gst"><xms:localization text="Cust. Rev."/></th>
        <th data-group="exc-gst"><xms:localization text="Cust. Rev."/></th>
        <th data-group="inc-gst"><xms:localization text="Rev./Ship"/></th>
        <th data-group="exc-gst"><xms:localization text="Rev./Ship"/></th>
        <th data-group="inc-gst"><xms:localization text="Franchise Cost"/></th>
        <th data-group="exc-gst"><xms:localization text="Franchise Cost"/></th>
        <th data-group="inc-gst"><xms:localization text="Gross Margin"/></th>
        <th data-group="exc-gst"><xms:localization text="Gross Margin"/></th>
        <th data-group="inc-gst"><xms:localization text="Margin/Ship"/></th>
        <th data-group="exc-gst"><xms:localization text="Margin/Ship"/></th>
        <th data-group="inc-gst"><xms:localization text="Margin/Weight"/></th>
        <th data-group="exc-gst"><xms:localization text="Margin/Weight"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="rankingReport==null || rankingReport.totalRecords==0">
        <tr>
            <td colspan="20"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="rankingReport.records">
            <tr>
                <td><s:property value="rank"/></td>
                <td><s:property value="saleRepName"/></td>
                <td><s:property value="franchiseCode"/></td>
                <td><s:property value="territory"/></td>
                <td class="text-right"><s:property value="activations"/></td>
                <td class="text-right"><s:property value="activateCustomers"/></td>
                <td class="text-right"><s:property value="shipments"/></td>
                <td class="text-right"><s:property value="weights"/></td>
                <td class="text-right" data-group="inc-gst"><s:property value="revenueIncGst"/></td>
                <td class="text-right" data-group="exc-gst"><s:property value="revenueExcGst"/></td>
                <td class="text-right" data-group="inc-gst"><s:property value="revenuePerShipIncGst"/></td>
                <td class="text-right" data-group="exc-gst"><s:property value="revenuePerShipExcGst"/></td>
                <td class="text-right" data-group="inc-gst"><s:property value="franchiseCostIncGst"/></td>
                <td class="text-right" data-group="exc-gst"><s:property value="franchiseCostExcGst"/></td>
                <td class="text-right" data-group="inc-gst"><s:property value="marginIncGst"/></td>
                <td class="text-right" data-group="exc-gst"><s:property value="marginExcGst"/></td>
                <td class="text-right" data-group="inc-gst"><s:property value="marginPerShipIncGst"/></td>
                <td class="text-right" data-group="exc-gst"><s:property value="marginPerShipExcGst"/></td>
                <td class="text-right" data-group="inc-gst"><s:property value="marginPerWeightIncGst"/></td>
                <td class="text-right" data-group="exc-gst"><s:property value="marginPerWeightExcGst"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="rankingReport.startRecord"/> <xms:localization
                    text="to"/> <s:property value="rankingReport.endRecord"/> <xms:localization text="of"/> <s:property
                    value="rankingReport.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="rankingReport!=null">
                <s:if test="rankingReport.hasPrev()">
                    <a href="javascript:changePage(<s:property value="%{rankingReport.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="rankingReport.pageRange" status="count">
                    <s:if test="%{rankingReport.pageRange[#count.index] == rankingReport.currentPage}">
                        <a class="paginate_button current"><s:property value="rankingReport.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="rankingReport.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{rankingReport.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(
            function () {
                $("#sales-rep-ranking-table").tablesorter(
                        {
                            sortFieldId: "orderField",
                            sortTypeId: "orderType",
                            fieldList: ["rank", "sale_rep_name", "franchise_code", "territory", "activate_customers", "activations", "shipments", "weights", "rev_inc_gst", "rev_exc_gst", "rev_per_ship_inc_gst", "rev_per_ship_exc_gst", "fran_cost_inc_tax", "fran_cost_exc_tax",
                                "gross_margin_inc_tax", "gross_margin_exc_tax", "margin_per_ship_inc_tax", "margin_per_ship_exc_tax", "margin_per_weight_inc_tax", "margin_per_weight_exc_tax"],
                            callback: doReport
                        });
                showColumns();
            });
    <s:if test="rankingReport==null || rankingReport.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>