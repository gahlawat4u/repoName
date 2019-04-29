<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script type="text/javascript">
    var hasRecords = false;
</script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="dashboard.html"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Airbill Import Error Logs"/></li>
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
                                    <xms:localization text="Airbill Import Error Logs"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group" id="btn-export">
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
                                                <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                        text="Export"/></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="form-group flr mgb">
                                        <form id="frmSearchAirbillImportErrors">
                                            <table class="s36">
                                                <tbody>
                                                <tr>
                                                    <td><span id="showLast90Days"><xms:localization
                                                            text="Limited to last 90 days"/></span>
                                                        <table id="showCustomDate" style="display: none;">
                                                            <tr>
                                                                <td><xms:localization text="Date Range"/></td>
                                                                <td>
                                                                    <div class="form-group input-group mg0">
																			<span class="input-group-addon s31"> <i
                                                                                    class="fa fa-calendar"></i>
																			</span> <input name="fromDate"
                                                                                           class="form-control form_datetime"
                                                                                           type="text"
                                                                                           data-date-format="dd MM yyyy"
                                                                                           placeholder="Start"/>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <div class="form-group input-group mg0">
																			<span class="input-group-addon s31"> <i
                                                                                    class="fa fa-calendar"></i>
																			</span> <input name="toDate"
                                                                                           class="form-control form_datetime"
                                                                                           type="text"
                                                                                           data-date-format="dd MM yyyy"
                                                                                           placeholder="End"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button" onclick="onSearchClick()">
                                                            <xms:localization text="Search"/>
                                                        </button>
                                                    </td>
                                                    <td><input id="useCustomDate" type="checkbox" name="useCustomDate"
                                                               onclick="changeSearchType()" value="1"/></td>
                                                    <td><xms:localization text="Use Custom Dates"/></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <s:hidden id="orderField" name="orderField"/>
                                            <s:hidden id="orderType" name="orderType"/>
                                            <s:hidden id="page" name="page"/>
                                        </form>
                                        <s:hidden id="export-data"/>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="portlet-body b12 b11">
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select list="pageSizes" name="pageSize"
                                                                                  cssClass="form-control"
                                                                                  cssStyle="height: 22px; padding-top: 1px;"
                                                                                  id="pageSize"
                                                                                  onchange="doSearch()"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="airbill_import_error_log_result">
                                                    <table class="table table-bordered mg0"
                                                           id="airbill_import_error_log_table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Airbill #"/></th>
                                                            <th><xms:localization text="Import Date"/></th>
                                                            <th><xms:localization text="Notes"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="duplicateAirbills==null || duplicateAirbills.totalRecords==0">
                                                            <tr>
                                                                <td colspan='3'><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="duplicateAirbills.records">
                                                                <tr>
                                                                    <td><s:property value="airbillNumber"/></td>
                                                                    <td><s:property value="importDate"/></td>
                                                                    <td><s:property value="note"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="duplicateAirbills.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="duplicateAirbills.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="duplicateAirbills.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="duplicateAirbills!=null">
                                                                    <s:if test="duplicateAirbills.hasPrev()">
                                                                        <a href="javascript:changePage(<s:property value="%{duplicateAirbills.currentPage - 1}"/>)"
                                                                           class="paginate_button previous"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:if>
																	<span> <s:iterator
                                                                            value="duplicateAirbills.pageRange"
                                                                            status="count">
                                                                        <s:if test="%{duplicateAirbills.pageRange[#count.index] == duplicateAirbills.currentPage}">
                                                                            <a class="paginate_button current"><s:property
                                                                                    value="duplicateAirbills.currentPage"/></a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a class="paginate_button"
                                                                               href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                        </s:else>
                                                                    </s:iterator>
																	</span>
                                                                    <s:if test="duplicateAirbills.hasNext()">
                                                                        <a class="paginate_button next"
                                                                           href="javascript:changePage(<s:property value="%{duplicateAirbills.currentPage+1}"/>)"><xms:localization
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
    var fieldList = ["airbill_number", "import_date", ""];
    $(document).ready(function () {
        changeSearchType();
        $("#airbill_import_error_log_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
    });

    function changeSearchType() {
        var showType = $("#useCustomDate").is(":checked");
        if (showType) {
            $("#showLast90Days").hide();
            $("#showCustomDate").show();
        } else {
            $("#showLast90Days").show();
            $("#showCustomDate").hide();
        }
    }

    function doSearch() {
        var data = $("#frmSearchAirbillImportErrors").serialize();
        $("#export-data").val(data);
        data += "&pageSize=" + $("#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("airbill_import_error_logs_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#airbill_import_error_log_result").html(res.content);
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

    function onSearchClick() {
        $("#page").val(1);
        $("#orderField").val("airbill_number");
        $("#orderType").val(0);
        doSearch();
    }

    function changePage(page) {
        $("#page").val(page);
        doSearch();
    }

    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val();
        if (data == "") {
            console.log("data: " + data);
            data = $("#frmSearchAirbillImportErrors").serialize();
            console.log("data: " + data);
        }
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("airbill_import_error_logs_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("airbill_import_error_logs_export.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                    failCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                });
                break;
        }
    }


</script>