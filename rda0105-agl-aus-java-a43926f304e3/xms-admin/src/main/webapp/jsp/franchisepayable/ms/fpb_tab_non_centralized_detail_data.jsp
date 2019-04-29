<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <div class="col-lg-12">
        <p class="s38">
            <xms:localization text="Non Centralized Details"/>
        </p>

        <div class="form-group flr mgb">
            <table class="">
                <tr>
                    <td><s:select list="{10,25,50,100}" value="recordSize" id="selRecordSizeNonCentralizedDetails"
                                  onchange="changeRecordSize();" class="form-control"></s:select></td>
                    <input type="hidden" id="paymentMarginCurrPage" value="page">
                </tr>
            </table>
        </div>
        <p>
            <xms:localization text="
			Please Note:</br> - The details below list only invoices that have payment
			from "/>
            <s:property value="startDate"/>
            <xms:localization text=" to "/>
            <s:property value="endDate"/>
            <xms:localization text="<br> - The invoices that do not have any payment are not listed."/>
        </p>
        <table class="table mg0">
            <tr>
                <th class="s42"></th>
            </tr>
        </table>
        <table class="table table-hover table-striped table-bordered mg0">
            <thead>
            <tr>
                <th width="100px;"><xms:localization text="Payment Date"/></th>
                <th><xms:localization text="Customer No"/></th>
                <th><xms:localization text="Customer Name"/></th>
                <th><xms:localization text="Invoice #"/></th>
                <th><xms:localization text="AWB/Connote No"/></th>
                <th><xms:localization text="International Domestic"/></th>
                <th><xms:localization text="Amount Outstanding"/></th>
                <th colspan="2"><xms:localization text="Customer Total"/></th>
                <th colspan="2"><xms:localization text="Franchisee Cost"/></th>
                <th colspan="2"><xms:localization text="Gross Margin"/></th>
                <th><xms:localization text="Previously Paid(inc GST)"/></th>
                <th><xms:localization text="Payment Received (inc GST)"/></th>
                <th colspan="2"><xms:localization text="Credits"/></th>
                <th><xms:localization text="Profit Share"/></th>
            </tr>
            <tr>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th><xms:localization text="Franchisee Cost."/></th>
                <th><xms:localization text="Customer Cost."/></th>
                <th style="border-top: none"></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="%{nonCentralDetails.isEmpty()}">
                <tr>
                    <td colspan="18"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="nonCentralDetails">
                    <tr>
                        <td><s:property value="paymentDate"/></td>
                        <td><s:property value="customerNumber"/></td>
                        <td><s:property value="customerName"/></td>
                        <td><s:property value="invoiceNumber"/></td>
                        <td><s:property value="airbillNumber"/></td>
                        <td><s:property value="internationalDomestic"/></td>
                        <td align="right"><s:property value="amountOutstanding"/></td>
                        <td align="right"><s:property value="customerTotalExcGst"/></td>
                        <td align="right"><s:property value="customerTotalGst"/></td>
                        <td align="right"><s:property value="franchiseCostExcGst"/></td>
                        <td align="right"><s:property value="franchiseCostGst"/></td>
                        <td align="right"><s:property value="grossMarginExcGst"/></td>
                        <td align="right"><s:property value="grossMarginGst"/></td>
                        <td align="right"><s:property value="previouslyPaid"/></td>
                        <td align="right"><s:property value="paymentsReceived"/></td>
                        <td align="right"><s:property value="creditsFranchiseCost"/></td>
                        <td align="right"><s:property value="creditsCustomerCost"/></td>
                        <td align="right"><s:property value="profitShareExcGst"/></td>
                    </tr>
                </s:iterator>
                <s:if test="%{page == pageCount}">
                    <tr>
                        <th colspan="6"><xms:localization text="TOTAL"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.amountOutstanding"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.customerTotalExcGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.customerTotalGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.franchiseCostExcGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.franchiseCostGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.grossMarginExcGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.grossMarginGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.previouslyPaid"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.paymentsReceived"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.creditsFranchiseCost"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.creditsCustomerCost"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="nonCentralDetailsTotal.profitShareExcGst"/></th>
                    </tr>
                </s:if>
            </s:else>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12">
        <div class="col-xs-2 pull-right">
            <select class="form-control" id="selPageNonCentralizedDetails"
                    onchange="changePageSelNonCentralizedDetails()">
                <option value=""><xms:localization text="Select Page"/></option>
                <s:iterator var="count" begin="1" end="pageCount">
                    <option
                            <s:if test="%{page==top}">selected</s:if> value="<s:property value="top"/>"><s:property
                            value="top"/></option>
                </s:iterator>
            </select>
        </div>
        <div class="col-xs-10">
            <div class="dataTables_paginate">
                <s:if test="%{page > 1}">
                    <a href="javascript:changePageNonCentralizedDetails('<s:property value="%{page-1}"/>');"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
                <s:iterator var="count" begin="1" end="pageCount">
                    <s:if test="%{(page-4) < top && top < (page+4)}">
						<span> <a href="javascript:changePageNonCentralizedDetails('<s:property value="top"/>');"
                                  class="paginate_button <s:if test="%{page==top}">current</s:if>"
                                  data-targetpage="<s:property value="top"/>"> <s:property value="top"/>
                        </a>
						</span>
                    </s:if>
                    <s:elseif test="%{top>(page+4) && top<(page+6) && (pageCount - page) >=6}">
                        <span>...</span>
                    </s:elseif>
                    <s:elseif test="%{top>(pageCount-7)}">
                        <span><a href="javascript:changePageNonCentralizedDetails('<s:property value="top"/>');"
                                 class="paginate_button" data-targetpage="<s:property value="top"/>"><s:property
                                value="top"/></a></span>
                    </s:elseif>
                </s:iterator>
                <s:if test="%{page < pageCount}">
                    <a href="javascript:changePageNonCentralizedDetails('<s:property value="%{page+1}"/>');"
                       class="paginate_button next"><xms:localization text="Next"/></a>
                </s:if>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function changePageNonCentralizedDetails(page) {
            var recordSize = $('#selRecordSizeNonCentralizedDetails').val();
            getNonCentralizedDetails(page, recordSize);
        }
        function changePageSelNonCentralizedDetails() {
            var recordSize = $('#selRecordSizeNonCentralizedDetails').val();
            var page = $('#selPageNonCentralizedDetails').val();
            if (page == null || page == '') {
                page = 1;
            }
            getNonCentralizedDetails(page, recordSize);
        }
        $(document).ready(function () {
            $('#rptTxnId').val('<s:property value="rptTxnId" />');
            $('#frozen-message').html('<s:property value="frozenMessage"/>');
        });
    </script>
</div>