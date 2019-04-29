<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="qsCustomer==null || qsCustomer.size()==0">
    0 <xms:localization text="result(s)"/>
</s:if>
<s:else>
    <s:property value="qsCustomer.totalRecords"/>
    <xms:localization text="result(s)"/>
</s:else>
<table class="s32 table table-striped table-bordered tablesorter" border="0" id="quick_search_customer_table">
    <thead>
    <tr>
        <th align="left"><xms:localization text="Customers ID"/></th>
        <th align="left"><xms:localization text="Customers Name"/></th>
        <th align="left"><xms:localization text="DHL #"/></th>
        <th align="left"><xms:localization text="DHL Inbound #"/></th>
        <th align="left"><xms:localization text="Invoice to Customer"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="qsCustomer==null || qsCustomer.totalRecords==0">
        <td align="left" colspan="5"><xms:localization text="No data available..."/></td>
    </s:if>
    <s:else>
        <s:iterator value="qsCustomer.records">
            <tr>
                <td align="left" style="cursor: pointer" onclick="get_data('<s:property value="customerCode"/>',0)">
                    <b><s:property value="customerCode"/></b></td>
                <td align="left"><s:property value="customerName"/></td>
                <td align="left"><s:property value="dhlInternationalAccount"/></td>
                <td align="left"><s:property value="dhlInboundAccount"/></td>
                <td align="left"><s:property value="invoiceToCustomerName"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $("#quick_search_customer_table").jstablesorter({
            sortList: [[0, 0]]
        });
    });


</script>