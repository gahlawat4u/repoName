<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0" id="territory-list-table">
    <thead>
    <tr>
        <th><xms:localization text="No"/></th>
        <th><xms:localization text="Territory"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="territories.records.isEmpty()">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="territories.records">
            <tr data-territoryId="<s:property value="territoryId" />">
                <td><s:property value="territoryId"/></td>
                <td territoryId="<s:property value='territoryId' />"
                    territoryName="<s:property value='territoryName'/>"
                    ondblclick="javascript:editTerritory($(this).attr('territoryId'),$(this).attr('territoryName'));">
                    <s:property value="territoryName"/>
                </td>
            </tr>
        </s:iterator>
    </s:else>

    </tbody>
</table>

<div class="dataTables_paginate">
    <s:if test="!territories.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization
                text="Previous"/></a>
    </s:if>
    <s:else>
        <a
                href="javascript:changePage(<s:property value="%{territories.currentPage - 1}"/>)"
                class="paginate_button previous"><xms:localization
                text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="territories.pageRange" status="count">
        <s:if
                test="%{territories.pageRange[#count.index] == territories.currentPage}">
            <a class="paginate_button current"><s:property
                    value="territories.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button"
               href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!territories.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization
                text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{territories.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#territory-list-table tbody tr').click(function () {
            var territoryId = $(this).attr('data-territoryId');
            if (typeof (territoryId) != "undefined" && territoryId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectTerritory(territoryId);
                $('#btnView').attr('disabled', false);
            }
        });
    });
</script>
