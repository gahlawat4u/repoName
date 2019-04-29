<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table table-hover  table-bordered mg0" id="datatable_lookup">
    <thead>
    <tr bgcolor="#F5F5F5">
        <th><xms:localization text="Code"/></th>
        <th><xms:localization text="Description"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="!listHtsGood.isEmpty()">
        <s:iterator value="listHtsGood">
            <tr data-code="<s:property value="code"/>" data-description="<s:property value="description"/>">
                <td><s:property value="code"/> <s:hidden name="code" id="txt_hts_code"></s:hidden></td>
                <td><s:property value="description"/></td>
            </tr>
        </s:iterator>
    </s:if>
    </tbody>
</table>
<script type="text/javascript">
    var txt_hts_code = "";
    $(document).ready(function () {
        $('table#datatable_lookup tbody tr').click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
        });
        $('table#datatable_lookup tbody tr').dblclick(function () {
            txt_hts_code = $(this).find('#txt_hts_code').val();
            $("table#packing_list_table tr:eq(" + htsCodeIndex + ")").find("#hts_lookup").val(txt_hts_code);
            $("#hts_good_lookup_load").dialog("close");
        });
    });


</script>