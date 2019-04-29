<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0" id="customer_markup_table">
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
    <tr id="markup-filter">
        <td><s:textfield name="description" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="code" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="typeName" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="amount" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="serviceName" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
        <td><input class="form-control s50"></td>
    </tr>
    <s:if test="markups!=null && markups.totalRecords>0">
        <s:iterator value="markups.records">
            <tr style="cursor: pointer;" ondblclick="editMarkup($(this))"
                accessorial-id="<s:property value="accessorialId" />">
                <td><s:property value="description"/></td>
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
            <td colspan="6"><xms:localization text="No data available"/>...</td>
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
                <a href="javascript:getMarkups(<s:property value="%{markups.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="markups.pageRange" status="count">
                <s:if test="%{markups.pageRange[#count.index] == markups.currentPage}">
                    <a class="paginate_button current"><s:property value="markups.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:getMarkups(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!markups.hasNext()">
                <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:getMarkups(<s:property value="%{markups.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var fieldList = ["description", "code", "typename", "amount", "service_name", "modified_date"];
        $("#customer_markup_table").tablesorter({
            sortFieldId: "customer_markup_order_field",
            sortTypeId: "customer_markup_order_type",
            fieldList: fieldList,
            callback: searchCustomerMarkup
        });
    });


</script>