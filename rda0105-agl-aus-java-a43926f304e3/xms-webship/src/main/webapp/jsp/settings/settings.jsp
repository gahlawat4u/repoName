<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="tab-general">
    <div class="row mbl">
        <div class="col-lg-12">
            <div class="col-md-12">
                <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-12">
                    <div class="portlet box">
                        <div class="portlet-header">
                            <div class="caption">
                                <xms:localization text="Settings"/>
                            </div>
                        </div>
                        <div class="portlet-body" style="padding: 0px;">
                            <ul id="user-settings-tab" class="nav nav-tabs responsive">
                                <li class="active" style="margin-left: 10px;"><a href="#Settings-tab" data-toggle="tab"><xms:localization
                                        text="User Settings"/></a></li>
                                <li><a href="#Address-tab" data-toggle="tab"><xms:localization
                                        text="Address Default"/></a></li>
                                <li><a href="#Change-tab" data-toggle="tab"><xms:localization
                                        text="Change Password"/></a></li>
                                <li><a href="#Dimensions-tab" data-toggle="tab"><xms:localization
                                        text="Dimensions"/></a></li>
                                <s:if test="hasAdminFunction==1">
                                    <li><a href="#admin-settings-tab" data-toggle="tab"><xms:localization
                                            text="Admin Settings"/></a></li>
                                    <li><a href="#user-list-tab" data-toggle="tab"><xms:localization
                                            text="User List"/></a></li>
                                </s:if>
                            </ul>
                            <div id="generalTabContent" class="tab-content responsive">
                                <div id="Settings-tab" class="tab-pane fade in active">
                                    <div class="row">
                                        <div class="col-lg-8">
                                            <div class="portlet box">
                                                <div class="portlet-header">
                                                    <div class="caption w4">
                                                        <xms:localization text="Default Settings"/>
                                                    </div>
                                                </div>
                                                <div class="portlet-body">
                                                    <div class="row">
                                                        <form id="frmUserSettings">
                                                            <div class="col-lg-6">
                                                                <div class="form-group">
                                                                    <s:hidden name="settings.customerCode"/>
                                                                    <div class="alert alert-danger" role="alert"
                                                                         style="display: none">
                                                                        <i class="fa fa-exclamation-triangle fa-fw"></i>
                                                                        <span id="save-error-messsage"></span>
                                                                    </div>
                                                                    <div class="alert alert-success" role="alert"
                                                                         style="display: none">
                                                                        <i class="fa fa-check fa-fw"></i> <span
                                                                            id="save-success-message"></span>
                                                                    </div>
                                                                    <label class="control-label"
                                                                           for="inputName"><xms:localization
                                                                            text="Billing Party"/></label>
                                                                    <s:select name="settings.billingParty"
                                                                              list="billingTypeList" listKey="billingId"
                                                                              listValue="billingName"
                                                                              cssClass="form-control" headerKey="0"
                                                                              headerValue="No default"
                                                                              onchange="onBillingTypeChange()"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label"
                                                                           for="inputName"><xms:localization
                                                                            text="Billing Account"/></label> <input
                                                                        name="settings.billingAccount"
                                                                        value="<s:property value="settings.billingAccount" />"
                                                                        type="text" placeholder=""
                                                                        class="form-control alloptions"
                                                                        data-placement="top" maxlength="25"
                                                                        <s:if test="settings.billingParty==1">readonly="readonly"</s:if> />
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label"
                                                                           for="inputName"><xms:localization
                                                                            text="Duties BILL TO"/></label>
                                                                    <s:select name="settings.dutiesBillTo"
                                                                              list="billingTypeList" listKey="billingId"
                                                                              listValue="billingName"
                                                                              cssClass="form-control" headerKey="0"
                                                                              headerValue="No default"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label" for="inputName">
                                                                        <xms:localization
                                                                                text="Duties Account"/></label> <input
                                                                        name="settings.dutiesAccount"
                                                                        value="<s:property value="settings.dutiesAccount" />"
                                                                        type="text" placeholder=""
                                                                        class="form-control alloptions"
                                                                        data-placement="top" maxlength="25"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label" for="inputName">
                                                                        <xms:localization
                                                                                text="Shipper's Reference"/></label>
                                                                    <input name="settings.defaultShipperReference"
                                                                           value="<s:property value="settings.defaultShipperReference" />"
                                                                           type="text" placeholder=""
                                                                           class="form-control alloptions"
                                                                           data-placement="top" maxlength="25"/>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-6">
                                                                <div class="form-group">
                                                                    <label class="control-label" for="inputName">
                                                                        <xms:localization text="Service Type"/></label>
                                                                    <s:select name="settings.defaultServiceType"
                                                                              list="shipmentTypeList"
                                                                              listKey="shipmentTypeId"
                                                                              listValue="shipmentTypeName"
                                                                              cssClass="form-control"
                                                                              onchange="changeShipmentType()"
                                                                              headerKey="0" headerValue="No default"/>
                                                                </div>
                                                                <div class="form-group" id="package-list">
                                                                    <label class="control-label" for="inputName">
                                                                        <xms:localization text="Package Type"/></label>
                                                                    <s:select name="settings.defaultPackageType"
                                                                              list="packageList" listKey="packageId"
                                                                              listValue="packageName"
                                                                              cssClass="form-control" headerKey="0"
                                                                              headerValue="No default"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label" for="inputName">
                                                                        <xms:localization text="Trading Terms"/></label>
                                                                    <s:select name="settings.defaultTermsOfTrade"
                                                                              list="tradeTypeList" listKey="tradeId"
                                                                              listValue="tradeName"
                                                                              cssClass="form-control" headerKey="0"
                                                                              headerValue="No default"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label" for="inputName">
                                                                        <xms:localization
                                                                                text="Collection Type"/></label>
                                                                    <s:select name="settings.defaultCollection"
                                                                              list="collectionTypeList"
                                                                              listKey="collectionTypeId"
                                                                              listValue="collectionTypeName"
                                                                              cssClass="form-control" headerKey="0"
                                                                              headerValue="No default"/>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <div class=" text-right pal pdt10">
                                                        <button class="btn s33" type="button" onclick="saveSettings()">
                                                            <xms:localization text="Save"/>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="Address-tab" class="tab-pane fade in"></div>
                                <div id="Change-tab" class="tab-pane fade in"></div>
                                <div id="Dimensions-tab" class="tab-pane fade in"></div>
                                <s:if test="hasAdminFunction==1">
                                    <div id="admin-settings-tab" class="tab-pane fade in"></div>
                                    <div id="user-list-tab" class="tab-pane fade in"></div>
                                </s:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/webship/customer/pages/webship-settings.js"></script>
<script type="text/javascript">
    var activeTab = "";
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        activeTab = $('ul#user-settings-tab li.active a').attr('href');
        switch (activeTab) {
            case "#Settings-tab":
                break;
            case "#Address-tab":
                loadAddressDefaultTab();
                break;
            case "#Change-tab":
                loadChangePasswordTab();
                break;
            case "#Dimensions-tab":
                loadDimensionTab();
                break;
            case "#admin-settings-tab":
                loadAdminSettingsTab();
                break;
            case "#user-list-tab":
                loadUserListTab();
                break;
        }
    });

//    $(document).ready(function () {
//        changeShipmentType();
//    });

    function changeShipmentType() {
        var serviceId = $("#settings_defaultServiceType option:selected").val();
        var data = {
            "settings.defaultServiceType": serviceId
        };
        $.post("settings_user_settings_change_service.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#package-list").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function saveSettings() {
        var data = $("#frmUserSettings").serialize();
        console.log(data);
        $.post("settings_user_settings_save.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#save-success-message").html("Saved succesfully.");
                $("#save-error-message").html("");
                $("#Settings-tab .alert-danger").hide();
                $("#Settings-tab .alert-success").show();
            } else {
                $("#save-success-message").html("");
                $("#save-error-message").html(res.errorMsg);
                $("#Settings-tab .alert-danger").show();
                $("#Settings-tab .alert-success").hide();
            }
        }).fail(function () {
            $("#save-success-message").html("");
            $("#save-error-message").html("<xms:localization text="System internal error, please contact administrator." />");
            $("#Settings-tab .alert-danger").show();
            $("#Settings-tab .alert-success").hide();
        });
    }

    function loadChangePasswordTab() {
        $.post("settings_change_password.ix?reqType=json", "", function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#Change-tab").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function loadDimensionTab() {
        $.post("settings_load_dimension_list.ix?reqType=json", "", function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#Dimensions-tab").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function loadAddressDefaultTab() {
        $.post("settings_address_default.ix?reqType=json", "", function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#Address-tab").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function loadAdminSettingsTab() {
        $.post("webship_settings_admin_settings.ix?reqType=json", "", function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#admin-settings-tab").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function loadUserListTab() {
        $.post("webship_settings_user_list.ix?reqType=json", "", function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#user-list-tab").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function onBillingTypeChange() {
        var billingType = $("#settings_billingParty option:selected").val();
        if (billingType == 0 || billingType == 1) {
            $("input[name='settings.billingAccount']").attr("readonly", "readonly");
        } else {
            $("input[name='settings.billingAccount']").removeAttr("readonly");
        }
    }
</script>
<!--END CONTENT-->