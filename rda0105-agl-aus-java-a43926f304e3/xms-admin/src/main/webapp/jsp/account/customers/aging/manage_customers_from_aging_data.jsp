<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <div class="col-lg-12">
        <div class="portlet box">
            <s:hidden id="franchiseCode" name="franchiseCode"/>
            <s:hidden id="customerCode" name="customerCode"/>
            <div class="portlet-body" style="padding: 0px;" id="manage-customer-content-body">
                <ul id="manage_customer_tab_nav" class="nav nav-tabs responsive">
                    <li style="margin-left: 10px;"><a href="#account-setup-tab" data-toggle="tab"><xms:localization
                            text="Account Setup"/></a></li>
                    <li><a href="#address-tab" data-toggle="tab"><xms:localization text="Address"/></a></li>
                    <li><a href="#base-rates-tab" data-toggle="tab"><xms:localization text="Base Rates"/></a></li>
                    <%-- <li><a href="#invoice-options-tab" data-toggle="tab"><xms:localization text="Invoice Options"/></a>
                    </li> --%>
                    <li><a href="#markups-tab" data-toggle="tab"><xms:localization text="Markups"/></a></li>
<%--                     <li class="active"><a href="#payments-tab" data-toggle="tab"><xms:localization text="Payments"/></a>
                    </li>
 --%>                    <%-- <li><a href="#collections-tab" data-toggle="tab"><xms:localization text="Collections"/></a></li>
                     --%><li><a href="#webship-tab" data-toggle="tab"><xms:localization text="Web Freight"/></a></li>
                    <li><a href="#notes-tab" data-toggle="tab"><xms:localization text="Notes"/></a></li>
                    <li><a href="#history-tab" data-toggle="tab"><xms:localization text="History"/></a></li>
                    <li><a href="#webship-history-tab" data-toggle="tab"><xms:localization text="Web Freight History"/></a>
                    </li>
                </ul>
                <div id="manage_customer_tab_content" class="tab-content responsive">
                    <div id="account-setup-tab" class="tab-pane fade"></div>
                    <div id="address-tab" class="tab-pane fade"></div>
                    <div id="base-rates-tab" class="tab-pane fade"></div>
                    <div id="invoice-options-tab" class="tab-pane fade"></div>
                    <div id="markups-tab" class="tab-pane fade"></div>
                    <div id="payments-tab" class="tab-pane fade in active"></div>
                    <div id="collections-tab" class="tab-pane fade"></div>
                    <div id="webship-tab" class="tab-pane fade"></div>
                    <div id="notes-tab" class="tab-pane fade"></div>
                    <div id="history-tab" class="tab-pane fade"></div>
                    <div id="webship-history-tab" class="tab-pane fade"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#manage_customer_tab_nav a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var activeTab = $('ul#manage_customer_tab_nav li.active a').attr('href');
            var customerCode = $("#customerCode").val();
            loadTabContent(customerCode, activeTab);
        });
        loadTabContent($("#customerCode").val(), "#payments-tab");
    });

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
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
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
            case "#invoice-options-tab":
                loadAction2Tab(customerCode, 'manage_customers_invoice_options', activeTab);
                break;
            case "#payments-tab":
                loadAction2Tab(customerCode, 'manage_customers_payments', activeTab);
                break;
            case "#collections-tab":
                loadAction2Tab(customerCode, 'manage_customers_collections', activeTab);
                break;
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


</script>