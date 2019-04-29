<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Franchise Receivable"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Franchise Payable"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<div class="page-content">
    <div id="tab-general">
        <div class="row mbl">
            <div class="col-lg-12">
                <div class="col-md-12">
                    <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Generate Report"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <table class="s36">
                                            <tr>
                                                <td><xms:localization text="Franchise:"/></td>
                                                <td><select id="selFranchiseCode" name="franchiseCode"
                                                            class="form-control">
                                                    <option value=""><xms:localization text="All"/></option>
                                                    <s:iterator value="franchiseList">
                                                        <option value="<s:property value='code'/>"><s:property
                                                                value='code'/></option>
                                                    </s:iterator>
                                                </select></td>
                                                <td><xms:localization text="Date Range:"/></td>
                                                <td><s:select list="dateRange" class="form-control"
                                                              id="payableSelDaterange"
                                                              onchange="changeDateRange();"/></td>
                                                <td class="customDateRange" style="display: none;"><xms:localization
                                                        text="Start:"/></td>
                                                <td class="customDateRange" style="display: none;"><s:textfield
                                                        id="startDate" cssClass="form-control date form_datetime"
                                                        data-date-format="dd MM yyyy" readonly="true"/></td>
                                                <td class="customDateRange" style="display: none;"><xms:localization
                                                        text="End:"/></td>
                                                <td class="customDateRange" style="display: none;"><s:textfield
                                                        id="endDate" cssClass="form-control date form_datetime"
                                                        data-date-format="dd MM yyyy" readonly="true"/></td>
                                                <td>
                                                    <button class="btn s37" type="button" onclick="fpbSubmit()">
                                                        <xms:localization text="Go"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button id="btn-print" class="btn s37" type="button"
                                                            onclick="export_report_html()" disabled="disabled">
                                                        <xms:localization text="Printable Report"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button id="btn-export" class="btn s37" type="button"
                                                            onclick="export_report_pdf()" disabled="disabled">
                                                        <xms:localization text="PDF Report"/>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                        <s:hidden name="rptTxnId" id="rptTxnId"/>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <ul id="generalTab" class="nav nav-tabs responsive">
                                    <li class="active" style="margin-left: 10px;"><a href="#overview-tab"
                                                                                     data-toggle="tab"><xms:localization
                                            text="Overview"/></a></li>
                                    <li><a href="#shipment-details-tab" data-toggle="tab"><xms:localization
                                            text="Shipment Details"/></a></li>
                                    <li><a href="#carrier-credit-tab" data-toggle="tab"><xms:localization
                                            text="Carrier Credit Details"/></a></li>
                                    <li><a href="#tech-fee-tab" data-toggle="tab"><xms:localization
                                            text="Technology Fee Details"/></a></li>
                                    <li><a href="#overpayment-tab" data-toggle="tab"><xms:localization
                                            text="Overpayment Page on Payables"/></a></li>
                                </ul>
                                <div class="tab-content responsive hidden-xs hidden-sm">
                                    <div id="overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-5">
                                                <h4 class="s34">
                                                    <xms:localization text="Franchise Info:"/>
                                                </h4>
                                                <table class="table" style="font-size: 11px;">
                                                    <tr>
                                                        <td class="td1"><xms:localization text="Name"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1"><xms:localization text="Franchise #"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1"><xms:localization text="Date Range"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                </table>
                                                <h4 class="s34">
                                                    <xms:localization text="Activity:"/>
                                                </h4>
                                                <table class="table" style="font-size: 11px;">
                                                    <tr>
                                                        <td class="td1"><xms:localization text="Setups"/></td>
                                                        <td class="td2">0</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1"><xms:localization text="Activations #"/></td>
                                                        <td class="td2">0</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1"><xms:localization text="Printed Invoices"/></td>
                                                        <td class="td2">0</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1"><xms:localization text="E-mail Invoices"/></td>
                                                        <td class="td2">0</td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="col-lg-7">
                                                <h4 class="s34">
                                                    <xms:localization text="Summary:"/>
                                                </h4>
                                                <table class="table" style="font-size: 11px;">
                                                    <tr>
                                                        <td class="td1 s39"
                                                            style="font-style: italic; text-decoration: underline;">
                                                            <xms:localization text="Receiveable"/> :
                                                        </td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Customer Cost"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Customer Marginable Cost"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Franchise Cost Taxable"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Franchise Cost Non-taxable"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Franchise TVA"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Total Franchise Cost"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Margin Shared"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Management Fee on Revenue Shared"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Marketing Fee"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Carrier Credit Taxable"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Carrier Credit Non-taxable"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Carrier Credit TVA"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Total Carrier Credit"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Tech Fees on International Shipments"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00 (0)
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s41">- <xms:localization
                                                                text="Tech Fees on Domestic Shipments"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00 (0)
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s39"
                                                            style="font-style: italic; text-decoration: underline;">
                                                            <xms:localization text="Net Receiveable"/> :
                                                        </td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization
                                                                text="Net Receiveable"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40">- <xms:localization text="TVA"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s41">- <xms:localization
                                                                text="Total Receiveable"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- === END OVERVIEW TAB -->
                                    <!-- === SHIPMENT DETAIL TAB -->
                                    <div id="shipment-details-tab" class="tab-pane fade ">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="Shipment Details"/>
                                                </p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-hover table-striped table-bordered mg0">
                                                    <thead>
                                                    <tr>
                                                        <th width="100px;" rowspan="2"><xms:localization
                                                                text="Import Date"/></th>
                                                        <th rowspan="2"><xms:localization text="Customer No"/></th>
                                                        <th rowspan="2"><xms:localization text="Customer Name"/></th>
                                                        <th rowspan="2"><xms:localization text="Invoice #"/></th>
                                                        <th rowspan="2"><xms:localization text="AWB/Connote No"/></th>
                                                        <th rowspan="2"><xms:localization
                                                                text="International Domestic"/></th>
                                                        <th colspan="2"><xms:localization text="Customer Total"/></th>
                                                        <th rowspan="2"><xms:localization
                                                                text="Customer Marginable Cost"/></th>
                                                        <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                                                        <th colspan="2"><xms:localization text="Gross Margin"/></th>
                                                        <th colspan="2"><xms:localization text="Credits"/></th>
                                                        <th rowspan="2"><xms:localization
                                                                text="Management Fees on Revenue"/></th>
                                                        <th rowspan="2"><xms:localization
                                                                text="Marketing Fees on Revenue"/></th>
                                                        <th rowspan="2"><xms:localization text="Profit Share"/></th>
                                                    </tr>
                                                    <tr>
                                                        <th><xms:localization text="Price Ex TVA"/></th>
                                                        <th><xms:localization text="TVA"/></th>
                                                        <th><xms:localization text="Price Ex TVA"/></th>
                                                        <th><xms:localization text="TVA"/></th>
                                                        <th><xms:localization text="Price Ex TVA"/></th>
                                                        <th><xms:localization text="TVA"/></th>
                                                        <th><xms:localization text="Franchisee Cost."/></th>
                                                        <th><xms:localization text="Customer Cost."/></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="17"><xms:localization
                                                                text="No data available..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- === END SHIPMENT DETAIL TAB === -->
                                    <!-- === CARRIER CREDIT TAB === -->
                                    <div id="carrier-credit-tab" class="tab-pane fade">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="Carrier Credit Details"/>
                                                </p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-hover table-striped table-bordered mg0">
                                                    <thead>
                                                    <tr>
                                                        <th colspan="7"></th>
                                                        <th colspan="6"><i><xms:localization
                                                                text="Original Invoice"/></i></th>
                                                        <th colspan="4"><i><xms:localization text="Credit"/></i></th>
                                                        <th colspan="2"></th>
                                                    </tr>
                                                    <tr>
                                                        <th width="100px;"><xms:localization text="Date"/></th>
                                                        <th><xms:localization text="Customer No"/></th>
                                                        <th><xms:localization text="Customer Name"/></th>
                                                        <th><xms:localization text="Invoice #"/></th>
                                                        <th><xms:localization text="Credit Note Number"/></th>
                                                        <th><xms:localization text="AWB/Connote No"/></th>
                                                        <th><xms:localization text="International Domestic"/></th>
                                                        <th colspan="2"><xms:localization text="Customer Total"/></th>
                                                        <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                                                        <th colspan="2"><xms:localization text="Gross Margin"/></th>
                                                        <th colspan="2"><xms:localization text="Customer Total"/></th>
                                                        <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                                                        <th colspan="2"><xms:localization text="New Margin"/></th>
                                                    </tr>
                                                    <tr>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th><xms:localization text="Price Ex TVA"/></th>
                                                        <th><xms:localization text="TVA"/></th>
                                                        <th><xms:localization text="Price Ex TVA"/></th>
                                                        <th><xms:localization text="TVA"/></th>
                                                        <th><xms:localization text="Price Ex TVA"/></th>
                                                        <th><xms:localization text="TVA"/></th>
                                                        <th><xms:localization text="Price Ex TVA"/></th>
                                                        <th><xms:localization text="TVA"/></th>
                                                        <th><xms:localization text="Price Ex TVA"/></th>
                                                        <th><xms:localization text="TVA"/></th>
                                                        <th><xms:localization text="Price Ex TVA"/></th>
                                                        <th><xms:localization text="TVA"/></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="19"><xms:localization
                                                                text="No data available..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- === END CARRIER CREDIT TAB === -->
                                    <!-- === TECHNOLOGY FEE DETAILS TAB === -->
                                    <div id="tech-fee-tab" class="tab-pane fade">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="Technology Fee Details"/>
                                                </p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-hover table-striped table-bordered mg0">
                                                    <thead>
                                                    <tr>
                                                        <th width="100px;"><xms:localization text="Import Date"/></th>
                                                        <th><xms:localization text="Customer No"/></th>
                                                        <th><xms:localization text="Customer Name"/></th>
                                                        <th><xms:localization text="Invoice #"/></th>
                                                        <th><xms:localization text="AWB/Connote No"/></th>
                                                        <th><xms:localization text="International Domestic"/></th>
                                                        <th><xms:localization
                                                                text="Tech Fee on International Shipments"/></th>
                                                        <th><xms:localization
                                                                text="Tech Fee on Domestic Shipments"/></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="8"><xms:localization
                                                                text="No data available..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- === END TECHNOLOGY FEE DETIALS TAB === -->
                                    <!-- === OVERPAYMENT TAB === -->
                                    <div id="overpayment-tab" class="tab-pane fade">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="Overpayment Page on Payables"/>
                                                </p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-hover table-striped table-bordered mg0"
                                                       id="datatable">
                                                    <thead>
                                                    <tr>
                                                        <th><xms:localization text="Original Payment Data"/></th>
                                                        <th><xms:localization text="Customer No"/></th>
                                                        <th><xms:localization text="Customer Name"/></th>
                                                        <th><xms:localization text="Overpayment Type"/></th>
                                                        <th><xms:localization text="Amount"/></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="5"><xms:localization
                                                                text="No data available..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- === END OVERPAYMENT TAB === -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--END CONTENT-->
<!--END CONTENT-->
<script type="text/javascript">
    $('#selFranchiseCode').val('<s:property value='franchiseCode'/>');
</script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/pages/fpb_sc.js"></script>