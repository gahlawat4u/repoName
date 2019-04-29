<html>
<head>
    <style type="text/css">
        @page {
            size: A4;
        }

        body {
            font-family: Arial;
            font-size: 10px;
        }
    </style>
</head>
<body>
<table style="float:right; width:100%">
    <tbody>
    <tr>
        <td width="80%" align="right"><img src="${(logo)!" "}" width="300px"/></td>
        <td align="right">${(franchiseAddress.customerName?html)!" "} <br/>
        ${(franchiseAddress.address1?html)!" "}<br/>
        ${(franchiseAddress.city?html)!" "} ${(franchiseAddress.postalCode?html)!" "}<br/>
            PHONE ${(franchiseAddress.phone?html)!" "}<br/>
            <a href="${(siteAddress)!" "}">${(siteAddress)!" "}</a>
        </td>
    </tr>
    </tbody>
</table>
<h1 style="">Shipment Receipt</h1>
<table style="width: 100%">
    <tr>
        <td><b>Service Type:</b></td>
        <td>${(shipmentInfo.serviceType)!" "}</td>
        <td><b>Shipment Date:</b></td>
        <td>${(shipmentInfo.shipmentDate)!" "}</td>
    </tr>
    <tr>
        <td><b>Package Type:</b></td>
        <td>${(shipmentInfo.packageType)!" "}</td>
        <td><b>Tracking #:</b></td>
        <td>${(shipmentInfo.airbillNumber)!" "}</td>
    </tr>
    <tr>
        <td><b>Reference #:</b></td>
        <td>${(shipmentInfo.referenceNo)!" "}</td>
        <td><b>Insurance:</b></td>
        <td><#if shipmentInfo.withInsurance == '0' || shipmentInfo.withInsurance == ''>No<#else>Yes</#if></td>
    </tr>
    <tr>
        <td><b>Zone:</b></td>
        <td>${(shipmentInfo.zone)!" "}</td>
        <td><b>Dim. Weight:</b></td>
        <td>${(shipmentInfo.dimWeight)!" "}</td>
    </tr>
</table>
<table bgcolor="silver" style="width: 100%;">
    <tbody>
    <tr>
        <td colspan="3"><b>Package Information</b></td>
    </tr>
    <#if pieces?has_content>
        <#list pieces as p>
        <tr>
            <td><b>Pieces:</b> ${p.pieces}</td>
            <td><b>Weight:</b> ${p.deadWeight}</td>
            <td><b>Dimension:</b> ${p.dimension}
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<table style="width: 100%;">
    <tbody>
    <tr>
        <td>
            <b>Content type:</b><br/>
        <#if shipmentInfo.productContentCode == 'WPX'>Product<#else>Document</#if><br/>
            <b>Shipper Address:</b><br/>
        ${(shipmentInfo.sCompanyName?html)!" "}<br/>
        ${(shipmentInfo.sContactName?html)!" "}<br/>
        ${(shipmentInfo.sAddress?html)!" "}<br/>
        ${(shipmentInfo.sCity?html)!" "}, ${(shipmentInfo.sPostalCode?html)!" "}<br/>
        ${(shipmentInfo.sCountryName?html)!" "}<br/> <br/>
            <b>Collection Instruction:</b><br/>
        ${(shipmentInfo.reasonForExport?html)!" "}<br/>
        </td>
        <td valign="top">
            <b>Content Description:</b><br/>
        ${(shipmentInfo.contentDescription?html)!" "}<br/>
            <b>Receiver Address:</b><br/>
        ${(shipmentInfo.rCompanyName?html)!" "}<br/>
        ${(shipmentInfo.rContactName?html)!" "}<br/>
        ${(shipmentInfo.rAddress?html)!" "}<br/>
        ${(shipmentInfo.rCity?html)!" "}, ${(shipmentInfo.rPostalCode?html)!" "}<br/>
        ${(shipmentInfo.rCountryName?html)!" "}
        </td>
    </tr>
    <tr>
        <td><b>Quote Detail:</b><br/></td>
    </tr>
    <#if shipmentInfo.baseCharge=='0.00'>
        <tr>
            <td>
                Cost will be advised later<br/>
            </td>
        </tr>
    <#else>
        <tr>
            <td>Base Charge:
            </td>
            <td>${shipmentInfo.baseCharge}</td>
        </tr>
        <#list detailAccessorialModels as acc>
        <tr>
            <td>${acc.description}:
            </td>
            <td>${acc.amount}</td>
        </tr>
        </#list>

    </#if>
    </tbody>
</table>
</body>
</html>