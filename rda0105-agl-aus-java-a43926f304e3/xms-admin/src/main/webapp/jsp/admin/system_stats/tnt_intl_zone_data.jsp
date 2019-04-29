<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="tnt_intl_zone_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="tnt_intl_zone_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeTntIntlZonePageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="tnt_intl_zone_page" name="page"/>
    <s:hidden id="tnt_intl_zone_order_type" name="orderType"/>
    <s:hidden id="tnt_intl_zone_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="tnt_intl_zone_table">
        <thead>
        <tr>
            <th><xms:localization text="Original File Country Name"/></th>
            <th><xms:localization text="Country Name"/></th>
            <th><xms:localization text="Express Outbound Zone"/></th>
            <th><xms:localization text="Economy Express Outbound Zone"/></th>
            <th><xms:localization text="Express Inbound Zone"/></th>
            <th><xms:localization text="Economy Express Inbound Zone"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="tntIntlZones==null || tntIntlZones.totalRecords==0">
            <tr>
                <td colspan="6"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="tntIntlZones.records">
                <tr>
                    <td><s:property value="originalFileCountryName"/></td>
                    <td><s:property value="countryName"/></td>
                    <td><s:property value="expressOutboundZone"/></td>
                    <td><s:property value="economyExpressOutboundZone"/></td>
                    <td><s:property value="expressInboundZone"/></td>
                    <td><s:property value="economyExpressInboundZone"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="tntIntlZones.startRecord"/> <xms:localization
                    text="to"/> <s:property value="tntIntlZones.endRecord"/> <xms:localization text="of"/> <s:property
                    value="tntIntlZones.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="tntIntlZones!=null">
                <s:if test="tntIntlZones.hasPrev()">
                    <a href="javascript:changeTntIntlZonePage(<s:property value="%{tntIntlZones.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="tntIntlZones.pageRange" status="count">
                    <s:if test="%{tntIntlZones.pageRange[#count.index] == tntIntlZones.currentPage}">
                        <a class="paginate_button current"><s:property value="tntIntlZones.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeTntIntlZonePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="tntIntlZones.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeTntIntlZonePage(<s:property value="%{tntIntlZones.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#tnt_intl_zone_table").tablesorter({
            sortFieldId: "tnt_intl_zone_order_field",
            sortTypeId: "tnt_intl_zone_order_type",
            fieldList: ["original_file_countryname", "countryname", "express_outbound_zone", "economy_express_outbound_zone", "express_inbound_zone", "economy_express_inbound_zone"],
            callback: getTntIntlZones
        });
        actionPrint = "system_stats_print_tnt_international_zone.ix";
        actionExport = "system_stats_export_tnt_international_zone.ix";
    });

    function getTntIntlZones() {
        callAction("tnt_intl_zone");
    }

    function changeTntIntlZonePage(page) {
        $("#tnt_intl_zone_page").val(page);
        getTntIntlZones();
    }

    function changeTntIntlZonePageSize() {
        $("#tnt_intl_zone_page").val(1);
        getTntIntlZones();
    }

    <s:if test="tntIntlZones==null || tntIntlZones.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>
</script>