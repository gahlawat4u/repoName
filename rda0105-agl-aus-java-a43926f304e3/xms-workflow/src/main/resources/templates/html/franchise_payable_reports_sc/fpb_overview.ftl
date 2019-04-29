<#setting date_format="dd-MM-yyyy">
<div class="page-break">
    <div class="title">
        <h3>Overview</h3>
    </div>
    <table class="no-border">
        <thead>
        <th width="30%">${lang.translate('Franchise Info')}</th>
        <th width="30%">${lang.translate('Activity')}</th>
        <th width="40%">${lang.translate('Summary')}</th>
        </thead>
        <tbody>
        <tr>
            <td valign="top">
                <table>
                    <tr>
                        <td>${lang.translate('Name')}</td>
                    <#if franchiseName == "">
                        <td>${lang.translate('All')}</td>
                    <#else>
                        <td>${(franchiseName?html)!" "}</td>
                    </#if>
                    </tr>
                    <tr>
                        <td>${lang.translate('Franchise #')}</td>
                    <#if franchiseCode == "">
                        <td>${lang.translate('All')}</td>
                    <#else>
                        <td>${(franchiseCode)!" "}</td>
                    </#if>
                    </tr>
                    <tr>
                        <td>${lang.translate('Date Range')}</td>
                        <td>${(startDate)!" "} - ${(endDate)!" "}</td>
                    </tr>
                </table>
            </td>
            <td valign="top">
                <table>
                    <tr>
                        <td>${lang.translate('Setups')}</td>
                        <td>${(overview.setups)!"0"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('Activations #')}</td>
                        <td>${(overview.activations)!"0"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('Printed Invoices')}</td>
                        <td>${(overview.printedInvoices)!"0"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('E-mail Invoices')}</td>
                        <td>${(overview.emailInvoices)!"0"}</td>
                    </tr>
                </table>
            </td>
            <td valign="top">
                <table>
                    <tr>
                        <td><u><i>${lang.translate('Receiveable:')}</i></u></td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Customer Cost')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.customerCost)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Customer Marginable Cost')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.customerMarginableCost)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Franchise Cost Taxable')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.franchiseCostTaxable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Franchise Cost Non-taxable')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.franchiseCostNonTaxable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Franchise TVA')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.franchiseGst)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Total Franchise Cost')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.franchiseTotal)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Margin Shared')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.marginShared)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Management Fee on Revenue Shared')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.managementFee)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Marketing Fee')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.marketingFee)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Carrier Credit Taxable')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.carrierCreditsTaxable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Carrier Credit Non-taxable')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.carrierCreditsNonTaxable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Carrier Credit TVA')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.carrierCreditsGst)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Total Carrier Credit')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.carrierCreditsTotal)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Tech Fees on International Shipments')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.techFeeOnIntlShipments)!"0.00"}
                            (${(overview.intlShipmentCount)!"0"})
                        </td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Tech Fees on Domestic Shipments')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.techFeeOnDomShipments)!"0.00"}
                            (${(overview.domShipmentCount)!"0"})
                        </td>
                    </tr>
                    <tr>
                        <td><u><i>${lang.translate('Net Receiveable:')}</i></u></td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Net Receiveable')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.netReceivable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- TVA')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.gst)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>${lang.translate('- Total Receiveable')}</td>
                        <td>${(currencySymbol)!"$"}${(overview.totalReceivable)!"0.00"}</td>
                    </tr>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>