<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>AGL</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="cache-control" content="max-age=0"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <link rel="icon" href="images/favicon.ico" />
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="<%=WebUtils.getContextPathURL(request)%>styles/xms/css_global.css">
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery_1.10.2.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_migrate.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_ui.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_dropdown.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_html5.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_respond.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_menu.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_responsive_tabs.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_datetimepicker.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_maxlength.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dt-custom.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/ckeditor/ckeditor.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.number.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileupload.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.smartmenus.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.smartmenus.bootstrap.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/common/common.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/bootstrap_easytree.js"></script>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/bootstrap_xms.js"></script>
    <style type="text/css">
        .portlet {
            border: 3px solid #314275 !important;
        }

        .caption {
            color: #314275;
        }

        .required-field {
            color: red;
        }

        .errorMessage {
            color: red;
            font-size: 11px;
        }

        .b13 {
            background: url(images/bg.jpg) no-repeat fixed center center/cover !important;
            background-color: !important;
            height: 100% !important;
            width: 100% !important;
            height: 100% !important;
        }

        .b14 {
            background: none !important;
            border: none !important;
        }
    </style>
</head>
<body class="b13">
<div class="page-content">
    <decorator:body/>
</div>
<div id="footer">
    <div class="copyright">
        <a href=""><xms:localization text="2017 © AGL Responsive Multi-Purpose Template"/></a>
    </div>
</div>
<!--END FOOTER-->
</body>
</html>