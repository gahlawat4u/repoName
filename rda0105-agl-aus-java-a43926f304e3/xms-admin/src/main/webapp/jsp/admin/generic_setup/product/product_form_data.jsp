<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="product_form">
    <s:hidden name="product.productId"/>
    <s:hidden name="product.carrierId"/>
    <s:hidden name="product.localizationId"/>
    <div class="form-group">
        <p class="">
            <xms:localization text="Product Name:"/>
            <span class="s30">*</span>
        </p>
        <s:textfield name="product.productName" class="form-control alloptions"/>
        <span class="text-danger"><s:fielderror fieldName="product.productName"/></span>
    </div>
    <div class="form-group">
        <p class="">
            <xms:localization text="Description:"/>
        </p>
        <s:textfield name="product.description" class="form-control alloptions"/>
        <span class="text-danger"><s:fielderror fieldName="product.description"/></span>
    </div>
</form>
