<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="stat_follow_ups">
    <s:if test="followUps==null || followUps.size()==0">
        <xms:localization text="No data to display."/>
    </s:if>
    <s:else>
        <table class="table table-hover table-bordered mg0">
            <tr>
                <th><xms:localization text="Follow-Up Date"/></th>
                <th><xms:localization text="Customer"/></th>
                <th><xms:localization text="Sales Rep"/></th>
                <th><xms:localization text="Date Added"/></th>
                <th><xms:localization text="Note"/></th>
            </tr>
            <s:iterator value="followUps">
                <tr>
                    <td><s:property value="followUpDate"/></td>
                    <td><s:property value="customerCode"/> - <s:property value="customerName"/></td>
                    <td><s:property value="saleRepName"/></td>
                    <td><s:property value="modifyDate"/></td>
                    <td><s:property value="note"/></td>
                </tr>
            </s:iterator>
        </table>
    </s:else>
</div>