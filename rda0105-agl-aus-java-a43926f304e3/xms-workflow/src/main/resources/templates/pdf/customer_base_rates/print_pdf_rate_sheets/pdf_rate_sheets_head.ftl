<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        @page {
            size: A4;
            margin: 5mm 5mm;
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

        .page-break {
            page-break-before: always;
        }
    </style>
</head>
<body>