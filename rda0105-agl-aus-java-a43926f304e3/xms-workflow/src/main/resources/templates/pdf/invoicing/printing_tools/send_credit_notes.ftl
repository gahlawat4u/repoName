<html>
<head>
    <style type="text/css">
        html * {
            font-size: 12px;
            font-family: Arial !important;
        }

        @page {
            size: A4 landscape;
        }

        body {
            font-family: Arial;
            font-size: 12px;
        }

        table {
            width: 100%;
            font-size: 12px;
        }

        table th {
            font-weight: bold;
        }

        .text-bold {
            font-weight: bold;
        }

        .title {
            margin: 0 auto;
            text-align: center;
        }

        .page-break {
            page-break-after: always;
        }

        .no-border {
            border: 0;
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
            font-size: 12px;
        }

        .border tbody tr td {
            border: 1px solid #000;
            font-size: 12px;
        }

        .border tr td {
            border: 1px solid #000;
            font-size: 12px;
        }

        h1 {
            font-size: 30px !important;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td align="left" valign="top" width="20%"><img src="${logo}" width="250"/></td>

        <td width="60%" align="center"><h1>
            <br/> ${lang.translate('Credit Notes')}
        </h1></td>

        <td width="20%"></td>
    </tr>
    <tr>
        <td valign="top" width="20%">
            <table width="100%" cellspacing="0" cellpadding="5" align="left" class="border">

                <tr>
                    <td>${lang.translate('CREDIT TO:')} <br/>${creditInfo.billingCustomerName?html} <br/>
                    ${creditInfo.billingContactName?html} <br/>
                    ${creditInfo.billingAddress1?html} <br/>
                    ${creditInfo.billingAddress2?html} <br/>
                    ${creditInfo.billingCity?html} <br/>
                    ${creditInfo.countryName?html} <br/>
                    ${creditInfo.billingPostalCode?html}
                    </td>
                </tr>
            </table>
        </td>
        <td width="60%" valign="top">
            <table width="100%" cellspacing="0" cellpadding="4" align="center" class="border">
                <tr>
                    <td width="20%">${lang.translate('Credit Number')}</td>
                    <td width="20%">${lang.translate('Credit Date')}</td>
                    <td width="20%">${lang.translate('Customer #')}</td>
                    <td width="20%">${lang.translate('Credits')}</td>
                    <td width="20%">${lang.translate('Total Credited')}</td>
                </tr>
                <tr>
                    <td width="20%">${creditInfo.creditCode}</td>
                    <td width="20%">${creditInfo.createDate}</td>
                    <td width="20%">${creditInfo.customerCode}</td>
                    <td width="20%">${creditInfo.credits}</td>
                    <td width="20%"><font face="helvetica">$</font> ${creditInfo.totalCredited}</td>
                </tr>
            </table>
        </td>
        <td width="20%"></td>
    </tr>
    <tr>
        <td width="20%"></td>

        <td width="60%">
            <table width="100%" cellspacing="0" cellpadding="5" class="border">
                <tr>
                    <td width="30%" bgcolor="#c5c5c5">${lang.translate('GST Summary')}</td>
                    <td width="15%" bgcolor="#c5c5c5">${lang.translate('GST Percent')}</td>
                    <td width="20%" bgcolor="#c5c5c5">${lang.translate('Shipment Amount')}</td>
                    <td width="15%" bgcolor="#c5c5c5">${lang.translate('GST Amount')}</td>
                    <td width="20%" bgcolor="#c5c5c5">${lang.translate('Total Amount')}</td>
                </tr>
            <#if gstSummary?has_content>
                <#list gstSummary as summary>
                    <tr>
                        <#if summary.customerTaxPercent != '0.00'>
                            <td width="30%" bgcolor="#c5c5c5">${lang.translate('GST Shipments')}</td>
                        <#else>
                            <td width="30%" bgcolor="#c5c5c5">${lang.translate('Non-GST Shipments')}</td>
                        </#if>
                        <td width="15%">${(summary.customerTaxPercent)!"0.00"}%</td>
                        <td width="20%"><font
                                face="helvetica">${currencySymbol}</font> ${(summary.customerAmount)!"0.00"}</td>
                        <td width="15%"><font
                                face="helvetica">${currencySymbol}</font> ${(summary.gstCustomerAmount)!"0.00"}</td>
                        <td width="20%"><font face="helvetica">${currencySymbol}</font> ${(summary.total)!"0.00"}</td>
                    </tr>
                </#list>
            <#else>
                <tr>
                    <td width="30%" bgcolor="#c5c5c5">${lang.translate('GST Shipments')}</td>
                    <td width="15%">${creditInfo.taxPercent}%</td>
                    <td width="20%"><font face="helvetica">${currencySymbol}</font> ${creditInfo.shipmentAmount}</td>
                    <td width="15%"><font face="helvetica">${currencySymbol}</font> ${creditInfo.gstAmount}</td>
                    <td width="20%"><font face="helvetica">${currencySymbol}</font> ${creditInfo.totalAmount}</td>

                </tr>

                <tr>

                    <td width="30%" bgcolor="#c5c5c5">${lang.translate('Non-GST Shipments')}</td>
                    <td width="15%">0.00%</td>
                    <td width="20%"><font face="helvetica">${currencySymbol}</font> ${creditInfo.nonShipmentAmount}</td>
                    <td width="15%"><font face="helvetica">${currencySymbol}</font> ${creditInfo.nonGstAmount}</td>
                    <td width="20%"><font face="helvetica">${currencySymbol}</font> ${creditInfo.nonShipmentAmount}</td>

                </tr>
            </#if>
            </table>
        </td>
        <td width="20%"></td>
    </tr>
    <tr>

        <td colspan="3">

            <table width="100%" cellspacing="0" cellpadding="3" align="left" class="border">
                <thead>
                <tr bgcolor="#c5c5c5">
                    <th>${lang.translate('Invoice #')}</th>
                    <th>${lang.translate('Airbill #')}</th>
                    <th>${lang.translate('Invoice Date')}</th>
                    <th>${lang.translate('Reason for Credit')}</th>
                    <th>${lang.translate('Amount')}</th>
                    <th>${lang.translate('GST Amount')}</th>
                    <th>${lang.translate('Total Credit')}</th>
                </tr>
                </thead>
                <tbody>
                <#if listAdj?has_content>
                    <#list listAdj as adj>
                    <tr>
                        <td>${adj.invoiceCode}</td>
                        <td>${adj.airbillNumber}</td>
                        <td>${adj.invoiceDate}</td>
                        <td>${adj.reason?html}</td>
                        <td><font face="helvetica">${currencySymbol}</font>${adj.shipmentAmount}</td>
                        <td><font face="helvetica">${currencySymbol}</font>${adj.gstAmount}</td>
                        <td align="right"><font face="helvetica">${currencySymbol}</font>${adj.totalCredit}</td>
                    </tr>
                    </#list>
                </#if>
                <tr>
                    <td colspan="6" align="right">${lang.translate('Grand Total:')}</td>
                    <td align="right"><font face="helvetica">${currencySymbol}</font> ${creditInfo.totalCredited}</td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
<#if invSignature?trim != ''>
    <tr>
        <td colspan="3" align="center">${(invSignature)?html?replace("\n", "<br/>")!" "}</td>
    </tr>
</#if>
</table>
</body>
</html>