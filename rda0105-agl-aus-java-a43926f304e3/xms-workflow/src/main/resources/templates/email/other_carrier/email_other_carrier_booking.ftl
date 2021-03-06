Dear ${(data.franchiseName)?html!" "},<br/>
<br/>I would like to ship the following shipment ${(data.airbillNumber)?html!" "} with ${(data.carrierName)?html!" "}.
<br/>Please assist to arrange a collection from following address. <br/><br/>Package Information<br/>
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">Package Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Ship Date
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.shipmentDate)!" "}
            (YYYY-MM-DD)
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Service Type
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.shipmentTypeName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Package Type
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.packageName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            No of Pieces
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.noOfPieces)!"0"}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Total estimated weight *
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            2.00
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Weight Unit
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            kg
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Dimension
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"><#list data.dimensions as dim>${dim}
            <br/></#list><br/></td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Collection Instruction
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.collectionInstruction)!"0"}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Insurance
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.insurance)!"No"}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Total Shipment Value
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.totalShipmentValue)!"0.00"}</td>
    </tr>
</table>
<br/><br/>Sender Contact and Address Information<br/>
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">Sender Contact Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Contact Name
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.contactName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Company Name
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.companyName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Telephone *
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.phone)?html!" "}</td>
    </tr>
</table>
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Sender Address Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 1 *
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.address)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 2
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.address2)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            City
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.city)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            State/Province
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.state)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Country
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.countryDetail.countryName)!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Postal Code
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.postalCode)!" "}</td>
    </tr>
</table>
<br/><br/>Receiver Contact and Address Information<br/>
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Receiver Contact Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Contact Name
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.contactName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Company Name
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.companyName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Telephone *
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.phone)?html!" "}</td>
    </tr>
</table>
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Receiver Address Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 1 *
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.address)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 2
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.address2)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            City
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.city)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            State/Province
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.state)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Country
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.countryDetail.countryName)!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Postal Code
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.postalCode)?html!" "}</td>
    </tr>
</table>
<br/> <br/>Thanks,<br/><br/>${(data.customerName)?html!" "}(${(data.customerCode)!" "})