<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <style type="text/css">
        @page {
            size: A4 landscape;
            margin: 10mm 0mm 0mm 0mm;
        }

        body {
            font-family: Arial;
            font-size: 11px;
            color: #000;
            padding-left: 30px;
            padding-right: 30px;
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

        .text-left {
            text-align: left;
        }

        .text-right {
            text-align: right;
        }

        .text-bold {
            font-weight: bold;
        }

        table {
            width: 100%;
        }

        table.tbl-border tr td, th {
            border: 1px solid #6F6F6F;
            padding: 5px;
        }

        .border {
            padding: 10px;
        }

        .border tbody td {
            border: 1px solid black;
        }

        .border-black {
            border: 1px solid black;
        }

        .width-50 {
            width: 50%;
        }

        .width-80 {
            width: 80%;
        }

        .width-20 {
            width: 2 s0%;
        }

        .width-100 {
            width: 100%;
        }

        .width-70 {
            width: 70%;
        }

        .width-30 {
            width: 30%;
        }

        .left {
            float: left;
        }

        .right {
            float: right;
        }

        .color-tb-header {
            background-color: #BDBDBD;
        }

        .clear {
            clear: both;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
        }

        h2 {
            font-size: 24px;
        }

        .page-break {
            page-break-after: always;
        }
    </style>
</head>
<body style="font-family: Arial; font-size: 12px;">
<center>
    <h2 class="text-center">TNT DESPATCH MANIFEST - ${detailInfoModel.serviceType}</h2>
</center>

<table style="width: 100%">
    <td>
        <table class="border" style="width: 90%">
            <thead>
            <th bgcolor="silver">Company:</th>
            </thead>
            <tbody>
            <td>
            ${customerAddress.customerName}<br/>
            ${customerAddress.address1}<br/>
            ${customerAddress.city} ${customerAddress.postalCode}<br/>
                Phone:${customerAddress.phone}<br/>
                Emai:${customerAddress.email}
            </td>
            </tbody>
        </table>
    </td>
    <td>
        <table class="border" style="width: 90%">
            <thead>
            <th bgcolor="silver">Sender: ${detailInfoModel.billingAccount} ETC Sender Code: TTINT</th>
            </thead>
            <tbody>
            <td>
            ${detailInfoModel.sCompanyName}<br/>
            ${detailInfoModel.sAddress}<br/>
            ${detailInfoModel.sCity} ${detailInfoModel.sState} ${detailInfoModel.sPostalCode}<br/>
                Contact: ${detailInfoModel.sContactName}<br/>
                Phone: ${detailInfoModel.sPhone}
            </td>
            </tbody>
        </table>
    </td>
    <td>
        <table cellspacing="0">
            <tr>
                <td><strong>Page</strong></td>
                <td>: 1 of 1</td>
            </tr>
            <tr>
                <td><strong>Date</strong></td>
                <td>: ${currentDate}</td>
            </tr>
            <tr>
                <td><strong>Time</strong></td>
                <td>: ${currentTime}</td>
            </tr>
            <tr>
                <td><strong>Carrier</strong></td>
                <td>: TNT Express</td>
            </tr>
            <tr>
                <td><strong>Service</strong></td>
                <td>: ${detailInfoModel.serviceCode}</td>
            </tr>
            <tr>
                <td><strong>Consignment Date</strong></td>
                <td>: ${detailInfoModel.shipmentDate}</td>
            </tr>
            <tr>
                <td><strong>Routing Version</strong></td>
                <td>: 71 - (080401)</td>
            </tr>
            <tr>
                <td><strong>9amfile Version</strong></td>
                <td>: 23 - (04/12/2012)</td>
            </tr>
        </table>
    </td>
</table>
<div class="width-100">
    <table border="1" class="tbl-border">
        <tr>
            <th class="color-tb-header">DG</th>
            <th class="color-tb-header">Consignment No</th>
            <th class="color-tb-header">P</th>
            <th class="color-tb-header">Sender Ref</th>
            <th class="color-tb-header">Receiver Name</th>
            <th class="color-tb-header">Destination</th>
            <th class="color-tb-header">Items</th>
            <th class="color-tb-header">KG</th>
            <th class="color-tb-header">Cubic</th>
        </tr>
    <#list listPieceGrouped as pic>
        <tr>
            <td>${isDG}</td>
            <td>${detailInfoModel.airbillNumber}</td>
            <td>S</td>
            <td>${detailInfoModel.referenceNo}</td>
            <td>${detailInfoModel.rCompanyName}</td>
            <td>${detailInfoModel.rCity} ${detailInfoModel.rState} ${detailInfoModel.rPostalCode}</td>
            <td>${pic.pieces}</td>
            <td>${pic.deadWeight}</td>
            <td>${pic.cubicWeight}</td>
        </tr>
    </#list>
        <tr>
            <td colspan="6">Total</td>
            <td>${detailInfoModel.noOfPieces}</td>
            <td>${detailInfoModel.actualWeight}</td>
            <td>${detailInfoModel.dimWeight}</td>
        </tr>
        <tr>
            <td colspan="9"></td>
        </tr>
        <tr>
            <td colspan="6">Grand Total</td>
            <td>${detailInfoModel.noOfPieces}</td>
            <td>${detailInfoModel.actualWeight}</td>
            <td>${detailInfoModel.dimWeight}</td>
        </tr>
    </table>
</div>
<div class="clear"></div>
<br/>

<div class="width-80 border-black text-bold">Shippers Declaration: DG: Y These consignments contain DANGEROUS GOODS.
    Dangerous Goods declaration must accompany this manifest and be checked in line with the TNT Dangerous Goods
    standards and procedures. DG: N These consignments do not contain dangerous goods.
</div>
<div class="clear"></div>
<br/>

<div class="text-bold">
    Customer Signature:_____________________________ Name: _____________________________ Date: ______/_____/_____ <br/>
    Driver's Signature:_______________________________ Name: _____________________________ Date: ______/_____/_____
    <br/>
</div>
<#if isDG == 'Y'>
<div class="page-break"></div>
<center>
    <h2 class="text-center">Dangerous Goods Manifest</h2>
</center>

<table style="width: 100%">
    <td>
        <table class="border" style="width: 90%">
            <thead>
            <th bgcolor="silver">Company:</th>
            </thead>
            <tbody>
            <td>
            ${customerAddress.customerName}<br/>
            ${customerAddress.address1}<br/>
            ${customerAddress.city} ${customerAddress.postalCode}<br/>
                Phone:${customerAddress.phone}<br/>
                Emai:${customerAddress.email}
            </td>
            </tbody>
        </table>
    </td>
    <td>
        <table class="border" style="width: 90%">
            <thead>
            <th bgcolor="silver">Sender: ${detailInfoModel.billingAccount} ETC Sender Code: TTINT</th>
            </thead>
            <tbody>
            <td>
            ${detailInfoModel.sCompanyName}<br/>
            ${detailInfoModel.sAddress}<br/>
            ${detailInfoModel.sCity} ${detailInfoModel.sState} ${detailInfoModel.sPostalCode}<br/>
                Contact: ${detailInfoModel.sContactName}<br/>
                Phone: ${detailInfoModel.sPhone}
            </td>
            </tbody>
        </table>
    </td>
    <td>
        <table cellspacing="0">
            <tr>
                <td><strong>Page</strong></td>
                <td>: 1 of 1</td>
            </tr>
            <tr>
                <td><strong>Date</strong></td>
                <td>: ${currentDate}</td>
            </tr>
            <tr>
                <td><strong>Time</strong></td>
                <td>: ${currentTime}</td>
            </tr>
            <tr>
                <td><strong>Carrier</strong></td>
                <td>: TNT Express</td>
            </tr>
            <tr>
                <td><strong>Service</strong></td>
                <td>: ${detailInfoModel.serviceCode}</td>
            </tr>
            <tr>
                <td><strong>Consignment Date</strong></td>
                <td>: ${detailInfoModel.shipmentDate}</td>
            </tr>
            <tr>
                <td><strong>Routing Version</strong></td>
                <td>: 71 - (080401)</td>
            </tr>
            <tr>
                <td><strong>9amfile Version</strong></td>
                <td>: 23 - (04/12/2012)</td>
            </tr>
        </table>
    </td>
</table>
<div class="width-100">
    <table border="1" class="tbl-border">
        <tr>
            <th class="color-tb-header">DG</th>
            <th class="color-tb-header">Consignment No</th>
            <th class="color-tb-header">Sender Ref</th>
            <th class="color-tb-header">Receiver Name</th>
            <th class="color-tb-header">Destination</th>
            <th class="color-tb-header">Items</th>
            <th class="color-tb-header">KG</th>
            <th class="color-tb-header">Cubic</th>
        </tr>
        <#list listPieceGrouped as pic>
            <tr>
                <td>${isDG}</td>
                <td>${detailInfoModel.airbillNumber}</td>
                <td>${detailInfoModel.referenceNo}</td>
                <td>${detailInfoModel.rCompanyName}</td>
                <td>${detailInfoModel.rCity} ${detailInfoModel.rState} ${detailInfoModel.rPostalCode}</td>
                <td>${pic.quantity}</td>
                <td>${pic.deadWeight}</td>
                <td>${pic.weight}</td>
            </tr>
        </#list>
        <tr>
            <td colspan="6">Total</td>
            <td>${detailInfoModel.noOfPieces}</td>
            <td>${detailInfoModel.actualWeight}</td>
            <td>${detailInfoModel.dimWeight}</td>
        </tr>
        <tr>
            <td colspan="9"></td>
        </tr>
        <tr>
            <td colspan="6">Grand Total</td>
            <td>${detailInfoModel.noOfPieces}</td>
            <td>${detailInfoModel.actualWeight}</td>
            <td>${detailInfoModel.dimWeight}</td>
        </tr>
    </table>
</div>
<div class="clear"></div>
<br/>

<div class="width-80 border-black text-bold">Shippers Declaration: DG: Y These consignments contain DANGEROUS GOODS.
    Dangerous Goods declaration must accompany this manifest and be checked in line with the TNT Dangerous Goods
    standards and procedures. DG: N These consignments do not contain dangerous goods.
</div>
<div class="clear"></div>
<br/>

<div class="text-bold">
    Customer Signature:_____________________________ Name: _____________________________ Date: ______/_____/_____ <br/>
    Driver's Signature:_______________________________ Name: _____________________________ Date: ______/_____/_____
    <br/>
</div>
</#if>
</body>
</html>


