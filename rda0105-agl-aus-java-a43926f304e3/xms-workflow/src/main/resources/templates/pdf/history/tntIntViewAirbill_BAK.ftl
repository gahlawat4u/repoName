<#setting date_format="dd-MM-yyyy">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>
        View airbill
    </title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

</head>
<body style="font-family: Arial; font-size: 12px;">
<#list pieces as pic>
<br/>
<br/>
<br/>
<table style="height:80%; width:100%; text-align:center;" border="0">
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
                                <td colspan="2" style="width:200px;height:150px;" align="center">
                                    <table style="height:100%; width:100%; text-align:center;" border="0"
                                           cellspacing="0" cellpadding="2">
                                        <tr>
                                            <td colspan="4" valign="bottom"
                                                style="width:170px;height:50px;text-align:center;">
                                                <table>
                                                    <tr>
                                                        <td><img width="240px" height="60px" src="${tntImage}"
                                                                 alt="TNT"/></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="width:25px;">&nbsp;</td>
                                            <td valign="bottom" style="width:130px;height:30px;text-align:center;"
                                                align="center">
                                                <table>
                                                    <tr>
                                                        <td><img width="300px" height="80px" src="${imgVoied}"
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
                                <td height="100" colspan="2" valign="top">
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
                                            <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Dimensions: </b>${pic.dimensionL}
                                                x ${pic.dimensionW} x ${pic.dimensionH}(cm)
                                            </td>
                                        </tr>
                                    </table>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" style="font-size: 8pt;" height="20" align="left"><b>Special Delivery
                                    Instructions:</b> ${detailInfoModel.reasonForExport}</td>
                                <td colspan="2" align="left"
                                    style="font-size: 8pt;"><#if detailInfoModel.courierMessage != ""> NON DANGEROUS
                                    GOODS</#if> </td>
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
                                            <td style="font-size: 8pt;" height="30" valign="bottom" align="left">1
                                                Of ${detailInfoModel.noOfPieces}</td>
                                        </tr>
                                        <tr>
                                            <td style="font-size: 8pt;" valign="bottom" align="left"><b>Item Weight</b>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="font-size: 8pt;" valign="bottom" align="left">${pic.weight}Kg
                                            </td>
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
</#list>
</body>
</html>