<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0 table-pointer" id="carrier-list-table">
    <thead>
    <tr>
        <th><xms:localization text="Id"/></th>
        <th><xms:localization text="Carrier Name"/></th>
        <th><xms:localization text="Active"/></th>
        <th><xms:localization text="Non Centralized"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="carrierList==null || carrierList.totalRecords==0">
        <tr>
            <td colspan="4"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="carrierList.records">
            <tr data-carrier-id="<s:property value="serviceId"/>"
                ondblclick="showEditDialog('<s:property value="serviceId"/>')">
                <td><s:property value="serviceId"/></td>
                <td><s:property value="serviceName"/></td>
                <td><input type="checkbox" disabled="disabled"
                           <s:if test="inactive == 0">checked="checked"</s:if> /></td>
                <td><input type="checkbox" disabled="disabled"
                           <s:if test="nonCentralized == 1">checked="checked"</s:if> /></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="carrierList.startRecord"/> <xms:localization
                    text="to"/> <s:property value="carrierList.endRecord"/> <xms:localization text="of"/> <s:property
                    value="carrierList.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!carrierList.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{carrierList.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="carrierList.pageRange" status="count">
                <s:if test="%{carrierList.pageRange[#count.index] == carrierList.currentPage}">
                    <a class="paginate_button current"><s:property value="carrierList.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!carrierList.hasNext()">
                <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{carrierList.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#carrier-list-table tbody tr').click(function () {
            var carrierId = $(this).attr('data-carrier-id');
            if (typeof (carrierId) != "undefined" && carrierId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectCarrier(carrierId);
                $('#btnView').attr('disabled', false);
                $('#btnDelete').attr('disabled', false);
            }
        });
        var fieldList = ["service_id", "service_name", "inactive", "non_centralized"];
        $("#carrier-list-table").tablesorter({
            sortFieldId: "carrier_list_order_field",
            sortTypeId: "carrier_list_order_type",
            fieldList: fieldList,
            callback: searchCarrierList
        });
    });


</script>