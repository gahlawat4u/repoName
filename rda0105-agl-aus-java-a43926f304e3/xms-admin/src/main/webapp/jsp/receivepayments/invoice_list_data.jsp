<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:if test="%{fieldErrors.isEmpty()}">
    <div class="col-lg-12">
        <table class="table mg0">
            <tr>
                <th class="s42"></th>
            </tr>
        </table>
        <table class="table table-hover table-striped table-bordered mg0" id="datatable1">
            <thead>
            <tr>
                <th><xms:localization text="Invoice Number"/></th>
                <th><xms:localization text="Due Date"/></th>
                <th><xms:localization text="Total"/></th>
                <th><xms:localization text="Late Fee"/></th>
                <th><xms:localization text="Paid /Credit"/></th>
                <th><xms:localization text="Owed"/></th>
                <th><xms:localization text="Payments"/></th>
                <th><xms:localization text="AWB Level"/></th>
                <th width="25px;"><i class="fa fa-times-circle-o s10" style="font-size: 18px;"></i></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="%{invoiceList==null || invoiceList.isEmpty()}">
                <tr>
                    <td colspan="9"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="invoiceList" status="row">
                    <tr>
                        <td><s:property value="invoiceCode"/> <s:hidden name="invoiceList[%{#row.index}].invoiceId"/>
                            <s:hidden name="invoiceList[%{#row.index}].invoiceCode"/> <s:hidden
                                    name="invoiceList[%{#row.index}].customerCode"/> <s:hidden
                                    name="invoiceList[%{#row.index}].invoiceDate"/> <s:hidden
                                    name="invoiceList[%{#row.index}].status"/> <s:hidden
                                    name="invoiceList[%{#row.index}].paid"/> <s:hidden
                                    name="invoiceList[%{#row.index}].invCreateDate"/></td>
                        <td><s:property value="dueDate"/> <s:hidden name="invoiceList[%{#row.index}].dueDate"/></td>
                        <td><s:property value="totalAmount"/> <s:hidden
                                name="invoiceList[%{#row.index}].totalAmount"/></td>
                        <td><s:property value="lateFee"/> <s:hidden name="invoiceList[%{#row.index}].lateFee"/></td>
                        <td><s:property value="totalPayment"/> <s:hidden
                                name="invoiceList[%{#row.index}].totalPayment"/></td>
                        <td><s:property value="remainningBalance"/> <s:hidden
                                name="invoiceList[%{#row.index}].remainningBalance"/></td>
                        <th><s:property value="payment"/> <s:hidden name="invoiceList[%{#row.index}].payment"/></th>
                        <th><a href="javascript:loadInvDetails('inv_<s:property value="invoiceCode" />_details');"><i
                                class="fa fa fa-exclamation-triangle s10" style="font-size: 18px;"></i></a></th>
                        <th><i class="fa fa-times-circle-o s10" style="font-size: 18px;"></i>
                            <!-- Begin Invoice Details -->
                            <div id="inv_<s:property value="invoiceCode" />_details" style="display: none">
                                <div class="modal-body text-left">
                                    <table class="table" style="font-size: 11px; margin-bottom: 0px">
                                        <tr>
                                            <td class="td1"><xms:localization text="Invoice"/></td>
                                            <td class="td2"><s:property value="invoiceCode"/></td>
                                            <td class="td1"><xms:localization text="Total Unreconciled Amount"/></td>
                                            <td class="td2"></td>
                                        </tr>
                                        <tr>
                                            <td class="td1"><xms:localization text="Invoice Note"/></td>
                                            <td class="td2"></td>
                                            <td class="td1"><xms:localization text="Amount Applied"/></td>
                                            <td class="td2"></td>
                                        </tr>
                                        <tr>
                                            <td class="td1"><xms:localization text="Previously Reconciled"/></td>
                                            <td class="td2"></td>
                                            <td class="td1"><xms:localization text="Total Unpaid on invoice"/></td>
                                            <td class="td2"><s:property value="remainningBalance"/></td>
                                        </tr>
                                        <tr>
                                            <td class="td1"><xms:localization text="Remaining Unapplied Amount"/></td>
                                            <td class="td2"></td>
                                            <td class="td1"></td>
                                            <td class="td2"></td>
                                        </tr>
                                    </table>
                                    </br>
                                    <table class="table table-hover table-bordeed mg0" id="datatable1">
                                        <thead>
                                        <tr>
                                            <th><xms:localization text="Airbill"/></th>
                                            <th><xms:localization text="Customer Total"/></th>
                                            <th><xms:localization text="Previously Paid"/></th>
                                            <th><xms:localization text="Adjustments Credits"/></th>
                                            <th><xms:localization text="Amount Due"/></th>
                                            <th><xms:localization text="Pending Adjustments"/></th>
                                            <th><xms:localization text="Denied Adjustments"/></th>
                                            <th><xms:localization text="Payments"/></th>
                                            <th><i class="fa fa-times-circle-o s10" style="font-size: 18px;"></i></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="shipmentInvoices" status="awbrow">
                                            <tr>
                                                <td><s:property value="airbillNumber"/> <s:hidden
                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].invoiceId"/>
                                                    <s:hidden
                                                            name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].shipmentId"/>
                                                    <s:hidden
                                                            name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].airbillNumber"/></td>
                                                <td><s:property value="totalAmount"/> <s:hidden
                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].totalAmount"/></td>
                                                <td><s:property value="totalPayment"/> <s:hidden
                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].totalPayment"/></td>
                                                <td><s:property value="adjustmentCredit"/> <s:hidden
                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].adjustmentCredit"/></td>
                                                <td><s:property value="amountDue"/> <s:hidden
                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].amountDue"/></td>
                                                <td><s:property value="pendingAdjustment"/> <s:hidden
                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].pendingAdjustment"/></td>
                                                <td><s:property value="deniedAdjustment"/> <s:hidden
                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].deniedAdjustment"/></td>
                                                <td><s:property value="payment"/> <s:hidden
                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].payment"/></td>
                                                <td><i class="fa fa-times-circle-o s10" style="font-size: 18px;"></i>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- End Invoice Details --></th>
                    </tr>
                </s:iterator>
            </s:else>
            </tbody>
        </table>
    </div>
</s:if>
<s:else>
    <s:fielderror/>
</s:else>