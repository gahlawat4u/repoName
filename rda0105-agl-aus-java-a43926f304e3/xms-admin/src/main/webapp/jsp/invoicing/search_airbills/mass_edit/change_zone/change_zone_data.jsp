<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_change_zone">
            <table class="s36 d1">
                <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
                <tr>
                    <td><xms:localization text="Zone"/> <span class="s30">*</span></td>
                    <td><input class="form-control ss36" name="txtZone" id="txtZone"/></td>
                </tr>
                <tr>
                    <td><xms:localization text="Recalc Customer Cost"/></td>
                    <td><input type="checkbox" name="checkRecalcCustomerCost" value="true" checked="checked"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>