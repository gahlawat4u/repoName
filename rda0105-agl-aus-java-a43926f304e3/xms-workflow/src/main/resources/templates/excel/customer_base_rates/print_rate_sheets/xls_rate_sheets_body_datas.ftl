<div style="text-align: center;"><span
        style="font-size: 13px;font-weight: bold;">${(rs["rateSheet"].title?html)!" "}</span></div>
<div><span
        style="font-weight: bold; font-size: 13px">${(lang.translate("Value in"))!"Value in"} ${(currencyCode)!"AUD"}</span>
</div>
<#if rs?? && rs["rateSheet"]??>
<table class="border">
    <thead>
    <tr>
        <th>${(lang.translate("Weight"))!"Weight"}</th>
        <#list rs["rateSheet"].columns as col>
            <th>${(col.columnName)!" "}</th>
        </#list>
    </tr>
    </thead>
    <tbody>
        <#list rs["rateSheet"].rows as row>
        <tr>
            <td><#if row.isChar == "true">${(row.charRowName)!" "}<#else>${(row.rowName)!" "}</#if></td>
            <#list rs["rateSheet"].columns as col>
                <#assign key = row.rowName + row.charRowName + col.columnName>
                <td>${(rs["rateSheet"].data[key].rate.value)!"0.00"}</td>
            </#list>
        </tr>
        </#list>
    </tbody>
</table>
</#if>
<#if rs?? && rs["perWeightRateSheet"]??>
<div><span
        style="font-weight: bold; font-size: 13px;">${(lang.translate("Non-Document above {weight} kg (Multiply shipment weight by zone rate"))?replace("{weight}",rs["rateSheet"].maxWeight)}</span>
</div>
<table class="border">
    <thead>
    <tr>
        <th>${(lang.translate("Weight"))!"Weight"}</th>
        <#list rs["perWeightRateSheet"].columns as col>
            <th>${(col.columnName)!" "}</th>
        </#list>
    </tr>
    </thead>
    <tbody>
        <#list rs["perWeightRateSheet"].rows as row>
        <tr>
            <td>${(lang.translate("Per Kg"))!"Per Kg"} <#if row.isChar == "true">${(row.charRowName)!" "}<#else>${(row.rowName)!" "}</#if>
                Kgs
            </td>
            <#list rs["perWeightRateSheet"].columns as col>
                <#assign key = row.rowName + row.charRowName + col.columnName>
                <td>${(rs["perWeightRateSheet"].data[key].rate.value)!"0.00"}</td>
            </#list>
        </tr>
        </#list>
    </tbody>
</table>
<div>${(lang.translate("All rates are stated in AUD exclusive of GST and all surcharges. <br/>
			Rates for specific weights not shown on the table above are available on request."))!" "}</div>
</#if>