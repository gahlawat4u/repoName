<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>">Home</a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#">System Settings</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active">System Settings</li>
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
                                    <xms:localization text="System Settings"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <xms:localization
                                                            text="<b>Warning :</b> <br /> - changing any of the below values can adversely affect the system! <br /> - Double-click the entry to modify its value."/>
                                                    <br/>
                                                </p>
                                            </div>
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
                                                <form id="order-form">
                                                    <s:hidden id="order_type" name="orderType"/>
                                                    <s:hidden id="order_field" name="orderField"/>
                                                </form>
                                                <div id="admin-system-setting-list">
                                                    <table class="table table-bordered mg0" id="system-setting-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Name"/></th>
                                                            <th><xms:localization text="Value"/></th>
                                                            <th><xms:localization text="Edit Admin Level"/></th>
                                                            <th><xms:localization text="Description"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="systemSettingListModel.records.size != 0">
                                                            <s:iterator value="systemSettingListModel.records">
                                                                <tr>

                                                                    <td><s:hidden value="%{settingId}"
                                                                                  id="setting_id"></s:hidden>
                                                                        <s:property value="settingName"/></td>
                                                                    <td><s:property value="settingValue"/></td>
                                                                    <td><s:property value="userLevel"/></td>
                                                                    <td><s:property value="description"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:if>
                                                        <s:else>
                                                            <tr>
                                                                <td colspan="4"><xms:localization
                                                                        text="No record to view"/> ...
                                                                </td>
                                                            </tr>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="systemSettingListModel.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="systemSettingListModel.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="systemSettingListModel.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:hidden value="%{systemSettingListModel.currentPage}"
                                                                          id="hid_curren_page"></s:hidden>
                                                                <s:if test="!systemSettingListModel.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{systemSettingListModel.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator
                                                                        value="systemSettingListModel.pageRange"
                                                                        status="count">
                                                                    <s:if test="%{systemSettingListModel.pageRange[#count.index] == systemSettingListModel.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="systemSettingListModel.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!systemSettingListModel.hasNext()">
                                                                    <a class="paginate_button next"
                                                                       href="#"><xms:localization text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{systemSettingListModel.currentPage+1}"/>)"><xms:localization
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
    </div>
</div>
<!--END CONTENT-->
<div id="edit_setting_dialog"></div>
<!--END CONTENT-->
<script type="text/javascript">
    $(document).ready(function () {
        var page = 1;
        var pageSize = $("#pageSize").val();
        //doSearch(pageSize, page);

        var fieldList = ["setting_name", "setting_value", "user_level", "description"];
        $("#system-setting-table").tablesorter({
            sortFieldId: "order_field",
            sortTypeId: "order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });
    function doSearch(ps, p) {
        page = typeof (p) != "undefined" ? p : 1;
        pageSize = typeof (ps) != "undefined" ? ps : $("#pageSize").val();
        var data = "page=" + page;
        data += "&pageSize=" + pageSize;
        data += "&" + $("#order-form").serialize();
        loadingDialog.dialog("open");
        $.post("admin_system_setting_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#admin-system-setting-list").html(res.content);
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

    function editEmail(val, email) {
        loadDialog("admin_system_setting_edit.ix?reqType=json", "", "form_edit_system_setting", "Edit System Setting", "Cancel", "edit_setting_dialog", "Edit System Setting", "admin-system-setting-list");
    }
    function viewDetailstate(id) {
        var data = {
            'settingId': id,
            'page': $('#hid_curren_page').val(),
            'pageSize': $("select[name='pageSize'] option:selected").val()
        };
        loadDialog("admin_system_setting_edit.ix?reqType=json", data, "form_edit_system_setting", "Edit", "Cancel", "edit_setting_dialog", "Edit System Setting", "admin-system-setting-list");
    }

    $('table#system-setting-table tbody tr').dblclick(function () {
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var settingId = $(this).find('#setting_id').val();
        viewDetailstate(settingId);
    });


</script>