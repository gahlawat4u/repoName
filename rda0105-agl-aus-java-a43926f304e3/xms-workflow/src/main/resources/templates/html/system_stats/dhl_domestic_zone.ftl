<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('DHL Domestic Zone')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('DHL Domestic Zone')}</span>
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
        <th>${lang.translate('Origin Name')}</th>
        <th>${lang.translate('Origin Code')}</th>
        <th>${lang.translate('Destination Name')}</th>
        <th>${lang.translate('Destination Code')}</th>
        <th>${lang.translate('Zone')}</th>
    </tr>
    </thead>
    <tbody>
    <#if datas?has_content>
        <#list datas as data>
        <tr>
            <td>${(data.originName)!" "}</td>
            <td>${(data.originCode)!" "}</td>
            <td>${(data.destinationName)!" "}</td>
            <td>${(data.destinationCode)!" "}</td>
            <td>${(data.zone)!" "}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>