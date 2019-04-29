<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN CONTENT-->
<div class="page-content">
    <div id="tab-general">
        <div class="row mbl">
            <div class="col-lg-12">
                <div class="col-md-12">
                    <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">

                    <div class="col-lg-6">
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
                                                    <s:if test="%{shipmentDetail.status==1}">
                                                        <p>
                                                            <b id="result_void">- <xms:localization
                                                                    text="This shipment has voided"/> !
                                                            </b>
                                                        </p>
                                                    </s:if>
                                                    <s:else>
                                                        <p>
                                                            <b id="result_void">- <xms:localization
                                                                    text="This shipment will be voided"/></b>
                                                        </p>
                                                    </s:else>


                                                    <table class="table" style="font-size: 11px; margin-bottom: 0px">
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Airway Bill Number"/></td>
                                                            <td class="td2"><s:property
                                                                    value="shipmentDetail.airbillNumber"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Shipment Date"/></td>
                                                            <td class="td2"><s:property
                                                                    value="shipmentDetail.shipmentDate"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Pickup Date"/></td>
                                                            <td class="td2"><s:property
                                                                    value="shipmentDetail.schedule.pickupDate"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Collection Number"/></td>
                                                            <td class="td2"><s:property
                                                                    value="shipmentDetail.schedule.confirmationNo"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" style="border: 0px;">
                                                                <p>
                                                                    <b>- <xms:localization
                                                                            text="This shipment is scheduled for collection. You need to cancel the scheduled collection"/>
                                                                        . <br> - <xms:localization
                                                                                text="Please select a reason for cancellation"/>.
                                                                    </b>
                                                                </p>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <s:hidden value="%{shipmentId}" id="shipmentId"/>
                                                            <s:hidden
                                                                    value="%{shipmentDetail.schedule.scheduleCollectionId}"
                                                                    id="schID"/>
                                                            <td class="td1">Reason</td>
                                                            <td class="td2"><select class="form-control"
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
                                    <s:actionerror/>
                                    <s:actionmessage/>
                                    <s:fielderror/>
                                    <button class="btn s33 " type="submit" <s:if test="%{shipmentDetail.status==1}">
                                        disabled
                                    </s:if> id="void_shipment">Submit
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
<script type="text/javascript">
    function voidAirbill() {
        var schID = document.getElementById('schID').value;
        var shipmentId = document.getElementById('shipmentId').value;
        $('#loading').modal('show');
        $.post("void_airbill.ix", {
            'shipmentId': shipmentId,
            'schID': schID,
            'statusVoid': '0'
        }, function (res) {
            $('#result_void').html('- This shipment voided success');
            document.getElementById("void_shipment").disabled = true;
            $('#loading').modal('hide');
        });
    }
    $(document).ready(function () {
        $('#void_shipment').click(function () {
            voidAirbill();
        });
    });
</script>

<!--END CONTENT-->