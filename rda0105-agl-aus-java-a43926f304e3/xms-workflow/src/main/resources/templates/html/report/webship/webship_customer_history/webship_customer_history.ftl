<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Webship Customer History Print')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('Webship Customer History')}</span>
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
    <#if displayColumns?has_content>
        <#list displayColumns as col>
            <th>${(col)!" "}</th>
        </#list>
    </#if>
    </tr>
    </thead>
    <tbody>
    <#if histories?has_content>
        <#list histories as history>
        <tr>
            <#if columns?has_content>
                <#list columns as col>
                    <#if col?starts_with("d_")>
                        <td align="right">${(history[col])}</td>
                    <#else>
                        <td>${(history[col])}</td>
                    </#if>
                </#list>
            </#if>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>