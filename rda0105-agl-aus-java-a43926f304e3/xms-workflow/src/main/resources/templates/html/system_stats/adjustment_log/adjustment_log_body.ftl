<#if datas?has_content>
    <#list datas as data>
    <tr>
        <td>${(data.userName)!" "}</td>
        <td>${(data.adjustmentId)!" "}</td>
        <td>${(data.adjustmentType)!" "}</td>
        <td>${(data.airbillNumber)!" "}</td>
        <td>${(data.customerCode)!" "}</td>
        <td>${(data.actionDate)!" "}</td>
        <td align="right">${(data.carrierAmt)!" "}</td>
        <td align="right">${(data.customerAmt)!" "}</td>
        <td>${(data.statusName)!" "}</td>
    </tr>
    </#list>
</#if>