<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <table class="table table-bordered mg0" id="airbill_margin_report_table">
        <thead>
        <tr>
            <th><xms:localization text="Airbill #"/></th>
            <th><xms:localization text="Invoice #"/></th>
            <th><xms:localization text="Type"/></th>
            <th class="text-right"><xms:localization text="Customer Cost"/></th>
            <th class="text-right"><xms:localization text="Franchise Cost"/></th>
            <th class="text-right"><xms:localization text="Franchise Margin"/></th>
            <th class="text-right"><xms:localization text="Franchise Margin Percent"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="report==null || report.totalRecords==0">
            <tr>
                <td colspan="7"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="report.records">
                <tr ondblclick='showEditAirbillDialog(<s:property value="shipmentId"/>,"<s:property
                        value="airbillNumber"/>")'>
                    <td><s:property value="airbillNumber"/></td>
                    <td><a href="#" onclick='viewInvoiceDetail(<s:property value="invoiceId"/>)'><s:property
                            value="invoiceCode"/></a></td>
                    <td><s:property value="shipmentTypeName"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="customerCost"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="franchiseCost"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="franchiseMargin"/></td>
                    <td class="text-right"><s:property value="franchiseMarginPct"/>%</td>
                </tr>
            </s:iterator>
            <tr>
                <th colspan="7"><xms:localization text="Showing"/> <s:property value="report.startRecord"/>
                    <xms:localization text="to"/> <s:property value="report.endRecord"/> <xms:localization text="of"/>
                    <s:property value="report.totalRecords"/></th>
            </tr>
            <s:if test="summary!=null">
                <tr>
                    <td colspan="7"><span class="b4"> <b><xms:localization text="Total Airbills:"/></b> <s:property
                            value="report.totalRecords"/>
						</span> <span class="b4"> <b>| <xms:localization text="Customer Total:"/></b> <s:property
                            value="currencySymbol"/> <s:property value="summary.customerCost"/>
						</span> <span class="b4"> <b>| <xms:localization text="Franchise Cost:"/></b> <s:property
                            value="currencySymbol"/> <s:property value="summary.franchiseCost"/>
						</span></td>
                </tr>
            </s:if>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="report.startRecord"/> <xms:localization text="to"/>
                <s:property value="report.endRecord"/> <xms:localization text="of"/> <s:property
                        value="report.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="report!=null">
                <s:if test="!report.hasPrev()">
                    <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
                </s:if>
                <s:else>
                    <a href="javascript:changePage(<s:property value="%{report.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:else>
				<span> <s:iterator value="report.pageRange" status="count">
                    <s:if test="%{report.pageRange[#count.index] == report.currentPage}">
                        <a class="paginate_button current"><s:property value="report.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="!report.hasNext()">
                    <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{report.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:else>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    var fieldList = ["airbill_number", "invoice_code", "shipment_type_name", "customer_cost", "franchise_cost", "franchise_margin", "franchise_margin_pct"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#airbill_margin_report_table").tablesorter({
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