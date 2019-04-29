<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        @page {
            size: A4 landscape;
            margin: 5mm 5mm;
            @bottom-right {
                font-family: Arial;
                font-size: 10px;
                content: counter(page);
            }
        }

        body {
            font-family: Arial;
            font-size: 9px;
            color: #000;
            padding-left: 20px;
            padding-right: 20px;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            margin-bottom: 5px;
            page-break-inside: auto
        }

        table thead {
            background-color: #c5c5c5;
        }

        table tr {
            page-break-inside: avoid;
            page-break-after: auto
        }

        .no-border {
            border: none;
        }

        .border {
            border-collapse: collapse;
        }

        .border thead tr:first-child th {
            border: 1px solid #000;
            border-bottom: none;
        }

        .border thead tr th {
            border: 1px solid #000;
            font-size: 9px;
        }

        .border tbody tr td {
            border: 1px solid #000;
            padding: 1px;
        }

        .border tr td {
            border: 1px solid #000;
            padding: 1px;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td align="center">
            <h3>${(lang.translate('Webship History'))!" "}</h3>
        </td>
    </tr>
</table>
<table class="border" style="width: 100% !important; float: right">
    <thead>
    <tr bgcolor="#fff">
        <th>${lang.translate('Invoice #')}</th>
        <th>${lang.translate('Customer #')}</th>
        <th>${lang.translate('Connote #')}</th>
        <th>${lang.translate('Carrier')}</th>
        <th>${lang.translate('Service')}</th>
        <th>${lang.translate('Warranty Revenue')}</th>
    <#if columnFlags.hasCreateDate>
        <th>${lang.translate('Create Date')}</th>
    </#if>
    <#if columnFlags.hasShipDate>
        <th>${lang.translate('Ship Date')}</th>
    </#if>
    <#if columnFlags.hasPickupDate>
        <th>${lang.translate('Pickup Date')}</th>
    </#if>
    <#if columnFlags.hasPieces>
        <th>${lang.translate('Pieces')}</th>
    </#if>
    <#if columnFlags.hasDeadWeight>
        <th>${lang.translate('Dead Weight')}</th>
    </#if>
    <#if columnFlags.hasDimension>
        <th>${lang.translate('Dimension')}</th>
    </#if>
    <#if columnFlags.hasWeight>
        <th>${lang.translate('Weight')}</th>
    </#if>
    <#if columnFlags.hasQuoted>
        <th>${lang.translate('Quoted')}</th>
    </#if>
    <#if columnFlags.hasSender>
        <th>${lang.translate('Sender Address')}</th>
    </#if>
    <#if columnFlags.hasReceiver>
        <th>${lang.translate('Receiver Address')}</th>
    </#if>
    </tr>
    </thead>
    <tbody>
    <#if listData?has_content>
        <#list listData as dt>
        <tr>
            <td>${(dt.invoiceCode)!" "}</td>
            <td>${(dt.customerCode)!" "}</td>
            <td>${(dt.airbillNumber)!" "}</td>
            <td>${(dt.carrier)!" "}</td>
            <td>${(dt.service)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(dt.customerInsuredAmount)!" "}</td>
            <#if columnFlags.hasCreateDate>
                <td>${(dt.createDate)!" "}</td>
            </#if>
            <#if columnFlags.hasShipDate>
                <td>${(dt.shipDate)!" "}</td>
            </#if>
            <#if columnFlags.hasPickupDate>
                <td>${(dt.pickupDate)!" "}</td>
            </#if>
            <#if columnFlags.hasPieces>
                <td>${(dt.noOfPieces)!" "}</td>
            </#if>
            <#if columnFlags.hasDeadWeight>
                <td align="right">${(dt.deadWeight)!" "}${(dt.weightUnit)!" "}</td>
            </#if>
            <#if columnFlags.hasDimension>
                <td>${(dt.dimension)!" "}${(dt.dimensionUnit)!" "}</td>
            </#if>
            <#if columnFlags.hasWeight>
                <td align="right">${(dt.weight)!" "}${(dt.weightUnit)!" "}</td>
            </#if>
            <#if columnFlags.hasQuoted>
                <td align="right">${(currencySymbol)!"$"}${(dt.quoted)!" "}</td>
            </#if>
            <#if columnFlags.hasSender>
                <td>${(dt.senderCompanyName?html)!" "}<br/>
                ${(dt.senderContactName?html)!" "}<br/>
                ${(dt.senderAddress1?html)!" "}<br/>
                    <#if dt.senderAddress2?? && dt.senderAddress2 != ''>
                    ${(dt.senderAddress2?html)!" "}<br/>
                    </#if>
                    <#if dt.senderCity?? && dt.senderCity != ''>${(dt.senderCity)!" "}
                        ,</#if><#if dt.senderPostalCode?? && dt.senderPostalCode != ''>${(dt.senderPostalCode)!" "}
                        ,</#if><#if dt.senderState?? && dt.senderState != ''>${(dt.senderState)!" "}</#if><br/>
                ${(dt.senderCountryName)!" "}</td>
            </#if>
            <#if columnFlags.hasReceiver>
                <td>${(dt.receiverCompanyName?html)!" "}<br/>
                ${(dt.receiverContactName?html)!" "}<br/>
                ${(dt.receiverAddress1?html)!" "}<br/>
                    <#if dt.receiverAddress2?? && dt.receiverAddress2 != ''>
                    ${(dt.receiverAddress2?html)!" "}<br/>
                    </#if>
                    <#if dt.receiverCity?? && dt.receiverCity != ''>${(dt.receiverCity)!" "}
                        ,</#if><#if dt.receiverPostalCode?? && dt.receiverPostalCode != ''>${(dt.receiverPostalCode)!" "}
                        ,</#if><#if dt.receiverState?? && dt.receiverState != ''>${(dt.receiverState)!" "}</#if><br/>
                ${(dt.receiverCountryName)!" "}</td>
            </#if>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>