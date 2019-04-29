<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_mass_edit_recalc_customer_base_charge">
            <table class="s36 d1">
                <s:hidden name="listAirbillStr"/>
                <tr>
                    <td><xms:localization text="Are you sure you want to recalculate Customer Base Charge "/>?</td>
                </tr>
            </table>
        </form>
    </div>
</div>