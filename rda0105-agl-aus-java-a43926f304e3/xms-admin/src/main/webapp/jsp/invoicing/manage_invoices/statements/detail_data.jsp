<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="tab-content responsive">
    <div class="tab-pane fade in active">
        <div class="row">
            <h2 style="text-align: center">
                <xms:localization text="Statement of Accounts"/>
            </h2>

            <div class="col-lg-12">
                <table class="table table-bordered" style="margin-top: 5px">
                    <thead>
                    <tr>
                        <th><xms:localization text="Date"/></th>
                        <th><xms:localization text="Customer #"/></th>
                        <th class="text-right"><xms:localization text="Total Due"/></th>
                        <th class="text-right"><xms:localization text="<=0 days"/></th>
                        <th class="text-right"><xms:localization text="1 to 15"/></th>
                        <th class="text-right"><xms:localization text="16 to 30"/></th>
                        <th class="text-right"><xms:localization text="31 to 45"/></th>
                        <th class="text-right"><xms:localization text="46 to 60"/></th>
                        <th class="text-right"><xms:localization text="61 to 90"/></th>
                        <th class="text-right"><xms:localization text="91 to 120"/></th>
                        <th class="text-right"><xms:localization text="Over 120"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:if test="aging!=null">
                        <tr>
                            <td><s:property value="currentDate"/></td>
                            <td><s:property value="customerCode"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="aging.totalDue"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="aging.range0"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="aging.range1To15"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="aging.range16To30"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="aging.range31To45"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="aging.range46To60"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="aging.range61To90"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="aging.range91To120"/></td>
                            <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                    value="aging.range120"/></td>
                        </tr>
                    </s:if>
                    <s:else>
                        <tr>
                            <td colspan="11"><xms:localization text="No data available..."/></td>
                        </tr>
                    </s:else>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-6">
                <p style="border: 1px solid #ddd; padding: 8px;">
                    <strong><xms:localization text="BILL TO:"/> </strong> <br>
                    <s:property value="billingAddress" escape="false"/>
                </p>
            </div>
            <div class="col-lg-6">
                <p style="border: 1px solid #ddd; padding: 8px;">
                    <strong><xms:localization text="MAIL PAYMENT TO:"/> </strong> <br>
                    <s:property value="mailPaymentAddress" escape="false"/>
                </p>
            </div>
            <div class="col-lg-12">
                <table class="table mg0">
                    <tbody>
                    <tr>
                        <th class="s42">
                            <table class="s36">
                                <tbody>
                                <tr>
                                    <td><xms:localization text="Show"/></td>
                                    <td><s:select id="pageSize" name="pageSize" cssClass="form-control"
                                                  cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                                  onchange="invoicePaging()"/></td>
                                    <td><xms:localization text="Entries"/></td>
                                </tr>
                                </tbody>
                            </table>
                        </th>
                    </tr>
                    </tbody>
                </table>
                <form id="statement_invoice_form">
                    <input type="hidden" id="orderField" name="orderField" value="invoice_code"/> <input type="hidden"
                                                                                                         id="orderType"
                                                                                                         name="orderType"
                                                                                                         value="0"/>
                    <input type="hidden" id="page" name="page" value="1"/>
                </form>
                <div id="customer_invoice_list">
                    <div class="s70">
                        <table class="table table-bordered mg0" style="margin-top: 20px;"
                               id="customer_invoice_list_table">
                            <thead>
                            <tr bgcolor="#f9f9f9">
                                <th><xms:localization text="Invoice #"/></th>
                                <th><xms:localization text="Invoice Date"/></th>
                                <th><xms:localization text="Due Date"/></th>
                                <th class="text-right"><xms:localization text="Invoice Amount"/></th>
                                <th class="text-right"><xms:localization text="Late Fee"/></th>
                                <th class="text-right"><xms:localization text="Invoice Total"/></th>
                                <th class="text-right"><xms:localization text="Total Paid"/></th>
                                <th class="text-right"><xms:localization text="Remaining Due"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:if test="invoices==null || invoices.totalRecords==0">
                                <tr>
                                    <td colspan="8"><xms:localization text="No data available..."/></td>
                                </tr>
                            </s:if>
                            <s:else>
                                <s:iterator value="invoices.records">
                                    <tr>
                                        <td><s:property value="invoiceCode"/></td>
                                        <td><s:property value="invoiceDate"/></td>
                                        <td><s:property value="dueDate"/></td>
                                        <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                value="invoiceAmount"/></td>
                                        <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                value="lateFee"/></td>
                                        <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                value="invoiceTotal"/></td>
                                        <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                value="totalPaid"/></td>
                                        <td class="text-right"><s:property value="currencySymbol"/> <s:property
                                                value="remainingDue"/></td>
                                    </tr>
                                </s:iterator>
                                <tr>
                                    <th colspan="8"><xms:localization text="Showing"/> <s:property
                                            value="invoices.startRecord"/> <xms:localization text="to"/> <s:property
                                            value="invoices.endRecord"/> <xms:localization text="of"/> <s:property
                                            value="invoices.totalRecords"/></th>
                                </tr>
                                <s:if test="summary!=null">
                                    <tr>
                                        <td colspan="8"><span class="b4"> <b><xms:localization text="Total Due: "/></b> <s:property
                                                value="currencySymbol"/> <s:property
                                                value="summary.remainingDue"/></span></td>
                                    </tr>
                                </s:if>
                            </s:else>
                            </tbody>
                        </table>
                    </div>
                    <div class="dataTables_paginate">
                        <s:if test="invoices!=null">
                            <s:if test="invoices.hasPrev()">
                                <a href="javascript:changePage(<s:property value="%{invoices.currentPage - 1}"/>)"
                                   class="paginate_button previous"><xms:localization text="Previous"/></a>
                            </s:if>
							<span> <s:iterator value="invoices.pageRange" status="count">
                                <s:if test="%{invoices.pageRange[#count.index] == invoices.currentPage}">
                                    <a class="paginate_button current"><s:property value="invoices.currentPage"/></a>
                                </s:if>
                                <s:else>
                                    <a class="paginate_button"
                                       href="javascript:changePage(<s:property/>);"><s:property/></a>
                                </s:else>
                            </s:iterator>
							</span>
                            <s:if test="invoices.hasNext()">
                                <a class="paginate_button next"
                                   href="javascript:changePage(<s:property value="%{invoices.currentPage+1}"/>)"><xms:localization
                                        text="Next"/></a>
                            </s:if>
                        </s:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var fieldList = ["invoice_code", "invoice_date", "due_date", "invoice_amount", "late_fee", "invoice_total", "total_paid", "remaining_due"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#customer_invoice_list_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: invoicePaging
        });
    });


</script>