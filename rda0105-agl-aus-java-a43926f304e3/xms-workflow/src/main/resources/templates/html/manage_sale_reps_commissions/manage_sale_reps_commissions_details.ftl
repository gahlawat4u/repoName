<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Sales Rep Commissions Print'))!" "}</title>
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
<table style="margin-bottom: 10px;">
    <tr>
        <td align="center" bgcolor="#FFFFFF"><span
                style="font-size: 14px; font-weight: bold">${(lang.translate('Sales Rep Commissions Report'))!" "}</span>
        </td>
    </tr>
</table>
<table cellpadding="2" cellspacing="0" border="1" class="border">
    <thead>
    <tr>
        <th>${(lang.translate('Airbill Number'))!" "}</th>
        <th>${(lang.translate('Invoice Number'))!" "}</th>
        <th>${(lang.translate('Customer Name'))!" "}</th>
        <th>${(lang.translate('Customer Total'))!" "}</th>
        <th>${(lang.translate('Franchise Cost'))!" "}</th>
        <th>${(lang.translate('Previously Paid'))!" "}</th>
        <th>${(lang.translate('Margin On Customer Total'))!" "}</th>
    </tr>
    </thead>
    <tbody>
    <#if listData?has_content>
        <#list listData as inv>
        <tr>
            <td>${(inv.airbillNumber?html)!" "}</td>
            <td>${(inv.invoiceCode?html)!" "}</td>
            <td>${(inv.customerName?html)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.customerCost)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.franchiseCost)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.totalPaid)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.margin)!" "}</td>
        </tr>
        </#list>
    <#else>
    <tr>
        <td colspan="7" align="center">${lang.translate('No data available ...')!" "}</td>
    </tr>
    </#if>
    </tbody>
</table>
</body>
</html>