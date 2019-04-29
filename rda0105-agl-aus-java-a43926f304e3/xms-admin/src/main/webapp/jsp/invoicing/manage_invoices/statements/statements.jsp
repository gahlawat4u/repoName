<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Statement of Accounts"/>
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
                                    <xms:localization text="Statement of Accounts"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb" id="export-btns" style="display: none;">
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
                                                            text="View printable copy"/></a>
                                                </li>
                                                <li>
                                                    <a href="#" onclick="exportClick('pdf')"><xms:localization
                                                            text="View PDF copy"/></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="form-group flr mgb">
                                        <table class="s36" style="width: auto; margin-bottom: auto">
                                            <tbody>
                                            <tr>
                                                <td><s:i18n_select id="franchiseCode" name="franchiseCode"
                                                                   list="franchises" listKey="code" listValue="code"
                                                                   cssClass="form-control" headerKey=""
                                                                   headerValue="All" i18nitem="no"/></td>
                                                <td>
                                                    <button class="btn s37" type="button" onclick="searchCustomers()">
                                                        <xms:localization text="Search"/>
                                                    </button>
                                                </td>
                                                <td id="customer_list_result"><s:i18n_select id="customerCode"
                                                                                             cssClass="form-control"
                                                                                             name="customerCode"
                                                                                             list="customers"
                                                                                             listKey="customerCode"
                                                                                             listValue="displayName"
                                                                                             headerKey=""
                                                                                             headerValue="Select a Customer"
                                                                                             i18nitem="no"
                                                                                             onchange="statementDetail()"/></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;" id="statement_customer_detail">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <h2 style="text-align: center">
                                                <xms:localization text="Statement of Accounts"/>
                                            </h2>

                                            <div class="col-lg-12">
                                                <table class="table table-bordered" style="margin-top: 5px">
                                                    <thead>
                                                    <tr>
                                                        <th><xms:localization text="Date"/></th>
                                                        <th><xms:localization text="Customer #"/></th>
                                                        <th class="text-right"><xms:localization text="Total Due"/></th>
                                                        <th class="text-right"><xms:localization text="<=0 days"/></th>
                                                        <th class="text-right"><xms:localization text="1 to 15"/></th>
                                                        <th class="text-right"><xms:localization text="16 to 30"/></th>
                                                        <th class="text-right"><xms:localization text="31 to 45"/></th>
                                                        <th class="text-right"><xms:localization text="46 to 60"/></th>
                                                        <th class="text-right"><xms:localization text="61 to 90"/></th>
                                                        <th class="text-right"><xms:localization text="91 to 120"/></th>
                                                        <th class="text-right"><xms:localization text="Over 120"/></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:if test="aging!=null">
                                                        <tr>
                                                            <td><s:property value="currentDate"/></td>
                                                            <td><s:property value="customerCode"/></td>
                                                            <td class="text-right"><s:property value="currencySymbol"/>
                                                                <s:property value="aging.totalDue"/></td>
                                                            <td class="text-right"><s:property value="currencySymbol"/>
                                                                <s:property value="aging.range0"/></td>
                                                            <td class="text-right"><s:property value="currencySymbol"/>
                                                                <s:property value="aging.range1To15"/></td>
                                                            <td class="text-right"><s:property value="currencySymbol"/>
                                                                <s:property value="aging.range16To30"/></td>
                                                            <td class="text-right"><s:property value="currencySymbol"/>
                                                                <s:property value="aging.range31To45"/></td>
                                                            <td class="text-right"><s:property value="currencySymbol"/>
                                                                <s:property value="aging.range46To60"/></td>
                                                            <td class="text-right"><s:property value="currencySymbol"/>
                                                                <s:property value="aging.range61To90"/></td>
                                                            <td class="text-right"><s:property value="currencySymbol"/>
                                                                <s:property value="aging.range91To120"/></td>
                                                            <td class="text-right"><s:property value="currencySymbol"/>
                                                                <s:property value="aging.range120"/></td>
                                                        </tr>
                                                    </s:if>
                                                    <s:else>
                                                        <tr>
                                                            <td colspan="11"><xms:localization
                                                                    text="No data available..."/></td>
                                                        </tr>
                                                    </s:else>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="col-lg-6">
                                                <p style="border: 1px solid #ddd; padding: 8px;">
                                                    <strong><xms:localization text="BILL TO:"/> </strong> <br>
                                                    <s:property value="billingAddress" escape="false"/>
                                                </p>
                                            </div>
                                            <div class="col-lg-6">
                                                <p style="border: 1px solid #ddd; padding: 8px;">
                                                    <strong><xms:localization text="MAIL PAYMENT TO:"/> </strong> <br>
                                                    <s:property value="mailPaymentAddress" escape="false"/>
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
                                                                    <td><s:select id="pageSize" name="pageSize"
                                                                                  cssClass="form-control"
                                                                                  cssStyle="height: 22px; padding-top: 1px;"
                                                                                  list="pageSizes"
                                                                                  onchange="invoicePaging()"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <form id="statement_invoice_form">
                                                    <input type="hidden" id="orderField" name="orderField"
                                                           value="invoice_code"/> <input type="hidden" id="orderType"
                                                                                         name="orderType" value="0"/>
                                                    <input type="hidden" id="page" name="page" value="1"/>
                                                </form>
                                                <div id="customer_invoice_list">
                                                    <div class="s70">
                                                        <table class="table table-bordered mg0"
                                                               style="margin-top: 20px;"
                                                               id="customer_invoice_list_table">
                                                            <thead>
                                                            <tr bgcolor="#f9f9f9">
                                                                <th><xms:localization text="Invoice #"/></th>
                                                                <th><xms:localization text="Invoice Date"/></th>
                                                                <th><xms:localization text="Due Date"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Invoice Amount"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Late Fee"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Invoice Total"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Total Paid"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Remaining Due"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="invoices==null || invoices.totalRecords==0">
                                                                <tr>
                                                                    <td colspan="8"><xms:localization
                                                                            text="No data available..."/></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:else>
                                                                <s:iterator value="invoices.records">
                                                                    <tr>
                                                                        <td><s:property value="invoiceCode"/></td>
                                                                        <td><s:property value="invoiceDate"/></td>
                                                                        <td><s:property value="dueDate"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="invoiceAmount"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="lateFee"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="invoiceTotal"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="totalPaid"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="remainingDue"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                                <tr>
                                                                    <th colspan="8"><xms:localization text="Showing"/>
                                                                        <s:property value="invoices.startRecord"/>
                                                                        <xms:localization text="to"/> <s:property
                                                                                value="invoices.endRecord"/>
                                                                        <xms:localization text="of"/> <s:property
                                                                                value="invoices.totalRecords"/></th>
                                                                </tr>
                                                                <s:if test="summary!=null">
                                                                    <tr>
                                                                        <td colspan="8"><span
                                                                                class="b4"> <b><xms:localization
                                                                                text="Total Due: "/></b> <s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="summary.remainingDue"/></span>
                                                                        </td>
                                                                    </tr>
                                                                </s:if>
                                                            </s:else>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="invoices!=null">
                                                            <s:if test="invoices.hasPrev()">
                                                                <a href="javascript:changePage(<s:property value="%{invoices.currentPage - 1}"/>)"
                                                                   class="paginate_button previous"><xms:localization
                                                                        text="Previous"/></a>
                                                            </s:if>
															<span> <s:iterator value="invoices.pageRange"
                                                                               status="count">
                                                                <s:if test="%{invoices.pageRange[#count.index] == invoices.currentPage}">
                                                                    <a class="paginate_button current"><s:property
                                                                            value="invoices.currentPage"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button"
                                                                       href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                </s:else>
                                                            </s:iterator>
															</span>
                                                            <s:if test="invoices.hasNext()">
                                                                <a class="paginate_button next"
                                                                   href="javascript:changePage(<s:property value="%{invoices.currentPage+1}"/>)"><xms:localization
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
    var dataExport = "";
    function searchCustomers() {
        var data = {
            "franchiseCode": $("#franchiseCode option:selected").val()
        };
        loadingDialog.dialog("open");
        $.post("statements_get_customers.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customer_list_result").html(res.content);
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

    function statementDetail() {
        if ($("#customerCode option:selected").val() == "") {
            return;
        }
        var data = {
            "customerCode": $("#customerCode option:selected").val()
        };
        dataExport = data;
        loadingDialog.dialog("open");
        $.post("statements_get_customer_detail.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#statement_customer_detail").html(res.content);
                $("#export-btns").show();
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

    function invoicePaging() {
        var data = $("#statement_invoice_form").serialize();
        data += "&pageSize=" + $("#pageSize option:selected").val();
        data += "&customerCode=" + $("#customerCode option:selected").val();
        loadingDialog.dialog("open");
        $.post("statements_get_invoice_paging.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customer_invoice_list").html(res.content);
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
        invoicePaging();
    }
    function exportClick(option) {
        var data = dataExport;
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="View printable copy" />");
                $.post("statements_print.ix?reqType=json", data, function (res) {
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
            case 'pdf':
                $("#export-option").val("<xms:localization text="View PDF copy" />");
                loadingDialog.dialog("open");
                $.fileDownload("statements_export_pdf.ix", {
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