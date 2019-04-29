<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered  table-hover mg0 " id="datatable1">
    <thead>
    <tr>
        <th>Customer #</th>
        <th>User Name</th>
        <th>IP Address</th>
        <th>Date/Time</th>
    </tr>
    </thead>
    <tbody>
    <s:if test="loginLogListModels.records.size != 0">
        <s:iterator value="loginLogListModels.records">
            <tr>
                <td><s:property value="franchiseCode"/></td>
                <td><s:property value="userName"/></td>
                <td><s:property value="IpAddress"/></td>
                <td><s:property value="loginDate"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="4"><xms:localization text="No record to view"/> ...</td>
        </tr>
    </s:else>
    </tbody>
</table>

<div class="dataTables_paginate">
    <s:if test="!loginLogListModels.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{loginLogListModels.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="loginLogListModels.pageRange" status="count">
        <s:if test="%{loginLogListModels.pageRange[#count.index] == loginLogListModels.currentPage}">
            <a class="paginate_button current"><s:property value="loginLogListModels.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!loginLogListModels.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{loginLogListModels.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>