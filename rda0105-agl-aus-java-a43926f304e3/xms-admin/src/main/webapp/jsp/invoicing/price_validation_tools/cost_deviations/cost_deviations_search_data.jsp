<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <table class="table table-bordered mg0" id="cost_deviation_report_table">
        <thead>
        <tr>
            <th><xms:localization text="Airbill #"/></th>
            <th><xms:localization text="Invoice #"/></th>
            <th class="text-right"><xms:localization text="Calculated Franchise Cost"/></th>
            <th class="text-right"><xms:localization text="Franchise Cost on Airbill"/></th>
            <th class="text-right"><xms:localization text="Difference"/></th>
            <th><xms:localization text="Carrier"/></th>
            <th class="text-right"><xms:localization text="Weight"/></th>
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
                <tr>
                    <td><s:property value="airbillNumber"/></td>
                    <td><s:property value="invoiceCode"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="calculatedFranchiseCost"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="franchiseCost"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="difference"/></td>
                    <td><s:property value="serviceName"/></td>
                    <td class="text-right"><s:property value="weight"/></td>
                </tr>
            </s:iterator>
            <s:if test="summary!=null">
                <tr>
                    <td colspan="7"><span class="b4"> <b><xms:localization
                            text="Total Calculated Franchise Costs:"/></b> <s:property
                            value="currencySymbol"/> <s:property value="summary.calculatedFranchiseCost"/>
						</span> <span class="b4"> <b>| <xms:localization text="Total Franchise Costs on Airbill:"/></b> <s:property
                            value="currencySymbol"/> <s:property value="summary.franchiseCost"/>
						</span> <span class="b4"> <b>| <xms:localization text="Total Differences:"/></b> <s:property
                            value="currencySymbol"/> <s:property value="summary.difference"/>
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
                        value="report.totalRecords"/></b>
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
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    var fieldList = ["airbill_number", "invoice_code", "calculated_franchise_cost", "franchise_cost", "difference", "service_name", "weight"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#cost_deviation_report_table").tablesorter({
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