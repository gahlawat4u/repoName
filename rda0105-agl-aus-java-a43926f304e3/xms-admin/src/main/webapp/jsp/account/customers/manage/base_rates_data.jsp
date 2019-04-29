<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
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
<form id="frmSaveCustomerBaseRates">
    <div id="Base-tab" class="tab-pane fade in">
        <div class="row">
            <div class="portlet-body b12 b11">
                <div class="portlet-body b22" style="padding: 0px;">
                    <ul id="generalTab1" class="nav nav-tabs responsive">
                        <li class="active" style="margin-left: 10px;">
                            <a href="#1a-tab" data-toggle="tab" onclick="loadGeneralTab(1)"><xms:localization
                                    text="General"/> </a>
                        </li>
                        <s:iterator value="baseRate.services" status="row">
                            <li>
                                <a href='#<s:property value="serviceId"/>-tab' data-toggle="tab" class="tb3"><s:property
                                        value="serviceName"/></a>
                            </li>
                        </s:iterator>
                        <li>
                            <a href="#8-tab" data-toggle="tab" class="tb3" onclick="loadOtherTab(1)"><xms:localization
                                    text="Others"/></a>
                        </li>
                    </ul>
                    <div id="generalTabContent" class="tab-content responsive">
                        <div id="1a-tab" class="tab-pane fade in tb2 active">
                            <div class="row">
                                <div class="portlet-body b12 b11">
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
                                            <td width="60"><s:textfield name="baseRate.minimunBaseCharge"
                                                                        class="form-control"></s:textfield></td>
                                            <td>%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <s:set var="global_index" value="0"/>
                        <s:iterator value="baseRate.services" status="row">

                            <div id='<s:property value="serviceId"/>-tab' class="tab-pane fade in">

                                <div class="row">
                                    <div class="portlet-body b12 b11">
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
                                        <s:iterator value="shipmentTypes">
                                            <s:iterator value="packageTypes" status="stats">
                                                <s:if test="baseRates.size() == 1">
                                                    <s:set var="global_index" value="%{#global_index + 1}"/>
                                                    <div class="form-group">
                                                        <div class="well well-sm pull-left c33 base-rate"
                                                             style="width: 100%">
                                                            <div class="pull-left c32"
                                                                 style="padding: 5px; width: 250px;">
                                                                <s:if test="packageType!=0">
                                                                    <s:set var="baseRateDescription">
                                                                        <s:property value="shipmentTypeName"/> -
                                                                        <s:property value="packageTypeName"/>
                                                                    </s:set>
                                                                    <s:property value="shipmentTypeName"/> - <s:property
                                                                        value="packageTypeName"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:set var="baseRateDescription">
                                                                        <s:property value="shipmentTypeName"/>
                                                                    </s:set>
                                                                    <s:property value="shipmentTypeName"/>
                                                                </s:else>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <input type="checkbox" value=""
                                                                       style="margin-top: 6px;">
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <i id="dwed-link"
                                                                   class="fa fa-chevron-circle-right s10 b3"
                                                                   style="font-size: 18px; padding-top: 3px;"></i>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:select
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].rateType"
                                                                        list="listRateType" listKey="key"
                                                                        listValue="value" value="baseRates[0].rateType"
                                                                        class="form-control"/>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:textfield class="form-control alloptions"
                                                                             value="%{baseRates[0].rate}"
                                                                             name="saveBaseRate.customerBaseRates[%{#global_index}].rate"/>
                                                            </div>
                                                            <div class="pull-left c32a">%</div>
                                                            <div class="pull-left c32a">
                                                                <a href="#"
                                                                   onclick="javascript:showRateSheet('<s:property
                                                                           value="carrierZone[0].sheetId"/>', '
                                                                       <s:property
                                                                               value="baseRateDescription"/>');"><strong
                                                                        class="b19"><u><b><xms:localization
                                                                        text="View"/></b></u></strong></a>
                                                            </div>
                                                            <div class="pull-left c32a">
                                                                <i id="note-link3" class="fa fa-minus-square s10 b3"
                                                                   style="font-size: 18px;"></i>&nbsp;<i id="note-link3"
                                                                                                         class="fa fa-plus-square s10 b3"
                                                                                                         style="font-size: 18px;"></i>
                                                            </div>
                                                            <s:hidden
                                                                    name="saveBaseRate.customerBaseRates[%{#global_index}].baserateDescription"
                                                                    value="%{baseRateDescription}"/>
                                                            <s:hidden
                                                                    name="saveBaseRate.customerBaseRates[%{#global_index}].weight"
                                                                    value="%{baseRates[0].weight}"/>
                                                            <s:hidden
                                                                    name="saveBaseRate.customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                    value="%{baseRates[0].shipmentTypeId}"/>
                                                            <s:hidden
                                                                    name="saveBaseRate.customerBaseRates[%{#global_index}].customerCode"
                                                                    value="%{baseRates[0].customerCode}"/>
                                                            <s:hidden
                                                                    name="saveBaseRate.customerBaseRates[%{#global_index}].zoneCheck"
                                                                    value="%{baseRates[0].zoneCheck}"/>
                                                            <s:hidden
                                                                    name="saveBaseRate.customerBaseRates[%{#global_index}].content"
                                                                    value="%{baseRates[0].content}"/>
                                                            <s:hidden
                                                                    name="saveBaseRate.customerBaseRates[%{#global_index}].bound"
                                                                    value="%{baseRates[0].bound}"/>
                                                            <s:hidden
                                                                    name="saveBaseRate.customerBaseRates[%{#global_index}].carrierId"
                                                                    value="%{serviceId}"/>
                                                        </div>
                                                        <div class="form-group pull-left base-rate"
                                                             style="display: none; width: 100%; overflow: auto;">
                                                            <ul class="c36" style="width: 7324px; overflow: hidden;">
                                                                <li>
                                                                    <div class="pull-left c35"
                                                                         style="margin-left: 40px;">
                                                                        <input type="checkbox" name="zoneCheck"
                                                                               style="margin-top: 7px;">
                                                                    </div>
                                                                </li>
                                                                <li>
                                                                    <div class="pull-left c35" style="padding: 5px;">
                                                                        <xms:localization text="By Zone"/>:
                                                                    </div>
                                                                </li>
                                                                <s:if test="baseRates[0].zoneCheck==1">
                                                                    <s:iterator value="customerBaseRateDetails">
                                                                        <li>
                                                                            <div class="pull-left c34">
                                                                                <s:textfield name="baseRates[0].zone"
                                                                                             readonly="true"
                                                                                             class="form-control alloptions text-center"
                                                                                             maxlength="25"
                                                                                             placeholder="1"/>
                                                                                <s:textfield
                                                                                        cssClass="form-control alloptions text-center"
                                                                                        maxlength="25"
                                                                                        value="%{baseRates[0].rate}"/>
                                                                            </div>
                                                                        </li>
                                                                    </s:iterator>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:iterator value="carrierZone">
                                                                        <li>
                                                                            <div class="pull-left c34">
                                                                                <s:textfield name="columnName"
                                                                                             readonly="true"
                                                                                             class="form-control alloptions text-center"
                                                                                             maxlength="25"
                                                                                             value="%{columnName}"/>
                                                                                <s:textfield name="rate"
                                                                                             value="%{baseRates[0].rate}"
                                                                                             cssClass="form-control alloptions text-center"
                                                                                             maxlength="25"
                                                                                             readonly="true"/>
                                                                            </div>
                                                                        </li>
                                                                    </s:iterator>
                                                                </s:else>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </s:if>
                                                <s:else>
                                                    <s:iterator value="baseRates" status="stats">
                                                        <s:set var="global_index" value="%{#global_index + 1}"/>
                                                        <div class="form-group">
                                                            <div class="well well-sm pull-left c33 base-rate"
                                                                 style="width: 100%">
                                                                <div class="pull-left c32"
                                                                     style="padding: 5px; width: 250px;">
                                                                    <s:if test="#stats.index == 0">
                                                                        <s:if test="packageType!=0">
                                                                            <s:set var="baseRateDescription">
                                                                                <s:property value="shipmentTypeName"/> -
                                                                                <s:property value="packageTypeName"/>
                                                                            </s:set>
                                                                            <s:property value="shipmentTypeName"/> -
                                                                            <s:property value="packageTypeName"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:set var="baseRateDescription">
                                                                                <s:property value="shipmentTypeName"/>
                                                                            </s:set>
                                                                            <s:property value="shipmentTypeName"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32a">
                                                                    <s:if test="#stats.index == 0">
                                                                        <input type="checkbox" value=""
                                                                               style="margin-top: 6px;">
                                                                    </s:if>
                                                                    <s:else>
                                                                        <i class="fa fa-times s10 b3"
                                                                           style="font-size: 18px; padding-top: 3px; color: red;"></i>
                                                                    </s:else>
                                                                </div>
                                                                <div class="pull-left c32a">
                                                                    <a class="b18"><b><s:property value="weight"/>+</b></a>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <i id="dwed-link"
                                                                       class="fa fa-chevron-circle-right s10 b3"
                                                                       style="font-size: 18px; padding-top: 3px;"></i>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <s:select
                                                                            name="saveBaseRate.customerBaseRates[%{#global_index}].rateType"
                                                                            list="listRateType" listKey="key"
                                                                            listValue="value" value="%{rateType}"
                                                                            class="form-control"/>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <s:textfield class="form-control alloptions"
                                                                                 value="%{rate}"
                                                                                 name="saveBaseRate.customerBaseRates[%{#global_index}].rate"/>
                                                                </div>
                                                                <div class="pull-left c32a">%</div>
                                                                <div class="pull-left c32a">
                                                                    <a href="#"
                                                                       onclick="javascript:showRateSheet('<s:property
                                                                               value="carrierZone[0].sheetId"/>', '
                                                                           <s:property
                                                                                   value="baseRateDescription"/>');"><strong
                                                                            class="b19"><u><b><xms:localization
                                                                            text="View"/></b></u></strong></a>
                                                                </div>
                                                                <div class="pull-left c32a">
                                                                    <i id="note-link3" class="fa fa-minus-square s10 b3"
                                                                       style="font-size: 18px;"></i>&nbsp;<i
                                                                        id="note-link3" class="fa fa-plus-square s10 b3"
                                                                        style="font-size: 18px;"></i>
                                                                </div>
                                                                <s:hidden
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].baserateDescription"
                                                                        value="%{baseRateDescription}"/>
                                                                <s:hidden
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].weight"
                                                                        value="%{weight}"/>
                                                                <s:hidden
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                        value="%{shipmentTypeId}"/>
                                                                <s:hidden
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].customerCode"
                                                                        value="%{customerCode}"/>
                                                                <s:hidden
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].zoneCheck"
                                                                        value="%{zoneCheck}"/>
                                                                <s:hidden
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].content"
                                                                        value="%{content}"/>
                                                                <s:hidden
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].bound"
                                                                        value="%{bound}"/>
                                                                <s:hidden
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].carrierId"
                                                                        value="%{serviceId}"/>
                                                            </div>
                                                        </div>
                                                    </s:iterator>
                                                </s:else>
                                            </s:iterator>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>

                        </s:iterator>
                        <div id="8-tab" class="tab-pane fade in">
                            <xms:localization text="Please choice a customer profile"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="form-actions pal pdt10">
    <div class="row">
        <div class="col-lg-12">
            <button class="btn s37" type="reset">
                <xms:localization text="Reset"/>
            </button>
            <button class="btn s37" type="button" onclick="saveCustomerBaseRate()">
                <xms:localization text="Save"/>
            </button>
        </div>
    </div>
</div>
<div id="sheetDialog"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $("div.div_baseRate").hide();
        $(".chk_rate_type_active").each(function () {
            if ($(this).is(':checked')) {
                $(this).parents("div.div_baseRate").slideDown("slow");
            }
        });

    });

    $("i.fa-plus-square").click(function () {
        $(this).parents("div.base-rate").next().show("slow");
    });
    $("i.fa-minus-square").click(function () {
        $(this).parents("div.base-rate").next().hide("slow");
    });
    function saveCustomerBaseRate() {
        var data = $("#frmSaveCustomerBaseRates").serialize();

        console.log(data);
        loadingDialog.dialog("open");
        $.post("manage_customers_base_rates_do_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS" || res.errorCode == "FIELD_ERROR") {
                $("#address-tab").html(res.content);
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
    function showRateSheet(sheetId, sheetName) {
        var data = {
            'sheetId': sheetId,
            'sheetName': sheetName,
            'customerCode': $('#customerCode').val()
        };
        var action = 'manage_customers_base_rate_show_rate_sheet.ix?reqType=json';
        loadDialog(action, data, '', '', 'Close', 'sheetDialog', 'Rate Sheet', '');
    }


</script>