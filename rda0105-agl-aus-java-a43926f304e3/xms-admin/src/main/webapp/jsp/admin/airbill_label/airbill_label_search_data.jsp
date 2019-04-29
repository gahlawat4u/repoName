<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-bordered mg0" id="airbill_label_table">
    <thead>
    <tr>
        <th><xms:localization text="Customer Id"/></th>
        <th><xms:localization text="Customer Name"/></th>
        <th><xms:localization text="Tracking#"/></th>
        <th><xms:localization text="Carrier"/></th>
        <th><xms:localization text="Voided"/></th>
        <th><xms:localization text="Date"/></th>
        <th><xms:localization text="Ship Date"/></th>
        <th class="text-right"><xms:localization text="Pieces"/></th>
        <th class="text-right"><xms:localization text="Weight"/></th>
        <th><xms:localization text="Scheduled"/></th>
        <th><xms:localization text="Collection Information"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="airbillLabels==null || airbillLabels.totalRecords==0">
        <tr>
            <td colspan="11"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="airbillLabels.records">
            <tr>
                <td><s:hidden id="airbill_label_shipment_id" name="shipmentId"/> <s:hidden
                        id="airbill_label_schedule_collection_id" name="scheduleCollectionId"/> <s:property
                        value="customerCode"/></td>
                <td><s:property value="customerName"/></td>
                <td><s:property value="airbillNumber"/></td>
                <td><s:property value="serviceName"/></td>
                <td><s:property value="voidedStatus"/></td>
                <td><s:property value="createDate"/></td>
                <td><s:property value="shipmentDate"/></td>
                <td class="text-right"><s:property value="noOfPieces"/></td>
                <td class="text-right"><s:if test="weight!=null">
                    <s:property value="weight"/>
                    <s:property value="weightUnit"/>
                </s:if></td>
                <td><s:property value="collectionTypeName"/></td>
                <td><s:textfield id="airbill_label_confirmation_no" cssClass="form-control" name="confirmationNo"/>
                    <button type="button" class="btn s37" onclick="updateAirbillLabelConfirmationNo($(this))">
                        <xms:localization text="Update"/>
                    </button>
                </td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="airbillLabels.startRecord"/> <xms:localization
                    text="to"/> <s:property value="airbillLabels.endRecord"/> <xms:localization text="of"/> <s:property
                    value="airbillLabels.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="airbillLabels!=null">
                <s:if test="airbillLabels.hasPrev()">
                    <a href="javascript:airbillLabelPageChange(<s:property value="%{airbillLabels.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="airbillLabels.pageRange" status="count">
                    <s:if test="%{airbillLabels.pageRange[#count.index] == airbillLabels.currentPage}">
                        <a class="paginate_button current"><s:property value="airbillLabels.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:airbillLabelPageChange(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="airbillLabels.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:airbillLabelPageChange(<s:property value="%{airbillLabels.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var fieldList = ["customer_code", "customer_name", "airbill_number", "service_name", "voided_status", "create_date", "shipment_date", "no_of_pieces", "weight", "collection_type_name", "confirmation_no"];
        $("#airbill_label_table").tablesorter({
            sortFieldId: "airbill_label_order_field",
            sortTypeId: "airbill_label_order_type",
            fieldList: fieldList,
            callback: airbillLabelSearch
        });
    });


</script>