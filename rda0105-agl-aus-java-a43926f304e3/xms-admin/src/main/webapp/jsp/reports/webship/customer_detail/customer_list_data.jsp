<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:i18n_select name="customerCode" cssClass="form-control" list="customers" listKey="customerCode"
               listValue="customerCode + ' - ' + customerAddress.customerName" headerKey="" headerValue="All"
               i18nitem="no" cssStyle="width: 160px;"/>