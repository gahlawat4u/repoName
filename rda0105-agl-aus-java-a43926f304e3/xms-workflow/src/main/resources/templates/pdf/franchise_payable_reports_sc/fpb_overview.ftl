<#setting date_format="dd-MM-yyyy">
<div class="page-break">
    <table class="no-border">
        <thead>
        <th width="30%" align="center"><span style="font-size: 20px;">${lang.translate('Franchise Info')}</span></th>
        <th width="30%" align="center"><span style="font-size: 20px;">${lang.translate('Activity')}</span></th>
        <th width="40%" align="center"><span style="font-size: 20px;">${lang.translate('Summary')}</span></th>
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
                        <td width="40%">${lang.translate('Setups')}</td>
                        <td width="60%" align="left">${(overview.setups)!"0"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('Activations #')}</td>
                        <td align="left" width="60%">${(overview.activations)!"0"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('Printed Invoices')}</td>
                        <td align="left" width="60%">${(overview.printedInvoices)!"0"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('E-mail Invoices')}</td>
                        <td align="left" width="60%">${(overview.emailInvoices)!"0"}</td>
                    </tr>
                </table>
            </td>
            <td valign="top">
                <table>
                    <tr>
                        <td><strong>${lang.translate('Receiveable:')}</strong></td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Customer Cost')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.customerCost)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Customer Marginable Cost')}</td>
                        <td align="left"
                            width="60%">${(currencySymbol)!"$"} ${(overview.customerMarginableCost)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Franchise Cost Taxable')}</td>
                        <td align="left"
                            width="60%">${(currencySymbol)!"$"} ${(overview.franchiseCostTaxable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Franchise Cost Non-taxable')}</td>
                        <td align="left"
                            width="60%">${(currencySymbol)!"$"} ${(overview.franchiseCostNonTaxable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Franchise TVA')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.franchiseGst)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Total Franchise Cost')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.franchiseTotal)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Margin Shared')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.marginShared)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Management Fee on Revenue Shared')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.managementFee)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Marketing Fee')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.marketingFee)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Carrier Credit Taxable')}</td>
                        <td align="left"
                            width="60%">${(currencySymbol)!"$"} ${(overview.carrierCreditsTaxable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Carrier Credit Non-taxable')}</td>
                        <td align="left"
                            width="60%">${(currencySymbol)!"$"} ${(overview.carrierCreditsNonTaxable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Carrier Credit TVA')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.carrierCreditsGst)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Total Carrier Credit')}</td>
                        <td align="left"
                            width="60%">${(currencySymbol)!"$"} ${(overview.carrierCreditsTotal)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Tech Fees on International Shipments')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.techFeeOnIntlShipments)!"0.00"}
                            (${(overview.intlShipmentCount)!"0"})
                        </td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Tech Fees on Domestic Shipments')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.techFeeOnDomShipments)!"0.00"}
                            (${(overview.domShipmentCount)!"0"})
                        </td>
                    </tr>
                    <tr>
                        <td><strong>${lang.translate('Net Receiveable:')}</strong></td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Net Receiveable')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.netReceivable)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- TVA')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.gst)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">${lang.translate('- Total Receiveable')}</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"} ${(overview.totalReceivable)!"0.00"}</td>
                    </tr>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>