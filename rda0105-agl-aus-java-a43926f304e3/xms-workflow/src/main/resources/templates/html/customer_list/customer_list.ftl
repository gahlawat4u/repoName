<!DOCTYPE html>
<html>
<head>
    <title>Customer List Print</title>
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
                    <td align="center"><span style="font-size: 14px; font-weight: bold">Customer List</span></td>
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
        <td>Customer #</td>
        <td>Name</td>
        <td>MTD Rev.</td>
        <td>YTD Rev.</td>
        <td>Last Shipment Date/Time</td>

    </tr>
    </thead>
    <tbody>
    <#if customerList?has_content>
        <#list customerList as customer>
        <tr>
            <td>${(customer.customerCode)!" "}</td>
            <td>${(customer.customerName)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.mtd)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(customer.ytd)!"0.00"}</td>
            <td>${(customer.lastShipmentDate)!" "}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>