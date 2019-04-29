<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="dashboard.html"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Customer Summary Report"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Customer Summary Report"/></li>
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
                                    <xms:localization text="Customer Summary Report"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group">
                                            <input type="button" id="export-option" class="btn s37"
                                                   value="<xms:localization text="Option" />"
                                                   onclick="exportClick($('#selected-option').val())"/>
                                            <button type="button" class="btn s37 dropdown-toggle" data-toggle="dropdown"
                                                    aria-expanded="true">
                                                <span class="caret"></span>
                                            </button>
                                            <s:hidden id="selected-option" value="option"/>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                <li><a href="#" onclick="exportClick('option')"><xms:localization
                                                        text="Option"/></a></li>
                                                <li><a href="#" onclick="exportClick('print')"><xms:localization
                                                        text="Print"/></a></li>
                                                <li><a href="#" onclick="exportClick('export')"><xms:localization
                                                        text="Export"/></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <ul id="generalTab" class="nav nav-tabs responsive">
                                    <li class="active" style="margin-left: 10px;"><a href="#General-information-tab"
                                                                                     data-toggle="tab"><xms:localization
                                            text="General Information"/></a></li>
                                    <li><a href="#Carrier-list-tab" data-toggle="tab"><xms:localization
                                            text="Carrier List"/></a></li>
                                </ul>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div id="generalTabContent" class="tab-content responsive">
                                            <div id="General-information-tab" class="tab-pane fade in active">
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="form-group">
                                                            <form id="frmCustomerSummarySearch">
                                                                <table class="s36 b24">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Franchise"/></td>
                                                                        <td><s:i18n_select id="franchiseCode"
                                                                                           name="franchiseCode"
                                                                                           list="franchises"
                                                                                           listValue="code + ' - ' + name"
                                                                                           listKey="code"
                                                                                           headerValue="All"
                                                                                           headerKey="All"
                                                                                           cssClass="form-control"
                                                                                           i18nitem="false"/></td>
                                                                        <td><xms:localization text="Sales Rep"/></td>
                                                                        <td><s:i18n_select id="saleRepId"
                                                                                           name="saleRepId"
                                                                                           list="saleReps"
                                                                                           listValue="displayName"
                                                                                           listKey="userId"
                                                                                           headerValue="All"
                                                                                           headerKey=""
                                                                                           cssClass="form-control"
                                                                                           i18nitem="false"/></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                                <table class="s36 b24">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Period"/></td>
                                                                        <td><s:select id="dateRange" name="dateRange"
                                                                                      list="periods"
                                                                                      headerValue="Custom Date Range"
                                                                                      headerKey=""
                                                                                      cssClass="form-control"
                                                                                      onchange="onChangeDateRange()"/></td>
                                                                        <td><input id="startDate" name="startDate"
                                                                                   class="form-control form_datetime"
                                                                                   type="text"
                                                                                   data-date-format="dd MM yyyy"
                                                                                   style="width: 70px;"
                                                                                   placeholder="Start"
                                                                                   readonly="readonly"/></td>
                                                                        <td><input id="endDate" name="endDate"
                                                                                   class="form-control form_datetime"
                                                                                   type="text"
                                                                                   data-date-format="dd MM yyyy"
                                                                                   style="width: 70px;"
                                                                                   placeholder="End"
                                                                                   readonly="readonly"/></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                                <div class="col-lg-12 row">
                                                                    <table class="s36 b24">
                                                                        <tr>
                                                                            <td><s:checkbox id="chkShowDetail"
                                                                                            name="showCostRevenue"
                                                                                            onclick="onShowDetailClick()"/></td>
                                                                            <td><xms:localization
                                                                                    text="Show Cost/ Revenue Detail Columns"/></td>
                                                                            <td>&nbsp;</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td><s:checkbox id="excludeGst"
                                                                                            name="excludeGst"
                                                                                            onchange="doSearch()"/></td>
                                                                            <td><xms:localization
                                                                                    text="Exclude GST/VAT"/></td>
                                                                            <td rowspan="2"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td><s:checkbox id="excludeGstSurcharge"
                                                                                            name="excludeGstSurcharge"
                                                                                            onchange="doSearch()"/></td>
                                                                            <td><xms:localization
                                                                                    text="Exclude Duty/Tax"/></td>
                                                                        </tr>
                                                                    </table>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="Carrier-list-tab" class="tab-pane fade in">
                                                <div class="row b25c" id="service_tree">
                                                    <div class="row">
                                                        <s:iterator value="services" status="sv">
                                                            <s:if test="(#sv.count%4) == 1">
                                                                <div class="row">
                                                            </s:if>
                                                            <div class="col-xs-3">
                                                                <table class="s36 b25a">
                                                                    <tr>
                                                                        <td>
                                                                            <button type="button"
                                                                                    class="fa fa-chevron-circle-right s10 b3"
                                                                                    data-toggle="collapse"
                                                                                    data-target='#sv_<s:property value="serviceId" />'></button>
                                                                        </td>
                                                                        <td><input type="checkbox"
                                                                                   id='chk_service_<s:property value="serviceId" />'
                                                                                   onclick="onServiceCheck($(this))"
                                                                                   carrier-id='<s:property value="serviceId" />'/>
                                                                        </td>
                                                                        <td><s:property value="serviceName"/></td>
                                                                    </tr>
                                                                </table>
                                                                <s:if test="shipmentTypes!=null && shipmentTypes.size()>0">
                                                                    <s:set var="serviceIdVar" value="serviceId"/>
                                                                    <div id='sv_<s:property value="serviceId" />'
                                                                         class="collapse">
                                                                        <table class="s36 b25b">
                                                                            <s:iterator value="shipmentTypes"
                                                                                        status="st">
                                                                                <tr>
                                                                                    <td><span
                                                                                            style="margin-left: 20px;">&nbsp;</span><input
                                                                                            type="checkbox"
                                                                                            id='chk_shipment_type_<s:property value="shipmentTypeId" />'
                                                                                            onclick='onShipmentTypeCheck($(this))'
                                                                                            carrier-id='<s:property value="serviceIdVar" />'
                                                                                            service-id='<s:property value="shipmentTypeId" />'/>
                                                                                    </td>
                                                                                    <td><s:property
                                                                                            value="shipmentTypeName"/></td>
                                                                                </tr>
                                                                            </s:iterator>
                                                                        </table>
                                                                    </div>
                                                                </s:if>
                                                            </div>
                                                            <s:if test="(#sv.count%4) == 0">
                                                                </div>
                                                            </s:if>
                                                        </s:iterator>
                                                    </div>
                                                    <s:hidden id="export-data"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12">
                                        <div class="tab-content responsive" style="padding-top: 0px">
                                            <button class="btn s37" type="button" onclick="onSearchClick()">
                                                <xms:localization text="Go"/>
                                            </button>
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="tab-content responsive">
                                            <div class="tab-pane fade in active">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"><xms:localization text="Show"/> <s:select
                                                                id="pageSize" name="pageSize" list="pageSizes"
                                                                onchange="doSearch()"
                                                                cssStyle="height:22px; padding-top:1px;"/>
                                                            <xms:localization text="entries"/></th>
                                                    </tr>
                                                </table>
                                                <div id="customer-summary-result"
                                                     style="overflow: auto; min-width: 300px;">
                                                    <s:hidden id="rptTxnId" name="rptTxnId"/>
                                                    <s:hidden id="page" name="page"/>
                                                    <s:hidden id="orderField" name="orderField"/>
                                                    <s:hidden id="orderType" name="orderType"/>
                                                    <table class="table table-bordered mg0 table-hover"
                                                           id="customer_summary_table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Customer #"/></th>
                                                            <th><xms:localization text="Customer Name"/></th>
                                                            <th><xms:localization text="Sales Rep"/></th>
                                                            <th><xms:localization text="Customer Revenue"/></th>
                                                            <th><xms:localization text="Franchise Cost"/></th>
                                                            <s:if test="adminLevel==1 || adminLevel==2">
                                                                <th><xms:localization text="Carrier Cost"/></th>
                                                            </s:if>
                                                            <th><xms:localization text="Gross Margin"/></th>
                                                            <th><xms:localization text="Gross Margin %"/></th>
                                                            <th><xms:localization
                                                                    text="Customer Cost (Base Charge)"/></th>
                                                            <th><xms:localization
                                                                    text="Franchise Cost (Base Charge)"/></th>
                                                            <s:if test="adminLevel==1 || adminLevel==2">
                                                                <th><xms:localization
                                                                        text="Carrier Cost (Base Charge)"/></th>
                                                            </s:if>
                                                            <th><xms:localization text="Margin On Base Charge"/></th>
                                                            <th><xms:localization text="Total Shipments"/></th>
                                                            <th><xms:localization text="Avg.Margin Per AWB"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <s:if test="adminLevel==1 || adminLevel==2">
                                                                <td colspan="14"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </s:if>
                                                            <s:else>
                                                                <td colspan="12"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </s:else>
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
</div>

<script type="text/javascript">
    $(document).ready(function () {
        showDetailColumns(0);
    });

    var isSearched = false;

    function onChangeDateRange() {
        var period = $("#dateRange option:selected").val();
        if (period == '') {
            $("#frmCustomerSummarySearch td[group='data-range']").show();
            $("#startDate").val("");
            $("#endDate").val("");
        } else {
            $("#frmCustomerSummarySearch td[group='data-range']").hide();
            var arr = period.split(" - ");
            $("#startDate").val(arr[0]);
            $("#endDate").val(arr[1]);
        }
    }

    function changePage(page) {
        $("#page").val(page);
        doSearch();
    }

    function doSearch() {
        var data = $("#frmCustomerSummarySearch").serialize();
        data += "&orderField=" + $("#orderField").val();
        data += "&orderType=" + $("#orderType").val();
        data += "&rptTxnId=" + $("#rptTxnId").val();
        data += "&pageSize=" + $("#pageSize option:selected").val();
        var carrierList = "";
        $("#service_tree input:not([service-id])[type='checkbox'][carrier-id]").each(function () {
            if ($(this).is(":checked")) {
                carrierList += $(this).attr("carrier-id") + ",";
            }
        });
        carrierList = carrierList.substring(0, carrierList.length - 1);
        var serviceList = "";
        $("#service_tree input[type='checkbox'][service-id]").each(function () {
            if ($(this).is(":checked")) {
                serviceList += $(this).attr("service-id") + ",";
            }
        });
        serviceList = serviceList.substring(0, serviceList.length - 1);
        data += "&carrierList=" + carrierList;
        data += "&serviceList=" + serviceList;
        var dataExport = data;
        data += "&page=" + $("#page").val();
        loadingDialog.dialog("open");
        $.post("customer_summary_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customer-summary-result").html(res.content);
                $("#export-data").val(dataExport);
                isSearched = true;
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

    function onSearchClick() {
        $("#rptTxnId").val("");
        $("#orderField").val("");
        $("#orderType").val("");
        $("#page").val(1);
        doSearch();
    }

    function showDetailColumns(show) {
        if (show) {
            $("#customer-summary-result th[data-group='detail-group']").show();
            $("#customer-summary-result td[data-group='detail-group']").show();
        } else {
            $("#customer-summary-result th[data-group='detail-group']").hide();
            $("#customer-summary-result td[data-group='detail-group']").hide();
        }
    }

    function onShowDetailClick() {
        if ($("#chkShowDetail").is(':checked')) {
            showDetailColumns(true);
        } else {
            showDetailColumns(false);
        }
    }

    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to " />" + option);
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val();
        console.log(data);
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'print':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("customer_summary_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                    loadingDialog.dialog("close");
                }).fail(function () {
                    loadingDialog.dialog("close");
                    alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                    alertDialog.dialog("open");
                });
                break;
            case 'export':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("customer_summary_export.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                    failCallback: function (url) {
                        loadingDialog.dialog("close");
                    }
                });
                break;
        }

    }

    function onShipmentTypeCheck(obj) {
        var svId = $(obj).attr("carrier-id");
        var checked = false;
        $("#sv_" + svId + " input[type='checkbox']").each(function () {
            if ($(this).is(":checked")) {
                checked = true;
                return false;
            }
        });
        $("#chk_service_" + svId).prop("checked", checked);
    }

    function onServiceCheck(obj) {
        var svId = $(obj).attr("carrier-id");
        if ($(obj).is(":checked")) {
            $("#sv_" + svId + " input[type='checkbox']").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("#sv_" + svId + " input[type='checkbox']").each(function () {
                $(this).prop("checked", false);
            });
        }
    }


</script>