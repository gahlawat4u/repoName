<div style="text-align: center;"><span
        style="font-size: 13px;font-weight: bold">${(rs["rateSheet"].title?html)!" "}</span>
    <br/>
${(rs["rateSheet"].senderSuburb)!" "}
</div>
<div><span
        style="font-weight: bold; font-size: 13px">${(lang.translate("Value in"))!"Value in"} ${(currencyCode)!"AUD"}</span>
</div>
<#if rs?? && rs["rateSheet"]??>
<table class="border">
    <thead>
    <tr>
        <th>${(lang.translate("Destination"))!"Destination"}</th>
        <th>${(lang.translate("Min"))!"Min"}</th>
        <th>${(lang.translate("Base"))!"Base"}</th>
        <th>${(lang.translate("Per Kg"))!"Per Kg"}</th>
    </tr>
    </thead>
    <tbody>
        <#list rs["rateSheet"].columns as col>
            <#list rs["rateSheet"].rows as row>
                <#assign key = row.rowName + row.charRowName + col.columnName>
                <#if rs["rateSheet"].data[key]?? && rs["rateSheet"].data[key].rate??>
                <tr>
                    <td>${(col.columnName)!" "}</td>
                    <td>${(rs["rateSheet"].data[key].rate.minCharge)!" "}</td>
                    <td>${(rs["rateSheet"].data[key].rate.baseCharge)!"0.00"}</td>
                    <td>${(rs["rateSheet"].data[key].rate.perKg)!" "}</td>
                </tr>
                </#if>
            </#list>
        </#list>
    </tbody>
</table>
</#if>
<#if rs?? && rs["perWeightRateSheet"]??>
<div><span
        style="font-weight: bold; font-size: 13px;"">${(lang.translate("Non-Document above {weight} kg (Multiply shipment weight by zone rate)"))?replace("{weight}",rs["rateSheet"].maxWeight)}</span>
</div>
<table class="border">
    <thead>
    <tr>
        <th>${(lang.translate("Destination"))!"Destination"}</th>
        <th>${(lang.translate("Min"))!"Min"}</th>
        <th>${(lang.translate("Base"))!"Base"}</th>
        <th>${(lang.translate("Per Kg"))!"Per Kg"}</th>
    </tr>
    </thead>
    <tbody>
        <#list rs["perWeightRateSheet"].columns as col>
            <#list rs["ratperWeightRateSheetSheet"].rows as row>
                <#assign key = row.rowName + row.charRowName + col.columnName>
                <#if rs["perWeightRateSheet"].data[key].rate??>
                <tr>
                    <td>${(col.columnName)!" "}</td>
                    <td>${(rs["perWeightRateSheet"].data[key].rate.minCharge)!" "}</td>
                    <td>${(rs["perWeightRateSheet"].data[key].rate.baseCharge)!"0.00"}</td>
                    <td>${(rs["perWeightRateSheet"].data[key].rate.perKg)!"0.00"}</td>
                </tr>
                </#if>
            </#list>
        </#list>
    </tbody>
</table>
<div>${(lang.translate("All rates are stated in {currencyCode} exclusive of GST and all surcharges. <br/>
			Rates for specific weights not shown on the table above are available on request."))?replace("{currencyCode}",currencyCode)!" "}</div>
</#if>