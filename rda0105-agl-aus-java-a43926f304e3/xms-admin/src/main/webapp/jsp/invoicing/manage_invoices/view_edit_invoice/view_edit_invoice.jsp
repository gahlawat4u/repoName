<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="View/Edit Invoice"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="View/Edit Invoice"/></li>
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
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="View/Edit Invoice"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <table class="s36" style="width: auto; margin-bottom: auto">
                                            <tbody>
                                            <tr>
                                                <td><xms:localization text="result(s)"/></td>
                                                <td id="td_invoice_list_result"><s:select list="invoiceList"
                                                                                          listKey="invoiceId"
                                                                                          id="sel_invoice_data"
                                                                                          name="invoiceId"
                                                                                          listValue="invoiceCode"
                                                                                          class="form-control"
                                                                                          onchange="searchInvoiceDetail($(this).val())"
                                                                                          value="invoiceId"/></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12 form-group">
                                                <s:form id="form_search_invoice">
                                                    <table class="s36 b24">
                                                        <tr>
                                                            <td>
                                                                <table class="s36 b24"
                                                                       style="width: auto; margin-bottom: auto">
                                                                    <tr>
                                                                        <td><label class="radio-inline cl666"><input
                                                                                id="searchType" type="radio" value="0"
                                                                                name="searchType"/></label></td>
                                                                        <td><input name="customerCode"
                                                                                   class="form-control alloptions "
                                                                                   type="text" placeholder="Search"/>
                                                                        </td>
                                                                        <td>
                                                                            <div class="form-group input-group mg0">
																				<span class="input-group-addon s31"> <i
                                                                                        class="fa fa-calendar"></i>
																				</span> <input readonly="readonly"
                                                                                               name="fromDate"
                                                                                               class="form-control form_datetime"
                                                                                               type="text"
                                                                                               data-date-format="dd MM yyyy"
                                                                                               placeholder="Start"
                                                                                               style="width: 70px;"/>
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <div class="form-group input-group mg0">
																				<span class="input-group-addon s31"> <i
                                                                                        class="fa fa-calendar"></i>
																				</span> <input readonly="readonly"
                                                                                               name="toDate"
                                                                                               class="form-control form_datetime"
                                                                                               type="text"
                                                                                               data-date-format="dd MM yyyy"
                                                                                               placeholder="End"
                                                                                               style="width: 70px;"/>
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><label class="radio-inline cl666"><input
                                                                                id="searchType" type="radio" value="1"
                                                                                name="searchType"
                                                                                checked="checked"/></label></td>
                                                                        <td colspan="3"><s:select list="dateList"
                                                                                                  class="form-control"
                                                                                                  listValue="invoiceDate"
                                                                                                  listKey="invoiceDate"
                                                                                                  name="invoiceDate"
                                                                                                  value="invoiceDate"/></td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                            <td class="s56a">
                                                                <table class="s36 b24">
                                                                    <tr>
                                                                        <td><select class="form-control"
                                                                                    name="franchiseSearchType">
                                                                            <option value="all"><xms:localization
                                                                                    text="All Franchises"/></option>
                                                                            <option value="exclude"><xms:localization
                                                                                    text="Exclude Franchises"/></option>
                                                                            <option value="include"><xms:localization
                                                                                    text="Include Franchises"/></option>
                                                                        </select></td>
                                                                        <td><input class="form-control alloptions "
                                                                                   type="text" placeholder=""
                                                                                   name="franchiseSearchTypeValue"/>
                                                                        </td>
                                                                        <td><xms:localization
                                                                                text="Invoice Status"/></td>
                                                                        <td><select class="form-control" name="status">
                                                                            <option value=""></option>
                                                                            <option value="0"><xms:localization
                                                                                    text="Unfrozen"/></option>
                                                                            <option value="1"><xms:localization
                                                                                    text="Frozen"/></option>
                                                                            <option value="2"><xms:localization
                                                                                    text="Sent Email"/></option>
                                                                            <option value="4"><xms:localization
                                                                                    text="Printed"/></option>
                                                                        </select></td>
                                                                        <td><input type="checkbox"
                                                                                   id="non-email-invoice"/>
                                                                            <input type="hidden" name="nonEmailInvoice">
                                                                        </td>
                                                                        <td><xms:localization
                                                                                text="Non-email invoices only"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><input name="minAirbills"
                                                                                   class="form-control alloptions "
                                                                                   type="text"
                                                                                   placeholder="<xms:localization text='Min airbills'/>"/>
                                                                        </td>
                                                                        <td><input name="maxAirbills"
                                                                                   class="form-control alloptions "
                                                                                   type="text"
                                                                                   placeholder="<xms:localization text='Max airbills'/>"/>
                                                                        </td>
                                                                        <td colspan="2">
                                                                            <button class="btn s37" type="button"
                                                                                    onclick="searchInvoice()">
                                                                                <xms:localization text="Search"/>
                                                                            </button>
                                                                            <button class="btn s37" type="button"
                                                                                    id="btn_new_invoice"
                                                                                    onclick="newInvoice()">
                                                                                <xms:localization text="New Invoice"/>
                                                                            </button>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </div>
                                            <div class="portlet-body" style="border: 1px solid #e5e5e5; margin: 0 20px;"
                                                 id="div_invocie_detail">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <table class="s36 b24" align="right">
                                                            <tr>
                                                                <td><span class="s30"><xms:localization
                                                                        text="This will be an email invoice to:"/> <s:property
                                                                        value="invoiceInfoModel.billingEmail"/></span>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <img src="images/LOGOiN.png" width="100%"
                                                             style="margin-top: -30px;"/>
                                                    </div>
                                                    <div class="col-lg-9">
                                                        <div class="col-lg-10">
                                                            <p style="color: red">
                                                                <s:if test="invoiceInfoModel.status==0">
                                                                    <xms:localization text="This invoice is frozen"/>
                                                                </s:if>
                                                            </p>

                                                            <p>
                                                                <s:property value="companyAddress" escape="false"/>
                                                            </p>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <table class="s36 b24" align="right">
                                                                <tr>
                                                                    <td><input type="checkbox" id="chkShowPayments"
                                                                               onclick="showPayments()"></td>
                                                                    <td><xms:localization text="Show Payments"/></td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <table id="invoice-info-summary"
                                                                   class="table table-bordered table-striped"
                                                                   style="margin-top: 5px">
                                                                <thead>
                                                                <tr>
                                                                    <th><xms:localization text="Invoice Number"/></th>
                                                                    <th><xms:localization text="Invoice Date"/></th>
                                                                    <th><xms:localization text="Customer #"/></th>
                                                                    <th class="text-right"><xms:localization
                                                                            text="Airbills"/></th>
                                                                    <th><xms:localization text="Due Date"/></th>
                                                                    <th class="text-right"><xms:localization
                                                                            text="Amount Due"/></th>
                                                                    <th class="text-right"><xms:localization
                                                                            text="If Not Paid by Due Date"/></th>
                                                                    <th class="text-right" group="show-payments">
                                                                        <xms:localization text="Paid/Credited"/></th>
                                                                    <th class="text-right" group="show-payments">
                                                                        <xms:localization text="Remaining Due"/></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <tr>
                                                                    <td><s:property
                                                                            value="invoiceInfoModel.invoiceCode"/></td>
                                                                    <td><s:property
                                                                            value="invoiceInfoModel.invoiceDate"/></td>
                                                                    <td><s:property
                                                                            value="invoiceInfoModel.customerCode"/>
                                                                        <s:hidden name="invoiceInfoModel.customerCode"
                                                                                  id="hid_invoice_customer"></s:hidden></td>
                                                                    <td class="text-right"><s:property
                                                                            value="invoiceInfoModel.noOfAirbills"/></td>
                                                                    <td><s:property
                                                                            value="invoiceInfoModel.dueDate"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceInfoModel.totalAmount"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceInfoModel.totalIfNotPaidByDueDate"/></td>
                                                                    <td class="text-right" group="show-payments">
                                                                        <s:property value="currencySymbol"/> <s:property
                                                                            value="invoiceInfoModel.totalPaid"/></td>
                                                                    <td class="text-right" group="show-payments">
                                                                        <s:property value="currencySymbol"/> <s:property
                                                                            value="invoiceInfoModel.remainingDue"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <div class="col-lg-6">
                                                            <p style="border: 1px solid #ddd; padding: 8px;">
                                                                <strong><xms:localization
                                                                        text="BILL TO:"/></strong><br/>
                                                                <s:property
                                                                        value="invoiceInfoModel.billingCustomerName"/>
                                                                <br/>
                                                                <s:property value="invoiceInfoModel.billingAddress1"/>
                                                                <br/>
                                                                <s:if test="%{invoiceInfoModel.billingAddress2!=null && invoiceInfoModel.billingAddress2!=''}">
                                                                    <s:property
                                                                            value="invoiceInfoModel.billingAddress2"/>
                                                                    <br/>
                                                                </s:if>
                                                                <s:if test="%{invoiceInfoModel.billingPostalCode!=null && invoiceInfoModel.billingPostalCode!=''}">
                                                                    <s:property
                                                                            value="invoiceInfoModel.billingPostalCode"/>
                                                                    ,
                                                                </s:if>
                                                                <s:if test="%{invoiceInfoModel.billingCity!=null && invoiceInfoModel.billingCity!=''}">
                                                                    <s:property value="invoiceInfoModel.billingCity"/>
                                                                    ,
                                                                </s:if>
                                                                <s:property
                                                                        value="invoiceInfoModel.billingCountryName"/>
                                                            </p>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <p style="border: 1px solid #ddd; padding: 8px;">
                                                                <strong><xms:localization
                                                                        text="MAIL PAYMENT TO:"/> </strong> <br>
                                                                <s:property value="mailToPayment" escape="false"/>
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-9">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr bgcolor="#f9f9f9">
                                                                <th><xms:localization text="GST Summary"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="GST Percent"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Credit Amount"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="GST Amount"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Total Amount"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tr>
                                                                <td bgcolor="#f9f9f9"><strong><xms:localization
                                                                        text="GST Shipments"/></strong></td>
                                                                <td class="text-right"><s:property
                                                                        value="invoiceInfoModel.gstTaxPercent"/>%
                                                                </td>
                                                                <td class="text-right"><s:property
                                                                        value="currencySymbol"/> <s:property
                                                                        value="invoiceInfoModel.gstTotalCost"/></td>
                                                                <td class="text-right"><s:property
                                                                        value="currencySymbol"/> <s:property
                                                                        value="invoiceInfoModel.gstTotalTax"/></td>
                                                                <td class="text-right"><s:property
                                                                        value="currencySymbol"/> <s:property
                                                                        value="invoiceInfoModel.gstTotalAmount"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td bgcolor="#f9f9f9"><strong><xms:localization
                                                                        text="Non-GST Shipments"/></strong></td>
                                                                <td class="text-right"><s:property
                                                                        value="invoiceInfoModel.nonGstTaxPercent"/>%
                                                                </td>
                                                                <td class="text-right"><s:property
                                                                        value="currencySymbol"/> <s:property
                                                                        value="invoiceInfoModel.nonGstTotalCost"/></td>
                                                                <td class="text-right"><s:property
                                                                        value="currencySymbol"/> <s:property
                                                                        value="invoiceInfoModel.nonGstTotalTax"/></td>
                                                                <td class="text-right"><s:property
                                                                        value="currencySymbol"/> <s:property
                                                                        value="invoiceInfoModel.nonGstTotalAmount"/></td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-12 ">
                                                        <div class="s70">
                                                            <table class="table table-bordered">
                                                                <thead>
                                                                <tr bgcolor="#f9f9f9">
                                                                    <th style="vertical-align: text-top !important;">
                                                                        <xms:localization
                                                                                text="Carrier - Airbill #"/><br/>
                                                                        <xms:localization text="Orig/Dest"/><br/>
                                                                        <xms:localization text="Ship Date"/><br/>
                                                                        <xms:localization text="Customer #"/><br/>
                                                                        <xms:localization text="Reference"/><br/>
                                                                        <xms:localization text="Reference 2"/></th>
                                                                    <th style="vertical-align: text-top !important;">
                                                                        <xms:localization text="Sender Address"/></th>
                                                                    <th style="vertical-align: text-top !important;">
                                                                        <xms:localization text="Receiver Address"/></th>
                                                                    <th style="vertical-align: text-top !important;">
                                                                        <xms:localization text="Pieces"/><br/>
                                                                        <xms:localization text="Weight"/><br/>
                                                                        <xms:localization text="Dimensions"/><br/>
                                                                        <xms:localization text="Zone"/></th>
                                                                    <th style="vertical-align: text-top !important;">
                                                                        <xms:localization text="Charges"/></th>
                                                                    <th style="vertical-align: text-top !important;"
                                                                        class="text-right"><xms:localization
                                                                            text="Total"/></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <s:iterator value="airbillInfoModels">
                                                                    <tr>
                                                                        <td><p>
                                                                            <a href="javascript:void(0)"
                                                                               title="Edit Airbill"
                                                                               onclick="loadAirbillDetail(<s:property
                                                                                       value='shipmentId'/>, '
                                                                                   <s:property
                                                                                           value='airbillNumber'/>')"><i
                                                                                    class="fa fa fa-pencil s10 b3"
                                                                                    style="font-size: 14px; margin-left: 5px"
                                                                                    id="md-20-link"></i></a>

                                                                            <s:if test="invoiceInfoModel.status != 0">
                                                                                <a href="javascript:void(0)"
                                                                                   title="Adjustment"
                                                                                   onclick="loadAjustment(<s:property
                                                                                           value='shipmentId'/>, '
                                                                                       <s:property
                                                                                               value='airbillNumber'/>','
                                                                                       <s:property
                                                                                               value='%{invoiceInfoModel.invoiceCode}'/>')"><i
                                                                                        class="fa fa-wrench s10 b3"
                                                                                        style="font-size: 14px; margin-left: 5px"
                                                                                        id="md-7-link"></i></a>
                                                                            </s:if>

                                                                            <a href="javascript:void(0)"
                                                                               title="View Rate Sheet"
                                                                               onclick="viewAirbillRateSheet(<s:property
                                                                                       value='invoiceInfoModel.customerCode'/>, '
                                                                                   <s:property value='airbillNumber'/>',
                                                                                   <s:property value='shipmentId'/>)"><i
                                                                                    class="fa fa-file-text s10 b3"
                                                                                    style="font-size: 14px; margin-left: 5px"></i></a>
                                                                            <s:if test="invoiceInfoModel.status == 0">
                                                                                <a href="javascript:void(0)"
                                                                                   title="Move Airbill"
                                                                                   onclick="loadMoveAirbill(<s:property
                                                                                           value='shipmentId'/>, '
                                                                                       <s:property
                                                                                               value='airbillNumber'/>','
                                                                                       <s:property
                                                                                               value='%{invoiceInfoModel.invoiceDate}'/>', '
                                                                                       <s:property
                                                                                               value='%{invoiceInfoModel.invoiceId}'/>', '
                                                                                       <s:property
                                                                                               value='%{invoiceInfoModel.customerCode}'/>')"><i
                                                                                        class="fa fa-retweet s10 b3"
                                                                                        style="font-size: 14px; margin-left: 5px"
                                                                                        id="md-7-link"></i></a>
                                                                            </s:if>
                                                                            <a href="javascript:void(0)"
                                                                               title="View Customer"
                                                                               onclick="viewManageCustomer(<s:property
                                                                                       value='customerCode'/>)"><i
                                                                                    class="fa fa-user s10 b3"
                                                                                    style="font-size: 16px; margin-left: 5px"></i></a>
                                                                            <a href="javascript:void(0)"
                                                                               title="Edit Sender Address"
                                                                               onclick="loadSenderEdit(<s:property
                                                                                       value='senderAddressId'/>)"><i
                                                                                    class="fa fa fa-reply s10 b3"
                                                                                    style="font-size: 14px; margin-left: 5px"
                                                                                    id="md-6-link"></i></a> <a
                                                                                href="javascript:void(0)"
                                                                                title="Edit Receiver Address"
                                                                                onclick="loadReceiverEdit(<s:property
                                                                                        value='receiverAddressId'/>)"><i
                                                                                class="fa fa fa-share s10 b3"
                                                                                style="font-size: 14px; margin-left: 5px"
                                                                                id="md-6a-link"></i></a>
                                                                            <%--XTD-58 only show delete button for admin level 1,2--%>
                                                                            <s:if test="invoiceInfoModel.status == 0 && #session.SESS_XMS_ADMIN_LEVEL_INT < 3">
                                                                                <a href="javascript:void(0)"
                                                                                   title="Delete Airbill"
                                                                                   onclick="deleteAirbill('<s:property
                                                                                           value='airbillNumber'/>',
                                                                                       <s:property value='shipmentId'/>,
                                                                                       <s:property
                                                                                               value='%{invoiceInfoModel.invoiceId}'/>)"><i
                                                                                        class="fa fa-times-circle-o s10 b3"
                                                                                        style="font-size: 16px; margin-left: 5px"></i></a>
                                                                            </s:if>
                                                                        </p> <s:property value="serviceName"/> -
                                                                            <s:property value="airbillNumber"/><br/>
                                                                            <s:property value="shipmentDate"/><br/>
                                                                            <s:property value="customerCode"/><br/>
                                                                            <s:property value="reference"/><br/>
                                                                            <s:property value="reference2"/></td>
                                                                        <td><s:property value="senderCompanyName"/>
                                                                            <br/> <s:property
                                                                                    value="senderContactName"/><br/>
                                                                            <s:property value="senderAddress1"/><br/>
                                                                            <s:property value="senderCity"/>-<s:property
                                                                                    value="senderStates"/> - <s:property
                                                                                    value="senderPostalCode"/><br/>
                                                                            <s:property value="senderCountryName"/></td>
                                                                        <td><s:property value="receiverCompanyName"/>
                                                                            <br/> <s:property
                                                                                    value="receiverContactName"/><br/>
                                                                            <s:property value="receiverAddress1"/><br/>
                                                                            <s:property
                                                                                    value="receiverCity"/>-<s:property
                                                                                    value="receiverStates"/> -
                                                                            <s:property
                                                                                    value="receiverPostalCode"/><br/>
                                                                            <s:property value="receiverCountryName"/>
                                                                        </td>
                                                                        <td><s:property value="noOfPieces"/> <br/>
                                                                            <s:property value="weight"/> <br/> <br/>
                                                                            <s:property value="zone"/> <br/></td>
                                                                        <td><s:iterator value="charges">
                                                                            <s:property value="awbDescription"/> -
                                                                            <s:property value="currencySymbol"/>
                                                                            <s:property value="awbCustomerCost"/>
                                                                            <br/>
                                                                        </s:iterator></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/> <s:property
                                                                                value="total"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                                </tbody>
                                                                <tfoot>
                                                                <tr>
                                                                    <td colspan="6"><span
                                                                            class="b4"> <b><xms:localization
                                                                            text="Total:"/></b> <s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceInfoModel.totalCost"/>
																		</span> <span class="b4"> <b>| <xms:localization
                                                                            text="Tax Amount:"/></b> <s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceInfoModel.totalTax"/>
																		</span> <span class="b4"> <b>| <xms:localization
                                                                            text="Grand Total:"/></b> <s:property
                                                                            value="currencySymbol"/> <s:property
                                                                            value="invoiceInfoModel.totalAmount"/>
																		</span></td>
                                                                </tr>
                                                                </tfoot>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="div_delete_airbill"></div>
<div id="div_new_invoice"></div>
<div id="div_new_invoice_do"></div>
<div id="div_new_airbill_do"></div>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // Load invoice if invoice code is not null.
        var invoiceId = '<s:property value="invoiceId" />';
        var action = "view_edit_invoice_get_invoice_detail.ix?reqType=json";
        if (invoiceId > 0) {
            var data = {
                'invoiceId': invoiceId
            };
            doPostDataByParameters(action, data, "", "div_invocie_detail", true, false);
            $("#selected-inv").val(invoiceId);
        }
    });

    function newInvoice() {
        var action = "view_edit_invoice_new_invoice.ix?reqType=json";
        var actionLoad = "view_edit_invoice_new_invoice_do.ix?reqType=json";
        var actionSave = "view_edit_invoice_new_airbill_do.ix?reqType=json";
        var data = {};
        load2DialogToSave(action, actionLoad, data, "form_new_invoice", "Save", "Close", "div_new_invoice", "Adding Invoice", "div_new_invoice_do", "New Airbill", "Save", "div_new_airbill_do", actionSave, 'form_new_airbill');
    }
    function searchInvoice() {
        var action = "view_edit_invoice_search_invoice.ix?reqType=json";
        if ($("#non-email-invoice").is(":checked")) {
            $("input[name='nonEmailInvoice']").val("true");
        } else {
            $("input[name='nonEmailInvoice']").val("false");
        }
        doPostData(action, "form_search_invoice", "", "td_invoice_list_result", true);
    }
    function searchInvoiceDetail(val) {
        if (val > 0) {
            var action = "view_edit_invoice_get_invoice_detail.ix?reqType=json";
            var data = {
                'invoiceId': val
            };
            doPostDataByParameters(action, data, "", "div_invocie_detail", true, false);
            $("#selected-inv").val(val);
        } else if (val == -3) {
            var postData = "";
            var i = 0;
            $("select#sel_invoice_data option").map(function () {
                if ($(this).val() != -1 && $(this).val() != -2 && $(this).val() != -3) {
                    postData += "&listInvoices[" + i + "]=" + $(this).val();
                    i++;
                }
            });
            postData = postData.substring(1, postData.length);
            var data = postData;
            loadingDialog.dialog("open");
            $.fileDownload("download_invoices_in_zip.ix", {
                failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                httpMethod: "POST",
                data: data,
                successCallback: function (url) {
                    loadingDialog.dialog("close");
                },
                failCallback: function (url) {
                    loadingDialog.dialog("close");
                },
            });
        }
    }

    function deleteAirbill(airbillNumber, shipmentId, invoiceId) {
        var data = {
            'shipmentId': shipmentId,
            'airbillNumber': airbillNumber,
            'invoiceId': invoiceId
        };
        var actionLoad = "view_edit_invoice_delete_airbill.ix?reqType=json";
        var actionSave = "view_edit_invoice_delete_airbill_do.ix?reqType=json";
        loadResultToSave(actionLoad, actionSave, data, "form_delete_airbill", "OK", "Cancel", "div_delete_airbill", "Delete Airbill", "div_invocie_detail");
    }


</script>