<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0  table-hover" style="table-layout: fixed;">
    <thead>
    <tr>
        <th><xms:localization text="Service Description"/></th>
        <th><xms:localization text="Carrier"/></th>
        <th><xms:localization text="Tier"/></th>
        <th><xms:localization text="Current Value"/></th>
        <th class="w14"><xms:localization text="Cost Basic Table"/></th>
    </tr>
    </thead>
    <tbody>
    <tr id="search-filter">
        <td><s:textfield name="searchFilter.serviceDescription" cssClass="form-control"
                         onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="searchFilter.carrier" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td></td>
        <td><s:textfield name="searchFilter.currentValue" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td></td>
    </tr>
    <s:if test="records.isEmpty()">
        <tr>
            <td colspan="5"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="records">
            <tr data-shipment-type-id='<s:property value="shipmentTypeId"/>'>
                <td><s:property value="shipmentTypeName"/> <s:property value="packageTypeName"/></td>
                <td><s:property value="serviceName"/></td>
                <td></td>
                <td id='sheetName-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>-<s:property value="isPerWeight"/>'>
                    <s:property value="sheetName"/></td>
                <td width="290px"><s:select id="sheetId_%{shipmentTypeId}_%{content}_%{bound}_%{isPerWeight}"
                                            value="sheetId" list="rateSheets" listValue="sheetName" listKey="sheetId"
                                            cssStyle="width: 200px;" cssClass="w12" headerKey="0" headerValue=""/>
                    <button class="w13"
                            onclick="saveSheet('<s:property value="shipmentTypeId"/>','<s:property value="content"/>','<s:property value="bound"/>','<s:property value="isPerWeight"/>')">
                        <xms:localization text="Set"/>
                    </button>
                </td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
