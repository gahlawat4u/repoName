<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_change_accessorial_type">
            <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
            <div class="col-lg-12" id="div_accessorial">
                <p align="center">
                    <b><xms:localization text="Accessorials"/></b>
                </p>

                <div style="overflow: auto; height: 300px; margin-right: -15px:">
                    <table class="table" style="font-size: 11px;">
                        <s:iterator value="accessorialInfoModels" status="accessorialInfo">
                            <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/>">
                                <td class="td1"><b><xms:localization
                                        text="Accessorial <s:property value='%{#accessorialInfo.index+1}'/>:"/></b></td>
                                <td class="td2" id="td_accessorial_${accessorialInfo.index}" colspan="5"
                                    style="border-bottom-color: #FFF">
                                    <s:select headerKey="" headerValue="" list="accessorialList" listKey="id"
                                              listValue="display"
                                              name="accessorialInfoModels[%{#accessorialInfo.index}].descriptionExt"
                                              value="massAccessorialInfo.accessorialInfo[#accessorialInfo.index].descriptionExt"
                                              cssClass="form-control sel_accessorial"/>
                                    <s:hidden
                                            name="massAccessorialInfo.accessorialInfo[%{#accessorialInfo.index}].isNew"/>
                                </td>
                            </tr>
                            <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/>">
                                <s:if test="adminLevel<3">
                                    <td class="td1"><xms:localization text="Carrier Cost"/></td>
                                    <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                 name="accessorialInfoModels[%{#accessorialInfo.index}].carrierCost"
                                                                 value="%{massAccessorialInfo.accessorialInfo[#accessorialInfo.index].carrierCost}"
                                                                 onkeypress="return Numericvalue(event,this,true)"
                                                                 onkeyup="markupFranchiseCharge(this.name,'accessorialInfoModels[%{#accessorialInfo.index}].franchiseCost')"/></td>
                                    <td class="td1"><xms:localization text="Franchise Cost"/></td>
                                    <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                 name="accessorialInfoModels[%{#accessorialInfo.index}].franchiseCost"
                                                                 value="%{massAccessorialInfo.accessorialInfo[#accessorialInfo.index].franchiseCost}"
                                                                 onkeypress="return Numericvalue(event,this,true)"/></td>
                                </s:if>
                                <s:else>
                                    <!-- <td class="td1"><xms:localization text="Carrier Cost"/></td> -->
                                    <td class="td2" style="display: none;"><s:textfield
                                            cssClass="form-control alloptions"
                                            name="accessorialInfoModels[%{#accessorialInfo.index}].carrierCost"
                                            value="%{massAccessorialInfo.accessorialInfo[#accessorialInfo.index].carrierCost}"
                                            onkeypress="return Numericvalue(event,this,true)"
                                            onkeyup="markupFranchiseCharge(this.name,'accessorialInfoModels[%{#accessorialInfo.index}].franchiseCost')"/></td>
                                    <td class="td1"><xms:localization text="Franchise Cost"/></td>
                                    <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                 name="accessorialInfoModels[%{#accessorialInfo.index}].franchiseCost"
                                                                 value="%{massAccessorialInfo.accessorialInfo[#accessorialInfo.index].franchiseCost}"
                                                                 onkeypress="return Numericvalue(event,this,true)"
                                                                 readonly="true"/></td>
                                </s:else>
                                <td class="td1"><xms:localization text="Customer Cost"/></td>
                                <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                             name="accessorialInfoModels[%{#accessorialInfo.index}].customerCost"
                                                             value="%{massAccessorialInfo.accessorialInfo[#accessorialInfo.index].customerCost}"
                                                             onkeypress="return Numericvalue(event,this,true)"
                                                             onkeyup="calculateTaxAmount(1,'accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent',this.name,'','accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount')"/></td>
                                <td class="td1"><xms:localization text="Recalc Markup"/></td>
                                <td class="td2a"><s:checkbox
                                        name="accessorialInfoModels[%{#accessorialInfo.index}].requireCalculate"
                                        value="massAccessorialInfo.accessorialInfo[#accessorialInfo.index].requireCalculate"/></td>
                            </tr>
                            <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/> tr_gst_status">
                                <td class="td1"><xms:localization text="GST/VAT"/></td>
                                <td class="td2a"><s:checkbox
                                        name="accessorialInfoModels[%{#accessorialInfo.index}].isGst"
                                        value="massAccessorialInfo.accessorialInfo[#accessorialInfo.index].isGst"/></td>
                                <td class="td1"><xms:localization text="Tax %"/></td>
                                <s:if test="massAccessorialInfo.accessorialInfo[#accessorialInfo.index].customerTaxPercent==0">
                                    <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                                 name="accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent"
                                                                 onkeypress="return Numericvalue(event,this,true)"
                                                                 onkeyup="calculateTaxAmount(1,'accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent',this.name,'','accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount')"/></td>
                                </s:if>
                                <s:else>
                                    <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                 name="accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent"
                                                                 value="%{massAccessorialInfo.accessorialInfo[#accessorialInfo.index].customerTaxPercent}"
                                                                 onkeypress="return Numericvalue(event,this,true)"
                                                                 onkeyup="calculateTaxAmount(1,'accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent',this.name,'','accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount')"/></td>
                                </s:else>
                                <td class="td1"><xms:localization text="Tax Amount"/></td>
                                <s:if test="massAccessorialInfo.accessorialInfo[#accessorialInfo.index].customerTaxAmount==0">
                                    <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                                 name="accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount"
                                                                 onkeypress="return Numericvalue(event,this,true)"/></td>
                                </s:if>
                                <s:else>
                                    <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                 name="accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount"
                                                                 value="%{massAccessorialInfo.accessorialInfo[#accessorialInfo.index].customerTaxAmount}"
                                                                 onkeypress="return Numericvalue(event,this,true)"/></td>
                                </s:else>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    var adminLevel = '<s:property value="adminLevel" />';
    function markupFranchiseCharge(origin, destination) {
        var carrierCharge = parseFloat($("input[name='" + origin + "']").val());
        var markup = 0.00;
        markup = parseFloat(markup);
        var franchiseCharge = 0.00;
        franchiseCharge = carrierCharge + carrierCharge * markup / 100;
        if ($("input[name='" + origin + "']").val() != '') {
            $("input[name='" + destination + "']").val(franchiseCharge.toFixed(2));
        } else {
            $("input[name='" + destination + "']").val("");
        }
    }
    function calculateTaxAmount(flag, tax, custCharge, forceCustCharge, taxAmount) {
        var custTaxAmount = 0.00;
        var calCharge = 0.00;
        var taxPercent = ($("input[name='" + tax + "']").val() == '') ? '0.00' : $("input[name='" + tax + "']").val();
        taxPercent = parseFloat(taxPercent);
        var custCost = ($("input[name='" + custCharge + "']").val() == '') ? '0.00' : $("input[name='" + custCharge + "']").val();
        custCost = parseFloat(custCost);
        calCharge = custCost;
        custTaxAmount = taxPercent * calCharge / 100;
        $("input[name='" + taxAmount + "']").val(custTaxAmount.toFixed(2));
    }
    function changeAccessorial(obj) {
        var class_tr = $("#" + obj).parent().parent().attr("class");
        if ($("#" + obj).val() != "") {
            $('.' + class_tr).find("input:text").attr("readonly", false);
            $('.' + class_tr).find("input:checkbox").prop('checked', true);
            $('.' + class_tr).find("input:checkbox").removeAttr("disabled");
        } else {
            $('.' + class_tr).find("input:text").val("");
            $('.' + class_tr).find("input:text").attr("readonly", true);
            $('.' + class_tr).find("input:checkbox").prop('checked', false);
            $('.' + class_tr).find("input:checkbox").attr("disabled", true);
        }
    }
    $(".sel_accessorial").change(function () {
        var class_tr = $(this).parent().parent().attr("class");
        if ($(this).val() != "") {
            $('.' + class_tr).find("input:text").attr("readonly", false);
            $('.' + class_tr).find("input:checkbox").prop('checked', true);
            $('.' + class_tr).find("input:checkbox").removeAttr("disabled");
        } else {
            $('.' + class_tr).find("input:text").val("");
            $('.' + class_tr).find("input:text").attr("readonly", true);
            $('.' + class_tr).find("input:checkbox").prop('checked', false);
            $('.' + class_tr).find("input:checkbox").attr("disabled", true);
        }
        for (i = 0; i < 8; i++) {
            $("input[name='accessorialInfoModels[" + i + "].customerTaxAmount']").attr("readonly", true);
            if (adminLevel >= 3) {
                if ($("input[name='massAccessorialInfo.accessorialInfo[" + i + "].isNew']").val() != '') {
                    $("input[name='accessorialInfoModels[" + i + "].franchiseCost']").attr("readonly", true);
                }
            }
        }
    });
    $(".sel_basecharge").change(function () {
        if ($("input[name='airbillInfo.isGst']").is(":checked")) {
            $("input[name='airbillInfo.tax']").attr("readonly", false);
        } else {
            $("input[name='airbillInfo.tax']").attr("readonly", true);
        }
    });
    $(".tr_gst_status").find("input:checkbox").click(function () {
        if ($(this).is(':checked')) {
            $(this).parent().parent().find("input:text").attr("readonly", false);
        } else {
            $(this).parent().parent().find("input:text").attr("readonly", true);
            $(this).parent().parent().find("input:text").val("");
        }
    });
</script>