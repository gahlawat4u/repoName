<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:hidden id="page" name="page"/>
<s:hidden id="orderField" name="orderField"/>
<s:hidden id="orderType" name="orderType"/>
<table class="table table-hover table-bordered mg0" id="user_list_table">
    <thead>
    <tr>
        <th><xms:localization text="Customer #"/></th>
        <th><xms:localization text="User Name"/></th>
        <th><xms:localization text="Password"/></th>
        <th><xms:localization text="Admin Level"/></th>
        <th><xms:localization text="Is Collector?"/></th>
        <th><xms:localization text="Force Password Change"/></th>
        <th><xms:localization text="Last Changed"/></th>
        <th><xms:localization text="Display Name"/></th>
        <th><xms:localization text="Email Address"/></th>
        <th><xms:localization text="Phone"/></th>
        <th><xms:localization text="Fax"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="users!=null && users.totalRecords>0">
        <s:iterator value="users.records">
            <tr uid='<s:property value="userId" />' ondblclick="loadUserDialog('<s:property value="userId"/>')"
                style="cursor: pointer">
                <td><s:property value="userCode"/></td>
                <td><s:property value="userName"/></td>
                <td><s:property value="password"/></td>
                <td><s:property value="userLevelCode"/></td>
                <td><input type="checkbox"
                           <s:if test="isCollector">checked="checked"</s:if> disabled="disabled"/></td>
                <td><input type="checkbox"
                           <s:if test="isRequireChangePassword">checked="checked"</s:if> disabled="disabled"/></td>
                <td><s:property value="lastChange"/></td>
                <td><s:property value="displayName"/></td>
                <td><s:property value="email"/></td>
                <td><s:property value="phone"/></td>
                <td><s:property value="fax"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="11"><xms:localization text="No data available..."/></td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="users.startRecord"/> <xms:localization text="to"/>
                <s:property value="users.endRecord"/> <xms:localization text="of"/> <s:property
                        value="users.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!users.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{users.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="users.pageRange" status="count">
                <s:if test="%{users.pageRange[#count.index] == users.currentPage}">
                    <a class="paginate_button current"><s:property value="users.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!users.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{users.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    var fieldList = ["user_code", "user_name", "", "user_level_code", "iscollector", "isrequirechangepassword", "last_change", "display_name", "email", "phone", "fax"];
    $(document).ready(function () {
        // Add highlight selected row function to user list table
        $('#userId').val("");
        $('#btnDeleteUser').attr('disabled', 'disabled');
        $('table#user_list_table tbody tr').click(function () {
            var uid = $(this).attr('uid');
            if (typeof (uid) != "undefined" && uid != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                $('#userId').val(uid);
                $('#btnDeleteUser').removeAttr('disabled');
            }
        });
        // Add sorting function to user list table
        $("#user_list_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
    });


</script>