<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Airbill Import Error Logs Print'))!" "}</title>
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
                            style="font-size: 14px; font-weight: bold">${(lang.translate('Airbill Import Error Logs'))!" "}</span>
                    </td>
                    <td align="right" width="25">
                        <button class="btn s37 s44 flr" onclick="this.remove();window.print();"><i
                                class="fa fa-print fa-fw"></i> ${lang.translate('Prints')}</button>
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
        <th>${(lang.translate('Import Date'))!" "}</th>
        <th>${(lang.translate('Notes'))!" "}</th>
    </tr>
    </thead>
    <tbody>
    <#if listData?has_content>
        <#list listData as dt>
        <tr>
            <td>${(dt.airbillNumber)!" "}</td>
            <td>${(dt.importDate)!" "}</td>
            <td>${(dt.note)!" "}</td>
        </tr>
        </#list>
    </#if>
    <tr>
    </tbody>
</table>
</body>
</html>