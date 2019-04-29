<div class="page-break">
    <div class="title">
        <h3>${lang.translate('Shipment Details')}</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th rowspan="2">${lang.translate('Import Date')}</th>
            <th rowspan="2">${lang.translate('Customer No')}</th>
            <th rowspan="2">${lang.translate('Customer Name')}</th>
            <th rowspan="2">${lang.translate('Invoice #')}</th>
            <th rowspan="2">${lang.translate('AWB/Connote No')}</th>
            <th rowspan="2">${lang.translate('International Domestic')}</th>
            <th colspan="2">${lang.translate('Customer Total')}</th>
            <th rowspan="2">${lang.translate('Customer Marginable Cost')}</th>
            <th colspan="2">${lang.translate('Franchisee Cost')}</th>
            <th colspan="2">${lang.translate('Gross Margin')}</th>
            <th colspan="2">${lang.translate('Credits')}</th>
            <th rowspan="2">${lang.translate('Management Fees on Revenue')}</th>
            <th rowspan="2">${lang.translate('Marketing Fees on Revenue')}</th>
            <th rowspan="2">${lang.translate('Profit Share')}</th>
        </tr>
        <tr>
            <th>${lang.translate('Price Ex TVA')}</th>
            <th>${lang.translate('TVA')}</th>
            <th>${lang.translate('Price Ex TVA')}</th>
            <th>${lang.translate('TVA')}</th>
            <th>${lang.translate('Price Ex TVA')}</th>
            <th>${lang.translate('TVA')}</th>
            <th>${lang.translate('Franchisee Cost.')}</th>
            <th>${lang.translate('Customer Cost.')}</th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_shipment_details_content.ftl">
        <#if shipmentDetailsTotal?has_content>
        <tr>
            <td colspan="6"
                class="bold">${lang.translate('Total taxable shipment(s):')} ${(shipmentDetailsTotal.taxableShipmentCount)!"0"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.custCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.custTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.custMarginable)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.franCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.franTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.grossMargin)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.grossMarginTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.franCredit)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.custCredit)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.managementFee)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.marketingFee)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableShipmentTotal.profitShare)!"0.00"}</td>
        </tr>
        <tr>
            <td colspan="6"
                class="bold">${lang.translate('Total non-taxable shipment(s):')} ${(shipmentDetailsTotal.nonTaxableShipmentCount)!"0"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.custCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.custTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.custMarginable)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.franCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.franTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.grossMargin)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.grossMarginTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.franCredit)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.custCredit)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.managementFee)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.marketingFee)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.profitShare)!"0.00"}</td>
        </tr>
        <tr>
            <td colspan="6" class="bold">${lang.translate('Total')}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.custCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.custTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.custMarginable)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.franCost)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.franTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.grossMargin)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.grossMarginTax)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.franCredit)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.custCredit)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.managementFee)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.marketingFee)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(shipmentDetailsTotal.profitShare)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>