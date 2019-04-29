<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0 table-hover table-pointer" id="product_list_table">
    <thead>
    <tr>
        <th><xms:localization text="No"/></th>
        <th><xms:localization text="Product Name"/></th>
        <th><xms:localization text="Description"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="productList==null || productList.totalRecords==0">
        <tr>
            <td colspan="3"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="productList.records">
            <tr data-product-id="<s:property value="productId" />" ondblclick='load(<s:property value="productId"/>)'>
                <td><s:property value="productId"/></td>
                <td><s:property value="productName"/></td>
                <td><s:property value="description"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate">
    <s:if test="!productList.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{productList.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="productList.pageRange" status="count">
        <s:if test="%{productList.pageRange[#count.index] == productList.currentPage}">
            <a class="paginate_button current"><s:property value="productList.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!productList.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{productList.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        productId = 0;
        $('table#product_list_table tbody tr').click(function () {
            productId = $(this).attr('data-product-id');
            if (typeof (productId) != "undefined" && productId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                $('#btnRemove').attr('disabled', false);
            }
        });
        $("#product_list_table").tablesorter({
            sortFieldId: "orderField",
            sortTypeId: "orderType",
            fieldList: ["product_id", "product_name", "description"],
            callback: search
        });
    });


</script>