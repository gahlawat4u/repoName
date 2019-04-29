<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Menu Editor"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Menu Editor"/></li>
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
                    <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Menu Editor"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <s:hidden id="menu_id" value=""></s:hidden>
                                        <div class="row" id="menu_list_data">
                                            <div class="col-lg-6"
                                                 style="overflow-x: hidden; overflow-y: auto; max-height: 650px !important;">
                                                <table class="table table-bordered">
                                                    <tr>
                                                        <td><s:if test="menus!=null && menus.size()>0">
                                                            <div class="easy-tree">
                                                                <ul>
                                                                    <s:iterator var="menu" value="menus">
                                                                        <li data-parentid="<s:property value='parentId' />"
                                                                            data-menuid="<s:property value='menuId' />">
                                                                            <s:property value="menuName"/> <s:if
                                                                                test="%{#menu.menuChilds!=null && #menu.menuChilds.size()>0}">
                                                                            <s:include value="menu_items_data.jsp"/>
                                                                        </s:if></li>
                                                                    </s:iterator>
                                                                </ul>
                                                            </div>
                                                        </s:if></td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div class="col-lg-6">
                                                <s:form id="form_menu_edit">
                                                    <div id="div_menu_detail">
                                                        <div class="remember_me">
                                                            <ul style="margin-left: 5px; margin-top: 10px; list-style: none;">
                                                                <li style="color: #F00;"><s:fielderror/></li>
                                                                <li style="color: #F00;"><s:actionerror/></li>
                                                            </ul>
                                                        </div>
                                                        <table class="table" style="font-size: 11px;">
                                                            <tr>
                                                                <td class="td1">ID :</td>
                                                                <td class="td2" colspan="2"><s:textfield
                                                                        name="menuModel.menuId"
                                                                        class="form-control alloptions"
                                                                        readonly="true"></s:textfield></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Label"/> :</td>
                                                                <td class="td2" colspan="2"><s:textfield
                                                                        name="menuModel.menuName"
                                                                        class="form-control alloptions"></s:textfield></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Hidden"/>? :
                                                                </td>
                                                                <td class="td2" colspan="2"><s:checkbox
                                                                        name="menuModel.hidden"></s:checkbox></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization text="Admin Level"/>
                                                                    :
                                                                </td>
                                                                <td class="td2" colspan="2"><s:textfield
                                                                        name="menuModel.userLevelId"
                                                                        class="form-control alloptions"></s:textfield></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="td1"><xms:localization
                                                                        text="Other Admin Levels(comma-separated)"/>? :
                                                                </td>
                                                                <td class="td2" colspan="2"><s:textfield
                                                                        name="menuModel.otherLevel"
                                                                        class="form-control alloptions"></s:textfield></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div class="form-actions pal pdt10">
                                                        <div class="row">
                                                            <div class="col-lg-12 text-right">
                                                                <button id="localize-link" class="btn s37" type="button"
                                                                        onclick="showLocalize()">Localize Menu
                                                                </button>
                                                                <button id="" class="btn s37" type="button"
                                                                        onclick="saveMenu()">Save
                                                                </button>
                                                                <button id="show-user-level-link" class="btn s37"
                                                                        type="button" onclick="showUserLevel()">Show
                                                                    User Level
                                                                </button>
                                                                <button id="" class="btn s37" type="submit">Apply Menu
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </s:form>
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
<div id="show-user-level" title="Show User Level"></div>
<div id="show-localize" title="Localize Menu"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.easy-tree').EasyTree({
            selectable: false,
            deletable: false,
            editable: false,
            addable: false
        });
        $('.easy-tree').find("a").each(function () {
            $(this).click(function () {
                var menuId = $(this).closest("li").attr("data-menuid");
                viewMenuDetail(menuId);
                return false;
            });
        });
    });
    var menu_id = "";
    function viewMenuDetail(menuId) {
        menu_id = menuId;
        var data = {
            'menuId': menuId
        };
        var action = "admin_menu_edit.ix?reqType=json";
        loadingDialog.dialog("open");
        $.post(action, data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#div_menu_detail").html(res.content);
            } else {
                $(".remember_me").html(res.errorMsg);
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
        loadingDialog.dialog("close");
    }

    function saveMenu() {
        var data = $('#form_menu_edit').serialize();
        var action = "admin_menu_edit.ix?reqType=json";
        loadingDialog.dialog("open");
        $.post(action, data, function (res) {
            if (res.errorCode == "SUCCESS") {
                alert("Save Menu Success!");
                $("#menu_list_data").html(res.content);
            } else {
                $(".remember_me").html(res.errorMsg);
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
        loadingDialog.dialog("close");
    }

    function showUserLevel() {
        loadDialog("admin_menu_user_level.ix?reqType=json", "", "", "", "Close", "show-user-level", "User Level", "show-user-level");
    }

    function showLocalize() {
        var data = {
            'menuId': menu_id
        };
        loadDialog("admin_menu_localize.ix?reqType=json", data, "show-localize", "Save", "Close", "show-localize", "Localize Menu", "show-localize");
    }
</script>
