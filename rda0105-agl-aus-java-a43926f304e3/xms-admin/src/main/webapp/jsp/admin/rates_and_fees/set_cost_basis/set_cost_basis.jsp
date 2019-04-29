<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Match Cost Basic Tables (Carrier Cost Rate Sheet)"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Match Cost Basic Tables (Carrier Cost Rate Sheet)"/></li>
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
                                    <xms:localization text="Match Cost Basic Tables (Carrier Cost Rate Sheet)"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                </table>
                                                <div id="set-cost-basis-table">
                                                    <table class="table table-bordered mg0  table-hover"
                                                           style="table-layout: fixed;">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Service Description"/></th>
                                                            <th><xms:localization text="Carrier"/></th>
                                                            <th><xms:localization text="Tier"/></th>
                                                            <th><xms:localization text="Current Value"/></th>
                                                            <th class="w14"><xms:localization
                                                                    text="Cost Basic Table"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr id="search-filter">
                                                            <td><s:textfield name="searchFilter.serviceDescription"
                                                                             cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td><s:textfield name="searchFilter.carrier"
                                                                             cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td></td>
                                                            <td><s:textfield name="searchFilter.currentValue"
                                                                             cssClass="form-control"
                                                                             onkeyup="searchOnKeypress()"/></td>
                                                            <td></td>
                                                        </tr>
                                                        <s:if test="records.isEmpty()">
                                                            <tr>
                                                                <td colspan="5"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="records">
                                                                <tr data-shipment-type-id='<s:property value="shipmentTypeId"/>'>
                                                                    <td><s:property value="shipmentTypeName"/>
                                                                        <s:property value="packageTypeName"/></td>
                                                                    <td><s:property value="serviceName"/></td>
                                                                    <td></td>
                                                                    <td id='sheetName-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>-<s:property value="isPerWeight"/>'>
                                                                        <s:property value="sheetName"/></td>
                                                                    <td width="290px"><s:select
                                                                            id="sheetId_%{shipmentTypeId}_%{content}_%{bound}_%{isPerWeight}"
                                                                            value="sheetId" list="rateSheets"
                                                                            listValue="sheetName" listKey="sheetId"
                                                                            cssStyle="width: 200px;" cssClass="w12"
                                                                            headerKey="0" headerValue=""/>
                                                                        <button class="w13"
                                                                                onclick="saveSheet('<s:property value="shipmentTypeId"/>','<s:property value="content"/>','<s:property value="bound"/>','<s:property value="isPerWeight"/>')">
                                                                            <xms:localization text="Set"/>
                                                                        </button>
                                                                    </td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
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
</div>
<script type="text/javascript">
    function saveSheet(shipmentTypeId, content, bound, isPerWeight) {
        var sheetId = $("#sheetId_" + shipmentTypeId + "_" + content + "_" + bound + "_" + isPerWeight).val();
        var data = {
            "sheetId": sheetId,
            "shipmentTypeId": shipmentTypeId,
            "content": content,
            "bound": bound,
            "isPerWeight": isPerWeight
        };
        var sheetTd = "sheetName-" + shipmentTypeId + "-" + content + "-" + bound + "-" + isPerWeight;
        doPostDataByParameters("set_cost_basis_set.ix?reqType=json", data, "<xms:localization text="Set Successfully!"/>", sheetTd, true, true);
    }

    function search() {
        var data = $("#search-filter input").serialize();
        doPostDataByParameters("set_cost_basis_search.ix?reqType=json", data, "", "set-cost-basis-table", false);
    }

    function searchOnKeypress() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            search();
        }, 500);
        $(this).data('timer', wait);
    }
</script>
<!--END CONTENT-->
