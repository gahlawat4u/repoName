<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('View/Edit Invoice Print'))!" "}</title>
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
    </style>
</head>
<body>
<table style="margin-bottom: 0px;">
    <tr>
        <td width="30%"><img src="${(logo)!" "}" width="100%" style="margin-top: -30px;"/></td>
        <td width="70%">
            <table>
                <tr>
                    <td>${(companyAddress)!" "}<br/>
                        <a href="${(siteAddress)!" "}">${(siteAddress)!" "}</a>
                    </td>
                    <td align="left"><span
                            style="font-size: 30px; font-weight: bold">${(lang.translate('Tax Invoice'))!" "}</span>
                    </td>
                    <td align="right" width="25"><img src="${(realPath)!" "}images/printer67.png" width="25" class="img"
                                                      onclick="this.remove();window.print();"/></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <table class="border" style="margin-top: 20px">
                            <thead>
                            <tr>
                                <td>${(lang.translate('Invoice Number'))!" "}</td>
                                <td>${(lang.translate('Invoice Date'))!" "}</td>
                                <td>${(lang.translate('Customer #'))!" "}</td>
                                <td>${(lang.translate('Airbills'))!" "}</td>
                                <td>${(lang.translate('Due Date'))!" "}</td>
                                <td>${(lang.translate('Amount Due'))!" "}</td>
                                <td>${(lang.translate('If Not Paid by Due Date'))!" "}</td>
                            <#if showPayments>
                                <td><strong>${lang.translate('Paid/Credited')}</strong></td>
                                <td><strong>${lang.translate('Remaining Due')}</strong></td>
                            </#if>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${(invInfo.invoiceCode?upper_case)!" "}</td>
                                <td>${(invInfo.invoiceDate)!" "}</td>
                                <td>${(invInfo.customerCode)!" "}</td>
                                <td>${(invInfo.noOfAirbills)!"0"}</td>
                                <td>${(invInfo.dueDate)!" "}</td>
                                <td align="right">${(currencySymbol)!"$"}${(invInfo.totalAmount)!" "}</td>
                                <td align="right">${(currencySymbol)!"$"}${(invInfo.totalIfNotPaidByDueDate)!" "}</td>
                            <#if showPayments>
                                <td align="right">${(currencySymbol)!"$"}${(invInfo.totalPaid)!" "}</td>
                                <td align="right">${(currencySymbol)!"$"}${(invInfo.remainingDue)!" "}</td>
                            </#if>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td valign="top">
            <div style="border: 1px solid #ddd; padding: 8px; margin-right: 10px">
                <strong>${(lang.translate('BILL TO:'))!" "} </strong> <br/>
            ${(invInfo.billingCustomerName?upper_case?html)!" "}<br/>${(invInfo.billingContactName?html)!" "} <br/>
            ${(invInfo.billingAddress1?upper_case?html)!" "} <br/>
            ${(invInfo.billingAddress2?upper_case?html)!" "} <br/>
            ${(invInfo.billingPostalCode?upper_case)!" "}, ${(invInfo.billingCity?upper_case)!" "}
                , ${(invInfo.billingCountryName?upper_case)!" "}
            </div>
        </td>
        <td valign="top">
            <div style="border: 1px solid #ddd; padding: 8px; margin-left: 10px;">
                <strong>${(lang.translate('MAIL PAYMENT TO:'))!" "} </strong> <br/> ${(mailToAddress)!" "}
            </div>
        </td>
    </tr>
</table>
<div style="width: 60%; float: right">
    <table class="border">
        <thead>
        <tr bgcolor="#f9f9f9">
            <td>${(lang.translate('GST Summary'))!" "}</td>
            <td>${(lang.translate('GST Percent'))!" "}</td>
            <td>${(lang.translate('Shipment Amount'))!" "}</td>
            <td>${(lang.translate('GST Amount'))!" "}</td>
            <td>${(lang.translate('Total Amount'))!" "}</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td bgcolor="#f9f9f9"><strong>${(lang.translate('GST Shipments'))!" "}</strong></td>
            <td>${(invInfo.gstTaxPercent)!"0"} %</td>
            <td>${(currencySymbol)!"$"} ${(invInfo.gstTotalCost)!"0"}</td>
            <td>${(currencySymbol)!"$"} ${(invInfo.gstTotalTax)!"0"}</td>
            <td>${(currencySymbol)!"$"} ${(invInfo.gstTotalAmount)!"0"}</td>
        </tr>
        <tr>
            <td bgcolor="#f9f9f9"><strong>${(lang.translate('Non-GST Shipments'))!" "}</strong></td>
            <td>0.00%</td>
            <td>${(currencySymbol)!"$"} ${(invInfo.nonGstTotalCost)!"0"}</td>
            <td>${(currencySymbol)!"$"} ${(invInfo.nonGstTotalTax)!"0"}</td>
            <td>${(currencySymbol)!"$"} ${(invInfo.nonGstTotalAmount)!"0"}</td>
        </tr>
        </tbody>
    </table>
</div>
<table class="border" style="width: 90% !important; float: right">
    <thead>
    <tr bgcolor="#f9f9f9">
        <td>${(lang.translate('Carrier - Airbill #'))!" "} <br/> ${(lang.translate('Orig/Dest'))!" "}
            <br/> ${(lang.translate('Ship Date'))!" "}<br/> ${(lang.translate('Customer #'))!" "}
            <br/> ${(lang.translate('Reference'))!" "}<br/> ${(lang.translate('Reference 2'))!" "}
        </td>
        <td>${(lang.translate('Sender Address'))!" "}</td>
        <td>${(lang.translate('Receiver Address'))!" "}</td>
        <td>${(lang.translate('Weight'))!" "}<br/> ${(lang.translate('Dimensions'))!" "}
            <br/> ${(lang.translate('Zone'))!" "}
        </td>
        <td>${(lang.translate('Charges'))!" "}</td>
        <td>${(lang.translate('Total'))!" "}</td>
    </tr>
    </thead>
    <tbody>
    <#if listAirbills?has_content>
        <#list listAirbills as awb>
        <tr>
            <td>${(awb.serviceName)!" "} - ${(awb.airbillNumber)!" "}
                <br/> <#if awb.serviceAreaCodeOrigin?? || awb.serviceAreaCodeDestination??>${(awb.serviceAreaCodeOrigin)!" "}
                    /${(awb.serviceAreaCodeDestination)!" "}</#if><br/>${(awb.shipmentDate)!" "}
                <br/>${(awb.customerCode)!" "}<br/>${(awb.reference)!" "}<br/>${(awb.reference2)!" "}</td>
            <td>${(awb.senderCompanyName)!" "} <br/>${(awb.senderContactName)!" "}<br/>${(awb.senderAddress1)!" "}
                <br/>${(awb.senderCity)!" "} - ${(awb.senderStates)!" "} - ${(awb.senderPostalCode)!" "}
                <br/>${(awb.senderCountryName)!" "}</td>
            <td>${(awb.receiverCompanyName)!" "} <br/>${(awb.receiverContactName)!" "}<br/>${(awb.receiverAddress1)!" "}
                <br/>${(awb.receiverCity)!" "} - ${(awb.receiverStates)!" "} - ${(awb.receiverPostalCode)!" "}
                <br/>${(awb.receiverCountryName)!" "}</td>
            <td>${(awb.noOfPieces)!" "}<br/>${(awb.weight)!" "}<br/>${(awb.zone)!" "}</td>
            <td><#if awb.charges?has_content><#list awb.charges as charge>${(charge.awbDescription)!" "}
                - ${(currencySymbol)!"$"} ${(charge.awbCustomerCost)!"0"} <br/></#list></#if></td>
            <td>${(currencySymbol)!"$"} ${(awb.total)!"0"}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
    <tfoot>

    <tr>
        <td colspan="5" align="right"><strong>${(lang.translate('GRAND TOTAL:'))!" "}</strong></td>
        <td>${(currencySymbol)!"$"} ${(invInfo.totalCost)!"0"}</td>
    </tr>
    <tr>
        <td colspan="5" align="right"><strong>${(lang.translate('TAX AMOUNT:'))!" "}</strong></td>
        <td>${(currencySymbol)!"$"} ${(invInfo.totalTax)!"0"}</td>
    </tr>
    <tr>
        <td colspan="5" align="right"><strong>${(lang.translate('GRAND TOTAL:'))!" "}</strong></td>
        <td>${(currencySymbol)!"$"} ${(invInfo.totalAmount)!"0"}</td>
    </tr>

    </tfoot>
</table>
<#if invSignature?trim != ''>
<table width="100%" class="border" style="float: right">
    <tr>
        <td align="center"><p>
        ${(invSignature)?html?replace("\n", "<br/>")!" "}
        </p></td>
    </tr>
</table>
</#if>
</body>
</html>