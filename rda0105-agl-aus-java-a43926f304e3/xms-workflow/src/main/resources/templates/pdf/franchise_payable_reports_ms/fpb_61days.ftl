<div class="page-break">
    <div class="title">
        <h3>61 + Day Payment/Credit Details</h3>
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
            <th>Previous Deducted</th>
            <th>Previously Paid<br/>(inc GST)</th>
            <th>Payment Received<br/>(inc GST)</th>
            <th colspan="2">Credits</th>
            <th>Repaid Carrier Deductions</th>
            <th>Profit Share</th>
            <th>Profit Share on Late Fees</th>
        </tr>
        <tr>
            <th style="border-top: none"></th>
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
            <th width="4%" style="border-top: none"></th>
            <th width="4%">Franchisee Cost.</th>
            <th width="4%">Customer Cost.</th>
            <th width="4%" style="border-top: none"></th>
            <th width="4%" style="border-top: none"></th>
            <th width="4%" style="border-top: none"></th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_61days_content.ftl">
        <#if paymentMarginDetails61Total??>
        <tr>
            <td colspan="6">TOTAL</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.amountOutstanding)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.customerTotalExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.customerTotalGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.franchiseCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.franchiseCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.grossMarginExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.grossMarginGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.previouslyDeductedCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.previouslyPaid)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.paymentsReceived)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.creditsFranchiseCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.creditsCustomerCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.repaidCarrierDeductions)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.profitShareExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(paymentMarginDetails61Total.profitShareOnLateFees)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>