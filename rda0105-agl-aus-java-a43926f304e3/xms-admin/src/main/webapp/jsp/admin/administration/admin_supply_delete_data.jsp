<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:form id="admin_supply_delete" method="post" enctype="multipart/form-data">
    <xms:localization text="Are you sure you want to delete this supply name"/>
    <s:property value="supply.supplyName"/>?
    <input type="hidden" name="isDeleteSupply" value="1"/>
    <s:hidden name="supply.supplyId"></s:hidden>
</s:form>
