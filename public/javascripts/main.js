function toggleNext(toggler_id) {
    $(document).on("click", function (e) {
        if (e.target.id == toggler_id) {
            $("#" + toggler_id).next().show();
        } else {
            $("#" + toggler_id).next().hide();
        }
    });
}

function slideNext(div) {
    $(div).toggle();
    $(div).next().toggle();
}

function slideBack(div) {
    $(div).toggle();
    $(div).prev().toggle();
}

;
(function ($) {
    'use strict';

    //variables
    var $happenings = $('#happenings');
    var $event_categories = $('#events-categories');
    var $event_categories_nav = $('.events-categories-nav');

    //help popups question marks
    var $help_poly = $('#help-poly');

    var $onoffswitch = $('.onoffswitch');

    //edit profile
    var $sign_up_edit_profile = document.getElementById('sign-up-edit-profile');
    var $avatar_button = $('#avatar-button');

    //profile and update profile
    var $follow_button = $('#follow');
    var $edit_name_head = $('#edit-name-head');
    var $save_name_head = $('#save-name-head');
    var $edit_contact_info = $('#edit-contact-info');
    var $save_contact_info = $('#save-contact-info');
    var $name_head_edit = $('#name-head-edit-mode');
    var $profile_pic_edit_button = $("#profile-pic-edit");
    var $profile_pic_popover = $("#profile-pic-popover");

    var listItems;

    //home page navbar click events:
    $happenings.on('click', 'li', function (e) {
        e.preventDefault();
        var code = $(this).data('code');
        $('#happenings li').removeClass('selected');
        $(this).addClass('selected');

        if (code === 'events') {
            $event_categories_nav.slideDown('fast');
        } else {
            $event_categories_nav.slideUp('fast');
        }

        $('.happenings-list .content').addClass('hidden');
        $('#' + code + '-content').removeClass('hidden');
    });

    $event_categories.on('click', 'li', function (e) {
        e.preventDefault();
        var code = $(this).data('code');
        $('#events-categories li').removeClass('selected');
        $(this).addClass('selected');

        $('#events-content .event-category').addClass('hidden');
        $('#' + code + '-events-list').removeClass('hidden');
    });

    $help_poly.on('click', function (e) {
        e.preventDefault();
        $('.help-poly').toggle();
    });

    //sign up edit profile completeness
    $("input, select").change(function (e) {
        var checked = 0;
        var radio_checked = 0;
        for (var i = 0; i < $sign_up_edit_profile.length; i++) {
            if ($sign_up_edit_profile[i].type !== 'radio') {
                if ($sign_up_edit_profile[i].value !== '' && $sign_up_edit_profile[i].value !== 'mm/dd/yyyy' && $sign_up_edit_profile[i].value !== 'Country' && $sign_up_edit_profile[i].value !== 'State' && $sign_up_edit_profile[i].value !== 'Province' && $sign_up_edit_profile[i].value !== 'County' && $sign_up_edit_profile[i].value !== 'District') {
                    checked++;
                }
            } else {
                if ($sign_up_edit_profile[i].checked) {
                    radio_checked++;
                }
            }
        }
        if (radio_checked > 0) {
            checked++;
        }
        var bar = (checked * 100 / ($sign_up_edit_profile.length - 6)).toString().split('.')[0];
        $('.completeness-sec').css('width', bar + '%');
        $('.nav-percentage p').text(bar + '% complete');
    });

    $follow_button.click(function (e) {
        e.preventDefault();
        followUser();
    });

    $edit_name_head.on('click', function (e) {
        var imgURL = 'images/placeholder-profile-pic.jpg';
        $('.name-head-edit-mode #profile-pic').attr('src', imgURL);
        $('.name-head').fadeOut('fast', function () {
            $('.name-head-edit-mode').fadeIn();
        })
    });

    $save_name_head.on('click', function (e) {
        e.preventDefault();
        $('.name-head-edit-mode').fadeOut('fast', function () {
            $('.name-head').fadeIn();
        })
    });

    $edit_contact_info.on('click', function (e) {
        $('.contact-info').fadeOut('fast', function () {
            $('.contact-info-edit-mode').fadeIn();
        })
    });

    $save_contact_info.on('click', function (e) {
        e.preventDefault();
        saveContactInfo();
    });

    $avatar_button.on('click', function (e) {
        e.preventDefault();
        showOverlay();
        $('#profile-pic-popover').fadeIn();
    });

    $profile_pic_edit_button.on('click', function (e) {
        e.preventDefault();
        showOverlay();
        $('#profile-pic-popover').fadeIn();
    });

    $('#upload-pic').on('click', function (e) {
        e.preventDefault();
        $('#profile-pic-popover').fadeOut();
        $('.overlay').fadeOut();
    });

    $('#link-accounts').on('click', function (e) {
        e.preventDefault();
        $('.link-accounts ul, .save-link-accounts, .link-accounts-website').fadeIn();
    });

    $('.accounts-list').on('click', 'a', function (e) {
        e.preventDefault();
        var code = $(this).data('code');
        $('.save-link-accounts input').attr({
            'id': code,
            'placeholder': code + ' URL'
        });
    });

    $('#save-link-accounts').on('click', function (e) {
        e.preventDefault();
        $('.link-accounts ul, .save-link-accounts, .link-accounts-website').fadeOut();
        // if ($('.save-link-accounts input') != "") {
        // }
    });

    //hide politicians
    $onoffswitch.on('click', '.onoffswitch-switch', function (e) {
        $(this).addClass('active');
        $(this).parents('li').fadeOut('slow');
    });

    $('.my-connections-list').on('click', '.connected-button', function (e) {
        e.preventDefault();
        $(this).siblings('.connection-settings').fadeToggle('fast');
    });

    $('.connection-settings').on('click', 'a', function (e) {
        e.preventDefault();
        if ($(this).hasClass('delete-connection')) {
            showOverlay();
            $(this).parents('.connection-settings').siblings('.delete-connection-popover').fadeIn();
            $('.connection-settings').fadeOut();
        } else {
            $(this).parents('.connection-settings').siblings('.delete-connection-popover').fadeOut();
            $('.connection-settings').fadeOut();
        }
    });

    $('.delete-connection-popover').on('click', 'button', function (e) {
        e.preventDefault();
        if ($(this).hasClass('confirm-delete')) {
            $('.overlay').fadeOut();
            $(this).parents('.delete-connection-popover').fadeOut();
            $(this).parents('.delete-connection-popover').siblings('.connected-button').text('+ Add');
            $(this).parents('.delete-connection-popover').siblings('.connected-button').attr('class', 'add-connection btn-secondary orange-btn');
            $('.connection-settings').fadeOut();
        } else {
            $(this).parents('.delete-connection-popover').fadeOut();
            $('.overlay').fadeOut();
            $('.connection-settings').fadeOut();
        }
    });

    $('.request-action').on('click', 'a', function (e) {
        e.preventDefault();
        if ($(this).hasClass('accept-request')) {
            $(this).parent().siblings('.close').fadeIn();
            $(this).parent().text('\u2713Request accepted');
        }
        if ($(this).hasClass('close')) {
            $(this).parent().parent().fadeOut('fast', function () {
                $(this).remove();
                listItems = $("#requests-list").children('li').length--;
                console.log(listItems);
            });
        }
        if ($(this).hasClass('decline-request')) {
            $(this).parent().siblings('.close').fadeIn();
            $(this).parent().text('Request declined');
        }
        if ((listItems - 1) === 1) {
            $('.no-requests').fadeIn();
        }
    });


    var saveContactInfo = function () {
        $('.contact-info-edit-mode').fadeOut('fast', function () {
            $('.contact-info').fadeIn();
        })
    }

    var followUser = function () {
        if ($follow_button.hasClass('followed')) {
            $follow_button.removeClass('followed');
            $follow_button.text('Follow');
        } else {
            $follow_button.addClass('followed');
            $follow_button.text('\u2713Followed');
        }
    }

    var showOverlay = function () {
        $('.overlay').fadeIn();
    }

    var hideOverlay = function () {
        $('.overlay').fadeOut();
    }

})(window.jQuery);