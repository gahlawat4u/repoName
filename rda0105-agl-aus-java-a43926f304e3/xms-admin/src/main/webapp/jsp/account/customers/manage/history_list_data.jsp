<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0" id="customer_history_table">
    <thead>
    <tr>
        <th><xms:localization text="Date"/></th>
        <th><xms:localization text="Franchise"/></th>
        <th><xms:localization text="User"/></th>
        <th><xms:localization text="Action"/></th>
        <th><xms:localization text="Table"/></th>
        <th><xms:localization text="Filter"/></th>
        <th><xms:localization text="Changes Mode"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="eventLogs!=null && eventLogs.totalRecords>0">
        <s:iterator value="eventLogs.records">
            <tr>
                <td><s:property value="actionDate"/></td>
                <td><s:property value="userCode"/></td>
                <td><s:property value="userName"/></td>
                <td><s:property value="actionType"/></td>
                <td><s:property value="actionTable"/></td>
                <td><s:property value="filter"/></td>
                <td><s:property value="changesMode" escapeHtml="false"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="7"><xms:localization text="No data available..."/></td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="eventLogs.startRecord"/> <xms:localization
                    text="to"/> <s:property value="eventLogs.endRecord"/> <xms:localization text="of"/> <s:property
                    value="eventLogs.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!eventLogs.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:getHistory(<s:property value="%{eventLogs.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="eventLogs.pageRange" status="count">
                <s:if test="%{eventLogs.pageRange[#count.index] == eventLogs.currentPage}">
                    <a class="paginate_button current"><s:property value="eventLogs.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:getHistory(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!eventLogs.hasNext()">
                <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:getHistory(<s:property value="%{eventLogs.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var fieldList = ["action_date", "user_code", "user_name", "action_type", "action_table", "filter", "changes_mode"];
        $("#customer_history_table").tablesorter({
            sortFieldId: "customer_history_order_field",
            sortTypeId: "customer_history_order_type",
            fieldList: fieldList,
            callback: searchCustomerHistory
        });
    });


</script>