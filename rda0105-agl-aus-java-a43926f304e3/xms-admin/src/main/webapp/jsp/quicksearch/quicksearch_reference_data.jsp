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
        <br/>

        <div id="quickSearchResult">
            <s:if test="qsShipmentByReference==null || qsShipmentByReference.size()==0">
                0 <xms:localization text="result(s)"/>
            </s:if>
            <s:else>
                <s:property value="qsShipmentByReference.totalRecords"/>
                <xms:localization text="result(s)"/>
            </s:else>
            <table class="s32 table table-striped table-bordered tablesorter" border="0"
                   id="quick_search_reference_table">
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
                    <th align="left"><xms:localization text="Reference #"/></th>
                </tr>
                </thead>
                <tbody>
                <s:if test="qsShipmentByReference==null || qsShipmentByReference.totalRecords==0">
                    <td align="left" colspan="12"><xms:localization text="No data available..."/></td>
                </s:if>
                <s:else>
                    <s:iterator value="qsShipmentByReference.records">
                        <tr>
                            <td align="left" style="cursor: pointer"
                                onclick="get_data('<s:property value="customerCode"/>',6)"><b><s:property
                                    value="customerCode"/></b></td>
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
                            <td align="left"><s:property value="reference"/></td>
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
        $('#searchTypeForm option[value=6]').attr('selected', 'selected');
        $("#quick_search_reference_table").jstablesorter({
            sortList: [[0, 0]]
        });
    });


</script>