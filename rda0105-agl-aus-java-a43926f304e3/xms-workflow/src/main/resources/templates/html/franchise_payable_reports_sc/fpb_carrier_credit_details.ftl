<div>
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
            <td class="total"
                colspan="7">${lang.translate('Total taxable shipment(s):')} ${(carrierCreditDetailsTotal.taxableShipmentCount)!"0"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.customerTotalExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.customerTotalGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.franchiseCostExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.franchiseCostGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.grossMarginExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.grossMarginGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.creditsCustomerCostExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.creditsCustomerCostGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.creditsFranchiseCostExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.creditsFranchiseCostGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.newMarginExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(taxableCarrierCreditTotal.newMarginGst)!"0.00"}</td>
        </tr>
        <tr>
            <td class="total"
                colspan="7">${lang.translate('Total non-taxable shipment(s):')} ${(carrierCreditDetailsTotal.nonTaxableShipmentCount)!"0"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.customerTotalExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.customerTotalGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.franchiseCostExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.franchiseCostGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.grossMarginExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.grossMarginGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.creditsCustomerCostExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.creditsCustomerCostGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.creditsFranchiseCostExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.creditsFranchiseCostGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.newMarginExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(nonTaxableCarrierCreditTotal.newMarginGst)!"0.00"}</td>
        </tr>
        <tr>
            <td class="total" colspan="7">${lang.translate('Total')}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.customerTotalExcGst)!"0"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.customerTotalGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.franchiseCostExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.franchiseCostGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.grossMarginExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.grossMarginGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.creditsCustomerCostExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.creditsCustomerCostGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.creditsFranchiseCostExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.creditsFranchiseCostGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.newMarginExcGst)!"0.00"}</td>
            <td align="right"
                class="total">${(currencySymbol)!"$"}${(carrierCreditDetailsTotal.newMarginGst)!"0.00"}</td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>