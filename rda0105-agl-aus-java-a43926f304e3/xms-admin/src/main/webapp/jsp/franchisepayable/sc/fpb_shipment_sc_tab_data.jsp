<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-12">
        <p class="s38">
            <xms:localization text="Shipment Details"/>
        </p>

        <p>
            <s:set var="paramMap" value="#{'{startDate}':'startDate', '{endDate}':'endDate'}"></s:set>
            <xms:localization
                    text="Please Note:</br> - The details below list only invoices that have payment from {startDate} to {endDate} .The invoices that do not have any payment are not listed."
                    paramMap="paramMap"/>
        </p>
        <table class="table mg0">
            <tr>
                <th class="s42">
                    <table class="s36">
                        <tbody>
                        <tr>
                            <td><xms:localization text="Show"/></td>
                            <td><s:select list="listPageSize" value="recordSize" id="selPageSizeShipmentDetail"
                                          cssClass="form-control"
                                          cssStyle="height: 22px; padding-top: 1px; width: 65px;"
                                          onchange="changePageSelShipmentDetail()"/></td>
                            <td><xms:localization text="Entries"/></td>
                        </tr>
                        </tbody>
                    </table>
                </th>
            </tr>
        </table>
        <table class="table table-hover table-striped table-bordered mg0">
            <thead>
            <tr>
                <th width="100px;" rowspan="2"><xms:localization text="Import Date"/></th>
                <th rowspan="2"><xms:localization text="Customer No"/></th>
                <th rowspan="2"><xms:localization text="Customer Name"/></th>
                <th rowspan="2"><xms:localization text="Invoice #"/></th>
                <th rowspan="2"><xms:localization text="AWB/Connote No"/></th>
                <th rowspan="2"><xms:localization text="International Domestic"/></th>
                <th colspan="2"><xms:localization text="Customer Total"/></th>
                <th rowspan="2"><xms:localization text="Customer Marginable Cost"/></th>
                <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                <th colspan="2"><xms:localization text="Gross Margin"/></th>
                <th colspan="2"><xms:localization text="Credits"/></th>
                <th rowspan="2"><xms:localization text="Management Fees on Revenue"/></th>
                <th rowspan="2"><xms:localization text="Marketing Fees on Revenue"/></th>
                <th rowspan="2"><xms:localization text="Profit Share"/></th>
            </tr>
            <tr>
                <th><xms:localization text="Price Ex TVA"/></th>
                <th><xms:localization text="TVA"/></th>
                <th><xms:localization text="Price Ex TVA"/></th>
                <th><xms:localization text="TVA"/></th>
                <th><xms:localization text="Price Ex TVA"/></th>
                <th><xms:localization text="TVA"/></th>
                <th><xms:localization text="Franchisee Cost."/></th>
                <th><xms:localization text="Customer Cost."/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="shipments.isEmpty()">
                <tr>
                    <td colspan="17"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="shipments">
                    <tr>
                        <td><s:property value="importDate"/></td>
                        <td><s:property value="customerCode"/></td>
                        <td><s:property value="customerName"/></td>
                        <td><s:property value="invoiceCode"/></td>
                        <td><s:property value="airbillNumber"/></td>
                        <td><s:if test="isDomestic=='true'">
                            <xms:localization text="Domestic"/>
                        </s:if> <s:else>
                            <xms:localization text="International"/>
                        </s:else></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property value="custCost"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property value="custTax"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="custMarginable"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property value="franCost"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property value="franTax"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="grossMargin"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="grossMarginTax"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property value="franCredit"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property value="custCredit"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="managementFee"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="marketingFee"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="profitShare"/></td>
                    </tr>
                </s:iterator>
            </s:else>
            <s:if test="page==pageCount">
                <tr>
                    <th colspan="6"><xms:localization text="Total taxable shipment(s):"/> <s:property
                            value="shipmentTotal.taxableShipmentCount"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.custCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.custTax"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.custMarginable"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.franCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.franTax"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.grossMargin"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.grossMarginTax"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.franCredit"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.custCredit"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.managementFee"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.marketingFee"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="taxableShipmentTotal.profitShare"/></th>
                </tr>
                <tr>
                    <th colspan="6"><xms:localization text="Total non-taxable shipment(s):"/> <s:property
                            value="shipmentTotal.nonTaxableShipmentCount"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.custCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.custTax"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.custMarginable"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.franCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.franTax"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.grossMargin"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.grossMarginTax"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.franCredit"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.custCredit"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.managementFee"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.marketingFee"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="nonTaxableShipmentTotal.profitShare"/></th>
                </tr>
                <tr>
                    <th colspan="6"><xms:localization text="Total"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.custCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.custTax"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.custMarginable"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.franCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.franTax"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.grossMargin"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.grossMarginTax"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.franCredit"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.custCredit"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.managementFee"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.marketingFee"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="shipmentTotal.profitShare"/></th>
                </tr>
            </s:if>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12">
        <div class="dataTables_paginate">
            <s:if test="%{page > 1}">
                <a href="javascript:changePageShipmentDetail('<s:property value="%{page-1}"/>');"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:if>
            <s:iterator var="count" begin="1" end="pageCount">
                <s:if test="%{(page-4) < top && top < (page+4)}">
					<span> <a href="javascript:changePageShipmentDetail('<s:property value="top"/>');"
                              class="paginate_button <s:if test="%{page==top}">current</s:if>"
                              data-targetpage="<s:property value="top"/>"> <s:property value="top"/>
                    </a>
					</span>
                </s:if>
                <s:elseif test="%{top>(page+4) && top<(page+6) && (pageCount - page) >=6}">
                    <span>...</span>
                </s:elseif>
                <s:elseif test="%{top>(pageCount-7)}">
                    <span><a href="javascript:changePageShipmentDetail('<s:property value="top"/>');"
                             class="paginate_button" data-targetpage="<s:property value="top"/>"><s:property
                            value="top"/></a></span>
                </s:elseif>
            </s:iterator>
            <s:if test="%{page < pageCount}">
                <a href="javascript:changePageShipmentDetail('<s:property value="%{page+1}"/>');"
                   class="paginate_button next"><xms:localization text="Next"/></a>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    function changePageShipmentDetail(page) {
        var recordSize = $('#selPageSizeShipmentDetail').val();
        getShipmentDetail(page, recordSize);
    }
    function changePageSelShipmentDetail() {
        var recordSize = $('#selPageSizeShipmentDetail').val();
        getShipmentDetail(1, recordSize);
    }
    $(document).ready(function () {
        $('#rptTxnId').val('<s:property value="rptTxnId" />');
        $('#frozen-message').html('<s:property value="frozenMessage"/>');
    });


</script>