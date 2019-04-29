<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Move Unfrozen Invoices"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Move Unfrozen Invoices"/></li>
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
                                    <xms:localization text="Move Unfrozen Invoices"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <form id="move_invoices_form">
                                            <div class="row">
                                                <div class="portlet-body b12 b11">
                                                    <table class="s36">
                                                        <tr>
                                                            <td><p>
                                                                <xms:localization
                                                                        text="This moves *all* unfrozen airbills invoices for a particular data to a new date. It will not move airbills onto frozen invoices."/>
                                                            </p></td>
                                                        </tr>
                                                        <tr>
                                                            <td><p>
                                                                <b><xms:localization text="From:"/> </b>
                                                            </p></td>
                                                        </tr>
                                                        <tr>
                                                            <td><p>
                                                                <xms:localization
                                                                        text="Existing (Unfrozen) Invoice Dates"/>
                                                            </p></td>
                                                        </tr>
                                                        <tr>
                                                            <td id="invoice_date_list"><s:select list="dateList"
                                                                                                 name="fromDate"
                                                                                                 cssClass="form-control"
                                                                                                 cssStyle="margin-bottom: 10px; width: 150px"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><p>
                                                                <b><xms:localization text="To:"/></b>
                                                            </p></td>
                                                        </tr>
                                                        <tr>
                                                            <td><p>
                                                                <xms:localization text="New invoice date:"/>
                                                                <span class="s30">*</span>
                                                            </p></td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                <div class="form-group input-group mg0"
                                                                     style="margin-bottom: 10px; width: 150px">
																	<span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i>
																	</span> <input name="toDate"
                                                                                   class="form-control form_datetime"
                                                                                   type="text"
                                                                                   data-date-format="dd MM yyyy"
                                                                                   placeholder="Date">
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                    <table class="s36 form-group">
                                                        <tr>
                                                            <td><input id="chkExclude" type="checkbox" name="exclude"
                                                                       onclick="excludeChange()"/></td>
                                                            <td><xms:localization
                                                                    text="Exclude Customers(comma-separated list)"/></td>
                                                            <td><input id="customerList" name="customerList"
                                                                       class="form-control" type="text"
                                                                       disabled="disabled"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="form-actions pal pdt10">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="form-group text-left s99">
                                                            <div class="form-group">
                                                                <button class="btn s37" type="button" onclick="move()">
                                                                    <xms:localization
                                                                            text="Move all Airbills / Invoices"/>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
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
    function move() {
        var data = $("#move_invoices_form").serialize();
        loadingDialog.dialog("open");
        $.post("move_invoices_dates_move.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                messageDialog.html(res.content.trim());
                messageDialog.dialog("open");
                // Get new date list.
                $.post("move_invoices_dates_get_dates.ix?reqType=json", function (response) {
                    if (response.errorCode == "SUCCESS") {
                        $("#invoice_date_list").html(response.content);
                    }
                });
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

    function excludeChange() {
        var isExclude = $("#chkExclude").is(":checked");
        console.log(isExclude);
        if (isExclude) {
            $("#customerList").prop('disabled', false);
        } else {
            $("#customerList").prop('disabled', true);
        }
    }


</script>