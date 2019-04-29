<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator"
           uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<script
        src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    var hasRecords = false;
</script>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left"
        style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization
                text="Import Rate Sheet"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Import Rate Sheet"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<div class="page-content">
    <div id="tab-general">
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
                                    <xms:localization text="Import Rate Sheet"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group" id="btn-export" style="display: none;">
                                            <input type="button" id="export-option" class="btn s37"
                                                   value="<xms:localization text="Option" />"
                                                   onclick="exportClick($('#selected-option').val())"/>
                                            <button type="button" class="btn s37 dropdown-toggle"
                                                    data-toggle="dropdown" aria-expanded="true">
                                                <span class="caret"></span>
                                            </button>
                                            <s:hidden id="selected-option"/>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                <li><a href="#" onclick="exportClick('option')"><xms:localization
                                                        text="Option"/></a></li>
                                                <li><a href="#" onclick="exportClick('html')"><xms:localization
                                                        text="Print"/></a></li>
                                                <s:if test="grandTotal != 0">
                                                    <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                            text="Export"/></a></li>
                                                </s:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12 ">
                                                <div class="form-group ">
                                                    <table class="s36 b24">
                                                        <tr>
                                                            <td><xms:localization
                                                                    text="Please name the rate sheet"/>*
                                                            </td>
                                                            <td><s:textfield id="sheet_name" name="sheet_name"
                                                                             class="form-control"/></td>
                                                            <td><xms:localization text="Carrier"/>*</td>
                                                            <td><s:select id="services" name="services"
                                                                          list="services" listValue="serviceName"
                                                                          listKey="serviceId" cssClass="form-control"
                                                                          onchange="getRateSheetType()"/></td>
                                                            <td><xms:localization text="Rate Sheet Type"/>*</td>
                                                            <td id="rateSheetType"><select class="form-control"
                                                                                           id="shipment_type"
                                                                                           name="shipment_type"
                                                                                           onchange="changeSheetType()">
                                                                <s:iterator value="rateSheetType">
                                                                    <s:if test="type=='document'">
                                                                        <option value="<s:property value="id" />">
                                                                            <s:property
                                                                                    value="shipmentTypeName"/> -
                                                                            <xms:localization text="Document"/></option>
                                                                    </s:if>
                                                                    <s:elseif test="type=='document_inbound'">
                                                                        <option value="<s:property value="id" />">
                                                                            <s:property
                                                                                    value="shipmentTypeName"/> -
                                                                            <xms:localization
                                                                                    text="Document Inbound"/></option>
                                                                    </s:elseif>
                                                                    <s:elseif test="type=='package'">
                                                                        <option value="<s:property value="id" />">
                                                                            <s:property
                                                                                    value="shipmentTypeName"/> -
                                                                            <xms:localization text="Package"/></option>
                                                                    </s:elseif>
                                                                    <s:elseif test="type=='package_inbound'">
                                                                        <option value="<s:property value="id" />">
                                                                            <s:property
                                                                                    value="shipmentTypeName"/> -
                                                                            <xms:localization
                                                                                    text="Package Inbound"/></option>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <option value="<s:property value="id" />">
                                                                            <s:property
                                                                                    value="shipmentTypeName"/></option>
                                                                    </s:else>
                                                                </s:iterator>
                                                            </select></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="pal form-actions">
                                                <table class="s36">
                                                    <tbody>
                                                    <tr id="source_zone">
                                                        <td><input type="checkbox" name="isSourceZone" id="isSourceZone"
                                                                   onchange="checkSourceZone(this)"/>
                                                            <xms:localization
                                                                    text="Specify Source Zone (Country - Country Only)"/>
                                                        </td>
                                                        <td><s:textfield id="source_zone"
                                                                         name="source_zone" class="form-control"
                                                                         cssStyle="width: 40px;" disabled="true"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td><s:hidden name="file_path" id="file_path"
                                                                      value=""/> <s:file name="fileUpload"
                                                                                         cssClass="w10"
                                                                                         id="rate_sheet_upload"/>
                                                            <div class="progress progress-striped active"
                                                                 style="display: none">
                                                                <div class="progress-bar progress-bar-success"
                                                                     role="progressbar" aria-valuenow="0"
                                                                     aria-valuemin="0" aria-valuemax="100"
                                                                     style="width: 0%;"></div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr id="carrier_cost" style="display: none;">
                                                        <td><input type="checkbox" name="isCarrierCost"
                                                                   id="isCarrierCost"/>
                                                            <xms:localization
                                                                    text="This is a Carrier Cost rate sheet. Rate Sheet."/>
                                                        </td>
                                                    </tr>
                                                    <tr id="per_weight">
                                                        <td><input type="checkbox" name="isPerWeight" id="isPerWeight"/>
                                                            <xms:localization text="This is a Per Weight Rate Sheet."/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <button class="btn s37" type="button"
                                                                    onclick="saveRateSheet()">
                                                                <xms:localization text="Save Rate Sheet"/>
                                                            </button>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
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
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#rate_sheet_upload').fileupload({
            url: "rate_sheets_upload.ix?reqType=json",
            done: function (e, data) {
                $('.progress-bar').css('width', '0%');
                $('.progress').hide();
                var result = data.result;
                if (result.errorCode == "SUCCESS") {
                    $('#file_path').val(result.content);
                    messageDialog.html("<xms:localization text="Upload successfully." />");
                    messageDialog.dialog("open");
                } else {
                    alertDialog.html(result.errorMsg);
                    alertDialog.dialog("open");
                }
            },
            submit: function (e, data) {
                $('.progress').show();
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('.progress-bar').css('width', progress + '%');
            }
        });
    });
    function checkSourceZone(obj) {
        document.getElementById('source_zone').disabled = !obj.checked; //obj.checked;
    }
    function getRateSheetType() {
        var carrierId = $("#services option:selected").val();
        var shipment_type = $("#shipment_type option:selected").val();
        var str_arr = shipment_type.split(',');
        var shipment_type_id = str_arr[0];
        if (carrierId == 3 || (carrierId == 72 && (shipment_type_id == 228 || shipment_type_id == 229))) {
            $('#source_zone').hide();
            $('#carrier_cost').hide();
            $('#per_weight').hide();
        } else {
            $('#source_zone').show();
            $('#carrier_cost').show();
            $('#per_weight').show();
        }
        var data = {
            'carrierId': carrierId
        };
        $.post("get_rate_sheet_type_data.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#rateSheetType").html(res.content);
                changeSheetType();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }
    function changeSheetType() {
        var carrierId = $("#services option:selected").val();
        var shipment_type = $("#shipment_type option:selected").val();
        var str_arr = shipment_type.split(',');
        var shipment_type_id = str_arr[0];
        var allow_non_carrier = str_arr[3];
        var allow_carrier = str_arr[4];
        if (carrierId == 3 || (carrierId == 72 && (shipment_type_id == 228 || shipment_type_id == 229))) {
            $('#source_zone').hide();
            $('#carrier_cost').hide();
            $('#per_weight').hide();
        } else {
            $('#source_zone').show();
            $('#carrier_cost').show();
            $('#per_weight').show();
        }
        if (allow_non_carrier == 0 && allow_carrier == 0) {
            $('#isCarrierCost').attr('checked', true);
            $('#carrier_cost').hide();
        } else if (allow_non_carrier == 0 && allow_carrier == 1) {
            $('#isCarrierCost').attr('checked', true);
            $('#carrier_cost').hide();
        } else if (allow_non_carrier == 1 && allow_carrier == 0) {
            $('#isCarrierCost').attr('checked', false);
            $('#carrier_cost').hide();
        } else {
            $('#isCarrierCost').attr('checked', false);
            $('#carrier_cost').show();
        }
    }
    function saveRateSheet() {
        var rateSheetName = $('#sheet_name').val();
        var carrierId = $('#services').val();
        var shipmentType = $('#shipment_type').val();
        if ($('#isSourceZone').is(':checked')) {
            var isSourceZone = true;
        } else {
            var isSourceZone = false;
        }
        var sourceZone = $('#sourceZone').val();
        if ($('#isCarrierCost').is(':checked')) {
            var isCarrierCost = true;
        } else {
            var isCarrierCost = false;
        }
        if ($('#isPerWeight').is(':checked')) {
            var isPerWeight = true;
        } else {
            var isPerWeight = false;
        }
        var filePath = $('#file_path').val();
        var data = {
            'rateSheetName': rateSheetName,
            'carrierId': carrierId,
            'shipmentType': shipmentType,
            'isSourceZone': isSourceZone,
            'sourceZone': sourceZone,
            'isCarrierCost': isCarrierCost,
            'isPerWeight': isPerWeight,
            'rateSheetFilePath': filePath
        };
        loadingDialog.dialog("open");
        $.post("save_import_rate_sheet.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                messageDialog.html("Import Successful!");
                messageDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }


</script>