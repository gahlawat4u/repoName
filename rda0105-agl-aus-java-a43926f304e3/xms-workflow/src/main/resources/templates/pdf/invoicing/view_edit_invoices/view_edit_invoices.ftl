<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        @page {
            size: A4;
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
        <td width="25%">
            <img src="${(logo)!" "}" width="200px"/>
        </td>
        <td width="75%">
            <table>
                <tr>
                    <td width="40%">
                    ${(companyAddress?upper_case?replace("<BR/>", "<br/>"))!" "}<br/>
                        <a href="${(siteAddress)!" "}">${(siteAddress)!" "}</a>
                    </td>
                    <td width="60%" valign="center" align="center"><span
                            style="font-size: 15px; font-weight: bold">${lang.translate('Tax Invoice')}</span></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td></td>
        <td colspan="2">
            <table class="border" style="margin-top: 0px">
                <thead>
                <tr>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Invoice Number')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Invoice Date')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Customer #')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Airbills')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Due Date')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Amount Due')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('If Not Paid by Due Date')}</strong></td>
                <#if showPayments>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Paid/Credited')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Remaining Due')}</strong></td>
                </#if>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td align="center">${(invInfo.invoiceCode?upper_case)!" "}</td>
                    <td align="center">${(invInfo.invoiceDate)!" "}</td>
                    <td align="center">${(invInfo.customerCode)!" "}</td>
                    <td align="center">${(invInfo.noOfAirbills)!"0"}</td>
                    <td align="center">${(invInfo.dueDate)!" "}</td>
                    <td align="center">${(currencySymbol)!"$"} ${(invInfo.totalAmount)!" "}</td>
                    <td align="center">${(currencySymbol)!"$"} ${(invInfo.totalIfNotPaidByDueDate)!" "}</td>
                <#if showPayments>
                    <td align="center">${(currencySymbol)!"$"} ${(invInfo.totalPaid)!" "}</td>
                    <td align="center">${(currencySymbol)!"$"} ${(invInfo.remainingDue)!" "}</td>
                </#if>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td valign="top">
            <div style="float:right; padding: 5px; border: 1px solid #000; width: 95%;">
            ${lang.translate('BILL TO:')} <br/>
            ${(invInfo.billingCustomerName?upper_case?html)!" "}<br/>
            ${(invInfo.billingAddress1?upper_case?html)!" "} <br/>
            <#if invInfo.billingAddress2 != ''>
            ${(invInfo.billingAddress2?upper_case?html)!" "} <br/>
            </#if>
            ${(invInfo.billingPostalCode?upper_case)!" "}, ${(invInfo.billingCity?upper_case)!" "}
                , ${(invInfo.billingCountryName?upper_case)!" "}
            </div>
        </td>
        <td valign="top">
            <div style="float: right; padding: 5px; border: 1px solid #000; width: 95%;">
            ${lang.translate('MAIL PAYMENT TO:')} <br/>
            ${(mailToAddress)!" "}
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
            <td align="center">${lang.translate('Shipment Amount')}</td>
            <td align="center">${lang.translate('GST Amount')}</td>
            <td align="center">${lang.translate('Total Amount')}</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td bgcolor="#c5c5c5" align="center">${lang.translate('GST Shipments')}</td>
            <td align="center">${(invInfo.gstTaxPercent)!"0"} %</td>
            <td align="center">${(currencySymbol)!"$"} ${(invInfo.gstTotalCost)!"0"}</td>
            <td align="center">${(currencySymbol)!"$"} ${(invInfo.gstTotalTax)!"0"}</td>
            <td align="center">${(currencySymbol)!"$"} ${(invInfo.gstTotalAmount)!"0"}</td>
        </tr>
        <tr>
            <td bgcolor="#c5c5c5" align="center">${lang.translate('Non-GST Shipments')}</td>
            <td align="center">0.00%</td>
            <td align="center">${(currencySymbol)!"$"} ${(invInfo.nonGstTotalCost)!"0"}</td>
            <td align="center">${(currencySymbol)!"$"} ${(invInfo.nonGstTotalTax)!"0"}</td>
            <td align="center">${(currencySymbol)!"$"} ${(invInfo.nonGstTotalAmount)!"0"}</td>
        </tr>
        </tbody>
    </table>
</div>
<table class="border" style="width: 100% !important; float: right">
    <thead>
    <tr bgcolor="#fff">
        <td><strong>${lang.translate('Carrier - Airbill #')} <br/> ${lang.translate('Orig/Dest')}
            <br/> ${lang.translate('Ship Date')} <br/> ${lang.translate('Customer #')}
            <br/>${lang.translate('Reference')} <br/>${lang.translate('Reference 2')}</strong></td>
        <td><strong>${lang.translate('Sender Address')}</strong></td>
        <td><strong>${lang.translate('Receiver Address')}</strong></td>
        <td><strong>${lang.translate('Pieces')} <br/>${lang.translate('Weight')} <br/>${lang.translate('Dimensions')}
            <br/>${lang.translate('Zone')} </strong></td>
        <td><strong>${lang.translate('Charges')}</strong></td>
        <td><strong>${lang.translate('Total')}</strong></td>
    </tr>
    </thead>
    <tbody>
    <#if listAirbills?has_content>
        <#list listAirbills as awb>
        <tr>
            <td>${(awb.serviceName?html)!" "} - ${(awb.airbillNumber)!" "}
                <br/> <#if awb.serviceAreaCodeOrigin?? || awb.serviceAreaCodeDestination??>${(awb.serviceAreaCodeOrigin)!" "}
                    /${(awb.serviceAreaCodeDestination?html)!" "}</#if><br/>${(awb.shipmentDate)!" "}
                <br/>${(awb.customerCode)!" "}<br/>${(awb.reference?html)!" "}<br/>${(awb.reference2?html)!" "}</td>
            <td>${(awb.senderCompanyName?html)!" "} <br/>${(awb.senderContactName?html)!" "}
                <br/>${(awb.senderAddress1?html)!" "}<br/>${(awb.senderCity?html)!" "} - ${(awb.senderStates?html)!" "}
                - ${(awb.senderPostalCode?html)!" "}<br/>${(awb.senderCountryName?html)!" "}</td>
            <td>${(awb.receiverCompanyName?html)!" "} <br/>${(awb.receiverContactName?html)!" "}
                <br/>${(awb.receiverAddress1?html)!" "}<br/>${(awb.receiverCity?html)!" "}
                - ${(awb.receiverStates?html)!" "} - ${(awb.receiverPostalCode?html)!" "}
                <br/>${(awb.receiverCountryName?html)!" "}</td>
            <td>${(awb.noOfPieces)!" "}<br/>${(awb.weight)!" "}<br/>${(awb.zone?html)!" "}</td>
            <td><#if awb.charges?has_content><#list awb.charges as charge>${(charge.awbDescription?html)!" "}
                - ${(currencySymbol)!"$"} ${(charge.awbCustomerCost)!"0"} <br/></#list></#if></td>
            <td>${(currencySymbol)!"$"} ${(awb.total)!"0"}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="4" align="right"></td>
        <td>${lang.translate('Total:')}<br/>${lang.translate('Tax Amount:')}<br/>${lang.translate('Grand Total:')}</td>
        <td align="right">${(currencySymbol)!"$"} ${(invInfo.totalCost)!"0"}
            <br/>${(currencySymbol)!"$"} ${(invInfo.totalTax)!"0"}
            <br/>${(currencySymbol)!"$"}${(invInfo.totalAmount)!"0"}</td>
    </tr>
    </tfoot>
</table>
<#if invSignature?trim != ''>
<table style="width: 100%; float: right;">
    <tr>
        <td align="center"><b>${(invSignature)?html?upper_case?replace("\n", "<br/>")!" "}</b></td>
    </tr>
</table>
</#if>
</body>
</html>