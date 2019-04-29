<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="saveCustomerProfileAccountSetupForm">
    <s:hidden name="saveCustomerProfile.accountSetup.profileId" value="%{cusProfile.profileId}"/>
    <s:hidden name="profileNameOld" value="%{cusProfile.profileName}"/>
    <s:hidden name="saveCustomerProfile.accountSetup.franchiseCode" value="%{cusProfile.franchiseCode}"
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
                            <td class="td1"><xms:localization text="Profile Name:"/><span class="s30"></span></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                     name="saveCustomerProfile.accountSetup.profileName"
                                                                     value="%{cusProfile.profileName}"></s:textfield></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Inactive?:"/></td>
                            <td class="td2" colspan="2"><s:checkbox name="saveCustomerProfile.accountSetup.inActive"
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
                                                                  name="saveCustomerProfile.accountSetup.previousCarrier"
                                                                  value="%{cusProfile.previousCarrier}"></s:select></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Web Freight Group:"/></td>
                            <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="customerGroup"
                                                                  class="form-control" listKey="customerGroupId"
                                                                  listValue="customerGroupName"
                                                                  name="saveCustomerProfile.accountSetup.webshipGroupId"
                                                                  value="%{cusProfile.webshipGroupId}"></s:select></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Industry:"/></td>
                            <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="industries"
                                                                  class="form-control" listKey="industryId"
                                                                  listValue="industryName"
                                                                  name="saveCustomerProfile.accountSetup.industryId"
                                                                  value="%{cusProfile.industryId}"></s:select></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Area:"/></td>
                            <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="areas"
                                                                  class="form-control" listKey="areaId"
                                                                  listValue="areaName"
                                                                  name="saveCustomerProfile.accountSetup.areaId"
                                                                  value="%{cusProfile.areaId}"></s:select></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Sales Rep:"/></td>
                            <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="saleReps"
                                                                  class="form-control" listKey="userId"
                                                                  listValue="userName"
                                                                  name="saveCustomerProfile.accountSetup.salesRepId"
                                                                  value="%{cusProfile.salesRepId}"></s:select></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Collector:"/></td>
                            <td class="td2" colspan="2"><s:select headerKey="" headerValue="" list="collectors"
                                                                  class="form-control" listKey="userId"
                                                                  listValue="userName"
                                                                  name="saveCustomerProfile.accountSetup.collectorId"
                                                                  value="%{cusProfile.collectorId}"></s:select></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Business Registration#:"/></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                     name="saveCustomerProfile.accountSetup.registrationId"
                                                                     onkeypress="return formartNumber(event,this,false);"
                                                                     value="%{cusProfile.registrationId}"></s:textfield></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="GST#:"/></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                     name="saveCustomerProfile.accountSetup.gstId"
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
                                                                     name="saveCustomerProfile.accountSetup.dhlAccount"
                                                                     value="%{cusProfile.dhlAccount}"></s:textfield></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Hub Account#:"/></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                     name="saveCustomerProfile.accountSetup.hubAccount"
                                                                     value="%{cusProfile.hubAccount}"></s:textfield></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="TNT Account#:"/></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                     name="saveCustomerProfile.accountSetup.tntAccount"
                                                                     value="%{cusProfile.tntAccount}"></s:textfield></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Toll Priority Account#:"/></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                     name="saveCustomerProfile.accountSetup.tollPriorityAccount"
                                                                     value="%{cusProfile.tollPriorityAccount}"></s:textfield></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Startrack Account#:"/></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                     name="saveCustomerProfile.accountSetup.startrackAccount"
                                                                     value="%{cusProfile.startrackAccount}"></s:textfield></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Startrack Dispatch ID#:"/></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                     name="saveCustomerProfile.accountSetup.dispatchId"
                                                                     value="%{cusProfile.dispatchId}"></s:textfield></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Other Account#:"/></td>
                            <td class="td2" colspan="2"><s:textfield class="form-control alloptions"
                                                                     name="saveCustomerProfile.accountSetup.otherAccount"
                                                                     value="%{cusProfile.otherAccount}"></s:textfield></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Booking Email Notification:"/></td>
                            <td class="td2" colspan="2"><s:checkbox
                                    name="saveCustomerProfile.accountSetup.bookingEmailNotification"
                                    value="%{cusProfile.bookingEmailNotification}"></s:checkbox></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Rejection Notes:"/></td>
                            <td class="td2" colspan="2"><s:textarea
                                    name="saveCustomerProfile.accountSetup.rejectionNote" class="form-control"
                                    value="%{cusProfile.rejectionNote}"></s:textarea></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Enable AGL Warranty:"/></td>
                            <td class="td2" colspan="2"><s:checkbox name="saveCustomerProfile.accountSetup.enableSi"
                                                                    value="%{cusProfile.enableSi}"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</s:form>
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