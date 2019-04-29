<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Import Totals Print'))!" "}</title>
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
            text-align: left;
            padding: 4px;
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
<table style="margin-bottom: 0px;">
    <tr>
        <td>
            <table>
                <tr>
                    <td align="center"><span
                            style="font-size: 14px; font-weight: bold">${(lang.translate('Import Totals'))!" "}</span>
                    </td>
                    <td align="right" width="25"><img src="images/printer67.png" width="25" class="img"/></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table class="border">
    <thead>
    <tr>
        <th>${(lang.translate('Invoice #'))!" "}</th>
        <th>${(lang.translate('Customer Total'))!" "}</th>
        <th>${(lang.translate('Franchise Chg.'))!" "}</th>
        <th>${(lang.translate('Carrier Cost'))!" "}</th>
        <th>${(lang.translate('Margin'))!" "}</th>
        <th>${(lang.translate('Airbill Count'))!" "}</th>
    </tr>
    </thead>
    <tbody>
    <#if listData?has_content>
        <#list listData as dt>
        <tr>
            <td>${(dt.invoiceCode)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.customerCost)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.franchiseCost)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.carrierCost)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.margin)!" "}</td>
            <td align="right">${(dt.airbillCount)!" "}</td>
        </tr>
        </#list>
    </#if>
    <tr>
        <td>${(lang.translate('Total'))!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(total.customerCost)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(total.franchiseCost)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(total.carrierCost)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(total.margin)!" "}</td>
        <td align="right">${(total.airbillCount)!" "}</td>
    </tr>
    </tbody>
</table>
</body>
</html>