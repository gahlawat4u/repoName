<!DOCTYPE html>
<html>
<head>
    <title>${(lang.translate('Search Payments / Deposit SlipPrint'))!" "}</title>
    <style type="text/css">
        @page {
            size: A4;
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
            text-align: left;
            padding: 4px;
        }

        .border tbody tr td {
            border: 1px solid #ddd;
            padding: 4px;
        }

        .border tr td {
            border: 1px solid #ddd;
            padding: 4px;
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
                            style="font-size: 14px; font-weight: bold">${(lang.translate('Search Payments / Deposit Slip'))!" "}</span>
                    </td>
                    <td align="right" width="25"><img src="${(realPath)!" "}images/printer67.png"
                                                      onclick="this.remove();window.print();" width="25" class="img"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table class="border">
    <thead>
    <tr>
        <th>${(lang.translate('Amount'))!" "}</th>
        <th>${(lang.translate('Cheque #'))!" "}</th>
    <#if flags.hasDate>
        <th>${(lang.translate('Date'))!" "}</th>
    </#if>
    <#if flags.hasCustomerNo>
        <th>${(lang.translate('Customer #'))!" "}</th>
    </#if>
    <#if flags.hasCustomerName>
        <th>${(lang.translate('Customer Name'))!" "}</th>
    </#if>
    <#if flags.hasInvoice>
        <th>${(lang.translate('Invoice(s)'))!" "}</th>
    </#if>
    <#if flags.hasNotes>
        <th>${(lang.translate('Notes'))!" "}</th>
    </#if>
    <#if flags.hasTypeOfOverpayment>
        <th>${(lang.translate('Types of Overpayment'))!" "}</th>
    </#if>
    </tr>
    </thead>
    <tbody>
    <#if listData?has_content>
        <#list listData as dt>
        <tr>
            <td align="right">${(currencySymbol)!"$"}${(dt.amount)!" "}</td>
            <td>${(dt.cheque)!" "}</td>
            <#if flags.hasDate>
                <td>${(dt.paymentDate)!" "}</td>
            </#if>
            <#if flags.hasCustomerNo>
                <td>${(dt.customerCode)!" "}</td>
            </#if>
            <#if flags.hasCustomerName>
                <td>${(dt.customerName)!" "}</td>
            </#if>
            <#if flags.hasInvoice>
                <td>${(dt.invoiceList)!" "}</td>
            </#if>
            <#if flags.hasNotes>
                <td>${(dt.note)!" "}</td>
            </#if>
            <#if flags.hasTypeOfOverpayment>
                <td>${(dt.overPaymentType)!" "}</td>
            </#if>
        </tr>
        </#list>
    </#if>
    <tr>
        <td colspan="7"><span
                class="b4"> <b>${(lang.translate('Total:'))!" "}</b> ${(currencySymbol)!"$"}${(total.total)!" "}
				</span> <span
                class="b4"> <b>| ${(lang.translate('Total line items:'))!" "}</b> ${(total.totalReceived)!" "}
				</span></td>
    </tr>
    </tbody>
</table>
</body>
</html>