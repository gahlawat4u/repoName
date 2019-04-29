<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table>
    <tr>
        <td><xms:localization text="Select a profile"/>: <span style="color: red">*&nbsp;</span></td>
        <td><s:if test="profiles!=null && profiles.size()>0">
            <s:select id="profileId" list="profiles" listValue="profileName" listKey="profileId"
                      cssClass="form-control"/>
        </s:if> <s:else>
            <select id="profileId" class="form-control">
                <option value="0"><xms:localization text="No profile"/></option>
            </select>
        </s:else></td>
    </tr>
</table>