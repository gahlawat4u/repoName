<#if datas?has_content>
    <#list datas as data>
    <tr>
        <td>${(data.actionDate)!" "}</td>
        <td>${(data.userCode)!" "}</td>
        <td>${(data.userName)!" "}</td>
        <td>${(data.actionType)!" "}</td>
        <td>${(data.actionTable)!" "}</td>
        <td>${(data.filter)!" "}</td>
        <td>${(data.changesMode)!" "}</td>
    </tr>
    </#list>
</#if>