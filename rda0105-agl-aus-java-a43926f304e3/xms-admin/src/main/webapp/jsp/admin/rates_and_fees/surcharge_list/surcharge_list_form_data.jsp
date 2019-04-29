<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="accessorial-form">
    <div class="form-group">
        <table class="table" style="font-size: 11px;">
            <tbody>
            <tr>
                <td class="td1"><xms:localization text="Accessorial Code"/>:<span class="s30">*</span></td>
                <td class="td2 s51" colspan="2"><s:if test="accessorial.accessorialId != ''">
                    <s:property value="accessorial.code"/>
                    <s:hidden name="accessorial.code"/>
                </s:if> <s:else>
                    <s:textfield name="accessorial.code" cssClass="form-control"/>
                    <span class="text-danger"><s:fielderror fieldName="accessorial.code"/></span>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Accessorial Description"/>:<span class="s30">*</span></td>
                <td class="td2 s51" colspan="2"><s:textfield name="accessorial.description" class="form-control"/> <span
                        class="text-danger"><s:fielderror fieldName="accessorial.description"/></span></td>
            </tr>
            <s:if test="accessorial.modifiedDate != null">
                <tr>
                    <td class="td1"><xms:localization text="Last Modified"/>:<span class="s30">*</span></td>
                    <td class="td2 s51" colspan="2"><s:property value="accessorial.modifiedDate"/></td>
                </tr>
            </s:if>
            <tr>
                <td class="td1"><xms:localization text="Accessorial Type"/></td>
                <td class="td2" colspan="2"><s:select name="accessorial.typeId" list="accessorialTypeList"
                                                      cssClass="form-control"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Quoteable"/>:</td>
                <td class="td2 s51" colspan="2"><input type="checkbox" name="accessorial.isQuotable" value="1"
                                                       <s:if test="%{accessorial.isQuotable == 1}">checked="checked"</s:if> />
                </td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Carrier"/>: <span class="s30"> *</span></td>
                <td class="td2" colspan="2"><s:select name="accessorial.carrier" list="serviceList"
                                                      cssClass="form-control" listKey="serviceId"
                                                      listValue="serviceName"/> <span class="text-danger"><s:fielderror
                        fieldName="accessorial.carrier"/></span></td>
            </tr>
            </tbody>
        </table>
    </div>
    <s:hidden name="accessorial.accessorialId"/>
    <s:hidden name="page"/>
    <s:hidden name="pageSize"/>
    <s:hidden id="orderField" name="orderField"/>
    <s:hidden id="orderType" name="orderType"/>
</s:form>