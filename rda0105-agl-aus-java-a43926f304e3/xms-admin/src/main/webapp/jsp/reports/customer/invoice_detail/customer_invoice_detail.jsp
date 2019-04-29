<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>">Home</a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Customer Invoice Detail Report"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Customer Invoice Detail Report"/></li>
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
                                    <xms:localization text="Customer Invoice Detail"/>
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
                                                <s:if test="grandTotal != 0">
                                                    <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                            text="Export"/></a></li>
                                                </s:if>
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
                                                    <form id="do-report-form">
                                                        <table class="s36 b24">
                                                            <tr>
                                                                <td><xms:localization text="Type"/></td>
                                                                <td><select id="reportType" name="reportType"
                                                                            class="form-control"
                                                                            onchange="changeReportType()">
                                                                    <option value="1"><xms:localization
                                                                            text="Invoice Detail"/></option>
                                                                    <option value="2"><xms:localization
                                                                            text="Credit Note Detail"/></option>
                                                                </select></td>
                                                                <td><xms:localization text="Franchise"/></td>
                                                                <td><s:select id="franchiseCode" name="franchiseCode"
                                                                              list="franchises"
                                                                              listValue="code + ' - ' + name"
                                                                              listKey="code" headerValue="All"
                                                                              headerKey="All"
                                                                              cssClass="form-control"/></td>
                                                                <td><xms:localization text="Start Date"/></td>
                                                                <td>
                                                                    <div class="form-group input-group"
                                                                         style="margin-bottom: 0px;">
																		<span class="input-group-addon s31"> <i
                                                                                class="fa fa-calendar"></i>
																		</span> <input name="startDate"
                                                                                       class="form-control form_datetime"
                                                                                       type="text"
                                                                                       data-date-format="dd MM yyyy"
                                                                                       width="80" readonly="readonly">
                                                                    </div>
                                                                </td>
                                                                <td><xms:localization text="End Date"/></td>
                                                                <td>
                                                                    <div class="form-group input-group"
                                                                         style="margin-bottom: 0px;">
																		<span class="input-group-addon s31"> <i
                                                                                class="fa fa-calendar"></i>
																		</span> <input name="endDate"
                                                                                       class="form-control form_datetime"
                                                                                       type="text"
                                                                                       data-date-format="dd MM yyyy"
                                                                                       width="80" readonly="readonly">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <button class="btn s37" type="button" id="btnGo"
                                                                            onclick="doReport()">
                                                                        &nbsp;
                                                                        <xms:localization text="Go"/>
                                                                        &nbsp;
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </form>
                                                    <s:hidden id="selected-reportType"/>
                                                    <s:hidden id="export-data"/>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"><xms:localization text="Show"/> <s:select
                                                                id="pageSize" name="pageSize" list="pageSizes"
                                                                onchange="refreshReport()"
                                                                cssStyle="height:22px; padding-top:1px;"/>
                                                            <xms:localization text="entries"/></th>
                                                    </tr>
                                                </table>
                                                <div id="customer-invoice-detail-report">
                                                    <input id="invoiceRptTxnId" type="hidden"
                                                           value="<s:property value="rptTxnId" />"/> <input
                                                        id="invoicePage" type="hidden"
                                                        value="<s:property value="page" />"/> <input
                                                        id="invoiceOrderField" type="hidden"
                                                        value="<s:property value="orderField" />"/> <input
                                                        id="invoiceOrderType" type="hidden"
                                                        value="<s:property value="orderType" />"/>
                                                    <table id="customer-invoice-detail-table"
                                                           class="table table-bordered mg0 table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Customer Inv #"/></th>
                                                            <th><xms:localization text="Customer"/></th>
                                                            <th><xms:localization text="Invoice Date"/></th>
                                                            <th><xms:localization text="Invoice Amount"/></th>
                                                            <th><xms:localization text="Invoice Credit"/></th>
                                                            <th><xms:localization text="Net Amount"/></th>
                                                            <th><xms:localization text="GST"/></th>
                                                            <th><xms:localization text="GST Credits"/></th>
                                                            <th><xms:localization text="Net GST"/></th>
                                                            <th><xms:localization text="Total"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="invoiceDetailReport==null || invoiceDetailReport.totalRecords==0">
                                                            <tr>
                                                                <td colspan="10"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="invoiceDetailReport.records">
                                                                <tr>
                                                                    <td><s:property value="invoiceCode"/></td>
                                                                    <td><s:property value="customerName"/></td>
                                                                    <td><s:property value="invoiceDate"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="invoiceAmount"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="invoiceCredit"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="netAmount"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="gst"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="creditGst"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="netGst"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="total"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                            <s:if test="invoiceDetailSummary!=null">
                                                                <tr>
                                                                    <th colspan="3" class="text-right"><xms:localization
                                                                            text="Total"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceDetailSummary.invoiceAmount"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceDetailSummary.invoiceCredit"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceDetailSummary.netAmount"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceDetailSummary.gst"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceDetailSummary.creditGst"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceDetailSummary.netGst"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceDetailSummary.total"/></th>
                                                                </tr>
                                                            </s:if>
                                                            <tr>
                                                                <th colspan="10"><xms:localization text="Showing"/>
                                                                    <s:property
                                                                            value="invoiceDetailReport.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="invoiceDetailReport.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="invoiceDetailReport.totalRecords"/></th>
                                                            </tr>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="invoiceDetailReport!=null">
                                                            <s:if test="invoiceDetailReport.hasPrev()">
                                                                <a href="javascript:changePage(<s:property value="%{invoiceDetailReport.currentPage - 1}"/>)"
                                                                   class="paginate_button previous"><xms:localization
                                                                        text="Previous"/></a>
                                                            </s:if>
															<span> <s:iterator value="invoiceDetailReport.pageRange"
                                                                               status="count">
                                                                <s:if test="%{invoiceDetailReport.pageRange[#count.index] == invoiceDetailReport.currentPage}">
                                                                    <a class="paginate_button current"><s:property
                                                                            value="invoiceDetailReport.currentPage"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button"
                                                                       href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                </s:else>
                                                            </s:iterator>
															</span>
                                                            <s:if test="invoiceDetailReport.hasNext()">
                                                                <a class="paginate_button next"
                                                                   href="javascript:changePage(<s:property value="%{invoiceDetailReport.currentPage+1}"/>)"><xms:localization
                                                                        text="Next"/></a>
                                                            </s:if>
                                                        </s:if>
                                                    </div>
                                                </div>
                                                <div id="customer-credit-note-detail-report" style="display: none;">
                                                    <input id="creditRptTxnId" type="hidden"
                                                           value="<s:property value="rptTxnId" />"/> <input
                                                        id="creditPage" type="hidden"
                                                        value="<s:property value="page" />"/> <input
                                                        id="creditOrderField" type="hidden"
                                                        value="<s:property value="orderField" />"/> <input
                                                        id="creditOrderType" type="hidden"
                                                        value="<s:property value="orderType" />"/>
                                                    <table id="customer-credit-note-detail-table"
                                                           class="table table-bordered mg0 table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Credit Note Number"/></th>
                                                            <th><xms:localization text="Customer"/></th>
                                                            <th><xms:localization text="Credit Note Date"/></th>
                                                            <th><xms:localization text="Amount"/></th>
                                                            <th><xms:localization text="GST"/></th>
                                                            <th><xms:localization text="Total"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="creditNoteDetailReport==null || creditNoteDetailReport.totalRecords==0">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="creditNoteDetailReport.records">
                                                                <tr>
                                                                    <td><s:property value="creditCode"/></td>
                                                                    <td><s:property value="customerName"/></td>
                                                                    <td><s:property value="createDate"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="amount"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="gst"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="total"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                            <s:if test="creditNoteDetailSummary!=null">
                                                                <tr>
                                                                    <th colspan="3" class="text-right"><xms:localization
                                                                            text="Total"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="creditNoteDetailSummary.amount"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="creditNoteDetailSummary.gst"/></th>
                                                                    <th class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="creditNoteDetailSummary.total"/></th>
                                                                </tr>
                                                            </s:if>
                                                            <tr>
                                                                <th colspan="6"><xms:localization text="Showing"/>
                                                                    <s:property
                                                                            value="creditNoteDetailReport.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="creditNoteDetailReport.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="creditNoteDetailReport.totalRecords"/></th>
                                                            </tr>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="creditNoteDetailReport!=null">
                                                                    <s:if test="creditNoteDetailReport.hasPrev()">
                                                                        <a href="javascript:changePage(<s:property value="%{creditNoteDetailReport.currentPage - 1}"/>)"
                                                                           class="paginate_button previous"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:if>
																	<span> <s:iterator
                                                                            value="creditNoteDetailReport.pageRange"
                                                                            status="count">
                                                                        <s:if test="%{creditNoteDetailReport.pageRange[#count.index] == creditNoteDetailReport.currentPage}">
                                                                            <a class="paginate_button current"><s:property
                                                                                    value="creditNoteDetailReport.currentPage"/></a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a class="paginate_button"
                                                                               href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                        </s:else>
                                                                    </s:iterator>
																	</span>
                                                                    <s:if test="creditNoteDetailReport.hasNext()">
                                                                        <a class="paginate_button next"
                                                                           href="javascript:changePage(<s:property value="%{creditNoteDetailReport.currentPage+1}"/>)"><xms:localization
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
    </div>
</div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    var dataExport = "";
    function doInvoiceReport() {
        var data = $("#do-report-form").serialize();
        data += "&orderField=" + $("#invoiceOrderField").val();
        data += "&orderType=" + $("#invoiceOrderType").val();
        data += "&page=" + $("#invoicePage").val();
        data += "&pageSize=" + $("#pageSize option:selected").val();
        dataExport = data;
        data += "&rptTxnId=" + $("#invoiceRptTxnId").val();
        loadingDialog.dialog("open");
        $.post("customer_invoice_detail_do_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customer-invoice-detail-report").html(res.content);
                $("#export-data").val(data);
                $("#btn-export").show();
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

    function doCreditReport() {
        var data = $("#do-report-form").serialize();
        data += "&orderField=" + $("#creditOrderField").val();
        data += "&orderType=" + $("#creditOrderType").val();
        data += "&page=" + $("#creditPage").val();
        data += "&pageSize=" + $("#pageSize option:selected").val();
        dataExport = data;
        data += "&rptTxnId=" + $("#creditRptTxnId").val();
        loadingDialog.dialog("open");
        $.post("customer_credit_note_detail_do_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customer-credit-note-detail-report").html(res.content);
                $("#export-data").val(data);
                $("#btn-export").show();
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

    function changeReportType() {
        var type = $("select#reportType option:selected").val();
        if (type == 1) {
            $("#customer-invoice-detail-report").show();
            $("#customer-credit-note-detail-report").hide();
        } else if (type == 2) {
            $("#customer-invoice-detail-report").hide();
            $("#customer-credit-note-detail-report").show();
        }
    }

    function doReport() {
        var type = $("select#reportType option:selected").val();
        $("#selected-reportType").val(type);
        if (type == 1) {
            $("#invoiceRptTxnId").val("");
            doInvoiceReport();
        } else if (type == 2) {
            $("#creditRptTxnId").val("");
            doCreditReport();
        }
    }

    function refreshReport() {
        var type = $("select#reportType option:selected").val();
        if (type == 1) {
            doInvoiceReport();
        } else if (type == 2) {
            doCreditReport();
        }
    }

    function changePage(page) {
        var type = $("select#reportType option:selected").val();
        if (type == 1) {
            $("#invoicePage").val(page);
            doInvoiceReport();
        } else if (type == 2) {
            $("#creditPage").val(page);
            doCreditReport();
        }
    }
    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var urlPrint = "";
        var urlExport = "";
        var rptTxnId = "";
        var type = $("#selected-reportType").val();
        if (type == 1) {
            urlPrint = "customer_invoice_detail_print.ix";
            urlExport = "customer_invoice_detail_export.ix";
            rptTxnId = $("#invoiceRptTxnId").val();
        } else {
            urlPrint = "customer_credit_note_detail_print.ix";
            urlExport = "customer_credit_note_detail_export.ix";
            rptTxnId = $("#creditRptTxnId").val()
        }
        var data = $("#export-data").val() + "&rptTxnId=" + rptTxnId;
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post(urlPrint, data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload(urlExport, {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    }
                });
        }

    }


</script>