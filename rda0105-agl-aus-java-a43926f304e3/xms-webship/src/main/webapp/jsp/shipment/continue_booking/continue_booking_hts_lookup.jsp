<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="lookup" title="Look up">
    <div class="col-lg-12 flr pd0">
        <div class="form-group fll mgb">
            <table class="s36" style="margin-bottom: 10px;">
                <tr>
                    <td><input class="form-control " type="text"/></td>
                    <td>
                        <button class="btn s33" type="submit">
                            <xms:localization text="Search"/>
                        </button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <table class="table table-hover  table-bordered mg0" id="">
        <thead>
        <tr bgcolor="#F5F5F5">
            <th><xms:localization text="Code"/></th>
            <th><xms:localization text="Description"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="!listHtsGood.isEmpty()">
            <s:iterator value="listHtsGood">
                <tr data-code="<s:property value="code"/>" data-description="<s:property value="description"/>">
                    <td><s:property value="code"/></td>
                    <td><s:property value="description"/></td>
                </tr>
            </s:iterator>
        </s:if>
        </tbody>
    </table>
    <br>
</div>