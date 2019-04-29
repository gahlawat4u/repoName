<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-12">
        <p class="s38">
            <xms:localization text="Carrier Cost Deduction"/>
        </p>

        <div class="form-group flr mgb">
            <table class="">
                <tr>
                    <td><s:select list="{10,25,50,100}" value="recordSize" id="selRecordSizeCarrierCostDeduction"
                                  onchange="changeRecordSize();" class="form-control"></s:select></td>
                    <input type="hidden" id="paymentMarginCurrPage" value="page">
                </tr>
            </table>
        </div>
        <p>
            <xms:localization text="
			Please Note:</br> - The details below list only invoices that reached 61
			days past due from "/>
            <s:property value="startDate"/>
            <xms:localization text=" to "/>
            <s:property value="endDate"/>
            </br>
            <xms:localization text="- The Franchise is
			charged 100% of the franchise cost.</br> - (Franchise can recover their
			losses by collecting from the customer after 61 days.)"/>
        </p>
        <table class="table mg0">
            <tr>
                <th class="s42"></th>
            </tr>
        </table>

        <table class="table table-hover table-striped table-bordered mg0" id="datatable" style="float: left">
            <thead>
            <tr>
                <th><xms:localization text="Customer Name"/></th>
                <th><xms:localization text="Invoice Number"/></th>
                <th><xms:localization text="AWB/Connote No"/></th>
                <th><xms:localization text="Amount Outstanding"/></th>
                <th colspan="2"><xms:localization text="Customer Total(Inc. Tax)"/></th>
                <th colspan="2"><xms:localization text="Franchise Cost(Inc. Tax)"/></th>
                <th><xms:localization text="Franchise Charge"/></th>
            </tr>
            <tr>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th><xms:localization text="Price Ex GST"/></th>
                <th><xms:localization text="GST"/></th>
                <th style="border-top: none"></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="%{carrierCostDeduction.isEmpty()}">
                <tr>
                    <td colspan="9"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="carrierCostDeduction">
                    <tr>
                        <td><s:property value="customerName"/></td>
                        <td><s:property value="invoiceNumber"/></td>
                        <td><s:property value="airbillNumber"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="customerPayment"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="customerCost"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="customerTax"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="franchiseCost"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="franchiseTax"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="franchiseCharge"/></td>
                    </tr>
                </s:iterator>
                <s:if test="%{page == pageCount}">
                    <tr>
                        <th colspan="3"><xms:localization text="TOTAL"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCostDeductionTotal.customerPayment"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCostDeductionTotal.customerCost"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCostDeductionTotal.customerTax"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCostDeductionTotal.franchiseCost"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCostDeductionTotal.franchiseTax"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="carrierCostDeductionTotal.franchiseCharge"/></th>
                    </tr>
                </s:if>
            </s:else>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12">
        <div class="col-xs-2 pull-right">
            <select class="form-control" id="selPageCarrierCostDeduction"
                    onchange="changePageSelCarrierCostDeduction()">
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
                    <a href="javascript:changePageCarrierCostDeduction('<s:property value="%{page-1}"/>');"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
                <s:iterator var="count" begin="1" end="pageCount">
                    <s:if test="%{(page-4) < top && top < (page+4)}">
						<span> <a href="javascript:changePageCarrierCostDeduction('<s:property value="top"/>');"
                                  class="paginate_button <s:if test="%{page==top}">current</s:if>"
                                  data-targetpage="<s:property value="top"/>"> <s:property value="top"/>
                        </a>
						</span>
                    </s:if>
                    <s:elseif test="%{top>(page+4) && top<(page+6) && (pageCount - page) >=6}">
                        <span>...</span>
                    </s:elseif>
                    <s:elseif test="%{top>(pageCount-7)}">
                        <span><a href="javascript:changePageCarrierCostDeduction('<s:property value="top"/>');"
                                 class="paginate_button" data-targetpage="<s:property value="top"/>"><s:property
                                value="top"/></a></span>
                    </s:elseif>
                </s:iterator>
                <s:if test="%{page < pageCount}">
                    <a href="javascript:changePageCarrierCostDeduction('<s:property value="%{page+1}"/>');"
                       class="paginate_button next"><xms:localization text="Next"/></a>
                </s:if>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function changePageCarrierCostDeduction(page) {
            var recordSize = $('#selRecordSizeCarrierCostDeduction').val();
            getCarrierCostDeduction(page, recordSize);
        }
        function changePageSelCarrierCostDeduction() {
            var recordSize = $('#selRecordSizeCarrierCostDeduction').val();
            var page = $('#selPageCarrierCostDeduction').val();
            if (page == null || page == '') {
                page = 1;
            }
            getCarrierCostDeduction(page, recordSize);
        }
        $(document).ready(function () {
            $('#rptTxnId').val('<s:property value="rptTxnId" />');
            $('#frozen-message').html('<s:property value="frozenMessage"/>');
        });
    </script>
</div>
