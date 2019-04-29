<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        @page {
            size: A4 landscape;
            margin: 5mm 5mm 5mm 5mm;
        }

        body {
            font-family: Arial;
            font-size: 11px;
            color: #000;
            padding-left: 30px;
            padding-right: 30px;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            margin-bottom: 20px;
        }

        table thead {
            background-color: #c5c5c5;
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
            font-size: 12px;
        }

        .border tbody tr td {
            border: 1px solid #000;
            padding: 4px;
        }

        .border tr td {
            border: 1px solid #000;
            padding: 4px;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td width="25%">
            <img src="${(logo)!" "}" width="300px"/>
        </td>
        <td width="75%">
            <table>
                <tr>
                    <td width="40%">
                    ${(creditNote.infoSystemAdmin.systemAddress)!" "} <br/>
                        <a href="${(creditNote.infoSystemAdmin.siteAddress)!" "}">${(creditNote.infoSystemAdmin.siteAddress)!" "}</a>
                    </td>
                    <td width="60%" valign="top" align="left"><span
                            style="font-size: 25px; font-weight: bold">${lang.translate('Credit Notes')}</span></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td></td>
        <td colspan="2">
            <table class="border" style="margin-top: 20px">
                <thead>
                <tr>
                    <td bgcolor="#c5c5c5" align="center"><strong>${lang.translate('Credit Number')}</strong></td>
                    <td bgcolor="#c5c5c5" align="center"><strong>${lang.translate('Credit Date')}</strong></td>
                    <td bgcolor="#c5c5c5" align="center"><strong>${lang.translate('Customer #')}</strong></td>
                    <td bgcolor="#c5c5c5" align="center"><strong>${lang.translate('Credits')}</strong></td>
                    <td bgcolor="#c5c5c5" align="center"><strong>${lang.translate('Total Credited')}</strong></td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td align="center"><strong>${(creditNote.creditNoteInfo.creditCode?upper_case)!" "}</strong></td>
                    <td align="center"><strong>${(creditNote.creditNoteInfo.createDate)!" "}</strong></td>
                    <td align="center"><strong>${(creditNote.creditNoteInfo.customerCode)!" "}</strong></td>
                    <td align="center"><strong>${(creditNote.creditNoteInfo.credits)!"0"}</strong></td>
                    <td align="center">
                        <strong>${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.totalCredited)!" "}</strong></td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td valign="top">
            <div style="float:right; padding: 8px; border: 1px solid #000; margin-right: 20px; width: 95%;">
                <strong>${lang.translate('CREDIT TO:')}</strong> <br/>
            ${(creditNote.creditNoteInfo.billingCustomerName?upper_case?html)!" "}
                <br/>${(creditNote.creditNoteInfo.billingContactName?html)!" "} <br/>
            ${(creditNote.creditNoteInfo.billingAddress1?upper_case?html)!" "} <br/>
            ${(creditNote.creditNoteInfo.billingCity?upper_case)!" "}
                , ${(creditNote.creditNoteInfo.countryName?upper_case)!" "}
                , ${(creditNote.creditNoteInfo.billingPostalCode?upper_case)!" "}
            </div>
        </td>
        <td valign="top">
            <div style="float: left; padding: 8px; border: 1px solid #000; margin-left: 20px; width: 95%;">
                <strong>${lang.translate('MAIL PAYMENT TO:')}</strong> <br/>
            ${(creditNote.infoSystemAdmin.mailPaymentToAddress)!" "}
            </div>
        </td>
    </tr>
</table>
<div style="width: 60%; float: right; margin-top: 20px;;">
    <table class="border">
        <thead>
        <tr bgcolor="#f9f9f9">
            <td align="center">${lang.translate('GST Summary')}</td>
            <td align="center">${lang.translate('GST Percent')}</td>
            <td align="center">${lang.translate('Credit Amount')}</td>
            <td align="center">${lang.translate('GST Amount')}</td>
            <td align="center">${lang.translate('Total Amount')}</td>
        </tr>
        </thead>
        <tbody>
        <#if gstSummary?has_content>
            <#list gstSummary as summary>
            <tr>
                <#if summary.customerTaxPercent != '0.00'>
                    <td bgcolor="#c5c5c5" align="center">${lang.translate('GST Shipments')}</td>
                <#else>
                    <td bgcolor="#c5c5c5" align="center">${lang.translate('Non-GST Shipments')}</td>
                </#if>
                <td align="center">${(summary.customerTaxPercent)!"0"}%</td>
                <td align="center">${(currencySymbol)!"$"} ${(summary.customerAmount)!"0.00"}</td>
                <td align="center">${(currencySymbol)!"$"} ${(summary.gstCustomerAmount)!"0.00"}</td>
                <td align="center">${(currencySymbol)!"$"} ${(summary.total)!"0.00"}</td>
            </tr>
            </#list>
        <#else>
        <tr>
            <td bgcolor="#c5c5c5" align="center">${lang.translate('GST Shipments')}</td>
            <td align="center">${(creditNote.creditNoteInfo.taxPercent)!"0"} %</td>
            <td align="center">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.shipmentAmount)!"0"}</td>
            <td align="center">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.gstAmount)!"0"}</td>
            <td align="center">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.totalAmount)!"0"}</td>
        </tr>
        <tr>
            <td bgcolor="#c5c5c5" align="center">${lang.translate('Non-GST Shipments')}</td>
            <td align="center">0.00%</td>
            <td align="center">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.nonShipmentAmount)!"0"}</td>
            <td align="center">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.nonGstAmount)!"0"}</td>
            <td align="center">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.nonShipmentAmount)!"0"}</td>
        </tr>
        </#if>

        </tbody>
    </table>
</div>
<table class="border" style="width: 90% !important; float: right">
    <thead>
    <tr bgcolor="#fff">
        <td><strong>${lang.translate('Invoice #')}</strong></td>
        <td><strong>${lang.translate('Airbill #')}</strong></td>
        <td><strong>${lang.translate('Invoice Date')}</strong></td>
        <td><strong>${lang.translate('Reason for Credit')}</strong></td>
        <td><strong>${lang.translate('Amount')}</strong></td>
        <td><strong>${lang.translate('GST Amount')}</strong></td>
        <td><strong>${lang.translate('Total Credit')}</strong></td>
    </tr>
    </thead>
    <tbody>
    <#if creditNote.listCreditNoteAdj?has_content>
        <#list creditNote.listCreditNoteAdj as adj>
        <tr>
            <td>${(adj.invoiceCode)!" "}</td>
            <td>${(adj.airbillNumber)!" "}</td>
            <td>${(adj.invoiceDate)!" "}</td>
            <td>${(adj.reason)!" "}</td>
            <td>${(currencySymbol)!"$"} ${(adj.shipmentAmount)!"0"}</td>
            <td>${(currencySymbol)!"$"} ${(adj.gstAmount)!"0"}</td>
            <td align="right">${(currencySymbol)!"$"} ${(adj.totalCredit)!"0"}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="6" align="right">${lang.translate('Grand Total:')}</td>
        <td align="right">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.totalCredited)!"0"}</td>
    </tr>
    </tfoot>
</table>
<#if invSignature?trim != ''>
<table style="width: 100%; float: right;">
    <tr>
        <td align="center">${(invSignature)?html?replace("\n", "<br/>")!" "}</td>
    </tr>
</table>
</#if>
</body>
</html>