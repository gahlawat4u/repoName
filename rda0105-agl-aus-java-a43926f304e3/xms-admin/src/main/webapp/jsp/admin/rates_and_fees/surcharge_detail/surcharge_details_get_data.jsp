<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0 table-hover table-pointer" id="accessorial-detail-table">
    <thead>
    <tr>
        <th><xms:localization text="Default Charge"/></th>
        <th><xms:localization text="Default Carrier Charge"/></th>
        <th><xms:localization text="Apply Start Date"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="accessorialDetailList.records.isEmpty()">
        <tr>
            <td colspan="4"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="accessorialDetailList.records" status="count">
            <tr data-apply-start-date="<s:property value="applyStartDate" />"
                ondblclick="showEditDialog('<s:property value="applyStartDate"/>')">
                <td><s:property value="defaultCharge"/></td>
                <td><s:property value="defaultChargeCarrier"/></td>
                <td><s:property value="applyStartDate"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $('#selectedId').val("");
        $('table#accessorial-detail-table tbody tr').click(function () {
            var applyDate = $(this).attr('data-apply-start-date');
            if (typeof (applyDate) != "undefined" && applyDate != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectAccessorialDetail(applyDate);
                $('#btnRemove').attr('disabled', false);
            }
        });
        var fieldList = ["default_charge", "default_charge_carrier", "apply_start_date"];
        $("#accessorial-detail-table").tablesorter({
            sortFieldId: "acc_details_order_field",
            sortTypeId: "acc_details_order_type",
            fieldList: fieldList,
            callback: doSearchAccDetails
        });
    });
</script>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="accessorialDetailList.startRecord"/>
                <xms:localization text="to"/> <s:property value="accessorialDetailList.endRecord"/> <xms:localization
                        text="of"/> <s:property value="accessorialDetailList.totalRecords"/> <xms:localization
                        text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!accessorialDetailList.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{accessorialDetailList.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="accessorialDetailList.pageRange" status="count">
                <s:if test="%{accessorialDetailList.pageRange[#count.index] == accessorialDetailList.currentPage}">
                    <a class="paginate_button current"><s:property value="accessorialDetailList.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!accessorialDetailList.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{accessorialDetailList.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>