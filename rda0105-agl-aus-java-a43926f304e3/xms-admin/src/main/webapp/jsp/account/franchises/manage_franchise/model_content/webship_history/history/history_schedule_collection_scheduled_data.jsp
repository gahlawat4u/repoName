<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:hidden name="confirmationNumber" id="confirmationNumber"/>
<s:hidden name="isSuccess" id="isSuccess"/>
<s:hidden name="pickupErrorMessage" id="pickupErrorMessage"/>
<div class="col-lg-12">
    <div class="portlet box">
        <div class="portlet-header">
            <div class="caption">
                <xms:localization text="Schedule Collection Result"/>
            </div>
            <div class="tools">
                <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
            </div>
        </div>
        <div class="portlet-body">
            <div class="panel-body pan">
                <s:if test="isSuccess == 'false'">
                    <s:property value="pickupErrorMessage"/>
                </s:if>
                <s:else>
                    <font color="green"><xms:localization text="Schedule collection is successful!"/> <br/></font>
                    <xms:localization text="Your confirmation number is:"/> <strong><s:property
                        value="confirmationNumber"/></strong>
                </s:else>
            </div>
            <div class="form-actions text-right pal pdt10 ">
                <button type="button" class="btn s33" onclick="window.top.close();">
                    <xms:localization text="Close"/>
                </button>
            </div>
        </div>
    </div>
</div>