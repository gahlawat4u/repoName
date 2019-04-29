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
            <span style="font-size:20px;">${(lang.translate('Packing List'))!"Packing List"}</span>
        </th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td width="25%" style="border-right: 0"><strong>${(lang.translate('Date:'))!"Date:"}</strong></td>
        <td width="25%" style="border-left: 0" align="right">${detailInfoModel.shipmentDate}</td>
        <td width="25%" style="border-right: 0">
            <strong>${(lang.translate('Destination Country:'))!"Destination Country:"}</strong></td>
        <td width="25%" style="border-left: 0" align="right">${detailInfoModel.rCountryName}</td>
    </tr>
    <tr>
        <td width="25%" style="border-right: 0"><strong>${(lang.translate('Shipper:'))!"Shipper:"}</strong></td>
        <td width="25%" style="border-left: 0" align="right">${detailInfoModel.sCompanyName}</td>
        <td width="25%" style="border-right: 0"><strong>${(lang.translate('Consignee:'))!"Consignee:"}</strong></td>
        <td width="25%" style="border-left: 0" align="right">${detailInfoModel.rCompanyName}</td>
    </tr>
    <tr>
        <td width="25%" style="border-right: 0"><strong>${(lang.translate('Contact:'))!"Contact:"}</strong></td>
        <td width="25%" style="border-left: 0" align="right">${detailInfoModel.sContactName}</td>
        <td width="25%" style="border-right: 0"><strong>${(lang.translate('Contact:'))!"Contact:"}</strong></td>
        <td width="25%" style="border-left: 0" align="right">${detailInfoModel.rContactName}</td>
    </tr>
    <tr>
        <td width="50%" colspan="2"><strong>${(lang.translate('Shipper Address:'))!"Shipper Address:"}</strong>
            <br/> ${detailInfoModel.sAddress}<br/> ${detailInfoModel.sCountryName}<br/> ${detailInfoModel.sPostalCode}
            <br/>${detailInfoModel.sCity}<br/>Tel: ${detailInfoModel.sPhone}</td>
        <td width="50%" colspan="2">
            <strong>${(lang.translate('Receiver Address:'))!"Receiver Address:"}</strong> ${detailInfoModel.rAddress}
            <br/> ${detailInfoModel.rCountryName}<br/> ${detailInfoModel.rPostalCode}<br/>${detailInfoModel.rCity}<br/>Tel: ${detailInfoModel.rPhone}
        </td>
    </tr>
    <tr>
        <td width="25%"><strong>${(lang.translate('Waybill No:'))!"Waybill No:"}</strong> <span
                style="float: right">${detailInfoModel.airbillNumber}</span></td>
        <td width="25%"><strong>${(lang.translate('Carrier:'))!"Carrier:"}</strong> <span
                style="float:right">${detailInfoModel.serviceName}</span></td>
        <td width="50%" colspan="2"><strong>${(lang.translate('Shipper Ref:'))!"Shipper Ref:"}</strong><span
                style="float:right">${detailInfoModel.customerCode}</span></td>
    </tr>
    <tr>
        <td width="25%" style="border-right: 0">
            <strong>${(lang.translate('No of Pieces:'))!"No of Pieces:"}</strong> ${detailInfoModel.noOfPieces}</td>
        <td width="25%" style="border-right: 0">
            <strong>${(lang.translate('Total Weight:'))!"Total Weight:"}</strong> ${detailInfoModel.totalWeight} KG(s)
        </td>
        <td width="5%" style="border-right: 0">
            <strong>${(lang.translate('Shipment Content:'))!"Shipment Content:"}</strong></td>
        <td width="45%" align="right" style="border-left: 0">${detailInfoModel.contentDescription}</td>
    </tr>
    <tr>
        <td colspan="4">
        ${(lang.translate('Invoice Items'))!"Invoice Items"}
            <table>
                <thead>
                <tr bgcolor="silver">
                    <th>${(lang.translate('No. of CTNS'))!"No. of CTNS"} </th>
                    <th>${(lang.translate('Description of Goods'))!"Description of Goods"}</th>
                    <th>${(lang.translate('Quantity'))!"Quantity"}</th>
                    <th>${(lang.translate('Net Weight'))!"Net Weight"}</th>
                    <th>${(lang.translate('Gross Weight'))!"Gross Weight"}</th>
                    <th>${(lang.translate('Measurement'))!"Measurement"}</th>
                </tr>
                </thead>
                <tbody>
                <#list shipmentProductDetailVos as pic>
                <tr>
                    <td>${pic.noOfCarton}</td>
                    <td>${pic.description}</td>
                    <td>${pic.qty}</td>
                    <td>${pieces[pic_index].deadWeight} KG</td>
                    <td>${pieces[pic_index].weight} KG</td>
                    <td>${pieces[pic_index].dimensionL} x ${pieces[pic_index].dimensionW}
                        x ${pieces[pic_index].dimensionH}(cm)
                    </td>
                </tr>
                </#list>
                <tr>
                    <td colspan="2"></td>
                    <td>${totalQuantity}</td>
                    <td>${totalNetWeight}</td>
                    <td>${totalGrossWeight} KG</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="4">
        ${(lang.translate('Declaration: I declare that the above information is true and correct to the best of my knowledge.<br/><br/>

					Signature : _________________________________________________________________________________ Date :_______________<br/>
					Position in Company : _____________________________________________________________________________________________'))!"Declaration: I declare that the above information is true and correct to the best of my knowledge.<br/><br/>

					Signature : _________________________________________________________________________________ Date :_______________<br/>
					Position in Company : _____________________________________________________________________________________________"}
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>