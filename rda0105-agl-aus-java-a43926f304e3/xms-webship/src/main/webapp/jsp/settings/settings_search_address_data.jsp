<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:if test="addressList==null || addressList.size()==0">
    No data available...
</s:if>
<s:else>
    <ul class="sss1">
        <s:iterator value="addressList">
            <li><a href="#"
                   onclick='onSelectedAddress($(this),<s:property value="addressId"/>, <s:property value="isFrom"/>);'>
                <p>
                    <s:property value="contactName"/>
                </p>

                <p>
                    <s:property value="companyName"/>
                </p>

                <p>
                    <s:property value="address1"/>
                </p>

                <p>
                    <s:property value="address2"/>
                </p>

                <p>
                    <s:property value="city"/>
                    &nbsp;
                    <s:property value="postalCode"/>
                </p>

                <p>
                    <s:property value="countryName"/>
                </p>
            </a></li>
        </s:iterator>
    </ul>
</s:else>