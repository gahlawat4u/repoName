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
        <li class="hidden"><a href="#"><xms:localization text="Activation"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Activation"/></li>
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
                                    <xms:localization text="Activation"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group" id="btn-export" style="display: none;">
                                            <input type="button" id="export-option" class="btn s37"
                                                   value="<xms:localization text="Option" />"
                                                   onclick="exportClick($('#selected-option').val())"/>
                                            <button type="button" class="btn s37 dropdown-toggle" data-toggle="dropdown"
                                                    aria-expanded="true">
                                                <span class="caret"></span>
                                            </button>
                                            <s:hidden id="selected-option"/>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                <li><a href="#" onclick="exportClick('option')"><xms:localization
                                                        text="Option"/></a></li>
                                                <li><a href="#" onclick="exportClick('html')"><xms:localization
                                                        text="Print"/></a></li>
                                                <s:if test="grandTotal != 0">
                                                    <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                            text="Export"/></a></li>
                                                </s:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12 ">
                                                <div class="form-group ">
                                                    <form id="activation-report-form">
                                                        <s:hidden name="orderField"/>
                                                        <s:hidden name="orderType"/>
                                                        <table class="s36 b24">
                                                            <tr>
                                                                <td><xms:localization text="Start"/></td>
                                                                <td>
                                                                    <div class="form-group input-group"
                                                                         style="margin-bottom: 0px;">
																		<span class="input-group-addon s31"> <i
                                                                                class="fa fa-calendar"></i>
																		</span> <input id="startDate" name="startDate"
                                                                                       class="form-control form_datetime"
                                                                                       type="text"
                                                                                       data-date-format="dd MM yyyy"
                                                                                       width="80"
                                                                                       onchange="$('#btn-export').hide()"
                                                                                       readonly="readonly">
                                                                    </div>
                                                                </td>
                                                                <td><xms:localization text="End"/></td>
                                                                <td>
                                                                    <div class="form-group input-group"
                                                                         style="margin-bottom: 0px;">
																		<span class="input-group-addon s31"> <i
                                                                                class="fa fa-calendar"></i>
																		</span> <input id="endDate" name="endDate"
                                                                                       class="form-control form_datetime"
                                                                                       type="text"
                                                                                       data-date-format="dd MM yyyy"
                                                                                       width="80"
                                                                                       onchange="$('#btn-export').hide()"
                                                                                       readonly="readonly">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <button class="btn s37" type="button"
                                                                            onclick="goClick()">
                                                                        &nbsp;
                                                                        <xms:localization text="Go"/>
                                                                        &nbsp;
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </form>
                                                    <s:form id="export-form">
                                                        <s:hidden name="startDate"/>
                                                        <s:hidden name="endDate"/>
                                                    </s:form>
                                                </div>
                                            </div>
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
                                                <div id="activation-report-result">
                                                    <s:hidden name="rptTxnId"/>
                                                    <table class="table table-bordered mg0 table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th sort-field="customer_code"><xms:localization
                                                                    text="Customer #"/> <i class="fa fa-sort"></i></th>
                                                            <th sort-field="customer_name"><xms:localization
                                                                    text="Customer Name"/> <i class="fa fa-sort"></i>
                                                            </th>
                                                            <th sort-field="sale_rep_name"><xms:localization
                                                                    text="Sales Rep"/> <i class="fa fa-sort"></i></th>
                                                            <th sort-field="submission_date"><xms:localization
                                                                    text="Submission Date"/> <i class="fa fa-sort"></i>
                                                            </th>
                                                            <th sort-field="activation_date"><xms:localization
                                                                    text="Activation Date"/> <i class="fa fa-sort"></i>
                                                            </th>
                                                            <th sort-field="days_to_activate"><xms:localization
                                                                    text="Days to Activate"/> <i class="fa fa-sort"></i>
                                                            </th>
                                                            <th sort-field="first_invoice"><xms:localization
                                                                    text="First Invoice"/> <i class="fa fa-sort"></i>
                                                            </th>
                                                            <th sort-field="margin_on_invoice"><xms:localization
                                                                    text="Margin On Invoice"/> <i
                                                                    class="fa fa-sort"></i></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="activationReport==null || activationReport.totalRecords==0">
                                                            <tr>
                                                                <td colspan="8"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="activationReport.records">
                                                                <tr>
                                                                    <td><s:property value="customerCode"/></td>
                                                                    <td><s:property value="customerName"/></td>
                                                                    <td><s:property value="saleRepName"/></td>
                                                                    <td><s:property value="submissionDate"/></td>
                                                                    <td><s:property value="activationDate"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="daysToActivate"/></td>
                                                                    <td><s:property value="firstInvoice"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="marginOnInvoice"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        <tr>
                                                            <th colspan="8"><xms:localization text="Total"/>: <s:if
                                                                    test="activationReport==null">
                                                                0
                                                            </s:if> <s:else>
                                                                <s:property value="activationReport.totalRecords"/>
                                                            </s:else> <xms:localization text="Activation(s)"/></th>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="activationReport.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="activationReport.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="activationReport.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="activationReport!=nul">
                                                                    <s:if test="activationReport.hasPrev()">
                                                                        <a href="javascript:doReport(<s:property value="%{activationReport.currentPage - 1}"/>)"
                                                                           class="paginate_button previous"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:if>
																	<span> <s:iterator
                                                                            value="activationReport.pageRange"
                                                                            status="count">
                                                                        <s:if test="%{activationReport.pageRange[#count.index] == activationReport.currentPage}">
                                                                            <a class="paginate_button current"><s:property
                                                                                    value="activationReport.currentPage"/></a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a class="paginate_button"
                                                                               href="javascript:doReport(<s:property/>);"><s:property/></a>
                                                                        </s:else>
                                                                    </s:iterator>
																	</span>
                                                                    <s:if test="activationReport.hasNext()">
                                                                        <a class="paginate_button next"
                                                                           href="javascript:doReport(<s:property value="%{activationReport.currentPage+1}"/>)"><xms:localization
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
<script type="text/javascript">
    function doReport(page) {
        var data = $("#activation-report-form").serialize();
        data += "&rptTxnId=" + $("#rptTxnId").val();
        data += "&page=" + page;
        data += "&pageSize=" + $("select#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("customer_activation_do_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#activation-report-result").html(res.content);
                $("#export-form input[name='startDate']").val($("#startDate").val());
                $("#export-form input[name='endDate']").val($("#endDate").val());
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

    function goClick() {
        $("#rptTxnId").val("");
        doReport(1);
    }
    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-form").serialize() + "&rptTxnId=" + $("#rptTxnId").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("customer_activation_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("customer_activation_export.ix", {
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