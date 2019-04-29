<html>
<head>
    <style type="text/css">
        @page {
            size: A4;
        }

        body {
            font-family: Arial;
            font-size: 12px;
        }

        table {
            width: 100%;
            font-size 12px;
        }

        table th {
            font-weight: bold;
        }

        .text-bold {
            font-weight: bold;
        }

        .title {
            margin: 0 auto;
            text-align: center;
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
        }

        .border tbody tr td {
            border: 1px solid #000;
            font-size 12px;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td><img width="200px" alt="AGL Logo" src="${(logo)!" "}"/></td>
        <td>
			<span style="font-size=9px;">ALL MAX LOGISTICS LIMITED<br/>ROOM 1701-04, 17TH FLOOR<br/>CHINA MERCHANTS BUILDING<br/>303-307 DES VOEUX ROAD<br/>CENTRAL, HONG KONG<br/>
			<a href="www.agl.com.hk">www.agl.com.hk</a>
			</span>
        </td>
        <td>
            <div class="title">
                <h3 style="text-transform: uppercase;">Recipient Created Tax Invoice</h3>
            </div>
        </td>
    </tr>
</table>
<table class="border" style="width: 50% !important;">
    <tbody>
    <tr>
        <td align="left">
            <strong>BILL TO:</strong><br/>
        ${(franchise.customerName)!" "}<br/>
        ${(franchise.address1)!" "}<br/>
        ${(franchise.address2)!" "}<br/>
        ${(franchise.city)!" "}, ${(franchise.postalCode)!" "}, ${(franchise.country)!" "}<br/>
        ${(franchise.email)!" "}
        </td>
    </tr>
    </tbody>
</table>
<br/>

<div class="title">
    Commissions Payable As Per Agreement
</div>
<br/>
<strong>Date range:</strong> ${(startDate)!" "} - ${(endDate)!" "}
<table class="border"
       id="datatable" style="float: left">
    <thead>

    <tr>
        <th>Item</th>
        <th>Description</th>
        <th>Amount</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td colspan="3"
            style="font-style: italic; text-decoration: underline;">Payables:
        </td>
    </tr>
    <tr>
        <td>1</td>
        <td>Margin Share</td>
        <td>${(overview.marginShare)!"0"}</td>
    </tr>
    <tr>
        <td>2</td>
        <td>Non Centralised Margin Share</td>
        <td>${(overview.nonCentralizedMarginShare)!"0"}</td>
    </tr>
    <tr>
        <td>3</td>
        <td>Late Fee</td>
        <td>${(overview.lateFee)!"0"}</td>
    </tr>
    <tr>
        <td colspan="3"
            style="font-style: italic; text-decoration: underline;">Gross
            Payables :
        </td>
    </tr>
    <tr>
        <td>4</td>
        <td>Gross Payables</td>
        <td>${(overview.grossPayables)!"0"}</td>
    </tr>
    <tr>
        <td colspan="3"
            style="font-style: italic; text-decoration: underline;">Other
            Payables :
        </td>
    </tr>
    <tr>
        <td>5</td>
        <td>Carrier Credits</td>
        <td>${(overview.carrierCredits)!"0"}</td>
    </tr>
    <tr>
        <td>6</td>
        <td>61 Day Payments</td>
        <td>${(overview.day61Payment)!"0"}</td>
    </tr>
    <tr>
        <td>7</td>
        <td>Non Central Carrier Cost</td>
        <td>${(overview.nonCentralCarrierCost)!"0"}</td>
    </tr>
    <tr>
        <td colspan="3"
            style="font-style: italic; text-decoration: underline;">Costs
            :
        </td>
    </tr>
    <tr>
        <td>8</td>
        <td>Carrier Cost Deductions</td>
        <td>${(overview.carrierCostDeduction)!"0"}</td>
    </tr>
    <tr>
        <td>9</td>
        <td>Tech Fees</td>
        <td>${(overview.techFees)!"0"}</td>
    </tr>
    <tr>
        <td>10</td>
        <td>Marketing Fee etc</td>
        <td>${(overview.marketingFees)! "0"}</td>
    </tr>
    <tr>
        <td colspan="3"
            style="font-style: italic; text-decoration: underline;">Net
            Payables :
        </td>
    </tr>
    <tr>
        <td>11</td>
        <td>Net Payables</td>
        <td>${(overview.netPayables)!"0"}</td>
    </tr>
    </tbody>
</table>
</body>
</html>