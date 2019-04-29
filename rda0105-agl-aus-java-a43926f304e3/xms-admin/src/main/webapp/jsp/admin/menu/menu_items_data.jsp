<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<ul>
    <s:iterator value="menuChilds">
        <li data-parentid="<s:property value='parentId' />" data-menuid="<s:property value='menuId' />"><s:property
                value="menuName"/> <s:if test="menuChilds!=null && menuChilds.size()>0">
            <s:include value="menu_items_data.jsp"/>
        </s:if></li>
    </s:iterator>
</ul>
