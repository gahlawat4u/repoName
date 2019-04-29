<%@page import="com.gms.xms.common.constants.AppConstants" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN SIDEBAR MENU-->
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Invoice Overpayment"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Invoice Overpayment"/></li>
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
                                    <xms:localization text="Manage Overpayments"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12 flr">
                                                <div class="form-group flr mgb">
                                                    <table class="s36">
                                                        <tr>
                                                            <s:form id="search-form" action="invoice_overpayment.ix">
                                                                <td><s:i18n_select name="filter.franchiseCode"
                                                                                   class="form-control"
                                                                                   id="search-franchise-code"
                                                                                   list="franchiseList" listKey="code"
                                                                                   listValue="code" headerKey="All"
                                                                                   headerValue="All"
                                                                                   cssClass="form-control"
                                                                                   i18nitem="no"/></td>
                                                                <td><s:textfield name="filter.customerCode"
                                                                                 cssClass="form-control"
                                                                                 id="search-customer-code"/>
                                                                <td>
                                                                    <button class="btn s37" type="button"
                                                                            onclick="refreshTable()">
                                                                        <xms:localization text="Search"/>
                                                                    </button>
                                                                </td>
                                                            </s:form>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <div id="invoice-overpayment-table">
                                                <div class="col-lg-12">
                                                    <p>
                                                        <b><xms:localization text="Note:</b><br>
														- Double-click a customer to apply overpayments."/>
                                                    </p>
                                                    <table class="table mg0">
                                                        <tr>
                                                            <td class="s42"><span
                                                                    style="margin-left: 10px;"><xms:localization
                                                                    text="Show"/></span> <s:i18n_select
                                                                    name="filter.pageSize" list="pageSizeList"
                                                                    cssClass="s52" onchange="changePageSize();"
                                                                    i18nitem="no"/> <span><xms:localization
                                                                    text="entries"/></span></td>
                                                        </tr>
                                                    </table>
                                                    <table class="table table-bordered  table-hover mg0 "
                                                           id="datatable1">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Franchise #"/></th>
                                                            <th><xms:localization text="Customer #"/></th>
                                                            <th><xms:localization text="Customer Name"/></th>
                                                            <th><xms:localization text="Total Overpayments"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="%{overpaymentList==null || overpaymentList.isEmpty()}">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="overpaymentList.records">
                                                                <tr <%
                                                                    if (AppConstants.APP_SETTINGS.getFanchiseReceivePayment()) {
                                                                %>
                                                                        <s:if test="userLevel<=3"> onclick="showDialog($(this))"</s:if>
                                                                        <%} else { %>
                                                                        <s:if test="userLevel<3"> onclick="showDialog($(this))"</s:if>
                                                                        <% }%>
                                                                        >
                                                                    <s:hidden id="customer-code"
                                                                              value="%{customerCode}"></s:hidden>
                                                                    <s:hidden id="source" value="%{source}"></s:hidden>
                                                                    <s:hidden id="customer-name"
                                                                              value="%{customerName}"></s:hidden>
                                                                    <td><s:property value="franchiseCode"/></td>
                                                                    <td><s:property value="customerCode"/></td>
                                                                    <td><s:property value="customerName"/></td>
                                                                    <td align="right"><s:property
                                                                            value="totalOverpayment"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        <tr>
                                                            <td colspan="6"><b><xms:localization text="Total:"/></b>
                                                                <s:property
                                                                        value="%{totalOverpayment==null?0:totalOverpayment}"/>
                                                                <xms:localization text="overpaid"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="6"><b><xms:localization text="Total:"/> </b>
                                                                <s:property value="%{recordCount==null?0:recordCount}"/>
                                                                <xms:localization text="overpayment"/></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="dataTables_paginate">
                                                        <s:if test="%{overpaymentList!=null && overpaymentList.totalRecords>0}">
                                                            <a href="javascript:changePage(1);"
                                                               class="paginate_button previous"><xms:localization
                                                                    text="First"/></a>
                                                            <s:iterator value="overpaymentList.pageRange"
                                                                        status="count">
                                                                <s:if test="%{overpaymentList.pageRange[#count.index] == overpaymentList.currentPage}">
                                                                    <span><a href="#"
                                                                             class="paginate_button current active"><s:property
                                                                            value="overpaymentList.currentPage"/></a> <input
                                                                            type="hidden"
                                                                            value='<s:property value="overpaymentList.currentPage" />'
                                                                            id='current_page'/> </span>
                                                                </s:if>
                                                                <s:else>
                                                                    <span><a
                                                                            href='javascript:changePage(<s:property />);'
                                                                            class="paginate_button"><s:property/></a></span>
                                                                </s:else>
                                                            </s:iterator>
                                                            <a href="javascript:changePage(<s:property value='%{overpaymentList.totalPage}'/>);"
                                                               class="paginate_button next"><xms:localization
                                                                    text="Last"/></a>
                                                        </s:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Dialog -->
                                            <div id="overpayment_dialog"></div>
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
<div id="inv-overpayment-dialog" title='<xms:localization text="Apply Overpayment to Invoice(s)" />'></div>
<!--END CONTENT-->
<script type="text/javascript">
    var dialog;
    function refreshTable() {
        var pageSize = $("select#filter_pageSize option:selected").val();
        var data = $('#search-form').serialize();
        data = data + "&" + 'filter.page=1';
        data = data + "&" + 'filter.pageSize=' + pageSize;
        doPostDataByParameters("invoice_overpayment_get_data.ix?reqType=json", data, "", "invoice-overpayment-table");
    }

    function changePage(page) {
        var pageSize = $("select#filter_pageSize option:selected").val();
        var data = $('#search-form').serialize();
        data = data + "&" + 'filter.page=' + page;
        data = data + "&" + 'filter.pageSize=' + pageSize;
        currentPage = page;
        doPostDataByParameters("invoice_overpayment_get_data.ix?reqType=json", data, "", "invoice-overpayment-table");
    }

    function changePageSize() {
        var pageSize = $("select#filter_pageSize option:selected").val();
        var data = $('#search-form').serialize();
        data = data + "&" + 'filter.page=1';
        data = data + "&" + 'filter.pageSize=' + pageSize;
        doPostDataByParameters("invoice_overpayment_get_data.ix?reqType=json", data, "", "invoice-overpayment-table");
    }

    function showDialog(row) {
        var customerCode = row.find("#customer-code").val();
        var source = row.find("#source").val();
        var customerName = row.find("#customer-name").val();
        var data = {
            "overpayment.customerCode": customerCode,
            "overpayment.source": source,
            "overpayment.customerName": customerName
        };
        var buttons = {};

        loadingDialog.dialog("open");
        var content = "";
        dialog = $("#inv-overpayment-dialog").dialog({
            modal: true,
            autoOpen: false,
            buttons: buttons,
            width: "auto",
            close: function (ev, ui) {
                refreshMain();
            }
        });
        $.post("load_apply_overpayment.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#inv-overpayment-dialog").html(res.content);
                dialog.dialog("open");
            } else {
                alertDialog.html('Error: ' + res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            $('#loading').modal('hide');
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function refreshMain() {
        /*
         var pageSize = $("select#filter_pageSize option:selected").val();
         var data = $('#search-form').serialize();
         data = data + "&" + 'filter.page=' + $("#current_page").val();
         data = data + "&" + 'filter.pageSize=' + pageSize;
         doPostDataByParameters("invoice_overpayment_get_data.ix?reqType=json", data, "", "invoice-overpayment-table", false);
         */
        $("#search-form").attr("action", "invoice_overpayment.ix?filter.page=" + $("#current_page").val() + "&filter.pageSize=" + $("select#filter_pageSize option:selected").val());
        $("#search-form").submit();
    }

    function submitApplyInvReq(submitType) {
        $("#overpayment_submitType").val(submitType);
        var orgdata = $('form#frmApplyToInvoices').serialize();
        $.post("inv_ovrpmt_apply_to_inv.ix?reqType=json", orgdata, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#inv-overpayment-dialog").html(res.content);
                if (submitType == 2) {
                    messageDialog.html("<xms:localization text="Save successful." />");
                    messageDialog.dialog("open");
                    dialog.dialog("close");
                }
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }


</script>