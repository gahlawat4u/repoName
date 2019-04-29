<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:hidden id="rptTxnId" name="rptTxnId"/>
<s:hidden id="page" name="page"/>
<s:hidden id="orderField" name="orderField"/>
<s:hidden id="orderType" name="orderType"/>
<table class="table table-hover table-bordered mg0" id="customer_summary_table">
    <thead>
    <tr>
        <s:iterator value="columns" var="col">
            <s:if test="%{#col.group==1}">
                <th data-group="detail-group"><s:property value="%{#col.columnName}"/></th>
            </s:if>
            <s:else>
                <th><s:property value="%{#col.columnName}"/></th>
            </s:else>
        </s:iterator>
    </tr>
    </thead>
    <tbody>
    <s:if test="customers==null || customers.totalRecords==0">
        <tr>
            <td colspan='<s:property value="%{columns.size()}" />'><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="customers.records" var="row">
            <tr>
                <s:iterator value="columns" var="col">
                    <td <s:if
                            test="%{#col.fieldName.contains('margin') || #col.fieldName.contains('cost') || #col.fieldName.contains('shipment_count')}">
                        class="text-right"
                    </s:if> <s:if test="%{#col.group==1}">
                        data-group="detail-group"
                    </s:if>><s:property value='%{#row[#col.fieldName]}'/></td>
                </s:iterator>
            </tr>
        </s:iterator>
        <tr>
            <s:iterator value="columns" var="col">
                <th <s:if
                        test="%{#col.fieldName.contains('margin') || #col.fieldName.contains('cost') || #col.fieldName.contains('shipment_count')}">
                    class="text-right"
                </s:if> <s:if test="%{#col.group==1}">
                    data-group="detail-group"
                </s:if>><s:property value='%{summary[#col.fieldName]}'/></th>
            </s:iterator>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="customers.startRecord"/> <xms:localization
                    text="to"/> <s:property value="customers.endRecord"/> <xms:localization text="of"/> <s:property
                    value="customers.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!customers.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{customers.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="customers.pageRange" status="count">
                <s:if test="%{customers.pageRange[#count.index] == customers.currentPage}">
                    <a class="paginate_button current"><s:property value="customers.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!customers.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{customers.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>

<script type="text/javascript">
    var fieldList = [];
    <s:iterator value="columns" var="col">
    fieldList.push('<s:property value="#col.fieldName" />');
    </s:iterator>
    $(document).ready(function () {
        var show = $("#chkShowDetail").is(':checked');
        showDetailColumns(show);
        $("#customer_summary_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
        <s:if test="customers!=null && customers.totalRecords!=0">
        hasRecords = true;
        </s:if>
        <s:else>
        hasRecords = false;
        </s:else>
    });


</script>