<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_force_quoted_accessorial_charge">
            <table class="s36 d1">
                <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
                <tr>
                    <td><xms:localization
                            text="Are you sure you want set the accessorial customer costs to be the same as the Web Freight quote amounts this airbill(s) "/>?
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>