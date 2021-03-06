<%@page import="com.gms.xms.common.constants.AppConstants.ENCODE_TYPE" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="row mbl">
    <div class="col-lg-12">
        <div class="col-md-12">
            <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
        </div>
    </div>
    <div class="col-lg-12">
        <div class="row">
            <div class="col-lg-12">
                <div class="portlet box">
                    <div class="portlet-header">
                        <div class="caption">
                            <xms:localization text="Shipment"/>
                        </div>
                        <div class="tools">
                            <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="panel-body pan">
                            <div class="form-body pal">
                                <div class="row">
                                    <div class="col-md-4">
                                        <table class="table s99" style="font-size: 13px; margin-bottom: 0px">
                                            <tr>
                                                <td class="td1"><xms:localization text="Carrier Name"/></td>
                                                <td class="td2"><s:property
                                                        value="shipmentType.service.serviceName"/></td>
                                            </tr>
                                            <tr>
                                                <td class="td1"><xms:localization text="Service Type"/></td>
                                                <td class="td2"><s:property value="shipmentType.shipmentTypeName"/></td>
                                            </tr>
                                            <tr>
                                                <td class="td1"><xms:localization text="Package Type"/></td>
                                                <td class="td2"><s:property value="packageType.packageName"/></td>
                                            </tr>
                                        </table>
                                    </div>

                                    <div class="col-md-4">
                                        <table class="table s99" style="font-size: 13px;">
                                            <tr>
                                                <td class="td1"><xms:localization text="Weight"/></td>
                                                <td class="td2"><s:property
                                                        value="shipmentRequestModel.quote.weight"/></td>
                                            </tr>
                                            <tr>
                                                <td class="td1"><xms:localization text="Dimensions"/></td>
                                                <td class="td2"></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div class="col-md-4">
                                        <table class="table s99" style="font-size: 13px;">
                                            <tr>
                                                <td class="td1"><xms:localization text="Quote"/></td>
                                                <td class="td2"><s:property
                                                        value="shipmentRequestModel.quote.totalCharge"/> <br/> <i><u>
                                                    <xms:localization
                                                            text="(Quote is an estimate. Additional fees may apply.)"/>
                                                </u></i></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div class="col-md-12 pd0">
                                        <div class="col-md-12">
                                            <h4 class="s34">
                                                <u><a href="" class="clicks"><xms:localization
                                                        text="More Details:"/></a></u>
                                            </h4>
                                        </div>
                                        <div class="col-md-6 sd1">
                                            <table class="table s99" style="font-size: 13px;">
                                                <tbody>
                                                <tr>
                                                    <td class="td1 s39"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Quote Details:"/></td>
                                                    <td class="td2"></td>
                                                </tr>
                                                <tr>
                                                    <td class="td1 s40 ncl"><xms:localization
                                                            text="- Base Charge"/></td>
                                                    <td class="td2"><s:property
                                                            value="shipmentRequestModel.quote.baseCharge"/></td>
                                                </tr>
                                                <s:iterator value="shipmentRequestModel.quote.accessorial">
                                                    <tr>
                                                        <td class="td1 s40 ncl">- <s:property value="description"/></td>
                                                        <td class="td2"><s:property value="value"/></td>
                                                    </tr>
                                                </s:iterator>
                                                <tr>
                                                    <td class="td1 s41 ncl"><xms:localization
                                                            text="- Total Charge"/></td>
                                                    <td class="td2"><s:property
                                                            value="shipmentRequestModel.quote.totalCharge"/></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-md-6 sd1">
                                            <table class="table s99" style="font-size: 13px;">
                                                <tbody>
                                                <tr>
                                                    <td class="td1"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Sender Address:"/></td>
                                                    <td class="td2"><s:property
                                                            value="shipmentPage.senderAddress.companyName"/> <s:property
                                                            value="shipmentPage.senderAddress.contactName"/> <s:property
                                                            value="shipmentPage.senderAddress.address"/> <s:property
                                                            value="shipmentPage.senderAddress.address2"/> <s:property
                                                            value="shipmentPage.senderAddress.countryDetail.countryName"/>
                                                        <s:property value="shipmentPage.senderAddress.postalCode"/>
                                                        <s:property value="shipmentPage.senderAddress.city"/></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <table class="table s99" style="font-size: 13px; margin-bottom: 0px">
                                                <tr>
                                                    <td class="td1"
                                                        style="font-style: italic; text-decoration: underline;">
                                                        <xms:localization text="Receiver Address:"/></td>
                                                    <td class="td2"><s:property
                                                            value="shipmentPage.receiverAddress.companyName"/>
                                                        <s:property value="shipmentPage.receiverAddress.contactName"/>
                                                        <s:property value="shipmentPage.receiverAddress.address"/>
                                                        <s:property value="shipmentPage.receiverAddress.address2"/>
                                                        <s:property
                                                                value="shipmentPage.receiverAddress.countryDetail.countryName"/>
                                                        <s:property value="shipmentPage.receiverAddress.postalCode"/>
                                                        <s:property value="shipmentPage.receiverAddress.city"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-5">
                <div class="portlet box">
                    <div class="portlet-header">
                        <div class="caption">
                            <xms:localization text="Additional Detail"/>
                        </div>
                        <div class="tools">
                            <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="panel-body pan">
                            <form action="#">
                                <div class="form-body pal">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Content Description"/> <span class="s30"> *</span>
                                                </label>
                                                <s:if test="shipmentRequestModel.shipmentInfo.contentType == 'DOX'">
                                                    <s:select name="shipmentRequestModel.contentDetail.description"
                                                              list="contentDetailList" listKey="description"
                                                              listValue="description" cssClass="form-control"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield type="text"
                                                           name="shipmentRequestModel.contentDetail.description"
                                                           class="form-control alloptions" data-placement="top"
                                                           data-toggle="tooltip" data-original-title='<%=WebUtils.getTooltip(request, "Please limit content descriptions to 50 characters.",
						ENCODE_TYPE.JAVASCRIPT)%>' maxlength="50"/>
                                                </s:else>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Shipment Reference"/> <span class="s30"> *</span>
                                                </label>
                                                <s:textfield name="shipmentRequestModel.shipmentReference"
                                                             cssClass="form-control"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Total Customs Value"/> <span class="s30"> *</span>
                                                </label>
                                                <s:textfield name="shipmentRequestModel.quote.totalCustomValue"
                                                             cssClass="form-control"/>
                                            </div>
                                            <s:if test="shipmentRequestModel.shipmentInfo.serviceId == 54 || shipmentRequestModel.shipmentInfo.serviceId == 1 && shipmentRequestModel.shipmentInfo.bound == 0 && shipmentRequestModel.shipmentInfo.contentType == 'WPX'">
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Commercial Invoice"/>
                                                    </label>
                                                    <s:select list="selCommercialInvoice" cssClass="form-control"
                                                              listKey="key" listValue="value"
                                                              onchange="selCommercialInvoiceChange($(this).val())"/>
                                                </div>
                                                <div class="form-group civ_generate">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Receiver Tax Id/GST#"/>
                                                    </label>
                                                    <s:textfield name="shipmentRequestModel.quote.totalCustomValue"
                                                                 cssClass="form-control"/>
                                                </div>
                                                <div class="form-group civ_generate">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Trading Terms"/>
                                                    </label>
                                                    <s:select list="selTermOfTradeList" listKey="key" listValue="value"
                                                              value="dutiesTax" cssClass="form-control"/>

                                                </div>
                                                <div class="form-group civ_generate">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Account Number"/>
                                                    </label>
                                                    <s:textfield name="shipmentRequestModel.quote.totalCustomValue"
                                                                 cssClass="form-control"/>
                                                </div>
                                                <div class="form-group civ_generate">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Reason for Export"/>
                                                    </label>
                                                    <s:textfield name="shipmentRequestModel.quote.totalCustomValue"
                                                                 cssClass="form-control"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Packing List"/>
                                                    </label>
                                                    <s:select list="selPackagingList" cssClass="form-control"
                                                              listKey="key" listValue="value"
                                                              onchange="changePackagingList($(this).val())"/>
                                                </div>
                                                <div class="form-group civ_generate">
                                                    <table>
                                                        <tr>
                                                            <td colspan="5"><xms:localization
                                                                    text="Goods Description"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="5"><s:textarea
                                                                    name="shipmentRequestModel.quote.totalCustomValue"
                                                                    cssClass="form-control"></s:textarea></td>
                                                        </tr>
                                                        <tr>

                                                            <td><xms:localization text="HTS# / B#"/></td>
                                                            <td><xms:localization text="Country of Origin"/></td>
                                                            <td><xms:localization text="Qty"/></td>
                                                            <td><xms:localization text="Unit Value"/></td>
                                                            <td width="7%"><xms:localization text="Total"/></td>
                                                        </tr>
                                                        <tr>

                                                            <td><s:textfield cssClass="form-control"></s:textfield></td>
                                                            <td><s:select list="countryList" listKey="countryId"
                                                                          listValue="countryName"
                                                                          cssClass="form-control"/></td>
                                                            <td><s:textfield cssClass="form-control"></s:textfield></td>
                                                            <td><s:textfield cssClass="form-control"></s:textfield></td>
                                                            <td width="7%">$ 0.00</td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label" for="inputName"> <xms:localization
                                                            text="Special Delivery Instructions"/>
                                                    </label>
                                                    <s:textarea name="shipmentRequestModel.quote.totalCustomValue"
                                                                cssClass="form-control"></s:textarea>
                                                </div>
                                            </s:if>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="portlet box">
                    <div class="portlet-header">
                        <div class="caption">
                            <xms:localization text="Billing Details"/>
                        </div>
                        <div class="tools">
                            <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="panel-body pan">
                            <form action="#">
                                <div class="form-body pal">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Transportation Charges"/> <span class="s30"> *</span>
                                                </label>
                                                <s:select list="listTransportationCharges" listKey="key"
                                                          listValue="value" cssClass="form-control"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Account*"/> <span class="s30"> *</span>
                                                </label>
                                                <s:textfield name="webshipLoginInfo.customerCode"
                                                             cssClass="form-control" readonly="true"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Duties/Tax Fee"/> <span class="s30"> *</span>
                                                </label>
                                                <s:select list="listDutiTax" listKey="key" listValue="value"
                                                          cssClass="form-control"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Account"/> <span class="s30"> *</span>
                                                </label>
                                                <s:textfield name="webshipLoginInfo.customerCode"
                                                             cssClass="form-control" readonly="true"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="portlet box">
                    <div class="portlet-header">
                        <div class="caption">
                            <xms:localization text="Collection Details"/>
                        </div>
                        <div class="tools">
                            <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="panel-body pan">
                            <s:include value="../component/schedule_collection_data.jsp"/>
                            <div class="text-right pal pdt10">
                                <button class="btn s33 " type="button" onclick="backToShipment()">
                                    <xms:localization text="Back"/>
                                </button>
                                <button class="btn s33 " type="button" onclick="doShip()">
                                    <xms:localization text="Ship"/>
                                </button>
                            </div>
                        </div>
                        <div class="chat-form s11"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<s:include value="../component/script_4_schedule_collection_data.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        $(".civ_generate").hide();
        $("#pickup-date").datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: 'dd-mm-yyyy'
        });
        $("#pickup-date").datetimepicker("update", new Date());
        $("[data-toggle='tooltip'], [data-hover='tooltip']").tooltip();
        $('input.alloptions').maxlength({
            alwaysShow: true,
            threshold: 10,
            warningClass: "label label-success w5",
            limitReachedClass: "label label-danger w5",
            separator: '/',

        });
        $('.form_datetime').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: 'dd-mm-yyyy'
        });
    });
    function doShip() {
        var captionList = [];
        captionList.push('<xms:localization text="View Waybill" />');
        captionList.push('<xms:localization text="Thermal Label" />');
        if ($("#ws-schedule-collection-select option:selected").val() != "1") {
            captionList.push('<xms:localization text="Schedule Collection" />');
        } else {
            captionList.push('');
        }
        captionList.push('<xms:localization text="Send Airbill" />');
        captionList.push('<xms:localization text="New Shipment" />');
        captionList.push('<xms:localization text="Re-Ship Same Package" />');
        loadDialogResultBooking(captionList);
    }


</script>