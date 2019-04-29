<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('TNT International Zone')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('TNT International Zone')}</span>
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
        <th>${lang.translate('Original File Country Name')}</th>
        <th>${lang.translate('Country Name')}</th>
        <th>${lang.translate('Express Outbound Zone')}</th>
        <th>${lang.translate('Economy Express Outbound Zone')}</th>
        <th>${lang.translate('Express Inbound Zone')}</th>
        <th>${lang.translate('Economy Express Inbound Zone')}</th>
    </tr>
    </thead>
    <tbody>
    <#if datas?has_content>
        <#list datas as data>
        <tr>
            <td>${(data.originalFileCountryName)!" "}</td>
            <td>${(data.countryName)!" "}</td>
            <td align="right">${(data.expressOutboundZone)!" "}</td>
            <td align="right">${(data.economyExpressOutboundZone)!" "}</td>
            <td align="right">${(data.expressInboundZone)!" "}</td>
            <td align="right">${(data.economyExpressInboundZone)!" "}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>