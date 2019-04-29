<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Toll Priority Rate Sheet')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('Toll Priority Rate Sheet')}</span>
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
        <th>${lang.translate('Customer')}</th>
        <th>${lang.translate('Product')}</th>
        <th>${lang.translate('Service')}</th>
        <th>${lang.translate('Zone From')}</th>
        <th>${lang.translate('Zone To')}</th>
        <th>${lang.translate('Price Type')}</th>
        <th>${lang.translate('Min Charge')}</th>
        <th>${lang.translate('Con Rate')}</th>
        <th>${lang.translate('Kgs Included')}</th>
        <th>${lang.translate('Kgs Rate')}</th>
        <th>${lang.translate('GST PCT')}</th>
        <th>${lang.translate('Cubic Conv')}</th>
        <th>${lang.translate('Surcharge PCT')}</th>
    </tr>
    </thead>
    <tbody>
    <#if datas?has_content>
        <#list datas as data>
        <tr>
            <td>${(data.customer)!" "}</td>
            <td>${(data.product)!" "}</td>
            <td>${(data.service)!" "}</td>
            <td>${(data.zoneFrom)!" "}</td>
            <td>${(data.zoneTo)!" "}</td>
            <td>${(data.priceType)!" "}</td>
            <td align="right">${(data.minCharge)!" "}</td>
            <td align="right">${(data.conRate)!" "}</td>
            <td align="right">${(data.kgsIncluded)!" "}</td>
            <td align="right">${(data.kgsRate)!" "}</td>
            <td align="right">${(data.gstPct)!" "}</td>
            <td align="right">${(data.cubicConv)!" "}</td>
            <td align="right">${(data.surchargePct)!" "}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>