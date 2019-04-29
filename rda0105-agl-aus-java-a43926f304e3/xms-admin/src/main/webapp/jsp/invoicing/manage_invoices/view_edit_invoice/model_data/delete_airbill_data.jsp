<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_delete_airbill">
    <xms:localization text="Are you sure you want to delete this Airbill # "/>
    <s:property value="airbillNumber"/> ?
    <s:hidden name="shipmentId"></s:hidden>
    <s:hidden name="airbillNumber"></s:hidden>
    <s:hidden name="invoiceId"></s:hidden>
    <s:hidden name="isDeleteAirbill" value="1"></s:hidden>
    <s:hidden name="massEditShipments[]"></s:hidden>
</s:form>
