<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    var actionPrint = "";
    var actionExport = "";
    var hasRecords = false;
</script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="System Stats"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="System Stats"/></li>
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
                                    <xms:localization text="System Stats"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group" id="btn-export" style="z-index: 999">
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
                                            <s:hidden id="export-data"/>
                                        </div>
                                    </div>
                                    <div class="form-group flr mgb">
                                        <table class="s36">
                                            <tr>
                                                <td><xms:localization text="Select an option: "/></td>
                                                <td><s:select id="system_stat_option" list="searchOptions" listKey="key"
                                                              listValue="value" cssClass="form-control"
                                                              onchange="searchOptionChange()"/></td>
                                                <td>
                                                    <button class="btn s37" type="button" onclick="search()">
                                                        <xms:localization text="Go"/>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div data-option="login_log" class="col-lg-12" style="display: none;">
                                    <div class="form-group flr mgb">
                                        <form id="login_log_filter_form">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Start:"/></td>
                                                    <td>
                                                        <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           type="text" data-date-format="dd-MM-yyyy"
                                                                           name="startDate" readonly="readonly"/>
                                                        </div>
                                                    </td>
                                                    <td><xms:localization text="End:"/></td>
                                                    <td>
                                                        <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           type="text" data-date-format="dd-MM-yyyy"
                                                                           name="endDate" readonly="readonly"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                    </div>
                                </div>
                                <div data-option="error_login_log" class="col-lg-12" style="display: none;">
                                    <div class="form-group flr mgb">
                                        <form id="error_login_log_filter_form">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Start:"/></td>
                                                    <td>
                                                        <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           type="text" data-date-format="dd-MM-yyyy"
                                                                           name="startDate" readonly="readonly"/>
                                                        </div>
                                                    </td>
                                                    <td><xms:localization text="End:"/></td>
                                                    <td>
                                                        <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           type="text" data-date-format="dd-MM-yyyy"
                                                                           name="endDate" readonly="readonly"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                    </div>
                                </div>
                                <div data-option="webship_log" class="col-lg-12" style="display: none;">
                                    <div class="form-group flr mgb">
                                        <form id="webship_log_filter_form">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Airway Bill Number:"/></td>
                                                    <td><input type="text" name="airbillNumber" class="form-control"/>
                                                    </td>
                                                    <td><xms:localization text="Action:"/></td>
                                                    <td><s:select name="action" list="actions" headerKey=""
                                                                  headerValue="" cssClass="form-control"/></td>
                                                    <td><xms:localization text="Start:"/></td>
                                                    <td>
                                                        <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           type="text" data-date-format="dd-MM-yyyy"
                                                                           name="startDate" readonly="readonly"/>
                                                        </div>
                                                    </td>
                                                    <td><xms:localization text="End:"/></td>
                                                    <td>
                                                        <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           type="text" data-date-format="dd-MM-yyyy"
                                                                           name="endDate" readonly="readonly"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                    </div>
                                </div>
                                <div data-option="adjustment_log" class="col-lg-12" style="display: none;">
                                    <div class="form-group flr mgb">
                                        <form id="adjustment_log_filter_form">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Airbill#:"/></td>
                                                    <td><input type="text" name="airbillNumber" class="form-control"/>
                                                    </td>
                                                    <td><xms:localization text="Request Type:"/></td>
                                                    <td><s:select name="requestType" list="requestTypes" headerKey="-1"
                                                                  headerValue="All" cssClass="form-control"/></td>
                                                    <td><xms:localization text="Status:"/></td>
                                                    <td><s:select name="status" list="statusList" listKey="key"
                                                                  listValue="value" headerKey="-1" headerValue="All"
                                                                  cssClass="form-control"/></td>
                                                    <td><xms:localization text="Start:"/></td>
                                                    <td>
                                                        <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           type="text" data-date-format="dd-MM-yyyy"
                                                                           name="startDate" readonly="readonly"/>
                                                        </div>
                                                    </td>
                                                    <td><xms:localization text="End:"/></td>
                                                    <td>
                                                        <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           type="text" data-date-format="dd-MM-yyyy"
                                                                           name="endDate" readonly="readonly"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <s:iterator value="searchOptions">
                                                <div class="col-lg-12" data-option="<s:property value="key"/>"
                                                     id='<s:property value="key"/>_result' style="display: none">
                                                    <table class="table mg0">
                                                        <tbody>
                                                        <tr>
                                                            <th class="s42">
                                                                <table class="s36">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Show"/></td>
                                                                        <td><s:select name="pageSize"
                                                                                      cssClass="form-control"
                                                                                      cssStyle="height: 22px; padding-top: 1px;"
                                                                                      list="pageSizes"
                                                                                      onchange="changeRecentImportPageSize()"/></td>
                                                                        <td><xms:localization text="Entries"/></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </th>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <table class="table table-bordered mg0">
                                                        <tbody>
                                                        <tr>
                                                            <td><xms:localization text="No data available..."/></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </s:iterator>
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
        searchOptionChange();
        search();
    });

    function searchOptionChange() {
        var option = $("select#system_stat_option option:selected").val();
        $("div[data-option]").each(function () {
            if ($(this).data("option") == option) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    }

    function search() {
        var option = $("select#system_stat_option option:selected").val();
        $("#" + option + "_page").val("");
        $("#" + option + "_order_type").val("");
        $("#" + option + "_order_field").val("");
        callAction(option);
    }

    function callAction(option) {
        var action = "system_stats_" + option + ".ix?reqType=json";
        var formId = "#" + option + "_form";
        var extraId = "#" + option + "_filter_form";
        var resultId = "#" + option + "_result";
        loadingDialog.dialog("open");
        var data = $(formId).serialize();
        data += "&" + $(extraId).serialize();
        $("#export-data").val(data);
        $.post(action, data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $(resultId).html(res.content);
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
                if (actionPrint == "") {
                    alertDialog.html("<xms:localization text="This fuction is not allowed to print." />");
                    alertDialog.dialog("open");
                    return false;
                }
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post(actionPrint + "?reqType=json", data, function (res) {
                    loadingDialog.dialog("close");
                    if (res.errorCode == "SUCCESS") {
                        var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                        win.document.write(res.content);
                    } else {
                        alertDialog.html(res.errorMsg);
                        alertDialog.dialog("open");
                    }
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                if (actionExport == "") {
                    alertDialog.html("<xms:localization text="This fuction is not allowed to export." />");
                    alertDialog.dialog("open");
                    return false;
                }
                loadingDialog.dialog("open");
                $.fileDownload(actionExport + "?reqType=json", {
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                    failCallback: function (res, url) {
                        try {
                            var data = JSON.parse(res);
                            if (data.errorCode != "") {
                                alertDialog.html($.parseHTML(data.errorMsg));
                            } else {
                                alertDialog.html("<xms:localization text="There was a problem generating your report, please try again." />");
                            }
                        } catch (e) {
                            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                        }
                        alertDialog.dialog("open");
                        loadingDialog.dialog("close");
                    }
                });
        }

    }


</script>