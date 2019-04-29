<html>
<head>
    <style type="text/css">
        @page {
            size: 50cm 20cm;
        }

        body {
            font-family: Arial;
            font-size: 12px;
        }

        table {
            width: 100%;
            font-size: 12px;
        }

        table th {
            font-size 12px;
            text-align: center;
        }

        .bold {
            font-weight: bold;
        }

        .title {
            margin: 0 auto;
            text-align: center;
            font-size: 19px;
        }

        .page-break {
            page-break-after: always;
        }

        .no-border {
            border: 0;
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
            font-size: 12px;
            background-color: #c5c5c5;
            font-weight: normal;
        }

        .border tbody tr td {
            border: 1px solid #000;
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
    </style>
</head>
<body>
<#include "fpb_overview.ftl">
	<#include "fpb_shipment_details.ftl">
	<#include "fpb_carrier_credit_details.ftl">
	<#include "fpb_tech_fee.ftl">
	<#include "fpb_overpayment.ftl">
</body>
</html>