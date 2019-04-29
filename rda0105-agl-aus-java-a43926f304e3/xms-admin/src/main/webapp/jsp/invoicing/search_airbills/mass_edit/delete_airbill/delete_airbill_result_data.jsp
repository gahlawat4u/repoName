<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="count!=0 and count!=null">
    <xms:localization text="Delete"/> <s:property value="count"/> <xms:localization text="airbill"/>(s)
    <xms:localization text="successfully! "/>
</s:if>
<s:elseif test="countFail!=0 and countFail!=null and (count==0 or count==null)">
    <xms:localization text="Delete failed"/>
</s:elseif>
<s:else>
    <s:actionmessage/>
    <s:fielderror/>
</s:else>
