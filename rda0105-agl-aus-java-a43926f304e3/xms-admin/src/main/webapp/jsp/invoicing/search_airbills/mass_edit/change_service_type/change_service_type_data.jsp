<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_change_service_type">
            <table class="s36 d1">
                <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
                <tr>
                    <td><xms:localization text="Service level"/></td>
                    <td>
                        <select class="form-control" id="serviceLevel" name="serviceLevel">
                            <s:iterator value="serviceLevelList">
                                <s:if test="type=='document'">
                                    <option value="<s:property value="id" />"><s:property value="shipmentTypeName"/> -
                                        <xms:localization text="Document"/></option>
                                </s:if>
                                <s:elseif test="type=='document_inbound'">
                                    <option value="<s:property value="id" />"><s:property value="shipmentTypeName"/> -
                                        <xms:localization text="Document Inbound"/></option>
                                </s:elseif>
                                <s:elseif test="type=='package'">
                                    <option value="<s:property value="id" />"><s:property value="shipmentTypeName"/> -
                                        <xms:localization text="Package"/></option>
                                </s:elseif>
                                <s:elseif test="type=='package_inbound'">
                                    <option value="<s:property value="id" />"><s:property value="shipmentTypeName"/> -
                                        <xms:localization text="Package Inbound"/></option>
                                </s:elseif>
                                <s:else>
                                    <option value="<s:property value="id" />"><s:property
                                            value="shipmentTypeName"/></option>
                                </s:else>
                            </s:iterator>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><xms:localization text="Recalc Customer Cost"/></td>
                    <td><input type="checkbox" name="checkRecalcCustomerCost" value="true" checked="checked"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>