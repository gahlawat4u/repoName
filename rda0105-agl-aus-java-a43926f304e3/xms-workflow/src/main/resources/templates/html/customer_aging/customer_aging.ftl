<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Customer Aging Print'))!" "}</title>
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
<table style="margin-bottom:0px;">
    <tr>
        <td width="70%">
            <table>
                <tr>
                    <td align="center" colspan="2"><span
                            style="font-size: 14px; font-weight: bold">${(lang.translate('Customer Aging'))!" "}</span>
                    </td>
                    <td align="right">
                        <button class="btn s37 s44 flr" onclick="this.remove();window.print();"><i
                                class="fa fa-print fa-fw"></i> ${(lang.translate('Prints'))!" "}</button>
                    </td>
                </tr>
                <tr>
                    <td width="50"></td>
                    <td>
                        <table class="border" style="margin-top: 20px">
                            <thead>
                            <tr>
                                <td>${(lang.translate('Total Due'))!" "}</td>
                                <td>${(lang.translate('Total Overdue'))!" "}</td>
                                <td>${(lang.translate('<=0 days'))!" "}</td>
                                <td>${(lang.translate('1 to 15'))!" "}</td>
                                <td>${(lang.translate('16 to 30'))!" "}</td>
                                <td>${(lang.translate('31 to 45'))!" "}</td>
                                <td>${(lang.translate('46 to 60'))!" "}</td>
                                <td>${(lang.translate('61 to 90'))!" "}</td>
                                <td>${(lang.translate('91 to 120'))!" "}</td>
                                <td>${(lang.translate('121+'))!" "}</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td align="right">${(currencySymbol)!"$"}${(agingTotal.totalDue)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(agingTotal.totalOverdue)!"0.00"}</td>
                                <td align="right">${(agingTotal.range0)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(agingTotal.range1To15)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(agingTotal.range16To30)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(agingTotal.range31To45)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(agingTotal.range46To60)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(agingTotal.range61To90)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(agingTotal.range91To120)!"0.00"}</td>
                                <td align="right">${(currencySymbol)!"$"}${(agingTotal.range120)!"0.00"}</td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                    <td width="50"></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table class="border">
    <thead>
    <tr>
        <th>${(lang.translate('Customer'))!" "}</th>
        <th>${(lang.translate('Customer #'))!" "}</th>
    <#if columnFlags.hasSalesRep>
        <th>${(lang.translate('Sales Rep'))!" "}</th>
    </#if>
        <th>${(lang.translate('Total Due'))!" "}</th>
        <th>${(lang.translate('Total Overdue'))!" "}</th>
    <#if columnFlags.hasAgingBuckets>
        <th>${(lang.translate('<=0 days'))!" "}</th>
        <th>${(lang.translate('1 to 15'))!" "}</th>
        <th>${(lang.translate('16 to 30'))!" "}</th>
        <th>${(lang.translate('31 to 45'))!" "}</th>
        <th>${(lang.translate('46 to 60'))!" "}</th>
        <th>${(lang.translate('61 to 90'))!" "}</th>
        <th>${(lang.translate('91 to 120'))!" "}</th>
        <th>${(lang.translate('121+'))!" "}</th>
    </#if>
    <#if columnFlags.hasInvoiceAge>
        <th>${(lang.translate('Max Age'))!" "}</th>
    </#if>
    <#if columnFlags.hasDaysOverdue>
        <th>${(lang.translate('Max Days Overdue'))!" "}</th>
    </#if>
    <#if columnFlags.hasInvoice>
        <th>${(lang.translate('Outstanding Inv#'))!" "}</th>
    </#if>
    <#if columnFlags.hasTotalOverpayments>
        <th>${(lang.translate('Total Overpaid'))!" "}</th>
    </#if>
    <#if columnFlags.hasTerms>
        <th>${(lang.translate('Terms'))!" "}</th>
    </#if>
    <#if columnFlags.hasAvgDaysToPay>
        <th>${(lang.translate('Avg Days To Pay'))!" "}</th>
    </#if>
    </tr>
    </thead>
    <tbody>
    <#list agingList as aging>
    <tr>
        <td>${(aging.customerName)!" "}</td>
        <td>${(aging.customerCode)!" "}</td>
        <#if columnFlags.hasSalesRep>
            <td>${(aging.salesRepName)!" "}</td>
        </#if>
        <td align="right">${(currencySymbol)!"$"}${(aging.totalDue)!"0.00"}</td>
        <td align="right">${(currencySymbol)!"$"}${(aging.totalOverdue)!"0.00"}</td>
        <#if columnFlags.hasAgingBuckets>
            <td align="right">${(currencySymbol)!"$"}${(aging.range0)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(aging.range1To15)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(aging.range16To30)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(aging.range31To45)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(aging.range46To60)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(aging.range61To90)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(aging.range91To120)!"0.00"}</td>
            <td align="right">${(currencySymbol)!"$"}${(aging.range120)!"0.00"}</td>
        </#if>
        <#if columnFlags.hasInvoiceAge>
            <td align="right">${(aging.maxInvoiceAge)!"0"}</td>
        </#if>
        <#if columnFlags.hasDaysOverdue>
            <td align="right">${(aging.maxDaysOverdue)!"0"}</td>
        </#if>
        <#if columnFlags.hasInvoice>
            <td>${(aging.unpaidInvoices)!" "}</td>
        </#if>
        <#if columnFlags.hasTotalOverpayments>
            <td align="right">${(currencySymbol)!"$"}${(aging.totalOverpaid)!"0.00"}</td>
        </#if>
        <#if columnFlags.hasTerms>
            <td>${(aging.terms)!"0"} days</td>
        </#if>
        <#if columnFlags.hasAvgDaysToPay>
            <td align="right">${(aging.avgDaysToPay)!"0"}</td>
        </#if>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>