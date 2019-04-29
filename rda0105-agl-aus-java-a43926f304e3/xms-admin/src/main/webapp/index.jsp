<META HTTP-EQUIV="Refresh" CONTENT="0;URL=statistics.ix">
<%---- %@page import="com.gms.xms.weblib.utils.WebUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%> 
<%  
String contextPath = WebUtils.getContextPathURL(request);
response.sendRedirect(contextPath+"statistics.ix");
--%>
