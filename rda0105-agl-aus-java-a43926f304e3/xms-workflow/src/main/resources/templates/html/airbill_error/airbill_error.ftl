<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Airbill Errors Print'))!" "}</title>
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
                            style="font-size: 14px; font-weight: bold">${(lang.translate('Airbill Errors'))!" "}</span>
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
        <th>${(lang.translate('Invoice Date'))!" "}</th>
        <th>${(lang.translate('Customer #'))!" "}</th>
    <#if showSenderAddress>
        <th>${(lang.translate('Sender'))!" "}</th>
        <th>${(lang.translate('Sender Attn'))!" "}</th>
        <th>${(lang.translate('Sender Address'))!" "}</th>
        <th>${(lang.translate('Sender City'))!" "}</th>
        <th>${(lang.translate('Sender State'))!" "}</th>
        <th>${(lang.translate('Sender Zip'))!" "}</th>
    </#if>
        <th>${(lang.translate('Reference'))!" "}</th>
        <th>${(lang.translate('Service'))!" "}</th>
    <#if showReceiverAddress>
        <th>${(lang.translate('Receiver'))!" "}</th>
        <th>${(lang.translate('Receiver Attn'))!" "}</th>
        <th>${(lang.translate('Receiver Address'))!" "}</th>
        <th>${(lang.translate('Receiver City'))!" "}</th>
        <th>${(lang.translate('Receiver State'))!" "}</th>
        <th>${(lang.translate('Receiver Zip'))!" "}</th>
    </#if>
    <#if showAccount>
        <th>${(lang.translate('Carrier Cost'))!" "}</th>
        <th>${(lang.translate('Sender Account'))!" "}</th>
        <th>${(lang.translate('Receiver Account'))!" "}</th>
        <th
        ${(lang.translate('>BILL TO Account'))!" "}</th>
        <th>${(lang.translate('3P Account'))!" "}</th>
    </#if>
        <th>${(lang.translate('Ship Date'))!" "}</th>
    </tr>
    </thead>
    <tbody>
    <#if listData?has_content>
        <#list listData as dt>
        <tr>
            <td>${(dt.airbillNumber)!" "}</td>
            <td>${(dt.invoiceDate)!" "}</td>
            <td>${(dt.customerCode)!" "}</td>
            <#if showSenderAddress>
                <td>${(dt.senderCompanyName)!" "}</td>
                <td>${(dt.senderContactName)!" "}</td>
                <td>${(dt.senderAddress1)!" "}</td>
                <td>${(dt.senderCity)!" "}</td>
                <td>${(dt.senderState)!" "}</td>
                <td>${(dt.senderPostalCode)!" "}</td>
            </#if>
            <td>${(dt.reference)!" "}</td>
            <td>${(dt.service)!" "}</td>
            <#if showReceiverAddress>
                <td>${(dt.receiverCompanyName)!" "}</td>
                <td>${(dt.receiverContactName)!" "}</td>
                <td>${(dt.receiverAddress1)!" "}</td>
                <td>${(dt.receiverCity)!" "}</td>
                <td>${(dt.receiverState)!" "}</td>
                <td>${(dt.receiverPostalCode)!" "}</td>
            </#if>
            <#if showAccount>
                <td align="right">${(dt.carrierCost)!" "}</td>
                <td>${(dt.senderAccount)!" "}</td>
                <td>${(dt.receiverAccount)!" "}</td>
                <td>${(dt.billingToAccount)!" "}</td>
                <td>${(dt.p3Account)!" "}</td>
            </#if>
            <td>${(dt.shipDate)!" "}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>