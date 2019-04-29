<#setting date_format="dd-MM-yyyy">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>
        View airbill
    </title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <style type="text/css">
        .text-lg {
            font-size: 40px;
        }

        .text-20 {
            font-size: 20px;
        }

        .text-md {
            font-size: 16px;
        }

        .text-sm {
            font-size: 12px;
        }

        .text-bold {
            font-weight: bold;
        }

        .text-left {
            text-align: left;
        }

        .text-right {
            text-align: right;
        }

        .text-center {
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        .table-border tbody tr td {
            border: 2px solid #000;
        }

        .line tbody tr td {
            border-bottom: 3px solid #000;
        }

        .line tbody tr:last-child td {
            border-bottom: 0;
        }
    </style>
</head>
<body style="font-family: Arial; font-size: 12px;">
<#if detailInfoModel.noOfPieces== "0">
<table class="line">
    <tbody>
    <tr border="0">
        <td class="text-left"><span class="text-lg text-bold">${detailInfoModel.rPostalCode}</span></td>
        <td class="text-left"><span class="text-lg text-bold">TNT</span></td>
        <td class="text-right"><span class="text-md text-bold">
					<br/>
        ${detailInfoModel.tracking}
				</span></td>
    </tr>
    <tr>
        <td><span class="text-20 text-bold">${detailInfoModel.rCity}</span></td>
        <td colspan="2" class="text-right"><span class="text-md">Itm: </span></td>
    </tr>
    <tr>
        <td><span class="text-20 text-bold">${detailInfoModel.serviceType}</span><br/><br/></td>
        <td colspan="2" class="text-right">
            <span class="text-sm">Sort Bin:</span> <span class="text-20 text-bold"></span>
            <br/><br/>
        </td>
    </tr>

    <tr>
        <td>${detailInfoModel.shipmentDate}</td>

        <td> 0 Of ${detailInfoModel.noOfPieces} </td>

        <td>Item Wt. 0 ${detailInfoModel.weightUnit}</td>
        <td align="right"></td>
    </tr>

    <tr>
        <td colspan="3" class="text-center">
            <span class="text-sm"></span>
            <br/><br/>
        </td>
    </tr>
    <tr>
        <td colspan="3">
            To:<br/>
        ${detailInfoModel.rCompanyName} <br/>
        ${detailInfoModel.rContactName}<br/>
        ${detailInfoModel.rAddress}<br/>
        ${detailInfoModel.rCity}, ${detailInfoModel.rPostalCode}<br/>
            Ph: ${detailInfoModel.rPhone}
        </td>
    </tr>
    <tr>
        <td colspan="3">
            From: ${detailInfoModel.sContactName}, ${detailInfoModel.sCompanyName}, ${detailInfoModel.sAddress}
            , ${detailInfoModel.sCity}, ${detailInfoModel.sPostalCode}.<br/>
            Ph: ${detailInfoModel.sPhone}
        </td>
    </tr>
    </tbody>
</table>
<table>
    <tbody>
    <tr>
        <td width="50%">
            Sender Ref:<br/>
        ${detailInfoModel.referenceNo}
        </td>
        <td width="50%" style="border-left: 3px solid #000;">
            Ref:<br/>
        </td>
    </tr>
    <tr>
        <td>Special Instructions</td>
        <td>Insurance: ${detailInfoModel.withInsurance}</td>
    </tr>
    <tr>

        <td colspan="2" align="left"></td>

    </tr>
    </tbody>
</table>
<div style="margin: 50px auto auto auto; width: 90%">
    <table class="table-border">
        <tbody>
        <tr>
            <td width="50%">
                CN: ${detailInfoModel.tracking}<br/>
                Itm: <br/>
                0 Of ${detailInfoModel.noOfPieces}<br/>
                TO:<br/>
            ${detailInfoModel.rCompanyName} <br/>
            ${detailInfoModel.rContactName}<br/>
            ${detailInfoModel.rAddress}<br/>
            ${detailInfoModel.rCity},${detailInfoModel.rState}, ${detailInfoModel.rPostalCode}<br/>
                Ph: ${detailInfoModel.rPhone}
            </td>
            <td width="50%">
            ${detailInfoModel.serviceType}<br/>
                Con Note Wt.: 0.00 ${detailInfoModel.weightUnit}<br/>
                FROM:<br/>
            ${detailInfoModel.sCompanyName} <br/>
            ${detailInfoModel.sContactName} <br/>
            ${detailInfoModel.sAddress} <br/>
            ${detailInfoModel.sCity} ,${detailInfoModel.sState}, ${detailInfoModel.sPostalCode}<br/>
                Ph: ${detailInfoModel.sPhone}
            </td>
        </tr>
        </tbody>
    </table>
</div>
<center>
    <div style="width: 30%; margin: auto;margin-top:20px; ">
        <div><img src="" width="300" height="90"/></div>
        <div class="text-center"><span></span></div>
    </div>
</center>
<#else>
    <#list listPieceViewAirbill as pic>
    <table class="line">
        <tbody>
        <tr border="0">
            <td class="text-left"><span class="text-lg text-bold">${detailInfoModel.rPostalCode}</span></td>
            <td class="text-left"><span class="text-lg text-bold">TNT</span></td>
            <td class="text-right"><span class="text-md text-bold">
					${pic.destinationPortDescription}<br/>
                    ${detailInfoModel.tracking}
				</span></td>
        </tr>
        <tr>
            <td><span class="text-20 text-bold">${detailInfoModel.rCity}</span></td>
            <td colspan="2" class="text-right"><span class="text-md">Itm: ${pic.itemCode}</span></td>
        </tr>
        <tr>
            <td><span class="text-20 text-bold">${detailInfoModel.serviceType}</span><br/><br/></td>
            <td colspan="2" class="text-right">
                <span class="text-sm">Sort Bin:</span> <span class="text-20 text-bold">${pic.sortBin}</span>
                <br/><br/>
            </td>
        </tr>

        <tr>
            <td>${detailInfoModel.shipmentDate}</td>

            <td> ${pic.picesCount} Of ${detailInfoModel.noOfPieces} </td>

            <td>Item Wt. ${pic.weight} ${detailInfoModel.weightUnit}</td>
            <td align="right">${pic.exPort}</td>
        </tr>

        <tr>
            <td colspan="3" class="text-center">
                <span class="text-sm">${pic.strDanger}</span>
                <br/><br/>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                To:<br/>
            ${detailInfoModel.rCompanyName} <br/>
            ${detailInfoModel.rContactName}<br/>
            ${detailInfoModel.rAddress}<br/>
            ${detailInfoModel.rCity}, ${detailInfoModel.rPostalCode}<br/>
                Ph: ${detailInfoModel.rPhone}
            </td>
        </tr>
        <tr>
            <td colspan="3">
                From: ${detailInfoModel.sContactName}, ${detailInfoModel.sCompanyName}, ${detailInfoModel.sAddress}
                , ${detailInfoModel.sCity}, ${detailInfoModel.sPostalCode}.<br/>
                Ph: ${detailInfoModel.sPhone}
            </td>
        </tr>
        </tbody>
    </table>
    <table>
        <tbody>
        <tr>
            <td width="50%">
                Sender Ref:<br/>
            ${detailInfoModel.referenceNo}
            </td>
            <td width="50%" style="border-left: 3px solid #000;">
                Ref:<br/>
            </td>
        </tr>
        <tr>
            <td>Special Instructions</td>
            <td>Insurance: ${detailInfoModel.withInsurance}</td>
        </tr>
        <tr>

            <td colspan="2" align="left"> ${pic.reasonForExport}</td>

        </tr>
        </tbody>
    </table>
    <div style="margin: 50px auto auto auto; width: 90%">
        <table class="table-border">
            <tbody>
            <tr>
                <td width="50%">
                    CN: ${detailInfoModel.tracking}<br/>
                    Itm: ${pic.itemCode}<br/>
                ${pic.picesCount} Of ${detailInfoModel.noOfPieces}<br/>
                    TO:<br/>
                ${detailInfoModel.rCompanyName} <br/>
                ${detailInfoModel.rContactName}<br/>
                ${detailInfoModel.rAddress}<br/>
                ${detailInfoModel.rCity},${detailInfoModel.rState}, ${detailInfoModel.rPostalCode}<br/>
                    Ph: ${detailInfoModel.rPhone}
                </td>
                <td width="50%">
                ${detailInfoModel.serviceType}<br/>
                    Con Note Wt.: ${pic.weight} ${detailInfoModel.weightUnit}<br/>
                    FROM:<br/>
                ${detailInfoModel.sCompanyName} <br/>
                ${detailInfoModel.sContactName} <br/>
                ${detailInfoModel.sAddress} <br/>
                ${detailInfoModel.sCity} ,${detailInfoModel.sState}, ${detailInfoModel.sPostalCode}<br/>
                    Ph: ${detailInfoModel.sPhone}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <center>
        <div style="width: 30%; margin: auto;margin-top:20px; ">
            <div><img src="${pic.imageBarcode}" width="300" height="90"/></div>
            <div class="text-center"><span>${pic.pieceBarcode}</span></div>
        </div>
    </center>
    </#list>
</#if>

</body>
</html>