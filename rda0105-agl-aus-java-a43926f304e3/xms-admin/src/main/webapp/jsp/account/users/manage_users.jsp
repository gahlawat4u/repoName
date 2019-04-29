<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Users"/>
            &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Manage Users"/>
        </li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->

<!--BEGIN CONTENT-->
<div class="page-content">
    <div class="row mbl">
        <div class="col-lg-12">
            <div class="col-md-12">
                <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-12">
                    <div class="portlet box">
                        <div class="portlet-header">
                            <div class="col-lg-4" style="margin-left: 0px !important; padding-left: 0px !important">
                                <table class="s36">
                                    <tbody>
                                    <tr>
                                        <td>
                                            <div class="caption">
                                                <xms:localization text="System Users"/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><xms:localization text="The users below can login to REMS"/></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-4 flr">
                                <div class="form-group flr mgb">
                                    <table class="s36">
                                        <tbody>
                                        <tr>
                                            <td><xms:localization
                                                    text="Double-click the entry to modify its value"/></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="portlet-body">
                                <s:hidden id="userId" name="userId"/>
                                <form id="search-form" style="margin-bottom: 15px !important">
                                    <table class="s36">
                                        <tbody>
                                        <tr>
                                            <td><xms:localization text="Customer #"/> :</td>
                                            <td><s:textfield name="userCode" cssClass="form-control"/></td>
                                            <td><xms:localization text="Admin Level"/> :</td>
                                            <td><s:select name="userLevelId" list="userLevels"
                                                          listValue="userLevelCode + ' - ' + userLevelName"
                                                          listKey="userLevelId" headerValue="" headerKey=""
                                                          cssClass="form-control"/></td>
                                            <td>&nbsp;</td>
                                            <td>
                                                <button class="btn s37" type="button" onclick="search()">
                                                    <xms:localization text="Go"/>
                                                </button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                                <table class="table mg0">
                                    <tr>
                                        <th class="s42"><xms:localization text="Show"/> <s:select id="pageSize"
                                                                                                  name="pageSize"
                                                                                                  list="pageSizes"
                                                                                                  cssStyle="height:22px; padding-top:1px;"
                                                                                                  onchange="search()"/>
                                            <xms:localization text="entries"/></th>
                                    </tr>
                                </table>
                                <div id="user-list-result">
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
                                                <tr uid='<s:property value="userId" />'
                                                    ondblclick="loadUserDialog('<s:property value="userId"/>')"
                                                    style="cursor: pointer">
                                                    <td><s:property value="userCode"/></td>
                                                    <td><s:property value="userName"/></td>
                                                    <td><s:property value="password"/></td>
                                                    <td><s:property value="userLevelCode"/></td>
                                                    <td><input type="checkbox"
                                                               <s:if test="isCollector">checked="checked"</s:if>
                                                               disabled="disabled"/></td>
                                                    <td><input type="checkbox"
                                                               <s:if test="isRequireChangePassword">checked="checked"</s:if>
                                                               disabled="disabled"/></td>
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
                                                <b><xms:localization text="Showing"/> <s:property
                                                        value="users.startRecord"/> <xms:localization text="to"/>
                                                    <s:property value="users.endRecord"/> <xms:localization text="of"/>
                                                    <s:property value="users.totalRecords"/></b>
                                            </div>
                                            <div class="col-xs-8">
                                                <s:if test="!users.hasPrev()">
                                                    <a class="paginate_button previous disabled"><xms:localization
                                                            text="Previous"/></a>
                                                </s:if>
                                                <s:else>
                                                    <a href="javascript:changePage(<s:property value="%{users.currentPage - 1}"/>)"
                                                       class="paginate_button previous"><xms:localization
                                                            text="Previous"/></a>
                                                </s:else>
												<span> <s:iterator value="users.pageRange" status="count">
                                                    <s:if test="%{users.pageRange[#count.index] == users.currentPage}">
                                                        <a class="paginate_button current"><s:property
                                                                value="users.currentPage"/></a>
                                                    </s:if>
                                                    <s:else>
                                                        <a class="paginate_button"
                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                    </s:else>
                                                </s:iterator>
												</span>
                                                <s:if test="!users.hasNext()">
                                                    <a class="paginate_button next" href="#"><xms:localization
                                                            text="Next"/></a>
                                                </s:if>
                                                <s:else>
                                                    <a class="paginate_button next"
                                                       href="javascript:changePage(<s:property value="%{users.currentPage+1}"/>)"><xms:localization
                                                            text="Next"/></a>
                                                </s:else>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-top: 5px">
                                    <table>
                                        <tr>
                                            <td>
                                                <button class="btn s37" type="button">
                                                    <xms:localization text="Assign Territory"/>
                                                </button>
                                            </td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>
                                                <button class="btn s37" type="button" onclick="loadUserDialog('')">
                                                    <xms:localization text="Add New User"/>
                                                </button>
                                            </td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <s:if test="adminLevel==1">
                                                <td>
                                                    <button id="btnDeleteUser" class="btn s37" type="button"
                                                            onclick="deleteUser()" disabled="disabled">
                                                        <xms:localization text="Delete User"/>
                                                    </button>
                                                </td>
                                            </s:if>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Add new/Edit user dialog -->
<div id="user-dialog"></div>
<div id="confirm-dialog" title=''></div>

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

    function doSearch() {
        var data = $("#search-form").serialize();
        data += "&orderField=" + $("#orderField").val();
        data += "&orderType=" + $("#orderType").val();
        data += "&page=" + $("#page").val();
        data += "&pageSize=" + $("#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("manage_users_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#user-list-result").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function changePage(page) {
        $("#page").val(page);
        doSearch();
    }

    function search() {
        $("#orderField").val("");
        $("#orderType").val("");
        $("#page").val(1);
        doSearch();
    }

    function loadUserDialog(userId) {
        loadingDialog.dialog("open");
        $.post("manage_users_load.ix?reqType=json", "userId=" + userId, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Define dialog
                var dialog = $("#user-dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: "auto",
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                // Dialog title
                if (userId == "") {
                    dialog.dialog("option", "title", '<xms:localization text="Add New User" />');
                } else {
                    dialog.dialog("option", "title", '<xms:localization text="Edit User" />');
                }
                dialog.html(res.content);
                dialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function deleteUser() {
        var uid = $("#userId").val();
        if (uid == "") {
            alertDialog.html('<xms:localization text="Please choose an user for deleting." />');
            alertDialog.dialog("open");
        } else {
            $("#confirm-dialog").html('<xms:localization text="Are you sure want to delete the selected user?" />');
            $("#confirm-dialog").dialog({
                resizable: false,
                modal: true,
                title: "Delete User",
                height: 200,
                width: 320,
                buttons: {
                    '<xms:localization text="Yes" />': function () {
                        $(this).dialog('close');
                        $.post("manage_users_delete.ix?reqType=json", "userId=" + uid, function (res) {
                            if (res.errorCode == "SUCCESS") {
                                doSearch();
                                messageDialog.html('<xms:localization text="The selected user was deleted." />');
                                messageDialog.dialog("open");
                            } else {
                                alertDialog.html(res.errorMsg);
                                alertDialog.dialog("open");
                            }
                        }).fail(function () {
                            loadingDialog.dialog("close");
                            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                            alertDialog.dialog("open");
                        });
                    },
                    '<xms:localization text="No" />': function () {
                        $(this).dialog('close');
                    }
                }
            });
        }
    }


</script>