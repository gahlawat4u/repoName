<div>
    <div class="title">
        <h3>Overpayment Page on Payables</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th>Original Payment Data</th>
            <th>Customer No</th>
            <th>Customer Name</th>
            <th>Overpayment Type</th>
            <th>Amount</th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_overpayment_content.ftl">
        <#if overpaymentTotal?has_content>
        <tr>
            <td colspan="4">TOTAL</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${ (overpaymentTotal.amount)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>