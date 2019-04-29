<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<xms:localization text="Booking successs. Your airbill number is "/>
<strong><s:property value="shipmentModel.airbillNumber"/></strong>
<br/>
<s:hidden name="shipmentModel.shipmentId" id="booking_result_shipmentid"/>
<s:if test="confirmationNumber != null && confirmationNumber != ''">
    <xms:localization text="Schedule collection success. Your confirmation number is "/>
    <strong><s:property value="confirmationNumber"/></strong>
</s:if>
<s:actionerror/>