<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_edit_airbill">
    <s:hidden name="airbillInfo.shipmentId"/>
    <s:hidden name="airbillInfo.description"/>
    <s:hidden name="airbillInfo.customerCode"/>
    <s:hidden name="airbillInfo.senderAddressId"/>
    <s:hidden name="airbillInfo.receiverAddressId"/>
    <s:hidden name="invoiceStatus"/>
    <div id="md-20" title="Edit Airbill">
        <div class="form-group">
            <p align="center">
                <b><xms:localization text="Edit Airbill"/></b>
            </p>

            <div class="row">
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td class="td1"><xms:localization text="Airbill:"/><span class="s30">*</span></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions" readonly="true"
                                                         name="airbillInfo.airbillNumber"/>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Carrier:"/></td>
                            <s:if test="adminLevel<3">
                                <td class="td2"><s:hidden name="airbillInfo.serviceName" id="hid_service_name"/>
                                    <s:select onchange="changeService($(this).val())" list="serviceModels"
                                              listKey="serviceId" listValue="serviceName" value="airbillInfo.serviceId"
                                              name="airbillInfo.serviceId" cssClass="form-control"/></td>
                            </s:if>
                            <s:else>
                                <td class="td2"><s:hidden name="airbillInfo.serviceName" id="hid_service_name"/>
                                    <s:select list="serviceModels" listKey="serviceId" listValue="serviceName"
                                              value="airbillInfo.serviceId" disabled="true" cssClass="form-control"/>
                                    <s:hidden name="airbillInfo.serviceId"/></td>
                            </s:else>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Service Level:"/></td>
                            <td class="td2" id="td_shipment_type"><s:select list="shipmentTypeModels"
                                                                            listKey="serviceCode"
                                                                            value="airbillInfo.serviceCode"
                                                                            listValue="shipmentTypeName" headerKey=""
                                                                            headerValue=""
                                                                            name="airbillInfo.serviceCode"
                                                                            cssClass="form-control"/></td>
                        </tr>
                        <s:if test="adminLevel<3">
                            <tr>
                                <td class="td1"><xms:localization text="Carrier Base Chg:"/></td>
                                <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                             name="airbillInfo.carrierBaseCharge"
                                                             onkeypress="return Numericvalue(event,this,true)"
                                                             onkeyup="markupFranchiseCharge(this.name,'airbillInfo.franchiseCost')"/>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Franchise Cost...:"/></td>
                                <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                             name="airbillInfo.franchiseCost"
                                                             onkeypress="return Numericvalue(event,this,true)"/></td>
                            </tr>
                        </s:if>
                        <s:else>
                            <tr>
                                <s:hidden name="airbillInfo.carrierBaseCharge"/>
                                <td class="td1"><xms:localization text="Franchise Cost...:"/></td>
                                <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                             name="airbillInfo.franchiseCost"
                                                             onkeypress="return Numericvalue(event,this,true)"
                                                             readonly="true"/></td>
                            </tr>
                        </s:else>
                        <tr>
                            <td class="td1"><xms:localization text="Calculated Chg.:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.calculatedCharge"
                                                         onkeypress="return Numericvalue(event,this,true)"
                                                         onkeyup="calculateTaxAmount(0,'airbillInfo.tax',this.name,'airbillInfo.forceCustCharge','airbillInfo.taxAmount')"/></td>
                        </tr>
                            <%-- <tr>
                                <td class="td1"><xms:localization text="Force Cust Chg.:" /></td>
                                <td class="td2"><s:textfield cssClass="form-control alloptions" name="airbillInfo.forceCustCharge" onkeypress="return Numericvalue(event,this,true)" onkeyup="calculateTaxAmount(0,'airbillInfo.tax','airbillInfo.calculatedCharge',this.name,'airbillInfo.taxAmount')" /></td>
                            </tr> --%>
                        <tr>
                            <td class="td1"><xms:localization text="Ship Date:"/><span class="s30">*</span></td>
                            <td class="td2">
                                <div class="form-group input-group mg0">
									<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
									</span> <input readonly="readonly" name="airbillInfo.shipmentDate"
                                                   value='<s:property value="airbillInfo.shipmentDate"/>'
                                                   class="form-control form_datetime" type="text"
                                                   data-date-format="dd MM yyyy" placeholder="">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Weight:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions" name="airbillInfo.weight"
                                                         onkeypress="return Numericvalue(event,this,true)"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Reweight Weight:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.reweightWeight"
                                                         onkeypress="return Numericvalue(event,this,true)"/></td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td class="td1"><xms:localization text="Actual Dimensions:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.actualDimensions"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Origin:"/></td>
                            <td class="td2"><s:select list="listCountryOrigin" listKey="countryId"
                                                      value="airbillInfo.originId" listValue="countryName"
                                                      cssClass="form-control" name="airbillInfo.originId"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Origin City:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.originCity"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Destination:"/><span class="s30">*</span></td>
                            <td class="td2"><s:select list="listCountryDestination" listKey="countryId"
                                                      value="airbillInfo.destinationId" listValue="countryName"
                                                      cssClass="form-control" name="airbillInfo.destinationId"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Destination City:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.destinationCity"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Zone:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.zone"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Pieces:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.noOfPieces"
                                                         onkeypress="return Numericvalue(event,this,true)"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Reference:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.reference"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Reference 2:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.reference2"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Reference 3:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.reference3"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div id="div_edit_airbill_do"></div>
                                <s:if test="invoiceStatus != 0">
                                    <i style="color: red;"><xms:localization text="This Invoice is Frozen."/></i>
                                </s:if>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td class="td1"><xms:localization text="Received By:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.receivedBy"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Received Date:"/></td>
                            <td class="td2">
                                <div class="form-group input-group mg0">
									<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
									</span> <input readonly="readonly" name="airbillInfo.receivedDate"
                                                   value='<s:property value="airbillInfo.receivedDate"/>'
                                                   class="form-control form_datetime" type="text"
                                                   data-date-format="dd MM yyyy" placeholder="">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Freight Class:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                         name="airbillInfo.freightClass"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Create Date:"/></td>
                            <td class="td2"><s:property value="airbillInfo.createDate"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Last Modified:"/></td>
                            <td class="td2"><s:property value="airbillInfo.modifiedDate"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="GST/VAT:"/></td>
                            <td class="td2a"><s:checkbox name="airbillInfo.isGst" cssClass="sel_basecharge"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Tax %:"/></td>
                            <td class="td2"><s:textfield cssClass="form-control alloptions" name="airbillInfo.tax"
                                                         onkeypress="return Numericvalue(event,this,true)"
                                                         onkeyup="calculateTaxAmount(0,this.name,'airbillInfo.calculatedCharge','airbillInfo.forceCustCharge','airbillInfo.taxAmount')"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Tax Amount:"/></td>
                            <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                         name="airbillInfo.taxAmount"
                                                         onkeypress="return Numericvalue(event,this,true)"/></td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-12" id="div_accessorial">
                    <p align="center">
                        <b><xms:localization text="Accessorials"/></b>
                    </p>

                    <div style="overflow: auto; height: 300px; margin-right: -15px">
                        <table class="table" style="font-size: 11px;">
                            <s:iterator value="accessorialInfoModels" status="accessorialInfo" var="accInfo">
                                <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/>">
                                    <td class="td1"><b><xms:localization
                                            text="Accessorial <s:property value='%{#accessorialInfo.index+1}'/>:"/></b>
                                    </td>
                                    <td class="td2" id="td_accessorial_${accessorialInfo.index}" colspan="7"
                                        style="border-bottom-color: #FFF">
                                        <s:if test="adminLevel < 2 || serviceId.equals('')">
                                            <s:select headerKey="" headerValue="" list="accessorialModels"
                                                      listKey="accessorialId" listValue="accessorialName"
                                                      value="accessorialId"
                                                      name="accessorialInfoModels[%{#accessorialInfo.index}].accessorialId"
                                                      cssClass="form-control sel_accessorial"/>
                                        </s:if>
                                        <s:elseif test="%{#accInfo.isNew==1}">
                                            <s:select headerKey="" headerValue="" list="accessorialModels"
                                                      listKey="accessorialId" listValue="accessorialName"
                                                      value="accessorialId"
                                                      name="accessorialInfoModels[%{#accessorialInfo.index}].accessorialId"
                                                      cssClass="form-control sel_accessorial"/>
                                        </s:elseif>
                                        <s:else>
                                            <s:select list="accessorialModels" listKey="accessorialId"
                                                      listValue="accessorialName" value="accessorialId"
                                                      name="accessorialInfoModels[%{#accessorialInfo.index}].accessorialId"
                                                      cssClass="form-control sel_accessorial"/>
                                        </s:else>
                                        <s:hidden
                                                name="accessorialInfoModels[%{#accessorialInfo.index}].descriptionOld"/>
                                        <s:hidden name="accessorialInfoModels[%{#accessorialInfo.index}].isNew"/>
                                    </td>
                                </tr>
                                <s:if test="serviceId == null ">
                                    <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/>">
                                        <s:if test="adminLevel<3">
                                            <td class="td1"><xms:localization text="Carrier Cost"/></td>
                                            <td class="td2"><s:textfield readonly="true"
                                                                         cssClass="form-control alloptions"
                                                                         name="accessorialInfoModels[%{#accessorialInfo.index}].carrierCost"
                                                                         onkeypress="return Numericvalue(event,this,true)"
                                                                         onkeyup="markupFranchiseCharge(this.name,'accessorialInfoModels[%{#accessorialInfo.index}].franchiseCost')"/></td>
                                        </s:if>
                                        <td class="td1"><xms:localization text="Franchise Cost"/></td>
                                        <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                                     name="accessorialInfoModels[%{#accessorialInfo.index}].franchiseCost"
                                                                     onkeypress="return Numericvalue(event,this,true)"/></td>
                                        <td class="td1"><xms:localization text="Customer Cost"/></td>
                                        <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                                     name="accessorialInfoModels[%{#accessorialInfo.index}].customerCost"
                                                                     onkeypress="return Numericvalue(event,this,true)"
                                                                     onkeyup="calculateTaxAmount(1,'accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent',this.name,'','accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount')"/></td>
                                        <td class="td1"><xms:localization text="Recalc Markup"/></td>
                                        <td class="td2a"><s:checkbox disabled="true"
                                                                     name="accessorialInfoModels[%{#accessorialInfo.index}].requireCalculate"/></td>
                                    </tr>
                                    <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/> tr_gst_status">
                                        <td class="td1"><xms:localization text="GST/VAT"/></td>
                                        <td class="td2a"><s:checkbox disabled="true"
                                                                     name="accessorialInfoModels[%{#accessorialInfo.index}].isGst"/></td>
                                        <td class="td1"><xms:localization text="Tax %"/></td>
                                        <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                                     name="accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent"
                                                                     onkeypress="return Numericvalue(event,this,true)"
                                                                     onkeyup="calculateTaxAmount(1,this.name ,accessorialInfoModels[%{#accessorialInfo.index}].customerCost,'','accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount')"/></td>
                                        <td class="td1"><xms:localization text="Tax Amount"/></td>
                                        <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                                     name="accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount"
                                                                     onkeypress="return Numericvalue(event,this,true)"/></td>
                                    </tr>
                                </s:if>
                                <s:else>
                                    <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/>">
                                        <s:if test="adminLevel<3">
                                            <td class="td1"><xms:localization text="Carrier Cost"/></td>
                                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                         name="accessorialInfoModels[%{#accessorialInfo.index}].carrierCost"
                                                                         onkeypress="return Numericvalue(event,this,true)"
                                                                         onkeyup="markupFranchiseCharge(this.name,'accessorialInfoModels[%{#accessorialInfo.index}].franchiseCost')"/></td>
                                            <td class="td1"><xms:localization text="Franchise Cost"/></td>
                                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                         name="accessorialInfoModels[%{#accessorialInfo.index}].franchiseCost"
                                                                         onkeypress="return Numericvalue(event,this,true)"/></td>
                                        </s:if>
                                        <s:else>
                                            <s:hidden
                                                    name="accessorialInfoModels[%{#accessorialInfo.index}].carrierCost"/>
                                            <td class="td1"><xms:localization text="Franchise Cost"/></td>
                                            <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                         name="accessorialInfoModels[%{#accessorialInfo.index}].franchiseCost"
                                                                         readonly="true"/></td>
                                        </s:else>
                                        <td class="td1"><xms:localization text="Customer Cost"/></td>
                                        <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                     name="accessorialInfoModels[%{#accessorialInfo.index}].customerCost"
                                                                     onkeypress="return Numericvalue(event,this,true)"
                                                                     onkeyup="calculateTaxAmount(1,'accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent',this.name,'','accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount')"/></td>
                                        <td class="td1"><xms:localization text="Recalc Markup"/></td>
                                        <td class="td2a"><s:checkbox
                                                name="accessorialInfoModels[%{#accessorialInfo.index}].requireCalculate"/></td>
                                    </tr>
                                    <tr class="tr_accessorial_<s:property value='%{#accessorialInfo.index+1}'/> tr_gst_status">
                                        <td class="td1"><xms:localization text="GST/VAT"/></td>
                                        <td class="td2a"><s:checkbox
                                                name="accessorialInfoModels[%{#accessorialInfo.index}].isGst"/></td>
                                        <td class="td1"><xms:localization text="Tax %"/></td>
                                        <td class="td2"><s:textfield cssClass="form-control alloptions"
                                                                     name="accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent"
                                                                     onkeypress="return Numericvalue(event,this,true)"
                                                                     onkeyup="calculateTaxAmount(1,'accessorialInfoModels[%{#accessorialInfo.index}].customerTaxPercent',this.name,'','accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount')"/></td>
                                        <td class="td1"><xms:localization text="Tax Amount"/></td>
                                        <td class="td2"><s:textfield readonly="true" cssClass="form-control alloptions"
                                                                     name="accessorialInfoModels[%{#accessorialInfo.index}].customerTaxAmount"
                                                                     onkeypress="return Numericvalue(event,this,true)"/></td>
                                    </tr>
                                </s:else>
                            </s:iterator>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</s:form>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'dd-mm-yyyy'
    });
    var adminLevel = '<s:property value="adminLevel" />';
    function markupFranchiseCharge(origin, destination) {
        var carrierId = $('select[name="airbillInfo.serviceId"] option:selected').val();
        var carrierCharge = parseFloat($("input[name='" + origin + "']").val());
        var markup = 0.00;
        switch (+carrierId) {
            case 1:	// DHL Intl markup rate
                markup = '<s:property value="markup.markupRate" />';
                break;
            case 15: // DHL Domestic markup rate
                markup = '<s:property value="markup.dhlDomMarkupRate" />';
                break;
            case 3: // TNT Domestic markup rate
                markup = '<s:property value="markup.tntMarkupRate" />';
                break;
            case 54: // TNT International markup rate
                markup = '<s:property value="markup.tntServiceMarkupRate" />';
                break;
            case 52: // Toll Priority markup rate
                markup = '<s:property value="markup.tollMarkupRate" />';
                break;
            case 59: // Toll Ipec markup rate
                markup = '<s:property value="markup.tollIpecMarkupRate" />';
                break;
            case 72: // Startrack markup rate
                markup = '<s:property value="markup.startrackMarkupRate" />';
                break;
            default:
                markup = 0;
        }
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
    function changeService(val) {
        $("#hid_service_name").val(val);
        $("#div_accessorial").find("select").val("");
        $("#div_accessorial").find("input:text").val("");
        $("#div_accessorial").find("input:checkbox").attr("disable", true);
        $("#div_accessorial").find("input:checkbox").attr("checked", false);
        var action = "view_edit_invoice_show_edit_airbill_change_service.ix?reqType=json";
        var data = {
            'serviceId': val
        };
        var action2 = "view_edit_invoice_show_edit_airbill_change_accessorial.ix?reqType=json";
        getServiceLevelAndAccessorials(action, data, "", "td_shipment_type", true, false, action2, "td_accessorial");
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
                if ($("input[name='accessorialInfoModels[" + i + "].isNew']").val() == 0) {
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