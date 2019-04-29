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
                                        <td class="caption b17" colspan="2"><xms:localization text="Base Rates"/></td>
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
                                                <td>Pdf</td>
                                                <td><input type="checkbox" value=""></td>
                                                <td>Excel</td>
                                            </tr>
                                        </table>
                                    </div>
                                    <s:iterator value="shipmentTypes">
                                        <s:iterator value="packageTypes" status="stats">
                                            <div class="form-group b23">
                                                <table class="s36">
                                                    <s:if test="baseRates.size() == 1">
                                                        <s:set var="global_index" value="%{#global_index + 1}"/>
                                                        <tr>
                                                            <td width="290"><s:if test="packageType!=0">
                                                                <s:set var="baseRateDescription">
                                                                    <s:property value="shipmentTypeName"/> - <s:property
                                                                        value="packageTypeName"/>
                                                                </s:set>
                                                                <s:property value="shipmentTypeName"/> - <s:property
                                                                    value="packageTypeName"/>
                                                            </s:if> <s:else>
                                                                <s:set var="baseRateDescription">
                                                                    <s:property value="shipmentTypeName"/>
                                                                </s:set>
                                                                <s:property value="shipmentTypeName"/>
                                                            </s:else></td>
                                                            <td><input type="checkbox"/></td>
                                                            <td><i id="note-link3"
                                                                   class="fa fa-chevron-circle-right s10 b3"
                                                                   style="font-size: 18px;"></i></td>
                                                            <td><s:select
                                                                    name="saveBaseRate.customerBaseRates[%{#global_index}].rateType"
                                                                    list="listRateType" listKey="key" listValue="value"
                                                                    value="baseRates[0].rateType"
                                                                    class="form-control"></s:select></td>
                                                            <td width="50"><s:textfield class="form-control alloptions"
                                                                                        value="%{baseRates[0].rate}"
                                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].rate"/></td>
                                                            <td>%</td>
                                                            <td><strong class="b19"
                                                                        sheetId='<s:property value="carrierZone[0].sheetId"/>'
                                                                        sheetName='<s:property value="shipmentTypeName" /> - <s:property value="packageTypeName" />'
                                                                        onclick="javascript:showRateSheet($(this).attr('sheetId'), $(this).attr('sheetName'));"><u><b><xms:localization
                                                                    text="View"/></b></u></strong></td>
                                                            <td><i id="note-link3" class="fa fa-minus-square s10 b3"
                                                                   style="font-size: 18px;"></i>&nbsp;<i id="note-link3"
                                                                                                         class="fa fa-plus-square s10 b3"
                                                                                                         style="font-size: 18px;"></i>
                                                            </td>
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
                                                        </tr>
                                                    </s:if>
                                                    <s:else>
                                                        <s:iterator value="baseRates" status="stats">
                                                            <s:set var="global_index" value="%{#global_index + 1}"/>
                                                            <tr>
                                                                <td width="290"><s:if test="#stats.index == 0">
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
                                                                </s:if></td>
                                                                <td><s:if test="#stats.index == 0">
                                                                    <input type="checkbox"/>
                                                                </s:if></td>
                                                                <td width="80" style="text-align: right; color: red;">
                                                                    <span id="span_weight"></span> <s:property
                                                                        value="weight"/> + :
                                                                </td>
                                                                <td><i id="note-link3"
                                                                       class="fa fa-chevron-circle-right s10 b3"
                                                                       style="font-size: 18px;"></i></td>
                                                                <td><s:select
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].rateType"
                                                                        list="listRateType" listKey="key"
                                                                        listValue="value" value="rateType"
                                                                        class="form-control"></s:select></td>
                                                                <td width="50"><s:textfield
                                                                        class="form-control alloptions" value="%{rate}"
                                                                        name="saveBaseRate.customerBaseRates[%{#global_index}].rate"></s:textfield></td>
                                                                <td>%</td>
                                                                <td><strong class="b19"
                                                                            sheetId='<s:property value="carrierZone[0].sheetId"/>'
                                                                            sheetName='<s:property value="shipmentTypeName" /> - <s:property value="packageTypeName" />'
                                                                            onclick="javascript:showRateSheet($(this).attr('sheetId'), $(this).attr('sheetName'));"><u><b><xms:localization
                                                                        text="View"/></b></u></strong></td>
                                                                <td><i id="note-link3" class="fa fa-minus-square s10 b3"
                                                                       style="font-size: 18px;"></i>&nbsp;<i
                                                                        id="note-link3" class="fa fa-plus-square s10 b3"
                                                                        style="font-size: 18px;"></i></td>
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
                                                            </tr>
                                                        </s:iterator>
                                                    </s:else>
                                                </table>
                                            </div>
                                            <div class="form-group div_baseRate scroll_horizontal">
                                                <table class="s36">
                                                    <tr>
                                                        <td width="0">&nbsp;</td>
                                                        <td><input type="checkbox" name="zoneCheck"
                                                                   <s:if test="baseRates[0].zoneCheck==1">checked="checked"</s:if> />
                                                        </td>
                                                        <td><xms:localization text="By Zone"/>:</td>
                                                        <s:iterator value="baseRates">
                                                            <s:if test="zoneCheck==1">
                                                                <s:iterator value="customerBaseRateDetails">
                                                                    <td width="70"><s:textfield name="zone"
                                                                                                readonly="true"
                                                                                                class="form-control alloptions text-center"
                                                                                                maxlength="25"
                                                                                                placeholder="1"></s:textfield>
                                                                        <input type="text"
                                                                               value='<s:property value="rate" />'
                                                                               class="form-control alloptions text-center"
                                                                               maxlength="25" placeholder="1"
                                                                               readonly="readonly"/></td>
                                                                </s:iterator>
                                                            </s:if>
                                                            <s:else>
                                                                <s:iterator value="carrierZone">
                                                                    <td width="70"><s:textfield name="columnName"
                                                                                                readonly="true"
                                                                                                class="form-control alloptions text-center"
                                                                                                maxlength="25"
                                                                                                placeholder="1"></s:textfield>
                                                                        <input type="text"
                                                                               value='<s:property value="rate" />'
                                                                               class="form-control alloptions text-center"
                                                                               maxlength="25" placeholder="1"
                                                                               readonly="readonly"/></td>
                                                                </s:iterator>
                                                            </s:else>
                                                        </s:iterator>
                                                    </tr>
                                                </table>
                                            </div>
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
<div class="form-actions pal pdt10">
    <div class="row">
        <div class="col-lg-12">
            <button class="btn s37" type="reset">Reset</button>
            <button class="btn s37" type="button" onclick="saveCustomerBaseRate()">Save</button>
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
        $(this).parents("div.form-group").next().show("slow");
    });
    $("i.fa-minus-square").click(function () {
        $(this).parents("div.form-group").next().hide("slow");
    });
    function saveCustomerBaseRate() {
        var data = $("#frmSaveCustomerBaseRates").serialize();
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