<div class="page-break">
    <div class="title">
        <h3>Carrier Credit Details</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th colspan="6"></th>
            <th colspan="6"><i>Original Invoice</i></th>
            <th colspan="4"><i>Credit</i></th>
            <th colspan="2"></th>
        </tr>
        <tr>
            <th width="100px;">Date</th>
            <th>Customer No</th>
            <th>Customer Name</th>
            <th>Invoice #</th>
            <th>AWB/Connote No</th>
            <th>International Domestic</th>
            <th colspan="2">Customer Total</th>
            <th colspan="2">Franchisee Cost</th>
            <th colspan="2">Gross Margin</th>
            <th colspan="2">Customer Total</th>
            <th colspan="2">Franchisee Cost</th>
            <th colspan="2">New Margin</th>
        </tr>
        <tr>
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
            <th>Price Ex GST</th>
            <th>GST</th>
            <th>Price Ex GST</th>
            <th>GST</th>
            <th>Price Ex GST</th>
            <th>GST</th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_credit_content.ftl">
        <#if carrierCreditDetailsTotal??>
        <tr>
            <td colspan="6">TOTAL</td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.customerTotalExcGst)!"0.00"}</strong></td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.customerTotalGst)!"0.00"}</strong></td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.franchiseCostExcGst)!"0.00"}</strong></td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.franchiseCostGst)!"0.00"}</strong></td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.grossMarginExcGst)!"0.00"}</strong></td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.grossMarginGst)!"0.00"}</strong></td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.creditsCustomerCostExcGst)!"0.00"}</strong>
            </td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.creditsCustomerCostGst)!"0.00"}</strong>
            </td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.creditsFranchiseCostExcGst)!"0.00"}</strong>
            </td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.creditsFranchiseCostGst)!"0.00"}</strong>
            </td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.newMarginExcGst)!"0.00"}</strong></td>
            <td class="text-right">
                <strong>${(currencySymbol)!"$"}${ (carrierCreditDetailsTotal.newMarginGst)!"0.00"}</strong></td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>