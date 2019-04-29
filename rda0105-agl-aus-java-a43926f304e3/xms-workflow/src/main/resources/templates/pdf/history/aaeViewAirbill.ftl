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
        @page {
            size: 14cm 20cm;
            margin: 10px 20px 10px 20px;
            font-family: Arial;
        }
        <#else>
        size: A4 landscape

        ;
        </#if>
    </style>
</head>
<body style="font-family: Arial; font-size: 12px;">
<#if listPieceViewAirbill?has_content>
    <#list listPieceViewAirbill as pic>
    <br/>
    <br/>

    <table height="100%" width="100%" align="center" cellpadding="5" border="0">

        <tr>
            <td width="50%">&nbsp;</td>

            <td width="15px">&nbsp;</td>

            <td width="380px"
                style="border-top-style: 0; border-bottom-style: 0; border-right-style: 0; border-left-style: 0;">

                <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">

                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td align="left" width="130px"><br/>
                            <label stype="font-size:16px;">
                                C/N: ${detailInfoModel.tracking}<br/>A/N: ${pic.articleId}
                            </label></td>

                        <td width="75px"><br/>

                            <table align="center" border="1" cellspacing="0" cellpadding="0">

                                <tr>
                                    <td style="border-top-style: 0; border-bottom-style: 0; border-right-style: 0; border-left-style: 0;"
                                        align="center" width="50px" height="30px"><label style="font-size: 17px;">
                                        <b>AAE</b>
                                    </label></td>

                                </tr>

                            </table>
                        </td>

                        <td width="150px" align="right">
                            <#if detailInfoModel.dhlRoutingCode=="1" >
                                <table width="130px" align="right" border="0" cellspacing="0" cellpadding="0">

                                    <tr>
                                        <td align="right">ATL &nbsp; </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <table>
                                                <tr>
                                                    <td><img width="130px" height="15px" src="${pic.imageBarcode3}"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="center">${pic.pieceBarcode3}</td>
                                    </tr>

                                </table>
                            </#if>
                        </td>
                    </tr>

                </table>

            </td>
        </tr>

        <tr>
            <td width="50%">&nbsp;</td>

            <td width="15px">&nbsp;</td>

            <td align="left" width="380px"
                style="border-top-style: 0; border-bottom-style: 0; border-right-style: 0; border-left-style: 0;">

                <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">

                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td width="100%" align="left"><label style="font-size: 12px;">
                            <b>SHIPPER COMPANY NAME</b><br/>${detailInfoModel.sCompanyName}
                            <br/>${detailInfoModel.sContactName}<br/> ${detailInfoModel.sAddress}
                            <br/>${detailInfoModel.sCity}, ${detailInfoModel.sPostalCode}
                        </label></td>
                    </tr>

                </table>

            </td>
        </tr>

        <tr>
            <td width="50%">&nbsp;</td>

            <td width="15px">&nbsp;</td>

            <td align="left" width="380px"
                style="border-top-style: 0; border-bottom-style: 0; border-right-style: 0; border-left-style: 0;">

                <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">

                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td width="380px" align="left"><label style="font-size: 12px;"> AUSTRALIAN AIR EXPRESS DELIVER
                            TO :</label> <br/> <br/>
                            <label style="font-size: 10px;">
                            ${detailInfoModel.rCompanyName} <br/> ${detailInfoModel.rContactName}
                                <br/> ${detailInfoModel.rAddress}

                            </label></td>
                    </tr>

                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td align="left">

                            <table width="100%" border="0" cellspacing="0" cellpadding="0">

                                <tr>
                                    <td align="left" width="250"><label style="font-size: 18px;">
                                        <B>${detailInfoModel.rCity}</B>
                                    </label></td>

                                    <td align="right" width="100"><label style="font-size: 18px;">
                                        <B>${detailInfoModel.rPostalCode}</B>
                                    </label></td>

                                </tr>

                            </table>

                        </td>
                    </tr>

                </table>

            </td>
        </tr>

        <tr>
            <td width="50%">&nbsp;</td>

            <td width="15px">&nbsp;</td>

            <td align="left" width="380px"
                style="border-top-style: 0; border-bottom-style: 0; border-right-style: 0; border-left-style: 0;">

                <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">

                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td width="380px" align="left"><label style="font-size: 8px;">
                            <b>DELIVERY INSTRUCTIONS:</b>
                        </label> <br/>
                            <label style="font-size: 10px;"> DELIVERY TO RECEPTION, AFTER HOURS CALL 95554545 </label>
                        </td>
                    </tr>

                </table>

            </td>
        </tr>

        <tr>
            <td width="50%">&nbsp;</td>

            <td width="15px">&nbsp;</td>

            <td align="left" width="380px"
                style="border-top-style: 0; border-bottom-style: 0; border-right-style: 0; border-left-style: 0;">

                <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td width="380px" align="left">

                            <table width="100%" border="0" cellspacing="0" cellpadding="0">

                                <tr>
                                    <td align="left" width="250"><label style="font-size: 10px;">
                                        <B>DESCRIPTION: </B> <br/>${detailInfoModel.packageType} DETAIL
                                    </label></td>

                                    <td align="left" width="100"><label style="font-size: 10px;">
                                        <B>REFERENCE</B><br/>${detailInfoModel.referenceNo}
                                    </label></td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                </table>

            </td>
        </tr>

        <tr>
            <td width="50%">&nbsp;</td>

            <td width="15px">&nbsp;</td>

            <td align="left" width="380px"
                style="border-top-style: 0; border-bottom-style: 0; border-right-style: 0; border-left-style: 0;">

                <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">

                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td align="left" width="380px" height="35px">

                            <table width="100%" border="0" cellspacing="0" cellpadding="0">

                                <tr>
                                    <td align="left"><label style="font-size: 28px;">
                                        <B>${detailInfoModel.primaryPort}</B>
                                    </label></td>

                                    <td align="left"><label style="font-size: 28px;">
                                        <B>${detailInfoModel.secondaryPort}</B>
                                    </label></td>

                                    <td align="left"><label style="font-size: 28px;">
                                        <B>${detailInfoModel.productContentCode}</B>
                                    </label></td>

                                </tr>

                            </table>

                        </td>
                    </tr>

                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td align="left">

                            <table width="100%" border="0" cellspacing="0" cellpadding="0">

                                <tr>
                                    <td align="left" width="100"><label style="font-size: 9px;">

                                    ${detailInfoModel.shipmentDate} <br/>TOTAL: ${pic.picesCount}
                                        Of ${detailInfoModel.noOfPieces}
                                        <br/>WEIGHT: ${pic.weight} ${detailInfoModel.weightUnit} <br/>
                                        <br/> REPRINT
                                    </label></td>

                                    <td align="right" width="250">

                                        <table align="left" border="0" cellspacing="0" cellpadding="0">

                                            <tr>
                                                <td align="center">
                                                    <table>
                                                        <tr>
                                                            <td><img width="200px" height="60px"
                                                                     src="${pic.imageBarcode}"/></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td align="center"><label
                                                        style="font-size: 11px;">${pic.pieceBarcode}</label></td>
                                            </tr>

                                            <tr>
                                                <td align="center">&nbsp;</td>
                                            </tr>

                                        </table>

                                    </td>

                                </tr>

                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td align="left">

                            <table width="100%" border="0" cellspacing="0" cellpadding="0">

                                <tr>

                                    <td align="left" width="350"><br/>

                                        <table align="left" border="0" cellspacing="0" cellpadding="0">

                                            <tr>
                                                <td align="center">
                                                    <table>
                                                        <tr>
                                                            <td><img width="250px" height="75px"
                                                                     src="${pic.imageBarcode}"/></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td align="center"><label
                                                        style="font-size: 11px;">${pic.pieceBarcode2}</label></td>
                                            </tr>

                                        </table>
                                    </td>

                                </tr>

                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td width="15px">&nbsp;</td>

                        <td align="left">&nbsp;</td>
                    </tr>

                </table>

            </td>
        </tr>

    </table>
    </#list>
<#else>
<br/>
<br/>
<br/>
<table style="height:80%; width:100%; text-align:center;" border="1">
    <tr>
        <td></td>
        <td>
            <table style="height:80%; width:50%; text-align:center;" border="0" cellspacing="0" cellpadding="3">

                <tr>
                    <td style="width:25px;">&nbsp;</td>
                    <td style="width:350px;">
                        <table border="1" style="height:100%; width:100%; text-align:center;" cellspacing="0"
                               cellpadding="2">
                            <tr>
                                <td style="width:150px;height:150px;">
                                    <table style="height:100%; width:100%; text-align:center;" border="0">
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Account:</b> ${detailInfoModel.billingAccount}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Sender</b>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom"
                                                align="left">${detailInfoModel.sCompanyName}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom"
                                                align="left">${detailInfoModel.sAddress}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom"
                                                align="left">${detailInfoModel.sCity}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom"
                                                align="left">${detailInfoModel.sPostalCode}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom"
                                                align="left">${detailInfoModel.sState}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom"
                                                align="left">${detailInfoModel.sCountryName}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Contact:</b> ${detailInfoModel.sContactName}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">
                                                <b>Tel:</b> ${detailInfoModel.sPhone}</td>
                                        </tr>
                                    </table>
                                </td>
                                <td colspan="2" style="width:200px;height:150px;">
                                    <table style="height:100%; width:100%; text-align:center;" border="0"
                                           cellspacing="0" cellpadding="2">
                                        <tr>
                                            <td colspan="4" valign="bottom"
                                                style="width:170px;height:50px;text-align:center;">
                                                <table>
                                                    <tr>
                                                        <td><img width="240px" height="60px" src="${tntBarcode}"
                                                                 alt="TNT"/></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="width:25px;">&nbsp;</td>
                                            <td valign="bottom" style="width:130px;height:30px;text-align:center;">
                                                <table>
                                                    <tr>
                                                        <td><img width="300px" height="80px" src="${pieceBarcode}"
                                                                 alt="TNT barcode"/></td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td width="25px">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" width="25px">&nbsp;</td>
                                            <td style="font-size: 7pt;width:130px;text-align:center;" valign="bottom">
                                                <b>*${detailInfoModel.tracking}*</b></td>
                                            <td width="25px">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" width="25px">&nbsp;</td>
                                            <td style="font-size: 7pt;width:130px;text-align:center;" valign="bottom">
                                                <b>Customer Reference:</b> ${detailInfoModel.referenceNo}</td>
                                            <td style="width:25px;">&nbsp;</td>
                                        </tr>
                                    </table>

                                </td>
                            </tr>
                            <tr>
                                <td style="height:100px; width:150px;">
                                    <table style="height:100%; width:100%; text-align:center;" border="0">
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt; text-align:left;" valign="bottom"><b>Delivery
                                                Address</b></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt; text-align:left; "
                                                valign="bottom">${detailInfoModel.rCompanyName}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt; text-align:left;"
                                                valign="bottom">${detailInfoModel.rAddress}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt; text-align:left;"
                                                valign="bottom">${detailInfoModel.rCity}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt; text-align:left;"
                                                valign="bottom">${detailInfoModel.rPostalCode}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt; text-align:left;"
                                                valign="bottom">${detailInfoModel.rState}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt; text-align:left;"
                                                valign="bottom">${detailInfoModel.rCountryName}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt; text-align:left;" valign="bottom"><b>Contact:</b> ${detailInfoModel.rContactName}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt; text-align:left;" valign="bottom"><b>Tel:</b> ${detailInfoModel.rPhone}
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td height="100" colspan="2">
                                    <table style="height:100%; width:100%; text-align:center;" border="0">
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Shipment
                                                Date:</b> ${detailInfoModel.shipmentDate}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Description
                                                of Goods</b></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">test
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Dimensions: </b>
                                            </td>
                                        </tr>
                                    </table>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="font-size: 8pt;" height="20" align="left"><b>Special Delivery
                                    Instructions:</b></td>
                                <td colspan="3" align="left" style="font-size: 8pt;">NON DANGEROUS GOODS</td>
                            </tr>
                            <tr>
                                <td height="100">
                                    <table style="height:100%; width:100%; text-align:center;" border="0">
                                        <tr>
                                            <td style="font-size: 8pt;" valign="bottom" align="left">
                                                <b>Account:</b> ${detailInfoModel.billingAccount}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Service
                                                and Options</b></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom"
                                                align="left">${detailInfoModel.serviceType}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"></td>
                                        </tr>
                                    </table>
                                </td>
                                <td height="150" width="55">
                                    <table style="height:100%; width:100%; text-align:center;">
                                        <tr>
                                            <td style="font-size: 8pt;" valign="bottom" align="left"><b>No of Items</b>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-size: 8pt;" height="30" valign="bottom" align="left">
                                                Of ${detailInfoModel.noOfPieces}</td>
                                        </tr>
                                        <tr>
                                            <td style="font-size: 8pt;" valign="bottom" align="left"><b>Item Weight</b>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-size: 8pt;" valign="bottom"
                                                align="left"> ${detailInfoModel.weightUnit}</td>
                                        </tr>
                                    </table>

                                </td>
                                <td width="145" align="left" style="font-size: 8pt;">TNT'S LIABLITIES FOR LOSS DAMAGE
                                    AND DELAY IS LIMITED BY THE CMR CONVENTION OR THE WARSAW CONVENTION WHICHEVER IS
                                    APPLICAPLE THE SENDER AGREES THAT THE GENERAL CONDITIONS ACCESABLE AT
                                    HTTP//:CONNECYION.TNT.COM:81/TERMSANDCONDITIONS.HTML ARE ACCEPTABLE AND GOVERN THIS
                                    CONTRACT.IF NO SERVICES OR BILLING OPTIONS ARE SELECTED.THE FASTEST AVAILABLE
                                    SERVICE WILL BE CHARGED TO THE SENDER.
                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</#if>
</body>
</html>