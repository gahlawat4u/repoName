<!DOCTYPE html>
<html>
<head>
    <title>Recipient Created Tax Invoice</title>
    <style type="text/css">
        @page {
            size: A4;
        }

        body {
            font-family: Arial;
            font-size: 11px;
            color: #000;
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

        .border tbody tr th {
            border: 1px solid #000;
            padding: 4px;
            text-align: left;
        }

        .border tr th {
            border: 1px solid #000;
            padding: 4px;
            text-align: left;
        }

        .border tr td {
            border: 1px solid #000;
            padding: 4px;
        }

        .b1 {
            background: #F5F5F5
        }

        .b2 {
            font-weight: 100;
            margin-left: 10px;
            background: #F60;;
            border-radius: 3px;
            color: #fff;
            font-size: 11px;
            padding: 3px 6px;
        }

        .s30 {
            color: #F00
        }
    </style>
</head>
<body>
<table style="margin-bottom: 0px;">
    <tr>
        <td width="30%"><img src="${(logo)!" "}" width="200px"/></td>
        <td width="70%">
            <table>
                <tr>
                    <td>${(franchise.customerName?html?upper_case)!" "}<br/> ${(franchise.address1?upper_case)!" "}
                        <br/> ${(franchise.city?upper_case)!" "}, ${(franchise.postalCode?upper_case)!" "}
                        , ${(franchise.country?upper_case)!" "} <br/> ${(franchise.registrationid)!"0"}
                    </td>
                    <td align="left"><span
                            style="font-size: 20px; font-weight: bold">Recipient Created Tax Invoice</span></td>
                </tr>
                <tr>
                    <td colspan="2"></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td valign="top">
            <div style="border: 1px solid #000; padding: 8px; margin-right: 10px">
                <strong>BILL TO : </strong> <br/> ${(systemAddress)!" "} <br/> ${(siteAddress)!" "}
            </div>
        </td>
        <td valign="top">
            <div style="border: 1px solid #000; padding: 8px; margin-left: 10px;">
                <strong>Date: </strong> ${(date)!" "}<br/>
                <strong>Invoice Number: </strong> ${(invoiceCode)!" "}<br/>
                <strong>Date range:</strong> ${(startDate)!" "} - ${(endDate)!" "}
            </div>
        </td>
    </tr>
</table>
<div style="width: 100%; float: left; margin-top: 20px;">
    <table class="border" style="font-size: 11px;">
        <tbody>
        <tr class="b1">
            <th>Description</th>
            <th></th>
            <th>Amount(ex GTS)</th>
            <th>GST</th>
            <th>Amount Payable(Incl GST)</th>
        </tr>
        <tr>
            <th rowspan="4">Payables</th>
            <td>Margin Share</td>
            <td>${(currencySymbol)!"$"}${(invoice.marginShareExcGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.marginShareGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.marginShare)!"0.00"}</td>
        </tr>
        <tr>
            <td>61+ Payment Margin Share</td>
            <td>${(currencySymbol)!"$"}${(invoice.day61MarginShareExcGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.day61MarginShareGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.day61MarginShare)!"0.00"}</td>
        </tr>
        <tr>
            <td>Non Centralised Margin Share</td>
            <td>${(currencySymbol)!"$"}${(invoice.nonCentralizedMarginShareExcGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.nonCentralizedMarginShareGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.nonCentralizedMarginShare)!"0.00"}</td>
        </tr>
        <tr>
            <td>Late Fee</td>
            <td>${(currencySymbol)!"$"}${(invoice.lateFeeExcGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.lateFeeGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.lateFee)!"0.00"}</td>
        </tr>
        <tr>
            <th class="b1" colspan="2">Gross Payables</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.grossPayablesExcGst)!"0.00"}</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.grossPayablesGst)!"0.00"}</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.grossPayables)!"0.00"}</th>
        </tr>
        <tr>
            <th rowspan="1">Other Payables</th>
            <td>Repaid Carrier Deductions</td>
            <td>${(currencySymbol)!"$"}${(invoice.repaidCarrierDeductionsExcGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.repaidCarrierDeductionsGst)!"0.00"}</td>
            <td>${(currencySymbol)!"$"}${(invoice.repaidCarrierDeductions)!"0.00"}</td>
        </tr>
        <tr>
            <th rowspan="3">Costs</th>
            <td>Carrier Cost Deductions</td>
            <td class="s30">- ${(currencySymbol)!"$"}${(invoice.carrierCostDeductionExcGst)!"0.00"}</td>
            <td class="s30">- ${(currencySymbol)!"$"}${(invoice.carrierCostDeductionGst)!"0.00"}</td>
            <td class="s30">- ${(currencySymbol)!"$"}${(invoice.carrierCostDeduction)!"0.00"}</td>
        </tr>
        <tr>
            <td>Tech Fees</td>
            <td class="s30">- ${(currencySymbol)!"$"}${(invoice.techFeesExcGst)!"0.00"}</td>
            <td class="s30">- ${(currencySymbol)!"$"}${(invoice.techFeesGst)!"0.00"}</td>
            <td class="s30">- ${(currencySymbol)!"$"}${(invoice.techFees)!"0.00"}</td>
        </tr>
        <tr>
            <td>Marketing Fees</td>
            <td class="s30">- ${(currencySymbol)!"$"}${(invoice.marketingFeesExcGst)!"0.00"}</td>
            <td class="s30">- ${(currencySymbol)!"$"}${(invoice.marketingFeesGst)!"0.00"}</td>
            <td class="s30">- ${(currencySymbol)!"$"}${(invoice.marketingFees)!"0.00"}</td>
        </tr>
        <tr>
            <th class="b1" colspan="2">Net payables</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.netPayablesExcGst)!"0.00"}</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.gstPayables)!"0.00"}</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.totalNetPayable)!"0.00"}</th>
        </tr>
        <tr>
            <th class="b1" colspan="1">Reimbursements</th>
            <td>Non-Central Carrier Costs</td>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.nonCentralCarrierCostExcGst)!"0.00"}</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.nonCentralCarrierCostGst)!"0.00"}</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.nonCentralCarrierCost)!"0.00"}</th>
        </tr>
        <tr>
            <th class="b1" colspan="2">Total Payables</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.totalPayableExcGst)!"0.00"}</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.totalPayableGst)!"0.00"}</th>
            <th class="b1">${(currencySymbol)!"$"}${(invoice.totalPayable)!"0.00"}</th>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>