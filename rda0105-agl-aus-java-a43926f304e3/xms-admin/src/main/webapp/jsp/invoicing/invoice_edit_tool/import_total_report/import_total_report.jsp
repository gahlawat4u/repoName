<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    var hasRecords = false;
</script>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="hidden">
            <a href="#"><xms:localization text="Import Totals"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Import Totals"/>
        </li>
    </ol>
    <div class="clearfix"></div>
</div>
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
                                    <xms:localization text="Import Totals"/>
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
                                        <form id="search_import_total_form">
                                            <table class="s36">
                                                <tbody>
                                                <tr>
                                                    <td><xms:localization text="Select an import date"/></td>
                                                    <td><s:select id="importDateList" list="importDateList"
                                                                  cssClass="form-control"
                                                                  onchange="changeImportDate()"/></td>
                                                    <td><xms:localization text="Carrier"/></td>
                                                    <td><s:i18n_select name="carrier" list="services"
                                                                       cssClass="form-control" listValue="serviceName"
                                                                       listKey="serviceId" headerValue="All"
                                                                       headerKey="" i18nitem="no"/></td>
                                                    <td>
                                                        <button class="btn s37" type="button" onclick="onSearchClick()">
                                                            <xms:localization text="Search"/>
                                                        </button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="6">&nbsp;</td>
                                                </tr>
                                                <tr id="custom_date_range_group" style="display: none;">
                                                    <td>&nbsp;</td>
                                                    <td colspan="4">
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
                                                    <td>&nbsp;</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <input type="hidden" id="searchType" name="searchType" value="0"/> <input
                                                type="hidden" id="importDate" name="importDate" value=""/> <input
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
                                                <div id="import_total_report">
                                                    <div class="s70">
                                                        <table class="table table-bordered mg0" id="import_total_table">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Invoice #"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Customer Total"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Franchise Chg."/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Carrier Cost"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Margin"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Airbill Count"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="report==null || report.totalRecords==0">
                                                                <tr>
                                                                    <td colspan="6"><xms:localization
                                                                            text="No data available..."/></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:else>
                                                                <s:iterator value="report.records">
                                                                    <tr>
                                                                        <td><a href="#"
                                                                               onclick='viewInvoiceDetail(<s:property
                                                                                       value="invoiceId"/>)'><s:property
                                                                                value="invoiceCode"/></a></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="customerCost"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="franchiseCost"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="carrierCost"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="margin"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="airbillCount"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                                <s:if test="summary!=null">
                                                                    <tr>
                                                                        <th>Total</th>
                                                                        <th class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="summary.customerCost"/></th>
                                                                        <th class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="summary.franchiseCost"/></th>
                                                                        <th class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="summary.carrierCost"/></th>
                                                                        <th class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="summary.margin"/></th>
                                                                        <th class="text-right"><s:property
                                                                                value="summary.airbillCount"/></th>
                                                                    </tr>
                                                                </s:if>
                                                                <tr>
                                                                    <th colspan="26"><xms:localization text="Showing"/>
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
<div id="invoice_detail_dialog" title='<xms:localization text="Invoice"/>'></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        changeImportDate();
    });

    function doSearch() {
        var data = $("#search_import_total_form").serialize();
        $("#export-data").val(data);
        data += "&pageSize=" + $("#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("import_total_report_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#import_total_report").html(res.content);
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
        $("#orderField").val("invoice_code");
        $("#orderType").val(0);
        doSearch();
    }

    function changePage(page) {
        $("#page").val(page);
        doSearch();
    }

    function changeImportDate() {
        var lastOption = '<xms:localization text="Custom Date Range" />';
        var selectedDate = $("#importDateList option:selected").val();
        if (selectedDate != lastOption) {
            $("#searchType").val(0);
            $("#importDate").val(selectedDate);
            $("#custom_date_range_group").hide();
        } else {
            $("#searchType").val(1);
            $("#custom_date_range_group").show();
        }
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
                $.post("import_total_report_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("import_total_report_export.ix", {
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

    function viewInvoiceDetail(invoiceId) {
        var data = {
            "invoiceId": invoiceId
        };
        loadingDialog.dialog("open");
        $.post("view_edit_invoice_get_invoice_detail.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                var dialog = $("#invoice_detail_dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: "auto",
                    height: 'auto',
                    maxHeight: 700,
                    maxWidth: 800,
                    show: {
                        effect: "fade",
                        duration: 300
                    },
                });
                dialog.html(res.content);
                dialog.dialog("open");
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