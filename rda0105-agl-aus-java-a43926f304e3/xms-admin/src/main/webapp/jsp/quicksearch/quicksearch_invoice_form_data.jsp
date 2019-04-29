<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="qsInvoice==null || qsInvoice.size()==0">
    0 <xms:localization text="result(s)"/>
</s:if>
<s:else>
    <s:property value="qsInvoice.totalRecords"/>
    <xms:localization text="result(s)"/>
</s:else>
<table class="s32 table table-striped table-bordered tablesorter" border="0" id="quick_search_invoice_table">
    <thead>
    <tr>
        <th align="left"><xms:localization text="Invoice Number"/></th>
        <th align="left"><xms:localization text="Margin"/></th>
        <th align="left"><xms:localization text="Total"/> (<xms:localization text="Customer"/>)</th>
        <th align="left"><xms:localization text="Total Airbills"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="qsInvoice==null || qsInvoice.totalRecords==0">
        <td align="left" colspan="4"><xms:localization text="No data available..."/></td>
    </s:if>
    <s:else>
        <s:iterator value="qsInvoice.records">
            <tr>
                <td align="left" style="cursor: pointer"
                    onclick="get_data('',3,'<s:property value="invoiceDate"/>','<s:property value="invoiceCode"/>','
                        <s:property value="invoiceId"/>')"><b><s:property value="invoiceCode"/></b></td>
                <td align="left"><s:property value="margin"/></td>
                <td align="left"><s:property value="total"/></td>
                <td align="left"><s:property value="airbillCount"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $("#quick_search_invoice_table").jstablesorter({
            sortList: [[0, 0]]
        });
    });


</script>