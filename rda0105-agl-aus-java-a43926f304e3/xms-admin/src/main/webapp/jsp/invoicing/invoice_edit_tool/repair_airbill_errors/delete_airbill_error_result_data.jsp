<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="actionmessage!='' or fielderror!=''">
    <xms:localization text="Delete airbill"/> # <s:property value="airbillNumber"/> <xms:localization
        text="successfully! "/>
</s:if>
<s:else>
    <s:actionmessage/>
    <s:fielderror/>
</s:else>
