<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:if test="hasActionErrors()">
    <s:actionerror/>
</s:if>
<s:else>
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
                                                    <div class="col-lg-6">
                                                        <table class="table s99" style="margin-bottom: 0px">
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Tracking Number"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.trackingNumber"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Shipped By"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.shippedBy"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Signed for by"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.signedForBy"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Destination"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.destination"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Status"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.status"/></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <table class="table s99" style="margin-bottom: 0px">
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Consignee"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.consignee"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Ship Date"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.shipDate"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Delivery Date"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.deliveryDate"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Service Type"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.serviceType"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Weight"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.weight"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Dimensional Weight"/></td>
                                                                <td class="td2"><s:property
                                                                        value="starTrack.dimensionWeight"/></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <br/> <br/>
                                                        <hr class="w11">
                                                        <table class="table table-hover table-bordeed mg0"
                                                               id="datatable1">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Date/Time"/></th>
                                                                <th><xms:localization text="Location"/></th>
                                                                <th><xms:localization text="Description"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:iterator value="starTrack.events">
                                                                <tr>
                                                                    <td><s:property value="dateTime"/></td>
                                                                    <td><s:property value="location"/></td>
                                                                    <td><s:property value="description"/></td>
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
</s:else>