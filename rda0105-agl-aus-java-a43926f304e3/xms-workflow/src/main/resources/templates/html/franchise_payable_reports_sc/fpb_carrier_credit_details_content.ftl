<#if carrierCreditDetails?has_content>
    <#list carrierCreditDetails as ccd>
    <tr>
        <td>${(ccd.paymentDate)!" "}</td>
        <td>${(ccd.customerNumber)!" "}</td>
        <td>${(ccd.customerName?html)!" "}</td>
        <td>${(ccd.invoiceNumber?html)!" "}</td>
        <td>&nbsp;</td>
        <td>${(ccd.airbillNumber?html)!" "}</td>
        <td>${(ccd.internationalDomestic)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.customerTotalExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.customerTotalGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.franchiseCostExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.franchiseCostGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.grossMarginExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.grossMarginGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.creditsCustomerCostExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.creditsCustomerCostGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.creditsFranchiseCostExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.creditsFranchiseCostGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.newMarginExcGst)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(ccd.newMarginGst)!"0.00"}</td>
    </tr>
    </#list>
<#else>
<tr>
    <td colspan="19">${lang.translate('No data available...')}</td>
</tr>
</#if>