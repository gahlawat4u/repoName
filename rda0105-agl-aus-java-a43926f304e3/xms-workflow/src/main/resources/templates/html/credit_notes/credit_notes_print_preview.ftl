<html lang="en">
<head>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="${(realPath)!" "}styles/xms/css_global.css"/>
    <style type="text/css">
        @media print {
            @page {
                size: landscape)!" "
            }

        )!" "
        }
    </style>
</head>
<body style="background:#FFF">
<div>
    <!--END TOPBAR-->
    <div id="wrapper">
        <!--BEGIN SIDEBAR MENU-->
        <div id="page-wrapper">
            <!--BEGIN TITLE & BREADCRUMB PAGE-->
            <!--END TITLE & BREADCRUMB PAGE-->
            <!--BEGIN CONTENT-->
            <div class="portlet box" style="border:0px">
                <button class="btn s37 s44 flr" onclick="this.remove();window.print();"><i
                        class="fa fa-print fa-fw"></i> ${lang.translate('Prints')}</button>
                <div class="portlet-body" style="padding:0px;">
                    <div class="page-content" style="background:#FFF">
                        <div class="row">
                            <div class="col-xs-3">
                                <table class="table" style="font-size:11px; ">
                                    <tr>
                                        <td class="td1 b8"><img src="${(logo)!" "}" width="100%"
                                                                style="margin-top:-30px;"/></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-xs-9 text-left">
                                <table class="table" style="font-size:11px; ">
                                    <tr>
                                        <td class="td1 b8">
                                        ${(creditNote.infoSystemAdmin.systemAddress)!" "} <br/>
                                            <a href="${(creditNote.infoSystemAdmin.siteAddress)!" "}">${(creditNote.infoSystemAdmin.siteAddress)!" "}</a>
                                        </td>
                                        <td class="td1 b8">
                                            <h4>${lang.translate('Credit Notes')}</h4>
                                        </td>
                                    </tr>
                                </table>
                                <table class="table table-striped table-bordered mg0" style="font-size:11px; ">
                                    <tr>
                                        <th>${lang.translate('Credit Number')}</th>
                                        <th>${lang.translate('Credit Date')}</th>
                                        <th>${lang.translate('Customer #')}</th>
                                        <th>${lang.translate('Credits')}</th>
                                        <th>${lang.translate('Total Credited')}</th>
                                    </tr>
                                    <tr>
                                        <td>${(creditNote.creditNoteInfo.creditCode)!" "}</td>
                                        <td>${(creditNote.creditNoteInfo.createDate)!" "}</td>
                                        <td>${(creditNote.creditNoteInfo.customerCode)!" "}</td>
                                        <td align="right">${(creditNote.creditNoteInfo.credits)!"0"}</td>
                                        <td align="right">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.totalCredited)!"0"}</td>
                                    </tr>
                                </table>
                                <br/>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom:20px;">
                            <div class="col-xs-5">
                                <table class="table table-bordered mg0" style="font-size:11px; ">
                                    <tr>
                                        <td>
                                            <strong>${lang.translate('CREDIT TO:')}</strong>
                                            <br/>
									<span style="font-size: 12px; line-height: 145%;" id="lblbillingto">
									${(creditNote.creditNoteInfo.billingCustomerName)!" "}
                                        <br/>${(creditNote.creditNoteInfo.billingContactName)!" "} <br/>
                                    ${(creditNote.creditNoteInfo.billingAddress1)!" "}
                                        , ${(creditNote.creditNoteInfo.billingAddress2)!" "} <br/>
                                    ${(creditNote.creditNoteInfo.billingCity)!" "}
                                        , ${(creditNote.creditNoteInfo.countryName)!" "}
                                        , ${(creditNote.creditNoteInfo.billingPostalCode)!" "}
								</span>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-xs-7">
                                <table class="table table-bordered mg0" style="font-size:11px; ">
                                    <tr>
                                        <td>
                                            <strong>${lang.translate('MAIL PAYMENT TO:')}</strong>
                                            <br/> <span style="font-size: 12px; line-height: 145%;" id="lblbillingto">
                                        ${(creditNote.infoSystemAdmin.mailPaymentToAddress)!" "}
                                        </span>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <br/>
                        </div>
                        <div class="row">
                            <div class="col-xs-8 flr">
                                <table class="table table-bordered mg0" style="font-size:11px; ">
                                    <tr class="b1">
                                        <th>${lang.translate('GST Summary')}</th>
                                        <th>${lang.translate('GST Percent')}</th>
                                        <th>${lang.translate('Credit Amount')}</th>
                                        <th>${lang.translate('GST Amount')}</th>
                                        <th>${lang.translate('Total Amount')}</th>
                                    </tr>
                                <#if gstSummary?has_content>
                                    <#list gstSummary as summary>
                                        <tr>
                                            <#if summary.customerTaxPercent != '0.00'>
                                                <th class="b1">${lang.translate('GST Shipments')}</th>
                                            <#else>
                                                <th class="b1">${lang.translate('Non-GST Shipments')}</th>
                                            </#if>
                                            <td align="right">${(summary.customerTaxPercent)!"0"}%</td>
                                            <td align="right">${(currencySymbol)!"$"} ${(summary.customerAmount)!"0.00"}</td>
                                            <td align="right">${(currencySymbol)!"$"} ${(summary.gstCustomerAmount)!"0.00"}</td>
                                            <td align="right">${(currencySymbol)!"$"} ${(summary.total)!"0.00"}</td>
                                        </tr>
                                    </#list>
                                <#else>
                                    <tr>
                                        <th class="b1">${lang.translate('GST Shipments')}</th>
                                        <td align="right">${(creditNote.creditNoteInfo.taxPercent)!"0"}%</td>
                                        <td align="right">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.shipmentAmount)!"0.00"}</td>
                                        <td align="right">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.gstAmount)!"0.00"}</td>
                                        <td align="right">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.totalAmount)!"0.00"}</td>
                                    </tr>
                                    <tr>
                                        <th class="b1">${lang.translate('Non-GST Shipments')}</th>
                                        <td align="right">0.00%</td>
                                        <td align="right">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.nonShipmentAmount)!"0.00"}</td>
                                        <td align="right">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.nonGstAmount)!"0.00"}</td>
                                        <td align="right">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.nonShipmentAmount)!"0.00"}</td>
                                    </tr>
                                </#if>
                                </table>
                                <br/>
                            </div>
                            <div class="col-xs-11 flr">
                                <table class="table table-bordered table-hover mg0" style="font-size:11px; ">
                                    <thead>
                                    <tr>
                                        <th>${lang.translate('Invoice #')}</th>
                                        <th>${lang.translate('Airbill #')}</th>
                                        <th>${lang.translate('Invoice Date')}</th>
                                        <th>${lang.translate('Reason for Credit')}</th>
                                        <th>${lang.translate('Amount')}</th>
                                        <th>${lang.translate('GST Amount')}</th>
                                        <th>${lang.translate('Total Credit')}</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if creditNote.listCreditNoteAdj?has_content>
                                        <#list creditNote.listCreditNoteAdj as adj>
                                        <tr>
                                            <td>${(adj.invoiceCode)!" "}</td>
                                            <td>${(adj.airbillNumber)!" "}</td>
                                            <td>${(adj.invoiceDate)!" "}</td>
                                            <td>${(adj.reason)!" "}</td>
                                            <td align="right">${(currencySymbol)!"$"} ${(adj.shipmentAmount)!"0.00"}</td>
                                            <td align="right">${(currencySymbol)!"$"} ${(adj.gstAmount)!"0.00"}</td>
                                            <td align="right">${(currencySymbol)!"$"} ${(adj.totalCredit)!"0.00"}</td>
                                        </tr>
                                        </#list>
                                    </#if>
                                    </tbody>
                                    <tr>
                                        <td colspan="6" class="text-right"><b> ${lang.translate('GRAND TOTAL:')}</b>
                                        </td>
                                        <td align="right">${(currencySymbol)!"$"} ${(creditNote.creditNoteInfo.totalCredited)!"0.00"}</td>
                                    </tr>
                                </table>
                                <br/>
                            </div>
                        </div>
                    <#if invSignature?trim != ''>
                        <div class="row">
                            <p style="text-align: center;">${(invSignature)?html?replace("\n", "<br/>")!" "}</p>
                        </div>
                    </#if>
                    </div>
                </div>
            </div>
        </div>
        <!--END CONTENT-->
        <!--BEGIN FOOTER-->
        <!--END FOOTER-->
    </div>
    <!--END PAGE WRAPPER-->
</div>
</body>
</html>