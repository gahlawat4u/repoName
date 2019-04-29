<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-5">
        <p class="s38">
            <xms:localization text="Recipient Created Tax Invoice"/>
        </p>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><img src="<%=WebUtils.getContextPathURL(request)%>images/LOGOiN.png" width="140"/></td>
                <td class="td2"><s:property value="franchise.customerName"/> <br/> <s:property
                        value="franchise.address1"/> <br/> <s:property value="franchise.city"/>, <s:property
                        value="franchise.postalCode"/>, <s:property value="franchise.country"/> <br/> <s:property
                        value="franchise.email"/> <br/> <s:property value="franchise.registrationid"/></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-5">
        <p class="s38" style="color: white">-</p>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><xms:localization text="BILL TO:"/></td>
                <td class="td2"><s:property escape="false" value="systemAddress"/> <br/> <s:property
                        value="siteAddress"/></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-12">
        <p class="s38">
            <xms:localization text="Commissions Payable As Per Agreement"/>
        </p>

        <p>
            <xms:localization text="Date Range:"/>
            <s:property value="startDate"/>
            -
            <s:property value="endDate"/>
            <br/>
        <table class="table mg0">
            <tr>
                <th class="s42"></th>
            </tr>
        </table>
        <table class="table table-hover table-striped table-bordered mg0" id="datatable" style="float: left">
            <thead>
            <tr>
                <th><xms:localization text="Item"/></th>
                <th><xms:localization text="Description"/></th>
                <th><xms:localization text="Amount"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <tr>
                <td colspan="3" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Payables :"/></td>
            </tr>
            <tr>
                <td><xms:localization text="1"/></td>
                <td><xms:localization text="Margin Share"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.marginShare"/></td>
            </tr>
            <tr>
                <td><xms:localization text="2"/></td>
                <td><xms:localization text="61+ Payment Margin Share"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.day61MarginShare"/></td>
            </tr>
            <tr>
                <td><xms:localization text="3"/></td>
                <td><xms:localization text="Non Centralised Margin Share"/></td>
                <td><s:property value="currencySymbol"/><s:property
                        value="overview.nonCentralizedMarginShare"/></td>
            </tr>
            <tr>
                <td><xms:localization text="4"/></td>
                <td><xms:localization text="Late Fee"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.lateFee"/></td>
            </tr>
            <tr>
                <td colspan="3" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Gross Payables :"/></td>
            </tr>
            <tr>
                <td><xms:localization text="5"/></td>
                <td><xms:localization text="Gross Payables"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.grossPayables"/></td>
            </tr>
            <tr>
                <td colspan="3" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Other Payables :"/></td>
            </tr>
            <tr>
                <td><xms:localization text="6"/></td>
                <td><xms:localization text="Repaid Carrier Deductions"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.repaidCarrierDeductions"/></td>
            </tr>
            <tr>
                <td colspan="3" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Costs :"/></td>
            </tr>
            <tr>
                <td><xms:localization text="7"/></td>
                <td><xms:localization text="Carrier Cost Deductions"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.carrierCostDeduction"/></td>
            </tr>
            <tr>
                <td><xms:localization text="8"/></td>
                <td><xms:localization text="Tech Fees"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.techFees"/></td>
            </tr>
            <tr>
                <td><xms:localization text="9"/></td>
                <td><xms:localization text="Marketing Fees"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.marketingFees"/></td>
            </tr>
            <tr>
                <td colspan="3" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Net payables :"/></td>
            </tr>
            <tr>
                <td><xms:localization text="10"/></td>
                <td><xms:localization text="Net Payables"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.netPayablesExcGst"/></td>
            </tr>
            <tr>
                <td><xms:localization text=""/></td>
                <td><xms:localization text="GST on Net Payables"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.netPayablesGst"/></td>
            </tr>
            <tr>
                <td colspan="3" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Reimbursements :"/></td>
            </tr>
            <tr>
                <td><xms:localization text="11"/></td>
                <td><xms:localization text="Non Central Carrier Cost"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.nonCentralCarrierCostExcGst"/></td>
            </tr>
            <tr>
                <td><xms:localization text=""/></td>
                <td><xms:localization text="GST from Non-Central Carrier Costs"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.nonCentralCarrierCostGst"/></td>
            </tr>
            <tr>
                <td colspan="3" style="font-style: italic; text-decoration: underline;"><xms:localization
                        text="Total Payables :"/></td>
            </tr>
            <tr>
                <td><xms:localization text="12"/></td>
                <td><xms:localization text="Total Payables"/></td>
                <td><s:property value="currencySymbol"/><s:property value="overview.totalPayables"/></td>
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