<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<!-- View Continue Booking DHL Int Package -->
<s:form id="form_booking">
    <s:hidden name="shipmentRequestModelGson" id="shipmentRequestModelGson"></s:hidden>
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
                                                    <td class="td2"><s:property
                                                            value="shipmentType.shipmentTypeName"/></td>
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
                                                     <td class="td2"> 
                                                    <s:iterator value="shipmentRequestModel.shipmentInfo.pieces">
                                                         
                                                         <s:property value="dimensionL" /> x <s:property value="dimensionW" /> x <s:property value="dimensionH" />
                                                         <br>
                                                    </s:iterator>
                                                   </td>
                                                   </tr>
                                            </table>
                                        </div>

                                            <div class="col-md-4">
                                                <s:if test="quotable">
                                                    <table class="table s99" style="font-size: 13px;">
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Quote"/></td>
                                                            <s:if test="%{shipmentPage.senderAddress.country != 12 && shipmentPage.receiverAddress.country!= 12}">
                                                                <td class="td1 s40 ncl" colspan="2"><xms:localization text="TBA"/></td>
                                                            </s:if>
                                                            <s:if test="%{shipmentPage.senderAddress.country == 12 || shipmentPage.receiverAddress.country == 12}">
                                                                <td class="td2"><s:property
                                                                        value="shipmentRequestModel.quote.totalCharge"/> <br/>
                                                                    <i><u> <xms:localization
                                                                            text="(Quote is an estimate. Additional fees may apply.)"/>
                                                                    </u></i></td>
                                                            </s:if>
                                                        </tr>
                                                    </table>
                                                </s:if>
                                            </div>

                                        <div class="col-md-12 pd0">
                                            <div class="col-md-12">
                                                <h4 class="s34">
                                                    <u><span style="cursor: pointer;"
                                                             class="clicks a_more_detail"><xms:localization
                                                            text="More Details:"/></span></u>
                                                </h4>
                                            </div>

                                                <div class="col-md-6 sd1 div_more_detail">
                                                    <s:if test="quotable">
                                                        <table class="table s99" style="font-size: 13px;">
                                                            <tbody>
                                                            <tr>
                                                                <td class="td1 s39"
                                                                    style="font-style: italic; text-decoration: underline;">
                                                                    <xms:localization text="Quote Details:"/></td>
                                                                <td class="td2"></td>
                                                            </tr>
                                                            <s:if test="%{shipmentPage.senderAddress.country != 12 && shipmentPage.receiverAddress.country!= 12}">
                                                                <tr>
                                                                    <td class="td1 s40 ncl" colspan="2"><xms:localization text="TBA"/></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:if test="%{shipmentPage.senderAddress.country == 12 || shipmentPage.receiverAddress.country == 12}">
                                                                <tr>
                                                                    <td class="td1 s40 ncl"><xms:localization
                                                                            text="- Base Charge"/></td>
                                                                    <td class="td2"><s:property
                                                                            value="shipmentRequestModel.quote.baseCharge"/></td>
                                                                </tr>
                                                                <s:iterator value="shipmentRequestModel.quote.accessorial">
                                                                    <tr>
                                                                        <td class="td1 s40 ncl">- <s:property
                                                                                value="description"/></td>
                                                                        <td class="td2"><s:property value="value"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                                <tr>
                                                                    <td class="td1 s41 ncl"><xms:localization
                                                                            text="- Total Charge"/></td>
                                                                    <td class="td2"><s:property
                                                                            value="shipmentRequestModel.quote.totalCharge"/></td>
                                                                </tr>
                                                            </s:if>
                                                            </tbody>
                                                        </table>
                                                    </s:if>
                                                </div>
                                            <div class="col-md-6 sd1 div_more_detail">
                                                <table class="table s99" style="font-size: 13px;">
                                                    <tbody>
                                                    <tr>
                                                        <td class="td1"
                                                            style="font-style: italic; text-decoration: underline;">
                                                            <xms:localization text="Sender Address:"/></td>
                                                        <td class="td2"><s:property
                                                                value="shipmentPage.senderAddress.companyName"/>
                                                            <s:property value="shipmentPage.senderAddress.contactName"/>
                                                            <s:property value="shipmentPage.senderAddress.address"/>
                                                            <s:property value="shipmentPage.senderAddress.address2"/>
                                                            <s:property
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
                                                            <s:property
                                                                    value="shipmentPage.receiverAddress.contactName"/>
                                                            <s:property value="shipmentPage.receiverAddress.address"/>
                                                            <s:property value="shipmentPage.receiverAddress.address2"/>
                                                            <s:property
                                                                    value="shipmentPage.receiverAddress.countryDetail.countryName"/>
                                                            <s:property
                                                                    value="shipmentPage.receiverAddress.postalCode"/>
                                                            <s:property value="shipmentPage.receiverAddress.city"/></td>
                                                    </tr>
                                                </table>
                                                <s:if test="%{dhlCapabilityVo != null}">
                                                    <table class="table s99"
                                                           style="font-size: 13px; margin-bottom: 0px">
                                                        <s:if test="%{dhlCapabilityVo.actionStatus == 'SUCCESS'}">
                                                            <tr>
                                                                <td class="td1"><b><xms:localization
                                                                        text="Pickup Date"/></b></td>
                                                                <td class="td2"><s:property
                                                                        value="dhlCapabilityVo.pickupDate"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><b><xms:localization
                                                                        text="Total Transit Days"/></b></td>
                                                                <td class="td2"><s:property
                                                                        value="dhlCapabilityVo.totalTransitDays"/>
                                                                    <xms:localization text="(day)"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><b><xms:localization
                                                                        text="Booking Time"/></b></td>
                                                                <td class="td2"><s:property
                                                                        value="dhlCapabilityVo.bookingTime"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><b><xms:localization
                                                                        text="Pickup CutOff Time"/></b></td>
                                                                <td class="td2"><s:property
                                                                        value="dhlCapabilityVo.pickupCutoffTime"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><b><xms:localization
                                                                        text="Delivery Date"/></b></td>
                                                                <td class="td2"><s:property
                                                                        value="dhlCapabilityVo.deliveryDate"/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:if test="%{dhlCapabilityVo.actionStatus == ''}">
                                                            <tr>
                                                                <td class="td1"><b><xms:localization
                                                                        text="Selected service is not available. please select those available services"/></b>
                                                                </td>
                                                                <td class="td2"><s:iterator
                                                                        value="dhlCapabilityVo.availSvrs">
                                                                    <s:property/>
                                                                    <br/>
                                                                </s:iterator></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:if test="%{dhlCapabilityVo.actionStatus == 'Error'}">
                                                            <tr>
                                                                <td class="td1"><b><xms:localization
                                                                        text="There was an error while checking service capability for the shipment"/></b>
                                                                </td>
                                                                <td class="td2"><s:iterator
                                                                        value="dhlCapabilityVo.errorList">
                                                                    <s:property value="errCode"
                                                                                escapeXml="true"/>:<s:property
                                                                        value="errMsg" escapeXml="true"/>
                                                                    <br/>
                                                                </s:iterator></td>
                                                            </tr>
                                                        </s:if>
                                                    </table>
                                                </s:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7">
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
                                <div class="form-body pal">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Content Description"/> <span class="s30"> *</span>
                                                </label>
                                                <s:textfield name="shipmentRequestModel.contentDetail.description"
                                                             class="form-control alloptions"
                                                             maxlength="30"></s:textfield>
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
                                                        text="Total Shipment Value"/> <span class="s30"> *</span>
                                                </label>
                                                <s:textfield name="shipmentRequestModel.quote.totalCustomValue"
                                                             cssClass="form-control"/>
                                            </div>
                                            <s:include value="../component/commercial_invoice_detail_data.jsp"/>
                                            <s:include value="../component/packing_list_detail_data.jsp"/>
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Special Delivery Instructions"/>
                                                </label>
                                                <s:textarea name="shipmentRequestModel.shipmentInfo.specialDelivery"
                                                            cssClass="form-control"></s:textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
                                <div class="form-body pal">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Transportation Charges"/> <span class="s30"> *</span>
                                                </label>
                                                <s:select list="listTransportationCharges" listKey="key"
                                                          listValue="value" cssClass="form-control"
                                                          name="shipmentRequestModel.shipmentInfo.billingType"
                                                          onchange="changeBillingType($(this).val())" disabled="true"/>
                                                <s:hidden name="shipmentRequestModel.shipmentInfo.billingType"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Account"/> <span class="s30"> *</span>
                                                </label>
                                                <s:if test="shipmentRequestModel.shipmentInfo.billingParty == '0' || shipmentRequestModel.shipmentInfo.billingParty == '1'">
                                                    <s:textfield name="shipmentRequestModel.shipmentInfo.dhInterAccout"
                                                                 cssClass="form-control"/>
                                                </s:if>
                                                <s:else>
                                                    <s:password name="shipmentRequestModel.shipmentInfo.shipperAccount"
                                                                value="%{shipmentRequestModel.shipmentInfo.shipperAccount}"
                                                                showPassword="true" cssClass="form-control"/>
                                                </s:else>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label" for="inputName"> <xms:localization
                                                        text="Duties/Tax Fee"/> <span class="s30"> *</span>
                                                </label>
                                                <s:select id="duties-type" list="listDutiTax"
                                                          name="shipmentRequestModel.shipmentInfo.dutiesType"
                                                          listKey="key" value="2" listValue="value"
                                                          cssClass="form-control"
                                                          onchange="changeDutiesType($(this).val())"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label"
                                                       for="shipmentRequestModel.shipmentInfo.dutiesAccount"
                                                       id="label-duties-account"> <xms:localization
                                                        text="Account"/></label>
                                                <s:password id="duties-account"
                                                            name="shipmentRequestModel.shipmentInfo.dutiesAccount"
                                                            cssClass="form-control"/>
                                            </div>
                                            <s:hidden name="shipmentRequestModel.shipmentInfo.dutiesType"
                                                      data-group="duties-tax" disabled="true"/>
                                            <s:hidden name="shipmentRequestModel.shipmentInfo.dutiesAccount"
                                                      data-group="duties-tax" disabled="true"/>
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
</s:form>
<div id="result_booking" title="<xms:localization text="Booking Result" />"></div>
<div id="webship_booking_send_airbill_dialog" title="<xms:localization text="Send Airbill" />"></div>
<s:include value="../component/script_4_commercial_packing_list_data.jsp"/>
<s:include value="../component/script_4_schedule_collection_data.jsp"/>
<script type="text/javascript">
    var shipperAcc = "<s:property value="shipmentRequestModel.shipmentInfo.shipperAccount"/>";
    $(document).ready(function () {
        $(".div_more_detail").hide();
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
        revampContinuePage();
        if ($("#chkReturnService").is(":checked")) {
            $("select[name='shipmentRequestModel.shipmentInfo.billingType']").val(2);
            $("input[name='shipmentRequestModel.shipmentInfo.billingType']").val(2);
        } else if ($("#chkThirdParty").is(":checked")) {
            $("select[name='shipmentRequestModel.shipmentInfo.billingType']").val(3);
            $("input[name='shipmentRequestModel.shipmentInfo.billingType']").val(3);
        }
        console.log($("input[name='shipmentRequestModel.shipmentInfo.billingType']").val());
    });
    function doShip() {
        if ($("input[name='shipmentRequestModel.contentDetail.description']").val() == "") {
            alertDialog.html("<xms:localization text="Content description cannot be empty." />");
            alertDialog.dialog("open");
            return false;
        } else if ($("#form_booking_shipmentRequestModel_shipmentReference").val() == "") {
            alertDialog.html("<xms:localization text="Shipment reference cannot be empty." />");
            alertDialog.dialog("open");
            return false;
        } else if ($("#form_booking_shipmentRequestModel_quote_totalCustomValue").val() == "") {
            alertDialog.html("<xms:localization text="Total customer value cannot be empty." />");
            alertDialog.dialog("open");
            return false;
        }
        var captionList = [];
        captionList.push('<xms:localization text="View Waybill" />');
        captionList.push('<xms:localization text="Thermal Label" />');
        if ($("#commercialInvoiceSelect").val() == 3) {
            captionList.push('<xms:localization text="Commercial Invoice" />');
        } else {
            captionList.push('');
        }

        if ($("#packingListSelect").val() == 2) {
            captionList.push('<xms:localization text="Packing List" />');
        } else {
            captionList.push('');
        }
        if ($("#ws-schedule-collection-select option:selected").val() != "1") {
            captionList.push('<xms:localization text="Schedule Collection" />');
        } else {
            captionList.push('');
        }
        captionList.push('<xms:localization text="Send Airbill" />');
        captionList.push('<xms:localization text="New Shipment" />');
        captionList.push('<xms:localization text="Re-Ship Same Package" />');
        var actionPost = "webship_book.ix?reqType=json";
        loadDialogResultBooking(actionPost, captionList);
    }
    function changeDutiesType(dType) {
        var acc = $("input[name='shipmentRequestModel.shipmentInfo.shipperAccount']").val();
        if (dType == "1") {
            $("input[name='shipmentRequestModel.shipmentInfo.dutiesAccount']").val(acc);
            setRequireLabel("label-duties-account", true);
        } else {
            if (dType == "3") {
                setRequireLabel("label-duties-account", true);
            } else {
                setRequireLabel("label-duties-account", false);
            }
            $("input[name='shipmentRequestModel.shipmentInfo.dutiesAccount']").val("");
        }
    }
    function revampContinuePage() {
        var senderAcc = $("input[name='shipmentRequestModel.shipmentInfo.shipperAccount']").val();
        if ($("input[data-group='dtpfee']").is(":checked")) {
            $("#term-of-trade").val("DTP");
            $("#duties-type").val("1");
            $("#duties-type").attr("disabled", true);
            $("#duties-account").attr("disabled", true);
            $("input[type='hidden'][data-group='duties-tax']").attr("disabled", false);
            $("input[type='hidden'][name='shipmentRequestModel.shipmentInfo.dutiesType']").val("1");
            $("input[type='hidden'][name='shipmentRequestModel.shipmentInfo.dutiesAccount']").val("senderAcc");
            changeDutiesType("1");
        } else {
            $("input[type='hidden'][data-group='duties-tax']").attr("disabled", true);
        }
        changeBillingType(1);
    }
    function changeBillingType(billType) {
        if (billType == "1") {
            $("input[type='password'][name='shipmentRequestModel.shipmentInfo.shipperAccount']").val(shipperAcc);
            $("input[type='password'][name='shipmentRequestModel.shipmentInfo.shipperAccount']").attr("readonly", true);
        } else {
            $("input[type='password'][name='shipmentRequestModel.shipmentInfo.shipperAccount']").val("");
            $("input[type='password'][name='shipmentRequestModel.shipmentInfo.shipperAccount']").attr("readonly", false);
        }
    }

    $(".a_more_detail").click(function () {
        $(".div_more_detail").toggle("slow");
    });


</script>