<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="toll_ipec_zone_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="toll_ipec_zone_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeTollIpecZonePageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="toll_ipec_zone_page" name="page"/>
    <s:hidden id="toll_ipec_zone_order_type" name="orderType"/>
    <s:hidden id="toll_ipec_zone_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="toll_ipec_zone_table">
        <thead>
        <tr>
            <th><xms:localization text="Business"/></th>
            <th><xms:localization text="Country"/></th>
            <th><xms:localization text="State"/></th>
            <th><xms:localization text="Post Code"/></th>
            <th><xms:localization text="Town Name"/></th>
            <th><xms:localization text="Zone"/></th>
            <th><xms:localization text="Local"/></th>
            <th><xms:localization text="ScNr"/></th>
            <th><xms:localization text="ScName"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="tollIpecZones==null || tollIpecZones.totalRecords==0">
            <tr>
                <td colspan="9"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="tollIpecZones.records">
                <tr>
                    <td><s:property value="business"/></td>
                    <td><s:property value="country"/></td>
                    <td><s:property value="state"/></td>
                    <td><s:property value="postcode"/></td>
                    <td><s:property value="townName"/></td>
                    <td><s:property value="zone"/></td>
                    <td><s:property value="local"/></td>
                    <td><s:property value="scNr"/></td>
                    <td><s:property value="scName"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="tollIpecZones.startRecord"/> <xms:localization
                    text="to"/> <s:property value="tollIpecZones.endRecord"/> <xms:localization text="of"/> <s:property
                    value="tollIpecZones.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="tollIpecZones!=null">
                <s:if test="tollIpecZones.hasPrev()">
                    <a href="javascript:changeTollIpecZonePage(<s:property value="%{tollIpecZones.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="tollIpecZones.pageRange" status="count">
                    <s:if test="%{tollIpecZones.pageRange[#count.index] == tollIpecZones.currentPage}">
                        <a class="paginate_button current"><s:property value="tollIpecZones.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeTollIpecZonePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="tollIpecZones.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeTollIpecZonePage(<s:property value="%{tollIpecZones.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#toll_ipec_zone_table").tablesorter({
            sortFieldId: "toll_ipec_zone_order_field",
            sortTypeId: "toll_ipec_zone_order_type",
            fieldList: ["business", "country", "state", "postcode", "town_name", "zone", "local", "sc_nr", "sc_name"],
            callback: getTollIpecZones
        });
        actionPrint = "system_stats_print_toll_ipec_town_postcode.ix";
        actionExport = "system_stats_export_toll_ipec_town_postcode.ix";
    });

    function getTollIpecZones() {
        callAction("toll_ipec_zone");
    }

    function changeTollIpecZonePage(page) {
        $("#toll_ipec_zone_page").val(page);
        getTollIpecZones();
    }

    function changeTollIpecZonePageSize() {
        $("#toll_ipec_zone_page").val(1);
        getTollIpecZones();
    }

    <s:if test="tollIpecZones==null || tollIpecZones.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>
</script>