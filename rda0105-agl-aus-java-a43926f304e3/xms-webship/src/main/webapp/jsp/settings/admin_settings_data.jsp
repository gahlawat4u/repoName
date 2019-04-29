<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <div class="col-lg-8">
        <div class="portlet box">
            <div class="portlet-header">
                <div class="caption w4">
                    <xms:localization text="Admin Settings"/>
                </div>
            </div>
            <div class="portlet-body">
                <div class="row">
                    <form id="admin_settings_form">
                        <s:hidden name="adminSettings.customerCode"/>
                        <div class="col-lg-6">
                            <table style="border-spacing: 5px; border-collapse: separate;">
                                <tr>
                                    <td><label class="control-label" for="inputName"><xms:localization
                                            text="Disable Quotes: "/></label></td>
                                    <td><s:checkbox name="adminSettings.disableQuote"/> <label class="control-label"
                                                                                               for="inputName"><xms:localization
                                            text="Disable Quote Estimates"/></label></td>
                                </tr>
                                <tr>
                                    <td valign="top"><label class="control-label" for="inputName"><xms:localization
                                            text="Address Book: "/></label></td>
                                    <td><s:if test="adminSettings.globalAddressBook">
                                        <input type="radio" name="adminSettings.globalAddressBook" checked="checked"
                                               value="true"/>
                                        <label class="control-label"><xms:localization
                                                text="Global Address Book"/></label>
                                        <br/>
                                        <input type="radio" name="adminSettings.globalAddressBook" value="false"/>
                                        <label class="control-label"><xms:localization
                                                text="User Address Book"/></label>
                                    </s:if> <s:else>
                                        <input type="radio" name="adminSettings.globalAddressBook" value="true"/>
                                        <label class="control-label"><xms:localization
                                                text="Global Address Book"/></label>
                                        <br/>
                                        <input type="radio" name="adminSettings.globalAddressBook" checked="checked"
                                               value="false"/>
                                        <label class="control-label"><xms:localization
                                                text="User Address Book"/></label>
                                    </s:else></td>
                                </tr>
                                <tr>
                                    <td><label class="control-label" for="inputName"><xms:localization
                                            text="History: "/></label></td>
                                    <td><s:checkbox name="adminSettings.individualUserHistory"/> <label
                                            class="control-label" for="inputName"><xms:localization
                                            text="Individual User History"/></label></td>
                                </tr>
                            </table>
                        </div>
                    </form>
                </div>
                <div class=" text-right pal pdt10">
                    <button class="btn s33" type="button" onclick="clearAdminSettings()">
                        <xms:localization text="Clear"/>
                    </button>
                    <button class="btn s33" type="button" onclick="saveAdminSettings()">
                        <xms:localization text="Save"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function saveAdminSettings() {
        var data = $("#admin_settings_form").serialize();
        $.post("webship_settings_admin_settings_save.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                alertDialog.html("<xms:localization text="Updated Successfully!<br/>Please log out and log in again to reflect the changes." />");
                alertDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function clearAdminSettings() {
        var data = $("#admin_settings_form").serialize();
        $.post("webship_settings_admin_settings_clear.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                alertDialog.html("<xms:localization text="Updated Successfully!<br/>Please log out and log in again to reflect the changes." />");
                alertDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }


</script>