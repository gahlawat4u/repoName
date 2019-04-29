<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:select list="invoiceList" listKey="invoiceId" name="invoiceId" id="sel_invoice_data" listValue="invoiceCode"
          class="form-control" onchange="searchInvoiceDetail($(this).val())"/>
