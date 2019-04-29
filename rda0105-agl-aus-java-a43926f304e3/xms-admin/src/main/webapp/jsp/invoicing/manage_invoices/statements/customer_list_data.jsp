<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<s:i18n_select id="customerCode" cssClass="form-control" name="customerCode" list="customers" listKey="customerCode"
               listValue="displayName" headerKey="" headerValue="Select a Customer" i18nitem="no"
               onchange="statementDetail()"/>