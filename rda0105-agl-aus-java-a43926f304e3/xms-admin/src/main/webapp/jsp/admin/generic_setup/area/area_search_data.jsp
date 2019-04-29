<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0" id="area-list-table">
    <thead>
    <tr>
        <th><xms:localization text="No"/></th>
        <th><xms:localization text="Areas Name"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="areaList.records.isEmpty()">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="areaList.records">
            <tr data-areaId="<s:property value="areaId" />">
                <td><s:property value="areaId"/></td>
                <td areaId="<s:property value='areaId' />"
                    areaName="<s:property value='areaName'/>"
                    ondblclick="javascript:editarea($(this).attr('areaId'),$(this).attr('areaName'));">
                    <s:property value="areaName"/>
                </td>
            </tr>
        </s:iterator>
    </s:else>

    </tbody>

</table>
<div class="dataTables_paginate">
    <s:if test="!areaList.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization
                text="Previous"/></a>
    </s:if>
    <s:else>
        <a
                href="javascript:changePage(<s:property value="%{areaList.currentPage - 1}"/>)"
                class="paginate_button previous"><xms:localization
                text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="areaList.pageRange" status="count">
        <s:if
                test="%{areaList.pageRange[#count.index] == areaList.currentPage}">
            <a class="paginate_button current"><s:property
                    value="areaList.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button"
               href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!areaList.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization
                text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{areaList.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#area-list-table tbody tr').click(function () {
            var areaId = $(this).attr('data-areaId');
            if (typeof (areaId) != "undefined" && areaId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectarea(areaId);
                $('#btnView').attr('disabled', false);
            }
        });
    });


</script>