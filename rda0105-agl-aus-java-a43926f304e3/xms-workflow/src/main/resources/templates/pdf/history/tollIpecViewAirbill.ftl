<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        @page {
            size: A4 landscape;
            margin: 0mm 0mm 0mm 0mm;
        }

        body {
            font-family: Arial;
            font-size: 13px;
            color: #000;
            padding-left: 30px;
            padding-right: 30px;
        }

        .page-break {
            page-break-after: always;
        }
    </style>
</head>
<body>
<#list listPieceViewAirbill as pic>
    <#if pic?index != 0>
    <div class="page-break"></div></#if>
    <@compress single_line=true>
<table style="width: 50%;  float: right;">
    <tr>
        <td>
        <table style="width: 520px; height : 775px; margin:0 auto">
    <tr>
        <td style="width: 25%"><span style="font-size: 17px"><strong>${detailInfoModel.serviceName}</strong></span></td>
        <td align="center" style="width: 50%"><span
                style="font-size: 17px">Connote No: ${detailInfoModel.tracking}</span><br/>Dispatch
            Date: ${detailInfoModel.shipmentDate}</td>
        <td style="width: 25%"><span style="font-size: 17px"><strong>${detailInfoModel.depotcCode}</strong></span></td>
    </tr>
    <tr>
        <td colspan="3" bgcolor="black" align="center"><span style="color: #fff">${detailInfoModel.serviceType}</span>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="left">
            <table>
                <tbody>
                <tr>
                    <td>TO:</td>
                    <td>${detailInfoModel.rCompanyName}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>${detailInfoModel.rAddress}<br/>
                    ${detailInfoModel.rAddress2}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>${detailInfoModel.rCity}<br/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>Phone: ${detailInfoModel.rPhone} Contact: ${detailInfoModel.rContactName}</td>
                </tr>
                </tbody>
            </table>
        </td>
        <td>
        ${detailInfoModel.rState} ${detailInfoModel.rPostalCode}
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <table>
                <tr>
                    <td>FROM:</td>
                    <td>${detailInfoModel.sCompanyName}</td>
                </tr>
                <tr>
                    <td></td>
                    <td>${detailInfoModel.sAddress} <br/> ${detailInfoModel.sAddress2} <br/>
                    ${detailInfoModel.sCountryName} <br/> ${detailInfoModel.sCity} ${detailInfoModel.sPostalCode}<br/>
                        Phone: ${detailInfoModel.sPhone}</td>
                </tr>
            </table>
        </td>
        <td></td>
    </tr>
    <tr>
        <td colspan="2">
            Description of Goods : ${detailInfoModel.contentDescription} <br/>Item Ref: ${detailInfoModel.referenceNo}
        </td>
        <td></td>
    </tr>
    <tr>
        <td colspan="2">
            Total Weight: ${pic.weight} ${detailInfoModel.weightUnit}<br/>Total Cube M3: ${pic.dimWeight}<br/>Cubic
            (cm): ${pic.dimession}
        </td>
        <td><b>Item ${pic.picesCount} Of ${detailInfoModel.noOfPieces}</b></td>
    </tr>
    <tr>
        <td colspan="2">
        ${detailInfoModel.rCompanyName} <br/> ${detailInfoModel.rAddress} <br/> ${detailInfoModel.rAddress2}
        </td>
        <td></td>
    </tr>
    <tr>
        <td colspan="2"><b>${detailInfoModel.rCity} ${detailInfoModel.rState} ${detailInfoModel.rPostalCode}</b></td>
        <td></td>
    </tr>
    <tr>
        <td colspan="2">${detailInfoModel.serviceType}</td>
        <td>Item: ${pic.itemCode}</td>
    </tr>
    <tr>
        <td colspan="3" align="center"><img width="240px" height="80px" src="${pic.imageBarcode}"/></td>
    </tr>
    <tr>
        <td colspan="3" align="center">${pic.pieceBarcode}</td>
    </tr>
    <tr>
        <td colspan="3">
            Declaration by:<br/>
            CARRIER'S TERMS AND CONDITIONS APPLY<br/>
            WARNING: DANGEROUS GOODS NOT CONSIGNED WITHIN
        </td>
    </tr>
</table></td>
    </tr>
</table>


</@compress>
</#list>
</body>
</html>