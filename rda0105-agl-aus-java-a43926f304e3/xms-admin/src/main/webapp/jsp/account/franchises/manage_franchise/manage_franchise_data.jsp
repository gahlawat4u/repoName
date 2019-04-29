<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<ul id="manage_franchise_tab_nav" class="nav nav-tabs responsive">
    <li style="margin-left: 10px;"><a href="#Account-tab" data-toggle="tab"><xms:localization text="Account Setup"/></a>
    </li>
    <li><a href="#Address-tab" data-toggle="tab"><xms:localization text="Address"/></a></li>
    <li><a href="#Base-tab" data-toggle="tab"><xms:localization text="Base Rates"/></a></li>
<%--     <li><a href="#Invoice-tab" data-toggle="tab"><xms:localization text="Invoice Options"/></a></li>
 --%>    <li><a href="#Markups-tab" data-toggle="tab"><xms:localization text="Markups"/></a></li>
<%--     <li><a href="#Payments-tab" data-toggle="tab"><xms:localization text="Payments"/></a></li>
 --%>   <%--  <li><a href="#Collections-tab" data-toggle="tab"><xms:localization text="Collections"/></a></li> --%>
    <li><a href="#Webship-tab" data-toggle="tab"><xms:localization text="Web Freight"/></a></li>
    <li><a href="#Notes-tab" data-toggle="tab"><xms:localization text="Notes"/></a></li>
    <li><a href="#History-tab" data-toggle="tab"><xms:localization text="History"/></a></li>
    <li><a href="#WebshipHistory-tab" data-toggle="tab"><xms:localization text="Web Freight History"/></a></li>
</ul>
<div id="manage_franchise_tab_content" class="tab-content responsive">
    <div id="Account-tab" class="tab-pane fade in active"></div>
    <div id="Address-tab" class="tab-pane fade in"></div>
    <div id="Base-tab" class="tab-pane fade in"></div>
    <!-- <div id="Invoice-tab" class="tab-pane fade in"></div> -->
    <div id="Markups-tab" class="tab-pane fade in"></div>
    <!-- <div id="Payments-tab" class="tab-pane fade in"></div>
    <div id="Collections-tab" class="tab-pane fade in"></div> -->
    <div id="Webship-tab" class="tab-pane fade in"></div>
    <div id="Notes-tab" class="tab-pane fade in"></div>
    <div id="History-tab" class="tab-pane fade in"></div>
    <div id="WebshipHistory-tab" class="tab-pane fade in"></div>
    <div class="row" style="text-align: right;">
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

<script type="text/javascript">
    $('#manage_franchise_tab_nav a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var activeTab = $('ul#manage_franchise_tab_nav li.active a').attr('href');
        var franchiseCode = $("#sel_franchise option:selected").val();
        if (franchiseCode != 0 && franchiseCode != -1) {
            loadTabContent(franchiseCode, activeTab);
        }
    });

    var activeTab = '<s:property value="tabId" />';
    var franchiseCode = '<s:property value="franchiseCode" />';
    $("#manage_franchise_tab_nav li a[href='" + activeTab + "']").parent().addClass("active");
    $(activeTab).addClass("in active");
    loadTabContent(franchiseCode, activeTab);


</script>