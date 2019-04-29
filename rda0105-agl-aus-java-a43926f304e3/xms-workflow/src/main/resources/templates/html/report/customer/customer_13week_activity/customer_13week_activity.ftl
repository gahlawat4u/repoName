<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('13 Week Activity Report Print')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('13 Week Activity Report')}</span>
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
        <th>${lang.translate('Customer #')}</th>
        <th>${lang.translate('Customer Name')}</th>
        <th>${lang.translate('Revenue')}</th>
        <th>${lang.translate('Franchise Cost')}</th>
        <th>${lang.translate('Carrier Cost')}</th>
        <th>${lang.translate('Gross Margin')}</th>
        <th>${lang.translate('% Gross Margin')}</th>
        <th>${lang.translate('#Docs')}</th>
        <th>${lang.translate('Doc Revenue')}</th>
        <th>${lang.translate('#Non Docs')}</th>
        <th>${lang.translate('Non Doc Revenue')}</th>
        <th>${lang.translate('Total Airbills')}</th>
        <th>${lang.translate('Gross Mgn Per AWB')}</th>
        <th>${lang.translate('%Gross Mgn Per AWB')}</th>
    </tr>
    </thead>
    <tbody>
    <#if customerList?has_content>
        <#list customerList as customer>
        <tr>
            <td>${(customer.customerCode)!" "}</td>
            <td>${(customer.customerName)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.revenue)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.franchiseCost)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.carrierCost)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.grossMargin)!"0.00"}</td>
            <td align="right">${(customer.grossMarginPct)!"0.00"}%</td>
            <td align="right">${(customer.documentShipmentCount)!"0"}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.documentRevenue)!"0.00"}</td>
            <td align="right">${(customer.packageShipmentCount)!"0"}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.packageRevenue)!"0.00"}</td>
            <td align="right">${(customer.totalAirbills)!"0"}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.grossMarginPerAwb)!"0.00"}</td>
            <td align="right">${(customer.grossMarginPerAwbPct)!"0.00"}%</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>