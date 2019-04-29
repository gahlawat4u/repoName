<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><xms:localization text="Web Freight"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="icon" href="images/favicon.ico" />
    <link rel="shortcut icon" href="images/favicon.ico" />
    <title><xms:localization text="Login Form"/></title>
    <link href="<%=WebUtils.getContextPathURL(request)%>styles/webship/css_global.css" rel="stylesheet"/>
    <script src="<%=WebUtils.getContextPathURL(request)%>script/webship/vendor/jquery_1.10.2.js" type="text/javascript"
            language="javascript"></script>
    <style>
        .s36 tr td {
            font-size: 12px;
            padding: 3px 5px;
        }

        .portlet-body {
            border-bottom-left-radius: 4px !important;
            border-bottom-right-radius: 4px !important;
        }

        .b19 {
            background: #fff none repeat scroll 0 0;
            border: 6px solid rgba(221, 218, 215, 0.23);
            border-radius: 2px;
            list-style: outside none none;
            margin-top: 20px;
            margin-bottom: 20px;
            width: 100%;
            height: auto !important;
            padding: 5px;
        }

        textarea:focus, input[type="text"]:focus, input[type="password"]:focus, input[type="datetime"]:focus, input[type="datetime-local"]:focus, input[type="date"]:focus, input[type="month"]:focus, input[type="time"]:focus, input[type="week"]:focus, input[type="number"]:focus, input[type="email"]:focus, input[type="url"]:focus, input[type="search"]:focus, input[type="tel"]:focus, input[type="color"]:focus, .uneditable-input:focus {
            border-color: #DAF2D5;
            box-shadow: 0 0 0.2em #5eb24d;
            outline: 0 none;
        }

        input[type="submit"] {
            background: #20358c none repeat scroll 0 0;
            border: medium none;
            border-radius: 2px !important;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2), 0 -1px 0 rgba(0, 0, 0, 0.1) inset;
            color: #fff;
            cursor: pointer;
            display: inline-block;
            float: right;
            font-family: "Open Sans", sans-serif;
            font-size: 12px;
            font-weight: 600;
            margin-top: 3px;
            outline: medium none;
            padding: 10px 6px;
            transition: all 0.1s ease 0s;
            width: 20%;
            margin-top: 15px;
            margin-bottom: 15px;
        }

        .btn {
            background: #5EB34D none repeat scroll 0 0;
            border: medium none;
            border-radius: 2px !important;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2), 0 -1px 0 rgba(0, 0, 0, 0.1) inset;
            color: #fff;
            cursor: pointer;
            display: inline-block;
            font-family: "Open Sans", sans-serif;
            font-size: 12px;
            font-weight: 600;
            outline: medium none;
            padding: 10px 6px;
            transition: all 0.1s ease 0s;
            width: 20%;
            margin-top: 15px;
            margin-right: 10px;
            margin-bottom: 15px;
        }

        .b20 {
            color: #5D6AA0 !important;
            font-size: 15px !important;
            margin-left: 5px;
            margin-top: 2px;
        }

        .portlet-header {
            border-top-left-radius: 20px !important;
            border-top-right-radius: 4px !important;
        }

        .portlet {
            box-shadow: none !important
        }

        .s3 {
            margin-left: 20px;
        }

        .s4 {
            font-size: 12px;
            line-height: 20px;
        }

        .s1 {
            font-weight: bold;
            margin-left: -10px;
            color: #5EB24D;
        }

        .s3 {
            margin-left: 20px;
        }

        .s2::after {
            content: "";
        }

        .s543 {
            color: #5d6aa0
        }

        .s543:hover {
            text-decoration: underline
        }

        .s544 {
            margin-top: 20px;
            margin-left: 10px;
            font-size: 12px !important;
            color: #848383 !important;
        }

        input[type=radio], input[type=checkbox] {
            margin: 4px 2px 0;
            margin-top: 1px \9;
            line-height: normal
        }

        .s545 {
            position: relative;
            top: -2px;
        }
    </style>
</head>
<body class="b13">
<div>
    <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>

    <div id="wrapper" class="b14">
        <div id="page-wrapper" class="b14">
            <decorator:body/>
        </div>
    </div>
</div>
</body>
</html>
