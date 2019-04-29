<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_force_markup_customer_cost">
            <table class="s36 d1">
                <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
                <tr>
                    <td><xms:localization text="MarkUp"/> % <span class="s30">*</span></td>
                    <td><input class="form-control ss36" name="txtMarkup" id="txtMarkup"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>