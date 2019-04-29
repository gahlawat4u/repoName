<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:if test="type==1">
    <tr>
        <td><s:textfield name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].noOfCarton"
                         cssClass="form-control alloptions"/></td>
        <td><s:textfield name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].description"
                         cssClass="form-control alloptions"/></td>
        <td><s:textfield id="hts_lookup" cssClass="form-control"
                         name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].code"/> <a type="submit"
                                                                                                       onclick="htsLookup($(this))"
                                                                                                       style="cursor: pointer;"><u><b><xms:localization
                text="Lookup"/></b></u></a></td>
        <td><s:select list="countryList" value="%{countryOfOrigin}" listKey="countryId"
                      cssStyle="height: 25px; min-width: 100px;" listValue="countryName" cssClass="form-control"
                      name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].countryOfOrigin"/></td>
        <td><s:textfield cssClass="form-control" name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].qty"
                         id="productDetailQty" onchange="calculateProductAmount($(this))"/></td>
        <td><s:textfield cssClass="form-control" id="productDetailPrice"
                         onchange="calculateProductAmount($(this))"/></td>
        <td><span id="span-amount" class="w9"></span> <s:hidden
                name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].amount" id="productDetailAmount"/>
        </td>
        <td>
            <button class='btn s33 btn_remove_item' type='button' onclick="removePackingListRow($(this))">X</button>
        </td>
    </tr>
</s:if>
<s:else>
    <tr>
        <td><s:textfield name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].description"
                         cssClass="form-control alloptions"/></td>
        <td><s:textfield id="hts_lookup" cssClass="form-control"
                         name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].code"/> <a type="submit"
                                                                                                       onclick="htsLookup($(this))"
                                                                                                       style="cursor: pointer;"><u><b><xms:localization
                text="Lookup"/></b></u></a></td>
        <td><s:select list="countryList" value="%{countryOfOrigin}" listKey="countryId"
                      cssStyle="height: 25px; min-width: 100px;" listValue="countryName" cssClass="form-control"
                      name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].countryOfOrigin"/></td>
        <td><s:textfield cssClass="form-control" name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].qty"
                         id="productDetailQty" onchange="calculateProductAmount($(this))"/></td>
        <td><s:textfield cssClass="form-control" id="productDetailPrice"
                         onchange="calculateProductAmount($(this))"></s:textfield></td>
        <td><span id="span-amount" class="w9"></span> <s:hidden
                name="shipmentRequestModel.shipmentProductDetails[%{indexPackage}].amount" id="productDetailAmount"/>
        </td>
        <td>
            <button class='btn s33 btn_remove_item' type='button' onclick="removePackingListRow($(this))">X</button>
        </td>
    </tr>
</s:else>