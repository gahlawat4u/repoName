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
        <li class="hidden"><a href="#"><xms:localization text="Service of"/> <s:property value="carrierName"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Service of"/> <s:property value="carrierName"/></li>
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
                                    <xms:localization text="Service of"/>
                                    <s:property value="carrierName"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <xms:localization text="<b>Note :</b>
													<br />
													- Service Information used for Carrier.
													<br />
													- Double-click the entry to modify its value."/>
                                                </p>
                                            </div>
                                            <div class="col-lg-12">
                                                <form id="service_list_search_form">
                                                    <s:hidden name="carrierId"/>
                                                    <s:hidden id="service_list_page" name="page"/>
                                                    <s:hidden id="service_list_order_type" name="orderType"/>
                                                    <s:hidden id="service_list_order_field" name="orderField"/>
                                                    <table class="table mg0">
                                                        <tbody>
                                                        <tr>
                                                            <th class="s42">
                                                                <table class="s36">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Show"/></td>
                                                                        <td><s:select id="service_list_page_size"
                                                                                      name="pageSize"
                                                                                      list="listPageSize"
                                                                                      cssClass="form-control"
                                                                                      cssStyle="height: 22px; padding-top: 1px;"
                                                                                      onchange="changePageSize()"/></td>
                                                                        <td><xms:localization text="Entries"/></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </th>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </form>
                                                <div id="service-list-div">
                                                    <table class="table table-bordered mg0 table-pointer"
                                                           id="service-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Id"/></th>
                                                            <th><xms:localization text="Service Name"/></th>
                                                            <th><xms:localization text="EDI Description"/></th>
                                                            <th><xms:localization text="Service Priority"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="serviceList==null || serviceList.totalRecords==0">
                                                            <tr>
                                                                <td colspan="4"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="serviceList.records">
                                                                <tr data-service-id="<s:property value="shipmentTypeId"/>"
                                                                    onclick="selectService($(this).attr('data-service-id'))"
                                                                    ondblclick="showEditDialog($(this).attr('data-service-id'))">
                                                                    <td><s:property value="shipmentTypeId"/></td>
                                                                    <td><s:property value="shipmentTypeName"/></td>
                                                                    <td><s:property value="ediDescription"/></td>
                                                                    <td><s:property value="servicePriority"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                            <tr>
                                                                <td colspan="4"><xms:localization text="Showing"/>
                                                                    <s:property value="serviceList.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="serviceList.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="serviceList.totalRecords"/>
                                                                    <xms:localization text="entries"/></td>
                                                            </tr>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <script type="text/javascript">
                                                        $(document).ready(function () {
                                                            $('table#service-list-table tbody tr').click(function () {
                                                                var serviceId = $(this).attr('data-service-id');
                                                                if (typeof (serviceId) != "undefined" && serviceId != "") {
                                                                    $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                                    $('#btnDelete').attr('disabled', false);
                                                                }
                                                            });
                                                            var fieldList = ["shipment_type_id", "shipment_type_name", "edi_description", "service_priority"];
                                                            $("#service-list-table").tablesorter({
                                                                sortFieldId: "service_list_order_field",
                                                                sortTypeId: "service_list_order_type",
                                                                fieldList: fieldList,
                                                                callback: searchServiceList
                                                            });
                                                        });
                                                    </script>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="!serviceList.hasPrev()">
                                                            <button class="paginate_button previous disabled"
                                                                    disabled="disabled">
                                                                <xms:localization text="Previous"/>
                                                            </button>
                                                        </s:if>
                                                        <s:else>
                                                            <a href="javascript:changePage(<s:property value="%{serviceList.currentPage - 1}"/>)"
                                                               class="paginate_button previous"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:else>
														<span> <s:iterator value="serviceList.pageRange" status="count">
                                                            <s:if test="%{serviceList.pageRange[#count.index] == serviceList.currentPage}">
                                                                <a class="paginate_button current"><s:property
                                                                        value="serviceList.currentPage"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a class="paginate_button"
                                                                   href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                            </s:else>
                                                        </s:iterator>
														</span>
                                                        <s:if test="!serviceList.hasNext()">
                                                            <button class="paginate_button next" disabled="disabled">
                                                                <xms:localization text="Next"/>
                                                            </button>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button next"
                                                               href="javascript:changePage(<s:property value="%{serviceList.currentPage+1}"/>)"><xms:localization
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
                                                <s:a cssClass="btn s37" action="carrier_list.ix">
                                                    <xms:localization text="Back to Carrier"/>
                                                </s:a>
                                                <button class="btn s37" type="button" onclick="deleteCarrier()"
                                                        disabled="disabled" id="btnDelete">
                                                    <xms:localization text="Delete Service"/>
                                                </button>
                                                <button id="add-service-link" class="btn s37" type="button"
                                                        onclick="showAddDialog()">
                                                    <xms:localization text="New Service"/>
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
<div id="service-dialog"></div>
<script type="text/javascript">
    var carrierId = <s:property value="carrierId"/>;
    var serviceId = "";

    function searchServiceList() {
        var data = $("#service_list_search_form").serialize();
        loadingDialog.dialog("open");
        $.post("service_list_get_data.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#service-list-div").html(res.content);
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
        $("#service_list_page").val(page);
        searchServiceList();
    }
    function changePageSize() {
        searchServiceList();
    }
    function selectService(id) {
        serviceId = id;
    }
    function showEditDialog(id) {
        var data = {
            "page": $("#service_list_page").val(),
            "pageSize": $("#service_list_page_size").val(),
            "carrierId": carrierId,
            "serviceId": id
        };
        $('#btnDelete').attr('disabled', true);
        loadDialog("service_list_edit.ix?reqType=json", data, "service-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "service-dialog", "<xms:localization text="Edit service" />", "service-list-div");
    }
    function showAddDialog() {
        var data = {
            "page": $("#service_list_page").val(),
            "pageSize": $("#service_list_page_size").val(),
            "carrierId": carrierId
        };
        $('#btnDelete').attr('disabled', true);
        loadDialog("service_list_add.ix?reqType=json", data, "service-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "service-dialog", "<xms:localization text="Add new service" />", "service-list-div");
    }
    function deleteCarrier() {
        var data = {
            "page": $("#service_list_page").val(),
            "pageSize": $("#service_list_page_size").val(),
            "carrierId": carrierId,
            "serviceId": serviceId
        };
        $('#btnDelete').attr('disabled', true);
        loadDeleteDialog("service_list_delete.ix?reqType=json", data, "<xms:localization text="Are you sure to delete serviceId " />" + serviceId, "service-dialog", "service-list-div", "<xms:localization text="Delete" />", "<xms:localization text="Cancel" />",
                "<xms:localization text="Delete service" />");
    }
</script>
<!--END CONTENT-->
