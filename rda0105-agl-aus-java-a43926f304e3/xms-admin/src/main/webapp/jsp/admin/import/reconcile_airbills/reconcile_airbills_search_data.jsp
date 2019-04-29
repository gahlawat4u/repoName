<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <table class="table table-bordered mg0 table-hover" id="reconcile_airbill_report_table">
        <thead>
        <tr>
            <th><xms:localization text="Airbill #"/></th>
            <th><xms:localization text="Invoice #"/></th>
            <th><xms:localization text="Import Date"/></th>
            <th><xms:localization text="Carrier Cost"/></th>
            <th><xms:localization text="Customer Total"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="report==null || report.totalRecords==0">
            <tr>
                <td colspan="5"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="report.records">
                <tr>
                    <td><s:property value="airbillNumber"/></td>
                    <td><s:property value="invoiceCode"/></td>
                    <td><s:property value="importDate"/></td>
                    <td class="text-right"><s:property value="carrierCost"/></td>
                    <td class="text-right"><s:property value="customerCost"/></td>
                </tr>
            </s:iterator>
            <tr>
                <th colspan="5"><xms:localization text="Showing"/> <s:property value="report.startRecord"/>
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
    var fieldList = ["airbill_number", "invoice_code", "import_date", "carrier_cost", "customer_cost"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#reconcile_airbill_report_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
        // Change counter.
        $("#lblFound").html('<s:property value="foundCount" />');
        $("#lblMissing").html('<s:property value="missingCount" />');
    });
    <s:if test="report==null || report.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

</script>