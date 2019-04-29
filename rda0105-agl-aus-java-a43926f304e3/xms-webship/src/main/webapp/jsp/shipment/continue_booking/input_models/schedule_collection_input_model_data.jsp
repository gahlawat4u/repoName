<%@page import="com.gms.xms.common.constants.AppConstants.ENCODE_TYPE" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:if test="shipmentRequestModel != null">
    <div class="col-lg-5">
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="Company"/> <span class="s30">*</span>
            </label>
            <s:textfield value="%{shipmentRequestModel.shipmentInfo.senderAddress.companyName}"
                         name="shipmentRequestModel.scheduleCollection.saddress.companyName"
                         cssClass="form-control alloptions" data-placement="top" data-toggle="tooltip"
                         data-original-title='<%=WebUtils.getTooltip(request, "Company name", ENCODE_TYPE.JAVASCRIPT)%>'
                         maxlength="25"></s:textfield>
        </div>
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="Contact Name"/> <span
                    class="s30">*</span>
            </label>
            <s:textfield value="%{shipmentRequestModel.shipmentInfo.senderAddress.contactName}"
                         name="shipmentRequestModel.scheduleCollection.saddress.contactName"
                         cssClass="form-control alloptions" data-placement="top" data-toggle="tooltip"
                         data-original-title='<%=WebUtils.getTooltip(request, "Contact name", ENCODE_TYPE.JAVASCRIPT)%>'
                         maxlength="25"></s:textfield>
        </div>
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="Address"/> <span class="s30"> *</span>
            </label>
            <s:textfield value="%{shipmentRequestModel.shipmentInfo.senderAddress.address}"
                         name="shipmentRequestModel.scheduleCollection.saddress.address"
                         cssClass="form-control alloptions" data-placement="top" data-toggle="tooltip"
                         data-original-title='<%=WebUtils.getTooltip(request, "Address line 1", ENCODE_TYPE.JAVASCRIPT)%>'
                         maxlength="25"></s:textfield>
        </div>
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="Address 2"/>
            </label>
            <s:textfield value="%{shipmentRequestModel.shipmentInfo.senderAddress.address2}"
                         name="shipmentRequestModel.scheduleCollection.saddress.address2"
                         cssClass="form-control alloptions" data-placement="top" data-toggle="tooltip"
                         data-original-title='<%=WebUtils.getTooltip(request, "Address line 2", ENCODE_TYPE.JAVASCRIPT)%>'
                         maxlength="25"></s:textfield>
        </div>
            <%-- <div class="form-group">
                <label class="control-label" for="inputName"> <xms:localization
                        text="Address 3" />
                </label>
                <s:textfield
                    value="shipmentRequestModel.shipmentInfo.receiverAddress.address3"
                    name="shipmentRequestModel.scheduleCollection"
                    cssClass="form-control alloptions" data-placement="top"
                    data-toggle="tooltip" data-original-title="Tooltip on"
                    maxlength="25"></s:textfield>
            </div> --%>
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="City"/> <span class="s30">*</span>
            </label>
            <s:textfield value="%{shipmentRequestModel.shipmentInfo.senderAddress.city}"
                         name="shipmentRequestModel.scheduleCollection.saddress.city" cssClass="form-control alloptions"
                         data-placement="top" data-toggle="tooltip"
                         data-original-title='<%=WebUtils.getTooltip(request, "City", ENCODE_TYPE.JAVASCRIPT)%>'
                         maxlength="25"></s:textfield>
        </div>
    </div>
    <div class="col-lg-7">
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="Phone"/><span class="s30"> *</span>
            </label>
            <s:textfield value="%{shipmentRequestModel.shipmentInfo.senderAddress.phone}"
                         name="shipmentRequestModel.scheduleCollection.saddress.phone"
                         cssClass="form-control alloptions" data-placement="top" data-toggle="tooltip"
                         data-original-title='<%=WebUtils.getTooltip(request, "Phone", ENCODE_TYPE.JAVASCRIPT)%>'
                         maxlength="25"></s:textfield>
        </div>
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="Postal Code"/>
            </label>
            <s:textfield value="%{shipmentRequestModel.shipmentInfo.senderAddress.postalCode}"
                         name="shipmentRequestModel.scheduleCollection.saddress.postalCode"
                         cssClass="form-control alloptions" data-placement="top" data-toggle="tooltip"
                         data-original-title='<%=WebUtils.getTooltip(request, "Postal code", ENCODE_TYPE.JAVASCRIPT)%>'
                         maxlength="25"></s:textfield>
        </div>
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="State/Province"/>
            </label>
            <s:textfield value="%{shipmentRequestModel.shipmentInfo.senderAddress.state}"
                         name="shipmentRequestModel.scheduleCollection.saddress.state"
                         cssClass="form-control alloptions" data-placement="top" data-toggle="tooltip"
                         data-original-title='<%=WebUtils.getTooltip(request, "State/Province", ENCODE_TYPE.JAVASCRIPT)%>'
                         maxlength="25"></s:textfield>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <label class="control-label" for="inputName"> <xms:localization text="Ready Time (DD-MM-YYYY)"/>
                </label>

                <div class="form-group input-group">
					<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
					</span>
                    <s:textfield class="form-control form_datetime schedule-time" type="text"
                                 data-date-format="dd MM yyyy" readonly="true"
                                 name="shipmentRequestModel.scheduleCollection.pickupDate"/>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="form-group">
                    <s:select list="pickupTimeList" cssClass="form-control"
                              name="shipmentRequestModel.scheduleCollection.pickupTime" id="sel-pickup-time"/>
                </div>
            </div>
        </div>
        <!--  <hr style="margin-top: 0px;">-->
        <div class="row">
            <div class="col-lg-6">
                <div class="form-group">
                    <label class="control-label" for="inputName"> <xms:localization text="Close Time"/>
                    </label>
                    <s:select list="pickupTimeList" cssClass="form-control"
                              name="shipmentRequestModel.scheduleCollection.pickupTimeNoLater"/>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="form-group">
                    <label class="control-label" for="inputName"> <xms:localization text="Pickup Location"/>
                    </label> <select class="form-control">
                    <option value="1"><xms:localization text="Front"/></option>
                    <option value="2"><xms:localization text="Rear"/></option>
                    <option value="3"><xms:localization text="Side"/></option>
                </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="Location Code"/>
            </label>
            <s:select list="locationCodeList" cssClass="form-control" listKey="locationCodeId"
                      listValue="locationCodeDescription"
                      name="shipmentRequestModel.scheduleCollection.locationCodeId"/>
        </div>
        <div class="form-group">
            <label class="control-label" for="inputName"> <xms:localization text="Location Description"/> <span
                    class="s30"> *</span>
            </label>
            <s:textfield value="Front Desk" name="shipmentRequestModel.scheduleCollection.description"
                         cssClass="form-control alloptions" data-placement="top" data-toggle="tooltip"
                         data-original-title='<%=WebUtils.getTooltip(request, "Location description", ENCODE_TYPE.JAVASCRIPT)%>'
                         maxlength="25"></s:textfield>
        </div>
    </div>
</s:if>
<script type="text/javascript">
    $(document).ready(function () {
        $('.schedule-time').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: 'dd-mm-yyyy'
            /* format: 'dd-mm-yyyy, HH:ii p' */
        });
        $(".schedule-time").datetimepicker("update", $("#shipment-date-input").val());
        setdefaulttime();
    });
</script>