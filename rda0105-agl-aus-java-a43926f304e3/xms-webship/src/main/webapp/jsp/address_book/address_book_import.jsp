<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="tab-general">
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
                                <xms:localization text="Address Book Import"/>
                            </div>
                            <div class="tools">
                                <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="panel-body pan">
                                <div class="form-body pal">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="form_import_address_book">
                                                <p>
                                                    <xms:localization text="<b>Note:</b>
														<br/>
														- Choose a Spreadsheet type to import."/>
                                                </p>

                                                <div class="form-group">
                                                    <s:file name="fileUpload" placeholder="Inlcude some file"
                                                            class="w10" id="address_upload"/>
                                                    <br>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12" style="overflow: auto;" id="file-datas-result"></div>
                                    </div>
                                </div>
                                <div class=" text-right pal pdt10">
                                    <s:hidden id="isMapped" value="0"/>
                                    <button class="btn s33 " type="button" id="mapping_btn" style="display: none;"
                                            onclick="doMapping()">
                                        <xms:localization text="Map States & Countries"/>
                                    </button>
                                    <button class="btn s33 " type="button" id="import_btn" onclick="doImport()"
                                            disabled="disabled">
                                        <xms:localization text="Import"/>
                                    </button>
                                    <s:a class="btn s33 " action="address_book">
                                        <xms:localization text="Cancel"/>
                                    </s:a>
                                </div>
                            </div>
                            <div class="chat-form s11"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="map-dialog" title="<xms:localization text="Address Fields Maps" />"></div>
<div id="import-address-result-dialog"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#address_upload').fileupload({
            url: "upload_address_book.ix?reqType=json",
            done: function (e, data) {
                $('.progress-bar').css('width', '0%');
                $('.progress').hide();
                var result = data.result;
                if (result.errorCode == "SUCCESS") {
                    $("#mapping_btn").show();
                    $("#import_btn").removeAttr("disabled");
                    $("#form_import_address_book").hide();
                    $("#file-datas-result").html(result.content);
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

    function selectField(col, field) {
        $('#address-import-table tbody td[data-field="' + col + '"]').each(function ($i, obj) {
            $(obj).find("input[type=hidden]").attr("name", "addressBookModels[" + $i + "]." + field);
        });
    }

    function doMapping() {
        var isMapped = $("#isMapped").val();
        var action = "address_book_import_show_mapping.ix?reqType=json";
        var doAction = "address_book_import_do_mapping.ix?reqType=json";
        var data = $("#address_book_import_form").serialize();
        if (isMapped == "0") {
            loadingDialog.dialog("open");
            var buttons = {};
            buttons["OK"] = function () {
                loadingDialog.dialog("open");
                var data = $('#map-data-form').serialize();
                $.post(doAction, data, function (res) {
                    loadingDialog.dialog("close");
                    if (res.errorCode == "SUCCESS") {
                        $('input[name="mapStateCountryDataStr"]').val(res.content);
                        $("#isMapped").val(1);
                        dialog.dialog("close");
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
            };
            buttons["Cancel"] = function () {
                $(this).dialog("close");
            }

            var dialog = $("#map-dialog").dialog({
                modal: true,
                autoOpen: false,
                width: "auto",
                buttons: buttons,
                show: {
                    effect: "fade",
                    duration: 300
                }
            });

            $.post(action, data, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    $(".alert").hide();
                    dialog.html(res.content);
                    dialog.dialog("open");
                } else if (res.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                } else {
                    alertDialog.html('Error: ' + res.errorMsg);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                alertDialog.dialog("open");
            });

        }
    }

    function doImport() {
        var isMapped = $("#isMapped").val();
        if (isMapped == "1") {
            loadingDialog.dialog("open");
            var buttons = {};
            buttons["OK"] = function () {
                $(this).dialog("close");
                window.location.href = 'address_book.ix';
            }
            var dialog = $("#import-address-result-dialog").dialog({
                modal: true,
                autoOpen: false,
                width: "auto",
                buttons: buttons,
                show: {
                    effect: "fade",
                    duration: 300
                }
            });
            var data = $("#address_book_import_form").serialize();
            $.post("address_book_do_import.ix?reqType=json", data, function (res) {
                if (res.errorCode == "SUCCESS") {
                    loadingDialog.dialog("close");
                    dialog.html(res.content);
                    dialog.dialog("open");
                    $(".ui-dialog-titlebar").hide();
                } else if (res.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
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
            alertDialog.html('<xms:localization text="Please maps country and state." />');
            alertDialog.dialog("open");
        }
    }


</script>