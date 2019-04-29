<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="form-group">
    <label class="control-label" for="inputName"> <xms:localization text="Packing List"/>
    </label>
    <s:select list="selPackagingList" id="packingListSelect" name="packingListSelect" cssClass="form-control"
              listKey="key" listValue="value" onchange="onCommerialOrPackingListChange()"/>
</div>
<div id="ws-packing-list-div">
    <div class="form-group ">
        <div style="min-width: 10px !important; overflow: auto">
            <table class="table table-bordered mg0" id="packing_list_table">
                <tbody>
                <tr bgcolor="#F5F5F5">
                    <th class="ws-packing-list-div-extra"><xms:localization text="No of CTNS"/></th>
                    <th><xms:localization text="Goods Description"/></th>
                    <th><xms:localization text="HTS# / B#"/></th>
                    <th><xms:localization text="Country of Origin"/></th>
                    <th><xms:localization text="Qty"/><span style="color: red">*</span></th>
                    <th><xms:localization text="Unit Value"/><span style="color: red">*</span></th>
                    <th><xms:localization text="Total"/></th>
                    <th>&nbsp;</th>
                </tr>
                <s:if test="!shipmentRequestModel.shipmentProductDetails.isEmpty()">
                    <s:iterator value="shipmentRequestModel.shipmentProductDetails" status="productStatus">
                        <tr>
                            <td class="ws-packing-list-div-extra">
                                <s:textfield cssClass="form-control" cssStyle="min-width: 50px;"  type="number" min="0" onkeypress="return event.charCode >= 48"
                                             name="shipmentRequestModel.shipmentProductDetails[%{#productStatus.index}].noOfCarton"/>
                            </td>
                            <td>
                                <s:textarea cols="100" cssClass="form-control"
                                            name="shipmentRequestModel.shipmentProductDetails[%{#productStatus.index}].description"/>
                            </td>
                            <td>
                                <s:textfield id="hts_lookup" cssClass="form-control" type="number" min="0" onkeypress="return event.charCode >= 48"
                                             cssStyle="min-width: 65px;" name="shipmentRequestModel.shipmentProductDetails[%{#productStatus.index}].code"/>
                                <a type="submit"
                                   onclick="htsLookup($(this))"
                                   style="cursor: pointer;">
                                    <u>
                                        <b><xms:localization text="Lookup"/></b>
                                    </u>
                                </a>
                            </td>
                            <td><s:select list="countryList" value="%{countryOfOrigin}" listKey="countryId"
                                          cssStyle="height: 25px; min-width: 100px;" listValue="countryName"
                                          cssClass="form-control"
                                          name="shipmentRequestModel.shipmentProductDetails[%{#productStatus.index}].countryOfOrigin"/></td>
                            <td><s:textfield cssClass="form-control" name="shipmentRequestModel.shipmentProductDetails[%{#productStatus.index}].qty"
                                             type="number" min="0" cssStyle="min-width: 50px;" id="productDetailQty" onkeypress="return event.charCode >= 48" onchange="calculateProductAmount($(this))"/></td>
                            <td><s:textfield cssClass="form-control" id="productDetailAmount" type="number" min="0" cssStyle="min-width: 65px;"
                                             name="shipmentRequestModel.shipmentProductDetails[%{#productStatus.index}].amount"
                                             onkeypress="return event.charCode >= 46" onchange="calculateProductAmount($(this))"/></td>
                            <td><span id="span-amount" class="w9"></span></td>
                            <td>
                                <button class="btn s33 btn_remove_item" type="button" style="display: none"
                                        onclick="removePackingListRow($(this))">
                                    <xms:localization text="X"/>
                                </button>
                            </td>
                        </tr>
                    </s:iterator>
                </s:if>
                <s:else>
                    <tr>
                        <td class="ws-packing-list-div-extra">
                            <s:textfield cssClass="form-control" cssStyle="min-width: 50px;" type="number" min="0" onkeypress="return event.charCode >= 48"
                                         name="shipmentRequestModel.shipmentProductDetails[0].noOfCarton"/>
                        </td>
                        <td>
                            <s:textarea cols="100" cssClass="form-control"
                                        name="shipmentRequestModel.shipmentProductDetails[0].description"/>
                        </td>
                        <td>
                            <s:textfield id="hts_lookup" cssClass="form-control" cssStyle="min-width: 65px;"
                                         type="number" min="0" onkeypress="return event.charCode >= 48"  name="shipmentRequestModel.shipmentProductDetails[0].code"/>
                            <a type="submit"
                               onclick="htsLookup($(this))"
                               style="cursor: pointer;">
                                <u><b><xms:localization text="Lookup"/></b></u>
                            </a>
                        </td>
                        <td>
                            <s:select list="countryList" value="%{countryOfOrigin}" listKey="countryId"
                                      cssStyle="height: 25px; min-width: 100px;" listValue="countryName"
                                      cssClass="form-control"
                                      name="shipmentRequestModel.shipmentProductDetails[0].countryOfOrigin"/>
                        </td>
                        <td>
                            <s:textfield cssClass="form-control" cssStyle="min-width: 50px;" type="number" min="0"
                                         name="shipmentRequestModel.shipmentProductDetails[0].qty"
                                         id="productDetailQty" onkeypress="return event.charCode >= 48" onchange="calculateProductAmount($(this))"/>
                        </td>
                        <td>
                            <s:textfield cssClass="form-control" id="productDetailAmount" type="number" min="0"
                                         cssStyle="min-width: 65px;" onkeypress="return event.charCode >= 46"
                                         name="shipmentRequestModel.shipmentProductDetails[0].amount"
                                         onchange="calculateProductAmount($(this))"/>
                        </td>
                        <td>
                            <span id="span-amount" class="w9"></span>
                        </td>
                        <td>
                            <button class="btn s33 btn_remove_item"
                                    type="button" style="display: none"
                                    onclick="removePackingListRow($(this))">
                                <xms:localization text="X"/>
                            </button>
                        </td>
                    </tr>
                </s:else>
                <tr>
                    <td class="ws-packing-list-div-extra">&nbsp;</td>
                    <td colspan="5" class="text-right"><xms:localization text="Item Sub-Total"/></td>
                    <td><span id="item_sub_total" class="w9"></span></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="ws-packing-list-div-extra">&nbsp;</td>
                    <td colspan="7">
                        <button class="btn s33" type="button" onclick="javascript:addAnotherItem();">
                            <xms:localization text="Add Another Item"/>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div id="hts_good_lookup_load" title="<xms:localization text="HTS Code Lookup" />"></div>