<%@page import="com.gms.xms.common.constants.AppConstants.ENCODE_TYPE" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="form-body pal">
    <div class="row">
        <div class="col-lg-12">
            <div class="form-group">
                <label class="control-label" for="inputName">
                    <xms:localization text="Special Instructions"/>
                </label>
                <s:textfield type="text"
                             name="shipmentRequestModel.scheduleCollection.specialInstructions"
                             class="form-control"/>
            </div>
        </div>
    </div>
</div>