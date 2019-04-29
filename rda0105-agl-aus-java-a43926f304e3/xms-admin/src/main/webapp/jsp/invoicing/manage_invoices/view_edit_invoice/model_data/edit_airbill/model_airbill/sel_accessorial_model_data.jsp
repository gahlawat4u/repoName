<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:select headerKey="" headerValue="" list="accessorials" listKey="description" listValue="description"
          value="description" name="accessorialInfoModels[0].description" cssClass="form-control sel_accessorial"
          onchange="changeAccessorial(this.id)"/>
										