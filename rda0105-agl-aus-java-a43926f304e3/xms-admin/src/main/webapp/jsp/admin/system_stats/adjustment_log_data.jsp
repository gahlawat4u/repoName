<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="adjustment_log_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="adjustment_log_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeAdjustmentLogPageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="adjustment_log_page" name="page"/>
    <s:hidden id="adjustment_log_order_type" name="orderType"/>
    <s:hidden id="adjustment_log_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="adjustment_log_table">
        <thead>
        <tr>
            <th><xms:localization text="User Name"/></th>
            <th><xms:localization text="Adj ID"/></th>
            <th><xms:localization text="Type"/></th>
            <th><xms:localization text="Airbill #"/></th>
            <th><xms:localization text="Customer #"/></th>
            <th><xms:localization text="Action Date"/></th>
            <th class="text-right"><xms:localization text="Carrier Amt. Requested Credited"/></th>
            <th class="text-right"><xms:localization text="Customer Amt. Requested Credited"/></th>
            <th><xms:localization text="Status"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="adjustmentLogs==null || adjustmentLogs.totalRecords==0">
            <tr>
                <td colspan="9"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="adjustmentLogs.records">
                <tr>
                    <td><s:property value="userName"/></td>
                    <td><s:property value="adjustmentId"/></td>
                    <td><s:property value="adjustmentType"/></td>
                    <td><s:property value="airbillNumber"/></td>
                    <td><s:property value="customerCode"/></td>
                    <td><s:property value="actionDate"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/><s:property value="carrierAmt"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/><s:property value="customerAmt"/></td>
                    <td><s:property value="statusName"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="adjustmentLogs.startRecord"/> <xms:localization
                    text="to"/> <s:property value="adjustmentLogs.endRecord"/> <xms:localization text="of"/> <s:property
                    value="adjustmentLogs.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="adjustmentLogs!=null">
                <s:if test="adjustmentLogs.hasPrev()">
                    <a href="javascript:changeAdjustmentLogPage(<s:property value="%{adjustmentLogs.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="adjustmentLogs.pageRange" status="count">
                    <s:if test="%{adjustmentLogs.pageRange[#count.index] == adjustmentLogs.currentPage}">
                        <a class="paginate_button current"><s:property value="adjustmentLogs.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeAdjustmentLogPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="adjustmentLogs.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeAdjustmentLogPage(<s:property value="%{adjustmentLogs.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#adjustment_log_table").tablesorter({
            sortFieldId: "adjustment_log_order_field",
            sortTypeId: "adjustment_log_order_type",
            fieldList: ["user_name", "adjustmentid", "adjustment_type", "airbill_number", "customer_code", "actiondate", "carrier_amt", "customer_amt", "status"],
            callback: getAdjustmentLogs
        });
        actionPrint = "system_stats_print_adjustment_log.ix";
        actionExport = "system_stats_export_adjustment_log.ix";
    });

    function getAdjustmentLogs() {
        callAction("adjustment_log");
    }

    function changeAdjustmentLogPage(page) {
        $("#adjustment_log_page").val(page);
        getAdjustmentLogs();
    }

    function changeAdjustmentLogPageSize() {
        $("#adjustment_log_page").val(1);
        getAdjustmentLogs();
    }
    <s:if test="adjustmentLogs==null || adjustmentLogs.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>