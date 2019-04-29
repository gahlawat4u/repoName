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
        <li class="hidden"><a href="#"><xms:localization text="Admin System Permissions"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Admin System Permissions"/></li>
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
                                    <xms:localization text="Admin System Permissions"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive" style="height: 650px !important;">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select name="pageSize" list="pageSizes"
                                                                                  onchange="onChangePageSize()"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="data_permission">
                                                    <table class="table table-bordered mg0">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Setting"/></th>
                                                            <th><xms:localization text="Corporate Admin"/></th>
                                                            <th><xms:localization
                                                                    text="Franchise/Group Owner or Agent"/></th>
                                                            <th><xms:localization text="Accounting"/></th>
                                                            <th><xms:localization text="Sales Manager"/></th>
                                                            <th><xms:localization text="Sales Rep"/></th>
                                                            <th><xms:localization text="Telemarketer"/></th>
                                                            <th><xms:localization text="Carrier (e.g. DHL) Login"/></th>
                                                            <th><xms:localization
                                                                    text="Carriers Services Failures"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="permissionModels.records.size != 0">
                                                            <s:iterator value="permissionModels.records">
                                                                <tr>
                                                                    <td><s:property value="setting"/></td>
                                                                    <td><s:checkbox name="adminPermission" userLevel="2"
                                                                                    perId="%{perId}"
                                                                                    onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                                                                    <td><s:checkbox name="franchisePermission"
                                                                                    userLevel="3" perId="%{perId}"
                                                                                    onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                                                                    <td><s:checkbox name="accountPermission"
                                                                                    userLevel="4" perId="%{perId}"
                                                                                    onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                                                                    <td><s:checkbox name="salePermission" userLevel="8"
                                                                                    perId="%{perId}"
                                                                                    onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                                                                    <td><s:checkbox name="saleRepPermission"
                                                                                    userLevel="11" perId="%{perId}"
                                                                                    onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                                                                    <td><s:checkbox name="telePermission" userLevel="9"
                                                                                    perId="%{perId}"
                                                                                    onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                                                                    <td><s:checkbox name="carrierPermission"
                                                                                    userLevel="6" perId="%{perId}"
                                                                                    onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                                                                    <td><s:checkbox name="carrierServicePermission"
                                                                                    userLevel="7" perId="%{perId}"
                                                                                    onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:if>
                                                        <s:else>
                                                            <tr>
                                                                <td colspan="9"><xms:localization
                                                                        text="No record to view"/> ...
                                                                </td>
                                                            </tr>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate">
                                                        <s:hidden value="%{permissionModels.currentPage}"
                                                                  id="hid_curren_page"></s:hidden>
                                                        <s:if test="!permissionModels.hasPrev()">
                                                            <a class="paginate_button previous disabled"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a href="javascript:changePage(<s:property value="%{permissionModels.currentPage - 1}"/>)"
                                                               class="paginate_button previous"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:else>
														<span> <s:iterator value="permissionModels.pageRange"
                                                                           status="count">
                                                            <s:if test="%{permissionModels.pageRange[#count.index] == permissionModels.currentPage}">
                                                                <a class="paginate_button current"><s:property
                                                                        value="permissionModels.currentPage"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a class="paginate_button"
                                                                   href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                            </s:else>
                                                        </s:iterator>
														</span>
                                                        <s:if test="!permissionModels.hasNext()">
                                                            <a class="paginate_button next" href="#"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button next"
                                                               href="javascript:changePage(<s:property value="%{permissionModels.currentPage+1}"/>)"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:else>
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
    </div>
</div>
<!--END CONTENT-->
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
        $.post("admin_permission_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#data_permission").html(res.content);
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

    function changePage(page) {
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, page);
    }

    function onChangePageSize() {
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, 1);
    }

    function updatePermission(val, valSetting, userLevel) {
        var data = {
            'levelPermissionModel.permissionId': valSetting,
            'statusUpdate': val,
            'levelPermissionModel.userLevelId': userLevel,
            'levelPermissionModel.franchiseCode': '0',
            'pageSize': $("select[name='pageSize'] option:selected").val(),
            'page': $("#hid_curren_page").val()
        };
        loadingDialog.dialog("open");
        $.post("admin_permission_update_status.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#data_permission").html(res.content);
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


</script>