<#setting date_format="dd-MM-yyyy">
<html>
<head>
    <style type="text/css">
        @page {
            size: A4 landscape;
            margin: 0mm 0mm 0mm 0mm;
        }

        body {
            font-family: Arial;
            font-size: 8px;
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

        .no-border {
            border: none;
        }

        .border {
            border-collapse: collapse;
        }

        .border tbody tr td {
            border: 1px solid #000;
            padding: 4px;
        }

        .border tr td {
            border: 1px solid #000;
            padding: 4px;
        }

        .page-break {
            page-break-before: always;
        }
    </style>
</head>
<body>
<#if detailInfoModel.noOfPieces?number == 0>
<table style="width: 50%;">
    <tr>
        <td></td>
    </tr>
</table>
<table style="width: 50%; float: right" border="2">
    <tr>
        <td style="border: 2px solid black;">
            <table style="text-align:center;" border="0" class="no-border">
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">
                        <b>Account:</b> ${detailInfoModel.billingAccount}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Sender</b></td>
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
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">${detailInfoModel.sCity}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom"
                        align="left">${detailInfoModel.sPostalCode}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">${detailInfoModel.sState}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom"
                        align="left">${detailInfoModel.sCountryName}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">
                        <b>Contact:</b> ${detailInfoModel.sContactName}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">
                        <b>Tel:</b> ${detailInfoModel.sPhone}</td>
                </tr>
            </table>
        </td>
        <td colspan="2" style="border: 2px solid black;">
            <table style="text-align:center;" border="0" cellspacing="0" cellpadding="2" class="no-border">
                <tr>
                    <td colspan="4" valign="bottom" align="center">
                        <table>
                            <tr>
                                <td><img width="140px" src="${tntImage}" alt="TNT"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="bottom" style="text-align:center;" align="center">
                        <table>
                            <tr>
                                <td><img width="240px" height="80px" src="${imgVoied}" alt="TNT barcode"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="font-size: 7pt;text-align:center;" valign="bottom"><b>*${detailInfoModel.tracking}*</b>
                    </td>
                </tr>
                <tr>
                    <td style="font-size: 7pt;text-align:center;" valign="bottom"><b>Customer
                        Reference:</b> ${detailInfoModel.referenceNo}</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td style="border: 2px solid black;">
            <table style="text-align:center;" border="0" class="no-border">
                <tr>
                    <td colspan="2" style="font-size: 8pt; text-align:left;" valign="bottom"><b>Delivery Address</b>
                    </td>
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
                    <td colspan="2" style="font-size: 8pt; text-align:left;" valign="bottom">
                        <b>Contact:</b> ${detailInfoModel.rContactName} </td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt; text-align:left;" valign="bottom">
                        <b>Tel:</b> ${detailInfoModel.rPhone}</td>
                </tr>
            </table>
        </td>
        <td colspan="2" style="border: 2px solid black;">
            <table style="text-align:center;" border="0" class="no-border">
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Shipment
                        Date:</b> ${detailInfoModel.shipmentDate}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Description of Goods</b>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom"
                        align="left">${detailInfoModel.contentDescription}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Dimensions: </b> (${detailInfoModel.dimensionUnit})</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td style="border: 2px solid black; font-size: 8pt;" colspan="2"><b>Special Delivery
            Instructions:</b> ${detailInfoModel.specialDeliveryinst}</td>
        <td style="border: 2px solid black;"><#if detailInfoModel.courierMessage == ""> NON DANGEROUS GOODS</#if></td>
    </tr>
    <tr>
        <td style="border: 2px solid black; width:30%">
            <table style="height:100%; width:100%; text-align:center;" border="0" class="no-border">
                <tr>
                    <td style="font-size: 8pt;" valign="bottom" align="left">
                        <b>Account:</b> ${detailInfoModel.billingAccount}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Service and Options</b></td>
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
        <td style="border: 2px solid black; width:30%">
            <table style="height:100%; width:100%; text-align:center;" class="no-border">
                <tr>
                    <td style="font-size: 8pt;" valign="bottom" align="left"><b>No of Items</b></td>
                </tr>
                <tr>
                    <td style="font-size: 8pt;" height="30" valign="top" align="left">1
                        Of ${detailInfoModel.noOfPieces}</td>
                </tr>
                <tr>
                    <td style="font-size: 8pt;" valign="bottom" align="left"><b>Item Weight</b></td>
                </tr>
                <tr>
                    <td style="font-size: 8pt;" valign="bottom" align="left">0.00 ${detailInfoModel.weightUnit}(s)</td>
                </tr>
            </table>
        </td>
        <td style="border: 2px solid black; font-size: 8pt; width:40%">TNT'S LIABLITIES FOR LOSS DAMAGE AND DELAY IS
            LIMITED BY THE CMR CONVENTION OR THE WARSAW CONVENTION WHICHEVER IS APPLICAPLE THE SENDER AGREES THAT THE
            GENERAL CONDITIONS ACCESABLE AT HTTP//:CONNECYION.TNT.COM:81/TERMSANDCONDITIONS.HTML ARE ACCEPTABLE AND
            GOVERN THIS CONTRACT.IF NO SERVICES OR BILLING OPTIONS ARE SELECTED.THE FASTEST AVAILABLE SERVICE WILL BE
            CHARGED TO THE SENDER.
        </td>
    </tr>
</table>
<#else>
    <#list detailPieces as pic>
        <#if pic?index != 0>
        <div class="page-break"></div>
        </#if>
    <table style="width: 50%;">
        <tr>
            <td></td>
        </tr>
    </table>
    <table style="width: 50%; float: right" border="2">
        <tr>
            <td style="border: 2px solid black;">
                <table style="text-align:center;" border="0" class="no-border">
                    <tr>
                        <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">
                            <b>Account:</b> <#if detailInfoModel.bound?number == 0>${detailInfoModel.billingAccount}</#if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Sender</b></td>
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
                        <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">
                            <b>Contact:</b> ${detailInfoModel.sContactName}</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">
                            <b>Tel:</b> ${detailInfoModel.sPhone}</td>
                    </tr>
                </table>
            </td>
            <td colspan="2" style="border: 2px solid black;">
                <table style="text-align:center;" border="0" cellspacing="0" cellpadding="2" class="no-border">
                    <tr>
                        <td colspan="4" valign="bottom" align="center">
                            <table>
                                <tr>
                                    <td><img width="140px" src="${tntImage}" alt="TNT"/></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td valign="bottom" style="text-align:center;" align="center">
                            <table>
                                <tr>
                                    <td><img width="240px" height="80px" src="${imgVoied}" alt="TNT barcode"/></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td style="font-size: 7pt;text-align:center;" valign="bottom"><b>*${detailInfoModel.tracking}
                            *</b></td>
                    </tr>
                    <tr>
                        <td style="font-size: 7pt;text-align:center;" valign="bottom"><b>Customer
                            Reference:</b> ${detailInfoModel.referenceNo}</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td style="border: 2px solid black;">
                <table style="text-align:center;" border="0" class="no-border">
                    <tr>
                        <td colspan="2" style="font-size: 8pt; text-align:left;" valign="bottom"><b>Delivery Address</b>
                        </td>
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
                        <td colspan="2" style="font-size: 8pt; text-align:left;" valign="bottom">
                            <b>Contact:</b> ${detailInfoModel.rContactName} </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 8pt; text-align:left;" valign="bottom">
                            <b>Tel:</b> ${detailInfoModel.rPhone}</td>
                    </tr>
                </table>
            </td>
            <td colspan="2" style="border: 2px solid black;">
                <table style="text-align:center;" border="0" class="no-border">
                    <tr>
                        <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Shipment
                            Date:</b> ${detailInfoModel.shipmentDate}</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Description of Goods</b>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 8pt;" valign="bottom"
                            align="left">${detailInfoModel.contentDescription}</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left">
                            <b>Dimensions: </b>${pic.dimension}
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td style="border: 2px solid black; font-size: 8pt;" colspan="2"><b>Special Delivery
                Instructions:</b> ${detailInfoModel.specialDeliveryinst}</td>
            <td style="border: 2px solid black;"><#if detailInfoModel.courierMessage == ""> NON DANGEROUS
                GOODS</#if></td>
        </tr>
        <tr>
            <td style="border: 2px solid black; width:30%">
                <table style="height:100%; width:100%; text-align:center;" border="0" class="no-border">
                    <tr>
                        <td style="font-size: 10pt;" valign="bottom" align="left">
                            <b>Account:</b> ${detailInfoModel.billingAccount}</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="font-size: 8pt;" valign="bottom" align="left"><b>Service and Options</b>
                        </td>
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
            <td style="border: 2px solid black; width:30%">
                <table style="height:100%; width:100%; text-align:center;" class="no-border">
                    <tr>
                        <td style="font-size: 8pt;" valign="bottom" align="left"><b>No of Items</b></td>
                    </tr>
                    <tr>
                        <td style="font-size: 8pt;" height="30" valign="top" align="left">${pic?index + 1}
                            Of ${detailInfoModel.noOfPieces}</td>
                    </tr>
                    <tr>
                        <td style="font-size: 8pt;" valign="bottom" align="left"><b>Item Weight</b></td>
                    </tr>
                    <tr>
                        <td style="font-size: 8pt;" valign="bottom" align="left">${pic.deadWeight}</td>
                    </tr>

                </table>
            </td>
            <td style="border: 2px solid black; font-size: 8pt; width:40%">TNT'S LIABLITIES FOR LOSS DAMAGE AND DELAY IS
                LIMITED BY THE CMR CONVENTION OR THE WARSAW CONVENTION WHICHEVER IS APPLICAPLE THE SENDER AGREES THAT
                THE GENERAL CONDITIONS ACCESABLE AT HTTP//:CONNECYION.TNT.COM:81/TERMSANDCONDITIONS.HTML ARE ACCEPTABLE
                AND GOVERN THIS CONTRACT.IF NO SERVICES OR BILLING OPTIONS ARE SELECTED.THE FASTEST AVAILABLE SERVICE
                WILL BE CHARGED TO THE SENDER.
            </td>
        </tr>
    </table>
    </#list>
</#if>
</body>
</html>