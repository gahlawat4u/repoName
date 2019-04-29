<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Customers"/> &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active"><xms:localization text="Manage Customers"/></li>
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
                                <div class="caption" id="manage-customer-title">
                                    <xms:localization text="Edit Customer"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <table class="s36">
                                            <tbody>
                                            <tr>
                                                <td><xms:localization text="Search"/></td>
                                                <td><input id="searchText" name="searchText" class="form-control b20"
                                                           type="text"/></td>
                                                <td><s:select id="franchiseCode" name="franchiseCode" list="franchises"
                                                              listValue="displayName" listKey="code"
                                                              cssClass="form-control"
                                                              onchange="onFranchiseChange()"/></td>
                                                <td>
                                                    <button class="btn s37" type="button" onclick="onGoClick()">
                                                        <xms:localization text="Search"/>
                                                    </button>
                                                </td>
                                                <td id="search-customer-list"><s:select id="customerCode"
                                                                                        name="customerCode"
                                                                                        cssClass="form-control"
                                                                                        onchange="onCustomerCodeChange()"
                                                                                        list="customers"
                                                                                        listValue="displayName"
                                                                                        listKey="customerCode"/></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;" id="manage-customer-content-body">
                                <ul id="manage_customer_tab_nav" class="nav nav-tabs responsive">
                                    <li class="active" style="margin-left: 10px;"><a href="#account-setup-tab"
                                                                                     data-toggle="tab"><xms:localization
                                            text="Account Setup"/></a></li>
                                    <li><a href="#address-tab" data-toggle="tab"><xms:localization text="Address"/></a>
                                    </li>
                                    <li><a href="#base-rates-tab" data-toggle="tab"><xms:localization
                                            text="Base Rates"/></a></li>
                                    <%-- <li><a href="#invoice-options-tab" data-toggle="tab"><xms:localization
                                            text="Invoice Options"/></a></li> --%>
                                    <li><a href="#markups-tab" data-toggle="tab"><xms:localization text="Markups"/></a>
                                    </li>
                                    <%-- <li><a href="#payments-tab" data-toggle="tab"><xms:localization
                                            text="Payments"/></a></li> --%>
                                   <%--  <li><a href="#collections-tab" data-toggle="tab"><xms:localization
                                            text="Collections"/></a></li> --%>
                                    <li><a href="#webship-tab" data-toggle="tab"><xms:localization text="Web Freight"/></a>
                                    </li>
                                    <li><a href="#notes-tab" data-toggle="tab"><xms:localization text="Notes"/></a></li>
                                    <li><a href="#history-tab" data-toggle="tab"><xms:localization text="History"/></a>
                                    </li>
                                    <li><a href="#webship-history-tab" data-toggle="tab"><xms:localization
                                            text="Web Freight History"/></a></li>
                                </ul>
                                <div id="manage_customer_tab_content" class="tab-content responsive">
                                    <div id="account-setup-tab" class="tab-pane fade in active"></div>
                                    <div id="address-tab" class="tab-pane fade"></div>
                                    <div id="base-rates-tab" class="tab-pane fade"></div>
                                    <!-- <div id="invoice-options-tab" class="tab-pane fade"></div> -->
                                    <div id="markups-tab" class="tab-pane fade"></div>
                                    <div id="payments-tab" class="tab-pane fade"></div>
                                   <!--  <div id="collections-tab" class="tab-pane fade"></div> -->
                                    <div id="webship-tab" class="tab-pane fade"></div>
                                    <div id="notes-tab" class="tab-pane fade"></div>
                                    <div id="history-tab" class="tab-pane fade"></div>
                                    <div id="webship-history-tab" class="tab-pane fade"></div>
                                </div>
                                <div class="pal form-actions">
                                    <table class="s36" align="right">
                                        <tbody>
                                        <tr>
                                            <td height="10"></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <button class="btn s37" type="button" onclick="doReset()">
                                                    <xms:localization text="Reset"/>
                                                </button>
                                            </td>
                                            <td>
                                                <button class="btn s37" type="button" onclick="doSaveCustomer()">
                                                    <xms:localization text="Save"/>
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
<!-- Create customer dialog -->
<div id="create-customer-dialog" title="Add Customer"></div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#manage_customer_tab_nav a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var activeTab = $('ul#manage_customer_tab_nav li.active a').attr('href');
            var customerCode = $("#customerCode option:selected").val();
            if (customerCode != 0 && customerCode != -1) {
                loadTabContent(customerCode, activeTab);
            }
        });
        // Load account setup if customer code is not null.
        var customerCode = '<s:property value="customerCode" />';
        if (customerCode != "" && customerCode != "-1" && customerCode != "0") {
            loadTabContent(customerCode, "#account-setup-tab");
        }
    });

    function onFranchiseChange() {
        $("#searchText").val("");
        var data = {
            searchText: "",
            franchiseCode: $("#franchiseCode option:selected").val()
        };
        loadingDialog.dialog("open");
        $.post("manage_customers_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#search-customer-list").html(res.content);
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

    function onGoClick() {
        var data = {
            searchText: $("#searchText").val(),
            franchiseCode: $("#franchiseCode option:selected").val()
        };
        loadingDialog.dialog("open");
        $.post("manage_customers_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#search-customer-list").html(res.content);
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

    function loadAction2Tab(customerCode, action, tab) {
        var data = "customerCode=" + customerCode;
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

    function loadTabContent(customerCode, activeTab) {
        switch (activeTab) {
            case "#account-setup-tab":
                loadAction2Tab(customerCode, 'manage_customers_account_setup', activeTab);
                break;
            case "#address-tab":
                loadAction2Tab(customerCode, 'manage_customers_address', activeTab);
                break;
            case "#base-rates-tab":
                loadAction2Tab(customerCode, 'manage_cust_base_rate_show', activeTab);
                break;
           /*  case "#invoice-options-tab":
                loadAction2Tab(customerCode, 'manage_customers_invoice_options', activeTab);
                break;
            case "#payments-tab":
                loadAction2Tab(customerCode, 'manage_customers_payments', activeTab);
                break;
            case "#collections-tab":
                loadAction2Tab(customerCode, 'manage_customers_collections', activeTab);
                break; */
            case "#webship-tab":
                loadAction2Tab(customerCode, 'manage_customers_webship', activeTab);
                break;
            case "#notes-tab":
                loadAction2Tab(customerCode, 'manage_customers_notes', activeTab);
                break;
            case "#history-tab":
                loadAction2Tab(customerCode, 'manage_customers_history', activeTab);
                break;
            case "#markups-tab":
                loadAction2Tab(customerCode, 'manage_customers_markups', activeTab);
                break;
            case "#webship-history-tab":
                loadAction2Tab(customerCode, 'history', activeTab);
                break;
        }
    }

    function onCustomerCodeChange() {
        var customerCode = $("#customerCode option:selected").val();
        if (customerCode != -1 && customerCode != 0) {
            var activeTab = $('ul#manage_customer_tab_nav li.active a').attr('href');
            var data = {
                "customerCode": customerCode,
                "tabId": activeTab
            };
            $.post("manage_customers_load_tab.ix?reqType=json", data, function (res) {
                if (res.errorCode == "SUCCESS") {
                    $("#manage-customer-content-body").html(res.content);
                    $("#manage-customer-title").html("<xms:localization text="Edit Customer" />");
                } else {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                alertDialog.dialog("open");
            });
        } else if (customerCode == 0) {
            loadAddCusDialog();
        }
        var url = document.location.href;
        var urlparts = url.split('?');
        if (urlparts.length >= 2) {
            window.history.pushState('', document.title, urlparts[0]);
        }
    }

    function loadAddCusDialog() {
        var franCode = $("#franchiseCode option:selected").val();
        loadingDialog.dialog("open");
        $.post("manage_customers_load_add_cus_dialog.ix?reqType=json", "franchiseCode=" + franCode, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Define Save and Cancel buttons
                var buttons = {};
                buttons["Create Customer"] = function () {
                    var profileId = $("select#profileId option:selected").val();
                    $("#create-customer-dialog").dialog("close");
                    createCustomer(profileId);
                };
                buttons["Cancel"] = function () {
                    $(this).dialog("close");
                }
                // Define dialog
                var dialog = $("#create-customer-dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: "auto",
                    buttons: buttons,
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                dialog.html(res.content);
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

    function createCustomer(profileId) {
        loadingDialog.dialog("open");
        $.post("add_customer_load.ix?reqType=json", "profileId=" + profileId, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#manage-customer-content-body").html(res.content);
                $("#manage-customer-title").html("<xms:localization text="Add New Customer" />");
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

    function doSaveCustomer() {
        loadingDialog.dialog("open");
        var accountSetupData = $("#frmSaveAccountSetup").serialize();
        var addressData = $("#frmSaveCustomerAddress").serialize();
        var baseRatesData = $("#frmSaveCustomerBaseRates").serialize();
        var invoiceOptionsData = $("#frmSaveCustomerInvoiceOptions").serialize();
        var collectionsData = $("#frmSaveCustomerCollection").serialize();
        var webshipCarrierData = $("#frmSaveWebshipSettings").serialize() + "&" + $("#frmSaveUserSetting").serialize();

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
        $.post("manage_customers_save_customer.ix?reqType=json", postData, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                messageDialog.html("Save Successful!");
                messageDialog.dialog("open");
            } else if (res.errorCode == "FIELD_ERROR") {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
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

    function doReset() {
        $("#manage_customer_tab_content form").each(function () {
            this.reset();
        });
    }


</script>