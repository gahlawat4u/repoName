<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:i18n_select list="listShipmentType" cssClass="form-control" name="searchAirbillFilter.serviceLevel"
               listKey="shipmentTypeKey" listValue="shipmentTypeName" headerKey="" headerValue="All"/>