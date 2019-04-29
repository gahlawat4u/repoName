<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="tnt_zone_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="tnt_zone_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeTntZonePageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="tnt_zone_page" name="page"/>
    <s:hidden id="tnt_zone_order_type" name="orderType"/>
    <s:hidden id="tnt_zone_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="tnt_zone_table">
        <thead>
        <tr>
            <th><xms:localization text="Zone Code"/></th>
            <th><xms:localization text="Zone Name"/></th>
            <th><xms:localization text="State Code"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="tntZones==null || tntZones.totalRecords==0">
            <tr>
                <td colspan="3"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="tntZones.records">
                <tr>
                    <td><s:property value="zoneCode"/></td>
                    <td><s:property value="zoneName"/></td>
                    <td><s:property value="stateCode"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="tntZones.startRecord"/> <xms:localization
                    text="to"/> <s:property value="tntZones.endRecord"/> <xms:localization text="of"/> <s:property
                    value="tntZones.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="tntZones!=null">
                <s:if test="tntZones.hasPrev()">
                    <a href="javascript:changeTntZonePage(<s:property value="%{tntZones.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="tntZones.pageRange" status="count">
                    <s:if test="%{tntZones.pageRange[#count.index] == tntZones.currentPage}">
                        <a class="paginate_button current"><s:property value="tntZones.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changeTntZonePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="tntZones.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeTntZonePage(<s:property value="%{tntZones.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#tnt_zone_table").tablesorter({
            sortFieldId: "tnt_zone_order_field",
            sortTypeId: "tnt_zone_order_type",
            fieldList: ["zone_code", "zone_name", "state_code"],
            callback: getTntZones
        });
        actionPrint = "system_stats_print_tnt_zone.ix";
        actionExport = "system_stats_export_tnt_zone.ix";
        <s:if test="tntZones==null || tntZones.totalRecords==0">
        hasRecords = false;
        </s:if>
        <s:else>
        hasRecords = true;
        </s:else>
    });

    function getTntZones() {
        callAction("tnt_zone");
    }

    function changeTntZonePage(page) {
        $("#tnt_zone_page").val(page);
        getTntZones();
    }

    function changeTntZonePageSize() {
        $("#tnt_zone_page").val(1);
        getTntZones();
    }


</script>