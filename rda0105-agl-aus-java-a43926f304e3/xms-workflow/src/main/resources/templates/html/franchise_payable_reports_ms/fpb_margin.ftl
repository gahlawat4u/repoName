<div class="page-break">
    <div class="title">
        <h3>Payment Margin Details</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th width="100px;">Payment Date</th>
            <th>Customer No</th>
            <th>Customer Name</th>
            <th>Invoice #</th>
            <th>AWB/Connote No</th>
            <th>International Domestic</th>
            <th>Amount Outstanding</th>
            <th colspan="2">Customer Total</th>
            <th colspan="2">Franchisee Cost</th>
            <th colspan="2">Gross Margin</th>
            <th>Previously Paid(inc GST)</th>
            <th>Payment Received (inc GST)</th>
            <th colspan="2">Credits</th>
            <th>Profit Share</th>
        </tr>
        <tr>
            <th style="border-top: none !important"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th width="4%">Price Ex GST</th>
            <th width="4%">GST</th>
            <th width="4%">Price Ex GST</th>
            <th width="4%">GST</th>
            <th width="4%">Price Ex GST</th>
            <th width="4%">GST</th>
            <th width="4%" style="border-top: none"></th>
            <th width="4%" style="border-top: none"></th>
            <th width="4%">Franchisee Cost.</th>
            <th width="4%">Customer Cost.</th>
            <th width="4%" style="border-top: none"></th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_margin_content.ftl">
        <#if paymentMarginDetailsTotal?has_content>
        <tr>
            <td colspan="6">TOTAL</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.amountOutstanding)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.customerTotalExcGst)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.customerTotalGst)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.franchiseCostExcGst)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.franchiseCostGst)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.grossMarginExcGst)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.grossMarginGst)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.previouslyPaid)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.paymentsReceived)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.creditsFranchiseCost)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.creditsCustomerCost)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${ (paymentMarginDetailsTotal.profitShareExcGst)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>