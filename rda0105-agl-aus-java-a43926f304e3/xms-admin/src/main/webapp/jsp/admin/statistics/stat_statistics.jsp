<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {
        'packages': ['corechart']
    });
</script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<div class="page-content">
    <div class="row mbl">
        <div class="col-lg-12">
            <div class="col-lg-6 no-padding-margin">
                <div class="stat-panel" data-divid="1">
                    <s:select id="statistics_type" value="%{userHomeModel.div1}" name="statType" list="graphTypes"
                              listKey="key" listValue="value" onchange="onStatTypeChange($(this))"/>
                    <div id="statistics_result"></div>
                </div>
                <div class="stat-panel" data-divid="2">
                    <s:select id="statistics_type" value="%{userHomeModel.div2}" name="statType" list="graphTypes"
                              listKey="key" listValue="value" onchange="onStatTypeChange($(this))"/>
                    <div id="statistics_result"></div>
                </div>
                <div class="stat-panel" data-divid="3">
                    <s:select id="statistics_type" value="%{userHomeModel.div3}" name="statType" list="graphTypes"
                              listKey="key" listValue="value" onchange="onStatTypeChange($(this))"/>
                    <div id="statistics_result"></div>
                </div>
            </div>
            <div class="col-lg-6 no-padding-margin">
                <div class="stat-panel" data-divid="4">
                    <s:select id="statistics_type" value="%{userHomeModel.div4}" name="statType" list="graphTypes"
                              listKey="key" listValue="value" onchange="onStatTypeChange($(this))"/>
                    <div id="statistics_result"></div>
                </div>
                <div class="stat-panel" data-divid="5">
                    <s:select id="statistics_type" value="%{userHomeModel.div5}" name="statType" list="graphTypes"
                              listKey="key" listValue="value" onchange="onStatTypeChange($(this))"/>
                    <div id="statistics_result"></div>
                </div>
                <div class="stat-panel" data-divid="6">
                    <s:select id="statistics_type" value="%{userHomeModel.div6}" name="statType" list="graphTypes"
                              listKey="key" listValue="value" onchange="onStatTypeChange($(this))"/>
                    <div id="statistics_result"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="airbill_label_dialog" title='<xms:localization text="Airbill Labels"/>'></div>
<!--END CONTENT-->
<script type="text/javascript">
    $(document).ready(function () {
        var maxWidth = $(".col-lg-6").width();
        $(".stat-panel").resizable({
            maxWidth: maxWidth,
            stop: function (event, ui) {
                var selOptions = $(this).find("select#statistics_type");
                onStatTypeChange(selOptions);
            }
        });
        $("select#statistics_type").each(function () {
            $(this).closest("div.stat-panel").height(270);
            onStatTypeChange($(this));
        });
    });

    function loadServiceTypesStat(resultDiv) {
        var data = {
            width: $(resultDiv).closest("div.stat-panel").width(),
            height: $(resultDiv).closest("div.stat-panel").height()
        };
        resultDiv.html("<div class=\"cssload-clock\"></div>");
        $.post("statistics_service_types.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                resultDiv.html(res.content);
            } else {
                resultDiv.html(res.errorMsg);
            }
        }).fail(function () {
            resultDiv.html('<xms:localization text="System internal error, please contact administrator." />');
        });
    }

    function loadWebshipLog(resultDiv, logType) {
        resultDiv.html("<div class=\"cssload-clock\"></div>");
        $.post("statistics_webship_logs.ix?reqType=json", "logType=" + logType, function (res) {
            if (res.errorCode == "SUCCESS") {
                resultDiv.html(res.content);
            } else {
                resultDiv.html(res.errorMsg);
            }
        }).fail(function () {
            resultDiv.html('<xms:localization text="System internal error, please contact administrator." />');
        });
    }

    function loadShipments(resultDiv, period) {
        resultDiv.html("<div class=\"cssload-clock\"></div>");
        var data = {
            width: $(resultDiv).closest("div.stat-panel").width(),
            height: $(resultDiv).closest("div.stat-panel").height(),
            "period": period
        };
        $.post("statistics_shipments.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                resultDiv.html(res.content);
            } else {
                resultDiv.html(res.errorMsg);
            }
        }).fail(function () {
            resultDiv.html('<xms:localization text="System internal error, please contact administrator." />');
        });
    }

    function loadCostVsMargins(resultDiv) {
        resultDiv.html("<div class=\"cssload-clock\"></div>");
        var data = {
            width: $(resultDiv).closest("div.stat-panel").width(),
            height: $(resultDiv).closest("div.stat-panel").height()
        };
        $.post("statistics_cost_vs_margins.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                resultDiv.html(res.content);
            } else {
                resultDiv.html(res.errorMsg);
            }
        }).fail(function () {
            resultDiv.html('<xms:localization text="System internal error, please contact administrator." />');
        });
    }

    function loadFollowUps(resultDiv) {
        resultDiv.html("<div class=\"cssload-clock\"></div>");
        $.post("statistics_follow_ups.ix?reqType=json", "", function (res) {
            if (res.errorCode == "SUCCESS") {
                resultDiv.html(res.content);
            } else {
                resultDiv.html(res.errorMsg);
            }
        }).fail(function () {
            resultDiv.html('<xms:localization text="System internal error, please contact administrator." />');
        });
    }

    function loadSalesRep(resultDiv) {
        resultDiv.html("<div class=\"cssload-clock\"></div>");
        var data = {
            width: $(resultDiv).closest("div.stat-panel").width(),
            height: $(resultDiv).closest("div.stat-panel").height()
        };
        $.post("statistics_sales_rep.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                resultDiv.html(res.content);
            } else {
                resultDiv.html(res.errorMsg);
            }
        }).fail(function () {
            resultDiv.html('<xms:localization text="System internal error, please contact administrator." />');
        });
    }

    function loadSalesGraph(resultDiv) {
        resultDiv.html("<div class=\"cssload-clock\"></div>");
        var data = {
            width: $(resultDiv).closest("div.stat-panel").width(),
            height: $(resultDiv).closest("div.stat-panel").height()
        };
        $.post("statistics_sales_graph.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                resultDiv.html(res.content);
            } else {
                resultDiv.html(res.errorMsg);
            }
        }).fail(function () {
            resultDiv.html('<xms:localization text="System internal error, please contact administrator." />');
        });
    }

    function onStatTypeChange(obj) {
        var statType = $(obj).val();
        var resultDiv = $(obj).closest("div.stat-panel").find("#statistics_result");
        switch (parseInt(statType)) {
            case 0:
                resultDiv.html("");
                break;
            case 1:
                loadServiceTypesStat(resultDiv);
                break;
            case 2:
                loadWebshipLog(resultDiv, 0);
                break;
            case 3:
                loadWebshipLog(resultDiv, 1);
                break;
            case 4:
                loadShipments(resultDiv, 3);
                break;
            case 5:
                loadShipments(resultDiv, 1);
                break;
            case 6:
                loadShipments(resultDiv, 2);
                break;
            case 7:
                loadCostVsMargins(resultDiv);
                break;
            case 8:
                loadFollowUps(resultDiv);
                break;
            case 9:
                loadSalesRep(resultDiv);
                break;
            case 10:
                loadSalesGraph(resultDiv);
                break;
        }
        saveDivInfo($(obj).closest("div.stat-panel"));
    }

    function loadAirbillLabels(periodType, period) {
        // Define dialog.
        var changeLogDialog = $("#airbill_label_dialog").dialog({
            modal: true,
            autoOpen: false,
            width: 'auto',
            maxWidth: 800,
            height: 'auto',
            maxHeight: 600,
            close: function (e) {
                $("#airbill_label_dialog").html("");
            }
        });
        // Get content for dialog.
        loadingDialog.dialog("open");
        var data = {
            'periodType': periodType,
            'period': period
        };
        $.post("airbill_label.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                changeLogDialog.html(res.content);
                changeLogDialog.dialog("open");
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

    function saveDivInfo(obj) {
        var data = {};
        data["userHomeModel.div" + $(obj).data("divid")] = $(obj).find("select#statistics_type").val();
        $.post("statistics_save_div_info.ix?reqType=json", data, function (res) {
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }


</script>