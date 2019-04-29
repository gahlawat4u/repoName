<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Webship Quote History Print')}</title>
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
                            style="font-size: 14px; font-weight: bold">${lang.translate('Webship Quote History')}</span>
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
        <th>${lang.translate('Customer #')}</th>
        <th>${lang.translate('Quote Date')}</th>
        <th>${lang.translate('Sender Address')}</th>
        <th>${lang.translate('Receiver Address')}</th>
        <th>${lang.translate('Service')}</th>
        <th>${lang.translate('Package Type')}</th>
        <th>${lang.translate('Pieces Weight Dimensions')}</th>
        <th>${lang.translate('Contents')}</th>
        <th>${lang.translate('IP Address')}</th>
    </tr>
    </thead>
    <tbody>
    <#if histories?has_content>
        <#list histories as history>
        <tr>
            <td>${(history.customerName)!" "}</td>
            <td>${(history.customerCode)!" "}</td>
            <td>${(history.quoteDate)!" "}</td>
            <td>${(history.senderCompanyName)!" "}<br/>${(history.senderContactName)!" "}
                <br/>${(history.senderAddress1)!" "}<br/>${(history.senderAddress2)!" "}<br/>${(history.senderCity)!" "}
                <br/>${(history.senderPostalCode)!" "}<br/>${(history.senderState)!" "}</td>
            <td>${(history.receiverCompanyName)!" "}<br/>${(history.receiverContactName)!" "}
                <br/>${(history.receiverAddress1)!" "}<br/>${(history.receiverAddress2)!" "}
                <br/>${(history.receiverCity)!" "}<br/>${(history.receiverPostalCode)!" "}
                <br/>${(history.receiverState)!" "}<br/></td>
            <td>${(history.shipmentTypeName)!" "}</td>
            <td>${(history.packageName)!" "}</td>
            <td>${(history.noOfPieces)!" "}<br/>${(history.weight)!" "} ${(history.weightUnit)!" "}
                <br/>${(history.dimensionL)!" "}x${(history.dimensionW)!" "}
                x${(history.dimensionH)!" "} ${(history.dimensionUnit)!" "}</td>
            <td>${(history.content)!" "}</td>
            <td>${(history.ipAddress)!" "}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>