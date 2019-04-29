<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:hidden id="view_franchise_code" name="franchiseCode"/>
<ul id="manage_franchise_tab_nav" class="nav nav-tabs responsive">
    <li style="margin-left: 10px;"><a href="#Account-tab" data-toggle="tab"><xms:localization text="Account Setup"/></a>
    </li>
    <li><a href="#Address-tab" data-toggle="tab"><xms:localization text="Address"/></a></li>
    <li><a href="#Base-tab" data-toggle="tab"><xms:localization text="Base Rates"/></a></li>
    <li><a href="#Invoice-tab" data-toggle="tab"><xms:localization text="Invoice Options"/></a></li>
    <li><a href="#Markups-tab" data-toggle="tab"><xms:localization text="Markups"/></a></li>
    <li><a href="#Payments-tab" data-toggle="tab"><xms:localization text="Payments"/></a></li>
    <li><a href="#Collections-tab" data-toggle="tab"><xms:localization text="Collections"/></a></li>
    <li><a href="#Webship-tab" data-toggle="tab"><xms:localization text="Web Freight"/></a></li>
    <li><a href="#Notes-tab" data-toggle="tab"><xms:localization text="Notes"/></a></li>
    <li><a href="#History-tab" data-toggle="tab"><xms:localization text="History"/></a></li>
    <li><a href="#WebshipHistory-tab" data-toggle="tab"><xms:localization text="Web Freight History"/></a></li>
</ul>
<div id="manage_franchise_tab_content" class="tab-content responsive">
    <div id="Account-tab" class="tab-pane fade in active"></div>
    <div id="Address-tab" class="tab-pane fade in"></div>
    <div id="Base-tab" class="tab-pane fade in"></div>
    <div id="Invoice-tab" class="tab-pane fade in"></div>
    <div id="Markups-tab" class="tab-pane fade in"></div>
    <div id="Payments-tab" class="tab-pane fade in"></div>
    <div id="Collections-tab" class="tab-pane fade in"></div>
    <div id="Webship-tab" class="tab-pane fade in"></div>
    <div id="Notes-tab" class="tab-pane fade in"></div>
    <div id="History-tab" class="tab-pane fade in"></div>
    <div id="WebshipHistory-tab" class="tab-pane fade in"></div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#manage_franchise_tab_nav a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            var activeTab = $('ul#manage_franchise_tab_nav li.active a').attr('href');
            var franchiseCode = $("#view_franchise_code").val();
            loadTabContent(franchiseCode, activeTab);
        });

        var activeTab = '<s:property value="tabId" />';
        var franchiseCode = '<s:property value="franchiseCode" />';
        $("#manage_franchise_tab_nav li a[href='" + activeTab + "']").parent().addClass("active");
        $(activeTab).addClass("in active");
        loadTabContent(franchiseCode, activeTab);
    });

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
            case "#Invoice-tab":
                loadAction2Tab(code, 'manage_franchise_invoice_options', tab);
                break;
            case "#Payments-tab":
                loadAction2Tab(code, 'manage_franchise_payments', tab);
                break;
            case "#Collections-tab":
                loadAction2Tab(code, 'manage_franchise_collections', tab);
                break;
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


</script>