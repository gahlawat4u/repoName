<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Customer Summary Print')}</title>
    <style type="text/css">
        @page {
            size: A4 landscape;
        }

        body {
            font-family: Tahoma, Geneva, sans-serif;
            font-size: 11px;
            color: #848383;
            text-shadow: none;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            margin-bottom: 20px;
        }

        table thead {
            background-color: #f9f9f9;
            font-weight: bold;
        }

        .no-border {
            border: none;
        }

        .border {
            border-collapse: collapse;
        }

        .border thead tr:first-child th {
            border: 1px solid #ddd;
            border-bottom: none;
        }

        .border thead tr th {
            border: 1px solid #ddd;
            font-size: 12px;
        }

        .border tbody tr td {
            border: 1px solid #ddd;
            padding: 4px;
        }

        .border tr td {
            border: 1px solid #ddd;
            padding: 4px;
        }

        .img {
            cursor: pointer;
        }

        .img:hover {
            opacity: 0.7
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td align="center" colspan="2"><span
                style="font-size: 14px; font-weight: bold">${lang.translate('Customer Summary')}</span></td>
        <td align="right">
            <button class="btn s37 s44 flr" onclick="this.remove();window.print();"><i
                    class="fa fa-print fa-fw"></i> ${lang.translate('Prints')}</button>
        </td>
    </tr>
</table>
<table class="border">
    <thead>
    <th>${lang.translate('Customer #')}</th>
    <th>${lang.translate('Customer Name')}</th>
    <th>${lang.translate('Sales Rep')}</th>
    <th>${lang.translate('Customer Revenue')}</th>
    <th>${lang.translate('Franchise Cost')}</th>
    <#if userLevel == 1 || userLevel == 2>
    <th>${lang.translate('Carrier Cost')}</th>
    </#if>
    <th>${lang.translate('Gross Margin')}</th>
    <th>${lang.translate('Gross Margin%')}</th>
    <#if userLevel == 1 || userLevel == 2>
    <th>${lang.translate('DHL Cost')}</th>
    </#if>
    <th>${lang.translate('DHL Revenue')}</th>
    <th>${lang.translate('DHL Franchisee Cost')}</th>
    <th>${lang.translate('DHL Gross Margin')}</th>
    <th>${lang.translate('DHL Shipments')}</th>
    <#if userLevel == 1 || userLevel == 2>
    <th>${lang.translate('Carrier Cost (Base Charge)')}</th>
    </#if>
    <th>${lang.translate('Customer Cost (Base Charge)')}</th>
    <th>${lang.translate('Margin  On  Base Charge')}</th>
    <#if userLevel == 1 || userLevel == 2>
    <th>${lang.translate('TNT Domestic Cost')}</th>
    </#if>
    <th>${lang.translate('TNT Domestic Revenue')}</th>
    <th>${lang.translate('TNT Domestic Franchisee Cost')}</th>
    <th>${lang.translate('TNT Domestic Gross Margin')}</th>
    <th>${lang.translate('TNT Domestic Shipments')}</th>
    <#if userLevel == 1 || userLevel == 2>
    <th>${lang.translate('DHL Domestic Cost')}</th>
    </#if>
    <th>${lang.translate('DHL Domestic Revenue')}</th>
    <th>${lang.translate('DHL Domestic Franchisee Cost')}</th>
    <th>${lang.translate('DHL Domestic Gross Margin')}</th>
    <th>${lang.translate('DHL Domestic Shipments')}</th>
    <#if userLevel == 1 || userLevel == 2>
    <th>${lang.translate('Toll Priority Cost')}</th>
    </#if>
    <th>${lang.translate('Toll Priority Revenue')}</th>
    <th>${lang.translate('Toll Priority Franchisee Cost')}</th>
    <th>${lang.translate('Toll Priority Gross Margin')}</th>
    <th>${lang.translate('Toll Priority Shipments')}</th>
    <#if userLevel == 1 || userLevel == 2>
    <th>${lang.translate('Other Cost')}</th>
    </#if>
    <th>${lang.translate('Other Revenue')}</th>
    <th>${lang.translate('Other Franchisee Cost')}</th>
    <th>${lang.translate('Other Margin')}</th>
    <th>${lang.translate('Other Shipments')}</th>
    <th>${lang.translate('Total Shipment')}</th>
    <th>${lang.translate('Avg.Margin Per AWB')}</th>
    </thead>
    <tbody>
    <#list summaryList as summary>
    <tr>
        <td>${(summary.customerCode)!" "}</td>
        <td>${(summary.customerName)!" "}</td>
        <td>${(summary.saleRepName)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.customerRevenue)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.franchiseCost)!"0.00"}</td>
        <#if userLevel == 1 || userLevel == 2>
            <td align="right">${(currencySymbol)!"$"}${(summary.carrierCost)!"0.00"}</td>
        </#if>
        <td align="right">${(currencySymbol)!"$"}${(summary.grossMargin)!"0.00"}</td>
        <td align="right">${(summary.grossMarginPct)!"0.00"}%</td>
        <#if userLevel == 1 || userLevel == 2>
            <td align="right">${(currencySymbol)!"$"}${(summary.dhlCost)!"0.00"}</td>
        </#if>
        <td align="right">${(currencySymbol)!"$"}${(summary.dhlRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.dhlFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.dhlGrossMargin)!"0.00"}</td>
        <td align="right">${(summary.dhlShipmentCount)!"0"}</td>
        <#if userLevel == 1 || userLevel == 2>
            <td align="right">${(currencySymbol)!"$"}${(summary.carrierCostBaseCharge)!"0.00"}</td>
        </#if>
        <td align="right">${(currencySymbol)!"$"}${(summary.customerCostBaseCharge)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.marginOnBaseCharge)!"0.00"}</td>
        <#if userLevel == 1 || userLevel == 2>
            <td align="right">${(currencySymbol)!"$"}${(summary.tntDomCost)!"0.00"}</td>
        </#if>
        <td align="right">${(currencySymbol)!"$"}${(summary.tntDomRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.tntDomFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.tntDomGrossMargin)!"0.00"}</td>
        <td align="right">${(summary.tntDomShipmentCount)!"0"}</td>
        <#if userLevel == 1 || userLevel == 2>
            <td align="right">${(currencySymbol)!"$"}${(summary.dhlDomCost)!"0.00"}</td>
        </#if>
        <td align="right">${(currencySymbol)!"$"}${(summary.dhlDomRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.dhlDomFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.dhlDomGrossMargin)!"0.00"}</td>
        <td align="right">${(summary.dhlDomShipmentCount)!"0"}</td>
        <#if userLevel == 1 || userLevel == 2>
            <td align="right">${(currencySymbol)!"$"}${(summary.tollPriorityCost)!"0.00"}</td>
        </#if>
        <td align="right">${(currencySymbol)!"$"}${(summary.tollPriorityRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.tollPriorityFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.tollPriorityGrossMargin)!"0.00"}</td>
        <td align="right">${(summary.tollPriorityShipmentCount)!"0"}</td>
        <#if userLevel == 1 || userLevel == 2>
            <td align="right">${(currencySymbol)!"$"}${(summary.otherCost)!"0.00"}</td>
        </#if>
        <td align="right">${(currencySymbol)!"$"}${(summary.otherRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.otherFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.otherGrossMargin)!"0.00"}</td>
        <td align="right">${(summary.otherShipmentCount)!"0"}</td>
        <td align="right">${(summary.totalShipmentCount)!"0"}</td>
        <td align="right">${(currencySymbol)!"$"}${(summary.avgMarginPerAwb)!"0.00"}</td>
    </tr>
    </#list>
    <tr>
        <td>Total</td>
        <td colspan="2">${(totalCustomer)!"0"} customer(s)</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.customerRevenue)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.franchiseCost)!"0.00"}</td>
    <#if userLevel == 1 || userLevel == 2>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.carrierCost)!"0.00"}</td>
    </#if>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.grossMargin)!"0.00"}</td>
        <td align="right">${(totalSummary.grossMarginPct)!"0.00"}%</td>
    <#if userLevel == 1 || userLevel == 2>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.dhlCost)!"0.00"}</td>
    </#if>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.dhlRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.dhlFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.dhlGrossMargin)!"0.00"}</td>
        <td align="right">${(totalSummary.dhlShipmentCount)!"0"}</td>
    <#if userLevel == 1 || userLevel == 2>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.carrierCostBaseCharge)!"0.00"}</td>
    </#if>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.customerCostBaseCharge)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.marginOnBaseCharge)!"0.00"}</td>
    <#if userLevel == 1 || userLevel == 2>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.tntDomCost)!"0.00"}</td>
    </#if>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.tntDomRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.tntDomFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.tntDomGrossMargin)!"0.00"}</td>
        <td align="right">${(totalSummary.tntDomShipmentCount)!"0"}</td>
    <#if userLevel == 1 || userLevel == 2>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.dhlDomCost)!"0.00"}</td>
    </#if>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.dhlDomRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.dhlDomFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.dhlDomGrossMargin)!"0.00"}</td>
        <td align="right">${(totalSummary.dhlDomShipmentCount)!"0"}</td>
    <#if userLevel == 1 || userLevel == 2>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.tollPriorityCost)!"0.00"}</td>
    </#if>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.tollPriorityRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.tollPriorityFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.tollPriorityGrossMargin)!"0.00"}</td>
        <td align="right">${(totalSummary.tollPriorityShipmentCount)!"0"}</td>
    <#if userLevel == 1 || userLevel == 2>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.otherCost)!"0.00"}</td>
    </#if>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.otherRevenue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.otherFranchiseCost)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.otherGrossMargin)!"0.00"}</td>
        <td align="right">${(totalSummary.otherShipmentCount)!"0"}</td>
        <td align="right">${(totalSummary.totalShipmentCount)!"0"}</td>
        <td align="right">${(currencySymbol)!"$"}${(totalSummary.avgMarginPerAwb)!"0.00"}</td>
    </tr>
    </tbody>
</table>
</body>
</html>