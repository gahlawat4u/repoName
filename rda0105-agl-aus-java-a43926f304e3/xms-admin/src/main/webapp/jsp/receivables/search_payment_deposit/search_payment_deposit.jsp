<%@ page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    var hasRecords = false;
</script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="dashboard.html"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="hidden">
            <a href="#"><xms:localization text="Search Payments / Deposit Slip"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Search Payments / Deposit Slip"/>
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
                    <div id="area-chart-spline" style="width: 100%; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Search Payments / Deposit Slip"/>
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
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12 form-group">
                                                <form id="search_payment_form">
                                                    <table class="s36 b24" style="width: auto; margin-bottom: auto">
                                                        <tr>
                                                            <td><xms:localization text="Date"/></td>
                                                            <td>
                                                                <div class="form-group input-group mg0">
                                                                    <span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i></span> <input
                                                                        name="startDate"
                                                                        class="form-control form_datetime" type="text"
                                                                        data-date-format="dd MM yyyy"
                                                                        placeholder='<xms:localization text="Start" />'/>
                                                                </div>
                                                            </td>
                                                            <td colspan="2">
                                                                <div class="form-group input-group mg0">
																	<span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i>
																	</span> <input name="endDate"
                                                                                   class="form-control form_datetime"
                                                                                   type="text"
                                                                                   data-date-format="dd MM yyyy"
                                                                                   placeholder='<xms:localization text="End" />'/>
                                                                </div>
                                                            </td>
                                                            <td><xms:localization text="Bank"/></td>
                                                            <td><s:i18n_select name="bankId" list="bankList"
                                                                               listKey="bankId" listValue="bankName"
                                                                               cssClass="form-control" headerKey=""
                                                                               headerValue="All" i18nitem="no"/></td>
                                                            <td><xms:localization text="Customer #"/></td>
                                                            <td><input name="customerCode"
                                                                       class="form-control alloptions" type="text"
                                                                       placeholder="" maxlength="25"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><xms:localization text="Min"/></td>
                                                            <td><input name="min" class="form-control alloptions"
                                                                       type="text" placeholder="" maxlength="25"/></td>
                                                            <td><xms:localization text="Max"/></td>
                                                            <td><input name="max" class="form-control alloptions"
                                                                       type="text" placeholder="" maxlength="25"/></td>
                                                            <td><xms:localization text="Search Notes"/></td>
                                                            <td><input name="note" class="form-control alloptions"
                                                                       type="text" placeholder="" maxlength="25"/></td>
                                                            <td><xms:localization text="Cheque #"/></td>
                                                            <td><input name="cheque" class="form-control alloptions"
                                                                       type="text" placeholder="" maxlength="25"/></td>
                                                            <td><xms:localization text="Franchise"/></td>
                                                            <td><s:i18n_select name="franchiseCode" list="franchises"
                                                                               listKey="code" listValue="code"
                                                                               cssClass="form-control" headerKey=""
                                                                               headerValue="All" i18nitem="no"/></td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="onSearchClick()">
                                                                    <xms:localization text="Search"/>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                    <input type="hidden" id="orderField" name="orderField"
                                                           value="amount"/> <input type="hidden" id="orderType"
                                                                                   name="orderType" value="0"/> <input
                                                        type="hidden" id="page" name="page" value="1"/>
                                                </form>
                                                <s:hidden id="export-data"/>
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
                                                <div id="payment_report">
                                                    <div class="s70">
                                                        <table class="table table-bordered mg0"
                                                               id="payment_report_table">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Amount"/></th>
                                                                <th><xms:localization text="Cheque #"/></th>
                                                                <th data-group="payment-date"><xms:localization
                                                                        text="Date"/></th>
                                                                <th data-group="customer-code"><xms:localization
                                                                        text="Customer #"/></th>
                                                                <th data-group="customer-name"><xms:localization
                                                                        text="Customer Name"/></th>
                                                                <th data-group="invoices"><xms:localization
                                                                        text="Invoice(s)"/></th>
                                                                <th data-group="note"><xms:localization
                                                                        text="Notes"/></th>
                                                                <th data-group="over-payment-type"><xms:localization
                                                                        text="Types of Overpayment"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="report==null || report.totalRecords==0">
                                                                <tr>
                                                                    <td colspan="8"><xms:localization
                                                                            text="No data available..."/></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:else>
                                                                <s:iterator value="report.records">
                                                                    <tr>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="amount"/></td>
                                                                        <td><s:property value="cheque"/></td>
                                                                        <td data-group="payment-date"><s:property
                                                                                value="paymentDate"/></td>
                                                                        <td data-group="customer-code"><s:property
                                                                                value="customerCode"/></td>
                                                                        <td data-group="customer-name"><s:property
                                                                                value="customerName"/></td>
                                                                        <td data-group="invoices"><s:property
                                                                                value="invoiceList"/></td>
                                                                        <td data-group="note"><s:property
                                                                                value="note"/></td>
                                                                        <td data-group="over-payment-type"><s:property
                                                                                value="overPaymentType"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                                <tr>
                                                                    <th colspan="8"><xms:localization text="Showing"/>
                                                                        <s:property value="report.startRecord"/>
                                                                        <xms:localization text="to"/> <s:property
                                                                                value="report.endRecord"/>
                                                                        <xms:localization text="of"/> <s:property
                                                                                value="report.totalRecords"/></th>
                                                                </tr>
                                                            </s:else>
                                                            <tr>
                                                                <td colspan="8"><span class="b4"> <b><xms:localization
                                                                        text="Total: "/></b> <s:property
                                                                        value="currencySymbol"/> <s:property
                                                                        value="summary.total"/>
																	</span> <span class="b4"> <b>| <xms:localization
                                                                        text="Total Payment Received: "/></b> <s:property
                                                                        value="currencySymbol"/> <s:property
                                                                        value="summary.totalReceived"/>
																	</span></td>
                                                            </tr>
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
                                                <form id="column-flags">
                                                    <table class="s36">
                                                        <tbody>
                                                        <tr>
                                                            <td><b><xms:localization text="Columns"/></b></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="invoices"
                                                                       checked="checked"/> <s:hidden
                                                                    name="columnFlags.hasInvoice" data-group="invoices"
                                                                    value="true"/></td>
                                                            <td><xms:localization text="Invoice"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="customer-code"
                                                                       checked="checked"/> <s:hidden
                                                                    name="columnFlags.hasCustomerNo"
                                                                    data-group="customer-code" value="true"/></td>
                                                            <td><xms:localization text="Cust. #"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="customer-name"
                                                                       checked="checked"/> <s:hidden
                                                                    name="columnFlags.hasCustomerName"
                                                                    data-group="customer-name" value="true"/></td>
                                                            <td><xms:localization text="Cust. Name"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="payment-date"
                                                                       checked="checked"/> <s:hidden
                                                                    name="columnFlags.hasDate" data-group="payment-date"
                                                                    value="true"/></td>
                                                            <td><xms:localization text="Date"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="note"
                                                                       checked="checked"/> <s:hidden
                                                                    name="columnFlags.hasNotes" data-group="note"
                                                                    value="true"/></td>
                                                            <td><xms:localization text="Notes"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="over-payment-type"
                                                                       checked="checked"/> <s:hidden
                                                                    name="columnFlags.hasTypeOfOverpayment"
                                                                    data-group="over-payment-type" value="true"/></td>
                                                            <td><xms:localization text="Types of Overpayment"/></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </form>
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
        showHideColumns();
        assignCheckBoxHandler();
    });

    function doSearch() {
        var data = $("#search_payment_form").serialize();
        $("#export-data").val(data);
        data += "&pageSize=" + $("#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("search_payments_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#payment_report").html(res.content);
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
        $("#orderField").val("amount");
        $("#orderType").val(0);
        doSearch();
    }

    function changePage(page) {
        $("#page").val(page);
        doSearch();
    }

    function showHideColumns() {
        $("input[type='checkbox'][data-group]").each(function () {
            var group = $(this).attr("data-group");
            if ($(this).is(":checked")) {
                $("#payment_report_table th[data-group='" + group + "']").show();
                $("#payment_report_table td[data-group='" + group + "']").show();
                $("input[type='hidden'][data-group='" + group + "']").val("true");
            } else {
                $("#payment_report_table th[data-group='" + group + "']").hide();
                $("#payment_report_table td[data-group='" + group + "']").hide();
                $("input[type='hidden'][data-group='" + group + "']").val("false");
            }
        });
    }

    function assignCheckBoxHandler() {
        $("input[type='checkbox'][data-group]").each(function () {
            $(this).click(function () {
                showHideColumns();
            });
        });
    }
    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val() + "&" + $("#column-flags").serialize();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("search_payments_print.ix?reqType=json", data, function (res) {
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
                $.fileDownload("search_payments_export.ix", {
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