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
        <li class="hidden"><a href="#"><xms:localization text="Inactivity Report"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Inactivity Report"/></li>
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
                                    <xms:localization text="Inactivity Report"/>
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
                                                    <form id="inactivity-report-form">
                                                        <s:hidden name="orderField"/>
                                                        <s:hidden name="orderType"/>
                                                        <table class="s36 b24">
                                                            <tbody>
                                                            <tr>
                                                                <td><xms:localization text="Franchise"/></td>
                                                                <td><s:select id="franchiseCode" name="franchiseCode"
                                                                              list="franchises"
                                                                              listValue="code + ' - ' + name"
                                                                              listKey="code" headerValue="All"
                                                                              headerKey="All"
                                                                              cssClass="form-control"/></td>
                                                                <td><xms:localization text="Show Customers"/></td>
                                                                <td><select name="showCustomerOption"
                                                                            class="form-control">
                                                                    <option value="1"><xms:localization
                                                                            text="active in previous month"/></option>
                                                                    <option value="2"><xms:localization
                                                                            text="active in previous 3 months"/></option>
                                                                    <option value="3"><xms:localization
                                                                            text="active in previous 6 months"/></option>
                                                                    <option value="4"><xms:localization
                                                                            text="active in previous 9 months"/></option>
                                                                    <option value="5"><xms:localization
                                                                            text="active in previous year"/></option>
                                                                    <option value="6"><xms:localization
                                                                            text="active any time previously"/></option>
                                                                    <option value="7"><xms:localization
                                                                            text="no activity in the past"/></option>
                                                                </select></td>
                                                                <td><xms:localization text="Start Date"/></td>
                                                                <td>
                                                                    <div class="form-group input-group"
                                                                         style="margin-bottom: 0px;">
																			<span class="input-group-addon s31"> <i
                                                                                    class="fa fa-calendar"></i>
																			</span> <input name="startDate"
                                                                                           class="form-control form_datetime"
                                                                                           type="text"
                                                                                           data-date-format="dd MM yyyy"
                                                                                           width="80"
                                                                                           readonly="readonly">
                                                                    </div>
                                                                </td>
                                                                <td><xms:localization text="End Date"/></td>
                                                                <td>
                                                                    <div class="form-group input-group"
                                                                         style="margin-bottom: 0px;">
																			<span class="input-group-addon s31"> <i
                                                                                    class="fa fa-calendar"></i>
																			</span> <input name="endDate"
                                                                                           class="form-control form_datetime"
                                                                                           type="text"
                                                                                           data-date-format="dd MM yyyy"
                                                                                           width="80"
                                                                                           readonly="readonly">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <button class="btn s37" type="button"
                                                                            onclick="doReport(1)">
                                                                        &nbsp;
                                                                        <xms:localization text="Go"/>
                                                                        &nbsp;
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </form>
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
                                                <div id="customer-inactivity-report">
                                                    <table class="table table-bordered mg0 table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th sort-field="customer_code"><xms:localization
                                                                    text="Customer #"/> <i class="fa fa-sort"></i></th>
                                                            <th sort-field="customer_name"><xms:localization
                                                                    text="Customer Name"/> <i class="fa fa-sort"></i>
                                                            </th>
                                                            <th sort-field="email"><xms:localization
                                                                    text="Customer Email"/> <i class="fa fa-sort"></i>
                                                            </th>
                                                            <th sort-field="sale_rep_name"><xms:localization
                                                                    text="Sales Rep"/> <i class="fa fa-sort"></i></th>
                                                            <th sort-field="submission_date"><xms:localization
                                                                    text="Submission Date"/> <i class="fa fa-sort"></i>
                                                            </th>
                                                            <th sort-field="activation_date"><xms:localization
                                                                    text="Activation Date"/> <i class="fa fa-sort"></i>
                                                            </th>
                                                            <th sort-field="last_invoice_date"><xms:localization
                                                                    text="Last Invoice Date"/> <i
                                                                    class="fa fa-sort"></i></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="inactivityReport==null || inactivityReport.totalRecords==0">
                                                            <tr>
                                                                <td colspan="7"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="inactivityReport.records">
                                                                <tr>
                                                                    <td><s:property value="customerCode"/></td>
                                                                    <td><s:property value="customerName"/></td>
                                                                    <td><s:property value="email"/></td>
                                                                    <td><s:property value="saleRepName"/></td>
                                                                    <td><s:property value="submissionDate"/></td>
                                                                    <td><s:property value="activationDate"/></td>
                                                                    <td><s:property value="lastInvoiceDate"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        <tr>
                                                            <th colspan="7"><xms:localization text="Total:"/> <s:if
                                                                    test="inactivityReport==null">
                                                                0
                                                            </s:if> <s:else>
                                                                <s:property value="inactivityReport.totalRecords"/>
                                                            </s:else> <xms:localization
                                                                    text="Inactive Customer(s)"/></th>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="inactivityReport.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="inactivityReport.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="inactivityReport.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="inactivityReport!=null">
                                                                    <s:if test="inactivityReport.hasPrev()">
                                                                        <a href="javascript:doReport(<s:property value="%{inactivityReport.currentPage - 1}"/>)"
                                                                           class="paginate_button previous"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:if>
																	<span> <s:iterator
                                                                            value="inactivityReport.pageRange"
                                                                            status="count">
                                                                        <s:if test="%{inactivityReport.pageRange[#count.index] == inactivityReport.currentPage}">
                                                                            <a class="paginate_button current"><s:property
                                                                                    value="inactivityReport.currentPage"/></a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a class="paginate_button"
                                                                               href="javascript:doReport(<s:property/>);"><s:property/></a>
                                                                        </s:else>
                                                                    </s:iterator>
																	</span>
                                                                    <s:if test="inactivityReport.hasNext()">
                                                                        <a class="paginate_button next"
                                                                           href="javascript:doReport(<s:property value="%{inactivityReport.currentPage+1}"/>)"><xms:localization
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
    var data = "";
    function doReport(page) {
        data = $("#inactivity-report-form").serialize();
        data += "&page=" + page;
        data += "&pageSize=" + $("select#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("customer_inactivity_do_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customer-inactivity-report").html(res.content);
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
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("customer_inactivity_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("customer_inactivity_export.ix", {
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