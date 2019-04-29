<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:select list="invoiceCustomerCode" id="select_invoice_code" listValue="customerName" listKey="customerCode"
          cssClass="form-control" name="customerCode" onchange="loadCustomerName()" cssStyle="width: 250px"/>
