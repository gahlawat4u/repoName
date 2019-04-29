<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div style="width: 400px">
    <table class="table mg0">
        <tbody>
        <tr>
            <th class="s42">
                <div class="form-group fll mgb"></div>
            </th>
        </tr>
        </tbody>
    </table>
    <table class="table table-hover  table-bordered mg0" id="">
        <thead>
        <tr>
            <th><xms:localization text="Create Date"/></th>
            <th><xms:localization text="Description"/></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="shipmentNoteModel">

            <tr>
                <td><s:property value="createDate"/></td>
                <td><s:property value="note"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>

</div>
<!-- End Note -->
