<#if carrierCreditDetails?has_content>
    <#list carrierCreditDetails as cd>
    <tr>
        <td>${cd.paymentDate}</td>
        <td>${cd.customerNumber}</td>
        <td>${cd.customerName?html}</td>
        <td>${cd.invoiceNumber?html}</td>
        <td>${cd.airbillNumber?html}</td>
        <td>${cd.internationalDomestic}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.customerTotalExcGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.customerTotalGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.franchiseCostExcGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.franchiseCostGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.grossMarginExcGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.grossMarginGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.creditsCustomerCostExcGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.creditsCustomerCostGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.creditsFranchiseCostExcGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.creditsFranchiseCostGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.newMarginExcGst)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (cd.newMarginGst)!"0.00"}</td>
    </tr>
    </#list>
<#else>
<tr>
    <td colspan="18" align="center">No data available ...</td>
</tr>
</#if>