<#assign userLv = userLevel?number>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${realPath}styles/xms/css_global.css">
</head>
<body style="background:#FFF">
<div class="portlet box" style="border:0px">
    <div class="portlet-header">
        <div class="caption">${lang.translate('Manage Adjustments')}</div>
        <button class="btn s37 s44 flr" onclick="this.remove();window.print();"><i
                class="fa fa-print fa-fw"></i> ${lang.translate('Prints')}</button>
    </div>
    <div class="portlet-body" style="padding:0px;">
        <div class="tab-content responsive">
            <div id="Overview-tab" class="tab-pane fade in active">
                <div class="row">
                    <div class="col-lg-12">
                        <table class="table mg0">
                            <tr>
                                <th class="s42"></th>
                            </tr>
                        </table>
                        <table class="table table-bordered  table-hover mg0 " id="datatable1" width="1000">
                            <thead>
                            <tr>
                                <th>${lang.translate('Type')}</th>
                                <th>${lang.translate('Airbill #')}</th>
                                <th>${lang.translate('Customer #')}</th>
                                <th>${lang.translate('Request Date')}</th>
                                <th>${lang.translate('Response Date')}</th>
                            <#if userLv < 3>
                                <th>${lang.translate('Carrier Amt. Requested Credited')}</th>
                            </#if>
                                <th>${lang.translate('Franchise Amt.Requested Credited')}</th>
                                <th>${lang.translate('Customer Amt.Requested Credited')}</th>
                                <th>${lang.translate('Status')}</th>
                                <th width="20%">${lang.translate('Reason for deleting Credit Note request')}</th>
                                <th width="20%">${lang.translate('Intial request information')}</th>
                                <th width="20%">${lang.translate('Franchise comments to FSC')}</th>
                            <#if userLv < 3>
                                <th width="20%">${lang.translate('FSC Credit notes')}</th>
                            </#if>
                            </tr>
                            </thead>
                            <tbody>
                            <#if adjustmentList?has_content>
                                <#list adjustmentList as adj>
                                <tr>
                                    <td>${lang.translate(adj.adjustmentType)!" "}</td>
                                    <td>${(adj.airbillNumber)!" "}</td>
                                    <td>${(adj.customerCode)!" "}</td>
                                    <td>${(adj.requestDate)!" "}</td>
                                    <td>${(adj.responseDate)!" "}</td>
                                    <#if userLv < 3>
                                        <td align="right">${(currencySymbol)!"$"}${(adj.carrierAmount)!"0"}</td>
                                    </#if>
                                    <td align="right">${(currencySymbol)!"$"}${(adj.franchiseAmount)!"0"}</td>
                                    <td align="right">${(currencySymbol)!"$"}${(adj.customerAmount)!"0"}</td>
                                    <td>${lang.translate(adj.statusName)!" "}</td>
                                    <td>${(adj.reasonForDeleting)!" "}</td>
                                    <td>${(adj.note)!" "}</td>
                                    <td>${(adj.franchiseCommentsToFsc)!" "}</td>
                                    <#if userLv < 3>
                                        <td>${(adj.fscCreditNote)!" "}</td>
                                    </#if>
                                </tr>
                                </#list>
                            </#if>
                            <#if total??>
                            <tr>
                                <td colspan="13">
                                    <span class="b4"> <b> ${lang.translate('Total Adjustments')}
                                        :</b> ${(total.recordCount)!"0"} </span>
                                    <span class="b4"> <b>| ${lang.translate('Franchise Request Total')}
                                        :</b> ${(currencySymbol)!"$"}${(total.carrierAmount)!"0"} </span>
                                    <span class="b4"> <b>| ${lang.translate('Customer Request Total')}
                                        :</b> ${(currencySymbol)!"$"}${(total.customerAmount)!"0"} </span>
                                    <span class="b4"> <b>| ${lang.translate('Franchise Credit Total')}
                                        :</b> ${(currencySymbol)!"$"}${(total.approvedCarrierAmount)!"0"} </span>
                                    <span class="b4"> <b>| ${lang.translate('Customer Credit Total')}
                                        :</b> ${(currencySymbol)!"$"}${(total.approvedCustomerAmount)!"0"} </span></td>
                            </tr>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>