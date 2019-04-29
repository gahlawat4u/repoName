<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <table class="table table-bordered mg0" id="webship_label_report_table">
        <thead>
        <tr>
            <th><xms:localization text="Invoice #"/></th>
            <th><xms:localization text="Customer #"/></th>
            <th><xms:localization text="Connote #"/></th>
            <th><xms:localization text="Carrier"/></th>
            <th><xms:localization text="Service"/></th>
            <th class="text-right"><xms:localization text="Warranty Revenue"/></th>
            <th data-group="create-date"><xms:localization text="Create Date"/></th>
            <th data-group="ship-date"><xms:localization text="Ship Date"/></th>
            <th data-group="pickup-date"><xms:localization text="Pickup Date"/></th>
            <th data-group="pieces"><xms:localization text="Pieces"/></th>
            <th class="text-right" data-group="dead-weight"><xms:localization text="Dead Weight"/></th>
            <th class="text-right" data-group="dimension"><xms:localization text="Dimension"/></th>
            <th class="text-right" data-group="weight"><xms:localization text="Weight"/></th>
            <th class="text-right" data-group="quoted"><xms:localization text="Quoted"/></th>
            <th data-group="sender"><xms:localization text="Sender Address"/></th>
            <th data-group="receiver"><xms:localization text="Receiver Address"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="report==null || report.totalRecords==0">
            <tr>
                <td colspan="18"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="report.records">
                <tr>
                    <td><s:property value="invoiceCode"/></td>
                    <td><s:property value="customerCode"/></td>
                    <td><s:property value="airbillNumber"/></td>
                    <td><s:property value="carrier"/></td>
                    <td><s:property value="service"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="customerInsuredAmount"/></td>
                    <td data-group="create-date"><s:property value="createDate"/></td>
                    <td data-group="ship-date"><s:property value="shipDate"/></td>
                    <td data-group="pickup-date"><s:property value="pickupDate"/></td>
                    <td data-group="pieces"><s:property value="noOfPieces"/></td>
                    <td class="text-right" data-group="dead-weight"><s:property value="deadWeight"/> <s:property
                            value="weightUnit"/></td>
                    <td class="text-right" data-group="dimension"><s:property value="dimension"/> <s:property
                            value="dimensionUnit"/></td>
                    <td class="text-right" data-group="weight"><s:property value="weight"/> <s:property
                            value="weightUnit"/></td>
                    <td class="text-right" data-group="quoted"><s:property value="currencySymbol"/> <s:property
                            value="quoted"/></td>
                    <td data-group="sender"><s:property value="senderCompanyName"/><br/> <s:property
                            value="senderContactName"/><br/> <s:property value="senderAddress1"/><br/> <s:if
                            test="senderAddress2!=null && senderAddress2!=''">
                        <s:property value="senderAddress2"/>
                        <br/>
                    </s:if> <s:if test="senderCity!=null && senderCity!=''">
                        <s:property value="senderCity"/>
                    </s:if> <s:if test="senderPostalCode!=null && senderPostalCode!=''">
                        <s:property value="senderPostalCode"/>
                    </s:if> <s:if test="senderState!=null && senderState!=''">
                        <s:property value="senderState"/>
                    </s:if> <br/> <s:property value="senderCountryName"/></td>
                    <td data-group="receiver"><s:property value="receiverCompanyName"/><br/> <s:property
                            value="receiverContactName"/><br/> <s:property value="receiverAddress1"/><br/> <s:if
                            test="receiverAddress2!=null && receiverAddress2!=''">
                        <s:property value="receiverAddress2"/>
                        <br/>
                    </s:if> <s:if test="receiverCity!=null && receiverCity!=''">
                        <s:property value="receiverCity"/>
                    </s:if> <s:if test="receiverPostalCode!=null && receiverPostalCode!=''">
                        <s:property value="receiverPostalCode"/>
                    </s:if> <s:if test="receiverState!=null && receiverState!=''">
                        <s:property value="receiverState"/>
                    </s:if> <br/> <s:property value="receiverCountryName"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="report.startRecord"/> <xms:localization text="to"/>
                <s:property value="report.endRecord"/> <xms:localization text="of"/> <s:property
                        value="report.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="report!=null">
                <s:if test="report.hasPrev()">
                    <a href="javascript:changePage(<s:property value="%{report.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="report.pageRange" status="count">
                    <s:if test="%{report.pageRange[#count.index] == report.currentPage}">
                        <a class="paginate_button current"><s:property value="report.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="report.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changePage(<s:property value="%{report.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>

<script type="text/javascript">
    var fieldList = ["invoice_code", "customer_code", "airbill_number", "carrier", "service", "voided", "customer_insured_amount", "create_date", "shipment_date", "pickup_date", "no_of_pieces", "dead_weight", "dimension", "weight", "quoted", "sender_address1", "receiver_address1"];
    $(document).ready(function () {
        // Add sorting function to the result table.
        $("#webship_label_report_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: doSearch
        });
        showHideColumns();
    });
    <s:if test="report==null || report.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>