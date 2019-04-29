<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:hidden id="surcharge_page" name="page"/>
<s:hidden id="orderField" name="orderField"/>
<s:hidden id="orderType" name="orderType"/>
<table class="table table-bordered mg0  table-hover table-pointer" id="surcharge-list-table">
    <thead>
    <tr>
        <th><xms:localization text="Accessorial Code"/></th>
        <th><xms:localization text="Last Modified"/></th>
        <th><xms:localization text="Accessorial Type"/></th>
        <th><xms:localization text="Accessorial Description"/></th>
        <th><xms:localization text="Carrier"/></th>
        <th><xms:localization text="Quoteable"/></th>
    </tr>
    </thead>
    <tbody>
    <!-- Multiple filter -->
    <tr id="surcharge-list-multiple-filter">
        <td><s:textfield name="code" cssClass="form-control" onkeyup="searchSurchargeByKeyUp()"/></td>
        <td><s:textfield name="modifiedDate" cssClass="form-control form_datetime" data-date-format="dd MM yyyy"
                         onchange="doSearch()"/></td>
        <td><s:select name="type" cssClass="form-control" list="accessorialTypeList" listKey="key" listValue="value"
                      headerKey="" headerValue="" onchange="doSearch()"/></td>
        <td><s:textfield name="description" cssClass="form-control" onkeyup="searchSurchargeByKeyUp()"/></td>
        <td><s:textfield name="carrierName" cssClass="form-control" onkeyup="searchSurchargeByKeyUp()"/></td>
        <td><s:select name="quotable" cssClass="form-control" list="quotableList" listKey="key" listValue="value"
                      headerKey="" headerValue="" onchange="doSearch()"/></td>
    </tr>
    <s:if test="surchargeList.records.isEmpty()">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="surchargeList.records">
            <tr data-accessorialId="<s:property value="accessorialId" />"
                ondblclick="showAccessorial($(this).attr('data-accessorialId'))">
                <td><s:property value="code"/></td>
                <td><s:property value="modifiedDate"/></td>
                <td><s:property value="typeId"/></td>
                <td><s:property value="description"/></td>
                <td><s:property value="serviceName"/></td>
                <td><s:property value="isQuotable"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $('.form_datetime').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: 'dd-mm-yyyy'
        });

        $('#selectedId').val("");
        $('table#surcharge-list-table tbody tr').click(function () {
            var accessorialId = $(this).attr('data-accessorialId');
            if (typeof (accessorialId) != "undefined" && accessorialId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectAccessorial(accessorialId);
                $('#btnView').attr('disabled', false);
            }
        });
        var fieldList = ["code", "modified_date", "typeid", "description", "service_name", "isquotable"];
        $(document).ready(function () {
            $("#surcharge-list-table").tablesorter({
                sortFieldId: "orderField",
                sortTypeId: "orderType",
                fieldList: fieldList,
                callback: doSearch
            });
        });
    });
</script>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="surchargeList.startRecord"/> <xms:localization
                    text="to"/> <s:property value="surchargeList.endRecord"/> <xms:localization text="of"/> <s:property
                    value="surchargeList.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!surchargeList.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{surchargeList.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="surchargeList.pageRange" status="count">
                <s:if test="%{surchargeList.pageRange[#count.index] == surchargeList.currentPage}">
                    <a class="paginate_button current"><s:property value="surchargeList.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!surchargeList.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{surchargeList.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
