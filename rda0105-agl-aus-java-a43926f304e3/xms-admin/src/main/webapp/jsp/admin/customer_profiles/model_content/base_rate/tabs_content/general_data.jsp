<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="portlet-body b12 b11">
        <table class="s36">
            <tbody>
            <tr>
                <td>* = <xms:localization text="May override other settings"/></td>
                <td class="caption b17" colspan="2"><xms:localization text="Base Rates"/></td>
            </tr>
            <TR>
                <td colspan="3" height="5"></td>
            </TR>
            <tr>
                <td>* <xms:localization text="Minimum Customer Base Charge Margin"/></td>
                <td width="60">
                <td width="60"><s:textfield name="cusProfile.minimunBaseCharge" class="form-control"></s:textfield></td>
                </td>
                <td>%</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
