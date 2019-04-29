<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/xms-tags" prefix="xms" %>
<s:if test="!fieldErrors.isEmpty()">
    <div class="alert alert-danger" role="alert">
        <div class="row">
            <div class="col-lg-1" style="width: 10px;">
                <i class="fa fa-exclamation-triangle fa-fw"> </i>
            </div>
            <div class="text-left">
                <s:fielderror/>
            </div>
        </div>
    </div>
</s:if>