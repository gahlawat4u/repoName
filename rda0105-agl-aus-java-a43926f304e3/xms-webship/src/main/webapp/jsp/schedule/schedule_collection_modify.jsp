<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div id="modify_schedule_collection_page">
    <!--BEGIN CONTENT-->
    <s:if test="hasActionErrors()">
        <s:actionerror/>
    </s:if>
    <s:else>
        <s:fielderror/>
        <s:form id="frmModifySchedule" name="frmModifySchedule" method="post" data-toggle="validator">
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
                                                <xms:localization text="Enter schedule and package information..."/>
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
                                                                <s:hidden name="schedule.scheduleCollectionId"/>
                                                                <h6>
                                                                    <u><i><b><xms:localization
                                                                            text="Schedule Information"/></b></i></u>
                                                                </h6>
                                                                <label class="control-label" for="schedule.pickupDate">
                                                                    <xms:localization text="Pickup date"/> <span
                                                                        class="s30"> *</span>
                                                                </label>

                                                                <div class="form-group input-group">
																	<span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i>
																	</span> <input class="form-control form_datetime"
                                                                                   readonly="readonly"
                                                                                   id="schedule.pickupDate"
                                                                                   name="schedule.pickupDate"
                                                                                   value="${schedule.pickupDate}"
                                                                                   type="text"
                                                                                   data-date-format="dd MM yyyy"/>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label" for="schedule.pickupTime">
                                                                    <xms:localization text="Package Pickup Time"/> <span
                                                                        class="s30"> *</span>
                                                                </label>
                                                                <s:select id="schedule.pickupTime"
                                                                          name="schedule.pickupTime" list="readyTimes"
                                                                          listKey="timeValue" listValue="timeDisplay"
                                                                          headerKey="" headerValue="Select One"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label"
                                                                       for="schedule.pickupTimeNoLater">
                                                                    <xms:localization
                                                                            text="Pickup Package no later than"/> <span
                                                                        class="s30"> *</span>
                                                                </label>
                                                                <s:select id="schedule.pickupTimeNoLater"
                                                                          name="schedule.pickupTimeNoLater"
                                                                          list="closeTimes" listKey="timeValue"
                                                                          listValue="timeDisplay" headerKey=""
                                                                          headerValue="Select One"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <h6>
                                                                    <u><i><b><xms:localization
                                                                            text="Package Information"/></b></i></u>
                                                                </h6>
                                                                <label class="control-label" for="schedule.noOfPieces">
                                                                    <xms:localization text="Number of pieces"/> <span
                                                                        class="s30"> *</span>
                                                                </label> <input type="text" placeholder=""
                                                                                id="schedule.noOfPieces"
                                                                                name="schedule.noOfPieces"
                                                                                value="${schedule.noOfPieces}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="6"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label" for="schedule.totalWeight">
                                                                    <xms:localization text="Total estimated weight"/>
                                                                    <span class="s30"> *</span>
                                                                </label> <input type="text" placeholder=""
                                                                                id="schedule.totalWeight"
                                                                                name="schedule.totalWeight"
                                                                                value="${schedule.totalWeight}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="6"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label"
                                                                       for="schedule.scheduleWeightUnit">
                                                                    <xms:localization text="Weight Unit"/> <span
                                                                        class="s30"> *</span>
                                                                </label>
                                                                <s:select id="schedule.scheduleWeightUnit"
                                                                          name="schedule.scheduleWeightUnit"
                                                                          list="weightUnits"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label"
                                                                       for="schedule.specialInstructions">
                                                                    <xms:localization text="Special Instruction"/>
                                                                </label>
                                                                <textarea class="form-control"
                                                                          placeholder="Additional info"
                                                                          id="schedule.specialInstructions"
                                                                          name="schedule.specialInstructions"
                                                                          rows="3">${schedule.specialInstructions}</textarea>
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
                                                            <s:hidden name="pickupAddress.country"/>
                                                            <div class="form-group">
                                                                <h6>
                                                                    <u><i><b><xms:localization
                                                                            text="Contact Information"/></b></i></u>
                                                                </h6>
                                                                <label class="control-label"
                                                                       for="pickupAddress.contactName">
                                                                    <xms:localization text="Contact name"/> <span
                                                                        class="s30"> *</span>
                                                                </label>
                                                                <s:hidden name="pickupAddress.addressId"/>
                                                                <input type="text" placeholder=""
                                                                       id="pickupAddress.contactName"
                                                                       name="pickupAddress.contactName"
                                                                       value="${pickupAddress.contactName}"
                                                                       class="form-control alloptions"
                                                                       data-placement="top" maxlength="25"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label"
                                                                       for="pickupAddress.companyName">
                                                                    <xms:localization text="Company name"/> <span
                                                                        class="s30"> *</span>
                                                                </label> <input type="text" placeholder=""
                                                                                id="pickupAddress.companyName"
                                                                                name="pickupAddress.companyName"
                                                                                value="${pickupAddress.companyName}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="25"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label" for="pickupAddress.phone">
                                                                    <xms:localization text="Telephone"/> <span
                                                                        class="s30"> *</span>
                                                                </label> <input type="text" placeholder=""
                                                                                id="pickupAddress.phone"
                                                                                name="pickupAddress.phone"
                                                                                value="${pickupAddress.phone}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="25"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <h6>
                                                                    <u><i><b><xms:localization
                                                                            text="Location Information"/></b></i></u>
                                                                </h6>
                                                                <label class="control-label" for="schedule.description">
                                                                    <xms:localization text="Location Description"/>
                                                                    <span class="s30"> *</span>
                                                                </label> <input type="text" placeholder=""
                                                                                id="schedule.description"
                                                                                name="schedule.description"
                                                                                value="${schedule.description}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="25"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label" for="inputName">
                                                                    <xms:localization text="Location Code"/>
                                                                </label>
                                                                <s:select id="schedule.locationCodeId"
                                                                          name="schedule.locationCodeId"
                                                                          list="locationCodes" listKey="locationCodeId"
                                                                          listValue="locationCodeDescription"
                                                                          cssClass="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6">
                                                            <div class="form-group">
                                                                <h6>
                                                                    <u><i><b>Address Information</b></i></u>
                                                                </h6>
                                                                <label class="control-label"
                                                                       for="pickupAddress.address"> <xms:localization
                                                                        text="Address line"/> 1<span
                                                                        class="s30"> *</span>
                                                                </label> <input type="text" placeholder=""
                                                                                name="pickupAddress.address"
                                                                                id="pickupAddress.address"
                                                                                value="${pickupAddress.address}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="25"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label"
                                                                       for="pickupAddress.address2"> <xms:localization
                                                                        text="Address line"/> 2
                                                                </label> <input type="text" placeholder=""
                                                                                id="pickupAddress.address2"
                                                                                name="pickupAddress.address2"
                                                                                value="${pickupAddress.address2}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="25"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label" for="pickupAddress.city">
                                                                    City<span class="s30"> *</span>
                                                                </label> <input type="text" placeholder=""
                                                                                id="pickupAddress.city"
                                                                                name="pickupAddress.city"
                                                                                value="${pickupAddress.city}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="25"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label" for="pickupAddress.state">
                                                                    <xms:localization text="State/Province"/>
                                                                </label> <input type="text" placeholder=""
                                                                                id="pickupAddress.state"
                                                                                name="pickupAddress.state"
                                                                                value="${pickupAddress.state}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="25"/>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label"
                                                                       for="pickupAddress.postalCode"> <xms:localization
                                                                        text="Postal code"/>
                                                                </label> <input type="text" placeholder=""
                                                                                id="pickupAddress.postalCode"
                                                                                name="pickupAddress.postalCode"
                                                                                value="${pickupAddress.postalCode}"
                                                                                class="form-control alloptions"
                                                                                data-placement="top" maxlength="25"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-actions text-right pal pdt10 ">
                                                <button type="button" onclick="onSubmit()" class="btn s33">
                                                    <xms:localization text="Submit"/>
                                                </button>
                                                <button type="button" class="btn s33" data-dismiss="modal"
                                                        onclick="javascript:window.close();">
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
    </s:else>
    <div id="modify-collection-dialog"></div>
    <script type="text/javascript">
        function onSubmit() {
            var data = $("#frmModifySchedule").serialize();
            var buttons = {};
            buttons["<xms:localization text="Close"/>"] = function () {
                window.close();
            }

            var dialog = $("#modify-collection-dialog").dialog({
                modal: true,
                autoOpen: false,
                width: "50%",
                buttons: buttons,
                show: {
                    effect: "fade",
                    duration: 300
                }
            });
            loadingDialog.dialog("open");
            $.post("schedule_collection_do_modify.ix?reqType=json", data, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    dialog.html("The schedule collection was updated successfull.");
                    dialog.dialog("open");
                } else if (res.errorCode == "FIELD_ERROR") {
                    console.log("Invalidate data.");
                    $("#modify_schedule_collection_page").replaceWith(res.content);
                } else {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                alertDialog.dialog("open");
            });
        }
    </script>
</div>