<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="qsShipmentBilling==null || qsShipmentBilling.size()==0">
    0 <xms:localization text="result(s)"/>
</s:if>
<s:else>
    <s:property value="qsShipmentBilling.totalRecords"/>
    <xms:localization text="result(s)"/>
</s:else>
<table class="s32 table table-striped table-bordered tablesorter" border="0" id="quick_search_airbill_table">
    <thead>
    <tr>
        <th align="left"><xms:localization text="Airbill No"/></th>
        <th align="left"><xms:localization text="Import Date"/></th>
        <th align="left"><xms:localization text="Invoice Number"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="qsShipmentBilling==null || qsShipmentBilling.totalRecords==0">
        <td align="left" colspan="3"><xms:localization text="No data available..."/></td>
    </s:if>
    <s:else>
        <s:iterator value="qsShipmentBilling.records">
            <tr>
                <td align="left" style="cursor: pointer"
                    onclick="get_data('',2,'<s:property value="invoiceDate"/>','<s:property value="invoiceCode"/>','
                        <s:property value="invoiceId"/>')"><b><s:property value="airbillNumber"/></b></td>
                <td align="left"><s:property value="importDateTime"/></td>
                <td align="left"><s:property value="invoiceCode"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $("#quick_search_airbill_table").jstablesorter({
            sortList: [[0, 0]]
        });
    });


</script>