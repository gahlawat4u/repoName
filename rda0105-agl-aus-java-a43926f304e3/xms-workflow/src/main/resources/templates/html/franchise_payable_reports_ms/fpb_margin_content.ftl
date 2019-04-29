<#if paymentMarginDetails?has_content>
    <#list paymentMarginDetails as pmd>
    <tr>
        <td>${pmd.paymentDate}</td>
        <td>${pmd.customerNumber}</td>
        <td>${pmd.customerName?html}</td>
        <td>${pmd.invoiceNumber}</td>
        <td>${pmd.airbillNumber}</td>
        <td>${pmd.internationalDomestic}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.amountOutstanding)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.customerTotalExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.customerTotalGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.franchiseCostExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.franchiseCostGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.grossMarginExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.grossMarginGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.previouslyPaid)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.paymentsReceived)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.creditsFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.creditsCustomerCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pmd.profitShareExcGst)!"0.00"}</td>
    </tr>
    </#list>
<#else>
<tr>
    <td colspan="18">No data available...</td>
</tr>
</#if>