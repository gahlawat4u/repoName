<!DOCTYPE html>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page import="com.gms.xms.common.constants.AppConstants" %>
<%@ page import="com.gms.xms.common.constants.Attributes" %>
<%@ page import="java.io.*" %>
<%@ page import="java.lang.Exception" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<html lang="en">
<head>
    <title><xms:localization text="Web Freight"/>
    </title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="images/favicon.ico" />
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="styles/webship/css_global.css">
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/jquery_1.10.2.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_migrate.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_min.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_ui.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_html5.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_respond.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_responsive_tabs.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_maxlength.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_datetime_picker.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/bootstrap_dialog.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/jquery.number.min.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/jquery.fileupload.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/customer/bootstrap_webship.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/customer/dt-custom.js"></script>
</head>
<body>
<div>
    <!--BEGIN BACK TO TOP-->
    <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
    <!--END BACK TO TOP-->
    <!--BEGIN TOPBAR-->
    <div id="header-topbar-option-demo" class="page-header-topbar">
        <div class="topbar-main">
            <a id="logo" href="" class="navbar-brand" style="height: 19px;"><span class="logo-text"> 
           <!--  <img src="images/LOGOiN.png" class="classlogo"/> -->
              <%
                            String filePath = WebUtils.getWebLogoPath(request)+WebUtils.getWebshipLoginInfo(request).getProfileCustomerCode()+".png";;
                            File f = new File(filePath);
                          	if(f.exists()){
                          		  %>
                          		  <img alt="" class="classlogo" src="profiles/<%=WebUtils.getWebshipLoginInfo(request).getProfileCustomerCode()%>.png " >
                          		  <%
                          	}else{
                       		String newValue = filePath.substring(filePath.lastIndexOf("/")+1, filePath.lastIndexOf("."));
                       		String franchiseCode= newValue.substring(0, 3);
                       		String filePathDefault =  WebUtils.getWebLogoPath(request)+franchiseCode+".png";
                         	File fdefault = new File(filePathDefault); 
                          	if(fdefault.exists()){
                          			%>
                            		  <img alt="" class="classlogo" src="profiles/<%=franchiseCode%>.png " >
                            		  <%
                     		 }else{
                          			%>
                          		  <img src="images/LOGOiN.png" class="classlogo"/>
                          		  <%
                          	 }
                          	  }
                                %>
            </span></a>

            <div id="cssmenu">
                <ul>
                    <li>
                        <s:a action="webship">
                            <i class="fa fa-truck fa-fw"></i>
                            <span><xms:localization text="SHIP"/></span>
                        </s:a>
                    </li>
                    <li>
                        <s:a action="address_book">
                            <i class="fa fa-credit-card fa-fw"></i>
                            <span><xms:localization text="ADDRESS BOOK"/></span>
                        </s:a>
                    </li>
                    <li>
                        <s:a action="history">
                            <i class=" fa fa-history fa-fw"></i>
                            <span><xms:localization text="HISTORY"/></span>
                        </s:a>
                    </li>
                    <li>
                        <s:a action="quote_job">
                            <i class="fa fa-paper-plane fa-fw"></i>
                            <span><xms:localization text="QUOTE/JOB"/></span>
                        </s:a>
                    </li>
                    <li>
                        <s:a action="settings">
                            <i class="fa fa-cog fa-fw"></i>
                            <span><xms:localization text="SETTINGS"/></span>
                        </s:a>
                    </li>
                    <li>
                        <s:a action="supplies">
                            <i class="fa fa-cubes fa-fw"></i>
                            <span><xms:localization text="SUPPLIES"/></span>
                        </s:a>
                    </li>
                   <%--  <li>
                        <s:a action="invoices">
                            <i class="fa fa-building-o fa-fw"></i>
                            <span><xms:localization text="INVOICES"/></span>
                        </s:a>
                    </li> --%>
                    <li>
                        <s:a action="help">
                            <i class="fa fa-flag fa-fw"></i>
                            <span><xms:localization text="HELP"/></span>
                        </s:a>
                    </li>
                </ul>
            </div>
        </div>
        <!--BEGIN MODAL CONFIG PORTLET-->

        <!--END MODAL CONFIG PORTLET-->
    </div>
    <div class="row" style="margin: 5px">
        <div class="col-xs-12" style="color: red;font-size: 16px">
            <%-- <%=WebUtils.getNotificationText()%> --%>
        </div>
    </div>
    <!--END TOPBAR-->
    <div id="wrapper">
        <!--BEGIN SIDEBAR MENU-->
        <!--BEGIN TITLE & BREADCRUMB PAGE-->
        <div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
            <ol class="breadcrumb page-breadcrumb pull-left s62">
                <li>
                    <i class="fa fa-home"></i>&nbsp;<a href="webship.ix"><xms:localization text="Home"/></a>&nbsp;&nbsp;<i
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
                <div id="login-as-dialog" title="Quick Search" style="z-index: 9999"></div>
                <script type="text/javascript">
                    var loginAsDialog = $("#login-as-dialog").dialog({
                        modal: true,
                        autoOpen: false,
                        width: "600",
                        height: 'auto',
                        maxHeight: 800,
//                        dialogClass: "no-close",
                        show: {
                            effect: "fade",
                            duration: 500
                        }
                    });
                </script>
                <div class="row">
                    <div class="col-lg-12">
                        <ol class=" pull-right">
                            <table class="s36 d1">
                                <tr><td>
                                
                              
                              
                                 </td>
                                    <td>Log in as <span
                                            style="color: #b5121b"><%=WebUtils.getWebshipLoginInfo(request).getName()%></span>
                                    </td>
                                    <s:if test="canLoginAs()">
                                        <td>|</td>
                                        <td>Log-in as</td>
                                        <td><input id="txtLoginAsName" class="form-control"/></td>
                                        <td>
                                            <button class="btn s33 " onclick="doLoginAs()">
                                                <xms:localization text="Log-in"/>
                                            </button>
                                        </td>
                                    </s:if>
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
                <decorator:body/>
            </div>
            <div id="footer">
                <div class="copyright">
                    <a href=""><xms:localization text="2017 © AGL Technology. All Rights reserved."/></a>
                </div>
            </div>
            <div id="loading-dialog" title='<xms:localization text="Loading" />' style="padding: 15px;">
                <div id="fadingBarsG">
                    <div id="fadingBarsG_1" class="fadingBarsG"></div>
                    <div id="fadingBarsG_2" class="fadingBarsG"></div>
                    <div id="fadingBarsG_3" class="fadingBarsG"></div>
                    <div id="fadingBarsG_4" class="fadingBarsG"></div>
                    <div id="fadingBarsG_5" class="fadingBarsG"></div>
                    <div id="fadingBarsG_6" class="fadingBarsG"></div>
                    <div id="fadingBarsG_7" class="fadingBarsG"></div>
                    <div id="fadingBarsG_8" class="fadingBarsG"></div>
                </div>
            </div>
            <div id="alert-dialog" title="<xms:localization text="Alert" />"></div>
            <script type="text/javascript">
                var loadingDialog = $("#loading-dialog").dialog({
                    autoOpen: false,
                    modal: true,
                    dialogClass: "no-close",
                    show: {
                        effect: 'fade',
                        duration: 300
                    }
                });
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

                function doLoginAs() {
                    var name = $("#txtLoginAsName").val();
                    var data = {
                        "name": name
                    };
                    $.post("quick_search_login_as.ix?reqType=json", data, function (res) {
                        if (res.errorCode == "SUCCESS") {
                            loginAsDialog.html(res.content);
                            loginAsDialog.dialog("open");
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
            <!--END FOOTER-->
        </div>
        <!--END PAGE WRAPPER-->
    </div>
</div>
</body>
</html>