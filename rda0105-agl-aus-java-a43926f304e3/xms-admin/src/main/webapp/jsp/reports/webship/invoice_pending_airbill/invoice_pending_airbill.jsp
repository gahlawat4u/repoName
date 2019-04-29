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
        <li><xms:localization text="Reports"/> &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li><xms:localization text="Web Freight"/> &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Invoice Pending Airbills"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<script type="text/javascript">
    var hasRecords = false;
</script>
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
                                    <xms:localization text="Invoice Pending Airbills"/>
                                </div>
                                <p style="clear: both;">
                                    <xms:localization
                                            text="Users can search, print and export the airbills that ship within the given date range which have not been invoiced to customers in this report."/>
                                </p>

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
                                                <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                        text="Export"/></a></li>
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
                                                    <form id="report_form">
                                                        <table class="s36 b24">
                                                            <tr>
                                                                <td><xms:localization text="Franchise"/></td>
                                                                <td><s:i18n_select id="franchiseCode"
                                                                                   name="franchiseCode"
                                                                                   cssClass="form-control"
                                                                                   list="franchises" listKey="code"
                                                                                   listValue="code + ' - ' + name"
                                                                                   onchange="onFranchiseChange()"
                                                                                   headerKey="All" headerValue="All"
                                                                                   i18nitem="no"/></td>
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
                                                                                       width="80" readonly="readonly">
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
                                                                                       width="80" readonly="readonly">
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <button class="btn s37" type="button"
                                                                            onclick="onGoClick()">
                                                                        &nbsp;
                                                                        <xms:localization text="Go"/>
                                                                        &nbsp;
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </form>
                                                    <s:hidden id="export-data"/>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42"><xms:localization text="Show"/> <s:select
                                                                id="pageSize" name="pageSize" list="pageSizes"
                                                                onchange="onChangePageSize()"
                                                                cssStyle="height:22px; padding-top:1px;"/>
                                                            <xms:localization text="entries"/></th>
                                                    </tr>
                                                </table>
                                                <div id="invoice_pending_airbill_report">
                                                    <s:hidden id="page" name="page"/>
                                                    <s:hidden id="orderField" name="orderField"/>
                                                    <s:hidden id="orderType" name="orderType"/>
                                                    <table class="table table-bordered mg0 table-hover"
                                                           id="invoice_pending_airbill_report_table">
                                                        <thead>
                                                        <tr>
                                                            <th>&nbsp;</th>
                                                            <th><xms:localization text="Customer #"/></th>
                                                            <th><xms:localization text="Customer Name"/></th>
                                                            <th><xms:localization text="Airbill #"/></th>
                                                            <th><xms:localization text="Carrier Name"/></th>
                                                            <th><xms:localization text="Service"/></th>
                                                            <th><xms:localization text="Weight"/></th>
                                                            <th><xms:localization text="Pieces"/></th>
                                                            <th><xms:localization text="Create Date"/></th>
                                                            <th><xms:localization text="Ship Date"/></th>
                                                            <th><xms:localization text="Destination"/></th>
                                                            <th><xms:localization text="Dest.Country"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr id="search-filter">
                                                            <td></td>
                                                            <td><s:textfield name="customerCode" cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="customerName" cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="airbillNumber"
                                                                             cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="carrierName" cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="service" cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="weight" cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="pieces" cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="createDate"
                                                                             cssClass="form-control form_datetime"
                                                                             onchange="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="shipDate"
                                                                             cssClass="form-control form_datetime"
                                                                             onchange="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="destination" cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="destinationCountry"
                                                                             cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                        </tr>
                                                        <s:if test="report==null || report.totalRecords==0">
                                                            <tr>
                                                                <td colspan='12'><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="report.records">
                                                                <tr>
                                                                    <td><s:checkbox name="deletedAirbills"
                                                                                    smid="%{shipmentId}"
                                                                                    onclick="onCheckBoxSelect()"/></td>
                                                                    <td><s:property value="customerCode"/></td>
                                                                    <td><s:property value="customerName"/></td>
                                                                    <td><s:property value="airbillNumber"/></td>
                                                                    <td><s:property value="carrierName"/></td>
                                                                    <td><s:property value="serviceName"/></td>
                                                                    <td align="right"><s:if test="weight!=null">
                                                                        <s:property value="weight"/>
                                                                        <s:property value="weightUnit"/>
                                                                    </s:if></td>
                                                                    <td align="right"><s:property
                                                                            value="noOfPieces"/></td>
                                                                    <td><s:property value="createDate"/></td>
                                                                    <td><s:property value="shipmentDate"/></td>
                                                                    <td><s:if test="city!=null && city!=''">
                                                                        <s:property value="city"/>, <s:property
                                                                            value="postalCode"/>
                                                                    </s:if></td>
                                                                    <td><s:property value="countryName"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="report.startRecord"/> <xms:localization
                                                                        text="to"/> <s:property
                                                                        value="report.endRecord"/> <xms:localization
                                                                        text="of"/> <s:property
                                                                        value="report.totalRecords"/> <xms:localization
                                                                        text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="report!=null">
                                                                    <s:if test="report.hasPrev()">
                                                                        <a href="javascript:changePage(<s:property value="%{report.currentPage - 1}"/>)"
                                                                           class="paginate_button previous"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:if>
																	<span> <s:iterator value="report.pageRange"
                                                                                       status="count">
                                                                        <s:if test="%{report.pageRange[#count.index] == report.currentPage}">
                                                                            <a class="paginate_button current"><s:property
                                                                                    value="report.currentPage"/></a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a class="paginate_button"
                                                                               href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                        </s:else>
                                                                    </s:iterator>
																	</span>
                                                                    <s:if test="report.hasNext()">
                                                                        <a class="paginate_button next"
                                                                           href="javascript:changePage(<s:property value="%{report.currentPage+1}"/>)"><xms:localization
                                                                                text="Next"/></a>
                                                                    </s:if>
                                                                </s:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12"
                                                     style="padding-left: 0px !important; padding-right: 0px !important;">
                                                    <button style="float: right; margin-top: 10px;"
                                                            id="btnDeleteAllShipments" class="btn s37" type="button"
                                                            onclick="onDeleteShipments()" disabled="disabled">
                                                        <xms:localization text="Delete Selected Airbill"/>
                                                    </button>
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
<div id="confirm-dialog" title=''></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    function doReport() {
        var data = $("#report_form").serialize();
        data += "&orderField=" + $("#orderField").val();
        data += "&orderType=" + $("#orderType").val();
        var dataExport = data;
        data += "&page=" + $("#page").val();
        data += "&pageSize=" + $("#pageSize option:selected").val();
        data += "&" + $("#search-filter input").serialize();
        loadingDialog.dialog("open");
        $.post("invoice_pending_airbill_do_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#invoice_pending_airbill_report").html(res.content);
                $("#btn-export").show();
                $("#export-data").val(dataExport);
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

    function onGoClick() {
        $("#orderField").val("");
        $("#orderType").val("");
        $("#page").val(1);
        doReport();
    }

    function changePage(page) {
        $("#page").val(page);
        doReport();
    }

    function onChangePageSize() {
        doReport();
    }

    function onDeleteShipments() {
        var deletedShipments = "";
        var shipmentCount = 0;
        $("input[name='deletedAirbills']:checked").each(function () {
            shipmentCount++;
            deletedShipments += "&deletedAirbills=" + $(this).attr("smid");
        });
        if (shipmentCount == 0) {
            return;
        }
        deletedShipments = deletedShipments.substring(1, deletedShipments.length);
        $("#confirm-dialog").html("Are you sure want to delete total " + shipmentCount + " shipment(s)?");
        $("#confirm-dialog").dialog({
            resizable: false,
            modal: true,
            title: "Delete shipment(s)",
            height: 200,
            width: 320,
            buttons: {
                "Yes": function () {
                    $(this).dialog('close');
                    $.post("invoice_pending_airbill_delete_shipments.ix?reqType=json", deletedShipments, function (res) {
                        if (res.errorCode == "SUCCESS") {
                            messageDialog.html('<xms:localization text="Delete successful." />');
                            messageDialog.dialog("open");
                            doReport();
                        } else {
                            alertDialog.html(res.errorMsg);
                            alertDialog.dialog("open");
                        }
                    }).fail(function () {
                        alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                        alertDialog.dialog("open");
                    });

                },
                "No": function () {
                    $(this).dialog('close');
                }
            }
        });
    }

    function onCheckBoxSelect() {
        if ($("input[name='deletedAirbills']:checked").length > 0) {
            $("#btnDeleteAllShipments").removeAttr("disabled");
        } else {
            $("#btnDeleteAllShipments").attr("disabled", "disabled");
        }
    }

    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val() + "&rptTxnId=" + $("#rptTxnId").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("invoice_pending_airbill_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("invoice_pending_airbill_export.ix", {
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
        }
    }
    function searchOnKeypress() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            doReport();
        }, 500);
        $(this).data('timer', wait);
    }


</script>