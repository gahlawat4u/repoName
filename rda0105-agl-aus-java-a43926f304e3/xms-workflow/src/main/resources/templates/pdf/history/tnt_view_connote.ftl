<!DOCTYPE html[
        <!ENTITY nbsp "&#160;">
]>
<html>
<head>
    <style type="text/css">
        @page {
            size: 297mm 210mm;
            margin: 10mm 0mm 0mm 0mm;
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

        hr {
            margin-top: 0px;
            border: 0.5px solid;
        }
    </style>
</head>
<body>
<table style="width: 50%;">
    <tr>
        <td></td>
    </tr>
</table>
<table style="width: 50%; float: right" border="2">
    <tr>
        <td style="border: 2px solid black; width: 50%; vertical-align: top;">
            <table style="text-align: center;">
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        <b>${(lang.translate("1. From (Collection Address)"))!"1. From (Collection Address)"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Sender's Account No.:"))!"Sender's Account No.:"} <#if connoteData.shipment.boundStatus?number == 0> ${connoteData.tntAccount} </#if></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Name:"))!"Name:"} ${(connoteData.senderAddress.companyName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Address:"))!"Address:"} ${(connoteData.senderAddress.address?html)!" "} <#if (connoteData.senderAddress.address2s)??>
                        ,${(connoteData.senderAddress.address2?html)!" "}</#if></td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("City:"))!"City:"} ${(connoteData.senderAddress.city?html)!" "}</td>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Postal/Zip Code:"))!"Postal/Zip Code:"} ${(connoteData.senderAddress.postalCode)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Province:"))!"Province:"} ${(connoteData.senderAddress.state)!" "}
                        Country: ${(connoteData.senderAddress.countryDetail.countryName)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Contact Name:"))!"Contact Name:"} ${(connoteData.senderAddress.contactName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Tel No.:"))!"Tel No.:"} ${(connoteData.senderAddress.phone?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        <b>${(lang.translate("2. To (Receiver Address)"))!"2. To (Receiver Address)"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom" align="left">
                    ${(connoteData.systemAddress?html?replace("\n", "<br/>"))!" "}
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        <b>${(lang.translate("3. Goods"))!"3. Goods"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("General Description:"))!"General Description:"}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(connoteData.shipment.contentDescription?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom" align="left">
                        <table style="font-size: 7pt;">
                            <tbody>
                            <td>
                            ${(lang.translate("Total Packages:"))!"Total Packages:"}<br/>
                            ${(connoteData.shipment.noOfPieces)!"0"}
                            </td>
                            <td>
                            ${(lang.translate("Total Weight:"))!"Total Weight:"} <br/>
                            ${(connoteData.totalWeight)!"0"}${(connoteData.shipment.weightUnit?lower_case)!"kg"}
                            </td>
                            <td>
                            ${(lang.translate("Total Volume:"))!"Total Volume:"}<br/>
                            ${(connoteData.totalVolume)!"0"}m3
                            </td>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        <b>${(lang.translate("4. Services"))!"4. Services"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Service:"))!"Service:"} ${(connoteData.shipmentType.shipmentTypeName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Options:"))!"Options:"}</td>
                </tr>
                <tr>
                    <td colspan="4" valign="bottom" align="right">
							<span style="font-size: 10pt;">
                            <#if connoteData.shipment.boundStatus?number == 0>
                                Sender Pays
                            <#else>
                                Receiver Pays
                            </#if>
                            </span>
                        <br/>
                    <#if connoteData.shipment.courierMessage?trim != "DG">
                        <span style="font-size: 7pt;">NON DANGEROUS GOODS</span>
                    </#if>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Marine Extended Warranty Currency:"))!"Marine Extended Warranty Currency:"} ${(connoteData.shipment.currencyCode?html)!"AUD"}</td>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Value:"))!"Value:"} <#if connoteData.shipment.withInsurance == "0.00">
                        0.00 <#else>${(connoteData.shipment.totalCustomValue)!"0.00"}</#if></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        &nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Sender's Signature:"))!"Sender's Signature:"}</td>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom" align="left">&nbsp;</td>
                </tr>
                <tr>
                    <td style="font-size: 7pt; width: 20%" valign="bottom"
                        align="left">${(lang.translate("Date:"))!"Date:"}</td>
                    <td style="font-size: 7pt; width: 30%" valign="bottom" align="left">
                        <table style="font-size: 7pt;">
                            <tr>
                                <td style="width: 20%;border-bottom: solid 1px">&nbsp;</td>
                                <td style="border-bottom: solid 1px">/</td>
                                <td style="width: 20%;border-bottom: solid 1px">&nbsp;</td>
                                <td style="border-bottom: solid 1px">/</td>
                                <td style="width: 40%;border-bottom: solid 1px">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                    <td colspan="2" style="font-size: 7pt; width: 50%" valign="bottom" align="left">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom" align="left">
                    ${(lang.translate("<br/>LIABILITY FOR LOSS,DAMAGE AND DELAY IS LIMITED BY THE CMR CONVENTION OR THE WARSAW CONVENTION WHICHEVER IS APPLICABLE.THE SENDER AGREES THAT THE GENERAL CONDITIONS,ACCESSIBLE VIA THE HELP TEXT THAT ARE ACCEPTABLE AND GOVERN THIS CONTRACT.IF NO SERVICE OR BILLING OPTION IS SELECTED THE FASTEST AVAILABLE SERVICE WILL BE CHARGED TO THE SENDER"))!"<br/>LIABILITY FOR LOSS,DAMAGE AND DELAY IS LIMITED BY THE CMR CONVENTION OR THE WARSAW CONVENTION WHICHEVER IS APPLICABLE.THE SENDER AGREES THAT THE GENERAL CONDITIONS,ACCESSIBLE VIA THE HELP TEXT THAT ARE ACCEPTABLE AND GOVERN THIS CONTRACT.IF NO SERVICE OR BILLING OPTION IS SELECTED THE FASTEST AVAILABLE SERVICE WILL BE CHARGED TO THE SENDER"}
                    </td>
                </tr>
            </table>
        </td>
        <td colspan="2" style="border: 2px solid black; width: 50%;">
            <table border="0" cellspacing="0" cellpadding="2" class="no-border">
                <tr>
                    <td colspan="4" valign="bottom" align="center">
                        <table>
                            <tr>
                                <td align="center"><img width="140px" src="${(connoteData.logo)!" "}" alt="TNT"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" valign="bottom" style="text-align:center;" align="center">
                        <table>
                            <tr>
                                <td align="center"><img width="240px" height="80px" src="${(connoteData.barCode)!" "}"
                                                        alt="TNT barcode"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:center;" valign="bottom">
                        <b>*${(connoteData.shipment.airbillNumber?html)!" "}*</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:center;"
                        valign="bottom">${(lang.translate("Please quote this number if you have an enquiry."))!"Please quote this number if you have an enquiry."}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: solid 1px" valign="bottom" align="left"><b>A.
                        Delivery Address</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Name:"))!"Name:"} ${(connoteData.receiverAddress.companyName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Address:"))!"Address:"} ${(connoteData.receiverAddress.address?html)!" "} <#if (connoteData.receiverAddress.address2)?? && connoteData.receiverAddress.address2?trim != "">${(connoteData.receiverAddress.address2?html)!" "}</#if></td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("City:"))!"City:"} ${(connoteData.receiverAddress.city?html)!" "}</td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Postal/Zip Code:"))!"Postal/Zip Code:"} ${(connoteData.receiverAddress.postalCode?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Province:"))!"Province:"} ${(connoteData.receiverAddress.state?html)!" "}</td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Country:"))!"Country:"} ${(connoteData.receiverAddress.countryDetail.countryName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Contact Name:"))!"Contact Name:"} ${(connoteData.receiverAddress.contactName?html)!" "}</td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Tel No.:"))!"Tel No.:"} ${(connoteData.receiverAddress.phone?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: solid 1px" valign="bottom" align="left">
                        <b>${(lang.translate("B. Dutiable Shipment Details"))!"B. Dutiable Shipment Details"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Receivers VAT TVA/BTW/MWST No.:"))!"Receivers VAT TVA/BTW/MWST No.:"}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Invoice Value of Dutiable"))!"Invoice Value of Dutiable"}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Currency:"))!"Currency:"} ${(connoteData.shipment.currencyCode?html)!"AUD"}</td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Value:"))!"Value:"} ${(connoteData.shipment.totalCustomValue)!"0.00"}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: solid 1px" valign="bottom" align="left">
                        <b>${(lang.translate("C. Special Delivery Instructions"))!"C. Special Delivery Instructions"}</b>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(connoteData.shipment.specialDeliveryinst?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: solid 1px" valign="bottom" align="left">
                        <b>${(lang.translate("D. Customer Reference"))!"D. Customer Reference"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(connoteData.shipment.reference?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt;" valign="bottom" align="left">
                        <b>${(lang.translate("E. Invoice Receiver(Receiver's Account Number)"))!"E. Invoice Receiver(Receiver's Account Number)"}</b><br/><#if connoteData.shipment.boundStatus?number == 1>
                        <span style="font-size: 10pt;">${(connoteData.tntAccount?html)!" "}</span></#if></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left; border-bottom: solid 1px" valign="bottom">
                        &nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Received by TNT"))!"Received by TNT"}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("By (Name):"))!"By (Name):"}</td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;border-bottom: solid 1px;" valign="bottom">
                        &nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Date:"))!"Date:"} ____/____/____
                    </td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Time:"))!"Time:"}  ____ : ____
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 10pt;text-align:left;" valign="bottom">
                        <b>${(lang.translate("Custom's Copy"))!"Custom's Copy"}</b></td>
                </tr>
                <tr style="border-bottom: solid 1px">
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<div class="page-break"></div>
<table style="width: 50%;">
    <tr>
        <td></td>
    </tr>
</table>
<table style="width: 50%; float: right" border="2">
    <tr>
        <td style="border: 2px solid black; width: 50%; vertical-align: top;">
            <table style="text-align: center;">
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        <b>${(lang.translate("1. From (Collection Address)"))!"1. From (Collection Address)"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Sender's Account No.:"))!"Sender's Account No.:"} <#if connoteData.shipment.boundStatus?number == 0> ${connoteData.tntAccount} </#if></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Name:"))!"Name:"} ${(connoteData.senderAddress.companyName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Address:"))!"Address:"} ${(connoteData.senderAddress.address?html)!" "} <#if (connoteData.senderAddress.address2)??>
                        ,${(connoteData.senderAddress.address2?html)!" "}</#if></td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("City:"))!"City:"} ${(connoteData.senderAddress.city?html)!" "}</td>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Postal/Zip Code:"))!"Postal/Zip Code:"} ${(connoteData.senderAddress.postalCode)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Province:"))!"Province:"} ${(connoteData.senderAddress.state)!" "}
                        Country: ${(connoteData.senderAddress.countryDetail.countryName)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Contact Name:"))!"Contact Name:"} ${(connoteData.senderAddress.contactName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Tel No.:"))!"Tel No.:"} ${(connoteData.senderAddress.phone?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        <b>${(lang.translate("2. To (Receiver Address)"))!"2. To (Receiver Address)"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom" align="left">
                    ${(connoteData.systemAddress?html?replace("\n", "<br/>"))!" "}
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        <b>${(lang.translate("3. Goods"))!"3. Goods"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("General Description:"))!"General Description:"}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(connoteData.shipment.contentDescription?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom" align="left">
                        <table style="font-size: 7pt;">
                            <tbody>
                            <td>
                            ${(lang.translate("Total Packages:"))!"Total Packages:"}<br/>
                            ${(connoteData.shipment.noOfPieces)!"0"}
                            </td>
                            <td>
                            ${(lang.translate("Total Weight:"))!"Total Weight:"} <br/>
                            ${(connoteData.totalWeight)!"0"}${(connoteData.shipment.weightUnit?lower_case)!"kg"}
                            </td>
                            <td>
                            ${(lang.translate("Total Volume:"))!"Total Volume:"}<br/>
                            ${(connoteData.totalVolume)!"0"}m3
                            </td>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        <b>${(lang.translate("4. Services"))!"4. Services"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Service:"))!"Service:"} ${(connoteData.shipmentType.shipmentTypeName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Options:"))!"Options:"}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 10pt;" valign="bottom" align="right">
                    <#if connoteData.shipment.boundStatus?number == 0>
                        Sender Pays
                    <#else>
                        Receiver Pays
                    </#if>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: 1px solid;" valign="bottom" align="left">
                        &nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom"
                        align="left">${(lang.translate("Sender's Signature:"))!"Sender's Signature:"}</td>
                    <td colspan="2" style="font-size: 7pt;" valign="bottom" align="left">&nbsp;</td>
                </tr>
                <tr>
                    <td style="font-size: 7pt; width: 20%" valign="bottom"
                        align="left">${(lang.translate("Date:"))!"Date:"}</td>
                    <td style="font-size: 7pt; width: 30%" valign="bottom" align="left">
                        <table style="font-size: 7pt;">
                            <tr>
                                <td style="width: 20%;border-bottom: solid 1px">&nbsp;</td>
                                <td style="border-bottom: solid 1px">/</td>
                                <td style="width: 20%;border-bottom: solid 1px">&nbsp;</td>
                                <td style="border-bottom: solid 1px">/</td>
                                <td style="width: 40%;border-bottom: solid 1px">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                    <td colspan="2" style="font-size: 7pt; width: 50%" valign="bottom" align="left">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;" valign="bottom" align="left">
                    ${(lang.translate("<br/>LIABILITY FOR LOSS,DAMAGE AND DELAY IS LIMITED BY THE CMR CONVENTION OR THE WARSAW CONVENTION WHICHEVER IS APPLICABLE.THE SENDER AGREES THAT THE GENERAL CONDITIONS,ACCESSIBLE VIA THE HELP TEXT THAT ARE ACCEPTABLE AND GOVERN THIS CONTRACT.IF NO SERVICE OR BILLING OPTION IS SELECTED THE FASTEST AVAILABLE SERVICE WILL BE CHARGED TO THE SENDER"))!"<br/>LIABILITY FOR LOSS,DAMAGE AND DELAY IS LIMITED BY THE CMR CONVENTION OR THE WARSAW CONVENTION WHICHEVER IS APPLICABLE.THE SENDER AGREES THAT THE GENERAL CONDITIONS,ACCESSIBLE VIA THE HELP TEXT THAT ARE ACCEPTABLE AND GOVERN THIS CONTRACT.IF NO SERVICE OR BILLING OPTION IS SELECTED THE FASTEST AVAILABLE SERVICE WILL BE CHARGED TO THE SENDER"}
                    </td>
                </tr>
            </table>
        </td>
        <td colspan="2" style="border: 2px solid black; width: 50%;">
            <table border="0" cellspacing="0" cellpadding="2" class="no-border">
                <tr>
                    <td colspan="4" valign="bottom" align="center">
                        <table>
                            <tr>
                                <td align="center"><img width="140px" src="${(connoteData.logo)!" "}" alt="TNT"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" valign="bottom" style="text-align:center;" align="center">
                        <table>
                            <tr>
                                <td align="center"><img width="240px" height="80px" src="${(connoteData.barCode)!" "}"
                                                        alt="TNT barcode"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:center;" valign="bottom">
                        <b>*${(connoteData.shipment.airbillNumber?html)!" "}*</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:center;"
                        valign="bottom">${(lang.translate("Please quote this number if you have an enquiry."))!"Please quote this number if you have an enquiry."}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: solid 1px" valign="bottom" align="left">
                        <b>${(lang.translate("A. Delivery Address"))!"A. Delivery Address"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Name:"))!"Name:"} ${(connoteData.receiverAddress.companyName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Address:"))!"Address:"} ${(connoteData.receiverAddress.address?html)!" "} <#if (connoteData.receiverAddress.address2)?? && connoteData.receiverAddress.address2?trim != "">${(connoteData.receiverAddress.address2?html)!" "}</#if></td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("City:"))!"City:"} ${(connoteData.receiverAddress.city?html)!" "}</td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Postal/Zip Code:"))!"Postal/Zip Code:"} ${(connoteData.receiverAddress.postalCode?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Province:"))!"Province:"} ${(connoteData.receiverAddress.state?html)!" "}</td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Country:"))!"Country:"} ${(connoteData.receiverAddress.countryDetail.countryName?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Contact Name:"))!"Contact Name:"} ${(connoteData.receiverAddress.contactName?html)!" "}</td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Tel No.:"))!"Tel No.:"} ${(connoteData.receiverAddress.phone?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: solid 1px" valign="bottom" align="left">
                        <b>${(lang.translate("B. Dutiable Shipment Details"))!"B. Dutiable Shipment Details"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Receivers VAT TVA/BTW/MWST No.:"))!"Receivers VAT TVA/BTW/MWST No.:"}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Invoice Value of Dutiable"))!"Invoice Value of Dutiable"}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: solid 1px" valign="bottom" align="left">
                        <b>${(lang.translate("C. Special Delivery Instructions"))!"C. Special Delivery Instructions"}</b>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(connoteData.shipment.specialDeliveryinst?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt; border-bottom: solid 1px" valign="bottom" align="left">
                        <b>${(lang.translate("D. Customer Reference"))!"D. Customer Reference"}</b></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(connoteData.shipment.reference?html)!" "}</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 8pt;" valign="bottom" align="left">
                        <b>${(lang.translate("E. Invoice Receiver(Receiver's Account Number)"))!"E. Invoice Receiver(Receiver's Account Number)"}</b><br/><#if connoteData.shipment.boundStatus?number == 1>
                        <span style="font-size: 10pt;">${(connoteData.tntAccount?html)!" "}</span></#if></td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left; border-bottom: solid 1px" valign="bottom">
                        &nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Received by TNT"))!"Received by TNT"}</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("By (Name):"))!"By (Name):"}</td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;border-bottom: solid 1px;" valign="bottom">
                        &nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Date:"))!"Date:"} ____/____/____
                    </td>
                    <td colspan="2" style="font-size: 7pt;text-align:left;"
                        valign="bottom">${(lang.translate("Time:"))!"Time:"}  ____ : ____
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 10pt;text-align:left;" valign="bottom">
                        <b>${(lang.translate("Receiver's Copy"))!"Receiver's Copy"}</b><br/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">
                    ${(lang.translate("Please Keep For Reference"))!"Please Keep For Reference"}
                    </td>
                </tr>
                <tr style="border-bottom: solid 1px">
                    <td colspan="4" style="font-size: 7pt;text-align:left;" valign="bottom">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>