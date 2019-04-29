<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:select id="customerCode" name="customerCode" cssClass="form-control" onchange="onCustomerCodeChange()"
          list="customers" listValue="displayName" listKey="customerCode"/>