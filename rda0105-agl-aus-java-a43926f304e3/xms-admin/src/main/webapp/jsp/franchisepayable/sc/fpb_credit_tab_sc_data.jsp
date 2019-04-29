<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-12">
        <p class="s38">
            <xms:localization text="Carrier Credit Details"/>
        </p>

        <div class="form-group flr mgb">
            <table class="">
                <tr>
                    <td></td>
                </tr>
            </table>
        </div>
        <p>
            <s:set var="paramMap" value="#{'{startDate}':'startDate', '{endDate}':'endDate'}"></s:set>
            <xms:localization
                    text="Please Note: </br> -The details below list only invoices that received carrier adjustment credits from {startDate} {endDate}."
                    paramMap="paramMap"/>
        </p>
        <table class="table mg0">
            <tr>
                <th class="s42">
                    <table class="s36">
                        <tbody>
                        <tr>
                            <td><xms:localization text="Show"/></td>
                            <td><s:select id="selPageSizeCarrierCredit" list="listPageSize" cssClass="form-control"
                                          onchange="changePageSelCarrierCredit()"
                                          cssStyle="height: 22px; padding-top: 1px; width: 65px;"
                                          value="recordSize"/></td>
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
                <th colspan="7"></th>
                <th colspan="6"><i><xms:localization text="Original Invoice"/></i></th>
                <th colspan="4"><i><xms:localization text="Credit"/></i></th>
                <th colspan="2"></th>
            </tr>
            <tr>
                <th width="100px;"><xms:localization text="Date"/></th>
                <th><xms:localization text="Customer No"/></th>
                <th><xms:localization text="Customer Name"/></th>
                <th><xms:localization text="Invoice #"/></th>
                <th><xms:localization text="Credit Note Number"/></th>
                <th><xms:localization text="AWB/Connote No"/></th>
                <th><xms:localization text="International Domestic"/></th>
                <th colspan="2"><xms:localization text="Customer Total"/></th>
                <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                <th colspan="2"><xms:localization text="Gross Margin"/></th>
                <th colspan="2"><xms:localization text="Customer Total"/></th>
                <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                <th colspan="2"><xms:localization text="New Margin"/></th>
            </tr>
            <tr>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th><xms:localization text="Price Ex TVA"/></th>
                <th><xms:localization text="TVA"/></th>
                <th><xms:localization text="Price Ex TVA"/></th>
                <th><xms:localization text="TVA"/></th>
                <th><xms:localization text="Price Ex TVA"/></th>
                <th><xms:localization text="TVA"/></th>
                <th><xms:localization text="Price Ex TVA"/></th>
                <th><xms:localization text="TVA"/></th>
                <th><xms:localization text="Price Ex TVA"/></th>
                <th><xms:localization text="TVA"/></th>
                <th><xms:localization text="Price Ex TVA"/></th>
                <th><xms:localization text="TVA"/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="carrierCreditDetails.isEmpty()">
                <tr>
                    <td colspan="19"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="carrierCreditDetails">
                    <tr>
                        <td><s:property value="paymentDate"/></td>
                        <td><s:property value="customerNumber"/></td>
                        <td><s:property value="customerName"/></td>
                        <td><s:property value="invoiceNumber"/></td>
                        <td>&nbsp;</td>
                        <td><s:property value="airbillNumber"/></td>
                        <td><s:property value="internationalDomestic"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="customerTotalExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="customerTotalGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="franchiseCostExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="franchiseCostGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="grossMarginExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="grossMarginGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="creditsCustomerCostExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="creditsCustomerCostGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="creditsFranchiseCostExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="creditsFranchiseCostGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="newMarginExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/>
                            <s:property value="newMarginGst"/></td>
                    </tr>
                </s:iterator>
            </s:else>
            <s:if test="page == pageCount">
                <tr>
                    <th colspan="7"><xms:localization text="Total taxable shipment(s):"/> <s:property
                            value="carrierCreditDetailsTotal.taxableShipmentCount"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.customerTotalExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.customerTotalGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.franchiseCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.franchiseCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.grossMarginExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.grossMarginGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.creditsCustomerCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.creditsCustomerCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.creditsFranchiseCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.creditsFranchiseCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.newMarginExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="taxableCarrierCreditTotal.newMarginGst"/></th>
                </tr>
                <tr>
                    <th colspan="7"><xms:localization text="Total non-taxable shipment(s):"/> <s:property
                            value="carrierCreditDetailsTotal.nonTaxableShipmentCount"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.customerTotalExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.customerTotalGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.franchiseCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.franchiseCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.grossMarginExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.grossMarginGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.creditsCustomerCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.creditsCustomerCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.creditsFranchiseCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.creditsFranchiseCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.newMarginExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="nonTaxableCarrierCreditTotal.newMarginGst"/></th>
                </tr>
                <tr>
                    <th colspan="7"><xms:localization text="Total"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.customerTotalExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.customerTotalGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.franchiseCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.franchiseCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.grossMarginExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.grossMarginGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.creditsCustomerCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.creditsCustomerCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.creditsFranchiseCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.creditsFranchiseCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.newMarginExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="carrierCreditDetailsTotal.newMarginGst"/></th>
                </tr>
            </s:if>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12">
        <div class="dataTables_paginate">
            <s:if test="%{page > 1}">
                <a href="javascript:changePageCarrierCredit('<s:property value="%{page-1}"/>');"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:if>
            <s:iterator var="count" begin="1" end="pageCount">
                <s:if test="%{(page-4) < top && top < (page+4)}">
					<span> <a href="javascript:changePageCarrierCredit('<s:property value="top"/>');"
                              class="paginate_button <s:if test="%{page==top}">current</s:if>"
                              data-targetpage="<s:property value="top"/>"> <s:property value="top"/>
                    </a>
					</span>
                </s:if>
                <s:elseif test="%{top>(page+4) && top<(page+6) && (pageCount - page) >=6}">
                    <span>...</span>
                </s:elseif>
                <s:elseif test="%{top>(pageCount-7)}">
                    <span><a href="javascript:changePageCarrierCredit('<s:property value="top"/>');"
                             class="paginate_button" data-targetpage="<s:property value="top"/>"><s:property
                            value="top"/></a></span>
                </s:elseif>
            </s:iterator>
            <s:if test="%{page < pageCount}">
                <a href="javascript:changePageCarrierCredit('<s:property value="%{page+1}"/>');"
                   class="paginate_button next"><xms:localization text="Next"/></a>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    function changePageCarrierCredit(page) {
        var recordSize = $('#selPageSizeCarrierCredit').val();
        getCarrierCredit(page, recordSize);
    }
    function changePageSelCarrierCredit() {
        var recordSize = $('#selPageSizeCarrierCredit').val();
        getCarrierCredit(1, recordSize);
    }
    $(document).ready(function () {
        $('#rptTxnId').val('<s:property value="rptTxnId" />');
        $('#frozen-message').html('<s:property value="frozenMessage"/>');
    });


</script>