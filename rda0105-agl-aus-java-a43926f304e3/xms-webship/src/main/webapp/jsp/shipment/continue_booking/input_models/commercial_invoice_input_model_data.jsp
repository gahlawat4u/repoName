<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<s:if test="commercialInvoiceSelect==3">
    <div class="form-group ">
        <label class="control-label" for="inputName"> <xms:localization text="Receiver Tax Id/GST#"/>
        </label>
        <s:textfield name="shipmentRequestModel.shipmentInfo.receiverTaxId" cssClass="form-control"/>
    </div>
    <div class="form-group ">
        <label class="control-label" for="inputName"> <xms:localization text="Trading Terms"/>
        </label>
        <s:select id="term-of-trade" list="selTermOfTradeList" listKey="key" listValue="value" value="dutiesTax"
                  cssClass="form-control" name="shipmentRequestModel.shipmentInfo.termOfTrade"/>
    </div>
    <div class="form-group ">
        <label class="control-label" for="inputName"> <xms:localization text="Reason for Export"/>
        </label>
        <s:textfield name="shipmentRequestModel.shipmentInfo.reasonForExport" cssClass="form-control"/>
    </div>
    <div id="packing-list-input">
        <div class="form-group ">
            <div style="min-width: 10px !important; overflow: auto">
                <table class="table  table-bordered mg0" id="packing_list_table">
                    <tbody>
                    <tr bgcolor="#F5F5F5">
                        <th><xms:localization text="Goods Description"/></th>
                        <th><xms:localization text="HTS# / B#"/></th>
                        <th><xms:localization text="Country of Origin"/></th>
                        <th><xms:localization text="Qty"/><span style="color: red">*</span></th>
                        <th><xms:localization text="Unit Value"/><span style="color: red">*</span></th>
                        <th><xms:localization text="Total"/></th>
                        <th>&nbsp;</th>
                    </tr>
                    <tr>
                        <td><s:textfield name="shipmentRequestModel.shipmentProductDetails[0].description"
                                         cssClass="form-control alloptions"/></td>
                        <td><s:textfield id="hts_lookup" cssClass="form-control"
                                         name="shipmentRequestModel.shipmentProductDetails[0].code"/> <a type="submit"
                                                                                                         onclick="htsLookup($(this))"
                                                                                                         style="cursor: pointer;"><u><b><xms:localization
                                text="Lookup"/></b></u></a></td>
                        <td><s:select list="countryList" value="%{countryOfOrigin}" listKey="countryId"
                                      cssStyle="height: 25px; min-width: 100px;" listValue="countryName"
                                      cssClass="form-control"
                                      name="shipmentRequestModel.shipmentProductDetails[0].countryOfOrigin"/></td>
                        <td><s:textfield cssClass="form-control"
                                         name="shipmentRequestModel.shipmentProductDetails[0].qty" id="productDetailQty"
                                         onchange="calculateProductAmount($(this))"/></td>
                        <td><s:textfield cssClass="form-control" id="productDetailPrice"
                                         onchange="calculateProductAmount($(this))"></s:textfield></td>
                        <td><span id="span-amount" class="w9"></span> <s:hidden
                                name="shipmentRequestModel.shipmentProductDetails[0].amount" id="productDetailAmount"/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="5" class="text-right"><xms:localization text="Item Sub-Total"/></td>
                        <td><span id="item_sub_total" class="w9"></span></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="7">
                            <button class="btn s33" type="button" onclick="javascript:addAnotherItem(2);">
                                <xms:localization text="Add Another Item"/>
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</s:if>
<s:elseif test="packingListSelect==2">
    <div class="form-group ">
        <div style="min-width: 10px !important; overflow: auto">
            <table class="table  table-bordered mg0" id="packing_list_table">
                <tbody>
                <tr bgcolor="#F5F5F5">
                    <th class="txtNoOfCtns"><xms:localization text="No of CTNS*"/></th>
                    <th><xms:localization text="Goods Description"/></th>
                    <th><xms:localization text="HTS# / B#"/></th>
                    <th><xms:localization text="Country of Origin"/></th>
                    <th><xms:localization text="Qty*"/></th>
                    <th><xms:localization text="Unit Value*"/></th>
                    <th><xms:localization text="Total"/></th>
                    <th>&nbsp;</th>
                </tr>
                <tr>
                    <td><s:textfield name="shipmentRequestModel.shipmentProductDetails[0].noOfCarton"
                                     cssClass="form-control alloptions"/></td>
                    <td><s:textfield name="shipmentRequestModel.shipmentProductDetails[0].description"
                                     cssClass="form-control alloptions"/></td>
                    <td><s:textfield id="hts_lookup" cssClass="form-control"
                                     name="shipmentRequestModel.shipmentProductDetails[0].code"/> <a type="submit"
                                                                                                     onclick="htsLookup($(this))"
                                                                                                     style="cursor: pointer;"><u><b><xms:localization
                            text="Lookup"/></b></u></a></td>
                    <td><s:select list="countryList" value="%{countryOfOrigin}" listKey="countryId"
                                  cssStyle="height: 25px; min-width: 100px;" listValue="countryName"
                                  cssClass="form-control"
                                  name="shipmentRequestModel.shipmentProductDetails[0].countryOfOrigin"/></td>
                    <td><s:textfield cssClass="form-control" name="shipmentRequestModel.shipmentProductDetails[0].qty"
                                     id="productDetailQty" onchange="calculateProductAmount($(this))"/></td>
                    <td><s:textfield cssClass="form-control" id="productDetailPrice"
                                     onchange="calculateProductAmount($(this))"/></td>
                    <td><span id="span-amount" class="w9"></span> <s:hidden
                            name="shipmentRequestModel.shipmentProductDetails[0].amount" id="productDetailAmount"/></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="6" class="text-right"><xms:localization text="Item Sub-Total"/></td>
                    <td><span id="item_sub_total" class="w9"></span></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="8">
                        <button class="btn s33" type="button" onclick="javascript:addAnotherItem(1);">
                            <xms:localization text="Add Another Item"/>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</s:elseif>
<s:else>
    <div id="packing-list-input"></div>
</s:else>
<div id="hts_good_lookup_load" title="<xms:localization text="HTS Code Lookup" />"></div>
<script type="text/javascript">
    function calculateProductAmount(obj) {
        var row = $(obj).parent().parent();
        var qty = $(row).find("#productDetailQty").val();
        var price = $(row).find("#productDetailPrice").val();
        if (!isNumeric(qty)) {
            qty = 0;
        }
        if (!isNumeric(price)) {
            price = 0;
        }
        var amount = qty * price;
        $(row).find("#span-amount").html(amount);
        calculateSubTotal();
    }
    function calculateSubTotal() {
        // Get item sub total.
        var subTotal = 0;
        $("table#packing_list_table span[id='span-amount']").each(function () {
            console.log($(this).html());
            subTotal += parseFloat($(this).html());
        });
        $("#item_sub_total").html(subTotal);
    }
    function isNumeric(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }
    function htsLookup(obj) {
        var htsGoodId = $(obj).prev().val();
        var index = $(obj).parent().parent().index();
        var data = {
            'index': index,
            'htsGoodModel.htsGoodId': htsGoodId
        };
        loadDetailDialog("hts_good_lookup.ix?reqType=json", data, "HTS Code Lookup", "Close", "hts_good_lookup_load");
    }
    var indexPackage = 0;
    function addAnotherItem(type) {
        indexPackage = indexPackage + 1;
        var action = "commercial_invoice_add_new_row.ix?reqType=json";
        var data = {
            "indexPackage": indexPackage,
            "type": type
        };
        $.post(action, data, function (res) {
            if (res.errorCode == "SUCCESS") {
                var lastRow = $("table#packing_list_table tr:last").prev().prev();
                $(res.content).insertAfter(lastRow);
            } else if (res.errorCode == "FIELD_ERROR") {
                dialog.html(res.content);
            } else if (res.errorCode == "ACTION_ERROR") {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }
    function removePackingListRow(obj) {
        $(obj).parent().parent().remove();
        calculateSubTotal();
        indexPackage = indexPackage - 1;
    }


</script>