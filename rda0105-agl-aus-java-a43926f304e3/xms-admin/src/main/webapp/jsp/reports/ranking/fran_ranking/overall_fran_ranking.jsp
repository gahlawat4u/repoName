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
        <li class="hidden"><a href="#"><xms:localization text="Overall Franchise Rankings"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Overall Franchise Rankings"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<script type="text/javascript">
    var hasRecords = false;
</script>
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
                                    <xms:localization text="Overall Franchise Rankings"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group" id="btn-export" style="display: none;">
                                            <input type="button" id="export-option" class="btn s37"
                                                   value="<xms:localization text="Option" />"
                                                   onclick="exportClick($('#selected-option').val())"/>
                                            <button type="button" class="btn s37 dropdown-toggle" data-toggle="dropdown"
                                                    aria-expanded="true">
                                                <span class="caret"></span>
                                            </button>
                                            <s:hidden id="selected-option"/>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                <li><a href="#" onclick="exportClick('option')"><xms:localization
                                                        text="Option"/></a></li>
                                                <li><a href="#" onclick="exportClick('html')"><xms:localization
                                                        text="Print"/></a></li>
                                                <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                        text="Export"/></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12 ">
                                                <div class="form-group ">
                                                    <table class="s36 b24">
                                                        <tbody>
                                                        <tr>
                                                            <td><xms:localization text="Ranked by Gross Margin"/></td>
                                                            <td>|</td>
                                                            <td><xms:localization text="Period Type"/></td>
                                                            <td><select id="periodType" class="form-control"
                                                                        onchange="onPeriodTypeChange()">
                                                                <option value="1"><xms:localization
                                                                        text="Billing Cycle"/></option>
                                                                <option value="2"><xms:localization
                                                                        text="Monthly"/></option>
                                                                <option value="3"><xms:localization
                                                                        text="Custom date range"/></option>
                                                            </select></td>
                                                            <td><xms:localization text="Period"/></td>
                                                            <td>
                                                                <div id="dateRangeResult">
                                                                    <s:select id="dateRange" name="dateRange"
                                                                              list="dateRange" value=""
                                                                              cssClass="form-control"
                                                                              onchange="onDateRangeChange()"/>
                                                                </div>
                                                                <div id="customDateRange" style="display: none;">
                                                                    <form id="overall-fran-ranking-report-form">
                                                                        <table>
                                                                            <tr>
                                                                                <td><xms:localization
                                                                                        text="Start"/></td>
                                                                                <td>
                                                                                    <div class="form-group input-group"
                                                                                         style="margin-bottom: 0px;">
																							<span class="input-group-addon s31"> <i
                                                                                                    class="fa fa-calendar"></i>
																							</span> <input
                                                                                            id="startDate"
                                                                                            name="startDate"
                                                                                            class="form-control form_datetime"
                                                                                            type="text"
                                                                                            data-date-format="dd MM yyyy"
                                                                                            width="80"
                                                                                            onchange="$('#btn-export').hide()"
                                                                                            readonly="readonly">
                                                                                    </div>
                                                                                </td>
                                                                                <td><xms:localization text="End"/></td>
                                                                                <td>
                                                                                    <div class="form-group input-group"
                                                                                         style="margin-bottom: 0px;">
																							<span class="input-group-addon s31"> <i
                                                                                                    class="fa fa-calendar"></i>
																							</span> <input id="endDate"
                                                                                                           name="endDate"
                                                                                                           class="form-control form_datetime"
                                                                                                           type="text"
                                                                                                           data-date-format="dd MM yyyy"
                                                                                                           width="80"
                                                                                                           onchange="$('#btn-export').hide()"
                                                                                                           readonly="readonly">
                                                                                    </div>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </form>
                                                                    <s:hidden id="export-data"/>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="onGoClick()">
                                                                    &nbsp;
                                                                    <xms:localization text="Go"/>
                                                                    &nbsp;
                                                                </button>
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <table class="s36 b24">
                                                        <tr>
                                                            <td><input id="excludeGst" type="checkbox" value="true"
                                                                       onclick="showColumns()"/> <s:hidden
                                                                    id="excludeGST"/></td>
                                                            <td><xms:localization text="Exclude GST/VAT"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"><xms:localization text="Show"/> <s:select
                                                                id="pageSize" name="pageSize" list="pageSizes"
                                                                onchange="onChangePageSize()"
                                                                cssStyle="height:22px; padding-top:1px;"/>
                                                            <xms:localization text="entries"/></th>
                                                    </tr>
                                                </table>
                                                <div id="overall-fran-ranking-report">
                                                    <s:hidden id="rptTxnId" name="rptTxnId"/>
                                                    <s:hidden id="page" name="page"/>
                                                    <s:hidden id="orderField" name="orderField"/>
                                                    <s:hidden id="orderType" name="orderType"/>
                                                    <table id="overall-fran-ranking-table"
                                                           class="table table-bordered mg0 table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Rank"/></th>
                                                            <th><xms:localization text="Fran"/></th>
                                                            <th><xms:localization text="Territory"/></th>
                                                            <th><xms:localization text="Active Customers"/></th>
                                                            <th><xms:localization text="Activations"/></th>
                                                            <th><xms:localization text="Shipments"/></th>
                                                            <th><xms:localization text="Weight"/></th>
                                                            <th data-group="inc-gst"><xms:localization
                                                                    text="Cust. Rev."/></th>
                                                            <th data-group="exc-gst"><xms:localization
                                                                    text="Cust. Rev."/></th>
                                                            <th data-group="inc-gst"><xms:localization
                                                                    text="Rev./Ship"/></th>
                                                            <th data-group="exc-gst"><xms:localization
                                                                    text="Rev./Ship"/></th>
                                                            <th data-group="inc-gst"><xms:localization
                                                                    text="Franchise Cost"/></th>
                                                            <th data-group="exc-gst"><xms:localization
                                                                    text="Franchise Cost"/></th>
                                                            <th data-group="inc-gst"><xms:localization
                                                                    text="Gross Margin"/></th>
                                                            <th data-group="exc-gst"><xms:localization
                                                                    text="Gross Margin"/></th>
                                                            <th data-group="inc-gst"><xms:localization
                                                                    text="Margin/Ship"/></th>
                                                            <th data-group="exc-gst"><xms:localization
                                                                    text="Margin/Ship"/></th>
                                                            <th data-group="inc-gst"><xms:localization
                                                                    text="Margin/Weight"/></th>
                                                            <th data-group="exc-gst"><xms:localization
                                                                    text="Margin/Weight"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="rankingReport==null || rankingReport.totalRecords==0">
                                                            <tr>
                                                                <td colspan="19"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="rankingReport.records">
                                                                <tr>
                                                                    <td><s:property value="rank"/></td>
                                                                    <td><s:property value="franchiseCode"/></td>
                                                                    <td><s:property value="territory"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="activateCustomers"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="activations"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="shipments"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="weights"/></td>
                                                                    <td class="text-right" data-group="inc-gst">
                                                                        <s:property value="revenueIncGst"/></td>
                                                                    <td class="text-right" data-group="exc-gst">
                                                                        <s:property value="revenueExcGst"/></td>
                                                                    <td class="text-right" data-group="inc-gst">
                                                                        <s:property value="revenuePerShipIncGst"/></td>
                                                                    <td class="text-right" data-group="exc-gst">
                                                                        <s:property value="revenuePerShipExcGst"/></td>
                                                                    <td class="text-right" data-group="inc-gst">
                                                                        <s:property value="franchiseCostIncGst"/></td>
                                                                    <td class="text-right" data-group="exc-gst">
                                                                        <s:property value="franchiseCostExcGst"/></td>
                                                                    <td class="text-right" data-group="inc-gst">
                                                                        <s:property value="marginIncGst"/></td>
                                                                    <td class="text-right" data-group="exc-gst">
                                                                        <s:property value="marginExcGst"/></td>
                                                                    <td class="text-right" data-group="inc-gst">
                                                                        <s:property value="marginPerShipIncGst"/></td>
                                                                    <td class="text-right" data-group="exc-gst">
                                                                        <s:property value="marginPerShipExcGst"/></td>
                                                                    <td class="text-right" data-group="inc-gst">
                                                                        <s:property value="marginPerWeightIncGst"/></td>
                                                                    <td class="text-right" data-group="exc-gst">
                                                                        <s:property value="marginPerWeightExcGst"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                            <tr>
                                                                <th colspan="19"><xms:localization text="Showing"/>
                                                                    <s:property value="rankingReport.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="rankingReport.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="rankingReport.totalRecords"/></th>
                                                            </tr>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="rankingReport!=null">
                                                            <s:if test="rankingReport.hasPrev()">
                                                                <a href="javascript:changePage(<s:property value="%{rankingReport.currentPage - 1}"/>)"
                                                                   class="paginate_button previous"><xms:localization
                                                                        text="Previous"/></a>
                                                            </s:if>
															<span> <s:iterator value="rankingReport.pageRange"
                                                                               status="count">
                                                                <s:if test="%{rankingReport.pageRange[#count.index] == rankingReport.currentPage}">
                                                                    <a class="paginate_button current"><s:property
                                                                            value="rankingReport.currentPage"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button"
                                                                       href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                </s:else>
                                                            </s:iterator>
															</span>
                                                            <s:if test="rankingReport.hasNext()">
                                                                <a class="paginate_button next"
                                                                   href="javascript:changePage(<s:property value="%{rankingReport.currentPage+1}"/>)"><xms:localization
                                                                        text="Next"/></a>
                                                            </s:if>
                                                        </s:if>
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
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        onDateRangeChange();
        showColumns();
    });

    function onPeriodTypeChange() {
        var periodType = $("#periodType option:selected").val();
        if (periodType == 3) {
            $("#dateRangeResult").hide();
            $("#customDateRange").show();
            $("#startDate").val("");
            $("#endDate").val("");
        } else {
            $("#dateRangeResult").show();
            $("#customDateRange").hide();
            loadingDialog.dialog("open");
            $.post("overall_fran_ranking_date_range.ix?reqType=json", "periodType=" + periodType, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    $("#dateRangeResult").html(res.content);
                    var period = $("#dateRange option:selected").val();
                    var arr = period.split(" - ");
                    $("#startDate").val(arr[0]);
                    $("#endDate").val(arr[1]);
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
    }

    function onDateRangeChange() {
        var period = $("#dateRange option:selected").val();
        var arr = period.split(" - ");
        $("#startDate").val(arr[0]);
        $("#endDate").val(arr[1]);
    }

    function doReport() {
        var data = $("#overall-fran-ranking-report-form").serialize();
        data += "&orderField=" + $("#orderField").val();
        data += "&orderType=" + $("#orderType").val();
        data += "&page=" + $("#page").val();
        data += "&pageSize=" + $("#pageSize option:selected").val();
        var dataExport = data;
        data += "&rptTxnId=" + $("#rptTxnId").val();
        loadingDialog.dialog("open");
        $.post("overall_fran_ranking_do_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#overall-fran-ranking-report").html(res.content);
                $("#btn-export").show();
                $("#export-data").val(dataExport);
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
        $("#page").val(page);
        doReport();
    }

    function onGoClick() {
        $("#rptTxnId").val("");
        $("#page").val(1);
        doReport();
    }

    function onChangePageSize() {
        if ($("#rptTxnId").val() != "") {
            doReport();
        }
    }

    function showColumns() {
        var excludeGst = $("#excludeGst").is(":checked");
        if (excludeGst) {
            $("#overall-fran-ranking-table th[data-group='inc-gst']").hide();
            $("#overall-fran-ranking-table td[data-group='inc-gst']").hide();
            $("#overall-fran-ranking-table th[data-group='exc-gst']").show();
            $("#overall-fran-ranking-table td[data-group='exc-gst']").show();
            $("#excludeGST").val("true");
        } else {
            $("#overall-fran-ranking-table th[data-group='inc-gst']").show();
            $("#overall-fran-ranking-table td[data-group='inc-gst']").show();
            $("#overall-fran-ranking-table th[data-group='exc-gst']").hide();
            $("#overall-fran-ranking-table td[data-group='exc-gst']").hide();
            $("#excludeGST").val("false");
        }
    }
    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val() + "&rptTxnId=" + $("#rptTxnId").val() + "&excludeGST=" + $("#excludeGST").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("overall_fran_ranking_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("overall_fran_ranking_export.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                    failCallback: function (url) {
                        loadingDialog.dialog("close");
                    }
                });
        }

    }


</script>