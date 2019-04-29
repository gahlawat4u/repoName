<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <div class="col-lg-5">
        <h4 class="s34">
            <xms:localization text="Franchise Info:"/>
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
            <xms:localization text="Activity:"/>
        </h4>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><xms:localization text="Setups"/></td>
                <td class="td2"><s:property value="overview.setups"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Activations #"/></td>
                <td class="td2"><s:property value="overview.activations"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Printed Invoices"/></td>
                <td class="td2"><s:property value="overview.printedInvoices"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="E-mail Invoices"/></td>
                <td class="td2"><s:property value="overview.emailInvoices"/></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-7">
        <h4 class="s34">
            <xms:localization text="Summary:"/>
        </h4>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1 s39" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Receiveable"/> :
                </td>
                <td class="td2"></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Customer Cost"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.customerCost"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Customer Marginable Cost"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.customerMarginableCost"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Franchise Cost Taxable"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.franchiseCostTaxable"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Franchise Cost Non-taxable"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.franchiseCostNonTaxable"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Franchise TVA"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.franchiseGst"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Total Franchise Cost"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.franchiseTotal"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Margin Shared"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.marginShared"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Management Fee on Revenue Shared"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.managementFee"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Marketing Fee"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.marketingFee"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Carrier Credit Taxable"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.carrierCreditsTaxable"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Carrier Credit Non-taxable"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.carrierCreditsNonTaxable"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Carrier Credit TVA"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.carrierCreditsGst"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Total Carrier Credit"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.carrierCreditsTotal"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Management Fee On Credit Revenue"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.managementFeeOnCreditRevenue"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Management Fee On Credit Profit"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.managementFeeOnCreditProfitShared"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Tech Fees on International Shipments"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.techFeeOnIntlShipments"/> (<s:property
                            value="overview.intlShipmentCount"/>)
                </td>
            </tr>
            <tr>
                <td class="td1 s41">- <xms:localization text="Tech Fees on Domestic Shipments"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.techFeeOnDomShipments"/> (<s:property
                            value="overview.domShipmentCount"/>)
                </td>
            </tr>
            <tr>
                <td class="td1 s39" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Net Receiveable"/> :
                </td>
                <td class="td2"></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="Net Receiveable"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.netReceivable"/></td>
            </tr>
            <tr>
                <td class="td1 s40">- <xms:localization text="TVA"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.gst"/></td>
            </tr>
            <tr>
                <td class="td1 s41">- <xms:localization text="Total Receiveable"/></td>
                <td class="td2"><s:property value="currencySymbol"/>
                    <s:property value="overview.totalReceivable"/></td>
            </tr>
        </table>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#rptTxnId').val('<s:property value="rptTxnId" />');
        $('#frozen-message').html('<s:property value="frozenMessage"/>');
    });


</script>