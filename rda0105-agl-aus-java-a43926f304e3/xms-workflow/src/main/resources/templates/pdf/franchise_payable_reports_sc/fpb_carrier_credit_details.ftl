<div class="page-break">
    <div class="title">
        <h3>${lang.translate('Carrier Credit Details')}</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th colspan="7"></th>
            <th colspan="6"><i>${lang.translate('Original Invoice')}</i></th>
            <th colspan="4"><i>${lang.translate('Credit')}</i></th>
            <th colspan="2"></th>
        </tr>
        <tr>
            <th width="100px;" rowspan="2">${lang.translate('Date')}</th>
            <th rowspan="2">${lang.translate('Customer No')}</th>
            <th rowspan="2">${lang.translate('Customer Name')}</th>
            <th rowspan="2">${lang.translate('Invoice #')}</th>
            <th rowspan="2">${lang.translate('Credit Note Number')}</th>
            <th rowspan="2">${lang.translate('AWB/Connote No')}</th>
            <th rowspan="2">${lang.translate('International Domestic')}</th>
            <th colspan="2">${lang.translate('Customer Total')}</th>
            <th colspan="2">${lang.translate('Franchisee Cost')}</th>
            <th colspan="2">${lang.translate('Gross Margin')}</th>
            <th colspan="2">${lang.translate('Customer Total')}</th>
            <th colspan="2">${lang.translate('Franchisee Cost')}</th>
            <th colspan="2">${lang.translate('New Margin')}</th>
        </tr>
        <tr>
            <th>${lang.translate('Price Ex TVA')}</th>
            <th>${lang.translate('TVA')}</th>
            <th>${lang.translate('Price Ex TVA')}</th>
            <th>${lang.translate('TVA')}</th>
            <th>${lang.translate('Price Ex TVA')}</th>
            <th>${lang.translate('TVA')}</th>
            <th>${lang.translate('Price Ex TVA')}</th>
            <th>${lang.translate('TVA')}</th>
            <th>${lang.translate('Price Ex TVA')}</th>
            <th>${lang.translate('TVA')}</th>
            <th>${lang.translate('Price Ex TVA')}</th>
            <th>${lang.translate('TVA')}</th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_carrier_credit_details_content.ftl">
        <#if carrierCreditDetailsTotal?has_content>
        <tr>
            <td class="bold"
                colspan="7">${lang.translate('Total taxable shipment(s):')} ${(carrierCreditDetailsTotal.taxableShipmentCount)!"0"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.customerTotalExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.customerTotalGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.franchiseCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.franchiseCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.grossMarginExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.grossMarginGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.creditsCustomerCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.creditsCustomerCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.creditsFranchiseCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.creditsFranchiseCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.newMarginExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.newMarginGst)!"0.00"}</td>
        </tr>
        <tr>
            <td class="bold"
                colspan="7">${lang.translate('Total non-taxable shipment(s):')} ${(carrierCreditDetailsTotal.nonTaxableShipmentCount)!"0"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.customerTotalExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.customerTotalGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.franchiseCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.franchiseCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.grossMarginExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.grossMarginGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.creditsCustomerCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.creditsCustomerCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.creditsFranchiseCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.creditsFranchiseCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.newMarginExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.newMarginGst)!"0.00"}</td>
        </tr>
        <tr>
            <td class="bold" colspan="7">${lang.translate('Total')}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.customerTotalExcGst)!"0"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.customerTotalGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.franchiseCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.franchiseCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.grossMarginExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.grossMarginGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.creditsCustomerCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.creditsCustomerCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.creditsFranchiseCostExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.creditsFranchiseCostGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.newMarginExcGst)!"0.00"}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.newMarginGst)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>