<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        @page {
            size: A4;
            margin: 5mm 5mm;
            @bottom-right {
                font-family: Arial;
                font-size: 10px;
                content: counter(page);
            }
        }

        body {
            font-family: Arial;
            font-size: 9px;
            color: #000;
            padding-left: 20px;
            padding-right: 20px;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            margin-bottom: 5px;
            page-break-inside: auto
        }

        table thead {
            background-color: #c5c5c5;
        }

        table tr {
            page-break-inside: avoid;
            page-break-after: auto
        }

        .no-border {
            border: none;
        }

        .border {
            border-collapse: collapse;
        }

        .border thead tr:first-child th {
            border: 1px solid #000;
            border-bottom: none;
        }

        .border thead tr th {
            border: 1px solid #000;
            font-size: 9px;
        }

        .border tbody tr td {
            border: 1px solid #000;
            padding: 1px;
        }

        .border tr td {
            border: 1px solid #000;
            padding: 1px;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td width="25%">
            <img src="${(logo)!" "}" width="200px"/>
        </td>
        <td width="75%" align="center">
            <h3>${(lang.translate('Search Airbills'))!" "}</h3>
        </td>
    </tr>
</table>
<table class="border" style="width: 100% !important; float: right">
    <thead>
    <tr bgcolor="#fff">
        <td><strong>${lang.translate('Carrier - Airbill #')} <br/> ${lang.translate('Orig/Dest')}
            <br/> ${lang.translate('Ship Date')} <br/> ${lang.translate('Customer #')}
            <br/>${lang.translate('Reference')} <br/>${lang.translate('Reference 2')}</strong></td>
        <td><strong>${lang.translate('Sender Address')}</strong></td>
        <td><strong>${lang.translate('Receiver Address')}</strong></td>
        <td><strong>${lang.translate('Pieces')} <br/>${lang.translate('Weight')} <br/>${lang.translate('Dimensions')}
            <br/>${lang.translate('Zone')} </strong></td>
        <td><strong>${lang.translate('Charges')}</strong></td>
        <td><strong>${lang.translate('Total')}</strong></td>
    </tr>
    </thead>
    <tbody>