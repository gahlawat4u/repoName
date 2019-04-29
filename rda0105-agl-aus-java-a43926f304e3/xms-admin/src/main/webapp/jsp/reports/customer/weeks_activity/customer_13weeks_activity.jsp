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
        <li class="hidden"><a href="#"><xms:localization text="13 Week Activity Report"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="13 Week Activity Report"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<script type="text/javascript">
    var hasRecords = false;
</script>
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
                                    <xms:localization text="13 Week Activity Report"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <form id="13weeks-activity-report-form">
                                            <s:hidden id="orderField" name="orderField"/>
                                            <s:hidden id="orderType" name="orderType"/>
                                            <table class="s36">
                                                <tbody>
                                                <tr>
                                                    <td><xms:localization text="Franchise"/></td>
                                                    <td><s:select id="franchiseCode" name="franchiseCode"
                                                                  list="franchises" listValue="code + ' - ' + name"
                                                                  listKey="code" headerValue="All" headerKey="All"
                                                                  cssClass="form-control"/></td>
                                                    <td>
                                                        <button class="btn s37" type="button" onclick="doReport(1)">
                                                            &nbsp;
                                                            <xms:localization text="Go"/>
                                                            &nbsp;
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <div class="btn-group" id="btn-export" style="display: none;">
                                                            <input type="button" id="export-option" class="btn s37"
                                                                   value="<xms:localization text="Option" />"
                                                                   onclick="exportClick($('#selected-option').val())"/>
                                                            <button type="button" class="btn s37 dropdown-toggle"
                                                                    data-toggle="dropdown" aria-expanded="true">
                                                                <span class="caret"></span>
                                                            </button>
                                                            <s:hidden id="selected-option"/>
                                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                                <li><a href="#"
                                                                       onclick="exportClick('option')"><xms:localization
                                                                        text="Option"/></a></li>
                                                                <li><a href="#"
                                                                       onclick="exportClick('html')"><xms:localization
                                                                        text="Print"/></a></li>
                                                                <li><a href="#"
                                                                       onclick="exportClick('xls')"><xms:localization
                                                                        text="Export"/></a></li>
                                                            </ul>
                                                        </div>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </form>
                                        <s:hidden id="export-data"/>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"><xms:localization text="Show"/> <s:select
                                                                id="pageSize" name="pageSize" list="pageSizes"
                                                                onchange="doReport(1)"
                                                                cssStyle="height:22px; padding-top:1px;"/>
                                                            <xms:localization text="entries"/></th>
                                                    </tr>
                                                </table>
                                                <div id="13weeks-activity-report">
                                                    <table id="13-weeks-activity-table"
                                                           class="table table-bordered mg0 table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Customer #"/></th>
                                                            <th><xms:localization text="Customer Name"/></th>
                                                            <th><xms:localization text="Revenue"/></th>
                                                            <th><xms:localization text="Franchise Cost"/></th>
                                                            <th><xms:localization text="Carrier Cost"/></th>
                                                            <th><xms:localization text="Gross Margin"/></th>
                                                            <th><xms:localization text="% Gross Margin"/></th>
                                                            <th><xms:localization text="#Docs"/></th>
                                                            <th><xms:localization text="Doc Revenue"/></th>
                                                            <th><xms:localization text="#Non Docs"/></th>
                                                            <th><xms:localization text="Non Doc Revenue"/></th>
                                                            <th><xms:localization text="Total Airbills"/></th>
                                                            <th><xms:localization text="Gross Mgn Per AWB"/></th>
                                                            <th><xms:localization text="% Gross Mgn Per AWB"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="weekActivityReport==null || weekActivityReport.totalRecords==0">
                                                            <tr>
                                                                <td colspan="14"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="weekActivityReport.records">
                                                                <tr>
                                                                    <td><s:property value="customerCode"/></td>
                                                                    <td><s:property value="customerName"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="revenue"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="franchiseCost"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="carrierCost"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="grossMargin"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="grossMarginPct"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="documentShipmentCount"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="documentRevenue"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="packageShipmentCount"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="packageRevenue"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="totalAirbills"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="grossMarginPerAwb"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="grossMarginPerAwbPct"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="weekActivityReport.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="weekActivityReport.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="weekActivityReport.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="weekActivityReport!=null">
                                                                    <s:if test="weekActivityReport.hasPrev()">
                                                                        <a href="javascript:doReport(<s:property value="%{weekActivityReport.currentPage - 1}"/>)"
                                                                           class="paginate_button previous"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:if>
																	<span> <s:iterator
                                                                            value="weekActivityReport.pageRange"
                                                                            status="count">
                                                                        <s:if test="%{weekActivityReport.pageRange[#count.index] == weekActivityReport.currentPage}">
                                                                            <a class="paginate_button current"><s:property
                                                                                    value="weekActivityReport.currentPage"/></a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a class="paginate_button"
                                                                               href="javascript:doReport(<s:property/>);"><s:property/></a>
                                                                        </s:else>
                                                                    </s:iterator>
																	</span>
                                                                    <s:if test="weekActivityReport.hasNext()">
                                                                        <a class="paginate_button next"
                                                                           href="javascript:doReport(<s:property value="%{weekActivityReport.currentPage+1}"/>)"><xms:localization
                                                                                text="Next"/></a>
                                                                    </s:if>
                                                                </s:if>
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
        </div>
    </div>
</div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    function doReport(page) {
        var data = $("#13weeks-activity-report-form").serialize();
        data += "&page=" + page;
        data += "&pageSize=" + $("select#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("customer_13weeks_activity_do_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#13weeks-activity-report").html(res.content);
                $("#export-data").val(data);
                $("#btn-export").show();
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
    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("customer_13weeks_activity_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("customer_13weeks_activity_export.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    }
                });
        }

    }


</script>