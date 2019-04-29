<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0" id="airbill_import_error_log_table">
    <thead>
    <tr>
        <th><xms:localization text="Airbill #"/></th>
        <th><xms:localization text="Import Date"/></th>
        <th><xms:localization text="Notes"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="duplicateAirbills==null || duplicateAirbills.totalRecords==0">
        <tr>
            <td colspan='3'><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="duplicateAirbills.records">
            <tr>
                <td><s:property value="airbillNumber"/></td>
                <td><s:property value="importDate"/></td>
                <td><s:property value="note"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="duplicateAirbills.startRecord"/> <xms:localization
                    text="to"/> <s:property value="duplicateAirbills.endRecord"/> <xms:localization text="of"/>
                <s:property value="duplicateAirbills.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="duplicateAirbills!=null">
                <s:if test="duplicateAirbills.hasPrev()">
                    <a href="javascript:changePage(<s:property value="%{duplicateAirbills.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="duplicateAirbills.pageRange" status="count">
                    <s:if test="%{duplicateAirbills.pageRange[#count.index] == duplicateAirbills.currentPage}">
                        <a class="paginate_button current"><s:property value="duplicateAirbills.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="duplicateAirbills.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{duplicateAirbills.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    var fieldList = ["airbill_number", "import_date", ""];

    $(document).ready(function () {
        changeSearchType();
        $("#airbill_import_error_log_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
    });
    <s:if test="duplicateAirbills==null || duplicateAirbills.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>