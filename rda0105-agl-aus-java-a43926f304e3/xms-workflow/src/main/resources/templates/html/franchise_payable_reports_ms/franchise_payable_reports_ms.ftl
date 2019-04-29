<html>
<head>
    <style type="text/css">
        @page {
            size: 40cm 25cm;
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
            font-weight: bold;
            font-size 12px;
        }

        .text-bold {
            font-weight: bold;
        }

        .title {
            margin: 0 auto;
            text-align: center;
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
        }

        .border tbody tr td {
            border: 1px solid #000;
        }

        .border tbody tr:last-child td {
            font-weight: bold;
        }

        .text-right {
            text-align: right;
        }
    </style>
</head>
<body>
<#include "fpb_overview.ftl">
	<#include "fpb_margin.ftl">
	<#include "fpb_credit.ftl">
	<#include "fpb_deduct.ftl">
	<#include "fpb_61days.ftl">
	<#if enableNonCentralizedTab>
    <#include "fpb_noncentral.ftl">
</#if>
	<#include "fpb_overpayment.ftl">
</body>
</html>