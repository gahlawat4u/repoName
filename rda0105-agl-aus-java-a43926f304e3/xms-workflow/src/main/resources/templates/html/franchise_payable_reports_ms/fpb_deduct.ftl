<div class="page-break">
    <div class="title">
        <h3>Carrier Cost Deduction</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th>Customer Name</th>
            <th>Invoice Number</th>
            <th>AWB/Connote No</th>
            <th>Amount Outstanding</th>
            <th colspan="2">Customer Total(Inc. Tax)</th>
            <th colspan="2">Franchise Cost(Inc. Tax)</th>
            <th>Franchise Charge</th>
        </tr>
        <tr>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th style="border-top: none"></th>
            <th>Price Ex GST</th>
            <th>GST</th>
            <th>Price Ex GST</th>
            <th>GST</th>
            <th style="border-top: none"></th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_deduct_content.ftl">
        <#if carrierCostDeductionTotal?has_content>
        <tr>
            <td colspan="3">TOTAL</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (carrierCostDeductionTotal.customerPayment)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (carrierCostDeductionTotal.customerCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (carrierCostDeductionTotal.customerTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (carrierCostDeductionTotal.franchiseCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (carrierCostDeductionTotal.franchiseTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (carrierCostDeductionTotal.franchiseCharge)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>