<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-hover table-bordered mg0" id="customer_list_table">
    <thead>
    <tr>
        <th><xms:localization text="Customer #"/></th>
        <th><xms:localization text="Customer Name"/></th>
        <th class="text-right"><xms:localization text="MTD Rev."/></th>
        <th class="text-right"><xms:localization text="YTD Rev."/></th>
        <th class="text-right"><xms:localization text="Last Shipment Date/Time"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="customers==null || customers.totalRecords==0">
        <tr>
            <td colspan="5"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="customers.records">
            <tr>
                <td><s:property value="customerCode"/></td>
                <td><s:property value="customerName"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="mtd"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property value="ytd"/></td>
                <td class="text-right"><s:property value="lastShipmentDate"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="customers.startRecord"/> <xms:localization
                    text="to"/> <s:property value="customers.endRecord"/> <xms:localization text="of"/> <s:property
                    value="customers.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!customers.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{customers.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="customers.pageRange" status="count">
                <s:if test="%{customers.pageRange[#count.index] == customers.currentPage}">
                    <a class="paginate_button current"><s:property value="customers.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!customers.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{customers.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    var fieldList = ["customer_code", "customer_name", "mtd", "ytd", "last_shipment_date"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#customer_list_table").tablesorter({
            sortFieldId: "customer_order_field",
            sortTypeId: "customer_order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });
    <s:if test="customers==null || customers.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>