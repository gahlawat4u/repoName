<#if overpayment?has_content>
    <#list overpayment as op>
    <tr>
        <td>${op.originPaymentDate}</td>
        <td>${op.customerNumber}</td>
        <td>${op.customerName?html}</td>
        <td>${op.overpaymentType}</td>
        <td class="text-right">${(currencySymbol)!"$"}${ (op.amount)!"0.00"}</td>
    </tr>
    </#list>
<#else>
<tr>
    <td colspan="5" align="center">No data available...</td>
</tr>
</#if>