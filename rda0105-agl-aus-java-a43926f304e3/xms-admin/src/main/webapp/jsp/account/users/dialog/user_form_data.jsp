<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="user-form">
    <s:hidden name="user.userId"/>
    <s:hidden name="user.targetPhoneCall"/>
    <s:hidden name="user.overdueDay"/>
    <s:hidden name="user.targetSuccess"/>
    <table>
        <tr>
            <td colspan="2"><s:if test="hasActionErrors()">
                <span style="color: red"><s:actionerror/></span>
            </s:if> <s:if test="hasActionMessages()">
                <span style="color: blue"><s:actionmessage/></span>
            </s:if> <s:if test="hasFieldErrors()">
                <span style="color: red"><s:fielderror/></span>
            </s:if></td>
        </tr>
        <tr>
            <td><xms:localization text="Customer #"/>: <span style="color: red">*</span></td>
            <td><s:textfield name="user.userCode" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td><xms:localization text="User Name"/>: <span style="color: red">*</span></td>
            <td><s:textfield name="user.userName" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Password"/>: <span style="color: red">*</span></td>
            <td><input type="text" name="user.password" class="form-control"
                       value='<s:property value="user.password" />'/></td>
        </tr>
        <tr>
            <td><xms:localization text="Admin Level"/>: <span style="color: red">*</span></td>
            <td><s:select name="user.userLevelId" list="userLevels" listValue="userLevelCode + ' - ' + userLevelName"
                          listKey="userLevelId" headerValue="" headerKey="" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Is Collector?"/>:</td>
            <td><s:checkbox name="user.isCollector"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Display Name"/>: <span style="color: red">*</span></td>
            <td><s:textfield name="user.displayName" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Email Address"/>: <span style="color: red">*</span></td>
            <td><s:textfield name="user.email" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Phone No"/>:</td>
            <td><s:textfield name="user.phone" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Fax No"/>:</td>
            <td><s:textfield name="user.fax" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Force Password Change"/>:</td>
            <td><s:checkbox name="user.isRequireChangePassword"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Language"/>:</td>
            <td><select name="user.language">
                <option value="0"><xms:localization text="English"/></option>
            </select></td>
        </tr>
        <tr>
            <td colspan="2" align="right">
                <button class="btn s37" type="button" onclick="onSave()">
                    <xms:localization text="Save"/>
                </button>
                <button class="btn s37" type="button" onclick="onCancel()">
                    <xms:localization text="Cancel"/>
                </button>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    function onCancel() {
        $("#user-dialog").dialog("close");
        $("#user-dialog").html("");
    }

    function onSave() {
        var data = $("#user-form").serialize();
        loadingDialog.dialog("open");
        $.post("manage_users_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            console.log(res.errorCode);
            if (res.errorCode == "SUCCESS") {
                $("#user-dialog").dialog("close");
                $("#user-dialog").html("");
                search(1);
                messageDialog.html("Save successfully");
                messageDialog.dialog("open");
            } else if (res.errorCode == "FIELD_ERROR") {
                $("#user-dialog").html(res.content);
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