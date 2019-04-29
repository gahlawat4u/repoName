<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <div class="col-lg-4">
        <div class="portlet box">
            <div class="portlet-header">
                <div class="caption w4">
                    <xms:localization text="Password"/>
                </div>
            </div>
            <div class="portlet-body">
                <form id="frmChangePassword">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="alert alert-success" role="alert" style="display: none">
                                <i class="fa fa-check fa-fw"></i><span><s:actionmessage/></span>
                            </div>
                            <p style="color: red">
                                <xms:localization text="<b>Note:</b>
																</br>
																- Password should contain minimum 8 characters, with at least one letter and one number."/>
                            </p>

                            <div class="form-group">
                                <label class="control-label" for="inputName"><xms:localization
                                        text="Old Password"/></label> <input name="user.oldPassword"
                                                                             value="<s:property value="user.oldPassword" />"
                                                                             type="password" placeholder=""
                                                                             class="form-control alloptions"
                                                                             data-placement="top" maxlength="25"/> <span
                                    class="s30"><s:fielderror fieldName="user.oldPassword"/></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="inputName"><xms:localization
                                        text="New Password"/></label> <input name="user.newPassword"
                                                                             value="<s:property value="user.newPassword" />"
                                                                             type="password" placeholder=""
                                                                             class="form-control alloptions"
                                                                             data-placement="top" maxlength="25"/> <span
                                    class="s30"><s:fielderror fieldName="user.newPassword"/></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="inputName"><xms:localization
                                        text="Confirm New"/></label> <input name="user.confirmPassword"
                                                                            value="<s:property value="user.confirmPassword" />"
                                                                            type="password" placeholder=""
                                                                            class="form-control alloptions"
                                                                            data-placement="top" maxlength="25"/> <span
                                    class="s30"><s:fielderror fieldName="user.confirmPassword"/></span>
                            </div>
                        </div>
                    </div>
                    <div class=" text-right pal pdt10">
                        <div class="row">
                            <div class="col-lg-12">
                                <button class="btn s33 s44" type="button" onclick="doChangePass()">
                                    <xms:localization text="Save"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function doChangePass() {
        var data = $("#frmChangePassword").serialize();
        $.post("settings_change_password.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#Change-tab").html(res.content);
                $("#Change-tab .alert-success").show();
            } else if (res.errorCode == "FIELD_ERROR") {
                $("#Change-tab").html(res.content);
                $("#Change-tab .alert-success").hide();
            } else {
                $("#Change-tab .alert-success").hide();
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            $("#Change-tab .alert-success").hide();
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }


</script>