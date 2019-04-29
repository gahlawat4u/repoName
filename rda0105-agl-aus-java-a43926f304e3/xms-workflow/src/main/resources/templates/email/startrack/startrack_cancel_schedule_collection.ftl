Dear ${customerFranchiseInfoVo.franchiseName},<br/>
<br/>The following schedule collection ${historyDetailScheduleInfoVo.airbillNumber} had been cancelled.
<br /><br />
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">

            Cancellation Information
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Reason
        </td>

        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${reasonCode}
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Collection number
        </td>

        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${shipmentScheduleCollectionVo.confirmationNo}
        </td>
    </tr>
</table><br/><br/>Schedule and Package Information<br/>
<table border="0">
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
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.pickupDate} (YYYY-MM-DD)</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Package Pickup Time
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.pickupTime}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Package Pickup no later than
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.pickupTimeNoLater}</td>
    </tr>
</table>
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Package Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Service Type
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentTypeVo.shipmentTypeName}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Package Type
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${packageVo.packageName}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            No of pieces
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.noOfPieces}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Total estimated weight
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.totalWeight}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Weight Unit
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.scheduleWeightUnit}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Special Instruction
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.specialInstructions} &nbsp</td>
    </tr>
</table><br/><br/>Pickup Contact and Address Information<br/>
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
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.contactName}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Company Name
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.companyName}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Telephone
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.phone} &nbsp;</td>
    </tr>
</table>
<br/><br/>Receiver Contact and Address Information<br/>
<table border="0">
    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">
            Pickup Address Information
        </td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 1
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.address}</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 2
        </td>
        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.address2}&nbsp;</td>
    </tr>
    <tr style="vertical-align:top;">
        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            City
        </td>
        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">${shipmentScheduleCollectionVo.city}</td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            State/Province
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${shipmentScheduleCollectionVo.state}&nbsp;</td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Postal code
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${shipmentScheduleCollectionVo.postalCode}&nbsp;</td>
    </tr>
</table>
<table border="0">

    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">

            Pickup Location Information
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Location Description
        </td>

        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${shipmentScheduleCollectionVo.description}&nbsp;</td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Location Code
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${locationCodeVo.locationCodeDescription}&nbsp;</td>
    </tr>
</table><br/><br/>Sender Contact and Address Information<br/>
<table border="0">

    <tr bgcolor="#fde4d0" style="vertical-align:bottom; height: 30px;">
        <td style="border: 1px solid #f9b074; border-width: 1px 1px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;"
            colspan="2">

            Sender Contact Information
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="width:180px; border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Contact Name
        </td>

        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.shiContactName}
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Company Name
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.shiCompanyName}
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Telephone
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.shiPhone}&nbsp;</td>
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

        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.shiAddress}
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 2
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.shiAddress2}&nbsp;</td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            City
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.shiCity}
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            State/Province
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.shiState}&nbsp;</td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Postal code
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.shiPostalCode}&nbsp;</td>
    </tr>
</table><br/><br/>Receiver Contact and Address Information<br/>
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

        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.recContactName}
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Company Name
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.recCompanyName}
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Telephone
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.recPhone}&nbsp;</td>
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

        <td style="width:250px; border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.recAddress}
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Address line 2
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.recAddress2}&nbsp;</td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            City
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.recCity}
        </td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            State/Province
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.recState}&nbsp;</td>
    </tr>
    <tr style="vertical-align:top;">

        <td style="border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
            Postal code
        </td>

        <td style="border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 10pt Arial, sans-serif; color: #142b71;">
        ${historyDetailScheduleInfoVo.recPhone}&nbsp;</td>
    </tr>
</table><br/> <br/>Thanks,<br/><br/>${customerFranchiseInfoVo.customerName}(${shipmentScheduleCollectionVo.customerCode})