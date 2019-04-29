<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:form id="admin_supply_localization" method="post" enctype="multipart/form-data">
    <xms:localization text="There is no other language."/>
    <input type="hidden" name="isLocalizationSupply" value="1"/>
    <s:hidden name="supply.supplyId"></s:hidden>
</s:form>
