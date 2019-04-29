<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="form-group">
    <table class="table table-bordered mg0 table-hover">
        <thead>
        <tr>
            <th><xms:localization text="Level"/></th>
            <th><xms:localization text="Name"/></th>

        </tr>
        </thead>
        <tbody>
        <s:iterator value="levelModels">
            <tr>
                <td><s:property value="userLevelCode"/></td>
                <td><s:property value="userLevelName"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>