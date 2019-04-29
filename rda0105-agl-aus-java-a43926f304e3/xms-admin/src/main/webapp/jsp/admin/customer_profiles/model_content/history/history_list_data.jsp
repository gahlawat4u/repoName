<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0" id="customer_profile_history_table">
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
    <s:if test="eventLogs.records.size != 0">
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
            <td colspan="7"><xms:localization text="No record to view"/> ...</td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate">
    <s:hidden name="customerEventLogFilter.page" id="currentPage"></s:hidden>
    <s:if test="!eventLogs.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{eventLogs.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="eventLogs.pageRange" status="count">
        <s:if test="%{eventLogs.pageRange[#count.index] == eventLogs.currentPage}">
            <a class="paginate_button current"><s:property value="eventLogs.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!eventLogs.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{eventLogs.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>
<script type="text/javascript">
    <s:if test="eventLogs==null || eventLogs.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

    $(document).ready(function () {
        var fieldList = ["action_date", "user_code", "user_name", "action_type", "action_table", "filter", "changes_mode"];
        $("#customer_profile_history_table").tablesorter({
            sortFieldId: "customer_profile_history_order_field",
            sortTypeId: "customer_profile_history_order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });


</script>