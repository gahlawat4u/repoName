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
        <li class="hidden"><a href="#"><xms:localization text="Carrier Adjustments"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Carrier Adjustments"/></li>
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
                                    <xms:localization text="Customer Aging"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <table class="s36">
                                            <tbody>
                                            <tr>
                                                <td><s:select name="franchiseCode" list="franchises"
                                                              listValue="code + ' - ' + name" listKey="code"
                                                              headerValue="All" headerKey="All" cssClass="form-control"
                                                              onchange="onFranchiseChange()"/></td>
                                                <td>
                                                    <button class="btn s37" type="button" onclick="search(true,1)">
                                                        Search
                                                    </button>
                                                </td>
                                                <td>
                                                    <div class="form-group flr mgb">
                                                        <div class="btn-group">
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
                                                                       onclick="exportClick('print')"><xms:localization
                                                                        text="Print"/></a></li>
                                                                <s:if test="grandTotal != 0">
                                                                    <li><a href="#"
                                                                           onclick="exportClick('export')"><xms:localization
                                                                            text="Export"/></a></li>
                                                                </s:if>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <ul id="generalTab" class="nav nav-tabs responsive">
                                    <li class="active" style="margin-left: 10px;"><a href="#Collections-tab"
                                                                                     data-toggle="tab"><xms:localization
                                            text="Show Collections Filter"/></a></li>
                                    <li><a href="#Columns-tab" data-toggle="tab"><xms:localization
                                            text="Show Collections Columns"/></a></li>
                                </ul>
                                <div id="generalTabContent" class="tab-content responsive">
                                    <div id="Collections-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="portlet-body b12 b11">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <form id="frmCustomerAgingFilter">
                                                            <s:hidden name="rptTxnId"/>
                                                            <s:hidden name="orderField"/>
                                                            <s:hidden name="orderType"/>
                                                            <table class="s36">
                                                                <tr>
                                                                    <td><b><xms:localization text="Aging Type"/></b>
                                                                    </td>
                                                                    <td><select name="agingType" class="form-control">
                                                                        <option value="1"><xms:localization
                                                                                text="By Invoice Date"/></option>
                                                                        <option value="0"><xms:localization
                                                                                text="By Due Date"/></option>
                                                                    </select></td>
                                                                </tr>
                                                            </table>
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td rowspan="2" id="col-sale-list-result"><s:select
                                                                            name="collectorId" list="collectors"
                                                                            listValue="displayName" listKey="userId"
                                                                            headerValue="Select Collector" headerKey="0"
                                                                            cssClass="form-control"/> <s:select
                                                                            name="saleRepId" list="saleReps"
                                                                            listValue="displayName" listKey="userId"
                                                                            headerValue="Select Sales Rep" headerKey="0"
                                                                            cssClass="form-control"/></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><xms:localization text="Avg Days to pay"/> :
                                                                    </td>
                                                                    <td><input name="minAvgDaysToPay"
                                                                               onkeypress="return formartNumber(event,this,false);"
                                                                               class="form-control b10" type="text"
                                                                               placeholder="Min" width="100px"></td>
                                                                    <td><input name="maxAvgDaysToPay"
                                                                               onkeypress="return formartNumber(event,this,false);"
                                                                               class="form-control b10" type="text"
                                                                               placeholder="Max"></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><xms:localization text="Invoice Age"/> :</td>
                                                                    <td><input name="minInvoiceAge"
                                                                               onkeypress="return formartNumber(event,this,false);"
                                                                               class="form-control b10" type="text"
                                                                               placeholder="Min"></td>
                                                                    <td><input name="maxInvoiceAge"
                                                                               onkeypress="return formartNumber(event,this,false);"
                                                                               class="form-control b10" type="text"
                                                                               placeholder="Max"></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><input name="withBalanceOnly" type="checkbox" checked/>
                                                                    </td>
                                                                    <td><xms:localization
                                                                            text="With balance only"/></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td><xms:localization text="Days Overdue"/> :</td>
                                                                    <td><input name="minDaysOverdue"
                                                                               onkeypress="return formartNumber(event,this,false);"
                                                                               class="form-control b10" type="text"
                                                                               placeholder="Min"></td>
                                                                    <td><input name="maxDaysOverdue"
                                                                               onkeypress="return formartNumber(event,this,false);"
                                                                               class="form-control b10" type="text"
                                                                               placeholder="Max"></td>
                                                                    <td>&nbsp;</td>
                                                                    <td colspan="6">
                                                                        <button class="btn s37" type="button"
                                                                                onclick="search(true,1)">
                                                                            <xms:localization text="Search"/>
                                                                        </button>
                                                                    </td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </form>
                                                    </div>
                                                    <div class="col-lg-2"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="Columns-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="portlet-body b12 b11">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <s:form id="column-flags">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><input type="checkbox" id="agingBuckets"
                                                                               onclick="showColumn($(this).attr('id'))"
                                                                               checked="checked"> <input type="hidden"
                                                                                                         name="columnFlags.hasAgingBuckets"
                                                                                                         value="true"
                                                                                                         data-group="agingBuckets">
                                                                    </td>
                                                                    <td><xms:localization text="Aging Buckets"/></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><input type="checkbox" id="invoiceAge"
                                                                               onclick="showColumn($(this).attr('id'))">
                                                                        <input type="hidden"
                                                                               name="columnFlags.hasInvoiceAge"
                                                                               value="false" data-group="invoiceAge">
                                                                    </td>
                                                                    <td><xms:localization text="Invoice Age"/></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><input type="checkbox" id="daysOverdue"
                                                                               onclick="showColumn($(this).attr('id'))">
                                                                        <input type="hidden"
                                                                               name="columnFlags.hasDaysOverdue"
                                                                               value="false" data-group="daysOverdue">
                                                                    </td>
                                                                    <td><xms:localization text="Days Overdue"/></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><input type="checkbox" id="invoice"
                                                                               onclick="showColumn($(this).attr('id'))">
                                                                        <input type="hidden"
                                                                               name="columnFlags.hasInvoice"
                                                                               value="false" data-group="invoice"></td>
                                                                    <td><xms:localization
                                                                            text="Outstanding Invoice(s)"/></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><input type="checkbox" id="salesRep"
                                                                               onclick="showColumn($(this).attr('id'))">
                                                                        <input type="hidden"
                                                                               name="columnFlags.hasSalesRep"
                                                                               value="false" data-group="salesRep"></td>
                                                                    <td><xms:localization text="Sales Rep"/></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><input type="checkbox" id="totalOverpayments"
                                                                               onclick="showColumn($(this).attr('id'))">
                                                                        <input type="hidden"
                                                                               name="columnFlags.hasTotalOverpayments"
                                                                               value="false"
                                                                               data-group="totalOverpayments"></td>
                                                                    <td><xms:localization
                                                                            text="Total Overpayments"/></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><input type="checkbox" id="terms"
                                                                               onclick="showColumn($(this).attr('id'))">
                                                                        <input type="hidden" name="columnFlags.hasTerms"
                                                                               value="false" data-group="terms"></td>
                                                                    <td><xms:localization text="Terms"/></td>
                                                                    <td>&nbsp;</td>
                                                                    <td><input type="checkbox" id="avgDaysToPay"
                                                                               onclick="showColumn($(this).attr('id'))">
                                                                        <input type="hidden"
                                                                               name="columnFlags.hasAvgDaysToPay"
                                                                               value="false" data-group="avgDaysToPay">
                                                                    </td>
                                                                    <td><xms:localization text="Avg Days to pay"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </s:form>
                                                    </div>
                                                    <div class="col-lg-2"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="customer-aging-report" class="portlet-body b11">
                                    <table class="table table-bordered mg0" id="datatable1">
                                        <thead>
                                        <tr style="background: #F4F4F4">
                                            <th><xms:localization text="Total Due"/></th>
                                            <th><xms:localization text="Total Overdue"/></th>
                                            <th><xms:localization text="<=0 days" escapeHtml="true"/></th>
                                            <th><xms:localization text="1 to 15"/></th>
                                            <th><xms:localization text="16 to 30"/></th>
                                            <th><xms:localization text="31 to 45"/></th>
                                            <th><xms:localization text="46 to 60"/></th>
                                            <th><xms:localization text="61 to 90"/></th>
                                            <th><xms:localization text="91 to 120"/></th>
                                            <th><xms:localization text="121+"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td colspan="10"><xms:localization text="No data available..."/></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <br/>

                                    <div class="caption b5">
                                        <xms:localization text="Aging"/>
                                    </div>
                                    <table class="table mg0">
                                        <tr>
                                            <th class="s42"><xms:localization text="Show"/> <s:select name="pageSize"
                                                                                                      list="pageSizes"
                                                                                                      onchange="search(false,1)"/>
                                                <xms:localization text="entries"/></th>
                                        </tr>
                                    </table>
                                    <table class="table table-hover table-bordered mg0" id="customer-aging-table">
                                        <thead>
                                        <tr>
                                            <th><xms:localization text="Customer"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="Customer #"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="Sales Rep"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="Total Due"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="Total Overdue"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="<=0 days" escapeHtml="true"/> <i
                                                    class="fa fa-sort"></i></th>
                                            <th><xms:localization text="1 to 15"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="16 to 30"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="31 to 45"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="46 to 60"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="61 to 90"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="91 to 120"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="121+"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="Max Age"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="Max Days Overdue"/> <i class="fa fa-sort"></i>
                                            </th>
                                            <th><xms:localization text="Outstanding Invoice(s)"/></th>
                                            <th><xms:localization text="Total Overpaid"/> <i class="fa fa-sort"></i>
                                            </th>
                                            <th><xms:localization text="Terms"/> <i class="fa fa-sort"></i></th>
                                            <th><xms:localization text="Avg Days to pay"/> <i class="fa fa-sort"></i>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td colspan="19"><xms:localization text="No records to view"/></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="aging_manage_customers_dialog" title='<xms:localization text="Customer"/>'></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    var isSearched = false;

    function search(isGo, page) {
        if (isGo) {
            $("#rptTxnId").val("");
        }
        var data = $("#frmCustomerAgingFilter").serialize();
        data = data + "&franchiseCode=" + $("#franchiseCode option:selected").val();
        data = data + "&pageSize=" + $("#pageSize option:selected").val();
        data = data + "&page=" + page;
        loadingDialog.dialog("open");
        $.post("customer_aging_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#customer-aging-report").html(res.content);
                isSearched = true;
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
        search(false, page);
    }

    function showColumn(dataGroup) {
        var isChecked = $('#' + dataGroup).is(':checked');
        if (isChecked) {
            $("#customer-aging-table [data-group='" + dataGroup + "']").show();
            $("input[data-group='" + dataGroup + "']").val("true");
        } else {
            $("#customer-aging-table [data-group='" + dataGroup + "']").hide();
            $("input[data-group='" + dataGroup + "']").val("false");
        }
    }
    function exportClick(option) {
        loadingDialog.dialog("open");
        if (!isSearched) {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="There's no record to export" />");
            alertDialog.dialog("open");
            die;
        }
        var data = $("#frmCustomerAgingFilter").serialize();
        data = data + "&franchiseCode=" + $("#franchiseCode option:selected").val();
        data = data + "&" + $("#column-flags").serialize();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                loadingDialog.dialog("close");
                break;
            case 'print':
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("customer_aging_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                    loadingDialog.dialog("close");
                }).fail(function () {
                    loadingDialog.dialog("close");
                    alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                    alertDialog.dialog("open");
                });
                break;
            case 'export':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("customer_aging_export.ix", {
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

    function onFranchiseChange() {
        var franCode = $("select#franchiseCode option:selected").val();
        loadingDialog.dialog("open");
        $.post("customer_aging_col_and_sale_list.ix?reqType=json", "franchiseCode=" + franCode, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#col-sale-list-result").html(res.content);
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

    function showManageCustomers(code) {
        loadingDialog.dialog("open");
        $.post("manage_customers_from_aging.ix?reqType=json", "customerCode=" + code, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                var height = $(window).height();
                var width = $(window).width();
                // height = height * 0.70;
                // width = width * 0.9;
                var dialog = $("#aging_manage_customers_dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: width,
                    height: height,
                    maxHeight: height,
                    maxWidth: width,
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                dialog.html(res.content);
                dialog.dialog("open");
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


</script>