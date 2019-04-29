<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<form id="reminder_letter_settings_form">
    <s:hidden name="reminderStart.settingId"/>
    <s:hidden name="reminderStart.settingName"/>
    <s:hidden name="reminderEnd.settingId"/>
    <s:hidden name="reminderEnd.settingName"/>
    <table class="s36">
        <tbody>
        <tr>
            <td colspan="2" id="reminder_letter_settings_errors"><span class="text-danger"><s:fielderror/></span></td>
        </tr>
        <tr>
            <td class="caption b17" colspan="2"><xms:localization text="Generate Reminder Letter Settings"/></td>
        </tr>
        <tr>
            <td height="5" colspan="3"></td>
        </tr>
        <tr>
            <td><xms:localization text="Days from Invoice Date"/>:</td>
            <td><xms:localization text="Start"/>:</td>
            <td width="60"><s:textfield name="reminderStart.settingValue" cssClass="form-control"/></td>
            <td><xms:localization text="End"/>:</td>
            <td width="60"><s:textfield name="reminderEnd.settingValue" cssClass="form-control"/></td>
        </tr>
        </tbody>
    </table>
</form>