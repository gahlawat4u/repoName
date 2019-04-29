<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN SIDEBAR MENU-->
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
                                    <xms:localization text="Carrier Adjustments"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb" id="div_static">
                                        <table class="s36" id="tbl_static">
                                            <tbody>
                                            <tr>
                                                <td><xms:localization text="Submitted"/>:</td>
                                                <td><input class="form-control b6" type="text"
                                                           value='<s:property value="carrierAdjustmentCountStatic.countSubmitted" />'>
                                                </td>
                                                <td><xms:localization text="Pending"/>:</td>
                                                <td><input class="form-control b6" type="text"
                                                           value='<s:property value="carrierAdjustmentCountStatic.countPending" />'>
                                                </td>
                                                <td><xms:localization text="Disputed"/>:</td>
                                                <td><input class="form-control b6" type="text"
                                                           value='<s:property value="carrierAdjustmentCountStatic.countDisputed" />'>
                                                </td>
                                                <td><xms:localization text="Approved"/>:</td>
                                                <td><input class="form-control b6" type="text"
                                                           value='<s:property value="carrierAdjustmentCountStatic.countApproved" />'>
                                                </td>
                                                <td><xms:localization text="Disputed Denied"/>:</td>
                                                <td><input class="form-control b6" type="text"
                                                           value='<s:property value="carrierAdjustmentCountStatic.countDisputedDenied" />'>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <ul id="generalTab" class="nav nav-tabs responsive">
                                    <li class="active" style="margin-left: 10px;"><a href="#Airbill-tab"
                                                                                     data-toggle="tab"><xms:localization
                                            text="Airbill Adjustments"/></a></li>
                                    <li><a href="#Pending-tab" data-toggle="tab"><xms:localization
                                            text="Pending Request"/></a></li>
                                    <li><a href="#Disputed-tab" data-toggle="tab"><xms:localization
                                            text="Disputed Requests"/></a></li>
                                    <li><a href="#Approved-tab" data-toggle="tab"><xms:localization
                                            text="Approved Requests"/></a></li>
                                    <li><a href="#Denied-tab" data-toggle="tab"><xms:localization
                                            text="Disputed Denied Requests"/></a></li>
                                </ul>
                                <div id="generalTabContent" class="tab-content responsive">
                                    <div id="Airbill-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="portlet-body">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="caption b5">
                                                            <xms:localization text="Airbill Adjustments"/>
                                                        </div>
                                                        <p>
                                                            <b><xms:localization text="Carrier"/> : </b> <br/>
                                                            <xms:localization
                                                                    text="- You may download all or process these requests individually."/>
                                                        </p>
                                                        <input type="hidden" id="orderField" name="orderField"
                                                               value="adjustmentId"/> <input type="hidden"
                                                                                             id="orderType"
                                                                                             name="orderType"
                                                                                             value="0"/>

                                                        <div id="Airbill-tab-table"></div>
                                                        <br/>
                                                    </div>
                                                </div>
                                                <div class="form-actions text-right pal pdt10">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <button class="btn s33 s44"
                                                                    onclick="acceptSubmittedRequest()">
                                                                <xms:localization text="Accept"/>
                                                            </button>
                                                            <button class="btn s33 s44"
                                                                    onclick="rejectSubmittedRequest()">
                                                                <xms:localization text="Reject"/>
                                                            </button>
                                                            <button class="btn s33 s44" onclick="doExport(1)">
                                                                <xms:localization
                                                                        text="Download All Submitted Requests"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="Pending-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="portlet-body">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="caption b5">
                                                            <xms:localization text="Pending Request"/>
                                                        </div>
                                                        <p>
                                                            <b><xms:localization text="Carrier"/> : </b> <br/>
                                                            <xms:localization
                                                                    text="- You may download all or process these requests individually."/>
                                                        </p>

                                                        <div id="Pending-tab-table"></div>
                                                        <br/>
                                                    </div>
                                                </div>
                                                <div class="form-actions text-right pal pdt10">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <button class="btn s33 s44"
                                                                    onclick="acceptPendingRequest()">
                                                                <xms:localization text="Accept"/>
                                                            </button>
                                                            <button class="btn s33 s44"
                                                                    onclick="rejectPendingRequest()">
                                                                <xms:localization text="Reject"/>
                                                            </button>
                                                            <button class="btn s33 s44" onclick="doExport(2)">
                                                                <xms:localization text="Download All Pending Request"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="Disputed-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="portlet-body">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="caption b5">
                                                            <xms:localization text="Disputed Requests"/>
                                                        </div>
                                                        <p>
                                                            <b><xms:localization text="Carrier"/> : </b> <br/>
                                                            <xms:localization
                                                                    text="- You may download all or process these requests individually."/>
                                                        </p>

                                                        <div id="Disputed-tab-table"></div>
                                                        <br/>
                                                    </div>
                                                </div>
                                                <div class="form-actions text-right pal pdt10">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <button class="btn s33 s44" onclick="doExport(3)">
                                                                <xms:localization
                                                                        text="Download All Disputed Requests"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="Approved-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="portlet-body">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="caption b5">
                                                            <xms:localization text="Approved Requests"/>
                                                        </div>
                                                        <p>
                                                            <b><xms:localization text="Carrier"/> : </b> <br/>
                                                            <xms:localization
                                                                    text="- You may download all or process these requests individually."/>
                                                        </p>

                                                        <div id="Approved-tab-table"></div>
                                                        <br/>
                                                    </div>
                                                </div>
                                                <div class="form-actions text-right pal pdt10">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <button class="btn s33 s44" onclick="doExport(4)">
                                                                <xms:localization
                                                                        text="Download All Approved Requests"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="Denied-tab" class="tab-pane fade in">
                                        <div class="row">
                                            <div class="portlet-body">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="caption b5">
                                                            <xms:localization text="Disputed Denied Requests"/>
                                                        </div>
                                                        <p>
                                                            <b><xms:localization text="Carrier"/> : </b> <br/>
                                                            <xms:localization
                                                                    text="- You may download all or process these requests individually."/>
                                                        </p>

                                                        <div id="Denied-tab-table"></div>
                                                        <br/>
                                                    </div>
                                                </div>
                                                <div class="form-actions text-right pal pdt10">
                                                    <div class="row">
                                                        <div class="col-lg-12">

                                                            <button class="btn s33 s44" onclick="doExport(5)">
                                                                <xms:localization
                                                                        text="Download All Disputed Denied Requests"/>
                                                            </button>
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
</div>
<!--END CONTENT-->
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/pages/carrier_adjustment.js"></script>