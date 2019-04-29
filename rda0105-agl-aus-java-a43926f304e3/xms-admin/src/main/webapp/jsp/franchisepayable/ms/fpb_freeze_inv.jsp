<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left"
        style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Freeze Franchise Payables"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Freeze Franchise Payables"/></li>
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
                                <div class="caption"><xms:localization text="Freeze Franchise Invoices"/></div>

                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">

                                            <div class="col-lg-12" style="height: 500px;">


                                                <p><xms:localization text="
															Befor printing or sending e-mail invoices, Franchise
															invoices must be frozen. Freezing Franchise invoices for
															a particular date prevents frees and totals from being
															edited, marking the Franchise invoices total permanent.<br>
															To freeze all Franchise invoices for a date, select a
															date from the below drop-down."/><br> <br>
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
                                                            <button class="btn s37" onclick="doFreeze()">
                                                                <xms:localization
                                                                        text="Freeze Franchise invoices for this Date"/></button>
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
</div>
</div>
<!--END CONTENT-->

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
    function doFreeze() {
        $('#loading').modal('show');
        startDate = $('#startDate').val();
        endDate = $('#endDate').val();

        postJsonByPramametersWithMessage('fpb_freeze_inv_ms.ix?reqType=json', {
            'startDate': startDate,
            'endDate': endDate
        }, 'loading', 'payableSelDaterange', "<xms:localization text="Froze successfully." />");
    }
</script>
