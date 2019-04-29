<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="toll_priority_suburb_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="toll_priority_suburb_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeTollPrioritySuburbPageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="toll_priority_suburb_page" name="page"/>
    <s:hidden id="toll_priority_suburb_order_type" name="orderType"/>
    <s:hidden id="toll_priority_suburb_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="toll_priority_suburb_table">
        <thead>
        <tr>
            <th><xms:localization text="Business Unit"/></th>
            <th><xms:localization text="Country"/></th>
            <th><xms:localization text="State Code"/></th>
            <th><xms:localization text="Post Code"/></th>
            <th><xms:localization text="Suburb Name"/></th>
            <th><xms:localization text="Zone"/></th>
            <th><xms:localization text="Local"/></th>
            <th><xms:localization text="Collection Depot"/></th>
            <th><xms:localization text="Collection Depot Name"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="tollPrioritySuburbs==null || tollPrioritySuburbs.totalRecords==0">
            <tr>
                <td colspan="9"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="tollPrioritySuburbs.records">
                <tr>
                    <td><s:property value="businessUnit"/></td>
                    <td><s:property value="country"/></td>
                    <td><s:property value="stateCode"/></td>
                    <td><s:property value="postCode"/></td>
                    <td><s:property value="suburbName"/></td>
                    <td><s:property value="zone"/></td>
                    <td><s:property value="local"/></td>
                    <td><s:property value="collectionDepot"/></td>
                    <td><s:property value="collectionDepotName"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="tollPrioritySuburbs.startRecord"/>
                <xms:localization text="to"/> <s:property value="tollPrioritySuburbs.endRecord"/> <xms:localization
                        text="of"/> <s:property value="tollPrioritySuburbs.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="tollPrioritySuburbs!=null">
                <s:if test="tollPrioritySuburbs.hasPrev()">
                    <a href="javascript:changeTollPrioritySuburbPage(<s:property value="%{tollPrioritySuburbs.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="tollPrioritySuburbs.pageRange" status="count">
                    <s:if test="%{tollPrioritySuburbs.pageRange[#count.index] == tollPrioritySuburbs.currentPage}">
                        <a class="paginate_button current"><s:property value="tollPrioritySuburbs.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeTollPrioritySuburbPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="tollPrioritySuburbs.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeTollPrioritySuburbPage(<s:property value="%{tollPrioritySuburbs.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#toll_priority_suburb_table").tablesorter({
            sortFieldId: "toll_priority_suburb_order_field",
            sortTypeId: "toll_priority_suburb_order_type",
            fieldList: ["business_unit", "country", "state_code", "post_code", "suburb_name", "zone", "local", "collection_depot", "collection_depot_name"],
            callback: getTollPrioritySuburbs
        });
        actionPrint = "system_stats_print_toll_priority_suburb.ix";
        actionExport = "system_stats_export_toll_priority_suburb.ix";
    });

    function getTollPrioritySuburbs() {
        callAction("toll_priority_suburb");
    }

    function changeTollPrioritySuburbPage(page) {
        $("#toll_priority_suburb_page").val(page);
        getTollPrioritySuburbs();
    }

    function changeTollPrioritySuburbPageSize() {
        $("#toll_priority_suburb_page").val(1);
        getTollPrioritySuburbs();
    }
    <s:if test="tollPrioritySuburbs==null || tollPrioritySuburbs.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

</script>