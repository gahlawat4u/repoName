<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0 table-hover table-pointer" id="webship-group-list-table">
    <thead>
    <tr>
        <th><xms:localization text="No"/></th>
        <th><xms:localization text="Web Freight Group Name"/></th>
        <th><xms:localization text="Owner Customer Id"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="webshipGroupList.records.isEmpty()">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="webshipGroupList.records">
            <tr data-webshipGroupId="<s:property value="webshipGroupId" />"
                ondblclick="editWebshipGroup($(this).attr('data-webshipGroupId'))">
                <td><s:property value="webshipGroupId"/></td>
                <td><s:property value="webshipGroupName"/></td>
                <td><s:property value="ownerCustomerId"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>

<script type="text/javascript">
    $(document).ready(function () {
        $('table#webship-group-list-table tbody tr').click(function () {
            var webshipGroupId = $(this).attr('data-webshipGroupId');
            if (typeof (webshipGroupId) != "undefined" && webshipGroupId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectWebshipGroup(webshipGroupId);
                $('#btnRemove').attr('disabled', false);
            }
        });
    });
</script>
<div class="dataTables_paginate">
    <s:if test="!webshipGroupList.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{webshipGroupList.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="webshipGroupList.pageRange" status="count">
        <s:if test="%{webshipGroupList.pageRange[#count.index] == webshipGroupList.currentPage}">
            <a class="paginate_button current"><s:property value="webshipGroupList.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!webshipGroupList.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{webshipGroupList.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>