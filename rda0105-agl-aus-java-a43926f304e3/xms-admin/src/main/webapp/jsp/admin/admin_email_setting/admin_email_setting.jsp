<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Admin Email Settings"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Admin Email Settings"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<div class="page-content">
    <div id="tab-general">
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
                                <div class="caption">
                                    <xms:localization text="Admin Email Settings"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive" style="height: 650px !important;">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <b><xms:localization text="Note"/> :</b> <br/> -
                                                    <xms:localization text="Click an e-mail address to modify"/>
                                                    .
                                                </p>
                                            </div>
                                            <div class="col-lg-12">
                                                <div id="admin-email-setting-list">
                                                    <table class="table mg0">
                                                        <tbody>
                                                        <tr>
                                                            <th class="s42">
                                                                <table class="s36">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Show"/></td>
                                                                        <td><s:select name="pageSize" list="pageSizes"
                                                                                      onchange="onChangePageSize()"
                                                                                      id="pageSize"/></td>
                                                                        <td><xms:localization text="Entries"/></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </th>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <table class="table table-bordered mg0">
                                                        <thead>
                                                        <tr>
                                                            <th colspan="2"><xms:localization
                                                                    text="Email Address"/></th>
                                                            <th><xms:localization text="E-mail Invoice Confirm"/></th>
                                                            <th><xms:localization text="EDI Import Notify"/></th>
                                                            <th><xms:localization text="Supply Request"/></th>
                                                            <th><xms:localization text="Last Modified"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="emailSettingModels.records.size != 0">
                                                            <s:iterator value="emailSettingModels.records">
                                                                <tr>
                                                                    <td width="45"><i id="adjustment-link"
                                                                                      class="fa fa-pencil s10 b3 adjustments"
                                                                                      data="<s:property value='id' />"
                                                                                      style="font-size: 18px;"
                                                                                      emailData="<s:property value='email' />"
                                                                                      onclick="javascript:editEmail($(this).attr('data'),$(this).attr('emailData'));"></i>
                                                                        <i id="note-link3"
                                                                           data="<s:property value='id' />"
                                                                           class="fa fa-times-circle-o s10 b3"
                                                                           style="font-size: 18px;"
                                                                           data="<s:property value='id' />"
                                                                           dataSetting="<s:property value='idSetting' />"
                                                                           onclick="javascript:deleteEmail($(this).attr('data'), $(this).attr('dataSetting'));"></i>
                                                                    </td>
                                                                    <td id="edit-email-link"><s:property
                                                                            value="email"/></td>
                                                                    <td><s:checkbox name="emailInvoiceConfirm"
                                                                                    dataSetting="1" adminEmailId="%{id}"
                                                                                    onclick="javascript:updateEmailStatus($(this).is(':checked'), $(this).attr('dataSetting'),$(this).attr('adminEmailId'));"></s:checkbox></td>
                                                                    <td><s:checkbox name="ediImportNotify"
                                                                                    dataSetting="2" adminEmailId="%{id}"
                                                                                    onclick="javascript:updateEmailStatus($(this).is(':checked'), $(this).attr('dataSetting'),$(this).attr('adminEmailId'));"></s:checkbox></td>
                                                                    <td><s:checkbox name="supplyRequest" dataSetting="3"
                                                                                    adminEmailId="%{id}"
                                                                                    onclick="javascript:updateEmailStatus($(this).is(':checked'), $(this).attr('dataSetting'),$(this).attr('adminEmailId'));"></s:checkbox></td>
                                                                    <td><s:property value="actionDate"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:if>
                                                        <s:else>
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No record to view"/> ...
                                                                </td>
                                                            </tr>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="!emailSettingModels.hasPrev()">
                                                            <a class="paginate_button previous disabled"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a href="javascript:changePage(<s:property value="%{emailSettingModels.currentPage - 1}"/>)"
                                                               class="paginate_button previous"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:else>
														<span> <s:iterator value="emailSettingModels.pageRange"
                                                                           status="count">
                                                            <s:if test="%{emailSettingModels.pageRange[#count.index] == emailSettingModels.currentPage}">
                                                                <a class="paginate_button current"><s:property
                                                                        value="emailSettingModels.currentPage"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a class="paginate_button"
                                                                   href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                            </s:else>
                                                        </s:iterator>
														</span>
                                                        <s:if test="!emailSettingModels.hasNext()">
                                                            <a class="paginate_button next" href="#"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button next"
                                                               href="javascript:changePage(<s:property value="%{emailSettingModels.currentPage+1}"/>)"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:else>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions pal pdt10">
                                        <div class="row">
                                            <div class="col-lg-12 text-right">
                                                <button id="add-email-link" class="btn s37" type="button"
                                                        onclick="javascript:addEmail();">New Email
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="add_email_dialog"></div>
<div id="edit_email_dialog"></div>
<div id="delete_email_dialog"></div>
<div id="update_status_email_dialog"></div>
<!--END CONTENT-->
<script type="text/javascript">
    $(document).ready(function () {
        var page = 1;
        var pageSize = $("select[name='pageSize'] option:selected").val();
        //doSearch(pageSize, page);
    });
    function doSearch(pageSize, page) {
        var data = "page=" + page;
        data = data + "&pageSize=" + pageSize;
        loadingDialog.dialog("open");
        $.post("admin_email_setting_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#admin-email-setting-list").html(res.content);
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
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, page);
    }

    function onChangePageSize() {
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, 1);
    }

    function addEmail() {
        loadDialog("admin_email_setting_add.ix?reqType=json", "", "admin_email_setting_add_form", "Add", "Cancel", "add_email_dialog", "Add Email", "admin-email-setting-list");

    }

    function deleteEmail(val, valSetting) {
        var data = {
            'adminEmailModel.id': val,
            'idSetting': valSetting
        };
        loadDeleteDialog("admin_email_setting_delete.ix?reqType=json", data, "Are you sure you want to delete this Email", "delete_email_dialog", "admin-email-setting-list", "Delete", "Cancel", "Delete Email");

    }

    function updateEmailStatus(val, valSetting, adminEmailId) {
        var data = {
            'adminEmailSettingModel.emailSettingId': valSetting,
            'adminEmailSettingModel.status': val,
            'adminEmailSettingModel.adminEmailId': adminEmailId
        };
        $.post("admin_email_setting_update_status.ix?reqType=json", data, function (res) {
            dialog.dialog("open");
            if (res.errorCode == "SUCCESS") {
                $("#admin-email-setting-list").html(res);
                dialog.dialog("close");
            } else {
                dialog.dialog("close");
            }
            dialog.dialog("close");
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });

    }

    function editEmail(val, email) {
        var data = {
            'adminEmailModel.id': val,
            'adminEmailModel.email': email
        };
        loadDialog("admin_email_setting_edit.ix?reqType=json", data, "admin_email_setting_edit_form", "Edit", "Cancel", "edit_email_dialog", "Edit Email", "admin-email-setting-list");

    }


</script>