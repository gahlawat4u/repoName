<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

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
                        <li class="active" style="margin-left: 10px;">
                            <a href="#cust_profile_base_rates_general_tab" data-toggle="tab"><xms:localization
                                    text="General"/></a>
                        </li>
                        <li>
                            <a href="#cust_profile_base_rates_dhl_tab" data-toggle="tab" class="tb3"><xms:localization
                                    text="DHL"/></a>
                        </li>
                        <li>
                            <a href="#cust_profile_base_rates_dhl_dom_tab" data-toggle="tab"
                               class="tb3"><xms:localization text="DHL Domestic"/></a>
                        </li>
                        <li>
                            <a href="#cust_profile_base_rates_tnt_dom_tab" data-toggle="tab"
                               class="tb3"><xms:localization text="TNT Domestic"/></a>
                        </li>
                        <li>
                            <a href="#cust_profile_base_rates_tnt_intl_tab" data-toggle="tab"
                               class="tb3"><xms:localization text="TNT International"/></a>
                        </li>
                        <li>
                            <a href="#cust_profile_base_rates_toll_priority_tab" data-toggle="tab"
                               class="tb3"><xms:localization text="Toll Priority"/></a>
                        </li>
                        <li>
                            <a href="#cust_profile_base_rates_toll_ipec_tab" data-toggle="tab"
                               class="tb3"><xms:localization text="Toll Ipec"/></a>
                        </li>
                        <li>
                            <a href="#cust_profile_base_rates_others_tab" data-toggle="tab"
                               class="tb3"><xms:localization text="Others"/></a>
                        </li>
                    </ul>
                    <div id="cust_profile_base_rates_tab_content" class="tab-content responsive">
                        <div id="cust_profile_base_rates_general_tab" class="tab-pane fade in tb2 active">
                            <div class="row">
                                <div class="portlet-body b12 b11">
                                    <s:hidden name="saveCustomerProfile.saveCustProfileBaseRate.profileId"
                                              value="%{profile.profileId}"/>
                                    <table class="s36">
                                        <tbody>
                                        <tr>
                                            <td>* = <xms:localization text="May override other settings"/></td>
                                            <td class="caption b17" colspan="2"><xms:localization
                                                    text="Base Rates"/></td>
                                        </tr>
                                        <TR>
                                            <td colspan="3" height="5"></td>
                                        </TR>
                                        <tr>
                                            <td>* <xms:localization text="Minimum Customer Base Charge Margin"/></td>
                                            <td width="60"><s:textfield id="minimum_base_charge"
                                                                        name="saveCustomerProfile.saveCustProfileBaseRate.minimunBaseCharge"
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
                                <div class="portlet-body b12 b11">
                                    <!-- Print Rate Sheet -->
                                    <div class="form-group">
                                        <table class="s36">
                                            <tr>
                                                <td><xms:localization text="Print Rate Sheets"/></td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Check All"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Check None"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Print Checked Rate Sheets"/>
                                                    </button>
                                                </td>
                                                <td><input type="checkbox" value=""></td>
                                                <td><xms:localization text="Pdf"/></td>
                                                <td><input type="checkbox" value=""></td>
                                                <td><xms:localization text="Excel"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <!-- Service Type List -->
                                    <s:iterator value="dhl">
                                        <div>
                                            <s:iterator value="custProfileBaseRates" status="stats">
                                                <s:set var="global_index" value="%{#global_index + 1}"/>
                                                <!-- Customer base rate -->
                                                <div class="form-group base-rate-row"
                                                     data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                     data-weight="<s:property value="weight"/>">
                                                    <div class="base-rate">
                                                        <div class="well well-sm pull-left c33" style="width: 100%">
                                                            <div class="pull-left c32"
                                                                 style="padding: 5px; width: 250px;">
                                                                <s:if test="#stats.index == 0">
                                                                    <span class="br-display-name"><s:property
                                                                            value="displayName"/></span>
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:if test="#stats.index == 0">
                                                                    <input type="checkbox" value=""
                                                                           style="margin-top: 6px;">
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32" data-group="br-remove"
                                                                 <s:if test="custProfileBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                 <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                <a class="b18"> <b><span data-group="br-remove"><i
                                                                        onclick="removeWeightBreak($(this))"
                                                                        style="color: red; font-size: 18px; padding-top: 3px;"
                                                                        class="fa fa-times s10 b3"></i></span></b>
                                                                </a>
                                                            </div>
                                                            <div class="pull-left c32" data-group="br-weight"
                                                                 <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                <a class="b18"> <b><span
                                                                        data-group="br-weight"><s:property
                                                                        value="weight"/>+</span></b>
                                                                </a>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <i onclick="addWeightBreak($(this))" id="dwed-link"
                                                                   class="fa fa-chevron-circle-right s10 b3"
                                                                   style="font-size: 18px; padding-top: 3px;"
                                                                   data-index="<s:property value="#global_index"/>"></i>
                                                            </div>
                                                            <div class="pull-left c32" data-group="sel-rate-type">
                                                                <s:select data-index="%{#global_index}"
                                                                          name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                          list="rateTypes" listKey="id" listValue="name"
                                                                          value="%{rateType}" cssClass="form-control"/>
                                                            </div>
                                                            <div class="pull-left c32" data-group="base-rate">
                                                                <s:if test="zoneCheck == 'false'">
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 data-group="br-rate"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 readonly="true" data-group="br-rate"/>
                                                                </s:else>
                                                            </div>
                                                            <div class="pull-left c32a">%</div>
                                                            <div class="pull-left c32a">
                                                                <a href="#" onclick="viewRateSheet('<s:property
                                                                        value="profileId"/>',<s:property
                                                                        value="serviceId"/>,$(this))" class="b19"
                                                                   data-sheet-id="<s:property value="rateSheetId"/>"
                                                                   data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                   data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                   data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                        text="View"/></b></u></a>
                                                            </div>
                                                            <div class="pull-left c32a">
                                                                <s:if test="zoneCheck == 'false'">
                                                                    <i id="show-zones" onclick="showZones($(this))"
                                                                       class="fa fa-plus-square s10 b3"
                                                                       data-show="close" style="font-size: 18px;"></i>
                                                                </s:if>
                                                                <s:else>
                                                                    <i id="show-zones" onclick="showZones($(this))"
                                                                       class="fa fa-minus-square s10 b3"
                                                                       data-show="open" style="font-size: 18px;"></i>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Customer base rate detail -->
                                                    <div class="form-group pull-left base-rate"
                                                         style="width: 100%; overflow: auto; <s:if
                                                                 test="zoneCheck=='false'">display: none;</s:if>">
                                                        <ul class="c36" style="width: 14324px; overflow: hidden;">
                                                            <li class="pull-left c35" style="margin-left: 40px;">
                                                                <s:checkbox data-group="zone-check"
                                                                            data-index="%{#global_index}"
                                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].zoneCheck"
                                                                            value="%{zoneCheck}" fieldValue="true"
                                                                            onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                            cssStyle="margin-top: 7px;"/>
                                                            </li>
                                                            <li class="pull-left c35" style="padding: 5px;">
                                                                <xms:localization text="By Zone:"/>
                                                            </li>
                                                            <s:iterator value="custProfileBaseRateDetails"
                                                                        status="dStats">
                                                                <li>
                                                                    <div class="pull-left c34" data-group="zone">
                                                                        <input data-index="<s:property value="#global_index"/>"
                                                                               name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].zone"
                                                                               class="form-control alloptions text-center"
                                                                               maxlength="25"
                                                                               value="<s:property value="zone"/>"
                                                                               <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                               <s:else>readonly</s:else> type="text"
                                                                               data-group="zone-name"/> <input
                                                                            data-index="<s:property value="#global_index"/>"
                                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].rate"
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
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                value="%{displayName}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                value="%{weight}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                value="%{shipmentTypeId}"
                                                                data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                value="%{profileId}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                value="%{content}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                value="%{bound}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                value="%{serviceId}" data-index="%{#global_index}"/>
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
                                <div class="portlet-body b12 b11">
                                    <!-- Print Rate Sheet -->
                                    <div class="form-group">
                                        <table class="s36">
                                            <tr>
                                                <td><xms:localization text="Print Rate Sheets"/></td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Check All"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Check None"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Print Checked Rate Sheets"/>
                                                    </button>
                                                </td>
                                                <td><input type="checkbox" value=""></td>
                                                <td><xms:localization text="Pdf"/></td>
                                                <td><input type="checkbox" value=""></td>
                                                <td><xms:localization text="Excel"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <!-- Service Type List -->
                                    <s:iterator value="dhlDom">
                                        <div>
                                            <s:iterator value="custProfileBaseRates" status="stats">
                                                <s:set var="global_index" value="%{#global_index + 1}"/>
                                                <!-- Customer base rate -->
                                                <div class="form-group base-rate-row"
                                                     data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                     data-weight="<s:property value="weight"/>">
                                                    <div class="base-rate">
                                                        <div class="well well-sm pull-left c33" style="width: 100%">
                                                            <div class="pull-left c32"
                                                                 style="padding: 5px; width: 250px;">
                                                                <s:if test="#stats.index == 0">
                                                                    <span class="br-display-name"><s:property
                                                                            value="displayName"/></span>
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:if test="#stats.index == 0">
                                                                    <input type="checkbox" value=""
                                                                           style="margin-top: 6px;">
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32" data-group="br-remove"
                                                                 <s:if test="custProfileBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                 <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                <a class="b18"> <b><span data-group="br-remove"><i
                                                                        onclick="removeWeightBreak($(this))"
                                                                        style="color: red; font-size: 18px; padding-top: 3px;"
                                                                        class="fa fa-times s10 b3"></i></span></b>
                                                                </a>
                                                            </div>
                                                            <div class="pull-left c32" data-group="br-weight"
                                                                 <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                <a class="b18"> <b><span
                                                                        data-group="br-weight"><s:property
                                                                        value="weight"/>+</span></b>
                                                                </a>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <i onclick="addWeightBreak($(this))" id="dwed-link"
                                                                   class="fa fa-chevron-circle-right s10 b3"
                                                                   style="font-size: 18px; padding-top: 3px;"
                                                                   data-index="<s:property value="#global_index"/>"></i>
                                                            </div>
                                                            <div class="pull-left c32" data-group="sel-rate-type">
                                                                <s:select data-index="%{#global_index}"
                                                                          name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                          list="rateTypes" listKey="id" listValue="name"
                                                                          value="%{rateType}" cssClass="form-control"/>
                                                            </div>
                                                            <div class="pull-left c32" data-group="base-rate">
                                                                <s:if test="zoneCheck == 'false'">
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 data-group="br-rate"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 readonly="true" data-group="br-rate"/>
                                                                </s:else>
                                                            </div>
                                                            <div class="pull-left c32a">%</div>
                                                            <div class="pull-left c32a">
                                                                <a href="#" onclick="viewRateSheet('<s:property
                                                                        value="profileId"/>',<s:property
                                                                        value="serviceId"/>,$(this))" class="b19"
                                                                   data-sheet-id="<s:property value="rateSheetId"/>"
                                                                   data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                   data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                   data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                        text="View"/></b></u></a>
                                                            </div>
                                                            <div class="pull-left c32a">
                                                                <s:if test="zoneCheck == 'false'">
                                                                    <i id="show-zones" onclick="showZones($(this))"
                                                                       class="fa fa-plus-square s10 b3"
                                                                       data-show="close" style="font-size: 18px;"></i>
                                                                </s:if>
                                                                <s:else>
                                                                    <i id="show-zones" onclick="showZones($(this))"
                                                                       class="fa fa-minus-square s10 b3"
                                                                       data-show="open" style="font-size: 18px;"></i>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Customer base rate detail -->
                                                    <div class="form-group pull-left base-rate"
                                                         style="width: 100%; overflow: auto; <s:if
                                                                 test="zoneCheck=='false'">display: none;</s:if>">
                                                        <ul class="c36" style="width: 14324px; overflow: hidden;">
                                                            <li class="pull-left c35" style="margin-left: 40px;">
                                                                <s:checkbox data-group="zone-check"
                                                                            data-index="%{#global_index}"
                                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].zoneCheck"
                                                                            value="%{zoneCheck}" fieldValue="true"
                                                                            onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                            cssStyle="margin-top: 7px;"/>
                                                            </li>
                                                            <li class="pull-left c35" style="padding: 5px;">
                                                                <xms:localization text="By Zone:"/>
                                                            </li>
                                                            <s:iterator value="custProfileBaseRateDetails"
                                                                        status="dStats">
                                                                <li>
                                                                    <div class="pull-left c34" data-group="zone">
                                                                        <input data-index="<s:property value="#global_index"/>"
                                                                               name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].zone"
                                                                               class="form-control alloptions text-center"
                                                                               maxlength="25"
                                                                               value="<s:property value="zone"/>"
                                                                               <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                               <s:else>readonly</s:else> type="text"
                                                                               data-group="zone-name"/> <input
                                                                            data-index="<s:property value="#global_index"/>"
                                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].rate"
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
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                value="%{displayName}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                value="%{weight}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                value="%{shipmentTypeId}"
                                                                data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                value="%{profileId}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                value="%{content}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                value="%{bound}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                value="%{serviceId}" data-index="%{#global_index}"/>
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
                                <div class="portlet-body b12 b11">
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
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Check All"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Check None"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Print Checked Rate Sheets"/>
                                                    </button>
                                                </td>
                                                <td><input type="checkbox" value=""></td>
                                                <td><xms:localization text="Pdf"/></td>
                                                <td><input type="checkbox" value=""></td>
                                                <td><xms:localization text="Excel"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <!-- Service Type List -->
                                    <s:iterator value="tntDom">
                                        <div>
                                            <s:iterator value="custProfileBaseRates" status="stats">
                                                <s:set var="global_index" value="%{#global_index + 1}"/>
                                                <!-- Customer base rate -->
                                                <div class="form-group base-rate-row"
                                                     data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                     data-weight="<s:property value="weight"/>">
                                                    <div class="base-rate">
                                                        <div class="well well-sm pull-left c33" style="width: 100%">
                                                            <div class="pull-left c32"
                                                                 style="padding: 5px; width: 250px;">
                                                                <s:if test="#stats.index == 0">
                                                                    <span class="br-display-name"><s:property
                                                                            value="displayName"/></span>
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:if test="#stats.index == 0">
                                                                    <input type="checkbox" value=""
                                                                           style="margin-top: 6px;">
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32" data-group="br-remove"
                                                                 <s:if test="custProfileBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                 <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                <a class="b18"> <b><span data-group="br-remove"><i
                                                                        onclick="removeWeightBreak($(this))"
                                                                        style="color: red; font-size: 18px; padding-top: 3px;"
                                                                        class="fa fa-times s10 b3"></i></span></b>
                                                                </a>
                                                            </div>
                                                            <div class="pull-left c32" data-group="br-weight"
                                                                 <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                <a class="b18"> <b><span
                                                                        data-group="br-weight"><s:property
                                                                        value="weight"/>+</span></b>
                                                                </a>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <i onclick="addWeightBreak($(this))" id="dwed-link"
                                                                   class="fa fa-chevron-circle-right s10 b3"
                                                                   style="font-size: 18px; padding-top: 3px;"
                                                                   data-index="<s:property value="#global_index"/>"></i>
                                                            </div>
                                                            <div class="pull-left c32" data-group="sel-rate-type">
                                                                <s:select data-index="%{#global_index}"
                                                                          name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                          list="rateTypes" listKey="id" listValue="name"
                                                                          value="%{rateType}" cssClass="form-control"/>
                                                            </div>
                                                            <div class="pull-left c32" data-group="base-rate">
                                                                <s:if test="zoneCheck == 'false'">
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 data-group="br-rate"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 readonly="true" data-group="br-rate"/>
                                                                </s:else>
                                                            </div>
                                                            <div class="pull-left c32a">%</div>
                                                            <div class="pull-left c32a">
                                                                <a href="#" onclick="viewRateSheet('<s:property
                                                                        value="profileId"/>',<s:property
                                                                        value="serviceId"/>,$(this))" class="b19"
                                                                   data-sheet-id="<s:property value="rateSheetId"/>"
                                                                   data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                   data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                   data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                        text="View"/></b></u></a>
                                                            </div>
                                                            <div class="pull-left c32a">
                                                                <s:if test="zoneCheck == 'false'">
                                                                    <i id="show-zones" onclick="showZones($(this))"
                                                                       class="fa fa-plus-square s10 b3"
                                                                       data-show="close" style="font-size: 18px;"></i>
                                                                </s:if>
                                                                <s:else>
                                                                    <i id="show-zones" onclick="showZones($(this))"
                                                                       class="fa fa-minus-square s10 b3"
                                                                       data-show="open" style="font-size: 18px;"></i>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Customer base rate detail -->
                                                    <div class="form-group pull-left base-rate"
                                                         style="width: 100%; overflow: auto; <s:if
                                                                 test="zoneCheck=='false'">display: none;</s:if>">
                                                        <ul class="c36" style="width: 14324px; overflow: hidden;">
                                                            <li class="pull-left c35" style="margin-left: 40px;">
                                                                <s:checkbox data-group="zone-check"
                                                                            data-index="%{#global_index}"
                                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].zoneCheck"
                                                                            value="%{zoneCheck}" fieldValue="true"
                                                                            onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                            cssStyle="margin-top: 7px;"/>
                                                            </li>
                                                            <li class="pull-left c35" style="padding: 5px;">
                                                                <xms:localization text="By Zone"/>:
                                                            </li>
                                                            <s:iterator value="custProfileBaseRateDetails"
                                                                        status="dStats">
                                                                <li>
                                                                    <div class="pull-left c34" data-group="zone">
                                                                        <input data-index="<s:property value="#global_index"/>"
                                                                               name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].zone"
                                                                               class="form-control alloptions text-center"
                                                                               maxlength="25"
                                                                               value="<s:property value="zone"/>"
                                                                               <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                               <s:else>readonly</s:else> type="text"
                                                                               data-group="zone-name"/> <input
                                                                            data-index="<s:property value="#global_index"/>"
                                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].rate"
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
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                value="%{displayName}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                value="%{weight}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                value="%{shipmentTypeId}"
                                                                data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                value="%{profileId}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                value="%{content}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                value="%{bound}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                value="%{serviceId}" data-index="%{#global_index}"/>
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
                                <div class="portlet-body b12 b11">
                                    <!-- Print Rate Sheet -->
                                    <div class="form-group">
                                        <table class="s36">
                                            <tr>
                                                <td><xms:localization text="Print Rate Sheets"/></td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Check All"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Check None"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn s37" type="submit">
                                                        <xms:localization text="Print Checked Rate Sheets"/>
                                                    </button>
                                                </td>
                                                <td><input type="checkbox" value=""></td>
                                                <td><xms:localization text="Pdf"/></td>
                                                <td><input type="checkbox" value=""></td>
                                                <td><xms:localization text="Excel"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <!-- Service Type List -->
                                    <s:iterator value="tntIntl">
                                        <div>
                                            <s:iterator value="custProfileBaseRates" status="stats">
                                                <s:set var="global_index" value="%{#global_index + 1}"/>
                                                <!-- Customer base rate -->
                                                <div class="form-group base-rate-row"
                                                     data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                     data-weight="<s:property value="weight"/>">
                                                    <div class="base-rate">
                                                        <div class="well well-sm pull-left c33" style="width: 100%">
                                                            <div class="pull-left c32"
                                                                 style="padding: 5px; width: 250px;">
                                                                <s:if test="#stats.index == 0">
                                                                    <span class="br-display-name"><s:property
                                                                            value="displayName"/></span>
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:if test="#stats.index == 0">
                                                                    <input type="checkbox" value=""
                                                                           style="margin-top: 6px;">
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32" data-group="br-remove"
                                                                 <s:if test="custProfileBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                 <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                <a class="b18"> <b><span data-group="br-remove"><i
                                                                        onclick="removeWeightBreak($(this))"
                                                                        style="color: red; font-size: 18px; padding-top: 3px;"
                                                                        class="fa fa-times s10 b3"></i></span></b>
                                                                </a>
                                                            </div>
                                                            <div class="pull-left c32" data-group="br-weight"
                                                                 <s:if test="custProfileBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                <a class="b18"> <b><span
                                                                        data-group="br-weight"><s:property
                                                                        value="weight"/>+</span></b>
                                                                </a>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <i onclick="addWeightBreak($(this))" id="dwed-link"
                                                                   class="fa fa-chevron-circle-right s10 b3"
                                                                   style="font-size: 18px; padding-top: 3px;"
                                                                   data-index="<s:property value="#global_index"/>"></i>
                                                            </div>
                                                            <div class="pull-left c32" data-group="sel-rate-type">
                                                                <s:select data-index="%{#global_index}"
                                                                          name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                          list="rateTypes" listKey="id" listValue="name"
                                                                          value="%{rateType}" cssClass="form-control"/>
                                                            </div>
                                                            <div class="pull-left c32" data-group="base-rate">
                                                                <s:if test="zoneCheck == 'false'">
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 data-group="br-rate"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 readonly="true" data-group="br-rate"/>
                                                                </s:else>
                                                            </div>
                                                            <div class="pull-left c32a">%</div>
                                                            <div class="pull-left c32a">
                                                                <a href="#" onclick="viewRateSheet('<s:property
                                                                        value="profileId"/>',<s:property
                                                                        value="serviceId"/>,$(this))" class="b19"
                                                                   data-sheet-id="<s:property value="rateSheetId"/>"
                                                                   data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                   data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                   data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                        text="View"/></b></u></a>
                                                            </div>
                                                            <div class="pull-left c32a">
                                                                <s:if test="zoneCheck == 'false'">
                                                                    <i id="show-zones" onclick="showZones($(this))"
                                                                       class="fa fa-plus-square s10 b3"
                                                                       data-show="close" style="font-size: 18px;"></i>
                                                                </s:if>
                                                                <s:else>
                                                                    <i id="show-zones" onclick="showZones($(this))"
                                                                       class="fa fa-minus-square s10 b3"
                                                                       data-show="open" style="font-size: 18px;"></i>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Customer base rate detail -->
                                                    <div class="form-group pull-left base-rate"
                                                         style="width: 100%; overflow: auto; <s:if
                                                                 test="zoneCheck=='false'">display: none;</s:if>">
                                                        <ul class="c36" style="width: 14324px; overflow: hidden;">
                                                            <li class="pull-left c35" style="margin-left: 40px;">
                                                                <s:checkbox data-group="zone-check"
                                                                            data-index="%{#global_index}"
                                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].zoneCheck"
                                                                            value="%{zoneCheck}" fieldValue="true"
                                                                            onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                            cssStyle="margin-top: 7px;"/>
                                                            </li>
                                                            <li class="pull-left c35" style="padding: 5px;">
                                                                <xms:localization text="By Zone:"/>
                                                            </li>
                                                            <s:iterator value="custProfileBaseRateDetails"
                                                                        status="dStats">
                                                                <li>
                                                                    <div class="pull-left c34" data-group="zone">
                                                                        <input data-index="<s:property value="#global_index"/>"
                                                                               name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].zone"
                                                                               class="form-control alloptions text-center"
                                                                               maxlength="25"
                                                                               value="<s:property value="zone"/>"
                                                                               <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                               <s:else>readonly</s:else> type="text"
                                                                               data-group="zone-name"/> <input
                                                                            data-index="<s:property value="#global_index"/>"
                                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[<s:property value="#global_index"/>].customerProfileBaseRateDetail[<s:property value="#dStats.index"/>].rate"
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
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                value="%{displayName}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                value="%{weight}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                value="%{shipmentTypeId}"
                                                                data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                value="%{profileId}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                value="%{content}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                value="%{bound}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                value="%{serviceId}" data-index="%{#global_index}"/>
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
                                                        <div class="well well-sm pull-left c33" style="width: 100%">
                                                            <div class="pull-left c32"
                                                                 style="padding: 5px; width: 250px;">
                                                                <s:if test="#stats.index == 0">
                                                                    <span class="br-display-name"><s:property
                                                                            value="displayName"/></span>
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32" data-group="sel-rate-type">
                                                                <s:select data-index="%{#global_index}"
                                                                          name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                          list="rateTypes" listKey="id" listValue="name"
                                                                          value="%{rateType}" cssClass="form-control"/>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:textfield data-index="%{#global_index}"
                                                                             name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                             value="%{rate}"
                                                                             cssClass="form-control alloptions"
                                                                             maxlength="25" cssStyle="width: 50px;"
                                                                             data-group="br-rate"/>
                                                            </div>
                                                            <div class="pull-left c32a">%</div>
                                                        </div>
                                                    </div>
                                                    <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                value="%{displayName}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                value="%{weight}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                value="%{shipmentTypeId}"
                                                                data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                value="%{profileId}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                value="%{content}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                value="%{bound}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                value="%{serviceId}" data-index="%{#global_index}"/>
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
                                                        <div class="well well-sm pull-left c33" style="width: 100%">
                                                            <div class="pull-left c32"
                                                                 style="padding: 5px; width: 250px;">
                                                                <s:if test="#stats.index == 0">
                                                                    <span class="br-display-name"><s:property
                                                                            value="displayName"/></span>
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32" data-group="sel-rate-type">
                                                                <s:select data-index="%{#global_index}"
                                                                          name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                          list="rateTypes" listKey="id" listValue="name"
                                                                          value="%{rateType}" cssClass="form-control"/>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:textfield data-index="%{#global_index}"
                                                                             name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                             value="%{rate}"
                                                                             cssClass="form-control alloptions"
                                                                             maxlength="25" cssStyle="width: 50px;"
                                                                             data-group="br-rate"/>
                                                            </div>
                                                            <div class="pull-left c32a">%</div>
                                                        </div>
                                                    </div>
                                                    <!-- Customer base rate detail -->
                                                    <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                                value="%{displayName}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                                value="%{weight}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                                value="%{shipmentTypeId}"
                                                                data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                                value="%{profileId}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                                value="%{content}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                                value="%{bound}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                                value="%{serviceId}" data-index="%{#global_index}"/>
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
                                                    <div class="well well-sm pull-left c33" style="width: 100%">
                                                        <div class="pull-left c32" style="padding: 5px; width: 250px;">
                                                            <s:property value="serviceName"/>
                                                        </div>
                                                        <div class="pull-left c32">
                                                            <s:if test="#stats.index == 0">
                                                                <input type="checkbox" value=""
                                                                       style="margin-top: 6px;">
                                                            </s:if>
                                                        </div>
                                                        <div class="pull-left c32" data-group="sel-rate-type">
                                                            <s:select data-index="%{#global_index}"
                                                                      name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rateType"
                                                                      list="rateTypes" listKey="id" listValue="name"
                                                                      value="%{rateType}" cssClass="form-control"/>
                                                        </div>
                                                        <div class="pull-left c32">
                                                            <s:textfield data-index="%{#global_index}"
                                                                         name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].rate"
                                                                         value="%{rate}"
                                                                         cssClass="form-control alloptions"
                                                                         maxlength="25" cssStyle="width: 50px;"
                                                                         data-group="br-rate"/>
                                                        </div>
                                                        <div class="pull-left c32a">%</div>
                                                    </div>
                                                </div>
                                                <!-- Customer base rate detail -->
                                                <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                    <s:hidden
                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].baseRateDescription"
                                                            value="%{displayName}" data-index="%{#global_index}"/>
                                                    <s:hidden
                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].weight"
                                                            value="%{weight}" data-index="%{#global_index}"/>
                                                    <s:hidden
                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].shipmentTypeId"
                                                            value="%{shipmentTypeId}" data-index="%{#global_index}"/>
                                                    <s:hidden
                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].profileId"
                                                            value="%{profileId}" data-index="%{#global_index}"/>
                                                    <s:hidden
                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].content"
                                                            value="%{content}" data-index="%{#global_index}"/>
                                                    <s:hidden
                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].bound"
                                                            value="%{bound}" data-index="%{#global_index}"/>
                                                    <s:hidden
                                                            name="saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[%{#global_index}].carrierId"
                                                            value="%{serviceId}" data-index="%{#global_index}"/>
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
<div id="add-weight-dialog" title="<xms:localization text="Add Weight Break" />" style="display: none;"></div>
<div id="view_rate_sheet_dialog" title="<xms:localization text="View Rate Sheet" />" style="display: none;"></div>
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
            $parent.find("[data-group='zone']").each(function (i) {
                $(this).attr("disabled", false);
                $(this).attr("readonly", true);
            });
            $parent.find("[data-group='zone-rate']").each(function (i) {
                $(this).attr("disabled", false);
            });
            $parent.parents("div.base-rate").prev().find("[data-group='br-rate']").attr("readonly", true);
        } else {
            $parent.find("[data-group='zone']").each(function (i) {
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
                alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
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
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
        console.log("saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[" + index + "]");
        console.log("saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[" + (index + 1) + "]");
        console.log("shipmentId: " + shipmentId + "| content: " + content + "| bound: " + bound + "| current weight: " + currentWeight + "| next weight: " + nextWeight);
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
    function viewRateSheet(code, serviceId, obj) {
        var type = 2; // Customer Profile
        var baseRateDiv = obj.parent().parent();
        // Get rate type.
        var rateType = $("option:selected", $("div[data-group='sel-rate-type']", baseRateDiv).first()).val();
        // Get base rate.
        var baseRate = $("input", $("div[data-group='base-rate']", baseRateDiv).first()).val();
        // Get customer minimum base charge.
        var minimumRate = $("#minimum_base_charge").val();
        // Get zone rates.
        var baseRateZones = baseRateDiv.parent().next();
        var zoneCheck = $("input[data-group='zone-check']", baseRateZones).is(":checked");
        var zoneRates = "";
        if (zoneCheck) {
            $("div[data-group='zone']", baseRateZones).each(function () {
                zoneRates += "&rateSheetRequest.zoneRates['" + $("input[data-group='zone-name']", this).val() + "']=";
                zoneRates += $("input[data-group='zone-rate']", this).val();
            });
        }
        loadingDialog.dialog("open");
        var sheetId = obj.attr("data-sheet-id");
        var perWeightSheetId = obj.attr("data-perweight-sheet-id");
        var ncSheetId = obj.attr("data-nc-sheet-id");
        var ncPerWeightSheetId = obj.attr("data-nc-perweight-sheet-id");
        var postData = "sheetId=" + sheetId;
        postData += "&perWeightSheetId=" + perWeightSheetId;
        postData += "&ncSheetId=" + ncSheetId;
        postData += "&ncPerWeightSheetId=" + ncPerWeightSheetId;
        postData += "&rateSheetRequest.type=" + type;
        postData += "&rateSheetRequest.code=" + code;
        postData += "&rateSheetRequest.serviceId=" + serviceId;
        postData += "&rateSheetRequest.rateType=" + rateType;
        postData += "&rateSheetRequest.minimumRate=" + minimumRate;
        postData += "&rateSheetRequest.baseRate=" + baseRate;
        postData += zoneRates;
        if (serviceId == 3) {
            postData += "&columnName=" + $("#tntDomColumnName option:selected").val();
        }
        $.post("view_rate_sheet.ix?reqType=json", postData, function (res) {
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
</script>