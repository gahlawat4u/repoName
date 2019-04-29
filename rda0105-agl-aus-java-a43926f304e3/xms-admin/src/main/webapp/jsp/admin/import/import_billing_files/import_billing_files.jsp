<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    var hasRecords = false;
</script>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>">Home</a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Import Billing Files"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Import Billing Files"/></li>
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
                                    <xms:localization text="Import Billing Files"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
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
                                                <li><a href="#" onclick="exportClick('option')"><xms:localization
                                                        text="Option"/></a></li>
                                                <li><a href="#" onclick="exportClick('html')"><xms:localization
                                                        text="Print"/></a></li>
                                                <s:if test="grandTotal != 0">
                                                    <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                            text="Export"/></a></li>
                                                </s:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12 ">
                                                <div class="form-group ">
                                                    <table class="s36 b24">
                                                        <tr>
                                                            <td><xms:localization
                                                                    text="Select a Carrier and the invoice date to import to, and click the \"Choose file to import\" button below to select and upload the EDI file from your hard drive"/>.
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td><b><xms:localization text="Choose a Carrier to Import"/>:</b><span
                                                                    class="s30">*</span></td>
                                                        </tr>
                                                        <s:if test="services!=null">
                                                            <s:iterator value="services">
                                                                <tr>
                                                                    <td><input type="radio" name="serviceId"
                                                                               value="<s:property value="serviceId" />"/>
                                                                        <s:property value="serviceName"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:if>
                                                    </table>

                                                    <div class="row">
                                                        <div class="form-group col-xs-2">
                                                            <div class="form-group">
                                                                <label class="control-label"><xms:localization
                                                                        text="Invoice date"/>:<span class="s30">*</span></label>

                                                                <div class="form-group input-group">
                                                                    <span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i></span> <input
                                                                        class="form-control form_datetime" type="text"
                                                                        data-date-format="dd MM yyyy" id="invoiceDate"
                                                                        name="invoiceDate" readonly="readonly">
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <input type="checkbox" name="hasHeader" id="hasHeader"/>
                                                                <label class="control-label"><xms:localization
                                                                        text="This file has a header row."/></label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-xs-12"
                                                             style="max-height: 300px; overflow: auto; display: none"
                                                             id="file-datas-result"></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-xs-2">
                                                            <div class="form-group" id="import-buttons">
                                                                <button class="btn s37" type="button"
                                                                        onclick="doImportBilling()">
                                                                    <xms:localization text="Import"/>
                                                                </button>
                                                            </div>
                                                            <div class="form-group" id="other-import-buttons"
                                                                 style="display: none;">
                                                                <button class="btn s37" type="button"
                                                                        onclick="doOtherImportBilling()">
                                                                    <xms:localization text="Import"/>
                                                                </button>
                                                                <button class="btn s37" type="button"
                                                                        onclick="cancelOtherImport()">
                                                                    <xms:localization text="Cancel"/>
                                                                </button>
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
<div id="div_import_billing"></div>
<div id="div_import_billing_do"></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var dateNow = new Date();
        $("#invoiceDate").datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: 'dd-mm-yyyy'
        });
        $("#invoiceDate").datetimepicker("update", new Date());
    });

    function doImportBilling() {
        var found = false;
        $('input:radio').each(function () {
            if ($(this).is(":checked") == true) {
                found = true;
                return false;
            }
        });
        if (found == false) {
            messageDialog.html("<xms:localization text='Choose One Carrier.' />");
            messageDialog.dialog("open");
            return false;
        }
        var carrierId = $("input[name='serviceId']:checked").val();
        var invoiceDate = $("#invoiceDate").val();
        var hasHeader = false;
        if ($("#hasHeader").is(":checked")) {
            hasHeader = true;
        }
        // 		alert("Carrier: " + carrierId + " \nInvoice date: " + invoiceDate + " \nHas header = " + hasHeader);
        var dataLoad = 'billingData.carrierId=' + carrierId;
        dataLoad += '&billingData.invoiceDate=' + invoiceDate;
        dataLoad += '&billingData.hasHeader=' + hasHeader;

        var actionLoad = "do_import_billing.ix?reqType=json";
        var actionSave = "save_import_billing.ix?reqType=json";
        loadDialogToSave(actionLoad, actionSave, dataLoad, "form_import_billing", "", "Cancel", "div_import_billing", "Import Billing", "div_import_billing_do");
    }

    function doOtherImportBilling() {
        loadingDialog.dialog("open");
        var buttons = {};
        buttons["Cancel"] = function () {
            $(this).dialog("close");
        }
        var buttonResult = {};
        buttonResult["Close"] = function () {
            $(this).dialog("close");
        }
        var height = $(window).height();
        var width = $(window).width();
        height = height * 0.9;
        width = width * 0.9;
        var dialog = $("#div_import_billing").dialog({
            modal: true,
            autoOpen: false,
            width: "auto",
            buttons: buttons,
            width: 'auto',
            height: 'auto',
            maxHeight: height,
            create: function (event, ui) {
                $(this).css("maxWidth", width);
            },
            show: {
                effect: "fade",
                duration: 300
            },
            close: function (e) {
                $("#div_import_billing").html("");
            }
        });
        var dialogResult = $("#div_import_billing_do").dialog({
            modal: true,
            autoOpen: false,
            width: "auto",
            buttons: buttonResult,
            width: 'auto',
            height: 'auto',
            maxHeight: height,
            create: function (event, ui) {
                $(this).css("maxWidth", width);
            },
            show: {
                effect: "fade",
                duration: 300
            },
            close: function (e) {
                $("#div_import_billing_do").html("");
            }
        });
        var dataForm = $('#form_import_billing').serialize() + "&" + $("#other-import-form").serialize();
        $.post("save_import_billing.ix?reqType=json", dataForm, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                dialog.dialog("close");
                dialogResult.html(res.content);
                dialogResult.dialog("open");
                $("#file-datas-result").hide();
                $("#file-datas-result").html("");
                $("#other-import-buttons").hide();
                $("#import-buttons").show();
            } else if (res.errorCode == "FIELD_ERROR") {
                dialog.html(res.content);
            } else if (res.errorCode == "ACTION_ERROR") {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function cancelOtherImport() {
        $("#file-datas-result").hide();
        $("#file-datas-result").html("");
        $("#other-import-buttons").hide();
        $("#import-buttons").show();
    }


</script>