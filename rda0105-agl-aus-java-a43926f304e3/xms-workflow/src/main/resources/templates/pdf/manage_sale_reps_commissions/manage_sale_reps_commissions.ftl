<html>
<head>
    <style type="text/css">
        @page {
            size: A4 landscape;
            margin: 5mm 5mm;
        }

        body {
            font-family: Arial;
            font-size: 12px;
        }

        table {
            width: 100%;
            font-size: 12px;
            page-break-inside: auto;
        }

        table tr {
            page-break-inside: avoid;
            page-break-after: auto
        }

        table th {
            font-size 12px;
            text-align: center;
        }

        .text-bold {
            font-weight: bold;
        }

        .title {
            margin: 0 auto;
            text-align: center;
            font-size: 19px;
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
            background-color: #c5c5c5;
            font-weight: normal;
        }

        .border tbody tr td {
            border: 1px solid #000;
        }

        .text-right {
            text-align: right;
        }

        .text-center {
            text-align: center;
        }

        .text-left {
            text-align: left;
        }

        .table-center {
            margin: 0 auto;
            text-align: center;
            width: auto;
        }
    </style>
</head>
<body>
<div>
    <div class="title">
        <h3>${lang.translate('Sales Rep Commissions Report')!" "}</h3>
    </div>
    <table class="no-border">
        <thead>
        <th width="50%" align="center"><span style="font-size: 20px;">${lang.translate('Sales Rep Info')!" "}</span>
        </th>
        <th width="50%" align="center"><span style="font-size: 20px;">${lang.translate('Activity')!" "}</span></th>
        </thead>
        <tbody>
        <tr>
            <td valign="top">
                <table>
                    <tr>
                        <td>${lang.translate('Sales Rep')!" "}</td>
                        <td>${(overview.displayName?html)!" "}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('Franchise')!" "}</td>
                        <td>${(overview.userCode?html)!" "}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('Date Range')!" "}</td>
                        <td>${(startDate)!" "} - ${(endDate)!" "}</td>
                    </tr>
                </table>
            </td>
            <td valign="top">
                <table>
                    <tr>
                        <td width="40%">${lang.translate('Setups')!" "}</td>
                        <td width="60%" align="left">${(overview.setups)!"0"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('Activations')!" "}</td>
                        <td align="left" width="60%">${(overview.activations)!"0"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('Printed Invoices')!" "}</td>
                        <td align="left" width="60%">${(overview.printedInvoices)!"0"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('E-mail Invoices')!" "}</td>
                        <td align="left" width="60%">${(overview.emailInvoices)!"0"}</td>
                    </tr>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="page-break">
    <div class="title">
        <h3>${lang.translate('Service Level Summary')!" "}</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th>${lang.translate('Service Level')!" "}</th>
            <th>${lang.translate('Shipment Goals')!" "}</th>
            <th>${lang.translate('Actual Shipments')!" "}</th>
            <th>${lang.translate('% Goal')!" "}</th>
            <th>${lang.translate('Actual Margin')!" "}</th>
            <th>${lang.translate('% Payout')!" "}</th>
            <th>${lang.translate('Payout')!" "}</th>
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
</div>
<div class="page-break">
    <div class="title">
        <h3>${lang.translate('Invoice Detail')!" "}</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th>${lang.translate('Airbill Number')!" "}</th>
            <th>${lang.translate('Invoice Number')!" "}</th>
            <th>${lang.translate('Customer Name')!" "}</th>
            <th>${lang.translate('Customer Total')!" "}</th>
            <th>${lang.translate('Franchise Cost')!" "}</th>
            <th>${lang.translate('Previously Paid')!" "}</th>
            <th>${lang.translate('Margin On Customer Total')!" "}</th>
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
</div>
<div>
    <div class="title">
        <h3>${lang.translate('Sales Manager Payout')!" "}</h3>
    </div>
    <table class="border table-center" style="width: 40%">
        <thead>
        <tr>
            <th colspan="2">${lang.translate('Sales Manager Payout')!" "}</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td width="50%">${lang.translate('Sales Manager')!" "}</td>
            <td width="50%">${(saleReps.salesManager?html)!" "}</td>
        </tr>
        <tr>
            <td>${lang.translate('Franchise#')!" "}</td>
            <td>${(overview.userCode?html)!" "}</td>
        </tr>
        <tr>
            <td>${lang.translate('Sales Rep')!" "}</td>
            <td>${(overview.displayName?html)!" "}</td>
        </tr>
        <tr>
            <td>${lang.translate('Date Range')!" "}</td>
            <td>${(startDate)!" "} - ${(endDate)!" "}</td>
        </tr>
        <tr>
            <td>${lang.translate('Paid Margin')!" "}</td>
            <td>${(currencySymbol)!"$"}${(totalMargin)!"0.00"}</td>
        </tr>
        <tr>
            <td>${lang.translate('Percent Payout')!" "}</td>
            <td>${(saleReps.percentPayout)!"0.00"}%</td>
        </tr>
        <tr>
            <td>${lang.translate('Total Payout')!" "}</td>
            <td>${(currencySymbol)!"$"}${(totalPayout)!"0.00"}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>