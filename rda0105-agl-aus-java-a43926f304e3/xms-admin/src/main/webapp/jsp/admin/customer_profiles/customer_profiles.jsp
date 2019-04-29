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
        <li class="hidden"><a href="#"><xms:localization text="Customer Profile"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Customer Profile"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<div class="page-content">
    <s:form id="formCustomerProfile">
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
                                        <xms:localization text="Customer Profile"/>
                                    </div>
                                    <br/>

                                    <div id="div_error_smg"></div>
                                    <div class="col-lg-8 flr">
                                        <div class="form-group flr mgb">
                                            <table class="s36">
                                                <tbody>
                                                <tr>
                                                    <td><input type="text" class="form-control" id="txt_profile_name"/>
                                                    </td>
                                                    <td><s:select name="franchiseCode" list="franchises"
                                                                  class="form-control" listKey="code" listValue="name"
                                                                  onchange="changeFranchise($(this).val())"
                                                                  id="sel_franchise"/></td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="searchCustomerProfiles()">
                                                            <xms:localization text="Search"/>
                                                        </button>
                                                    </td>
                                                    <td id="td_select_customer_profiles"><s:select list="customers"
                                                                                                   name="profileId"
                                                                                                   class="form-control"
                                                                                                   listKey="profileId"
                                                                                                   listValue="profileName"
                                                                                                   onchange="loadInfoProfile($(this).val())"
                                                                                                   id="sel_customer_profiles"
                                                                                                   headerKey="-1"
                                                                                                   headerValue="Select a Customer"/></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body" style="padding: 0px;" id="customer-profiles-container">
                                    <ul id="generalTab" class="nav nav-tabs responsive">
                                        <li class="active" style="margin-left: 10px;"><a href="#Account-tab"
                                                                                         data-toggle="tab"
                                                                                         onclick="loadAccountSetup(0)"><xms:localization
                                                text="Account Setup"/></a></li>
                                        <li><a href="#Base-tab" data-toggle="tab" class="tb1" onclick="loadBaseRate(0)"><xms:localization
                                                text="Base Rates"/></a></li>
                                        <li><a href="#Invoice-tab" data-toggle="tab"
                                               onclick="loadInvoiceOptions(0)"><xms:localization
                                                text="Invoice Options"/></a></li>
                                        <li><a href="#Markups-tab" data-toggle="tab"
                                               onclick="loadMarkups(0)"><xms:localization text="Markups"/></a></li>
                                        <li><a href="#Collections-tab" data-toggle="tab" class="tb1"
                                               onclick="loadCollections(0)"><xms:localization text="Collections"/></a>
                                        </li>
                                        <li><a href="#History-tab" data-toggle="tab"
                                               onclick="loadHistory(0)"><xms:localization text="History"/></a></li>
                                    </ul>
                                    <div id="generalTabContent" class="tab-content responsive">
                                        <div id="Account-tab" class="tab-pane fade in active"></div>
                                        <div id="Base-tab" class="tab-pane fade in"></div>
                                        <div id="Invoice-tab" class="tab-pane fade in"></div>
                                        <div id="Markups-tab" class="tab-pane fade in"></div>
                                        <div id="Collections-tab" class="tab-pane fade in"></div>
                                        <div id="History-tab" class="tab-pane fade in"></div>
                                    </div>
                                </div>
                                <div class="pal form-actions">
                                    <table class="s36" align="right">
                                        <tbody>
                                        <tr>
                                            <td height="10"></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <button class="btn s37" type="button">
                                                    <xms:localization text="Reset"/>
                                                </button>
                                            </td>
                                            <td>
                                                <button class="btn s37" type="button" onclick="saveCustomerProfile()">
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
    </s:form>
</div>
<!--END CONTENT-->
<div id="div-add-customer-profile" title="<xms:localization text="Add customer profile" />"></div>
<div id="msg-dialog" title="<xms:localization text="Message" />"></div>
<script type="text/javascript">
    $(document).ready(function () {
        var profileId = '<s:property value="profileId" />';
        if (profileId != 0 && profileId != "") {
            checkLoadTab("Account-tab");
        }
    });
    function changeFranchise(val) {
        var data = {
            'franchiseCode': val
        };
        var action = "select_list_customer_profiles.ix?reqType=json";
        loadingDialog.dialog("open");
        $.post(action, data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $('#td_select_customer_profiles').html(res.content);
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
    }

    function searchCustomerProfiles() {
        $('#div_error_smg').html("");
        var data = {
            'profileName': $('#txt_profile_name').val(),
            'franchiseCode': $('#sel_franchise').val()
        };
        var action = "search_customer_profiles.ix?reqType=json";
        loadingDialog.dialog("open");
        $.post(action, data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $('#td_select_customer_profiles').html(res.content);
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
    }

    function loadAccountSetup(check) {
        $("#btn_action").show();
        if ($("#Account-tab").html().trim() == "" || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val()
            };
            if (val == "-1") {
                $('#Account-tab').html("");
            } else {
                var action = "manager_customer_account_setup.ix?reqType=json";
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
            }
        }
    }

    function loadMarkups(check) {
        $("#btn_action").hide();
        if ($("#Markups-tab").html().trim() == "" || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val()
            };
            if (val == "-1") {
                $('#Markups-tab').html("");
            } else if (val == "0") {
                $('#Markups-tab').html("Display mode add new profile.");
            } else {
                var action = "manager_customer_markups.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#Markups-tab').html(res.content);
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
            }
        }
    }

    function loadCollections(check) {
        $("#btn_action").show();
        if ($("#Collections-tab").html().trim() == "" || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val()
            };
            if (val == "-1") {
                $('#Collections-tab').html("");
            } else {
                var action = "manager_customer_collections.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#Collections-tab').html(res.content);
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
            }
        }
    }

    function loadBaseRate(check) {
        $("#btn_action").show();
        if ($("#Base-tab").html().trim() == "" || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val()
            };
            if (val == "-1") {
                $('#Base-tab').html("");
            } else {
                var action = "manage_cust_profile_base_rate_show.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#Base-tab').html(res.content);
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
            }
        }
    }

    function loadHistory(check) {
        $("#btn_action").hide();
        if ($("#History-tab").html().trim() == "" || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val()
            };
            if (val == "-1") {
                $('#History-tab').html("");
            } else if (val == "0") {
                $('#History-tab').html("Display mode add new profile.");
            } else {
                var action = "manager_customer_history.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#History-tab').html(res.content);
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
            }
        }
    }

    function loadInvoiceOptions(check) {
        $("#btn_action").show();
        if ($("#Invoice-tab").html().trim() == "" || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val()
            };
            if (val == "-1") {
                $('#Invoice-tab').html("");
            } else {
                var action = "manager_customer_invoice_options.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#Invoice-tab').html(res.content);
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
            }
        }
    }

    function loadInfoProfile(val) {
        if (val == "0") {
            loadAddNew();
        } else if (val == "-1") {
            $("div.tab-pane").removeClass("active");
            $("#Account-tab").addClass("active");
            $("li.active").removeClass("active");
            $("#generalTab").find("li").removeClass("active");
            $("#generalTab").find("a[href$='Account-tab']").parent("li").addClass("active");
            $("div.tab-pane").html("");
            $("#generalTab").find("li").show();
        } else {
            $("div.tab-pane").each(function () {
                var id_tab = $(this).attr("id");
                if ($(this).hasClass("active")) {
                    checkLoadTab(id_tab);
                } else {
                    $(this).html("");
                }
            });
            $("#generalTab").find("li").show();
        }
    }

    function loadAddNew() {
        var data = {
            "franchiseCode": $("#sel_franchise").val()
        };
        loadDialog("manager_customer_profiles_load_add.ix?reqType=json", data, "add-customer-profile-form", "<xms:localization text="Create Customer Profile" />", "<xms:localization text="Cancel" />", "div-add-customer-profile", "", "customer-profiles-container");
    }

    function checkLoadTab(idTab) {
        if (idTab == "Invoice-tab") {
            loadInvoiceOptions(1);
        }
        if (idTab == "Account-tab") {
            loadAccountSetup(1);
        }
        if (idTab == "Markups-tab") {
            loadMarkups(1);
        }
        if (idTab == "Collections-tab") {
            loadCollections(1);
        }
        if (idTab == "History-tab") {
            loadHistory(1);
        }
        if (idTab == "Base-tab") {
            loadBaseRate(1);
        }
    }

    function saveCustomerProfile() {
        loadingDialog.dialog("open");
        var accountSetupData = $("#saveCustomerProfileAccountSetupForm").serialize();
        var baseRatesData = $("#frmSaveCustomerProfileBaseRates").serialize();
        var invoiceOptionsData = $("#saveCustomerProfileInvoiceOption").serialize();
        var collectionsData = $("#saveCustomerProfileCollectionForm").serialize();
        var profileId = $("#sel_customer_profiles").val();

        var postData = "";
        if (accountSetupData != "" && accountSetupData != null) {
            postData += "&" + accountSetupData;
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
        var action = "manager_customer_profiles_save.ix?reqType=json";
        if (profileId == "0") {
            postData = $("#add-new-profile-form").serialize();
            action = "manager_customer_profiles_do_add.ix?reqType=json";
        }
        $.post(action, postData, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                if (profileId == "0") {
                    // Define message dialog.
                    var buttons = {};
                    buttons["<xms:localization text="OK" />"] = function () {
                        var prId = $("#msg-dialog").find("#profileId").val();
                        window.location.href = "manager_customer_profiles.ix?profileId=" + prId;
                    };
                    var msgDialog = $("#msg-dialog").dialog({
                        modal: true,
                        autoOpen: false,
                        buttons: buttons,
                        width: '320',
                        height: '180'
                    });
                    var htmlString = $.trim(res.content) + "<xms:localization text="Saved successful!" />";
                    msgDialog.html(htmlString);
                    msgDialog.dialog("open");
                } else {
                    messageDialog.html("<xms:localization text="Saved successful!" />");
                    messageDialog.dialog("open");
                }
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
</script>