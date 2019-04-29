<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="stat_sale_manager">
    <s:if test="managers==null || managers.size()==0">
        <xms:localization text="No data to display."/>
    </s:if>
    <s:else>
        <table class="table table-hover table-bordered mg0">
            <tr>
                <th><xms:localization text="Sales Rep"/></th>
                <th class="text-right"><xms:localization text="Opportunities"/></th>
                <th class="text-right"><xms:localization text="Estimated Revenue"/></th>
                <th class="text-right"><xms:localization text="Sales/week"/></th>
                <th class="text-right"><xms:localization text="Overdue Opportunity"/></th>
                <th class="text-right"><xms:localization text="Success Rate"/></th>
            </tr>
            <s:iterator value="managers">
                <tr>
                    <td><s:property value="displayName"/></td>
                    <td class="text-right"><s:property value="opportunities"/></td>
                    <td class="text-right"><s:property value="amount"/></td>
                    <td class="text-right"><s:property value="totalCall"/></td>
                    <td class="text-right"><s:property value="opportunitiesOver"/></td>
                    <td class="text-right"><s:property value="successRate"/></td>
                </tr>
            </s:iterator>
        </table>
    </s:else>
</div>