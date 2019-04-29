<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li>
            <xms:localization text="Payables"/>
            &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li>
            <xms:localization text="Sales Rep"/>
            &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Sales Rep Commissions"/>
        </li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<script type="text/javascript">
    var hasRecords = false;
</script>
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
                                    <xms:localization text="Sales Rep Commissions"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="row">
                                                    <div class="portlet-body b12 b11">
                                                        <div class="portlet-body b22" style="padding: 0px;">
                                                            <ul id="main_sales_rep_nav" class="nav nav-tabs">
                                                                <li class="active" style="margin-left: 10px;">
                                                                    <a href="#sales_rep_settings_tab" data-toggle="tab"><xms:localization
                                                                            text="Sales Rep Settings"/></a>
                                                                </li>
                                                                <li>
                                                                    <a href="#generate_report_tab"
                                                                       data-toggle="tab"><xms:localization
                                                                            text="Generate Report"/></a>
                                                                </li>
                                                            </ul>
                                                            <div class="tab-content responsive">
                                                                <div id="sales_rep_settings_tab"
                                                                     class="tab-pane fade in active">
                                                                    <div class="row">
                                                                        <div class="col-lg-12 ">
                                                                            <div class="form-group ">
                                                                                <table class="s36">
                                                                                    <tbody>
                                                                                    <tr>
                                                                                        <td><xms:localization
                                                                                                text="Add existing sales rep :"/><span
                                                                                                class="s30">*</span>
                                                                                        </td>
                                                                                        <td><s:i18n_select
                                                                                                id="existingSalesReps"
                                                                                                list="existingSalesReps"
                                                                                                listValue="userCode + ' - ' + displayName"
                                                                                                listKey="userId"
                                                                                                cssClass="form-control"
                                                                                                headerKey=""
                                                                                                headerValue=""
                                                                                                i18nitem="no"/></td>
                                                                                        <td>
                                                                                            <button class="btn s37"
                                                                                                    onclick="createSalesRepSetting()">
                                                                                                <xms:localization
                                                                                                        text="New"/>
                                                                                            </button>
                                                                                        </td>
                                                                                    </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-lg-12">
                                                                            <table class="table mg0">
                                                                                <tbody>
                                                                                <tr>
                                                                                    <th class="s42">
                                                                                        <table class="s36">
                                                                                            <tbody>
                                                                                            <tr>
                                                                                                <td><xms:localization
                                                                                                        text="Show"/></td>
                                                                                                <td><s:select
                                                                                                        id="sales_rep_page_size"
                                                                                                        cssClass="form-control"
                                                                                                        cssStyle="height: 22px; padding-top: 1px;"
                                                                                                        list="pageSizes"
                                                                                                        onchange="doSearchSalesRep()"/></td>
                                                                                                <td><xms:localization
                                                                                                        text="Entries"/></td>
                                                                                            </tr>
                                                                                            </tbody>
                                                                                        </table>
                                                                                    </th>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>
                                                                            <input type="hidden" id="sales_rep_page"
                                                                                   value="1"/>

                                                                            <div id="sales_rep_settings_report">
                                                                                <div class="s70">
                                                                                    <table class="table table-bordered mg0"
                                                                                           id="sales_rep_settings_report_table">
                                                                                        <thead>
                                                                                        <tr>
                                                                                            <th colspan="2">
                                                                                                <xms:localization
                                                                                                        text="General Info"/></th>
                                                                                            <th><xms:localization
                                                                                                    text="Salary and Allowances"/></th>
                                                                                            <th><xms:localization
                                                                                                    text="Payout (%Margin)"/></th>
                                                                                            <th><xms:localization
                                                                                                    text="Deductions"/></th>
                                                                                            <th><xms:localization
                                                                                                    text="Setups/Activations Goals"/></th>
                                                                                            <th><xms:localization
                                                                                                    text="Shipment Goals"/></th>
                                                                                            <th><xms:localization
                                                                                                    text="Other"/></th>
                                                                                        </tr>
                                                                                        </thead>
                                                                                        <tbody>
                                                                                        <s:if test="salesRepReport==null || salesRepReport.totalRecords==0">
                                                                                            <tr>
                                                                                                <td colspan="8">
                                                                                                    <xms:localization
                                                                                                            text="No data available..."/></td>
                                                                                            </tr>
                                                                                        </s:if>
                                                                                        <s:else>
                                                                                            <s:iterator
                                                                                                    value="salesRepReport.records">
                                                                                                <tr>
                                                                                                    <td>
                                                                                                        <xms:localization
                                                                                                                text="Franchise: "/>
                                                                                                        <s:property
                                                                                                                value="user.userCode"/><br/>
                                                                                                        <xms:localization
                                                                                                                text="Sales Rep: "/>
                                                                                                        <s:property
                                                                                                                value="user.displayName"/>
                                                                                                    </td>
                                                                                                    <td width="41">
                                                                                                        <button class="btn s37"
                                                                                                                onclick="editSalesRepSetting(
                                                                                                                    <s:property
                                                                                                                            value="salesRepId"/>)">
                                                                                                            <xms:localization
                                                                                                                    text="Edit"/>
                                                                                                        </button>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <xms:localization
                                                                                                                text="Salary: "/>
                                                                                                        <s:property
                                                                                                                value="currencySymbol"/>
                                                                                                        <s:property
                                                                                                                value="salary"/><br/>
                                                                                                        <xms:localization
                                                                                                                text="Vehicle Allow: "/>
                                                                                                        <s:property
                                                                                                                value="currencySymbol"/>
                                                                                                        <s:property
                                                                                                                value="vehicleAllowance"/><br/>
                                                                                                        <xms:localization
                                                                                                                text="Phone Allow: "/>
                                                                                                        <s:property
                                                                                                                value="phoneAllowance"/><br/>
                                                                                                        <xms:localization
                                                                                                                text="Health Allow: "/>
                                                                                                        <s:property
                                                                                                                value="healthAllowance"/><br/>
                                                                                                        <s:property
                                                                                                                value="allowance1Description"/>
                                                                                                        <s:property
                                                                                                                value="currencySymbol"/>
                                                                                                        <s:property
                                                                                                                value="allowance1Amount"/><br/>
                                                                                                        <s:property
                                                                                                                value="allowance2Description"/>
                                                                                                        <s:property
                                                                                                                value="currencySymbol"/>
                                                                                                        <s:property
                                                                                                                value="allowance2Amount"/><br/>
                                                                                                        <s:property
                                                                                                                value="allowance3Description"/>
                                                                                                        <s:property
                                                                                                                value="currencySymbol"/>
                                                                                                        <s:property
                                                                                                                value="allowance3Amount"/>
                                                                                                    </td>
                                                                                                    <td><s:iterator
                                                                                                            value="salesRepServices">
                                                                                                        <s:if test="payout!=0">
                                                                                                            <s:property
                                                                                                                    value="service.serviceName"/>:
                                                                                                            <s:property
                                                                                                                    value="payout"/>
                                                                                                        </s:if>
                                                                                                        <br/>
                                                                                                    </s:iterator></td>
                                                                                                    <td><s:property
                                                                                                            value="deduction1Description"/>
                                                                                                        <s:property
                                                                                                                value="currencySymbol"/>
                                                                                                        <s:property
                                                                                                                value="deduction1Amount"/><br/>
                                                                                                        <s:property
                                                                                                                value="deduction2Description"/>
                                                                                                        <s:property
                                                                                                                value="currencySymbol"/>
                                                                                                        <s:property
                                                                                                                value="deduction2Amount"/><br/>
                                                                                                        <s:property
                                                                                                                value="deduction3Description"/>
                                                                                                        <s:property
                                                                                                                value="currencySymbol"/>
                                                                                                        <s:property
                                                                                                                value="deduction3Amount"/>
                                                                                                    </td>
                                                                                                    <td>
                                                                                                        <xms:localization
                                                                                                                text="Setups: "/>
                                                                                                        <s:property
                                                                                                                value="setups"/><br/>
                                                                                                        <xms:localization
                                                                                                                text="Activation: "/>
                                                                                                        <s:property
                                                                                                                value="activation"/><br/>
                                                                                                        <xms:localization
                                                                                                                text="DayOfActivate: "/>
                                                                                                        <s:property
                                                                                                                value="dayOfActivate"/>
                                                                                                    </td>
                                                                                                    <td><s:iterator
                                                                                                            value="salesRepServices">
                                                                                                        <s:if test="goal!=0">
                                                                                                            <s:property
                                                                                                                    value="service.serviceName"/>:
                                                                                                            <s:property
                                                                                                                    value="goal"/>
                                                                                                        </s:if>
                                                                                                        <br/>
                                                                                                    </s:iterator></td>
                                                                                                    <td>
                                                                                                        <xms:localization
                                                                                                                text="Sales Manager: "/>
                                                                                                        <s:property
                                                                                                                value="salesManager"/><br/>
                                                                                                        <xms:localization
                                                                                                                text="Sales Manager % Payouts: "/>
                                                                                                        <s:property
                                                                                                                value="percentPayout"/>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </s:iterator>
                                                                                        </s:else>
                                                                                        </tbody>
                                                                                    </table>
                                                                                </div>
                                                                                <div class="dataTables_paginate records">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-4 text-left">
                                                                                            <b><xms:localization
                                                                                                    text="Showing"/>
                                                                                                <s:property
                                                                                                        value="salesRepReport.startRecord"/>
                                                                                                <xms:localization
                                                                                                        text="to"/>
                                                                                                <s:property
                                                                                                        value="salesRepReport.endRecord"/>
                                                                                                <xms:localization
                                                                                                        text="of"/>
                                                                                                <s:property
                                                                                                        value="salesRepReport.totalRecords"/>
                                                                                                <xms:localization
                                                                                                        text="entries"/></b>
                                                                                        </div>
                                                                                        <div class="col-xs-8">
                                                                                            <s:if test="salesRepReport!=null">
                                                                                                <s:if test="salesRepReport.hasPrev()">
                                                                                                    <a href="javascript:changeSalesRepPage(<s:property value="%{salesRepReport.currentPage - 1}"/>)"
                                                                                                       class="paginate_button previous"><xms:localization
                                                                                                            text="Previous"/></a>
                                                                                                </s:if>
																								<span> <s:iterator
                                                                                                        value="salesRepReport.pageRange"
                                                                                                        status="count">
                                                                                                    <s:if test="%{salesRepReport.pageRange[#count.index] == salesRepReport.currentPage}">
                                                                                                        <a class="paginate_button current"><s:property
                                                                                                                value="salesRepReport.currentPage"/></a>
                                                                                                    </s:if>
                                                                                                    <s:else>
                                                                                                        <a class="paginate_button"
                                                                                                           href="javascript:changeSalesRepPage(<s:property/>);"><s:property/></a>
                                                                                                    </s:else>
                                                                                                </s:iterator>
																								</span>
                                                                                                <s:if test="salesRepReport.hasNext()">
                                                                                                    <a class="paginate_button next"
                                                                                                       href="javascript:changeSalesRepPage(<s:property value="%{salesRepReport.currentPage+1}"/>)"><xms:localization
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
                                                                <div id="generate_report_tab" class="tab-pane fade in">
                                                                    <div class="row">
                                                                        <div class="portlet-body b12 b11">
                                                                            <div class="col-lg-12 ">
                                                                                <div class="form-group ">
                                                                                    <form id="generate_report_form">
                                                                                        <table class="s36">
                                                                                            <tbody>
                                                                                            <tr>
                                                                                                <td><xms:localization
                                                                                                        text="Sales Rep"/></td>
                                                                                                <td><s:i18n_select
                                                                                                        name="userId"
                                                                                                        list="generateSalesReps"
                                                                                                        listValue="userCode + ' - ' + displayName"
                                                                                                        listKey="userId"
                                                                                                        cssClass="form-control"
                                                                                                        headerKey=""
                                                                                                        headerValue=""
                                                                                                        i18nitem="no"/></td>
                                                                                                <td><xms:localization
                                                                                                        text="Start"/></td>
                                                                                                <td>
                                                                                                    <div class="form-group input-group mg0">
                                                                                                        <span class="input-group-addon s31"><i
                                                                                                                class="fa fa-calendar"></i></span><input
                                                                                                            name="startDate"
                                                                                                            class="form-control form_datetime"
                                                                                                            type="text"
                                                                                                            data-date-format="dd MM yyyy">
                                                                                                    </div>
                                                                                                </td>
                                                                                                <td><xms:localization
                                                                                                        text="End"/></td>
                                                                                                <td>
                                                                                                    <div class="form-group input-group mg0">
                                                                                                        <span class="input-group-addon s31"><i
                                                                                                                class="fa fa-calendar"></i></span><input
                                                                                                            name="endDate"
                                                                                                            class="form-control form_datetime"
                                                                                                            type="text"
                                                                                                            data-date-format="dd MM yyyy">
                                                                                                    </div>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <button class="btn s37"
                                                                                                            type="button"
                                                                                                            onclick="generateReport()">
                                                                                                        <xms:localization
                                                                                                                text="Go"/>
                                                                                                    </button>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <button class="btn s37 btn-export"
                                                                                                            type="button"
                                                                                                            onclick="viewPrintable()"
                                                                                                            disabled="disabled">
                                                                                                        <xms:localization
                                                                                                                text="Printable Report"/>
                                                                                                    </button>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <button class="btn s37 btn-export"
                                                                                                            type="button"
                                                                                                            onclick="viewPdf()"
                                                                                                            disabled="disabled">
                                                                                                        <xms:localization
                                                                                                                text="PDF Report"/>
                                                                                                    </button>
                                                                                                </td>
                                                                                            </tr>
                                                                                            </tbody>
                                                                                        </table>
                                                                                        <input type="hidden" name="page"
                                                                                               value="1"/> <input
                                                                                            type="hidden"
                                                                                            name="orderField"
                                                                                            value="service_level"/>
                                                                                        <input type="hidden"
                                                                                               name="orderType"
                                                                                               value="0"/>
                                                                                    </form>
                                                                                    <s:hidden id="export-data"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="portlet-body b12 b11">
                                                                                    <div class="portlet-body b22"
                                                                                         style="padding: 0px;">
                                                                                        <ul id="generate_report_tab_nav"
                                                                                            class="nav nav-tabs">
                                                                                            <li class="active"
                                                                                                style="margin-left: 10px;">
                                                                                                <a href="#generate_report_overview_tab"
                                                                                                   data-toggle="tab"><xms:localization
                                                                                                        text="Overview"/></a>
                                                                                            </li>
                                                                                            <li>
                                                                                                <a href="#generate_report_detail_tab"
                                                                                                   data-toggle="tab"><xms:localization
                                                                                                        text="Details"/></a>
                                                                                            </li>
                                                                                        </ul>
                                                                                        <div id="generate_report_tab_content"
                                                                                             class="tab-content responsive">
                                                                                            <div id="generate_report_overview_tab"
                                                                                                 class="tab-pane fade in active">
                                                                                                <div class="row">
                                                                                                    <div class="portlet-body b12 b11">
                                                                                                        <div class="row">
                                                                                                            <div class="col-lg-4">
                                                                                                                <table class="table"
                                                                                                                       style="font-size: 11px;">
                                                                                                                    <tbody>
                                                                                                                    <tr>
                                                                                                                        <td style="border-top: 0px !important"
                                                                                                                            colspan="">
                                                                                                                            <div class="caption b17">
                                                                                                                                <xms:localization
                                                                                                                                        text="Sales Rep Info"/>
                                                                                                                            </div>
                                                                                                                        </td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="td1">
                                                                                                                            <xms:localization
                                                                                                                                    text="Sales Rep"/></td>
                                                                                                                        <td class="td2">
                                                                                                                            &nbsp;</td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="td1">
                                                                                                                            <xms:localization
                                                                                                                                    text="Franchise"/></td>
                                                                                                                        <td class="td2">
                                                                                                                            &nbsp;</td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="td1">
                                                                                                                            <xms:localization
                                                                                                                                    text="Date Range"/></td>
                                                                                                                        <td class="td2">
                                                                                                                            &nbsp;</td>
                                                                                                                    </tr>
                                                                                                                    </tbody>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                            <div class="col-lg-4">
                                                                                                                <table class="table"
                                                                                                                       style="font-size: 11px;">
                                                                                                                    <tbody>
                                                                                                                    <tr>
                                                                                                                        <td style="border-top: 0px !important"
                                                                                                                            colspan="">
                                                                                                                            <div class="caption b17">
                                                                                                                                <xms:localization
                                                                                                                                        text="Activity"/>
                                                                                                                            </div>
                                                                                                                        </td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="td1">
                                                                                                                            <xms:localization
                                                                                                                                    text="Setups"/></td>
                                                                                                                        <td class="td2">
                                                                                                                            &nbsp;</td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="td1">
                                                                                                                            <xms:localization
                                                                                                                                    text="Activations"/></td>
                                                                                                                        <td class="td2">
                                                                                                                            &nbsp;</td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="td1">
                                                                                                                            <xms:localization
                                                                                                                                    text="Printed Invoices"/></td>
                                                                                                                        <td class="td2">
                                                                                                                            &nbsp;</td>
                                                                                                                    </tr>
                                                                                                                    </tbody>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                            <div class="col-lg-4">
                                                                                                                <table class="table"
                                                                                                                       style="font-size: 11px;">
                                                                                                                    <tbody>
                                                                                                                    <tr>
                                                                                                                        <td style="border-top: 0px !important"
                                                                                                                            colspan="">
                                                                                                                            <div class="caption b17">
                                                                                                                                &nbsp;</div>
                                                                                                                        </td>
                                                                                                                    </tr>
                                                                                                                    <tr>
                                                                                                                        <td class="td1">
                                                                                                                            <xms:localization
                                                                                                                                    text="E-mail Invoices"/></td>
                                                                                                                        <td class="td2">
                                                                                                                            &nbsp;</td>
                                                                                                                    </tr>
                                                                                                                    </tbody>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                            <div class="col-lg-12">
                                                                                                                <table class="table table-bordered mg0">
                                                                                                                    <thead>
                                                                                                                    <tr bgcolor="#F0F2F5">
                                                                                                                        <th>
                                                                                                                            <xms:localization
                                                                                                                                    text="Service Level"/></th>
                                                                                                                        <th class="text-right">
                                                                                                                            <xms:localization
                                                                                                                                    text="Shipment Goals"/></th>
                                                                                                                        <th class="text-right">
                                                                                                                            <xms:localization
                                                                                                                                    text="Actual Shipments"/></th>
                                                                                                                        <th class="text-right">
                                                                                                                            <xms:localization
                                                                                                                                    text="% Goal"/></th>
                                                                                                                        <th class="text-right">
                                                                                                                            <xms:localization
                                                                                                                                    text="Actual Margin"/></th>
                                                                                                                        <th class="text-right">
                                                                                                                            <xms:localization
                                                                                                                                    text="% Payout"/></th>
                                                                                                                        <th class="text-right">
                                                                                                                            <xms:localization
                                                                                                                                    text="Payout"/></th>
                                                                                                                    </tr>
                                                                                                                    </thead>
                                                                                                                    <tbody>
                                                                                                                    <tr>
                                                                                                                        <td colspan="7">
                                                                                                                            <xms:localization
                                                                                                                                    text="No data available..."/></td>
                                                                                                                    </tr>
                                                                                                                    </tbody>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div id="generate_report_detail_tab"
                                                                                                 class="tab-pane fade in">
                                                                                                <div class="row">
                                                                                                    <div class="portlet-body b12 b11">
                                                                                                        <div class="form-group row">
                                                                                                            <div class="col-lg-12 ">
                                                                                                                <div class="form-group"
                                                                                                                     style="float: right">
                                                                                                                    <table class="s36">
                                                                                                                        <tbody>
                                                                                                                        <tr>
                                                                                                                            <td>
                                                                                                                                <div class="btn-group"
                                                                                                                                     id="btn-export"
                                                                                                                                     style="display: none;">
                                                                                                                                    <input type="button"
                                                                                                                                           id="export-option"
                                                                                                                                           class="btn s37"
                                                                                                                                           value="<xms:localization text="Option" />"
                                                                                                                                           onclick="exportClick($('#selected-option').val())"/>
                                                                                                                                    <button type="button"
                                                                                                                                            class="btn s37 dropdown-toggle"
                                                                                                                                            data-toggle="dropdown"
                                                                                                                                            aria-expanded="true">
                                                                                                                                        <span class="caret"></span>
                                                                                                                                    </button>
                                                                                                                                    <s:hidden
                                                                                                                                            id="selected-option"/>
                                                                                                                                    <ul class="dropdown-menu dropdown-menu-right"
                                                                                                                                        role="menu">
                                                                                                                                        <li>
                                                                                                                                            <a href="#"
                                                                                                                                               onclick="exportClick('option')"><xms:localization
                                                                                                                                                    text="Option"/></a>
                                                                                                                                        </li>
                                                                                                                                        <li>
                                                                                                                                            <a href="#"
                                                                                                                                               onclick="exportClick('html')"><xms:localization
                                                                                                                                                    text="Print"/></a>
                                                                                                                                        </li>
                                                                                                                                        <li>
                                                                                                                                            <a href="#"
                                                                                                                                               onclick="exportClick('xls')"><xms:localization
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
                                                                                                                <table class="table"
                                                                                                                       style="font-size: 11px; margin-bottom: 0px">
                                                                                                                    <tr>
                                                                                                                        <td class="td1">
                                                                                                                            <xms:localization
                                                                                                                                    text="Please Note"/>:
                                                                                                                        </td>
                                                                                                                        <td class="td2">
                                                                                                                            <xms:localization
                                                                                                                                    text="- Sales Rep Commissions are paid only on margin from paid invoices.<br /> - The details below list only invoices that are paid from . Unpaid invoices are irrelevant and not listed."/></td>
                                                                                                                    </tr>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                            <div class="col-lg-12">
                                                                                                                <table class="table table-bordered mg0">
                                                                                                                    <thead>
                                                                                                                    <tr bgcolor="#F0F2F5">
                                                                                                                        <th>
                                                                                                                            <xms:localization
                                                                                                                                    text="Airbill Number"/></th>
                                                                                                                        <th>
                                                                                                                            <xms:localization
                                                                                                                                    text="Invoice Number"/></th>
                                                                                                                        <th>
                                                                                                                            <xms:localization
                                                                                                                                    text="Customer Name"/></th>
                                                                                                                        <th>
                                                                                                                            <xms:localization
                                                                                                                                    text="Customer Total"/></th>
                                                                                                                        <th>
                                                                                                                            <xms:localization
                                                                                                                                    text="Franchise Cost"/></th>
                                                                                                                        <th>
                                                                                                                            <xms:localization
                                                                                                                                    text="Previously Paid"/></th>
                                                                                                                        <th>
                                                                                                                            <xms:localization
                                                                                                                                    text="Margin On Customer Total"/></th>
                                                                                                                    </tr>
                                                                                                                    </thead>
                                                                                                                    <tbody>
                                                                                                                    <tr>
                                                                                                                        <td colspan="7">
                                                                                                                            <xms:localization
                                                                                                                                    text="No data available..."/></td>
                                                                                                                    </tr>
                                                                                                                    </tbody>
                                                                                                                </table>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="sales_rep_settings_dialog"></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    function doSearchSalesRep() {
        var data = {
            "page": $("#sales_rep_page").val(),
            "pageSize": $("#sales_rep_page_size option:selected").val()
        };
        loadingDialog.dialog("open");
        $.post("search_sales_rep_settings.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#sales_rep_settings_report").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function changeSalesRepPage(page) {
        $("#sales_rep_page").val(page);
        doSearchSalesRep();
    }

    function editSalesRepSetting(salesRepId) {
        var data = {
            "salesRepId": salesRepId
        };
        loadingDialog.dialog("open");
        $.post("load_sales_rep_settings.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Define edit sales rep settings dialog.
                var buttons = {};
                buttons["<xms:localization text="Save" />"] = function () {
                    saveSalesRepSetting();
                }
                buttons["<xms:localization text="Cancel" />"] = function () {
                    $(this).dialog("close");
                }
                var dialog = $("#sales_rep_settings_dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: 'auto',
                    height: 'auto',
                    maxHeight: '800',
                    buttons: buttons,
                    title: '<xms:localization text="Edit Sales Rep"/>',
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                dialog.html(res.content);
                // Show edit sales rep setting dialog.
                dialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function createSalesRepSetting() {
        var userId = $("#existingSalesReps option:selected").val();
        if (userId == "") {
            alertDialog.html('<xms:localization text="Please select Sales Rep." />');
            alertDialog.dialog("open");
            return;
        }
        var data = {
            "userId": userId
        };
        loadingDialog.dialog("open");
        $.post("create_sales_rep_settings.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Define new sales rep settings dialog.
                var buttons = {};
                buttons["<xms:localization text="Save" />"] = function () {
                    saveSalesRepSetting();
                }
                buttons["<xms:localization text="Cancel" />"] = function () {
                    $(this).dialog("close");
                }
                var dialog = $("#sales_rep_settings_dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: 'auto',
                    height: 'auto',
                    maxHeight: '800',
                    buttons: buttons,
                    title: '<xms:localization text="Add New Sales Rep"/>',
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                dialog.html(res.content);
                // Show edit sales rep setting dialog.
                dialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function saveSalesRepSetting() {
        var data = $("#sales_rep_setting_form").serialize();
        loadingDialog.dialog("open");
        $.post("save_sales_rep_settings.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "FIELD_ERROR") {
                $("#sales_rep_setting_form").replaceWith(res.content);
            }
            else if (res.errorCode == "SUCCESS") {
                messageDialog.html('<xms:localization text="Saved successfully." />');
                messageDialog.dialog("open");
                $("#sales_rep_settings_dialog").dialog("close");
                doSearchSalesRep();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function generateReport() {
        var data = $("#generate_report_form").serialize();
        $("#export-data").val(data);
        loadingDialog.dialog("open");
        $.post("sales_rep_generate_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#generate_report_tab_content").html(res.content);
                $(".btn-export").attr("disabled", false);
                $("#btn-export").show();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function getSalesRepServiceStats() {
        var data = $("#export-data").val();
        data += "&svOrderField=" + $("#svOrderField").val();
        data += "&svOrderType=" + $("#svOrderType").val();
        loadingDialog.dialog("open");
        $.post("sales_rep_generate_service_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#sales_rep_service_stats").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function getSalesRepAirbillStats() {
        var data = $("#export-data").val();
        data += "&awbPage=" + $("#awbPage").val();
        data += "&awbPageSize=" + $("#awbPageSize option:selected").val();
        data += "&awbOrderField=" + $("#awbOrderField").val();
        data += "&awbOrderType=" + $("#awbOrderType").val();
        loadingDialog.dialog("open");
        $.post("sales_rep_generate_airbill_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#sales_rep_airbill_stats").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function changeAwbPageSize(page) {
        $("#awbPage").val(page);
        getSalesRepAirbillStats();
    }
    function viewPrintable() {
        loadingDialog.dialog("open");
        var data = $("#export-data").val();
        $.post("manage_sales_reps_and_commissions_view_printable.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                win.document.write(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        });
    }
    function viewPdf() {
        loadingDialog.dialog("open");
        var data = $("#export-data").val();
        data += "&svOrderField=" + $("#svOrderField").val();
        data += "&svOrderType=" + $("#svOrderType").val();
        data += "&awbOrderField=" + $("#awbOrderField").val();
        data += "&awbOrderType=" + $("#awbOrderType").val();
        $.fileDownload("manage_sales_reps_and_commissions_view_pdf.ix", {
            failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
            httpMethod: "POST",
            data: data,
            successCallback: function (url) {
                loadingDialog.dialog("close");
            },
            failCallback: function (url) {
                loadingDialog.dialog("close");
            },
        });
    }
    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("manage_sales_reps_and_commissions_print_details.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("manage_sales_reps_and_commissions_export_xls_details.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    }
                });
        }

    }
</script>