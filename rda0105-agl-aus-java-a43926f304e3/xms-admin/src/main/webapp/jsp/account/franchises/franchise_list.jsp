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
        <li class="active"><xms:localization text="Franchises"/>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active"><xms:localization text="Franchises List"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->

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
                                <xms:localization text="Franchise List"/>
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
                                        <s:hidden id="selected-option"/>
                                        <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                            <li><a href="#" onclick="exportClick('option')"><xms:localization
                                                    text="Option"/></a></li>
                                            <li><a href="#" onclick="exportClick('export')"><xms:localization
                                                    text="Export"/></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="portlet-body">
                                <table class="table mg0">
                                    <tr>
                                        <th class="s42"><xms:localization text="Show"/> <select name="pageSize"
                                                                                                style="height: 22px; padding-top: 1px;"
                                                                                                onchange="search()">
                                            <option>25</option>
                                            <option>50</option>
                                            <option>100</option>
                                        </select> <xms:localization text="entries"/></th>
                                    </tr>
                                </table>
                                <form id="search-form">
                                    <s:hidden name="orderField"/>
                                    <s:hidden name="orderType"/>
                                </form>
                                <div id="franchise-list-result">
                                    <table class="table table-hover table-bordered mg0">
                                        <thead>
                                        <tr>
                                            <th sort-field="franchise_code"><xms:localization text="Franchise #"/> <i
                                                    class="fa fa-sort"></i></th>
                                            <th sort-field="franchise_territory"><xms:localization text="Territory"/> <i
                                                    class="fa fa-sort"></i></th>
                                            <th sort-field="customer_name" data-group="address"><xms:localization
                                                    text="Name"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="address1" data-group="address"><xms:localization
                                                    text="Addr 1"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="city" data-group="address"><xms:localization text="City"/>
                                                <i class="fa fa-sort"></i></th>
                                            <th sort-field="postal_code" data-group="address"><xms:localization
                                                    text="Postal Code"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="phone" data-group="address"><xms:localization text="Phone"/>
                                                <i class="fa fa-sort"></i></th>
                                            <th sort-field="email" data-group="address"><xms:localization text="Email"/>
                                                <i class="fa fa-sort"></i></th>
                                            <th sort-field="billing_customer_name" data-group="billing-address">
                                                <xms:localization text="Billing Name"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="billing_address1" data-group="billing-address">
                                                <xms:localization text="Billing Addr 1"/> <i class="fa fa-sort"></i>
                                            </th>
                                            <th sort-field="billing_city" data-group="billing-address"><xms:localization
                                                    text="Billing City"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="billing_postal_code" data-group="billing-address">
                                                <xms:localization text="Billing Postal Code"/> <i
                                                    class="fa fa-sort"></i></th>
                                            <th sort-field="billing_phone" data-group="billing-address">
                                                <xms:localization text="Billing Phone"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="dhl_international_account" data-group="dhl-columns">
                                                <xms:localization text="DHL Acct#"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="franchise_start_date" data-group="dates"><xms:localization
                                                    text="Fran Start Date"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="create_date" data-group="dates"><xms:localization
                                                    text="Submitted"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="activate_date" data-group="dates"><xms:localization
                                                    text="Activated"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="invoice_term" data-group="invoice-option"><xms:localization
                                                    text="Inv Terms"/> <i class="fa fa-sort"></i></th>
                                            <th sort-field="email_invoice" data-group="invoice-option"><xms:localization
                                                    text="E-mail Invoice?"/> <i class="fa fa-sort"></i></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:if test="franchises!=null && franchises.totalRecords>0">
                                            <s:iterator value="franchises.records">
                                                <tr>
                                                    <td><s:property value="franchiseCode"/></td>
                                                    <td><s:property value="franchiseTerritory"/></td>
                                                    <td data-group="address"><s:property value="customerName"/></td>
                                                    <td data-group="address"><s:property value="address1"/></td>
                                                    <td data-group="address"><s:property value="city"/></td>
                                                    <td data-group="address"><s:property value="postalCode"/></td>
                                                    <td data-group="address"><s:property value="phone"/></td>
                                                    <td data-group="address"><s:property value="email"/></td>
                                                    <td data-group="billing-address"><s:property
                                                            value="billingCustomerName"/></td>
                                                    <td data-group="billing-address"><s:property
                                                            value="billingAddress1"/></td>
                                                    <td data-group="billing-address"><s:property
                                                            value="billingCity"/></td>
                                                    <td data-group="billing-address"><s:property
                                                            value="billingPostalCode"/></td>
                                                    <td data-group="billing-address"><s:property
                                                            value="billingPhone"/></td>
                                                    <td data-group="dhl-columns"><s:property
                                                            value="dhlInternationalAccount"/></td>
                                                    <td data-group="dates"><s:property value="franchiseStartDate"/></td>
                                                    <td data-group="dates"><s:property value="createDate"/></td>
                                                    <td data-group="dates"><s:property value="activateDate"/></td>
                                                    <td data-group="invoice-option"><s:property value="invoiceTerm"/>
                                                        <xms:localization text="days"/></td>
                                                    <td data-group="invoice-option"><s:if test="emailInvoice=='true'">
                                                        <xms:localization text="Yes"/>
                                                    </s:if> <s:else>
                                                        <xms:localization text="No"/>
                                                    </s:else></td>
                                                </tr>
                                            </s:iterator>
                                        </s:if>
                                        <s:else>
                                            <tr>
                                                <td colspan="19"><xms:localization text="No data available..."/></td>
                                            </tr>
                                        </s:else>
                                        </tbody>
                                    </table>
                                    <div class="dataTables_paginate records">
                                        <div class="row">
                                            <div class="col-xs-4 text-left">
                                                <b><xms:localization text="Showing"/> <s:property
                                                        value="franchises.startRecord"/> <xms:localization text="to"/>
                                                    <s:property value="franchises.endRecord"/> <xms:localization
                                                            text="of"/> <s:property value="franchises.totalRecords"/>
                                                    <xms:localization text="entries"/></b>
                                            </div>
                                            <div class="col-xs-8">
                                                <s:if test="!franchises.hasPrev()">
                                                    <a class="paginate_button previous disabled"><xms:localization
                                                            text="Previous"/></a>
                                                </s:if>
                                                <s:else>
                                                    <a href="javascript:changePage(<s:property value="%{franchises.currentPage - 1}"/>)"
                                                       class="paginate_button previous"><xms:localization
                                                            text="Previous"/></a>
                                                </s:else>
												<span> <s:iterator value="franchises.pageRange" status="count">
                                                    <s:if test="%{franchises.pageRange[#count.index] == franchises.currentPage}">
                                                        <a class="paginate_button current"><s:property
                                                                value="franchises.currentPage"/></a>
                                                    </s:if>
                                                    <s:else>
                                                        <a class="paginate_button"
                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                    </s:else>
                                                </s:iterator>
												</span>
                                                <s:if test="!franchises.hasNext()">
                                                    <a class="paginate_button next" href="#"><xms:localization
                                                            text="Next"/></a>
                                                </s:if>
                                                <s:else>
                                                    <a class="paginate_button next"
                                                       href="javascript:changePage(<s:property value="%{franchises.currentPage+1}"/>)"><xms:localization
                                                            text="Next"/></a>
                                                </s:else>
                                            </div>
                                        </div>
                                    </div>
                                    <br/> <br/>
                                </div>
                                <div class="form-actions pal pdt10">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group text-left s99">
                                                <div class="form-group">
                                                    <s:form id="column-flags-form">
                                                        <table class="s36 ">
                                                            <tbody>
                                                            <tr>
                                                                <td><b><xms:localization text="Columns"/></b></td>
                                                                <td><input type="checkbox" data-group="address"
                                                                           checked="checked"/> <s:hidden
                                                                        name="columnFlags.hasAddress"
                                                                        data-group="address" value="true"/></td>
                                                                <td><xms:localization text="Address"/></td>
                                                                <td><input name="chkBillingAddress" type="checkbox"
                                                                           data-group="billing-address"/> <s:hidden
                                                                        name="columnFlags.hasBilling"
                                                                        data-group="billing-address" value="false"/>
                                                                </td>
                                                                <td><xms:localization text="Billing"/></td>
                                                                <td><input name="chkDHLColumns" type="checkbox"
                                                                           data-group="dhl-columns"/> <s:hidden
                                                                        name="columnFlags.hasDhl"
                                                                        data-group="dhl-columns" value="false"/></td>
                                                                <td><xms:localization text="DHL Account"/></td>
                                                                <td><input name="chkDates" type="checkbox"
                                                                           data-group="dates"/> <s:hidden
                                                                        name="columnFlags.hasDates" data-group="dates"
                                                                        value="false"/></td>
                                                                <td><xms:localization text="Dates"/></td>
                                                                <td><input name="chkInvoiceOption" type="checkbox"
                                                                           data-group="invoice-option"/> <s:hidden
                                                                        name="columnFlags.hasInvoicingOptions"
                                                                        data-group="invoice-option" value="false"/></td>
                                                                <td><xms:localization text="Invoicing Option"/></td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </s:form>
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
    $(document).ready(function () {
        // Show/Hide columns
        $(document).ready(function () {
            $("input[type='checkbox'][data-group]").each(function () {
                if ($(this).is(":checked")) {
                    showColumns($(this).attr("data-group"), true);
                } else {
                    showColumns($(this).attr("data-group"), false);
                }
                $(this).click(function () {
                    var group = $(this).attr("data-group");
                    var show = $(this).is(":checked");
                    showColumns(group, show);
                });
            });
        });
        // Sort table
        $("#franchise-list-result th[sort-field]").each(function () {
            $(this).css("cursor", "pointer");
            var curField = $("input[name='orderField']").val();
            var curType = $("input[name='orderType']").val();
            if (curField == $(this).attr("sort-field")) {
                if (curType == 0) {
                    $(this).find("i").removeClass().addClass("fa fa-sort-up");
                } else {
                    $(this).find("i").removeClass().addClass("fa fa-sort-down");
                }
            }
            $(this).click(function () {
                var field = $(this).attr("sort-field");
                var type = 0;
                if (field == curField) {
                    if (curType == 0) {
                        curType = 1;
                        type = 1;
                    } else {
                        curType = 0;
                        type = 0;
                    }
                }
                $("input[name='orderField']").val(field);
                $("input[name='orderType']").val(type);
                search();
            });
        });
    });

    function doSearch(pageSize, page) {
        var data = $("#search-form").serialize();
        data = data + "&page=" + page;
        data = data + "&pageSize=" + pageSize;
        loadingDialog.dialog("open");
        $.post("franchise_list_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#franchise-list-result").html(res.content);
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

    function changePage(page) {
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, page);
    }

    function search() {
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, 1);
    }

    function exportClick(option) {
        $('#selected-option').val(option);
        var data = $("#column-flags-form").serialize() + "&" + $("#search-form").serialize();
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'export':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("franchise_list_export.ix", {
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

    function showColumns(group, show) {
        if (show) {
            $("#franchise-list-result th[data-group='" + group + "']").show();
            $("#franchise-list-result td[data-group='" + group + "']").show();
            $("#column-flags-form input[type='hidden'][data-group='" + group + "']").val("true");
        } else {
            $("#franchise-list-result th[data-group='" + group + "']").hide();
            $("#franchise-list-result td[data-group='" + group + "']").hide();
            $("#column-flags-form input[type='hidden'][data-group='" + group + "']").val("false");
        }
    }


</script>