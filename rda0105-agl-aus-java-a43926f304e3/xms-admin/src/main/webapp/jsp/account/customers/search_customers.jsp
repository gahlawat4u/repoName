<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Customers"/>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active"><xms:localization text="Search Customers"/></li>
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
                                    <xms:localization text="Search Customers"/>
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
                                            <form id="order-form">
                                                <s:hidden name="orderField" id="order_field"/>
                                                <s:hidden name="orderType" id="order_type"/>
                                            </form>
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
                                    <form id="search-customer-form">
                                        <table class="s36">
                                            <tbody>
                                            <tr>
                                                <td><xms:localization text="Franchise:"/></td>
                                                <td><s:select name="franchiseCode" list="franchises"
                                                              listValue="code + ' - ' + name" listKey="code"
                                                              headerValue="All" headerKey="All" cssClass="form-control"
                                                              onchange="getSaleRepsList()"/></td>
                                                <td>&nbsp;</td>
                                                <td><xms:localization text="Customer #:"/></td>
                                                <td><input type="text" name="customerCode" class="form-control"/></td>
                                                <td>&nbsp;</td>
                                                <td><xms:localization text="Submit Date Start:"/></td>
                                                <td>
                                                    <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input name="startDate"
                                                                           class="form-control form_datetime"
                                                                           type="text" data-date-format="dd MM yyyy">
                                                    </div>
                                                </td>
                                                <td>&nbsp;</td>
                                                <td><xms:localization text="End:"/></td>
                                                <td>
                                                    <div class="form-group input-group" style="margin-bottom: 0px;">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input name="endDate"
                                                                           class="form-control form_datetime"
                                                                           type="text" data-date-format="dd MM yyyy">
                                                    </div>
                                                </td>
                                            </tr>
                                            <%-- <tr>
                                                <td colspan="11" height="5"></td>
                                            </tr>
                                            <tr>
                                                <td><xms:localization text="DHL #:"/></td>
                                                <td><input name="dhl" type="text" class="form-control"/></td>
                                                <td>&nbsp;</td>
                                                <td><xms:localization text="DHL Inbound:"/></td>
                                                <td><input name="dhlInbound" type="text" class="form-control"/></td>
                                                <td colspan="6">&nbsp;</td>
                                            </tr> --%>
                                            <tr>
                                                <td colspan="11" height="5"></td>
                                            </tr>
                                            <tr>
                                                <td><xms:localization text="Sales Rep:"/></td>
                                                <td id="sale-rep-list-result"><s:select name="salesRepId"
                                                                                        list="saleReps"
                                                                                        listValue="displayName"
                                                                                        listKey="userId"
                                                                                        headerValue="All" headerKey=""
                                                                                        cssClass="form-control"/></td>
                                                <td>&nbsp;</td>
                                                <td><xms:localization text="Web Freight Group:"/></td>
                                                <td><s:select name="webshipGroupId" list="webshipGroups"
                                                              listValue="webshipGroupName" listKey="webshipGroupId"
                                                              headerKey="" headerValue="" cssClass="form-control"/></td>
                                                <td>&nbsp;</td>
                                                <td><xms:localization text="Search Name:"/></td>
                                                <td><input name="customerName" type="text" class="form-control"/></td>
                                                <td>&nbsp;</td>
                                                <td colspan="2">
                                                    <button class="btn s37" type="button" onclick="onGoClick()">
                                                        <xms:localization text="Go"/>
                                                    </button>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                    <table class="s36">
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <br/>
                                    <table class="table mg0">
                                        <tr>
                                            <th class="s42"><xms:localization text="Show"/> <s:select name="pageSize"
                                                                                                      list="pageSizes"
                                                                                                      onchange="onGoClick()"/>
                                                <xms:localization text="entries"/></th>
                                        </tr>
                                    </table>
                                    <div id="search-customers-list">
                                        <div style="overflow: auto">
                                            <table class="table table-hover table-bordered mg0"
                                                   id="customer-list-table">
                                                <thead>
                                                <tr>
                                                    <th><xms:localization text="Customer #"/></th>
                                                    <th><xms:localization text="Customer Name"/></th>
                                                    <th><xms:localization text="Web Freight Group"/></th>
                                                    <th><xms:localization text="Sales Rep"/></th>
                                                    <th data-group="address-detail"><xms:localization text="Name"/></th>
                                                    <th data-group="address-detail"><xms:localization text="Attn"/></th>
                                                    <th data-group="address-detail"><xms:localization
                                                            text="Addr 1"/></th>
                                                    <th data-group="address-detail"><xms:localization
                                                            text="Addr 2"/></th>
                                                    <th data-group="address-detail"><xms:localization text="City"/></th>
                                                    <th data-group="address-detail"><xms:localization
                                                            text="Country"/></th>
                                                    <th data-group="address-detail"><xms:localization
                                                            text="Postal Code"/></th>
                                                    <th data-group="address-detail"><xms:localization
                                                            text="Phone"/></th>
                                                    <th data-group="address-detail"><xms:localization
                                                            text="Email"/></th>
                                                    <th data-group="billing-detail"><xms:localization
                                                            text="Billing Name"/></th>
                                                    <th data-group="billing-detail"><xms:localization
                                                            text="Billing Attn"/></th>
                                                    <th data-group="billing-detail"><xms:localization
                                                            text="Billing Addr 1"/></th>
                                                    <th data-group="billing-detail"><xms:localization
                                                            text="Billing Addr 2"/></th>
                                                    <th data-group="billing-detail"><xms:localization
                                                            text="Billing City"/></th>
                                                    <th data-group="billing-detail"><xms:localization
                                                            text="Billing Country"/></th>
                                                    <th data-group="billing-detail"><xms:localization
                                                            text="Billing Postal Code"/></th>
                                                    <th data-group="billing-detail"><xms:localization
                                                            text="Billing Phone"/></th>
                                                    <th data-group="billing-detail"><xms:localization
                                                            text="Billing Email"/></th>
                                                    <th data-group="dhl-cost-basis"><xms:localization
                                                            text="DHL Acct #"/></th>
                                                    <th data-group="dhl-cost-basis"><xms:localization
                                                            text="Inbound Acct #"/></th>
                                                    <th data-group="invoicing-option"><xms:localization
                                                            text="Inv Terms"/></th>
                                                    <th data-group="invoicing-option"><xms:localization
                                                            text="Email Invoice?"/></th>
                                                    <th data-group="dates"><xms:localization text="Submitted"/></th>
                                                    <th data-group="dates"><xms:localization text="Activated"/></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td colspan="28"><xms:localization
                                                            text="No data available..."/></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="dataTables_paginate records">
                                            <div class="row">
                                                <div class="col-xs-4 text-left">
                                                    <b><xms:localization text="Showing"/> <s:property
                                                            value="customers.startRecord"/> <xms:localization
                                                            text="to"/> <s:property value="customers.endRecord"/>
                                                        <xms:localization text="of"/> <s:property
                                                                value="customers.totalRecords"/> <xms:localization
                                                                text="entries"/></b>
                                                </div>
                                                <div class="col-xs-8">
                                                    <s:if test="!customers.hasPrev()">
                                                        <a class="paginate_button previous disabled"><xms:localization
                                                                text="Previous"/></a>
                                                    </s:if>
                                                    <s:else>
                                                        <a href="javascript:changePage(<s:property value="%{customers.currentPage - 1}"/>)"
                                                           class="paginate_button previous"><xms:localization
                                                                text="Previous"/></a>
                                                    </s:else>
													<span> <s:iterator value="customers.pageRange" status="count">
                                                        <s:if test="%{customers.pageRange[#count.index] == customers.currentPage}">
                                                            <a class="paginate_button current"><s:property
                                                                    value="customers.currentPage"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button"
                                                               href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                        </s:else>
                                                    </s:iterator>
													</span>
                                                    <s:if test="!customers.hasNext()">
                                                        <a class="paginate_button next" href="#"><xms:localization
                                                                text="Next"/></a>
                                                    </s:if>
                                                    <s:else>
                                                        <a class="paginate_button next"
                                                           href="javascript:changePage(<s:property value="%{customers.currentPage+1}"/>)"><xms:localization
                                                                text="Next"/></a>
                                                    </s:else>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <s:form id="column-flags-form">
                                            <b><xms:localization text="Columns"/>: </b>
                                            <input type="checkbox" data-group="address-detail"
                                                   onchange="onCheckboxToggle($(this))" checked="checked"/>
                                            <s:hidden name="columnFlags.hasAddress" value="true"
                                                      data-group="address-detail"/>
                                            <xms:localization text="Address"/>
                                            <input type="checkbox" data-group="billing-detail"
                                                   onchange="onCheckboxToggle($(this))"/>
                                            <s:hidden name="columnFlags.hasBilling" value="false"
                                                      data-group="billing-detail"/>
                                            <xms:localization text="Billing"/>
                                            <input type="checkbox" data-group="dhl-cost-basis"
                                                   onchange="onCheckboxToggle($(this))"/>
                                            <s:hidden name="columnFlags.hasDhlAccounts" value="false"
                                                      data-group="dhl-cost-basis"/>
                                            <xms:localization text="DHL Accounts"/>
                                            <input type="checkbox" data-group="invoicing-option"
                                                   onchange="onCheckboxToggle($(this))"/>
                                            <s:hidden name="columnFlags.hasInvoicingOptions" value="false"
                                                      data-group="invoicing-option"/>
                                            <xms:localization text="Invoicing Option"/>
                                            <input type="checkbox" data-group="dates"
                                                   onchange="onCheckboxToggle($(this))"/>
                                            <s:hidden name="columnFlags.hasDates" value="false" data-group="dates"/>
                                            <xms:localization text="Dates"/>
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
<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='checkbox'][data-group]").each(function () {
            if ($(this).is(":checked")) {
                showColumns($(this).attr("data-group"), true);
            } else {
                showColumns($(this).attr("data-group"), false);
            }
        });
        var fieldList = ["customer_code", "customer_name", "webship_group_name", "sales_rep_name", "customer_name", "address_contact_name", "address_address1", "address_address2", "address_city", "address_country", "address_postal_code", "address_phone", "address_email", "billing_customer_name", "billing_contact_name", "billing_address1", "billing_address2", "billing_city", "billing_country", "billing_postal_code", "billing_phone", "billing_email", "dhl_international_account", "dhl_inbound_account", "invoice_days", "email_invoice", "create_date", "activate_date"];
        $("#customer-list-table").tablesorter({
            sortFieldId: "order_field",
            sortTypeId: "order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });

    function showColumns(group, show) {
        if (show) {
            $("#search-customers-list [data-group='" + group + "']").show();
            $("#column-flags-form input[type='hidden'][data-group='" + group + "']").val("true");
        } else {
            $("#search-customers-list [data-group='" + group + "']").hide();
            $("#column-flags-form input[type='hidden'][data-group='" + group + "']").val("false");
        }
    }

    function onCheckboxToggle(obj) {
        var group = obj.attr("data-group");
        var show = obj.is(":checked");
        showColumns(group, show);
    }

    function doSearch(pageSize, page) {
        var p = typeof(page) != "undefined" ? page : "1";
        var ps = typeof(pageSize) != "undefined" ? pageSize : $("#pageSize").val();
        var data = $("#search-customer-form").serialize();
        data = data + "&page=" + p;
        data = data + "&pageSize=" + ps;
        data = data + "&" + $("#order-form").serialize();
        loadingDialog.dialog("open");
        $.post("search_customers_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#search-customers-list").html(res.content);
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
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, 1);
    }

    function changePage(page) {
        var pageSize = $("select[name='pageSize'] option:selected").val();
        doSearch(pageSize, page);
    }

    function exportClick(option) {
        var data = $("#search-customer-form").serialize() + "&" + $("#column-flags-form").serialize() + "&" + $("#order-form").serialize();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");

                break;
            case 'export':
                $("#export-option").val("<xms:localization text="Export" />");
                var url = "search_customers_export.ix?" + data;
                window.open(url);
                break;
        }

    }

    function getSaleRepsList() {
        var data = $("select#franchiseCode option:selected").val();
        loadingDialog.dialog("open");
        $.post("search_customers_sale_rep_list.ix?reqType=json", "franchiseCode=" + data, function (res) {
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


</script>