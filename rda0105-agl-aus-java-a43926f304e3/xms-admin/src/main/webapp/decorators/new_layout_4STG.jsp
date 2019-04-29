<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>AGL</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="max-age=0"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <link rel="icon" href="images/favicon.ico" />
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="<%=WebUtils.getContextPathURL(request)%>styles/xms/css_global.css">
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery_1.10.2.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_migrate.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_ui.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_dropdown.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_html5.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_respond.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_menu.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_responsive_tabs.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_datetimepicker.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_maxlength.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/bootstrap_xms.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dt-custom.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/ckeditor/ckeditor.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.number.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileupload.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_easytree.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.smartmenus.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.smartmenus.bootstrap.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/common/common.js"></script>
</head>
<body>
<div id="loading-dialog" title="<xms:localization text="Loading..." />">
    <div class="cs22">
        <span><img src="<%=WebUtils.getContextPathURL(request)%>images/LOGOiN.png" width="300px;" style="opacity: 0.9"/></span>
        <span></span> <span></span> <span></span> <span class="l-7"></span> <span class="l-0"></span> <span
            class="l-1"></span> <span class="l-2"></span> <span class="l-3"></span> <span class="l-4"></span> <span
            class="l-5"></span> <span class="l-6"></span> <span><strong><xms:localization
            text="Please wait ..."/></strong></span>
    </div>
</div>
<div id="alert-dialog" title="<xms:localization text="Error" />"></div>
<div id="message-dialog" title="<xms:localization text="Message" />"></div>
<script type="text/javascript">
    // init common dialog
    var loadingDialog = $("#loading-dialog").dialog({
        modal: true,
        autoOpen: false,
        width: "500px",
        height: "auto",
        dialogClass: "no-close",
        closeOnEscape: false,
        show: {
            effect: "fade",
            duration: 500
        }
    });
    var alertDialog = $("#alert-dialog").dialog({
        autoOpen: false,
        modal: true,
        close: function (e) {
            $("#message-dialog").html("");
        }
    });
    var messageDialog = $("#message-dialog").dialog({
        autoOpen: false,
        show: {
            effect: "fade",
            duration: 300
        },
        modal: true,
        close: function (e) {
            $("#message-dialog").html("");
        }
    });
</script>
<div>

    <!--BEGIN BACK TO TOP-->
    <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
    <!--END BACK TO TOP-->
    <!--BEGIN TOPBAR-->
    <div id="header-topbar-option-demo" class="page-header-topbar">
        <nav id="topbar" role="navigation" style="margin-bottom: 0;" data-step="3"
             class="navbar navbar-default navbar-static-top">
            <div class="topbar-main" style="height: 9px !important; background: #EBE7E7; border: 0px !important"></div>
            <div class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span>
                    </button>
                    <img src="<%=WebUtils.getContextPathURL(request)%>images/agl_logo3.png"
                         style="width: 284px; margin-top: -9px; margin-left: -50px;"/>
                </div>
                <div class="navbar-collapse collapse"
                     style="box-shadow: 0 1px 3px rgba(0, 0, 0, 0.25), 0 -1px 0 rgba(0, 0, 0, 0.1) inset;">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="#" class="st"><i class="fa fa-key fa-fw"></i><span><xms:localization
                                    text="ACCOUNT"/></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#"><xms:localization text="Contacts"/><span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>manage_contact.ix"><span
                                                    class="sws"><xms:localization text="Search Contacts"/></span><span
                                                    class="sws mgl"><i
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>manage_contact.ix');return false;"
                                                    class="fa fa-external-link fa-fw sws pst"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><xms:localization text="Customers"/><span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>manage_customers.ix"><span
                                                    class="sws"><xms:localization text="Manage Customers"/></span><span
                                                    class="sws mgl"><i
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>manage_customers.ix');return false;"
                                                    class="fa fa-external-link fa-fw sws pst"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>search_customers.ix"><span
                                                    class="sws"><xms:localization text="Search Customers"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>search_customers.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>customer_list.ix"><span
                                                    class="sws"><xms:localization text="Customer List"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>customer_list.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><xms:localization text="Franchise"/><span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>manage_franchise.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Manage Franchise Accounts"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>manage_franchise.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>franchise_list.ix"><span
                                                    class="sws"><xms:localization text="Franchise List"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>franchise_list.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><xms:localization text="Users"/><span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>manage_users.ix"><span
                                                    class="sws"><xms:localization text="Manage Users"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>manage_users.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                      <%--   <li>
                            <a href="#" class="st"><i class="fa fa-building-o fa-fw"></i><span> <xms:localization
                                    text="INVOICING"/></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#"><xms:localization text="Invoice Editing Tools"/><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>import_total_report.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Import Totals Report"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>import_total_report.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>airbill_import_error_logs.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Airbill Import Error Logs"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>airbill_import_error_logs.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>repair_airbill_errors.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Repair Airbill Errors"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>repair_airbill_errors.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>move_invoices_dates.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Move Invoices Dates"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>move_invoices_dates.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><xms:localization text="Price Validation Tools"/><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>cost_deviations.ix"><span
                                                    class="sws"><xms:localization text="Cost Deviations"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>cost_deviations.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>airbill_margins.ix"><span
                                                    class="sws"><xms:localization text="Airbill Margins"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>airbill_margins.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="<%=WebUtils.getContextPathURL(request)%>search_airbill.ix"><xms:localization
                                            text="Search Airbills"/><span class="sws mgl"><i
                                            class="fa fa-external-link fa-fw sws pst"
                                            onclick="window.open('<%=WebUtils.getContextPathURL(request)%>search_airbill.ix');return false;"></i></span></a>
                                </li>
                                <li>
                                    <a href="#"><xms:localization text="Manage Invoices"/><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>view_edit_invoice.ix"><span
                                                    class="sws"><xms:localization text="View/Edit Invoice"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>view_edit_invoice.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>credit_notes.ix"><span
                                                    class="sws"><xms:localization text="Credit Notes"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>credit_notes.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>statements.ix"><span
                                                    class="sws"><xms:localization text="Statements"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>statements.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><xms:localization text="Printing Tools"/><span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>freeze_inv.ix"><span
                                                    class="sws"><xms:localization text="Freeze Invoices"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>freeze_inv.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>send_email_invoices.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Send E-mail Invoices"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>send_email_invoices.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>freeze_credit_notes.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Freeze Credit Notes"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>freeze_credit_notes.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>send_credit_notes.ix"><span
                                                    class="sws"><xms:localization text="Send Credit Notes"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>send_credit_notes.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>csv_invoices.ix"><span
                                                    class="sws"><xms:localization text="CSV Invoices"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>csv_invoices.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>unfreeze_inv.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Unfreeze Customer Invoices"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>unfreeze_inv.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>unfreeze_credit_notes.ix"><span
                                                    class="sws"><xms:localization
                                                    text="UnFreeze Credit Notes"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>unfreeze_credit_notes.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="<%=WebUtils.getContextPathURL(request)%>manage_adjustment.ix"><span
                                            class="sws"><xms:localization text="Manage Adjustments"/></span><span
                                            class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                               onclick="window.open('<%=WebUtils.getContextPathURL(request)%>manage_adjustment.ix');return false;"></i></span></a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" class="st"><i class=" fa fa-cubes fa-fw"></i><span> <xms:localization
                                    text="RECEIVABLES"/></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#"><span><xms:localization text="Customers"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>receive_payment.ix"><span
                                                    class="sws"><xms:localization text="Receive Payments"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>receive_payment.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>bank_list.ix"><span
                                                    class="sws"><xms:localization text="Banks"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>bank_list.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>customer_aging.ix"><span
                                                    class="sws"><xms:localization text="Customer Aging"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>customer_aging.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>search_payments.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Search Payments / Deposit Slip"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>search_payments.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>invoice_overpayment.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Invoice Overpayment"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>invoice_overpayment.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>reminder_letter.ix"><span
                                                    class="sws"><xms:localization text="Reminder Letters"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>reminder_letter.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" class="st"><i class="fa fa-cube fa-fw"></i><span> <xms:localization
                                    text="PAYABLES"/></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#"><span><xms:localization text="Franchise"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>fpb_ms.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Franchise Payables Details"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>fpb_ms.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>fpb_freeze_inv_ms.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Freeze Franchise Payables"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>fpb_freeze_inv_ms.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>fpb_send_inv_ms.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Send Franchise Payables"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>fpb_send_inv_ms.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>fpb_inv_ms.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Franchise Payables Invoice"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>fpb_inv_ms.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><xms:localization text="Sales Rep"/><span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>manage_sales_reps_and_commissions.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Manage Sales Reps & Commissions"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>manage_sales_reps_and_commissions.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li> --%>
                        <li>
                            <a href="#" class="st"><i class="fa fa-file-text-o fa-fw"></i><span> <xms:localization
                                    text="REPORTS"/></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#"><span><xms:localization text="Customer"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>customer_status.ix"><span
                                                    class="sws"><xms:localization text="Customer Status Report"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>customer_status.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>customer_summary.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Customer Summary Report"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>customer_summary.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>customer_activation.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Activations Report"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>customer_activation.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>customer_inactivity.ix"><span
                                                    class="sws"><xms:localization text="Inactivity Report"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>customer_inactivity.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>customer_13weeks_activity.ix"><span
                                                    class="sws"><xms:localization
                                                    text="13 Week Activity Report"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>customer_13weeks_activity.ix');return false;"></i></span></a>
                                        </li>
                                       <%--  <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>customer_invoice_detail.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Customer Invoice Detail"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>customer_invoice_detail.ix');return false;"></i></span></a>
                                        </li> --%>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><span><xms:localization text="Rankings"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>overall_fran_ranking.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Overall Franchise Rankings"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>overall_fran_ranking.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>sales_rep_ranking.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Sales Rep Rankings"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>sales_rep_ranking.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><span><xms:localization text="Web Freight"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>webship_cust_history.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Web Freight Customer History"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>webship_cust_history.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>webship_quote_history.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Web Freight Quote History"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>webship_quote_history.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>invoice_pending_airbill.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Invoice Pending Airbills"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>invoice_pending_airbill.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>webship_customer_detail.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Web Freight Customer Details"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>webship_customer_detail.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><span><xms:localization text="Warranty"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>webship_label.ix"><span
                                                    class="sws"><xms:localization text="Web Freight History Report"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>webship_label.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>invoiced_airbill.ix"><span
                                                    class="sws"><xms:localization text="Invoice History Report"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>invoiced_airbill.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" class="st"><i class="fa fa fa-user fa-fw"></i><span> <xms:localization
                                    text="ADMIN"/></span> </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#"><span><xms:localization text="Rates and Fees"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>surcharge_list.ix"><span
                                                    class="sws"><xms:localization text="Surcharge List"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>surcharge_list.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>import_rate_sheet.ix"><xms:localization
                                                    text="Import Rates & Costs"/><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>import_rate_sheet.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>rate_sheets.ix"><span
                                                    class="sws"><xms:localization text="Rate Sheets"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>rate_sheets.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>manage_cover_sheets.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Manage Cover Sheets"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>manage_cover_sheets.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>set_cost_basis.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Set Cost Basis Tables"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>set_cost_basis.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>set_list_rates.ix"><span
                                                    class="sws"><xms:localization text="Set List Rates"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>set_list_rates.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><span><xms:localization text="Logs"/></span><span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>system_log.ix"><span
                                                    class="sws"><xms:localization text="System Logs"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>system_log.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>system_stats.ix"><span
                                                    class="sws"><xms:localization text="Stats"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>system_stats.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <%-- <li>
                                    <a href="#"><span><xms:localization text="Import"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#" class="disabled-link"><span class="sws"><xms:localization
                                                    text="Import Carrier Airbill Adjustment Responses"/></span><span
                                                    class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>import_billing_file.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Import Billing Files"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>import_billing_file.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>reconcile_airbill.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Reconcile Airbills"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>reconcile_airbill.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="#" class="disabled-link"><span class="sws"><xms:localization
                                                    text="Import Web Freight"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="#" class="disabled-link"><span class="sws"><xms:localization
                                                    text="Import Non Centralized Billing Files"/></span><span
                                                    class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"></i></span></a>
                                        </li>
                                    </ul>
                                </li> --%>
                                <li>
                                    <a href="#"><span><xms:localization text="System Settings"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>admin_menu.ix"><span
                                                    class="sws"><xms:localization text="Menu Editor"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>admin_menu.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>admin_email_setting.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Admin Email Settings"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>admin_email_setting.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>admin_system_setting.ix"><span
                                                    class="sws"><xms:localization text="System Settings"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>admin_system_setting.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><span><xms:localization text="Carriers"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>carrier_list.ix"><span
                                                    class="sws"><xms:localization text="Carrier List"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>carrier_list.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>country_list.ix"><span
                                                    class="sws"><xms:localization text="Country List"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>country_list.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><span><xms:localization text="Administration"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>franchise_setting.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Franchise Settings"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>franchise_setting.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>admin_permission.ix"><span
                                                    class="sws"><xms:localization text="Permissions"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>admin_permission.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>admin_supply.ix"><span
                                                    class="sws"><xms:localization text="Supplies List"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>admin_supply.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>manager_customer_profiles.ix"><span
                                                    class="sws"><xms:localization
                                                    text="Manage Customer Profiles"/></span><span class="sws mgl"><i
                                                    class="fa fa-external-link fa-fw sws pst"
                                                    onclick="window.open('<%=WebUtils.getContextPathURL(request)%>manager_customer_profiles.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><span><xms:localization text="Generic Setup"/></span><span
                                            class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>email_template.ix"><span
                                                    class="sws"><xms:localization text="Email Template"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>email_template.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>product_carrier_list.ix"><span
                                                    class="sws"><xms:localization text="Product"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>product_carrier_list.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>industry_list.ix"><span
                                                    class="sws"><xms:localization text="Industry"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>industry_list.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>customer_group_list.ix"><span
                                                    class="sws"><xms:localization text="Customer Group"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>customer_group_list.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>webship_group_list.ix"><span
                                                    class="sws"><xms:localization text="Web Freight Group"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>webship_group_list.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>area_list.ix"><span
                                                    class="sws"><xms:localization text="Area"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>area_list.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>language_value.ix"><span
                                                    class="sws"><xms:localization text="Localization"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>language_value.ix');return false;"></i></span></a>
                                        </li>
                                        <li>
                                            <a href="<%=WebUtils.getContextPathURL(request)%>territory_list.ix"><span
                                                    class="sws"><xms:localization text="Territory List"/></span><span
                                                    class="sws mgl"><i class="fa fa-external-link fa-fw sws pst"
                                                                       onclick="window.open('<%=WebUtils.getContextPathURL(request)%>territory_list.ix');return false;"></i></span></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <script type="text/javascript">
        $('.disabled-link').click(function () {
            return false;
        });
    </script>
    <!--END TOPBAR-->
    <div id="wrapper">
        <div id="page-wrapper">
            <!-- Quick search form -->
            <ol class="breadcrumb page-breadcrumb pull-right b36">
                <table class="s36">
                    <tr>
                        <td><xms:localization text="Search"/></td>
                        <td><input class="form-control ss36" name="txtQuickSearch" id="txtQuickSearch"/></td>
                        <td><select class="form-control ss38" id="searchType" name="searchType">
                            <option value="0"><xms:localization text="Customers"/></option>
                            <option value="1"><xms:localization text="Contacts"/></option>
                            <option value="2"><xms:localization text="Airbills"/></option>
                            <%-- <option value="3"><xms:localization text="Invoices"/></option> --%>
                            <option value="4"><xms:localization text="Airbill Labels"/></option>
                            <option value="5"><xms:localization text="Web Freight Details"/></option>
                            <option value="6"><xms:localization text="Reference No."/></option>
                            <option value="7"><xms:localization text="Booking No."/></option>
                        </select></td>
                        <td>
                            <button class="btn s37 ss37" onclick="doQuickSearch()" id="md-20-link">
                                <i class="fa fa-search"></i>
                            </button>
                        </td>
                    </tr>
                </table>
            </ol>
            <div id="quickSearchDialog" title="Quick Search"></div>
            <div id="quickSearchWebshipDetailDialog" title="Shipment Detail"></div>
            <script type="text/javascript">
                var quickSearchDialog = $("#quickSearchDialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: 1100,
                    height: 550,
                    minHeight: 500,
                    maxHeight: 800,
                    show: {
                        effect: "fade",
                        duration: 500
                    },
                    open: function () {
                        $("body").css("overflow", "hidden");
                    },
                });
                var buttons = {};
                buttons["Export to PDF"] = function () {
                    exportDetail();
                };
                buttons["Close"] = function () {
                    $(this).dialog("close");
                }
                var quickSearchWebshipDetailDialog = $("#quickSearchWebshipDetailDialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: "80%",
                    buttons: buttons,
                    show: {
                        effect: "fade",
                        duration: 500
                    },
                    open: function () {
                        $("body").css("overflow", "hidden");
                    }
                });
                function doQuickSearch() {
                    var quickSearchValue = $("#txtQuickSearch").val();
                    var quickSearchType = $("#searchType").val();
                    var data = {
                        "searchValue": quickSearchValue,
                        "searchType": quickSearchType
                    };
                    loadingDialog.dialog("open");
                    $.post("admin_quick_search.ix?reqType=json", data, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            if (quickSearchType == 5) {
                                quickSearchWebshipDetailDialog.html(res.content);
                                quickSearchWebshipDetailDialog.dialog("open");
                            } else {
                                quickSearchDialog.html(res.content);
                                quickSearchDialog.dialog("open");
                            }
                        } else {
                            alertDialog.html(res.errorMsg);
                            alertDialog.dialog("open");
                        }
                    }).fail(function () {
                        alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                        alertDialog.dialog("open");
                    });
                }
                function get_data(customerCode, searchType, invoiceDate, invoiceCode, invoiceId) {
                    var action = "manage_customers.ix?customerCode=";
                    if (customerCode.substring(customerCode.length - 3) == "001") {
                        action = "manage_franchise.ix?franchiseCode=";
                    }
                    var action_invoice = "view_edit_invoice.ix?invoiceDate=" + invoiceDate + "&invoiceCode=" + invoiceCode + "&invoiceId=" + invoiceId;
                    switch (searchType) {
                        case 0:
                            // Quick search customers.
                            loadingDialog.dialog("open");
                            window.location.href = action + customerCode;
                            break;
                        case 1:
                            // Quick search contacts.

                            break;
                        case 2:
                            // Quick search airbills.
                            loadingDialog.dialog("open");
                            window.location.href = action_invoice;
                            break;
                        case 3:
                            // Quick search invoices.
                            loadingDialog.dialog("open");
                            window.location.href = action_invoice;
                            break;
                        case 4:
                            // Quick search airbill labels.
                            loadingDialog.dialog("open");
                            window.location.href = action + customerCode;
                            break;
                        case 6:
                            // Quick search reference #.
                            loadingDialog.dialog("open");
                            window.location.href = action + customerCode;
                            break;
                        case 7:
                            // Quick search booking No.
                            loadingDialog.dialog("open");
                            window.location.href = action + customerCode;
                            break;
                    }
                }
            </script>
            <decorator:body/>
        </div>
        <div id="footer">
            <div class="copyright">
                <a href=""><xms:localization text="2017 © AGL Technology. All Rights reserved. "/></a>
            </div>
        </div>
        <!--END FOOTER-->
    </div>
    <!--END PAGE WRAPPER-->
</div>
</body>
</html>