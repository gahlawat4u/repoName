<!DOCTYPE html>
<html>
<head>
    <title>Invoice</title>
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
                    <td>${(systemAddress)!" "} <br/> ${(siteAddress)!" "}</td>
                    <td align="left"><span
                            style="font-size: 20px; font-weight: bold">${lang.translate('INVOICE')}</span></td>
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
                <strong>${lang.translate('BILL TO: ')}</strong> <br/>${(franchise.customerName?html?upper_case)!" "}
                <br/> ${(franchise.address1?upper_case)!" "}<br/> ${(franchise.city?upper_case)!" "}
                , ${(franchise.postalCode?upper_case)!" "}, ${(franchise.country?upper_case)!" "}
                <br/> ${(franchise.registrationid)!"0"}
            </div>
        </td>
        <td valign="top">
            <div style="border: 1px solid #000; padding: 8px; margin-left: 10px;">
                <strong>${lang.translate('Date: ')}</strong> ${(date)!" "}<br/>
                <strong>${lang.translate('Invoice Number: ')}</strong> ${(invoiceCode)!" "}<br/>
                <strong>${lang.translate('Date range: ')}</strong> ${(startDate)!" "} - ${(endDate)!" "}
            </div>
        </td>
    </tr>
</table>
<div style="width: 100%; float: left; margin-top: 20px;">
    <table class="border" style="font-size: 11px;">
        <tbody>
        <tr class="b1">
            <th>${lang.translate('Item')}</th>
            <th>${lang.translate('Description')}</th>
            <th>${lang.translate('Amount')}</th>
        </tr>
        <tr>
            <th colspan="3">${lang.translate('Receiveables:')}</th>
        </tr>
        <tr>
            <td>1</td>
            <td>${lang.translate('Customer Cost')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.customerCost)!"0.00"}</td>
        </tr>
        <tr>
            <td>2</td>
            <td>${lang.translate('Customer Marginable Cost')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.customerMarginableCost)!"0.00"}</td>
        </tr>
        <tr>
            <td>3</td>
            <td>${lang.translate('Franchise Cost Taxable')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.franchiseCostTaxable)!"0.00"}</td>
        </tr>
        <tr>
            <td>4</td>
            <td>${lang.translate('Franchise Cost Non-taxable')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.franchiseCostNonTaxable)!"0.00"}</td>
        </tr>
        <tr>
            <td>5</td>
            <td>${lang.translate('Franchise TVA')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.franchiseGst)!"0.00"}</td>
        </tr>
        <tr>
            <td>6</td>
            <td>${lang.translate('Total Franchise Cost')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.franchiseTotal)!"0.00"}</td>
        </tr>
        <tr>
            <td>7</td>
            <td>${lang.translate('Margin Shared')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.marginShared)!"0.00"}</td>
        </tr>
        <tr>
            <td>8</td>
            <td>${lang.translate('Management Fee on Revenue Shared')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.managementFee)!"0.00"}</td>
        </tr>
        <tr>
            <td>9</td>
            <td>${lang.translate('Marketing Fee')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.marketingFee)!"0.00"}</td>
        </tr>
        <tr>
            <td>10</td>
            <td>${lang.translate('Carrier Credit Taxable')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.carrierCreditsTaxable)!"0.00"}</td>
        </tr>
        <tr>
            <td>11</td>
            <td>${lang.translate('Carrier Credit Non-taxable')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.carrierCreditsNonTaxable)!"0.00"}</td>
        </tr>
        <tr>
            <td>12</td>
            <td>${lang.translate('Carrier Credit TVA')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.carrierCreditsGst)!"0.00"}</td>
        </tr>
        <tr>
            <td>13</td>
            <td>${lang.translate('Total Carrier Credit')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.carrierCreditsTotal)!"0.00"}</td>
        </tr>
        <tr>
            <td>14</td>
            <td>${lang.translate('Management Fee On Credit Revenue')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.managementFeeOnCreditRevenue)!"0.00"}</td>
        </tr>
        <tr>
            <td>15</td>
            <td>${lang.translate('Management Fee On Credit Profit')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.managementFeeOnCreditProfitShared)!"0.00"}</td>
        </tr>
        <tr>
            <td>16</td>
            <td>${lang.translate('Tech Fees on International Shipments')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.techFeeOnIntlShipments)!"0.00"} (${(invoice.intlShipmentCount)!"0"})
            </td>
        </tr>
        <tr>
            <td>17</td>
            <td>${lang.translate('Tech Fees on Domestic Shipments')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.techFeeOnDomShipments)!"0.00"} (${(invoice.domShipmentCount)!"0"})
            </td>
        </tr>
        <tr>
            <th colspan="3">${lang.translate('Net Receiveables:')}</th>
        </tr>
        <tr>
            <td>18</td>
            <td>${lang.translate('Net Receiveables')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.netReceivable)!"0.00"}</td>
        </tr>
        <tr>
            <td>19</td>
            <td>${lang.translate('TVA')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.gst)!"0.00"}</td>
        </tr>
        <tr>
            <td>20</td>
            <td>${lang.translate('Total Receiveables')}</td>
            <td>${(currencySymbol)!"$"}${(invoice.totalReceivable)!"0.00"}</td>
        </tr>
        </tbody>
    </table>
</div>
<div style="width: 100%; float: left">
${(invoicePaymentText)!""}
</div>
</body>
</html>