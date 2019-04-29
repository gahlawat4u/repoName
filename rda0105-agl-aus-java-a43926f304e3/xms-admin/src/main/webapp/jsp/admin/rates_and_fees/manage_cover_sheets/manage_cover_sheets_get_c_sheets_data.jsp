<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0 table-hover table-pointer" id="cover-sheets-table">
    <thead>
    <tr>
        <th><xms:localization text="File Name"/></th>
        <th><xms:localization text="Upload Date"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="cvSheets.records.isEmpty()">
        <tr>
            <td colspan="2"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="cvSheets.records">
            <tr data-cover-sheet-id="<s:property value="coverSheetId"/>"
                data-file-name="<s:property value="fileName" />">
                <td><s:property value="fileName"/></td>
                <td><s:property value="createDate"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $("table#cover-sheets-table tbody tr[data-cover-sheet-id='" + cSheetId + "']").addClass('selected-row').siblings().removeClass('selected-row');
        $('table#cover-sheets-table tbody tr').click(function () {
            var coverSheetId = $(this).attr('data-cover-sheet-id');
            var fileName = $(this).attr('data-file-name');
            if (typeof (coverSheetId) != "undefined" && coverSheetId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectCSheet(coverSheetId, fileName);
            }
        });
    });
</script>
<div class="row">
    <div class="col-lg-12">
        <div class="dataTables_paginate">
            <s:if test="!cvSheets.hasPrev()">
                <button class="paginate_button previous disabled" disabled="disabled">
                    <xms:localization text="Previous"/>
                </button>
            </s:if>
            <s:else>
                <a href="javascript:changeCPage(<s:property value="%{cvSheets.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="cvSheets.pageRange" status="count">
                <s:if test="%{cvSheets.pageRange[#count.index] == cvSheets.currentPage}">
                    <a class="paginate_button current"><s:property value="cvSheets.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changeCPage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!cvSheets.hasNext()">
                <button class="paginate_button next" disabled="disabled">
                    <xms:localization text="Next"/>
                </button>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changeCPage(<s:property value="%{cvSheets.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
