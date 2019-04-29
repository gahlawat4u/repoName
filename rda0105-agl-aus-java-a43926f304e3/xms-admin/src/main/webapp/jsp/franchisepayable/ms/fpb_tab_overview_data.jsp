<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<input type="hidden" value='<s:property value="rptTxnId" />' id="rptTxnId-overview"/>

<div class="row">
    <div class="col-lg-5">
        <h4 class="s34">
            <xms:localization text="Franchise Info :"/>
        </h4>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><xms:localization text="Name"/></td>
                <s:if test="%{franchiseName == ''}">
                    <td class="td2"><xms:localization text="All"/></td>
                </s:if>
                <s:else>
                    <td class="td2"><s:property value="franchiseName"/></td>
                </s:else>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Franchise #"/></td>
                <s:if test="%{franchiseCode == ''}">
                    <td class="td2"><xms:localization text="All"/></td>
                </s:if>
                <s:else>
                    <td class="td2"><s:property value="franchiseCode"/></td>
                </s:else>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Date Range"/></td>
                <td class="td2"><s:property value="startDate"/> - <s:property value="endDate"/></td>
            </tr>
        </table>
        <h4 class="s34">
            <xms:localization text="Activity :"/>
        </h4>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><xms:localization text="Setups"/></td>
                <td class="td2"><s:if test="%{overview==null}">0</s:if> <s:else>
                    <s:property value="overview.setups"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Activations #"/></td>
                <td class="td2"><s:if test="%{overview==null}">0</s:if> <s:else>
                    <s:property value="overview.activations"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Printed Invoices"/></td>
                <td class="td2"><s:if test="%{overview==null}">0</s:if> <s:else>
                    <s:property value="overview.printedInvoices"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="E-mail Invoices"/></td>
                <td class="td2"><s:if test="%{overview==null}">0</s:if> <s:else>
                    <s:property value="overview.emailInvoices"/>
                </s:else></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-7">
        <h4 class="s34">
            <xms:localization text="Summary :"/>
        </h4>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1 s39" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Payables :"/></td>
                <td class="td2"></td>
            </tr>
            <tr>
                <td class="td1 s40"><xms:localization text="- Margin Share"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.marginShare"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1 s40"><xms:localization text="- 61+ Payment Margin Share"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.day61MarginShare"/>
                </s:else></td>
            </tr>
            <s:if test="enableNonCentralizedTab">
                <tr>
                    <td class="td1 s40"><xms:localization text="- Non Centralised Margin Share"/></td>
                    <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                        <s:property value="currencySymbol"/>
                        <s:property value="overview.nonCentralizedMarginShare"/>
                    </s:else></td>
                </tr>
            </s:if>
            <tr>
                <td class="td1 s41"><xms:localization text="- Late Fee"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.lateFee"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1 s39" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Gross Payables :"/></td>
                <td class="td2"></td>
            </tr>
            <tr>
                <td class="td1 s41"><xms:localization text="- Gross Payables"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.grossPayables"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1 s39" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Other Payables :"/></td>
                <td class="td2"></td>
            </tr>
            <tr>
                <td class="td1 s40"><xms:localization text="- Repaid Carrier Deductions"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.repaidCarrierDeductions"/>
                </s:else></td>
            </tr>
            <s:if test="enableNonCentralizedTab">
                <tr>
                    <td class="td1 s41"><xms:localization text="- Non Central Carrier Cost"/></td>
                    <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                        <s:property value="currencySymbol"/>
                        <s:property value="overview.nonCentralCarrierCost"/>
                    </s:else></td>
                </tr>
            </s:if>
            <tr>
                <td class="td1 s39" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Costs :"/></td>
                <td class="td2"></td>
            </tr>
            <tr>
                <td class="td1 s40"><xms:localization text="- Carrier Cost Deductions"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.carrierCostDeduction"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1 s40"><xms:localization text="- Tech Fees"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.techFees"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1 s41"><xms:localization text="- Marketing Fee etc"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.marketingFees"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1 s39" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Net Payables :"/></td>
                <td class="td2"></td>
            </tr>
            <tr>
                <td class="td1 s41"><xms:localization text="- Net Payables"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.netPayables"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1 s41"><xms:localization text="- GST"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.gst"/>
                </s:else></td>
            </tr>
            <tr>
                <td class="td1 s41"><xms:localization text="- Total Payables"/></td>
                <td class="td2"><s:if test="%{overview==null}">0.00</s:if> <s:else>
                    <s:property value="currencySymbol"/>
                    <s:property value="overview.totalPayables"/>
                </s:else></td>
            </tr>
        </table>
    </div>
    <script>
        $(document).ready(function () {
            $('#rptTxnId').val('<s:property value="rptTxnId" />');
            $('#frozen-message').html('<s:property value="frozenMessage"/>');
        });
    </script>
</div>