<#if nonCentralDetails?has_content>
    <#list nonCentralDetails as ncd>
    <tr>
        <td>${ncd.paymentDate}</td>
        <td>${ncd.customerNumber}</td>
        <td>${ncd.customerName?html}</td>
        <td>${ncd.invoiceNumber}</td>
        <td>${ncd.airbillNumber}</td>
        <td>${ncd.internationalDomestic}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.amountOutstanding)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.customerTotalExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.customerTotalGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.franchiseCostExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.franchiseCostGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.grossMarginExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.grossMarginGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.previouslyPaid)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.paymentsReceived)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.creditsFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.creditsCustomerCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${ (ncd.profitShareExcGst)!"0.00"}</td>
    </tr>
    </#list>
<#else>
<tr>
    <td colspan="18" align="content">No data available...</td>
</tr>
</#if>