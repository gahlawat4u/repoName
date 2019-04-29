<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:form id="saveQuoteLog">
    <s:hidden name="saveQuoteInfoJson"/>
    <table class="table" style="font-size: 11px; margin-bottom: 0px">
        <tr>
            <td class="td1"><xms:localization text="Base Charge"/></td>
            <td class="td2"><s:property value="quoteModel.baseChargeUnit"/></td>
        </tr>
        <s:iterator value="quoteModel.accessorial">
            <tr>
                <td class="td1"><s:property value="description"/></td>
                <td class="td2"><s:property value="valueCurrency"/></td>
            </tr>
        </s:iterator>
        <tr>
            <td colspan="2" style="background: #686BB1;padding: 1px;"></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Total weight"/></td>
            <td class="td2"><s:property value="quoteModel.weightFomated"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Weight type"/></td>
            <td class="td2"><s:property value="quoteModel.weightType"/></td>
        </tr>
        <tr>
            <td colspan="2" style="background: #005786;padding: 1px;"></td>
        </tr>
        <tr>
            <td class="td1"><b><xms:localization text="Total Charge"/></b></td>
            <td class="td2"><s:property value="quoteModel.totalChargeUnit"/></td>
        </tr>
        <tr>
            <td colspan="2" style="background: #005786;padding: 1px;"></td>
        </tr>
        <s:if test="%{dhlCapabilityVo != null}">
            <s:if test="%{dhlCapabilityVo.actionStatus == 'SUCCESS'}">
                <tr>
                    <td class="td1"><b><xms:localization text="Pickup Date"/></b></td>
                    <td class="td2"><s:property value="dhlCapabilityVo.pickupDate"/></td>
                </tr>
                <tr>
                    <td class="td1"><b><xms:localization text="Total Transit Days"/></b></td>
                    <td class="td2"><s:property value="dhlCapabilityVo.totalTransitDays"/><xms:localization
                            text="(day)"/></td>
                </tr>
                <tr>
                    <td class="td1"><b><xms:localization text="Booking Time"/></b></td>
                    <td class="td2"><s:property value="dhlCapabilityVo.bookingTime"/></td>
                </tr>
                <tr>
                    <td class="td1"><b><xms:localization text="Pickup CutOff Time"/></b></td>
                    <td class="td2"><s:property value="dhlCapabilityVo.pickupCutoffTime"/></td>
                </tr>
                <tr>
                    <td class="td1"><b><xms:localization text="Delivery Date"/></b></td>
                    <td class="td2"><s:property value="dhlCapabilityVo.deliveryDate"/></td>
                </tr>
            </s:if>
            <s:if test="%{dhlCapabilityVo.actionStatus == ''}">
                <tr>
                    <td class="td1"><b><xms:localization
                            text="Selected service is not available. please select those available services"/></b></td>
                    <td class="td2">
                        <s:iterator value="dhlCapabilityVo.availSvrs">
                            <s:property/>
                            <br/>
                        </s:iterator>
                    </td>
                </tr>
            </s:if>
            <s:if test="%{dhlCapabilityVo.actionStatus == 'Error'}">
                <tr>
                    <td class="td1"><b><xms:localization
                            text="There was an error while checking service capability for the shipment"/></b></td>
                    <td class="td2">
                        <s:iterator value="dhlCapabilityVo.errorList">
                            <s:property value="errCode" escapeXml="true"/>:<s:property value="errMsg" escapeXml="true"/>
                            <br/>
                        </s:iterator>
                    </td>
                </tr>
            </s:if>
        </s:if>
        <tr>
            <td colspan="2">
                <p><xms:localization text="Quote is an estimate. Additional fees may apply."/></p>
            </td>
        </tr>
    </table>
</s:form>
