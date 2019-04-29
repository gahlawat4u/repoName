<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:select id="customer_select_list" list="customerList" listKey="customerCode"
          listValue="customerCode + ' - ' + customerName" cssClass="form-control" onchange="onSelectCustomer()"/>