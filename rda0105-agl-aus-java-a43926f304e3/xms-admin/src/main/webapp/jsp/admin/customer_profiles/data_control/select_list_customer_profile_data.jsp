<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:select list="customers" class="form-control" listKey="profileId" listValue="profileName"
          onchange="loadInfoProfile($(this).val())" id="sel_customer_profiles" headerKey="-1"
          headerValue="Select a Customer" value='%{profileId}'></s:select>