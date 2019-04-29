<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="count!=0 and count!=null">
    <s:property value="count"/> <xms:localization text="airbill"/>(s) <xms:localization
        text="recalculate successfully! "/>
</s:if>
<s:else>
    <s:actionmessage/>
    <s:fielderror/>
</s:else>
