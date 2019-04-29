<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN CONTENT-->
<div class="page-content">
    <div id="tab-general">
        <s:if test="hasActionErrors()">
            <s:actionerror/>
        </s:if>
        <s:else>
            <div class="row mbl">
                <div class="col-lg-12">
                    <div class="col-md-12">
                        <div id="area-chart-spline"
                             style="width: 100%; height: 300px; display: none;"></div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="portlet box">
                                <div class="portlet-header">
                                    <div class="caption">
                                        <xms:localization text="Shipments Void"/>
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
                                                    <div class="col-lg-9">
                                                        <p>
                                                            <b id="result_void"><s:if
                                                                    test="%{detailInfoModel.status==1}">
                                                                - <xms:localization text="This shipment has voided"/>
                                                            </s:if> </b>
                                                        </p>
                                                        <s:if test="%{detailInfoModel.scStatus==0}">
                                                            <p>
                                                                <b id="result_schedule">- <xms:localization
                                                                        text="This shipment has cancel schedule collection."/>
                                                                </b>
                                                            </p>
                                                        </s:if>
                                                        <table class="table"
                                                               style="font-size: 11px; margin-bottom: 0px">
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Airway Bill Number"/></td>
                                                                <td class="td2"><s:property
                                                                        value="detailInfoModel.tracking"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Shipment Date"/></td>
                                                                <td class="td2"><s:property
                                                                        value="detailInfoModel.shipmentDate"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Pickup Date"/></td>
                                                                <td class="td2"><s:property
                                                                        value="detailInfoModel.pickupDate"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Collection Number"/></td>
                                                                <td class="td2" id="td_confirmation_no">
                                                                        <s:property
                                                                                value="detailInfoModel.confirmationNo"/>
                                                                        <s:hidden
                                                                                value="%{shipmentId}" id="shipmentId"/>
                                                                        <s:hidden
                                                                                value="%{detailInfoModel.schId}"
                                                                                id="schID"/>
                                                            <tr>
                                                                <td colspan="2" style="border: 0px;">
                                                                    <p>
                                                                        <b><xms:localization
                                                                                text="- If this shipment is scheduled for collection you may need to cancel the scheduled collection first"/>
                                                                            . <br> - <xms:localization
                                                                                    text="Please select a reason for cancelling this shipment"/>.
                                                                        </b>
                                                                    </p>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Reason"/></td>
                                                                <td class="td2"><select id="void_reason"
                                                                                        class="form-control"
                                                                                        style="height: 25px; padding-top: 2px;">
                                                                    <option value="001"><xms:localization
                                                                            text="PACKAGE NOT READY"/></option>
                                                                    <option value="002"><xms:localization
                                                                            text="RATES TOO HIGH"/></option>
                                                                    <option value="003"><xms:localization
                                                                            text="TRANSIT TIME TOO SLOW"/></option>
                                                                    <option value="004"><xms:localization
                                                                            text="TAKE TO SERVICE CENTER OR DROP BOX"/></option>
                                                                    <option value="005"><xms:localization
                                                                            text="COMMITMENT TIME NOT MET"/></option>
                                                                    <option value="006"><xms:localization
                                                                            text="REASON NOT GIVEN"/></option>
                                                                    <option value="007"><xms:localization
                                                                            text="OTHER"/></option>
                                                                    <option value="008"><xms:localization
                                                                            text="PICKUP MODIFIED"/></option>
                                                                </select></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="form-actions text-right pal pdt10">
                                        <button class="btn s33 " id="void_shipment" type="button"
                                                <s:if test="%{detailInfoModel.status==1}">
                                                    disabled
                                                </s:if>
                                                onclick="javascript:voidAirbill();">
                                            <xms:localization text="Submit"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:else>
    </div>
</div>
<div id="void-dialog" title="<xms:localization text="Shipment void"/>"></div>
<script type="text/javascript">
    function voidAirbill() {
        var schID = $("#schID").val();
        var shipmentId = $("#shipmentId").val();
        var confirmationNo = $("#td_confirmation_no").text();
        loadingDialog.dialog("open");
        var buttons = {};
        buttons["<xms:localization text="Close"/>"] = function () {
            window.close();
        }
        var dialog = $("#void-dialog").dialog({
            modal: true,
            autoOpen: false,
            width: "60%",
            buttons: buttons,
            show: {
                effect: "fade",
                duration: 300
            }
        });
        $.post("history_do_void_airbill.ix?reqType=json", {
            'scheduleCollectionModel.shipmentId': shipmentId,
            'scheduleCollectionModel.scheduleCollectionId': schID,
            'shipmentModel.shipmentId': shipmentId,
            'reason': $("#void_reason").val()
        }, function (res) {
            if (res.errorCode == "SUCCESS") {
                dialog.html("This shipment has been voided successfully");
                dialog.dialog("open");
                $("#void_shipment").attr("disabled", true);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
            loadingDialog.dialog("close");
        });
    }
</script>
<!--END CONTENT-->