<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Customer Status Report"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Customer Status Report"/></li>
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
                                    <xms:localization text="Customer Status Report"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <table class="s36">
                                            <tbody>
                                            <tr>
                                                <td><input id="chkExcGst" type="checkbox" onclick="showHideColumns()">
                                                </td>
                                                <td><xms:localization text="Exclude GST/VAT"/></td>
                                                <td>|</td>
                                                <td><xms:localization text="Franchise"/></td>
                                                <td><s:select id="franchiseCode" name="franchiseCode" list="franchises"
                                                              listValue="code + ' - ' + name" listKey="code"
                                                              headerValue="All" headerKey="All"
                                                              cssClass="form-control"/></td>
                                                <td>
                                                    <button class="btn s37" type="button" onclick="search()">
                                                        &nbsp;
                                                        <xms:localization text="Go"/>
                                                        &nbsp;
                                                    </button>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-header">
                                <div class="row">
                                    <div class="tab-content" style="font-size: 11px;">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <strong><xms:localization text="Carriers:"/></strong>
                                            </div>
                                        </div>
                                        <s:iterator value="services">
                                            <div class="col-md-2 col-sm-6">
                                                <input type="checkbox" name='chkService<s:property value="serviceId" />'
                                                       service-group='<s:property value="serviceId" />'
                                                       onclick="showHideColumns()"/>
                                                <s:property value="serviceName"/>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                                <table class="s36">
                                    <tr>
                                        <td></td>

                                    </tr>
                                </table>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row" id="customer-status-report">
                                            <s:hidden name="rptTxnId"/>
                                            <div class="col-lg-12" id="weekly-report-result">
                                                <s:hidden name="weeklyField"/>
                                                <s:hidden name="weeklyType"/>
                                                <table class="table table-bordered mg0" id="weekly_report_table">
                                                    <thead>
                                                    <tr>
                                                        <th><xms:localization text="Period"/></th>
                                                        <th><xms:localization text="Setups"/></th>
                                                        <th><xms:localization text="Activations"/></th>
                                                        <th><xms:localization text="Total Ship"/></th>
                                                        <th group="inc-gst"><xms:localization text="Total Rev"/></th>
                                                        <th group="exc-gst"><xms:localization text="Total Rev"/></th>
                                                        <th group="inc-gst"><xms:localization text="Total Mrg"/></th>
                                                        <th group="exc-gst"><xms:localization text="Total Mrg"/></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="8"><xms:localization
                                                                text="No data available..."/></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div style="clear: both; width: 1px; height: 30px;"></div>
                                            <div class="col-lg-12" id="monthly-report-result" id="monthly_report_table">
                                                <s:hidden name="monthlyField"/>
                                                <s:hidden name="monthlyType"/>
                                                <table class="table table-bordered mg0">
                                                    <thead>
                                                    <tr>
                                                        <th><xms:localization text="Month"/></th>
                                                        <th><xms:localization text="Setups"/></th>
                                                        <th><xms:localization text="Activations"/></th>
                                                        <th><xms:localization text="Total Ship"/></th>
                                                        <th group="inc-gst"><xms:localization text="Total Rev"/></th>
                                                        <th group="exc-gst"><xms:localization text="Total Rev"/></th>
                                                        <th group="inc-gst"><xms:localization text="Total Mrg"/></th>
                                                        <th group="exc-gst"><xms:localization text="Total Mrg"/></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="8"><xms:localization
                                                                text="No data available..."/></td>
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
<script type="text/javascript">
    $(document).ready(function () {
        showHideColumns();
    });

    function search() {
        var data = {
            "franchiseCode": $("select#franchiseCode option:selected").val()
        };
        loadingDialog.dialog("open");
        $.post("customer_status_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customer-status-report").html(res.content);
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

    function getWeekly() {
        var data = {
            "franchiseCode": $("select#franchiseCode option:selected").val(),
            "rptTxnId": $("input[name='rptTxnId']").val(),
            "weeklyField": $("input[name='weeklyField']").val(),
            "weeklyType": $("input[name='weeklyType']").val()
        };
        loadingDialog.dialog("open");
        $.post("customer_status_weekly.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#weekly-report-result").html(res.content);
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

    function getMonthly() {
        var data = {
            "franchiseCode": $("select#franchiseCode option:selected").val(),
            "rptTxnId": $("input[name='rptTxnId']").val(),
            "monthlyField": $("input[name='monthlyField']").val(),
            "monthlyType": $("input[name='monthlyType']").val()
        };
        loadingDialog.dialog("open");
        $.post("customer_status_monthly.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#monthly-report-result").html(res.content);
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

    function showHideColumns() {
        var isExclude = $("#chkExcGst").is(":checked");
        // Show/Hide service group columns
        $("input[type='checkbox'][service-group]").each(function () {
            var isShow = $(this).is(':checked');
            var serviceGroup = $(this).attr("service-group");
            if (isShow) {
                $("#customer-status-report th[service-group='" + serviceGroup + "']").show();
                $("#customer-status-report td[service-group='" + serviceGroup + "']").show();
                if (isExclude) {
                    $("#customer-status-report th[service-group='" + serviceGroup + "'][group='inc-gst']").hide();
                    $("#customer-status-report td[service-group='" + serviceGroup + "'][group='inc-gst']").hide();
                } else {
                    $("#customer-status-report th[service-group='" + serviceGroup + "'][group='exc-gst']").hide();
                    $("#customer-status-report td[service-group='" + serviceGroup + "'][group='exc-gst']").hide();
                }
            } else {
                $("#customer-status-report th[service-group='" + serviceGroup + "']").hide();
                $("#customer-status-report td[service-group='" + serviceGroup + "']").hide();
            }
        });
        // Show/Hide columns with no service group
        if (isExclude) {
            $("#customer-status-report th:not([service-group])[group='inc-gst']").hide();
            $("#customer-status-report td:not([service-group])[group='inc-gst']").hide();
            $("#customer-status-report th:not([service-group])[group='exc-gst']").show();
            $("#customer-status-report td:not([service-group])[group='exc-gst']").show();
        } else {
            $("#customer-status-report th:not([service-group])[group='inc-gst']").show();
            $("#customer-status-report td:not([service-group])[group='inc-gst']").show();
            $("#customer-status-report th:not([service-group])[group='exc-gst']").hide();
            $("#customer-status-report td:not([service-group])[group='exc-gst']").hide();
        }
    }


</script>