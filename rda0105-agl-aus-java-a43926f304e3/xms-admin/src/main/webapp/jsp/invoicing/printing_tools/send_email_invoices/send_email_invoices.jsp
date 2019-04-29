<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Send Email Invoices"/>
        </li>
    </ol>
    <div class="clearfix"></div>
</div>
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
                                    <xms:localization text="Send Email Invoices"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12" style="height: 500px;">
                                                <p>
                                                    <xms:localization
                                                            text="<b>Note:</b> <br/> - Before sending e-mail invoices, invoices must be frozen.<br/> If you do not see a date here that you would expect, because<br/> a) the invoices have not been frozen or<br/> b) the e-mail invoices have already been sent.<br/> <br/> To send all invoices for a date, select a date and Franchise the below drop-down."/>
                                                    <br/>
                                                <table class="s36">
                                                    <tr>
                                                        <td><b><xms:localization text="Franchise Code"/></b></td>
                                                        <td><s:select list="franchises" listKey="code" listValue="code"
                                                                      headerKey="" headerValue="All"
                                                                      cssClass="form-control"
                                                                      onchange="getInvDates($(this).val())"
                                                                      id="franchise-codes"/></td>
                                                        <td><b><xms:localization text="Date"/></b></td>
                                                        <td id="inv-dates-list"><s:select list="listInvoiceDates"
                                                                                          cssClass="form-control"
                                                                                          id="invoice-dates"/></td>
                                                        <td>
                                                            <button class="btn s37" type="button"
                                                                    onclick="doSendEmailByInvDate()">
                                                                <xms:localization
                                                                        text="Send E-mail Invoices for this Date"/>
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </table>
                                                <br/>
                                                <table class="s36">
                                                    <tr>
                                                        <td><b><xms:localization text="Invoice Code"/></b></td>
                                                        <td><input class="form-control" type="text" id="inv-code"></td>
                                                        <td>
                                                            <button class="btn s37" type="button"
                                                                    onclick="doSendEmailByInvCode()">
                                                                <xms:localization text="Send E-mail for this Invoice"/>
                                                            </button>
                                                        </td>
                                                    </tr>
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
<script type="text/javascript">
    function getInvDates(franchiseCode) {
        doPostDataByParameters("send_email_invoices_get_inv_dates.ix?reqType=json", {
            "franchiseCode": franchiseCode
        }, "", "inv-dates-list", true, true);
    }
    function doSendEmailByInvCode() {
        var invCode = $("#inv-code").val();
        var franchiseCode = $("#franchise-codes").val();
        doPostDataByParameters("send_email_invoices_by_inv_code.ix?reqType=json", {
            "invoiceCode": invCode,
            "franchiseCode": franchiseCode
        }, "<xms:localization text="Send E-mail successful!" />", "inv-dates-list", true, true);
    }
    function doSendEmailByInvDate() {
        var invDate = $("#invoice-dates").val();
        var franchiseCode = $("#franchise-codes").val();
        doPostDataByParameters("send_email_invoices_by_inv_date.ix?reqType=json", {
            "invoiceDate": invDate,
            "franchiseCode": franchiseCode
        }, "<xms:localization text="First email sent, remainder will go in background!" />", "inv-dates-list", true, true);
    }
</script>
