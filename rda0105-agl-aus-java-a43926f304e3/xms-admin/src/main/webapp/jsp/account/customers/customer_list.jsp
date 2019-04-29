<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Customers"/>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active"><xms:localization text="Customers List"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<script type="text/javascript">
    var hasRecords = false;
</script>
<!--BEGIN CONTENT-->
<div class="page-content">
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
                                <xms:localization text="Customers List"/>
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
                        <div class="form-group">
                            <div class="portlet-body">
                                <div class="portlet-body">
                                    <form id="search-form">
                                        <table class="s36">
                                            <tbody>
                                            <tr>
                                                <td><xms:localization text="Franchise"/> :</td>
                                                <td><s:select id="franchiseCode" name="franchiseCode" list="franchises"
                                                              listValue="code + ' - ' + name" listKey="code"
                                                              headerValue="All" headerKey="All" cssClass="form-control"
                                                              onchange="getSaleRepsList()"/></td>
                                                <td>&nbsp;</td>
                                                <td><xms:localization text="Sale Rep"/> :</td>
                                                <td id="sale-rep-list-result"><s:select name="salesRepId"
                                                                                        list="saleReps"
                                                                                        listValue="displayName"
                                                                                        listKey="userId"
                                                                                        headerValue="All" headerKey=""
                                                                                        cssClass="form-control"/></td>
                                                <td>&nbsp;</td>
                                                <td>
                                                    <button class="btn s37" type="button" onclick="onGoClick()">
                                                        <xms:localization text="Go"/>
                                                    </button>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <s:hidden id="customer_page" name="page"/>
                                        <s:hidden id="customer_order_type" name="orderType"/>
                                        <s:hidden id="customer_order_field" name="orderField"/>
                                    </form>
                                    <s:form id="export-form">
                                        <s:hidden name="franchiseCode"/>
                                        <s:hidden name="salesRepId"/>
                                    </s:form>
                                </div>
                                <table class="table mg0">
                                    <tr>
                                        <th class="s42"><xms:localization text="Show"/> <select id="customer_page_size"
                                                                                                name="pageSize"
                                                                                                style="height: 22px; padding-top: 1px;"
                                                                                                onchange="onChangePageSize()">
                                            <option>25</option>
                                            <option>50</option>
                                            <option>100</option>
                                        </select> <xms:localization text="entries"/></th>
                                    </tr>
                                </table>
                                <div id="customers-list-table">
                                    <table class="table table-hover table-bordered mg0" id="customer_list_table">
                                        <thead>
                                        <tr>
                                            <th><xms:localization text="Customer #"/></th>
                                            <th><xms:localization text="Customer Name"/></th>
                                            <th class="text-right"><xms:localization text="MTD Rev."/></th>
                                            <th class="text-right"><xms:localization text="YTD Rev."/></th>
                                            <th class="text-right"><xms:localization
                                                    text="Last Shipment Date/Time"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td colspan="5"><xms:localization text="No data available..."/></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="dataTables_paginate">
                                        <a class="paginate_button previous disabled"><xms:localization
                                                text="Previous"/></a> <a class="paginate_button next"
                                                                         href="#"><xms:localization text="Next"/></a>
                                    </div>
                                </div>
                                <br/> <br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function doSearch() {
        var data = $("#search-form").serialize();
        data += "&pageSize=" + $("#customer_page_size").val();
        loadingDialog.dialog("open");
        $.post("customer_list_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customers-list-table").html(res.content);
                $("#export-form input[name='franchiseCode']").val($("#franchiseCode").val());
                $("#export-form input[name='salesRepId']").val($("#salesRepId").val());
                $("#btn-export").show();
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

    function changePage(page) {
        $("#customer_page").val(page);
        doSearch();
    }

    function onChangePageSize() {
        doSearch();
    }

    function onGoClick() {
        var franCode = $("select#franchiseCode option:selected").val();
        var saleRepId = $("select[name='salesRepId'] option:selected").val();
        var canSearch = franCode == "All" && saleRepId == "";
        if (canSearch) {
            alertDialog.html("Please select a Franchise or Sales rep to continue");
            alertDialog.dialog("open");
        } else {
            $("#customer_page").val(1);
            doSearch();
        }
    }

    function getSaleRepsList() {
        $("#btn-export").hide();
        var data = $("select#franchiseCode option:selected").val();
        loadingDialog.dialog("open");
        $.post("customer_list_sale_rep_list.ix?reqType=json", "franchiseCode=" + data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#sale-rep-list-result").html(res.content);
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
        var data = $("#export-form").serialize();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("customer_list_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("customer_list_export.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    }
                });
                break;
        }
    }


</script>