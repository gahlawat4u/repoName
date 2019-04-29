<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="toll_priority_remote_area_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="toll_priority_remote_area_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeTollPriorityRemoteAreaPageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="toll_priority_remote_area_page" name="page"/>
    <s:hidden id="toll_priority_remote_area_order_type" name="orderType"/>
    <s:hidden id="toll_priority_remote_area_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="toll_priority_remote_area_table">
        <thead>
        <tr>
            <th><xms:localization text="Post Code"/></th>
            <th><xms:localization text="Town"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="tollRemoteAreas==null || tollRemoteAreas.totalRecords==0">
            <tr>
                <td colspan="2"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="tollRemoteAreas.records">
                <tr>
                    <td><s:property value="postCode"/></td>
                    <td><s:property value="town"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="tollRemoteAreas.startRecord"/> <xms:localization
                    text="to"/> <s:property value="tollRemoteAreas.endRecord"/> <xms:localization text="of"/>
                <s:property value="tollRemoteAreas.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="tollRemoteAreas!=null">
                <s:if test="tollRemoteAreas.hasPrev()">
                    <a href="javascript:changeTollPriorityRemoteAreaPage(<s:property value="%{tollRemoteAreas.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="tollRemoteAreas.pageRange" status="count">
                    <s:if test="%{tollRemoteAreas.pageRange[#count.index] == tollRemoteAreas.currentPage}">
                        <a class="paginate_button current"><s:property value="tollRemoteAreas.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeTollPriorityRemoteAreaPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="tollRemoteAreas.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeTollPriorityRemoteAreaPage(<s:property value="%{tollRemoteAreas.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#toll_priority_remote_area_table").tablesorter({
            sortFieldId: "toll_priority_remote_area_order_field",
            sortTypeId: "toll_priority_remote_area_order_type",
            fieldList: ["postcode", "town"],
            callback: getTollPriorityRemoteAreas
        });
        actionPrint = "system_stats_print_toll_priority_remote_area.ix";
        actionExport = "system_stats_export_toll_priority_remote_area.ix";
    });

    function getTollPriorityRemoteAreas() {
        callAction("toll_priority_remote_area");
    }

    function changeTollPriorityRemoteAreaPage(page) {
        $("#toll_priority_remote_area_page").val(page);
        getTollPriorityRemoteAreas();
    }

    function changeTollPriorityRemoteAreaPageSize() {
        $("#toll_priority_remote_area_page").val(1);
        getTollPriorityRemoteAreas();
    }
    <s:if test="tollRemoteAreas==null || tollRemoteAreas.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

</script>