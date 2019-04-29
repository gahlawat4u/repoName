<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div style="overflow: auto">
    <table class="table table-hover table-bordered mg0" style="overflow: auto" id="customer-list-table">
        <thead>
        <tr>
            <th><xms:localization text="Customer #"/></th>
            <th><xms:localization text="Customer Name"/></th>
            <th><xms:localization text="Web Freight Group"/></th>
            <th><xms:localization text="Sales Rep"/></th>
            <th data-group="address-detail"><xms:localization text="Name"/></th>
            <th data-group="address-detail"><xms:localization text="Attn"/></th>
            <th data-group="address-detail"><xms:localization text="Addr 1"/></th>
            <th data-group="address-detail"><xms:localization text="Addr 2"/></th>
            <th data-group="address-detail"><xms:localization text="City"/></th>
            <th data-group="address-detail"><xms:localization text="Country"/></th>
            <th data-group="address-detail"><xms:localization text="Postal Code"/></th>
            <th data-group="address-detail"><xms:localization text="Phone"/></th>
            <th data-group="address-detail"><xms:localization text="Email"/></th>
            <th data-group="billing-detail"><xms:localization text="Billing Name"/></th>
            <th data-group="billing-detail"><xms:localization text="Billing Attn"/></th>
            <th data-group="billing-detail"><xms:localization text="Billing Addr 1"/></th>
            <th data-group="billing-detail"><xms:localization text="Billing Addr 2"/></th>
            <th data-group="billing-detail"><xms:localization text="Billing City"/></th>
            <th data-group="billing-detail"><xms:localization text="Billing Country"/></th>
            <th data-group="billing-detail"><xms:localization text="Billing Postal Code"/></th>
            <th data-group="billing-detail"><xms:localization text="Billing Phone"/></th>
            <th data-group="billing-detail"><xms:localization text="Billing Email"/></th>
            <th data-group="dhl-cost-basis"><xms:localization text="DHL Acct #"/></th>
            <th data-group="dhl-cost-basis"><xms:localization text="Inbound Acct #"/></th>
            <th data-group="invoicing-option"><xms:localization text="Inv Terms"/></th>
            <th data-group="invoicing-option"><xms:localization text="Email Invoice?"/></th>
            <th data-group="dates"><xms:localization text="Submitted"/></th>
            <th data-group="dates"><xms:localization text="Activated"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="customers==null || customers.totalRecords==0">
            <tr>
                <td colspan="28"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="customers.records">
                <tr>
                    <td><s:property value="customerCode"/></td>
                    <td><s:property value="customerName"/></td>
                    <td><s:property value="webshipGroupName"/></td>
                    <td><s:property value="salesRepName"/></td>
                    <td data-group="address-detail"><s:property value="customerName"/></td>
                    <td data-group="address-detail"><s:property value="addressContactName"/></td>
                    <td data-group="address-detail"><s:property value="addressAddress1"/></td>
                    <td data-group="address-detail"><s:property value="addressAddress2"/></td>
                    <td data-group="address-detail"><s:property value="addressCity"/></td>
                    <td data-group="address-detail"><s:property value="addressCountry"/></td>
                    <td data-group="address-detail"><s:property value="addressPostalCode"/></td>
                    <td data-group="address-detail"><s:property value="addressPhone"/></td>
                    <td data-group="address-detail"><s:property value="addressEmail"/></td>
                    <td data-group="billing-detail"><s:property value="billingCustomerName"/></td>
                    <td data-group="billing-detail"><s:property value="billingContactName"/></td>
                    <td data-group="billing-detail"><s:property value="billingAddress1"/></td>
                    <td data-group="billing-detail"><s:property value="billingAddress2"/></td>
                    <td data-group="billing-detail"><s:property value="billingCity"/></td>
                    <td data-group="billing-detail"><s:property value="billingCountry"/></td>
                    <td data-group="billing-detail"><s:property value="billingPostalCode"/></td>
                    <td data-group="billing-detail"><s:property value="billingPhone"/></td>
                    <td data-group="billing-detail"><s:property value="billingEmail"/></td>
                    <td data-group="dhl-cost-basis"><s:property value="dhlInternationalAccount"/></td>
                    <td data-group="dhl-cost-basis"><s:property value="dhlInboundAccount"/></td>
                    <td data-group="invoicing-option"><s:property value="invoiceDays"/> days</td>
                    <td data-group="invoicing-option"><s:property value="%{'true'.equals(emailInvoice)?'Yes':'No'}"/>
                        <br/> <s:property value="billingEmail"/></td>
                    <td data-group="dates"><s:property value="createDate"/></td>
                    <td data-group="dates"><s:property value="activateDate"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="customers.startRecord"/> <xms:localization
                    text="to"/> <s:property value="customers.endRecord"/> <xms:localization text="of"/> <s:property
                    value="customers.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!customers.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{customers.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="customers.pageRange" status="count">
                <s:if test="%{customers.pageRange[#count.index] == customers.currentPage}">
                    <a class="paginate_button current"><s:property value="customers.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!customers.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{customers.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='checkbox'][data-group]").each(function () {
            if ($(this).is(":checked")) {
                showColumns($(this).attr("data-group"), true);
            } else {
                showColumns($(this).attr("data-group"), false);
            }
        });
        var fieldList = ["customer_code", "customer_name", "webship_group_name", "sales_rep_name", "customer_name", "address_contact_name", "address_address1", "address_address2", "address_city", "address_country", "address_postal_code", "address_phone", "address_email", "billing_customer_name", "billing_contact_name", "billing_address1", "billing_address2", "billing_city", "billing_country", "billing_postal_code", "billing_phone", "billing_email", "dhl_international_account", "dhl_inbound_account", "invoice_days", "email_invoice", "create_date", "activate_date"];
        $("#customer-list-table").tablesorter({
            sortFieldId: "order_field",
            sortTypeId: "order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });


</script>