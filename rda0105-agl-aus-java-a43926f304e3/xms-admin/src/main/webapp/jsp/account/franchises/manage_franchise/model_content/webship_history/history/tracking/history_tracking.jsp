<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><a href="#"><xms:localization text="Tracking"/></a></li>
    </ol>
    <div class="clearfix"></div>
</div>
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
                                                                <td class="td2">${detailInfoModel.tracking}</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Shipped By"/></td>

                                                                <td class="td2">${detailInfoModel.sCompanyName}</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Signed for by"/></td>
                                                                <td class="td2">
                                                                    <s:if test="trackingModels != null">
                                                                        <s:iterator value="trackingModels" var="">
                                                                            <s:if test="eventDescription.contains('Delivered')">
                                                                                ${detailInfoModel.rContactName}
                                                                            </s:if>
                                                                        </s:iterator>
                                                                    </s:if>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Destination"/></td>
                                                                <td class="td2">${detailInfoModel.rCity}- ${detailInfoModel.rCountryName}</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Status"/></td>
                                                                <td class="td2"><s:if test="!trackingModels.isEmpty() ">
                                                                    ${trackingModels[0].eventDescription}
                                                                </s:if></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <table class="table s99" style="margin-bottom: 0px">
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Consignee"/></td>
                                                                <td class="td2">${detailInfoModel.rCompanyName}</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Ship Date"/></td>
                                                                <td class="td2">${detailInfoModel.shipmentDate}</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Delivery Date"/></td>
                                                                <td class="td2"><s:if test="trackingModels != null">
                                                                    <s:iterator value="trackingModels" var="">
                                                                        <s:if test="eventDescription.contains('Delivered')">
                                                                            <s:property value="trackDate"/>
                                                                            <s:property value="trackTime"/>
                                                                        </s:if>
                                                                    </s:iterator>
                                                                </s:if></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Service Type"/></td>
                                                                <td class="td2">${detailInfoModel.serviceType}</td>
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
                                                                <th><xms:localization text="Activity"/></th>
                                                                <th><xms:localization text="Location"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="!trackingModels.isEmpty() ">
                                                                <s:iterator value="trackingModels">
                                                                    <tr>
                                                                        <s:if test="trackTime != '' && trackTime != null && trackDate != '' && trackDate != null">
                                                                            <td>
                                                                                <strong>${trackDate}</strong><br/>${trackTime}
                                                                            </td>
                                                                        </s:if>
                                                                        <s:elseif
                                                                                test="trackTime != '' && trackTime != null && (trackDate == '' || trackDate == null)">
                                                                            <td>${trackTime}</td>
                                                                        </s:elseif>
                                                                        <s:else>
                                                                            <td>${trackDate}</td>
                                                                        </s:else>
                                                                        <td><s:property value="eventDescription"/></td>
                                                                        <td><s:property value="originServiceArea"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                            </s:if>
                                                            <s:else>
                                                                <tr>
                                                                    <td colspan="3"><s:property
                                                                            value="#request.error_message"/></td>
                                                                </tr>
                                                            </s:else>
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
