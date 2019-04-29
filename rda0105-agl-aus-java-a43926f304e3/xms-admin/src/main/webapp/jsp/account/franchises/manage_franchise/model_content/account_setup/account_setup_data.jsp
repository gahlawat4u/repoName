<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="frmSaveAccountSetup">
    <div class="row">
        <div class="portlet-body b12 b11">
            <div class="row">
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td colspan="3" class="b16 uppercase" id="td_address"><p>
                                <s:property value="franchise.customerAddress.customerName"/>
                                <s:hidden name="saveManageFranchiseModel.accountSetup.customerAddress.customerName"
                                          value="%{franchise.customerAddress.customerName}"/>
                                <br/>
                                <s:property value="franchise.customerAddress.address1"/>
                                <s:hidden name="saveManageFranchiseModel.accountSetup.customerAddress.address1"
                                          value="%{franchise.customerAddress.address1}"/>
                                <br/>
                                <s:property value="franchise.customerAddress.city"/>
                                <s:hidden name="saveManageFranchiseModel.accountSetup.customerAddress.city"
                                          value="%{franchise.customerAddress.city}"/>
                                <br/>
                                <s:property value="franchise.customerAddress.postalCode"/>
                                <s:hidden name="saveManageFranchiseModel.accountSetup.customerAddress.postalCode"
                                          value="%{franchise.customerAddress.postalCode}"/>
                                <br/>
                                <s:property value="franchise.customerAddress.countryD.countryName"/>
                                <s:hidden
                                        name="saveManageFranchiseModel.accountSetup.customerAddress.countryD.countryName"
                                        value="%{franchise.customerAddress.countryD.countryName}"/>
                                <br/>
                                <s:property value="franchise.customerAddress.phone"/>
                                <s:hidden name="saveManageFranchiseModel.accountSetup.customerAddress.phone"
                                          value="%{franchise.customerAddress.phone}"/>
                                <br/>
                                <s:property value="franchise.customerAddress.email"/>
                                <s:hidden name="saveManageFranchiseModel.accountSetup.customerAddress.email"
                                          value="%{franchise.customerAddress.email}"/>
                            </p></td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div class="caption b17">
                                    <xms:localization text="Account Setup"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Franchise # (*):"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.franchiseCode"
                                    value="%{franchise.franchiseCode}" cssClass="form-control txt_ready_only"
                                    readonly="true" id="txt_franchise_code" maxLength="3"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Submit Date:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.createDate"
                                    value="%{franchise.createDate}" cssClass="form-control " readonly="true"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Activation Date:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.activateDate"
                                    value="%{franchise.activateDate}" cssClass="form-control " readonly="true"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Inactive?:"/></td>
                            <td class="td2" colspan="2"><s:checkbox
                                    name="saveManageFranchiseModel.accountSetup.inactive"
                                    value="%{franchise.inactive}"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Exclude From ALL:"/></td>
                            <td class="td2" colspan="2"><s:checkbox
                                    name="saveManageFranchiseModel.accountSetup.excludeFromAll"
                                    value="%{franchise.excludeFromAll}"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Franchise Start Date:"/></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control form_datetime"
                                                                     name="saveManageFranchiseModel.accountSetup.franchiseStartDate"
                                                                     value="%{franchise.franchiseStartDate}"
                                                                     data-date-format="dd MM yyyy"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Group With Franchise:"/></td>
                            <td class="td2" colspan="2"><s:select name="saveManageFranchiseModel.accountSetup.groupId"
                                                                  value="%{franchise.groupId}" list="franchises"
                                                                  class="form-control" listKey="id" headerKey="0"
                                                                  headerValue="" listValue="name"></s:select></td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td colspan="3" style="border-top: 0px !important">
                                <div class="caption b17">
                                    <xms:localization text="Reporting Setup"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Web Freight Group:"/></td>
                            <td class="td2"><s:select name="saveManageFranchiseModel.accountSetup.webshipGroupid"
                                                      value="%{franchise.webshipGroupid}" list="webshipGroups"
                                                      listValue="webshipGroupName" listKey="webshipGroupId"
                                                      cssClass="form-control" headerValue="" headerKey="0"/></td>
                        </tr>

                        <tr>
                            <td class="td1"><xms:localization text="Area:"/></td>
                            <td class="td2" colspan="2"><s:select name="saveManageFranchiseModel.accountSetup.areaId"
                                                                  value="%{franchise.areaId}" list="areas"
                                                                  listValue="areaName" listKey="areaId"
                                                                  cssClass="form-control" headerValue=""
                                                                  headerKey="0"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Sales Rep:"/></td>
                            <td class="td2" colspan="2"><s:select
                                    name="saveManageFranchiseModel.accountSetup.salesRepId"
                                    value="%{franchise.salesRepId}" list="salesReps" listValue="displayName"
                                    listKey="userId" cssClass="form-control" headerValue="" headerKey="0"/></td>
                        </tr>

                        <tr>
                            <td class="td1"><xms:localization text="Business Registration #:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.registrationid"
                                    value="%{franchise.registrationid}" cssClass="form-control"
                                    onkeypress="return formartNumber(event,this,false);"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="GST #:"/></td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageFranchiseModel.accountSetup.gstid"
                                                                     value="%{franchise.gstid}" cssClass="form-control"
                                                                     onkeypress="return formartNumber(event,this,false);"/></td>
                        </tr>

                        <tr>
                            <td class="td1"><xms:localization text="Franchise Territory (*):"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.franchiseTerritory"
                                    value="%{franchise.franchiseTerritory}" cssClass="form-control"/></td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td colspan="3" style="border-top: 0px !important">
                                <div class="caption b17">
                                    <xms:localization text="Carrier Setup"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="DHL Account #:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.dhlAccount"
                                    value="%{franchise.dhlAccount}" cssClass="form-control"/></td>
                        </tr>
                        <%-- <tr>
                            <td class="td1"><xms:localization text="Hub Account #:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.hubAccount"
                                    value="%{franchise.hubAccount}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="TNT Account #:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.tntAccount"
                                    value="%{franchise.tntAccount}" cssClass="form-control"/></td>
                        </tr> --%>
                        <tr>
                            <td class="td1"><xms:localization text="Toll Priority Account #:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.tollPriorityAccount"
                                    value="%{franchise.tollPriorityAccount}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Startrack Account #:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.startrackAccount"
                                    value="%{franchise.startrackAccount}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Startrack Dispatch ID #:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.dispatchId"
                                    value="%{franchise.dispatchId}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Other Account #:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.otherAccount"
                                    value="%{franchise.otherAccount}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Booking Email Notification:"/></td>
                            <td class="td2" colspan="2"><s:checkbox
                                    name="saveManageFranchiseModel.accountSetup.bookingEmailNotification"
                                    value="%{franchise.bookingEmailNotification}"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Profit Split:"/></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageFranchiseModel.accountSetup.profitShare"
                                    value="%{franchise.profitShare}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Invoicing Charge:"/></td>
                            <td class="td2" colspan="2"><s:checkbox
                                    name="saveManageFranchiseModel.accountSetup.invoicingCharge"
                                    value="%{franchise.invoicingCharge}" cssStyle="width: 14px;"
                                    cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization
                                    text="Enable AGL Warranty"/>:
                            </td>
                            <td class="td2" colspan="2"><s:checkbox
                                    name="saveManageFranchiseModel.accountSetup.enableSi"
                                    value="%{franchise.enableSi}"/></td>
                        </tr>
                        <s:if test="franchise.id != 1">
                            <tr>
                                <td class="td1"><xms:localization text="DHL Int. Markup Rate:"/></td>
                                <td class="td2" colspan="2"><s:textfield
                                        name="saveManageFranchiseModel.accountSetup.markupRate"
                                        value="%{franchise.markupRate}" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="DHL Domestic Markup Rate:"/></td>
                                <td class="td2" colspan="2"><s:textfield
                                        name="saveManageFranchiseModel.accountSetup.dhlDomMarkupRate"
                                        value="%{franchise.dhlDomMarkupRate}" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="TNT Int. Markup Rate:"/></td>
                                <td class="td2" colspan="2"><s:textfield
                                        name="saveManageFranchiseModel.accountSetup.tntInternationalMarkupRate"
                                        value="%{franchise.tntInternationalMarkupRate}"
                                        cssClass="form-control"></s:textfield></td>
                            </tr>
                            <s:if test="listServiceMarkup != null && !listServiceMarkup.isEmpty()">
                                <s:iterator value="listServiceMarkup" status="stats">
                                    <s:if test="shipmentTypeId == 213">
                                        <tr>
                                            <td class="td1"><xms:localization
                                                    text="TNT Int. Express Markup Rate:"/></td>
                                            <td class="td2" colspan="2"><s:textfield
                                                    name="saveManageFranchiseModel.listServiceMarkup[%{#stats.index}].markupRate"
                                                    value="%{markupRate}" cssClass="form-control"/></td>
                                            <s:hidden
                                                    name="saveManageFranchiseModel.listServiceMarkup[%{#stats.index}].shipmentTypeId"
                                                    value="213"/>
                                            <s:hidden
                                                    name="saveManageFranchiseModel.listServiceMarkup[%{#stats.index}].serviceId"
                                                    value="54"/>
                                            <s:hidden
                                                    name="saveManageFranchiseModel.listServiceMarkup[%{#stats.index}].franchiseCode"
                                                    value="%{franchiseCode}"/>
                                        </tr>
                                    </s:if>
                                    <s:if test="shipmentTypeId == 214">
                                        <tr>
                                            <td class="td1"><xms:localization
                                                    text="TNT Int. Economy Markup Rate:"/></td>
                                            <td class="td2" colspan="2"><s:textfield
                                                    name="saveManageFranchiseModel.listServiceMarkup[%{#stats.index}].markupRate"
                                                    value="%{markupRate}" cssClass="form-control"/></td>
                                            <s:hidden
                                                    name="saveManageFranchiseModel.listServiceMarkup[%{#stats.index}].shipmentTypeId"
                                                    value="214"/>
                                            <s:hidden
                                                    name="saveManageFranchiseModel.listServiceMarkup[%{#stats.index}].serviceId"
                                                    value="54"/>
                                            <s:hidden
                                                    name="saveManageFranchiseModel.listServiceMarkup[%{#stats.index}].franchiseCode"
                                                    value="%{franchiseCode}"/>
                                        </tr>
                                    </s:if>
                                </s:iterator>
                            </s:if>
                            <s:else>
                                <tr>
                                    <td class="td1"><xms:localization text="TNT Int. Express Markup Rate:"/></td>
                                    <td class="td2" colspan="2"><s:textfield
                                            name="saveManageFranchiseModel.listServiceMarkup[0].markupRate" value="0"
                                            cssClass="form-control"/></td>
                                    <s:hidden name="saveManageFranchiseModel.listServiceMarkup[0].shipmentTypeId"
                                              value="213"/>
                                    <s:hidden name="saveManageFranchiseModel.listServiceMarkup[0].serviceId"
                                              value="54"/>
                                    <s:hidden name="saveManageFranchiseModel.listServiceMarkup[0].franchiseCode"
                                              value="%{franchiseCode}"/>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="TNT Int. Economy Markup Rate:"/></td>
                                    <td class="td2" colspan="2"><s:textfield
                                            name="saveManageFranchiseModel.listServiceMarkup[1].markupRate" value="0"
                                            cssClass="form-control"/></td>
                                    <s:hidden name="saveManageFranchiseModel.listServiceMarkup[1].shipmentTypeId"
                                              value="214"/>
                                    <s:hidden name="saveManageFranchiseModel.listServiceMarkup[1].serviceId"
                                              value="54"/>
                                    <s:hidden name="saveManageFranchiseModel.listServiceMarkup[1].franchiseCode"
                                              value="%{franchiseCode}"/>
                                </tr>
                            </s:else>
                            <tr>
                                <td class="td1"><xms:localization text="TNT Markup Rate:"/></td>
                                <td class="td2" colspan="2"><s:textfield
                                        name="saveManageFranchiseModel.accountSetup.tntMarkupRate"
                                        value="%{franchise.tntMarkupRate}" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Toll Priority Markup Rate:"/></td>
                                <td class="td2" colspan="2"><s:textfield
                                        name="saveManageFranchiseModel.accountSetup.tollMarkupRate"
                                        value="%{franchise.tollMarkupRate}" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Toll IPEC Markup Rate:"/></td>
                                <td class="td2" colspan="2"><s:textfield
                                        name="saveManageFranchiseModel.accountSetup.tollIpecMarkupRate"
                                        value="%{franchise.tollIpecMarkupRate}" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Startrack Markup Rate:"/></td>
                                <td class="td2" colspan="2"><s:textfield
                                        name="saveManageFranchiseModel.accountSetup.startrackMarkupRate"
                                        value="%{franchise.startrackMarkupRate}" cssClass="form-control"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="UPS Markup Rate:"/></td>
                                <td class="td2" colspan="2"><s:textfield
                                        name="saveManageFranchiseModel.accountSetup.upsMarkupRate"
                                        value="%{franchise.upsMarkupRate}" cssClass="form-control"/></td>
                            </tr>
                        </s:if>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="div_save_franchise_account_setup_result"></div>
</form>
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
    $(document).ready(function () {
        if ($("#sel_franchise").val() == '0') {
            loadingDialog.dialog("open");
            $("#td_address").html("You must be create franchise info base before. ");
            $(".txt_ready_only").attr("readonly", false);
            $("#generalTab").find("li.li_hiden_for_save").hide();
            loadingDialog.dialog("close");
        } else {
            $("#generalTab").find("li").show();
        }
    });
    function saveFranchiseAccountSetup() {
        var data = $("#frmSaveFranchiseAccountSetup").serialize();
        loadingDialog.dialog("open");
        $.post("manage_franchise_account_setup_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                if ($("#sel_franchise").val() == "0") {
                    $("#generalTab").find("li").show();
                    $("#txt_franchise_code").val($("#txt_franchise_code").val() + "00001");
                    $("#txt_franchise_code").prop("readonly", true);
                    $("#td_address").html("");
                    dialog.html("Create Success.");
                    dialog.dialog("open");
                    $('#td_list_franchise').html(res.content);
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
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });

        var buttons = {};
        var height = $(window).height();
        var width = $(window).width();
        height = height * 0.70;
        width = width * 0.9;
        var dialog = $("#div_save_franchise_account_setup_result").dialog({
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
                $("#div_save_franchise_account_setup_result").html("");
            }
        });
        dialog.dialog("option", "title", "Save Account Setup.");
    }


</script>