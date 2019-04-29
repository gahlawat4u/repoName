<#if shipmentDetails?has_content>
    <#list shipmentDetails as sm>
    <tr>
        <td>${(sm.importDate)!" "}</td>
        <td>${(sm.customerCode)!" "}</td>
        <td>${(sm.customerName?html)!" "}</td>
        <td>${(sm.invoiceCode?html)!" "}</td>
        <td>${(sm.airbillNumber?html)!" "}</td>
        <td><#if sm.isDomestic=='true'>
			${lang.translate('Domestic')}
		<#else>
        ${lang.translate('International')}
        </#if>
        </td>
        <td align="right">${(currencySymbol)!"$"}${(sm.custCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.custTax)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.custMarginable)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.franCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.franTax)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.grossMargin)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.grossMarginTax)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.franCredit)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.custCredit)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.managementFee)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.marketingFee)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(sm.profitShare)!"0.00"}</td>
    </tr>
    </#list>
<#else>
<tr>
    <td colspan="19" align="center">${lang.translate('No data available...')}</td>
</tr>
</#if>