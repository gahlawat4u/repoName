<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>
        View Priority Manifest
    </title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <style type="text/css">
        @page {
            size: A4;
            margin: 10mm 0mm 0mm 0mm;
        }

        body {
            font-family: Arial;
            font-size: 13px;
            color: #000;
            padding-left: 30px;
            padding-right: 30px;
        }

        table {
            border-collapse: 0;
            border-spacing: 0;
        }

        table td {
            padding: 5px;
            vertical-align: top;
        }

        .font-16 {
            font-size: 16px;
        }

        .font-35 {
            font-size: 35px;
        }

        .color-red {
            color: red;
        }

        .border-black {
            border: 1px solid black;
        }

        .padding-margin-0 {
            margin: 0;
            padding: 0;
        }

        .width-10pc {
            width: 10%;
        }

        .width-15pc {
            width: 15%;
        }

        .width-12pc {
            width: 12%;
        }

        .width-14pc {
            width: 14%;
        }

        .width-8pc {
            width: 8%;
        }

        .width-5pc {
            width: 5%;
        }

        .width-100pc {
            width: 100%;
        }

        .width-70pc {
            width: 70%;
        }

        .width-30pc {
            width: 30%;
        }

        .bold {
            font-weight: bold;
        }

        .text-left {
            text-align: left;
        }

        .border-top {
            border-top: 1px solid black;
        }

        .border-bottom {
            border-bottom: 1px solid black;
        }
    </style>
</head>
<body style="font-family: Arial; font-size: 12px; ">
<span>Printed Date: ${currentDate}</span>
<center>
    <h1>Manifest Report for Toll Priority <#if detailInfoModel.status == 1><i class="font-35 color-red">VOID</i></#if>
    </h1>
</center>
<br/>
<hr class="border-black padding-margin-0"/>
<hr class="padding-margin-0"/>
<br/><b>Service: Parcels Priority</b><br/>
<br/>
<hr class="border-black padding-margin-0"/>
<hr class="padding-margin-0"/>
<br/>
<table class="width-100pc">
    <tr>
        <td class="width-10pc bold">Manifest ID:</td>
        <td>${manifestIdentifier}</td>
        <td class="width-10pc bold">Sender:</td>
        <td>${detailInfoModel.sCompanyName}</td>
        <td class="width-10pc bold">Sender Account:</td>
        <td>${detailInfoModel.referenceNo}</td>
    </tr>
</table>
<br/>
<table class="width-100pc text-left">
    <tr>
        <th class="width-12pc">ConNote ID</th>
        <th class="width-15pc">Deliver To</th>
        <th class="width-8pc">Who Pays</th>
        <th class="width-8pc">Payer Accounts</th>
        <th class="width-14pc">Suburb</th>
        <th class="width-8pc">PostCode</th>
        <th class="width-8pc">Total Item</th>
        <th class="width-8pc">Total Cubic (m3)</th>
        <th class="width-8pc">Total Weight (kg)</th>
        <th class="width-8pc">Extra Service $</th>
        <th class="width-5pc">DG</th>
    </tr>
</table>
<table class="width-100pc text-left border-top">
<#list pieces as piece>
    <tr>
        <td class="width-12pc">${connoteNumber}</td>
        <td class="width-15pc">${detailInfoModel.rCompanyName}</td>
        <td class="width-8pc">Sender</td>
        <td class="width-8pc">${detailInfoModel.billingAccount}</td>
        <td class="width-14pc">${detailInfoModel.rCity}</td>
        <td class="width-8pc">${detailInfoModel.rPostalCode}</td>
        <td class="width-8pc">${detailInfoModel.noOfPieces}</td>
        <td class="width-8pc">${piece.cubicWeight}</td>
        <td class="width-8pc">${piece.deadWeight}</td>
        <td class="width-8pc">0.00</td>
        <td class="width-5pc">N</td>
    </tr>
</#list>
</table>
<table class="width-100pc text-left ">
    <tr>
        <th class="width-12pc"></th>
        <th class="width-15pc"></th>
        <th class="width-8pc"></th>
        <th class="width-8pc"></th>
        <th class="width-14pc"></th>
        <th class="width-8pc"></th>
        <th class="width-8pc"></th>
        <th class="width-8pc"></th>
        <th class="width-8pc"></th>
        <th class="width-8pc"></th>
        <th class="width-5pc"></th>
    </tr>
    <tr>
        <td colspan="5"><b>Total No. of ConNotes: ${detailInfoModel.noOfPieces} </b></td>
        <td class="border-top border-bottom"><b>Total for Manifest</b></td>
        <td class="border-top border-bottom">${detailInfoModel.noOfPieces}</td>
        <td class="border-top border-bottom">${totalGrossWeight}</td>
        <td class="border-top border-bottom">${totalNetWeight}</td>
        <td class="border-top border-bottom">0.00</td>
        <td class="border-top border-bottom"></td>
    </tr>
</table>

<span>References: ${detailInfoModel.referenceNo}<#if detailInfoModel.status == 1> Cencelled Connote<#else></#if></span>

<h3>DO THESE CONSIGNMENTS CONTAIN DANGEROUS GOODS?( )YES( )NO</h3>
<table class="width-100pc text-left border-top">
    <tr>
        <td class="width-70pc">
            I HEREBY DECLARE THAT THESE CONSIGNMENTS DO NOT CONTAIN DANGEROUS
            GOODS, AND THAT THESE CONSIGNMENTS DO NOT CONTAIN ANY UNAUTHORISED
            EXPLOSIVE OR INCENDIARY DEVICES. PLEASE ACCEPT FOR CARRIAGE THE GOODS
            DESCRIBED HEREON SUBJECT TO THE TERMS AND CONDITIONS OF THE CARRIER. I
            AM ALSO AWARE THAT THESE CONSIGNMENTS WILL BE SUBJECT TO SECURITY
            EXAMINATION AND CLEARING.

        </td>
        <td class="width-30pc">
            GOODS DESCRIBED HEREONNUMBER 1 ITEMS RECEIVED FOR CARRIAGE IN ACCORDANCE WITH CARRIER'S CONDITION OF
            TRANSPORT.
        </td>
    </tr>
    <tr>
        <td>
            Senders Signature: _____________________________________
        </td>
        <td>
            Driver's Signature: ____________________________________
        </td>
    </tr>
    <tr>
        <td>
            Date: _____________________________________
        </td>
        <td>
            Date: ____________________________________
        </td>
    </tr>
</table>
</body>
</html>