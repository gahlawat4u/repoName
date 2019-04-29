<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="tnt_dom_remote_area_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="tnt_dom_remote_area_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeTntDomRemoteAreaPageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="tnt_dom_remote_area_page" name="page"/>
    <s:hidden id="tnt_dom_remote_area_order_type" name="orderType"/>
    <s:hidden id="tnt_dom_remote_area_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="tnt_dom_remote_area_table">
        <thead>
        <tr>
            <th><xms:localization text="Post Code"/></th>
            <th><xms:localization text="Town"/></th>
            <th><xms:localization text="State"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="tntRemoteAreas==null || tntRemoteAreas.totalRecords==0">
            <tr>
                <td colspan="3"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="tntRemoteAreas.records">
                <tr>
                    <td><s:property value="postCode"/></td>
                    <td><s:property value="town"/></td>
                    <td><s:property value="state"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="tntRemoteAreas.startRecord"/> <xms:localization
                    text="to"/> <s:property value="tntRemoteAreas.endRecord"/> <xms:localization text="of"/> <s:property
                    value="tntRemoteAreas.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="tntRemoteAreas!=null">
                <s:if test="tntRemoteAreas.hasPrev()">
                    <a href="javascript:changeTntDomRemoteAreaPage(<s:property value="%{tntRemoteAreas.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="tntRemoteAreas.pageRange" status="count">
                    <s:if test="%{tntRemoteAreas.pageRange[#count.index] == tntRemoteAreas.currentPage}">
                        <a class="paginate_button current"><s:property value="tntRemoteAreas.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeTntDomRemoteAreaPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="tntRemoteAreas.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeTntDomRemoteAreaPage(<s:property value="%{tntRemoteAreas.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#tnt_dom_remote_area_table").tablesorter({
            sortFieldId: "tnt_dom_remote_area_order_field",
            sortTypeId: "tnt_dom_remote_area_order_type",
            fieldList: ["postcode", "town", "state"],
            callback: getTntDomRemoteAreas
        });
        actionPrint = "system_stats_print_tnt_dom_remote_area.ix";
        actionExport = "system_stats_export_tnt_dom_remote_area.ix";
    });

    function getTntDomRemoteAreas() {
        callAction("tnt_dom_remote_area");
    }

    function changeTntDomRemoteAreaPage(page) {
        $("#tnt_dom_remote_area_page").val(page);
        getTntDomRemoteAreas();
    }

    function changeTntDomRemoteAreaPageSize() {
        $("#tnt_dom_remote_area_page").val(1);
        getTntDomRemoteAreas();
    }
    <s:if test="tntRemoteAreas==null || tntRemoteAreas.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

</script>