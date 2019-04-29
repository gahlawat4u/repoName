<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="form-group">
    <label class="control-label" for="inputName"> <xms:localization text="Commercial Invoice"/>
    </label>
    <s:select list="selCommercialInvoice" name="commercialInvoiceSelect" id="commercialInvoiceSelect"
              cssClass="form-control" listKey="key" listValue="value" onchange="onCommerialOrPackingListChange()"/>
</div>
<div id="ws-commercial-invoice-div">
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
    <s:if test="serviceId != 1">
        <div class="form-group ">
            <label class="control-label" for="inputName"> <xms:localization text="Account Number"/>
            </label>
            <s:textfield name="shipmentRequestModel.shipmentInfo.shipperAccount" cssClass="form-control"/>
        </div>
    </s:if>
    <div class="form-group ">
        <label class="control-label" for="inputName"> <xms:localization text="Reason for Export"/>
        </label>
        <s:textfield name="shipmentRequestModel.shipmentInfo.reasonForExport" cssClass="form-control"/>
    </div>
</div>