<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Logs')}</title>
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
                    <td align="center"><span style="font-size: 14px; font-weight: bold">${lang.translate('Logs')}</span>
                    </td>
                    <td align="right" width="25"><img src="${(realPath)!" "}images/printer67.png" width="25" class="img"
                                                      onclick="this.remove();window.print();"/></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table class="border" style="table-layout: fixed; word-wrap: break-word;">
    <thead>
    <tr>
        <th width="15%">${lang.translate('Date')}</th>
        <th width="5%">${lang.translate('Franchise')}</th>
        <th width="5%">${lang.translate('User')}</th>
        <th width="5%">${lang.translate('Action')}</th>
        <th width="15%">${lang.translate('Table')}</th>
        <th width="15%">${lang.translate('Filter')}</th>
        <th width="40%">${lang.translate('Changes Mode')}</th>
    </tr>
    </thead>
    <tbody>
    <#if eventLogs?has_content>
        <#list eventLogs as log>
        <tr>
            <td>${(log.actionDate)!" "}</td>
            <td>${(log.userCode)!" "}</td>
            <td>${(log.userName)!" "}</td>
            <td>${(log.actionType)!" "}</td>
            <td>${(log.actionTable)!" "}</td>
            <td>${(log.filter?replace("and", "and<br/>"))!" "}</td>
            <td>${(log.changesMode?replace("@,@","<br/>"))!" "}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>