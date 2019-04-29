<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <div class="col-lg-12 text-right">
        <div class="form-group text-right">
            <select id="filterType" name="filterType" class="form-control" style="width: 150px;"
                    onchange="getInvoices(1)">
                <option value="0"><xms:localization text="All Invoices"/></option>
                <option value="1"><xms:localization text="Outstanding Invoices"/></option>
                <option value="2"><xms:localization text="Overdue Invoices"/></option>
                <option value="3"><xms:localization text="Paid Invoices"/></option>
            </select>
        </div>
    </div>
    <div class="col-lg-4">
        <table class="table" style="font-size: 11px;">
            <tr>
                <td colspan="2" style="border-top: 0px !important">
                    <div class="caption b17">
                        <s:property value="customerDetail.billingCustomerName"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Phone"/> :</td>
                <td class="td2"><s:property value="customerDetail.billingPhone"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Email"/> :</td>
                <td class="td2"><s:property value="customerDetail.billingEmail"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Attn"/> :</td>
                <td class="td2"><s:property value="customerDetail.billingContactName"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Fax"/> :</td>
                <td class="td2"><s:property value="customerDetail.billingFax"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Terms"/> :</td>
                <td class="td2"><s:property value="customerDetail.invoiceDays"/> <xms:localization text="days"/></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-8">
        <table class="" style="font-size: 11px;">
            <tr>
                <td colspan="" style="border-top: 0px !important">
                    <div class="caption b17">
                        <xms:localization text="Aging"/>
                    </div>
                </td>
            </tr>
        </table>
        <table class="table table-bordered mg0 table-pointer">
            <thead>
            <tr>
                <th><xms:localization text="Total Due"/></th>
                <th><xms:localization text="Total Overdue"/></th>
                <th><xms:localization text="<=0 days" escapeHtml="true"/></th>
                <th><xms:localization text="1 to 15"/></th>
                <th><xms:localization text="16 to 30"/></th>
                <th><xms:localization text="31 to 45"/></th>
                <th><xms:localization text="45 to 60"/></th>
                <th><xms:localization text="61 to 90"/></th>
                <th><xms:localization text="91 to 120"/></th>
                <th><xms:localization text="Over 120"/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="aging!=null">
                <tr>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="aging.totalDue"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="aging.totalOverdue"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="aging.range0"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="aging.range1To15"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="aging.range16To30"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="aging.range31To45"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="aging.range46To60"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="aging.range61To90"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="aging.range91To120"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="aging.range120"/></td>
                </tr>
            </s:if>
            <s:else>
                <tr>
                    <td colspan="10"><xms:localization text="No data available..."/></td>
                </tr>
            </s:else>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12">
        <table class="" style="font-size: 11px;">
            <tbody>
            <tr>
                <td style="border-top: 0px !important" colspan="">
                    <div class="caption b17">
                        <xms:localization text="Invoices"/>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <table class="table mg0">
            <tbody>
            <tr>
                <th class="s42">
                    <table class="s36">
                        <tbody>
                        <tr>
                            <td><xms:localization text="Show"/></td>
                            <td><s:select id="payment_invoice_pageSize" name="pageSize" list="pageSizes"
                                          cssClass="form-control"
                                          cssStyle="height: 22px; padding-top: 1px; width: 55px;"
                                          onchange="getInvoices(1)"/></td>
                            <td><xms:localization text="Entries"/></td>
                        </tr>
                        </tbody>
                    </table>
                </th>
            </tr>
            </tbody>
        </table>
        <div id="invoice-list-result">
            <table class="table table-bordered mg0 table-pointer">
                <thead>
                <tr>
                    <th>&nbsp;</th>
                    <th><xms:localization text="Invoice No"/></th>
                    <th class="text-right"><xms:localization text="Total"/></th>
                    <th class="text-right"><xms:localization text="Late Fee"/></th>
                    <th class="text-right"><xms:localization text="Total+Fees"/></th>
                    <th class="text-right"><xms:localization text="Paid/Credit"/></th>
                    <th class="text-right"><xms:localization text="Owed"/></th>
                    <th class="text-right"><xms:localization text="Date"/></th>
                    <th class="text-right"><xms:localization text="Due Date"/></th>
                    <th class="text-right"><xms:localization text="Overdue"/></th>
                    <th class="text-right"><xms:localization text="Airbill"/></th>
                </tr>
                </thead>
                <tbody>
                <s:if test="invoices!=null && invoices.totalRecords>0">
                    <s:iterator value="invoices.records">
                        <tr onclick="showInvoiceInfo('<s:property value="invoiceCode"/>')">
                            <td><s:if test="status!=0">
                                <a target="_blank"
                                   href='<%=WebUtils.getContextPathURL(request)%>receive_payment.ix?receivePayment.customerOrInvoiceCode=<s:property value="invoiceCode" />&receivePayment.submitType=Go&receivePayment.searchOption=0'><i
                                        class="fa fa-plus" aria-hidden="true"></i></a>
                                <a href="javascript:void(0)"
                                   onclick='showAddInvoiceNoteDialog("<s:property value="invoiceCode"/>")'><i
                                        class="fa fa-file-text-o" aria-hidden="true"></i></a>
                                <a href="javascript:void(0)"
                                   onclick='showNotesAndFollowUpDialog("<s:property value="invoiceCode"/>")'><i
                                        class="fa fa-list" aria-hidden="true"></i></a>
                            </s:if> <s:else>
                                <a href="javascript:void(0)"
                                   onclick='showNotesAndFollowUpDialog("<s:property value="invoiceCode"/>")'><i
                                        class="fa fa-list" aria-hidden="true"></i></a>
                            </s:else></td>
                            <td><a href="javascript:void(0)"
                                   onclick='viewInvoiceDetail(<s:property value="invoiceId"/>)'><s:property
                                    value="invoiceCode"/></a></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property value="total"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="lateFee"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="totalCost"/></td>
                            <td class="text-right" data-paid=""><s:property value="currencySymbol"/> <s:property
                                    value="paid"/></td>
                            <td class="text-right" data-owed=""><s:property value="currencySymbol"/> <s:property
                                    value="owed"/></td>
                            <td class="text-right"><s:property value="invoiceDate"/></td>
                            <td class="text-right"><s:property value="dueDate"/></td>
                            <td class="text-right"><s:if test="overDue!=null">
                                <s:property value="overDue"/>
                                <xms:localization text="days"/>
                            </s:if></td>
                            <td class="text-right"><s:property value="airbillCount"/></td>
                        </tr>
                    </s:iterator>
                </s:if>
                <s:else>
                    <tr>
                        <td colspan="10"><xms:localization text="No data available"/>...</td>
                    </tr>
                </s:else>
                </tbody>
            </table>
            <script type="text/javascript">
                $(document).ready(function () {
                    $('table.table-pointer tbody tr').click(function () {
                        $(this).addClass('selected-row').siblings().removeClass('selected-row');
                    });
                });
            </script>
            <div class="dataTables_paginate records">
                <div class="row">
                    <div class="col-xs-4 text-left">
                        <b><xms:localization text="Showing"/> <s:property value="invoices.startRecord"/>
                            <xms:localization text="to"/> <s:property value="invoices.endRecord"/> <xms:localization
                                    text="of"/> <s:property value="invoices.totalRecords"/> <xms:localization
                                    text="entries"/></b>
                    </div>
                    <div class="col-xs-8">
                        <s:if test="!invoices.hasPrev()">
                            <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
                        </s:if>
                        <s:else>
                            <a href="javascript:getInvoices(<s:property value="%{invoices.currentPage - 1}"/>)"
                               class="paginate_button previous"><xms:localization text="Previous"/></a>
                        </s:else>
						<span> <s:iterator value="invoices.pageRange" status="count">
                            <s:if test="%{invoices.pageRange[#count.index] == invoices.currentPage}">
                                <a class="paginate_button current"><s:property value="invoices.currentPage"/></a>
                            </s:if>
                            <s:else>
                                <a class="paginate_button"
                                   href="javascript:getInvoices(<s:property/>);"><s:property/></a>
                            </s:else>
                        </s:iterator>
						</span>
                        <s:if test="!invoices.hasNext()">
                            <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
                        </s:if>
                        <s:else>
                            <a class="paginate_button next"
                               href="javascript:getInvoices(<s:property value="%{invoices.currentPage+1}"/>)"><xms:localization
                                    text="Next"/></a>
                        </s:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="payment-list-result">
        <div class="col-lg-12 form-group">
            <table style="font-size: 11px; margin-top: 15px;">
                <tr>
                    <td colspan="" style="border-top: 0px !important">
                        <div class="caption b17">
                            <xms:localization text="Payments"/>
                        </div>
                    </td>
                </tr>
            </table>
            <table class="table table-bordered mg0">
                <thead>
                <tr>
                    <th></th>
                    <th><xms:localization text="Chq #"/></th>
                    <th class="text-right"><xms:localization text="Amount"/></th>
                    <th class="text-center"><xms:localization text="Date"/></th>
                    <th><xms:localization text="Follow Up"/></th>
                    <th><xms:localization text="Note"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="6"><xms:localization text="No data available..."/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div id="invoice-detail-result">
        <div class="col-lg-4">
            <table class="table" style="font-size: 11px;">
                <tr>
                    <td colspan="2" style="border-top: 0px !important">
                        <div class="caption b17">
                            <xms:localization text="Invoice Detail"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="No"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Amount"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Paid"/> :</td>
                    <td class="td2" data-detail-paid=""></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Credit"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Bad Debt"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Satisfaction Credit"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Owed"/> :</td>
                    <td class="td2" data-detail-owed=""></td>
                </tr>
            </table>
        </div>
        <div class="col-lg-4">
            <table class="table" style="font-size: 11px;">
                <tr>
                    <td colspan="2" style="border-top: 0px !important">
                        <div class="caption b17">&nbsp;</div>
                    </td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Date"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Due Date"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Days Over"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Airbills"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Margin(Excl. Tax)"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Late Fee"/> :</td>
                    <td class="td2"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Paid Late Fee"/> :</td>
                    <td class="td2"></td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div id="add_payment_dialog" title='<xms:localization text="Add Payment"/>'></div>
<div id="invoice_detail_dialog" title='<xms:localization text="Invoice"/>'></div>
<div id="add_invoice_note_dialog" title='<xms:localization text="Adding a Note"/>'></div>
<div id="notes_and_follow_up_dialog" title='<xms:localization text="Notes and Follow Ups"/>'></div>
<script type="text/javascript">
    function getInvoices(page) {
        // Clean Payment List and Invoice Detail part
        $("#payment-list-result").html("");
        $("#invoice-detail-result").html("");
        // Load Invoices List
        var pageSize = $("#payment_invoice_pageSize option:selected").val();
        var type = $("#filterType option:selected").val();
        var customerCode = $("#sel_franchise option:selected").val();
        var data = {
            "filterType": type,
            "page": page,
            "pageSize": pageSize,
            "customerCode": customerCode
        };
        loadingDialog.dialog("open");
        $.post("manage_franchise_payments_search_invoices.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#invoice-list-result").html(res.content);
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

    function showInvoiceDetail(code) {
        var data = {
            "invoiceCode": code
        };
        $.post("manage_franchise_payments_get_invoice_detail.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#invoice-detail-result").html(res.content);
                // Update selected row.
                var totalPaidCredit = $("#invoice-detail-result #total-paid-credit").val();
                var owed = $("#invoice-detail-result td[data-detail-owed]").html();
                $("#invoice-list-result tr[class='selected-row'] td[data-paid]").html("<s:property value="currencySymbol" /> " + totalPaidCredit);
                $("#invoice-list-result tr[class='selected-row'] td[data-owed]").html(owed);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function showPayments(code) {
        var data = {
            "invoiceCode": code
        };
        $.post("manage_franchise_payments_get_payments.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#payment-list-result").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function showInvoiceInfo(code) {
        showInvoiceDetail(code);
        showPayments(code);
    }

    function showAddPaymentDialog(invoicePaymentId) {
        var data = {
            "invoicePaymentId": invoicePaymentId
        };
        loadingDialog.dialog("open");
        $.post("add_payment_show.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                var buttons = {};
                buttons["<xms:localization text="Save" />"] = function () {
                    addPayment();
                }
                buttons["<xms:localization text="Cancel" />"] = function () {
                    $(this).dialog("close");
                }
                var dialog = $("#add_payment_dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: 'auto',
                    height: 'auto',
                    maxWidth: '400',
                    maxHeight: '400',
                    buttons: buttons,
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                // Get dialog content.
                dialog.html(res.content);
                // Show dialog.
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

    function addPayment() {
        var data = $("#add_payment_form").serialize();
        loadingDialog.dialog("open");
        $.post("add_payment_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Close add payment dialog.
                $("#add_payment_dialog").dialog("close");
                messageDialog.html('<xms:localization text="Add payment successfull." />');
                messageDialog.dialog("open");
                // Update invoice info.
                updateInvoiceInfo();
            } else if (res.errorCode == "FIELD_ERROR") {
                $("#add_payment_form").replaceWith(res.content);
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

    function updateInvoiceInfo() {
        var invoiceCode = $("#add_payment_form #add_payment_invoice_code").html();
        showInvoiceInfo(invoiceCode);
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

    function showAddInvoiceNoteDialog(invoiceCode) {
        var data = {
            "invoiceCode": invoiceCode
        };
        loadingDialog.dialog("open");
        $.post("add_invoice_note.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                var buttons = {};
                buttons["<xms:localization text="Save" />"] = function () {
                    saveInvoiceNote();
                }
                buttons["<xms:localization text="Cancel" />"] = function () {
                    $(this).dialog("close");
                }
                var dialog = $("#add_invoice_note_dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: 'auto',
                    height: 'auto',
                    maxWidth: '400',
                    maxHeight: '400',
                    buttons: buttons,
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                // Get dialog content.
                dialog.html(res.content);
                // Show dialog.
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

    function saveInvoiceNote() {
        var data = $("#invoice_note_form").serialize();
        loadingDialog.dialog("open");
        $.post("save_invoice_note.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Close add invoice note dialog.
                $("#add_invoice_note_dialog").dialog("close");
                messageDialog.html('<xms:localization text="Add note successfull." />');
                messageDialog.dialog("open");
            } else if (res.errorCode == "FIELD_ERROR") {
                $("#invoice_note_form").replaceWith(res.content);
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

    function showNotesAndFollowUpDialog(invoiceCode) {
        var data = {
            "invoiceCode": invoiceCode
        };
        loadingDialog.dialog("open");
        $.post("note_and_follow_up_show.ix?reqType=json", data, function (res) {
            var dialog = $("#notes_and_follow_up_dialog").dialog({
                modal: true,
                autoOpen: false,
                width: 'auto',
                height: 'auto',
                maxWidth: '800',
                maxHeight: '600',
                show: {
                    effect: "fade",
                    duration: 300
                }
            });
            // Get dialog content.
            dialog.html(res.content);
            // Show dialog.
            dialog.dialog("open");
            loadingDialog.dialog("close");
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }
</script>