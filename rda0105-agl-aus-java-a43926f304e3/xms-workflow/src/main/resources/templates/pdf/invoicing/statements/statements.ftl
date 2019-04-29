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
            margin-bottom: 20px;
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
                            style="font-size: 15px; font-weight: bold">${lang.translate('Statement of Accounts')}</span>
                    </td>
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
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Date')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Customer #')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Total due')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('<= 0 days')?html}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('1 to 15')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('16 to 30')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('31 to 45')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('46 to 60')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('61 to 90')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('91 to 120')}</strong></td>
                    <td bgcolor="#fff" align="center"><strong>${lang.translate('Over 120')}</strong></td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td align="center">${(currentDate)!" "}</td>
                    <td align="center">${(customerCode)!" "}</td>
                    <td align="center">${(currencySymbol)!"$"}${(aging.totalDue)!" "}</td>
                    <td align="center">${(currencySymbol)!"$"}${(aging.range0)!"0.00"}</td>
                    <td align="center">${(currencySymbol)!"$"}${(aging.range1To15)!"0.00"}</td>
                    <td align="center">${(currencySymbol)!"$"}${(aging.range16To30)!"0.00"}</td>
                    <td align="center">${(currencySymbol)!"$"}${(aging.range31To45)!"0.00"}</td>
                    <td align="center">${(currencySymbol)!"$"}${(aging.range46To60)!"0.00"}</td>
                    <td align="center">${(currencySymbol)!"$"}${(aging.range61To90)!"0.00"}</td>
                    <td align="center">${(currencySymbol)!"$"}${(aging.range91To120)!"0.00"}</td>
                    <td align="center">${(currencySymbol)!"$"}${(aging.range120)!"0.00"}</td>
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
            <div style="float: right; padding: 5px; border: 1px solid #000; width: 95%;">
            ${lang.translate('MAIL PAYMENT TO:')} <br/>
            ${(mailPaymentTo)!" "}
            </div>
        </td>
    </tr>
</table>
<table class="border" style="width: 100% !important;">
    <thead>
    <tr bgcolor="#fff">
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
        <td colspan="7" align="right">${lang.translate('Total Due:')}</td>
        <td align="right">${(currencySymbol)!"$"}${(invoiceTotal.remainingDue)!"0.00"}</td>
    </tr>
    </tfoot>
</table>
</body>
</html>