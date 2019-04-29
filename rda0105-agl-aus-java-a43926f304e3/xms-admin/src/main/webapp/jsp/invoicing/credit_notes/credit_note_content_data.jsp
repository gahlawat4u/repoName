<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<div class="tab-content responsive">
    <div id="Overview-tab" class="tab-pane fade in active">
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group flr mgb">
                    <div class="btn-group">
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
                                <a href="#" onclick="exportClick('option')"><xms:localization text="Option"/></a>
                            </li>
                            <li>
                                <a href="#" onclick="exportClick('html')"><xms:localization
                                        text="View Printable Copy"/></a>
                            </li>
                            <s:if test="grandTotal != 0">
                                <li>
                                    <a href="#" onclick="exportClick('pdf')"><xms:localization
                                            text="View PDF Copy"/></a>
                                </li>
                            </s:if>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <table class="table" style="font-size: 11px;">
                    <tr>
                        <td class="td1"><img src="images/LOGOiN.png" width="140"/></td>
                        <td class="td2"><s:property escape="false" value="systemAdminInfo.systemAddress"/> <br/>
                            <s:property value="systemAdminInfo.siteAddress"/></td>
                    </tr>
                </table>
            </div>
            <div class="col-lg-4">
                <table class="table" style="font-size: 11px;">
                    <tr>
                        <td class="td1"><xms:localization text="CREDIT TO:"/></td>
                        <td class="td2"><s:property value="creditNoteInfoModel.billingCustomerName"/> <br/> <s:property
                                value="creditNoteInfoModel.billingContactName"/> <br/> <s:property
                                value="creditNoteInfoModel.billingAddress1"/>, <s:property
                                value="creditNoteInfoModel.billingAddress2"/> <br/> <s:property
                                value="creditNoteInfoModel.billingCity"/>, <s:property
                                value="creditNoteInfoModel.countryName"/>, <s:property
                                value="creditNoteInfoModel.billingPostalCode"/></td>
                    </tr>
                </table>
            </div>
            <div class="col-lg-4">
                <table class="table" style="font-size: 11px;">
                    <tr>
                        <td class="td1"><xms:localization text="MAIL PAYMENT TO:"/></td>
                        <td class="td2"><s:property escape="false" value="systemAdminInfo.mailPaymentToAddress"/></td>
                    </tr>
                </table>

            </div>
            <div class="col-lg-12">
                <table class="table table-striped table-bordered mg0" style="font-size: 11px;">
                    <tr>
                        <th><xms:localization text="Credit Number"/></th>
                        <th><xms:localization text="Credit Date"/></th>
                        <th><xms:localization text="Customer #"/></th>
                        <th><xms:localization text="Credits"/></th>
                        <th><xms:localization text="Total Credited"/></th>
                    </tr>
                    <tr>

                        <td><s:property value="creditNoteInfoModel.creditCode"/></td>
                        <td><s:property value="creditNoteInfoModel.createDate"/></td>
                        <td><s:property value="creditNoteInfoModel.customerCode"/></td>
                        <td class="text-right"><s:property value="creditNoteInfoModel.credits"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                value="creditNoteInfoModel.totalCredited"/></td>
                    </tr>
                </table>
                <br/>
            </div>
            <div class="col-lg-5">
                <table class="table mg0">
                    <tr>
                        <th class="s42"></th>
                    </tr>
                </table>
                <table class="table table-bordered mg0" style="font-size: 11px;" id="tbl-gst-summary">
                    <tr class="b1">
                        <th><xms:localization text="GST Summary"/></th>
                        <th><xms:localization text="GST Percent"/></th>
                        <th><xms:localization text="Credit Amount"/></th>
                        <th><xms:localization text="GST Amount"/></th>
                        <th><xms:localization text="Total Amount"/></th>
                    </tr>
                    <s:if test="gstSummaryList != null && !gstSummaryList.isEmpty()">
                        <s:iterator value="gstSummaryList">
                            <tr>
                                <s:if test="customerTaxPercent != 0">
                                    <th class="b1"><xms:localization text="GST Shipments"/></th>
                                </s:if>
                                <s:else>
                                    <th class="b1"><xms:localization text="Non-GST Shipments"/></th>
                                </s:else>
                                <td class="text-right"><s:property value="customerTaxPercent"/>%</td>
                                <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                        value="customerAmount"/></td>
                                <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                        value="gstCustomerAmount"/></td>
                                <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                        value="total"/></td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <tr>
                            <th class="b1"><xms:localization text="GST Shipments"/></th>
                            <td class="text-right"><s:property value="creditNoteInfoModel.taxPercent"/>%</td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="creditNoteInfoModel.shipmentAmount"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="creditNoteInfoModel.gstAmount"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="creditNoteInfoModel.totalAmount"/></td>
                        </tr>
                        <tr>
                            <th class="b1"><xms:localization text="Non-GST Shipments"/></th>
                            <td class="text-right">0.00%</td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="creditNoteInfoModel.nonShipmentAmount"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="creditNoteInfoModel.nonGstAmount"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="creditNoteInfoModel.nonTotalAmount"/></td>
                        </tr>
                    </s:else>
                </table>
            </div>
            <div class="col-lg-7">
                <table class="table mg0">
                    <tr>
                        <th class="s42"></th>
                    </tr>
                </table>
                <table class="table table-bordered table-hover mg0" style="font-size: 11px;" id="tbl-invoice">
                    <tr>

                        <th><xms:localization text="Invoice #"/></th>
                        <th><xms:localization text="Airbill #"/></th>
                        <th><xms:localization text="Invoice Date"/></th>
                        <th><xms:localization text="Reason for Credit"/></th>
                        <th><xms:localization text="Amount"/></th>
                        <th><xms:localization text="GST Amount"/></th>
                        <th><xms:localization text="Total Credit"/></th>
                    </tr>
                    <s:iterator value="listAdjustment">
                        <tr>
                            <td><s:property value="invoiceCode"/></td>
                            <td><s:property value="airbillNumber"/></td>
                            <td><s:property value="invoiceDate"/></td>
                            <td><s:property value="reason"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="shipmentAmount"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="gstAmount"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="totalCredit"/></td>
                        </tr>
                    </s:iterator>
                    <tr>
                        <td colspan="7"><b><xms:localization text="GRAND TOTAL:"/> <s:property value="currencySymbol"/>
                            <s:property value="grandTotal"/></b></td>
                    </tr>
                </table>
            </div>

        </div>
    </div>
</div>
<div id="confirm-dialog" title="<xms:localization text="Confirmation" />"></div>
<script type="text/javascript">
    var status = "<s:property value="creditNoteInfoModel.status" />";
    var confirmDialog = $("#confirm-dialog").dialog({
        modal: true,
        autoOpen: false,
        width: "auto"
    });
    $(document).ready(function () {
        $("#tbl-invoice").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: ["invoiceCode", "airbillNumber", "invoiceDate", "reason", "shipmentAmount", "gstAmount", "totalCredit"],
            callback: function () {
                showInforCreditCode($("#listCreditCode".val()));
            }
        });

        if (status == 1) {
            $("#frozen-status").show();
        } else {
            $("#frozen-status").hide();
        }
        $("#credit-note-status").val(status);
    });
    function exportClick(option) {
        var creditNoteCode = $("#selected-credit-note").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");

                break;
            case 'html':
                $("#export-option").val("<xms:localization text="View Printable Copy" />");
                confirmDialog.dialog("option", "buttons", {
                    OK: function () {
                        $.post("credit_notes_printable_preview.ix", {
                            "creditNotesPageModel.creditNoteCode": creditNoteCode
                        }, function (res) {
                            loadingDialog.dialog("close");
                            var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                            win.document.write(res);
                        });
                        $(this).dialog("close");
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                    }
                });
                if (status == 0) {
                    confirmDialog.html("<xms:localization text="This credit note has not been frozen. Are you sure to continue printing?" />")
                    confirmDialog.dialog("open");
                } else {
                    $.post("credit_notes_printable_preview.ix", {
                        "creditNotesPageModel.creditNoteCode": creditNoteCode
                    }, function (res) {
                        loadingDialog.dialog("close");
                        var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                        win.document.write(res);
                    });
                }
                break;
            case 'pdf':
                $("#export-option").val("<xms:localization text="View PDF Copy" />");
                confirmDialog.dialog("option", "buttons", {
                    OK: function () {
                        var url = "credit_notes_printable_pdf.ix?creditNotesPageModel.creditNoteCode=" + creditNoteCode;
                        window.open(url);
                        $(this).dialog("close");
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                    }
                });
                if (status == 0) {
                    confirmDialog.html("<xms:localization text="This credit note has not been frozen. Are you sure to continue printing?" />")
                    confirmDialog.dialog("open");
                } else {
                    var url = "credit_notes_printable_pdf.ix?creditNotesPageModel.creditNoteCode=" + creditNoteCode;
                    window.open(url);
                }
                break;
        }

    }


</script>