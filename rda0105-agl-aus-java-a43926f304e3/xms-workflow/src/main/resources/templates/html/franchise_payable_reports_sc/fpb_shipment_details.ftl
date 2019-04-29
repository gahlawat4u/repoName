<div>
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
                class="total">${lang.translate('Total taxable shipment(s):')} ${(shipmentDetailsTotal.taxableShipmentCount)!"0"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.custCost)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.custTax)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.custMarginable)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.franCost)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.franTax)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.grossMargin)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.grossMarginTax)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.franCredit)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.custCredit)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.managementFee)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.marketingFee)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(taxableShipmentTotal.profitShare)!"0.00"}</td>
        </tr>
        <tr>
            <td colspan="6"
                class="total">${lang.translate('Total non-taxable shipment(s):')} ${(shipmentDetailsTotal.nonTaxableShipmentCount)!"0"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.custCost)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.custTax)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.custMarginable)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.franCost)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.franTax)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.grossMargin)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.grossMarginTax)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.franCredit)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.custCredit)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.managementFee)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.marketingFee)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(nonTaxableShipmentTotal.profitShare)!"0.00"}</td>
        </tr>
        <tr>
            <td colspan="6" class="total">${lang.translate('Total')}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.custCost)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.custTax)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.custMarginable)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.franCost)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.franTax)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.grossMargin)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.grossMarginTax)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.franCredit)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.custCredit)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.managementFee)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.marketingFee)!"0.00"}</td>
            <td align="right" class="total">${(currencySymbol)!"$"}${(shipmentDetailsTotal.profitShare)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>