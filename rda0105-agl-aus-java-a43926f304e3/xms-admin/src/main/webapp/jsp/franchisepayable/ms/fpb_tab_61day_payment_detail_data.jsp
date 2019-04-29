<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<input type="hidden" value='<s:property value="rptTxnId" />' id="rptTxnId-61dayPaymentCreditDetail"/>

<div class="row">
    <div class="col-lg-12">
        <p class="s38">
            <xms:localization text="61 + Day Payment/Credit Details"/>
        </p>

        <p>
            <xms:localization text="
			Please Note:</br> - The details below list only invoices > 61 days from
			invoice due date that had payment/credit activity from "/>
            <s:property value="startDate"/>
            <xms:localization text=" to "/>
            <s:property value="endDate"/>
            <xms:localization text=" </br> - Franchise
			is paid back 100% of carrier credits or payments that contribute
			toward the carrier cost</br> - Franchise is paid % of carrier credits or
			payments that result in a discount profit (amount above the carrier
			cost)</br> - Franchise is paid 50% of carrier credits or payments above
			the discount invoice price(list profit)"/>
        </p>

        <div class="form-group flr mgb">
            <table class="">
                <tr>
                    <td><s:select list="{10,25,50,100}" value="recordSize" id="selRecordSize61"
                                  onchange="changeRecordSize();" class="form-control"></s:select></td>
                    <input type="hidden" id="paymentMarginCurrPage" value="page">
                </tr>
            </table>
        </div>
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
                <th><xms:localization text="Previous Deducted"/></th>
                <th><xms:localization text="Previously Paid(inc GST)"/></th>
                <th><xms:localization text="Payment Received (inc GST)"/></th>
                <th colspan="2"><xms:localization text="Credits"/></th>
                <th><xms:localization text="Repaid Carrier Deductions"/></th>
                <th><xms:localization text="Profit Share"/></th>
                <th><xms:localization text="Profit Share on Late Fees"/></th>
            </tr>
            <tr>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th style="border-top: none"></th>
                <th width="4%"><xms:localization text="Price Ex GST"/></th>
                <th width="4%"><xms:localization text="GST"/></th>
                <th width="4%"><xms:localization text="Price Ex GST"/></th>
                <th width="4%"><xms:localization text="GST"/></th>
                <th width="4%"><xms:localization text="Price Ex GST"/></th>
                <th width="4%"><xms:localization text="GST"/></th>
                <th width="4%" style="border-top: none"></th>
                <th width="4%" style="border-top: none"></th>
                <th width="4%" style="border-top: none"></th>
                <th width="4%"><xms:localization text="Franchisee Cost."/></th>
                <th width="4%"><xms:localization text="Customer Cost."/></th>
                <th width="4%" style="border-top: none"></th>
                <th width="4%" style="border-top: none"></th>
                <th width="4%" style="border-top: none"></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="%{paymentCreditDetails61.isEmpty()}">
                <tr>
                    <td colspan="21"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="paymentCreditDetails61">
                    <tr>
                        <td><s:property value="paymentDate"/></td>
                        <td><s:property value="customerNumber"/></td>
                        <td><s:property value="customerName"/></td>
                        <td><s:property value="invoiceNumber"/></td>
                        <td><s:property value="airbillNumber"/></td>
                        <td><s:property value="internationalDomestic"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="amountOutstanding"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="customerTotalExcGst"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="customerTotalGst"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="franchiseCostExcGst"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="franchiseCostGst"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="grossMarginExcGst"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property value="grossMarginGst"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="previouslyDeductedCost"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property value="previouslyPaid"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="paymentsReceived"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="creditsFranchiseCost"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="creditsCustomerCost"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="repaidCarrierDeductions"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="profitShareExcGst"/></td>
                        <td align="right"><s:property value="currencySymbol"/><s:property
                                value="profitShareOnLateFees"/></td>
                    </tr>
                </s:iterator>
            </s:else>
            <s:if test="%{page == pageCount}">
                <tr>
                    <th colspan="6"><xms:localization text="TOTAL"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.amountOutstanding"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.customerTotalExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.customerTotalGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.franchiseCostExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.franchiseCostGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.grossMarginExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.grossMarginGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.previouslyDeductedCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.previouslyPaid"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.paymentsReceived"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.creditsFranchiseCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.creditsCustomerCost"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.repaidCarrierDeductions"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.profitShareExcGst"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="paymentCreditDetails61Total.profitShareOnLateFees"/></th>
                </tr>
            </s:if>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12">
        <div class="col-xs-2 pull-right">
            <select class="form-control" id="selPage61" onchange="changePageSel61()">
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
                    <a href="javascript:changePage61('<s:property value="%{page-1}"/>');"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
                <s:iterator var="count" begin="1" end="pageCount">
                    <s:if test="%{(page-4) < top && top < (page+4)}">
						<span> <a href="javascript:changePage61('<s:property value="top"/>');"
                                  class="paginate_button <s:if test="%{page==top}">current</s:if>"
                                  data-targetpage="<s:property value="top"/>"> <s:property value="top"/>
                        </a>
						</span>
                    </s:if>
                    <s:elseif test="%{top>(page+4) && top<(page+6) && (pageCount - page) >=6}">
                        <span>...</span>
                    </s:elseif>
                    <s:elseif test="%{top>(pageCount-7)}">
                        <span><a href="javascript:changePage61('<s:property value="top"/>');" class="paginate_button"
                                 data-targetpage="<s:property value="top"/>"><s:property value="top"/></a></span>
                    </s:elseif>
                </s:iterator>
                <s:if test="%{page < pageCount}">
                    <a href="javascript:changePage61('<s:property value="%{page+1}"/>');"
                       class="paginate_button next"><xms:localization text="Next"/></a>
                </s:if>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function changePage61(page) {
            var recordSize = $('#selRecordSize61').val();
            get61dayPaymentCreditDetail(page, recordSize);
        }
        function changePageSel61() {
            var recordSize = $('#selRecordSize61').val();
            var page = $('#selPage61').val();
            if (page == null || page == '') {
                page = 1;
            }
            get61dayPaymentCreditDetail(page, recordSize);
        }
        $(document).ready(function () {
            $('#rptTxnId').val('<s:property value="rptTxnId" />');
            $('#frozen-message').html('<s:property value="frozenMessage"/>');
        });
    </script>
</div>