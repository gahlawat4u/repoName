<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<table class="table" style="font-size: 11px; margin-bottom: 0px; max-width: 500px">
    <tr>
        <td class="td2"><s:property value="supply.supplyName"/></td>
    </tr>
    <s:if test="supply.fileData != ''">
        <tr>
            <td class="td2"><img src='<s:property value="supply.image" />' id="img" name="img"
                                 onload="fitimagethumb(this, 120, 100);" style="float: none;" width="200"/></td>
        </tr>
    </s:if>
    <tr>
        <td class="td2"><s:property value="supply.description"/></td>
    </tr>
</table>