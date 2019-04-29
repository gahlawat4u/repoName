<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0" id="customer-group-list-table">
    <thead>
    <tr>
        <th><xms:localization text="No"/></th>
        <th><xms:localization text="Customer Group Name"/></th>
    </tr>
    </thead>

    <tbody>
    <s:if test="customerGroupList.records.isEmpty()">
        <tr>
            <td colspan="6"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="customerGroupList.records">
            <tr data-customerGroupId="<s:property value="customerGroupId" />">
                <td><s:property value="customerGroupId"/></td>
                <td customerGroupId="<s:property value='customerGroupId' />"
                    customerGroupName="<s:property value='customerGroupName'/>"
                    ondblclick="javascript:editcustomergroup($(this).attr('customerGroupId'),$(this).attr('customerGroupName'));">
                    <s:property value="customerGroupName"/></td>
            </tr>
        </s:iterator>
    </s:else>

    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="customerGroupList.startRecord"/> <xms:localization
                    text="to"/> <s:property value="customerGroupList.endRecord"/> <xms:localization text="of"/>
                <s:property value="customerGroupList.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!customerGroupList.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{customerGroupList.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="customerGroupList.pageRange" status="count">
                <s:if test="%{customerGroupList.pageRange[#count.index] == customerGroupList.currentPage}">
                    <a class="paginate_button current"><s:property value="customerGroupList.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!customerGroupList.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{customerGroupList.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#customer-group-list-table tbody tr').click(function () {
            var customerGroupId = $(this).attr('data-customerGroupId');
            if (typeof (customerGroupId) != "undefined" && customerGroupId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectcustomergroup(customerGroupId);
                $('#btnView').attr('disabled', false);
            }
        });
        var fieldList = ["customer_group_id", "customer_group_name"];
        $("#customer-group-list-table").tablesorter({
            sortFieldId: "order_field",
            sortTypeId: "order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });


</script>