<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:select name="collectorId" list="collectors" listValue="displayName" listKey="userId" headerValue="Select Collector"
          headerKey="0" cssClass="form-control"/>
<s:select name="saleRepId" list="saleReps" listValue="displayName" listKey="userId" headerValue="Select Sales Rep"
          headerKey="0" cssClass="form-control"/>