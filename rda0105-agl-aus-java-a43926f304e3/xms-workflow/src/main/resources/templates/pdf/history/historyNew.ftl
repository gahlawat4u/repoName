<#setting date_format="dd-MM-yyyy">
<html>
<head>
    <style>
        @page {
            size: A4 landscape;
            margin: 5mm 5mm;
            @bottom-right {
                font-family: Arial;
                font-size: 10px;
                content: counter(page);
            }
        }

        body {
            font-family: Arial;
            font-size: 8px;
            color: #000;
            padding-left: 10px;
            padding-right: 10px;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            margin-bottom: 5px;
            page-break-inside: auto
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
<div>
    <center><h2>Shipping History Report</h2></center>
</div>


<table class="border" style="width: 100% !important;" align="center">
    <thead>
    <tr>

        <th width="95px">
            Carrier
        </th>
        <th>
            Voided
        </th>
        <th>
            Tracking#
        </th>
        <th>
            Date
        </th>
        <th>
            Time Stamp
        </th>
        <th>
            Ship Date
        </th>
        <th>
            Pieces
        </th>
        <th>
            Service
        </th>
        <#if packageName?has_content>
            <th>
                Package
            </th>
        </#if>
        <th>
            Weight
        </th>
        <#if dimensions?has_content>
            <th>
                Dimension
            </th>
        </#if>
        <th>
            Quoted
        </th>
        <#if withInsurance?has_content>
            <th>
                Insured Amount
            </th>
        </#if>
        <th>
            Scheduled
        </th>
        <th>
            Scheduled Time Stamp
        </th>
        <th>
            Collection Information
        </th>
        <#if reference?has_content>
        <th>
            Reference
        </th>
        </#if>
        <#if billingParty?has_content>
        <th>
            Billing Party
        </th>
        </#if>
        <#if senderCompany?has_content>
            <th>
                Sender Company
            </th>
        </#if>
        <#if senderName?has_content>
            <th>
                Sender Contact
            </th>
        </#if>
        <#if senderLocation?has_content>
            <th>
                Sender Location
            </th>
        </#if>
        <#if reciverCompany?has_content>
            <th>
                Receiver Company
            </th>
        </#if>
        <#if reciverContact?has_content>
            <th>
                Receiver Contact
            </th>
        </#if>
        <th>
            Destination
        </th>
        <th>
            Dest. Country
        </th>
    </tr>
</thead>
<tbody>
<#if historyModels?has_content>
    <#list historyModels as sm>
        <tr>
            <td width="95px">
            ${sm.serviceName}
            </td>
            <td>
            ${sm.voidStatus}
            </td>
            <td>

            ${sm.airbillNumber}
            </td>
            <td>
            ${sm.createDate}
            </td>
            <td>
            ${sm.timeStamp}
            </td>
            <td>
            ${sm.shipDate}
            </td>
            <td>
            ${sm.noOfPieces}
            </td>
            <td>
            ${sm.shipmentType}
            </td>
            <#if packageName?has_content>
                <td>
                    ${sm.packageName}
                </td>
            </#if>
            <td>
            ${sm.weight}
            </td>
            <#if dimensions?has_content>
                <td>
                    ${sm.dimensions}
                </td>
            </#if>
            <td>
            ${sm.total}
            </td>
            <#if withInsurance?has_content>
                <td>
                ${sm.withInsurance}
                </td>
            </#if>
            <td>
            ${sm.schedule}
            </td>
            <td>
            ${sm.schcollTimeStamp}
            </td>
            <td>
            ${sm.confirmationNo}
            </td>
            <#if reference?has_content>
                <td>
                    ${sm.reference}
                </td>
            </#if>
            <#if billingParty?has_content>
                <td>
                    ${sm.billingParty}
                </td>
            </#if>
            <#if senderCompany?has_content>
                <td>
                    ${sm.senderCompany}
                </td>
            </#if>
            <#if senderName?has_content>
                <td>
                    ${sm.senderName}
                </td>
            </#if>
            <#if senderLocation?has_content>
                <td>
                    ${sm.senderLocation}
                </td>
            </#if>
            <#if reciverCompany?has_content>
                <td>
                    ${sm.reciverCompany}
                </td>
            </#if>
            <#if reciverContact?has_content>
                <td>
                    ${sm.reciverContact}
                </td>
            </#if>
            <td>
            ${sm.destinations}
            </td>
            <td>
            ${sm.destCountry}
            </td>
        </tr>
    </#list>
</tbody>
</table>
<#else>
</table>
<h3>No data available...</h3>
</#if>

</body>
</html>