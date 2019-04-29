$(function () {
    //BEGIN MENU SIDEBAR
    $('#sidebar').css('min-height', '100%');


    $(window).bind("load resize", function () {
        if ($(this).width() < 768) {
            $('div.sidebar-collapse').addClass('collapse');
        } else {
            $('div.sidebar-collapse').removeClass('collapse');
            $('div.sidebar-collapse').css('height', 'auto');
        }
        if ($('body').hasClass('sidebar-icons')) {
            $('#menu-toggle').hide();
        } else {
            $('#menu-toggle').show();
        }
    });
    //END MENU SIDEBAR

    //BEGIN TOOTLIP
    $("[data-toggle='tooltip'], [data-hover='tooltip']").tooltip();
    //END TOOLTIP

    //BEGIN POPOVER
    $("[data-toggle='popover'], [data-hover='popover']").popover();
    //END POPOVER

    //BEGIN THEME SETTING
    $('#theme-setting > a.btn-theme-setting').click(function () {
        if ($('#theme-setting').css('right') < '0') {
            $('#theme-setting').css('right', '0');
        } else {
            $('#theme-setting').css('right', '-250px');
        }
    });

    // Begin Change Theme Color
    var list_style = $('#theme-setting > .content-theme-setting > select#list-style');
    var list_color = $('#theme-setting > .content-theme-setting > ul#list-color > li');
    // FUNCTION CHANGE URL STYLE ON HEAD TAG
    var setTheme = function (style, color) {
        $.cookie('style', style);
        $.cookie('color', color);
        $('#theme-change').attr('href', 'css/themes/' + style + '/' + color + '.css');
    }

    $('.btn-fullscreen').click(function () {

        if (!document.fullscreenElement &&    // alternative standard method
            !document.mozFullScreenElement && !document.webkitFullscreenElement && !document.msFullscreenElement) {  // current working methods
            if (document.documentElement.requestFullscreen) {
                document.documentElement.requestFullscreen();
            } else if (document.documentElement.msRequestFullscreen) {
                document.documentElement.msRequestFullscreen();
            } else if (document.documentElement.mozRequestFullScreen) {
                document.documentElement.mozRequestFullScreen();
            } else if (document.documentElement.webkitRequestFullscreen) {
                document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
            }
        } else {
            if (document.exitFullscreen) {
                document.exitFullscreen();
            } else if (document.msExitFullscreen) {
                document.msExitFullscreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitExitFullscreen) {
                document.webkitExitFullscreen();
            }
        }
    });
    //END FULL SCREEN


    //BEGIN PORTLET
    $(".portlet").each(function (index, element) {
        var me = $(this);
        $(">.portlet-header>.tools>i", me).click(function (e) {
            if ($(this).hasClass('fa-chevron-up')) {
                $(">.portlet-body", me).slideUp('fast');
                $(this).removeClass('fa-chevron-up').addClass('fa-chevron-down');
            }
            else if ($(this).hasClass('fa-chevron-down')) {
                $(">.portlet-body", me).slideDown('fast');
                $(this).removeClass('fa-chevron-down').addClass('fa-chevron-up');
            }
            else if ($(this).hasClass('fa-cog')) {
                //Show modal
            }
            else if ($(this).hasClass('fa-refresh')) {
                //$(">.portlet-body", me).hide();
                $(">.portlet-body", me).addClass('wait');

                setTimeout(function () {
                    //$(">.portlet-body>div", me).show();
                    $(">.portlet-body", me).removeClass('wait');
                }, 1000);
            }
            else if ($(this).hasClass('fa-times')) {
                me.remove();
            }
        });
    });
    //END PORTLET

    //BEGIN BACK TO TOP
    $(window).scroll(function () {
        if ($(this).scrollTop() < 200) {
            $('#totop').fadeOut();
        } else {
            $('#totop').fadeIn();
        }
    });
    $('#totop').on('click', function () {
        $('html, body').animate({scrollTop: 0}, 'fast');
        return false;
    });
    //END BACK TO TOP

    //BEGIN CHECKBOX TABLE
    $('.checkall').on('ifChecked ifUnchecked', function (event) {
        if (event.type == 'ifChecked') {
            $(this).closest('table').find('input[type=checkbox]').iCheck('check');
        } else {
            $(this).closest('table').find('input[type=checkbox]').iCheck('uncheck');
        }
    });
    //END CHECKBOX TABLE


    $('.option-demo').hover(function () {
        $(this).append("<div class='demo-layout animated fadeInUp'><i class='fa fa-magic mrs'></i>Demo</div>");
    }, function () {
        $('.demo-layout').remove();
    });
    $('#header-topbar-page .demo-layout').live('click', function () {
        var HtmlOption = $(this).parent().detach();
        $('#header-topbar-option-demo').html(HtmlOption).addClass('animated flash').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $(this).removeClass('animated flash');
        });
        $('#header-topbar-option-demo').find('.demo-layout').remove();
        return false;
    });
    $('#title-breadcrumb-page .demo-layout').live('click', function () {
        var HtmlOption = $(this).parent().html();
        $('#title-breadcrumb-option-demo').html(HtmlOption).addClass('animated flash').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
            $(this).removeClass('animated flash');
        });
        $('#title-breadcrumb-option-demo').find('.demo-layout').remove();
        return false;
    });
    // CALL FUNCTION RESPONSIVE TABS
    fakewaffle.responsiveTabs(['xs', 'sm']);
    $('.form_datetime').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'dd-mm-yyyy'
        /*format: 'dd-mm-yyyy, HH:ii p'*/
    });
    //BEGIN COUNTER FOR SUMMARY BOX
    counterNum($(".profit h4 span:first-child"), 189, 112, 1, 30);
    counterNum($(".income h4 span:first-child"), 636, 812, 1, 50);
    counterNum($(".task h4 span:first-child"), 103, 155, 1, 100);
    counterNum($(".visit h4 span:first-child"), 310, 376, 1, 500);
    function counterNum(obj, start, end, step, duration) {
        $(obj).html(start);
        setInterval(function () {
            var val = Number($(obj).html());
            if (val < end) {
                $(obj).html(val + step);
            } else {
                clearInterval();
            }
        }, duration);
    }

    //END COUNTER FOR SUMMARY BOX
    //Dialog UI
    $("#open-invoice").dialog({
        autoOpen: false,

        width: 300,
        modal: true,
        buttons: [
            {
                text: "Ok",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "Cancel",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
// Link to open the dialog
    $("#open-invoice-link").click(function (event) {
        $("#open-invoice").dialog("open");
        event.preventDefault();
    });

//Dialog UI
    $("#apply-payment").dialog({
        autoOpen: false,

        width: 900,
        modal: true,
        buttons: [
            {
                text: "Ok",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "Cancel",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
// Link to open the dialog
    $("#apply-payment-link").click(function (event) {
        $("#apply-payment").dialog("open");
        event.preventDefault();
    });
//Dialog UI
    $("#overpayment").dialog({
        autoOpen: false,

        width: 900,
        modal: true,
        buttons: [
            {
                text: "Auto-Apply Payment",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "Reset Payments",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                text: "Save",
                click: function () {
                    $(this).dialog("close");
                }
            },

            {
                text: "Cancel",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
// Link to open the dialog
    $("#overpayment-link").click(function (event) {
        $("#overpayment").dialog("open");
        event.preventDefault();
    });


    $("#notification").dialog({
        autoOpen: false,

        width: 300,
        modal: true,
        buttons: [

            {
                text: "Ok",
                click: function () {
                    $('#overpayment').dialog("open");
                }
            }

        ]
    });
// Link to open the dialog
    $("#notification-link").click(function (event) {
        $("#notification").dialog("open");
        event.preventDefault();
    });


});



