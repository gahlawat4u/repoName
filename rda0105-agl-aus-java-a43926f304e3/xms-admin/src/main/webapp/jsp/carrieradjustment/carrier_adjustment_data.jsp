<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:set id="tableId" value="tableId"/>
<table class="table mg0">
    <tbody>
    <tr>
        <th class="s42">
            <table class="s36">
                <tbody>
                <tr>
                    <td><xms:localization text="Show"/></td>
                    <td><s:select id="recordSize_%{tableId}" name="recordSize_%{tableId}" cssClass="form-control"
                                  cssStyle="height: 22px; padding-top: 1px;" list="listRecordSize"
                                  value="%{manageAdjustmentPageModel.recordSize}" onchange="changePageSize()"/></td>
                    <td><xms:localization text="Entries"/></td>
                </tr>
                </tbody>
            </table>
        </th>
    </tr>
    </tbody>
</table>
<table class="table table-hover table-striped table-bordered mg0"
       id="carrier_adjustments_table_<s:property value="tableId"/>">
    <thead>
    <tr>
        <s:if test="%{listCarrierAdjustment.records[0].status==1}">
            <th style="width: 23px !important;"><input type="checkbox" value="" id="checkAll" onclick="checkAll()"></th>
        </s:if>
        <s:elseif test="%{listCarrierAdjustment.records[0].status==2}">
            <th style="width: 23px !important;"><input type="checkbox" value="" id="checkAll" onclick="checkAll()"></th>
        </s:elseif>
        <th style="width: 110px;"><xms:localization text="Airbill Number"/></th>
        <th style="width: 70px;"><xms:localization text="Adj ID"/></th>
        <th style="width: 60px;"><xms:localization text="Carrier"/></th>
        <th style="width: 80px;"><xms:localization text="Ship Date"/></th>
        <th style="width: 80px;"><xms:localization text="Delivery Date"/></th>
        <th style="width: 100px;"><xms:localization text="Request Date"/></th>
        <th style="width: 100px;"><xms:localization text="EDI Import Date"/></th>
        <th style="width: 120px;"><xms:localization text="Request Type"/></th>
        <th style="width: 120px;"><xms:localization text="Request Amount"/></th>
        <th><xms:localization text="Request Notes"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="%{listCarrierAdjustment.records.isEmpty()}">
        <tr>
            <td colspan="11"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="listCarrierAdjustment.records" status="row">
            <tr>
                <s:if test="%{status==1}">
                    <td><s:checkbox cssClass="chk_airbill" name="chk_airbill[]" fieldValue="%{adjustmentId}"/></td>
                </s:if>
                <s:elseif test="%{status==2}">
                    <td><s:checkbox cssClass="chk_airbill" name="chk_airbill[]" fieldValue="%{adjustmentId}"/></td>
                </s:elseif>
                <td><s:property value="airbillNumber"/></td>
                <td><s:property value="adjustmentId"/></td>
                <td><s:property value="serviceName"/></td>
                <td><s:property value="shipmentDate"/></td>
                <td><s:property value="deliveryDate"/></td>
                <td><s:property value="createDate"/></td>
                <td><s:property value="importDate"/></td>
                <td><s:property value="adjustmentType"/></td>
                <td><s:property value="customerAmountFormatted"/></td>
                <td><s:property value="note"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="listCarrierAdjustment.startRecord"/>
                <xms:localization text="to"/> <s:property value="listCarrierAdjustment.endRecord"/> <xms:localization
                        text="of"/> <s:property value="listCarrierAdjustment.totalRecords"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="%{listCarrierAdjustment != null && listCarrierAdjustment.totalRecords>0}">
                <s:if test="listCarrierAdjustment.hasPrev()">
                    <a href="javascript:changePage(1);" class="paginate_button previous"><xms:localization
                            text="First"/></a>
                </s:if>
                <s:iterator value="listCarrierAdjustment.pageRange" status="count">
                    <s:if test="%{listCarrierAdjustment.pageRange[#count.index] == listCarrierAdjustment.currentPage}">
                        <span><a href="#" class="paginate_button current active"><s:property
                                value="listCarrierAdjustment.currentPage"/></a> <input type="hidden"
                                                                                       value='<s:property value="listCarrierAdjustment.currentPage" />'
                                                                                       id='current_page_<s:property value="tableId"/>'/> </span>
                    </s:if>
                    <s:else>
                        <span><a href='javascript:changePage(<s:property />);' class="paginate_button"><s:property/></a></span>
                    </s:else>
                </s:iterator>
                <s:if test="listCarrierAdjustment.hasNext()">
                    <a href="javascript:changePage(<s:property value='%{listCarrierAdjustment.totalPage}'/>);"
                       class="paginate_button next"><xms:localization text="Last"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    var fieldList = [<s:if test="%{listCarrierAdjustment.records[0].status==1 || listCarrierAdjustment.records[0].status==2}">"",
        </s:if>
        "airbillNumber", "adjustmentId", "serviceName", "shipmentDate", "deliveryDate", "createDate", "importDate", "adjustmentType", "customerAmount", "note"];
    $(document).ready(function () {
        div_show = $("#generalTab").find(".active").find("a").attr("href");
        div_show_id = div_show.replace("#", "");
        // Add sorting function to the result table.
        $("#carrier_adjustments_table_" + div_show_id).tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: fieldList,
            callback: changePageSize
        });
    });
    function checkAll() {
        if ($("#checkAll").is(":checked")) {
            $(".chk_airbill").prop("checked", true);
        } else {
            $(".chk_airbill").prop("checked", false);
        }
    }
</script>