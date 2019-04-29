<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<ul id="generalTab" class="nav nav-tabs responsive">
    <li class="active" style="margin-left: 10px;"><a href="#Account-tab" data-toggle="tab"
                                                     onclick="loadAccountSetup(0)"><xms:localization
            text="Account Setup"/></a></li>
    <li><a href="#Base-tab" data-toggle="tab" class="tb1" onclick="loadBaseRate(0)"><xms:localization
            text="Base Rates"/></a></li>
    <li><a href="#Invoice-tab" data-toggle="tab" onclick="loadInvoiceOptions(0)"><xms:localization
            text="Invoice Options"/></a></li>
    <li><a href="#Collections-tab" data-toggle="tab" class="tb1" onclick="loadCollections(0)"><xms:localization
            text="Collections"/></a></li>
</ul>
<s:form id="add-new-profile-form">
    <div id="generalTabContent" class="tab-content responsive">
        <div id="Account-tab" class="tab-pane fade in active">
            <s:hidden name="addNewCutomerProfile.customerProfile.franchiseCode" value="%{cusProfile.franchiseCode}"
                      id="franchise_code"/>
            <div class="row">
                <div class="portlet-body b12 b11">
                    <div class="row">
                        <div class="col-lg-5">
                            <table class="table" style="font-size: 11px;">
                                <tr>
                                    <td colspan="3" style="border-top: 0px !important">
                                        <div class="caption b17">
                                            <xms:localization text="Account Setup"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Profile Name:"/><span class="s30"></span>
                                    </td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.profileName"
                                                                             value="%{cusProfile.profileName}"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Inactive?:"/></td>
                                    <td class="td2" colspan="2"><s:checkbox
                                            name="addNewCutomerProfile.customerProfile.inActive"
                                            value="%{cusProfile.inActive}"></s:checkbox></td>
                                </tr>
                            </table>
                            <table class="table" style="font-size: 11px;">
                                <tr>
                                    <td colspan="3" style="border-top: 0px !important">
                                        <div class="caption b17">
                                            <xms:localization text="Reporting Setup"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Previous Carrier:"/></td>
                                    <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="services"
                                                                          class="form-control" listKey="serviceId"
                                                                          listValue="serviceName"
                                                                          name="addNewCutomerProfile.customerProfile.previousCarrier"
                                                                          value="%{cusProfile.previousCarrier}"></s:select></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Web Freight Group:"/></td>
                                    <td class="td2" colspan="2"><s:select headerKey="" headerValue=""
                                                                          list="customerGroup" class="form-control"
                                                                          listKey="customerGroupId"
                                                                          listValue="customerGroupName"
                                                                          name="addNewCutomerProfile.customerProfile.webshipGroupId"
                                                                          value="%{cusProfile.webshipGroupId}"></s:select></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Industry:"/></td>
                                    <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="industries"
                                                                          class="form-control" listKey="industryId"
                                                                          listValue="industryName"
                                                                          name="addNewCutomerProfile.customerProfile.industryId"
                                                                          value="%{cusProfile.industryId}"></s:select></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Area:"/></td>
                                    <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="areas"
                                                                          class="form-control" listKey="areaId"
                                                                          listValue="areaName"
                                                                          name="addNewCutomerProfile.customerProfile.areaId"
                                                                          value="%{cusProfile.areaId}"></s:select></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Sales Rep:"/></td>
                                    <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="saleReps"
                                                                          class="form-control" listKey="userId"
                                                                          listValue="userName"
                                                                          name="addNewCutomerProfile.customerProfile.salesRepId"
                                                                          value="%{cusProfile.salesRepId}"></s:select></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Collector:"/></td>
                                    <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="collectors"
                                                                          class="form-control" listKey="userId"
                                                                          listValue="userName"
                                                                          name="addNewCutomerProfile.customerProfile.collectorId"
                                                                          value="%{cusProfile.collectorId}"></s:select></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Business Registration#:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.registrationId"
                                                                             onkeypress="return formartNumber(event,this,false);"
                                                                             value="%{cusProfile.registrationId}"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="GST#:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.gstId"
                                                                             onkeypress="return formartNumber(event,this,false);"
                                                                             value="%{cusProfile.gstId}"></s:textfield></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-lg-5">
                            <table class="table" style="font-size: 11px;">
                                <tr>
                                    <td colspan="3" style="border-top: 0px !important">
                                        <div class="caption b17">
                                            <xms:localization text="Carrier Setup"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="DHL Account#:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.dhlAccount"
                                                                             value="%{cusProfile.dhlAccount}"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Hub Account#:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.hubAccount"
                                                                             value="%{cusProfile.hubAccount}"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="TNT Account#:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.tntAccount"
                                                                             value="%{cusProfile.tntAccount}"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Toll Priority Account#:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.tollPriorityAccount"
                                                                             value="%{cusProfile.tollPriorityAccount}"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Startrack Account#:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.startrackAccount"
                                                                             value="%{cusProfile.startrackAccount}"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Startrack Dispatch ID#:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.dispatchId"
                                                                             value="%{cusProfile.dispatchId}"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other Account#:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                             name="addNewCutomerProfile.customerProfile.otherAccount"
                                                                             value="%{cusProfile.otherAccount}"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Booking Email Notification:"/></td>
                                    <td class="td2" colspan="2"><s:checkbox
                                            name="addNewCutomerProfile.customerProfile.bookingEmailNotification"
                                            value="%{cusProfile.bookingEmailNotification}"></s:checkbox></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Rejection Notes:"/></td>
                                    <td class="td2" colspan="2"><s:textarea
                                            name="addNewCutomerProfile.customerProfile.rejectionNote"
                                            class="form-control" value="%{cusProfile.rejectionNote}"></s:textarea></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Enable AGL Warranty:"/></td>
                                    <td class="td2" colspan="2"><s:checkbox
                                            name="addNewCutomerProfile.customerProfile.enableSi"
                                            value="%{cusProfile.enableSi}"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div id="div_save_account_setup_result"></div>
            <script type="text/javascript">
                $(document).ready(function () {
                    if ($("#sel_customer_profiles").val() == "0") {
                        $("#franchise_code").val($("#sel_franchise").val());
                    }
                });
                function saveCustomerProfileAccountSetup() {
                    var data = $("#saveCustomerProfileAccountSetupForm").serialize();
                    loadingDialog.dialog("open");
                    $.post("manage_customer_profile_account_setup_save.ix?reqType=json", data, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            if ($("#sel_customer_profiles").val() == "0") {
                                $("#generalTab").find("li").show();
                                dialog.html("Create Success.");
                                dialog.dialog("open");
                                $('#td_select_customer_profiles').html(res.content);
                            } else {
                                dialog.html(res.content);
                                dialog.dialog("open");
                            }
                        } else if (res.errorCode == "FIELD_ERROR") {
                            dialog.html(res.content);
                            dialog.dialog("open");
                        } else if (res.errorCode == "ACTION_ERROR") {
                            alertDialog.html(res.errorMsg);
                            alertDialog.dialog("open");
                        }
                    }).fail(function () {
                        loadingDialog.dialog("close");
                        alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                        alertDialog.dialog("open");
                    });

                    var buttons = {};
                    var height = $(window).height();
                    var width = $(window).width();
                    height = height * 0.70;
                    width = width * 0.9;
                    var dialog = $("#div_save_account_setup_result").dialog({
                        modal: true,
                        autoOpen: false,
                        width: "auto",
                        buttons: buttons,
                        width: 'auto',
                        height: 'auto',
                        maxHeight: height,
                        create: function (event, ui) {
                            $(this).css("maxWidth", width);
                        },
                        show: {
                            effect: "fade",
                            duration: 300
                        },
                        close: function (e) {
                            $("#div_save_account_setup_result").html("");
                        }
                    });
                    dialog.dialog("option", "title", "Save Account Setup.");
                }
            </script>
        </div>
        <div id="Base-tab" class="tab-pane fade in">
            <style type="text/css">
                .scroll_horizontal {
                    overflow-x: auto;
                    overflow-y: hidden;
                    min-height: 55px;
                    width: 100%;
                }

                .div_baseRate input {
                    min-width: 50px;
                }
            </style>
            <form id="frmSaveCustomerProfileBaseRates">
                <div id="Base-tab" class="tab-pane fade in">
                    <div class="row">
                        <div class="portlet-body b12 b11">
                            <div class="portlet-body b22" style="padding: 0px;">
                                <ul id="cust_base_rate_tabs_nav" class="nav nav-tabs responsive">
                                    <li class="active" style="margin-left: 10px;"><a
                                            href="#cust_profile_base_rates_general_tab"
                                            data-toggle="tab"><xms:localization text="General"/></a></li>
                                    <li><a href="#cust_profile_base_rates_dhl_tab" data-toggle="tab"
                                           class="tb3"><xms:localization text="DHL"/></a></li>
                                    <li><a href="#cust_profile_base_rates_dhl_dom_tab" data-toggle="tab"
                                           class="tb3"><xms:localization text="DHL Domestic"/></a></li>
                                    <li><a href="#cust_profile_base_rates_tnt_dom_tab" data-toggle="tab"
                                           class="tb3"><xms:localization text="TNT Domestic"/></a></li>
                                    <li><a href="#cust_profile_base_rates_tnt_intl_tab" data-toggle="tab"
                                           class="tb3"><xms:localization text="TNT International"/></a></li>
                                    <li><a href="#cust_profile_base_rates_toll_priority_tab" data-toggle="tab"
                                           class="tb3"><xms:localization text="Toll Priority"/></a></li>
                                    <li><a href="#cust_profile_base_rates_toll_ipec_tab" data-toggle="tab"
                                           class="tb3"><xms:localization text="Toll Ipec"/></a></li>
                                    <li><a href="#cust_profile_base_rates_star_track_tab" data-toggle="tab" class="tb3"><xms:localization
                                            text="Star Track"/></a></li>
                                    <li><a href="#cust_profile_base_rates_others_tab" data-toggle="tab"
                                           class="tb3"><xms:localization text="Others"/></a></li>
                                </ul>
                                <div id="cust_profile_base_rates_tab_content" class="tab-content responsive">
                                    <div id="cust_profile_base_rates_general_tab" class="tab-pane fade in tb2 active">
                                        <div class="row">
                                            <div class="portlet-body b12 b11">
                                                <s:hidden name="addNewCutomerProfile.saveCustProfileBaseRate.profileId"
                                                          value="%{profile.profileId}"/>
                                                <table class="s36">
                                                    <tbody>
                                                    <tr>
                                                        <td>* = <xms:localization
                                                                text="May override other settings"/></td>
                                                        <td class="caption b17" colspan="2"><xms:localization
                                                                text="Base Rates"/></td>
                                                    </tr>
                                                    <TR>
                                                        <td colspan="3" height="5"></td>
                                                    </TR>
                                                    <tr>
                                                        <td>* <xms:localization
                                                                text="Minimum Customer Base Charge Margin"/></td>
                                                        <td width="60"><s:textfield id="minimum_base_charge"
                                                                                    name="addNewCutomerProfile.saveCustProfileBaseRate.minimunBaseCharge"
                                                                                    value="%{profile.minimunBaseCharge}"
                                                                                    cssClass="form-control"/></td>
                                                        <td>%</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <s:set var="global_index" value="-1"/>
                                    <div id="cust_profile_base_rates_dhl_tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                                <!-- Print Rate Sheet -->
                                                <div class="form-group">
                                                    <table class="s36">
                                                        <tr>
                                                            <td><xms:localization text="Print Rate Sheets"/></td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckAll($(this))">
                                                                    <xms:localization text="Check All"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckNone($(this))">
                                                                    <xms:localization text="Check None"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheet($(this))">
                                                                    <xms:localization text="Print Checked Rate Sheets"/>
                                                                </button>
                                                            </td>
                                                            <td><input type="checkbox" data-type="pdf-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Pdf"/></td>
                                                            <td><input type="checkbox" data-type="excel-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Excel"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <!-- Service Type List -->
                                                <s:iterator value="dhl" var="sv">
                                                    <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                        <s:iterator value="custProfileBaseRates" status="stats">
                                                            <s:set var="global_index" value="%{#global_index + 1}"/>
                                                            <!-- Customer base rate -->
                                                            <div class="form-group base-rate-row"
                                                                 data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                                 data-weight="<s:property value="weight"/>">
                                                                <div class="base-rate">
                                                                    <div class="well well-sm pull-left c33"
                                                                         style="width: 100%">
                                                                        <div class="pull-left c32"
                                                                             style="padding: 5px; width: 250px;">
                                                                            <s:if test="#stats.index == 0">
                                                                                <span class="br-display-name"><s:property
                                                                                        value="displayName"/></span>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <s:if test="#stats.index == 0">
                                                                                <input data-type="print-rate-sheet-checkbox"
                                                                                       type="checkbox"
                                                                                       data-customercode="<s:property value="profileId" />"
                                                                                       data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                                       data-content="<s:property value="%{#sv.content}" />"
                                                                                       data-bound="<s:property value="%{#sv.bound}" />"
                                                                                       data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                                       style="margin-top: 6px;"/>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-remove"
                                                                             <s:if test="custProfileBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-remove"><i
                                                                                    onclick="removeWeightBreak($(this))"
                                                                                    style="color: red; font-size: 18px; padding-top: 3px;"
                                                                                    class="fa fa-times s10 b3"></i></span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-weight"
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-weight"><s:property
                                                                                    value="weight"/>+</span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <i onclick="addWeightBreak($(this))"
                                                                               id="dwed-link"
                                                                               class="fa fa-chevron-circle-right s10 b3"
                                                                               style="font-size: 18px; padding-top: 3px;"
                                                                               data-index="<s:property value="#global_index"/>"></i>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="sel-rate-type">
                                                                            <s:select data-index="%{#global_index}"
                                                                                      name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                                      list="rateTypes" listKey="id"
                                                                                      listValue="name"
                                                                                      value="%{rateType}"
                                                                                      cssClass="form-control"/>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="base-rate">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        data-group="br-rate"/>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        readonly="true"
                                                                                        data-group="br-rate"/>
                                                                            </s:else>
                                                                        </div>
                                                                        <div class="pull-left c32a">%</div>
                                                                        <div class="pull-left c32a">
                                                                            <a href="javascript:void(0)"
                                                                               onclick="viewRateSheet('<s:property
                                                                                       value="profileId"/>',<s:property
                                                                                       value="%{#sv.shipmentTypeId}"/>,
                                                                                   <s:property value="%{#sv.content}"/>,
                                                                                   <s:property value="%{#sv.bound}"/>,
                                                                                   <s:property
                                                                                           value="%{#sv.serviceId}"/>)"
                                                                               class="b19"
                                                                               data-sheet-id="<s:property value="rateSheetId"/>"
                                                                               data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                               data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                               data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                                    text="View"/></b></u></a>
                                                                        </div>
                                                                        <div class="pull-left c32a">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-plus-square s10 b3"
                                                                                   data-show="close"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-minus-square s10 b3"
                                                                                   data-show="open"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:else>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!-- Customer base rate detail -->
                                                                <div class="form-group pull-left base-rate"
                                                                     style="width: 100%; overflow: auto; <s:if
                                                                             test="zoneCheck=='false'">display: none;</s:if>">
                                                                    <ul class="c36"
                                                                        style="width: 14324px; overflow: hidden;">
                                                                        <li class="pull-left c35"
                                                                            style="margin-left: 40px;"><s:checkbox
                                                                                data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                        <li class="pull-left c35" style="padding: 5px;">
                                                                            <xms:localization text="By Zone:"/></li>
                                                                        <s:iterator value="custProfileBaseRateDetails"
                                                                                    status="dStats">
                                                                            <li>
                                                                                <div class="pull-left c34"
                                                                                     data-group="zone">
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].zone"
                                                                                           class="form-control alloptions text-center"
                                                                                           maxlength="25"
                                                                                           value="<s:property value="zone"/>"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           <s:else>readonly</s:else>
                                                                                           type="text"
                                                                                           data-group="zone-name"/>
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].rate"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           class="form-control alloptions text-center"
                                                                                           type="text" maxlength="25"
                                                                                           value="<s:property value="rate"/>"
                                                                                           data-group="zone-rate">
                                                                                </div>
                                                                            </li>
                                                                        </s:iterator>
                                                                    </ul>
                                                                </div>
                                                                <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                            value="%{displayName}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                            value="%{weight}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                            value="%{shipmentTypeId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                            value="%{profileId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                            value="%{content}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                            value="%{bound}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                            value="%{serviceId}"
                                                                            data-index="%{#global_index}"/>
                                                                </div>
                                                            </div>
                                                        </s:iterator>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- DHL Dom -->
                                    <div id="cust_profile_base_rates_dhl_dom_tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                                <!-- Print Rate Sheet -->
                                                <div class="form-group">
                                                    <table class="s36">
                                                        <tr>
                                                            <td><xms:localization text="Print Rate Sheets"/></td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckAll($(this))">
                                                                    <xms:localization text="Check All"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckNone($(this))">
                                                                    <xms:localization text="Check None"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheet($(this))">
                                                                    <xms:localization text="Print Checked Rate Sheets"/>
                                                                </button>
                                                            </td>
                                                            <td><input type="checkbox" data-type="pdf-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Pdf"/></td>
                                                            <td><input type="checkbox" data-type="excel-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Excel"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <!-- Service Type List -->
                                                <s:iterator value="dhlDom" var="sv">
                                                    <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                        <s:iterator value="custProfileBaseRates" status="stats">
                                                            <s:set var="global_index" value="%{#global_index + 1}"/>
                                                            <!-- Customer base rate -->
                                                            <div class="form-group base-rate-row"
                                                                 data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                                 data-weight="<s:property value="weight"/>">
                                                                <div class="base-rate">
                                                                    <div class="well well-sm pull-left c33"
                                                                         style="width: 100%">
                                                                        <div class="pull-left c32"
                                                                             style="padding: 5px; width: 250px;">
                                                                            <s:if test="#stats.index == 0">
                                                                                <span class="br-display-name"><s:property
                                                                                        value="displayName"/></span>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <s:if test="#stats.index == 0">
                                                                                <input data-type="print-rate-sheet-checkbox"
                                                                                       type="checkbox"
                                                                                       data-customercode="<s:property value="profileId" />"
                                                                                       data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                                       data-content="<s:property value="%{#sv.content}" />"
                                                                                       data-bound="<s:property value="%{#sv.bound}" />"
                                                                                       data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                                       style="margin-top: 6px;"/>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-remove"
                                                                             <s:if test="custProfileBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-remove"><i
                                                                                    onclick="removeWeightBreak($(this))"
                                                                                    style="color: red; font-size: 18px; padding-top: 3px;"
                                                                                    class="fa fa-times s10 b3"></i></span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-weight"
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-weight"><s:property
                                                                                    value="weight"/>+</span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <i onclick="addWeightBreak($(this))"
                                                                               id="dwed-link"
                                                                               class="fa fa-chevron-circle-right s10 b3"
                                                                               style="font-size: 18px; padding-top: 3px;"
                                                                               data-index="<s:property value="#global_index"/>"></i>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="sel-rate-type">
                                                                            <s:select data-index="%{#global_index}"
                                                                                      name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                                      list="rateTypes" listKey="id"
                                                                                      listValue="name"
                                                                                      value="%{rateType}"
                                                                                      cssClass="form-control"/>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="base-rate">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        data-group="br-rate"/>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        readonly="true"
                                                                                        data-group="br-rate"/>
                                                                            </s:else>
                                                                        </div>
                                                                        <div class="pull-left c32a">%</div>
                                                                        <div class="pull-left c32a">
                                                                            <a href="javascript:void(0)"
                                                                               onclick="viewRateSheet('<s:property
                                                                                       value="profileId"/>',<s:property
                                                                                       value="%{#sv.shipmentTypeId}"/>,
                                                                                   <s:property value="%{#sv.content}"/>,
                                                                                   <s:property value="%{#sv.bound}"/>,
                                                                                   <s:property
                                                                                           value="%{#sv.serviceId}"/>)"
                                                                               class="b19"
                                                                               data-sheet-id="<s:property value="rateSheetId"/>"
                                                                               data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                               data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                               data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                                    text="View"/></b></u></a>
                                                                        </div>
                                                                        <div class="pull-left c32a">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-plus-square s10 b3"
                                                                                   data-show="close"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-minus-square s10 b3"
                                                                                   data-show="open"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:else>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!-- Customer base rate detail -->
                                                                <div class="form-group pull-left base-rate"
                                                                     style="width: 100%; overflow: auto; <s:if
                                                                             test="zoneCheck=='false'">display: none;</s:if>">
                                                                    <ul class="c36"
                                                                        style="width: 14324px; overflow: hidden;">
                                                                        <li class="pull-left c35"
                                                                            style="margin-left: 40px;"><s:checkbox
                                                                                data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                        <li class="pull-left c35" style="padding: 5px;">
                                                                            <xms:localization text="By Zone:"/></li>
                                                                        <s:iterator value="custProfileBaseRateDetails"
                                                                                    status="dStats">
                                                                            <li>
                                                                                <div class="pull-left c34"
                                                                                     data-group="zone">
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].zone"
                                                                                           class="form-control alloptions text-center"
                                                                                           maxlength="25"
                                                                                           value="<s:property value="zone"/>"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           <s:else>readonly</s:else>
                                                                                           type="text"
                                                                                           data-group="zone-name"/>
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].rate"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           class="form-control alloptions text-center"
                                                                                           type="text" maxlength="25"
                                                                                           value="<s:property value="rate"/>"
                                                                                           data-group="zone-rate">
                                                                                </div>
                                                                            </li>
                                                                        </s:iterator>
                                                                    </ul>
                                                                </div>
                                                                <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                            value="%{displayName}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                            value="%{weight}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                            value="%{shipmentTypeId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                            value="%{profileId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                            value="%{content}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                            value="%{bound}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                            value="%{serviceId}"
                                                                            data-index="%{#global_index}"/>
                                                                </div>
                                                            </div>
                                                        </s:iterator>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- TNT Dom -->
                                    <div id="cust_profile_base_rates_tnt_dom_tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                                <!-- Orgin -->
                                                <div class="form-group">
                                                    <table class="s36">
                                                        <tr>
                                                            <td><xms:localization text="Origin"/></td>
                                                            <td><s:select id="tntDomColumnName" cssClass="form-control"
                                                                          list="tntDomColumns"
                                                                          listValue="zoneName + ' - ' + zoneCode"
                                                                          listKey="zoneCode"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <!-- Print Rate Sheet -->
                                                <div class="form-group">
                                                    <table class="s36">
                                                        <tr>
                                                            <td><xms:localization text="Print Rate Sheets"/></td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckAll($(this))">
                                                                    <xms:localization text="Check All"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckNone($(this))">
                                                                    <xms:localization text="Check None"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheet($(this))">
                                                                    <xms:localization text="Print Checked Rate Sheets"/>
                                                                </button>
                                                            </td>
                                                            <td><input type="checkbox" data-type="pdf-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Pdf"/></td>
                                                            <td><input type="checkbox" data-type="excel-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Excel"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <!-- Service Type List -->
                                                <s:iterator value="tntDom" var="sv">
                                                    <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                        <s:iterator value="custProfileBaseRates" status="stats">
                                                            <s:set var="global_index" value="%{#global_index + 1}"/>
                                                            <!-- Customer base rate -->
                                                            <div class="form-group base-rate-row"
                                                                 data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                                 data-weight="<s:property value="weight"/>">
                                                                <div class="base-rate">
                                                                    <div class="well well-sm pull-left c33"
                                                                         style="width: 100%">
                                                                        <div class="pull-left c32"
                                                                             style="padding: 5px; width: 250px;">
                                                                            <s:if test="#stats.index == 0">
                                                                                <span class="br-display-name"><s:property
                                                                                        value="displayName"/></span>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <s:if test="#stats.index == 0">
                                                                                <input data-type="print-rate-sheet-checkbox"
                                                                                       type="checkbox"
                                                                                       data-customercode="<s:property value="profileId" />"
                                                                                       data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                                       data-content="<s:property value="%{#sv.content}" />"
                                                                                       data-bound="<s:property value="%{#sv.bound}" />"
                                                                                       data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                                       style="margin-top: 6px;"/>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-remove"
                                                                             <s:if test="custProfileBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-remove"><i
                                                                                    onclick="removeWeightBreak($(this))"
                                                                                    style="color: red; font-size: 18px; padding-top: 3px;"
                                                                                    class="fa fa-times s10 b3"></i></span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-weight"
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-weight"><s:property
                                                                                    value="weight"/>+</span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <i onclick="addWeightBreak($(this))"
                                                                               id="dwed-link"
                                                                               class="fa fa-chevron-circle-right s10 b3"
                                                                               style="font-size: 18px; padding-top: 3px;"
                                                                               data-index="<s:property value="#global_index"/>"></i>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="sel-rate-type">
                                                                            <s:select data-index="%{#global_index}"
                                                                                      name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                                      list="rateTypes" listKey="id"
                                                                                      listValue="name"
                                                                                      value="%{rateType}"
                                                                                      cssClass="form-control"/>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="base-rate">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        data-group="br-rate"/>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        readonly="true"
                                                                                        data-group="br-rate"/>
                                                                            </s:else>
                                                                        </div>
                                                                        <div class="pull-left c32a">%</div>
                                                                        <div class="pull-left c32a">
                                                                            <a href="javascript:void(0)"
                                                                               onclick="viewRateSheet('<s:property
                                                                                       value="profileId"/>',<s:property
                                                                                       value="%{#sv.shipmentTypeId}"/>,
                                                                                   <s:property value="%{#sv.content}"/>,
                                                                                   <s:property value="%{#sv.bound}"/>,
                                                                                   <s:property
                                                                                           value="%{#sv.serviceId}"/>)"
                                                                               class="b19"
                                                                               data-sheet-id="<s:property value="rateSheetId"/>"
                                                                               data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                               data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                               data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                                    text="View"/></b></u></a>
                                                                        </div>
                                                                        <div class="pull-left c32a">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-plus-square s10 b3"
                                                                                   data-show="close"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-minus-square s10 b3"
                                                                                   data-show="open"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:else>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!-- Customer base rate detail -->
                                                                <div class="form-group pull-left base-rate"
                                                                     style="width: 100%; overflow: auto; <s:if
                                                                             test="zoneCheck=='false'">display: none;</s:if>">
                                                                    <ul class="c36"
                                                                        style="width: 14324px; overflow: hidden;">
                                                                        <li class="pull-left c35"
                                                                            style="margin-left: 40px;"><s:checkbox
                                                                                data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                        <li class="pull-left c35" style="padding: 5px;">
                                                                            <xms:localization text="By Zone:"/></li>
                                                                        <s:iterator value="custProfileBaseRateDetails"
                                                                                    status="dStats">
                                                                            <li>
                                                                                <div class="pull-left c34"
                                                                                     data-group="zone">
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].zone"
                                                                                           class="form-control alloptions text-center"
                                                                                           maxlength="25"
                                                                                           value="<s:property value="zone"/>"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           <s:else>readonly</s:else>
                                                                                           type="text"
                                                                                           data-group="zone-name"/>
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].rate"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           class="form-control alloptions text-center"
                                                                                           type="text" maxlength="25"
                                                                                           value="<s:property value="rate"/>"
                                                                                           data-group="zone-rate">
                                                                                </div>
                                                                            </li>
                                                                        </s:iterator>
                                                                    </ul>
                                                                </div>
                                                                <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                            value="%{displayName}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                            value="%{weight}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                            value="%{shipmentTypeId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                            value="%{profileId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                            value="%{content}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                            value="%{bound}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                            value="%{serviceId}"
                                                                            data-index="%{#global_index}"/>
                                                                </div>
                                                            </div>
                                                        </s:iterator>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- TNT Intl -->
                                    <div id="cust_profile_base_rates_tnt_intl_tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                                <!-- Print Rate Sheet -->
                                                <div class="form-group">
                                                    <table class="s36">
                                                        <tr>
                                                            <td><xms:localization text="Print Rate Sheets"/></td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckAll($(this))">
                                                                    <xms:localization text="Check All"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckNone($(this))">
                                                                    <xms:localization text="Check None"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheet($(this))">
                                                                    <xms:localization text="Print Checked Rate Sheets"/>
                                                                </button>
                                                            </td>
                                                            <td><input type="checkbox" data-type="pdf-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Pdf"/></td>
                                                            <td><input type="checkbox" data-type="excel-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Excel"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <!-- Service Type List -->
                                                <s:iterator value="tntIntl" var="sv">
                                                    <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                        <s:iterator value="custProfileBaseRates" status="stats">
                                                            <s:set var="global_index" value="%{#global_index + 1}"/>
                                                            <!-- Customer base rate -->
                                                            <div class="form-group base-rate-row"
                                                                 data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                                 data-weight="<s:property value="weight"/>">
                                                                <div class="base-rate">
                                                                    <div class="well well-sm pull-left c33"
                                                                         style="width: 100%">
                                                                        <div class="pull-left c32"
                                                                             style="padding: 5px; width: 250px;">
                                                                            <s:if test="#stats.index == 0">
                                                                                <span class="br-display-name"><s:property
                                                                                        value="displayName"/></span>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <s:if test="#stats.index == 0">
                                                                                <input data-type="print-rate-sheet-checkbox"
                                                                                       type="checkbox"
                                                                                       data-customercode="<s:property value="profileId" />"
                                                                                       data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                                       data-content="<s:property value="%{#sv.content}" />"
                                                                                       data-bound="<s:property value="%{#sv.bound}" />"
                                                                                       data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                                       style="margin-top: 6px;"/>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-remove"
                                                                             <s:if test="custProfileBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-remove"><i
                                                                                    onclick="removeWeightBreak($(this))"
                                                                                    style="color: red; font-size: 18px; padding-top: 3px;"
                                                                                    class="fa fa-times s10 b3"></i></span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-weight"
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-weight"><s:property
                                                                                    value="weight"/>+</span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <i onclick="addWeightBreak($(this))"
                                                                               id="dwed-link"
                                                                               class="fa fa-chevron-circle-right s10 b3"
                                                                               style="font-size: 18px; padding-top: 3px;"
                                                                               data-index="<s:property value="#global_index"/>"></i>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="sel-rate-type">
                                                                            <s:select data-index="%{#global_index}"
                                                                                      name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                                      list="rateTypes" listKey="id"
                                                                                      listValue="name"
                                                                                      value="%{rateType}"
                                                                                      cssClass="form-control"/>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="base-rate">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        data-group="br-rate"/>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        readonly="true"
                                                                                        data-group="br-rate"/>
                                                                            </s:else>
                                                                        </div>
                                                                        <div class="pull-left c32a">%</div>
                                                                        <div class="pull-left c32a">
                                                                            <a href="javascript:void(0)"
                                                                               onclick="viewRateSheet('<s:property
                                                                                       value="profileId"/>',<s:property
                                                                                       value="%{#sv.shipmentTypeId}"/>,
                                                                                   <s:property value="%{#sv.content}"/>,
                                                                                   <s:property value="%{#sv.bound}"/>,
                                                                                   <s:property
                                                                                           value="%{#sv.serviceId}"/>)"
                                                                               class="b19"
                                                                               data-sheet-id="<s:property value="rateSheetId"/>"
                                                                               data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                               data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                               data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                                    text="View"/></b></u></a>
                                                                        </div>
                                                                        <div class="pull-left c32a">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-plus-square s10 b3"
                                                                                   data-show="close"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-minus-square s10 b3"
                                                                                   data-show="open"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:else>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!-- Customer base rate detail -->
                                                                <div class="form-group pull-left base-rate"
                                                                     style="width: 100%; overflow: auto; <s:if
                                                                             test="zoneCheck=='false'">display: none;</s:if>">
                                                                    <ul class="c36"
                                                                        style="width: 14324px; overflow: hidden;">
                                                                        <li class="pull-left c35"
                                                                            style="margin-left: 40px;"><s:checkbox
                                                                                data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                        <li class="pull-left c35" style="padding: 5px;">
                                                                            <xms:localization text="By Zone:"/></li>
                                                                        <s:iterator value="custProfileBaseRateDetails"
                                                                                    status="dStats">
                                                                            <li>
                                                                                <div class="pull-left c34"
                                                                                     data-group="zone">
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].zone"
                                                                                           class="form-control alloptions text-center"
                                                                                           maxlength="25"
                                                                                           value="<s:property value="zone"/>"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           <s:else>readonly</s:else>
                                                                                           type="text"
                                                                                           data-group="zone-name"/>
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].rate"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           class="form-control alloptions text-center"
                                                                                           type="text" maxlength="25"
                                                                                           value="<s:property value="rate"/>"
                                                                                           data-group="zone-rate">
                                                                                </div>
                                                                            </li>
                                                                        </s:iterator>
                                                                    </ul>
                                                                </div>
                                                                <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                            value="%{displayName}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                            value="%{weight}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                            value="%{shipmentTypeId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                            value="%{profileId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                            value="%{content}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                            value="%{bound}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                            value="%{serviceId}"
                                                                            data-index="%{#global_index}"/>
                                                                </div>
                                                            </div>
                                                        </s:iterator>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Toll priority -->
                                    <div id="cust_profile_base_rates_toll_priority_tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="portlet-body b12 b11">
                                                <!-- Service Type List -->
                                                <s:iterator value="tollPriority">
                                                    <div>
                                                        <s:iterator value="custProfileBaseRates" status="stats">
                                                            <s:set var="global_index" value="%{#global_index + 1}"/>
                                                            <!-- Customer base rate -->
                                                            <div class="form-group base-rate-row"
                                                                 data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                                 data-weight="<s:property value="weight"/>">
                                                                <div class="base-rate">
                                                                    <div class="well well-sm pull-left c33"
                                                                         style="width: 100%">
                                                                        <div class="pull-left c32"
                                                                             style="padding: 5px; width: 250px;">
                                                                            <s:if test="#stats.index == 0">
                                                                                <span class="br-display-name"><s:property
                                                                                        value="displayName"/></span>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="sel-rate-type">
                                                                            <s:select data-index="%{#global_index}"
                                                                                      name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                                      list="rateTypes" listKey="id"
                                                                                      listValue="name"
                                                                                      value="%{rateType}"
                                                                                      cssClass="form-control"/>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <s:textfield data-index="%{#global_index}"
                                                                                         name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                         value="%{rate}"
                                                                                         cssClass="form-control alloptions"
                                                                                         maxlength="25"
                                                                                         cssStyle="width: 50px;"
                                                                                         data-group="br-rate"/>
                                                                        </div>
                                                                        <div class="pull-left c32a">%</div>
                                                                    </div>
                                                                </div>
                                                                <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                            value="%{displayName}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                            value="%{weight}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                            value="%{shipmentTypeId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                            value="%{profileId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                            value="%{content}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                            value="%{bound}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                            value="%{serviceId}"
                                                                            data-index="%{#global_index}"/>
                                                                </div>
                                                            </div>
                                                        </s:iterator>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Toll Ipec -->
                                    <div id="cust_profile_base_rates_toll_ipec_tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="portlet-body b12 b11">
                                                <!-- Service Type List -->
                                                <s:iterator value="tollIpec">
                                                    <div>
                                                        <s:iterator value="custProfileBaseRates" status="stats">
                                                            <s:set var="global_index" value="%{#global_index + 1}"/>
                                                            <!-- Customer base rate -->
                                                            <div class="form-group base-rate-row"
                                                                 data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                                 data-weight="<s:property value="weight"/>">
                                                                <div class="base-rate">
                                                                    <div class="well well-sm pull-left c33"
                                                                         style="width: 100%">
                                                                        <div class="pull-left c32"
                                                                             style="padding: 5px; width: 250px;">
                                                                            <s:if test="#stats.index == 0">
                                                                                <span class="br-display-name"><s:property
                                                                                        value="displayName"/></span>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="sel-rate-type">
                                                                            <s:select data-index="%{#global_index}"
                                                                                      name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                                      list="rateTypes" listKey="id"
                                                                                      listValue="name"
                                                                                      value="%{rateType}"
                                                                                      cssClass="form-control"/>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <s:textfield data-index="%{#global_index}"
                                                                                         name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                         value="%{rate}"
                                                                                         cssClass="form-control alloptions"
                                                                                         maxlength="25"
                                                                                         cssStyle="width: 50px;"
                                                                                         data-group="br-rate"/>
                                                                        </div>
                                                                        <div class="pull-left c32a">%</div>
                                                                    </div>
                                                                </div>
                                                                <!-- Customer base rate detail -->
                                                                <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                            value="%{displayName}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                            value="%{weight}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                            value="%{shipmentTypeId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                            value="%{profileId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                            value="%{content}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                            value="%{bound}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                            value="%{serviceId}"
                                                                            data-index="%{#global_index}"/>
                                                                </div>
                                                            </div>
                                                        </s:iterator>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Star Track -->
                                    <div id="cust_profile_base_rates_star_track_tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                                <!-- Orgin -->
                                                <div class="form-group">
                                                    <table class="s36">
                                                        <tr>
                                                            <td><xms:localization text="Origin"/></td>
                                                            <td><s:select id="starTrackColumnName"
                                                                          cssClass="form-control"
                                                                          list="starTrackColumns"
                                                                          listValue="zoneName + ' - ' + zoneCode"
                                                                          listKey="zoneCode"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <!-- Print Rate Sheet -->
                                                <div class="form-group">
                                                    <table class="s36">
                                                        <tr>
                                                            <td><xms:localization text="Print Rate Sheets"/></td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckAll($(this))">
                                                                    <xms:localization text="Check All"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheetCheckNone($(this))">
                                                                    <xms:localization text="Check None"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="printRateSheet($(this))">
                                                                    <xms:localization text="Print Checked Rate Sheets"/>
                                                                </button>
                                                            </td>
                                                            <td><input type="checkbox" data-type="pdf-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Pdf"/></td>
                                                            <td><input type="checkbox" data-type="excel-format"
                                                                       onchange="rateSheetFormatChange($(this))"/></td>
                                                            <td><xms:localization text="Excel"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <!-- Service Type List -->
                                                <s:iterator value="starTrack" var="sv">
                                                    <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                        <s:iterator value="custProfileBaseRates" status="stats">
                                                            <s:set var="global_index" value="%{#global_index + 1}"/>
                                                            <!-- Customer base rate -->
                                                            <div class="form-group base-rate-row"
                                                                 data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                                 data-weight="<s:property value="weight"/>">
                                                                <div class="base-rate">
                                                                    <div class="well well-sm pull-left c33"
                                                                         style="width: 100%">
                                                                        <div class="pull-left c32"
                                                                             style="padding: 5px; width: 250px;">
                                                                            <s:if test="#stats.index == 0">
                                                                                <span class="br-display-name"><s:property
                                                                                        value="displayName"/></span>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <s:if test="#stats.index == 0">
                                                                                <input data-type="print-rate-sheet-checkbox"
                                                                                       type="checkbox"
                                                                                       data-customercode="<s:property value="profileId" />"
                                                                                       data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                                       data-content="<s:property value="%{#sv.content}" />"
                                                                                       data-bound="<s:property value="%{#sv.bound}" />"
                                                                                       data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                                       style="margin-top: 6px;"/>
                                                                            </s:if>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-remove"
                                                                             <s:if test="custProfileBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-remove"><i
                                                                                    onclick="removeWeightBreak($(this))"
                                                                                    style="color: red; font-size: 18px; padding-top: 3px;"
                                                                                    class="fa fa-times s10 b3"></i></span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="br-weight"
                                                                             <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                            <a class="b18"> <b><span
                                                                                    data-group="br-weight"><s:property
                                                                                    value="weight"/>+</span></b>
                                                                            </a>
                                                                        </div>
                                                                        <div class="pull-left c32">
                                                                            <i onclick="addWeightBreak($(this))"
                                                                               id="dwed-link"
                                                                               class="fa fa-chevron-circle-right s10 b3"
                                                                               style="font-size: 18px; padding-top: 3px;"
                                                                               data-index="<s:property value="#global_index"/>"></i>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="sel-rate-type">
                                                                            <s:select data-index="%{#global_index}"
                                                                                      name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                                      list="rateTypes" listKey="id"
                                                                                      listValue="name"
                                                                                      value="%{rateType}"
                                                                                      cssClass="form-control"/>
                                                                        </div>
                                                                        <div class="pull-left c32"
                                                                             data-group="base-rate">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        data-group="br-rate"/>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <s:textfield
                                                                                        data-index="%{#global_index}"
                                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                        value="%{rate}"
                                                                                        cssClass="form-control alloptions"
                                                                                        maxlength="25"
                                                                                        cssStyle="width: 50px;"
                                                                                        readonly="true"
                                                                                        data-group="br-rate"/>
                                                                            </s:else>
                                                                        </div>
                                                                        <div class="pull-left c32a">%</div>
                                                                        <div class="pull-left c32a">
                                                                            <a href="javascript:void(0)"
                                                                               onclick="viewRateSheet('<s:property
                                                                                       value="profileId"/>',<s:property
                                                                                       value="%{#sv.shipmentTypeId}"/>,
                                                                                   <s:property value="%{#sv.content}"/>,
                                                                                   <s:property value="%{#sv.bound}"/>,
                                                                                   <s:property
                                                                                           value="%{#sv.serviceId}"/>)"
                                                                               class="b19"
                                                                               data-sheet-id="<s:property value="rateSheetId"/>"
                                                                               data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                               data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                               data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                                    text="View"/></b></u></a>
                                                                        </div>
                                                                        <div class="pull-left c32a">
                                                                            <s:if test="zoneCheck == 'false'">
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-plus-square s10 b3"
                                                                                   data-show="close"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <i id="show-zones"
                                                                                   onclick="showZones($(this))"
                                                                                   class="fa fa-minus-square s10 b3"
                                                                                   data-show="open"
                                                                                   style="font-size: 18px;"></i>
                                                                            </s:else>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!-- Customer base rate detail -->
                                                                <div class="form-group pull-left base-rate"
                                                                     style="width: 100%; overflow: auto; <s:if
                                                                             test="zoneCheck=='false'">display: none;</s:if>">
                                                                    <ul class="c36"
                                                                        style="width: 14324px; overflow: hidden;">
                                                                        <li class="pull-left c35"
                                                                            style="margin-left: 40px;"><s:checkbox
                                                                                data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                        <li class="pull-left c35" style="padding: 5px;">
                                                                            <xms:localization text="By Zone:"/></li>
                                                                        <s:iterator value="custProfileBaseRateDetails"
                                                                                    status="dStats">
                                                                            <li>
                                                                                <div class="pull-left c34"
                                                                                     data-group="zone">
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].zone"
                                                                                           class="form-control alloptions text-center"
                                                                                           maxlength="25"
                                                                                           value="<s:property value="zone"/>"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           <s:else>readonly</s:else>
                                                                                           type="text"
                                                                                           data-group="zone-name"/>
                                                                                    <input data-index="<s:property value="#global_index"/>"
                                                                                           name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].rate"
                                                                                           <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                           class="form-control alloptions text-center"
                                                                                           type="text" maxlength="25"
                                                                                           value="<s:property value="rate"/>"
                                                                                           data-group="zone-rate">
                                                                                </div>
                                                                            </li>
                                                                        </s:iterator>
                                                                    </ul>
                                                                </div>
                                                                <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                            value="%{displayName}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                            value="%{weight}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                            value="%{shipmentTypeId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                            value="%{profileId}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                            value="%{content}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                            value="%{bound}"
                                                                            data-index="%{#global_index}"/>
                                                                    <s:hidden
                                                                            name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                            value="%{serviceId}"
                                                                            data-index="%{#global_index}"/>
                                                                </div>
                                                            </div>
                                                        </s:iterator>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Others -->
                                    <div id="cust_profile_base_rates_others_tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="portlet-body b12 b11">
                                                <!-- Service Type List -->
                                                <s:iterator value="others">
                                                    <div>
                                                        <s:set var="global_index" value="%{#global_index + 1}"/>
                                                        <!-- Customer base rate -->
                                                        <div class="form-group base-rate-row"
                                                             data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                             data-weight="<s:property value="weight"/>">
                                                            <div class="base-rate">
                                                                <div class="well well-sm pull-left c33"
                                                                     style="width: 100%">
                                                                    <div class="pull-left c32"
                                                                         style="padding: 5px; width: 250px;">
                                                                        <s:property value="serviceName"/>
                                                                    </div>
                                                                    <div class="pull-left c32">
                                                                        <s:if test="#stats.index == 0">
                                                                            <input type="checkbox" value=""
                                                                                   style="margin-top: 6px;">
                                                                        </s:if>
                                                                    </div>
                                                                    <div class="pull-left c32"
                                                                         data-group="sel-rate-type">
                                                                        <s:select data-index="%{#global_index}"
                                                                                  name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                                  list="rateTypes" listKey="id"
                                                                                  listValue="name" value="%{rateType}"
                                                                                  cssClass="form-control"/>
                                                                    </div>
                                                                    <div class="pull-left c32">
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     data-group="br-rate"/>
                                                                    </div>
                                                                    <div class="pull-left c32a">%</div>
                                                                </div>
                                                            </div>
                                                            <!-- Customer base rate detail -->
                                                            <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                                <s:hidden
                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                        value="%{displayName}"
                                                                        data-index="%{#global_index}"/>
                                                                <s:hidden
                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                        value="%{weight}"
                                                                        data-index="%{#global_index}"/>
                                                                <s:hidden
                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                        value="%{shipmentTypeId}"
                                                                        data-index="%{#global_index}"/>
                                                                <s:hidden
                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                        value="%{profileId}"
                                                                        data-index="%{#global_index}"/>
                                                                <s:hidden
                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                        value="%{content}"
                                                                        data-index="%{#global_index}"/>
                                                                <s:hidden
                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                        value="%{bound}" data-index="%{#global_index}"/>
                                                                <s:hidden
                                                                        name="addNewCutomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                        value="%{serviceId}"
                                                                        data-index="%{#global_index}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div id="add-weight-break-comp" style="display: none;"></div>
            <div id="add-weight-dialog" title="<xms:localization text="Add Weight Break" />"
                 style="display: none;"></div>
            <div id="view_rate_sheet_dialog" title="<xms:localization text="View Rate Sheet" />"
                 style="display: none;"></div>
            <script type="text/javascript">
                var globalIndex = "<s:property value="#global_index"/>";
                function showZones($this) {
                    if ($this.data("show") == "close") {
                        $this.removeClass("fa-plus-square");
                        $this.addClass("fa-minus-square");
                        $this.parents("div.base-rate").next().show("slow");
                        $this.data("show", "open");
                    } else {
                        $this.removeClass("fa-minus-square");
                        $this.addClass("fa-plus-square");
                        $this.parents("div.base-rate").next().hide("slow");
                        $this.data("show", "close");
                    }
                }
                function onCheckZoneCheck($this, $parent) {
                    if ($this.is(":checked") == true) {
                        $parent.find("[data-group='zone-name']").each(function (i) {
                            $(this).attr("disabled", false);
                            $(this).attr("readonly", true);
                        });
                        $parent.find("[data-group='zone-rate']").each(function (i) {
                            $(this).attr("disabled", false);
                        });
                        $parent.parents("div.base-rate").prev().find("[data-group='br-rate']").attr("readonly", true);
                    } else {
                        $parent.find("[data-group='zone-name']").each(function (i) {
                            $(this).attr("disabled", true);
                            $(this).attr("readonly", false);
                        });
                        $parent.find("[data-group='zone-rate']").each(function (i) {
                            $(this).attr("disabled", "disabled");
                        });
                        $parent.parents("div.base-rate").prev().find("[data-group='br-rate']").attr("readonly", false);
                    }
                }
                function saveCustomerProfileBaseRate() {
                    var data = $("#frmSaveCustomerProfileBaseRates").serialize();
                    doPostDataByParameters("manage_cust_profile_base_rate_save.ix?reqType=json", data, "<xms:localization text="Saved successful!" />", "", true, true);
                }
                function replaceAll(str, find, replace) {
                    return str.replace(new RegExp(find, 'g'), replace);
                }
                function addWeightBreak($this) {
                    loadingDialog.dialog("open");
                    var $parent = $this.parents("div.base-rate-row");
                    var baseRateData = $parent.data("baserate");
                    var baseRateDataArr = baseRateData.split("_");
                    var shipmentId = baseRateDataArr[0];
                    var content = baseRateDataArr[1];
                    var bound = baseRateDataArr[2];
                    var currentWeight = $parent.data("weight");
                    var nextWeight = $parent.next().data("weight");
                    var index = $this.data("index");
                    var displayName = $parent.find("span.br-display-name").html();
                    var html = $parent.html();
                    var data = {
                        "weightBreakModel.htmlString": html,
                        "weightBreakModel.globalIndex": globalIndex,
                        "weightBreakModel.currentIndex": index,
                        "weightBreakModel.displayName": displayName,
                        "weightBreakModel.baseRateData": baseRateData,
                        "weightBreakModel.currentWeight": currentWeight,
                        "weightBreakModel.nextWeight": nextWeight
                    };
                    var buttons = {};
                    buttons["<xms:localization text="Save" />"] = function () {
                        loadingDialog.dialog("open");
                        var dataForm = $('#form-add-weight-break').serialize();
                        $.post("add_weight_break_do_add_profile.ix?reqType=json", dataForm, function (res) {
                            loadingDialog.dialog("close");
                            if (res.errorCode == "SUCCESS") {
                                $parent.after(res.content);
                                $parent.parent("div").find("div[data-group='br-weight']").show();
                                $parent.parent("div").find("div[data-group='br-remove-break']").show();
                                dialog.dialog("close");
                                globalIndex++;
                            } else if (res.errorCode == "FIELD_ERROR") {
                                dialog.html(res.content);
                            } else if (res.errorCode == "ACTION_ERROR") {
                                alertDialog.html(res.errorMsg);
                                alertDialog.dialog("open");
                            }
                        }).fail(function () {
                            loadingDialog.dialog("close");
                            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                            alertDialog.dialog("open");
                        });
                    };
                    buttons["<xms:localization text="Cancel" />"] = function () {
                        $(this).dialog("close");
                    }
                    var height = $(window).height();
                    var width = $(window).width();
                    height = height * 0.70;
                    width = width * 0.9;
                    var dialog = $("#add-weight-dialog").dialog({
                        modal: true,
                        autoOpen: false,
                        width: "auto",
                        buttons: buttons,
                        width: 'auto',
                        height: 'auto',
                        maxHeight: height,
                        create: function (event, ui) {
                            $(this).css("maxWidth", width);
                        },
                        show: {
                            effect: "fade",
                            duration: 300
                        },
                        close: function (e) {
                            $("#add-weight-dialog").html("");
                        }
                    });
                    $.post("add_weight_break.ix?reqType=json", data, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            dialog.html(res.content);
                            dialog.dialog("open");
                        } else {
                            alertDialog.html('Error: ' + res.errorMsg);
                            alertDialog.dialog("open");
                        }
                    }).fail(function () {
                        loadingDialog.dialog("close");
                        alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                        alertDialog.dialog("open");
                    });
                }
                function removeWeightBreak($this) {
                    $parent = $this.parents("div.base-rate-row");
                    var count = $parent.parent("div").find("div.base-rate-row").length;
                    count = count - 1;
                    if (count == 1) {
                        $parent.parent("div").find("div[data-group='br-weight']").hide();
                    }
                    $parent.remove();

                }
                function viewRateSheet(customerCode, shipmentTypeId, content, bound, serviceId) {
                    var request = getRateSheetRequest(customerCode, shipmentTypeId, content, bound, serviceId);
                    $.post("view_rate_sheet.ix?reqType=json", "viewRequest=" + request, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            var dialog = $("#view_rate_sheet_dialog").dialog({
                                modal: true,
                                autoOpen: false,
                                width: "90%",
                                height: 'auto',
                                position: {my: "top", at: "top+50"},
                                show: {
                                    effect: "fade",
                                    duration: 300
                                }
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

                function getRateSheetRequest(customerCode, shipmentTypeId, content, bound, serviceId) {
                    var columnName = "";
                    if (serviceId == 3) {
                        columnName = $("#tntDomColumnName option:selected").val();
                    } else if (serviceId == 72) {
                        columnName = $("#starTrackColumnName option:selected").val();
                    }
                    var stId = "#shipment_type_" + shipmentTypeId + "_" + content + "_" + bound;
                    var request = "{";
                    // Type=2 for view customer profile base rate.
                    request += "\"type\":2,";
                    request += "\"customerCode\":\"" + customerCode + "\",";
                    request += "\"shipmentTypeId\":" + shipmentTypeId + ",";
                    request += "\"content\":" + content + ",";
                    request += "\"bound\":" + bound + ",";
                    request += "\"minimumBaseCharge\":" + $("#minimum_base_charge").val() + ",";
                    if (columnName != "") {
                        request += "\"columnName\":\"" + columnName + "\",";
                    }
                    request += "\"baseRates\":[";
                    $(stId).find("div[data-baserate]").each(function () {
                        request += "{";
                        // Get weight.
                        var weightText = $(this).find("span[data-group='br-weight']").html();
                        var weight = 0;
                        if (weightText == "") {
                            weight = 0.0;
                        } else {
                            weight = parseFloat(weightText.substring(0, weightText.length - 1));
                        }
                        // Get rate type.
                        var rateType = $(this).find("div[data-group='sel-rate-type']>select option:selected").val();
                        // Get rate.
                        var rate = $(this).find("div[data-group='base-rate']>input").val();
                        request += "\"weight\":" + weight + ",";
                        request += "\"rateType\":" + rateType + ",";
                        request += "\"rate\":" + rate + ",";
                        // Get zone check.
                        var zoneCheck = $(this).find("input[data-group='zone-check']").is(":checked");
                        if (zoneCheck) {
                            var zones = "{";
                            $(this).find("div[data-group='zone']").each(function () {
                                var zoneName = $(this).find("input[data-group='zone-name']").val();
                                var zoneRate = $(this).find("input[data-group='zone-rate']").val();
                                zones += "\"" + zoneName + "\":" + zoneRate + ",";
                            });
                            if (zones.charAt(zones.length - 1) == ',') {
                                zones = zones.substring(0, zones.length - 1);
                            }
                            zones += "}";
                            request += "\"zoneRates\":" + zones;
                        } else {
                            request = request.substring(0, request.length - 1);
                        }
                        request += "},";
                    });
                    if (request.charAt(request.length - 1) == ',') {
                        request = request.substring(0, request.length - 1);
                    }
                    request += "]}";
                    return request;
                }

                function printRateSheetCheckAll(obj) {
                    var topDiv = $(obj).closest("div[data-type='print-rate-sheet']");
                    $(topDiv).find("input[data-type='print-rate-sheet-checkbox']").each(function () {
                        $(this).attr("checked", true);
                    });
                }

                function printRateSheetCheckNone(obj) {
                    var topDiv = $(obj).closest("div[data-type='print-rate-sheet']");
                    $(topDiv).find("input[data-type='print-rate-sheet-checkbox']").each(function () {
                        $(this).attr("checked", false);
                    });
                }

                function printRateSheet(obj) {
                    var topDiv = $(obj).closest("div[data-type='print-rate-sheet']");
                    var pdfCheck = $(topDiv).find("input[data-type='pdf-format']").is(":checked");
                    var excelCheck = $(topDiv).find("input[data-type='excel-format']").is(":checked");
                    var selected = false;
                    $(topDiv).find("input[data-type='print-rate-sheet-checkbox']").each(function () {
                        if ($(this).is(":checked")) {
                            selected = true;
                            return false;
                        }
                    });
                    if (!selected) {
                        alertDialog.html("<xms:localization text="Please select shipment type to print." />");
                        alertDialog.dialog("open");
                    } else {
                        // Prepare data for print rate sheet request.
                        var index = 0;
                        var result = "";
                        $(topDiv).find("input[data-type='print-rate-sheet-checkbox']").each(function () {
                            if ($(this).is(":checked")) {
                                var customerCode = $(this).attr("data-customercode");
                                var shipmentTypeId = $(this).attr("data-shipmenttypeid");
                                var content = $(this).attr("data-content");
                                var bound = $(this).attr("data-bound");
                                var serviceId = $(this).attr("data-serviceid");
                                var request = getRateSheetRequest(customerCode, shipmentTypeId, content, bound, serviceId);
                                result += "listViewRequest[" + index + "]=" + request + "&";
                                index++;
                            }
                        });
                        result = result.substring(0, result.length - 1);
                        // Get print option.
                        var excelCheck = $(topDiv).find("input[data-type='excel-format']");
                        var pdfCheck = $(topDiv).find("input[data-type='pdf-format']");
                        var printType = 0;
                        if (pdfCheck.is(":checked")) {
                            printType = 1;
                        }
                        if (excelCheck.is(":checked")) {
                            printType = 2;
                        }
                        switch (printType) {
                            case 0:
                                exportRateSheet2Html(result);
                                break;
                            case 1:
                                exportRateSheet2Pdf(result);
                                break;
                            case 2:
                                exportRateSheet2Excel(result);
                                break;
                        }
                    }
                }

                function exportRateSheet2Pdf(listRequest) {
                    loadingDialog.dialog("open");
                    $.post("rate_sheet_export_to_pdf.ix?reqType=json", listRequest, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            window.alert("Export rate sheet to PDF");
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

                function exportRateSheet2Excel(listRequest) {
                    loadingDialog.dialog("open");
                    $.post("rate_sheet_export_to_excel.ix?reqType=json", listRequest, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            window.alert("Export rate sheet to Excel");
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

                function exportRateSheet2Html(listRequest) {
                    loadingDialog.dialog("open");
                    $.post("rate_sheet_export_to_html.ix?reqType=json", listRequest, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            window.alert("Export rate sheet to HTML");
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

                function rateSheetFormatChange(obj) {
                    if ($(obj).is(":checked")) {
                        var topDiv = $(obj).closest("div[data-type='print-rate-sheet']");
                        if ($(obj).attr("data-type") == "pdf-format") {
                            var excelCheck = $(topDiv).find("input[data-type='excel-format']");
                            excelCheck.attr("checked", false);
                        } else {
                            var pdfCheck = $(topDiv).find("input[data-type='pdf-format']");
                            pdfCheck.attr("checked", false);
                        }
                    }
                }
            </script>
        </div>
        <div id="Invoice-tab" class="tab-pane fade in">
            <div class="row">
                <div class="portlet-body b12 b11 col-md-4">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td style="border-top: 0px !important" colspan="3">
                                <div class="caption b17">
                                    <xms:localization text="Invoicing Options"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Invoice Sorting"/></td>
                            <td colspan="2" class="td2"><s:select class="form-control" headerKey="" headerValue=""
                                                                  list="invoiceSortingOptions"
                                                                  name="addNewCutomerProfile.customerProfile.invoiceSorting"
                                                                  value="%{cusProfile.invoiceSorting}" listValue="name"
                                                                  listKey="id"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Invoice Terms"/></td>
                            <td colspan="2" class="td2"><s:select class="form-control" headerKey="" headerValue=""
                                                                  list="invoiceTerms"
                                                                  name="addNewCutomerProfile.customerProfile.invoiceTerms"
                                                                  value="%{cusProfile.invoiceTerms}" listValue="days"
                                                                  listKey="invoiceTermId"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Invoice to Customer"/></td>
                            <td colspan="2" class="td2"><s:select class="form-control" headerKey="" headerValue=""
                                                                  list="invoiceToCustomers"
                                                                  name="addNewCutomerProfile.customerProfile.invoiceToCustomerId"
                                                                  value="%{cusProfile.invoiceToCustomerId}"
                                                                  listValue="customerCode" listKey="id"
                                                                  onkeypress="return formartNumber(event,this,false);"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Pickup Fee"/></td>
                            <td colspan="2" class="td2"><s:select class="form-control" headerKey="" headerValue=""
                                                                  list="pickupFees"
                                                                  name="addNewCutomerProfile.customerProfile.pickupFee"
                                                                  value="%{cusProfile.pickupFee}" listValue="name"
                                                                  listKey="id">
                            </s:select></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Invoice Late Fee"/> % (e.g. 10)</td>
                            <td width="60" class="td2"><s:textfield class="form-control"
                                                                    name="addNewCutomerProfile.customerProfile.invoiceLateFee"
                                                                    value="%{cusProfile.invoiceLateFee}"
                                                                    onkeypress="return formartNumber(event,this,false);"/></td>
                            <td class="td2">%</td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Download .CSV Invoice"/></td>
                            <td colspan="2" class="td2"><s:checkbox
                                    name="addNewCutomerProfile.customerProfile.downloadCsvInvoice"
                                    value="%{cusProfile.downloadCsvInvoice}"/></td>
                        </tr>
                        <tr>
                            <td class="td1">*<xms:localization text="E-mail Invoice"/></td>
                            <td colspan="2" class="td2"><s:checkbox
                                    name="addNewCutomerProfile.customerProfile.emailInvoice"
                                    value="%{cusProfile.emailInvoice}"/></td>
                        </tr>
                        <tr>
                            <td colspan="3">* = <xms:localization
                                    text="Requires valid e-mail listed in Billing Address under Address tab."/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="div_save_invoice_option_result"></div>
            <script type="text/javascript">
                function saveCustomerProfileInvoiceOptions() {
                    var data = $("#saveCustomerProfileInvoiceOption").serialize();
                    loadingDialog.dialog("open");
                    $.post("manage_customer_profile_invoice_options_save.ix?reqType=json", data, function (res) {
                        loadingDialog.dialog("close");
                        dialog.html(res.content);
                        dialog.dialog("open");
                    }).fail(function () {
                        loadingDialog.dialog("close");
                        alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                        alertDialog.dialog("open");
                    });

                    var buttons = {};
                    var height = $(window).height();
                    var width = $(window).width();
                    height = height * 0.70;
                    width = width * 0.9;
                    var dialog = $("#div_save_invoice_option_result").dialog({
                        modal: true,
                        autoOpen: false,
                        width: "auto",
                        buttons: buttons,
                        width: 'auto',
                        height: 'auto',
                        maxHeight: height,
                        create: function (event, ui) {
                            $(this).css("maxWidth", width);
                        },
                        show: {
                            effect: "fade",
                            duration: 300
                        },
                        close: function (e) {
                            $("#div_save_invoice_option_result").html("");
                        }
                    });
                    dialog.dialog("option", "title", "Save Invoice Options.");
                }
            </script>
        </div>
        <div id="Collections-tab" class="tab-pane fade in">
            <s:hidden name="addNewCutomerProfile.collection.userType" value="3"></s:hidden>
            <div class="row">
                <div class="portlet-body b12 b11">
                    <div class="portlet-body b22" style="padding: 0px;">
                        <ul id="generalTab1" class="nav nav-tabs responsive">
                            <li class="active" style="margin-left: 10px;"><a href="#9-tab"
                                                                             data-toggle="tab"><xms:localization
                                    text="Freight"/></a></li>
                            <li><a href="#10-tab" data-toggle="tab" class="tb3"><xms:localization
                                    text="Reminder Letters"/></a></li>
                        </ul>
                        <div class="tab-content responsive">
                            <div id="9-tab" class="tab-pane fade in tb2 active">
                                <div class="row">
                                    <div class="portlet-body b12 b11">
                                        <table class="s36">
                                            <tbody>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td class="caption b17" colspan="2"><xms:localization
                                                        text="Credit Limits"/></td>
                                            </tr>
                                            <TR>
                                                <td colspan="3" height="5"></td>
                                            </TR>
                                            <tr>
                                                <td><xms:localization text="Freight Credit Limits:"/></td>
                                                <td width="90"><s:textfield class="form-control"
                                                                            name="addNewCutomerProfile.collection.freightCreditLimit"
                                                                            value="%{collectionModel.freightCreditLimit}"
                                                                            onkeypress="return formartNumber(event,this,false);"></s:textfield></td>
                                                <td>$</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div id="10-tab" class="tab-pane fade in">
                                <div class="row">
                                    <div class="portlet-body b12 b11">
                                        <table class="s36">
                                            <tbody>
                                            <tr>
                                                <td class="caption b17" colspan="9"><xms:localization
                                                        text="Credit Limits"/></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="10" height="5"></td>
                                            </tr>
                                            <tr>
                                                <td><s:checkbox name="addNewCutomerProfile.collection.reminder"
                                                                value="%{collectionModel.reminder}"/></td>
                                                <td><xms:localization text="Send"/></td>
                                                <td><s:checkbox name="addNewCutomerProfile.collection.reminderEmail"
                                                                value="%{collectionModel.reminderEmail}"/></td>
                                                <td><xms:localization text="Email"/></td>
                                                <td><s:checkbox name="addNewCutomerProfile.collection.reminderPrint"
                                                                value="%{collectionModel.reminderPrint}"/></td>
                                                <td><xms:localization text="Print"/></td>
                                                <td><s:checkbox
                                                        name="addNewCutomerProfile.collection.reminderUseEmailInvoice"
                                                        value="%{collectionModel.reminderUseEmailInvoice}"/></td>
                                                <td><xms:localization text="Use Email Inv. AddressInv."/></td>
                                                <td><s:textfield class="form-control"
                                                                 name="addNewCutomerProfile.collection.reminderEmailAddress"
                                                                 value="%{collectionModel.reminderEmailAddress}"/></td>
                                                <td><xms:localization text="Other Email Address(es)"/></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="div_save_collection_result"></div>
            <script type="text/javascript">
                function saveCustomerProfileCollection() {
                    var data = $("#saveCustomerProfileCollectionForm").serialize();
                    loadingDialog.dialog("open");
                    $.post("manage_customer_profile_collection_save.ix?reqType=json", data, function (res) {
                        loadingDialog.dialog("close");
                        dialog.html(res.content);
                        dialog.dialog("open");
                    }).fail(function () {
                        loadingDialog.dialog("close");
                        alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                        alertDialog.dialog("open");
                    });

                    var buttons = {};
                    var height = $(window).height();
                    var width = $(window).width();
                    height = height * 0.70;
                    width = width * 0.9;
                    var dialog = $("#div_save_collection_result").dialog({
                        modal: true,
                        autoOpen: false,
                        width: "auto",
                        buttons: buttons,
                        width: 'auto',
                        height: 'auto',
                        maxHeight: height,
                        create: function (event, ui) {
                            $(this).css("maxWidth", width);
                        },
                        show: {
                            effect: "fade",
                            duration: 300
                        },
                        close: function (e) {
                            $("#div_save_collection_result").html("");
                        }
                    });
                    dialog.dialog("option", "title", "<xms:localization text="Save Collection." />");
                }
            </script>
        </div>
    </div>
</s:form>