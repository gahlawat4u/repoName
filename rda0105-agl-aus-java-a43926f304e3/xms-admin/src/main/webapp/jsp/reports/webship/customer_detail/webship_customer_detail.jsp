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
                text="Home"/> </a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Web Freight Customer Details"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Web Freight Customer Details"/></li>
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
                                    <xms:localization text="Web Freight Customer Details"/>
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
                                                                                   listValue="code"
                                                                                   onchange="onFranchiseChange()"
                                                                                   headerKey="All" headerValue="All"
                                                                                   i18nitem="no"/></td>
                                                                <td><xms:localization text="Customer"/></td>
                                                                <td id="customer_list"><s:i18n_select
                                                                        name="customerCode" cssClass="form-control"
                                                                        list="customers" listKey="customerCode"
                                                                        listValue="customerCode + ' - ' + customerAddress.customerName"
                                                                        headerKey="" headerValue="All" i18nitem="no"
                                                                        cssStyle="width: 160px;"/></td>
                                                                <td><xms:localization text="Carrier"/></td>
                                                                <td><s:i18n_select name="serviceId"
                                                                                   cssClass="form-control"
                                                                                   list="services" listKey="serviceId"
                                                                                   listValue="serviceName" headerKey=""
                                                                                   headerValue="All" i18nitem="no"
                                                                                   cssStyle="width: 160px;"/></td>
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
                                                <div id="webship_customer_detail_report">
                                                    <s:hidden id="page" name="page"/>
                                                    <s:hidden id="orderField" name="orderField"/>
                                                    <s:hidden id="orderType" name="orderType"/>
                                                    <table id="webship_customer_detail_report_table"
                                                           class="table table-bordered mg0 table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Invoice #"/></th>
                                                            <th><xms:localization text="Airbill #"/></th>
                                                            <th><xms:localization text="Customer #"/></th>
                                                            <th><xms:localization text="Shipment Date"/></th>
                                                            <th><xms:localization text="Sender Address"/></th>
                                                            <th><xms:localization text="Receiver Address"/></th>
                                                            <th><xms:localization text="Pieces"/></th>
                                                            <th><xms:localization text="Weight"/></th>
                                                            <th><xms:localization text="Quoted"/></th>
                                                            <th><xms:localization text="Charges"/></th>
                                                            <th><xms:localization text="Total"/></th>
                                                            <th><xms:localization text="Service Type"/></th>
                                                            <th><xms:localization text="Accessorial"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="report==null || report.totalRecords==0">
                                                            <tr>
                                                                <td colspan='13'><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="report.records">
                                                                <tr>
                                                                    <td><s:property value="invoiceCode"/></td>
                                                                    <td><s:property value="airbillNumber"/></td>
                                                                    <td><s:property value="customerCode"/></td>
                                                                    <td><s:property value="shipmentDate"/></td>
                                                                    <td><s:property value="senderCompanyName"/><br/>
                                                                        <s:property value="senderContactName"/><br/>
                                                                        <s:property value="senderAddress1"/><br/>
                                                                        <s:property value="senderAddress2"/><br/>
                                                                        <s:property value="senderCity"/> <s:property
                                                                                value="senderPostalCode"/> <s:property
                                                                                value="senderState"/> <s:property
                                                                                value="senderCountryName"/></td>
                                                                    <td><s:property value="receiverCompanyName"/><br/>
                                                                        <s:property value="receiverContactName"/><br/>
                                                                        <s:property value="receiverAddress1"/><br/>
                                                                        <s:property value="receiverAddress2"/><br/>
                                                                        <s:property value="receiverCity"/> <s:property
                                                                                value="receiverPostalCode"/> <s:property
                                                                                value="receiverState"/> <s:property
                                                                                value="receiverCountryName"/></td>
                                                                    <td><s:property value="noOfPieces"/></td>
                                                                    <td><s:property value="weight"/> <s:property
                                                                            value="weightUnit"/></td>
                                                                    <td><s:property value="quoted"/></td>
                                                                    <td><s:iterator value="charges">
                                                                        <s:property value="description"/> - <s:property
                                                                            value="currencySymbol"/>
                                                                        <s:property value="customerCost"/> (<s:property
                                                                            value="currencySymbol"/>
                                                                        <s:property
                                                                                value="franchiseCost"/>) [<s:property
                                                                            value="currencySymbol"/>
                                                                        <s:property value="margin"/>] <br/>
                                                                    </s:iterator></td>
                                                                    <td><s:property value="currencySymbol"/> <s:property
                                                                            value="totalCustomerCost"/><br/>
                                                                        (<s:property value="currencySymbol"/>
                                                                        <s:property value="totalFranchiseCost"/>)<br/>
                                                                        Mrg: <s:property value="currencySymbol"/>
                                                                        <s:property value="totalMargin"/></td>
                                                                    <td><s:property value="serviceType"/></td>
                                                                    <td><xms:localization text="Insurance:"/> <s:if
                                                                            test="'true'.equalsIgnoreCase(insurance)">
                                                                        <xms:localization text="Yes"/>
                                                                    </s:if> <s:else>
                                                                        <xms:localization text="No"/>
                                                                    </s:else><br/> <xms:localization
                                                                            text="Duties/Taxes Fee:"/> <s:property
                                                                            value="dutiesTaxesFee"/><br/>
                                                                        <xms:localization text="Dangerous Goods:"/>
                                                                        <s:if test="'true'.equalsIgnoreCase(dangerousGoods)">
                                                                            <xms:localization text="Yes"/>
                                                                        </s:if> <s:else>
                                                                            <xms:localization text="No"/>
                                                                        </s:else><br/> <xms:localization
                                                                                text="Pre Clearance:"/> <s:if
                                                                                test="'true'.equalsIgnoreCase(preClearance)">
                                                                            <xms:localization text="Yes"/>
                                                                        </s:if> <s:else>
                                                                            <xms:localization text="No"/>
                                                                        </s:else><br/> <s:iterator value="charges">
                                                                            <s:if test="!'true'.equalsIgnoreCase(isBaseCharge)">
                                                                                <s:property value="description"/>
                                                                                <br/>
                                                                            </s:if>
                                                                        </s:iterator></td>
                                                                </tr>
                                                            </s:iterator>
                                                            <tr>
                                                                <th colspan='13'><xms:localization text="Showing"/>
                                                                    <s:property value="report.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="report.endRecord"/> <xms:localization
                                                                            text="of"/> <s:property
                                                                            value="report.totalRecords"/></th>
                                                            </tr>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="report!=null">
                                                            <s:if test="report.hasPrev()">
                                                                <a href="javascript:changePage(<s:property value="%{report.currentPage - 1}"/>)"
                                                                   class="paginate_button previous"><xms:localization
                                                                        text="Previous"/></a>
                                                            </s:if>
															<span> <s:iterator value="report.pageRange" status="count">
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
    function doReport() {
        var data = $("#report_form").serialize();
        data += "&orderField=" + $("#orderField").val();
        data += "&orderType=" + $("#orderType").val();
        var dataExport = data;
        data += "&page=" + $("#page").val();
        data += "&pageSize=" + $("#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("webship_customer_detail_do_report.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#webship_customer_detail_report").html(res.content);
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

    function onFranchiseChange() {
        var franCode = $("#franchiseCode option:selected").val();
        loadingDialog.dialog("open");
        $.post("webship_customer_detail_get_customers.ix?reqType=json", "franchiseCode=" + franCode, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customer_list").html(res.content);
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
        var data = $("#export-data").val() + "&rptTxnId=" + $("#rptTxnId").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("webship_customer_detail_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("webship_customer_detail_export.ix", {
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


</script>