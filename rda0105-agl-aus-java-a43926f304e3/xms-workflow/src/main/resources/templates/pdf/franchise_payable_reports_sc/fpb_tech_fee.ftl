<div class="page-break">
    <div class="title">
        <h3>${lang.translate('Technology Fee Details')}</h3>
    </div>
    <table class="border">
        <thead>
        <tr>
            <th width="100px;">${lang.translate('Import Date')}</th>
            <th>${lang.translate('Customer No')}</th>
            <th>${lang.translate('Customer Name')}</th>
            <th>${lang.translate('Invoice #')}</th>
            <th>${lang.translate('AWB/Connote No')}</th>
            <th>${lang.translate('International Domestic')}</th>
            <th>${lang.translate('Tech Fee on International Shipments')}</th>
            <th>${lang.translate('Tech Fee on Domestic Shipments')}</th>
        </tr>
        </thead>
        <tbody>
        <#include "fpb_tech_fee_content.ftl">
        <#if techFeeDetailsTotal?has_content>
        <tr>
            <td colspan="6" class="bold">${lang.translate('Total')}</td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(techFeeDetailsTotal.intlShipmentFee)!"0.00"}
                (${(techFeeDetailsTotal.intlShipmentCount)!"0.00"})
            </td>
            <td class="text-right bold">${(currencySymbol)!"$"}${(techFeeDetailsTotal.domShipmentFee)!"0.00"}
                (${(techFeeDetailsTotal.domShipmentCount)!"0.00"})
            </td>
        </tr>
        </#if>
        </tbody>
    </table>
</div>