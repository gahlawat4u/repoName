<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="add-customer-profile-form">
    <table>
        <tr>
            <td><xms:localization text="Select a profile:"/> <span style="color: red">*&nbsp;</span></td>
            <td><s:if test="profileList!=null && profileList.size()>0">
                <s:select name="profileId" id="profileId" list="profileList" listValue="profileName" listKey="profileId"
                          cssClass="form-control"/>
            </s:if> <s:else>
                <select id="profileId" class="form-control">
                    <option value="0"><xms:localization text="No profile"/></option>
                </select>
            </s:else></td>
        </tr>
    </table>
    <s:hidden name="franchiseCode"/>
</s:form>