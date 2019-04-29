<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_recalc_franchise_base">
            <table class="s36 d1">
                <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
                <tr>
                    <td><xms:localization text="Are you sure you want to recalculate Franchise Base Charge "/>?</td>
                </tr>
            </table>
        </form>
    </div>
</div>