<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_edit_system_setting" title="Edit System Setting">
    <s:hidden name="page"></s:hidden>
    <s:hidden name="pageSize"></s:hidden>
    <div class="form-group">
        <p class="">
            <xms:localization text="Name"/>
            :<span class="s30">*</span>
        </p>
        <s:textfield name="systemSettingModel.settingName" class="form-control alloptions" placeholder=""
                     maxlength="25"></s:textfield>
    </div>
    <s:hidden name="systemSettingModel.settingId"></s:hidden>
    <s:hidden name="isEdit" value="1"></s:hidden>
    <div class="form-group">
        <p class="">
            <xms:localization text="Value"/>
            :<span class="s30">*</span>
        </p>
        <s:if test="systemSettingModel.listValueDefault != null">
            <s:iterator value="viewListSystemSetting" var="item" status="count">
                <s:if test="viewListSystemSetting.size() > 1">
                    <p class="">
                        <s:property value='key'/>
                    </p>
                    <s:if test="%{value[#count.index].inputDisplay == 'select'}">
                        <s:select list="%{value}" listKey="key" listValue="value" name="multiValue"
                                  class="form-control alloptions" headerKey="0"
                                  headerValue="Please choice a value"></s:select>
                    </s:if>
                    <s:elseif test="%{value[#count.index].inputDisplay == 'text'}">
                        <s:if test="%{value[#count.index].inputDisplay == 'string'}">
                            <s:textarea name="multiValue" class="form-control alloptions" placeholder=""></s:textarea>
                        </s:if>
                        <s:if test="%{value[#count.index].inputDisplay == 'date'}">
							<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
							</span>
                            <input class="form-control form_datetime" type="text" name="multiValue"
                                   data-date-format="dd MM yyyy">
                        </s:if>
                    </s:elseif>
                    <s:elseif test="%{value[#count.index].inputDisplay == 'radio'}">
                        <s:radio list="%{value}" listKey="key" listValue="value" name="multiValue"></s:radio>
                    </s:elseif>
                    <s:else>
                        <s:textarea name="multiValue" class="form-control alloptions" placeholder=""></s:textarea>
                    </s:else>
                </s:if>
                <s:else>
                    <s:if test="%{value[#count.index].inputDisplay == 'select'}">
                        <s:select list="%{value}" listKey="key" listValue="value" name="systemSettingModel.settingValue"
                                  class="form-control alloptions" headerKey="0"
                                  headerValue="Please choice a value"></s:select>
                    </s:if>
                    <s:elseif test="%{value[#count.index].inputDisplay == 'text'}">
                        <s:if test="%{value[#count.index].inputDisplay == 'string'}">
                            <s:textarea name="systemSettingModel.settingValue" class="form-control alloptions"
                                        placeholder=""></s:textarea>
                        </s:if>
                        <s:if test="%{value[#count.index].inputDisplay == 'date'}">
							<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
							</span>
                            <input class="form-control form_datetime" type="text" name="systemSettingModel.settingValue"
                                   data-date-format="dd MM yyyy">
                        </s:if>
                    </s:elseif>
                    <s:elseif test="%{value[#count.index].inputDisplay == 'radio'}">
                        <s:radio list="%{value}" listKey="key" listValue="value"
                                 name="systemSettingModel.settingValue"></s:radio>
                    </s:elseif>
                    <s:else>
                        <s:textarea name="systemSettingModel.settingValue" class="form-control alloptions"
                                    placeholder=""></s:textarea>
                    </s:else>
                </s:else>
            </s:iterator>
        </s:if>
        <s:else>
            <s:textarea name="systemSettingModel.settingValue" class="form-control alloptions"
                        placeholder=""></s:textarea>
        </s:else>
    </div>
    <div class="form-group">
        <p class="">
            <xms:localization text="Edit Admin Level"/>
            :
        </p>
        <s:select list="userLevelList" name="systemSettingModel.userLevel" listKey="userLevelId"
                  listValue="userLevelName" class="form-control alloptions"></s:select>
    </div>
    <div class="form-group">
        <p class="">
            <xms:localization text="Description"/>
            :
        </p>
        <s:textarea name="systemSettingModel.description" class="form-control alloptions" placeholder=""></s:textarea>
    </div>

</s:form>

<s:actionerror/>
<s:actionmessage/>
<s:fielderror/>