<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="note-form">
    <s:hidden name="note.noteId"/>
    <s:hidden name="note.userId"/>
    <s:hidden name="note.accountNo"/>
    <s:hidden name="note.noteType"/>
    <s:hidden name="note.modifyDate"/>
    <s:hidden name="note.current"/>
    <s:hidden name="note.invoiceCode"/>
    <s:hidden name="note.paymentId"/>
    <table>
        <tr>
            <td colspan="3"><s:if test="hasActionErrors()">
                <s:actionerror/>
            </s:if> <s:if test="hasActionMessages()">
                <s:actionmessage/>
            </s:if> <s:if test="hasFieldErrors()">
                <s:fielderror/>
            </s:if></td>
        </tr>
        <tr>
            <td><xms:localization text="Customer"/> #:</td>
            <td><s:property value="note.accountNo"/></td>
        </tr>
        <tr>
            <td colspan="3"><xms:localization text="Note"/>: <span style="color: red">*</span><br/> <s:textarea
                    name="note.note" cols="60" rows="10" cssClass="form-control"></s:textarea></td>
        </tr>
        <tr>
            <td colspan="3" style="height: 5px;"></td>
        </tr>
        <tr>
            <td><s:checkbox name="note.check"/> <xms:localization text="Follow Up Date"/>: <span
                    style="color: red">*</span></td>
            <td>
                <div class="form-group input-group" style="width: 150px; margin-bottom: 0px;">
					<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
					</span> <input name="note.followUpDate" class="form-control form_datetime" type="text"
                                   data-date-format="dd MM yyyy" value='<s:property value="note.followUpDate"/>'
                                   readonly="readonly"/>
                </div>
            </td>
        </tr>
    </table>
</form>
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