<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="frmDeleteAdjustment">
    <s:hidden name="noteType"/>
    <s:hidden name="adjustmentId"/>
    <div class="form-group">
        <p class="s30">
            <xms:localization text="Reason for deleting"/>
        </p>
        <textarea name="note" class="form-control alloptions ckeditor b7" cols="80" style="height: 100px;"><s:property
                value="note"/></textarea>
    </div>
</form>