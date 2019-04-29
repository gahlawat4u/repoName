<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div id="generate_report_overview_tab" class="tab-pane fade in active">
    <div class="row">
        <div class="portlet-body b12 b11">
            <div class="row">
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tbody>
                        <tr>
                            <td style="border-top: 0px !important" colspan="">
                                <div class="caption b17">
                                    <xms:localization text="Sales Rep Info"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Sales Rep"/></td>
                            <td class="td2"><s:property value="overview.displayName"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Franchise"/></td>
                            <td class="td2"><s:property value="overview.userCode"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Date Range"/></td>
                            <td class="td2"><s:property value="startDate"/> - <s:property value="endDate"/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tbody>
                        <tr>
                            <td style="border-top: 0px !important" colspan="">
                                <div class="caption b17">
                                    <xms:localization text="Activity"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Setups"/></td>
                            <td class="td2"><s:property value="overview.setups"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Activations"/></td>
                            <td class="td2"><s:property value="overview.activations"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Printed Invoices"/></td>
                            <td class="td2"><s:property value="overview.printedInvoices"/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tbody>
                        <tr>
                            <td style="border-top: 0px !important" colspan="">
                                <div class="caption b17">&nbsp;</div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="E-mail Invoices"/></td>
                            <td class="td2"><s:property value="overview.emailInvoices"/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-lg-12" id="sales_rep_service_stats">
                    <div class="s70">
                        <s:hidden id="svOrderField" name="svOrderField"/>
                        <s:hidden id="svOrderType" name="svOrderType"/>
                        <table class="table table-bordered mg0" id="sales_rep_service_stats_table">
                            <thead>
                            <tr bgcolor="#F0F2F5">
                                <th><xms:localization text="Service Level"/></th>
                                <th class="text-right"><xms:localization text="Shipment Goals"/></th>
                                <th class="text-right"><xms:localization text="Actual Shipments"/></th>
                                <th class="text-right"><xms:localization text="% Goal"/></th>
                                <th class="text-right"><xms:localization text="Actual Margin"/></th>
                                <th class="text-right"><xms:localization text="% Payout"/></th>
                                <th class="text-right"><xms:localization text="Payout"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:if test="serviceStats==null || serviceStats.size()==0">
                                <tr>
                                    <td colspan="7"><xms:localization text="No data available..."/></td>
                                </tr>
                            </s:if>
                            <s:else>
                                <s:iterator value="serviceStats">
                                    <tr>
                                        <td><s:property value="serviceName"/></td>
                                        <td class="text-right"><s:property value="goal"/></td>
                                        <td class="text-right"><s:property value="actualShipments"/></td>
                                        <td class="text-right"><s:property value="goalPct"/></td>
                                        <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                value="actualMargin"/></td>
                                        <td class="text-right"><s:property value="payout"/></td>
                                        <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                value="payoutAmount"/></td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="generate_report_detail_tab" class="tab-pane fade in">
    <div class="row">
        <div class="portlet-body b12 b11">
            <div class="form-group row">
                <div class="col-lg-12 ">
                    <div class="form-group" style="float: right">
                        <table class="s36">
                            <tbody>
                            <tr>
                                <td>
                                    <div class="btn-group" id="btn-export" style="display: none;">
                                        <input type="button" id="export-option" class="btn s37"
                                               value="<xms:localization text="Option" />"
                                               onclick="exportClick($('#selected-option').val())"/>
                                        <button type="button" class="btn s37 dropdown-toggle" data-toggle="dropdown"
                                                aria-expanded="true">
                                            <span class="caret"></span>
                                        </button>
                                        <s:hidden id="selected-option"/>
                                        <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                            <li>
                                                <a href="#" onclick="exportClick('option')"><xms:localization
                                                        text="Option"/></a>
                                            </li>
                                            <li>
                                                <a href="#" onclick="exportClick('html')"><xms:localization
                                                        text="Print"/></a>
                                            </li>
                                            <li>
                                                <a href="#" onclick="exportClick('xls')"><xms:localization
                                                        text="Export"/></a>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-lg-8 form-group">
                    <table class="table" style="font-size: 11px; margin-bottom: 0px">
                        <tr>
                            <td class="td1"><xms:localization text="Please Note"/>:</td>
                            <td class="td2"><xms:localization
                                    text="- Sales Rep Commissions are paid only on margin from paid invoices.<br /> - The details below list only invoices that are paid from . Unpaid invoices are irrelevant and not listed."/></td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-12">
                    <table class="table mg0">
                        <tbody>
                        <tr>
                            <th class="s42">
                                <table class="s36">
                                    <tbody>
                                    <tr>
                                        <td><xms:localization text="Show"/></td>
                                        <td><s:select id="awbPageSize" name="awbPageSize" cssClass="form-control"
                                                      cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                                      onchange="getSalesRepAirbillStats()"/></td>
                                        <td><xms:localization text="Entries"/></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </th>
                        </tr>
                        </tbody>
                    </table>
                    <div id="sales_rep_airbill_stats">
                        <div class="s70">
                            <s:hidden id="awbPage" name="awbPage"/>
                            <s:hidden id="awbOrderField" name="awbOrderField"/>
                            <s:hidden id="awbOrderType" name="awbOrderType"/>
                            <table class="table table-bordered mg0" id="sales_rep_airbill_stats_table">
                                <thead>
                                <tr bgcolor="#F0F2F5">
                                    <th><xms:localization text="Airbill Number"/></th>
                                    <th><xms:localization text="Invoice Number"/></th>
                                    <th><xms:localization text="Customer Name"/></th>
                                    <th class="text-right"><xms:localization text="Customer Total"/></th>
                                    <th class="text-right"><xms:localization text="Franchise Cost"/></th>
                                    <th class="text-right"><xms:localization text="Previously Paid"/></th>
                                    <th class="text-right"><xms:localization text="Margin On Customer Total"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:if test="airbillStats==null || airbillStats.totalRecords==0">
                                    <tr>
                                        <td colspan="7"><xms:localization text="No data available..."/></td>
                                    </tr>
                                </s:if>
                                <s:else>
                                    <s:iterator value="airbillStats.records">
                                        <tr>
                                            <td><s:property value="airbillNumber"/></td>
                                            <td><s:property value="invoiceCode"/></td>
                                            <td><s:property value="customerName"/></td>
                                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                    value="customerCost"/></td>
                                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                    value="franchiseCost"/></td>
                                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                    value="totalPaid"/></td>
                                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                    value="margin"/></td>
                                        </tr>
                                    </s:iterator>
                                </s:else>
                                </tbody>
                            </table>
                        </div>
                        <div class="dataTables_paginate records">
                            <div class="row">
                                <div class="col-xs-4 text-left">
                                    <b><xms:localization text="Showing"/> <s:property value="airbillStats.startRecord"/>
                                        <xms:localization text="to"/> <s:property value="airbillStats.endRecord"/>
                                        <xms:localization text="of"/> <s:property value="airbillStats.totalRecords"/>
                                        <xms:localization text="entries"/></b>
                                </div>
                                <div class="col-xs-8">
                                    <s:if test="airbillStats!=null">
                                        <s:if test="airbillStats.hasPrev()">
                                            <a href="javascript:changeAwbPageSize(<s:property value="%{airbillStats.currentPage - 1}"/>)"
                                               class="paginate_button previous"><xms:localization text="Previous"/></a>
                                        </s:if>
										<span> <s:iterator value="airbillStats.pageRange" status="count">
                                            <s:if test="%{airbillStats.pageRange[#count.index] == airbillStats.currentPage}">
                                                <a class="paginate_button current"><s:property
                                                        value="airbillStats.currentPage"/></a>
                                            </s:if>
                                            <s:else>
                                                <a class="paginate_button"
                                                   href="javascript:changeAwbPageSize(<s:property/>);"><s:property/></a>
                                            </s:else>
                                        </s:iterator>
										</span>
                                        <s:if test="airbillStats.hasNext()">
                                            <a class="paginate_button next"
                                               href="javascript:changeAwbPageSize(<s:property value="%{airbillStats.currentPage+1}"/>)"><xms:localization
                                                    text="Next"/></a>
                                        </s:if>
                                    </s:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var serviceColumns = ["service_name", "goal", "actual_shipments", "goal_pct", "actual_margin", "payout", "payout_amt"];
    var airbillColumns = ["airbill_number", "invoice_code", "customer_name", "customer_cost", "franchise_cost", "total_paid", "margin"];
    $(document).ready(function () {
        // Set default active tab is Generate Report Overview tab.
        $("#generate_report_tab_nav>li>a").each(function () {
            if ($(this).attr("href") == "#generate_report_overview_tab") {
                $(this).parent().addClass("active");
            } else {
                $(this).parent().removeClass("active");
            }
        });
        $("#sales_rep_service_stats_table").tablesorter({
            sortFieldId: "svOrderField",
            sortTypeId: "svOrderType",
            fieldList: serviceColumns,
            callback: getSalesRepServiceStats
        });
        $("#sales_rep_airbill_stats_table").tablesorter({
            sortFieldId: "awbOrderField",
            sortTypeId: "awbOrderType",
            fieldList: airbillColumns,
            callback: getSalesRepAirbillStats
        });
    });
    <s:if test="airbillStats==null || airbillStats.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

</script>