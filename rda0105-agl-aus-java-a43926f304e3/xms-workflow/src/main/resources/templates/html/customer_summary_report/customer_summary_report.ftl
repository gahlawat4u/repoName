<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Customer Summary Print')}</title>
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

        .img {
            cursor: pointer;
        }

        .img:hover {
            opacity: 0.7
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td align="center" colspan="2"><span
                style="font-size: 14px; font-weight: bold">${lang.translate('Customer Summary')}</span></td>
        <td align="right">
            <button class="btn s37 s44 flr" onclick="this.remove();window.print();"><i
                    class="fa fa-print fa-fw"></i> ${lang.translate('Prints')}</button>
        </td>
    </tr>
</table>
<table class="border">
    <thead>
    <#list columns as col>
    <th>${(lang.translate(col.columnName))!" "}</th>
    </#list>
    </thead>
    <tbody>
    <#list summaries as summary>
    <tr>
        <#list columns as col>
            <td <#if col.fieldName?contains('margin') || col.fieldName?contains('cost') || col.fieldName?contains('shipment_count')>align="right"</#if>>${(summary[col.fieldName])!" "}</td>
        </#list>
    </tr>
    </#list>
    <tr>
        <td>Total</td>
        <td colspan="2">${(totalCustomer)!"0"} customer(s)</td>
    <#list totalColumns as totalCol>
        <td align="right">${(summaryTotal[totalCol.fieldName])!"0"}</td>
    </#list>
    </tr>
    </tbody>
</table>
</body>
</html>