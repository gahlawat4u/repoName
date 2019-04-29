<#setting date_format="dd-MM-yyyy">
<div class="page-break">
    <table class="no-border">
        <thead>
        <th width="30%" align="center"><span style="font-size: 20px;">Franchise Info</span></th>
        <th width="30%" align="center"><span style="font-size: 20px;">Activity</span></th>
        <th width="40%" align="center"><span style="font-size: 20px;">Summary</span></th>
        </thead>
        <tbody>
        <tr>
            <td valign="top">
                <table>
                    <tr>
                        <td>Name</td>
                    <#if franchiseName == "">
                        <td>All</td>
                    <#else>
                        <td>${(franchiseName?html)!" "}</td>
                    </#if>
                    </tr>
                    <tr>
                        <td>Franchise #</td>
                    <#if franchiseCode == "">
                        <td>All</td>
                    <#else>
                        <td>${(franchiseCode)!" "}</td>
                    </#if>
                    </tr>
                    <tr>
                        <td>Date Range</td>
                        <td>${(startDate)!" "} - ${(endDate)!" "}</td>
                    </tr>
                </table>
            </td>
            <td valign="top">
                <table>
                    <tr>
                        <td width="40%">Setups</td>
                        <td width="60%" align="left">${(overview.setups)!"0"}</td>
                    </tr>
                    <tr>
                        <td width="40%">Activations #</td>
                        <td align="left" width="60%">${(overview.activations)!"0"}</td>
                    </tr>
                    <tr>
                        <td width="40%">Printed Invoices</td>
                        <td align="left" width="60%">${(overview.printedInvoices)!"0"}</td>
                    </tr>
                    <tr>
                        <td width="40%">E-mail Invoices</td>
                        <td align="left" width="60%">${(overview.emailInvoices)!"0"}</td>
                    </tr>
                </table>
            </td>
            <td valign="top">
                <table>
                    <tr>
                        <td><strong>Payables:</strong></td>
                    </tr>
                    <tr>
                        <td width="40%">- Payment Margin</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"}${(overview.marginShare)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">- 61 + Day Payment</td>
                        <td>${(currencySymbol)!"$"}${(overview.day61MarginShare)!"0.00"}</td>
                    </tr>
                <#if enableNonCentralizedTab>
                    <tr>
                        <td width="40%">- Non Centralised Margin Share</td>
                        <td align="left"
                            width="60%">${(currencySymbol)!"$"}${(overview.nonCentralizedMarginShare)!"0.00"}</td>
                    </tr>
                </#if>
                    <tr>
                        <td width="40%">- Late Fee</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"}${(overview.lateFee)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td><strong>Gross Payables :</strong></td>
                    </tr>
                    <tr>
                        <td width="40%">- Gross Payables</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"}${(overview.grossPayables)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td><strong>Other Payables :</strong></td>
                    </tr>
                    <tr>
                        <td>- Repaid Carrier Deductions</td>
                        <td>${(currencySymbol)!"$"}${(overview.repaidCarrierDeductions)!"0.00"}</td>
                    </tr>
                <#if enableNonCentralizedTab>
                    <tr>
                        <td width="40%">- Non Central Carrier Cost</td>
                        <td align="left"
                            width="60%">${(currencySymbol)!"$"}${(overview.nonCentralCarrierCost)!"0.00"}</td>
                    </tr>
                </#if>
                    <tr>
                        <td><strong>Costs :</strong></td>
                    </tr>
                    <tr>
                        <td width="40%">- Carrier Cost Deductions</td>
                        <td align="left"
                            width="60%">${(currencySymbol)!"$"}${(overview.carrierCostDeduction)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">- Tech Fees</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"}${(overview.techFees)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td width="40%">- Marketing Fee etc</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"}${(overview.marketingFees)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td><strong>Net Payables :</strong></td>
                    </tr>
                    <tr>
                        <td width="40%">- Net Payables</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"}${(overview.netPayables)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td class="td1 s41">- GST</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"}${(overview.gst)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td class="td1 s41">- Total Payables</td>
                        <td align="left" width="60%">${(currencySymbol)!"$"}${(overview.totalPayables)!"0.00"}</td>
                    </tr>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>