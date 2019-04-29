<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="email_template_form">
    <div class="col-lg-5">
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><xms:localization text="Template Name"/>: <span class="s30">*</span></td>
                <td class="td2" colspan="2"><s:select id="emailTemplateId" name="emailTemplate.templateId"
                                                      list="emailTemplateList" listKey="templateId"
                                                      listValue="templateName" cssClass="form-control"
                                                      onchange="loadEmailTemplate()"/><span
                        class="text-danger"><s:fielderror fieldName="emailTemplate.templateId"/></span></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Subject"/>: <span class="s30">*</span></td>
                <td class="td2" colspan="2"><s:textfield name="emailTemplate.subject"
                                                         class="form-control alloptions"/><span
                        class="text-danger"><s:fielderror fieldName="emailTemplate.subject"/></span></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Testing for credit card email"/> :</td>
                <td class="td2" colspan="2"><s:textarea name="emailTemplate.templateContent" class="form-control"
                                                        rows="20"></s:textarea><span class="text-danger"><s:fielderror
                        fieldName="emailTemplate.templateContent"/></span></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-4">
        <xms:localization text="For email template, you can use the following variables:"/>
        <br/>
        <s:property value="emailTemplate.variable" escapeHtml="false"/>
    </div>
</form>