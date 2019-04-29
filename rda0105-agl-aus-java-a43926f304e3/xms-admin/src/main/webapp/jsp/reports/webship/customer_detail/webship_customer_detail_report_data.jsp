<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:hidden id="page" name="page"/>
<s:hidden id="orderField" name="orderField"/>
<s:hidden id="orderType" name="orderType"/>
<table id="webship_customer_detail_report_table" class="table table-bordered mg0 table-hover">
    <thead>
    <tr>
        <th><xms:localization text="Invoice #"/></th>
        <th><xms:localization text="Airbill #"/></th>
        <th><xms:localization text="Customer #"/></th>
        <th><xms:localization text="Shipment Date"/></th>
        <th><xms:localization text="Sender Address"/></th>
        <th><xms:localization text="Receiver Address"/></th>
        <th><xms:localization text="Pieces"/></th>
        <th><xms:localization text="Weight"/></th>
        <th><xms:localization text="Quoted"/></th>
        <th><xms:localization text="Charges"/></th>
        <th><xms:localization text="Total"/></th>
        <th><xms:localization text="Service Type"/></th>
        <th><xms:localization text="Accessorial"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="report==null || report.totalRecords==0">
        <tr>
            <td colspan='13'><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="report.records">
            <tr>
                <td><s:property value="invoiceCode"/></td>
                <td><s:property value="airbillNumber"/></td>
                <td><s:property value="customerCode"/></td>
                <td><s:property value="shipmentDate"/></td>
                <td><s:property value="senderCompanyName"/><br/> <s:property value="senderContactName"/><br/>
                    <s:property value="senderAddress1"/><br/> <s:property value="senderAddress2"/><br/> <s:property
                            value="senderCity"/> <s:property value="senderPostalCode"/> <s:property
                            value="senderState"/> <s:property value="senderCountryName"/></td>
                <td><s:property value="receiverCompanyName"/><br/> <s:property value="receiverContactName"/><br/>
                    <s:property value="receiverAddress1"/><br/> <s:property value="receiverAddress2"/><br/> <s:property
                            value="receiverCity"/> <s:property value="receiverPostalCode"/> <s:property
                            value="receiverState"/> <s:property value="receiverCountryName"/></td>
                <td><s:property value="noOfPieces"/></td>
                <td><s:property value="weight"/> <s:property value="weightUnit"/></td>
                <td><s:property value="quoted"/></td>
                <td><s:iterator value="charges">
                    <s:property value="description"/> - <s:property value="currencySymbol"/>
                    <s:property value="customerCost"/> (<s:property value="currencySymbol"/>
                    <s:property value="franchiseCost"/>) [<s:property value="currencySymbol"/>
                    <s:property value="margin"/>] <br/>
                </s:iterator></td>
                <td><s:property value="currencySymbol"/> <s:property value="totalCustomerCost"/><br/> (<s:property
                        value="currencySymbol"/> <s:property value="totalFranchiseCost"/>)<br/> <xms:localization
                        text="Mrg:"/> <s:property value="currencySymbol"/> <s:property value="totalMargin"/></td>
                <td><s:property value="serviceType"/></td>
                <td><xms:localization text="Insurance:"/> <s:if test="'true'.equalsIgnoreCase(insurance)">
                    <xms:localization text="Yes"/>
                </s:if> <s:else>
                    <xms:localization text="No"/>
                </s:else><br/> <xms:localization text="Duties/Taxes Fee:"/> <s:property value="dutiesTaxesFee"/><br/>
                    <xms:localization text="Dangerous Goods:"/> <s:if test="'true'.equalsIgnoreCase(dangerousGoods)">
                        <xms:localization text="Yes"/>
                    </s:if> <s:else>
                        <xms:localization text="No"/>
                    </s:else><br/> <xms:localization text="Pre Clearance:"/> <s:if
                            test="'true'.equalsIgnoreCase(preClearance)">
                        <xms:localization text="Yes"/>
                    </s:if> <s:else>
                        <xms:localization text="No"/>
                    </s:else><br/> <s:iterator value="charges">
                        <s:if test="!'true'.equalsIgnoreCase(isBaseCharge)">
                            <s:property value="description"/>
                            <br/>
                        </s:if>
                    </s:iterator></td>
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
    var fieldList = ["invoice_code", "airbill_number", "customer_code", "shipment_date", "sender_company_name", "receiver_company_name", "no_of_pieces", "weight", "quoted", "", "", "service_type", ""];
    $(document).ready(function () {
        $("#webship_customer_detail_report_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doReport
        });
    });
    <s:if test="report==null || report.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>