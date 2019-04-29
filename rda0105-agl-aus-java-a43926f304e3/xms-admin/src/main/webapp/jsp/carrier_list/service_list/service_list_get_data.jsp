<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0 table-pointer" id="service-list-table">
    <thead>
    <tr>
        <th><xms:localization text="Id"/></th>
        <th><xms:localization text="Service Name"/></th>
        <th><xms:localization text="EDI Description"/></th>
        <th><xms:localization text="Service Priority"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="serviceList==null || serviceList.totalRecords==0">
        <tr>
            <td colspan="4"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="serviceList.records">
            <tr data-service-id="<s:property value="shipmentTypeId"/>"
                onclick="selectService($(this).attr('data-service-id'))"
                ondblclick="showEditDialog($(this).attr('data-service-id'))">
                <td><s:property value="shipmentTypeId"/></td>
                <td><s:property value="shipmentTypeName"/></td>
                <td><s:property value="ediDescription"/></td>
                <td><s:property value="servicePriority"/></td>
            </tr>
        </s:iterator>
        <tr>
            <td colspan="4"><xms:localization text="Showing"/> <s:property value="serviceList.startRecord"/>
                <xms:localization text="to"/> <s:property value="serviceList.endRecord"/> <xms:localization text="of"/>
                <s:property value="serviceList.totalRecords"/> <xms:localization text="entries"/></td>
        </tr>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#service-list-table tbody tr').click(function () {
            var serviceId = $(this).attr('data-service-id');
            if (typeof (serviceId) != "undefined" && serviceId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                $('#btnView').attr('disabled', false);
                $('#btnDelete').attr('disabled', false);
            }
        });
        var fieldList = ["shipment_type_id", "shipment_type_name", "edi_description", "service_priority"];
        $("#service-list-table").tablesorter({
            sortFieldId: "service_list_order_field",
            sortTypeId: "service_list_order_type",
            fieldList: fieldList,
            callback: searchServiceList
        });
    });
</script>
<div class="dataTables_paginate">
    <s:if test="!serviceList.hasPrev()">
        <button class="paginate_button previous disabled" disabled="disabled">
            <xms:localization text="Previous"/>
        </button>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{serviceList.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="serviceList.pageRange" status="count">
        <s:if test="%{serviceList.pageRange[#count.index] == serviceList.currentPage}">
            <a class="paginate_button current"><s:property value="serviceList.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!serviceList.hasNext()">
        <button class="paginate_button next" disabled="disabled">
            <xms:localization text="Next"/>
        </button>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{serviceList.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>