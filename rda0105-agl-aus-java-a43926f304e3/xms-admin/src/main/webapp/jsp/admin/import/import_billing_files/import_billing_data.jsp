<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_import_billing">
            <table class="s36 d1">
                <s:hidden name="billingDataStr" fieldValue="billingDataStr"/>
                <tr>
                    <td colspan="2"><xms:localization text="Choose a CSV type to import"/></td>
                </tr>
                <tr>
                    <td><xms:localization text="Date Format"/></td>
                    <td><select id="selDateFormat" name="selDateFormat" class="form-control alloptions">
                        <option value="dd-MM-yyyy">dd-MM-yyyy</option>
                        <option value="dd-MMM-yyyy">dd-MMM-yyyy</option>
                        <option value="dd/MM/yyyy">dd/MM/yyyy</option>
                        <option value="MM-dd-yyyy">MM-dd-yyyy</option>
                        <option value="MM/dd/yyyy">MM/dd/yyyy</option>
                        <option value="yyyy-MM-dd">yyyy-MM-dd</option>
                        <option value="yyyy/MM/dd">yyyy/MM/dd</option>
                        <option value="yyyyMMdd">yyyyMMdd</option>
                        <option value="ddMMyyyy">ddMMyyyy</option>
                        <option value="dd-MM-yy">dd-MM-yy</option>
                    </select></td>
                </tr>
                <s:if test="otherCarrier==true">
                <s:hidden name="otherCarrier" value="false"/>
                <tr style="height: 10px;"></tr>
                <tr>
                    <td colspan="2"><s:checkbox name="isInternationalShipment" id="isInternationalShipment"/>
                        <xms:localization text="International Shipment"/></td>
                <tr>
                <tr>
                    <td colspan="2"><s:checkbox name="applyCustomerTax" id="applyCustomerTax"/> <xms:localization
                            text="Apply Customer Tax with Default Tax Percentage"/></td>
                <tr>
                <tr>
                    <td colspan="2"><s:checkbox name="applyCarrierTax" id="applyCarrierTax"/> <xms:localization
                            text="Apply Carrier Tax with Default Tax Percentage"/></td>
                <tr>
                    </s:if>
                    <s:else>
                        <s:hidden name="otherCarrier" value="false"/>
                    </s:else>
                <tr style="height: 10px;"></tr>
                <tr>
                    <td colspan="2"><s:hidden name="billingFilePath" id="billingFilePath" value=""/> <s:file
                            name="fileUpload" cssClass="w10" id="billing_upload"/>
                        <div class="progress progress-striped active" style="display: none">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    var buttons = {};
    buttons["Cancel"] = function () {
        $(this).dialog("close");
    }
    var buttonResult = {};
    buttonResult["Close"] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#div_import_billing").dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttons,
        width: 'auto',
        height: 'auto',
        maxHeight: height,
        create: function (event, ui) {
            $(this).css("maxWidth", width);
        },
        show: {
            effect: "fade",
            duration: 300
        },
        close: function (e) {
            $("#div_import_billing").html("");
        }
    });
    var dialogResult = $("#div_import_billing_do").dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttonResult,
        width: 'auto',
        height: 'auto',
        maxHeight: height,
        create: function (event, ui) {
            $(this).css("maxWidth", width);
        },
        show: {
            effect: "fade",
            duration: 300
        },
        close: function (e) {
            $("#div_import_billing_do").html("");
        }
    });
    $(document).ready(function () {
        $('#billing_upload').fileupload({
            url: "upload_billing_file.ix?reqType=json",
            done: function (e, data) {
                $('.progress-bar').css('width', '0%');
                $('.progress').hide();
                var result = data.result;
                if (result.errorCode == "SUCCESS") {
                    $('#form_import_billing #billingFilePath').val(result.content);
                    // Save import billing
                    loadingDialog.dialog("open");
                    var dataForm = $('#form_import_billing').serialize();
                    $.post("import_billing_validate_import.ix?reqType=json", dataForm, function (res) {
                        if (res.errorCode == "SUCCESS") {
                            $("#file-datas-result").html(res.content);
                            var importType = $("#file-datas-result input[name='importType']").val();
                            if (importType == "continue") {
                                $.post("save_import_billing.ix?reqType=json", dataForm, function (res) {
                                    loadingDialog.dialog("close");
                                    if (res.errorCode == "SUCCESS") {
                                        dialog.dialog("close");
                                        dialogResult.html(res.content);
                                        dialogResult.dialog("open");
                                    } else if (res.errorCode == "FIELD_ERROR") {
                                        dialog.html(res.content);
                                    } else if (res.errorCode == "ACTION_ERROR") {
                                        alertDialog.html(res.errorMsg);
                                        alertDialog.dialog("open");
                                    }
                                }).fail(function () {
                                    loadingDialog.dialog("close");
                                    alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                                    alertDialog.dialog("open");
                                });
                            } else {
                                loadingDialog.dialog("close");
                                $("#file-datas-result").show();
                                dialog.dialog("close");
                                $("#other-import-buttons").show();
                                $("#import-buttons").hide();
                            }
                        } else if (res.errorCode == "FIELD_ERROR") {
                            loadingDialog.dialog("close");
                            dialog.html(res.content);
                        } else if (res.errorCode == "ACTION_ERROR") {
                            loadingDialog.dialog("close");
                            alertDialog.html(res.errorMsg);
                            alertDialog.dialog("open");
                        }
                    }).fail(function () {
                        loadingDialog.dialog("close");
                        alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                        alertDialog.dialog("open");
                    });
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


</script>