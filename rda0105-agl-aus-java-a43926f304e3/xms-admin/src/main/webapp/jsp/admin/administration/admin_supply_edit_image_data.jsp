<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<img src="images/supply/<s:property value="fileUploadFileName"/>" width="100"/>
<s:hidden name="supply.image" value="%{fileUploadFileName}"/>