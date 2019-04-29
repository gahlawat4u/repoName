<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0 table-hover table-pointer" id="language-value-table">
    <thead>
    <tr>
        <th><xms:localization text="Language code"/></th>
        <th><xms:localization text="Original"/></th>
        <th><xms:localization text="Destination"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="languageValueList.records.isEmpty()">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="languageValueList.records">
            <tr ondblclick="showLanguaValue('<s:property value="id"/>')">
                <td><s:property value="langCode"/></td>
                <td><s:property value="original"/></td>
                <td><s:property value="destination"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $("#language-value-table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: ["lang_code", "original", "destination"],
            callback: function () {
                getData(1);
            }
        });
    });
</script>
<div class="dataTables_paginate">
    <s:if test="languageValueList.hasPrev()">
        <a href="javascript:changePage(<s:property value="%{languageValueList.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:if>
	<span> <s:iterator value="languageValueList.pageRange" status="count">
        <s:if test="%{languageValueList.pageRange[#count.index] == languageValueList.currentPage}">
            <a class="paginate_button current"><s:property value="languageValueList.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="languageValueList.hasNext()">
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{languageValueList.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:if>
</div>