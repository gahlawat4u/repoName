<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <div class="col-lg-5">
        <p class="s38">
            <xms:localization text="Invoice"/>
        </p>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><img
                        src="<%=WebUtils.getContextPathURL(request)%>images/LOGOiN.png"
                        width="140"/></td>
                <td class="td2"><s:property escape="false"
                                            value="systemAddress"/> <br/> <s:property value="siteAddress"/></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-5">
        <p class="s38" style="color: white">-</p>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><xms:localization text="BILL TO:"/></td>
                <td class="td2"><s:property value="franchise.customerName"/>
                    <br/> <s:property value="franchise.address1"/> <br/> <s:property
                            value="franchise.city"/>, <s:property
                            value="franchise.postalCode"/>, <s:property
                            value="franchise.country"/> <br/> <s:property
                            value="franchise.email"/> <br/> <s:property
                            value="franchise.registrationid"/></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-12">
        <p class="s38">
            <xms:localization text="Commissions Payable As Per Agreement"/>
        </p>

        <p>
            <xms:localization text="Invoice Number:"/>
            <s:property value="invoiceNumber"/>
            <br/>
            <xms:localization text="Date Range:"/>
            <s:property value="startDate"/>
            -
            <s:property value="endDate"/>
        </p>
        <table class="table mg0">
            <tr>
                <th class="s42"></th>
            </tr>
        </table>
        <table class="table table-hover table-striped table-bordered mg0"
               id="datatable" style="float: left">
            <thead>
            <tr>
                <th><xms:localization text="Item"/></th>
                <th><xms:localization text="Description"/></th>
                <th><xms:localization text="Amount"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="3"
                    style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Receiveables :"/></td>
            </tr>
            <tr>
                <td><xms:localization text="1"/></td>
                <td><xms:localization text="Customer Cost"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.customerCost"/></td>
            </tr>
            <tr>
                <td><xms:localization text="2"/></td>
                <td><xms:localization text="Customer Marginable Cost"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.customerMarginableCost"/></td>
            </tr>
            <tr>
                <td><xms:localization text="3"/></td>
                <td><xms:localization text="Franchise Cost Taxable"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.franchiseCostTaxable"/></td>
            </tr>
            <tr>
                <td><xms:localization text="4"/></td>
                <td><xms:localization text="Franchise Cost Non-taxable"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.franchiseCostNonTaxable"/></td>
            </tr>
            <tr>
                <td><xms:localization text="5"/></td>
                <td><xms:localization text="Franchise TVA"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.franchiseGst"/></td>
            </tr>
            <tr>
                <td><xms:localization text="6"/></td>
                <td><xms:localization text="Total Franchise Cost"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.franchiseTotal"/></td>
            </tr>
            <tr>
                <td><xms:localization text="7"/></td>
                <td><xms:localization text="Margin Shared"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.marginShared"/></td>
            </tr>
            <tr>
                <td><xms:localization text="8"/></td>
                <td><xms:localization text="Management Fee on Revenue Shared"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.managementFee"/></td>
            </tr>
            <tr>
                <td><xms:localization text="9"/></td>
                <td><xms:localization text="Marketing Fee"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.marketingFee"/></td>
            </tr>
            <tr>
                <td><xms:localization text="10"/></td>
                <td><xms:localization text="Carrier Credit Taxable"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.carrierCreditsTaxable"/></td>
            </tr>
            <tr>
                <td><xms:localization text="11"/></td>
                <td><xms:localization text="Carrier Credit Non-taxable"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.carrierCreditsNonTaxable"/></td>
            </tr>
            <tr>
                <td><xms:localization text="12"/></td>
                <td><xms:localization text="Carrier Credit TVA"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.carrierCreditsGst"/></td>
            </tr>
            <tr>
                <td><xms:localization text="13"/></td>
                <td><xms:localization text="Total Carrier Credit"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.carrierCreditsTotal"/></td>
            </tr>
            <tr>
                <td><xms:localization text="14"/></td>
                <td><xms:localization text="Management Fee On Credit Revenue"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.managementFeeOnCreditRevenue"/></td>
            </tr>
            <tr>
                <td><xms:localization text="15"/></td>
                <td><xms:localization text="Management Fee On Credit Profit"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.managementFeeOnCreditProfitShared"/></td>
            </tr>
            <tr>
                <td><xms:localization text="16"/></td>
                <td><xms:localization
                        text="Tech Fees on International Shipments"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.techFeeOnIntlShipments"/> (<s:property
                        value="overview.intlShipmentCount"/>)
                </td>
            </tr>
            <tr>
                <td><xms:localization text="17"/></td>
                <td><xms:localization text="Tech Fees on Domestic Shipments"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.techFeeOnDomShipments"/> (<s:property
                        value="overview.domShipmentCount"/>)
                </td>
            </tr>
            <tr>
                <td colspan="3"
                    style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Net Receiveables: "/></td>
            </tr>
            <tr>
                <td><xms:localization text="18"/></td>
                <td><xms:localization text="Net Receiveables"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.netReceivable"/></td>
            </tr>
            <tr>
                <td><xms:localization text="19"/></td>
                <td><xms:localization text="TVA"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.gst"/></td>
            </tr>
            <tr>
                <td><xms:localization text="20"/></td>
                <td><xms:localization text="Total Receiveables"/></td>
                <td><s:property value="currencySymbol"/> <s:property
                        value="overview.totalReceivable"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#rptTxnId').val('<s:property value="rptTxnId" />');
    });


</script>