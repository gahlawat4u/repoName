<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="CSV Invoice"/>
        </li>
    </ol>
    <div class="clearfix"></div>
</div>
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
                                    <xms:localization text="Download .CSV Invoice"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12" style="height: 500px;">
                                                <p>
                                                    <xms:localization
                                                            text="<b>Note:</b> <br/> - Select a customer from the list below. Customers must be enabled for .CSV invoices by a corporate admin. If your customer does not appear in the list below, contact a corporate administrator."/>
                                                    <br/>
                                                </p>
                                                <table class="s36">
                                                    <tr>
                                                        <td><s:select cssClass="form-control" list="franchises"
                                                                      listKey="code" listValue="code"
                                                                      onchange="changeFranchiseCode($(this).val())"/></td>
                                                        <td id="invoices-list"><s:select list="invoicesList"
                                                                                         cssClass="form-control"
                                                                                         listKey="invoiceId"
                                                                                         listValue="invoiceStr"
                                                                                         id="sel-invoices"/></td>
                                                        <td>
                                                            <button class="btn s37" type="button"
                                                                    onclick="doExportCsv()">
                                                                <xms:localization text="Download .CSV Invoice"/>
                                                            </button>
                                                        </td>
                                                    </tr>
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
        </div>
    </div>
</div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    var submmitFranchiseCode = "";
    function changeFranchiseCode(franchiseCode) {
        submmitFranchiseCode = franchiseCode;
        doPostDataByParameters("csv_invoices_get_list_invoices.ix?reqType=json", {
            "franchiseCode": franchiseCode
        }, "", "invoices-list", true, true);
    }
    function doExportCsv() {
        var invoiceId = $("#sel-invoices").val();
        var data = {
            "franchiseCode": submmitFranchiseCode,
            "invoiceId": invoiceId
        };
        loadingDialog.dialog("open");
        $.fileDownload("csv_invoices_export_csv.ix", {
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
    }


</script>