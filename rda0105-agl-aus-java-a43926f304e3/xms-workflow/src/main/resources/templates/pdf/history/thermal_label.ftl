<html>
<head>

</head>
<body>
<table height="100%" width="100%" align="center" cellpadding="0" border="0">
    <tr>
        <td width="95px" height="5px"></td>
    </tr>
    <tr>
        <td width="95px">
            <table align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="50%" align="left"><b><font size="3">* ARCHIVE DOC *</font></b><BR/>
                        <font size="2">Not to be attached to package</font>
                    </td>
                    <td align="center" width="20%" height="3px" style="border-top-style:0;"><font
                            size="5"><b>"${shipmentDetail.awbProductContentCode}"</b></font></td>
                    <td align="center" width="30%">${dhllogo}</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td width="95px"  ${bortop} >
            <table align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="80px">
                        <table align="center" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="8px">From:</td>
                                <td width="62" align="left">
                                    <font size="2"> ${shipmentDetail.sender.contactName}<BR/>
                                    ${shipmentDetail.sender.contactName}<BR/>
                                    ${shipmentDetail.sender.address}"</font>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="30px" align="left">
                        Origin:<br/><font size="5"><b>"${shipmentDetail.sender.phone}</b></font>
                    </td>
                    <td width="2px"></td>
                </tr>
                <tr>
                    <td width="66px"></td>
                    <td width="25px" align="left"><font size="2">Ph:"${shipmentDetail.sender.phone}"</font></td>
                    <td width="5px"></td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="8px"></td>
                                <td width="62" align="left">
                                    <font size="2">"${shipmentDetail.sender.city}
                                    ${shipmentDetail.sender.postalCode}<BR/>
                                    ${shipmentDetail.sCountry.countryName}
                                    </font>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="5px" align="right"></td>
                    <td width="15px"></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td width="100px"  ${bortop}>
            <table align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="65px">
                        <table align="center" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="8px">To:</td>
                                <td width="62" align="left">
                                    <font size="2">"${shipmentDetail.receiver.companyName} <br/>
                                    ${shipmentDetail.receiver.contactName}<br/>
                                    ${shipmentDetail.receiver.address}</font>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="25px" align="left">
                        <font size="2">Contact:</font><BR/>Ph:"${shipmentDetail.receiver.phone}
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td width="50%"></td>
                    <td width="30%" align="center"></td>
                    <td width="20%"></td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="8px"></td>
                                <td width="87" align="left">
                                    <b><font size="3">${shipmentDetail.receiver.city} ,
                                    ${shipmentDetail.receiver.postalCode}<BR/>
                                    ${shipmentDetail.rCountry.countryName}</font></b>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="5px" align="right"></td>
                    <td width="15px"></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td height="5px" width="95px"  ${bortop}  align="center">
            <table align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="20%" align="left"><font size="8"><B>${OutboundSortCode}</B></font></td>
                    <td width="60%"><font size="8"><B>"${shipmentDetail.receiver.country}
                        -${shipmentDetail.serviceAreaCodeDestination}
                        -${shipmentDetail.serviceAreaCodeDestination}</B></font></td>
                    <td width="20%" align="right"><font size="8"><B>${InboundSortCode}</B></font></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td width="95px"  ${bortop}  align="center">
            <table align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="50px" align="left">Product:</td>
                    <td width="50px" align="left">
                        Features/Services:"${shipmentDetail.termOfTrade} ${shipmentDetail.internalServiceCode}</td>
                </tr>
                <tr>
                    <td colspan="3" align="left">
                        "${shipmentDetail.globalProductCode}  ${shipmentDetail.shipmentType.shipmentTypeName}</td>
                </tr>
                <tr>
                    <td width="20px" align="left">Payment Code</td>
                    <td colspan="2" align="left">:${shipmentDetail.billingAccount}</td>
                </tr>
                <tr>
                    <td width="20x" align="left">FRT A/C</td>
                    <td colspan="2" align="left">:"${frtAccount}</td>
                </tr>
                <tr>
                    <td width="20px" align="left">DTP A/C</td>
                    <td colspan="2" align="left">:${shipmentDetail.dutiesAccount}</td>
                </tr>
                <tr>
                    <td width="20px" align="left">Terms of Trade</td>
                    <td colspan="2" align="left">:${shipmentDetail.termOfTrade}</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td width="95px"  ${bortop}  align="center">
            <table align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="45%" align="left">Ref :<font size="7">${shipmentDetail.reference}</font></td>
                    <td width="40%" height="6px" align="left"> Shpt Wght : <font
                            size="5"><B>${totalweight}  ${shipmentDetail.weightUnit}</B></font></td>
                    <td width="15%" align="left"># of Pieces <BR/>;
                        <font size="3"><B>${shipmentDetail.noOfPieces}</B></font>
                    </td>
                </tr>
                <tr>
                    <td align="left">Custom Val:
                        <font size="7">${shipmentDetail.totalCustomValue}${shipmentDetail.currencyCode}</font>
                    </td>
                    <td width="40%" align="left">Shipment Date : <font size="2">${shipmentDetail.shipmentDate}</font>
                    </td>
                    <td width="15%" align="right" valign="top"></td>
                </tr>
                <tr>
                    <td colspan="3" align="left">Insured Amount :
                        <font size="7"> ${shipmentDetail.withInsurance} ${shipmentDetail.currencyCode}</font>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" width="45%" align="left">
                        Account No : <font size="12"><B>${shipmentDetail.billingAccount}</B> </font>
                    </td>
                </tr>
            ${securityInspection}
            </table>
        </td>
    </tr>
    <tr>
        <td width="95px"  ${bortop}  align="center">
            <table align="center" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="100%" height="2px" align="left"></td>
                </tr>
                <tr>
                    <td width="5%" align="left"></td>
                    <td width="60%" height="7px" align="center">
                    ${barCode}
                    </td>
                    <td width="10%" align="left"></td>
                    <td width="20%" align="left">Contents: <font size="8">${shipmentDetail.contentDescription}</font>
                    </td>
                </tr>
                <tr>
                    <td width="5%" align="left"></td>
                    <td width="60%" align="center">WAYBILL ${shipmentDetail.airbillNumber}</td>
                    <td width="35%" align="left"></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td width="95px" align="center"></td>
    </tr>
    <tr>
        <td width="55%" align="right">-Page 1 of 1-</td>
    </tr>
</table>
</body>
</html>