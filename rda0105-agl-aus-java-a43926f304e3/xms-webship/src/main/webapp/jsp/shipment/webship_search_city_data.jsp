<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:if test="cities==null || cities.size()==0">
</s:if>
<s:else>
    <ul class="sss1">
        <s:iterator value="cities">
            <li onclick="onCityClick($(this),<s:property value="isSender"/>)">
                <div class="row">
                    <div class="col-xs-6" data-cityName="<s:property value="cityName" />">
                        <s:property value="cityName"/>
                    </div>
                    <div class="col-xs-3" data-postalCode="<s:property value="postalCode" />">
                        <s:property value="postalCode"/>
                    </div>
                    <div class="col-xs-3" data-stateCode="<s:property value="stateCode" />">
                        <s:property value="stateCode"/>
                    </div>
                </div>
            </li>
        </s:iterator>
    </ul>
</s:else>