<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/webship/customer/dato.tablesorter.js"></script>
<div class="row">
    <div class="col-lg-12">
        <div class="portlet box">
            <div class="portlet-header">
                <div class="caption w4">
                    <xms:localization text="User List"/>
                </div>
            </div>
            <div class="portlet-body b12 b11">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-group">
                            <p>
                                <b><xms:localization text="Note:"/></b> <br> -
                                <xms:localization
                                        text="Web Freight accounts may not be deleted. To disable access to an account, simply change the password."/>
                            </p>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <table class="table mg0">
                            <tr>
                                <th class="s42">
                                    <div class="form-group fll mgb">
                                        <form id="user_list_search_form">
                                            <s:hidden id="user_list_page" name="page"/>
                                            <s:hidden id="user_list_order_type" name="orderType"/>
                                            <s:hidden id="user_list_order_field" name="orderField"/>
                                            <table class="s36">
                                                <tbody>
                                                <tr>
                                                    <td><xms:localization text="Show"/></td>
                                                    <td><s:select id="user_pageSize" name="pageSize" list="pageSizes"
                                                                  cssClass="form-control"
                                                                  cssStyle="height: 22px; padding-top: 1px;"
                                                                  onchange="changeUserListPage(1)"/></td>
                                                    <td><xms:localization text="Entries"/></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </form>
                                    </div>
                                </th>
                            </tr>
                        </table>
                        <div id="user_list_result">
                            <table class="table table-bordered table-hover mg0" id="user_list_table">
                                <thead>
                                <tr>
                                    <th><xms:localization text="WebFreightID"/></th>
                                    <th><xms:localization text="CustomerID"/></th>
                                    <th><xms:localization text="Alt User Name"/></th>
                                    <th><xms:localization text="Password"/></th>
                                    <th><xms:localization text="Created Date"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:if test="webships!=null && webships.totalRecords>0">
                                    <s:iterator value="webships.records">
                                        <tr data-webship-id="<s:property value="webshipId" />" style="cursor: pointer;">
                                            <td><s:property value="webshipId"/></td>
                                            <td><s:property value="customerCode"/></td>
                                            <td><s:property value="name"/></td>
                                            <td><s:property value="password"/></td>
                                            <td><s:property value="createDate"/></td>
                                        </tr>
                                    </s:iterator>
                                    <tr>
                                        <th colspan="5"><xms:localization text="Showing"/> <s:property
                                                value="webships.startRecord"/> <xms:localization text="to"/> <s:property
                                                value="webships.endRecord"/> <xms:localization text="of"/> <s:property
                                                value="webships.totalRecords"/> <xms:localization text="entries"/></th>
                                    </tr>
                                </s:if>
                                <s:else>
                                    <tr>
                                        <td colspan="5"><xms:localization text="No data available"/>...</td>
                                    </tr>
                                </s:else>
                                </tbody>
                            </table>
                            <div class="dataTables_paginate">
                                <s:if test="!webships.hasPrev()">
                                    <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
                                </s:if>
                                <s:else>
                                    <a href="javascript:changeUserListPage(<s:property value="%{webships.currentPage - 1}"/>)"
                                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                                </s:else>
								<span> <s:iterator value="webships.pageRange" status="count">
                                    <s:if test="%{webships.pageRange[#count.index] == webships.currentPage}">
                                        <a class="paginate_button current"><s:property
                                                value="webships.currentPage"/></a>
                                    </s:if>
                                    <s:else>
                                        <a class="paginate_button" href="javascript:changeUserListPage(<s:property/>);"><s:property/></a>
                                    </s:else>
                                </s:iterator>
								</span>
                                <s:if test="!webships.hasNext()">
                                    <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
                                </s:if>
                                <s:else>
                                    <a class="paginate_button next"
                                       href="javascript:changeUserListPage(<s:property value="%{webships.currentPage+1}"/>)"><xms:localization
                                            text="Next"/></a>
                                </s:else>
                            </div>
                        </div>
                        <table>
                            <tr>
                                <td>
                                    <button class="btn s37" type="button" onclick="onAddEditUser(true)">
                                        <xms:localization text="Add User"/>
                                    </button>
                                </td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>
                                    <button class="btn s37" type="button" onclick="onAddEditUser(false)">
                                        <xms:localization text="Edit User"/>
                                    </button>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="webship-dialog" title=""></div>

<script type="text/javascript">
    var webshipId = "";

    $(document).ready(function () {
        $("#user_list_result tr[data-webship-id]").click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
            webshipId = $(this).attr("data-webship-id");
        });
        var fieldList = ["webshipid", "customer_code", "name", "password", "create_date"];
        $("#user_list_table").tablesorter({
            sortFieldId: "user_list_order_field",
            sortTypeId: "user_list_order_type",
            fieldList: fieldList,
            callback: searchUserList
        });
    });

    function searchUserList() {
        var data = $("#user_list_search_form").serialize();
        loadingDialog.dialog("open");
        $.post("webship_settings_user_list_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#user_list_result").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function changeUserListPage(page) {
        $("#user_pageSize").val(page);
        searchUserList();
    }

    function loadWebshipDialog(webshipId) {
        var data = {
            "webshipId": webshipId
        };
        loadingDialog.dialog("open");
        $.post("webship_settings_user_list_load.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Define Save and Cancel buttons
                var buttons = {};
                buttons["Save"] = function () {
                    var formData = $("#webship-form").serialize();
                    loadingDialog.dialog("open");
                    $.post("webship_settings_user_list_edit.ix?reqType=json", formData, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            $("#webship-dialog").html("");
                            $("#webship-dialog").dialog("close");
                            changeUserListPage(1);
                            messageDialog.html("<xms:localization text="Saved successfully." />");
                            messageDialog.dialog("open");
                        } else if (res.errorCode == "FIELD_ERROR") {
                            $("#webship-dialog").html(res.content);
                        } else {
                            alertDialog.html(res.errorMsg);
                            alertDialog.dialog("open");
                        }
                    }).fail(function () {
                        loadingDialog.dialog("close");
                        alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                        alertDialog.dialog("open");
                    });
                };
                buttons["Cancel"] = function () {
                    $(this).dialog("close");
                }
                // Define dialog
                var dialog = $("#webship-dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: "auto",
                    buttons: buttons,
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                // Dialog title
                if (webshipId == "") {
                    dialog.dialog("option", "title", "<xms:localization text="Add Web Freight User" />");
                } else {
                    dialog.dialog("option", "title", "<xms:localization text="Edit Web Freight User" />");
                }
                dialog.html(res.content);
                dialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function onAddEditUser(isAdd) {
        if (!isAdd) {
            if (webshipId == "") {
                alertDialog.html("<xms:localization text="Please choose a Web Freight user for editing." />");
                alertDialog.dialog("open");
            } else {
                loadWebshipDialog(webshipId);
            }
        } else {
            loadWebshipDialog("");
        }
    }


</script>