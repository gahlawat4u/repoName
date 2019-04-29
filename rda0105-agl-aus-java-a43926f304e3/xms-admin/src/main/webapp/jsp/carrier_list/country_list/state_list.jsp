<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>">Home</a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#">State List</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active">State List</li>
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
                                    <xms:localization text="State List"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <b><xms:localization text="Note :"/></b> <br/>
                                                    <xms:localization
                                                            text="- Double-click a setting to modify its value.WARNING - any change to the values below will take effect immediately."/>
                                                </p>
                                            </div>
                                            <div class="col-lg-12">
                                                <s:hidden name="countryId" id="hid_country_id"/>
                                                <s:hidden name="stateId" id="hid_state_id"/>
                                                <s:hidden id="state_page" name="page"/>
                                                <s:hidden id="state_list_order_field" name="orderField"/>
                                                <s:hidden id="state_list_order_type" name="orderType"/>
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select id="state_page_size" name="pageSize"
                                                                                  list="pageSizes"
                                                                                  onchange="doSearch()"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="state_list_data">
                                                    <table class="table table-bordered mg0 datatable1" id="datatable1">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="ID"/></th>
                                                            <th><xms:localization text="State Name"/></th>
                                                            <th><xms:localization text="State Code"/></th>
                                                            <th><xms:localization text="City Name"/></th>
                                                            <th><xms:localization text="City Code"/></th>
                                                            <th><xms:localization text="Postal Code (From)"/></th>
                                                            <th><xms:localization text="Postal Code (To)"/></th>
                                                            <th><xms:localization text="DHL Zone Code"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr id="adm-state-list-filter">
                                                            <td><s:textfield name="id" cssClass="form-control"
                                                                             onkeyup="onKeyUpStateSearch()"/></td>
                                                            <td><s:textfield name="stateName" cssClass="form-control"
                                                                             onkeyup="onKeyUpStateSearch()"/></td>
                                                            <td><s:textfield name="stateCode" cssClass="form-control"
                                                                             onkeyup="onKeyUpStateSearch()"/></td>
                                                            <td><s:textfield name="cityName" cssClass="form-control"
                                                                             onkeyup="onKeyUpStateSearch()"/></td>
                                                            <td><s:textfield name="cityCode" cssClass="form-control"
                                                                             onkeyup="onKeyUpStateSearch()"/></td>
                                                            <td><s:textfield name="postCodeFrom" cssClass="form-control"
                                                                             onkeyup="onKeyUpStateSearch()"/></td>
                                                            <td><s:textfield name="postCodeTo" cssClass="form-control"
                                                                             onkeyup="onKeyUpStateSearch()"/></td>
                                                            <td><s:textfield name="dhlZoneCode" cssClass="form-control"
                                                                             onkeyup="onKeyUpStateSearch()"/></td>
                                                        </tr>
                                                        <s:if test="stateLists.records.size != 0">
                                                            <s:iterator value="stateLists.records">
                                                                <tr id="edit-state-link">
                                                                    <td><s:hidden value="%{id}"
                                                                                  id="state_id"></s:hidden> <s:property
                                                                            value="id"/></td>
                                                                    <td><s:property value="stateName"/></td>
                                                                    <td><s:property value="stateCode"/></td>
                                                                    <td><s:property value="cityName"/></td>
                                                                    <td><s:property value="cityCode"/></td>
                                                                    <td><s:property value="fromPostCode"/></td>
                                                                    <td><s:property value="toPostCode"/></td>
                                                                    <td><s:property value="dhlZone"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:if>
                                                        <s:else>
                                                            <tr>
                                                                <td colspan="8"><xms:localization
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
                                                                        value="stateLists.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="stateLists.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="stateLists.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!stateLists.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{stateLists.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator value="stateLists.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{stateLists.pageRange[#count.index] == stateLists.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="stateLists.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!stateLists.hasNext()">
                                                                    <a class="paginate_button next"
                                                                       href="#"><xms:localization text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{stateLists.currentPage+1}"/>)"><xms:localization
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
                                                <button id="" class="btn s37" type="button"
                                                        onclick="javascript:backToCountry();">
                                                    <xms:localization text="Back to Country"/>
                                                </button>
                                                <button class="btn s37" type="button" onclick="addNewState()">
                                                    <xms:localization text="Add"/>
                                                </button>
                                                <button class="btn s37" type="button" id="btn_del"
                                                        onclick="deleteState()">
                                                    <xms:localization text="Delete"/>
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
<div id="state-edit-dialog" title='<xms:localization text="Edit State" />'></div>
<div id="state-add-dialog" title='<xms:localization text="Add State" />'></div>
<div id="state-update-dialog" title='<xms:localization text="Update State" />'></div>
<!--END CONTENT-->
<script type="text/javascript">
    $(document).ready(function () {
        if ($("#hid_state_id").val() == '') {
            $("#btn_del").prop("disabled", true);
        } else {
            $("#btn_del").prop("disabled", false);
        }
        var fieldList = ["id", "state_name", "state_code", "city_name", "city_code", "from_postcode", "to_postcode", "dhl_zone"];
        $("#datatable1").tablesorter({
            sortFieldId: "state_list_order_field",
            sortTypeId: "state_list_order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });
    function backToCountry() {
        var win = window.open("country_list.ix", '_self');
        win.focus();
    }

    function doSearch() {
        var orderField = $("#state_list_order_field").val();
        var orderType = $("#state_list_order_type").val();
        var data = $("#adm-state-list-filter input").serialize();
        data += "&countryId=" + $("#hid_country_id").val();
        data += "&page=" + $("#state_page").val();
        data += "&pageSize=" + $("#state_page_size option:selected").val();
        data += "&orderField=" + orderField;
        data += "&orderType=" + orderType;
        loadingDialog.dialog("open");
        $.post("state_list_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#state_list_data").html(res.content);
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
    function onKeyUpStateSearch() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            doSearch();
        }, 500);
        $(this).data('timer', wait);
    }
    function changePage(page) {
        $("#state_page").val(page);
        doSearch();
    }

    function addNewState() {
        var data = {
            'countryId': $("#hid_country_id").val()
        };
        loadDialog("state_list_add.ix?reqType=json", data, "form_state_list_add", "Add", "Cancel", "state-add-dialog", "Add State", "state_list_data");
    }

    function deleteState() {
        var data = {
            'stateId': $("#hid_state_id").val(),
            'countryId': $("#hid_country_id").val()
        };
        loadDeleteDialog("state_list_delete.ix?reqType=json", data, "Are you sure delete state?", "state-add-dialog", "state_list_data", "Delete", "Cancel", "Delete State");
    }

    function viewDetailState(id) {
        var data = {
            'stateId': id,
            'countryId': $("#hid_country_id").val()
        };
        loadDialog("state_list_edit.ix?reqType=json", data, "form_state_list_add", "Edit", "Cancel", "state-edit-dialog", "Edit State", "state_list_data");
    }

    $('table#datatable1 tbody tr').click(function () {
        $("#btn_del").prop("disabled", false);
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var stateId = $(this).find('#state_id').val();
        $("#hid_state_id").val(stateId);
    });

    $('table#datatable1 tbody tr').dblclick(function () {
        var stateId = $(this).find('#state_id').val();
        viewDetailState(stateId);
    });


</script>