<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Toll Priority')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('Toll Priority')}</span></td>
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
        <th>${lang.translate('Business Unit')}</th>
        <th>${lang.translate('Country')}</th>
        <th>${lang.translate('State Code')}</th>
        <th>${lang.translate('Post Code')}</th>
        <th>${lang.translate('Suburb Name')}</th>
        <th>${lang.translate('Zone')}</th>
        <th>${lang.translate('Local')}</th>
        <th>${lang.translate('Collection Depot')}</th>
        <th>${lang.translate('Collection Depot Name')}</th>
    </tr>
    </thead>
    <tbody>
    <#if datas?has_content>
        <#list datas as data>
        <tr>
            <td>${(data.businessUnit)!" "}</td>
            <td>${(data.country)!" "}</td>
            <td>${(data.stateCode)!" "}</td>
            <td>${(data.postCode)!" "}</td>
            <td>${(data.suburbName)!" "}</td>
            <td>${(data.zone)!" "}</td>
            <td>${(data.local)!" "}</td>
            <td>${(data.collectionDepot)!" "}</td>
            <td>${(data.collectionDepotName)!" "}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>