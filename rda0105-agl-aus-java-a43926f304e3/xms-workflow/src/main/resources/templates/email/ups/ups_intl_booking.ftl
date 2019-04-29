Dear ${(data.franchiseName)?html!" "},<br/>
<br/>I would like to ship the following shipment ${(data.airbillNumber)?html!" "} with ${(data.carrierName)?html!" "}.
<br/>Please assist to arrange a collection from following address.

<#if data.scheduleInfo?has_content>
<br/><br/>Schedule and Package Information<br/>
<#else>
<br/><br/>Package Information<br/>
</#if>

<br/>
<#if data.scheduleInfo?has_content>
<table border="0" >
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Schedule Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Pickup Date
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupDate)!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Package Pickup Time
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupTime)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Package Pickup no later than
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupTimeNoLater)?html!" "}</td>
    </tr>
</table>
</#if>


<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Package Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Shipment Date
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.shipmentDate)!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Service Type
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.shipmentTypeName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Package Type
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.packageName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            No of pieces
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.noOfPieces)!"0"}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Total estimated weight
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.totalWeight)!"0.0"}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Weight Unit
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            kg
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Dimension
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"><#list data.dimensions as dim>${dim}
            <br/></#list></td>
    </tr>

</table>
<#if data.scheduleInfo?has_content>
<br/><br/>Pickup Contact and Address Information<br/>
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Pickup Contact Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Contact Name
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupAddress.contactName)!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Company Name
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupAddress.companyName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Telephone
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupAddress.phone)?html!" "}</td>
    </tr>
</table>

<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Pickup Address Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 1
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupAddress.address)!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 2
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupAddress.address2)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            City
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupAddress.city)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            State/Province
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupAddress.state)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Postal code
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.scheduleInfo.pickupAddress.postalCode)?html!" "}</td>
    </tr>
</table>

<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Pickup Contact Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Location Description
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.locationDescription)!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Location Code
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.locationCode)?html!" "}</td>
    </tr>
</table>
</#if>

<br/><br/>Sender Contact and Address Information<br/>
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Sender Contact Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            COMPANY
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.companyName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            CONTACT PERSON
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.contactName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Telephone
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.phone)?html!" "}</td>
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
            Address line 1
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.address)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 2
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.address2)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            City
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.city)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            State/Province
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.state)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Postal code
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.senderAddress.postalCode)!" "}</td>
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
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            COMPANY
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.companyName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            CONTACT PERSON
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.contactName)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Telephone
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.phone)?html!" "}</td>
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
            Address line 1
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.address)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 2
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.address2)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            City
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.city)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            State/Province
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.state)?html!" "}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Postal code
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${(data.receiverAddress.postalCode)?html!" "}</td>
    </tr>
</table>
<br/> <br/>Thanks,<br/><br/>${(data.customerName)?html!" "}(${(data.customerCode)!" "})