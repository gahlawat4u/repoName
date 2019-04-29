<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="count==0 or count==null">
    <s:actionmessage/>
    <s:fielderror/>
</s:if>
<s:else>
    <xms:localization text="Change"/> <s:property value="count"/> <xms:localization text="airbill"/>(s)
    <xms:localization text="successfully! "/>
</s:else>
