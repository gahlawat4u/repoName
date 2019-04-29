<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0" id="customer_profile_markup_table">
    <thead>
    <tr>
        <th><xms:localization text="Description"/></th>
        <th><xms:localization text="Id"/></th>
        <th><xms:localization text="Markup Type"/></th>
        <th><xms:localization text="Amount"/></th>
        <th><xms:localization text="Carrier"/></th>
        <th><xms:localization text="Last Modified"/></th>
    </tr>
    </thead>
    <tbody>
    <tr id="tr_filter_markup">
        <td><s:textfield name="description" class="filter form-control s50"
                         onkeyup="searchOnKeypress()"></s:textfield></td>
        <td><s:textfield name="code" class="filter form-control s50" onkeyup="searchOnKeypress()"></s:textfield></td>
        <td><s:textfield name="typeName" class="filter form-control s50"
                         onkeyup="searchOnKeypress()"></s:textfield></td>
        <td><s:textfield name="amount" class="filter form-control s50" onkeyup="searchOnKeypress()"></s:textfield></td>
        <td><s:textfield name="serviceName" class="filter form-control s50"
                         onkeyup="searchOnKeypress()"></s:textfield></td>
        <td><s:textfield name="modifiedDate" class="filter form-control s50"
                         onkeyup="searchOnKeypress()"></s:textfield></td>
    </tr>
    <s:if test="markups.records.size != 0">
        <s:iterator value="markups.records">
            <tr id="edit-markup-1-link" class="edit-markup">
                <td><s:property value="description"/> <s:hidden id="accessorialId" value="%{accessorialId}"></s:hidden>
                    <s:hidden id="profileId" value="%{profileId}"></s:hidden></td>
                <td><s:property value="code"/></td>
                <td><s:property value="typeName"/></td>
                <td><s:property value="amount"/></td>
                <td><s:property value="serviceName"/></td>
                <td><s:property value="modifiedDate"/></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="6"><xms:localization text="No record to view"/> ...</td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="markups.startRecord"/> <xms:localization
                    text="to"/> <s:property value="markups.endRecord"/> <xms:localization text="of"/> <s:property
                    value="markups.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!markups.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{markups.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="markups.pageRange" status="count">
                <s:if test="%{markups.pageRange[#count.index] == markups.currentPage}">
                    <a class="paginate_button current"><s:property value="markups.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!markups.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{markups.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#customer_profile_markup_table tbody tr.edit-markup').dblclick(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
            var accessorialId = $(this).find('#accessorialId').val();
            var profileId = $(this).find('#profileId').val();
            $("#accessorialId").val(accessorialId);
            $("#profileId").val(profileId);
            viewMarkupDetail();
        });

        var fieldList = ["description", "code", "typename", "amount", "service_name", "modified_date"];
        $("#customer_profile_markup_table").tablesorter({
            sortFieldId: "customer_profile_markup_order_field",
            sortTypeId: "customer_profile_markup_order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });


</script>