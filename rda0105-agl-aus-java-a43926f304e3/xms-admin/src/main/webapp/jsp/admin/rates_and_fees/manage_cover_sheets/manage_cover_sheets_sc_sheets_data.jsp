<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0 table-hover" id="service-cover-sheets-table">
    <thead>
    <tr>
        <th width="22"><input type="checkbox" onclick="selectAll($(this))"></th>
        <th><xms:localization text="Carrier Name"/></th>
        <th><xms:localization text="Cover Sheet"/></th>
        <th><xms:localization text="Inbound Cover Sheet"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="scvSheets.isEmpty()">
        <tr>
            <td colspan="4"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="scvSheets">
            <tr>
                <td><input name="serviceId" type="checkbox" value="<s:property value="serviceId"/>"
                           onclick="countService()"></td>
                <td><s:property value="serviceName"/></td>
                <td><s:property value="fileName"/></td>
                <td><s:property value="inboundFileName"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        countService();
    });
</script>
