<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<select class="form-control" id="shipment_type" name="shipment_type" onchange="changeSheetType()">
    <s:iterator value="rateSheetType">
        <s:if test="type=='document'">
            <option value="<s:property value="id" />"><s:property value="shipmentTypeName"/> - <xms:localization
                    text="Document"/></option>
        </s:if>
        <s:elseif test="type=='document_inbound'">
            <option value="<s:property value="id" />"><s:property value="shipmentTypeName"/> - <xms:localization
                    text="Document Inbound"/></option>
        </s:elseif>
        <s:elseif test="type=='package'">
            <option value="<s:property value="id" />"><s:property value="shipmentTypeName"/> - <xms:localization
                    text="Package"/></option>
        </s:elseif>
        <s:elseif test="type=='package_inbound'">
            <option value="<s:property value="id" />"><s:property value="shipmentTypeName"/> - <xms:localization
                    text="Package Inbound"/></option>
        </s:elseif>
        <s:elseif test="type=='pak'">
            <option value="<s:property value="id" />">
                <s:property
                        value="shipmentTypeName"/> -
                <xms:localization
                        text="Pak"/></option>
        </s:elseif>
        <s:elseif test="type=='pak_inbound'">
            <option value="<s:property value="id" />">
                <s:property
                        value="shipmentTypeName"/> -
                <xms:localization
                        text="Pak Inbound"/></option>
        </s:elseif>
        <s:else>
            <option value="<s:property value="id" />"><s:property value="shipmentTypeName"/></option>
        </s:else>
    </s:iterator>
</select>
