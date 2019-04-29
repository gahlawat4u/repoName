<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/jquery.tablesorter.min.js"></script>
<link rel="stylesheet" type="text/css"
      href="<%=WebUtils.getContextPathURL(request)%>styles/xms/styles/customer/css_tablesorter_plugin.css"/>
<div class="row">
    <div class="form-group">
        <table class="s36 d1">
            <tr>
                <td><xms:localization text="Search"/></td>
                <td><s:textfield cssClass="form-control ss36" name="searchValue" id="txtQuickSearchForm"/></td>
                <td><s:select list="searchTypeList" listValue="typeName" id="searchTypeForm" name="searchType"
                              listKey="id" cssClass="form-control"/></td>
                <td>
                    <button class="btn s37" type="button" onclick="doQuickSearchForm()">
                        <xms:localization text="Search"/>
                    </button>
                </td>
            </tr>
        </table>
        <br/> <br/>

        <div id="quickSearchResult">
            <s:if test="qsInvoice==null || qsInvoice.size()==0">
                0 <xms:localization text="result(s)"/>
            </s:if>
            <s:else>
                <s:property value="qsInvoice.totalRecords"/>
                <xms:localization text="result(s)"/>
            </s:else>
            <table class="s32 table table-striped table-bordered tablesorter" border="0"
                   id="quick_search_invoice_table">
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
                                onclick="get_data('',3,'<s:property value="invoiceDate"/>','<s:property
                                        value="invoiceCode"/>','<s:property value="invoiceId"/>')"><b><s:property
                                    value="invoiceCode"/></b></td>
                            <td align="left"><s:property value="margin"/></td>
                            <td align="left"><s:property value="total"/></td>
                            <td align="left"><s:property value="airbillCount"/></td>
                        </tr>
                    </s:iterator>
                </s:else>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#txtQuickSearchForm').val($('#txtQuickSearch').val());
        $('#searchTypeForm option[value=3]').attr('selected', 'selected');
        $("#quick_search_invoice_table").jstablesorter({
            sortList: [[0, 0]]
        });
    });


</script>