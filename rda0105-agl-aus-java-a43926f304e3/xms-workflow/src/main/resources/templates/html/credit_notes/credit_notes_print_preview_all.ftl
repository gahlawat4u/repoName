<html lang="en">
<head>
    <title>Credit Notes PDF</title>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="${(realPath)!" "}styles/xms/css_global.css">
    <style type="text/css">
        @media print {
            @page {
                size: landscape
            }
        }
    </style>
</head>
<body style="background:#FFF">
<div>
    <!--END TOPBAR-->
    <div id="wrapper">
        <!--BEGIN SIDEBAR MENU-->
        <div id="page-wrapper">
            <!--BEGIN TITLE & BREADCRUMB PAGE-->
            <!--END TITLE & BREADCRUMB PAGE-->
            <!--BEGIN CONTENT-->
            <div class="portlet box" style="border:0px">
                <button class="btn s37 s44 flr" onclick="this.remove();window.print();"><i
                        class="fa fa-print fa-fw"></i> ${lang.translate('Prints')}</button>
            <#include "credit_notes_print_preview_all_content.ftl">
            </div>
        </div>
        <!--END CONTENT-->
        <!--BEGIN FOOTER-->
        <!--END FOOTER-->
    </div>
    <!--END PAGE WRAPPER-->
</div>
</div>
</body>
</html>