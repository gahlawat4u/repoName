<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_add_accessorial_charge">
            <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
            <div class="col-lg-12" id="div_accessorial">
                <p align="center">
                    <b><xms:localization text="Accessorials"/></b>
                </p>

                <div style="overflow: auto; height: 300px; margin-right: -15px:">
                    <table class="table" style="font-size: 11px;">
                        <tr class="tr_accessorial">
                            <td class="td1"><b><xms:localization text="Accessorial:"/></b></td>
                            <td class="td2" id="td_accessorial" colspan="5" style="border-bottom-color: #FFF"><s:select
                                    headerKey="" headerValue="" list="accessorialList" listKey="description"
                                    listValue="description" name="accessorialInfoModels.description"
                                    cssClass="form-control sel_accessorial"/></td>
                        </tr>
                        <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/>">
                            <s:if test="adminLevel<3">
                                <td class="td1"><xms:localization text="Carrier Cost"/></td>
                                <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                             name="accessorialInfoModels.carrierCost"
                                                             onkeypress="return Numericvalue(event,this,true)"
                                                             onkeyup="markupFranchiseCharge(this.name,'accessorialInfoModels.franchiseCost')"/></td>
                                <!-- <td class="td1"><xms:localization text="Franchise Cost"/></td>
                                <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                             name="accessorialInfoModels.franchiseCost"
                                                             onkeypress="return Numericvalue(event,this,true)"/></td> -->
                            </s:if>
                            <s:else>
                                <!-- <td class="td1"><xms:localization text="Carrier Cost"/></td> -->
                                <td class="td2" style="display: none"><s:textfield cssClass="form-control alloptions"
                                                                                   name="accessorialInfoModels.carrierCost"
                                                                                   onkeypress="return Numericvalue(event,this,true)"
                                                                                   onkeyup="markupFranchiseCharge(this.name,'accessorialInfoModels.franchiseCost')"/></td>
                                <td class="td1"><xms:localization text="Franchise Cost"/></td>
                                <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                             name="accessorialInfoModels.franchiseCost"
                                                             onkeypress="return Numericvalue(event,this,true)"/></td>
                            </s:else>
                            <td class="td1"><xms:localization text="Customer Cost"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="accessorialInfoModels.customerCost"
                                                         onkeypress="return Numericvalue(event,this,true)"
                                                         onkeyup="calculateTaxAmount(1,'accessorialInfoModels.customerTaxPercent',this.name,'','accessorialInfoModels.customerTaxAmount')"/></td>
                            <td class="td1"><xms:localization text="Recalc Markup"/></td>
                            <td class="td2a"><s:checkbox name="accessorialInfoModels.requireCalculate"/></td>
                        </tr>
                        <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/> tr_gst_status">
                            <td class="td1"><xms:localization text="GST/VAT"/></td>
                            <td class="td2a"><s:checkbox name="accessorialInfoModels.isGst"/></td>
                            <td class="td1"><xms:localization text="Tax %"/></td>
                            <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                         name="accessorialInfoModels.customerTaxPercent"
                                                         onkeypress="return Numericvalue(event,this,true)"
                                                         onkeyup="calculateTaxAmount(1,'accessorialInfoModels.customerTaxPercent',this.name,'','accessorialInfoModels.customerTaxAmount')"/></td>
                            <td class="td1"><xms:localization text="Tax Amount"/></td>
                            <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                         name="accessorialInfoModels.customerTaxAmount"
                                                         onkeypress="return Numericvalue(event,this,true)"/></td>
                        </tr>
                        <tr>
                            <td colspan="6"><span class="s30"><xms:localization
                                    text="Can only edit the airbill(s) that has same carrier with carrier of selected accessorial(s)"/></span>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
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
        var forceCustCost = ($("input[name='" + forceCustCharge + "']").val() == '') ? '0.00' : $("input[name='" + forceCustCharge + "']").val();
        forceCustCost = parseFloat(forceCustCost);
        if (flag == 0) // for base charge
        {
            if (forceCustCost == '' || forceCustCost == 0.00) {
                calCharge = custCost;
            } else {
                calCharge = forceCustCost;
            }
        }
        else {
            calCharge = custCost;
        }
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