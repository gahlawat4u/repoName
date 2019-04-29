<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        @page {
            size: A4;
            margin: 10px;
        }

        body {
            font-family: Arial;
            font-size: 10px;
            color: #000;
            padding-left: 30px;
            padding-right: 30px;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            margin-bottom: 20px;
            font-size: 10px;
        }

        table thead {
            background-color: #c5c5c5;
            font-size: 10px;
        }

        .no-border {
            border: none;
            font-size: 10px;
        }

        .border {
            border-collapse: collapse;
            font-size: 9px;
        }

        .border thead tr:first-child th {
            border: 1px solid #000;
            border-bottom: none;
            font-size: 9px;
        }

        .border thead tr th {
            border: 1px solid #000;
            font-size: 9px;
        }

        .border tbody tr td {
            border: 1px solid #000;
            padding: 4px;
            font-size: 9px;
        }

        .border tr td {
            border: 1px solid #000;
            padding: 4px;
            font-size: 9px;
        }

    </style>
</head>
<body>
<table width="100%">
    <tr>
        <td width="15%">
            <img src="${(logo)!" "}" width="150px"/>
        </td>
        <td width="85%">
            <table>
                <tr>
                    <td width="40%">
                    ${(systemAddress)!" "} <br/>
                        <a href="${(siteAddress)!" "}">${(siteAddress)!" "}</a>
                    </td>
                    <td width="60%" valign="middle" align="center"><span style="font-size: 20px; font-weight: bold">Tax Invoice</span>
                    </td>
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
                    <td bgcolor="#fff" align="center"><strong>Invoice Number</strong></td>
                    <td bgcolor="#fff" align="center"><strong>Invoice Date</strong></td>
                    <td bgcolor="#fff" align="center"><strong>Customer #</strong></td>
                    <td bgcolor="#fff" align="center"><strong>Airbills</strong></td>
                    <td bgcolor="#fff" align="center"><strong>Due Date</strong></td>
                    <td bgcolor="#fff" align="center"><strong>Amount Due</strong></td>
                    <td bgcolor="#fff" align="center"><strong>If Not Paid by Due Date</strong></td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td align="center">${(taxInvoice.invoiceCode)!" "}</td>
                    <td align="center">${(taxInvoice.invoiceDate)!" "}</td>
                    <td align="center">${(taxInvoice.customerCode)!" "}</td>
                    <td align="center">${(taxInvoice.airbillCount)!" "}</td>
                    <td align="center">${(taxInvoice.dueDate)!" "}</td>
                    <td align="center">${(currencySymbol)!"$"}${(taxInvoice.totalAmount)!"0.00"}</td>
                    <td align="center">${(currencySymbol)!"$"}${(taxInvoice.ifNotPaidByDue)!"0.00"}</td>
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
                BILL TO : <br/>
            ${(billTo.billingCustomerName?upper_case)!" "}<br/>${(billTo.billingContactName)!" "} <br/>
            ${(billTo.billingAddress1?upper_case)!" "} <br/>
            ${(billTo.billingCity?upper_case)!" "}, ${(billTo.countryName?upper_case)!" "}
                , ${(billTo.billingPostalCode?upper_case)!" "}
            </div>
        </td>
        <td valign="top">
            <div style="float: left; padding: 8px; border: 1px solid #000; margin-left: 20px; width: 95%;">
                MAIL PAYMENT TO: <br/>
            ${(mailPaymentTo)!" "}
            </div>
        </td>
    </tr>
</table>
<div style="width: 60%; float: right; margin-top: 20px;;">
    <table class="border">
        <thead>
        <tr bgcolor="#c5c5c5">
            <td align="center">GST Summary</td>
            <td align="center">GST Percent</td>
            <td align="center">Shipment Amount</td>
            <td align="center">GST Amount</td>
            <td align="center">Total Amount</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td bgcolor="#c5c5c5" align="center">GST Shipments</td>
            <td align="center"> ${(gstSummary.customerTaxPercent)!"0"} %</td>
            <td align="center">${(currencySymbol)!"$"}${(gstSummary.gstCustomerCost)!"0.00"}</td>
            <td align="center">${(currencySymbol)!"$"}${(gstSummary.customerTaxAmount)!"0.00"}</td>
            <td align="center">${(currencySymbol)!"$"}${(gstSummary.totalGstAmount)!"0.00"}</td>
        </tr>
        <tr>
            <td bgcolor="#c5c5c5" align="center">Non-GST Shipments</td>
            <td align="center">0.00%</td>
            <td align="center">${(currencySymbol)!"$"}${(gstSummary.nonGstCustomerCost)!"0"}</td>
            <td align="center">${(currencySymbol)!"$"}0.00</td>
            <td align="center">${(currencySymbol)!"$"}${(gstSummary.nonGstCustomerCost)!"0"}</td>
        </tr>
        </tbody>
    </table>
</div>
<table class="border" style="width: 100% !important; float: right">
    <thead>
    <tr>
        <th>Carrier - Airbill # <br/>
            Orig/Dest <br/>
            Ship Date <br/>
            Customer # <br/>
            Reference <br/>
            Reference 2
        </th>
        <th>Sender Address</th>
        <th>Receiver Address</th>
        <th>Pieces <br/>
            Weight <br/>
            Dimensions <br/>
            Zone
        </th>
        <th>Charges</th>
        <th>Total</th>
    </tr>
    </thead>
    <tbody>
    <#if airbillList?has_content>
        <#list airbillList as airbill>
        <tr>
            <td>${(airbill.serviceName)!" "} - ${(airbill.airbillNumber)!" "} <br/>
            ${(airbill.serviceAreaCodeOrigin)!" "}/${(airbill.serviceAreaCodeDestination)!" "} <br/>
            ${(airbill.shipmentDate)!" "} <br/>
            ${(airbill.customerCode)!" "} <br/>
            ${(airbill.shipperReference)!" "} <br/>
            ${(airbill.billingReference)!" "} <br/>
            </td>
            <td>
            ${(airbill.senderCompanyName)!" "} <br/>
            ${(airbill.senderContactName)!" "} <br/>
            ${(airbill.senderAddress)!" "} ${(airbill.senderAddress2)!" "} <br/>
            ${(airbill.senderCity)!" "} ${(airbill.senderPostalCode)!" "} ${(airbill.senderState)!" "} ${(airbill.senderCountry)!" "}
            </td>
            <td>
            ${(airbill.receiverCompanyName)!" "} <br/>
            ${(airbill.receiverContactName)!" "} <br/>
            ${(airbill.receiverAddress)!" "} ${(airbill.receiverAddress2)!" "} <br/>
            ${(airbill.receiverCity)!" "} ${(airbill.receiverPostalCode)!" "} ${(airbill.receiverState)!" "} ${(airbill.receiverCountry)!" "}
            </td>
            <td>
            ${(airbill.noOfPieces)!" "} <br/>
            ${(airbill.weight)!" "} <br/>
            ${(airbill.billActualDimension)!" "} <br/>
            ${(airbill.zone)!" "} <br/>
            </td>
            <td>
                <#if airbillList?has_content>
                    <#list airbill.charges as charge>
                    ${(charge.description)!"0.00"} - ${(currencySymbol)!"$"}${(charge.customerCost)!"0.00"} <br/>
                    </#list>
                </#if>
            </td>
            <td>${(currencySymbol)!"$"}${(airbill.total)!"0.00"}</td>
        </tr>
        </#list>
    </#if>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>Total <br/> Tax Amount <br/> Grand Total</td>
        <td>${(currencySymbol)!"$"}${(total)!"0.00"} <br/>
        ${(currencySymbol)!"$"}${(gstSummary.customerTaxAmount)!"0"} <br/>
        ${(currencySymbol)!"$"}${(taxInvoice.totalAmount)!"0.00"}</td>
    </tr>
    </tbody>
</table>
<div style="text-align:center; float: right;">
    <p><strong>
    ${(invoiceSign)!" "}
    </strong></p>
</div>
</body>
</html>