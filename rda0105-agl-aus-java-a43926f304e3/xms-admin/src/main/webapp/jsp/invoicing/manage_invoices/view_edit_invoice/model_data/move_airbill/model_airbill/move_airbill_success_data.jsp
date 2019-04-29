<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="hasActionErrors()">
    <s:actionerror/>
</s:if>
<s:else>
    <xms:localization text="Move airbill "/>
    <s:property value="airbillNumber"/>
    <xms:localization text="to invoice "/>
    <s:property value="invoiceCode"/>
    <xms:localization text="success! "/>
</s:else>
