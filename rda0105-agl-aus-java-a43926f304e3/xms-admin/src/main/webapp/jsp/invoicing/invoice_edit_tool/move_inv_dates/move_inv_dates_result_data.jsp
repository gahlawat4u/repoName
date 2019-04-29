<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:if test="count==0">
    <xms:localization
            text="Frozen invoices on new invoice date selected. Please select a date without frozen invoices."/>
</s:if>
<s:else>
    <s:property value="count"/>
    <xms:localization text="invoices had been moved successfully."/>
</s:else>