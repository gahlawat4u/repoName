<#setting date_format="dd-MM-yyyy">
<div class="page-break">
    <div class="title">
        <h3>Overview</h3>
    </div>
    <table class="no-border">
        <thead>
        <th width="30%">Franchise Info</th>
        <th width="30%">Activity</th>
        <th width="40%">Summary</th>
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
                        <td>Setups</td>
                        <td>${(overview.setups)!"0"}</td>
                    </tr>
                    <tr>
                        <td>Activations #</td>
                        <td>${(overview.activations)!"0"}</td>
                    </tr>
                    <tr>
                        <td>Printed Invoices</td>
                        <td>${(overview.printedInvoices)!"0"}</td>
                    </tr>
                    <tr>
                        <td>E-mail Invoices</td>
                        <td>${(overview.emailInvoices)!"0"}</td>
                    </tr>
                </table>
            </td>
            <td valign="top">
                <table>
                    <tr>
                        <td><u><i>Payables:</i></u></td>
                    </tr>
                    <tr>
                        <td>- Margin Share</td>
                        <td>${(currencySymbol)!"$"}${(overview.marginShare)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>- 61+ Payment Margin Share</td>
                        <td>${(currencySymbol)!"$"}${(overview.day61MarginShare)!"0.00"}</td>
                    </tr>
                <#if enableNonCentralizedTab>
                    <tr>
                        <td>- Non Centralised Margin Share</td>
                        <td>${(currencySymbol)!"$"}${(overview.nonCentralizedMarginShare)!"0.00"}</td>
                    </tr>
                </#if>
                    <tr>
                        <td>- Late Fee</td>
                        <td>${(currencySymbol)!"$"}${(overview.lateFee)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td><u><i>Gross Payables :</i></u></td>
                    </tr>
                    <tr>
                        <td>- Gross Payables</td>
                        <td>${(currencySymbol)!"$"}${(overview.grossPayables)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td><u><i>Other Payables :</i></u></td>
                    </tr>
                    <tr>
                        <td>- Repaid Carrier Deductions</td>
                        <td>${(currencySymbol)!"$"}${(overview.repaidCarrierDeductions)!"0.00"}</td>
                    </tr>
                <#if enableNonCentralizedTab>
                    <tr>
                        <td>- Non Central Carrier Cost</td>
                        <td>${(currencySymbol)!"$"}${(overview.nonCentralCarrierCost)!"0.00"}</td>
                    </tr>
                </#if>
                    <tr>
                        <td><u><i>Costs :</i></u></td>
                    </tr>
                    <tr>
                        <td>- Carrier Cost Deductions</td>
                        <td>${(currencySymbol)!"$"}${(overview.carrierCostDeduction)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>- Tech Fees</td>
                        <td>${(currencySymbol)!"$"}${(overview.techFees)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td>- Marketing Fee etc</td>
                        <td>${(currencySymbol)!"$"}${(overview.marketingFees)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td><u><i>Net Payables :</i></u></td>
                    </tr>
                    <tr>
                        <td>- Net Payables</td>
                        <td>${(currencySymbol)!"$"}${(overview.netPayables)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td class="td1 s41">- GST</td>
                        <td>${(currencySymbol)!"$"}${(overview.gst)!"0.00"}</td>
                    </tr>
                    <tr>
                        <td class="td1 s41">- Total Payables</td>
                        <td>${(currencySymbol)!"$"}${(overview.totalPayables)!"0.00"}</td>
                    </tr>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>