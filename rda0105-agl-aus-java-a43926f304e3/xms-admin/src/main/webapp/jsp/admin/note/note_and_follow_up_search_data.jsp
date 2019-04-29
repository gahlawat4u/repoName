<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0 table-hover" id="note_and_follow_up_table">
    <thead>
    <tr>
        <th><xms:localization text="Follow Up?"/></th>
        <th><xms:localization text="Follow Up Date"/></th>
        <th><xms:localization text="Modify Date"/></th>
        <th><xms:localization text="Category"/></th>
        <th><xms:localization text="Invoice No"/></th>
        <th><xms:localization text="Note"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="notes==null || notes.totalRecords==0">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="notes.records">
            <tr>
                <td><s:checkbox name="check"/></td>
                <td><s:property value="followUpDate"/></td>
                <td><s:property value="modifyDate"/></td>
                <td><s:property value="category"/></td>
                <td><s:property value="invoiceCode"/></td>
                <td><s:property value="note"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate">
    <s:if test="notes!=nul">
        <s:if test="notes.hasPrev()">
            <a href="javascript:changePage(<s:property value="%{notes.currentPage - 1}"/>)"
               class="paginate_button previous"><xms:localization text="Previous"/></a>
        </s:if>
		<span> <s:iterator value="notes.pageRange" status="count">
            <s:if test="%{notes.pageRange[#count.index] == notes.currentPage}">
                <a class="paginate_button current"><s:property value="notes.currentPage"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
            </s:else>
        </s:iterator>
		</span>
        <s:if test="notes.hasNext()">
            <a class="paginate_button next"
               href="javascript:changePage(<s:property value="%{notes.currentPage+1}"/>)"><xms:localization
                    text="Next"/></a>
        </s:if>
    </s:if>
</div>

<script type="text/javascript">
    var fieldList = ["`check`", "follow_up_date", "modify_date", "category", "invoicecode", "note"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#note_and_follow_up_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
    });


</script>