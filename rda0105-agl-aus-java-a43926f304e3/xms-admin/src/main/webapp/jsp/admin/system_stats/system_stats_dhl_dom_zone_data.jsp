<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered  table-hover mg0 " id="datatable1">
    <thead>
    <tr>
        <th>App Code</th>
        <th>App Zone</th>
        <th>Country Name</th>
        <th>Region</th>
    </tr>
    </thead>
    <tbody>
    <s:if test="dhlCountryModels.records.size != 0">
        <s:iterator value="dhlCountryModels.records">
            <tr>
                <td><s:property value="dhlApCode"/></td>
                <td><s:property value="dhlApZone"/></td>
                <td><s:property value="dhlCountryName"/></td>
                <td><s:property value="dhlRegion"/></td>
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
    <s:if test="!dhlCountryModels.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{dhlCountryModels.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="dhlCountryModels.pageRange" status="count">
        <s:if test="%{dhlCountryModels.pageRange[#count.index] == dhlCountryModels.currentPage}">
            <a class="paginate_button current"><s:property value="dhlCountryModels.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!dhlCountryModels.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{dhlCountryModels.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>