<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<form id="reminder_letter_email_letter_form">
    <s:hidden name="emailLetter.templateId"/>
    <s:hidden name="emailLetter.templateName"/>
    <table class="table" style="font-size: 11px; margin-bottom: 0px">
        <tr id="email_letter_errors">
            <td class="td1">&nbsp;</td>
            <td class="td2"><span class="text-danger"><s:fielderror/></span></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="E-mail Subject:"/></td>
            <td class="td2"><s:textfield name="emailLetter.subject" cssClass="form-control alloptions"
                                         maxlength="25"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="E-mail Body:"/></td>
            <td class="td2"><s:textarea name="emailLetter.templateContent" cssClass="form-control"
                                        cssStyle="height: 450px;"></s:textarea></td>
        </tr>
    </table>
    <table class="s36 b24">
        <tr>
            <td><s:checkbox name="emailLetter.invoiceAttachment"/></td>
            <td><xms:localization text="Attach Invoice"/></td>
            <td><s:checkbox name="emailLetter.statementAttachment"/></td>
            <td><xms:localization text="Attach Statement of Accounts"/></td>
        </tr>
    </table>
</form>