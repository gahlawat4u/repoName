<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0">
    <thead>
    <tr>
        <th><xms:localization text="Setting"/></th>
        <th><xms:localization text="Corporate Admin"/></th>
        <th><xms:localization text="Franchise/Group Owner or Agent"/></th>
        <th><xms:localization text="Accounting"/></th>
        <th><xms:localization text="Sales Manager"/></th>
        <th><xms:localization text="Sales Rep"/></th>
        <th><xms:localization text="Telemarketer"/></th>
        <th><xms:localization text="Carrier (e.g. DHL) Login"/></th>
        <th><xms:localization text="Carriers Services Failures"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="permissionModels.records.size != 0">
        <s:iterator value="permissionModels.records">
            <tr>
                <td><s:property value="setting"/></td>
                <td><s:checkbox name="adminPermission" userLevel="2" perId="%{perId}"
                                onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                <td><s:checkbox name="franchisePermission" userLevel="3" perId="%{perId}"
                                onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                <td><s:checkbox name="accountPermission" userLevel="4" perId="%{perId}"
                                onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                <td><s:checkbox name="salePermission" userLevel="8" perId="%{perId}"
                                onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                <td><s:checkbox name="saleRepPermission" userLevel="11" perId="%{perId}"
                                onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                <td><s:checkbox name="telePermission" userLevel="9" perId="%{perId}"
                                onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                <td><s:checkbox name="carrierPermission" userLevel="6" perId="%{perId}"
                                onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
                <td><s:checkbox name="carrierServicePermission" userLevel="7" perId="%{perId}"
                                onclick="javascript:updatePermission($(this).is(':checked'), $(this).attr('perId'),$(this).attr('userLevel'));"></s:checkbox></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="9"><xms:localization text="No record to view"/> ...</td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate">
    <s:hidden value="%{permissionModels.currentPage}" id="hid_curren_page"></s:hidden>
    <s:if test="!permissionModels.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{permissionModels.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="permissionModels.pageRange" status="count">
        <s:if test="%{permissionModels.pageRange[#count.index] == permissionModels.currentPage}">
            <a class="paginate_button current"><s:property value="permissionModels.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!permissionModels.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{permissionModels.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>