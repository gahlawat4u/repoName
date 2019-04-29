<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>


Are you sure you want to delete this Industry
<s:property value="industryModel.industryName"/>
?
<s:form id="industry_delete_form">
    <input type="hidden" name="industryModel.industryId"
           value='<s:property value="industryModel.industryId"/>'/>
    <s:hidden name="page"></s:hidden>
    <s:hidden name="pageSize"></s:hidden>
</s:form>
<s:actionerror/>
<s:actionmessage/>
<s:fielderror/>