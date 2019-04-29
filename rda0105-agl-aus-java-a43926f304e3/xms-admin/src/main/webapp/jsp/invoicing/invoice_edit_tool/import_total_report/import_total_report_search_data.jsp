<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <table class="table table-bordered mg0" id="import_total_table">
        <thead>
        <tr>
            <th><xms:localization text="Invoice #"/></th>
            <th class="text-right"><xms:localization text="Customer Total"/></th>
            <th class="text-right"><xms:localization text="Franchise Chg."/></th>
            <th class="text-right"><xms:localization text="Carrier Cost"/></th>
            <th class="text-right"><xms:localization text="Margin"/></th>
            <th class="text-right"><xms:localization text="Airbill Count"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="report==null || report.totalRecords==0">
            <tr>
                <td colspan="6"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="report.records">
                <tr>
                    <td><a href="#" onclick='viewInvoiceDetail(<s:property value="invoiceId"/>)'><s:property
                            value="invoiceCode"/></a></td>
                    <td class="text-right"><s:property value="currencySymbol"/><s:property value="customerCost"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/><s:property value="franchiseCost"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/><s:property value="carrierCost"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/><s:property value="margin"/></td>
                    <td class="text-right"><s:property value="airbillCount"/></td>
                </tr>
            </s:iterator>
            <s:if test="summary!=null">
                <tr>
                    <th>Total</th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="summary.customerCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="summary.franchiseCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="summary.carrierCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property value="summary.margin"/></th>
                    <th class="text-right"><s:property value="summary.airbillCount"/></th>
                </tr>
            </s:if>
            <tr>
                <th colspan="26"><xms:localization text="Showing"/> <s:property value="report.startRecord"/>
                    <xms:localization text="to"/> <s:property value="report.endRecord"/> <xms:localization text="of"/>
                    <s:property value="report.totalRecords"/></th>
            </tr>
        </s:else>
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
    var fieldList = ["invoice_code", "customer_cost", "franchise_cost", "carrier_cost", "margin", "airbill_count"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#import_total_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
    });
    <s:if test="report==null || report.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>