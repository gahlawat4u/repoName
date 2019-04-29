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
        <#if isThermalLable == "1">
        body: width:

        30
        %
        ;
        @page {
            size: A2 landscape;
            margin: 10px 20px 10px 20px;
            font-family: Arial;
        }
        <#else>
        @page {
            size: A4 landscape;
        }

        body: width:

        100
        %
        ;
        </#if>

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
<#list listPieceViewAirbill as pic>
<br/>
<table style="height:100%; width:100%; text-align:center;" cellpadding="0" border="0">
    <tr>
        <td style="width:50%;"> &nbsp;</td>
        <td style="width:35px;">&nbsp;</td>
        <td style="width:330px; text-align:right;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="font-size:12px;width:25%; text-align:left;"><b>Toll Priority</b></td>
                                <td style="font-size:11px;width:45%;">Connote No: ${detailInfoModel.connNumber}</td>
                                <td rowspan="2" valign="bottom" style="font-size:17;text-align:right;width:20%;">
                                    <b>${detailInfoModel.depotcCode}</b>&nbsp;&nbsp;&nbsp;</td>
                                <td style="width:5%;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td style="width:25%;">&nbsp;</td>
                                <td colspan="3" align="left" style="font-size:12;width:75%;text-align:left;">Dispatch
                                    Date: ${detailInfoModel.shipmentDate}</td>
                            </tr>
                        </table>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table style="width:100%;" border="1">
                            <tr>
                                <td valign="bottom"
                                    style="width:100%;font-size:12;text-align:center;background-color:black;color:white;">
                                    <b>${detailInfoModel.serviceType}</b></td>
                            </tr>
                        </table>


                    </td>
                </tr>
                <tr>
                    <td style="width:100%;">
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="width:10px;">&nbsp;</td>
                                <td style="font-size:10;width:10%;"><b>TO:</b></td>
                                <td colspan="2" style="font-size:10;width:90%;text-align:left">
                                    <b>${detailInfoModel.rCompanyName}</b></td>
                            </tr>
                            <tr>
                                <td style="width:10px;">&nbsp;</td>
                                <td style="font-size:10;width:10%;">&nbsp;</td>
                                <td colspan="2"
                                    style="font-size:10;width:90%;text-align:left">${detailInfoModel.rAddress}</td>
                            </tr>
                            <tr>
                                <td style="width:10px;">&nbsp;</td>
                                <td style="font-size:10;width:10%;">&nbsp;</td>
                                <td colspan="2"
                                    style="font-size:10;width:90%;text-align:left">${detailInfoModel.rCity}</td>
                            </tr>
                            <tr>

                                <td style="width:10px;">&nbsp;</td>
                                <td colspan="2" style="font-size:10;width:90%;text-align:right">
                                    <b>${detailInfoModel.rState} ${detailInfoModel.rPostalCode}</b></td>
                                <td style="width:10%;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td style="width:5%;">&nbsp;</td>
                                <td style="width:95%;">
                                    <table style="width:100%;" border="0">
                                        <tr>
                                            <td style="font-size:10;width:50%;">Phone: ${detailInfoModel.rPhone}</td>
                                            <td align="left" style="font-size:10;width:50%;text-align:left">
                                                Contact: ${detailInfoModel.rContactName}</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:10px;">&nbsp;</td>
                                <td style="font-size:10;width:10%;">&nbsp;</td>
                                <td colspan="2" style="font-size:10;width:90%;text-align:left">Special
                                    Inst: ${pic.reasonForExport}</td>
                            </tr>
                        </table>

                    </td>
                </tr>
                <tr>
                    <td>
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="width:10px;">&nbsp;</td>
                                <td style="font-size:10px;width:12%;"><b>FROM: </b></td>
                                <td style="font-size:10px;width:45%;text-align:left;">
                                    <label style="font-size:12px;"><b>${detailInfoModel.sCompanyName}</b></label><br/>
                                    <label style="font-size:10px;">${detailInfoModel.sAddress}
                                        <br/> ${detailInfoModel.sCountryName}
                                        <br/></label><br/>${detailInfoModel.sCity} ${detailInfoModel.sPostalCode}<br/>
                                    Phone: ${detailInfoModel.sPhone}
                                </td>
                                <td width="35%" style="font-size:10;">
                                    <br/><br/>Charge Acc: ${detailInfoModel.billingAccount}<br/>
                                    Toll Extra Service: $0.00
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>

                    </td>
                </tr>
                <tr>
                    <td>
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="width:5%;">&nbsp;</td>
                                <td style="font-size:10px;width:95%;text-align:left;">
                                    Description of Goods : ${detailInfoModel.contentDescription}
                                    <br/>Item Ref: ${detailInfoModel.referenceNo}
                                </td>
                            </tr>
                        </table>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="width:5%;">&nbsp;</td>
                                <td style="font-size:10px;width:95%;text-align:left;">
                                    Connote Ref:
                                </td>
                            </tr>
                        </table>
                    </td>

                </tr>
                <tr>
                    <td>
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="width:10%;">&nbsp;</td>
                                <td style="font-size:10px;width:40%;text-align:left;">
                                    Total Weight: ${pic.weight} ${detailInfoModel.weightUnit}<br/>
                                    Total Cube M3: ${pic.dimWeight} ${detailInfoModel.weightUnit}<br/>
                                    Cubic (cm): ${pic.dimession}                            </td>
                                <td style="font-size:10px;width:40%;text-align:right;"><b>Item ${pic.picesCount}
                                    Of ${detailInfoModel.noOfPieces}</b></td>
                                <td style="width:10%;">&nbsp;</td>
                            </tr>
                        </table>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="width:15%;">&nbsp;</td>
                                <td style="width:85%;text-align:left;">
                                    <label style="font-size:12px;">${detailInfoModel.rCompanyName}</label><br/>
                                    <label style="font-size:12px;">${detailInfoModel.rAddress}</label>
                                </td>
                            </tr>
                        </table>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="width:15%;">&nbsp;</td>
                                <td style="width:85%;text-align:left;font-size:12px;">
                                    <b>${detailInfoModel.rCity} ${detailInfoModel.rState} ${detailInfoModel.rPostalCode}</b>
                                </td>
                            </tr>
                        </table>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="width:15%;">&nbsp;</td>
                                <td style="width:45%;text-align:left;font-size:12px;">${detailInfoModel.serviceType}</td>
                                <td style="width:40%;font-size:12px;">Item: ${pic.itemCode}</td>
                            </tr>
                        </table>

                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>
                        <table border="0" style="width:100%;text-align:center;">
                            <tr>
                                <td style="width:100%;"><img width="338px" height="42px" alt="BARCODE"
                                                             src="${pic.imageBarcode}"/></td>
                            </tr>
                        </table>

                    </td>
                </tr>
                <tr>
                    <td>
                        <table border="0" style="width:100%;text-align:center;">
                            <tr>
                                <td style="width:100%;"><img width="338px" height="42px" alt="BARCODE2"
                                                             src="${pic.imageBarcode2}"/></td>
                            </tr>
                        </table>

                    </td>
                </tr>
                <tr>
                    <td>
                        <table style="width:100%;" border="0">
                            <tr>
                                <td style="width:100%;text-align:center;font-size:10px;">${pic.pieceBarcode}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td style="width:12%;">&nbsp;</td>
                                <td style="width:88%;text-align:left;font-size:10px;">Declaration by:</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <br/>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td style="width:10%;">&nbsp;</td>
                                <td style="width:90%;text-align:left;font-size:8px;">
                                    CARRIER'S TERMS AND CONDITIONS APPLY<br/>
                                    WARNING: DANGEROUS GOODS NOT CONSIGNED WITHIN
                                </td>
                            </tr>
                        </table>
                        <br/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

</table>
</#list>
</body>
</html>