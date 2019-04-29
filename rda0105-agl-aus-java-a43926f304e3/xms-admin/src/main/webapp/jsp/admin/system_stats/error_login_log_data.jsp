<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="error_login_log_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="error_login_log_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeErrorLoginLogPageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="loginType" value="1"/>
    <s:hidden id="error_login_log_page" name="page"/>
    <s:hidden id="error_login_log_order_type" name="orderType"/>
    <s:hidden id="error_login_log_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="error_login_log_table">
        <thead>
        <tr>
            <th><xms:localization text="Customer#"/></th>
            <th><xms:localization text="User Name"/></th>
            <th><xms:localization text="IP Address"/></th>
            <th><xms:localization text="Date/Time"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="loginLogs==null || loginLogs.totalRecords==0">
            <tr>
                <td colspan="4"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="loginLogs.records">
                <tr>
                    <td><s:property value="franchiseCode"/></td>
                    <td><s:property value="userName"/></td>
                    <td><s:property value="ipAddress"/></td>
                    <td><s:property value="loginDate"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="loginLogs.startRecord"/> <xms:localization
                    text="to"/> <s:property value="loginLogs.endRecord"/> <xms:localization text="of"/> <s:property
                    value="loginLogs.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="loginLogs!=null">
                <s:if test="loginLogs.hasPrev()">
                    <a href="javascript:changeErrorLoginLogPage(<s:property value="%{loginLogs.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="loginLogs.pageRange" status="count">
                    <s:if test="%{loginLogs.pageRange[#count.index] == loginLogs.currentPage}">
                        <a class="paginate_button current"><s:property value="loginLogs.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeErrorLoginLogPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="loginLogs.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeErrorLoginLogPage(<s:property value="%{loginLogs.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#error_login_log_table").tablesorter({
            sortFieldId: "error_login_log_order_field",
            sortTypeId: "error_login_log_order_type",
            fieldList: ["franchise_code", "user_name", "ip_address", "login_date"],
            callback: getErrorLoginLogs
        });
        actionPrint = "system_stats_print_login_log.ix";
        actionExport = "system_stats_export_login_log.ix";
    });

    function getErrorLoginLogs() {
        callAction("error_login_log");
    }

    function changeErrorLoginLogPage(page) {
        $("#error_login_log_page").val(page);
        getErrorLoginLogs();
    }

    function changeErrorLoginLogPageSize() {
        $("#error_login_log_page").val(1);
        getErrorLoginLogs();
    }
    <s:if test="loginLogs==null || loginLogs.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>