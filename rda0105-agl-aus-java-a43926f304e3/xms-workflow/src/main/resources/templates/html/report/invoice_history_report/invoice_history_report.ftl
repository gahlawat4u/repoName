<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Invoice History Print')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('Invoice History Report')}</span>
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
        <th>${lang.translate('Invoice #')}</th>
        <th>${lang.translate('Customer #')}</th>
        <th>${lang.translate('Airbill #')}</th>
        <th>${lang.translate('Carrier')}</th>
        <th>${lang.translate('Service Type')}</th>
        <th>${lang.translate('Warranty Revenue (Exc. GST)')}</th>
        <th>${lang.translate('GST Revenue on Warranty')}</th>
        <th>${lang.translate('Total Warranty Revenue (inc GST)')}</th>
        <th>${lang.translate('Ship Date')}</th>
        <th>${lang.translate('Import Date')}</th>
        <th>${lang.translate('Invoice Date')}</th>
    <#if columnFlags.hasPieces>
        <th>${lang.translate('Pieces')}</th>
    </#if>
    <#if columnFlags.hasDeadWeight>
        <th>${lang.translate('Dead Weight')}</th>
    </#if>
    <#if columnFlags.hasDimension>
        <th>${lang.translate('Dimension')}</th>
    </#if>
    <#if columnFlags.hasQuoted>
        <th>${lang.translate('Quoted')}</th>
    </#if>
        <th>${lang.translate('Charges')}</th>
        <th>${lang.translate('Total')}</th>
    <#if columnFlags.hasSender>
        <th>${lang.translate('Sender Address')}</th>
    </#if>
    <#if columnFlags.hasReceiver>
        <th>${lang.translate('Receiver Address')}</th>
    </#if>
    </tr>
    </thead>
    <tbody>
    <#if listData?has_content>
        <#list listData as dt>
        <tr>
            <td>${(dt.invoiceCode)!" "}</td>
            <td>${(dt.customerCode)!" "}</td>
            <td>${(dt.airbillNumber)!" "}</td>
            <td>${(dt.carrier)!" "}</td>
            <td>${(dt.service)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.insuredAmount)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.insuredGst)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.insuredTotal)!"0.00"}</td>
            <td>${(dt.shipDate)!" "}</td>
            <td>${(dt.importDate)!" "}</td>
            <td>${(dt.invoiceDate)!" "}</td>
            <#if columnFlags.hasPieces>
                <td>${(dt.noOfPieces)!" "}</td>
            </#if>
            <#if columnFlags.hasDeadWeight>
                <td align="right">${(dt.deadWeight)!" "}${(dt.weightUnit)!" "}</td>
            </#if>
            <#if columnFlags.hasDimension>
                <td>${(dt.dimension)!" "}${(dt.dimensionUnit)!" "}</td>
            </#if>
            <#if columnFlags.hasQuoted>
                <td align="right">${(currencySymbol)!"$"}${(dt.quoted)!" "}</td>
            </#if>
            <td>${(dt.charges)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.total)!" "}</td>
            <#if columnFlags.hasSender>
                <td>${(dt.senderAddress1?html)!" "}</td>
            </#if>
            <#if columnFlags.hasReceiver>
                <td>${(dt.receiverAddress1?html)!" "}</td>
            </#if>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<table style="float: right; width: 50%" class="border">
    <thead>
    <tr>
        <th>${lang.translate('International Shipment Count')}</th>
        <th>${lang.translate('Domestic Shipment Count')}</th>
        <th>${lang.translate('International Total Revenue')}</th>
        <th>${lang.translate('Domestic Total Revenue')}</th>
        <th>${lang.translate('GST on Domestic')}</th>
        <th>${lang.translate('Total Revenue inc GST')}</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td align="right">${(total.intlShipmentCount)!" "}</td>
        <td align="right">${(total.domShipmentCount)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(total.intlTotalRevenue)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(total.domTotalRevenue)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(total.domGst)!" "}</td>
        <td align="right">${(currencySymbol)!"$"}${(total.totalRevenueIncGst)!" "}</td>
    </tr>
    </tbody>
</table>
</body>
</html>