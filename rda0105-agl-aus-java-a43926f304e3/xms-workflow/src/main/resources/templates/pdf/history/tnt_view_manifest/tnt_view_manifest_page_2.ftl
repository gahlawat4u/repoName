<html>
<head>
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
<div class="portraitPage">
    <div class="float-left width-60pc text-right">
        <h3 class="text-underline">COLLECTION MANIFEST(SUMMARY)</h3>
    </div>
    <div class="width-40pc text-right float-right">
        <img width="130px" height="45px" src="${tntImage}" alt="TNT"/>
    </div>
    <div class="clear"></div>
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
                <td>:${detailInfoModel.sCompanyName} ${detailInfoModel.sAddress}
                    , ${detailInfoModel.sAddress2} ${detailInfoModel.sCity}
                    ,${detailInfoModel.sState} ${detailInfoModel.sPostalCode}, ${detailInfoModel.sCountryName}</td>
            </tr>
            <tr>
                <td>Group Code</td>
                <td>:</td>
            </tr>
        </table>
    </div>
    <div class="width-30pc text-right float-right">
        <table>
            <tr>
                <td>Page: 1 of 1</td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td>Date: ${currentDate}</td>
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

    <div class="clear"></div>
    <table>
        <tr>
            <td class="width-15pc">Con Nr.</td>
            <td class="width-10pc">No. of Pieces</td>
            <td class="width-10pc">Weight(kg)</td>
            <td class="width-15pc">Shipper Ref</td>
            <td class="width-10pc">Receiver</td>
            <td class="width-10pc">City</td>
            <td class="width-10pc">Destination</td>
            <td>Service</td>
        </tr>
        <tr>
            <td>${detailInfoModel.airbillNumber}</td>
            <td>${detailInfoModel.noOfPieces}</td>
            <td>${detailInfoModel.totalWeight}</td>
            <td>${detailInfoModel.referenceNo}</td>
            <td>${detailInfoModel.rCompanyName}</td>
            <td>${detailInfoModel.rCity}</td>
            <td>${detailInfoModel.rCountryName}</td>
            <td>${detailInfoModel.internalServiceCode}${detailInfoModel.contents}${detailInfoModel.serviceName}</td>
        </tr>
    </table>
    <br/>
    <hr class="border-black padding-margin-0"/>
    <br/>
	<span>
		Account ${detailInfoModel.billingAccount} Totals:
	</span>
    <table>
        <tr>
            <td class="width-15pc">1</td>
            <td class="width-10pc">${detailInfoModel.noOfPieces}</td>
            <td class="width-10pc">${detailInfoModel.actualWeight}</td>
            <td class="width-15pc"></td>
            <td class="width-10pc"></td>
            <td class="width-10pc"></td>
            <td class="width-10pc"></td>
            <td></td>
        </tr>
    </table>
    <br/>
    <hr class="border-black padding-margin-0"/>
    <br/>
	<span>
		Account ${detailInfoModel.billingAccount} Totals:
	</span>
    <table>
        <tr>
            <td class="width-15pc">1</td>
            <td class="width-10pc">${detailInfoModel.noOfPieces}</td>
            <td class="width-10pc">${detailInfoModel.actualWeight}</td>
            <td class="width-15pc"></td>
            <td class="width-10pc"></td>
            <td class="width-10pc"></td>
            <td class="width-10pc"></td>
            <td></td>
        </tr>
    </table>
    <br/>
    <hr class="border-black padding-margin-0"/>
    <br/>
	<span>
		Account ${detailInfoModel.billingAccount} Totals:
	</span>
    <table>
        <tr>
            <td class="width-15pc">1</td>
            <td class="width-10pc">${detailInfoModel.noOfPieces}</td>
            <td class="width-10pc">${detailInfoModel.actualWeight}</td>
            <td class="width-15pc"></td>
            <td class="width-10pc"></td>
            <td class="width-10pc"></td>
            <td class="width-10pc"></td>
            <td></td>
        </tr>
    </table>
    <div class="clear"></div>
    <br/>
    <hr class="border-black padding-margin-0"/>
    <br/><br/>
    <span class="text-bold">Sender's Signature:______________________________________ Date: ______/_____/_____</span>
    <br/><br/>
    <span class="text-bold">Received By TNT:________________________________________ Date: ______/_____/_____Time____:____hrs</span>
    <br/>
    <br/>

    <div class="width-40pc float-left text-right padding-10px text-bold">Print Date :${currentDate}</div>
    <div class="width-40pc float-right text-left padding-10px text-bold">Print Time :18:09</div>
    <div class="clear"></div>
    <br/>
    <br/>
    <hr class="border-black padding-margin-0"/>
    <hr class="padding-margin-0"/>
    <br/>
	<span class="text-bold">TNT'S LIABILITY FOR LOSS,DAMAGE AND DELAY IS LIMITED BY THE CMR CONVENTION OR WARSAW
CONVENTION,WHICHEVERR IS APPLICAPLE.THE SENDER AGREES THAT THE GENERAL
CONDITIONS,ACCESSIBLE VIA THE TNT HELP,ARE ACCEPTABLE AND GOVERN THIS CONTRACT.IF NO
SERVICE OR BILLING OPTION IS SELECTED THEN THE FASTEST AVAILABLE SERVICE WILL BE CHARGED TO
THE SENDER.
	</span>
</div>
</body>
</html>