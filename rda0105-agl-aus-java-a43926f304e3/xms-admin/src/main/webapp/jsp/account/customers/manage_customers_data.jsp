<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<ul id="manage_customer_tab_nav" class="nav nav-tabs responsive">
    <li style="margin-left: 10px;"><a href="#account-setup-tab" data-toggle="tab"><xms:localization
            text="Account Setup"/></a></li>
    <li><a href="#address-tab" data-toggle="tab"><xms:localization text="Address"/></a></li>
    <li><a href="#base-rates-tab" data-toggle="tab"><xms:localization text="Base Rates"/></a></li>
<%--     <li><a href="#invoice-options-tab" data-toggle="tab"><xms:localization text="Invoice Options"/></a></li>
 --%>    <li><a href="#markups-tab" data-toggle="tab"><xms:localization text="Markups"/></a></li>
    <%-- <li><a href="#payments-tab" data-toggle="tab"><xms:localization text="Payments"/></a></li> --%>
<%--     <li><a href="#collections-tab" data-toggle="tab"><xms:localization text="Collections"/></a></li>
 --%>    <li><a href="#webship-tab" data-toggle="tab"><xms:localization text="Web Freight"/></a></li>
    <li><a href="#notes-tab" data-toggle="tab"><xms:localization text="Notes"/></a></li>
    <li><a href="#history-tab" data-toggle="tab"><xms:localization text="History"/></a></li>
    <li><a href="#webship-history-tab" data-toggle="tab"><xms:localization text="Web Freight History"/></a></li>
</ul>
<div id="manage_customer_tab_content" class="tab-content responsive">
    <div id="account-setup-tab" class="tab-pane fade in active"></div>
    <div id="address-tab" class="tab-pane fade"></div>
    <div id="base-rates-tab" class="tab-pane fade"></div>
    <div id="invoice-options-tab" class="tab-pane fade"></div>
    <div id="markups-tab" class="tab-pane fade"></div>
    <div id="payments-tab" class="tab-pane fade"></div>
    <div id="collections-tab" class="tab-pane fade"></div>
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
<script type="text/javascript">
    $('#manage_customer_tab_nav a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var activeTab = $('ul#manage_customer_tab_nav li.active a').attr('href');
        var customerCode = $("#customerCode option:selected").val();
        if (customerCode != 0 && customerCode != -1) {
            loadTabContent(customerCode, activeTab);
        }
    });

    var activeTab = '<s:property value="tabId" />';
    var customerCode = '<s:property value="customerCode" />';
    $("#manage_customer_tab_nav li a[href='" + activeTab + "']").parent().addClass("active");
    $(activeTab).addClass("in active");
    loadTabContent(customerCode, activeTab);


</script>