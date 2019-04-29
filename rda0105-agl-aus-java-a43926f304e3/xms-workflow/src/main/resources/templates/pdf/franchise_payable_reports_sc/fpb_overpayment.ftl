<div>
    <div class="title">
        <h3>${lang.translate('Overpayment Page on Payables')}</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th>${lang.translate('Original Payment Data')}</th>
            <th>${lang.translate('Customer No')}</th>
            <th>${lang.translate('Customer Name')}</th>
            <th>${lang.translate('Overpayment Type')}</th>
            <th>${lang.translate('Amount')}</th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_overpayment_content.ftl">
        <#if overpaymentTotal?has_content>
        <tr>
            <td class="bold" colspan="4">${lang.translate('Total')}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(overpaymentTotal.amount)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>