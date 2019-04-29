<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <table class="table table-bordered mg0" id="invoiced_airbill_report_table">
        <thead>
        <tr>
            <th><xms:localization text="Invoice #"/></th>
            <th><xms:localization text="Customer #"/></th>
            <th><xms:localization text="Airbill #"/></th>
            <th><xms:localization text="Carrier"/></th>
            <th><xms:localization text="Service Type"/></th>
            <th class="text-right"><xms:localization text="Warranty Revenue (Exc. GST)"/></th>
            <th class="text-right"><xms:localization text="GST Revenue on Warranty"/></th>
            <th class="text-right"><xms:localization text="Total Warranty Revenue (inc GST)"/></th>
            <th><xms:localization text="Ship Date"/></th>
            <th><xms:localization text="Import Date"/></th>
            <th><xms:localization text="Invoice Date"/></th>
            <th class="text-right" data-group="pieces"><xms:localization text="Pieces"/></th>
            <th class="text-right" data-group="dead-weight"><xms:localization text="Dead Weight"/></th>
            <th class="text-right" data-group="dimension"><xms:localization text="Dimension"/></th>
            <th class="text-right" data-group="quoted"><xms:localization text="Quoted"/></th>
            <th><xms:localization text="Charges"/></th>
            <th class="text-right"><xms:localization text="Total"/></th>
            <th data-group="sender"><xms:localization text="Sender Address"/></th>
            <th data-group="receiver"><xms:localization text="Receiver Address"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="report==null || report.totalRecords==0">
            <tr>
                <td colspan="19"><xms:localization text="No data available..."/></td>
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
                    <td class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="insuredAmount"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="insuredGst"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="insuredTotal"/></td>
                    <td><s:property value="shipDate"/></td>
                    <td><s:property value="importDate"/></td>
                    <td><s:property value="invoiceDate"/></td>
                    <td class="text-right" data-group="pieces"><s:property value="noOfPieces"/></td>
                    <td class="text-right" data-group="dead-weight"><s:property value="deadWeight"/>
                        <s:property value="weightUnit"/></td>
                    <td class="text-right" data-group="dimension"><s:property value="dimension"/>
                        <s:property value="dimensionUnit"/></td>
                    <td class="text-right" data-group="quoted"><s:property value="currencySymbol"/>
                        <s:property value="quoted"/></td>
                    <td><s:property value="charges" escape="false"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/>
                        <s:property value="Total"/></td>
                    <td data-group="sender"><s:property value="senderAddress1"/></td>
                    <td data-group="receiver"><s:property value="receiverAddress1"/></td>
                </tr>
            </s:iterator>
            <tr>
                <th colspan="19"><xms:localization text="Showing"/> <s:property value="report.startRecord"/>
                    <xms:localization text="to"/> <s:property value="report.endRecord"/> <xms:localization text="of"/>
                    <s:property value="report.totalRecords"/></th>
            </tr>
        </s:else>
        </tbody>
    </table>
</div>
<div style="float: right; clear: both; margin-top: 5px;">
    <s:if test="summary!=null">
        <table class="table table-bordered mg0" id="summary_invoiced_airbill_report_table">
            <thead>
            <tr>
                <th class="text-right"><xms:localization text="International Shipment Count"/></th>
                <th class="text-right"><xms:localization text="Domestic Shipment Count"/></th>
                <th class="text-right"><xms:localization text="International Total Revenue"/></th>
                <th class="text-right"><xms:localization text="Domestic Total Revenue"/></th>
                <th class="text-right"><xms:localization text="GST on Domestic"/></th>
                <th class="text-right"><xms:localization text="Total Revenue inc GST"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="text-right"><s:property value="summary.intlShipmentCount"/></td>
                <td class="text-right"><s:property value="summary.domShipmentCount"/></td>
                <td class="text-right"><s:property value="currencySymbol"/>
                    <s:property value="summary.intlTotalRevenue"/></td>
                <td class="text-right"><s:property value="currencySymbol"/>
                    <s:property value="summary.domTotalRevenue"/></td>
                <td class="text-right"><s:property value="currencySymbol"/>
                    <s:property value="summary.domGst"/></td>
                <td class="text-right"><s:property value="currencySymbol"/>
                    <s:property value="summary.totalRevenueIncGst"/></td>
            </tr>
            </tbody>
        </table>
    </s:if>
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
    var fieldList = ["invoice_code", "customer_code", "airbill_number", "carrier", "service", "insured_amount", "insured_gst", "dom_warranty_gst", "ship_date", "import_date", "invoice_date", "no_of_pieces", "dead_weight", "dimension", "quoted", "", "total", "sender_address1", "receiver_address1"];
    $(document).ready(function () {
        // Update new rpt txn id.
        $("#rpt_txn_id").val("<s:property value="rptTxnId" />");
        // Add sorting function to the result table.
        $("#invoiced_airbill_report_table").tablesorter({
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