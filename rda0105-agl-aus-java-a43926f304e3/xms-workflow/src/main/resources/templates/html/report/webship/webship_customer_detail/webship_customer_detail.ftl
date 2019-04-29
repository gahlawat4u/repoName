<!DOCTYPE html>
<html>
<head>
    <title>${lang.translate('Webship Customer Details Print')}</title>
    <style type="text/css">
        @page {
            size: A4 landscape;
        }

        body {
            font-family: Tahoma, Geneva, sans-serif;
            font-size: 11px;
            color: #848383;
            text-shadow: none;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            margin-bottom: 20px;
        }

        table thead {
            background-color: #f9f9f9;
            font-weight: bold;
        }

        .no-border {
            border: none;
        }

        .border {
            border-collapse: collapse;
        }

        .border thead tr:first-child th {
            border: 1px solid #ddd;
            border-bottom: none;
        }

        .border thead tr th {
            border: 1px solid #ddd;
            font-size: 12px;
        }

        .border tbody tr td {
            border: 1px solid #ddd;
            padding: 4px;
        }

        .border tr td {
            border: 1px solid #ddd;
            padding: 4px;
        }

        .border tr th {
            border: 1px solid #ddd;
            padding: 4px;
            text-align: left;
        }

        .img {
            cursor: pointer;
        }

        .img:hover {
            opacity: 0.7
        }
    </style>
</head>
<body>
<table style="margin-bottom: 0px;">
    <tr>
        <td>
            <table>
                <tr>
                    <td align="center"><span
                            style="font-size: 14px; font-weight: bold">${lang.translate('Webship Customer Details')}</span>
                    </td>
                    <td align="right" width="25"><img src="${(realPath)!" "}images/printer67.png" width="25" class="img"
                                                      onclick="this.remove();window.print();"/></td>
                </tr>

            </table>
        </td>
    </tr>
</table>
<table class="border">
    <thead>
    <tr>
        <th>${lang.translate('Invoice #')}</th>
        <th>${lang.translate('Airbill #')}</th>
        <th>${lang.translate('Customer #')}</th>
        <th>${lang.translate('Shipment Date')}</th>
        <th>${lang.translate('Sender Address')}</th>
        <th>${lang.translate('Receiver Address')}</th>
        <th>${lang.translate('Pieces')}</th>
        <th>${lang.translate('Weight')}</th>
        <th>${lang.translate('Quoted')}</th>
        <th>${lang.translate('Charges')}</th>
        <th>${lang.translate('Total')}</th>
        <th>${lang.translate('Service Type')}</th>
        <th>${lang.translate('Accessorial')}</th>
    </tr>
    </thead>
    <tbody>
    <#if details?has_content>
        <#list details as detail>
        <tr>
            <td>${(detail.invoiceCode)!" "}</td>
            <td>${(detail.airbillNumber)!" "}</td>
            <td>${(detail.customerCode)!" "}</td>
            <td>${(detail.shipmentDate)!" "}</td>
            <td>${(detail.senderCompanyName)!" "}<br/>${(detail.senderContactName)!" "}
                <br/>${(detail.senderAddress1)!" "}<br/>${(detail.senderAddress2)!" "}
                <br/>${(detail.senderCity)!" "}${(detail.senderState)!" "}${(detail.senderCountryName)!" "}</td>
            <td>${(detail.receiverCompanyName)!" "}<br/>${(detail.receiverContactName)!" "}
                <br/>${(detail.receiverAddress1)!" "}<br/>${(detail.receiverAddress2)!" "}
                <br/>${(detail.receiverCity)!" "}${(detail.receiverState)!" "}${(detail.receiverCountryName)!" "}</td>
            <td>${(detail.noOfPieces)!" "}</td>
            <td>${(detail.weight)!" "}${(detail.weightUnit)!" "}</td>
            <td>${(detail.quoted)!" "}</td>
            <td>
                <#if detail.charges?has_content>
                    <#list detail.charges as charge>
                    ${(charge.description)!" "} - ${(currencySymbol)!"$"}${(charge.customerCost)!"0.00"}
                        (${(currencySymbol)!"$"}${(charge.franchiseCost)!"0.00"}
                        )[${(currencySymbol)!"$"}${(charge.margin)!"0.00"}]<br/>
                    </#list>
                </#if>
            </td>
            <td>${(currencySymbol)!"$"}${(detail.totalCustomerCost)!"0.00"}
                <br/>(${(currencySymbol)!"$"}${(detail.totalFranchiseCost)!"0.00"}
                )<br/>${lang.translate('Mrg:')}${(currencySymbol)!"$"}${(detail.totalMargin)!"0.00"}</td>
            <td>${(detail.serviceType)!" "}</td>
            <td>
            ${lang.translate('Insurance:')}<#if detail.insurance == 'true'>${lang.translate('Yes')}<#else>${lang.translate('No')}</#if>
                <br/>
            ${lang.translate('Duties/Taxes Fee:')} ${(detail.dutiesTaxesFee)!" "}<br/>
            ${lang.translate('Dangerous Goods:')}<#if detail.dangerousGoods == 'true'>${lang.translate('Yes')}<#else>${lang.translate('No')}</#if>
                <br/>
            ${lang.translate('Pre Clearance:')}<#if detail.preClearance == 'true'>${lang.translate('Yes')}<#else>${lang.translate('No')}</#if>
                <br/>
                <#if detail.charges?has_content>
                    <#list detail.charges as charge>
                        <#if charge.isBaseCharge != 'true'>
                        ${(charge.description)!" "}<br/>
                        </#if>
                    </#list>
                </#if>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
</body>
</html>