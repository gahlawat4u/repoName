<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/xms-tags" prefix="xms" %>
<s:if test="countSuccess==0 and countUnSuccess!=0">
    <s:property value="countUnSuccess"/> <xms:localization text="address(s) duplicated!"/>
</s:if>
<s:elseif test="countSuccess!=0 and countUnSuccess!=0">
    <xms:localization text="Import"/> <s:property value="countSuccess"/> <xms:localization
        text="address(s) successfully!"/> <br>
    <s:property value="countUnSuccess"/> <xms:localization text="address(s) duplicated!"/>
</s:elseif>
<s:else>
    <xms:localization text="Import"/> <s:property value="countSuccess"/> <xms:localization
        text="address(s) successfully!"/>
</s:else>
