<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    var hasRecords = false;
</script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="hidden">
            <a href="#"><xms:localization text="Cost Deviations Report"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Cost Deviations Report"/>
        </li>
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
                                    <xms:localization text="Cost Deviations Report"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group" id="btn-export">
                                            <input type="button" id="export-option" class="btn s37"
                                                   value="<xms:localization text="Option" />"
                                                   onclick="exportClick($('#selected-option').val())"/>
                                            <button type="button" class="btn s37 dropdown-toggle" data-toggle="dropdown"
                                                    aria-expanded="true">
                                                <span class="caret"></span>
                                            </button>
                                            <s:hidden id="selected-option"/>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                <li>
                                                    <a href="#" onclick="exportClick('option')"><xms:localization
                                                            text="Option"/></a>
                                                </li>
                                                <li>
                                                    <a href="#" onclick="exportClick('html')"><xms:localization
                                                            text="Print"/></a>
                                                </li>
                                                <li>
                                                    <a href="#" onclick="exportClick('xls')"><xms:localization
                                                            text="Export"/></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="form-group flr mgb">
                                        <form id="cost_deviation_form">
                                            <table class="s36">
                                                <tbody>
                                                <tr>
                                                    <td><xms:localization text="Invoice Date"/></td>
                                                    <td><s:select id="invoiceDateList" list="invoiceDates"
                                                                  cssClass="form-control"
                                                                  onchange="changeInvoiceDate()"/></td>
                                                    <td id="custom_date_range">
                                                        <table>
                                                            <tr>
                                                                <td><xms:localization text="Start"/></td>
                                                                <td>
                                                                    <div class="form-group input-group mg0">
                                                                        <span class="input-group-addon s31"><i
                                                                                class="fa fa-calendar"></i></span>
                                                                        <input name="startDate"
                                                                               class="form-control form_datetime"
                                                                               type="text" data-date-format="dd MM yyyy"
                                                                               placeholder='<xms:localization text="Start"/>'>
                                                                    </div>
                                                                </td>
                                                                <td><xms:localization text="End"/></td>
                                                                <td>
                                                                    <div class="form-group input-group mg0">
                                                                        <span class="input-group-addon s31"><i
                                                                                class="fa fa-calendar"></i></span>
                                                                        <input name="endDate"
                                                                               class="form-control form_datetime"
                                                                               type="text" data-date-format="dd MM yyyy"
                                                                               placeholder='<xms:localization text="End"/>'>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td><xms:localization text="Franchise"/></td>
                                                    <td><s:i18n_select name="franchiseCode" list="franchises"
                                                                       listValue="code" listKey="code" headerKey=""
                                                                       headerValue="All" cssClass="form-control"
                                                                       i18nitem="no"/></td>
                                                    <td>
                                                        <button class="btn s37" type="button" onclick="onSearchClick()">
                                                            <xms:localization text="Search"/>
                                                        </button>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <input type="hidden" id="searchType" name="searchType" value="0"/> <input
                                                type="hidden" id="invoiceDate" name="invoiceDate" value=""/> <input
                                                type="hidden" id="orderField" name="orderField" value="invoice_code"/>
                                            <input type="hidden" id="orderType" name="orderType" value="0"/> <input
                                                type="hidden" id="page" name="page" value="1"/>
                                        </form>
                                        <s:hidden id="export-data"/>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <xms:localization
                                                            text="<b>Note:</b> <br> Cost Deviation is where the base charge that comes through on the airbill is different from the cost tables loaded in the system. This could be for any number of reasons, including:<br>"/>
                                                    &nbsp;&nbsp;<span class="s30">1)</span>
                                                    <xms:localization
                                                            text="The cost tables loaded in the system are incorrect"/>
                                                    <br> &nbsp;&nbsp;<span class="s30">2)</span>
                                                    <xms:localization
                                                            text="The carrier incorrectly calculated the cost for the airbill"/>
                                                    <br> &nbsp;&nbsp;<span class="s30">3)</span>
                                                    <xms:localization
                                                            text="Something on the airbill was modified after import (eg.zone,weight)"/>
                                                    <br> &nbsp;&nbsp;<span class="s30">4)</span>
                                                    <xms:localization
                                                            text="The airbill/customer has special cost pricing"/>
                                                    <br> <br>
                                                    <xms:localization
                                                            text="Please note that where cost tables are not loaded into the system, we cannot calculated a deviation for the airbill. Also, multi-piece shipments are not included on this report."/>
                                                </p>
                                            </div>
                                            <div class="portlet-body b12 b11">
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select id="pageSize" name="pageSize"
                                                                                  cssClass="form-control"
                                                                                  cssStyle="height: 22px; padding-top: 1px;"
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
                                                <div id="cost_deviation_report">
                                                    <div class="s70">
                                                        <table class="table table-bordered mg0"
                                                               id="cost_deviation_report_table">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Airbill #"/></th>
                                                                <th><xms:localization text="Invoice #"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Calculated Franchise Cost"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Franchise Cost on Airbill"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Difference"/></th>
                                                                <th><xms:localization text="Carrier"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Weight"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="report==null || report.totalRecords==0">
                                                                <tr>
                                                                    <td colspan="7"><xms:localization
                                                                            text="No data available..."/></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:else>
                                                                <s:iterator value="report.records">
                                                                    <tr>
                                                                        <td><s:property value="airbillNumber"/></td>
                                                                        <td><s:property value="invoiceCode"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="calculatedFranchiseCost"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="franchiseCost"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="difference"/></td>
                                                                        <td><s:property value="serviceName"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="weight"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                                <s:if test="summary!=null">
                                                                    <tr>
                                                                        <td colspan="7"><span
                                                                                class="b4"> <b><xms:localization
                                                                                text="Total Calculated Franchise Costs:"/></b> <s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="summary.calculatedFranchiseCost"/>
																			</span> <span class="b4"> <b>|
                                                                            <xms:localization
                                                                                    text="Total Franchise Costs on Airbill:"/></b> <s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="summary.franchiseCost"/>
																			</span> <span class="b4"> <b>|
                                                                            <xms:localization
                                                                                    text="Total Differences:"/></b> <s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="summary.difference"/>
																			</span></td>
                                                                    </tr>
                                                                </s:if>
                                                                <tr>
                                                                    <th colspan="7"><xms:localization text="Showing"/>
                                                                        <s:property value="report.startRecord"/>
                                                                        <xms:localization text="to"/> <s:property
                                                                                value="report.endRecord"/>
                                                                        <xms:localization text="of"/> <s:property
                                                                                value="report.totalRecords"/></th>
                                                                </tr>
                                                            </s:else>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="report!=null">
                                                            <s:if test="report.hasPrev()">
                                                                <a href="javascript:changePage(<s:property value="%{report.currentPage - 1}"/>)"
                                                                   class="paginate_button previous"><xms:localization
                                                                        text="Previous"/></a>
                                                            </s:if>
															<span> <s:iterator value="report.pageRange" status="count">
                                                                <s:if test="%{report.pageRange[#count.index] == report.currentPage}">
                                                                    <a class="paginate_button current"><s:property
                                                                            value="report.currentPage"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button"
                                                                       href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                </s:else>
                                                            </s:iterator>
															</span>
                                                            <s:if test="report.hasNext()">
                                                                <a class="paginate_button next"
                                                                   href="javascript:changePage(<s:property value="%{report.currentPage+1}"/>)"><xms:localization
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
        changeInvoiceDate();
    });

    function changeInvoiceDate() {
        var lastOption = '<xms:localization text="Custom Date Range" />';
        var selectedDate = $("#invoiceDateList option:selected").val();
        if (selectedDate != lastOption) {
            $("#searchType").val(0);
            $("#invoiceDate").val(selectedDate);
            $("#custom_date_range").hide();
        } else {
            $("#searchType").val(1);
            $("#custom_date_range").show();
        }
    }

    function doSearch() {
        var data = $("#cost_deviation_form").serialize();
        $("#export-data").val(data);
        data += "&pageSize=" + $("#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("cost_deviations_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#cost_deviation_report").html(res.content);
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

    function onSearchClick() {
        $("#page").val(1);
        $("#orderField").val("airbill_number");
        $("#orderType").val(0);
        doSearch();
    }

    function changePage(page) {
        $("#page").val(page);
        doSearch();
    }
    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("cost_deviations_print.ix?reqType=json", data, function (res) {
                    loadingDialog.dialog("close");
                    if (res.errorCode == "SUCCESS") {
                        var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                        win.document.write(res.content);
                    } else {
                        alertDialog.html(res.errorMsg);
                        alertDialog.dialog("open");
                    }
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("cost_deviations_export.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                    failCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                });
                break;
        }
    }


</script>