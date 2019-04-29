<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="qsContact==null || qsContact.size()==0">
    0 <xms:localization text="result(s)"/>
</s:if>
<s:else>
    <s:property value="qsContact.totalRecords"/>
    <xms:localization text="result(s)"/>
</s:else>
<table class="s32 table table-striped table-bordered tablesorter" border="0" id="quick_search_contact_table">
    <thead>
    <tr>
        <th align="left"><xms:localization text="Company Name"/></th>
        <th align="left"><xms:localization text="Contact Name"/></th>
        <th align="left"><xms:localization text="Sales Stage"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="qsContact==null || qsContact.totalRecords==0">
        <td align="left" colspan="3"><xms:localization text="No data available..."/></td>
    </s:if>
    <s:else>
        <s:iterator value="qsContact.records">
            <tr>
                <td align="left" style="cursor: pointer" onclick=""><b><s:property value="companyName"/></b></td>
                <td align="left"><s:property value="contactName"/></td>
                <td align="left"><s:property value="saleStage"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<script type="text/javascript">
    $(document).ready(function () {
        $("#quick_search_contact_table").jstablesorter({
            sortList: [[0, 0]]
        });
    });


</script>