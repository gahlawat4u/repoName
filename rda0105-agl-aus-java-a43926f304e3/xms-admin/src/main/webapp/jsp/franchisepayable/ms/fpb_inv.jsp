<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Franchise Payables Invoice"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Franchise Payables Invoice"/></li>
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
                                    <xms:localization text="Franchise Payables Invoice"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <table class="s36">
                                            <tr>
                                                <td><xms:localization text="Franchise:"/></td>
                                                <td><select id="selFranchiseCode" name="franchiseCode"
                                                            class="form-control">
                                                    <s:iterator value="franchiseList">
                                                        <option value="<s:property value='code'/>"><s:property
                                                                value='code'/></option>
                                                    </s:iterator>
                                                </select></td>
                                                <td><xms:localization text="Date Range:"/></td>
                                                <td>
                                                <td><s:select list="dateRange" value="" class="form-control"
                                                              id="invSelDaterange"
                                                              onchange="changeDateRange();"></s:select></td>
                                                <td class="customDateRange" style="display: none"><s:textfield
                                                        name="startDate" class="form-control date form_datetime"
                                                        id="startDate" placeholder="Start Date"
                                                        data-date-format="dd MM yyyy"
                                                        readonly="true"></s:textfield></td>
                                                <td class="customDateRange" style="display: none"><s:textfield
                                                        name="endDate" class="form-control date form_datetime"
                                                        id="endDate" placeholder="End Date"
                                                        data-date-format="dd MM yyyy"
                                                        readonly="true"></s:textfield></td>
                                                <td>
                                                    <button class="btn s37" onclick="get_fpb_invoice();">
                                                        <xms:localization text="Go"/>
                                                    </button>
                                                    <input id="rptTxnId" type="hidden"
                                                           value='<s:property value="rptTxnId"/>'/></td>
                                                <!-- <td><button class="btn s37 btn_export" disabled="disabled">
                                                                Printable Report</button></td> -->
                                                <td>
                                                    <button class="btn s37 btn_export" disabled="disabled"
                                                            onclick="do_export_inv()">
                                                        <xms:localization text="PDF Report"/>
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive" id="inv-content">
                                    <div class="row">
                                        <div class="col-lg-5">
                                            <p class="s38">
                                                <xms:localization text="Recipient Created Tax Invoice"/>
                                            </p>
                                            <table class="table" style="font-size: 11px;">
                                                <tr>
                                                    <td class="td1"><img
                                                            src="<%=WebUtils.getContextPathURL(request)%>images/LOGOiN.png"
                                                            width="140"/></td>
                                                    <td class="td2"></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="col-lg-5">
                                            <p class="s38" style="color: white">-</p>
                                            <table class="table" style="font-size: 11px;">
                                                <tr>
                                                    <td class="td1"><xms:localization text="BILL TO:"/></td>
                                                    <td class="td2"><s:property escape="false" value="systemAddress"/>
                                                        <br/> <s:property value="siteAddress"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="col-lg-12">
                                            <p class="s38">
                                                <xms:localization text="Commissions Payable As Per Agreement"/>
                                            </p>

                                            <p>
                                                <xms:localization text="Date Range:"/>
                                                <br>
                                            <table class="table mg0">
                                                <tr>
                                                    <th class="s42"></th>
                                                </tr>
                                            </table>
                                            <table class="table table-hover table-striped table-bordered mg0"
                                                   id="datatable" style="float: left">
                                                <thead>
                                                <tr>
                                                    <th><xms:localization text="Item"/></th>
                                                    <th><xms:localization text="Description"/></th>
                                                    <th><xms:localization text="Amount"/></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                <tr>
                                                    <td colspan="3"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Payables :"/></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="1"/></td>
                                                    <td><xms:localization text="Margin Share"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="2"/></td>
                                                    <td><xms:localization text="61+ Payment Margin Share"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="3"/></td>
                                                    <td><xms:localization text="Non Centralised Margin Share"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="4"/></td>
                                                    <td><xms:localization text="Late Fee"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Gross Payables :"/></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="5"/></td>
                                                    <td><xms:localization text="Gross Payables"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Other Payables :"/></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="6"/></td>
                                                    <td><xms:localization text="Repaid Carrier Deductions"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Costs :"/></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="7"/></td>
                                                    <td><xms:localization text="Carrier Cost Deductions"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="8"/></td>
                                                    <td><xms:localization text="Tech Fees"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="9"/></td>
                                                    <td><xms:localization text="Marketing Fee etc"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Net Payables :"/></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="10"/></td>
                                                    <td><xms:localization text="Net Payables"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text=""/></td>
                                                    <td><xms:localization text="GST on Net Payables"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Reimbursements :"/></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="11"/></td>
                                                    <td><xms:localization text="Non-Central Carrier Costs"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text=""/></td>
                                                    <td><xms:localization text="GST from Non-Central Carrier Costs"/></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Total Payables :"/></td>
                                                </tr>
                                                <tr>
                                                    <td><xms:localization text="12"/></td>
                                                    <td><xms:localization text="Total Payables"/></td>
                                                    <td></td>
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
    </div>
</div>
</div>
<!--END PAGE WRAPPER-->
</div>
<script type="text/javascript">
    var dateRange = $('#invSelDaterange').val();
    var dateArr = dateRange.split(' - ');
    var startDate = dateArr[0];
    var endDate = dateArr[1];
    $('#startDate').val(startDate);
    $('#endDate').val(endDate);
    function changeDateRange() {
        var result = $('#invSelDaterange').val();
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
    var dtStartDate = "";
    var dtEndDate = "";
    var dtFranchiseCode = "";
    function get_fpb_invoice() {
        dtStartDate = $('#startDate').val();
        dtEndDate = $('#endDate').val();
        dtFranchiseCode = $('#selFranchiseCode').val();
        $('#loading').modal('show');

        postJsonByPramameters('fpb_get_inv_ms.ix?reqType=json', {
            'startDate': dtStartDate,
            'endDate': dtEndDate,
            'franchiseCode': dtFranchiseCode
        }, "loading", 'inv-content', 'btn_export');
    }
    function do_export_inv() {
        rptTxnId = $('#rptTxnId').val();
        var url = "fpb_export_inv_ms.ix?startDate=" + dtStartDate + "&endDate=" + dtEndDate + "&franchiseCode=" + dtFranchiseCode + "&rptTxnId=" + rptTxnId;
        window.open(url, '_blank');
    }
</script>
