<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<xms:localization text="Home"/>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="hidden"><a href="#"><xms:localization text="Carriers"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Carriers"/></li>
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
                                    <xms:localization text="Carriers"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <xms:localization text="<b>Note :</b>
													<br />
													- The list of Carriers
													<br />
													- Double-click the entry to modify its value."/>
                                                </p>
                                            </div>
                                            <div class="col-lg-12">
                                                <form id="carrier_list_search_form">
                                                    <s:hidden id="carrier_list_page" name="page"/>
                                                    <s:hidden id="carrier_list_order_type" name="orderType"/>
                                                    <s:hidden id="carrier_list_order_field" name="orderField"/>
                                                    <table class="table mg0">
                                                        <tbody>
                                                        <tr>
                                                            <th class="s42">
                                                                <table class="s36">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Show"/></td>
                                                                        <td><s:select id="carrier_list_page_size"
                                                                                      name="pageSize"
                                                                                      list="listPageSize"
                                                                                      cssClass="form-control"
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
                                                <div id="carrier-list-div">
                                                    <table class="table table-bordered mg0 table-pointer"
                                                           id="carrier-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Id"/></th>
                                                            <th><xms:localization text="Carrier Name"/></th>
                                                            <th><xms:localization text="Active"/></th>
                                                            <th><xms:localization text="Non Centralized"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="carrierList==null || carrierList.totalRecords==0">
                                                            <tr>
                                                                <td colspan="4"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="carrierList.records">
                                                                <tr data-carrier-id="<s:property value="serviceId"/>"
                                                                    ondblclick="showEditDialog('<s:property
                                                                            value="serviceId"/>')">
                                                                    <td><s:property value="serviceId"/></td>
                                                                    <td><s:property value="serviceName"/></td>
                                                                    <td><input type="checkbox" disabled="disabled"
                                                                               <s:if test="inactive == 0">checked="checked"</s:if> />
                                                                    </td>
                                                                    <td><input type="checkbox" disabled="disabled"
                                                                               <s:if test="nonCentralized == 1">checked="checked"</s:if> />
                                                                    </td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="carrierList.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="carrierList.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="carrierList.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!carrierList.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{carrierList.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator value="carrierList.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{carrierList.pageRange[#count.index] == carrierList.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="carrierList.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!carrierList.hasNext()">
                                                                    <a class="paginate_button next disabled"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{carrierList.currentPage+1}"/>)"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions pal pdt10">
                                        <div class="row">
                                            <div class="col-lg-12 text-right">
                                                <button class="btn s37" id="btnView" disabled="disabled"
                                                        onclick="viewService()">
                                                    <xms:localization text="View Service"/>
                                                </button>
                                                <button id="add-carriers-link" class="btn s37" type="button"
                                                        onclick="showAddDialog()">
                                                    <xms:localization text="New Carriers"/>
                                                </button>
                                                <button class="btn s37" id="btnDelete" disabled="disabled"
                                                        onclick="deleteCarrier()">
                                                    <xms:localization text="Delete Carrier"/>
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
<div id="carrier-dialog"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#carrier-list-table tbody tr').click(function () {
            var carrierId = $(this).attr('data-carrier-id');
            if (typeof (carrierId) != "undefined" && carrierId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectCarrier(carrierId);
                $('#btnView').attr('disabled', false);
                $('#btnDelete').attr('disabled', false);
            }
        });
        var fieldList = ["service_id", "service_name", "inactive", "non_centralized"];
        $("#carrier-list-table").tablesorter({
            sortFieldId: "carrier_list_order_field",
            sortTypeId: "carrier_list_order_type",
            fieldList: fieldList,
            callback: searchCarrierList
        });
    });
    var carrierId = 0;

    function searchCarrierList() {
        var data = $("#carrier_list_search_form").serialize();
        loadingDialog.dialog("open");
        $.post("carrier_list_get_data.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#carrier-list-div").html(res.content);
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
        $("#carrier_list_page").val(page);
        searchCarrierList()
    }

    function changePageSize() {
        searchCarrierList()
    }

    function selectCarrier(id) {
        carrierId = id;
    }

    function showEditDialog(id) {
        var data = {
            "page": $("#carrier_list_page").val(),
            "pageSize": $("#carrier_list_page_page").val(),
            "carrierId": id
        };
        loadDialog("carrier_list_edit.ix?reqType=json", data, "carrier-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "carrier-dialog", "<xms:localization text="Edit detail" />", "carrier-list-div");
    }

    function showAddDialog() {
        var data = {
            "page": $("#carrier_list_page").val(),
            "pageSize": $("#carrier_list_page_page").val(),
        };
        loadDialog("carrier_list_add.ix?reqType=json", data, "carrier-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "carrier-dialog", "<xms:localization text="Add new detail" />", "carrier-list-div");
    }

    function deleteCarrier() {
        var data = {
            "page": $("#carrier_list_page").val(),
            "pageSize": $("#carrier_list_page_page").val(),
            "carrierId": carrierId,
        };
        loadDeleteDialog("carrier_list_delete.ix?reqType=json", data, "<xms:localization text="Are you sure to delete carrierId " />" + carrierId, "carrier-dialog", "carrier-list-div", "<xms:localization text="Delete" />", "<xms:localization text="Cancel" />",
                "<xms:localization text="Delete carrier" />");
    }

    function viewService() {
        if (carrierId == 0) {
            alertDialog.html("<xms:localization text="Please select a row to view detail." />");
            alertDialog.dialog("open");
            return false;
        }
        var url = "service_list.ix?carrierId=" + carrierId;
        window.location.href = url;
    }
</script>
<!--END CONTENT-->
