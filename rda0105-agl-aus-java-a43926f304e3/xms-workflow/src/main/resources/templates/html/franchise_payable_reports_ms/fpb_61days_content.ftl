<#if paymentMarginDetails61?has_content>
    <#list paymentMarginDetails61 as pcd>
    <tr>
        <td>${pcd.paymentDate}</td>
        <td>${pcd.customerNumber}</td>
        <td>${pcd.customerName?html}</td>
        <td>${pcd.invoiceNumber}</td>
        <td>${pcd.airbillNumber}</td>
        <td>${pcd.internationalDomestic}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.amountOutstanding)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.customerTotalExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.customerTotalGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.franchiseCostExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.franchiseCostGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.grossMarginExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.grossMarginGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.previouslyDeductedCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.previouslyPaid)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.paymentsReceived)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.creditsFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.creditsCustomerCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.repaidCarrierDeductions)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.profitShareExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (pcd.profitShareOnLateFees)!"0.00"}</td>
    </tr>
    </#list>
<#else>
<tr>
    <td colspan="21">No data available...</td>
</tr>
</#if>