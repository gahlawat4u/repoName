<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>


<!--BEGIN CONTENT-->
<s:form name="scheduleCollection" method="post" data-toggle="validator" id="formScheduleCollection">
    <div class="page-content">
        <div id="tab-general">
            <div class="row mbl">
                <div class="col-lg-12">
                    <div class="col-md-12">
                        <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="row" id="div-schedule-collection">
                        <div class="col-lg-12">
                            <div class="portlet box">
                                <div class="portlet-header">
                                    <div class="caption">
                                        <xms:localization text="Enter schedule and package information"/>
                                        ...
                                    </div>
                                    <div class="tools">
                                        <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                    </div>

                                </div>
                                <div class="portlet-body">
                                    <div class="panel-body pan">

                                        <s:hidden value="%{detailInfoModel.schId}" name="detailInfoModel.schId"
                                                  id="scheduleCollectionId"></s:hidden>
                                        <div class="form-body pal">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <h6>
                                                            <u><i><b><xms:localization text="Schedule Information"/></b></i></u>
                                                        </h6>
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Pickup date"/>
                                                            <span class="s30"> *</span>
                                                        </label>

                                                        <div class="form-group input-group">
															<span class="input-group-addon s31"> <i
                                                                    class="fa fa-calendar"></i>
															</span> <input class="form-control form_datetime"
                                                                           readonly="readonly"
                                                                           name="scheduleCollectionModel.pickupDate"
                                                                           value="${detailInfoModel.pickupDate}"
                                                                           id="pickupDate" type="text"
                                                                           data-date-format="dd MM yyyy"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Package Pickup Time"/>
                                                            <span class="s30"> *</span>
                                                        </label>
                                                        <select class="form-control"
                                                                name="scheduleCollectionModel.pickupTime"
                                                                id="pickupTime"> ${pickupTimeString}
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Pickup Package no later than"/>
                                                            <span class="s30"> *</span>
                                                        </label>
                                                        <select class="form-control"
                                                                name="scheduleCollectionModel.pickupTimeNoLater"
                                                                id="pickupTimeNoLater"> ${pickupTimeNoLate}
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <h6>
                                                            <s:hidden value="%{shipmentId}" id="shipmentId"
                                                                      name="shipmentId"/>
                                                            <s:hidden value="%{shipmentId}"
                                                                      name="scheduleCollectionModel.shipmentId"/>
                                                            <s:hidden value="%{detailInfoModel.schId}"
                                                                      name="scheduleCollectionModel.scheduleCollectionId"/>
                                                            <u><i><b><xms:localization text="Package Information"/></b></i></u>
                                                        </h6>
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Number of pieces"/>
                                                            <span class="s30"> *</span>
                                                        </label>
                                                        <input type="text" placeholder=""
                                                               name="scheduleCollectionModel.noOfPieces"
                                                               value="${detailInfoModel.noOfPieces}" id="noOfPieces"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="6"/>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Total estimated weight"/>
                                                            <span class="s30"> *</span>
                                                        </label>
                                                        <input type="text" placeholder=""
                                                               name="scheduleCollectionModel.totalWeight"
                                                               id="txtTotalWeight"
                                                               value="${detailInfoModel.actualWeight}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="6"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Weight Unit"/>
                                                            <span class="s30"> *</span>
                                                        </label>
                                                        <s:select list="weightUnit"
                                                                  name="scheduleCollectionModel.scheduleWeightUnit"
                                                                  value="detailInfoModel.weightUnit"></s:select>

                                                        <s:hidden value="%{detailInfoModel.weightUnit}"
                                                                  id="hidWeightUnit"></s:hidden>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Special Instruction"/>
                                                        </label>
                                                        <textarea class="form-control" placeholder="Additional info"
                                                                  name="scheduleCollectionModel.specialInstructions"
                                                                  id="specialInstructions"
                                                                  rows="3">${detailInfoModel.specialInstructions}</textarea>
                                                    </div>
                                                </div>
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
                                        <xms:localization text="Enter pickup address and contact information"/>
                                        ...
                                    </div>
                                    <div class="tools">
                                        <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="panel-body pan">
                                        <div class="form-body pal">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <h6>
                                                            <u><i><b><xms:localization text="Contact Information"/></b></i></u>
                                                        </h6>
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Contact name"/>
                                                            <span class="s30"> *</span>
                                                        </label>
                                                        <input type="text" placeholder="" id="contactName"
                                                               name="addressModel.contactName"
                                                               value="${detailInfoModel.sContactName}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="25"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Company name"/>
                                                            <span class="s30"> *</span>
                                                        </label>
                                                        <s:hidden value='%{sAddressId}' id="addressId"></s:hidden>
                                                        <s:hidden value='%{sAddressId}'
                                                                  name="scheduleCollectionModel.addressId"></s:hidden>
                                                        <input type="text" placeholder="" id="companyName"
                                                               name="addressModel.companyName"
                                                               value="${detailInfoModel.sCompanyName}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="25"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Telephone"/>
                                                            <span class="s30"> *</span>
                                                        </label>
                                                        <input type="text" placeholder="" id="phone"
                                                               name="addressModel.phone"
                                                               value="${detailInfoModel.sPhone}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="25"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <h6>
                                                            <u><i><b><xms:localization text="Location Information"/></b></i></u>
                                                        </h6>
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Location Description"/>
                                                            <span class="s30"> *</span>
                                                        </label>
                                                        <input type="text" placeholder="" id="locationDesc"
                                                               name="scheduleCollectionModel.description"
                                                               value="${detailInfoModel.scDescription}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="25"/>
                                                    </div>
                                                    <s:property value="detailInfoModel"/>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Location Code"/>
                                                        </label>
                                                        <s:set name="locationCodeId"
                                                               value="%{scheduleCollectionModel.locationCodeId}"/>
                                                        <select class="form-control"
                                                                name="scheduleCollectionModel.locationCodeId"
                                                                id="locationCodeId"
                                                                value="${detailInfoModel.locationCodeId}">
                                                            <option value="1"
                                                                    <s:if test="%{#locationCodeId==1}">selected</s:if>>
                                                                Business
                                                            </option>
                                                            <option value="2"
                                                                    <s:if test="%{#locationCodeId==2}">selected</s:if>>
                                                                Residence
                                                            </option>
                                                            <option value="3"
                                                                    <s:if test="%{#locationCodeId==3}">selected</s:if>>
                                                                Business/Residence
                                                            </option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <h6>
                                                            <u><i><b>Address Information</b></i></u>
                                                        </h6>
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Address line"/>
                                                            1<span class="s30"> *</span>
                                                        </label>
                                                        <input type="text" placeholder="" name="addressModel.address"
                                                               id="address" value="${detailInfoModel.sAddress}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="25"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Address line"/>
                                                            2
                                                        </label>
                                                        <input type="text" placeholder="" id="address2"
                                                               name="addressModel.address2"
                                                               value="${detailInfoModel.sAddress2}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="25"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            City<span class="s30"> *</span>
                                                        </label>
                                                        <input type="text" placeholder="" id="city"
                                                               name="addressModel.city" value="${detailInfoModel.sCity}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="25"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="State/Province"/>
                                                        </label>
                                                        <input type="text" placeholder="" id="state"
                                                               name="addressModel.state"
                                                               value="${detailInfoModel.sState}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="25"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label" for="inputName">
                                                            <xms:localization text="Postal code"/>
                                                        </label>
                                                        <input type="text" placeholder="" id="postal_code"
                                                               name="addressModel.postalCode"
                                                               value="${detailInfoModel.sPostalCode}"
                                                               class="form-control alloptions" data-placement="top"
                                                               maxlength="25"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="form-actions text-right pal pdt10 ">
                                        <div class="remember_me" id="error_schedule"></div>
                                        <br/>
                                        <button type="button" onclick="javascript:scheduleSubmit();" class="btn s33">
                                            <xms:localization text="Submit"/>
                                        </button>
                                        <button type="button" class="btn s33" data-dismiss="modal">
                                            <xms:localization text="Close"/>
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
</s:form>
<!--END CONTENT-->
<script type="text/javascript">
    function scheduleSubmit() {
        doPostDataUpdate("history_schedule_collection.ix?reqType=json", "formScheduleCollection", "Success", "div-schedule-collection", "error_schedule");

    }
</script>