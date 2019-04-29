<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>View Thermal Lable</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <style type="text/css">
        @page {
            size: 50%;
        )!""
        }

        .text-bold {
            font-weight: bold;
        ) ! ""
        }

        .text-left {
            text-align: left;
        ) ! ""
        }

        .text-right {
            text-align: right;
        ) ! ""
        }

        .text-center {
            text-align: center;
        ) ! ""
        }

        .text-left {
            text-align: left;
        ) ! ""
        }

        .text-right {
            text-align: right;
        ) ! ""
        }

        .text-bold {
            font-weight: bold;
        ) ! ""
        }

        table {
            width: 100%;
        ) ! ""
        }

        table tr td, th {
            border-top: 2px solid black;
            border-bottom: 2px solid black;
            padding: 5px;
        ) ! ""
        }

        table tr:first-child td {
            border-top: none;
        ) ! ""
        }

        table tr:last-child td {
            border-bottom: none;
        ) ! ""
        }

        .width-50 {
            width: 50%;
        ) ! ""
        }

        .left {
            float: left;
        ) ! ""
        }

        .right {
            float: right;
        ) ! ""
        }

        .color-tb-header {
            background-color: #BDBDBD;
        ) ! ""
        }

        .background-black {
            background-color: black;
        ) ! ""
        }

        .color-white {
            color: white;
        ) ! ""
        }

        .border-top-right-left {
            border-top: 4px solid black;
            border-right: 4px solid black;
            border-left: 4px solid black;
        ) ! ""
        }
    </style>
</head>
<body style="font-family: Arial; font-size: 12px;">
<#list pieces as piece>
<table cellspacing="0">
    <tr>
        <td class="width-50"><h2 class="text-center">${(detailInfo.serviceType)!""}</h2>
            <h4 class="text-center">XMLPI 4.6 /*03-1211*</h4></td>
        <td class="border-top-right-left" style="border-top: 4px solid black;"><h2
                class="text-center">${(detailInfo.awbProductContentCode)!""}</h2></td>
        <td class="text-center"><img src="${(dhlImage)!""}" alt="DHL" height="50%" width="50%"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <div class="text-bold text-left left">From:</div>
            <div class="text-left left">
            ${(detailInfo.sCompanyName?html)!""} <br/>${(detailInfo.sContactName?html)!""}
                <br/>${(detailInfo.sAddress?html)!""}
                <p>
                ${(detailInfo.sCity?html)!""}, ${(detailInfo.sPostalCode)!""} <br/>${(detailInfo.sCountryName?html)!""}
                </p>
            </div>
        </td>
        <td valign="top">
            <div class="text-bold text-center">
                Origin:
                <h3>${(detailInfo.serviceAreaCodeOrigin)!""}</h3>
            </div>
            <div class="text-left">Ph:${(detailInfo.sPhone)!""}</div>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div class="text-bold left">TO:</div>
            <div class="text-left left">
            ${(detailInfo.rCompanyName?html)!""} <br/>${(detailInfo.rContactName?html)!""}
                <br/>${(detailInfo.rAddress?html)!""}
                <p>
                ${(detailInfo.rCity?html)!""}, ${(detailInfo.rPostalCode)!""} <br/>${(detailInfo.rCountryName)!""}
                </p>
            </div>
        </td>
        <td valign="top">
            <div class="text-bold ">Contact:</div>
            <div class="text-left">Ph:${(detailInfo.rPhone)!""}</div>
        </td>
    </tr>
    <tr>
        <td colspan="3">${(outboundSortCode)!""}<h1 class="text-center">${(detailInfo.rCountryCode)!""}
            -${(detailInfo.serviceAreaCodeDestination)!""}
            -${(detailInfo.serviceAreaCodeDestination)!""} </h1>${(inboundSortCode)!""}</td>
    </tr>
    <tr>
        <td colspan="2" class="background-black"><h2
                class="color-white">${(detailInfo.termOfTrade)!""} ${(detailInfo.internalServiceCode)!""}</h2></td>
        <td>
            <div class="width-50 left text-center">${(detailInfo.deliveryDateCode)!""}</div>
            <div class="width-50 right text-center">${(detailInfo.deliveryTimeCode)!""}</div>
        </td>
    </tr>
    <tr>
        <td>
            <div class="text-bold text-left left">Ref : ${(detailInfo.referenceNo)!""}</div>
            <div class="text-right right">Date :</div>
        </td>
        <td>
            <div class="text-center">Pce/Shpt Weight</div>
        </td>
        <td>
            <div class="text-center">Pieces</div>
        </td>
    </tr>
    <tr>
        <td>
            <div class="text-bold text-left left">Account No : ${(detailInfo.billingAccount)!""}</div>
            <div class="text-right right"> ${(detailInfo.shipmentDate)!""}</div>
        </td>
        <td>
            <div class="text-center text-bold">${(detailInfo.dimWeight)!""} ${(detailInfo.weightUnit)!""}</div>
        </td>
        <td>
            <div class="text-center text-bold">1 / ${(detailInfo.noOfPieces)!""}</div>
        </td>
    </tr>

    <#if dhlAccount == "3p">
        <tr>
            <td class="background-black color-white">Security Inspection Required</td>
            <td></td>
            <td></td>
        </tr>
    </#if>
    <tr>
        <td colspan="2">
            <p class="text-center">
                <#if detailInfo.status == 1>
                    <img src="${(imgVoied)!""}"
                         alt="WAYBILL ${(detailInfo.airbillNumber)!""}"/><br/>WAYBILL ${(detailInfo.airbillNumber)!""}
                <#else>
                    <img src="data:image/png;base64,${(detailInfo.awbBarcode)!""}"
                         alt="WAYBILL ${(detailInfo.airbillNumber)!""}"/><br/>WAYBILL ${(detailInfo.airbillNumber)!""}
                </#if>
            </p>

            <p class="text-center">
                <#if detailInfo.status == 1>
                    <img src="${(imgVoied)!""}"
                         alt="WAYBILL ${(detailInfo.dhlRoutingCode)!""}"/><br/>WAYBILL ${(detailInfo.dhlRoutingCode)!""}
                <#else>
                    <img src="data:image/png;base64,${(detailInfo.dhlRoutingBarcode)!""}"
                         alt="WAYBILL ${(detailInfo.dhlRoutingCode)!""}"/><br/>WAYBILL ${(detailInfo.dhlRoutingCode)!""}
                </#if>
            </p>

            <p class="text-center">
                <#if detailInfo.status == 1>
                    <img src="${(imgVoied)!""}"
                         alt="WAYBILL ${(piece.licensePlate)!""}"/><br/>WAYBILL ${(piece.licensePlate)!""}
                <#else>
                    <img src="data:image/png;base64,${(piece.dataIdentifier)!""}${(piece.licensePlate)!""}"
                         alt="WAYBILL ${(piece.licensePlate)!""}"/><br/>WAYBILL ${(piece.licensePlate)!""}
                </#if>
            </p>
        </td>
        <td valign="top">
            <div class="text-center ">Contents: ${(detailInfo.contentDescription)!""}</div>
        </td>
    </tr>

</table>
</#list>

<table cellspacing="0">
    <tr>
        <td class="width-50"><h2 class="text-center">* ARCHIVE DOC *</h2>
            <h4 class="text-center">Not to be attached to package</h4></td>
        <td class="border-top-right-left" style="border-top: 4px solid black;"><h2
                class="text-center">${(detailInfo.awbProductContentCode)!""}</h2></td>
        <td class="text-center"><img src="${(dhlImage)!""}" alt="DHL" height="50%" width="50%"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <div class="text-bold text-left left">Product:</div>
            <br/>

            <div class="text-left ">
            ${(detailInfo.serviceType)!""}<br/>Payment Code : 963374484 <br/>FRT A/C : <br/>DTP A/C : <br/>Terms of
                Trade : ${(detailInfo.termOfTrade)!""}
                <p>
                ${(detailInfo.sCity)!""}, ${(detailInfo.sPostalCode)!""} <br/>${(detailInfo.sCountryName)!""}
                </p>
            </div>
        </td>
        <td valign="top">
            <div class="text-center ">Features/Services: ${(detailInfo.termOfTrade)!""}
                - ${(detailInfo.internalServiceCode)!""}</div>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div class="text-left left">Ref :${(detailInfo.referenceNo)!""}
                <p>Custom Val: ${(detailInfo.totalCustomValue)!""}<br/>
                    Insured Amount : :${(detailInfo.withInsurance)!""}<br/>
                    Account No : <strong>${(detailInfo.billingAccount)!""}</strong>
                </p>
            </div>
            <div class="right">Shpt Wght : ${(detailInfo.dimWeight)!""} ${(detailInfo.weightUnit)!""}
                <p>Shipment Date :${(detailInfo.shipmentDate)!""}
                </p>
            </div>
        </td>
        <td valign="top">
            <div class="text-center "># of Pieces</div>
        </td>
    </tr>

    <tr>
        <td colspan="2">
            <p class="text-center">
                <img src="${(imgVoied)!""}"
                     alt="WAYBILL ${(detailInfo.airbillNumber)!""}"/><br/>WAYBILL ${(detailInfo.airbillNumber)!""}
            </p>
        </td>
        <td valign="top">
            <div class="text-center ">Contents: ${(detailInfo.contentDescription)!""}</div>
        </td>
    </tr>

    <tr>
        <td colspan="3">
            <p>License plates of Pieces in Shipment</p> - (J)JD014600002745759895
        </td>
    </tr>
</table>

</body>
</html>
