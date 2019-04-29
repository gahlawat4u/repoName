<#if listAirbills?has_content>
    <#list listAirbills as awb>
    <tr>
        <td>${(awb.invoiceCode?html)!" "} <br/>${(awb.serviceName?html)!" "} - ${(awb.airbillNumber)!" "}
            <br/> <#if awb.serviceAreaCodeOrigin?? || awb.serviceAreaCodeDestination??>${(awb.serviceAreaCodeOrigin)!" "}
                /${(awb.serviceAreaCodeDestination)!" "}</#if><br/>${(awb.shipmentDate)!" "}
            <br/>${(awb.customerCode)!" "}<br/>${(awb.shipperReference?html)!" "}
            <br/>${(awb.billingReference2?html)!" "}</td>
        <td>${(awb.senderCompanyName?html)!" "} <br/>${(awb.senderContactName?html)!" "}
            <br/>${(awb.senderAddress?html)!" "}<br/><#if awb.senderAddress2 != ''>${(awb.senderAddress2?html)!" "}
                <br/></#if>${(awb.senderCity?html)!" "} - ${(awb.senderStates?html)!" "} - ${(awb.senderPostalCode)!" "}
            <br/>${(awb.senderCountry?html)!" "}</td>
        <td>${(awb.receiverCompanyName?html)!" "} <br/>${(awb.receiverContactName?html)!" "}
            <br/>${(awb.receiverAddress?html)!" "}<#if awb.receiverAddress2 != ''>${(awb.receiverAddress2?html)!" "}
                <br/></#if><br/>${(awb.receiverCity?html)!" "} - ${(awb.receiverStates?html)!" "}
            - ${(awb.receiverPostalCode)!" "}<br/>${(awb.receiverCountry?html)!" "}</td>
        <td>${(awb.noOfPieces)!" "}<br/>${(awb.weight)!" "}<br/>${(awb.zone)!" "}</td>
        <td><#if awb.listCharge?has_content><#list awb.listCharge as charge>${(charge.chargeDescription?html)!" "}
            ( ${(currencySymbol)!"$"}${(charge.customerCost)!"0"} )[ ${(currencySymbol)!"$"}${(charge.margin)!"0"} ]
            <br/></#list></#if>
            <#if awb.gstTaxAmount?? && awb.gstTaxAmount != '0.0'>
                <br/>${(lang.translate('GST - '))!" "}${(currencySymbol)!"$"}${(awb.gstTaxAmount)!"0"}</#if>
            <#if awb.listAdjustment?has_content>
                <#list awb.listAdjustment as adj><font color="red">${(awb.adjustmentType)!" "}
                    - ${(currencySymbol)!"$"}${(customerAmount)!"0"} [${(currencySymbol)!"$"}${(carrierAmount)!"0"}
                    ]</font></#list>
            </#if>
        </td>
        <td>${(currencySymbol)!"$"} ${(awb.totalCustomerAmount)!"0"}
            <br/>( ${(currencySymbol)!"$"} ${(awb.totalFranchiseAmount)!"0"} )
            <br/>${(lang.translate('Mrg:'))!" "}${(currencySymbol)!"$"} ${(awb.totalMargin)!"0"}</td>
    </tr>
    </#list>
</#if>