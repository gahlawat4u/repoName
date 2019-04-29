<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="bank-form">
    <div id="md-6" title="Edit Bank ">
        <div class="form-group">
            <p class="">
                <xms:localization text="Bank Name: "/>
                <span class="s30">*</span>
            </p>
            <s:textfield name="bank.bankName" class="form-control alloptions"/>
            <span class="text-danger"><s:fielderror fieldName="bank.bankName"/></span>
        </div>
        <div class="form-group">
            <p class="">
                <xms:localization text="Admin Level: "/>
            </p>
            <s:select name="bank.userLevelId" list="userLevels" listValue="userLevelCode + ' - ' + userLevelName"
                      listKey="userLevelId" cssClass="form-control"/>
        </div>
        <div class="form-group">
            <p class="">
                <xms:localization text="Last Modified"/>
                :<span class="s30">*</span>
            </p>
            <s:property value="bank.modifiedDate"/>
            <s:hidden name="bank.modifiedDate" class="form-control alloptions"/>
            <span class="text-danger"><s:fielderror fieldName="bank.modifiedDate"/></span>
        </div>
    </div>
    <s:hidden name="bank.bankId"/>
    <s:hidden name="page"/>
    <s:hidden name="pageSize"/>
</s:form>