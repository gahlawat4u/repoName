<#if carrierCostDeduction?has_content>
    <#list carrierCostDeduction as cd>
    <tr>
        <td>${cd.customerName?html}</td>
        <td>${cd.invoiceNumber?html}</td>
        <td>${cd.airbillNumber?html}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.customerPayment)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.customerCost)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.customerTax)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.franchiseCost)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.franchiseTax)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.franchiseCharge)!"0.00"}</td>
    </tr>
    </#list>
<#else>
<tr>
    <td colspan="9">No data available...</td>
</tr>
</#if>