<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>


<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Receive Payments"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Receive Payments"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<div class="page-content">
    <div id="tab-general">
        <div class="row mbl">
            <div class="col-lg-12">
                <div class="col-md-12">
                    <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <form id="frmReceivePayment" method="post" action="receive_payment.ix">
                    <s:hidden id="submitType" name="receivePayment.submitType"/>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="portlet box">
                                <div class="portlet-header">
                                    <div class="caption">
                                        <xms:localization text="Receive Payments (Payments Quick Entry)"/>
                                    </div>
                                </div>
                                <div class="portlet-body" style="padding: 0px;">
                                    <div class="tab-content responsive">
                                        <div id="Overview-tab" class="tab-pane fade in active">
                                            <div class="row">
                                                <div class="col-lg-8">
                                                    <div class="form-group">
                                                        <div class="form-group">
                                                            <label class="radio-inline"> <input
                                                                    id="searchByCustomerOrInvoiceNumber" type="radio"
                                                                    name="receivePayment.searchOption" value="0"
                                                                    <s:if test='%{receivePayment==null || receivePayment.searchOption=="0"}'>checked="checked"</s:if> />
                                                                &nbsp; <xms:localization
                                                                        text="Customer/Invoice Code"/>
                                                            </label> <label class="radio-inline"> <input
                                                                id="searchByCustomerName" type="radio"
                                                                name="receivePayment.searchOption" value="1"
                                                                <s:if test='%{receivePayment.searchOption=="1"}'>checked="checked"</s:if> />
                                                            &nbsp; <xms:localization text="Customer Name"/>
                                                        </label>

                                                            <div id="validate_receive_payment"
                                                                 style="color: red; float: right;">
                                                                <s:fielderror/>
                                                                <s:actionerror/>
                                                                <span style="color: blue"><s:actionmessage/></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <table class="s36">
                                                        <tr>
                                                            <td><label><xms:localization
                                                                    text="Customer or Invoice Number:"/><span
                                                                    class="s30"> *</span> </label></td>
                                                            <td><input class="form-control alloptions" type="text"
                                                                       maxlength="25" id="customerOrInvoiceCode"
                                                                       name="receivePayment.customerOrInvoiceCode"
                                                                       <s:if test='%{receivePayment.searchOption=="1"}'>style="display:none"</s:if>
                                                                       value="<s:property value='receivePayment.customerOrInvoiceCode' />"/>
                                                                <input class="form-control alloptions" type="text"
                                                                       maxlength="25" id="customerName"
                                                                       name="receivePayment.customerName"
                                                                       <s:if test='%{receivePayment==null || receivePayment.searchOption=="0"}'>style="display:none"</s:if>
                                                                       value="<s:property value='receivePayment.customerName' />"/>
                                                            </td>
                                                            <td>
                                                                <button class="btn s37" id="btnGo" type="button">
                                                                    <xms:localization text="Go"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <div id="customer_list">
                                                                    <s:if test="%{customerList!=null && !customerList.isEmpty()}">
                                                                        <s:select class="form-control"
                                                                                  cssStyle="height: 25px;"
                                                                                  list="customerList"
                                                                                  listValue="displayName"
                                                                                  listKey="customerCode"
                                                                                  id="listOfCustomers"
                                                                                  value="receivePayment.customerOrInvoiceCode"/>
                                                                        <script>
                                                                            $(document).ready(function () {
                                                                                $("#customerOrInvoiceCode").val($('#listOfCustomers').val());
                                                                                $("#listOfCustomers").on("change", function () {
                                                                                    $("#customerOrInvoiceCode").val(this.value);
                                                                                });
                                                                            });
                                                                        </script>
                                                                    </s:if>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                    <hr>
                                                    <div class="col-lg-6">
                                                        <table class="table" style="font-size: 11px;">
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Customer Number:"/></td>
                                                                <td class="td2 s51" colspan="2"><s:property
                                                                        value="receivePayment.customerCode"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Payment Method/Type:"/><span class="s30"> *</span>
                                                                </td>
                                                                <td class="td2"><s:i18n_select
                                                                        name="receivePayment.paymentType"
                                                                        class="form-control"
                                                                        cssStyle="height: 25px;width: 140px"
                                                                        list="paymentTypeList" listKey="id"
                                                                        listValue="name"/></td>
                                                                <td class="td2"><s:textfield
                                                                        name="receivePayment.cheque"
                                                                        class="form-control alloptions" maxlength="25"
                                                                        cssStyle="width: 100px"/></td>
                                                            </tr>
                                                            <tr>
                                                                <s:set var="paramMap"
                                                                       value="#{'{currencySymbol}':'currencySymbol'}"/>
                                                                <td class="td1"><xms:localization
                                                                        text="Amount {currencySymbol}:"
                                                                        paramMap="paramMap"/><span class="s30"> *</span>
                                                                </td>
                                                                <td class="td2" colspan="2"><s:textfield
                                                                        name="receivePayment.amount"
                                                                        class="form-control alloptions" maxlength="25"
                                                                        id="receive_payment_amount"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Date:"/><span
                                                                        class="s30"> *</span></td>
                                                                <td class="td2" colspan="2">
                                                                    <div class="form-group input-group"
                                                                         style="margin-bottom: 0px;">
																				<span class="input-group-addon s31"> <i
                                                                                        class="fa fa-calendar"></i>
																				</span>
                                                                        <s:textfield name="receivePayment.paymentDate"
                                                                                     class="form-control form_datetime"
                                                                                     data-date-format="dd MM yyyy"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <table class="table" style="font-size: 11px;">
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Customer Name:"/></td>
                                                                <td class="td2 s51"><s:property
                                                                        value="receivePayment.customerName"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Bank:"/></td>
                                                                <td class="td2"><s:select class="form-control"
                                                                                          cssStyle="height: 25px;"
                                                                                          list="bankList"
                                                                                          listValue="bankName"
                                                                                          listKey="bankId"
                                                                                          name="receivePayment.bankId"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Amount Applied:"/></td>
                                                                <td class="td2 s51"><s:textfield
                                                                        name="receivePayment.appliedAmount"
                                                                        id="payment_applied_amount"
                                                                        class="form-control s50"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Remaining Unapplied:"/></td>
                                                                <td class="td2 s51"><s:textfield
                                                                        name="receivePayment.unappliedAmount"
                                                                        id="payment_unapplied_amount"
                                                                        class="form-control s50"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Total Overpayment:"/></td>
                                                                <td class="td2 s51" colspan="2"><s:property
                                                                        value="receivePayment.totalOverpayment"/></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4">
                                                    <p>
                                                        <b><xms:localization text="Note:</b>
																Entering payments into XMS: <br /> <br />
																Step 1 - Search the customer or the invoice number that payment needs to be posted.
																<br />
																Step 2 - Enter the Cheque #, received payment amount and payment date.
																<br/>
																Step 3 - Allocate the payment to the invoice by:
																<br />
																- clicking on 'Auto-Apply Payment' button 
																<br/>
																- clicking on the '>>' icon next to the respective invoice 
																<br/>
																- entering the payment for each invoice under payment column 
																<br/> <br/>
																If the payment is less the invoice, airbill reconciliation is necessary. If the payment is more than pending invoice, overpayment will be created and can be used it for future invoices."/>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="row" id="invoice_list">
                                                <div class="col-lg-12">
                                                    <table class="table mg0">
                                                        <tr>
                                                            <th class="s42"></th>
                                                        </tr>
                                                    </table>
                                                    <table class="table table-bordered mg0" id="datatable1">
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
                                                            <th width="25px;"></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="%{invoiceList==null || invoiceList.isEmpty()}">
                                                            <tr>
                                                                <td colspan="9"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="invoiceList" status="row">
                                                                <tr>
                                                                    <td><s:property value="invoiceCode"/> <s:hidden
                                                                            name="invoiceList[%{#row.index}].invoiceId"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].invoiceCode"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].customerCode"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].invoiceDate"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].status"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].paid"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].invCreateDate"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].awbLevel"/></td>
                                                                    <td><s:property value="dueDate"/> <s:hidden
                                                                            name="invoiceList[%{#row.index}].dueDate"/></td>
                                                                    <td align="right"><s:property value="totalAmount"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].totalAmount"/></td>
                                                                    <td align="right"><s:property value="lateFee"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].lateFee"/></td>
                                                                    <td align="right"><s:property value="totalPayment"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].totalPayment"/></td>
                                                                    <td align="right"><s:if
                                                                            test="%{awbLevel!=1 && awbLevel!=3}">
                                                                        <a href="javascript:autofillInvoicePayment('<s:property value="%{#row.index}"/>')">
                                                                            >> </a>
                                                                    </s:if> <s:property value="remainningBalance"/>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].remainningBalance"/>
                                                                    </td>
                                                                    <th align="right"><s:if test="%{awbLevel!=1}">
                                                                        <input type="text"
                                                                               name="invoiceList[<s:property value="%{#row.index}" />].payment"
                                                                               id="invoiceList_<s:property value="%{#row.index}" />_payment"
                                                                               value="<s:property value="payment"/>"
                                                                               data-invoice_payment_amount="invoice_payment"
                                                                               class="form-control s50"
                                                                               data-invoice_index="<s:property value="%{#row.index}" />"/>
                                                                    </s:if> <s:else>
                                                                        <s:hidden
                                                                                name="invoiceList[%{#row.index}].payment"/>
                                                                    </s:else></th>
                                                                    <th align="center"><a
                                                                            href="<s:if test="%{awbLevel!=1}">javascript:loadInvDetails('inv_<s:property value="invoiceCode" />_details');</s:if><s:else>javascript:void(0);</s:else>">
                                                                        <i id="invoiceList_<s:property value="%{#row.index}" />_payment_status"
                                                                           class="fa fa
																					<s:if test="%{awbLevel==0}">
																						fa-exclamation-triangle text-warning
																					</s:if>
																					<s:elseif test="%{awbLevel==1}">
																						fa-times-circle text-danger
																					</s:elseif>
																					<s:elseif test="%{awbLevel==2}">
																						fa-check-circle text-success
																					</s:elseif>
																					<s:elseif test="%{awbLevel==3}">
																						fa-times text-warning
																					</s:elseif>
																					s10"
                                                                           style="font-size: 18px;"></i></a></th>
                                                                    <th><s:if test="%{awbLevel!=1 && awbLevel!=3}">
                                                                        <a href="javascript:resetInvoicePayment('<s:property value="%{#row.index}" />')">
                                                                            <i class="fa fa-times-circle-o s10"
                                                                               style="font-size: 18px;"></i>
                                                                        </a>
                                                                    </s:if> <!-- Begin Invoice Details -->
                                                                        <!-- div id="inv_<s:property value="invoiceCode" />_details" style="display: none"-->
                                                                        <div id="inv_<s:property value="invoiceCode" />_details"
                                                                             style="display: none">
                                                                            <s:iterator value="shipmentInvoices"
                                                                                        status="awbrow">
                                                                                <s:hidden
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].invoiceId"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_invoiceId"/>
                                                                                <s:hidden
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].shipmentId"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_shipmentId"/>
                                                                                <s:hidden
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].airbillNumber"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_invoiceId"/>
                                                                                <s:hidden
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].totalAmount"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_airbillNumber"/>
                                                                                <s:hidden
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].totalPayment"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_invoiceId"/>
                                                                                <s:hidden
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].adjustmentCredit"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_totalPayment"/>
                                                                                <s:hidden
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].amountDue"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_amountDue"/>
                                                                                <s:hidden
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].pendingAdjustment"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_pendingAdjustment"/>
                                                                                <s:hidden
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].deniedAdjustment"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_deniedAdjustment"/>
                                                                                <s:textfield
                                                                                        name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].payment"
                                                                                        id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_payment"
                                                                                        data-airbill_payment_amount="payment"
                                                                                        data-awb_invoice_index="%{#row.index}"
                                                                                        data-awb_shipment_index="%{#awbrow.index}"/>
                                                                            </s:iterator>
                                                                        </div>
                                                                    </th>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="form-actions pal pdt10">
                                                <div class="row">
                                                    <div class="col-lg-7">
                                                        <table class="s36">
                                                            <tr>
                                                                <td><label><xms:localization text="Note:"/></label></td>
                                                                <td><textarea class="form-control alloptions"
                                                                              style="width: 300px; height: 40px;"
                                                                              name="receivePayment.note"><s:property
                                                                        value="receivePayment.note"/></textarea></td>
                                                                <td>
                                                                    <button class="btn s37" id="btnConvertToOverpayment"
                                                                            type="button">
                                                                        <xms:localization
                                                                                text="Convert to overpayment"/>
                                                                    </button>
                                                                    <button class="btn s37" id="btnSave" type="button">
                                                                        <xms:localization text="Save"/>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-5 text-right">
                                                        <button class="btn s37" type="button" id="btnAuto">
                                                            <xms:localization text="Auto-Apply Payment"/>
                                                        </button>
                                                        <button class="btn s37" type="button" id="btnReset">
                                                            <xms:localization text="Reset Payments"/>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="bottomform"></div>
                </form>
            </div>
        </div>
    </div>
</div>
<!--END CONTENT-->
</div>
</div>
<div id="overpayment-dialog-msg"></div>
<s:if test="%{invoiceList!=null && invoiceList.size()>0}">
    <s:iterator value="invoiceList" status="row">
        <!-- Begin Invoice Details -->
        <div id="inv_<s:property value="invoiceCode" />_details_tmp" title="Apply Payment to Airbills"
             style="display: none">
            <table class="table" style="font-size: 11px; margin-bottom: 0px">
                <tr>
                    <td class="td1" id="inv_<s:property value="invoiceCode" />_details_invoice_index"
                        index='<s:property value="%{#row.index}" />'><xms:localization text="Invoice"/></td>
                    <td class="td2" id="inv_<s:property value="invoiceCode" />_details_invoiceid"><s:property
                            value="invoiceCode"/></td>
                    <td class="td1"><xms:localization text="Remaining Unapplied Amount"/></td>
                    <td class="td2" align="right"><s:label id="invoiceList_%{#row.index}_unappliedAmount"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Amount Applied"/></td>
                    <td class="td2"><s:label id="invoiceList_%{#row.index}_appliedAmount"/></td>
                    <td class="td1"><xms:localization text="Total Unpaid on invoice"/></td>
                    <td class="td2"><s:property value="remainningBalance"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Previously Reconciled"/></td>
                    <td class="td2"><s:property value="totalAwbPayment"/></td>
                    <td class="td2" colspan="2"></td>
                </tr>
            </table>
            <br>
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
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="shipmentInvoices" status="awbrow">
                    <tr <s:if test="%{amountDue==0}">style="display: none"</s:if>>
                        <td><s:property value="airbillNumber"/></td>
                        <td align="right"><s:property value="totalAmount"/></td>
                        <td align="right"><s:property value="totalPayment"/></td>
                        <td align="right"><s:property value="adjustmentCredit"/></td>
                        <td align="right"><a
                                href="javascript:autofillInvoiceAirbillPayment('<s:property value="%{#row.index}"/>','<s:property value="%{#awbrow.index}" />')">
                            >> </a> <s:property value="amountDue"/></td>
                        <td align="right"><s:property value="pendingAdjustment"/></td>
                        <td align="right"><s:property value="deniedAdjustment"/></td>
                        <td align="right"><s:textfield
                                name="invoiceList[%{#row.index}].shipmentInvoices[%{#awbrow.index}].payment"
                                class="form-control s50"
                                id="invoiceList_%{#row.index}_shipmentInvoices_%{#awbrow.index}_payment_tmp"
                                data-awb_payment_tmp="awb_payment_tmp" data-group_awb_payment="%{#row.index}"/></td>
                        <td>
                            <a href="javascript:resetValue('invoiceList_<s:property value="%{#row.index}" />_shipmentInvoices_<s:property value="%{#awbrow.index}" />_payment_tmp')">
                                <i class="fa fa-times-circle-o s10" style="font-size: 18px;"></i>
                            </a></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
            <br>
        </div>
    </s:iterator>
</s:if>
<s:elseif
        test="%{receivePayment!=null && receivePayment.submitType=='Go' && (getFieldErrors().size() == 0 && getActionErrors().size() == 0)}">
    <div id="confirm-overpayment-dialog"></div>
    <script>
        $("#confirm-overpayment-dialog").html("<xms:localization text="This customer has no invoices so this payment will be convert to over payment." />");
        $("#confirm-overpayment-dialog").dialog({
            resizable: false,
            modal: true,
            title: "<xms:localization text="Notification" />",
            height: 240,
            width: 360,
            buttons: {
                "<xms:localization text="OK" />": function () {
                    $(this).dialog('close');
                },
                "<xms:localization text="Cancel" />": function () {
                    $(this).dialog('close');
                    window.location.href = "receive_payment.ix";
                },
            }
        });
    </script>
</s:elseif>
<script>
    $(document).ready(function () {
        function disableF5(e) {
            if ((e.which || e.keyCode) == 116) {
                return false
            }
            ;
        }

        $(document).bind("keydown", disableF5);
        $(document).on("keydown", disableF5);

        $("#customerName").change(function () {
            if ($("#customerName").val() != "") {
                $("#validate_receive_payment").html("");
            }
            loadCustomerList();
        });
        $("#searchByCustomerName").on('click', function () {
            $("#customerOrInvoiceCode").hide();
            $("#customerName").show();
            $("#customer_list").show();
            if ($("#customerName").val() != "") {
                loadCustomerList();
            }
        });
        $("#searchByCustomerOrInvoiceNumber").on('click', function () {
            $("#customerOrInvoiceCode").show();
            $("#customerName").hide();
            $("#customer_list").hide();
        });
        $("#btnGo").click(function () {
            $("#submitType").val("Go");
            $("#frmReceivePayment").submit();
        });
        $("#btnSave").click(function () {
            $("#submitType").val("Save");
            loadingDialog.dialog("open");
            $("#frmReceivePayment").submit();
            $('#btnSave').attr("disabled", "disabled");
        });
        $("#btnConvertToOverpayment").click(function () {
            $("#submitType").val("Convert");
            loadingDialog.dialog("open");
            $('#btnSave').attr("disabled", "disabled");
            $("#frmReceivePayment").submit();
        });
        $("#btnAuto").click(function () {
            autoApplyInvoice();
        });
        $("#btnReset").click(function () {
            $("#submitType").val("Reset");
            $("#frmReceivePayment").submit();
        });
    });

    function calculateUnappliedAmount() {
        var testdata = 0;
        $("input[data-invoice_payment_amount]").each(function () {
            testdata = testdata + Number($(this).val());
        });
        $("#payment_unapplied_amount").val(Number($("#receive_payment_amount").val() - testdata).toFixed(2));
        $("#payment_applied_amount").val(testdata.toFixed(2));

    }

    function calculateInvoiceUnappliedAmount(invoiceIndex) {
        var totalAwbApplied = Number(caculateAwbPaymentTmp(invoiceIndex));
        var totalInvoicePaymentReceive = Number($("#invoiceList_" + invoiceIndex + "_payment").val());
        var unappliedAmount = Number(totalInvoicePaymentReceive - totalAwbApplied);
        var appliedAmount = Number(totalAwbApplied);
        $("#invoiceList_" + invoiceIndex + "_unappliedAmount").html(unappliedAmount.toFixed(2));
        $("#invoiceList_" + invoiceIndex + "_appliedAmount").html(appliedAmount.toFixed(2));
        console.log("calculateInvoiceUnappliedAmount | totalAwbApplied:" + totalAwbApplied + "| totalInvoicePaymentReceive: " + totalInvoicePaymentReceive + "| unappliedAmount:" + unappliedAmount + "| appliedAmount:" + appliedAmount);
    }

    function loadCustomerList() {
        var customerName = $("#customerName").val();
        doPostWithError("receive_payment_get_cus.ix?reqType=json", {
            'receivePayment.customerName': customerName
        }, '', "customer_list", "invalidCustomerCodeOrName");
    }
    //begin init

    $('#receive_payment_amount').number(true, 2, '.', '');
    $('#payment_applied_amount').number(true, 2, '.', '');
    //$('#payment_unapplied_amount').number(true, 2, '.', '');

    $("input[data-awb_payment_tmp]").each(function () {
        $(this).number(true, 2, '.', '');

        $(this).blur(function () {
            var invoiceIndex = $(this).data("group_awb_payment");
            console.log("invoiceIndex:" + invoiceIndex);
            calculateInvoiceUnappliedAmount(invoiceIndex);
        });

    });

    $("input[data-invoice_payment_amount]").each(function () {
        $(this).number(true, 2, '.', '');
        $(this).blur(function () {
            updateInvoicePaymentView();
            var index = $(this).data("invoice_index");
            var amount = Number($(this).val());
            var invoiceOwed = Number($("#invoiceList_" + index + "__remainningBalance").val());
            if (invoiceOwed == amount) {
                autoApplyAirbill(index);
            }
            var total = caculateAwbPayment(index);
            var paymentStatusId = $(this).attr('id') + "_status";
            console.log("data-invoice_payment_amount:" + index + ":" + total + ":" + amount);
            if (amount != 0) {
                if (amount == total) {
                    $('#' + paymentStatusId).removeClass().addClass('fa fa fa-check-circle text-success');
                } else {
                    $('#' + paymentStatusId).removeClass().addClass('fa fa fa-exclamation-triangle text-warning');
                }
                if (!checkAwbPayment(index)) {
                    $('#' + paymentStatusId).removeClass().addClass('fa fa fa-exclamation-triangle text-warning');
                }
            } else {
                if (amount == total) {
                    $('#' + paymentStatusId).removeClass();
                } else {
                    $('#' + paymentStatusId).removeClass().addClass('fa fa fa-exclamation-triangle text-warning');
                }
            }

        });
        $(this).trigger("blur");
    });
    $("input[data-invoice_payment_amount]").trigger("blur");
    //end init
    function updateInvoicePaymentView() {
        calculateUnappliedAmount();
    }
    function caculateAwbPayment(index) {
        var total = Number(0);
        $("*[data-awb_invoice_index='" + index + "']").each(function () {
            console.log("before caculateAwbPayment : " + index + ":" + total + ":" + Number($(this).val()));
            total = Number(total) + Number($(this).val());
            total = total.toFixed(2);
            console.log("after caculateAwbPayment : " + index + ":" + total + ":" + Number($(this).val()));
        });
        return total;

    }

    function caculateAwbPaymentTmp(index) {
        var total = Number(0);
        $("input[data-group_awb_payment='" + index + "']").each(function () {
            console.log("before caculateAwbPayment : " + index + ":" + total + ":" + Number($(this).val()));
            total = Number(total) + Number($(this).val());
            total = total.toFixed(2);
            console.log("after caculateAwbPayment : " + index + ":" + total + ":" + Number($(this).val()));
        });
        return total;

    }

    calculateUnappliedAmount();
    $("#receive_payment_amount").change(function () {
        updateInvoicePaymentView();
    });

    function checkAwbPayment(index) {
        var result = true;
        $("*[data-awb_invoice_index='" + index + "']").each(function () {
            var shipmentIndex = $(this).data("awb_shipment_index");
            var amountDue = Number($("#invoiceList_" + index + "_shipmentInvoices_" + shipmentIndex + "_amountDue").val());
            var awbPayment = Number($(this).val());
            console.log("checkAwbPayment: " + amountDue + ":" + awbPayment);
            if (awbPayment > amountDue || awbPayment < 0)
                result = false;
        });
        return result;
    }
    function resetValue(id) {
        $("#" + id).val("");
        $("#" + id).trigger("blur");
    }
    function resetInvoicePayment(invoiceIndex) {
        $("#invoiceList_" + invoiceIndex + "_payment").val("");
        autoApplyAirbill(invoiceIndex);
        $("#invoiceList_" + invoiceIndex + "_payment").trigger("blur");
    }
    function loadInvDetails(orgdivId) {
        var divId = orgdivId + "_tmp";
        var invoiceCode = $("#" + orgdivId + "_invoiceid").html();

        var invoiceIndex = $("#" + orgdivId + "_invoice_index").attr("index");
        calculateInvoiceUnappliedAmount(invoiceIndex);
        console.log("#" + divId + "_invoice_index");
        console.log(invoiceIndex);
        //load value
        $("div#" + orgdivId + " input[data-airbill_payment_amount]").each(function () {
            var id = $(this).attr('id');
            $("#" + id + "_tmp").val($("#" + id).val());
        });
        var orgdata = $('form#frmReceivePayment').serialize();

        $("#" + divId).dialog({
            resizable: false,
            modal: true,
            width: 'auto',
            close: function (ev, ui) {
                $("div#" + orgdivId + " input[data-airbill_payment_amount]").each(function () {
                    var id = $(this).attr('id');
                    $("#" + id).val($("#" + id + "_tmp").val());
                });
                $("input[data-invoice_payment_amount]").trigger("blur");
            },
            buttons: {
                "Auto-apply Payment": function () {
                    $(this).dialog("close");
                    autoApplyAirbill(invoiceIndex);
                    loadInvDetails(orgdivId);
                },
                "Reset Payments": function () {
                    $(this).dialog("close");
                    $("div#" + orgdivId + " input[data-airbill_payment_amount]").val("");
                    loadInvDetails(orgdivId);
                },
                "Done": function () {
                    $(this).dialog("close");
                }
            }
        });
    }
    function autoApplyAirbill(invoiceIndex) {
        var invoicePayment = Number($("#invoiceList_" + invoiceIndex + "_payment").val());
        console.log("invoicePayment : " + invoiceIndex + ":" + invoicePayment);
        $("*[data-awb_invoice_index='" + invoiceIndex + "']").each(function () {
            $(this).val("");
            var shipmentIndex = $(this).data("awb_shipment_index");
            var amountDue = Number($("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + shipmentIndex + "_amountDue").val());
            invoicePayment = Number(invoicePayment);
            console.log("invoiceIndex: " + invoiceIndex);
            console.log("invoicePayment: " + invoicePayment + "|| amountDue: " + amountDue);
            if (invoicePayment > 0 && amountDue > 0) {
                var awbPayment = 0;
                if (invoicePayment > amountDue) {
                    awbPayment = amountDue;
                } else {
                    awbPayment = invoicePayment;
                }
                invoicePayment = Number(invoicePayment - awbPayment);
                invoicePayment = invoicePayment.toFixed(2);
                if (awbPayment >= 0) {
                    awbPayment = Number(awbPayment);
                    awbPayment = awbPayment.toFixed(2);
                    $(this).val(awbPayment);
                }
            }
            $(this).trigger("blur");
            console.log("apply payment: " + awbPayment + ":" + amountDue + ":" + invoicePayment);
        });
    }

    function autoApplyInvoice() {
        var receivePayment = Number($("#receive_payment_amount").val());
        console.log("receivePayment : " + receivePayment);
        $("input[data-invoice_payment_amount]").each(function () {
            $(this).val("");
            var invoiceIndex = $(this).data("invoice_index");
            var amountDue = Number($("#invoiceList_" + invoiceIndex + "__remainningBalance").val());
            if (receivePayment > 0 && amountDue > 0) {
                var invoicePayment = 0;
                if (receivePayment > amountDue) {
                    invoicePayment = amountDue;
                } else {
                    invoicePayment = receivePayment;
                }
                receivePayment = receivePayment - invoicePayment;
                if (invoicePayment >= 0) {
                    invoicePayment = Number(invoicePayment);
                    invoicePayment = invoicePayment.toFixed(2);
                    $(this).val(invoicePayment);
                    console.log("apply invoice payment: " + invoiceIndex + ":" + invoicePayment + ":" + amountDue + ":" + receivePayment);
                }
            }
            autoApplyAirbill(invoiceIndex);
            $(this).trigger("blur");
        });
    }

    function autofillInvoicePayment(invoiceIndex) {
        var remainning = Number($("#payment_unapplied_amount").val());
        //alert($("#payment_unapplied_amount").val());
        var currInvoicePayment = Number($("#invoiceList_" + invoiceIndex + "_payment").val());
        var invoiceDue = Number($("#invoiceList_" + invoiceIndex + "__remainningBalance").val());
        if (remainning + currInvoicePayment > invoiceDue) {
            $("#invoiceList_" + invoiceIndex + "_payment").val(invoiceDue);
            //fullInvoiceAirbill(invoiceIndex);
        } else {
            if (remainning + currInvoicePayment >= 0)
                $("#invoiceList_" + invoiceIndex + "_payment").val(remainning + currInvoicePayment);
        }
        $("#invoiceList_" + invoiceIndex + "_payment").trigger("blur");
    }

    function fullInvoiceAirbillPayment(invoiceIndex) {
        /*
         var currInvoicePayment = Number($("#invoiceList_"+invoiceIndex+"_payment").val());
         var invoiceDue = Number($("#invoiceList_" + invoiceIndex + "_remainningBalance").val());
         if (currInvoicePayment==invoiceDue){
         autoApplyAirbill(invoiceIndex);
         }
         */
    }

    function autofillInvoiceAirbillPayment(invoiceIndex, airbillIndex) {
        var remainning = Number($("#invoiceList_" + invoiceIndex + "_unappliedAmount").html());
        var currInvoiceAirbillPayment = Number($("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_payment_tmp").val());
        var invoiceAirbillDue = Number($("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_amountDue").val());

        if (remainning + currInvoiceAirbillPayment > invoiceAirbillDue) {
            $("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_payment_tmp").val(invoiceAirbillDue);
        } else {
            $("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_payment_tmp").val(remainning + currInvoiceAirbillPayment);
        }
        $("#invoiceList_" + invoiceIndex + "_shipmentInvoices_" + airbillIndex + "_payment_tmp").trigger("blur");
    }


</script>