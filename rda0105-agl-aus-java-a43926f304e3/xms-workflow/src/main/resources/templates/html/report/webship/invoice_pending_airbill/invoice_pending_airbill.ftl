<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Invoice Pending Airbills Print')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('Invoice Pending Airbills')}</span>
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
        <th>${lang.translate('Airbill #')}</th>
        <th>${lang.translate('Carrier Name')}</th>
        <th>${lang.translate('Service')}</th>
        <th>${lang.translate('Weight')}</th>
        <th>${lang.translate('Pieces')}</th>
        <th>${lang.translate('Create Date')}</th>
        <th>${lang.translate('Ship Date')}</th>
        <th>${lang.translate('Destination')}</th>
        <th>${lang.translate('Dest.Country')}</th>
    </tr>
    </thead>
    <tbody>
    <#if histories?has_content>
        <#list histories as history>
        <tr>
            <td>${(history.customerCode)!" "}</td>
            <td>${(history.customerName)!" "}</td>
            <td>${(history.airbillNumber)!" "}</td>
            <td>${(history.carrierName)!" "}</td>
            <td>${(history.serviceName)!" "}</td>
            <td align="right"><#if history.weight?has_content>${(history.weight)!"0"}${(history.weightUnit)!"kg"}</#if></td>
            <td align="right">${(history.noOfPieces)!" "}</td>
            <td>${(history.createDate)!" "}</td>
            <td>${(history.shipmentDate)!" "}</td>
            <td><#if history.city?has_content>${(history.city)!" "},${(history.postalCode)!" "}</#if></td>
            <td>${(history.countryName)!" "}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>