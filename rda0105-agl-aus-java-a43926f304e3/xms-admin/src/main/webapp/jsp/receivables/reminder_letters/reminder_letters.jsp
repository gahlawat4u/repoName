<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>


<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li>
            <xms:localization text="Receivables"/>
            &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li>
            <xms:localization text="Customers"/>
            &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Reminder Letters"/>
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
                                    <xms:localization text="Reminder Letters"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive" style="">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="row">
                                                    <div class="portlet-body b12 b11">
                                                        <div class="portlet-body b22" style="padding: 0px;">
                                                            <ul id="reminder_letter_nav" class="nav nav-tabs">
                                                                <li class="active" style="margin-left: 10px;">
                                                                    <a href="#general_settings_tab"
                                                                       data-toggle="tab"><xms:localization
                                                                            text="General Settings"/></a>
                                                                </li>
                                                                <li>
                                                                    <a href="#edit_email_tab"
                                                                       data-toggle="tab"><xms:localization
                                                                            text="Edit E-mail"/></a>
                                                                </li>
                                                                <li>
                                                                    <a href="#edit_print_letter_tab"
                                                                       data-toggle="tab"><xms:localization
                                                                            text="Edit Print Letter"/></a>
                                                                </li>
                                                                <li>
                                                                    <a href="#preview_generate_tab"
                                                                       data-toggle="tab"><xms:localization
                                                                            text="Preview / Generate"/></a>
                                                                </li>
                                                            </ul>
                                                            <div id="reminder_letter_tabs_content"
                                                                 class="tab-content responsive">
                                                                <div id="general_settings_tab"
                                                                     class="tab-pane fade in active">
                                                                    <div class="row">
                                                                        <div class="portlet-body b12 b11">
                                                                            <div class="col-lg-12"
                                                                                 style="border: 1px solid #e5e5e5; padding-top: 20px; height: 300px !important; overflow: auto">
                                                                                <div class="portlet-body b12 b11">
                                                                                    <form id="reminder_letter_settings_form">
                                                                                        <s:hidden
                                                                                                name="reminderStart.settingId"/>
                                                                                        <s:hidden
                                                                                                name="reminderStart.settingName"/>
                                                                                        <s:hidden
                                                                                                name="reminderEnd.settingId"/>
                                                                                        <s:hidden
                                                                                                name="reminderEnd.settingName"/>
                                                                                        <table class="s36">
                                                                                            <tbody>
                                                                                            <tr>
                                                                                                <td class="caption b17"
                                                                                                    colspan="2">
                                                                                                    <xms:localization
                                                                                                            text="Generate Reminder Letter Settings"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td height="5"
                                                                                                    colspan="3"></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><xms:localization
                                                                                                        text="Days from Invoice Date"/>:
                                                                                                </td>
                                                                                                <td><xms:localization
                                                                                                        text="Start"/>:
                                                                                                </td>
                                                                                                <td width="60">
                                                                                                    <s:textfield
                                                                                                            name="reminderStart.settingValue"
                                                                                                            cssClass="form-control"/></td>
                                                                                                <td><xms:localization
                                                                                                        text="End"/>:
                                                                                                </td>
                                                                                                <td width="60">
                                                                                                    <s:textfield
                                                                                                            name="reminderEnd.settingValue"
                                                                                                            cssClass="form-control"/></td>
                                                                                            </tr>
                                                                                            </tbody>
                                                                                        </table>
                                                                                    </form>
                                                                                </div>
                                                                            </div>
                                                                            <div class=" pal pdt10 ">
                                                                                <div class="row">
                                                                                    <div class="col-lg-12 text-left"
                                                                                         style="padding-top: 10px; padding-left: 5px;">
                                                                                        <button id="save_reminder_letter_settings"
                                                                                                class="btn s37"
                                                                                                type="button"
                                                                                                onclick="saveSettings()">
                                                                                            <xms:localization
                                                                                                    text="Save"/>
                                                                                        </button>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div id="edit_email_tab" class="tab-pane fade in">
                                                                    <div class="row">
                                                                        <div class="portlet-body b12 b11">
                                                                            <div class="col-lg-10 row">
                                                                                <div class="form-group row">
                                                                                    <div class="col-lg-8">
                                                                                        <form id="reminder_letter_email_letter_form">
                                                                                            <s:hidden
                                                                                                    name="emailLetter.templateId"/>
                                                                                            <s:hidden
                                                                                                    name="emailLetter.templateName"/>
                                                                                            <table class="table"
                                                                                                   style="font-size: 11px; margin-bottom: 0px">
                                                                                                <tr>
                                                                                                    <td class="td1">
                                                                                                        <xms:localization
                                                                                                                text="E-mail Subject:"/></td>
                                                                                                    <td class="td2">
                                                                                                        <s:textfield
                                                                                                                name="emailLetter.subject"
                                                                                                                cssClass="form-control alloptions"
                                                                                                                maxlength="25"/></td>
                                                                                                </tr>
                                                                                                <tr>
                                                                                                    <td class="td1">
                                                                                                        <xms:localization
                                                                                                                text="E-mail Body:"/></td>
                                                                                                    <td class="td2">
                                                                                                        <s:textarea
                                                                                                                name="emailLetter.templateContent"
                                                                                                                cssClass="form-control"
                                                                                                                cssStyle="height: 450px;"></s:textarea></td>
                                                                                                </tr>
                                                                                            </table>
                                                                                            <table class="s36 b24">
                                                                                                <tr>
                                                                                                    <td><s:checkbox
                                                                                                            name="emailLetter.invoiceAttachment"/></td>
                                                                                                    <td>
                                                                                                        <xms:localization
                                                                                                                text="Attach Invoice"/></td>
                                                                                                    <td><s:checkbox
                                                                                                            name="emailLetter.statementAttachment"/></td>
                                                                                                    <td>
                                                                                                        <xms:localization
                                                                                                                text="Attach Statement of Accounts"/></td>
                                                                                                </tr>
                                                                                            </table>
                                                                                        </form>
                                                                                    </div>
                                                                                    <div class="col-lg-4">
                                                                                        <table class="s36"
                                                                                               style="font-size: 11px;"
                                                                                               width="100%">
                                                                                            <tr>
                                                                                                <td height="35"></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><p>
                                                                                                    <s:property
                                                                                                            value="emailLetter.variable"
                                                                                                            escape="false"/>
                                                                                                </p></td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-actions pal pdt10">
                                                                        <div class="row">
                                                                            <div class="col-lg-12 text-right">
                                                                                <button id="save_reminder_letter_email_letter"
                                                                                        class="btn s37" type="button"
                                                                                        onclick="saveEmailLetter()">
                                                                                    <xms:localization text="Save"/>
                                                                                </button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div id="edit_print_letter_tab"
                                                                     class="tab-pane fade in">
                                                                    <div class="row">
                                                                        <div class="portlet-body b12 b11">
                                                                            <div class="col-lg-10 row">
                                                                                <div class="form-group row">
                                                                                    <div class="col-lg-8">
                                                                                        <form id="reminder_letter_print_letter_form">
                                                                                            <s:hidden
                                                                                                    name="printLetter.templateId"/>
                                                                                            <s:hidden
                                                                                                    name="printLetter.templateName"/>
                                                                                            <table class="table"
                                                                                                   style="font-size: 11px; margin-bottom: 0px">
                                                                                                <tr>
                                                                                                    <td class="td1">
                                                                                                        <xms:localization
                                                                                                                text="E-mail Body:"/></td>
                                                                                                    <td class="td2">
                                                                                                        <s:textarea
                                                                                                                name="printLetter.templateContent"
                                                                                                                cssClass="form-control"
                                                                                                                cssStyle="height: 450px;"></s:textarea></td>
                                                                                                </tr>
                                                                                            </table>
                                                                                            <table class="s36 b24">
                                                                                                <tr>
                                                                                                    <td><s:checkbox
                                                                                                            name="printLetter.statementAttachment"/></td>
                                                                                                    <td>
                                                                                                        <xms:localization
                                                                                                                text="Attach Statement of Accounts"/></td>
                                                                                                </tr>
                                                                                            </table>
                                                                                        </form>
                                                                                    </div>
                                                                                    <div class="col-lg-4">
                                                                                        <table class="s36"
                                                                                               style="font-size: 11px;"
                                                                                               width="100%">
                                                                                            <tr>
                                                                                                <td><p>
                                                                                                    <s:property
                                                                                                            value="printLetter.variable"
                                                                                                            escape="false"/>
                                                                                                </p></td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-actions pal pdt10">
                                                                        <div class="row">
                                                                            <div class="col-lg-12 text-right">
                                                                                <button id="save_reminder_letter_print_letter"
                                                                                        class="btn s37" type="button"
                                                                                        onclick="savePrintLetter()">
                                                                                    <xms:localization text="Save"/>
                                                                                </button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div id="preview_generate_tab" class="tab-pane fade in">
                                                                    <div class="row">
                                                                        <div class="portlet-body b12 b11">
                                                                            <div class="col-lg-10 row">
                                                                                <div class="portlet-body b12 b11 form-group">
                                                                                    <table class="s36 b24 ">
                                                                                        <tbody>
                                                                                        <tr>
                                                                                            <td class="caption b17">
                                                                                                <xms:localization
                                                                                                        text="Preview / Generate Reminder Letters"/></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td>
                                                                                                <button class="btn s37"
                                                                                                        type="button"
                                                                                                        onclick="generate()">
                                                                                                    <xms:localization
                                                                                                            text="Click Here to Find E-mail and Print Letters"/>
                                                                                                </button>
                                                                                            </td>
                                                                                        </tr>
                                                                                        </tbody>
                                                                                    </table>
                                                                                    <hr>
                                                                                    <div id="generate_result">
                                                                                        <table class="s36 b24">
                                                                                            <tbody>
                                                                                            <tr>
                                                                                                <td class="caption b17"
                                                                                                    colspan="3">
                                                                                                    <xms:localization
                                                                                                            text="E-mail Letters:"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="3">
                                                                                                    <s:i18n_select
                                                                                                            name="emailInvoice"
                                                                                                            list="emailInvoices"
                                                                                                            listValue="invoiceCode"
                                                                                                            listKey="invoiceId"
                                                                                                            headerKey=""
                                                                                                            headerValue="Select"
                                                                                                            cssClass="form-control"
                                                                                                            i18nitem="no"
                                                                                                            cssStyle="width: 200px"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="3">
                                                                                                    <button id="btnSendSelected"
                                                                                                            class="btn s37"
                                                                                                            type="button"
                                                                                                            disabled="disabled">
                                                                                                        <xms:localization
                                                                                                                text="Send E-mail Letter for Selected Customer"/>
                                                                                                    </button>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="3">
                                                                                                    <p>
                                                                                                        <i>Eg:
                                                                                                            first@email.com;
                                                                                                            second@email.com</i>
                                                                                                    </p>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><xms:localization
                                                                                                        text="Email Address:"/></td>
                                                                                                <td><s:textfield
                                                                                                        id="testEmail"
                                                                                                        name="testEmail"
                                                                                                        cssClass="form-control"/></td>
                                                                                                <td>
                                                                                                    <button id="btnSendTest"
                                                                                                            class="btn s37"
                                                                                                            type="button"
                                                                                                            disabled="disabled">
                                                                                                        <xms:localization
                                                                                                                text="Send Test Preview Email"/>
                                                                                                    </button>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td colspan="3"><p>
                                                                                                    <i><xms:localization
                                                                                                            text="Note: E-mail reminder letters will be marked as 'sent' once the button below is clicked"/></i>
                                                                                                </p>
                                                                                                    <button id="btnSendAll"
                                                                                                            class="btn s37"
                                                                                                            type="button"
                                                                                                            disabled="disabled">
                                                                                                        <xms:localization
                                                                                                                text="Send E-mail letters for All Customers in List"/>
                                                                                                    </button>
                                                                                                </td>
                                                                                            </tr>
                                                                                            </tbody>
                                                                                        </table>
                                                                                        <hr>
                                                                                        <table class="s36 b24">
                                                                                            <tbody>
                                                                                            <tr>
                                                                                                <td class="caption b17"
                                                                                                    colspan="3">
                                                                                                    <xms:localization
                                                                                                            text="Print Letters:"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><s:i18n_select
                                                                                                        name="printInvoice"
                                                                                                        list="printInvoices"
                                                                                                        listValue="invoiceCode"
                                                                                                        listKey="invoiceId"
                                                                                                        headerKey=""
                                                                                                        headerValue="Select"
                                                                                                        cssClass="form-control"
                                                                                                        i18nitem="no"
                                                                                                        cssStyle="width: 200px"/></td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td>
                                                                                                    <button id="btnPrintSelected"
                                                                                                            class="btn s37"
                                                                                                            type="button"
                                                                                                            disabled="disabled">
                                                                                                        <xms:localization
                                                                                                                text="Print Letter for Selected Customer"/>
                                                                                                    </button>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr>
                                                                                                <td><p>
                                                                                                    <i><xms:localization
                                                                                                            text="Note: E-mail reminder letters will be marked as 'sent' once the button below is clicked"/> </i>
                                                                                                </p>
                                                                                                    <button id="btnPrintAll"
                                                                                                            class="btn s37"
                                                                                                            type="button"
                                                                                                            disabled="disabled">
                                                                                                        <xms:localization
                                                                                                                text="Print Letters for All Customers in List"/>
                                                                                                    </button>
                                                                                                </td>
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

<script type="text/javascript">
    function saveSettings() {
        var data = $("#reminder_letter_settings_form").serialize();
        loadingDialog.dialog("open");
        $("#save_reminder_letter_settings").attr("disabled", "disabled");
        $.post("reminder_letter_save_settings.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "FIELD_ERROR") {
                $("#reminder_letter_settings_form").replaceWith(res.content);
            } else if (res.errorCode == "SUCCESS") {
                messageDialog.html('<xms:localization text="Saved successfully." />');
                messageDialog.dialog("open");
                $("#reminder_letter_settings_errors").html("");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
            $("#save_reminder_letter_settings").removeAttr("disabled");
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
            $("#save_reminder_letter_settings").removeAttr("disabled");
        });
    }

    function saveEmailLetter() {
        var data = $("#reminder_letter_email_letter_form").serialize();
        loadingDialog.dialog("open");
        $("#save_reminder_letter_email_letter").attr("disabled", "disabled");
        $.post("reminder_letter_save_email_letter.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "FIELD_ERROR") {
                $("#reminder_letter_email_letter_form").replaceWith(res.content);
            } else if (res.errorCode == "SUCCESS") {
                messageDialog.html('<xms:localization text="Saved successfully." />');
                messageDialog.dialog("open");
                $("#email_letter_errors").html("");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
            $("#save_reminder_letter_email_letter").removeAttr("disabled");
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
            $("#save_reminder_letter_email_letter").removeAttr("disabled");
        });
    }

    function savePrintLetter() {
        var data = $("#reminder_letter_print_letter_form").serialize();
        loadingDialog.dialog("open");
        $("#save_reminder_letter_print_letter").attr("disabled", "disabled");
        $.post("reminder_letter_save_print_letter.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "FIELD_ERROR") {
                $("#reminder_letter_print_letter_form").replaceWith(res.content);
            } else if (res.errorCode == "SUCCESS") {
                messageDialog.html('<xms:localization text="Saved successfully." />');
                messageDialog.dialog("open");
                $("#print_letter_errors").html("");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
            $("#save_reminder_letter_print_letter").removeAttr("disabled");
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
            $("#save_reminder_letter_print_letter").removeAttr("disabled");
        });
    }

    function generate() {
        var data = {
            "testEmail": $("#testEmail").val()
        };
        loadingDialog.dialog("open");
        $.post("reminder_letter_generate.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#generate_result").html(res.content);
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

    function sendInv() {
        var emailInvoice = $("#emailInvoice option:selected").val();
        if (emailInvoice == "") {
            alertDialog.html('<xms:localization text="Choose a customer to send e-mail letter." />');
            alertDialog.dialog("open");
            return;
        }
        var data = {
            "emailInvoice": emailInvoice
        };
        loadingDialog.dialog("open");
        $.post("reminder_letter_send_invoice.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                messageDialog.html('<xms:localization text="Sent successfully." />');
                messageDialog.dialog("open");
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

    function printInv() {
        var printInvoice = $("#printInvoice option:selected").val();
        if (printInvoice == "") {
            alertDialog.html('<xms:localization text="Choose a customer to print letter." />');
            alertDialog.dialog("open");
            return;
        }
        var data = {
            "printInvoice": printInvoice
        };
        loadingDialog.dialog("open");
        $.post("reminder_letter_print_invoice.ix?reqType=json", data, function (res) {
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

    function sendAll() {
        var data = $("#email_invoices_form").serialize();
        loadingDialog.dialog("open");
        $.post("reminder_letter_send_all_invoices.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                messageDialog.html('<xms:localization text="Sent successfully for first mail. System will send the rest mail in the back-end." />');
                messageDialog.dialog("open");
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

    function printAll() {
        var data = $("#print_invoices_form").serialize();
        loadingDialog.dialog("open");
        $.post("reminder_letter_print_all_invoices.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                messageDialog.html('<xms:localization text="Printed successfully." />');
                messageDialog.dialog("open");
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

    function sendPreview() {
        var data = $("#email_invoices_form").serialize();
        loadingDialog.dialog("open");
        $.post("reminder_letter_send_preview.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                messageDialog.html('<xms:localization text="Sent successfully for first mail. System will send the rest mail in the back-end." />');
                messageDialog.dialog("open");
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


</script>