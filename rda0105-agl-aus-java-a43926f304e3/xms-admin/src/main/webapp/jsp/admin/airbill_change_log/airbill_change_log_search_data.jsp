<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0" id="airbill_change_log_table">
    <thead>
    <tr>
        <th><xms:localization text="Date"/></th>
        <th><xms:localization text="Franchise"/></th>
        <th><xms:localization text="User"/></th>
        <th><xms:localization text="Action"/></th>
        <th><xms:localization text="Change Mode"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="events==null || events.totalRecords==0">
        <tr>
            <td colspan="5"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="events.records">
            <tr>
                <td><s:property value="actionDate"/></td>
                <td><s:property value="userCode"/></td>
                <td><s:property value="userName"/></td>
                <td><s:property value="actionType"/></td>
                <td><s:property value="changesMode" escapeHtml="false"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="events.startRecord"/> <xms:localization text="to"/>
                <s:property value="events.endRecord"/> <xms:localization text="of"/> <s:property
                        value="events.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="report!=null">
                <s:if test="events.hasPrev()">
                    <a href="javascript:changePage(<s:property value="%{events.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="events.pageRange" status="count">
                    <s:if test="%{events.pageRange[#count.index] == events.currentPage}">
                        <a class="paginate_button current"><s:property value="events.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="events.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{events.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var fieldList = ["action_date", "user_code", "user_name", "action_type", "changes_mode"];
        $("#airbill_change_log_table").tablesorter({
            sortFieldId: "airbill_change_log_order_field",
            sortTypeId: "airbill_change_log_order_type",
            fieldList: fieldList,
            callback: airbillChangeLogSearch
        });
    });


</script>