<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<style type="text/css">
    .scroll_horizontal {
        overflow-x: scroll;
        overflow-y: hidden;
        min-height: 55px;
        width: 100%;
    }

    .div_baseRate input {
        min-width: 50px;
    }
</style>
<div class="row">
    <div class="portlet-body b12 b11">
        <div class="portlet-body b22" style="padding: 0px;">
            <ul id="generalTab1" class="nav nav-tabs responsive">
                <li class="active" style="margin-left: 10px;"><a href="#1a-tab" data-toggle="tab"
                                                                 onclick="loadGeneralTab(1)"><xms:localization
                        text="General"/> </a></li>
                <s:iterator value="services">
                    <li><a href='#<s:property value="serviceId"/>-tab' data-toggle="tab" class="tb3"
                           onclick='loadBaseRate(<s:property value="serviceId"/>)'><s:property value="serviceName"/></a>
                    </li>
                </s:iterator>
                <li><a href="#8-tab" data-toggle="tab" class="tb3" onclick="loadOtherTab(1)"><xms:localization
                        text="Others"/></a></li>
            </ul>
            <div id="generalTabContent" class="tab-content responsive">
                <div id="1a-tab" class="tab-pane fade in tb2 active">
                    <div class="row">
                        <div class="portlet-body b12 b11">
                            <table class="s36">
                                <tbody>
                                <tr>
                                    <td>* = <xms:localization text="May override other settings"/></td>
                                    <td class="caption b17" colspan="2"><xms:localization text="Base Rates"/></td>
                                </tr>
                                <TR>
                                    <td colspan="3" height="5"></td>
                                </TR>
                                <tr>
                                    <td>* <xms:localization text="Minimum Customer Base Charge Margin"/></td>
                                    <td width="60"><s:textfield name="cusProfile.minimunBaseCharge"
                                                                class="form-control"></s:textfield></td>
                                    <td>%</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <s:iterator value="services">
                    <div id='<s:property value="serviceId"/>-tab' class="tab-pane fade in">
                        <xms:localization text="Please choice a customer profile"/>
                    </div>
                </s:iterator>
                <div id="8-tab" class="tab-pane fade in">
                    <xms:localization text="Please choice a customer profile"/>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function loadGeneralTab(check) {
        $("#btn_action").show();
        if ($("#1a-tab").html().length < 55 || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val()
            };

            if (val == "-1") {
                $('#1a-tab').html("");
            } else {
                var action = "manager_customer_base_rate_general.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#1a-tab').html(res.content);
                        loadingDialog.dialog("close");
                    } else {
                        loadingDialog.dialog("close");
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
    }

    function loadBaseRate(svId) {
        $("#btn_action").show();
        if ($("#" + svId + "-tab").html().length < 55) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val(),
                'serviceId': svId
            };

            if (val == "-1") {
                $("#" + svId + "-tab").html("");
            } else {
                var action = "manager_customer_show_base_rate.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $("#" + svId + "-tab").html(res.content);
                        loadingDialog.dialog("close");
                    } else {
                        loadingDialog.dialog("close");
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
    }

    function loadBaseRateDhl(check) {
        $("#btn_action").show();
        if ($("#2-tab").html().length < 55) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val(),
                'serviceId': "1"
            };

            if (val == "-1") {
                $('#2-tab').html("");
            } else {
                var action = "manager_customer_base_rate_dhl.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#2-tab').html(res.content);
                        loadingDialog.dialog("close");
                    } else {
                        loadingDialog.dialog("close");
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
    }

    function loadDhlDomTab(check) {
        $("#btn_action").show();
        if ($("#3-tab").html().length < 55 || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val(),
                'serviceId': "15"
            };

            if (val == "-1") {
                $('#3-tab').html("");
            } else {
                var action = "manager_customer_base_rate_dhl_dom.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#3-tab').html(res.content);
                        loadingDialog.dialog("close");
                    } else {
                        loadingDialog.dialog("close");
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
    }

    function loadTntDomTab(check) {
        $("#btn_action").show();
        if ($("#4-tab").html().length < 55 || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val(),
                'serviceId': "3"
            };

            if (val == "-1") {
                $('#4-tab').html("");
            } else {
                var action = "manager_customer_base_rate_tnt_dom.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#4-tab').html(res.content);
                        loadingDialog.dialog("close");
                    } else {
                        loadingDialog.dialog("close");
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
    }

    function loadTntIntTab(check) {
        $("#btn_action").show();
        if ($("#5-tab").html().length < 55 || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val(),
                'serviceId': "54"
            };

            if (val == "-1") {
                $('#5-tab').html("");
            } else {
                var action = "manager_customer_base_rate_tnt_int.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#5-tab').html(res.content);
                        loadingDialog.dialog("close");
                    } else {
                        loadingDialog.dialog("close");
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
    }

    function loadTollProTab(check) {
        $("#btn_action").show();
        if ($("#6-tab").html().length < 55 || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val(),
                'serviceId': "52"
            };

            if (val == "-1") {
                $('#6-tab').html("");
            } else {
                var action = "manager_customer_base_rate_toll_pro.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#6-tab').html(res.content);
                        loadingDialog.dialog("close");
                    } else {
                        loadingDialog.dialog("close");
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
    }

    function loadTollIpecTab(check) {
        $("#btn_action").show();
        if ($("#7-tab").html().length < 55 || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val(),
                'serviceId': "59"
            };

            if (val == "-1") {
                $('#7-tab').html("");
            } else {
                var action = "manager_customer_base_rate_toll_ipec.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#7-tab').html(res.content);
                        loadingDialog.dialog("close");
                    } else {
                        loadingDialog.dialog("close");
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
    }

    function loadOtherTab(check) {
        $("#btn_action").show();
        if ($("#8-tab").html().length < 55 || check == 1) {
            var val = $('#sel_customer_profiles').val();
            var data = {
                'profileId': val,
                'franchiseCode': $('#sel_franchise').val(),
                'serviceId': "0"
            };
            if (val == "-1") {
                $('#8-tab').html("");
            } else {
                var action = "manager_customer_base_rate_other.ix?reqType=json";
                loadingDialog.dialog("open");
                $.post(action, data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        $('#8-tab').html(res.content);
                        loadingDialog.dialog("close");
                    } else {
                        loadingDialog.dialog("close");
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
    }


</script>