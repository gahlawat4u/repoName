<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-hover table-bordered mg0">
    <thead>
    <tr>
        <th sort-field="franchise_code"><xms:localization text="Franchise #"/> <i class="fa fa-sort"></i></th>
        <th sort-field="franchise_territory"><xms:localization text="Territory"/> <i class="fa fa-sort"></i></th>
        <th sort-field="customer_name" data-group="address"><xms:localization text="Name"/> <i class="fa fa-sort"></i>
        </th>
        <th sort-field="address1" data-group="address"><xms:localization text="Addr 1"/> <i class="fa fa-sort"></i></th>
        <th sort-field="city" data-group="address"><xms:localization text="City"/> <i class="fa fa-sort"></i></th>
        <th sort-field="postal_code" data-group="address"><xms:localization text="Postal Code"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="phone" data-group="address"><xms:localization text="Phone"/> <i class="fa fa-sort"></i></th>
        <th sort-field="email" data-group="address"><xms:localization text="Email"/> <i class="fa fa-sort"></i></th>
        <th sort-field="billing_customer_name" data-group="billing-address"><xms:localization text="Billing Name"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="billing_address1" data-group="billing-address"><xms:localization text="Billing Addr 1"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="billing_city" data-group="billing-address"><xms:localization text="Billing City"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="billing_postal_code" data-group="billing-address"><xms:localization text="Billing Postal Code"/>
            <i class="fa fa-sort"></i></th>
        <th sort-field="billing_phone" data-group="billing-address"><xms:localization text="Billing Phone"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="dhl_international_account" data-group="dhl-columns"><xms:localization text="DHL Acct#"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="franchise_start_date" data-group="dates"><xms:localization text="Fran Start Date"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="create_date" data-group="dates"><xms:localization text="Submitted"/> <i class="fa fa-sort"></i>
        </th>
        <th sort-field="activate_date" data-group="dates"><xms:localization text="Activated"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="invoice_term" data-group="invoice-option"><xms:localization text="Inv Terms"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="email_invoice" data-group="invoice-option"><xms:localization text="E-mail Invoice?"/> <i
                class="fa fa-sort"></i></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="franchises!=null && franchises.totalRecords>0">
        <s:iterator value="franchises.records">
            <tr>
                <td><s:property value="franchiseCode"/></td>
                <td><s:property value="franchiseTerritory"/></td>
                <td data-group="address"><s:property value="customerName"/></td>
                <td data-group="address"><s:property value="address1"/></td>
                <td data-group="address"><s:property value="city"/></td>
                <td data-group="address"><s:property value="postalCode"/></td>
                <td data-group="address"><s:property value="phone"/></td>
                <td data-group="address"><s:property value="email"/></td>
                <td data-group="billing-address"><s:property value="billingCustomerName"/></td>
                <td data-group="billing-address"><s:property value="billingAddress1"/></td>
                <td data-group="billing-address"><s:property value="billingCity"/></td>
                <td data-group="billing-address"><s:property value="billingPostalCode"/></td>
                <td data-group="billing-address"><s:property value="billingPhone"/></td>
                <td data-group="dhl-columns"><s:property value="dhlInternationalAccount"/></td>
                <td data-group="dates"><s:property value="franchiseStartDate"/></td>
                <td data-group="dates"><s:property value="createDate"/></td>
                <td data-group="dates"><s:property value="activateDate"/></td>
                <td data-group="invoice-option"><s:property value="invoiceTerm"/> <xms:localization text="days"/></td>
                <td data-group="invoice-option"><s:if test="emailInvoice=='true'">
                    <xms:localization text="Yes"/>
                </s:if> <s:else>
                    <xms:localization text="No"/>
                </s:else></td>
            </tr>
        </s:iterator>
    </s:if>
    <s:else>
        <tr>
            <td colspan="19"><xms:localization text="No data available..."/></td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="franchises.startRecord"/> <xms:localization
                    text="to"/> <s:property value="franchises.endRecord"/> <xms:localization text="of"/> <s:property
                    value="franchises.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!franchises.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{franchises.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="franchises.pageRange" status="count">
                <s:if test="%{franchises.pageRange[#count.index] == franchises.currentPage}">
                    <a class="paginate_button current"><s:property value="franchises.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!franchises.hasNext()">
                <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{franchises.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<br/>
<br/>
<script type="text/javascript">
    $(document).ready(function () {
        // Show/Hide columns
        $(document).ready(function () {
            $("input[type='checkbox'][data-group]").each(function () {
                if ($(this).is(":checked")) {
                    showColumns($(this).attr("data-group"), true);
                } else {
                    showColumns($(this).attr("data-group"), false);
                }
            });
        });
        // Sort table
        $("#franchise-list-result th[sort-field]").each(function () {
            $(this).css("cursor", "pointer");
            var curField = $("input[name='orderField']").val();
            var curType = $("input[name='orderType']").val();
            if (curField == $(this).attr("sort-field")) {
                if (curType == 0) {
                    $(this).find("i").removeClass().addClass("fa fa-sort-up");
                } else {
                    $(this).find("i").removeClass().addClass("fa fa-sort-down");
                }
            }
            $(this).click(function () {
                var field = $(this).attr("sort-field");
                var type = 0;
                if (field == curField) {
                    if (curType == 0) {
                        curType = 1;
                        type = 1;
                    } else {
                        curType = 0;
                        type = 0;
                    }
                }
                $("input[name='orderField']").val(field);
                $("input[name='orderType']").val(type);
                search();
            });
        });
    });


</script>