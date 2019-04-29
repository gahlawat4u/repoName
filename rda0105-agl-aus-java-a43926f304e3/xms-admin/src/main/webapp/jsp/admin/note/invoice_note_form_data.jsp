<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="invoice_note_form">
    <s:hidden name="note.accountNo"/>
    <s:hidden name="note.invoiceCode"/>
    <table class="s36">
        <tr>
            <th colspan="2" class="text-center"><xms:localization text="Adding a Note"/></th>
        </tr>
        <tr>
            <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2"><span style="color: red"><s:fielderror/></span></td>
        </tr>
        <tr>
            <td><xms:localization text="Customer #:"/></td>
            <td><s:property value="note.accountNo"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Invoice No:"/></td>
            <td><s:property value="note.invoiceCode"/></td>
        </tr>
        <tr>
            <td colspan="2"><xms:localization text="Note:"/><span style="color: red">*</span> <s:textarea
                    name="note.note" cssClass="form-control" cols="40" rows="5"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <table class="s36">
                    <tr>
                        <td><s:checkbox name="note.check"/></td>
                        <td><xms:localization text="Follow Up Date:"/><span style="color: red">*</span></td>
                        <td>
                            <div class="form-group input-group" style="width: 150px; margin-bottom: 0px;">
                                <s:textfield name="note.followUpDate" cssClass="form-control form_datetime"
                                             data-date-format="dd MM yyyy" readonly="true"/>
                                <span class="input-group-addon s31"><i class="fa fa-calendar"></i></span>
                            </div>
                        </td>
                    </tr>
                </table>
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