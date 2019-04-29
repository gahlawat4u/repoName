<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!-- BEGIN EDIT ADJUSTMENT CONTENT -->
<div class="form-group">
    <form id="frmEditAdjustment" carrier-amount='<s:property value="adjustment.carrierAmount" />'
          gst-carrier-amount='<s:property value="adjustment.gstCarrierAmount" />'
          fran-amount='<s:property value="adjustment.franchiseAmount" />'
          gst-fran-amount='<s:property value="adjustment.gstFranchiseAmount" />'>
        <div class="row">
            <div class="col-lg-6">
                <s:hidden name="adjustment.adjustmentRequestId"/>
                <s:hidden name="adjustment.status"/>
                <table class="table" style="font-size: 11px;">
                    <tr>
                        <td class="td1"><xms:localization text="Airbill Number:"/></td>
                        <td class="td2 s51" colspan="2"><s:property value="adjustment.airbillNumber"/> <s:hidden
                                name="adjustment.airbillNumber"/></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Adjustment Type:"/></td>
                        <td class="td2 s51" colspan="2"><s:property value="adjustment.adjustmentType"/> <s:hidden
                                name="adjustment.adjustmentType"/></td>
                    </tr>
                    <s:if test="userLevel<3">
                        <tr>
                            <td class="td1"><xms:localization text="Carrier Amount:"/><span class="s30"> *</span></td>
                            <td class="td2" colspan="2"><input
                                    <s:if test="%{'Full Refund'.equalsIgnoreCase(adjustment.adjustmentType) || adjustment.status==4}">readonly="readonly"</s:if>
                                    <s:else>onkeyup="validCarrierAmount()" numeric-text="true"</s:else>
                                    name="adjustment.carrierAmount"
                                    class="form-control alloptions" type="text" maxlength="25"
                                    value="<s:property value="adjustment.carrierAmount" />"/> <s:hidden
                                    name="adjustmentInfo.carrierAmount"/> <span class="s30"
                                                                                id="msg_carrier_amount"></span></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="GST Carrier Amount:"/></td>
                            <td class="td2 s51" colspan="2"><input class="disable-input" readonly="readonly"
                                                                   name="adjustment.gstCarrierAmount"
                                                                   class="form-control alloptions" type="text"
                                                                   maxlength="25"
                                                                   value="<s:property value="adjustment.gstCarrierAmount" />"/>
                                <s:hidden name="adjustmentInfo.gstCarrierAmount"/></td>
                        </tr>
                    </s:if>
                    <tr>
                        <td class="td1"><xms:localization text="Franchise Amount:"/><span class="s30"> *</span></td>
                        <td class="td2" colspan="2"><input
                                <s:if test="%{'Full Refund'.equalsIgnoreCase(adjustment.adjustmentType) || adjustment.status==4}">readonly="readonly"</s:if>
                                <s:else>onkeyup="validFranchiseAmount()" numeric-text="true"</s:else>
                                name="adjustment.franchiseAmount"
                                class="form-control alloptions" type="text" maxlength="25"
                                value="<s:property value="adjustment.franchiseAmount" />"/> <s:hidden
                                name="adjustmentInfo.franchiseAmount"/> <span class="s30"
                                                                              id="msg_franchise_amount"></span></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="GST Franchise Amount:"/></td>
                        <td class="td2 s51" colspan="2"><input class="disable-input" readonly="readonly"
                                                               name="adjustment.gstFranchiseAmount"
                                                               class="form-control alloptions" type="text"
                                                               maxlength="25"
                                                               value="<s:property value="adjustment.gstFranchiseAmount" />"/>
                            <s:hidden
                                    name="adjustmentInfo.gstFranchiseAmount"/></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Customer Amount:"/><span class="s30"> *</span></td>
                        <td class="td2" colspan="2"><input onkeyup="validCustomerAmount()" numeric-text="true"
                                                           name="adjustment.customerAmount"
                                                           class="form-control alloptions" type="text" maxlength="25"
                                                           value="<s:property value="adjustment.customerAmount" />"/>
                            <s:hidden name="adjustmentInfo.customerAmount"/>
                            <span class="s30" id="msg_customer_amount"></span></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="GST Customer Amount:"/></td>
                        <td class="td2 s51" colspan="2"><input class="disable-input" readonly="readonly"
                                                               name="adjustment.gstCustomerAmount"
                                                               class="form-control alloptions" type="text"
                                                               maxlength="25"
                                                               value="<s:property value="adjustment.gstCustomerAmount" />"/>
                            <s:hidden name="adjustmentInfo.gstCustomerAmount"/></td>
                    </tr>
                </table>
            </div>
            <div class="col-lg-6">
                <table class="table" style="font-size: 11px;">
                    <tr>
                        <td class="td1"><xms:localization text="Notes:"/><span class="s30"> *</span> <s:hidden
                                name="adjustment.requestCarrier" value="0"/></td>
                        <s:set var="paramMap" value="#{'{currencySymbol}':'currencySymbol' }"/>
                        <td class="td2" colspan="2"><textarea name="adjustment.note" class="form-control alloptions"
                                                              placeholder='<xms:localization text="Please enter a valid description in the notes fields test - upon carrier 1- 2{currencySymbol}" paramMap="paramMap"/>'><s:property
                                value="adjustment.note"/></textarea></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Credit Customer Now?:"/></td>
                        <td class="td2" colspan="2"><s:if test="adjustment.status==5">
                            <s:i18n_select name="adjustment.creditType" list="creditTypes" listKey="id" listValue="name"
                                           onchange="onCreditTypeChange()"/>
                        </s:if> <s:else>
                            <s:property value="adjustment.creditTypeName"/>
                            <s:hidden name="adjustment.creditType"/>
                        </s:else></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Automatically apply GST?:"/></td>
                        <td class="td2" colspan="2"><s:i18n_select name="adjustment.applyGstType" list="gstTypes"
                                                                   listKey="id" listValue="name"
                                                                   cssClass="form-control s52"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
    <s:hidden name="adjustmentValid.carrierAmount"/>
    <s:hidden name="adjustmentValid.franchiseAmount"/>
    <s:hidden name="adjustmentValid.customerAmount"/>
</div>
<!-- END EDIT ADJUSTMENT CONTENT -->
<script>
    //Prevents the invalid number will be entered into the numeric text
    $("input[numeric-text]").each(function () {
        $(this).number(true, 2, '.', '');
    });

    function onCreditTypeChange() {
        var creditType = $("#adjustment_creditType option:selected").val();
        var isCreditNow = creditType == 1;
        if (isCreditNow) {
            $("input[name='adjustment.carrierAmount']").val(0);
            $("input[name='adjustment.gstCarrierAmount']").val(0);
            $("input[name='adjustment.franchiseAmount']").val(0);
            $("input[name='adjustment.gstFranchiseAmount']").val(0);
            $("input[name='adjustment.carrierAmount']").attr("readonly", true);
            $("input[name='adjustment.franchiseAmount']").attr("readonly", true);
        } else {
            var carrier_amount = $("#frmEditAdjustment").attr("carrier-amount");
            var carrier_gst = $("#frmEditAdjustment").attr("gst-carrier-amount");
            var franchise_amount = $("#frmEditAdjustment").attr("fran-amount");
            var franchise_gst = $("#frmEditAdjustment").attr("gst-fran-amount");
            $("input[name='adjustment.carrierAmount']").val(carrier_amount);
            $("input[name='adjustment.gstCarrierAmount']").val(carrier_gst);
            $("input[name='adjustment.franchiseAmount']").val(franchise_amount);
            $("input[name='adjustment.gstFranchiseAmount']").val(franchise_gst);
            $("input[name='adjustment.carrierAmount']").removeAttr("readonly");
            $("input[name='adjustment.franchiseAmount']").removeAttr("readonly");
        }
    }

    function validInput() {
        var carrierAmount = $("input[name='adjustment.carrierAmount']").val();
        var franchiseAmount = $("input[name='adjustment.franchiseAmount']").val();
        var customerAmount = $("input[name='adjustment.customerAmount']").val();
        var note = $("textarea[name='adjustment.note']").val();
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
        if (note == "") {
            alertDialog.html("<xms:localization text="Please enter Note" />");
            alertDialog.dialog("open");
            return false;
        }
        return true;
    }

    function validCarrierAmount() {
        var carrier_amount = Number($("#adjustmentInfo_carrierAmount").val());
        var carrier_gst = Number($("#adjustmentInfo_gstCarrierAmount").val());
        var valid_carrier_amount = Number($("#adjustmentValid_carrierAmount").val());
        var carrierAmount = Number($("input[name='adjustment.carrierAmount']").val());
        if (carrierAmount > valid_carrier_amount) {
            $("#msg_carrier_amount").html("<xms:localization text="Carrier Amount must not be greater than " />" + valid_carrier_amount);
            $("input[name='adjustment.carrierAmount']").val(valid_carrier_amount);
            carrierAmount = valid_carrier_amount;
        } else {
            $("#msg_carrier_amount").html("");
        }
        var gstCarrierAmount = carrierAmount * carrier_gst / carrier_amount;
        gstCarrierAmount = gstCarrierAmount.toFixed(2);
        $("input[name='adjustment.gstCarrierAmount']").val(gstCarrierAmount);

        var fran_amount = Number($("#adjustmentInfo_franchiseAmount").val());
        var fran_gst = Number($("#adjustmentInfo_gstFranchiseAmount").val());
        var franchiseAmount = fran_amount * carrierAmount / carrier_amount;
        franchiseAmount = franchiseAmount.toFixed(2);
        $("input[name='adjustment.franchiseAmount']").val(franchiseAmount);
        var gstFranchiseAmount = franchiseAmount * fran_gst / fran_amount;
        gstFranchiseAmount = gstFranchiseAmount.toFixed(2);
        $("input[name='adjustment.gstFranchiseAmount']").val(gstFranchiseAmount);
    }

    function validFranchiseAmount() {
        var fran_amount = Number($("#adjustmentInfo_franchiseAmount").val());
        var fran_gst = Number($("#adjustmentInfo_gstFranchiseAmount").val());
        var valid_fran_amount = Number($("#adjustmentValid_franchiseAmount").val());
        var franchiseAmount = Number($("input[name='adjustment.franchiseAmount']").val());
        if (franchiseAmount > valid_fran_amount) {
            $("#msg_franchise_amount").html("<xms:localization text="Franchise Amount must not be greater than " />" + valid_fran_amount);
            $("input[name='adjustment.franchiseAmount']").val(valid_fran_amount);
            franchiseAmount = valid_fran_amount;
        } else {
            $("#msg_franchise_amount").html("");
        }
        var gstFranchiseAmount = franchiseAmount * fran_gst / fran_amount;
        gstFranchiseAmount = gstFranchiseAmount.toFixed(2);
        $("input[name='adjustment.gstFranchiseAmount']").val(gstFranchiseAmount);

//        var carrier_amount = Number($("#adjustmentInfo_carrierAmount").val());
//        var carrier_gst = Number($("#adjustmentInfo_gstCarrierAmount").val());
//        var carrierAmount = carrier_amount * franchiseAmount / fran_amount;
//        carrierAmount = carrierAmount.toFixed(2);
//        $("input[name='adjustment.carrierAmount']").val(carrierAmount);
//        var gstCarrierAmount = carrierAmount * carrier_gst / carrier_amount;
//        gstCarrierAmount = gstCarrierAmount.toFixed(2);
//        $("input[name='adjustment.gstCarrierAmount']").val(gstCarrierAmount);
    }

    function validCustomerAmount() {
        var customer_amount = Number($("#adjustmentInfo_customerAmount").val());
        var customer_gst = Number($("#adjustmentInfo_gstCustomerAmount").val());
        var valid_cust_amount = Number($("#adjustmentValid_customerAmount").val());
        var customerAmount = Number($("input[name='adjustment.customerAmount']").val());
        if (customerAmount > valid_cust_amount) {
            $("#msg_customer_amount").html("<xms:localization text="Customer Amount must not be greater than " />" + valid_cust_amount);
            $("input[name='adjustment.customerAmount']").val(valid_cust_amount);
            customerAmount = valid_cust_amount;
        } else {
            $("#msg_customer_amount").html("");
        }
        var gstCustomerAmount = customerAmount * customer_gst / customer_amount;
        gstCustomerAmount = gstCustomerAmount.toFixed(2);
        $("input[name='adjustment.gstCustomerAmount']").val(gstCustomerAmount);
    }
</script>
