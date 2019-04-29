<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered  table-hover mg0 " id="datatable1">
    <thead>
    <tr>
        <th>Airbill Import Date</th>
        <th>Carrier Name</th>
        <th>Import Count</th>
    </tr>
    </thead>
    <tbody>
    <s:if test="systemStatsList.records.size != 0">
        <s:iterator value="systemStatsList.records">
            <tr>
                <td><s:property value="importDate"/></td>
                <td><s:property value="serviceName"/></td>
                <td><s:property value="count"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="3"><xms:localization text="No record to view"/>
                ...
            </td>
        </tr>
    </s:else>
    </tbody>
</table>

<div class="dataTables_paginate">
    <s:if test="!systemStatsList.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization
                text="Previous"/></a>
    </s:if>
    <s:else>
        <a
                href="javascript:changePage(<s:property value="%{systemStatsList.currentPage - 1}"/>)"
                class="paginate_button previous"><xms:localization
                text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="systemStatsList.pageRange"
                       status="count">
        <s:if
                test="%{systemStatsList.pageRange[#count.index] == systemStatsList.currentPage}">
            <a class="paginate_button current"><s:property
                    value="systemStatsList.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button"
               href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!systemStatsList.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization
                text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{systemStatsList.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>