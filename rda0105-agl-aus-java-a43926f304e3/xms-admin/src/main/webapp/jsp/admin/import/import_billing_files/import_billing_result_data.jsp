<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="countSuccess==0 or countSuccess==null">
    <s:actionmessage/>
    <s:fielderror/>
    <s:if test="countUnSuccess!=0 and countUnSuccess!=null">
        <s:property value="countUnSuccess"/> <xms:localization text="billing"/>(s) <xms:localization
            text="already exists."/>
    </s:if>
    <table>
        <s:iterator value="errorList">
            <tr>
                <td><s:property/></td>
            </tr>
        </s:iterator>
    </table>
</s:if>
<s:else>
    <xms:localization text="Import"/> <s:property value="countSuccess"/> <xms:localization text="airbill"/>(s)
    <xms:localization text="successfully!"/> <br/>
    <s:if test="countUnSuccess!=0 and countUnSuccess!=null">
        <s:property value="countUnSuccess"/> <xms:localization text="billing"/>(s) <xms:localization
            text="already exists."/>
    </s:if>
    <table>
        <s:iterator value="errorList">
            <tr>
                <td><s:property/></td>
            </tr>
        </s:iterator>
    </table>
</s:else>