<%@page import="com.gms.xms.common.constants.AppConstants.ENCODE_TYPE" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/xms-tags" prefix="xms" %>
<div class="form-group col-lg-12" id="content-type-radios">
    <label class="control-label" for="inputName"> <xms:localization text="Contents"/> <span class="s30"> *</span>
    </label> <br/> <label class="radio-inline"> <input type="radio" value="DOX" name="shipmentPage.contentType"
                                                       <s:if test="shipmentTypePackage.defaultContentType == 'DOX'">checked="checked"</s:if>
                                                       <s:if test="%{shipmentTypePackage.allowDox == 0}">disabled="disabled"</s:if>
                                                       onclick="resetAddPiece($(this).val())"/> &nbsp; <xms:localization
        text="Documents"/>
</label> <label class="radio-inline"> <input type="radio" value="WPX" name="shipmentPage.contentType"
                                             <s:if test="shipmentTypePackage.defaultContentType == 'WPX'">checked="checked"</s:if>
                                             <s:if test="%{shipmentTypePackage.allowWpx == 0}">disabled="disabled"</s:if>
                                             onclick="resetAddPiece($(this).val())"/> &nbsp; <xms:localization
        text="Parcel"/>
</label>
</div>
<div class="col-md-4">
    <div class="form-group">
        <label class="control-label" for="inputName"> <xms:localization text="Weight Unit"/>
        </label>
        <s:select name="shipmentPage.weightUnit" list="weightUnitList" cssClass="form-control" id="sel-weight-unit"
                  onchange="changeWeightUnit($(this).val())"/>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group">
        <label class="control-label" for="inputName"> <xms:localization text="Dimension Unit"/>
        </label>
        <s:select name="shipmentPage.dimensionUnit" list="dimensionUnitList" cssClass="form-control" id="sel-dim-unit"
                  onchange="changeDimUnit($(this).val())"/>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group">
        <label class="control-label" for="inputName"> <xms:localization text="Currency"/>
        </label>
        <s:select name="shipmentPage.currencyCode" list="currencyList" cssClass="form-control" listKey="code"
                  listValue="name" onchange="changeCurrency($(this).val())"/>
    </div>
</div>
<div class="form-group col-lg-12" id="reset-add-piece-div">
    <table class="s32 table table-striped table-bordered" border="" id="piece-table">
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
                <th><xms:localization text="Declared Customs Value"/>*</th>
            </s:if>
            <s:if test="%{shipmentTypePackage.allowDoxAddpiece != 0}">
                <th class="quantity" <s:if test="%{addPiece == 0}">style="display:none;" </s:if>><xms:localization
                        text="Quantity"/>*
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
        <s:if test="%{shipmentPage.pieces != null && shipmentTypePackage.allowDoxAddpiece != 0}">
        <s:iterator value="shipmentPage.pieces" status="rowPiece">
        <tr id="piece-dt<s:property value='%{#rowPiece.index+1}'/>">
            <td width="2%" style="padding-top: 8px" id="piece-order<s:property value='%{#rowPiece.index+1}'/>"
                class="order-number"><s:property value='%{#rowPiece.index+1}'/></td>
            <td width="10%"><s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].weight" placeholder=""
                                         class="form-control alloptions weight" data-placement="top"
                                         data-toggle="tooltip"
                                         data-original-title='<%=WebUtils.getTooltip(request, "Shipment weight", ENCODE_TYPE.JAVASCRIPT)%>'
                                         maxlength="6" onkeypress="return formartNumber(event,this,true);"/></td>
            <td width="15%"><s:select list="dimensionList" listKey="id" listValue="name" headerKey="0" headerValue=""
                                      cssClass="form-control alloptions dimensionList" id="sel-dimension-list"
                                      onchange="changeDimensionList($(this).val(),%{#rowPiece.index})"/></td>
            <td width="40%">
                <div class="row mg0">
                    <div class="col-lg-4 pd1">
                        <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">
                            <s:if test="%{shipmentTypePackage.packageId != 3}">
                                <s:textfield value="0" disabled="true"
                                             cssClass="form-control alloptions dimL"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:if>
                            <s:else>
                                <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionL"
                                             cssClass="form-control alloptions dimL"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:else>
                        </s:if>
                        <s:else>
                            <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionL"
                                         cssClass="form-control alloptions dimL"
                                         onkeypress="return formartNumber(event,this,false);" maxlength="4"
                            />
                        </s:else>
                    </div>
                    <div class="col-lg-4 pd1">
                        <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">
                            <s:if test="%{shipmentTypePackage.packageId != 3}">
                                <s:textfield value="0" disabled="true"
                                             cssClass="form-control alloptions dimW"
                                             onkeypress="return formartNumber(event,this,false);" maxlength="4"
                                />
                            </s:if>
                            <s:else>
                                <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionW"
                                             cssClass="form-control alloptions dimW"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:else>
                        </s:if>
                        <s:else>
                            <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionW"
                                         cssClass="form-control alloptions dimW"
                                         onkeypress="return formartNumber(event,this,false);" maxlength="4"
                            />
                        </s:else>
                    </div>
                    <div class="col-lg-4 pd1">
                        <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">
                            <s:if test="%{shipmentTypePackage.packageId != 3}">
                                <s:textfield value="0" disabled="true"
                                             cssClass="form-control alloptions dimH"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:if>
                            <s:else>
                                <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionH"
                                             cssClass="form-control alloptions dimH"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:else>
                        </s:if>
                        <s:else>
                            <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionH"
                                         cssClass="form-control alloptions dimH"
                                         onkeypress="return formartNumber(event,this,false);"
                                         maxlength="4"/>
                        </s:else>
                    </div>
                </div>
            </td>
            <td width="25%"
                <s:if test="shipmentTypePackage.allowCustomValue == 0 || carrierType == 1">style="display:none"</s:if>>
                <s:textfield name="shipmentPage.pieces[0].customValue" placeholder=""
                             class="form-control alloptions customValue" data-placement="top" data-toggle="tooltip"
                             data-original-title='<%=WebUtils.getTooltip(request, "Shipment value", ENCODE_TYPE.JAVASCRIPT)%>'
                             maxlength="10" onkeypress="return formartNumber(event,this,true);"/></td>
            <s:if test="%{shipmentTypePackage.allowDoxAddpiece != 0}">
                <td class="quantity"><s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].quantity"
                                                  placeholder="" class="form-control quantity" data-placement="top"
                                                  data-toggle="tooltip"
                                                  data-original-title='<%=WebUtils.getTooltip(request, "Quantity", ENCODE_TYPE.JAVASCRIPT)%>'
                                                  onkeypress="return formartNumber(event,this,false);"/></td>
            </s:if>
            <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                <td class="nonStandardPackage">
                    <input class="nonStandardPackage"
                           name="shipmentPage.pieces[<s:property value='%{#rowPiece.index}'/>].nonStandardPackage"
                           type="checkbox">
                </td>
            </s:if>
            <td width="3%" style="padding-top: 9px"><i id="remove" class="fa fa-times-circle-o s10"
                                                       style="font-size: 18px; <s:if
                                                               test="#rowPiece.index == 0"> display: none; </s:if>"
                                                       onclick='removePiece($(this))'></i></td>
        </tr>
        </s:iterator>
        </s:if>
        <s:else>
        <tr id="piece-dt1">
            <td width="2%" style="padding-top: 8px" id="piece-order1" class="order-number">1</td>
            <td width="10%"><s:textfield name="shipmentPage.pieces[0].weight" placeholder=""
                                         class="form-control alloptions weight" data-placement="top"
                                         data-toggle="tooltip"
                                         data-original-title='<%=WebUtils.getTooltip(request, "Shipment weight", ENCODE_TYPE.JAVASCRIPT)%>'
                                         maxlength="6" onkeypress="return formartNumber(event,this,true);"/></td>
            <td width="15%"><s:select list="dimensionList" listKey="id" listValue="name" headerKey="0" headerValue=""
                                      cssClass="form-control alloptions dimensionList" id="sel-dimension-list"
                                      onchange="changeDimensionList($(this).val(),0)"/></td>
            <td width="40%">
                <div class="row mg0">
                    <div class="col-lg-4 pd1">
                        <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">
                            <s:if test="%{shipmentTypePackage.packageId != 3}">
                                <s:textfield value="0" disabled="true"
                                             cssClass="form-control alloptions dimL"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:if>
                            <s:else>
                                <s:textfield name="shipmentPage.pieces[0].dimensionL"
                                             cssClass="form-control alloptions dimL"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:else>
                        </s:if>
                        <s:else>
                            <s:textfield name="shipmentPage.pieces[0].dimensionL"
                                         cssClass="form-control alloptions dimL"
                                         onkeypress="return formartNumber(event,this,false);"
                                         maxlength="4"/>
                        </s:else>
                    </div>
                    <div class="col-lg-4 pd1">
                        <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">
                            <s:if test="%{shipmentTypePackage.packageId != 3}">
                                <s:textfield value="0" disabled="true"
                                             cssClass="form-control alloptions dimW"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:if>
                            <s:else>
                                <s:textfield name="shipmentPage.pieces[0].dimensionW"
                                             cssClass="form-control alloptions dimW"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:else>
                        </s:if>
                        <s:else>
                            <s:textfield name="shipmentPage.pieces[0].dimensionW"
                                         cssClass="form-control alloptions dimW"
                                         onkeypress="return formartNumber(event,this,false);" maxlength="4"
                            />
                        </s:else>
                    </div>
                    <div class="col-lg-4 pd1">
                        <s:if test="%{shipmentTypePackage.allowDoxAddpiece == 0}">
                            <s:if test="%{shipmentTypePackage.packageId != 3}">
                                <s:textfield value="0" disabled="true"
                                             cssClass="form-control alloptions dimH"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:if>
                            <s:else>
                                <s:textfield name="shipmentPage.pieces[0].dimensionH"
                                             cssClass="form-control alloptions dimH"
                                             onkeypress="return formartNumber(event,this,false);"
                                             maxlength="4"/>
                            </s:else>
                        </s:if>
                        <s:else>
                            <s:textfield name="shipmentPage.pieces[0].dimensionH"
                                         cssClass="form-control alloptions dimH"
                                         onkeypress="return formartNumber(event,this,false);" maxlength="4"
                            />
                        </s:else>
                    </div>
                </div>
            </td>
            <td width="25%"
                <s:if test="shipmentTypePackage.allowCustomValue == 0 || carrierType == 1">style="display:none"</s:if>>
                <s:textfield name="shipmentPage.pieces[0].customValue" value="0.00" placeholder=""
                             class="form-control alloptions customValue" data-placement="top" data-toggle="tooltip"
                             data-original-title='<%=WebUtils.getTooltip(request, "Shipment value", ENCODE_TYPE.JAVASCRIPT)%>'
                             maxlength="10" onkeypress="return formartNumber(event,this,true);"/></td>
            <s:if test="%{shipmentTypePackage.allowDoxAddpiece != 0}">
                <td class="quantity"><s:textfield name="shipmentPage.pieces[0].quantity" value="1" placeholder=""
                                                  class="form-control quantity" data-placement="top"
                                                  data-toggle="tooltip"
                                                  data-original-title='<%=WebUtils.getTooltip(request, "Quantity", ENCODE_TYPE.JAVASCRIPT)%>'
                                                  onkeypress="return formartNumber(event,this,false);"/></td>
            </s:if>
            <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                <td class="nonStandardPackage">
                    <input class="nonStandardPackage" name="shipmentPage.pieces[0].nonStandardPackage" type="checkbox">
                </td>
            </s:if>
            <td width="3%" style="padding-top: 9px"><i id="remove" class="fa fa-times-circle-o s10"
                                                       style="font-size: 18px; <s:if
                                                               test="#rowPiece.index == 0"> display: none; </s:if>"
                                                       onclick='removePiece($(this))'></i></td>
        </tr>
        </s:else>
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
            <th></th>
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
                <th><xms:localization text="Declared Customs Value"/>*</th>
            </s:if>
            <s:if test="%{shipmentTypePackage.allowWpxAddpiece != 0}">
                <th class="quantity" <s:if test="%{addPiece == 0}">style="display:none;" </s:if>><xms:localization
                        text="Quantity"/>*
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
        <s:if test="%{shipmentPage.pieces != null && shipmentTypePackage.allowWpxAddpiece != 0}">
            <s:iterator value="shipmentPage.pieces" status="rowPiece">
                <tr id="piece-dt<s:property value='%{#rowPiece.index+1}'/>">
                    <td width="2%" style="padding-top: 8px" id="piece-order<s:property value='%{#rowPiece.index+1}'/>"
                        class="order-number"><s:property value='%{#rowPiece.index+1}'/></td>
                    <td width="10%"><s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].weight" placeholder=""
                                                 class="form-control alloptions weight" data-placement="top"
                                                 data-toggle="tooltip"
                                                 data-original-title='<%=WebUtils.getTooltip(request, "Shipment weight", ENCODE_TYPE.JAVASCRIPT)%>'
                                                 maxlength="6"
                                                 onkeypress="return formartNumber(event,this,true);"/></td>
                    <td width="15%"><s:select list="dimensionList" listKey="id" listValue="name" headerKey="0"
                                              headerValue="" cssClass="form-control alloptions dimensionList"
                                              id="sel-dimension-list"
                                              onchange="changeDimensionList($(this).val(),%{#rowPiece.index})"/></td>
                    <td width="40%">
                        <div class="row mg0">
                            <div class="col-lg-4 pd1">
                                <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">
                                    <s:if test="%{shipmentTypePackage.packageId != 3}">
                                        <s:textfield value="0" disabled="true"
                                                     cssClass="form-control alloptions dimL"
                                                     onkeypress="return formartNumber(event,this,false);"
                                                     maxlength="4"/>
                                    </s:if>
                                    <s:else>
                                        <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionL"
                                                     cssClass="form-control alloptions dimL"
                                                     onkeypress="return formartNumber(event,this,false);"
                                                     maxlength="4"/>
                                    </s:else>
                                </s:if>
                                <s:else>
                                    <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionL"
                                                 cssClass="form-control alloptions dimL"
                                                 onkeypress="return formartNumber(event,this,false);" maxlength="4"
                                    />
                                </s:else>
                            </div>
                            <div class="col-lg-4 pd1">
                                <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">
                                    <s:if test="%{shipmentTypePackage.packageId != 3}">
                                        <s:textfield value="0" disabled="true"
                                                     cssClass="form-control alloptions dimW"
                                                     onkeypress="return formartNumber(event,this,false);"
                                                     maxlength="4"/>
                                    </s:if>
                                    <s:else>
                                        <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionW"
                                                     cssClass="form-control alloptions dimW"
                                                     onkeypress="return formartNumber(event,this,false);"
                                                     maxlength="4"/>
                                    </s:else>
                                </s:if>
                                <s:else>
                                    <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionW"
                                                 cssClass="form-control alloptions dimW"
                                                 onkeypress="return formartNumber(event,this,false);" maxlength="4"
                                    />
                                </s:else>
                            </div>
                            <div class="col-lg-4 pd1">
                                <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">
                                    <s:if test="%{shipmentTypePackage.packageId != 3}">
                                        <s:textfield value="0" disabled="true"
                                                     cssClass="form-control alloptions dimH"
                                                     onkeypress="return formartNumber(event,this,false);"
                                                     maxlength="4"/>
                                    </s:if>
                                    <s:else>
                                        <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionH"
                                                     cssClass="form-control alloptions dimH"
                                                     onkeypress="return formartNumber(event,this,false);"
                                                     maxlength="4"/>
                                    </s:else>
                                </s:if>
                                <s:else>
                                    <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].dimensionH"
                                                 cssClass="form-control alloptions dimH"
                                                 onkeypress="return formartNumber(event,this,false);" maxlength="4"
                                    />
                                </s:else>
                            </div>
                        </div>
                    </td>
                    <td width="25%" <s:if test="shipmentTypePackage.allowCustomValue == 0 || carrierType == 1"> style="display:none;" </s:if>>
                        <s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].customValue"
                                     placeholder="" class="form-control alloptions customValue" data-placement="top"
                                     data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "Shipment value", ENCODE_TYPE.JAVASCRIPT)%>'
                                     maxlength="10" onkeypress="return formartNumber(event,this,true);"/></td>
                    <s:if test="%{shipmentTypePackage.allowWpxAddpiece != 0}">
                        <td class="quantity"><s:textfield name="shipmentPage.pieces[%{#rowPiece.index}].quantity"
                                                          placeholder="" class="form-control quantity"
                                                          data-placement="top" data-toggle="tooltip"
                                                          data-original-title='<%=WebUtils.getTooltip(request, "Quantity", ENCODE_TYPE.JAVASCRIPT)%>'
                                                          onkeypress="return formartNumber(event,this,false);"/></td>
                    </s:if>
                    <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                        <td class="nonStandardPackage">
                            <input class="nonStandardPackage"
                                   name="shipmentPage.pieces[<s:property value='%{#rowPiece.index}'/>].nonStandardPackage"
                                   type="checkbox">
                        </td>
                    </s:if>
                    <td width="3%" style="padding-top: 9px"><i id="remove" class="fa fa-times-circle-o s10"
                                                               style="font-size: 18px; <s:if
                                                                       test="#rowPiece.index == 0"> display: none; </s:if>"
                                                               onclick='removePiece($(this))'></i></td>
                </tr>
            </s:iterator>
        </s:if>
        <s:else>
            <tr id="piece-dt1">
                <td width="2%" style="padding-top: 8px" id="piece-order1" class="order-number">1</td>
                <td width="10%"><s:textfield name="shipmentPage.pieces[0].weight" placeholder=""
                                             class="form-control alloptions weight" data-placement="top"
                                             data-toggle="tooltip"
                                             data-original-title='<%=WebUtils.getTooltip(request, "Shipment weight", ENCODE_TYPE.JAVASCRIPT)%>'
                                             maxlength="6" onkeypress="return formartNumber(event,this,true);"/></td>
                <td width="15%"><s:select list="dimensionList" listKey="id" listValue="name" headerKey="0"
                                          headerValue="" cssClass="form-control alloptions dimensionList"
                                          id="sel-dimension-list" onchange="changeDimensionList($(this).val(),0)"/></td>
                <td width="40%">
                    <div class="row mg0">
                        <div class="col-lg-4 pd1">
                            <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">
                                <s:if test="%{shipmentTypePackage.packageId != 3}">
                                    <s:textfield value="0" disabled="true"
                                                 cssClass="form-control alloptions dimL"
                                                 onkeypress="return formartNumber(event,this,false);"
                                                 maxlength="4"/>
                                </s:if>
                                <s:else>
                                    <s:textfield name="shipmentPage.pieces[0].dimensionL"
                                                 cssClass="form-control alloptions dimL"
                                                 onkeypress="return formartNumber(event,this,false);"
                                                 maxlength="4"/>
                                </s:else>
                            </s:if>
                            <s:else>
                                <s:textfield name="shipmentPage.pieces[0].dimensionL"
                                             cssClass="form-control alloptions dimL"
                                             onkeypress="return formartNumber(event,this,false);" maxlength="4"
                                />
                            </s:else>
                        </div>
                        <div class="col-lg-4 pd1">
                            <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">
                                <s:if test="%{shipmentTypePackage.packageId != 3}">
                                    <s:textfield value="0" disabled="true"
                                                 cssClass="form-control alloptions dimW"
                                                 onkeypress="return formartNumber(event,this,false);"
                                                 maxlength="4"/>
                                </s:if>
                                <s:else>
                                    <s:textfield name="shipmentPage.pieces[0].dimensionW"
                                                 cssClass="form-control alloptions dimW"
                                                 onkeypress="return formartNumber(event,this,false);"
                                                 maxlength="4"/>
                                </s:else>
                            </s:if>
                            <s:else>
                                <s:textfield name="shipmentPage.pieces[0].dimensionW"
                                             cssClass="form-control alloptions dimW"
                                             onkeypress="return formartNumber(event,this,false);" maxlength="4"
                                />
                            </s:else>
                        </div>
                        <div class="col-lg-4 pd1">
                            <s:if test="%{shipmentTypePackage.allowWpxAddpiece == 0}">
                                <s:if test="%{shipmentTypePackage.packageId != 3}">
                                    <s:textfield value="0" disabled="true"
                                                 cssClass="form-control alloptions dimH"
                                                 onkeypress="return formartNumber(event,this,false);"
                                                 maxlength="4"/>
                                </s:if>
                                <s:else>
                                    <s:textfield name="shipmentPage.pieces[0].dimensionH"
                                                 cssClass="form-control alloptions dimH"
                                                 onkeypress="return formartNumber(event,this,false);"
                                                 maxlength="4"/>
                                </s:else>
                            </s:if>
                            <s:else>
                                <s:textfield name="shipmentPage.pieces[0].dimensionH"
                                             cssClass="form-control alloptions dimH"
                                             onkeypress="return formartNumber(event,this,false);" maxlength="4"
                                />
                            </s:else>
                        </div>
                    </div>
                </td>
                <td width="25%" <s:if
                        test="shipmentTypePackage.allowCustomValue == 0 || carrierType == 1"> style="display:none;" </s:if>>
                    <s:textfield name="shipmentPage.pieces[0].customValue" value="0.00" placeholder=""
                                 class="form-control alloptions customValue" data-placement="top" data-toggle="tooltip"
                                 data-original-title='<%=WebUtils.getTooltip(request, "Shipment value", ENCODE_TYPE.JAVASCRIPT)%>'
                                 maxlength="10" onkeypress="return formartNumber(event,this,true);"/></td>
                <s:if test="%{shipmentTypePackage.allowWpxAddpiece != 0}">
                    <td class="quantity"><s:textfield name="shipmentPage.pieces[0].quantity" value="1" placeholder=""
                                                      class="form-control quantity" data-placement="top"
                                                      data-toggle="tooltip"
                                                      data-original-title='<%=WebUtils.getTooltip(request, "Quantity", ENCODE_TYPE.JAVASCRIPT)%>'
                                                      onkeypress="return formartNumber(event,this,false);"/></td>
                </s:if>
                <s:if test="%{shipmentPage.serviceId == 3 || shipmentPage.serviceId == 400}">
                    <td class="nonStandardPackage">
                        <input class="nonStandardPackage" name="shipmentPage.pieces[0].nonStandardPackage"
                               type="checkbox">
                    </td>
                </s:if>
                <td width="3%" style="padding-top: 9px"><i id="remove" class="fa fa-times-circle-o s10"
                                                           style="font-size: 18px; <s:if
                                                                   test="#rowPiece.index == 0"> display: none; </s:if>"
                                                           onclick='removePiece($(this))'></i></td>
            </tr>
        </s:else>
        <s:if test="%{shipmentTypePackage.allowWpxAddpiece != 0}">
            <s:hidden name="shipmentPage.isAddPiece" value="true"/>
        </s:if>
        <s:else>
            <s:hidden name="shipmentPage.isAddPiece" value="false"/>
        </s:else>
        </s:else>
        </tbody>
    </table>
    <s:if test="shipmentTypePackage.defaultContentType == 'DOX'">
        <s:if test="shipmentTypePackage.allowDoxAddpiece != 0">
            <button class="btn s33" type="button" onclick="addPiece()">
                <xms:localization text="Add Piece"/>
            </button>
        </s:if>
    </s:if>
    <s:else>
        <s:if test="shipmentTypePackage.allowWpxAddpiece != 0">
            <button class="btn s33" type="button" onclick="addPiece()">
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
        $(document).ready(function () {
            $('input.alloptions').maxlength({
                alwaysShow: true,
                threshold: 10,
                warningClass: "label label-success w5",
                limitReachedClass: "label label-danger w5",
                separator: '/',

            });
        });
    </script>
</div>
