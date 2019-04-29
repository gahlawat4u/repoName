<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<tr>
    <td><s:textfield name="shipmentRequestModel.quote.totalCustomValue"
                     cssClass="form-control alloptions"></s:textfield></td>
    <td><s:textfield name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].description"
                     cssClass="form-control alloptions"></s:textfield></td>
    <td><s:textfield id="hts_lookup" cssClass="form-control"
                     name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].code"></s:textfield>
        <a type="submit" onclick="htsLookup($(this))" style="cursor: pointer;"><u><b><xms:localization
                text="Lookup"/></b></u></a>
    </td>
    <td><s:select list="countryList" listKey="countryId" cssStyle="height: 25px; min-width: 100px;"
                  listValue="countryName" cssClass="form-control"
                  name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].countryOfOrigin"/></td>
    <td><s:textfield cssClass="form-control" name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].qty"
                     id="productDetailQty" onchange="caculateProductAmount()"></s:textfield></td>
    <td><s:textfield cssClass="form-control" id="productDetailPrice"
                     onchange="caculateProductAmount()"></s:textfield></td>
    <td><span id="span-amount" class="w9"></span> <s:hidden
            name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].amount"
            id="productDetailAmount"></s:hidden>
        <button class="btn s33 btn_remove_item" type="button">
            <xms:localization text="X"/>
        </button>
    </td>
</tr>
<script type="text/javascript">
    $(".btn_remove_item").click(function () {
        $(this).parent().parent().remove();
    });
</script>