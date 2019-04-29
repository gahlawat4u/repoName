<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="webship-form">
    <s:hidden name="webship.webshipId"/>
    <s:hidden name="webship.customerCode"/>
    <div class="form-group">
        <s:if test="hasActionErrors()">
            <s:actionerror/>
        </s:if>
        <s:if test="hasActionMessages()">
            <s:actionmessage/>
        </s:if>
        <s:if test="hasFieldErrors()">
            <s:fielderror/>
        </s:if>
    </div>
    <div class="form-group">
        <p>
            <b><xms:localization text="NOTE"/>:</b><br/> <span class="s30"><xms:localization
                text="Password should contains minimum 8 characters with at least one alphabet and one number."/></span>
        </p>
        <label><xms:localization text="Alternate User "/>:<span class="s30">*</span></label>
        <s:textfield name="webship.name" cssClass="form-control alloptions" maxLength="25"/>
    </div>
    <div class="form-group">
        <label><xms:localization text="Password "/>:<span class="s30">*</span></label> <input name="webship.password"
                                                                                              type="password"
                                                                                              value='<s:property value="webship.password" />'
                                                                                              class="form-control alloptions"
                                                                                              maxlength="25"/>
    </div>
    <div class="form-group">
        <label><xms:localization text="Language "/>:</label> <select name="webship.language"
                                                                     class="form-control alloptions">
        <option value="0"><xms:localization text="English"/></option>
    </select>
    </div>
    <div class="form-group">
        <table class="s36">
            <tr>
                <td width="25"><s:checkbox name="webship.allowExportAddressBook"/></td>
                <td><xms:localization text="Allow Address Book Export"/></td>
            </tr>
            <tr>
                <td width="25"><s:checkbox name="webship.isRequireChangePassword"/></td>
                <td><xms:localization text="Force Password Change"/></td>
            </tr>
        </table>
    </div>
</form>