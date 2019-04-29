<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0 table-hover table-pointer" id="rate-sheets-table">
    <thead>
    <tr>
        <th><xms:localization text="Name"/></th>
        <th><xms:localization text="Carrier"/></th>
        <th><xms:localization text="Shipment Type"/></th>
        <th><xms:localization text="Type"/></th>
        <th><xms:localization text="Date Imported"/></th>
        <th><xms:localization text="Total Number of Cells"/></th>
    </tr>
    </thead>
    <tbody>
    <tr id="search-filter">
        <td><s:textfield name="rateSheetFilter.rateSheetName" cssClass="form-control"
                         onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="rateSheetFilter.carrierName" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="rateSheetFilter.serviceName" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:select list="listRateSheetsType" name="rateSheetFilter.type" cssClass="form-control"
                      onchange="searchOnKeypress()"/></td>
        <td><s:textfield name="rateSheetFilter.importDate" cssClass="form-control form_datetime"
                         onchange="searchOnKeypress()"/></td>
        <td><s:textfield name="rateSheetFilter.totalCells" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
    </tr>
    <s:if test="rateSheetsList.records.isEmpty()">
        <tr id="view-rate-sheet-link">
            <td colspan="6"><xms:localization text="No data available"/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="rateSheetsList.records">
            <tr data-sheet-id='<s:property value="sheetId"/>' data-rate-sheet-name='<s:property value="rateSheetName"/>'
                ondblclick="showRateSheet($(this).attr('data-sheet-id'))">
                <td><s:property value="rateSheetName"/></td>
                <td><s:property value="carrierName"/></td>
                <td><s:property value="serviceName"/></td>
                <td><s:property value="type"/></td>
                <td><s:property value="importDate"/></td>
                <td><s:property value="totalCells"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="rateSheetsList.startRecord"/> <xms:localization
                    text="to"/> <s:property value="rateSheetsList.endRecord"/> <xms:localization text="of"/> <s:property
                    value="rateSheetsList.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!rateSheetsList.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{rateSheetsList.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="rateSheetsList.pageRange" status="count">
                <s:if test="%{rateSheetsList.pageRange[#count.index] == rateSheetsList.currentPage}">
                    <a class="paginate_button current"><s:property value="rateSheetsList.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!rateSheetsList.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{rateSheetsList.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
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
            /*format: 'dd-mm-yyyy, HH:ii p'*/
        });
        $('table#rate-sheets-table tbody tr').click(function () {
            var sheetId = $(this).attr('data-sheet-id');
            var rateSheetName = $(this).attr('data-rate-sheet-name');
            if (typeof (sheetId) != "undefined" && sheetId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectRateSheet(sheetId, rateSheetName);
                $('#btnRemove').attr('disabled', false);
            }
        });
    });


</script>