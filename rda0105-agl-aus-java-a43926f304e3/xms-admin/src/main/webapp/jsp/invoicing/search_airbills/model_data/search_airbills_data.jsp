<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-hover table-bordered mg0 datatable1" id="datatable1">
    <thead>
    <tr>
        <th></th>
        <th><xms:localization text="Carrier - Airbill #"/><br> <xms:localization text="Orig/Dest"/><br>
            <xms:localization text="Ship Date"/><br> <xms:localization text="Customer #"/><br> <xms:localization
                    text="Reference"/><br> <xms:localization text="Reference 2"/></th>
        <th><xms:localization text="Sender Address"/></th>
        <th><xms:localization text="Receiver Address"/></th>
        <th><xms:localization text="Pieces"/><br> <xms:localization text="Weight"/><br> <xms:localization
                text="Dimensions Zone"/></th>
        <th><xms:localization text="Charges"/></th>
        <th><xms:localization text="Total"/></th>
        <th class="td_quoted"><xms:localization text="Quoted"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="listAirbill==null || listAirbill.totalRecords==0">
        <tr>
            <td colspan="8"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="listAirbill.records" var="index">
            <tr>
                <td><s:if test="invoiceStatus == 0">
                    <input type="checkbox" class="massEditShipments" name="massEditShipments[]"
                           value="<s:property value="shipmentId"/>,<s:property value="airbillNumber"/>,<s:property value="invoiceId"/>,<s:property value="invoiceDate"/>,<s:property value="customerCode"/>,<s:property value="invoiceStatus"/>"
                           data-shipmentId="<s:property value="shipmentId"/>"
                           data-airbillNumber="<s:property value="airbillNumber"/>"
                           data-invoiceId="<s:property value="invoiceId"/>"
                           data-invoiceDate="<s:property value="invoiceDate"/>"
                           data-customerCode="<s:property value="customerCode"/>"
                           data-invoiceStatus="<s:property value="invoiceStatus"/>"/>
                </s:if></td>
                <td>
                    <p>
                        <a href="javascript:void(0)" title="Edit Airbill"
                           onclick="loadAirbillDetail(<s:property value='shipmentId'/>, '<s:property
                                   value='airbillNumber'/>', <s:property value='invoiceStatus'/>, <s:property
                                   value='customerCode'/>)"><i class="fa fa fa-pencil s10 b3"
                                                               style="font-size: 14px; margin-left: 5px"
                                                               id="md-20-link"></i></a>
                        <s:if test="invoiceStatus != 0">
                            <a href="javascript:void(0)" title="Adjustment"
                               onclick="loadAjustment(<s:property value='shipmentId'/>, '<s:property
                                       value='airbillNumber'/>','<s:property value='invoiceCode'/>')"><i
                                    class="fa fa-wrench s10 b3" style="font-size: 14px; margin-left: 5px"
                                    id="md-7-link"></i></a>
                        </s:if>
                        <a href="javascript:void(0)" title="View Rate Sheet"
                           onclick="viewAirbillRateSheet(<s:property value='shipmentId'/>, '<s:property
                                   value='airbillNumber'/>')"><i class="fa fa-file-text s10 b3"
                                                                 style="font-size: 14px; margin-left: 5px"></i></a>
                        <s:if test="invoiceStatus == 0">
                            <a href="javascript:void(0)" title="Move Airbill"
                               onclick="loadMoveAirbill(<s:property value='shipmentId'/>, '<s:property
                                       value='airbillNumber'/>','<s:property value='invoiceDate'/>', '<s:property
                                       value='invoiceId'/>', '<s:property value='customerCode'/>', '<s:property
                                       value='invoiceStatus'/>')"><i class="fa fa-retweet s10 b3"
                                                                     style="font-size: 14px; margin-left: 5px"
                                                                     id="md-7-link"></i></a>
                        </s:if>
                        <a href="javascript:void(0)" title="View Customer"
                           onclick="viewManageCustomer('<s:property value='customerCode'/>')"><i
                                class="fa fa-user s10 b3" style="font-size: 16px; margin-left: 5px"></i></a> <a
                            href="javascript:void(0)" title="Edit Sender Address"
                            onclick="loadSenderEdit(<s:property value='senderAddressId'/>, <s:property
                                    value='invoiceStatus'/>)"><i class="fa fa fa-reply s10 b3"
                                                                 style="font-size: 14px; margin-left: 5px"
                                                                 id="md-6-link"></i></a> <a href="javascript:void(0)"
                                                                                            title="Edit Receiver Address"
                                                                                            onclick="loadReceiverEdit(
                                                                                                <s:property
                                                                                                        value='receiverAddressId'/>,
                                                                                                <s:property
                                                                                                        value='invoiceStatus'/>)"><i
                            class="fa fa fa-share s10 b3" style="font-size: 14px; margin-left: 5px" id="md-6a-link"></i></a>
                        <%--XTD-58 only show delete button for admin level 1,2--%>
                        <s:if test="invoiceStatus == 0 && #session.SESS_XMS_ADMIN_LEVEL_INT < 3">
                            <a href="javascript:void(0)" title="Delete Airbill"
                               onclick="deleteAirbill('<s:property value='airbillNumber'/>',<s:property
                                       value='shipmentId'/>,<s:property value='invoiceId'/>)"><i
                                    class="fa fa-times-circle-o s10 b3"
                                    style="font-size: 16px; margin-left: 5px"></i></a>
                        </s:if>
                        <a href="javascript:void(0)" title="Change Log"
                           onclick="viewAirbillChangeLog('',<s:property value='shipmentId'/>)"><i class="fa fa-history"
                                                                                                  aria-hidden="true"></i></a>
                    </p> <s:property value="invoiceCode"/> <br/> <s:property value="serviceName"/> - <s:property
                        value="airbillNumber"/> <br/> <s:property value="serviceAreaCodeOrigin"/> / <s:property
                        value="serviceAreaCodeDestination"/> <br/> <s:property value="shipmentDate"/> <br/> <s:property
                        value="customerCode"/> <br/> <s:property value="shipperReference"/> <br/> <s:property
                        value="billingReference2"/>
                </td>
                <td><s:property value="senderCompanyname"/> <br/> <s:property value="senderContactName"/> <br/>
                    <s:property value="senderAddress"/> <s:if test="senderAddress2 != ''">
                        <br/>
                        <s:property value="senderAddress2"/>
                    </s:if> <br/> <s:property value="senderCity"/> <s:property value="senderPostalCode"/> <s:property
                            value="senderState"/> <s:property value="senderCountry"/></td>
                <td><s:property value="receiverCompanyName"/> <br/> <s:property value="receiverContactName"/> <br/>
                    <s:property value="receiverAddress"/> <s:if test="receiverAddress2 != ''">
                        <br/>
                        <s:property value="receiverAddress2"/>
                    </s:if> <br/> <s:property value="receiverCity"/> <s:property value="receiverPostalCode"/>
                    <s:property value="receiverState"/> <s:property value="receiverCountry"/></td>
                <td><s:property value="noOfPieces"/> <br/> <s:property value="weight"/> <br/> <s:property
                        value="billActualDimension"/> <br/> <s:property value="zone"/></td>
                <td><s:iterator value="listCharge">
                    <s:property value="chargeDescription"/>
                    <s:property value="currencySymbol"/>
                    <s:property value="customerCost"/> (<s:property value="currencySymbol"/>
                    <s:property value="franchiseCost"/>) [<s:property value="currencySymbol"/>
                    <s:property value="margin"/>]
                    <br/>
                </s:iterator> <s:if test="gstTaxAmount != null">
                    <xms:localization text="GST - "/>
                    <s:property value="currencySymbol"/>
                    <s:property value="gstTaxAmount"/>
                </s:if>
                    <p style="color: red;">
                        <s:iterator value="listAdjustment">
                            <s:property value="adjustmentType"/> - <s:property value="currencySymbol"/>
                            <s:property value="customerAmount"/> [<s:property value="currencySymbol"/>
                            <s:property value="carrierAmount"/>]
                            <br/>
                        </s:iterator>
                    </p></td>
                <td><s:property value="currencySymbol"/> <s:property value="totalCustomerAmount"/> <br/>(<s:property
                        value="currencySymbol"/> <s:property value="totalFranchiseAmount"/>) <br/>[<s:property
                        value="currencySymbol"/> <s:property value="totalMargin"/>]
                </td>
                <td class="td_quoted"><s:if test="listQuote != null and baseCharge != null ">
                    <xms:localization text="Base charge "/> - <s:property value="currencySymbol"/>
                    <s:property value="baseCharge"/>
                    <br/>
                    <s:iterator value="listQuote">
                        <s:property value="quoteDescription"/> - <s:property value="currencySymbol"/>
                        <s:property value="amount"/>
                        <br/>
                    </s:iterator>
                    <xms:localization text="Total Charges:"/>
                    <s:property value="currencySymbol"/>
                    <s:property value="totalCharges"/>
                </s:if> <s:else>
                    <xms:localization text="TBA"/>
                </s:else></td>
            </tr>
        </s:iterator>
        <tr>
            <td colspan="8">
                <div class="text-right">
                    <xms:localization text="Total Airbills:"/>
                    <s:property value="totalSearchAirbill.totalAirbills"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <xms:localization text="Customer Total:"/>
                    <s:property value="currencySymbol"/>
                    <s:property value="totalSearchAirbill.totalCustomerAmount"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <xms:localization text="Franchise Cost:"/>
                    <s:property value="currencySymbol"/>
                    <s:property value="totalSearchAirbill.totalFranchiseAmount"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <xms:localization text="Margin:"/>
                    <s:property value="currencySymbol"/>
                    <s:property value="totalSearchAirbill.totalMargin"/>
                </div>
            </td>
        </tr>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="listAirbill.startRecord"/> <xms:localization
                    text="to"/> <s:property value="listAirbill.endRecord"/> <xms:localization text="of"/> <s:property
                    value="listAirbill.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="!listAirbill.hasPrev()">
                <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
            </s:if>
            <s:else>
                <a href="javascript:changePage(<s:property value="%{listAirbill.currentPage - 1}"/>)"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:else>
			<span> <s:iterator value="listAirbill.pageRange" status="count">
                <s:if test="%{listAirbill.pageRange[#count.index] == listAirbill.currentPage}">
                    <a class="paginate_button current"><s:property value="listAirbill.currentPage"/></a>
                </s:if>
                <s:else>
                    <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
			</span>
            <s:if test="!listAirbill.hasNext()">
                <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
            </s:if>
            <s:else>
                <a class="paginate_button next"
                   href="javascript:changePage(<s:property value="%{listAirbill.currentPage+1}"/>)"><xms:localization
                        text="Next"/></a>
            </s:else>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $(".td_quoted").hide();
        check_all_pages();
        showQuoted();
        var fieldList = ["", "customer_code", "", "", "", "", "totalCustomerCostWithTax", ""];
        $("#datatable1").tablesorter({
            sortFieldId: "search_airbill_order_field",
            sortTypeId: "search_airbill_order_type",
            fieldList: fieldList,
            callback: searchAirbill
        });
    });

    function changePage(page) {
        $("#hid_page").val(page);
        doPostDataNonError('search_airbill_by_filter.ix?reqType=json', 'form_search_airbill', '', 'result_search_airbill');
    }

    <s:if test="listAirbill==null || listAirbill.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>

    $('table#datatable1 tbody tr').click(function () {
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
    });
</script>
