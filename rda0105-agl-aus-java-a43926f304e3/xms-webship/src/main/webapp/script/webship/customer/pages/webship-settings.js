$(document).ready(function () {
    $('body').click(function () {
        $('.sss1').hide();
    });
    $('#s-from-addr-company').live("keyup", function () {
        var searchQuery = $(this).val();
        $('.focus').removeClass('focus');
        $(this).addClass("focus");
        $('.address-search:not(.focus)').val('');
        clearTimeout($.data(this, 'timer'));
        if (searchQuery == '') {
            $('#s-from-by-company-result').hide();
        } else {
            $(this).data('timer', setTimeout(function () {
                var action = "settings_search_address.ix?reqType=json";
                var data = {
                    'webshipSettingModel.companyName': searchQuery,
                    'webshipSettingModel.addressSearchType': 'from'
                };
                var divId = "s-from-by-company-result";
                doPostDataByParameters(action, data, '', divId);
                $('#s-from-by-company-result').show();
            }, 500));
        }
    });
    $('#s-from-addr-contact').live("keyup", function () {
        var searchQuery = $(this).val();
        $('.focus').removeClass('focus');
        $(this).addClass("focus");
        $('.address-search:not(.focus)').val('');
        clearTimeout($.data(this, 'timer'));
        if (searchQuery == '') {
            $('#s-from-by-contact-result').hide();
        } else {
            $(this).data('timer', setTimeout(function () {
                var action = "settings_search_address.ix?reqType=json";
                var data = {
                    'webshipSettingModel.contactName': searchQuery,
                    'webshipSettingModel.addressSearchType': 'from'
                };
                var divId = "s-from-by-contact-result";
                doPostDataByParameters(action, data, '', divId);
                $('#s-from-by-contact-result').show();
            }, 500));
        }
    });
    $('#s-to-addr-company').live("keyup", function () {
        var searchQuery = $(this).val();
        $('.focus').removeClass('focus');
        $(this).addClass("focus").siblings();
        $('.address-search:not(.focus)').val('');
        clearTimeout($.data(this, 'timer'));
        if (searchQuery == '') {
            $('#s-to-by-company-result').hide();
        } else {
            $(this).data('timer', setTimeout(function () {
                var action = "settings_search_address.ix?reqType=json";
                var data = {
                    'webshipSettingModel.companyName': searchQuery,
                    'webshipSettingModel.addressSearchType': 'to'
                };
                var divId = "s-to-by-company-result";
                doPostDataByParameters(action, data, '', divId);
                $('#s-to-by-company-result').show();
            }, 500));
        }
    });
    $('#s-to-addr-contact').live("keyup", function () {
        var searchQuery = $(this).val();
        $('.focus').removeClass('focus');
        $(this).addClass("focus");
        $('.address-search:not(.focus)').val('');
        clearTimeout($.data(this, 'timer'));
        if (searchQuery == '') {
            $('#s-to-by-contact-result').hide();
        } else {
            $(this).data('timer', setTimeout(function () {
                var action = "settings_search_address.ix?reqType=json";
                var data = {
                    'webshipSettingModel.contactName': searchQuery,
                    'webshipSettingModel.addressSearchType': 'to'
                };
                var divId = "s-to-by-contact-result";
                doPostDataByParameters(action, data, '', divId);
                $('#s-to-by-contact-result').show();
            }, 500));
        }
    });
});

function showSelectedFromAddress(addressId, type) {
    var action = "settings_get_customer_address.ix?reqType=json";
    var data = {
        'webshipSettingModel.addressId': addressId,
        'webshipSettingModel.addressSearchType': type
    }
    var message = "";
    var divId = "";
    if (type == 'from') {
        var divId = "default-from-address-content";
    } else {
        var divId = "default-to-address-content";
    }
    doPostDataByParameters(action, data, message, divId);
}
