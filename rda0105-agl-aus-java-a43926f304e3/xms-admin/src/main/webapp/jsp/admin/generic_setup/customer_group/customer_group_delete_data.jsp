<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>


Are you sure you want to delete this territory
<s:property value="territoryModel.territoryName"/>
?
<s:form id="territory_delete_form">
    <input type="hidden" name="territoryModel.territoryId"
           value='<s:property value="territoryModel.territoryId"/>'/>
    <s:hidden name="page"></s:hidden>
    <s:hidden name="pageSize"></s:hidden>
</s:form>
<s:actionerror/>
<s:actionmessage/>
<s:fielderror/>