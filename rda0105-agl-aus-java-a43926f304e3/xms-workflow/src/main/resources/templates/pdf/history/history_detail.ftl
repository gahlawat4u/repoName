<#setting date_format="dd-MM-yyyy">
<html>
<head>
    <style type="text/css">
        @page {
            size: A4 landscape;
        }

        body {
            font-family: arial;
        }
    </style>
</head>
<body>
<label>
    <b style="font-weight: 700; color: #5EB24D;">Shipment Detail:</b>
</label>
<hr style="margin-top: 0px; margin-bottom: 0px;"/>
<div style="float:left; width:48%;">
    <table style="font-size: 11px; margin-bottom: 0px; width:100%;">
        <tr>
            <td style="border-top: 1px solid #ddd;  color: #293979; ">Service Type</td>
            <td style="border-top: 1px solid #ddd;">${(detailInfoModel.serviceType)!" "}</td>
        </tr>
        <tr>
            <td style="border-top: 1px solid #ddd; color: #293979;">Shipment Date</td>
            <td style="border-top: 1px solid #ddd;">${(detailInfoModel.shipmentDate)!" "}</td>
        </tr>
        <tr>
            <td style="border-top: 1px solid #ddd; color: #293979;">Package Type</td>
            <td style="border-top: 1px solid #ddd;">${(detailInfoModel.packageType)!" "}</td>
        </tr>
        <tr>
            <td style="border-top: 1px solid #ddd; color: #293979;">Tracking #</td>
            <td style="border-top: 1px solid #ddd;">${(detailInfoModel.tracking)!" "}</td>
        </tr>

    </table>
</div>
<div style="width:48%; float:right;">
    <table style="font-size: 11px; margin-bottom: 0px; width:100%;">
        <tr>
            <td style="border-top: 1px solid #ddd; color: #293979;">Reference" #</td>
            <td style="border-top: 1px solid #ddd;">${(detailInfoModel.referenceNo)!" "}</td>
        </tr>
        <tr>
            <td style="border-top: 1px solid #ddd; color: #293979;">Confirmation#</td>
            <td style="border-top: 1px solid #ddd;">${(detailInfoModel.confirmationNo)!" "}</td>
        </tr>
        <tr>
            <td style="border-top: 1px solid #ddd; color: #293979;">Zone</td>
            <td style="border-top: 1px solid #ddd;">${(detailInfoModel.zone)!" "}</td>
        </tr>
        <tr>
            <td style="border-top: 1px solid #ddd; color: #293979;">Dim. Weight</td>
            <td style="border-top: 1px solid #ddd;">${(detailInfoModel.dimWeight)!" "}</td>
        </tr>
        <tr>
            <td style="border-top: 1px solid #ddd; color: #293979;">Actual. Weight</td>
            <td style="border-top: 1px solid #ddd;">${(detailInfoModel.actualWeight)!" "}</td>
        </tr>
    </table>
</div>
<div style="width:100%">
    <br/>
    <label>
        <b style="font-weight: 700; color: #5EB24D;">Package Information:</b>
    </label>
    <hr style="margin-top: 0px; margin-bottom: 0px;"/>
    <table width="100%">
        <thead>
        <tr>
            <th style="font-weight: bold;color: #293979; text-transform: uppercase;">Piece</th>
            <th style="font-weight: bold;color: #293979; text-transform: uppercase;">Dead weight</th>
            <th style="font-weight: bold;color: #293979; text-transform: uppercase;">Cubic weight</th>
            <th style="font-weight: bold;color: #293979; text-transform: uppercase;">Dimension</th>
        </tr>
        </thead>
        <tbody>
        <#if detailPieceModels?has_content>
            <#list detailPieceModels as pic>
            <tr>
                <td style="border-top: 1px solid #ddd;">${(pic.pieces)!" "}</td>
                <td style="border-top: 1px solid #ddd;">${(pic.deadWeight)!" "}<br/></td>
                <td style="border-top: 1px solid #ddd;">${(pic.cubicWeight)!" "}</td>
                <td style="border-top: 1px solid #ddd;">${(pic.dimension)!" "}</td>
            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
    <hr style="margin-top: 0px; margin-bottom: 0px;"/>

</div>
<div style="width:100%">
    <div style="width:48%; float:left; padding-right:5px;">
        <table cellpadding="0" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th style="font-weight: bold;color: #293979; text-transform: uppercase;">Shipper Address</th>
                <th style="font-weight: bold;color: #293979; text-transform: uppercase;">Receiver Address</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${(detailInfoModel.sCompanyName)!" "}</td>
                <td>${(detailInfoModel.rCompanyName)!" "}</td>
            </tr>
            <tr>
                <td>${(detailInfoModel.sAddress)!" "}</td>
                <td>${(detailInfoModel.rAddress)!" "}</td>
            </tr>
            <tr>
                <td>${(detailInfoModel.sCity)!" "}${(detailInfoModel.sPostalCode)!" "}${(detailInfoModel.sState)!" "}</td>
                <td>${(detailInfoModel.rCity)!" "}${(detailInfoModel.rPostalCode)!" "}${(detailInfoModel.rState)!" "}</td>
            </tr>
            <tr>
                <td>${(detailInfoModel.sCountryName)!" "}</td>
                <td>${(detailInfoModel.rCountryName)!" "}</td>
            </tr>
            <tr>
                <td>Ph: ${(detailInfoModel.sPhone)!" "}</td>
                <td>Ph: ${(detailInfoModel.rPhone)!" "}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="width:48%; float:right;">
        <table cellpadding="0" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th colspan="2" style="font-weight: bold;color: #293979; text-transform: uppercase;">Quote Detail</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>- Base Charge:
                </td>
                <td>${(detailInfoModel.baseCharge)!" "}</td>
            </tr>
            <#if detailAccessorialModels?has_content>
                <#list detailAccessorialModels as ac>

                <tr>

                    <td>- ${(ac.description)!" "}:
                    </td>
                    <td>${(ac.amount)!" "}</td>
                </tr>
                </#list>
            </#if>
            <tr>

                <td colspan="2"><i>Quote is an estimate. Additional fees may apply.</i></td>
            </tr>
            </tbody>
        </table>
    </div>
    <hr style="margin-top: 0px; margin-bottom: 0px;"/>
</div>
</body>
</html>