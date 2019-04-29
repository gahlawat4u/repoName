<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="dhl_country_zone_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="dhl_country_zone_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeDhlCountryZonePageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="dhl_country_zone_page" name="page"/>
    <s:hidden id="dhl_country_zone_order_type" name="orderType"/>
    <s:hidden id="dhl_country_zone_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="dhl_country_zone_table">
        <thead>
        <tr>
            <th><xms:localization text="App Code"/></th>
            <th><xms:localization text="App Zone"/></th>
            <th><xms:localization text="Country Name"/></th>
            <th><xms:localization text="Region"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="dhlZones==null || dhlZones.totalRecords==0">
            <tr>
                <td colspan="4"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="dhlZones.records">
                <tr>
                    <td><s:property value="dhlApCode"/></td>
                    <td><s:property value="dhlApZone"/></td>
                    <td><s:property value="dhlCountryName"/></td>
                    <td><s:property value="dhlRegion"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="dhlZones.startRecord"/> <xms:localization
                    text="to"/> <s:property value="dhlZones.endRecord"/> <xms:localization text="of"/> <s:property
                    value="dhlZones.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="dhlZones!=null">
                <s:if test="dhlZones.hasPrev()">
                    <a href="javascript:changeDhlCountryZonePage(<s:property value="%{dhlZones.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="dhlZones.pageRange" status="count">
                    <s:if test="%{dhlZones.pageRange[#count.index] == dhlZones.currentPage}">
                        <a class="paginate_button current"><s:property value="dhlZones.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeDhlCountryZonePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="dhlZones.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeDhlCountryZonePage(<s:property value="%{dhlZones.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#dhl_country_zone_table").tablesorter({
            sortFieldId: "dhl_country_zone_order_field",
            sortTypeId: "dhl_country_zone_order_type",
            fieldList: ["dhl_ap_code", "dhl_ap_zone", "dhl_countryname", "dhl_region"],
            callback: getDhlCountryZones
        });
        actionPrint = "system_stats_print_dhl_country_zone.ix";
        actionExport = "system_stats_export_dhl_country_zone.ix";
    });

    function getDhlCountryZones() {
        callAction("dhl_country_zone");
    }

    function changeDhlCountryZonePage(page) {
        $("#dhl_country_zone_page").val(page);
        getDhlCountryZones();
    }

    function changeDhlCountryZonePageSize() {
        $("#dhl_country_zone_page").val(1);
        getDhlCountryZones();
    }
    <s:if test="dhlZones==null || dhlZones.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>