<#setting date_format="dd-MM-yyyy">
<html>
<head>
    <title>
        View airbill
    </title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <style type="text/css">
        @page {
            size: A4 landscape;
            margin: 0mm 0mm 0mm 0mm;
        }

        body {
            font-family: Arial;
            font-size: 11px;
            color: #000;
            padding-left: 30px;
            padding-right: 30px;
        }

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

        .border-bottom td {
            border-bottom: 1px solid #000;
        }

        .page-break {
            page-break-before: always;
        }
    </style>
</head>
<body style="font-family: Arial; font-size: 12px;">
<#list listPieceViewAirbill as pic>
    <#if pic?index != 0>
    <div class="page-break"></div></#if>
<table style="width: 55%;">
    <tr>
        <td></td>
    </tr>
</table>
<table style="width: 45%; float: right;">
    <tr>
        <td>
            <table>
                <tbody>
                <tr border="0">
                    <td class="text-left"><span class="text-lg text-bold">${detailInfoModel.rPostalCode}</span></td>
                    <td class="text-left"><span class="text-lg text-bold">TNT</span></td>
                    <td class="text-right"><span class="text-md text-bold">
					${pic.destinationPortDescription}<br/>
                    ${detailInfoModel.tracking}
				</span></td>
                </tr>
                <tr class="border-bottom">
                    <td><span class="text-20 text-bold">${detailInfoModel.rCity}</span></td>
                    <td colspan="2" class="text-right"><span class="text-md">Itm: ${pic.itemCode}</span></td>
                </tr>
                <tr class="border-bottom">
                    <td><span class="text-20 text-bold">${detailInfoModel.serviceType}</span><br/><br/></td>
                    <td colspan="2" class="text-right">
                        <span class="text-sm">Sort Bin:</span> <span class="text-20 text-bold">${pic.sortBin}</span>
                        <br/><br/>
                    </td>
                </tr>

                <tr>
                    <td colspan="3">
                        <table>
                            <tr class="border-bottom">
                                <td>${detailInfoModel.shipmentDate}</td>
                                <td> ${pic.picesCount} Of ${detailInfoModel.noOfPieces} </td>
                                <td>Item Wt. ${pic.weight} ${detailInfoModel.weightUnit}</td>
                                <td align="right">${pic.exPort}</td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr class="border-bottom">
                    <td colspan="3" class="text-center">
                        <span class="text-sm">${pic.strDanger}</span>
                        <br/><br/>
                    </td>
                </tr>
                <tr class="border-bottom">
                    <td colspan="3">
                        To:<br/>
                    ${detailInfoModel.rCompanyName} <br/>
                    ${detailInfoModel.rContactName}<br/>
                    ${detailInfoModel.rAddress}<br/>
                    ${detailInfoModel.rAddress2}<br/>
                    ${detailInfoModel.rCity}, ${detailInfoModel.rState}<br/>
                        Ph: ${detailInfoModel.rPhone}
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        From: ${detailInfoModel.sContactName}, ${detailInfoModel.sCompanyName}
                        , ${detailInfoModel.sAddress}, ${detailInfoModel.sAddress2}, ${detailInfoModel.sCity}, ${detailInfoModel.sState}.<br/>
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
                    <td width="50%" style="border-left: 1px solid #000;">
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
            <div style="margin: 15px auto auto auto; width: 100%">
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
                        ${detailInfoModel.rAddress2}<br/>
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
                        ${detailInfoModel.sAddress2} <br/>
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
        </td>
    </tr>
</table>
</#list>
</body>
</html>