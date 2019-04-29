<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Cost Deviations Report Print'))!" "}</title>
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
                            style="font-size: 14px; font-weight: bold">${(lang.translate('Cost Deviations Report'))!" "}</span>
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
        <th>${(lang.translate('Calculated Franchise Cost'))!" "}</th>
        <th>${(lang.translate('Franchise Cost on Airbill'))!" "}</th>
        <th>${(lang.translate('Difference'))!" "}</th>
        <th>${(lang.translate('Carrier'))!" "}</th>
        <th>${(lang.translate('Weight'))!" "}</th>
    </tr>
    </thead>
    <tbody>
    <#if listData?has_content>
        <#list listData as dt>
        <tr>
            <td>${(dt.airbillNumber)!" "}</td>
            <td>${(dt.invoiceCode)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.calculatedFranchiseCost)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.franchiseCost)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.difference)!" "}</td>
            <td>${(dt.serviceName)!" "}</td>
            <td align="right">${(dt.weight)!" "}</td>
        </tr>
        </#list>
    </#if>
    <tr>
        <td colspan="7"><span
                class="b4"> <b>${(lang.translate('Total Calculated Franchise Costs:'))!" "}</b> ${(currencySymbol)!"$"}${(total.calculatedFranchiseCost)!" "}
				</span> <span
                class="b4"> <b>| ${(lang.translate('Total Franchise Costs on Airbill:'))!" "}</b> ${(currencySymbol)!"$"}${(total.franchiseCost)!" "}
				</span> <span
                class="b4"> <b>| ${(lang.translate('Total Differences:'))!" "}</b> ${(currencySymbol)!"$"}${(total.difference)!" "}
				</span></td>
    </tr>
    </tbody>
</table>
</body>
</html>