<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Customer Credit Note Detail Print')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('Customer Credit Note Detail Print')}</span>
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
        <th>${lang.translate('Credit Note Number')}</th>
        <th>${lang.translate('Customer')}</th>
        <th>${lang.translate('Credit Note Date')}</th>
        <th>${lang.translate('Amount')}</th>
        <th>${lang.translate('GST')}</th>
        <th>${lang.translate('Total')}</th>
    </tr>
    </thead>
    <tbody>
    <#if customerList?has_content>
        <#list customerList as customer>
        <tr>
            <td>${(customer.creditCode)!" "}</td>
            <td>${(customer.customerName)!" "}</td>
            <td>${(customer.createDate)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.amount)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.gst)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.total)!"0.00"}</td>
        </tr>
        </#list>
    </#if>
    <#if summary??>
    <tr>
        <th colspan="3" style="text-align: right !important">${lang.translate('Total')}</th>
        <th style="text-align: right !important">${(currencySymbol)!"$"}${(summary.amount)!"0.00"}</th>
        <th style="text-align: right !important">${(currencySymbol)!"$"}${(summary.gst)!"0.00"}</th>
        <th style="text-align: right !important">${(currencySymbol)!"$"}${(summary.total)!"0.00"}</th>
    </tr>
    </#if>
    </tbody>
</table>
</body>
</html>