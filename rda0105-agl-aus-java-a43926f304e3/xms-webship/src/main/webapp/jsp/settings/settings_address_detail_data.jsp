<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<p>
    <s:property value="address.contactName"/>
</p>

<p>
    <s:property value="address.companyName"/>
</p>

<p>
    <s:property value="address.address1"/>
</p>

<p>
    <s:property value="address.address2"/>
</p>

<p>
    <s:property value="address.city"/>
    &nbsp;
    <s:property value="address.postalCode"/>
</p>

<p>
    <s:property value="address.countryName"/>
</p>