<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>


Are you sure you want to delete this Email <s:property value="adminEmailModel.email"/>?
<s:form id="admin_email_setting_delete_form">

    <input type="hidden" name="adminEmailModel.id" value='<s:property value="adminEmailModel.id"/>'/>
</s:form>
<s:actionerror/>
<s:actionmessage/>
<s:fielderror/>