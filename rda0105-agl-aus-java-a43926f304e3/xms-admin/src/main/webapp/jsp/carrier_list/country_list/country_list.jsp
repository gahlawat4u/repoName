<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Admin Country List"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Admin Country List"/></li>
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
                                    <xms:localization text="Admin Country List"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb" id="div_list_chk_option">
                                        <table class="s36">
                                            <tbody>
                                            <tr>
                                                <td><input type="checkbox" name="th_gst"
                                                           onclick="javascript:optionShowCol();"></td>
                                                <td><xms:localization text="Show GST"/></td>
                                                <td><input type="checkbox" name="th_ap_code"
                                                           onclick="javascript:optionShowCol();"></td>
                                                <td><xms:localization text="Show DHL"/></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <s:hidden id="hid_country_id_selected"/>
                                <s:hidden id="order_type" name="orderType"/>
                                <s:hidden id="order_field" name="orderField"/>
                                <s:hidden id="country_page" name="page"/>
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <b><xms:localization text="Note:"/></b> <br/>
                                                    <xms:localization
                                                            text="- Double-click a setting to modify its value.WARNING - any change to the values below will take effect immediately."/>
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
                                                                    <td><s:select id="country_page_size" name="pageSize"
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
                                                <div id="country_list_data">
                                                    <table class="table table-bordered mg0" id="country-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Country Code"/></th>
                                                            <th><xms:localization text="Country Name"/></th>
                                                            <th><xms:localization text="Display Name"/></th>
                                                            <th><xms:localization text="Other Name"/></th>
                                                            <th class="col_hidden th_gst"><xms:localization
                                                                    text="GST"/></th>
                                                            <th><xms:localization text="Last Modified"/></th>
                                                            <th><xms:localization text="Show in Country List"/></th>
                                                            <th class="col_hidden th_ap_code"><xms:localization
                                                                    text="DHL-AP Code"/></th>
                                                            <th class="col_hidden th_ap_code"><xms:localization
                                                                    text="DHL-AP Zone"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr id="adm-country-list-filter">
                                                            <td><s:textfield cssClass="form-control" name="countryCode"
                                                                             onkeyup="onSearchCountryKeyUp()"/></td>
                                                            <td><s:textfield cssClass="form-control" name="countryName"
                                                                             onkeyup="onSearchCountryKeyUp()"/></td>
                                                            <td><s:textfield cssClass="form-control" name="displayName"
                                                                             onkeyup="onSearchCountryKeyUp()"/></td>
                                                            <td><s:textfield cssClass="form-control" name="otherName"
                                                                             onkeyup="onSearchCountryKeyUp()"/></td>
                                                            <td class="col_hidden th_gst"><s:textfield
                                                                    cssClass="form-control" name="gst"
                                                                    onkeyup="onSearchCountryKeyUp()"/></td>
                                                            <td><s:textfield cssClass="form-control form_datetime"
                                                                             data-date-format="dd MM yyyy"
                                                                             name="modifiedDate"
                                                                             onchange="doSearch()"/></td>
                                                            <td>
                                                                <!--<s:checkbox name="isShow" onchange="doSearch()" />-->
                                                                &nbsp;
                                                            </td>
                                                            <td class="col_hidden th_ap_code"><s:textfield
                                                                    cssClass="form-control" name="apCode"
                                                                    onkeyup="onSearchCountryKeyUp()"/></td>
                                                            <td class="col_hidden th_ap_code"><s:textfield
                                                                    cssClass="form-control" name="apZone"
                                                                    onkeyup="onSearchCountryKeyUp()"/></td>
                                                        </tr>
                                                        <s:if test="countryLists.records.size != 0">
                                                            <s:iterator value="countryLists.records">
                                                                <tr id="edit-country-link">
                                                                    <td><s:hidden value="%{countryId}"
                                                                                  id="country_id"></s:hidden>
                                                                        <s:property value="countryCode"/></td>
                                                                    <td><s:property value="countryName"/></td>
                                                                    <td><s:property value="displayName"/></td>
                                                                    <td><s:property value="otherName"/></td>
                                                                    <td class="col_hidden th_gst"><s:property
                                                                            value="gstPercent"/></td>
                                                                    <td><s:property value="modifiedDate"/></td>
                                                                    <td><s:checkbox name="isShow"
                                                                                    countryId='%{countryId}'
                                                                                    onclick="isShow($(this).is(':checked'), $(this).attr('countryId'));"></s:checkbox></td>
                                                                    <td class="col_hidden th_ap_code"><s:property
                                                                            value="dhlApCode"/></td>
                                                                    <td class="col_hidden th_ap_code"><s:property
                                                                            value="dhlApZone"/></td>
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
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="countryLists.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="countryLists.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="countryLists.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!countryLists.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{countryLists.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator value="countryLists.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{countryLists.pageRange[#count.index] == countryLists.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="countryLists.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!countryLists.hasNext()">
                                                                    <a class="paginate_button next"
                                                                       href="#"><xms:localization text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{countryLists.currentPage+1}"/>)"><xms:localization
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
                                                    <button class="btn s37" type="button" onclick="viewState()"
                                                            id="btn_view_state">
                                                        <xms:localization text="View State/City"/>
                                                    </button>
                                                    <button class="btn s37" type="button" onclick="addNewCountry()">
                                                        <xms:localization text="Add New Country"/>
                                                    </button>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Import"/>
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
<div id="country-edit-dialog" title='<xms:localization text="Edit Country" />'></div>
<div id="country-add-dialog" title='<xms:localization text="Add Country" />'></div>
<div id="country-update-dialog" title='<xms:localization text="Update Country" />'></div>
<!--END CONTENT-->
<script type="text/javascript">
    var idCountry = "";
    $(document).ready(function () {
        if ($("#hid_country_id_selected").val() == '') {
            $("#btn_view_state").attr("disabled", true);
        } else {
            $("#btn_view_state").attr("disabled", false);
        }
        $(".col_hidden").hide();
        optionShowCol();
        var fieldList = ["countryCode", "countryName", "displayName", "otherName", "gstPercent", "modifiedDate", "isShow", "dhlApCode", "dhlApZone"];
        $("#country-list-table").tablesorter({
            sortFieldId: "order_field",
            sortTypeId: "order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });
    function onSearchCountryKeyUp() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            doSearch();
        }, 500);
        $(this).data('timer', wait);
    }
    function doSearch() {
        var data = $("#adm-country-list-filter input").serialize();
        data += "&page=" + $("#country_page").val();
        data += "&pageSize=" + $("#country_page_size option:selected").val();
        data += "&orderField=" + $("#order_field").val();
        data += "&orderType=" + $("#order_type").val();
        loadingDialog.dialog("open");
        $.post("country_list_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#country_list_data").html(res.content);
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

    function isShow(val, id) {
        loadingDialog.dialog("open");
        var data = {
            'countryModel.isShow': val,
            'countryModel.countryId': id,
            'pageSize': $("#country_page_size option:selected").val(),
            'page': $("#country_page").val()
        };
        $.post("country_list_is_show.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#country_list_data").html(res.content);
                loadingDialog.dialog("close");
            } else {
                loadingDialog.dialog("close");
            }
            loadingDialog.dialog("close");
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function optionShowCol() {
        $("#div_list_chk_option").find("input").each(function () {
            var index = $("#div_list_chk_option").find("input").index(this) - 1;
            var colToHide = $("#country-list-table").find("." + $(this).attr("name"));
            if ($(this).is(":checked")) {
                $(this).attr('checked', true);
                colToHide.show();
            } else {
                $(this).attr('checked', false);
                colToHide.hide();
            }
        });
    }

    function changePage(page) {
        $("#country_page").val(page);
        doSearch();
    }

    function addNewCountry() {
        loadDialog("country_list_add.ix?reqType=json", "", "form_countr_list_add", "Add", "Cancel", "country-add-dialog", "Add Country", "country_list_data");
    }

    function viewDetailCountry(id) {
        var data = {
            'countryId': id,
            'orderField': $("#order_field").val(),
            'orderType': $("#order_type").val()
        };
        loadDialog("country_list_edit.ix?reqType=json", data, "form_country_list_edit", "Edit", "Cancel", "country-edit-dialog", "Edit Country", "country_list_data");
    }

    function viewState() {
        location.href = "state_list.ix?countryId=" + idCountry;
    }

    $('table#country-list-table tbody tr').click(function () {
        $("#btn_view_state").prop("disabled", false);
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var countryId = $(this).find('#country_id').val();
        $("#hid_country_id_selected").val(countryId);
        idCountry = countryId;
    });

    $('table#country-list-table tbody tr').dblclick(function () {
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var countryId = $(this).find('#country_id').val();
        idCountry = countryId;
        viewDetailCountry(countryId);
    });


</script>