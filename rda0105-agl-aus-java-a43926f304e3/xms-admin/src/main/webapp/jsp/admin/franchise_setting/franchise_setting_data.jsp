<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0">
    <thead>
    <tr>
        <th>Name</th>
        <th>Value</th>
        <th>Edit Admin Level</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <s:if test="franchiseSettingListExtModels.records.size != 0">
        <s:iterator value="franchiseSettingListExtModels.records">
            <tr>
                <td><s:property value="name"/></td>
                <td><s:property value="value"/></td>
                <td><s:property value="adminLevel"/></td>
                <td><s:property value="description"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="6"><xms:localization text="No record to view"/>
                ...
            </td>
        </tr>
    </s:else>


    </tbody>
</table>


<div class="dataTables_paginate">
    <s:if test="!franchiseSettingListExtModels.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization
                text="Previous"/></a>
    </s:if>
    <s:else>
        <a
                href="javascript:changePage(<s:property value="%{franchiseSettingListExtModels.currentPage - 1}"/>)"
                class="paginate_button previous"><xms:localization
                text="Previous"/></a>
    </s:else>
	<span> <s:iterator
            value="franchiseSettingListExtModels.pageRange" status="count">
        <s:if
                test="%{franchiseSettingListExtModels.pageRange[#count.index] == franchiseSettingListExtModels.currentPage}">
            <a class="paginate_button current"><s:property
                    value="franchiseSettingListExtModels.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button"
               href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!franchiseSettingListExtModels.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization
                text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{franchiseSettingListExtModels.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>