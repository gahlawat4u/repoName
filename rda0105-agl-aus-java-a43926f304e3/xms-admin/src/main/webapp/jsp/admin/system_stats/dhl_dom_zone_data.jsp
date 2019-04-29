<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="dhl_dom_zone_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="dhl_dom_zone_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeDhlDomZonePageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="dhl_dom_zone_page" name="page"/>
    <s:hidden id="dhl_dom_zone_order_type" name="orderType"/>
    <s:hidden id="dhl_dom_zone_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="dhl_dom_zone_table">
        <thead>
        <tr>
            <th><xms:localization text="Origin Name"/></th>
            <th><xms:localization text="Origin Code"/></th>
            <th><xms:localization text="Destination Name"/></th>
            <th><xms:localization text="Destination Code"/></th>
            <th><xms:localization text="Zone"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="dhlDomZones==null || dhlDomZones.totalRecords==0">
            <tr>
                <td colspan="5"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="dhlDomZones.records">
                <tr>
                    <td><s:property value="originName"/></td>
                    <td><s:property value="originCode"/></td>
                    <td><s:property value="destinationName"/></td>
                    <td><s:property value="destinationCode"/></td>
                    <td><s:property value="zone"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="dhlDomZones.startRecord"/> <xms:localization
                    text="to"/> <s:property value="dhlDomZones.endRecord"/> <xms:localization text="of"/> <s:property
                    value="dhlDomZones.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="dhlDomZones!=null">
                <s:if test="dhlDomZones.hasPrev()">
                    <a href="javascript:changeDhlDomZonePage(<s:property value="%{dhlDomZones.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="dhlDomZones.pageRange" status="count">
                    <s:if test="%{dhlDomZones.pageRange[#count.index] == dhlDomZones.currentPage}">
                        <a class="paginate_button current"><s:property value="dhlDomZones.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeDhlDomZonePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="dhlDomZones.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeDhlDomZonePage(<s:property value="%{dhlDomZones.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#dhl_dom_zone_table").tablesorter({
            sortFieldId: "dhl_dom_zone_order_field",
            sortTypeId: "dhl_dom_zone_order_type",
            fieldList: ["origin_name", "origin_code", "destination_name", "destination_code", "zone"],
            callback: getDhlDomZones
        });
        actionPrint = "system_stats_print_dhl_domestic_zone.ix";
        actionExport = "system_stats_export_dhl_domestic_zone.ix";
    });

    function getDhlDomZones() {
        callAction("dhl_dom_zone");
    }

    function changeDhlDomZonePage(page) {
        $("#dhl_dom_zone_page").val(page);
        getDhlDomZones();
    }

    function changeDhlDomZonePageSize() {
        $("#dhl_dom_zone_page").val(1);
        getDhlDomZones();
    }
    <s:if test="dhlDomZones==null || dhlDomZones.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

</script>