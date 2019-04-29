<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="note" title="Note" style="min-width:300px;">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <div class="form-group fll mgb">
                    <table class="s36">
                        <tr>
                            <td><xms:localization text="Show"/></td>
                            <td><s:select list="listPageSize" cssClass="form-control" value="10"></s:select></td>
                            <td><xms:localization text="Entries"/></td>
                        </tr>
                    </table>
                </div>
            </th>
        </tr>
        </tbody>
    </table>
    <table class="table table-hover  table-bordered mg0" id="">
        <thead>
        <tr>
            <th><xms:localization text="Create Date"/></th>
            <th><xms:localization text="Note"/></th>
        </tr>
        </thead>
        <tbody>

        <s:if test="!shipmentNoteModels.isEmpty()">
            <s:iterator value="shipmentNoteModels">
                <tr>
                    <td><s:property value="createDate"/></td>
                    <td><s:property value="note"/></td>
                </tr>
            </s:iterator>
        </s:if>
        <s:else>
            <tr>
                <td colspan="2">
                    <xms:localization text="No data available"/> ..
                </td>
            </tr>
        </s:else>
        </tbody>
    </table>
</div>
<!-- End Note -->