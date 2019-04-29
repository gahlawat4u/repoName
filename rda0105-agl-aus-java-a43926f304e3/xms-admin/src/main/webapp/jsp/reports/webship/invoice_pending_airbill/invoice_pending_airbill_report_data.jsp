<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:hidden id="page" name="page"/>
<s:hidden id="orderField" name="orderField"/>
<s:hidden id="orderType" name="orderType"/>
<table class="table table-bordered mg0 table-hover" id="invoice_pending_airbill_report_table">
    <thead>
    <tr>
        <th>&nbsp;</th>
        <th><xms:localization text="Customer #"/></th>
        <th><xms:localization text="Customer Name"/></th>
        <th><xms:localization text="Airbill #"/></th>
        <th><xms:localization text="Carrier Name"/></th>
        <th><xms:localization text="Service"/></th>
        <th><xms:localization text="Weight"/></th>
        <th><xms:localization text="Pieces"/></th>
        <th><xms:localization text="Create Date"/></th>
        <th><xms:localization text="Ship Date"/></th>
        <th><xms:localization text="Destination"/></th>
        <th><xms:localization text="Dest.Country"/></th>
    </tr>
    </thead>
    <tbody>
    <tr id="search-filter">
        <td></td>
        <td><s:textfield name="customerCode" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="customerName" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="airbillNumber" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="carrierName" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="service" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="weight" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="pieces" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="createDate" cssClass="form-control form_datetime" onchange="searchOnKeypress()"/></td>
        <td><s:textfield name="shipDate" cssClass="form-control form_datetime" onchange="searchOnKeypress()"/></td>
        <td><s:textfield name="destination" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
        <td><s:textfield name="destinationCountry" cssClass="form-control" onkeyup="searchOnKeypress()"/></td>
    </tr>
    <s:if test="report==null || report.totalRecords==0">
        <tr>
            <td colspan='12'><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="report.records">
            <tr>
                <td><s:checkbox name="deletedAirbills" smid="%{shipmentId}" onclick="onCheckBoxSelect()"/></td>
                <td><s:property value="customerCode"/></td>
                <td><s:property value="customerName"/></td>
                <td><s:property value="airbillNumber"/></td>
                <td><s:property value="carrierName"/></td>
                <td><s:property value="serviceName"/></td>
                <td align="right"><s:if test="weight!=null">
                    <s:property value="weight"/>
                    <s:property value="weightUnit"/>
                </s:if></td>
                <td align="right"><s:property value="noOfPieces"/></td>
                <td><s:property value="createDate"/></td>
                <td><s:property value="shipmentDate"/></td>
                <td><s:if test="city!=null && city!=''">
                    <s:property value="city"/>, <s:property value="postalCode"/>
                </s:if></td>
                <td><s:property value="countryName"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="report.startRecord"/> <xms:localization text="to"/>
                <s:property value="report.endRecord"/> <xms:localization text="of"/> <s:property
                        value="report.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="report!=null">
                <s:if test="report.hasPrev()">
                    <a href="javascript:changePage(<s:property value="%{report.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="report.pageRange" status="count">
                    <s:if test="%{report.pageRange[#count.index] == report.currentPage}">
                        <a class="paginate_button current"><s:property value="report.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="report.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{report.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    var fieldList = ["", "customer_code", "customer_name", "airbill_number", "carrier_name", "service_name", "weight", "no_of_pieces", "create_date", "shipment_date", "city", "country_name"];
    $(document).ready(function () {
        // Enable or disable delete selected airbills button
        onCheckBoxSelect();
        // Adding sorting func for report table
        $("#invoice_pending_airbill_report_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doReport
        });
        $('.form_datetime').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: 'dd-mm-yyyy'
            /*format: 'dd-mm-yyyy, HH:ii p'*/
        });
    });
    <s:if test="report==null || report.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>