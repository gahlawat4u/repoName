<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="stat_sale_person">
    <s:if test="salePerson!=null">
        <table class="table table-hover table-bordered mg0">
            <tr>
                <th><xms:localization text="Customer Total"/></th>
                <th><xms:localization text="Active"/></th>
                <th><xms:localization text="Inactive/Lost"/></th>
            </tr>
            <tr>
                <td><s:property value="salePerson['cus_total']"/></td>
                <td><s:property value="salePerson['cus_active']"/></td>
                <td><s:property value="salePerson['cus_inactive']"/></td>
            </tr>
            <tr>
                <td><xms:localization text="Opportunities"/></td>
                <td><s:property value="salePerson['count']"/></td>
                <td><s:property value="salePerson['amount']"/></td>
            </tr>
            <tr>
                <td><xms:localization text="Sales Calls (Current Week)"/></td>
                <td><s:property value="salePerson['total_call']"/> <xms:localization text="(WTD)"/></td>
                <td><s:property value="salePerson['target_call']"/> <xms:localization text="(Target)"/></td>
            </tr>
            <tr>
                <td><xms:localization text="Success Rate"/></td>
                <td><s:property value="salePerson['success_count']"/> <xms:localization text="(WTD)"/></td>
                <td><s:property value="salePerson['target_success']"/> <xms:localization text="(Target)"/></td>
            </tr>
        </table>
    </s:if>
    <s:else>
        <xms:localization text="No data to display."/>
    </s:else>
</div>