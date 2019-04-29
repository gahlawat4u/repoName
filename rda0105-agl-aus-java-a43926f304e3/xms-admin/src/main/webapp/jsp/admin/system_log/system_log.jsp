<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script type="text/javascript">
    var hasRecords = false;
</script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="System Logs"/></li>
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
                                    <xms:localization text="System Logs"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12 flr">
                                                <div class="form-group flr mgb">
                                                    <div class="btn-group" id="btn-export" style="z-index: 999">
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
                                                            <s:if test="grandTotal != 0">
                                                                <li><a href="#"
                                                                       onclick="exportClick('xls')"><xms:localization
                                                                        text="Export"/></a></li>
                                                            </s:if>
                                                        </ul>
                                                        <s:hidden id="export-data"/>
                                                    </div>
                                                </div>
                                                <div class="form-group flr mgb">
                                                    <table class="s36">
                                                        <tr>
                                                            <td><xms:localization text="Search Filters:"/></td>
                                                            <td><input type="text" class="form-control"
                                                                       name="userName"/></td>
                                                            <td><xms:localization text="Search Changes:"/></td>
                                                            <td><input type="text" class="form-control"
                                                                       name="changesMode"/>
                                                            <td><xms:localization text="Start:"/></td>
                                                            <td>
                                                                <div class="form-group input-group"
                                                                     style="margin-bottom: 0px;">
																	<span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i>
																	</span> <input class="form-control form_datetime"
                                                                                   type="text"
                                                                                   data-date-format="dd-MM-yyyy"
                                                                                   name="fromDate" readonly="true">
                                                                </div>
                                                            </td>
                                                            <td><xms:localization text="End:"/></td>
                                                            <td>
                                                                <div class="form-group input-group"
                                                                     style="margin-bottom: 0px;">
																	<span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i>
																	</span> <input class="form-control form_datetime"
                                                                                   type="text"
                                                                                   data-date-format="dd-MM-yyyy"
                                                                                   name="toDate" readonly="true">
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" type="button"
                                                                        onclick="doSearch()">Search
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <p>&nbsp;</p>
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42">
                                                            <div class="form-group fll mgb">
                                                                <table class="s36">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Show"/></td>
                                                                        <td><s:select name="pageSize" list="pageSizes"
                                                                                      onchange="onChangePageSize()"/></td>
                                                                        <td><xms:localization text="Entries"/></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </th>
                                                    </tr>
                                                </table>
                                                <div id="system-log-list">
                                                    <div style="overflow: auto">
                                                        <table class="table table-bordered table-hover mg0 "
                                                               id="datatable1">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Date"/></th>
                                                                <th><xms:localization text="Franchise"/></th>
                                                                <th><xms:localization text="User"/></th>
                                                                <th><xms:localization text="Action"/></th>
                                                                <th><xms:localization text="Table"/></th>
                                                                <th><xms:localization text="Filter"/></th>
                                                                <th><xms:localization text="Changes Mode"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="eventLogModels.records.size != 0">
                                                                <s:iterator value="eventLogModels.records" status="row">
                                                                    <tr>
                                                                        <td><s:property value="actionDate"/></td>
                                                                        <td><s:property value="userCode"/></td>
                                                                        <td><s:property value="userName"/></td>
                                                                        <td><s:property value="actionType"/></td>
                                                                        <td><s:property value="actionTable"/></td>
                                                                        <td><s:property value="filter"
                                                                                        escape="false"/></td>
                                                                        <td><s:property value="changesMode"
                                                                                        escape="false"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                            </s:if>
                                                            <s:else>
                                                                <tr>
                                                                    <td colspan="8"><xms:localization
                                                                            text="No data available..."/></td>
                                                                </tr>
                                                            </s:else>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="eventLogModels.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="eventLogModels.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="eventLogModels.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!eventLogModels.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{eventLogModels.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator value="eventLogModels.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{eventLogModels.pageRange[#count.index] == eventLogModels.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="eventLogModels.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!eventLogModels.hasNext()">
                                                                    <a class="paginate_button next"
                                                                       href="#"><xms:localization text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{eventLogModels.currentPage+1}"/>)"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:else>
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
<!--END CONTENT-->

<script type="text/javascript">
    $(document).ready(function () {
        //doSearch(pageSize, page);
    });
    var page = 1;
    var pageSize = $("select[name='pageSize'] option:selected").val();
    function doSearch() {
        var data = "page=" + page;

        var toDate = $("input[name=toDate]").val();
        var fromDate = $("input[name=fromDate]").val();
        var changesMode = $("input[name=changesMode]").val();
        var userName = $("input[name=userName]").val();

        data = data + "&pageSize=" + pageSize;
        data = data + "&toDate=" + toDate;
        data = data + "&fromDate=" + fromDate;
        data = data + "&changesMode=" + changesMode;
        data = data + "&userName=" + userName;
        $("#export-data").val(data);
        loadingDialog.dialog("open");
        $.post("system_log_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#system-log-list").html(res.content);
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

    function changePage(p) {
        page = p
        doSearch(pageSize, page);
    }

    function onChangePageSize() {
        pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, 1);
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
                $.post("system_log_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("system_log_export.ix", {
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