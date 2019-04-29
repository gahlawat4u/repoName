<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        @page {
            size: A4;
            margin: 20mm 0mm 0mm 0mm;
        }

        body {
            font-family: Arial;
            font-size: 11px;
            color: #000;
            padding-left: 30px;
            padding-right: 30px;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            margin-bottom: 20px;
        }

        table thead {
            background-color: #c5c5c5;
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

        .border tr td {
            border: 1px solid #000;
            padding: 4px;
        }
    </style>
</head>
<body>
<table width="530px" class="border">
    <thead>
    <tr>
        <th colspan="4" bgcolor="silver" height="12" align="center">
            <span style="font-size:20px;">Commercial Invoice</span>
        </th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td width="25%" style="border-right: 0"><strong>Date:</strong></td>
        <td width="25%" style="border-left: 0" align="right">${(detailInfoModel.shipmentDate)!" "}</td>
        <td width="25%" style="border-right: 0"><strong>Destination Country:</strong></td>
        <td width="25%" style="border-left: 0" align="right">${(detailInfoModel.rCountryName)!" "}</td>
    </tr>
    <tr>
        <td width="25%" style="border-right: 0"><strong>Shipper:</strong></td>
        <td width="25%" style="border-left: 0" align="right">${(detailInfoModel.sCompanyName?html)!" "}</td>
        <td width="25%" style="border-right: 0"><strong>Consignee:</strong></td>
        <td width="25%" style="border-left: 0" align="right">${(detailInfoModel.rCompanyName?html)!" "}</td>
    </tr>
    <tr>
        <td width="25%" style="border-right: 0"><strong>Contact:</strong></td>
        <td width="25%" style="border-left: 0" align="right">${(detailInfoModel.sContactName?html)!" "}</td>
        <td width="25%" style="border-right: 0"><strong>Contact:</strong></td>
        <td width="25%" style="border-left: 0" align="right">${(detailInfoModel.rContactName?html)!" "}</td>
    </tr>
    <tr>
        <td width="50%" colspan="2"><strong>Shipper Address:</strong> <br/> ${(detailInfoModel.sAddress?html)!" "}
            <br/> ${(detailInfoModel.sCountryName?html)!" "}<br/> ${(detailInfoModel.sPostalCode)!" "}
            <br/>${(detailInfoModel.sCity)!" "}<br/>Tel: ${(detailInfoModel.sPhone)!" "}</td>
        <td width="50%" colspan="2"><strong>Receiver Address:</strong> ${(detailInfoModel.rAddress?html)!" "}
            <br/> ${(detailInfoModel.rCountryName?html)!" "}<br/> ${(detailInfoModel.rPostalCode)!" "}
            <br/>${(detailInfoModel.rCity)!" "}<br/>Tel: ${(detailInfoModel.rPhone)!" "}</td>
    </tr>
    <tr>
        <td width="25%"><strong>Waybill No:</strong> <span
                style="float: right">${(detailInfoModel.airbillNumber?html)!" "}</span></td>
        <td width="25%"><strong>Carrier:</strong> <span
                style="float:right">${(detailInfoModel.serviceName?html)!" "}</span></td>
        <td width="50%" colspan="2"><strong>Bill To Party TaxID or GST
            No:</strong> ${(detailInfoModel.receiverTaxId)!" "}</td>
    </tr>
    <tr>
        <td width="25%"><strong>Sender's GST No:</strong> ${(detailInfoModel.gstId)!" "}</td>
        <td width="25%"><strong>Shipper Ref:</strong><span
                style="float:right">${(detailInfoModel.customerCode)!" "}</span></td>
        <td width="5%" style="border-right: 0"><strong>Shipment Content:</strong></td>
        <td width="45%" align="right" style="border-left: 0">${(detailInfoModel.contentDescription?html)!" "}</td>
    </tr>
    <tr>
        <td width="25%" style="border-right: 0"><strong>Term of Trade:</strong></td>
        <td width="25%" style="border-left: 0" align="right">${(detailInfoModel.termOfTrade)!" "}</td>
        <td width="25%" style="border-right: 0"><strong>Reason for Export:</strong></td>
        <td width="25%" style="border-left: 0" align="right">${(detailInfoModel.reasonForExport)!" "}</td>
    </tr>
    <tr>
        <td width="25%" style="border-right: 0"><strong>No of Pieces:</strong> ${(detailInfoModel.noOfPieces)!" "}</td>
        <td width="25%" style="border-right: 0"><strong>Total Weight:</strong> ${(detailInfoModel.totalWeight)!" "}
        ${(detailInfoModel.weightUnit)!" "}(s)
        </td>
        <td colspan="2" width="50%"></td>
    </tr>
    <tr>
        <td colspan="4">
            Invoice Items
            <table>
                <thead>
                <tr bgcolor="silver">
                    <th>Description of Merchandise</th>
                    <th>HTS #</th>
                    <th>Country of Origin</th>
                    <th>QTY</th>
                    <th>Unit</th>
                    <th>Unit Value</th>
                    <th>Total</th>
                </tr>
                </thead>
            <#list productAirbillModels as pro>
                <tr>
                    <td>${(pro.description)!" "}</td>
                    <td>${(pro.code)!" "}</td>
                    <td>${(pro.country)!" "}</td>
                    <td>${(pro.qty)!" "}</td>
                    <td>EA</td>
                    <td>${(pro.price)!" "}</td>
                    <td>${(pro.amount)!" "} ${(detailInfoModel.currencyCode)!" "}</td>
                </tr>
            </#list>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2"></td>
        <td colspan="2">
            <table class="border">
                <thead>
                <th border="1">Total Invoice</th>
                <th border="1">Amount</th>
                </thead>
                <tbody>
                <td style="border-right: 0" width="40%"></td>
                <td style="border-left: 0" width="60%">${(ttAmount)!" "}${(detailInfoModel.currencyCode)!" "}</td>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            Declaration: I declare that the above information is true and correct to the best of my knowledge.<br/><br/>

            Signature : _________________________________________________________________________________ Date
            :_______________<br/>
            Position in Company :
            _____________________________________________________________________________________________
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>