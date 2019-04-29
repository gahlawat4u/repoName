<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_edit_sender_address">
    <s:hidden name="senderAddress.addressId"></s:hidden>
    <div id="md-6" title="Editing Sender Address">
        <div class="form-group">
            <p align="center">
                <b><xms:localization text="Editing Address"/></b>
            </p>
            <table class="table" style="font-size: 11px;">
                <tr>
                    <td class="td1"><xms:localization text="Name:"/></td>
                    <td class="td2"><s:textfield name="senderAddress.companyName"
                                                 cssClass="form-control alloptions"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Attention:"/></td>
                    <td class="td2"><s:textfield name="senderAddress.contactName"
                                                 cssClass="form-control alloptions"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Address 1:"/></td>
                    <td class="td2"><s:textfield name="senderAddress.address"
                                                 cssClass="form-control alloptions"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Address 2:"/></td>
                    <td class="td2"><s:textfield name="senderAddress.address2"
                                                 cssClass="form-control alloptions"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="City:"/></td>
                    <td class="td2"><s:textfield name="senderAddress.city"
                                                 cssClass="form-control alloptions"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="State:"/></td>
                    <td class="td2"><s:textfield name="senderAddress.state"
                                                 cssClass="form-control alloptions"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Zip:"/></td>
                    <td class="td2"><s:textfield name="senderAddress.postalCode"
                                                 cssClass="form-control alloptions"></s:textfield></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Country:"/></td>
                    <td class="td2">
                        <s:select list="countryList" listKey="countryId" listValue="displayName"
                                  name="senderAddress.country"></s:select>
                    </td>
                </tr>
                <s:if test="invoiceStatus == 1">
                    <tr>
                        <td class="td1" colspan="2" style="color: red;"><xms:localization
                                text="Airbill is frozen. Editing is disabled"/></td>
                    </tr>
                </s:if>
            </table>
        </div>
    </div>
</s:form>