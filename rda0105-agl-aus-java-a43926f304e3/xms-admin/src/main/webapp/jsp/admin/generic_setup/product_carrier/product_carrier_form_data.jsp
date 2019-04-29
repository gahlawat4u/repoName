<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="product_carrier_form">
    <div class="form-group">
        <s:hidden name="productCarrier.productCarrierId"/>
        <table class="table" style="font-size: 11px;">
            <tbody>
            <tr>
                <td class="td1"><xms:localization text="Product Carrier Name"/>:<span class="s30">*</span></td>
                <td class="td2 s51" colspan="2"><s:textfield name="productCarrier.productCarrierName"
                                                             class="form-control"/> <span
                        class="text-danger"><s:fielderror fieldName="productCarrier.productCarrierName"/></span></td>
            </tr>
            </tbody>
        </table>
    </div>
</form>