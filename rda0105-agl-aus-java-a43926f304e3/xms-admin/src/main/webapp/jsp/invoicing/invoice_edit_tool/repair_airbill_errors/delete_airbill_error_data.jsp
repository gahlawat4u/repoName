<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_delete_airbill_error">
            <table class="s36 d1">
                <s:hidden name="airbillNumber"/>
                <tr>
                    <td><xms:localization text="Are you sure you want to delete this airbill"/> # <s:property
                            value="airbillNumber"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>