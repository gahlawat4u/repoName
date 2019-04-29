<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="webship_log_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="webship_log_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeWebshipLogPageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="webship_log_page" name="page"/>
    <s:hidden id="webship_log_order_type" name="orderType"/>
    <s:hidden id="webship_log_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="webship_log_table">
        <thead>
        <tr>
            <th><xms:localization text="Date"/></th>
            <th><xms:localization text="Customer Code"/></th>
            <th><xms:localization text="User"/></th>
            <th><xms:localization text="Action"/></th>
            <th><xms:localization text="Table"/></th>
            <th><xms:localization text="Filter"/></th>
            <th><xms:localization text="Changes Mode"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="webshipLogs==null || webshipLogs.totalRecords==0">
            <tr>
                <td colspan="7"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="webshipLogs.records">
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
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="webshipLogs.startRecord"/> <xms:localization
                    text="to"/> <s:property value="webshipLogs.endRecord"/> <xms:localization text="of"/> <s:property
                    value="webshipLogs.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="webshipLogs!=null">
                <s:if test="webshipLogs.hasPrev()">
                    <a href="javascript:changeWebshipLogPage(<s:property value="%{webshipLogs.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="webshipLogs.pageRange" status="count">
                    <s:if test="%{webshipLogs.pageRange[#count.index] == webshipLogs.currentPage}">
                        <a class="paginate_button current"><s:property value="webshipLogs.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeWebshipLogPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="webshipLogs.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeWebshipLogPage(<s:property value="%{webshipLogs.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#webship_log_table").tablesorter({
            sortFieldId: "webship_log_order_field",
            sortTypeId: "webship_log_order_type",
            fieldList: ["action_date", "user_code", "user_name", "action_type", "action_table", "filter", "changes_mode"],
            callback: getWebshipLogs
        });
        actionPrint = "system_stats_print_webship_log.ix"
        actionExport = "";
    });

    function getWebshipLogs() {
        callAction("webship_log");
    }

    function changeWebshipLogPage(page) {
        $("#webship_log_page").val(page);
        getWebshipLogs();
    }

    function changeWebshipLogPageSize() {
        $("#webship_log_page").val(1);
        getWebshipLogs();
    }


</script>