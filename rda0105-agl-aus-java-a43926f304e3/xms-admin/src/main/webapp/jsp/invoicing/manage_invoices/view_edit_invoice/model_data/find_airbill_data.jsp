<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_find_airbill">
    <s:hidden name="invoiceId"></s:hidden>
    <div id="md-4" title="Find Airbill">
        <div class="form-group">
            <p align="center">
                <b><xms:localization text="Find Airbill"/></b>
            </p>
            <table class="table" style="font-size: 11px;">
                <tr>
                    <td class="td1"><xms:localization text="Airbill#:"/><span class="s30">*</span></td>
                    <td class="td2"><input class="form-control alloptions" type="text" placeholder="" maxlength="25"
                                           name="airbillNumber"></td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
<script type="text/javascript">

</script>
