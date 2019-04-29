<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="toll_priority_rate_sheet_form">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <table class="s36">
                    <tbody>
                    <tr>
                        <td><xms:localization text="Show"/></td>
                        <td><s:select id="toll_priority_rate_sheet_page_size" name="pageSize" cssClass="form-control"
                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                      onchange="changeTollPriorityRateSheetPageSize()"/></td>
                        <td><xms:localization text="Entries"/></td>
                    </tr>
                    </tbody>
                </table>
            </th>
        </tr>
        </tbody>
    </table>
    <s:hidden id="toll_priority_rate_sheet_page" name="page"/>
    <s:hidden id="toll_priority_rate_sheet_order_type" name="orderType"/>
    <s:hidden id="toll_priority_rate_sheet_order_field" name="orderField"/>
</form>
<div class="s70">
    <table class="table table-bordered mg0" id="toll_priority_rate_sheet_table">
        <thead>
        <tr>
            <th><xms:localization text="Customer"/></th>
            <th><xms:localization text="Product"/></th>
            <th><xms:localization text="Service"/></th>
            <th><xms:localization text="Zone From"/></th>
            <th><xms:localization text="Zone To"/></th>
            <th><xms:localization text="Price Type"/></th>
            <th><xms:localization text="Min Charge"/></th>
            <th><xms:localization text="Con Rate"/></th>
            <th><xms:localization text="Kgs Included"/></th>
            <th><xms:localization text="Kgs Rate"/></th>
            <th><xms:localization text="GST Pct"/></th>
            <th><xms:localization text="Cubic Conv"/></th>
            <th><xms:localization text="Surcharge Pct"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="tollPriorityRateSheets==null || tollPriorityRateSheets.totalRecords==0">
            <tr>
                <td colspan="13"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="tollPriorityRateSheets.records">
                <tr>
                    <td><s:property value="customer"/></td>
                    <td><s:property value="product"/></td>
                    <td><s:property value="service"/></td>
                    <td><s:property value="zoneFrom"/></td>
                    <td><s:property value="zoneTo"/></td>
                    <td><s:property value="priceType"/></td>
                    <td><s:property value="minCharge"/></td>
                    <td><s:property value="conRate"/></td>
                    <td><s:property value="kgsIncluded"/></td>
                    <td><s:property value="kgsRate"/></td>
                    <td><s:property value="gstPct"/></td>
                    <td><s:property value="cubicConv"/></td>
                    <td><s:property value="surchargePct"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="tollPriorityRateSheets.startRecord"/>
                <xms:localization text="to"/> <s:property value="tollPriorityRateSheets.endRecord"/> <xms:localization
                        text="of"/> <s:property value="tollPriorityRateSheets.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="tollPriorityRateSheets!=null">
                <s:if test="tollPriorityRateSheets.hasPrev()">
                    <a href="javascript:changeTollPriorityRateSheetPage(<s:property value="%{tollPriorityRateSheets.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="tollPriorityRateSheets.pageRange" status="count">
                    <s:if test="%{tollPriorityRateSheets.pageRange[#count.index] == tollPriorityRateSheets.currentPage}">
                        <a class="paginate_button current"><s:property value="tollPriorityRateSheets.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeTollPriorityRateSheetPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="tollPriorityRateSheets.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeTollPriorityRateSheetPage(<s:property value="%{tollPriorityRateSheets.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#toll_priority_rate_sheet_table").tablesorter({
            sortFieldId: "toll_priority_rate_sheet_order_field",
            sortTypeId: "toll_priority_rate_sheet_order_type",
            fieldList: ["customer", "product", "service", "zone_from", "zone_to", "price_type", "min_charge", "con_rate", "kgs_included", "kgs_rate", "gst_pct", "cubic_conv", "surcharge_pct"],
            callback: getTollPriorityRateSheets
        });
        actionPrint = "system_stats_print_toll_priority_rate_sheet.ix";
        actionExport = "system_stats_export_toll_priority_rate_sheet.ix";
    });

    function getTollPriorityRateSheets() {
        callAction("toll_priority_rate_sheet");
    }

    function changeTollPriorityRateSheetPage(page) {
        $("#toll_priority_rate_sheet_page").val(page);
        getTollPriorityRateSheets();
    }

    function changeTollPriorityRateSheetPageSize() {
        $("#toll_priority_rate_sheet_page").val(1);
        getTollPriorityRateSheets();
    }
    <s:if test="tollPriorityRateSheets==null || tollPriorityRateSheets.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

</script>