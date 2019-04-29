<%@page import="com.gms.xms.common.constants.AppConstants.ENCODE_TYPE" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
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
                        <label class="control-label" for="inputName"> <xms:localization text="Company"/> <span
                                class="s30"> *</span>
                        </label>
                        <s:textfield name="shipmentPage.receiverAddress.companyName" value="%{address.companyName}"
                                     class="form-control alloptions" data-placement="top" data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "Company", ENCODE_TYPE.JAVASCRIPT)%>'
                                     maxlength="35" onkeyup="searchReceiverAddress(true)"
                                     ondblclick="searchReceiverAddress(true)"/>
                        <div id="receiver-address-by-company-search-result"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label" for="inputName"> <xms:localization text="Phone"/> <span
                                class="s30"> *</span>
                        </label>
                        <s:textfield name="shipmentPage.receiverAddress.phone" value="%{address.phone}"
                                     class="form-control alloptions" data-placement="top" data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "Phone", ENCODE_TYPE.JAVASCRIPT)%>'
                                     maxlength="25"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label" for="inputName"> <xms:localization text="Contact Name"/> <span
                                class="s30"> *</span>
                        </label>
                        <s:textfield name="shipmentPage.receiverAddress.contactName" value="%{address.contactName}"
                                     class="form-control alloptions" data-placement="top" data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "Contact Name", ENCODE_TYPE.JAVASCRIPT)%>'
                                     maxlength="35" onkeyup="searchReceiverAddress(false)"
                                     ondblclick="searchReceiverAddress(false)"/>
                        <div id="receiver-address-by-contact-search-result"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label" for="inputName"> <xms:localization text="Email Address"/>
                        </label>
                        <s:textfield name="shipmentPage.receiverAddress.email" value="%{address.email}"
                                     class="form-control alloptions" data-placement="top" data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "Email", ENCODE_TYPE.JAVASCRIPT)%>'
                                     maxlength="50"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="control-label" for="inputName"> <xms:localization text="Country"/> <span
                                class="s30"> *</span>
                        </label>
                        <s:select name="shipmentPage.receiverAddress.country" value="%{address.country}"
                                  id="shipmentPage_receiverAddress_country" list="countryList" listKey="countryId"
                                  listValue="countryName" cssClass="form-control" onchange="changeCountry('receiver')"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label" for="inputName"> <xms:localization text="Address"/> <span
                                class="s30"> *</span>
                        </label>
                        <s:textfield name="shipmentPage.receiverAddress.address" value="%{address.address1}"
                                     class="form-control alloptions" data-placement="top" data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "Address 1", ENCODE_TYPE.JAVASCRIPT)%>'
                                     maxlength="35"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label" for="inputName"> <xms:localization text="Address 2"/>
                        </label>
                        <s:textfield name="shipmentPage.receiverAddress.address2" value="%{address.address2}"
                                     class="form-control alloptions" data-placement="top" data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "Address 2", ENCODE_TYPE.JAVASCRIPT)%>'
                                     maxlength="35"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="fw0"> <input name="shipmentPage.isSaveRecipientAddressBook" value="0" tabindex="5"
                                                   type="checkbox"
                                                   onclick="checkSavaAddressBook('shipmentPage.isSaveRecipientAddressBook')"/>
                            &nbsp; <xms:localization text="Save to Address Book"/>
                        </label>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="fw0"> <input tabindex="5" type="checkbox" value="true"
                                                   name="shipmentPage.residentialDelivery"
                                                   <s:if test="shipmentPage.receiverAddress.residential.equals('1')">checked="checked"</s:if> />
                            &nbsp; <xms:localization text="Residence"/>
                        </label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="control-label" for="inputName"> <xms:localization text="City"/> <span class="s30"> *</span>
                        </label>
                        <s:textfield name="shipmentPage.receiverAddress.city" value="%{address.city}"
                                     class="form-control alloptions" data-placement="top" data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "City", ENCODE_TYPE.JAVASCRIPT)%>'
                                     maxlength="35" onkeyup="searchCity(false,true)"/>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="control-label" for="inputName"> <xms:localization text="Postal Code"/>
                        </label>
                        <s:textfield name="shipmentPage.receiverAddress.postalCode" value="%{address.postalCode}"
                                     class="form-control alloptions" data-placement="top" data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "Postal Code", ENCODE_TYPE.JAVASCRIPT)%>'
                                     maxlength="12" onkeyup="searchCity(false,false)"/>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="control-label" for="inputName"> <xms:localization text="State/Province"/>
                        </label>
                        <s:textfield name="shipmentPage.receiverAddress.state" value="%{address.state}"
                                     class="form-control" data-placement="top" data-toggle="tooltip"
                                     data-original-title='<%=WebUtils.getTooltip(request, "Sate/Province", ENCODE_TYPE.JAVASCRIPT)%>'/>
                    </div>
                </div>
                <div class="col-md-12" id="receiver-city-search"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var countryId = <s:property value="address.country" />;
    $(document).ready(function () {
        $("#shipmentPage_receiverAddress_country option[value=" + countryId + "]").attr("selected", "selected");
        changeCountry('receiver');
    });


</script>