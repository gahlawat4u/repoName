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
                    <td><s:select list="{10,25,50,100}" value="recordSize" id="selRecordSizeCarrierCreditDetails"
                                  onchange="changeRecordSize();" class="form-control"></s:select></td>
                    <input type="hidden" id="paymentMarginCurrPage" value="page"/>

                </tr>
            </table>
        </div>
        <p>
            <xms:localization text="
			Please Note: </br> -The details below list only invoices that received
			carrier adjustment credits from "/>
            <s:property value="startDate"/>
            <xms:localization text=" to "/>
            <s:property value="endDate"/>
        </p>
        <table class="table mg0">
            <tr>
                <th class="s42"></th>
            </tr>
        </table>
        <table class="table table-hover table-striped table-bordered mg0" id="datatable1">
            <thead>
            <tr>
                <th colspan="6"></th>
                <th colspan="6"><i><xms:localization text="Original Invoice"/></i></th>
                <th colspan="4"><i><xms:localization text="Credit"/></i></th>
                <th colspan="2"></th>
            </tr>
            <tr>
                <th width="100px;"><xms:localization text="Date"/></th>
                <th><xms:localization text="Customer No"/></th>
                <th><xms:localization text="Customer Name"/></th>
                <th><xms:localization text="Invoice #"/></th>
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
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="%{carrierCreditDetails.isEmpty()}">
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
                        <td><s:property value="airbillNumber"/></td>
                        <td><s:property value="internationalDomestic"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="customerTotalExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="customerTotalGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="franchiseCostExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="franchiseCostGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="grossMarginExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="grossMarginGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="creditsCustomerCostExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="creditsCustomerCostGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="creditsFranchiseCostExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="creditsFranchiseCostGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="newMarginExcGst"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="newMarginGst"/></td>
                    </tr>
                </s:iterator>
                <s:if test="%{page == pageCount}">
                    <tr>
                        <th colspan="6"><xms:localization text="TOTAL"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.customerTotalExcGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.customerTotalGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.franchiseCostExcGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.franchiseCostGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.grossMarginExcGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.grossMarginGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.creditsCustomerCostExcGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.creditsCustomerCostGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.creditsFranchiseCostExcGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.creditsFranchiseCostGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.newMarginExcGst"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCreditDetailsTotal.newMarginGst"/></th>
                    </tr>
                </s:if>
            </s:else>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12">
        <div class="col-xs-2 pull-right">
            <select class="form-control" id="selPageCarrierCreditDetails"
                    onchange="changePageSelCarrierCreditDetails()">
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
                    <a href="javascript:changePageCarrierCreditDetails('<s:property value="%{page-1}"/>');"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
                <s:iterator var="count" begin="1" end="pageCount">
                    <s:if test="%{(page-4) < top && top < (page+4)}">
						<span> <a href="javascript:changePageCarrierCreditDetails('<s:property value="top"/>');"
                                  class="paginate_button <s:if test="%{page==top}">current</s:if>"
                                  data-targetpage="<s:property value="top"/>"> <s:property value="top"/>
                        </a>
						</span>
                    </s:if>
                    <s:elseif test="%{top>(page+4) && top<(page+6) && (pageCount - page) >=6}">
                        <span>...</span>
                    </s:elseif>
                    <s:elseif test="%{top>(pageCount-7)}">
                        <span><a href="javascript:changePageCarrierCreditDetails('<s:property value="top"/>');"
                                 class="paginate_button" data-targetpage="<s:property value="top"/>"><s:property
                                value="top"/></a></span>
                    </s:elseif>
                </s:iterator>
                <s:if test="%{page < pageCount}">
                    <a href="javascript:changePageCarrierCreditDetails('<s:property value="%{page+1}"/>');"
                       class="paginate_button next"><xms:localization text="Next"/></a>
                </s:if>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function changePageCarrierCreditDetails(page) {
            var recordSize = $('#selRecordSizeCarrierCreditDetails').val();
            getCarrierCreditDetails(page, recordSize);
        }
        function changePageSelCarrierCreditDetails() {
            var recordSize = $('#selRecordSizeCarrierCreditDetails').val();
            var page = $('#selPageCarrierCreditDetails').val();
            if (page == null || page == '') {
                page = 1;
            }
            getCarrierCreditDetails(page, recordSize);
        }
        $(document).ready(function () {
            $('#rptTxnId').val('<s:property value="rptTxnId" />');
            $('#frozen-message').html('<s:property value="frozenMessage"/>');
        });
    </script>
</div>