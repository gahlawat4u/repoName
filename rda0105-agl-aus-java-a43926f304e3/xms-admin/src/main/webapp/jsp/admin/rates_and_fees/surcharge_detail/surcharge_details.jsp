<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Accessorial Detail"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Accessorial Detail"/></li>
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
                                    <xms:localization text="Details of"/>
                                    <s:property value="accessorialName"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42">
                                                            <form id="acc_details_search_form">
                                                                <s:hidden id="accessorialId" name="accessorialId"/>
                                                                <s:hidden id="acc_details_page" name="page"/>
                                                                <s:hidden id="acc_details_order_type" name="orderType"/>
                                                                <s:hidden id="acc_details_order_field"
                                                                          name="orderField"/>
                                                                <table class="s36">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Show"/></td>
                                                                        <td><s:select id="acc_details_page_size"
                                                                                      name="pageSize"
                                                                                      list="listPageSize"
                                                                                      cssClass="form-control"
                                                                                      onchange="doSearchAccDetails()"/></td>
                                                                        <td><xms:localization text="Entries"/></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </form>
                                                        </th>
                                                    </tr>
                                                </table>
                                                <div id="accessorial-detail-div">
                                                    <table class="table table-bordered mg0 table-hover table-pointer"
                                                           id="accessorial-detail-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Default Charge"/></th>
                                                            <th><xms:localization text="Default Carrier Charge"/></th>
                                                            <th><xms:localization text="Apply Start Date"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="accessorialDetailList.records.isEmpty()">
                                                            <tr>
                                                                <td colspan="4"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="accessorialDetailList.records"
                                                                        status="count">
                                                                <tr data-apply-start-date="<s:property value="applyStartDate" />"
                                                                    ondblclick="showEditDialog('<s:property
                                                                            value="applyStartDate"/>')">
                                                                    <td><s:property value="defaultCharge"/></td>
                                                                    <td><s:property value="defaultChargeCarrier"/></td>
                                                                    <td><s:property value="applyStartDate"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <script type="text/javascript">
                                                        $(document).ready(function () {
                                                            $('table#accessorial-detail-table tbody tr').click(function () {
                                                                var applyDate = $(this).attr('data-apply-start-date');
                                                                if (typeof (applyDate) != "undefined" && applyDate != "") {
                                                                    $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                                    selectAccessorialDetail(applyDate);
                                                                    $('#btnRemove').attr('disabled', false);
                                                                }
                                                            });
                                                            var fieldList = ["default_charge", "default_charge_carrier", "apply_start_date"];
                                                            $("#accessorial-detail-table").tablesorter({
                                                                sortFieldId: "acc_details_order_field",
                                                                sortTypeId: "acc_details_order_type",
                                                                fieldList: fieldList,
                                                                callback: doSearchAccDetails
                                                            });
                                                        });
                                                    </script>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="accessorialDetailList.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="accessorialDetailList.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="accessorialDetailList.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!accessorialDetailList.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{accessorialDetailList.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator
                                                                        value="accessorialDetailList.pageRange"
                                                                        status="count">
                                                                    <s:if test="%{accessorialDetailList.pageRange[#count.index] == accessorialDetailList.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="accessorialDetailList.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!accessorialDetailList.hasNext()">
                                                                    <a class="paginate_button next"
                                                                       href="#"><xms:localization text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{accessorialDetailList.currentPage+1}"/>)"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions pal pdt10">
                                            <div class="row">
                                                <div class="col-lg-12 text-right">
                                                    <a class="btn s37" href="surcharge_list.ix"><xms:localization
                                                            text="Back To Accessorial"/></a>
                                                    <button class="btn s37" id="btnRemove" type="button"
                                                            onclick="deleteAccessorialDetail()" disabled="disabled">
                                                        <xms:localization text="Delete Accessorial Detail"/>
                                                    </button>
                                                    <button class="btn s37" type="button" onclick="showAddDialog()">
                                                        <xms:localization text="New Accessorial Detail"/>
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
</div>
<div id="accessorial-detail-dialog"></div>
<script type="text/javascript">
    var applyStartDate = "";
    function changePage(page) {
        $("#acc_details_page").val(page);
        doSearchAccDetails();
    }
    function selectAccessorialDetail(date) {
        applyStartDate = date;
    }
    function showEditDialog(date) {
        var data = {
            "page": $("#acc_details_page").val(),
            "pageSize": $("#acc_details_page_size").val(),
            "accessorialId": $("#accessorialId").val(),
            "applyStartDate": date
        };
        loadDialog("surcharge_details_edit.ix?reqType=json", data, "accessorial-detail-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "accessorial-detail-dialog", "<xms:localization text="Edit detail" />", "accessorial-detail-div");
    }
    function showAddDialog() {
        var data = {
            "page": $("#acc_details_page").val(),
            "pageSize": $("#acc_details_page_size").val(),
            "accessorialId": $("#accessorialId").val()
        };
        loadDialog("surcharge_details_add.ix?reqType=json", data, "accessorial-detail-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "accessorial-detail-dialog", "<xms:localization text="Add new detail" />", "accessorial-detail-div");
    }
    function deleteAccessorialDetail() {
        var data = {
            "page": $("#acc_details_page").val(),
            "pageSize": $("#acc_details_page_size").val(),
            "accessorialId": $("#accessorialId").val(),
            "applyStartDate": applyStartDate
        };
        loadDeleteDialogWithCallBack("surcharge_details_delete.ix?reqType=json", data, "<xms:localization text="Are you sure to delete accessorial detail for " />" + applyStartDate, "accessorial-detail-dialog", "accessorial-detail-div", "<xms:localization text="Delete" />",
                "<xms:localization text="Cancel" />", "<xms:localization text="Delete accessorial detail" />", function () {
                    applyStartDate = "";
                    $("#btnRemove").attr("disabled", true);
                });

    }
    function doSearchAccDetails() {
        var data = $("#acc_details_search_form").serialize();
        loadingDialog.dialog("open");
        $.post("surcharge_details_get_data.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#accessorial-detail-div").html(res.content);
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
</script>
<!--END CONTENT-->
