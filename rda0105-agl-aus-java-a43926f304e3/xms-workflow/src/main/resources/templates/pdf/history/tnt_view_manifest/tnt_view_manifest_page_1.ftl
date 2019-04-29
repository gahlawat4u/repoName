<html>
<head>
    <style type="text/css">
        @page {
            size: A4 landscape;
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
            width: 100%;
        }

        table tr td, th {
            padding: 5px;
            vertical-align: top;
        }

        .text-bold {
            font-weight: bold;
        }

        .float-left {
            float: left;
        }

        .float-right {
            float: right;
        }

        .clear {
            clear: both;
        }

        .text-underline {
            text-decoration: underline;
        }

        .width-50pc {
            width: 50%;
        }

        .padding-10px {
            padding: 10px;
        }

        .width-30pc {
            width: 30%;
        }

        .width-100pc {
            width: 100%;
        }

        .width-15pc {
            width: 15%;
        }

        .width-10pc {
            width: 10%;
        }

        .width-70pc {
            width: 70%;
        }

        .width-40pc {
            width: 40%;
        }

        .width-60pc {
            width: 60%;
        }

        .width-30pc {
            width: 30%;
        }

        .text-right {
            text-align: right;
        }

        .text-left {
            text-align: left;
        }

        .border-black {
            border: 1px solid black;
        }

        .padding-margin-0 {
            margin: 0;
            padding: 0;
        }

        .page-break {
            page-break-before: always;
        }
    </style>
</head>
<body style="font-family: Arial; font-size: 12px;">
<div class="landscapePage">
    <center>
        <h3>COLLECTION MANIFEST (DETAIL) - OTHERS (SENDER PAYS)-1</h3>

        <div class="float-left">
            <img width="130px" height="45px" src="${tntImage}" alt="TNT"/>
        </div>
        <div>
            <span>TNT International Express</span> <br/> <span>Shipment Date:${detailInfoModel.shipmentDate}</span>
            <br/> <span>Pickup id:${detailInfoModel.confirmationNo}</span> <br/>
        </div>
    </center>
    <div class="float-left width-70pc">
        <table>
            <tr>
                <td class="width-30pc">Sender Account</td>
                <td>:${detailInfoModel.billingAccount}</td>
            </tr>
            <tr>
                <td>Sender Name</td>
                <td>:${detailInfoModel.sCompanyName}</td>
            </tr>
            <tr>
                <td>Address</td>
                <td>:${detailInfoModel.sCompanyName},${detailInfoModel.sAddress}
                    , ${detailInfoModel.sAddress2} ${detailInfoModel.sCity}
                    , ${detailInfoModel.sState} ${detailInfoModel.sPostalCode}
                    , ${detailInfoModel.sAddress2} ${detailInfoModel.sCountryName}</td>
            </tr>
        </table>
    </div>
    <div class="width-30pc text-right float-right">
        <table>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>Printed on:${currentDate}</td>
            </tr>
            <tr>
                <td></td>
            </tr>
        </table>
    </div>
    <div class="clear"></div>
    <br/>
    <hr class="border-black padding-margin-0"/>
    <br/>

    <div class="width-70pc">
        <table>
            <td class="width-40pc">
                <center><img width="260" height="90" src="${pieceBarcodeStr}" alt="TNT"/> <br/>
                    *${detailInfoModel.airbillNumber}*
                </center>
            </td>
            <td>Sending Depot</td>
            <td>Sending Depot</td>
            <td><span class="text-underline">Special instructions </span><br/>Sender Pays</td>
        </table>
    </div>
    <div class="clear"></div>
    <div class="float-left width-70pc">
        <table>
            <tr>
                <td class="width-30pc t">Sender Contact</td>
                <td>:${detailInfoModel.sContactName} Tel: ${detailInfoModel.sPhone} Sender
                    Ref: ${detailInfoModel.referenceNo} Receiver Vat Nr:
                </td>
            </tr>
            <tr>
                <td>Receiver Name</td>
                <td>:${detailInfoModel.rCompanyName}, ${detailInfoModel.rAddress}, ${detailInfoModel.rAddress2} Receiver
                    Contact: ${detailInfoModel.rContactName}</td>
            </tr>
            <tr>
                <td>Address</td>
                <td>:${detailInfoModel.rCity},${detailInfoModel.rState} ${detailInfoModel.rPostalCode}
                    , ${detailInfoModel.rCountryName}</td>
            </tr>
            <tr>
                <td>Receiver Tel</td>
                <td>:${detailInfoModel.rPhone}</td>
            </tr>
            <tr>
                <td>Collection</td>
                <td>:${detailInfoModel.sCompanyName} ${detailInfoModel.sAddress}, ${detailInfoModel.sAddress2}</td>
            </tr>
            <tr>
                <td>Address</td>
                <td>:${detailInfoModel.sCity},${detailInfoModel.sState} ${detailInfoModel.sPostalCode}
                    , ${detailInfoModel.sCountryName}</td>
            </tr>
            <tr>
                <td>Delivery</td>
                <td>:${detailInfoModel.rCompanyName} ${detailInfoModel.rAddress}, ${detailInfoModel.rAddress2}</td>
            </tr>
            <tr>
                <td>Address</td>
                <td>:${detailInfoModel.rCity},${detailInfoModel.rState} ${detailInfoModel.rPostalCode}
                    , ${detailInfoModel.rCountryName}</td>
            </tr>
            <tr>
                <td>No Pieces:3</td>
                <td>:${detailInfoModel.noOfPieces} Weight : ${detailInfoModel.totalWeight}${detailInfoModel.weightUnit}
                    Insurance Value: ${detailInfoModel.withInsurance}  Invoice Value
                    : ${detailInfoModel.totalCustomValue}</td>
            </tr>
        </table>
    </div>
    <div class="width-30pc float-right">
        <table>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>Serv: ${detailInfoModel.internalServiceCode} <#if detailInfoModel.contents == "1">N<#else>
                    D</#if> ${detailInfoModel.serviceName}</td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>Opts:</td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td></td>
            </tr>
        </table>
    </div>
    <div class="clear"></div>
    <table>
        <tr>
            <td class="width-50pc">Description(Including packing and marks)</td>
            <td>Dimentions(LxWxH)</td>
        </tr>
    <#list pieces as pic>
        <tr>
            <td>${detailInfoModel.contentDescription}</td>
            <td>${pic.dimension}</td>
        </tr>
    </#list>
    </table>
    <br/>
    <hr class="border-black padding-margin-0"/>
</div>

</body>
</html>