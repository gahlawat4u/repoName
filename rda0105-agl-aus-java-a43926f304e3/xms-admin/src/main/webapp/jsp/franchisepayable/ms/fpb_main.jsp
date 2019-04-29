<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Franchise Payable"/></a>&nbsp;&nbsp;<i
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
                                <div class="col-lg-2">
                                    <span class="label label-danger" id="frozen-message"></span>
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
                                                <td><s:select list="dateRange" value="" class="form-control"
                                                              id="payableSelDaterange"
                                                              onchange="changeDateRange();"></s:select></td>
                                                <td class="customDateRange" style="display: none"><s:textfield
                                                        name="startDate" class="form-control date form_datetime"
                                                        id="startDate" placeholder="Start Date"
                                                        data-date-format="dd MM yyyy"
                                                        readonly="true"></s:textfield></td>
                                                <td class="customDateRange" style="display: none"><s:textfield
                                                        name="endDate" class="form-control date form_datetime"
                                                        id="endDate" placeholder="End Date"
                                                        data-date-format="dd MM yyyy"
                                                        readonly="true"></s:textfield></td>
                                                <td>
                                                    <button class="btn s37" type="submit"
                                                            onclick="franchisePayableSubmit();">
                                                        <xms:localization text="Go"/>
                                                    </button>
                                                    <input id="rptTxnId" type="hidden"
                                                           value='<s:property value="rptTxnId"/>'/></td>
                                                <td>
                                                    <button class="btn s37 btn_export" onclick="export_report_html()"
                                                            disabled="disabled">
                                                        <xms:localization text="Printable Report"/>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn s37 btn_export" onclick="export_report_pdf()"
                                                            disabled="disabled">
                                                        <xms:localization text="PDF Report"/>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <ul id="generalTab" class="nav nav-tabs responsive">
                                    <li class="active" style="margin-left: 10px;"><a href="#Overview-tab"
                                                                                     data-toggle="tab"><xms:localization
                                            text="Overview"/></a></li>
                                    <li><a href="#paymentMarginDetail-tab" data-toggle="tab"><xms:localization
                                            text="Payment Margin Details"/></a></li>
                                    <li><a href="#carrierCreditDetails-tab" data-toggle="tab"><xms:localization
                                            text="Carrier Credit Details"/></a></li>
                                    <li><a href="#carrierCostDeduction-tab" data-toggle="tab"><xms:localization
                                            text="Carrier Cost Deduction"/></a></li>
                                    <li><a href="#after61PaymentCreditDetail-tab" data-toggle="tab"><xms:localization
                                            text="61 + Day Payment/Credit Details"/></a></li>
                                    <s:if test="enableNonCentralizedTab">
                                        <li><a href="#nonCentralizedDetails-tab" data-toggle="tab"><xms:localization
                                                text="Non Centralized Details"/></a></li>
                                    </s:if>
                                    <li><a href="#overpayment-tab" data-toggle="tab"><xms:localization
                                            text="Overpayment Page on Payables"/></a></li>
                                </ul>
                                <div id="" class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-5">
                                                <h4 class="s34">
                                                    <xms:localization text="Franchise Info :"/>
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
                                                    <xms:localization text="Activity :"/>
                                                </h4>
                                                <table class="table" style="font-size: 11px;">
                                                    <tr>
                                                        <td class="td1"><xms:localization text="Setups"/></td>
                                                        <td class="td2"><xms:localization text="0"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1"><xms:localization text="Activations #"/></td>
                                                        <td class="td2"><xms:localization text="0"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1"><xms:localization text="Printed Invoices"/></td>
                                                        <td class="td2"><xms:localization text="0"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1"><xms:localization text="E-mail Invoices"/></td>
                                                        <td class="td2"><xms:localization text="0"/></td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="col-lg-7">
                                                <h4 class="s34">
                                                    <xms:localization text="Summary :"/>
                                                </h4>
                                                <table class="table" style="font-size: 11px;">
                                                    <tr>
                                                        <td class="td1 s39"
                                                            style="font-style: italic; text-decoration: underline;">
                                                            <xms:localization text="Payables :"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40"><xms:localization
                                                                text="- Margin Share"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40"><xms:localization
                                                                text="- 61+ Payment Margin Share"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <s:if test="enableNonCentralizedTab">
                                                        <tr>
                                                            <td class="td1 s40"><xms:localization
                                                                    text="- Non Centralised Margin Share"/></td>
                                                            <td class="td2"><s:property value="currencySymbol"/>0.00
                                                            </td>
                                                        </tr>
                                                    </s:if>
                                                    <tr>
                                                        <td class="td1 s41"><xms:localization text="- Late Fee"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s39"
                                                            style="font-style: italic; text-decoration: underline;">
                                                            <xms:localization text="Gross Payables :"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s41"><xms:localization
                                                                text="- Gross Payables"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s39"
                                                            style="font-style: italic; text-decoration: underline;">
                                                            <xms:localization text="Other Payables :"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40"><xms:localization
                                                                text="- Repaid Carrier Deductions"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <s:if test="enableNonCentralizedTab">
                                                        <tr>
                                                            <td class="td1 s41"><xms:localization
                                                                    text="- Non Central Carrier Cost"/></td>
                                                            <td class="td2"><s:property value="currencySymbol"/>0.00
                                                            </td>
                                                        </tr>
                                                    </s:if>
                                                    <tr>
                                                        <td class="td1 s39"
                                                            style="font-style: italic; text-decoration: underline;">
                                                            <xms:localization text="Costs :"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40"><xms:localization
                                                                text="- Carrier Cost Deductions"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s40"><xms:localization text="- Tech Fees"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s41"><xms:localization
                                                                text="- Marketing Fee etc"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s39"
                                                            style="font-style: italic; text-decoration: underline;">
                                                            <xms:localization text="Net Payables :"/></td>
                                                        <td class="td2"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s41"><xms:localization
                                                                text="- Net Payables"/></td>
                                                        <td class="td2"><s:property value="currencySymbol"/>0.00</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s41"><xms:localization text="- GST"/></td>
                                                        <td class="td2"><s:if test="%{overview==null}"><s:property
                                                                value="currencySymbol"/>0.00</s:if> <s:else>
                                                            <s:property value="overview.gst"/>
                                                        </s:else></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td1 s41"><xms:localization
                                                                text="- Total Payables"/></td>
                                                        <td class="td2"><s:if test="%{overview==null}"><s:property
                                                                value="currencySymbol"/>0.00</s:if> <s:else>
                                                            <s:property value="overview.totalPayables"/>
                                                        </s:else></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!--(Payment Margin Details)-->
                                    <div id="paymentMarginDetail-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="Payment Margin Details"/>
                                                </p>

                                                <div class="form-group flr mgb">
                                                    <table class="">
                                                        <tr>
                                                            <td><select class="form-control">
                                                                <option><xms:localization text="Record Size"/></option>
                                                            </select></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <p>
                                                    <xms:localization text="
															Please Note:<br> - The details below list only
															invoices that have payment from .The invoices that do not
															have any payment are not listed."/>
                                                </p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-hover table-striped table-bordered mg0">
                                                    <thead>
                                                    <tr>
                                                        <th width="100px;"><xms:localization text="Payment Date"/></th>
                                                        <th><xms:localization text="Customer No"/></th>
                                                        <th><xms:localization text="Customer Name"/></th>
                                                        <th><xms:localization text="Invoice #"/></th>
                                                        <th><xms:localization text="AWB/Connote No"/></th>
                                                        <th><xms:localization text="International Domestic"/></th>
                                                        <th><xms:localization text="Amount Outstanding"/></th>
                                                        <th colspan="2"><xms:localization text="Customer Total"/></th>
                                                        <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                                                        <th colspan="2"><xms:localization text="Gross Margin"/></th>
                                                        <th><xms:localization text="Previously Paid(inc GST)"/></th>
                                                        <th><xms:localization text="Payment Received (inc GST)"/></th>
                                                        <th colspan="2"><xms:localization text="Credits"/></th>
                                                        <th><xms:localization text="Profit Share"/></th>
                                                    </tr>
                                                    <tr>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th><xms:localization text="Franchisee Cost."/></th>
                                                        <th><xms:localization text="Customer Cost."/></th>
                                                        <th style="border-top: none"></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="18"><xms:localization
                                                                text="No data available..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!--(Carrier Credit Details)-->
                                    <div id="carrierCreditDetails-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="Carrier Credit Details"/>
                                                </p>

                                                <div class="form-group flr mgb">
                                                    <table class="">
                                                        <tr>
                                                            <td><select class="form-control">
                                                                <option><xms:localization text="Record Size"/></option>
                                                            </select></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <p>
                                                    <xms:localization text="
															Please Note: <br> -The details below list only
															invoices that received carrier adjustment credits from
															01-12-2014 - 07-12-2014."/>
                                                </p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-hover table-striped table-bordered mg0"
                                                       id="datatable1">
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
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="18"><xms:localization
                                                                text="No data available ..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="dataTables_paginate">
                                                    <a class="paginate_button previous"><xms:localization
                                                            text="Previous"/></a><span><a
                                                        class="paginate_button current"><xms:localization text="1"/></a></span><a
                                                        class="paginate_button next"><xms:localization text="Next"/></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--(Carrier Credit Details)-->
                                    <!--Carrier Cost Deduction-->
                                    <div id="carrierCostDeduction-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="Carrier Cost Deduction"/>
                                                </p>

                                                <div class="form-group flr mgb">
                                                    <table class="">
                                                        <tr>
                                                            <td><select class="form-control">
                                                                <option><xms:localization text="Record Size"/></option>
                                                            </select></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <p>
                                                    <xms:localization text="
															Please Note:<br> - The details below list only
															invoices that reached 61 days past due from 01-12-2014 -
															07-12-2014 .<br> - The Franchise is charged 100% of
															the franchise cost.<br> - (Franchise can recover
															their losses by collecting from the customer after 61
															days.)"/>
                                                </p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-hover table-striped table-bordered mg0"
                                                       id="datatable" style="float: left">
                                                    <thead>
                                                    <tr>
                                                        <th><xms:localization text="Customer Name"/></th>
                                                        <th><xms:localization text="Invoice Number"/></th>
                                                        <th><xms:localization text="AWB/Connote No"/></th>
                                                        <th><xms:localization text="Amount Outstanding"/></th>
                                                        <th colspan="2"><xms:localization
                                                                text="Customer Total(Inc. Tax)"/></th>
                                                        <th colspan="2"><xms:localization
                                                                text="Franchise Cost(Inc. Tax)"/></th>
                                                        <th><xms:localization text="Franchise Charge"/></th>
                                                    </tr>
                                                    <tr>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th style="border-top: none"></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="9"><xms:localization
                                                                text="No data available ..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!--Carrier Cost Deduction-->
                                    <!--(Payment Margin Details)-->
                                    <!--61 + Day Payment/Credit Details-->
                                    <div id="after61PaymentCreditDetail-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="61 + Day Payment/Credit Details"/>
                                                </p>

                                                <p>
                                                    <xms:localization text="
															Please Note:<br> - The details below list only
															invoices > 61 days from invoice due date that had
															payment/credit activity from .<br> - Franchise is
															paid back 100% of carrier credits or payments that
															contribute toward the carrier cost<br> - Franchise
															is paid % of carrier credits or payments that result in a
															discount profit (amount above the carrier cost)<br>
															- Franchise is paid 50% of carrier credits or payments
															above the discount invoice price(list profit)"/>
                                                </p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-hover table-striped table-bordered mg0" id="">
                                                    <thead>
                                                    <tr>
                                                        <th width="100px;"><xms:localization text="Payment Date"/></th>
                                                        <th><xms:localization text="Customer No"/></th>
                                                        <th><xms:localization text="Customer Name"/></th>
                                                        <th><xms:localization text="Invoice #"/></th>
                                                        <th><xms:localization text="AWB/Connote No"/></th>
                                                        <th><xms:localization text="International Domestic"/></th>
                                                        <th><xms:localization text="Amount Outstanding"/></th>
                                                        <th colspan="2"><xms:localization text="Customer Total"/></th>
                                                        <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                                                        <th colspan="2"><xms:localization text="Gross Margin"/></th>
                                                        <th><xms:localization text="Previous Deducted"/></th>
                                                        <th><xms:localization text="Previously Paid(inc GST)"/></th>
                                                        <th><xms:localization text="Payment Received (inc GST)"/></th>
                                                        <th colspan="2"><xms:localization text="Credits"/></th>
                                                        <th><xms:localization text="Repaid Carrier Deductions"/></th>
                                                        <th><xms:localization text="Profit Share"/></th>
                                                        <th><xms:localization text="Profit Share on Late Fees"/></th>
                                                    </tr>
                                                    <tr>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th><xms:localization text="Franchisee Cost."/></th>
                                                        <th><xms:localization text="Customer Cost."/></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="21"><xms:localization
                                                                text="No data available..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!--61 + Day Payment/Credit Details-->
                                    <!--Non Centralized Details-->
                                    <div id="nonCentralizedDetails-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="Non Centralized Details"/>
                                                </p>

                                                <div class="form-group flr mgb">
                                                    <table class="">
                                                        <tr>
                                                            <td><select class="form-control">
                                                                <option><xms:localization text="Record Size"/></option>
                                                            </select></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <p>
                                                    <xms:localization text="
															Please Note:<br> - The details below list only
															invoices that have payment from .The invoices that do not
															have any payment are not listed."/>
                                                </p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-hover table-striped table-bordered mg0">
                                                    <thead>
                                                    <tr>
                                                        <th width="100px;"><xms:localization text="Payment Date"/></th>
                                                        <th><xms:localization text="Customer No"/></th>
                                                        <th><xms:localization text="Customer Name"/></th>
                                                        <th><xms:localization text="Invoice #"/></th>
                                                        <th><xms:localization text="AWB/Connote No"/></th>
                                                        <th><xms:localization text="International Domestic"/></th>
                                                        <th><xms:localization text="Amount Outstanding"/></th>
                                                        <th colspan="2"><xms:localization text="Customer Total"/></th>
                                                        <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                                                        <th colspan="2"><xms:localization text="Gross Margin"/></th>
                                                        <th><xms:localization text="Previously Paid(inc GST)"/></th>
                                                        <th><xms:localization text="Payment Received (inc GST)"/></th>
                                                        <th colspan="2"><xms:localization text="Credits"/></th>
                                                        <th><xms:localization text="Profit Share"/></th>
                                                    </tr>
                                                    <tr>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th><xms:localization text="Price Ex GST"/></th>
                                                        <th><xms:localization text="GST"/></th>
                                                        <th style="border-top: none"></th>
                                                        <th style="border-top: none"></th>
                                                        <th><xms:localization text="Franchisee Cost."/></th>
                                                        <th><xms:localization text="Customer Cost."/></th>
                                                        <th style="border-top: none"></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="18"><xms:localization
                                                                text="No data available ..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!--Non Centralized Details-->
                                    <!--Overpayment Page on Payables-->
                                    <div id="overpayment-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p class="s38">
                                                    <xms:localization text="Overpayment Page on Payables"/>
                                                </p>

                                                <div class="form-group flr mgb">
                                                    <table class="">
                                                        <tr>
                                                            <td><select class="form-control">
                                                                <option><xms:localization text="Record Size"/></option>
                                                            </select></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <p>
                                                    <xms:localization text="
															Please Note:<br> - The details below list only
															invoices that have payment from .The invoices that do not
															have any payment are not listed."/>
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
                                    <!--Overpayment Page on Payables-->
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
<script type="text/javascript">
    $('#selFranchiseCode').val('<s:property value='franchiseCode'/>');
</script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/common/common-ms.js"></script>