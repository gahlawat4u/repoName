<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>">Home</a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Rate Sheets"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Rate Sheets"/></li>
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
                                    <xms:localization text="Rate Sheets"/>
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
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select id="rate-sheet-page-sizes"
                                                                                  list="listPageSize"
                                                                                  onchange="changePageSize($(this).val())"
                                                                                  cssClass="form-control"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                </table>
                                                <div id="rate-sheets-div">
                                                    <table class="table table-bordered mg0 table-hover table-pointer"
                                                           id="rate-sheets-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Name"/></th>
                                                            <th><xms:localization text="Carrier"/></th>
                                                            <th><xms:localization text="Shipment Type"/></th>
                                                            <th><xms:localization text="Type"/></th>
                                                            <th><xms:localization text="Date Imported"/></th>
                                                            <th><xms:localization text="Total Number of Cells"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr id="search-filter">
                                                            <td><s:textfield name="rateSheetFilter.rateSheetName"
                                                                             cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="rateSheetFilter.carrierName"
                                                                             cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="rateSheetFilter.serviceName"
                                                                             cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:select list="listRateSheetsType"
                                                                          name="rateSheetFilter.type"
                                                                          cssClass="form-control"
                                                                          onchange="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="rateSheetFilter.importDate"
                                                                             cssClass="form-control form_datetime"
                                                                             onchange="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="rateSheetFilter.totalCells"
                                                                             cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                        </tr>
                                                        <s:if test="rateSheetsList.records.isEmpty()">
                                                            <tr id="view-rate-sheet-link">
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available"/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="rateSheetsList.records">
                                                                <tr data-sheet-id='<s:property value="sheetId"/>'
                                                                    data-rate-sheet-name='<s:property value="rateSheetName"/>'
                                                                    ondblclick="showRateSheet($(this).attr('data-sheet-id'))">
                                                                    <td><s:property value="rateSheetName"/></td>
                                                                    <td><s:property value="carrierName"/></td>
                                                                    <td><s:property value="serviceName"/></td>
                                                                    <td><s:property value="type"/></td>
                                                                    <td><s:property value="importDate"/></td>
                                                                    <td><s:property value="totalCells"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <script type="text/javascript">
                                                        $(document).ready(function () {
                                                            $('table#rate-sheets-table tbody tr').click(function () {
                                                                var sheetId = $(this).attr('data-sheet-id');
                                                                var rateSheetName = $(this).attr('data-rate-sheet-name');
                                                                if (typeof (sheetId) != "undefined" && sheetId != "") {
                                                                    $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                                    $('#btnRemove').attr('disabled', false);
                                                                }
                                                            });
                                                        });
                                                    </script>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="rateSheetsList.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="rateSheetsList.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="rateSheetsList.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!rateSheetsList.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{rateSheetsList.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator value="rateSheetsList.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{rateSheetsList.pageRange[#count.index] == rateSheetsList.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="rateSheetsList.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!rateSheetsList.hasNext()">
                                                                    <a class="paginate_button next"
                                                                       href="#"><xms:localization text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{rateSheetsList.currentPage+1}"/>)"><xms:localization
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
                                                    <button class="btn s37" type="button" id="btnRemove"
                                                            disabled="disabled" onclick="deleteRateSheet()">
                                                        <xms:localization text="Delete Selected Rate Sheet"/>
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
<div id="rate-sheet-dialog"></div>
<script type="text/javascript">
    var page = 1;
    var pageSize = $("#rate-sheet-page-sizes").val();
    var sheetId = 0;
    var rateSheetName = "";
    var order = 0;
    var modifiedData = {};
    function changePage(p) {
        page = p;
        searhRateSheets(p);
    }
    function changePageSize(ps) {
        pageSize = ps;
        searhRateSheets(p);
    }

    function searhRateSheets(p) {
        page = p;
        var data = "page=" + page + "&pageSize=" + pageSize;
        data += "&" + $("#search-filter input").serialize() + "&" + $("#search-filter select").serialize();
        doPostDataByParameters("rate_sheets_get_data.ix?reqType=json", data, "", "rate-sheets-div", false);
    }
    function selectRateSheet(id, name) {
        sheetId = id;
        rateSheetName = name;
    }
    function showRateSheet(id ) {
        orgValue = "";
        oldRowId = "";
        oldColumnId = "";
        var data = {
            "page": page,
            "pageSize": pageSize,
            "sheetId": id,
        };
        loadRateSheetDialog("rate_sheets_view_detail.ix?reqType=json", "rate_sheets_save_rate_sheet.ix?reqType=json", data, "rate-sheet-detail-form", "<xms:localization text="Modified rate sheet" />", "<xms:localization text="Cancel" />", "rate-sheet-dialog", "<xms:localization text="View rate sheet" />");
    }
    function deleteRateSheet() {
        $("#btnRemove").attr("disabled", true);
        var data = {
            "page": page,
            "pageSize": pageSize,
            "sheetId": sheetId
        };
        loadDeleteDialog("rate_sheets_delete.ix?reqType=json", data, "<xms:localization text="Are you sure to delete rate sheet " />" + rateSheetName + "?", "rate-sheet-dialog", "rate-sheets-div", "<xms:localization text="Delete" />", "<xms:localization text="Cancel" />", "<xms:localization text="Delete rate sheet" />");
        $('table#rate-sheets-table tbody tr.selected-row').removeClass('selected-row');
    }

    function changeValue(rowId, columnId) {
        var selector = "table#rate-sheet-details-table tbody tr td[data-row-id='" + rowId + "'][data-column-id='" + columnId + "']";
        var oldValue = $(selector).attr('data-old-value');
        var value = $(selector + " input").val();
        if (value != oldValue) {
            if (modifiedData.hasOwnProperty(rowId + "-" + columnId)) {
                modifiedData["detailsList[" + modifiedData[rowId + "-" + columnId] + "].value"] = value;
            } else {
                modifiedData["detailsList[" + order + "].columnId"] = columnId;
                modifiedData["detailsList[" + order + "].rowId"] = rowId;
                modifiedData["detailsList[" + order + "].value"] = value;
                modifiedData[rowId + "-" + columnId] = order;
                order++;
            }
        }
        $(selector + " label").html(value);
        showRateSheetInput(rowId, columnId, true);
    }

    function showRateSheetInput(rowId, columnId, hide) {
        var selector = "table#rate-sheet-details-table tbody tr td[data-row-id='" + rowId + "'][data-column-id='" + columnId + "']";
        if (!hide) {
            $(selector + " input").attr("disabled", false);
            $(selector + " input").show().focus();
            $(selector + " label").hide();
        } else {
            $(selector + " input").attr("disabled", true);
            $(selector + " input").hide();
            $(selector + " label").show();
        }
    }

    function loadRateSheetDialog(action, saveAction, data, formId, btnSave, btnCancel, dialogId, title) {
        resetData();
        loadingDialog.dialog("open");
        var buttons = {};
        buttons[btnSave] = function () {
            console.log(modifiedData);
            loadingDialog.dialog("open");
            $.post(saveAction, modifiedData, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    messageDialog.html("<xms:localization text="Edit success!" />");
                    messageDialog.dialog("open");
                    dialog.dialog("close");
                } else if (res.errorCode == "FIELD_ERROR") {
                    dialog.html(res.content);
                } else if (res.errorCode == "ACTION_ERROR") {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                alertDialog.dialog("open");
            });
        };
        buttons[btnCancel] = function () {
            $(this).dialog("close");
        }
        var height = $(window).height();
        var width = $(window).width();
        height = height * 0.70;
        width = width * 0.9;
        var dialog = $("#" + dialogId).dialog({
            modal: true,
            autoOpen: false,
            buttons: buttons,
            width: 'auto',
            height: 'auto',
            maxHeight: height,
            create: function (event, ui) {
                $(this).css("maxWidth", width);
            },
            show: {
                effect: "fade",
                duration: 300
            },
            close: function (e) {
                $("#" + dialogId).html("");
            }
        });

        if (typeof title != "undefined" && title != "") {
            dialog.dialog("option", "title", title);
        }

        $.post(action, data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                dialog.html(res.content);
                dialog.dialog("open");
            } else {
                alertDialog.html('Error: ' + res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }
    function resetData() {
        order = 0;
        modifiedData = {};
    }

    function searchOnKeypress() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            searhRateSheets(1);
        }, 500);
        $(this).data('timer', wait);
    }


</script>