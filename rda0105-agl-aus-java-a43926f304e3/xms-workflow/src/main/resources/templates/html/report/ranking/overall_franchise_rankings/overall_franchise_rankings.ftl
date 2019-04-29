<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Overall Franchise Rankings Print')}</title>
    <style type="text/css">
        @page {
            size: A4;
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

        .border tr th {
            border: 1px solid #ddd;
            padding: 4px;
            text-align: left;
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
<table style="margin-bottom: 0px;">
    <tr>
        <td>
            <table>
                <tr>
                    <td align="center"><span
                            style="font-size: 14px; font-weight: bold">${lang.translate('Overall Franchise Rankings Report')}</span>
                    </td>
                    <td align="right" width="25"><img src="${(realPath)!" "}images/printer67.png" width="25" class="img"
                                                      onclick="this.remove();window.print();"/></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table class="border">
    <thead>
    <tr>
        <th>${lang.translate('Rank')}</th>
        <th>${lang.translate('Fran')}</th>
        <th>${lang.translate('Territory')}</th>
        <th>${lang.translate('Active Customers')}</th>
        <th>${lang.translate('Activations')}</th>
        <th>${lang.translate('Shipments')}</th>
        <th>${lang.translate('Weight')}</th>
        <th>${lang.translate('Cust. Rev.')}</th>
        <th>${lang.translate('Rev./Ship')}</th>
        <th>${lang.translate('Franchise Cost')}</th>
        <th>${lang.translate('Gross Margin')}</th>
        <th>${lang.translate('Margin/Ship')}</th>
        <th>${lang.translate('Margin/Weight')}</th>
    </tr>
    </thead>
    <tbody>
    <#if franchiseList?has_content>
        <#list franchiseList as franchise>
        <tr>
            <td>${(franchise.rank)!" "}</td>
            <td>${(franchise.franchiseCode)!" "}</td>
            <td>${(franchise.territory)!" "}</td>
            <td align="right">${(franchise.activateCustomers)!" "}</td>
            <td align="right">${(franchise.activations)!" "}</td>
            <td align="right">${(franchise.shipments)!" "}</td>
            <td align="right">${(franchise.weights)!" "}</td>
            <#if excludeGST>
                <td align="right">${(currencySymbol)!"$"}${(franchise.revenueExcGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.revenuePerShipExcGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.franchiseCostExcGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.marginExcGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.marginPerShipExcGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.marginPerWeightExcGst)!" "}</td>
            <#else>
                <td align="right">${(currencySymbol)!"$"}${(franchise.revenueIncGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.revenuePerShipIncGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.franchiseCostIncGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.marginIncGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.marginPerShipIncGst)!" "}</td>
                <td align="right">${(currencySymbol)!"$"}${(franchise.marginPerWeightIncGst)!" "}</td>
            </#if>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>