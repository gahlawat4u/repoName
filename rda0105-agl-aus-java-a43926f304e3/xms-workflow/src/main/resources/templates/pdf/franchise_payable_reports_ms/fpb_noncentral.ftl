<div class="page-break">
    <div class="title">
        <h3>Non Centralized Details</h3>
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
            <th>Previously Paid<br/>(inc GST)</th>
            <th>Payment Received<br/>(inc GST)</th>
            <th colspan="2">Credits</th>
            <th>Profit Share</th>
        </tr>
        <tr>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th>Price Ex GST</th>
            <th>GST</th>
            <th>Price Ex GST</th>
            <th>GST</th>
            <th>Price Ex GST</th>
            <th>GST</th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th>Franchisee Cost.</th>
            <th>Customer Cost.</th>
            <th style="border-top: none"></th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_noncentral_content.ftl">
        <#if nonCentralDetailsTotal?has_content>
        <tr>
            <td colspan="6">TOTAL</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.amountOutstanding)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.customerTotalExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.customerTotalGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.franchiseCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.franchiseCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.grossMarginExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.grossMarginGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.previouslyPaid)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.paymentsReceived)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.creditsFranchiseCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.creditsCustomerCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (nonCentralDetailsTotal.profitShareExcGst)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>