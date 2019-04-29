<#if techFeeDetails?has_content>
    <#list techFeeDetails as tf>
    <tr>
        <td>${(tf.importDate)!" "}</td>
        <td>${(tf.customerCode)!" "}</td>
        <td>${(tf.customerName?html)!" "}</td>
        <td>${(tf.invoiceCode?html)!" "}</td>
        <td>${(tf.airbillNumber?html)!" "}</td>
        <td><#if tf.isDomestic=='true'>
			${lang.translate('Domestic')}
		<#else>
        ${lang.translate('International')}
        </#if>
        <td class="text-right">${(currencySymbol)!"$"}${(tf.intlShipmentFee)!"0.00"}</td>
        <td class="text-right">${(currencySymbol)!"$"}${(tf.domShipmentFee)!"0.00"}</td>
    </tr>
    </#list>
<#else>
<tr>
    <td colspan="8">${lang.translate('No data available...')}</td>
</tr>
</#if>