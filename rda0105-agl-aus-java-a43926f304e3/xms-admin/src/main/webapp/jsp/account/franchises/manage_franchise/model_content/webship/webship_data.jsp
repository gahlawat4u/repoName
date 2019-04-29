<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <div class="portlet-body b12 b11">
        <div class="portlet-body b22" style="padding: 0px;">
            <ul id="manage-customer-webship-tabs" class="nav nav-tabs responsive">
                <li class="active" style="margin-left: 10px;"><a href="#webship-users-tab" data-toggle="tab">Users</a>
                </li>
                <li><a href="#webship-carriers-tab" data-toggle="tab" class="tb3">Carriers</a></li>
                <li><a href="#admin-profile-image" data-toggle="tab" class="tb3">Profile Image</a></li>
            </ul>
            <div id="manage-customer-webship-tabs-content" class="tab-content responsive">
                <div id="webship-users-tab" class="tab-pane fade in tb2 active">
                    <div class="row">
                        <div class="portlet-body b12 b11">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <form id="frmSaveUserSetting">
                                            <table class="s36 b24">
                                                <tr>
                                                    <td colspan="4"><b><xms:localization
                                                            text="Web Freight Account Setting"/> :</b></td>
                                                </tr>
                                                <tr>
                                                    <td><s:hidden
                                                            name="saveManageFranchiseModel.franchise.franchiseCode"
                                                            value="%{franchise.franchiseCode}"/> <s:checkbox
                                                            name="saveManageFranchiseModel.franchise.adminFunction"
                                                            value="%{franchise.adminFunction}"/></td>
                                                    <td><xms:localization text="Admin Function"/></td>
                                                    <td><s:select
                                                            name="saveManageFranchiseModel.franchise.webshipAdminid"
                                                            value="%{franchise.webshipAdminid}" list="webshipList"
                                                            listValue="name" listKey="webshipId" cssClass="form-control"
                                                            headerValue="" headerKey="0"/></td>
                                                    <td><xms:localization text="Set Webship Admin"/></td>
                                                </tr>
                                            </table>
                                            <p>
                                                <b><xms:localization text="Note"/> :</b> <br> -
                                                <xms:localization
                                                        text="Web Freight accounts may not be deleted. To disable access to an account, simply change the password."/>
                                            </p>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <table class="table mg0">
                                        <tr>
                                            <th class="s42">
                                                <div class="form-group fll mgb">
                                                    <table class="s36">
                                                        <tbody>
                                                        <tr>
                                                            <td><xms:localization text="Show"/></td>
                                                            <td><s:select id="webship_pageSize" name="pageSize"
                                                                          list="pageSizes" cssClass="form-control"
                                                                          cssStyle="height: 22px; padding-top: 1px; width: 55px;"
                                                                          onchange="getWebships(1)"/></td>
                                                            <td><xms:localization text="Entries"/></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </th>
                                        </tr>
                                    </table>
                                    <div id="webship-list-result">
                                        <table class="table table-bordered table-hover mg0">
                                            <thead>
                                            <tr>
                                                <th><xms:localization text="WebFreightID"/></th>
                                                <th><xms:localization text="CustomerID"/></th>
                                                <th><xms:localization text="Alt User Name"/></th>
                                                <th><xms:localization text="Password"/></th>
                                                <th><xms:localization text="Created Date"/></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <s:if test="webships!=null && webships.totalRecords>0">
                                                <s:iterator value="webships.records">
                                                    <tr webship-id="<s:property value="webshipId" />"
                                                        style="cursor: pointer;">
                                                        <td><s:property value="webshipId"/></td>
                                                        <td><s:property value="customerCode"/></td>
                                                        <td><s:property value="name"/></td>
                                                        <td><s:property value="password"/></td>
                                                        <td><s:property value="createDate"/></td>
                                                    </tr>
                                                </s:iterator>
                                            </s:if>
                                            <s:else>
                                                <tr>
                                                    <td colspan="5"><xms:localization text="No data available"/>...</td>
                                                </tr>
                                            </s:else>
                                            </tbody>
                                        </table>
                                        <div class="dataTables_paginate records" style="margin-bottom: 15px;">
                                            <div class="row">
                                                <div class="col-xs-4 text-left">
                                                    <b><xms:localization text="Showing"/> <s:property
                                                            value="webships.startRecord"/> <xms:localization text="to"/>
                                                        <s:property value="webships.endRecord"/> <xms:localization
                                                                text="of"/> <s:property value="webships.totalRecords"/>
                                                        <xms:localization text="entries"/></b>
                                                </div>
                                                <div class="col-xs-8">
                                                    <s:if test="!webships.hasPrev()">
                                                        <a class="paginate_button previous disabled"><xms:localization
                                                                text="Previous"/></a>
                                                    </s:if>
                                                    <s:else>
                                                        <a href="javascript:getWebships(<s:property value="%{webships.currentPage - 1}"/>)"
                                                           class="paginate_button previous"><xms:localization
                                                                text="Previous"/></a>
                                                    </s:else>
													<span> <s:iterator value="webships.pageRange" status="count">
                                                        <s:if test="%{webships.pageRange[#count.index] == webships.currentPage}">
                                                            <a class="paginate_button current"><s:property
                                                                    value="webships.currentPage"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button"
                                                               href="javascript:getWebships(<s:property/>);"><s:property/></a>
                                                        </s:else>
                                                    </s:iterator>
													</span>
                                                    <s:if test="!webships.hasNext()">
                                                        <a class="paginate_button next disabled"><xms:localization
                                                                text="Next"/></a>
                                                    </s:if>
                                                    <s:else>
                                                        <a class="paginate_button next"
                                                           href="javascript:getWebships(<s:property value="%{webships.currentPage+1}"/>)"><xms:localization
                                                                text="Next"/></a>
                                                    </s:else>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table>
                                        <tr>
                                            <td>
                                                <button class="btn s37" type="button" onclick="onAddEditUser(true)">
                                                    <xms:localization text="Add User"/>
                                                </button>
                                            </td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>
                                                <button class="btn s37" type="button" onclick="onAddEditUser(false)">
                                                    <xms:localization text="Edit User"/>
                                                </button>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="webship-carriers-tab" class="tab-pane fade in">
                    <div class="row">
                        <div class="portlet-body b12 b11">
                            <div class="form-group">
                                <form id="frmSaveWebshipSettings">
                                    <s:if test="services!=null && services.size()>0">
                                        <s:iterator value="services" status="sv">
                                            <table class="s36 b24">
                                                <tr>
                                                    <td>
                                                        <button type="button" class="fa fa-chevron-circle-right s10 b3"
                                                                data-toggle="collapse"
                                                                data-target="#sv-<s:property value="serviceId" />"></button>
                                                    </td>
                                                    <td><s:checkbox id="sv-%{serviceId}-checkbox"
                                                                    name="saveManageFranchiseModel.webship.services[%{#sv.index}].checked"
                                                                    value="%{services[#sv.index].checked}"
                                                                    onclick="onServiceCheck('#sv-%{serviceId}',this)"/></td>
                                                    <td><s:property value="serviceName"/></td>
                                                </tr>
                                            </table>
                                            <div id="sv-<s:property value="serviceId" />" class="collapse">
                                                <s:if test="shipmentTypes!=null && shipmentTypes.size()>0">
                                                    <table class="s36 b25">
                                                        <s:iterator value="shipmentTypes" status="st">
                                                            <tr>
                                                                <td><s:checkbox
                                                                        name="saveManageFranchiseModel.webship.services[%{#sv.index}].shipmentTypes[%{#st.index}].checked"
                                                                        value="%{shipmentTypes[#st.index].checked}"
                                                                        onclick="onShipmentTypeCheck('#sv-%{serviceId}',this)"/></td>
                                                                <td><s:property value="shipmentTypeName"/> <s:hidden
                                                                        name="saveManageFranchiseModel.webship.services[%{#sv.index}].shipmentTypes[%{#st.index}].customerCode"
                                                                        value="%{shipmentTypes[#st.index].customerCode}"/>
                                                                    <s:hidden
                                                                            name="saveManageFranchiseModel.webship.services[%{#sv.index}].shipmentTypes[%{#st.index}].userType"
                                                                            value="%{shipmentTypes[#st.index].userType}"/>
                                                                    <s:hidden
                                                                            name="saveManageFranchiseModel.webship.services[%{#sv.index}].shipmentTypes[%{#st.index}].serviceId"
                                                                            value="%{shipmentTypes[#st.index].serviceId}"/>
                                                                    <s:hidden
                                                                            name="saveManageFranchiseModel.webship.services[%{#sv.index}].shipmentTypes[%{#st.index}].shipmentTypeId"
                                                                            value="%{shipmentTypes[#st.index].shipmentTypeId}"/></td>
                                                            </tr>
                                                        </s:iterator>
                                                    </table>
                                                </s:if>
                                            </div>
                                        </s:iterator>
                                    </s:if>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <div id="admin-profile-image" class="tab-pane fade in">
                    <div class="row">
                        <div class="portlet-body b12 b11">
                            <div class="form-group">
                               
                               	    <s:hidden name="file_path_admin" id="file_path_admin"   value=""/> 
									<s:file  name="userImage"  cssClass="w10"   id="admin_profile_image_upload"/>
                               
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</div>
<div id="webship-dialog" title=""></div>

<script type="text/javascript">
    var webshipId = "";

    $(document).ready(function () {
        $("#webship-list-result tr[webship-id]").click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
            webshipId = $(this).attr("webship-id");
        });
    });
    
    
 $(document).ready(function () {
       
	 $('#admin_profile_image_upload').fileupload({
            url: "adminprofileimageupload.ix?customerCode="+$("input[name='saveManageFranchiseModel.franchise.franchiseCode']").val()+"&reqType=json",
            done: function (e, data) {
                $('.progress-bar').css('width', '0%');
                $('.progress').hide();
                var result = data.result;
                if (result.errorCode == "SUCCESS") {
                    $('#file_path_admin').val(result.content);
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
    
    

    function getWebships(page) {
        // Load Webship List
        var pageSize = $("#webship_pageSize option:selected").val();
        var customerCode = $("#sel_franchise option:selected").val();
        var data = {
            "page": page,
            "pageSize": pageSize,
            "customerCode": customerCode
        };
        loadingDialog.dialog("open");
        $.post("manage_customers_webship_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#webship-list-result").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function onServiceCheck(svId, obj) {
        if ($(obj).is(":checked")) {
            $(svId + " input[type='checkbox']").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $(svId + " input[type='checkbox']").each(function () {
                $(this).prop("checked", false);
            });
        }
    }

    function onShipmentTypeCheck(svId, obj) {
        var checked = false;
        $(svId + " input[type='checkbox']").each(function () {
            if ($(this).is(":checked")) {
                checked = true;
                return false;
            }
        });
        $(svId + "-checkbox").prop("checked", checked);
    }

    function save() {
        var data = $("#frmSaveUserSetting").serialize();
        data = data + "&" + $("#frmSaveWebshipSettings").serialize();
        $.post("manage_franchise_webship_save.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                messageDialog.html("Saved successfully");
                messageDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function loadWebshipDialog(webshipId) {
        var data = {
            "webshipId": webshipId,
            "customerCode": $("#sel_franchise option:selected").val()
        };
        loadingDialog.dialog("open");
        $.post("manage_customers_webship_load.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Define Save and Cancel buttons
                var buttons = {};
                buttons["Save"] = function () {
                    var formData = $("#webship-form").serialize();
                    loadingDialog.dialog("open");
                    $.post("manage_franchise_webship_edit.ix?reqType=json", formData, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            $("#webship-dialog").html("");
                            $("#webship-dialog").dialog("close");
                            getWebships(1);
                            messageDialog.html("Save successfully");
                            messageDialog.dialog("open");
                        } else if (res.errorCode == "FIELD_ERROR") {
                            $("#webship-dialog").html(res.content);
                        } else {
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
                // Define dialog
                var dialog = $("#webship-dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: "auto",
                    buttons: buttons,
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                // Dialog title
                if (webshipId == "") {
                    dialog.dialog("option", "title", "Add Web Freight User");
                } else {
                    dialog.dialog("option", "title", "Edit Web Freight User");
                }
                dialog.html(res.content);
                dialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function onAddEditUser(isAdd) {
        if (!isAdd) {
            if (webshipId == "") {
                alertDialog.html("Please choose a Web Freight user for editing");
                alertDialog.dialog("open");
            } else {
                loadWebshipDialog(webshipId);
            }
        } else {
            loadWebshipDialog("");
        }
    }


</script>