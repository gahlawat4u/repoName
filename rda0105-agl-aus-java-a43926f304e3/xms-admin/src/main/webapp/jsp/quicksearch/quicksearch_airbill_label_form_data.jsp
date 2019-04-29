<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="qsShipmentByAirbill==null || qsShipmentByAirbill.size()==0">
    0 <xms:localization text="result(s)"/>
</s:if>
<s:else>
    <s:property value="qsShipmentByAirbill.totalRecords"/>
    <xms:localization text="result(s)"/>
</s:else>
<table class="s32 table table-striped table-bordered tablesorter" border="0" id="quick_search_airbill_label_table">
    <thead>
    <tr>
        <th align="left"><xms:localization text="Customers ID"/></th>
        <th align="left"><xms:localization text="Customers Name"/></th>
        <th align="left"><xms:localization text="Tracking #"/></th>
        <th align="left"><xms:localization text="Carrier"/></th>
        <th align="left"><xms:localization text="Voided"/></th>
        <th align="left"><xms:localization text="Date"/></th>
        <th align="left"><xms:localization text="Ship Date"/></th>
        <th align="left"><xms:localization text="Pieces"/></th>
        <th align="left"><xms:localization text="Weight"/></th>
        <th align="left"><xms:localization text="Scheduled"/></th>
        <th align="left"><xms:localization text="Collection Information"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="qsShipmentByAirbill==null || qsShipmentByAirbill.totalRecords==0">
        <td align="left" colspan="11"><xms:localization text="No data available..."/></td>
    </s:if>
    <s:else>
        <s:iterator value="qsShipmentByAirbill.records">
            <tr>
                <td align="left" style="cursor: pointer" onclick="get_data('<s:property value="customerCode"/>',4)">
                    <b><s:property value="customerCode"/></b></td>
                <td align="left"><s:property value="customerName"/></td>
                <td align="left"><s:property value="airbillNumber"/></td>
                <td align="left"><s:property value="serviceName"/></td>
                <td align="left"><s:property value="voidStatus"/></td>
                <td align="left"><s:property value="createDate"/></td>
                <td align="left"><s:property value="shipmentDate"/></td>
                <td align="left"><s:property value="noOfPieces"/></td>
                <td align="left"><s:property value="weightStr"/></td>
                <td align="left"><s:property value="collectionTypeName"/></td>
                <td align="left"><s:property value="confirmationNo"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $("#quick_search_airbill_label_table").jstablesorter({
            sortList: [[0, 0]]
        });
    });


</script>