<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Sales Rep Commissions Print'))!" "}</title>
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
            <table style="margin-bottom: 0px;">
                <tr>
                    <td align="center"><span
                            style="font-size: 14px; font-weight: bold">${(lang.translate('Sales Rep Commissions Report'))!" "}</span>
                    </td>
                    <td align="right" width="25"><img src="${(realPath)!" "}images/printer67.png"
                                                      onclick="this.remove();window.print();" width="25" class="img"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
        <td valign="top" align="right" width="50%">
            <table cellpadding="0" cellspacing="5" border="0" width="75%" class="border">
                <tr bgcolor="#f9f9f9">
                    <td colspan="2"><span
                            style="font-size: 13px; font-weight: bold">${(lang.translate('Sales Rep Info'))!" "}</span>
                    </td>
                </tr>
                <tr>
                    <td>${(lang.translate('Sales Rep'))!" "}</td>
                    <td>${(overview.displayName?html)!" "}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Franchise'))!" "}</td>
                    <td>${(overview.userCode?html)!" "}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Date Range'))!" "}</td>
                    <td>${(startDate)!" "} - ${(endDate)!" "}</td>
                </tr>
            </table>
        </td>
        <td valign="top" width="50%">
            <table cellpadding="0" cellspacing="5" border="0" width="75%" class="border">
                <tr bgcolor="#f9f9f9">
                    <td colspan="2"><span
                            style="font-size: 13px; font-weight: bold">${(lang.translate('Activity'))!" "}</span></td>
                </tr>
                <tr>
                    <td width="30%">${(lang.translate('Setups'))!" "}</td>
                    <td>${(overview.setups)!"0"}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Activations'))!" "}</td>
                    <td>${(overview.activations)!"0"}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Printed Invoices'))!" "}</td>
                    <td>${(overview.printedInvoices)!"0"}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('E-mail Invoices'))!" "}</td>
                    <td>${(overview.emailInvoices)!"0"}</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center"><br/> <br/> <span
                style="font-size: 14px; font-weight: bold">${(lang.translate('Service Level Summary'))!" "}</span></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <table cellpadding="2" cellspacing="0" border="1" class="border">
                <thead>
                <tr>
                    <th>${(lang.translate('Service Level'))!" "}</th>
                    <th>${(lang.translate('Shipment Goals'))!" "}</th>
                    <th>${(lang.translate('Actual Shipments'))!" "}</th>
                    <th>${(lang.translate('% Goal'))!" "}</th>
                    <th>${(lang.translate('Actual Margin'))!" "}</th>
                    <th>${(lang.translate('% Payout'))!" "}</th>
                    <th>${(lang.translate('Payout'))!" "}</th>
                </tr>
                </thead>
                <tbody>
                <#if service?has_content>
                    <#list service as s>
                    <tr>
                        <td>${(s.serviceName?html)!" "}</td>
                        <td align="right">${(s.goal)!"0"}</td>
                        <td align="right">${(s.actualShipments)!" "}</td>
                        <td align="right">${(s.goalPct)!" "}%</td>
                        <td align="right">${(currencySymbol)!"$"}${(s.actualMargin)!" "}</td>
                        <td align="right">${(s.payout)!" "}%</td>
                        <td align="right">${(currencySymbol)!"$"}${(s.payoutAmount)!" "}</td>
                    </tr>
                    </#list>
                <#else>
                <tr>
                    <td colspan="7" align="center">${lang.translate('No data available...')!" "}</td>
                </tr>
                </#if>
                </tbody>
            </table>
        </td>
    </tr>
</table>
<table style="margin-bottom: 10px;">
    <tr>
        <td align="center" bgcolor="#FFFFFF"><span
                style="font-size: 14px; font-weight: bold">${(lang.translate('Invoice Detail'))!" "}</span></td>
    </tr>
</table>
<table cellpadding="2" cellspacing="0" border="1" class="border">
    <thead>
    <tr>
        <th>${(lang.translate('Airbill Number'))!" "}</th>
        <th>${(lang.translate('Invoice Number'))!" "}</th>
        <th>${(lang.translate('Customer Name'))!" "}</th>
        <th>${(lang.translate('Customer Total'))!" "}</th>
        <th>${(lang.translate('Franchise Cost'))!" "}</th>
        <th>${(lang.translate('Previously Paid'))!" "}</th>
        <th>${(lang.translate('Margin On Customer Total'))!" "}</th>
    </tr>
    </thead>
    <tbody>
    <#if invoice?has_content>
        <#list invoice as inv>
        <tr>
            <td>${(inv.airbillNumber?html)!" "}</td>
            <td>${(inv.invoiceCode?html)!" "}</td>
            <td>${(inv.customerName?html)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.customerCost)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.franchiseCost)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.totalPaid)!" "}</td>
            <td align="right">${(currencySymbol)!"$"}${(inv.margin)!" "}</td>
        </tr>
        </#list>
    <#else>
    <tr>
        <td colspan="7" align="center">${lang.translate('No data available ...')!" "}</td>
    </tr>
    </#if>
    </tbody>
</table>
<table cellpadding="5" cellspacing="0" border="0" width="100%">
    <tr>
        <td colspan="2" align="center"><br/> <br/></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <table cellpadding="2" cellspacing="0" border="1" align="center" class="border" style="width: 75%;">
                <thead>
                <tr>
                    <th colspan="2"><span
                            style="font-size: 13px; font-weight: bold">${(lang.translate('Sales Manager Payout'))!" "}</span>
                    </th>
                </tr>
                </thead>
                <tr>
                    <td>${(lang.translate('Sales Manager'))!" "}</td>
                    <td>${(saleReps.salesManager?html)!" "}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Franchise#'))!" "}</td>
                    <td>${(overview.userCode?html)!" "}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Sales Rep'))!" "}</td>
                    <td>${(overview.displayName?html)!" "}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Date Range'))!" "}</td>
                    <td>${(startDate)!" "} - ${(endDate)!" "}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Paid Margin'))!" "}</td>
                    <td>${(currencySymbol)!"$"}${(totalMargin)!"0.00"}</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Percent Payout'))!" "}</td>
                    <td>${(saleReps.percentPayout)!"0.00"}%</td>
                </tr>
                <tr>
                    <td>${(lang.translate('Total Payout'))!" "}</td>
                    <td>${(currencySymbol)!"$"}${(totalPayout)!"0.00"}</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>