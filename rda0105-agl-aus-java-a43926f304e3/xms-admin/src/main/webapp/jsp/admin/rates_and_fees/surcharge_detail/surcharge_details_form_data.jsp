<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div class="form-group">
    <s:if test="hasActionErrors()">
        <div class="alert alert-danger" role="alert">
            <s:actionerror/>
        </div>
    </s:if>
    <s:form id="accessorial-detail-form">
        <table class="table" style="font-size: 11px;">
            <tbody>
            <tr>
                <td class="td1"><xms:localization text="Default Charge"/>:<span class="s30">*</span></td>
                <td class="td2 s51" colspan="2"><s:textfield name="accessorialDetail.defaultCharge"
                                                             cssClass="form-control"
                                                             onkeypress="return formartNumber(event, this, true)"/>
                    <span class="text-danger"><s:fielderror fieldName="accessorialDetail.defaultCharge"/></span>
                </td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Default Carrier Charge"/>:</td>
                <td class="td2 s51" colspan="2"><s:textfield name="accessorialDetail.defaultChargeCarrier"
                                                             cssClass="form-control"
                                                             onkeypress="return formartNumber(event, this, true)"/>
                    <span class="text-danger"><s:fielderror fieldName="accessorialDetail.defaultChargeCarrier"/></span>
                </td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Apply Start Date"/>:<span class="s30">*</span></td>
                <td class="td2" colspan="2">
                    <div class="form-group input-group" style="margin-bottom: 0px;">
							<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
							</span>
                        <s:textfield name="accessorialDetail.applyStartDate" cssClass="form-control form_datetime"
                                     type="text" data-date-format="dd MM yyyy" readonly="true"/>
                    </div>
                    <span class="text-danger"><s:fielderror fieldName="accessorialDetail.applyStartDate"/></span></td>
            </tr>
            </tbody>
        </table>
        <s:hidden name="accessorialId"/>
        <s:hidden name="oldApplyStartDate"/>
        <s:hidden name="page"/>
        <s:hidden name="pageSize"/>
    </s:form>
    <script type="text/javascript">
        $('.form_datetime').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0,
            format: 'dd-mm-yyyy'
        });
    </script>
</div>
