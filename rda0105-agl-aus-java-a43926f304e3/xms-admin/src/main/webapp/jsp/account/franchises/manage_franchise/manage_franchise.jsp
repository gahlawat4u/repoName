<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Manage Franchise"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Manage Franchise"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<s:form id="formManageFranchise">
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
                                    <div class="caption" id="manage_franchise_title">
                                        <xms:localization text="Edit Franchise"/>
                                    </div>
                                    <br/>

                                    <div id="div_error_smg"></div>
                                    <div class="col-lg-8 flr">
                                        <div class="form-group flr mgb">
                                            <table class="s36">
                                                <tbody>
                                                <tr>
                                                    <td id="td_list_franchise"><s:select name="franchiseCode"
                                                                                         list="franchises"
                                                                                         class="form-control"
                                                                                         listKey="code" headerKey="-1"
                                                                                         headerValue="Select a Franchise"
                                                                                         listValue="name"
                                                                                         onchange="changeFranchise($(this).val())"
                                                                                         id="sel_franchise"
                                                                                         value="franchiseCode"></s:select></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body" style="padding: 0px;" id="manage_franchise_body_content">
                                    <ul id="manage_franchise_tab_nav" class="nav nav-tabs responsive">
                                        <li class="active" style="margin-left: 10px;"><a href="#Account-tab"
                                                                                         data-toggle="tab"><xms:localization
                                                text="Account Setup"/></a></li>
                                        <li><a href="#Address-tab" data-toggle="tab"><xms:localization
                                                text="Address"/></a></li>
                                        <li><a href="#Base-tab" data-toggle="tab"><xms:localization
                                                text="Base Rates"/></a></li>
                                        <%-- <li><a href="#Invoice-tab" data-toggle="tab"><xms:localization
                                                text="Invoice Options"/></a></li> --%>
                                        <li><a href="#Markups-tab" data-toggle="tab"><xms:localization
                                                text="Markups"/></a></li>
                                        <%-- <li><a href="#Payments-tab" data-toggle="tab"><xms:localization
                                                text="Payments"/></a></li> --%>
                                       <%--  <li><a href="#Collections-tab" data-toggle="tab"><xms:localization
                                                text="Collections"/></a></li> --%>
                                        <li><a href="#Webship-tab" data-toggle="tab"><xms:localization
                                                text="Web Freight"/></a></li>
                                        <li><a href="#Notes-tab" data-toggle="tab"><xms:localization text="Notes"/></a>
                                        </li>
                                        <li><a href="#History-tab" data-toggle="tab"><xms:localization
                                                text="History"/></a></li>
                                        <li><a href="#WebshipHistory-tab" data-toggle="tab"><xms:localization
                                                text="Web Freight History"/></a></li>
                                    </ul>
                                    <div id="manage_franchise_tab_content" class="tab-content responsive">
                                        <div id="Account-tab" class="tab-pane fade in active"></div>
                                        <div id="Address-tab" class="tab-pane fade in"></div>
                                        <div id="Base-tab" class="tab-pane fade in"></div>
                                        <!-- <div id="Invoice-tab" class="tab-pane fade in"></div> -->
                                        <div id="Markups-tab" class="tab-pane fade in"></div>
                                       <!--  <div id="Payments-tab" class="tab-pane fade in"></div>
                                        <div id="Collections-tab" class="tab-pane fade in"></div> -->
                                        <div id="Webship-tab" class="tab-pane fade in"></div>
                                        <div id="Notes-tab" class="tab-pane fade in"></div>
                                        <div id="History-tab" class="tab-pane fade in"></div>
                                        <div id="WebshipHistory-tab" class="tab-pane fade in"></div>
                                        <div class="row" style="text-align: right;margin-top:10px;">
                                            <div class="col-lg-12">
                                                <button class="btn s37" type="button" onclick="doReset()">
                                                    <xms:localization text="Reset"/>
                                                </button>
                                                <button class="btn s37" type="button" onclick="doSaveFranchise();">
                                                    <xms:localization text="Save"/>
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
    <!--END CONTENT-->
</s:form>
<div id="div_save_franchise_result"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#manage_franchise_tab_nav a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var activeTab = $('ul#manage_franchise_tab_nav li.active a').attr('href');
            var franchiseCode = $("#sel_franchise option:selected").val();
            if (franchiseCode != 0 && franchiseCode != -1) {
                loadTabContent(franchiseCode, activeTab);
            }
        });
        // Load account setup if franchise code is not null.
        var franchiseCode = '<s:property value="franchiseCode" />';
        if (franchiseCode != "" && franchiseCode != "-1" && franchiseCode != "0") {
            loadTabContent(franchiseCode, "#Account-tab");
        }
    });

    function manageFranchiseSave() {
        var data = $("#formManageFranchise").serialize();
        loadingDialog.dialog("open");
        $.post("manage_franchise_account_setup_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                if ($("#sel_franchise").val() == "0") {
                    dialog.html("Create Success.");
                    dialog.dialog("open");
                    $('#td_list_franchise').html(res.content);
                    var data = {
                        'franchiseCode': $('#sel_franchise').val()
                    };

                    var action = "manage_franchise_account_setup.ix?reqType=json";
                    loadingDialog.dialog("open");
                    $.post(action, data, function (res) {
                        if (res.errorCode == "SUCCESS") {
                            $('#Account-tab').html(res.content);
                            loadingDialog.dialog("close");
                        } else {
                            loadingDialog.dialog("close");
                            alertDialog.html('Error: ' + res.errorMsg);
                            alertDialog.dialog("open");
                        }
                    }).fail(function () {
                        loadingDialog.dialog("close");
                        alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                        alertDialog.dialog("open");
                    });
                } else {
                    dialog.html(res.content);
                    dialog.dialog("open");
                }
            } else if (res.errorCode == "FIELD_ERROR") {
                dialog.html(res.content);
                dialog.dialog("open");
            } else if (res.errorCode == "ACTION_ERROR") {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });

        var buttons = {};
        var height = $(window).height();
        var width = $(window).width();
        height = height * 0.70;
        width = width * 0.9;
        var dialog = $("#div_save_franchise_result").dialog({
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
                $("#div_save_franchise_result").html("");
            }
        });
        dialog.dialog("option", "title", "Save Franchise.");
    }

    function changeFranchise(val) {
        var franchiseCode = $("#sel_franchise option:selected").val();
        if (franchiseCode != -1 && franchiseCode != 0) {
            var activeTab = $('ul#manage_franchise_tab_nav li.active a').attr('href');
            var data = {
                "franchiseCode": franchiseCode,
                "tabId": activeTab
            };
            $.post("manage_franchise_load_tab.ix?reqType=json", data, function (res) {
                if (res.errorCode == "SUCCESS") {
                    $("#manage_franchise_body_content").html(res.content);
                    $("#manage_franchise_title").html("<xms:localization text="Edit Franchise" />");
                } else {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                alertDialog.dialog("open");
            });
        } else if (franchiseCode == 0) {
            createFranchise();
        }
    }

    function createFranchise() {
        loadingDialog.dialog("open");
        $.post("add_franchise_load.ix?reqType=json", "", function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#manage_franchise_body_content").html(res.content);
                $("#manage_franchise_title").html("<xms:localization text="Add New Franchise" />");
            } else {
                alertDialog.html('Error: ' + res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function loadAction2Tab(code, action, tab) {
        var data = {
            'franchiseCode': code,
            'customerCode': code
        };
        loadingDialog.dialog("open");
        $.post(action + ".ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $(tab).html(res.content);
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

    function loadTabContent(code, tab) {
        switch (tab) {
            case "#Account-tab":
                loadAction2Tab(code, 'manage_franchise_account_setup', tab);
                break;
            case "#Address-tab":
                loadAction2Tab(code, 'manage_franchise_address', tab);
                break;
            case "#Base-tab":
                loadAction2Tab(code, 'manage_fran_base_rate_show', tab);
                break;
            /* case "#Invoice-tab":
                loadAction2Tab(code, 'manage_franchise_invoice_options', tab);
                break; */
            /* case "#Payments-tab":
                loadAction2Tab(code, 'manage_franchise_payments', tab);
                break;
 */            /* case "#Collections-tab":
                loadAction2Tab(code, 'manage_franchise_collections', tab);
                break; */
            case "#Webship-tab":
                loadAction2Tab(code, 'manage_franchise_webship', tab);
                break;
            case "#Notes-tab":
                loadAction2Tab(code, 'manage_franchise_notes', tab);
                break;
            case "#History-tab":
                loadAction2Tab(code, 'manage_franchise_history', tab);
                break;
            case "#Markups-tab":
                loadAction2Tab(code, 'manage_franchise_markups', tab);
                break;
            case "#WebshipHistory-tab":
                loadAction2Tab(code, 'history', tab);
                break;
        }
    }

    function doSaveFranchise() {
        loadingDialog.dialog("open");
        var accountSetupData = $("#frmSaveAccountSetup").serialize();
        var addressData = $("#frmSaveFranchiseAddress").serialize();
        var baseRatesData = $("#frmSaveCustomerBaseRates").serialize();
        var invoiceOptionsData = $("#frmSaveFranchiseInvoiceOptions").serialize();
        var collectionsData = $("#frmSaveFranchiseCollection").serialize();
        var webshipCarrierData = $("#frmSaveWebshipSettings").serialize() + "&" + $("#frmSaveUserSetting").serialize();
        var prevFranchise = $('#sel_franchise').val();
        var postData = "";
        if (accountSetupData != "" && accountSetupData != null) {
            postData += "&" + accountSetupData;
        }
        if (addressData != "" && addressData != null) {
            postData += "&" + addressData;
        }
        if (baseRatesData != "" && baseRatesData != null) {
            postData += "&" + baseRatesData;
        }

        if (invoiceOptionsData != "" && invoiceOptionsData != null) {
            postData += "&" + invoiceOptionsData;
        }
        if (collectionsData != "" && collectionsData != null) {
            postData += "&" + collectionsData;
        }
        if (webshipCarrierData != "" && webshipCarrierData != null) {
            postData += "&" + webshipCarrierData;
        }
        $.post("manage_franchises_save_franchise.ix?reqType=json", postData, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                messageDialog.html("Success");
                messageDialog.dialog("open");
                $('#td_list_franchise').html(res.content);
                $('#sel_franchise').val(prevFranchise);
            } else if (res.errorCode == "FIELD_ERROR") {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function doReset() {
        $("#manage_franchise_tab_content form").each(function () {
            this.reset();
        });
    }
</script>
