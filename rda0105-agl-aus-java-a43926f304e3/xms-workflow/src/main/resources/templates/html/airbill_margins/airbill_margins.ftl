<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Negative Margins Print'))!" "}</title>
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
                            style="font-size: 14px; font-weight: bold">${(lang.translate('Negative Margins'))!" "}</span>
                    </td>
                    <td align="right" width="25"><img src="${(realPath)!" "}images/printer67.png"
                                                      onclick="this.remove();window.print();" width="25" class="img"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table class="border">
    <thead>
    <tr>
        <th>${(lang.translate('Airbill #'))!" "}</th>
        <th>${(lang.translate('Invoice #'))!" "}</th>
        <th>${(lang.translate('Type'))!" "}</th>
        <th>${(lang.translate('Customer Cost'))!" "}</th>
        <th>${(lang.translate('Franchise Cost'))!" "}</th>
        <th>${(lang.translate('Franchise Margin'))!" "}</th>
        <th>${(lang.translate('Franchise Margin Percent'))!" "}</th>
    </tr>
    </thead>
    <tbody>
    <#if listData?has_content>
        <#list listData as dt>
        <tr id="md-20-link">
            <td>${(dt.airbillNumber)!" "}</td>
            <td>${(dt.invoiceCode)!" "}</td>
            <td>${(dt.shipmentTypeName)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.customerCost)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.franchiseCost)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.franchiseMargin)!"0.00"}</td>
            <td align="right">${(dt.franchiseMarginPct)!"0.00"}%</td>
        </tr>
        </#list>
    </#if>
    <tr>
        <td colspan="7"><span class="b4"> <b>${(lang.translate('Total Airbills:'))!" "}</b> ${(listData?size)!"0"}
				</span> <span
                class="b4"> <b>| ${(lang.translate('Customer Total:'))!" "}</b> ${(currencySymbol)!"$"}${(total.customerCost)!"0.00"}
				</span> <span
                class="b4"> <b>| ${(lang.translate('Franchise Cost:'))!" "}</b> ${(currencySymbol)!"$"}${(total.franchiseCost)!"0.00"}
				</span></td>
    </tr>
    </tbody>
</table>
</body>
</html>
