<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!-- Form Modal -->
<form id="frmApplyToInvoices">
    <s:hidden name="overpayment.submitType"/>
    <table class="table" style="font-size: 11px; margin-bottom: 0px">
        <tr>
            <td class="td1"><xms:localization text="Customer Number:"/></td>
            <td class="td2 s51" id="customer-code-td"><s:property value="overpayment.customerCode"/> <s:hidden
                    name="overpayment.customerCode"></s:hidden></td>
            <td class="td1"><xms:localization text="Customer Name:"/></td>
            <td class="td2 s51"><s:property value="overpayment.customerName"/> <s:hidden
                    name="overpayment.customerName"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Select Overpayments:"/></td>
            <td class="td2" id="overpayment-list"><s:select list="overpaymentList" listKey="cusPaymentId"
                                                            listValue="overAmount" name="overpayment.cusPaymentId"
                                                            id="overpayment-select"
                                                            onchange="changeOverpaymentList()"/></td>
            <td class="td1"><xms:localization text="Amount Apply:"/></td>
            <td class="td2 s51"><label id="overpayment_appliedAmount_label"></label> <s:hidden
                    name="overpayment.appliedAmount" id="overpayment_appliedAmount"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Source of Overpayment:"/></td>
            <td class="td2"><label id="source_overpayment_label"></label></td>
            <td class="td1"><xms:localization text="Remaining Amount:"/></td>
            <td class="td2 s51"><label id="overpayment_unappliedAmount_label"></label> <s:hidden
                    name="overpayment.unappliedAmount" id="overpayment_unappliedAmount"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Amount To Apply:"/></td>
            <td class="td2 s51"><span id="lblAmountToApply"><s:property value="overpayment.amountToApply"/></span>
                <s:hidden name="overpayment.amountToApply"/></td>
        </tr>
    </table>
    <div class="text-left pdt10">
        <button class="btn s37" type="button" onclick="deleteOverpayment()">
            <xms:localization text="Refund / Delete Overpayment"/>
        </button>
    </div>
    <br/>
    <table class="table table-bordered mg0" id="datatable1">
        <thead>
        <tr>
            <th><xms:localization text="Invoice Number"/></th>
            <th><xms:localization text="Due Date"/></th>
            <th><xms:localization text="Total"/></th>
            <th><xms:localization text="Late Fee"/></th>
            <th><xms:localization text="Paid /Credit"/></th>
            <th><xms:localization text="Owed"/></th>
            <th><xms:localization text="Payments"/></th>
            <th><xms:localization text="AWB Level"/></th>
            <th width="25px;"></th>
        </tr>
        </thead>
        <tbody id="inv_ovrpmt_invoice_list">
        <s:if test="%{invoiceList==null || invoiceList.isEmpty()}">
            <tr>
                <td colspan="9"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="invoiceList" status="row">
                <s:hidden name="invoiceList[%{#row.index}].invoiceId"/>
                <s:hidden name="invoiceList[%{#row.index}].invoiceCode"/>
                <s:hidden name="invoiceList[%{#row.index}].customerCode"/>
                <s:hidden name="invoiceList[%{#row.index}].invoiceDate"/>
                <s:hidden name="invoiceList[%{#row.index}].lateFee"/>
                <s:hidden name="invoiceList[%{#row.index}].status"/>
                <s:hidden name="invoiceList[%{#row.index}].paid"/>
                <s:hidden name="invoiceList[%{#row.index}].invCreateDate"/>
                <s:hidden name="invoiceList[%{#row.index}].totalAmount"/>
                <s:hidden name="invoiceList[%{#row.index}].totalPayment"/>
                <s:hidden name="invoiceList[%{#row.index}].totalAwbPayment"/>
                <s:hidden name="invoiceList[%{#row.index}].unreconcileAmount"/>
                <s:hidden name="invoiceList[%{#row.index}].dueDate"/>
                <s:hidden name="invoiceList[%{#row.index}].remainningBalance"
                          id="invoiceList_%{#row.index}_remainningBalance"/>
                <s:hidden name="invoiceList[%{#row.index}].awbLevel"/>
                <s:hidden name="invoiceList[%{#row.index}].appliedAmount"/>
                <s:hidden name="invoiceList[%{#row.index}].unappliedAmount"/>
                <tr>
                    <td><s:property value="invoiceCode"/></td>
                    <td><s:property value="dueDate"/></td>
                    <td align="right"><s:property value="totalAmount"/></td>
                    <td align="right"><s:property value="lateFee"/></td>
                    <td align="right"><s:property value="totalPayment"/></td>
                    <td align="right"><s:if test="%{awbLevel!=1 && awbLevel!=3}">
                        <a href="javascript:autofillInvoicePayment('<s:property value="%{#row.index}"/>')"> >> </a>
                    </s:if> <s:property value="remainningBalance"/></td>
                    <th align="right"><s:if test="%{awbLevel!=1}">
                        <input type="text" name="invoiceList[<s:property value="%{#row.index}" />].payment"
                               id="invoiceList_<s:property value="%{#row.index}" />_payment"
                               value="<s:property value="payment"/>" data-invoice_payment_amount="invoice_payment"
                               class="form-control s50"
                               data-invoice_index="<s:property value="%{#row.index}" />"/>
                    </s:if> <s:else>
                        <s:hidden name="invoiceList[%{#row.index}].payment"/>
                    </s:else></th>
                    <th align="center"><a
                            href="<s:if test="%{awbLevel!=1}">javascript:loadInvDetails('inv_<s:property value="invoiceCode" />_details');</s:if><s:else>javascript:void(0);</s:else>">
                        <i id="invoiceList_<s:property value="%{#row.index}" />_payment_status"
                           class="fa fa
																					<s:if test="%{awbLevel==0}">
																						fa-exclamation-triangle text-warning
																					</s:if>
																					<s:elseif test="%{awbLevel==1}">
																						fa-times-circle text-danger
																					</s:elseif>
																					<s:elseif test="%{awbLevel==2}">
																						fa-check-circle text-success
																					</s:elseif>
																					<s:elseif test="%{awbLevel==3}">
																						fa-times text-warning
																					</s:elseif>
																					<s:else>
																					</s:else>
																					s10"
                           style="font-size: 18px;"></i></a></th>
                    <th><s:if test="%{awbLevel!=1 && awbLevel!=3}">
                        <a href="javascript:resetInvoicePayment('<s:property value="%{#row.index}" />')"> <i
                                class="fa fa-times-circle-o s10" style="font-size: 18px;"></i>
                        </a>
                    </s:if> <!-- Begin Invoice Details -->
                        <div id="inv_<s:property value="invoiceCode" />_details" title="Apply Payment to Airbills"
                             style="display: none">
                            <s:iterator value="shipmentInvoices" status="awbrow">
                                <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].invoiceId"
                                          id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_invoiceId"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].shipmentId"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_shipmentId"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].airbillNumber"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_airbillNumber"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].cusPaymentId"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_cusPaymentId"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].paidCustomerCost"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_paidCustomerCost"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].paidCarrierCost"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_paidCarrierCost"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].invoicePaymentId"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_invoicePaymentId"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].totalAmount"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_totalAmount"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].totalPayment"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_totalPayment"/>
                                <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].amountDue"
                                          id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_amountDue"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].adjustmentCredit"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_adjustmentCredit"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].pendingAdjustment"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_pendingAdjustment"/>
                                <s:hidden
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].deniedAdjustment"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_deniedAdjustment"/>
                                <s:textfield
                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].payment"
                                        class="form-control s50"
                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_payment"
                                        data-airbill_payment_amount="payment" data-awb_invoice_index="%{#row.index}"
                                        data-awb_shipment_index="%{#awbrow.index}"/>
                            </s:iterator>
                        </div>
                        <!-- End Invoice Details --></th>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
    <div class="form-actions pal pdt10">
        <div class="row">
            <div class="col-lg-5"></div>
            <div class="col-lg-7 text-right">
                <button class="btn s37" type="button" id="btnAuto">
                    <xms:localization text="Auto-Apply Payment"/>
                </button>
                <button class="btn s37" type="button" id="btnReset">
                    <xms:localization text="Reset Payments"/>
                </button>
                <button class="btn s37" type="button" id="btnSave">
                    <xms:localization text="Save"/>
                </button>
                <button class="btn s37" type="button" id="btnCancel">
                    <xms:localization text="Cancel"/>
                </button>
            </div>
        </div>
    </div>
</form>
<br/>
<s:if test="%{invoiceList!=null && invoiceList.size()>0}">
    <s:iterator value="invoiceList" status="row">
        <!-- Begin Invoice Details -->
        <div id="inv_<s:property value="invoiceCode" />_details_tmp" title="Apply Payment to Airbills"
             style="display: none">
            <span style="display: none" id='inv_<s:property value="invoiceCode" />'><s:property
                    value="invoiceCode"/></span>
            <table class="table" style="font-size: 11px; margin-bottom: 0px">
                <tr>
                    <td class="td1" id="inv_<s:property value="invoiceCode" />_details_invoice_index"
                        index='<s:property value="%{#row.index}" />'><xms:localization text="Invoice"/></td>
                    <td class="td2" id="inv_<s:property value="invoiceCode" />_details_invoiceid"><s:property
                            value="invoiceCode"/></td>
                    <td class="td1"><xms:localization text="Remaining Unapplied Amount"/></td>
                    <td class="td2" align="right"><s:label id="invoiceList_%{#row.index}_unappliedAmount"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Amount Applied"/></td>
                    <td class="td2"><s:label id="invoiceList_%{#row.index}_appliedAmount"/></td>
                    <td class="td1"><xms:localization text="Total Unpaid on invoice"/></td>
                    <td class="td2"><s:property value="remainningBalance"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Previously Reconciled"/></td>
                    <td class="td2"><s:property value="totalAwbPayment"/></td>
                </tr>
            </table>
            <br>
            <table class="table table-hover table-bordeed mg0" id="datatable1">
                <thead>
                <tr>
                    <th><xms:localization text="Airbill"/></th>
                    <th><xms:localization text="Customer Total"/></th>
                    <th><xms:localization text="Previously Paid"/></th>
                    <th><xms:localization text="Adjustments Credits"/></th>
                    <th><xms:localization text="Amount Due"/></th>
                    <th><xms:localization text="Pending Adjustments"/></th>
                    <th><xms:localization text="Denied Adjustments"/></th>
                    <th><xms:localization text="Payments"/></th>
                    <th><i class="fa fa-times-circle-o s10" style="font-size: 18px;"></i></th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="shipmentInvoices" status="awbrow">
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].invoiceId"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].shipmentId"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].airbillNumber"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].cusPaymentId"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].paidCustomerCost"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].paidCarrierCost"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].invoicePaymentId"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].totalAmount"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].totalPayment"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].amountDue"
                              id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_amountDue"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].adjustmentCredit"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].pendingAdjustment"/>
                    <s:hidden name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].deniedAdjustment"/>
                    <tr <s:if test="%{amountDue==0}">style="display: none"</s:if>>
                        <td><s:property value="airbillNumber"/></td>
                        <td align="right"><s:property value="totalAmount"/></td>
                        <td align="right"><s:property value="totalPayment"/></td>
                        <td align="right"><s:property value="adjustmentCredit"/></td>
                        <td align="right"><a
                                href="javascript:autofillInvoiceAirbillPayment('<s:property value="%{#row.index}"/>','<s:property value="%{#awbrow.index}" />')">
                            >> </a> <s:property value="amountDue"/></td>
                        <td align="right"><s:property value="pendingAdjustment"/></td>
                        <td align="right"><s:property value="deniedAdjustment"/></td>
                        <td align="right"><s:textfield
                                name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].payment"
                                class="form-control s50"
                                id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_payment_tmp"
                                data-awb_payment_tmp="awb_payment_tmp" data-group_awb_payment="%{#row.index}"/></td>
                        <td>
                            <a href="javascript:resetValue('invoiceList_<s:property value="%{#row.index}" />_shipmentInvoices_<s:property value="%{#awbrow.index}" />_payment_tmp')">
                                <i class="fa fa-times-circle-o s10" style="font-size: 18px;"></i>
                            </a></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
        <!-- End Invoice Details -->
    </s:iterator>
</s:if>
<div id="inv-apply-payment-dialog" title='<xms:localization text="Apply Payment to Airbills" />'></div>
<div id="confirm-dialog"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#lblAmountToApply").html($("#overpayment-select option:selected").text());
        $("#overpayment_amountToApply").val($("#overpayment-select option:selected").text());

        $("#btnSave").click(function () {
            $("#btnSave").attr("disabled", true);
            submitApplyInvReq(2);
            $("#btnSave").attr("disabled", false);
        });
        $("#btnAuto").click(function () {
            autoApplyInvoice();
        });
        $("#btnReset").click(function () {
            resetPayment();
        });
        $("#btnCancel").click(function () {
            dialog.dialog("close");
        });
        changeOverpaymentList();
    });

    function changeOverpaymentList() {
        var overAmount = $("#overpayment-list option:selected").text();
        $("#lblAmountToApply").html(overAmount);
        $("#overpayment_amountToApply").val(overAmount);
        updateInvoicePaymentView();
        //var cusPId = $("#overpayment-select").val();
        //if (cusPId == "") return;

        var data = {
            'cusPaymentId': $("#overpayment-select").val()
        }
        $.post("get_paymenttype_by_paymentid.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#source_overpayment_label").html(res.content);
            } else {
                //alertDialog.html(res.errorMsg);
                //alertDialog.dialog("open");
                $("#source_overpayment_label").html(res.errorMsg);
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error" />");
            alertDialog.dialog("open");
            $("#source_overpayment_label").html("");
        });
    }

    function deleteOverpayment() {
        $("#confirm-dialog").html("Are you sure?");
        $("#confirm-dialog").dialog({
            resizable: false,
            modal: true,
            title: "Confirmation",
            height: 180,
            width: 240,
            buttons: {
                "Yes": function () {
                    $(this).dialog('close');
                    var cusPaymentId = $("#overpayment-list option:selected").val();
                    var data = {
                        'overpayment.cusPaymentId': cusPaymentId
                    };
                    $.post("inv_overpmt_delete.ix?reqType=json", data, function (res) {
                        if (res.errorCode == "SUCCESS") {
                            $("#inv-overpayment-dialog").dialog("close");
                            refreshTable();
                            messageDialog.html("<xms:localization text="Delete successful." />");
                            messageDialog.dialog("open");
                        } else {
                            alertDialog.html(res.errorMsg);
                            alertDialog.dialog("open");
                        }
                    }).fail(function () {
                        alertDialog.html("System internal error");
                        alertDialog.dialog("open");
                    });

                },
                "No": function () {
                    $(this).dialog('close');
                }
            }
        });
    }

    function changeSource(source) {
        var customerCode = $('#customer-code-td').find("input[type='hidden']").val();
        var data = {
            'overpayment.source': source,
            'overpayment.customerCode': customerCode
        }
        doPostDataByParameters("load_overpayment_list.ix?reqType=json", data, "", "overpayment-list", false);
    }

    function loadInvDetails(orgdivId) {
        var divId = orgdivId + "_tmp";
        var invoiceCode = $("#" + orgdivId + "_invoiceid").html();
        var invoiceIndex = $("#" + orgdivId + "_invoice_index").attr("index");
        console.log(invoiceCode);
        console.log(invoiceIndex);
        //load value

        $("div#" + orgdivId + " input[data-airbill_payment_amount]").each(function () {
            var id = $(this).attr('id');
            $("#" + id + "_tmp").val($("#" + id).val());
            console.log("#" + id + "_tmp" + " | " + $("#" + id).val());
        });
        calculateInvoiceUnappliedAmount(invoiceIndex);
        $("#" + divId).dialog({
            resizable: false,
            modal: true,
            width: 'auto',
            close: function (ev, ui) {
                $("div#" + orgdivId + " input[data-airbill_payment_amount]").each(function () {
                    var id = $(this).attr('id');
                    console.log(id + "_tmp");
                    $("#" + id).val($("#" + id + "_tmp").val());
                });
                $("input[data-invoice_payment_amount]").trigger("blur");
            },
            buttons: {
                "Auto-apply Payment": function () {
                    $(this).dialog("close");
                    autoApplyAirbill(invoiceIndex);
                    loadInvDetails(orgdivId);
                },
                "Reset Payments": function () {
                    $(this).dialog("close");
                    $("div#" + orgdivId + " input[data-airbill_payment_amount]").val("");
                    loadInvDetails(orgdivId);
                },
                "Done": function () {
                    $(this).dialog("close");
                }
            }
        });
    }

    function autoApplyAirbill(invoiceIndex) {
        var invoicePayment = Number($("#invoiceList_" + invoiceIndex + "_payment").val());
        console.log("invoicePayment : " + invoiceIndex + ":" + invoicePayment);
        $("*[data-awb_invoice_index='" + invoiceIndex + "']").each(function () {
            $(this).val("");
            var shipmentIndex = $(this).data("awb_shipment_index");
            var amountDue = Number($("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + shipmentIndex + "_amountDue").val());
            amountDue = amountDue.toFixed(2);
            amountDue = Number(amountDue);
            console.log("amountDue: " + amountDue + "|invoicePayment:" + invoicePayment);
            var awbPayment = Number(0);
            if (invoicePayment > 0 && amountDue > 0) {
                if (Number(invoicePayment) > Number(amountDue)) {
                    awbPayment = amountDue;
                } else {
                    awbPayment = invoicePayment;
                }
                invoicePayment = Number(invoicePayment - awbPayment);
                invoicePayment = invoicePayment.toFixed(2);
                if (awbPayment >= 0) {
                    awbPayment = Number(awbPayment);
                    awbPayment = awbPayment.toFixed(2);
                    $(this).val(awbPayment);
                }
            }
            console.log("apply payment: " + awbPayment + ":" + amountDue + ":" + invoicePayment);
            $(this).trigger("blur");
        });
    }

    $("input[data-invoice_payment_amount]").each(function () {
        $(this).number(true, 2, '.', '');
        $(this).blur(function () {
            updateInvoicePaymentView();
            var index = $(this).data("invoice_index");
            var invoiceOwed = Number($("#invoiceList_" + index + "_remainningBalance").val());
            var amount = Number($(this).val());
            if (invoiceOwed == amount) {
                autoApplyAirbill(index);
            }
            console.log("test autoApplyAirbill:" + index + ":" + invoiceOwed + ":" + amount);
            var total = caculateAwbPayment(index);
            var paymentStatusId = $(this).attr('id') + "_status";
            console.log("data-invoice_payment_amount:" + index + ":" + total + ":" + amount);
            if (amount != 0) {
                if (amount == total) {
                    $('#' + paymentStatusId).removeClass().addClass('fa fa fa-check-circle text-success');
                } else {
                    $('#' + paymentStatusId).removeClass().addClass('fa fa fa-exclamation-triangle text-warning');
                }
                if (!checkAwbPayment(index)) {
                    $('#' + paymentStatusId).removeClass().addClass('fa fa fa-exclamation-triangle text-warning');
                }
            } else {
                if (amount == total) {
                    $('#' + paymentStatusId).removeClass();
                } else {
                    $('#' + paymentStatusId).removeClass().addClass('fa fa fa-exclamation-triangle text-warning');
                }
            }
        });
        $(this).trigger('blur');

    });

    $("input[data-awb_payment_tmp]").each(function () {
        $(this).number(true, 2, '.', '');

        $(this).blur(function () {
            var invoiceIndex = $(this).data("group_awb_payment");
            console.log("invoiceIndex:" + invoiceIndex);
            calculateInvoiceUnappliedAmount(invoiceIndex);
        });

    });

    $("input[data-invoice_payment_amount]").trigger("blur");
    //end init
    function updateInvoicePaymentView() {
        calculateUnappliedAmount();
    }
    function calculateUnappliedAmount() {
        var totalApplied = 0;
        $("input[data-invoice_payment_amount]").each(function () {
            totalApplied = totalApplied + Number($(this).val());
        });
        //$("#overpayment_appliedAmount").val($("#receive_payment_amount").val() - testdata);
        totalApplied = Number(totalApplied);
        totalApplied = totalApplied.toFixed(2);
        $("#overpayment_appliedAmount").val(totalApplied);
        $("#overpayment_appliedAmount_label").html(totalApplied);
        var receivePayment = Number($("#overpayment_amountToApply").val());
        var unappliedAmount = Number(receivePayment - totalApplied);
        unappliedAmount = unappliedAmount.toFixed(2);
        $("#overpayment_unappliedAmount").val(unappliedAmount);
        $("#overpayment_unappliedAmount_label").html(unappliedAmount);

    }

    function calculateInvoiceUnappliedAmount(invoiceIndex) {
        var totalAwbApplied = Number(caculateAwbPaymentTmp(invoiceIndex));
        var totalInvoicePaymentReceive = Number($("#invoiceList_" + invoiceIndex + "_payment").val());
        var unappliedAmount = Number(totalInvoicePaymentReceive - totalAwbApplied);
        var appliedAmount = Number(totalAwbApplied);
        $("#invoiceList_" + invoiceIndex + "_unappliedAmount").html(unappliedAmount.toFixed(2));
        $("#invoiceList_" + invoiceIndex + "_appliedAmount").html(appliedAmount.toFixed(2));
        console.log("calculateInvoiceUnappliedAmount | totalAwbApplied:" + totalAwbApplied + "| totalInvoicePaymentReceive: " + totalInvoicePaymentReceive + "| unappliedAmount:" + unappliedAmount + "| appliedAmount:" + appliedAmount);
    }

    function caculateAwbPaymentTmp(index) {
        var total = Number(0);
        $("input[data-group_awb_payment='" + index + "']").each(function () {
            console.log("before caculateAwbPayment tmp : " + index + ":" + total + ":" + Number($(this).val()));
            total = Number(total) + Number($(this).val());
            total = total.toFixed(2);
            console.log("after caculateAwbPayment tmp : " + index + ":" + total + ":" + Number($(this).val()));
        });
        return total;

    }

    function caculateAwbPayment(index) {
        var total = Number(0);
        $("*[data-awb_invoice_index='" + index + "']").each(function () {
            console.log("before caculateAwbPayment : " + index + ":" + total + ":" + Number($(this).val()));
            total = Number(total) + Number($(this).val());
            total = total.toFixed(2);
            console.log("after caculateAwbPayment : " + index + ":" + total + ":" + Number($(this).val()));
        });
        return total;

    }

    function checkAwbPayment(index) {
        var result = true;
        $("*[data-awb_invoice_index='" + index + "']").each(function () {
            var shipmentIndex = $(this).data("awb_shipment_index");
            var amountDue = Number($("#invoiceList_" + index + "_shipmentInvoices_" + shipmentIndex + "_amountDue").val());
            var awbPayment = Number($(this).val());
            console.log("checkAwbPayment: " + amountDue + ":" + awbPayment);
            if (awbPayment > amountDue || awbPayment < 0)
                result = false;
        });
        return result;
    }

    function autoApplyInvoice() {
        var receivePayment = Number($("#overpayment_amountToApply").val());
        console.log("receivePayment : " + receivePayment);
        $("input[data-invoice_payment_amount]").each(function () {
            $(this).val("");
            var invoiceIndex = $(this).data("invoice_index");
            var amountDue = Number($("#invoiceList_" + invoiceIndex + "_remainningBalance").val());
            if (receivePayment > 0 && amountDue > 0) {
                var invoicePayment = 0;
                if (receivePayment > amountDue) {
                    invoicePayment = amountDue;
                } else {
                    invoicePayment = receivePayment;
                }
                receivePayment = receivePayment - invoicePayment;
                if (invoicePayment >= 0) {
                    invoicePayment = Number(invoicePayment);
                    invoicePayment = invoicePayment.toFixed(2);
                    $(this).val(invoicePayment);
                    console.log("apply invoice payment: " + invoiceIndex + ":" + invoicePayment + ":" + amountDue + ":" + receivePayment);
                }
            }
            autoApplyAirbill(invoiceIndex);
            $(this).trigger("blur");
        });
    }

    function resetValue(id) {
        $("#" + id).val("");
        $("#" + id).trigger("blur");
    }
    function resetPayment() {
        $("input[data-invoice_index]").each(function () {
            var invoiceIndex = $(this).data("invoice_index");
            resetInvoicePayment(invoiceIndex);
        });

    }
    function resetInvoicePayment(invoiceIndex) {
        $("#invoiceList_" + invoiceIndex + "_payment").val("");
        autoApplyAirbill(invoiceIndex);
        $("#invoiceList_" + invoiceIndex + "_payment").trigger("blur");
    }

    function submitApplyInvReq(submitType) {
        $("#overpayment_submitType").val(submitType);
        var orgdata = $('form#frmApplyToInvoices').serialize();
        loadingDialog.dialog("open");
        $.post("inv_ovrpmt_apply_to_inv.ix?reqType=json", orgdata, function (res) {
            if (res.errorCode == "SUCCESS") {
                loadingDialog.dialog("close");
                if (submitType == 2) {
                    messageDialog.html("<xms:localization text="Save successful." />");
                    messageDialog.dialog("option", "buttons", {
                        "<xms:localization text="Ok" />": function () {
                            $(this).dialog("close");
                        }
                    });
                    messageDialog.dialog("option", "close", function () {
                        $("#inv-overpayment-dialog").html("");
                        $("#inv-overpayment-dialog").dialog("close");
                    });
                    messageDialog.dialog("open");
                } else {
                    $("#inv-overpayment-dialog").html(res.content);
                }
            } else {
                loadingDialog.dialog("close");
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }
    function autofillInvoicePayment(invoiceIndex) {
        var remainning = Number($("#overpayment_unappliedAmount_label").html());
        var currInvoicePayment = Number($("#invoiceList_" + invoiceIndex + "_payment").val());
        var invoiceDue = Number($("#invoiceList_" + invoiceIndex + "_remainningBalance").val());
        if (remainning + currInvoicePayment > invoiceDue) {
            $("#invoiceList_" + invoiceIndex + "_payment").val(invoiceDue);
            //fullInvoiceAirbill(invoiceIndex);
        } else {
            $("#invoiceList_" + invoiceIndex + "_payment").val(remainning + currInvoicePayment);
        }
        $("#invoiceList_" + invoiceIndex + "_payment").trigger("blur");
    }

    function fullInvoiceAirbillPayment(invoiceIndex) {
        /*
         var currInvoicePayment = Number($("#invoiceList_"+invoiceIndex+"_payment").val());
         var invoiceDue = Number($("#invoiceList_" + invoiceIndex + "_remainningBalance").val());
         if (currInvoicePayment==invoiceDue){
         autoApplyAirbill(invoiceIndex);
         }
         */
    }

    function autofillInvoiceAirbillPayment(invoiceIndex, airbillIndex) {
        var remainning = Number($("#invoiceList_" + invoiceIndex + "_unappliedAmount").html());
        var currInvoiceAirbillPayment = Number($("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_payment_tmp").val());
        var invoiceAirbillDue = Number($("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_amountDue").val());
        if (remainning + currInvoiceAirbillPayment > invoiceAirbillDue) {
            $("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_payment_tmp").val(invoiceAirbillDue);
        } else {
            $("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_payment_tmp").val(remainning + currInvoiceAirbillPayment);
        }
        $("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_payment_tmp").trigger("blur");
    }
</script>
<!-- End Form Modal -->