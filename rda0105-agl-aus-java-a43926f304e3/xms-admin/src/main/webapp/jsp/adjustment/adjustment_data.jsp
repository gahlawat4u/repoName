<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!-- BEGIN ADJUSTMENT CONTENT -->
<div class="col-lg-9">
    <div class="col-lg-12">
        <table class="table" style="font-size: 11px; width: 50%">
            <tr>
                <td class="td1"><xms:localization text="Adjustments :"/></td>
                <td class="td2"><s:i18n_select name="adjustmentRequest.adjustType" list="adjustmentTypes" listKey="id"
                                               listValue="name" cssClass="form-control s52"
                                               onchange="changeAdjustType(1)"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Sub Adjustments :"/></td>
                <td class="td2" colspan="2"><s:i18n_select name="adjustmentRequest.adjustmentType"
                                                           list="subAdjustmentTypes" cssClass="form-control s52"
                                                           onchange="changeAdjustType(2)"/></td>
            </tr>
        </table>
        <hr>
    </div>
    <div class="col-lg-6">
        <p class="s38">
            <xms:localization text="New Adjustment Request"/>
        </p>
        <table class="table" style="font-size: 11px;" id="adjustment-table">
            <tr>
                <td class="td1"><xms:localization text="Airbill Number :"/></td>
                <td class="td2 s51" colspan="2"><s:property value="adjustmentRequest.airbillNumber"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Adjustment Type :"/></td>
                <td class="td2 s51" colspan="2"><s:property value="adjustmentRequest.adjustmentType"/></td>
            </tr>
            <tr data-group="carrier-amount">
                <td class="td1"><xms:localization text="Carrier Amount :"/><span class="s30"> *</span></td>
                <td class="td2" colspan="2"><input numeric-text="true" name="adjustmentRequest.carrierAmount"
                                                   class="form-control alloptions" type="text" maxlength="25" value="0"
                                                   onkeyup="validCarrierAmount()"/> <s:hidden
                        name="adjustmentInfo.carrierAmount"/> <span class="s30" id="msg_carrier_amount"></span></td>
            </tr>
            <tr data-group="carrier-amount">
                <td class="td1"><xms:localization text="GST Carrier Amount :"/></td>
                <td class="td2 s51" colspan="2"><input class="disable-input" name="adjustmentRequest.gstCarrierAmount"
                                                       class="form-control alloptions" type="text" maxlength="25"
                                                       value="0"/> <s:hidden name="adjustmentInfo.gstCarrierAmount"/>
                </td>
            </tr>
            <tr data-group="franchise-amount">
                <td class="td1"><xms:localization text="Franchise Amount :"/><span class="s30"> *</span></td>
                <td class="td2" colspan="2"><input numeric-text="true" name="adjustmentRequest.franchiseAmount"
                                                   class="form-control alloptions" type="text" maxlength="25" value="0"
                                                   onkeyup="validFranchiseAmount()"/> <s:hidden
                        name="adjustmentInfo.franchiseAmount"/><span class="s30" id="msg_franchise_amount"></span></td>
            </tr>
            <tr data-group="franchise-amount">
                <td class="td1"><xms:localization text="GST Franchise Amount :"/></td>
                <td class="td2 s51" colspan="2"><input class="disable-input" name="adjustmentRequest.gstFranchiseAmount"
                                                       class="form-control alloptions" type="text" maxlength="25"
                                                       value="0"/> <s:hidden name="adjustmentInfo.gstFranchiseAmount"/>
                </td>
            </tr>
            <tr data-group="customer-amount">
                <td class="td1"><xms:localization text="Customer Amount :"/><span class="s30"> *</span></td>
                <td class="td2" colspan="2"><input numeric-text="true" name="adjustmentRequest.customerAmount"
                                                   class="form-control alloptions" type="text" maxlength="25"
                                                   value="<s:property value="adjustmentInfo.customerAmount" />"
                                                   onkeyup="validCustomerAmount()"/> <s:hidden
                        name="adjustmentInfo.customerAmount"/><span class="s30" id="msg_customer_amount"></span></td>
            </tr>
            <tr data-group="customer-amount">
                <td class="td1"><xms:localization text="GST Customer Amount :"/></td>
                <td class="td2 s51" colspan="2"><input class="disable-input" name="adjustmentRequest.gstCustomerAmount"
                                                       class="form-control alloptions" type="text" maxlength="25"
                                                       value="<s:property value="adjustmentInfo.gstCustomerAmount" />"/>
                    <s:hidden name="adjustmentInfo.gstCustomerAmount"/></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-6">
        <p class="s38">&nbsp;</p>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><xms:localization text="Notes :"/><span class="s30"> *</span></td>
                <s:set var="paramMap" value="#{'{currencySymbol}':'currencySymbol' }"/>
                <td class="td2" colspan="2"><textarea name="adjustmentRequest.note" class="form-control alloptions"
                                                      style="height: 100px;"
                                                      placeholder='<xms:localization text="Please enter a valid description in the notes fields test - upon carrier 1- 2{currencySymbol}" paramMap="paramMap" escapeXml = "true"/>'></textarea>
                </td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Credit Customer Now? :"/></td>
                <td class="td2" colspan="2"><s:i18n_select name="adjustmentRequest.creditType" list="creditTypes"
                                                           listKey="id" listValue="name" cssClass="form-control s52"
                                                           onchange="changeAdjustType(1)"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Automatically apply GST? :"/></td>
                <td class="td2" colspan="2"><s:i18n_select name="adjustmentRequest.applyGstType" list="gstTypes"
                                                           listKey="id" listValue="name"
                                                           cssClass="form-control s52"/></td>
            </tr>
            <tr>
                <td class="" colspan="2">
                    <button id="btnAddRequest" class="btn s37" type="button" onclick="addAdjustmentRequest()">
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
        <xms:localization text="<b>Note:</b> <br /> <br />
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

<script>
    $(document).ready(function () {
        // Prevents the invalid number will be entered into the numeric text
        $("input[numeric-text]").each(function () {
            $(this).number(true, 2, '.', '');
        });

        var userLevel = '<s:property value="userLevel" />';
        if (userLevel == '') {
            userLevel = 0;
        }
        // Show/hide appropriate amount.
        showHideAmount(userLevel);
    });


</script>