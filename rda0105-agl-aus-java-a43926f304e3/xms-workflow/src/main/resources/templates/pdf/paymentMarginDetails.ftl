<html>
<head>
    <title>Payment Margin Detail Reports</title>
    <style>
        @page {
            size: 50cm 30cm;
        }

        .table {
            margin: 0px;
            padding: 0px;
            width: 100%;
            border: 1px solid #4f9193;

            -moz-border-radius-bottomleft: 0px;
            -webkit-border-bottom-left-radius: 0px;
            border-bottom-left-radius: 0px;

            -moz-border-radius-bottomright: 0px;
            -webkit-border-bottom-right-radius: 0px;
            border-bottom-right-radius: 0px;

            -moz-border-radius-topright: 0px;
            -webkit-border-top-right-radius: 0px;
            border-top-right-radius: 0px;

            -moz-border-radius-topleft: 0px;
            -webkit-border-top-left-radius: 0px;
            border-top-left-radius: 0px;
        }

        .table table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            height: 100%;
            margin: 0px;
            padding: 0px;
        }

        .table tr:last-child td:last-child {
            -moz-border-radius-bottomright: 0px;
            -webkit-border-bottom-right-radius: 0px;
            border-bottom-right-radius: 0px;
        }

        .table table tr:first-child td:first-child {
            -moz-border-radius-topleft: 0px;
            -webkit-border-top-left-radius: 0px;
            border-top-left-radius: 0px;
        }

        .table table tr:first-child td:last-child {
            -moz-border-radius-topright: 0px;
            -webkit-border-top-right-radius: 0px;
            border-top-right-radius: 0px;
        }

        .table tr:last-child td:first-child {
            -moz-border-radius-bottomleft: 0px;
            -webkit-border-bottom-left-radius: 0px;
            border-bottom-left-radius: 0px;
        }

        .table tr:hover td {

        }

        .table tr:nth-child(odd) {
            background-color: #def9fc;
        }

        .table tr:nth-child(even) {
            background-color: #ffffff;
        }

        .table td {
            vertical-align: middle;

            border: 1px solid #4f9193;
            border-width: 0px 1px 1px 0px;
            text-align: left;
            padding: 7px;
            font-size: 10px;
            font-family: Helvetica;
            font-weight: normal;
            color: #0379a0;
        }

        .table tr:last-child td {
            border-width: 0px 1px 0px 0px;
        }

        .table tr td:last-child {
            border-width: 0px 0px 1px 0px;
        }

        .table tr:last-child td:last-child {
            border-width: 0px 0px 0px 0px;
        }

        .table tr:first-child td {
            background: -o-linear-gradient(bottom, #60bfff 5%, #60bfff 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #60bfff), color-stop(1, #60bfff));
            background: -moz-linear-gradient(center top, #60bfff 5%, #60bfff 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#60bfff", endColorstr="#60bfff");
            background: -o-linear-gradient(top, #60bfff, 60 bfff);

            background-color: #60bfff;
            border: 0px solid #4f9193;
            text-align: center;
            border-width: 0px 0px 1px 1px;
            font-size: 14px;
            font-family: Helvetica;
            font-weight: bold;
            color: #ffffff;
        }

        .table tr:first-child:hover td {
            background: -o-linear-gradient(bottom, #60bfff 5%, #60bfff 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #60bfff), color-stop(1, #60bfff));
            background: -moz-linear-gradient(center top, #60bfff 5%, #60bfff 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#60bfff", endColorstr="#60bfff");
            background: -o-linear-gradient(top, #60bfff, 60 bfff);

            background-color: #60bfff;
        }

        .table tr:first-child td:first-child {
            border-width: 0px 0px 1px 0px;
        }

        .table tr:first-child td:last-child {
            border-width: 0px 0px 1px 1px;
        }
    </style>
</head>
<img src="http://localhost:8080/demo/images/agl_logo21.gif"/>
<table class="table">
    <thead>
    <tr>
        <th width="100px;">Payment Date</th>
        <th>Customer No</th>
        <th>Customer Name</th>
        <th>Invoice #</th>
        <th>AWB/Connote No</th>
        <th>Amount Outstanding</th>
        <th>International Domestic</th>
        <th colspan="2">Customer Total</th>
        <th colspan="2">Franchisee Cost</th>
        <th colspan="2">Gross Margin</th>
        <th>Previously Paid(inc GST)</th>
        <th>Payment Received (inc GST)</th>
        <th colspan="2">Credits</th>
        <th>Profit Share</th>
        <th>Profit Share - GST</th>
        <th>Total Profit Share</th>
    </tr>
    <tr>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th></th>
        <th>Price Ex GST</th>
        <th>GST</th>
        <th>Price Ex GST</th>
        <th>GST</th>
        <th>Price Ex GST</th>
        <th>GST</th>
        <th></th>
        <th></th>
        <th>Franchisee Cost.</th>
        <th>Customer Cost.</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>

    </thead>
    <tbody>
    <#list paymentMarginDetails as pmd>
    <tr>
        <td>${pmd.paymentDate?date}</td>
        <td>${pmd.customerNumber}</td>
        <td>${pmd.customerName}</td>
        <td>${pmd.invoiceNumber}</td>
        <td>${pmd.airbillNumber}</td>
        <td align="right">${pmd.amountOutstanding}</td>
        <td>${pmd.internationalDomestic}</td>
        <td align="right">${pmd.customerTotalExcGst}</td>
        <td align="right">${pmd.customerTotalGst}</td>
        <td align="right">${pmd.franchiseCostExcGst}</td>
        <td align="right">${pmd.franchiseCostGst}</td>
        <td align="right">${pmd.grossMarginExcGst}</td>
        <td align="right">${pmd.grossMarginGst}</td>
        <td align="right">${pmd.previouslyPaid}</td>
        <td align="right">${pmd.paymentsReceived}</td>
        <td align="right">${pmd.creditsFranchiseCost}</td>
        <td align="right">${pmd.creditsCustomerCost}</td>
        <td align="right">${pmd.profitShareExcGst}</td>
        <td align="right">${pmd.profitShareGst}</td>
        <td align="right">${pmd.totalProfitShare}</td>
    </tr>
    </#list>
    </tbody>
</table>
</html>