<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0" id="industry-list-table">
    <thead>
    <tr>
        <th><xms:localization text="System ID"/></th>
        <th><xms:localization text="Industry Name"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="industryList.records.isEmpty()">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="industryList.records">
            <tr data-industryId="<s:property value="industryId" />">
                <td><s:property value="industryId"/></td>
                <td industryId="<s:property value='industryId' />" industryName="<s:property value='industryName'/>"
                    ondblclick="javascript:editindustry($(this).attr('industryId'),$(this).attr('industryName'));">
                    <s:property value="industryName"/></td>
            </tr>
        </s:iterator>
    </s:else>

    </tbody>
    <s:hidden name="page"></s:hidden>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#industry-list-table tbody tr').click(function () {
            var industryId = $(this).attr('data-industryId');
            if (typeof (industryId) != "undefined" && industryId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectindustry(industryId);
                $('#btnView').attr('disabled', false);
            }
        });
        var fieldList = ["industry_id", "industry_name"];
        $("#industry-list-table").tablesorter({
            sortFieldId: "order_field",
            sortTypeId: "order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });
</script>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="industryList.startRecord"/> <xms:localization
                    text="to"/> <s:property value="industryList.endRecord"/> <xms:localization text="of"/> <s:property
                    value="industryList.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!industryList.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{industryList.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>

			<span> <s:iterator value="industryList.pageRange" status="count">
                <s:if test="%{industryList.pageRange[#count.index] == industryList.currentPage}">
                    <a class="paginate_button current"><s:property value="industryList.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!industryList.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{industryList.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            $('table#industry-list-table tbody tr').click(function () {
                var industryId = $(this).attr('data-industryId');
                if (typeof (industryId) != "undefined" && industryId != "") {
                    $(this).addClass('selected-row').siblings().removeClass('selected-row');
                    selectindustry(industryId);
                    $('#btnView').attr('disabled', false);
                }
            });
        });
    </script>
</div>