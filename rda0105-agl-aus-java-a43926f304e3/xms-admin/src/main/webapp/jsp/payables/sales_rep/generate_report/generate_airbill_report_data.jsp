<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <s:hidden id="awbPage" name="awbPage"/>
    <s:hidden id="awbOrderField" name="awbOrderField"/>
    <s:hidden id="awbOrderType" name="awbOrderType"/>
    <table class="table table-bordered mg0" id="sales_rep_airbill_stats_table">
        <thead>
        <tr bgcolor="#F0F2F5">
            <th><xms:localization text="Airbill Number"/></th>
            <th><xms:localization text="Invoice Number"/></th>
            <th><xms:localization text="Customer Name"/></th>
            <th class="text-right"><xms:localization text="Customer Total"/></th>
            <th class="text-right"><xms:localization text="Franchise Cost"/></th>
            <th class="text-right"><xms:localization text="Previously Paid"/></th>
            <th class="text-right"><xms:localization text="Margin On Customer Total"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="airbillStats==null || airbillStats.totalRecords==0">
            <tr>
                <td colspan="7"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="airbillStats.records">
                <tr>
                    <td><s:property value="airbillNumber"/></td>
                    <td><s:property value="invoiceCode"/></td>
                    <td><s:property value="customerName"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="customerCost"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="franchiseCost"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="totalPaid"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="margin"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="airbillStats.startRecord"/> <xms:localization
                    text="to"/> <s:property value="airbillStats.endRecord"/> <xms:localization text="of"/> <s:property
                    value="airbillStats.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="airbillStats!=null">
                <s:if test="airbillStats.hasPrev()">
                    <a href="javascript:changeAwbPageSize(<s:property value="%{airbillStats.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="airbillStats.pageRange" status="count">
                    <s:if test="%{airbillStats.pageRange[#count.index] == airbillStats.currentPage}">
                        <a class="paginate_button current"><s:property value="airbillStats.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changeAwbPageSize(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="airbillStats.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeAwbPageSize(<s:property value="%{airbillStats.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    var airbillColumns = ["airbill_number", "invoice_code", "customer_name", "customer_cost", "franchise_cost", "total_paid", "margin"];
    $(document).ready(function () {
        $("#sales_rep_airbill_stats_table").tablesorter({
            sortFieldId: "awbOrderField",
            sortTypeId: "awbOrderType",
            fieldList: airbillColumns,
            callback: getSalesRepAirbillStats
        });
    });
    <s:if test="airbillStats==null || airbillStats.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>
</script>