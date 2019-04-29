<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left"
        style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Send Franchise Email Invoices"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Send Franchise Email Invoices"/></li>
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
                    <div id="area-chart-spline"
                         style="width: 100%; height: 300px; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption"><xms:localization text="Send Franchise Email Invoices"/></div>

                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">

                                            <div class="col-lg-12" style="height: 500px;">
                                                <p><xms:localization text="
															Before sending Franchise e-mail invoices, Franchise
															invoices must befrozen.<br> If you do not see a date here
															that you would expect, it is because a) the invoices have
															not been frozen, or b) the e-mail invoices have already
															been sent.<br>To send all Franchise e-mail invoices for a
															date, select a date from the below drop-down."/><br> <br>
                                                <table class="s36">
                                                    <tr>
                                                        <td><b><xms:localization text="Date"/></b></td>
                                                        <td><select id="payableSelDaterange"
                                                                    onchange="changeDateRange();">
                                                            <s:iterator value="periodList">
                                                                <option><s:property value="startDate"/> -
                                                                    <s:property value="endDate"/></option>
                                                            </s:iterator>
                                                        </select> <input name="startDate" id="startDate" type="hidden"/>
                                                            <input name="endDate" id="endDate" type="hidden"/></td>
                                                        <td>
                                                            <button class="btn s37" onclick="doSend()">
                                                                <xms:localization
                                                                        text="Send Franchise E-mail Invoices for this Date"/></button>
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
<!--END PAGE WRAPPER-->
</div>
</div>
<script type="text/javascript">
    var dateRange = $('#payableSelDaterange').val();
    var dateArr = dateRange.split(' - ');
    var startDate = dateArr[0];
    var endDate = dateArr[1];
    $('#startDate').val(startDate);
    $('#endDate').val(endDate);
    function changeDateRange() {
        var result = $('#payableSelDaterange').val();
        if (result == 'Custom date range') {
            $('.customDateRange').show().val('');
        } else {
            $('.customDateRange').hide();
            var dateArr = result.split(' - ');
            var startDate = dateArr[0];
            var endDate = dateArr[1];
            $('#startDate').val(startDate);
            $('#endDate').val(endDate);
        }
    }
    function doSend() {
        $('#loading').modal('show');
        startDate = $('#startDate').val();
        endDate = $('#endDate').val();

        postJsonByPramametersWithMessage('fpb_send_inv_ms.ix?reqType=json', {
            'startDate': startDate,
            'endDate': endDate
        }, 'loading', 'payableSelDaterange', "<xms:localization text="Sent successfully." />");
    }
</script>