<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="other-import-form">
    <s:hidden name="importType"/>
    <s:hidden name="billingFilePath"/>
    <s:hidden name="selDateFormat"/>
    <s:hidden name="billingDataStr"/>
    <s:hidden name="otherCarrier" value="true"/>
    <s:hidden name="isInternationalShipment"/>
    <s:hidden name="applyCustomerTax"/>
    <s:hidden name="applyCarrierTax"/>
    <s:if test="importType == 'other_carrier'">
        <p>
            <span class="s30"><b> <xms:localization text="* Required Fields:"/></b></span> <br/>
            <xms:localization text="- Airbill Number, Ship Date."/>
        </p>
        <table class="table  table-bordered mg0" id="address-import-table">
            <thead>
            <tr bgcolor="#F5F5F5">
                <s:iterator begin="1" end="totalColumns" status="counter">
                    <th><xms:localization text="Field"/> <s:property value="#counter.count"/></th>
                </s:iterator>
            </tr>
            <tr>
                <s:iterator begin="1" end="totalColumns" status="counter">
                    <td><s:select name="selectedImportFields[%{#counter.index}]" list="importFields"
                                  cssClass="form-control sel-box" headerKey="0" headerValue=""/></td>
                </s:iterator>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="rowData">
                <tr>
                    <s:iterator begin="1" end="totalColumns" status="counter">
                        <td data-field="1"><s:property value="cellData[#counter.index]"/></td>
                    </s:iterator>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </s:if>
</form>