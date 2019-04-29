<%@page import="com.gms.xms.common.constants.AppConstants.ENCODE_TYPE" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="shipment-div">
    <div class="row mbl">
        <div class="col-lg-12">
            <div class="col-md-12">
                <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
            </div>
        </div>
        <s:form id="shipment-info-form">
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="radio-inline cl666"> <input id="chkReturnService" type="checkbox"
                                                                      onchange="onChangeServiceType(true)"/> &nbsp;
                                <xms:localization text="Return Service"/>
                            </label> <label class="radio-inline cl666"> <input id="chkThirdParty" type="checkbox"
                                                                               onchange="onChangeServiceType(false)"/>
                            &nbsp; <xms:localization text="Third Party"/>
                        </label>
                        </div>
                    </div>
                    <div class="col-lg-6" id="return-service-box">
                        <div class="portlet box" id="sender-address-box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Sender Address"/>
                                </div>
                                <div class="tools">
                                    <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="panel-body pan">
                                    <div class="form-body pal">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Company"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.senderAddress.companyName"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Company", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35" onkeyup="searchSenderAddress(true)"/>
                                                    <div id="sender-address-by-company-search-result"></div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Phone"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.senderAddress.phone"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Phone", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="25"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Contact Name"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.senderAddress.contactName"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Contact Name", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35" onkeyup="searchSenderAddress(false)"/>
                                                    <div id="sender-address-by-contact-search-result"></div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Email Address"/>
                                                    </label>
                                                    <s:textfield name="shipmentPage.senderAddress.email"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Email", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="50"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Country"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:select name="shipmentPage.senderAddress.country"
                                                              id="shipmentPage_senderAddress_country" list="countryList"
                                                              listKey="countryId" listValue="countryName"
                                                              cssClass="form-control"
                                                              onchange="changeCountry('sender')"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Address"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.senderAddress.address"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Address 1", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Address 2"/>
                                                    </label>
                                                    <s:textfield name="shipmentPage.senderAddress.address2"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Address 2", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="fw0"> <input
                                                            name="shipmentPage.isSaveSenderAddressBook" value="0"
                                                            tabindex="5" type="checkbox"
                                                            onclick="checkSavaAddressBook('shipmentPage.isSaveSenderAddressBook')"/>
                                                        &nbsp; <xms:localization text="Save to Address Book"/>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="fw0"> <input tabindex="5" type="checkbox" value="true"
                                                                               name="shipmentPage.residentialPickup"
                                                                               <s:if test="shipmentPage.senderAddress.residential.equals('1')">checked="checked"</s:if> />
                                                        &nbsp; <xms:localization text="Residence"/>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="City"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.senderAddress.city"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "City", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35" onkeyup="searchCity(true,true)"/>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Postal Code"/>
                                                    </label>
                                                    <s:textfield name="shipmentPage.senderAddress.postalCode"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Postal Code", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="12" onkeyup="searchCity(true,false)"/>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group" id="div-sender-state">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="State/Province"/>
                                                    </label>
                                                    <s:textfield name="shipmentPage.senderAddress.state"
                                                                 class="form-control" data-placement="top" id="senderStateId"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Sate/Province", ENCODE_TYPE.JAVASCRIPT)%>'/>
                                                </div>
                                            </div>
                                            <div class="col-md-12" id="sender-city-search"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="portlet box" id="receiver-address-box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Recipient Information"/>
                                </div>
                                <div class="tools">
                                    <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="panel-body pan">
                                    <div class="form-body pal">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Company"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.receiverAddress.companyName"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Company", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35" onkeyup="searchReceiverAddress(true)"
                                                                 ondblclick="searchReceiverAddress(true)"/>
                                                    <div id="receiver-address-by-company-search-result"></div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Phone"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.receiverAddress.phone"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Phone", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="25"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Contact Name"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.receiverAddress.contactName"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Contact Name", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35" onkeyup="searchReceiverAddress(false)"
                                                                 ondblclick="searchReceiverAddress(false)"/>
                                                    <div id="receiver-address-by-contact-search-result"></div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Email Address"/>
                                                    </label>
                                                    <s:textfield name="shipmentPage.receiverAddress.email"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Email", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="50"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Country"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:select name="shipmentPage.receiverAddress.country"
                                                              id="shipmentPage_receiverAddress_country"
                                                              list="countryList" listKey="countryId"
                                                              listValue="countryName" cssClass="form-control"
                                                              onchange="changeCountry('receiver')"/>
                                                </div>
                                            </div>
                                            
                                           
                                            
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Address"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.receiverAddress.address"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Address 1", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Address 2"/>
                                                    </label>
                                                    <s:textfield name="shipmentPage.receiverAddress.address2"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Address 2", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="fw0"> <input
                                                            name="shipmentPage.isSaveRecipientAddressBook" value="0"
                                                            tabindex="5" type="checkbox"
                                                            onclick="checkSavaAddressBook('shipmentPage.isSaveRecipientAddressBook')"/>
                                                        &nbsp; <xms:localization text="Save to Address Book"/>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="fw0"> <input tabindex="5" type="checkbox" value="true"
                                                                               name="shipmentPage.residentialDelivery"
                                                                               <s:if test="shipmentPage.receiverAddress.residential == 1">checked="checked"</s:if> />
                                                        &nbsp; <xms:localization text="Residence"/>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="City"/> <span class="s30"> *</span>
                                                    </label>
                                                    <s:textfield name="shipmentPage.receiverAddress.city"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "City", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="35" onkeyup="searchCity(false,true)"/>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Postal Code"/>
                                                    </label>
                                                    
                                                  
                                                    <s:textfield name="shipmentPage.receiverAddress.postalCode"
                                                                 class="form-control alloptions" data-placement="top"
                                                                 data-toggle="tooltip"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Postal Code", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                 maxlength="12" onkeyup="searchCity(false,false)"/>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group" id="div-receiver-state">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="State/Province"/>
                                                    </label>
                                                    <s:textfield name="shipmentPage.receiverAddress.state"
                                                                 class="form-control" data-placement="top"
                                                                 data-toggle="tooltip" id="receiverStateId"
                                                                 data-original-title='<%=WebUtils.getTooltip(request, "Sate/Province", ENCODE_TYPE.JAVASCRIPT)%>'/>
                                                </div>
                                            </div>
                                            <div class="col-md-12" id="receiver-city-search"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Package and Shipment Details"/>
                                </div>
                                <div class="tools">
                                    <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="panel-body pan">
                                    <div class="form-body pal">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="row">
                                                    <div class="form-group col-lg-6">
                                                        <label class="control-label" for="inputName"> <xms:localization
                                                                text="Shipping Date"/>
                                                        </label>

                                                        <div class="form-group input-group">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           type="text" data-date-format="dd MM yyyy"
                                                                           id="shipment-date-input"
                                                                           name="shipmentPage.shipmentDate"
                                                                           readonly="readonly">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-6" id="change-country-div">
                                                        <label class="control-label" for="inputName"> <xms:localization
                                                                text="Carrier"/> <span class="s30"> *</span>
                                                        </label>
                                                        <s:select name="shipmentPage.serviceId"
                                                                  id="shipmentPage_serviceId" list="serviceList"
                                                                  listKey="serviceId" listValue="serviceName"
                                                                  cssClass="form-control"
                                                                  onchange="changeService($(this).val())"/>
                                                    </div>
                                                    <div class="form-group col-lg-6" id="change-service-div">
                                                        <label class="control-label" for="inputName"> <xms:localization
                                                                text="Service Type"/> <span class="s30"> *</span>
                                                        </label>
                                                        <s:select name="shipmentPage.shipmentTypeId"
                                                                  id="shipmentPage_shipmentTypeId"
                                                                  list="shipmentTypeList" listKey="shipmentTypeId"
                                                                  listValue="shipmentTypeName" cssClass="form-control"
                                                                  onchange="changeShipmentType($(this).val())"/>
                                                    </div>
                                                    <div class="form-group col-lg-12" id="change-shipmenttype-div">
                                                        <label class="control-label" for="inputName"> <xms:localization
                                                                text="Package Type"/>
                                                        </label>
                                                        <s:select name="shipmentPage.packageId"
                                                                  id="shipmentPage_packageId" list="packageList"
                                                                  listKey="packageId" listValue="packageName"
                                                                  cssClass="form-control" onchange="changePackage()"/>
                                                        <hr>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="row" id="change-package-div">
                                                    <div class="form-group col-lg-12">
                                                        <label class="control-label" for="inputName"> <xms:localization
                                                                text="Contents"/> <span class="s30"> *</span>
                                                        </label>
                                                        <s:if test="shipmentPage.contentType == ''">
                                                            <br/>
                                                            <label class="radio-inline"> <input type="radio" value="DOX"
                                                                                                name="shipmentPage.contentType"
                                                                                                <s:if test="shipmentPage.contentType == 'DOX'">checked="checked"</s:if>
                                                                                                <s:if test="%{shipmentTypePackage.allowDox == 0}">disabled="disabled"</s:if>
                                                                                                onclick="resetAddPiece($(this).val())"/>
                                                                &nbsp; <xms:localization text="Documents"/>
                                                            </label>
                                                            <label class="radio-inline"> <input type="radio" value="WPX"
                                                                                                name="shipmentPage.contentType"
                                                                                                <s:if test="shipmentPage.contentType == 'WPX'">checked="checked"</s:if>
                                                                                                <s:if test="%{shipmentTypePackage.allowWpx == 0}">disabled="disabled"</s:if>
                                                                                                onclick="resetAddPiece($(this).val())"/>
                                                                &nbsp; <xms:localization text="Parcel"/>
                                                            </label>
                                                        </s:if>
                                                        <s:else>
                                                            <br/>
                                                            <label class="radio-inline"> <input type="radio" value="DOX"
                                                                                                name="shipmentPage.contentType"
                                                                                                <s:if test="shipmentPage.contentType != '' && shipmentPage.contentType == 'DOX'">checked="checked"</s:if>
                                                                                                <s:else>
                                                                                                <s:if test="%{shipmentTypePackage.defaultContentType == 'DOX' || shipmentPage.contentType == 'DOX'}">checked="checked"</s:if>
                                                                                                <s:if test="%{shipmentTypePackage.allowDox == 0}">disabled="disabled"</s:if>
                                                            </s:else> onclick="resetAddPiece($(this).val())"/> &nbsp;
                                                                <xms:localization text="Documents"/>
                                                            </label>
                                                            <label class="radio-inline"> <input type="radio" value="WPX"
                                                                                                name="shipmentPage.contentType"
                                                                                                <s:if test="%{shipmentTypePackage.defaultContentType == 'WPX'}">checked="checked"</s:if>
                                                                                                <s:if test="%{shipmentTypePackage.allowWpx == 0}">disabled="disabled"</s:if>
                                                                                                onclick="resetAddPiece($(this).val())"/>
                                                                &nbsp; <xms:localization text="Parcel"/>
                                                            </label>
                                                        </s:else>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label" for="inputName">
                                                                <xms:localization text="Weight Unit"/>
                                                            </label>
                                                            <s:select name="shipmentPage.weightUnit"
                                                                      list="weightUnitList" cssClass="form-control"
                                                                      id="sel-weight-unit"
                                                                      onchange="changeWeightUnit($(this).val())"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label" for="inputName">
                                                                <xms:localization text="Dimension Unit"/>
                                                            </label>
                                                            <s:select name="shipmentPage.dimensionUnit"
                                                                      list="dimensionUnitList" cssClass="form-control"
                                                                      id="sel-dim-unit"
                                                                      onchange="changeDimUnit($(this).val())"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label" for="inputName">
                                                                <xms:localization text="Currency"/>
                                                            </label>
                                                            <s:select name="shipmentPage.currencyCode"
                                                                      list="currencyList" cssClass="form-control"
                                                                      listKey="code" listValue="name"
                                                                      onchange="changeCurrency($(this).val())"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-lg-12" id="reset-add-piece-div">
                                                        <table class="s32 table table-striped table-bordered" border=""
                                                               id="piece-table">
                                                            <s:if test="%{shipmentPage.pieces == null}">
                                                            <s:if test="%{shipmentTypePackage.defaultContentType == 'DOX'}">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Row"/></th>
                                                                <s:if test="%{shipmentPage.weightUnit == kg }">
                                                                    <th id="weight-unit"><xms:localization text="Weight"/>(kgs)*</th>
                                                                </s:if>
                                                                <s:else>
                                                                    <th id="weight-unit"><xms:localization text="Weight"/>(lbs)*</th>
                                                                </s:else>
                                                                <th></th>
                                                                <th style="text-align: center">
                                                                    <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">
                                                                        <s:if test="%{shipmentPage.dimensionUnit == CM }">
                                                                            <div id="dimensions-unit"><xms:localization text="Dimensions"/>(CM)</div>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <div id="dimensions-unit"><xms:localization text="Dimensions"/>(IN)</div>
                                                                        </s:else>
                                                                        <div style="width: 100%;text-align: center;padding-left: 25px;padding-right: 25px">
                                                                            <span style="float: left">L</span>
                                                                            <span style="float: none">W</span>
                                                                            <span style="float: right">H</span>
                                                                        </div>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:if test="%{shipmentPage.dimensionUnit == CM }">
                                                                            <div id="dimensions-unit"><xms:localization text="Dimensions"/>(CM)</div>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <div id="dimensions-unit"><xms:localization text="Dimensions"/>(IN)</div>
                                                                        </s:else>
                                                                        <div style="width: 100%;text-align: center;padding-left: 25px;padding-right: 25px">
                                                                            <span style="float: left">L*</span>
                                                                            <span style="float: none">W*</span>
                                                                            <span style="float: right">H*</span>
                                                                        </div>
                                                                    </s:else>
                                                                </th>
                                                                <s:if test="shipmentTypePackage.allowCustomValue != 0 && carrierType != 1">
                                                                    <th><xms:localization
                                                                            text="Declared Customs Value"/>*
                                                                    </th>
                                                                </s:if>
                                                                <s:if test="%{shipmentTypePackage.allowDoxAddpiece != 0}">
                                                                    <th class="quantity"
                                                                        <s:if test="%{addPiece == 0}">style="display:none;" </s:if>>
                                                                        <xms:localization text="Quantity"/>*
                                                                    </th>
                                                                </s:if>
                                                                <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                                                                    <th class="nonStandardPackage">
                                                                        <xms:localization text="Non-standard packages"/>
                                                                    </th>
                                                                </s:if>
                                                                <th></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                                <%--<s:property value="dimensionList.id"/>--%>
                                                            <tr id="piece-dt1">
                                                                <td width="2%" style="padding-top: 8px"
                                                                    id="piece-order1" class="order-number">1
                                                                </td>
                                                                <td width="10%"><s:textfield
                                                                        name="shipmentPage.pieces[0].weight" value="0"
                                                                        onkeypress="return formartNumber(event,this,true);"
                                                                        placeholder=""
                                                                        class="form-control alloptions weight"
                                                                        data-placement="top" data-toggle="tooltip"
                                                                        data-original-title='<%=WebUtils.getTooltip(request, "Weight", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                        maxlength="6"></s:textfield></td>
                                                                <td width="15%"><s:select list="dimensionList"
                                                                                          listKey="id" listValue="name"
                                                                                          headerKey="0" headerValue=""
                                                                                          cssClass="form-control alloptions dimensionList"
                                                                                          id="sel-dimension-list"
                                                                                          onchange="changeDimensionList($(this).val(),0)"/></td>
                                                                <td width="40%">
                                                                    <div class="row mg0">
                                                                        <div class="col-lg-4 pd1">
                                                                            <input name="shipmentPage.pieces[0].dimensionL"
                                                                                   class="form-control alloptions dimL"
                                                                                   <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">disabled="true"</s:if>
                                                                                   onkeypress="return formartNumber(event,this,false);"
                                                                                   maxlength="4"/>
                                                                        </div>
                                                                        <div class="col-lg-4 pd1">
                                                                            <input name="shipmentPage.pieces[0].dimensionW"
                                                                                   class="form-control alloptions dimW"
                                                                                   <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">disabled="true"</s:if>
                                                                                   onkeypress="return formartNumber(event,this,false);"
                                                                                   maxlength="4"/>
                                                                        </div>
                                                                        <div class="col-lg-4 pd1">
                                                                            <input name="shipmentPage.pieces[0].dimensionH"
                                                                                   class="form-control alloptions dimH"
                                                                                   <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">disabled="true"</s:if>
                                                                                   onkeypress="return formartNumber(event,this,false);"
                                                                                   maxlength="4"/>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td width="25%"
                                                                    <s:if test="shipmentTypePackage.allowCustomValue == 0 || carrierType == 1">style="display:none"</s:if>>
                                                                    <s:textfield
                                                                            name="shipmentPage.pieces[0].customValue"
                                                                            value="0.00"
                                                                            onkeypress="return formartNumber(event,this,true);"
                                                                            placeholder=""
                                                                            class="form-control alloptions customValue"
                                                                            data-placement="top" data-toggle="tooltip"
                                                                            data-original-title='<%=WebUtils.getTooltip(request, "Shipment value", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                            maxlength="10"></s:textfield></td>
                                                                <s:if test="%{shipmentTypePackage.allowDoxAddpiece != 0}">
                                                                    <td class="quantity"><s:textfield
                                                                            name="shipmentPage.pieces[0].quantity"
                                                                            value="1"
                                                                            onkeypress="return formartNumber(event,this,false);"
                                                                            placeholder="" class="form-control quantity"
                                                                            data-placement="top" data-toggle="tooltip"
                                                                            data-original-title='<%=WebUtils.getTooltip(request, "Quantity", ENCODE_TYPE.JAVASCRIPT)%>'/></td>
                                                                </s:if>
                                                                <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                                                                    <td class="nonStandardPackage">
                                                                        <input class="nonStandardPackage" name="shipmentPage.pieces[0].nonStandardPackage" type="checkbox">
                                                                    </td>
                                                                </s:if>

                                                                <td width="3%" style="padding-top: 9px"><i id="remove"
                                                                                                           class="fa fa-times-circle-o s10"
                                                                                                           style="font-size: 18px; display: none;"
                                                                                                           onclick='removePiece($(this))'></i>
                                                                </td>
                                                            </tr>
                                                            <s:if test="%{shipmentTypePackage.allowDoxAddpiece != 0}">
                                                                <s:hidden name="shipmentPage.isAddPiece" value="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:hidden name="shipmentPage.isAddPiece" value="false"/>
                                                            </s:else>
                                                            </s:if>
                                                            <s:else>
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Row"/></th>
                                                                <s:if test="%{shipmentPage.weightUnit == kg }">
                                                                    <th id="weight-unit"><xms:localization text="Weight"/>(kgs)*</th>
                                                                </s:if>
                                                                <s:else>
                                                                    <th id="weight-unit"><xms:localization text="Weight"/>(lbs)*</th>
                                                                </s:else>
                                                                <th style="text-align: center">
                                                                    <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">
                                                                        <s:if test="%{shipmentPage.dimensionUnit == CM }">
                                                                            <div id="dimensions-unit"><xms:localization text="Dimensions"/>(CM)</div>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <div id="dimensions-unit"><xms:localization text="Dimensions"/>(IN)</div>
                                                                        </s:else>
                                                                        <div style="width: 100%;text-align: center;padding-left: 25px;padding-right: 25px">
                                                                            <span style="float: left">L</span>
                                                                            <span style="float: none">W</span>
                                                                            <span style="float: right">H</span>
                                                                        </div>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:if test="%{shipmentPage.dimensionUnit == CM }">
                                                                            <div id="dimensions-unit"><xms:localization text="Dimensions"/>(CM)</div>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <div id="dimensions-unit"><xms:localization text="Dimensions"/>(IN)</div>
                                                                        </s:else>
                                                                        <div style="width: 100%;text-align: center;padding-left: 25px;padding-right: 25px">
                                                                            <span style="float: left">L*</span>
                                                                            <span style="float: none">W*</span>
                                                                            <span style="float: right">H*</span>
                                                                        </div>
                                                                    </s:else>
                                                                </th>
                                                                <s:if test="shipmentTypePackage.allowCustomValue != 0 && carrierType != 1">
                                                                    <th><xms:localization
                                                                            text="Declared Customs Value"/>*
                                                                    </th>
                                                                </s:if>
                                                                <s:if test="%{shipmentTypePackage.allowWpxAddpiece != 0}">
                                                                    <th class="quantity"
                                                                        <s:if test="%{addPiece == 0}">style="display:none;" </s:if>>
                                                                        <xms:localization text="Quantity"/>*
                                                                    </th>
                                                                </s:if>
                                                                <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                                                                    <th class="nonStandardPackage">
                                                                        <xms:localization text="Non-standard packages"/>
                                                                    </th>
                                                                </s:if>
                                                                <th></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tr id="piece-dt1">
                                                                <td width="2%" style="padding-top: 8px"
                                                                    id="piece-order1" class="order-number">1
                                                                </td>
                                                                <td width="10%"><s:textfield
                                                                        name="shipmentPage.pieces[0].weight" value=""
                                                                        placeholder=""
                                                                        class="form-control alloptions weight"
                                                                        data-placement="top" data-toggle="tooltip"
                                                                        data-original-title='<%=WebUtils.getTooltip(request, "Weight", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                        maxlength="6"
                                                                        onkeypress="return formartNumber(event,this,false);"/></td>
                                                                <td width="40%">
                                                                    <div class="row mg0">
                                                                        <div class="col-lg-4 pd1">
                                                                            <input name="shipmentPage.pieces[0].dimensionL"
                                                                                   value=""
                                                                                   class="form-control alloptions dimL"
                                                                                   <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">disabled="true"</s:if>
                                                                                   onkeypress="return formartNumber(event,this,false);"
                                                                                   maxlength="4"/>
                                                                        </div>
                                                                        <div class="col-lg-4 pd1">
                                                                            <input name="shipmentPage.pieces[0].dimensionW"
                                                                                   value=""
                                                                                   class="form-control alloptions dimW"
                                                                                   <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">disabled="true"</s:if>
                                                                                   onkeypress="return formartNumber(event,this,false);"
                                                                                   maxlength="4"/>
                                                                        </div>
                                                                        <div class="col-lg-4 pd1">
                                                                            <input name="shipmentPage.pieces[0].dimensionH"
                                                                                   value=""
                                                                                   class="form-control alloptions dimH"
                                                                                   <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">disabled="true"</s:if>
                                                                                   onkeypress="return formartNumber(event,this,false);"
                                                                                   maxlength="4"/>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td width="25%"
                                                                    <s:if test="shipmentTypePackage.allowCustomValue == 0 || carrierType == 1">style="display:none"</s:if>>
                                                                    <s:textfield
                                                                            name="shipmentPage.pieces[0].customValue"
                                                                            value="0.00" placeholder=""
                                                                            class="form-control alloptions customValue"
                                                                            data-placement="top" data-toggle="tooltip"
                                                                            data-original-title='<%=WebUtils.getTooltip(request, "Shipment value", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                            maxlength="10"
                                                                            onkeypress="return formartNumber(event,this,true);"/></td>
                                                                <s:if test="%{shipmentTypePackage.allowWpxAddpiece != 0}">
                                                                    <td class="quantity"><s:textfield
                                                                            name="shipmentPage.pieces[0].quantity"
                                                                            value="1" placeholder=""
                                                                            class="form-control quantity"
                                                                            data-placement="top" data-toggle="tooltip"
                                                                            data-original-title='<%=WebUtils.getTooltip(request, "Quantity", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                            onkeypress="return formartNumber(event,this,false);"/></td>
                                                                </s:if>
                                                                <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                                                                    <td class="nonStandardPackage">
                                                                        <input class="nonStandardPackage"
                                                                               name="shipmentPage.pieces[0].nonStandardPackage"
                                                                               type="checkbox">
                                                                    </td>
                                                                </s:if>
                                                                <td width="3%" style="padding-top: 9px"><i id="remove"
                                                                                                           class="fa fa-times-circle-o s10"
                                                                                                           style="font-size: 18px; display: none;"
                                                                                                           onclick='removePiece($(this))'></i>
                                                                </td>
                                                            </tr>
                                                            <s:if test="%{shipmentTypePackage.allowWpxAddpiece != 0}">
                                                                <s:hidden name="shipmentPage.isAddPiece" value="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:hidden name="shipmentPage.isAddPiece" value="false"/>
                                                            </s:else>
                                                            </s:else>
                                                            </s:if>
                                                            <s:else>
                                                            <s:if test="%{shipmentTypePackage.defaultContentType == 'DOX'}">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Row"/></th>
                                                                <s:if test="%{shipmentPage.weightUnit == kg }">
                                                                    <th id="weight-unit"><xms:localization text="Weight"/>(kgs)*</th>
                                                                </s:if>
                                                                <s:else>
                                                                    <th id="weight-unit"><xms:localization text="Weight"/>(lbs)*</th>
                                                                </s:else>
                                                                <th style="text-align: center">
                                                                    <s:if test="%{shipmentPage.dimensionUnit == CM }">
                                                                        <div id="dimensions-unit"><xms:localization text="Dimensions"/>(CM)</div>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <div id="dimensions-unit"><xms:localization text="Dimensions"/>(IN)</div>
                                                                    </s:else>
                                                                    <div style="width: 100%;text-align: center;padding-left: 25px;padding-right: 25px">
                                                                        <span style="float: left">L*</span>
                                                                        <span style="float: none">W*</span>
                                                                        <span style="float: right">H*</span>
                                                                    </div>
                                                                </th>
                                                                <s:if test="shipmentTypePackage.allowCustomValue != 0 && carrierType != 1">
                                                                    <th><xms:localization
                                                                            text="Declared Customs Value"/>*
                                                                    </th>
                                                                </s:if>
                                                                <s:if test="%{shipmentTypePackage.allowDoxAddpiece != 0}">
                                                                    <th class="quantity"
                                                                        <s:if test="%{addPiece == 0}">style="display:none;" </s:if>>
                                                                        <xms:localization text="Quantity"/>*
                                                                    </th>
                                                                </s:if>
                                                                <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                                                                    <th class="nonStandardPackage">
                                                                        <xms:localization text="Non-standard packages"/>
                                                                    </th>
                                                                </s:if>
                                                                <th></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:iterator value="shipmentPage.pieces" status="row">
                                                            <tr id="piece-dt1">
                                                                <td width="2%" style="padding-top: 8px"
                                                                    id="piece-order1" class="order-number">1
                                                                </td>
                                                                <td width="10%"><s:textfield
                                                                        name="shipmentPage.pieces[%{#row.index}].weight"
                                                                        onkeypress="return formartNumber(event,this,true);"
                                                                        placeholder=""
                                                                        class="form-control alloptions weight"
                                                                        data-placement="top" data-toggle="tooltip"
                                                                        data-original-title='<%=WebUtils.getTooltip(request, "Weight", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                        maxlength="6"></s:textfield></td>
                                                                <td width="40%">
                                                                    <div class="row mg0">
                                                                        <div class="col-lg-4 pd1">
                                                                            <input name="shipmentPage.pieces[<s:property value="#row.index"/>].dimensionL"
                                                                                   value="<s:property value="shipmentPage.pieces[#row.index].dimensionL"/>"
                                                                                   class="form-control alloptions dimL"
                                                                                   <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">disabled="true"</s:if>
                                                                                   onkeypress="return formartNumber(event,this,false);"
                                                                                   maxlength="4"/>
                                                                        </div>
                                                                        <div class="col-lg-4 pd1">
                                                                            <input name="shipmentPage.pieces[<s:property value="#row.index"/>].dimensionW"
                                                                                   value="<s:property value="shipmentPage.pieces[#row.index].dimensionW"/>"
                                                                                   class="form-control alloptions dimW"
                                                                                   <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">disabled="true"</s:if>
                                                                                   onkeypress="return formartNumber(event,this,false);"
                                                                                   maxlength="4"/>
                                                                        </div>
                                                                        <div class="col-lg-4 pd1">
                                                                            <input name="shipmentPage.pieces[<s:property value="#row.index"/>].dimensionH"
                                                                                   value="<s:property value="shipmentPage.pieces[#row.index].dimensionH"/>"
                                                                                   class="form-control alloptions dimH"
                                                                                   <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">disabled="true"</s:if>
                                                                                   onkeypress="return formartNumber(event,this,false);"
                                                                                   maxlength="4"/>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td width="25%"
                                                                    <s:if test="shipmentTypePackage.allowCustomValue == 0 || carrierType == 1">style="display:none"</s:if>>
                                                                    <s:textfield
                                                                            name="shipmentPage.pieces[%{#row.index}].customValue"
                                                                            onkeypress="return formartNumber(event,this,true);"
                                                                            placeholder=""
                                                                            class="form-control alloptions customValue"
                                                                            data-placement="top" data-toggle="tooltip"
                                                                            data-original-title='<%=WebUtils.getTooltip(request, "Shipment value", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                            maxlength="10"></s:textfield></td>
                                                                <s:if test="%{shipmentTypePackage.allowDoxAddpiece != 0}">
                                                                    <td class="quantity"><s:textfield
                                                                            name="shipmentPage.pieces[%{#row.index}].quantity"
                                                                            value="%{quantity}"
                                                                            onkeypress="return formartNumber(event,this,false);"
                                                                            placeholder="" class="form-control quantity"
                                                                            data-placement="top" data-toggle="tooltip"
                                                                            data-original-title='<%=WebUtils.getTooltip(request, "Quantity", ENCODE_TYPE.JAVASCRIPT)%>'/></td>
                                                                </s:if>
                                                                <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                                                                    <td class="nonStandardPackage">
                                                                        <input class="nonStandardPackage"
                                                                               name="shipmentPage.pieces[<s:property value='%{#row.index}'/>].nonStandardPackage"
                                                                               type="checkbox">
                                                                    </td>
                                                                </s:if>
                                                                <td width="3%" style="padding-top: 9px"><i id="remove"
                                                                                                           class="fa fa-times-circle-o s10"
                                                                                                           style="font-size: 18px; display: none;"
                                                                                                           onclick='removePiece($(this))'></i>
                                                                </td>
                                                            </tr>
                                                            <s:if test="%{shipmentTypePackage.allowDoxAddpiece != 0}">
                                                                <s:hidden name="shipmentPage.isAddPiece" value="true"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:hidden name="shipmentPage.isAddPiece" value="false"/>
                                                            </s:else>
                                                            </s:iterator>
                                                            </s:if>
                                                            <s:else>
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Row"/></th>
                                                                <s:if test="%{shipmentPage.weightUnit == kg }">
                                                                    <th id="weight-unit"><xms:localization text="Weight"/>(kgs)*</th>
                                                                </s:if>
                                                                <s:else>
                                                                    <th id="weight-unit"><xms:localization text="Weight"/>(lbs)*</th>
                                                                </s:else>
                                                                <th style="text-align: center">
                                                                    <s:if test="%{shipmentPage.dimensionUnit == CM }">
                                                                    <div id="dimensions-unit"><xms:localization text="Dimensions"/>(CM)</div>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <div id="dimensions-unit"><xms:localization text="Dimensions"/>(IN)</div>
                                                                    </s:else>
                                                                    <div style="width: 100%;text-align: center;padding-left: 25px;padding-right: 25px">
                                                                        <span style="float: left">L*</span>
                                                                        <span style="float: none">W*</span>
                                                                        <span style="float: right">H*</span>
                                                                    </div>
                                                                </th>
                                                                <s:if test="shipmentTypePackage.allowCustomValue != 0 && carrierType != 1">
                                                                    <th><xms:localization
                                                                            text="Declared Customs Value"/>*
                                                                    </th>
                                                                </s:if>
                                                                <th class="quantity"
                                                                    <s:if test="%{addPiece == 0}">style="display:none;" </s:if>>
                                                                    <xms:localization text="Quantity"/>*
                                                                </th>
                                                                <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                                                                    <th class="nonStandardPackage">
                                                                        <xms:localization text="Non-standard packages"/>
                                                                    </th>
                                                                </s:if>
                                                                <th></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:iterator value="shipmentPage.pieces" status="row">
                                                                <tr id='piece-dt<s:property value="#row.index+1"/>'>
                                                                    <td width="2%" style="padding-top: 8px"
                                                                        id="piece-order1" class="order-number">
                                                                        <s:property value="#row.index+1"/></td>
                                                                    <td width="10%"><s:textfield
                                                                            name="shipmentPage.pieces[%{#row.index}].weight"
                                                                            value="%{weight}" placeholder=""
                                                                            class="form-control alloptions weight"
                                                                            data-placement="top" data-toggle="tooltip"
                                                                            data-original-title='<%=WebUtils.getTooltip(request, "Shipment weight", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                            maxlength="6"
                                                                            onkeypress="return formartNumber(event,this,false);"/></td>
                                                                    <td width="40%">
                                                                        <div class="row mg0">
                                                                            <div class="col-lg-4 pd1">
                                                                                <s:textfield
                                                                                        name="shipmentPage.pieces[%{#row.index}].dimensionL"
                                                                                        value="%{dimensionL}"
                                                                                        class="form-control dimL"
                                                                                        onkeypress="return formartNumber(event,this,false);"/>
                                                                            </div>
                                                                            <div class="col-lg-4 pd1">
                                                                                <s:textfield
                                                                                        name="shipmentPage.pieces[%{#row.index}].dimensionW"
                                                                                        value="%{dimensionW}"
                                                                                        class="form-control dimW"
                                                                                        onkeypress="return formartNumber(event,this,false);"/>
                                                                            </div>
                                                                            <div class="col-lg-4 pd1">
                                                                                <s:textfield
                                                                                        name="shipmentPage.pieces[%{#row.index}].dimensionH"
                                                                                        value="%{dimensionH}"
                                                                                        class="form-control dimH"
                                                                                        onkeypress="return formartNumber(event,this,false);"/>
                                                                            </div>
                                                                        </div>
                                                                    </td>
                                                                    <td width="25%"
                                                                        <s:if test="shipmentTypePackage.allowCustomValue == 0 || carrierType == 1">style="display:none"</s:if>>
                                                                        <s:textfield
                                                                                name="shipmentPage.pieces[%{#row.index}].customValue"
                                                                                value="%{customValue}" placeholder=""
                                                                                class="form-control alloptions customValue"
                                                                                data-placement="top"
                                                                                data-toggle="tooltip"
                                                                                data-original-title='<%=WebUtils.getTooltip(request, "Shipment value", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                                maxlength="25"
                                                                                onkeypress="return formartNumber(event,this,true);"/></td>
                                                                    <td class="quantity"
                                                                        <s:if test="%{addPiece == 0}">style="display:none;" </s:if>>
                                                                        <s:textfield
                                                                                name="shipmentPage.pieces[%{#row.index}].quantity"
                                                                                value="%{quantity}" placeholder=""
                                                                                class="form-control alloptions quantity"
                                                                                data-placement="top"
                                                                                data-toggle="tooltip"
                                                                                data-original-title='<%=WebUtils.getTooltip(request, "Quantity", ENCODE_TYPE.JAVASCRIPT)%>'
                                                                                maxlength="25"
                                                                                onkeypress="return formartNumber(event,this,false);"/></td>
                                                                    <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                                                                        <td class="nonStandardPackage">
                                                                            <input class="nonStandardPackage"
                                                                                   name="shipmentPage.pieces[<s:property value='%{#row.index}'/>].nonStandardPackage"
                                                                                   type="checkbox">
                                                                        </td>
                                                                    </s:if>
                                                                    <td width="3%" style="padding-top: 9px"><i
                                                                            id="remove" class="fa fa-times-circle-o s10"
                                                                            style="font-size: 18px;<s:if
                                                                                    test="%{#row.index == 0}"> display:none; </s:if>"
                                                                            onclick='removePiece($(this))'></i></td>
                                                                </tr>
                                                            </s:iterator>
                                                            </s:else>
                                                            </s:else>
                                                            </tbody>
                                                        </table>
                                                        <s:if test="%{shipmentTypePackage.defaultContentType == 'DOX' and shipmentTypePackage.allowDoxAddpiece != 0}">
                                                            <button class="btn s33" type="button" onclick="addPiece()">
                                                                <xms:localization text="Add Piece"/>
                                                            </button>
                                                        </s:if>
                                                        <s:else>
                                                            <s:if test="shipmentTypePackage.allowWpxAddpiece != 0">
                                                                <button class="btn s33" type="button"
                                                                        onclick="addPiece()">
                                                                    <xms:localization text="Add Piece"/>
                                                                </button>
                                                            </s:if>
                                                        </s:else>
                                                        <script type="text/javascript">
                                                            var order = $('#piece-table tbody tr').length;
                                                            function addPiece() {
                                                                order++;
                                                                index = order - 1;
                                                                var addPieceContent = $("#piece-dt" + index).clone();
                                                                addPieceContent.attr('id', 'piece-dt' + order);
                                                                addPieceContent.find(".order-number").html(order);
                                                                addPieceContent.find(".weight").attr('name', 'shipmentPage.pieces[' + index + '].weight');
//                                                                addPieceContent.find(".dimensionList").attr('name', 'dimensionList[' + index + ']');
                                                                addPieceContent.find(".dimensionList").attr('onchange', 'changeDimensionList($(this).val(),' + index + ')');
                                                                addPieceContent.find(".dimL").attr('name', 'shipmentPage.pieces[' + index + '].dimensionL');
                                                                addPieceContent.find(".dimW").attr('name', 'shipmentPage.pieces[' + index + '].dimensionW');
                                                                addPieceContent.find(".dimH").attr('name', 'shipmentPage.pieces[' + index + '].dimensionH');
                                                                addPieceContent.find(".customValue").attr('name', 'shipmentPage.pieces[' + index + '].customValue');
                                                                addPieceContent.find(".quantity").attr('name', 'shipmentPage.pieces[' + index + '].quantity');
                                                                addPieceContent.find(".nonStandardPackage").attr('name', 'shipmentPage.pieces[' + index + '].nonStandardPackage');
                                                                addPieceContent.find('#remove').show();
                                                                $('#piece-table tbody tr:last').after(addPieceContent);
                                                            }
                                                            function removePiece($this) {
                                                                $this.closest("tr").remove();
                                                                $('#piece-table tbody tr').each(function (i) {
                                                                    $(this).find(".order-number").html(i + 1);
                                                                });
                                                                order--;
                                                            }
                                                        </script>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-12" id="additional-config-div">
                                                        <div class="form-group">
                                                            <label class="fw0"> <input tabindex="5" type="checkbox"
                                                                                       name="shipmentPage.insurance"
                                                                                       value="true"/> &nbsp;
                                                                <xms:localization text="Insurance"/>
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <div class="text-left pal pdt10"
                                                             style="margin-bottom: 50px; margin-top: 102px; text-align: right;">
                                                            <button class="btn s33a" type="button"
                                                                    onclick="createNewShipment()">
                                                                <xms:localization text="New Shipment"/>
                                                            </button>
                                                            <s:if test="quotable">
                                                                <button class="btn s33a" type="button"
                                                                        onclick="getQuote()">
                                                                    <xms:localization text="Quote"/>
                                                                </button>
                                                           </s:if>
                                                            <button type="button" class="btn s33a"
                                                                    onclick="continueBooking()">
                                                                <xms:localization text="Continue Booking"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:form>
    </div>
</div>
<s:hidden name="defaultAddressJson" id="defaultAddressJson"/>
<div id="continue-booking-div"></div>
<div id="get-quote-dialog" title="<xms:localization text="Quote" />"></div>
<div id="session_time_out_dialog" title="<xms:localization text="Message" />"></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/webship/customer/pages/webship.js"></script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/common/common.js"></script>
<script type="text/javascript">
    var defaultCurrencyCode = "<s:property value="defaultCurrencyCode"/>";
    $(document).ready(function () {
        loadAdditionalConfig();
        loadState('sender');
        loadState('receiver');
    });
    $('body').click(function () {
        $('.sss1').hide();
    });
    function createNewShipment() {
        window.location.reload();
    }
    function getQuote() {
        $(".nonStandardPackage input[type=checkbox]").each(function() {
            $(this).val(this.checked);
        });
        loadDialogQuoteWithSessionTimeout("webship_get_quote.ix?reqType=json", "webship_save_quote.ix?reqType=json", "get-quote-dialog", "shipment-info-form", "saveQuoteLog", "<xms:localization text="Save quote" />", "<xms:localization text="Ok" />", "session_time_out_dialog",
                "<xms:localization text="Ok" />");

        var senderId = $("select[name='shipmentPage.senderAddress.country']").val();
        var receiverId = $("select[name='shipmentPage.receiverAddress.country']").val();
        if (senderId != 12 &&  receiverId != 12) {
            $('.ui-dialog-buttonset button:contains("Save quote")').button().hide();
        }
    }
    function changeCurrency(code) {
        if (code != defaultCurrencyCode) {
            $(".fw0:contains('Insurance')").find("input[type='checkbox']").attr("disabled", "disabled");
        } else {
            $(".fw0:contains('Insurance')").find("input[type='checkbox']").attr("disabled", false);
        }
    }
    function changeDimensionList(val, no) {
        if (val > 0) {
            var data = {
                "dimensionId": val
            };
            $.post("webship_change_dimension_type.ix?reqType=json", data, function (res) {
                if (res.errorCode == "SUCCESS") {
                    var json = res.content.trim();
                    var obj = JSON.parse(json);
                    $("input[name='shipmentPage.pieces[" + no + "].dimensionL']").val(obj.l);
                    $("input[name='shipmentPage.pieces[" + no + "].dimensionW']").val(obj.w);
                    $("input[name='shipmentPage.pieces[" + no + "].dimensionH']").val(obj.h);
                } else {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                alertDialog.dialog("open");
            });
        } else {
            $("input[name='shipmentPage.pieces[" + no + "].dimensionL']").val("");
            $("input[name='shipmentPage.pieces[" + no + "].dimensionW']").val("");
            $("input[name='shipmentPage.pieces[" + no + "].dimensionH']").val("");
        }
    }

    // Prepare for return service

    function onChangeServiceType(isReturn) {
        var defaultAddressJs = JSON.parse($("#defaultAddressJson").val());
        if (isReturn) {
            $("#chkThirdParty").prop("checked", false);
            if ($("#chkReturnService").prop("checked")) {
                $("input[name='shipmentPage.senderAddress.companyName']").val("");
                $("input[name='shipmentPage.senderAddress.contactName']").val("");
                $("input[name='shipmentPage.senderAddress.phone']").val("");
                $("input[name='shipmentPage.senderAddress.email']").val("");
                $("select[name='shipmentPage.senderAddress.country']").val("");
                $("input[name='shipmentPage.senderAddress.address']").val("");
                $("input[name='shipmentPage.senderAddress.address2']").val("");
                $("input[name='shipmentPage.senderAddress.city']").val("");
                $("input[name='shipmentPage.senderAddress.postalCode']").val("");
                $("input[name='shipmentPage.senderAddress.state']").val("");

                $("input[name='shipmentPage.receiverAddress.companyName']").val(defaultAddressJs["companyName"]);
                $("input[name='shipmentPage.receiverAddress.contactName']").val(defaultAddressJs["contactName"]);
                $("input[name='shipmentPage.receiverAddress.phone']").val(defaultAddressJs["phone"]);
                $("input[name='shipmentPage.receiverAddress.email']").val(defaultAddressJs["email"]);
                $("select[name='shipmentPage.receiverAddress.country']").val(defaultAddressJs["country"]);
                $("input[name='shipmentPage.receiverAddress.address']").val(defaultAddressJs["address"]);
                $("input[name='shipmentPage.receiverAddress.address2']").val(defaultAddressJs["address2"]);
                $("input[name='shipmentPage.receiverAddress.city']").val(defaultAddressJs["city"]);
                $("input[name='shipmentPage.receiverAddress.postalCode']").val(defaultAddressJs["postalCode"]);
                $("input[name='shipmentPage.receiverAddress.state']").val(defaultAddressJs["state"]);
            } else {
                $("input[name='shipmentPage.receiverAddress.companyName']").val("");
                $("input[name='shipmentPage.receiverAddress.contactName']").val("");
                $("input[name='shipmentPage.receiverAddress.phone']").val("");
                $("input[name='shipmentPage.receiverAddress.email']").val("");
                $("select[name='shipmentPage.receiverAddress.country']").val("");
                $("input[name='shipmentPage.receiverAddress.address']").val("");
                $("input[name='shipmentPage.receiverAddress.address2']").val("");
                $("input[name='shipmentPage.receiverAddress.city']").val("");
                $("input[name='shipmentPage.receiverAddress.postalCode']").val("");
                $("input[name='shipmentPage.receiverAddress.state']").val("");

                $("input[name='shipmentPage.senderAddress.companyName']").val(defaultAddressJs["companyName"]);
                $("input[name='shipmentPage.senderAddress.contactName']").val(defaultAddressJs["contactName"]);
                $("input[name='shipmentPage.senderAddress.phone']").val(defaultAddressJs["phone"]);
                $("input[name='shipmentPage.senderAddress.email']").val(defaultAddressJs["email"]);
                $("select[name='shipmentPage.senderAddress.country']").val(defaultAddressJs["country"]);
                $("input[name='shipmentPage.senderAddress.address']").val(defaultAddressJs["address"]);
                $("input[name='shipmentPage.senderAddress.address2']").val(defaultAddressJs["address2"]);
                $("input[name='shipmentPage.senderAddress.city']").val(defaultAddressJs["city"]);
                $("input[name='shipmentPage.senderAddress.postalCode']").val(defaultAddressJs["postalCode"]);
                $("input[name='shipmentPage.senderAddress.state']").val(defaultAddressJs["state"]);
            }
        } else {
            if ($("#chkThirdParty").prop("checked")) {
                $("#chkReturnService").prop("checked", false);
                $("input[name='shipmentPage.senderAddress.companyName']").val("");
                $("input[name='shipmentPage.senderAddress.contactName']").val("");
                $("input[name='shipmentPage.senderAddress.phone']").val("");
                $("input[name='shipmentPage.senderAddress.email']").val("");
                $("select[name='shipmentPage.senderAddress.country']").val("");
                $("input[name='shipmentPage.senderAddress.address']").val("");
                $("input[name='shipmentPage.senderAddress.address2']").val("");
                $("input[name='shipmentPage.senderAddress.city']").val("");
                $("input[name='shipmentPage.senderAddress.postalCode']").val("");
                $("input[name='shipmentPage.senderAddress.state']").val("");

                $("input[name='shipmentPage.receiverAddress.companyName']").val("");
                $("input[name='shipmentPage.receiverAddress.contactName']").val("");
                $("input[name='shipmentPage.receiverAddress.phone']").val("");
                $("input[name='shipmentPage.receiverAddress.email']").val("");
                $("select[name='shipmentPage.receiverAddress.country']").val("");
                $("input[name='shipmentPage.receiverAddress.address']").val("");
                $("input[name='shipmentPage.receiverAddress.address2']").val("");
                $("input[name='shipmentPage.receiverAddress.city']").val("");
                $("input[name='shipmentPage.receiverAddress.postalCode']").val("");
                $("input[name='shipmentPage.receiverAddress.state']").val("");
            } else {
                $("input[name='shipmentPage.receiverAddress.companyName']").val("");
                $("input[name='shipmentPage.receiverAddress.contactName']").val("");
                $("input[name='shipmentPage.receiverAddress.phone']").val("");
                $("input[name='shipmentPage.receiverAddress.email']").val("");
                $("select[name='shipmentPage.receiverAddress.country']").val("");
                $("input[name='shipmentPage.receiverAddress.address']").val("");
                $("input[name='shipmentPage.receiverAddress.address2']").val("");
                $("input[name='shipmentPage.receiverAddress.city']").val("");
                $("input[name='shipmentPage.receiverAddress.postalCode']").val("");
                $("input[name='shipmentPage.receiverAddress.state']").val("");

                $("input[name='shipmentPage.senderAddress.companyName']").val(defaultAddressJs["companyName"]);
                $("input[name='shipmentPage.senderAddress.contactName']").val(defaultAddressJs["contactName"]);
                $("input[name='shipmentPage.senderAddress.phone']").val(defaultAddressJs["phone"]);
                $("input[name='shipmentPage.senderAddress.email']").val(defaultAddressJs["email"]);
                $("select[name='shipmentPage.senderAddress.country']").val(defaultAddressJs["country"]);
                $("input[name='shipmentPage.senderAddress.address']").val(defaultAddressJs["address"]);
                $("input[name='shipmentPage.senderAddress.address2']").val(defaultAddressJs["address2"]);
                $("input[name='shipmentPage.senderAddress.city']").val(defaultAddressJs["city"]);
                $("input[name='shipmentPage.senderAddress.postalCode']").val(defaultAddressJs["postalCode"]);
                $("input[name='shipmentPage.senderAddress.state']").val(defaultAddressJs["state"]);
            }
        }
    }
    function setdefaulttime() {
        var nowdate = new Date();
        var nh = nowdate.getHours();
        var nm = nowdate.getMinutes();
        $('#sel-pickup-nolater').val("<s:property value="defCloseTime"/>:00");
        if (nh < 6) {
            $('#sel-pickup-time').val("09:00:00");
        } else if (nh < 18) {
            var nhstr = nh;
            if (nh < 10)
                nhstr = "0" + nh;
            if (nm == 0) {
                $('#sel-pickup-time').val(nhstr + ":00:00");
            } else if (nm <= 30) {
                $('#sel-pickup-time').val(nhstr + ":30:00");
            } else {
                nh = nh + 1;
                nhstr = nh;
                if (nh < 10)
                    nhstr = "0" + nh;
                if (nh == 18)
                    $('#sel-pickup-time').val("17:30:00");
                else
                    $('#sel-pickup-time').val(nhstr + ":00:00");
            }
        } else {
            $('#sel-pickup-time').val("17:30:00");
        }
    }
    function setRequireLabel(labelId, isRequired) {
        if (isRequired) {
            $("#" + labelId + " span.s30").remove();
            $("#" + labelId).append("<span class=\"s30\"> *</span>");
        } else {
            $("#" + labelId + " span.s30").remove();
        }

    }
    function checkSavaAddressBook(nameCheck) {
        if ($('input[name="' + nameCheck + '"]').is(":checked")) {
            $('input[name="' + nameCheck + '"]').val(1);
        } else {
            $('input[name="' + nameCheck + '"]').val(0);
        }
    }

</script>