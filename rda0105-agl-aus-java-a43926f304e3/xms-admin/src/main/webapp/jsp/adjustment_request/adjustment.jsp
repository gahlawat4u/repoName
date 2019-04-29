<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Adjustments"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Adjustments"/></li>
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
                                    <xms:localization text="Adjustments"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row" id="adjustment_content">
                                            <!-- BEGIN ADJUSTMENT CONTENT -->
                                            <div class="col-lg-9">
                                                <div class="col-lg-12">
                                                    <table class="table" style="font-size: 11px; width: 50%">
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Adjustments:"/></td>
                                                            <td class="td2"><s:i18n_select
                                                                    name="adjustmentRequest.adjustType"
                                                                    list="adjustmentTypes" listKey="id" listValue="name"
                                                                    cssClass="form-control s52"
                                                                    onchange="changeType()"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Sub Adjustments:"/></td>
                                                            <td class="td2" colspan="2"><s:i18n_select
                                                                    name="adjustmentRequest.adjustmentType"
                                                                    list="subAdjustmentTypes"
                                                                    cssClass="form-control s52"
                                                                    onchange="changeSubType()"/></td>
                                                        </tr>
                                                    </table>
                                                    <hr>
                                                </div>
                                                <div class="col-lg-6">
                                                    <p class="s38">
                                                        <xms:localization text="New Adjustment Request"/>
                                                    </p>
                                                    <table class="table" style="font-size: 11px;">
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Airbill Number:"/></td>
                                                            <td class="td2 s51" colspan="2"><s:hidden
                                                                    name="adjustmentRequest.airbillNumber"/> <s:hidden
                                                                    name="adjustmentRequest.shipmentId"/> <s:hidden
                                                                    name="adjustmentRequest.invoiceCode"/> <s:property
                                                                    value="adjustmentRequest.airbillNumber"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Adjustment Type:"/></td>
                                                            <td class="td2 s51" colspan="2"><s:property
                                                                    value="adjustmentRequest.adjustmentType"/></td>
                                                        </tr>
                                                        <s:if test="userLevel<3">
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Carrier Amount:"/><span
                                                                        class="s30"> *</span></td>
                                                                <td class="td2" colspan="2"><input
                                                                        <s:if test="%{'Full Refund'.equalsIgnoreCase(adjustmentRequest.adjustmentType)}">disabled="disabled"</s:if>
                                                                        numeric-text="true"
                                                                        name="adjustmentRequest.carrierAmount"
                                                                        class="form-control alloptions" type="text"
                                                                        maxlength="25"
                                                                        value="<s:property value="adjustmentInfo.carrierAmount" />"
                                                                        onkeyup="validCarrierAmount()"/> <s:hidden
                                                                        name="adjustmentInfo.carrierAmount"/> <span
                                                                        class="s30" id="msg_carrier_amount"></span></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="GST Carrier Amount:"/></td>
                                                                <td class="td2 s51" colspan="2"><input
                                                                        class="disable-input" disabled="disabled"
                                                                        name="adjustmentRequest.gstCarrierAmount"
                                                                        class="form-control alloptions" type="text"
                                                                        maxlength="25"
                                                                        value="<s:property value="adjustmentInfo.gstCarrierAmount" />"/>
                                                                    <s:hidden
                                                                            name="adjustmentInfo.gstCarrierAmount"/>
                                                                </td>
                                                            </tr>
                                                        </s:if>
                                                        <tr>
                                                            <s:if test="userLevel>=3">
                                                                <input
                                                                        <s:if test="%{'Full Refund'.equalsIgnoreCase(adjustmentRequest.adjustmentType)}">disabled="disabled"</s:if>
                                                                        numeric-text="true"
                                                                        name="adjustmentRequest.carrierAmount"
                                                                        class="form-control alloptions" type="hidden"
                                                                        maxlength="25"
                                                                        value="<s:property value="adjustmentInfo.carrierAmount" />"
                                                                        onkeyup="validCarrierAmount()"/>
                                                                <s:hidden name="adjustmentInfo.carrierAmount"/>
                                                                <span class="s30" id="msg_carrier_amount"></span>
                                                                <input class="disable-input" disabled="disabled"
                                                                       name="adjustmentRequest.gstCarrierAmount"
                                                                       class="form-control alloptions" type="hidden"
                                                                       maxlength="25"
                                                                       value="<s:property value="adjustmentInfo.gstCarrierAmount" />"/>
                                                                <s:hidden name="adjustmentInfo.gstCarrierAmount"/>
                                                            </s:if>
                                                            <td class="td1"><xms:localization text="Franchise Amount:"/><span
                                                                    class="s30"> *</span></td>
                                                            <td class="td2" colspan="2"><input
                                                                    <s:if test="%{'Full Refund'.equalsIgnoreCase(adjustmentRequest.adjustmentType)}">disabled="disabled"</s:if>
                                                                    numeric-text="true"
                                                                    name="adjustmentRequest.franchiseAmount"
                                                                    class="form-control alloptions" type="text"
                                                                    maxlength="25"
                                                                    value="<s:property value="adjustmentInfo.franchiseAmount" />"
                                                                    onkeyup="validFranchiseAmount()"/> <s:hidden
                                                                    name="adjustmentInfo.franchiseAmount"/><span
                                                                    class="s30" id="msg_franchise_amount"></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="GST Franchise Amount:"/></td>
                                                            <td class="td2 s51" colspan="2"><input class="disable-input"
                                                                                                   disabled="disabled"
                                                                                                   name="adjustmentRequest.gstFranchiseAmount"
                                                                                                   class="form-control alloptions"
                                                                                                   type="text"
                                                                                                   maxlength="25"
                                                                                                   value="<s:property value="adjustmentInfo.gstFranchiseAmount" />"/>
                                                                <s:hidden
                                                                        name="adjustmentInfo.gstFranchiseAmount"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Customer Amount:"/><span class="s30"> *</span>
                                                            </td>
                                                            <td class="td2" colspan="2"><input
                                                                    <s:if test="%{'Full Refund'.equalsIgnoreCase(adjustmentRequest.adjustmentType)}">disabled="disabled"</s:if>
                                                                    numeric-text="true"
                                                                    name="adjustmentRequest.customerAmount"
                                                                    class="form-control alloptions" type="text"
                                                                    maxlength="25"
                                                                    value="<s:property value="adjustmentInfo.customerAmount" />"
                                                                    onkeyup="validCustomerAmount()"/> <s:hidden
                                                                    name="adjustmentInfo.customerAmount"/><span
                                                                    class="s30" id="msg_customer_amount"></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="GST Customer Amount:"/></td>
                                                            <td class="td2 s51" colspan="2"><input class="disable-input"
                                                                                                   disabled="disabled"
                                                                                                   name="adjustmentRequest.gstCustomerAmount"
                                                                                                   class="form-control alloptions"
                                                                                                   type="text"
                                                                                                   maxlength="25"
                                                                                                   value="<s:property value="adjustmentInfo.gstCustomerAmount" />"/>
                                                                <s:hidden
                                                                        name="adjustmentInfo.gstCustomerAmount"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="col-lg-6">
                                                    <p class="s38">&nbsp;</p>
                                                    <table class="table" style="font-size: 11px;">
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Notes:"/><span
                                                                    class="s30"> *</span></td>
                                                            <s:set var="paramMap"
                                                                   value="#{'{currencySymbol}':'currencySymbol' }"/>
                                                            <td class="td2" colspan="2"><textarea
                                                                    name="adjustmentRequest.note"
                                                                    class="form-control alloptions"
                                                                    style="height: 100px;"
                                                                    placeholder='<xms:localization text="Please enter a valid description in the notes fields test - upon carrier 1- 2{currencySymbol}" paramMap="paramMap" escapeXml = "true"/>'></textarea>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Credit Customer Now?:"/></td>
                                                            <td class="td2" colspan="2"><s:i18n_select
                                                                    name="adjustmentRequest.creditType"
                                                                    list="creditTypes" listKey="id" listValue="name"
                                                                    cssClass="form-control s52"
                                                                    onchange="changeCreditType()"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Automatically apply GST?:"/></td>
                                                            <td class="td2" colspan="2"><s:i18n_select
                                                                    name="adjustmentRequest.applyGstType"
                                                                    list="gstTypes" listKey="id" listValue="name"
                                                                    cssClass="form-control s52"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="" colspan="2">
                                                                <button id="btnAddRequest" class="btn s37" type="button"
                                                                        onclick="addAdjustmentRequest()">
                                                                    <xms:localization text="Add"/>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <s:hidden name="adjustmentTotal.carrierAmount"/>
                                                <s:hidden name="adjustmentTotal.franchiseAmount"/>
                                                <s:hidden name="adjustmentTotal.customerAmount"/>
                                            </div>
                                            <div class="col-lg-3">
                                                <p>
                                                    <b><xms:localization text="Note:</b> <br /> <br />
													1. At the Credit Customer Now drop down list, if user selects 'Upon Carrier Approval', the credit code will be generated only after being approved by carrier. If user selects 'Credit Now', credit code will be generated immediately.
													<br /> <br />
													2. When a Full refund request is only approved partially by carrier, please follow the below steps:
													<br /> <br />
													a) Find the requested AWB using Invoicing->Manage Adjustments menu
													<br /> <br />
													b) Key in the partial approved amount into Customer Amount field as per normal and save.
													<br /> <br />
													c) Login to XMS using the franchisee's account and key in the balance as a Goodwill credit using Search Airbill->Adjustments option."/>
                                                </p>
                                            </div>
                                            <!-- END ADJUSTMENT CONTENT -->
                                        </div>
                                        <!-- BEGIN ADJUSTMENT REQUEST LIST -->
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-bordered mg0" id="datatable1">
                                                    <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th><xms:localization text="Adjustment Type"/></th>
                                                        <s:if test="userLevel<3">
                                                            <th><xms:localization text="Carrier Amount"/></th>
                                                        </s:if>
                                                        <th><xms:localization text="Franchise Amount"/></th>
                                                        <th><xms:localization text="Customer Amount"/></th>
                                                        <th><xms:localization text="Credit Customer Now"/>?</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="adjustment_request_list">
                                                    <tr id="adjustment_request_list_header">
                                                        <td colspan="6"><xms:localization
                                                                text="No data available..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="form-actions  pal pdt10">
                                            <div class="row">
                                                <div class="col-lg-12 text-right">
                                                    <button id="btn_send_adj_req" class="btn s37" type="button"
                                                            onclick="sendAdjustmentRequest()">
                                                        <xms:localization text="Request Adjustment"/>
                                                    </button>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Cancel"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- END ADJUSTMENT REQUEST LIST -->
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
<script>
    // Prevents the invalid number will be entered into the numeric text
    $("input[numeric-text]").each(function () {
        $(this).number(true, 2, '.', '');
    });

    // Determine the current adjustment type can be added or not?
    recalculateAmount();

    function changeCreditType() {
        var isCustomerNow = $("#adjustmentRequest_creditType option:selected").val() == 1;
        if (isCustomerNow) {
            $("input[name='adjustmentRequest.carrierAmount']").val("0");
            $("input[name='adjustmentRequest.gstCarrierAmount']").val("0");
            $("input[name='adjustmentRequest.carrierAmount']").attr("readonly", true);
            $("input[name='adjustmentRequest.franchiseAmount']").val("0");
            $("input[name='adjustmentRequest.gstFranchiseAmount']").val("0");
            $("input[name='adjustmentRequest.franchiseAmount']").attr("readonly", true);
        } else {
            $("input[name='adjustmentRequest.carrierAmount']").val($("#adjustmentInfo_carrierAmount").val());
            $("input[name='adjustmentRequest.gstCarrierAmount']").val($("#adjustmentInfo_gstCarrierAmount").val());
            $("input[name='adjustmentRequest.carrierAmount']").removeAttr("readonly");
            $("input[name='adjustmentRequest.franchiseAmount']").val($("#adjustmentInfo_franchiseAmount").val());
            $("input[name='adjustmentRequest.gstFranchiseAmount']").val($("#adjustmentInfo_gstFranchiseAmount").val());
            $("input[name='adjustmentRequest.franchiseAmount']").removeAttr("readonly");
        }
    }

    function changeType() {
        var type = $("select#adjustmentRequest_adjustType option:selected").val();
        var awb = $("#adjustmentRequest_airbillNumber").val();
        var shipmentId = $("#adjustmentRequest_shipmentId").val();
        var invoiceCode = $("#adjustmentRequest_invoiceCode").val();
        var data = "adjustmentRequest.airbillNumber=" + awb;
        data = data + "&adjustmentRequest.adjustType=" + type;
        data = data + "&adjustmentRequest.shipmentId=" + shipmentId;
        data = data + "&adjustmentRequest.invoiceCode=" + invoiceCode;
        $.post("get_adjustment_request_by_type.ix?reqType=json", data, function (res) {
            // loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#adjustment_content").html(res.content);
                recalculateAmount();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            // loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }

    function changeSubType() {
        var type = $("select#adjustmentRequest_adjustType option:selected").val();
        var subType = encodeURIComponent($("select#adjustmentRequest_adjustmentType option:selected").val());
        var awb = $("#adjustmentRequest_airbillNumber").val();
        var shipmentId = $("#adjustmentRequest_shipmentId").val();
        var invoiceCode = $("#adjustmentRequest_invoiceCode").val();
        var data = "adjustmentRequest.airbillNumber=" + awb;
        data = data + "&adjustmentRequest.adjustType=" + type;
        data = data + "&adjustmentRequest.adjustmentType=" + subType;
        data = data + "&adjustmentRequest.shipmentId=" + shipmentId;
        data = data + "&adjustmentRequest.invoiceCode=" + invoiceCode;
        $.post("get_adjustment_request_by_sub_type.ix?reqType=json", data, function (res) {
            // loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#adjustment_content").html(res.content);
                recalculateAmount();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            // loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }

    function addAdjustmentRequest() {
        // Check existing
        if (checkExistAdjustmentType()) {
            alertDialog.html("<xms:localization text="This Adjustment Type is already added." />");
            alertDialog.dialog("open");
            return;
        }

        // Valid input
        if (!validInput()) {
            return;
        }

        var userLevel = Number('<s:property value="userLevel" />');

        // Find and remove header if it's existed
        $("#adjustment_request_list tr#adjustment_request_list_header").remove();

        // Define row's content
        var shipmentId = $("#adjustmentRequest_shipmentId").val();
        var airbillNumber = $("#adjustmentRequest_airbillNumber").val();
        var adjustmentType = $("#adjustmentRequest_adjustmentType option:selected").val();
        var carrierAmount = $("input[name='adjustmentRequest.carrierAmount']").val();
        var gstCarrierAmount = $("input[name='adjustmentRequest.gstCarrierAmount']").val();
        var franchiseAmount = $("input[name='adjustmentRequest.franchiseAmount']").val();
        var gstFranchiseAmount = $("input[name='adjustmentRequest.gstFranchiseAmount']").val();
        var customerAmount = $("input[name='adjustmentRequest.customerAmount']").val();
        var gstCustomerAmount = $("input[name='adjustmentRequest.gstCustomerAmount']").val();
        var creditType = $("#adjustmentRequest_creditType option:selected").val();
        var creditTypeName = $("#adjustmentRequest_creditType option:selected").text();
        var applyGstType = $("#adjustmentRequest_applyGstType option:selected").val();
        var note = $("textarea[name='adjustmentRequest.note']").val();

        var rowContent = "";
        rowContent += "<tr";
        rowContent += " shipment-id='" + shipmentId + "'";
        rowContent += " airbill-number='" + airbillNumber + "'";
        rowContent += " adjustment-type='" + adjustmentType + "'";
        rowContent += " carrier-amount='" + carrierAmount + "'";
        rowContent += " gst-carrier-amount='" + gstCarrierAmount + "'";
        rowContent += " franchise-amount='" + franchiseAmount + "'";
        rowContent += " gst-franchise-amount='" + gstFranchiseAmount + "'";
        rowContent += " customer-amount='" + customerAmount + "'";
        rowContent += " gst-customer-amount='" + gstCustomerAmount + "'";
        rowContent += " credit-type='" + creditType + "'";
        rowContent += " apply-gst-type='" + applyGstType + "'";
        rowContent += " request-carrier='0'";
        rowContent += " note='" + note + "'";
        rowContent += ">";
        rowContent += "<td align='center'><a onclick='removeRow($(this))'><i class='fa fa-trash-o'></i></a></td>";
        rowContent += "<td>" + adjustmentType + "</td>";
        if (userLevel < 3) {
            rowContent += "<td>" + carrierAmount + "</td>";
        }
        rowContent += "<td>" + franchiseAmount + "</td>";
        rowContent += "<td>" + customerAmount + "</td>";
        rowContent += "<td>" + creditTypeName + "</td>";
        rowContent += "</tr>";

        // Add new row
        $("#adjustment_request_list").append(rowContent);
    }

    function removeRow(row) {
        row.closest("tr").remove();
        // If row count is zero so need row to show no data available
        if ($("#adjustment_request_list tr").length == 0) {
            $("#adjustment_request_list").append("<tr id='adjustment_request_list_header'><td colspan='7'><xms:localization text="No data available..." /></td></tr>");
        }
        recalculateAmount();
    }

    function buildJSONRequest() {
        var jsonArray = [];
        $("#adjustment_request_list tr").each(function () {
            var shipmentId = $(this).attr("shipment-id");
            var airbillNumber = $(this).attr("airbill-number");
            var adjustmentType = $(this).attr("adjustment-type");
            var carrierAmount = $(this).attr("carrier-amount");
            var gstCarrierAmount = $(this).attr("gst-carrier-amount");
            var franchiseAmount = $(this).attr("franchise-amount");
            var gstFranchiseAmount = $(this).attr("gst-franchise-amount");
            var customerAmount = $(this).attr("customer-amount");
            var gstCustomerAmount = $(this).attr("gst-customer-amount");
            var creditType = $(this).attr("credit-type");
            var applyGstType = $(this).attr("apply-gst-type");
            var requestCarrier = $(this).attr("request-carrier");
            var note = $(this).attr("note");

            var item = {}
            item["shipmentId"] = shipmentId;
            item["airbillNumber"] = airbillNumber;
            item["adjustmentType"] = adjustmentType;
            item["carrierAmount"] = carrierAmount;
            item["gstCarrierAmount"] = gstCarrierAmount;
            item["franchiseAmount"] = franchiseAmount;
            item["gstFranchiseAmount"] = gstFranchiseAmount;
            item["customerAmount"] = customerAmount;
            item["gstCustomerAmount"] = gstCustomerAmount;
            item["creditType"] = creditType;
            item["applyGstType"] = applyGstType;
            item["requestCarrier"] = requestCarrier;
            item["note"] = encodeURIComponent(note);

            jsonArray.push(item);
        });

        return JSON.stringify(jsonArray);
    }

    function checkExistAdjustmentType() {
        var currentType = $("#adjustmentRequest_adjustmentType option:selected").val();
        var existed = false;
        $("#adjustment_request_list tr td:nth-child(2)").each(function () {
            var adjustmentType = $(this).html();
            if (currentType == adjustmentType) {
                existed = true;
            }
        });
        return existed;
    }

    function recalculateAmount() {
        var carrier_amount = Number($("#adjustmentInfo_carrierAmount").val());
        var carrier_gst = Number($("#adjustmentInfo_gstCarrierAmount").val());

        var fran_amount = Number($("#adjustmentInfo_franchiseAmount").val());
        var fran_gst = Number($("#adjustmentInfo_gstFranchiseAmount").val());

        var customer_amount = Number($("#adjustmentInfo_customerAmount").val());
        var customer_gst = Number($("#adjustmentInfo_gstCustomerAmount").val());

        // Determine valid carrier amount
        var valid_carrier_amount;
        var total_adjustable_carrier_amount = Number($("#adjustmentTotal_carrierAmount").val());
        total_adjustable_carrier_amount = total_adjustable_carrier_amount - getTotalCarrierAdjust();

        if (total_adjustable_carrier_amount > carrier_amount) {
            valid_carrier_amount = carrier_amount;
        } else {
            valid_carrier_amount = total_adjustable_carrier_amount;
        }
        valid_carrier_amount = valid_carrier_amount.toFixed(2);

        // Determine valid franchise amount
        var valid_fran_amount;
        var total_adjustable_fran_amount = Number($("#adjustmentTotal_franchiseAmount").val());
        total_adjustable_fran_amount = total_adjustable_fran_amount - getTotalFranchiseAdjust();

        if (total_adjustable_fran_amount > fran_amount) {
            valid_fran_amount = fran_amount;
        } else {
            valid_fran_amount = total_adjustable_fran_amount;
        }
        valid_fran_amount = valid_fran_amount.toFixed(2);

        // Determine valid customer amount
        var valid_cust_amount;
        var total_adjustable_cust_amount = Number($("#adjustmentTotal_customerAmount").val());
        total_adjustable_cust_amount = total_adjustable_cust_amount - getTotalCustomerAdjust();

        if (total_adjustable_cust_amount > customer_amount) {
            valid_cust_amount = customer_amount;
        } else {
            valid_cust_amount = total_adjustable_cust_amount;
        }
        valid_cust_amount = valid_cust_amount.toFixed(2);

        // Disable add button if there isn't adjustable amount
        if (valid_carrier_amount <= 0 && valid_fran_amount <= 0 && valid_cust_amount <= 0) {
            $("#btnAddRequest").attr("disabled", true);
            messageDialog.html("<xms:localization text="You cannot continue adjust this airbill because it has been adjusting all possible values." />");
            messageDialog.dialog("open");
        } else {
            $("#btnAddRequest").removeAttr("disabled");
        }
    }

    function validInput() {
        var carrierAmount = $("input[name='adjustmentRequest.carrierAmount']").val();
        var franchiseAmount = $("input[name='adjustmentRequest.franchiseAmount']").val();
        var customerAmount = $("input[name='adjustmentRequest.customerAmount']").val();

        if (carrierAmount == "") {
            alertDialog.html("<xms:localization text="Please enter Carrier Amount" />");
            alertDialog.dialog("open");
            return false;
        }
        if (franchiseAmount == "") {
            alertDialog.html("<xms:localization text="Please enter Franchise Amount" />");
            alertDialog.dialog("open");
            return false;
        }
        if (customerAmount == "") {
            alertDialog.html("<xms:localization text="Please enter Customer Amount" />");
            alertDialog.dialog("open");
            return false;
        }

        carrierAmount = Number(carrierAmount);
        franchiseAmount = Number(franchiseAmount);
        customerAmount = Number(customerAmount);

        if (carrierAmount < 0) {
            alertDialog.html('<xms:localization text="The carrier amount cannot be less than zero." />');
            alertDialog.dialog("open");
            return false;
        } else if (franchiseAmount < 0) {
            alertDialog.html('<xms:localization text="The franchise amount cannot be less than zero." />');
            alertDialog.dialog("open");
            return false;
        } else if (customerAmount < 0) {
            alertDialog.html('<xms:localization text="The customer amount cannot be less than zero." />');
            alertDialog.dialog("open");
            return false;
        }

        // Determine valid franchise amount
        var fran_amount = Number($("#adjustmentInfo_franchiseAmount").val());
        var fran_gst = Number($("#adjustmentInfo_gstFranchiseAmount").val());
        var valid_fran_amount;
        var total_adjustable_fran_amount = Number($("#adjustmentTotal_franchiseAmount").val());
        total_adjustable_fran_amount = total_adjustable_fran_amount - getTotalFranchiseAdjust();

        if (total_adjustable_fran_amount > fran_amount) {
            valid_fran_amount = fran_amount;
        } else {
            valid_fran_amount = total_adjustable_fran_amount;
        }
        valid_fran_amount = valid_fran_amount.toFixed(2);

        if (valid_fran_amount < 0) {
            alertDialog.html("<xms:localization text="The franchise amount of this airbill cannot be adjusted anymore." />");
            alertDialog.dialog("open");
            return false;
        }

        if (franchiseAmount > valid_fran_amount) {
            alertDialog.html("<xms:localization text="Franchise Amount must not be greater than " />" + valid_fran_amount);
            alertDialog.dialog("open");
            return false;
        }

        // Determine valid customer amount
        var customer_amount = Number($("#adjustmentInfo_customerAmount").val());
        var customer_gst = Number($("#adjustmentInfo_gstCustomerAmount").val());
        var valid_cust_amount;
        var total_adjustable_cust_amount = Number($("#adjustmentTotal_customerAmount").val());
        total_adjustable_cust_amount = total_adjustable_cust_amount - getTotalCustomerAdjust();

        if (total_adjustable_cust_amount > customer_amount) {
            valid_cust_amount = customer_amount;
        } else {
            valid_cust_amount = total_adjustable_cust_amount;
        }
        valid_cust_amount = valid_cust_amount.toFixed(2);

        if (valid_cust_amount < 0) {
            alertDialog.html("<xms:localization text="The customer amount of this airbill cannot be adjusted anymore." />");
            alertDialog.dialog("open");
            return false;
        }

        if (customerAmount > valid_cust_amount) {
            alertDialog.html("<xms:localization text="Customer Amount must not be greater than " />" + valid_cust_amount);
            alertDialog.dialog("open");
            return false;
        }

        // Validate note
        var note = $("textarea[name='adjustmentRequest.note']").val();
        if (note == "") {
            alertDialog.html("<xms:localization text="Please enter Note" />");
            alertDialog.dialog("open");
            return false;
        }

        return true;
    }

    function getTotalCarrierAdjust() {
        var total = 0;
        $("#adjustment_request_list tr[carrier-amount]").each(function () {
            var carrierAmount = Number($(this).attr("carrier-amount"));
            total += carrierAmount;
        });
        return total.toFixed(2);
    }

    function getTotalFranchiseAdjust() {
        var total = 0;
        $("#adjustment_request_list tr[franchise-amount]").each(function () {
            var franchiseAmount = Number($(this).attr("franchise-amount"));
            total += franchiseAmount;
        });
        return total.toFixed(2);
    }

    function getTotalCustomerAdjust() {
        var total = 0;
        $("#adjustment_request_list tr[customer-amount]").each(function () {
            var customerAmount = Number($(this).attr("customer-amount"));
            total += customerAmount;
        });
        return total.toFixed(2);
    }

    function validCarrierAmount() {
        var carrier_amount = Number($("#adjustmentInfo_carrierAmount").val());
        var carrier_gst = Number($("#adjustmentInfo_gstCarrierAmount").val());
        var carrierAmount = Number($("input[name='adjustmentRequest.carrierAmount']").val());

        if (carrierAmount > carrier_amount) {
            $("#msg_carrier_amount").html("Carrier Amount must not be greater than " + carrier_amount);
            $("input[name='adjustmentRequest.carrierAmount']").val(carrier_amount);
            carrierAmount = carrier_amount;
        } else {
            $("#msg_carrier_amount").html("");
        }
        var gstCarrierAmount = carrierAmount * carrier_gst / carrier_amount;
        gstCarrierAmount = gstCarrierAmount.toFixed(2);
        $("input[name='adjustmentRequest.gstCarrierAmount']").val(gstCarrierAmount);

        var fran_amount = Number($("#adjustmentInfo_franchiseAmount").val());
        var fran_gst = Number($("#adjustmentInfo_gstFranchiseAmount").val());
        var franchiseAmount = fran_amount * carrierAmount / carrier_amount;
        franchiseAmount = franchiseAmount.toFixed(2);
        $("input[name='adjustmentRequest.franchiseAmount']").val(franchiseAmount);
        var gstFranchiseAmount = franchiseAmount * fran_gst / fran_amount;
        gstFranchiseAmount = gstFranchiseAmount.toFixed(2);
        $("input[name='adjustmentRequest.gstFranchiseAmount']").val(gstFranchiseAmount);
    }

    function validFranchiseAmount() {
        var fran_amount = Number($("#adjustmentInfo_franchiseAmount").val());
        var fran_gst = Number($("#adjustmentInfo_gstFranchiseAmount").val());
        var franchiseAmount = Number($("input[name='adjustmentRequest.franchiseAmount']").val());

        if (franchiseAmount > fran_amount) {
            $("#msg_franchise_amount").html("Franchise Amount must not be greater than " + fran_amount);
            $("input[name='adjustmentRequest.franchiseAmount']").val(fran_amount);
            franchiseAmount = fran_amount;
        } else {
            $("#msg_franchise_amount").html("");
        }
        var gstFranchiseAmount = franchiseAmount * fran_gst / fran_amount;
        gstFranchiseAmount = gstFranchiseAmount.toFixed(2);
        $("input[name='adjustmentRequest.gstFranchiseAmount']").val(gstFranchiseAmount);

//        var carrier_amount = Number($("#adjustmentInfo_carrierAmount").val());
//        var carrier_gst = Number($("#adjustmentInfo_gstCarrierAmount").val());
//        var carrierAmount = carrier_amount * franchiseAmount / fran_amount;
//        carrierAmount = carrierAmount.toFixed(2);
//        $("input[name='adjustmentRequest.carrierAmount']").val(carrierAmount);
//        var gstCarrierAmount = carrierAmount * carrier_gst / carrier_amount;
//        gstCarrierAmount = gstCarrierAmount.toFixed(2);
//        $("input[name='adjustmentRequest.gstCarrierAmount']").val(gstCarrierAmount);
    }

    function validCustomerAmount() {
        var customer_amount = Number($("#adjustmentInfo_customerAmount").val());
        var customer_gst = Number($("#adjustmentInfo_gstCustomerAmount").val());
        var customerAmount = Number($("input[name='adjustmentRequest.customerAmount']").val());

        if (customerAmount > customer_amount) {
            $("#msg_customer_amount").html("<xms:localization text="Customer Amount must not be greater than " />" + customer_amount);
            $("input[name='adjustmentRequest.customerAmount']").val(customer_amount);
            customerAmount = customer_amount;
        } else {
            $("#msg_customer_amount").html("");
        }
        var gstCustomerAmount = 0;
        if (customer_amount != 0) {
            gstCustomerAmount = customerAmount * customer_gst / customer_amount;
        }
        gstCustomerAmount = gstCustomerAmount.toFixed(2);
        $("input[name='adjustmentRequest.gstCustomerAmount']").val(gstCustomerAmount);
    }

    function sendAdjustmentRequest() {
        // Check the adjustment list request
        if ($("#adjustment_request_list tr#adjustment_request_list_header").length != 0) {
            alertDialog.html("<xms:localization text="Please add one Adjustment" />");
            alertDialog.dialog("open");
        } else {
            // Prepare data to post
            var data = "adjustmentRequest.invoiceCode=" + $("#adjustmentRequest_invoiceCode").val();
            data = data + "&requestList=" + buildJSONRequest();

            // Post to the save requests action
            $("#btn_send_adj_req").attr("disabled", "disabled");
            loadingDialog.dialog("open");
            $.post("adjustment_request_save_adjustment.ix?reqType=json", data, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    changeSubType();
                    $("#adjustment_request_list tr").each(function () {
                        $(this).remove();
                    });
                    $("#adjustment_request_list").append("<tr id='adjustment_request_list_header'><td colspan='7'><xms:localization text="No data available..." /></td></tr>");
                    messageDialog.html("<xms:localization text="Saved airbill adjustment requests successfully." />");
                    messageDialog.dialog("open");
                } else {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                }
                $("#btn_send_adj_req").removeAttr("disabled");
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
                alertDialog.dialog("open");
                $("#btn_send_adj_req").removeAttr("disabled");
            });
        }
    }


</script>