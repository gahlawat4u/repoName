<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:form id="service-form">
    <div class="row">
        <div class="col-lg-6">
            <s:if test="hasActionMessages()">
                <div class="alert alert-danger" role="alert">
                    <s:actionmessage/>
                </div>
            </s:if>
            <table class="table" style="font-size: 11px;">
                <tr>
                    <td class="td1"><xms:localization text="Service Name"/>:<span class="s30">*</span></td>
                    <td class="td2"><s:if
                            test="service == null || service.shipmentTypeId == null || service.shipmentTypeId == '' || service.allowChangeName">
                        <s:textfield cssClass="form-control" name="service.shipmentTypeName"/>
                    </s:if> <s:else>
                        <s:textfield cssClass="form-control" name="service.shipmentTypeName" readonly="true"/>
                    </s:else>
                        <span class="text-danger"><s:fielderror fieldName="service.shipmentTypeName"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="EDI Description"/>:<span class="s30">*</span></td>
                    <td class="td2"><s:if
                            test="service == null || service.shipmentTypeId == null || service.shipmentTypeId == '' || service.allowChangeName">
                        <s:textfield cssClass="form-control" name="service.ediDescription"/>
                    </s:if> <s:else>
                        <s:textfield cssClass="form-control" name="service.ediDescription" readonly="true"/>
                    </s:else>
                        <span class="text-danger"><s:fielderror fieldName="service.ediDescription"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Service Code"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.serviceCode"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Basic Charge"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.basicCharge"
                                                 onkeypress="return formartNumber(event, this, true)"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Per Kg Charge"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.perKg"
                                                 onkeypress="return formartNumber(event, this, true)"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Service Priority"/>:<span class="s30">*</span></td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.servicePriority"
                                                 onkeypress="return formartNumber(event, this, false)"/> <span
                            class="text-danger"><s:fielderror fieldName="service.servicePriority"/></span></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Documents Local Product Code"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.localProductCodeDoc"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Documents Global Product Code"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.globalProductCodeDoc"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Non-Document Local Product Code"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.localProductCodeNonDoc"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Non-Document Global Product Code"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.globalProductCodeNonDoc"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Documents Outbound Tax"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.docOutboundTax"
                                                 onkeypress="return formartNumber(event, this, true)"/></td>
                </tr>
            </table>
        </div>
        <div class="col-lg-6">
            <table class="table" style="font-size: 11px;">
                <tr>
                    <td class="td1"><xms:localization text="Documents Inbound Tax"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.docInboundTax"
                                                 onkeypress="return formartNumber(event, this, true)"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Non-Document Outbound Tax"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.nonDocOutboundTax"
                                                 onkeypress="return formartNumber(event, this, true)"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Non-Document Inbound Tax"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.nonDocInboundTax"
                                                 onkeypress="return formartNumber(event, this, true)"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Service Group"/>:</td>
                    <td class="td2"><s:textfield cssClass="form-control" name="service.serviceGroup"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Show At Web Freight"/>:</td>
                    <td class="td2"><input type="checkbox" value="1" name="service.showStatus"
                                           <s:if test="service.showStatus == 1">checked="checked"</s:if>></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Document"/>:</td>
                    <td class="td2"><input type="checkbox" value="true" name="service.document"
                                           <s:if test="service.document">checked="checked"</s:if>></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Document Inbound"/>:</td>
                    <td class="td2"><input type="checkbox" value="true" name="service.documentInbound"
                                           <s:if test="service.documentInbound">checked="checked"</s:if>></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Package"/>:</td>
                    <td class="td2"><input type="checkbox" value="true" name="service.package"
                                           <s:if test="service.package">checked="checked"</s:if>></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Package Inbound"/>:</td>
                    <td class="td2"><input type="checkbox" value="true" name="service.packageInbound"
                                           <s:if test="service.packageInbound">checked="checked"</s:if>></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Allow Non Carrier"/>:</td>
                    <td class="td2"><input type="checkbox" value="true" name="service.allowNonCarrier"
                                           <s:if test="service.allowNonCarrier">checked="checked"</s:if>></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Allow Carrier"/>:</td>
                    <td class="td2"><input type="checkbox" value="true" name="service.allowCarrier"
                                           <s:if test="service.allowCarrier">checked="checked"</s:if>></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Start with Carrier Name"/>:</td>
                    <td class="td2"><input type="checkbox" value="true" name="service.startWithCarrierName"
                                           <s:if test="service.startWithCarrierName">checked="checked"</s:if>></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Calculate Add Perweight"/>:</td>
                    <td class="td2"><input type="checkbox" value="1" name="service.perWeightStatus"
                                           <s:if test="service.perWeightStatus == 1">checked="checked"</s:if>></td>
                </tr>
            </table>
        </div>
    </div>
    <s:hidden name="service.shipmentTypeId"/>
    <s:hidden name="service.allowChangeName"/>
    <s:hidden name="oldServiceName"/>
    <s:hidden name="oldEdiCode"/>
    <s:hidden name="carrierId"/>
    <s:hidden name="page"/>
    <s:hidden name="pageSize"/>
</s:form>