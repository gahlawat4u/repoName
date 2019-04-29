<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0 table-hover table-pointer" id="product_carrier_table">
    <thead>
    <tr>
        <th><xms:localization text="No"/></th>
        <th><xms:localization text="Product Carrier Name"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="productCarrierList==null || productCarrierList.totalRecords==0">
        <tr>
            <td colspan="2"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="productCarrierList.records">
            <tr data-product-carrier-id='<s:property value="productCarrierId" />'
                ondblclick='load("<s:property value="productCarrierId"/>")'>
                <td><s:property value="productCarrierId"/></td>
                <td><s:property value="productCarrierName"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate">
    <s:if test="!productCarrierList.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{productCarrierList.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="productCarrierList.pageRange" status="count">
        <s:if test="%{productCarrierList.pageRange[#count.index] == productCarrierList.currentPage}">
            <a class="paginate_button current"><s:property value="productCarrierList.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!productCarrierList.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{productCarrierList.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        productCarrierId = 0;
        $('table#product_carrier_table tbody tr').click(function () {
            productCarrierId = $(this).attr('data-product-carrier-id');
            if (typeof (productCarrierId) != "undefined" && productCarrierId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                $('#btnView').attr('disabled', false);
                $('#btnRemove').attr('disabled', false);
            }
        });
        $("#product_carrier_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: ["product_carrierid", "product_carriername"],
            callback: search
        });
    });


</script>