<!DOCTYPE html>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<html lang="en">
<head>
    <title><xms:localization text="Web Freight"/> | <s:property value="pageTitle"/>
    </title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="styles/webship/css_global.css">
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/jquery_1.10.2.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_migrate.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_min.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_ui.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_html5.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_respond.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_dialog.js"></script>
</head>
<body>
<div>

    <!--BEGIN BACK TO TOP-->
    <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
    <!--END BACK TO TOP-->
    <!--BEGIN TOPBAR-->
    <div id="header-topbar-option-demo" class="page-header-topbar">
        <div class="topbar-main">
            <a id="logo" href="" class="navbar-brand" style="height: 19px;"><span class="logo-text"> <img
                    src="images/LOGOiN.png" class="classlogo"/></span></a>

            <div id="cssmenu">
                <ul>
                    <li>
                        <a>
                            <i class="fa fa-truck fa-fw"></i>
                            <span><xms:localization text="SHIP"/></span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="fa fa-credit-card fa-fw"></i>
                            <span><xms:localization text="ADDRESS BOOK"/></span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class=" fa fa-history fa-fw"></i>
                            <span><xms:localization text="HISTORY3333"/></span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="fa fa-paper-plane fa-fw"></i>
                            <span><xms:localization text="QUOTE/JOB"/></span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="fa fa-cog fa-fw"></i>
                            <span><xms:localization text="SETTINGS"/></span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="fa fa-cubes fa-fw"></i>
                            <span><xms:localization text="SUPPLIES"/></span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="fa fa-building-o fa-fw"></i>
                            <span><xms:localization text="INVOICES"/></span>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="fa fa-flag fa-fw"></i>
                            <span><xms:localization text="HELP"/></span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!--BEGIN MODAL CONFIG PORTLET-->

        <!--END MODAL CONFIG PORTLET-->
    </div>
    <!--END TOPBAR-->
    <div id="wrapper">
        <!--BEGIN SIDEBAR MENU-->
        <!--BEGIN TITLE & BREADCRUMB PAGE-->
        <div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
            <ol class="breadcrumb page-breadcrumb pull-left s62">
                <li>
                    <i class="fa fa-home"></i>&nbsp;<a><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
                        class="fa fa-angle-right"></i>&nbsp;&nbsp;
                </li>
                <li class="hidden">
                    <a href="#"><xms:localization text="Ship"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
                </li>
                <li class="active"><s:property value="breadCrumb"/></li>
            </ol>
            <ol class="breadcrumb page-breadcrumb pull-right">
                <span class="badge badge-info s61"><i class="fa fa-comments s60"></i></span>
                <span class="badge badge-info s61"><i class="fa fa-phone s60" style="padding-top: 1px;"></i></span>
                <span class="badge badge-info s61"><i class="fa fa-envelope s60"></i></span>
            </ol>
            <div class="clearfix"></div>
        </div>
        <!--END TITLE & BREADCRUMB PAGE-->
        <!--BEGIN CONTENT-->
        <form id="login_as_order_form">
            <s:hidden id="login_as_table_field" name="orderField" value="customer_code"/>
            <s:hidden id="login_as_table_type" name="orderType" value="0"/>
        </form>
        <div id="page-wrapper">
            <div class="page-content">

                <div class="row">
                    <div class="col-lg-12">
                        <ol class=" pull-right">
                            <table class="s36 d1">
                                <tr>
                                    <td><s:a action="logout" class="btn s33">
                                        <xms:localization text="Log-out"/>
                                    </s:a></td>
                                    <td><select class="form-control ">
                                        <option>English</option>
                                    </select></td>
                                </tr>
                            </table>
                        </ol>
                    </div>
                </div>


                <div id="alertPopup" class="alert alert-success" role="alert"
                     style="display: none">
                    <i class="fa fa-check fa-fw"></i> <span
                        id="save-success-message"></span>
                </div>
                <div id="setting-div">
                    <decorator:body/>
                </div>
                <script type="text/javascript">
                    function doChangePass() {
                        var data = $("#frmChangePassword").serialize();
                        $.post("require_change_password.ix?reqType=json", data, function (res) {
                            if (res.errorCode == "SUCCESS") {
                                window.location.replace("webship.ix")
                            } else if (res.errorCode == "FIELD_ERROR") {
                                $("#setting-div").html(res.content);
                                $("#alertPopup").hide();
                            } else {
                                $("#alertPopup").hide();
                                alertDialog.html(res.errorMsg);
                            }
                        }).fail(function () {
                            $("#alertPopup").hide();
                            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                            alertDialog.dialog("open");
                        });
                    }


                </script>
            </div>
            <div id="footer">
                <div class="copyright">
                    <a href=""><xms:localization text="2017 © AGL Technology. All Rights reserved."/></a>
                </div>
            </div>

            <div id="alert-dialog" title="<xms:localization text="Alert" />"></div>
            <script type="text/javascript">
                var alertDialog = $("#alert-dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    maxHeight: 800,
                    minWidth: 400,
                    buttons: {
                        '<xms:localization text="Close" />': function () {
                            $(this).dialog("close");
                        }
                    }
                });


            </script>
        </div>
    </div>
</div>
</body>
</html>