<#setting date_format="dd-MM-yyyy">
<html>
<head>
    <style>
        @page {
            size: A4 landscape;
        }

        body {
            font-family: Arial;
            font-size: 13px;
            color: #000;
            padding-left: 10px;
            padding-right: 10px;
        }

        table {
            border-collapse: collapse;
        }

        td, th {
            border: 1px solid #000;
        }
    </style>
</head>
<body>
<div>
    <center><h2>Shipping History Report</h2></center>
</div>


<table height="100%" width="100%" align="center" class="table-border">
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
        <th>
            Weight
        </th>
        <th>
            Quoted
        </th>
        <th>
            Insured Amount
        </th>
        <th>
            Scheduled
        </th>
        <th>
            Schcoll time stamp
        </th>
        <th>
            Collection Information
        </th>
        <th>
            Destination
        </th>
        <th>
            Dest. Country
        </th>
    </tr>
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
            <td>
            ${sm.weight}
            </td>
            <td>
            ${sm.total}
            </td>
            <td>
            ${sm.withInsurance}
            </td>
            <td>
            ${sm.schedule}
            </td>
            <td>
            ${sm.schcollTimeStamp}
            </td>
            <td>
            ${sm.confirmationNo}
            </td>
            <td>
            ${sm.destinations}
            </td>
            <td>
            ${sm.destCountry}
            </td>
        </tr>
    </#list>
</table>
<#else>
</table>
<h3>No data available...</h3>
</#if>

</body>
</html>