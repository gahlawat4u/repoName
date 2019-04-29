<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Statement of Accounts Print'))!" "}</title>
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
                            style="font-size: 30px; font-weight: bold">${(lang.translate('Statement of Accounts'))!" "}</span>
                    </td>
                    <td align="right" width="25"><img src="${(realPath)!" "}images/printer67.png" width="25" class="img"
                                                      onclick="this.remove();window.print();"/></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <table class="border" style="margin-top: 20px">
                            <thead>
                            <tr>
                                <td align="center"><strong>${lang.translate('Date')}</strong></td>
                                <td align="center"><strong>${lang.translate('Customer #')}</strong></td>
                                <td align="center"><strong>${lang.translate('Total due')}</strong></td>
                                <td align="center"><strong>${lang.translate('<= 0 days')?html}</strong></td>
                                <td align="center"><strong>${lang.translate('1 to 15')}</strong></td>
                                <td align="center"><strong>${lang.translate('16 to 30')}</strong></td>
                                <td align="center"><strong>${lang.translate('31 to 45')}</strong></td>
                                <td align="center"><strong>${lang.translate('46 to 60')}</strong></td>
                                <td align="center"><strong>${lang.translate('61 to 90')}</strong></td>
                                <td align="center"><strong>${lang.translate('91 to 120')}</strong></td>
                                <td align="center"><strong>${lang.translate('Over 120')}</strong></td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${(currentDate)!" "}</td>
                                <td>${(customerCode)!" "}</td>
                                <td align="right">${(currencySymbol)!"$"}${(aging.totalDue)!" "}</td>
                                <td align="right">${(currencySymbol)!"$"}${(aging.range0)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(aging.range1To15)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(aging.range16To30)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(aging.range31To45)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(aging.range46To60)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(aging.range61To90)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(aging.range91To120)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(aging.range120)!"0.00"}</td>
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
            ${(billingAddress.customerName?upper_case?html)!" "}<br/>
            ${(billingAddress.address1?upper_case?html)!" "} <br/>
            <#if billingAddress.address2 != ''>
            ${(billingAddress.address2?upper_case?html)!" "} <br/>
            </#if>
            ${(billingAddress.postalCode?upper_case)!" "}, ${(billingAddress.city?upper_case)!" "}
                , ${(billingAddress.countryName?upper_case)!" "}
            </div>
        </td>
        <td valign="top">
            <div style="border: 1px solid #ddd; padding: 8px; margin-left: 10px;">
                <strong>${(lang.translate('MAIL PAYMENT TO:'))!" "} </strong> <br/>
            ${(mailPaymentTo)!" "}
            </div>
        </td>
    </tr>
</table>
<table class="border" style="width: 100% !important;">
    <thead>
    <tr bgcolor="#f9f9f9">
        <td><strong>${lang.translate('Invoice #')} </strong></td>
        <td><strong>${lang.translate('Invoice Date')}</strong></td>
        <td><strong>${lang.translate('Due Date')}</strong></td>
        <td><strong>${lang.translate('Invoice Amount')}</strong></td>
        <td><strong>${lang.translate('Late Fee')}</strong></td>
        <td><strong>${lang.translate('Invoice Total')}</strong></td>
        <td><strong>${lang.translate('Total Paid')}</strong></td>
        <td><strong>${lang.translate('Remaining Due')}</strong></td>
    </tr>
    </thead>
    <tbody>
    <#if invoices?has_content>
        <#list invoices as inv>
        <tr>
            <td>${(inv.invoiceCode?html)!" "}</td>
            <td>${(inv.invoiceDate?html)!" "}</td>
            <td>${(inv.dueDate?html)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.invoiceAmount)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.lateFee)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.invoiceTotal)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.totalPaid)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.remainingDue)!"0.00"}</td>
        </tr>
        </#list>
    </#if>
    </tbody>
    <tfoot>

    <tr>
        <td colspan="7" align="right"><strong>${(lang.translate('Total Due:'))!" "}</strong></td>
        <td align="right">${(currencySymbol)!"$"}${(invoiceTotal.remainingDue)!"0.00"}</td>
    </tr>
    </tfoot>
</table>
</body>
</html>