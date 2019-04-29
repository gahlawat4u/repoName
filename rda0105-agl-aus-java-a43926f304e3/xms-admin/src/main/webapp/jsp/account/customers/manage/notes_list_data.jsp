<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0" id="customer_notes_list_table">
    <thead>
    <tr>
        <th><xms:localization text="Last Modified"/></th>
        <th><xms:localization text="Franchise"/></th>
        <th><xms:localization text="User"/></th>
        <th><xms:localization text="Account"/></th>
        <th><xms:localization text="Note"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="notes!=null && notes.totalRecords>0">
        <s:iterator value="notes.records">
            <tr data-group="note-row" noteid="<s:property value="noteId" />">
                <td><s:property value="modifyDate"/></td>
                <td><s:property value="user.userCode"/></td>
                <td><s:property value="user.displayName"/></td>
                <td><s:property value="accountNo"/></td>
                <td><s:property value="note"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="5"><xms:localization text="No data available..."/></td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="notes.startRecord"/> <xms:localization text="to"/>
                <s:property value="notes.endRecord"/> <xms:localization text="of"/> <s:property
                        value="notes.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!notes.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:getNotes(<s:property value="%{notes.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="notes.pageRange" status="count">
                <s:if test="%{notes.pageRange[#count.index] == notes.currentPage}">
                    <a class="paginate_button current"><s:property value="notes.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:getNotes(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!notes.hasNext()">
                <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:getNotes(<s:property value="%{notes.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("tr[data-group='note-row']").click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
            noteId = $(this).attr("noteid");
        });
        var fieldList = ["modify_date", "user_code", "display_name", "account_no", "note"];
        $("#customer_notes_list_table").tablesorter({
            sortFieldId: "customer_notes_order_field",
            sortTypeId: "customer_notes_order_type",
            fieldList: fieldList,
            callback: searchCustomerNotes
        });
    });


</script>