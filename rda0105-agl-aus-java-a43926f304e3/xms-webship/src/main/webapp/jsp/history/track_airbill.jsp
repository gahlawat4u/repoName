<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
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

                    <div class="col-lg-6">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Track Shipments"/>
                                </div>
                                <div class="tools">
                                    <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="panel-body pan">
                                    <form action="#">
                                        <div class="form-body pal">
                                            <div class="row">
                                                <s:if test="%{trackingModels.size <= 0}">
                                                    <div class="col-lg-6">
                                                        <table class="table"
                                                               style="font-size: 11px; margin-bottom: 0px">
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Tracking Number"/></td>
                                                                <td class="td2"><s:property
                                                                        value="%{detailInfoModel.airbillNumber}"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Shipped By"/></td>
                                                                <td class="td2"><s:property
                                                                        value="%{detailInfoModel.sender.contactName}"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Signed for by"/></td>
                                                                <td class="td2"><s:property
                                                                        value="%{detailInfoModel.recever.contactName}"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Destination"/></td>
                                                                <td class="td2"><s:property
                                                                        value="%{detailInfoModel.contentDescription}"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Ship Date"/></td>
                                                                <td class="td2"><s:property
                                                                        value="%{detailInfoModel.shipmentDate}"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Status"/></td>
                                                                <td class="td2"></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <table class="table"
                                                               style="font-size: 11px; margin-bottom: 0px">
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Consignee"/></td>
                                                                <td class="td2"><s:property
                                                                        value="%{detailInfoModel.customerCode}"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Delivery Date"/></td>
                                                                <td class="td2"></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Service Type"/></td>
                                                                <td class="td2"><s:property
                                                                        value="%{detailInfoModel.service.serviceName}"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Weight"/></td>
                                                                <td class="td2"><s:property
                                                                        value="%{detailInfoModel.totalWeight}"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Dimensional Weight"/></td>
                                                                <td class="td2"><s:property
                                                                        value="%{detailInfoModel.dimWeight}"/></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </s:if>
                                                <s:else>
                                                    <s:iterator value="trackingModels" status="shipmentId">
                                                        <s:if test="#shipmentId.last == true ">
                                                            <div class="col-lg-6">

                                                                <table class="table"
                                                                       style="font-size: 11px; margin-bottom: 0px">
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Tracking Number"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="airbillNumber"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Shipped By"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="shipperName"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Signed for by"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="signatory"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Destination"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="destinationServiceArea"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Ship Date"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="shipmentDate"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Status"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="trackStatus"/></td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <div class="col-lg-6">
                                                                <table class="table"
                                                                       style="font-size: 11px; margin-bottom: 0px">
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Consignee"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="consigneeName"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Delivery Date"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="trackDate"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Service Type"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="serviceType"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Weight"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="weight"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td class="td1"><xms:localization
                                                                                text="Dimensional Weight"/></td>
                                                                        <td class="td2"><s:property
                                                                                value="weightUnit"/></td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </s:if>
                                                    </s:iterator>
                                                </s:else>


                                                <div class="col-lg-12">
                                                    <br/> <br/>

                                                    <hr class="w11">
                                                    <table class="table table-hover table-bordeed mg0" id="datatable1">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Date/Time"/></th>
                                                            <th><xms:localization text="Activity"/></th>
                                                            <th><xms:localization text="Location"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:iterator value="trackingModels">
                                                            <tr>
                                                                <td><s:property value="trackDate"/> / <s:property
                                                                        value="trackTime"/></td>
                                                                <td><s:property value="eventDescription"/></td>
                                                                <td><s:property value="serviceAreaDescription"/></td>
                                                            </tr>
                                                        </s:iterator>

                                                        </tbody>
                                                    </table>
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
<!--END CONTENT-->
