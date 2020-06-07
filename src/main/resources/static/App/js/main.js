//BODY PADDING TOP
function body_padding_top() {
    jQuery("#site-content").css("padding-top", parseInt(jQuery("header").outerHeight()));

}

//var hT = $('.header-top').offset().top;
var hT = $('.header-top').outerHeight();
var hH = $('.header').outerHeight();
$('.fix-header').css('height', hH);
function sticky_header() {
    var wH = $(window).height(),
        wS = jQuery(window).scrollTop();
    if (wS > hT) {
        $('header').addClass('sticky');
        $('.fix-header').css('display', 'block');
    } else {
        $('header').removeClass('sticky');
        $('.fix-header').css('display', 'none');
    }

}


//--DOCUMENT READY FUNCTION BEGIN
jQuery(document).ready(function () {

    jQuery(document).on('click', '.company-item', function () {
        var url = jQuery(this).attr('data-href');
        if (typeof url !== typeof undefined && url !== false) {
            window.location.href = url;
        }
    });

    var reaction = '';
    var reviewId = '';
    jQuery(document).on('click', '.link-comment', function (e) {
        e.preventDefault();
        reaction = jQuery(this).attr('data-reaction');
        reviewId = jQuery(this).attr('review-id');
        console.log(reaction);
        console.log(reviewId);
        $('#Write-comment').modal();
    });
    $('#Write-comment').on('show.bs.modal', function (e) {
        jQuery('#review-reaction').find('option[value="' + reaction + '"]').prop("selected", true);
        jQuery('#review-id').val(reviewId);
    });

    jQuery(document).on('click', '.see-more__btn', function (e) {
        e.preventDefault();
        jQuery(this).next('.see-more__expand').show();
        jQuery(this).prev('.see-more__dots').hide();
        jQuery(this).hide();
    });

    // Search bar focus setting
    $('#search-bar .company-search-form input').focus(function () {
        $('#search-bar .autocomplete-suggestions').show();
    });

    $('#search-bar .company-search-form input').blur(function () {
        $('#search-bar .autocomplete-suggestions').hide(1000);
    });


    //Back to top
    $('.cd-top').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 800);
        return false;
    });
    jQuery('.language-toolbox .language-selector').click(function (e) {
        e.preventDefault();
        jQuery(this).parents(".language-toolbox").toggleClass("active");
    });

});
//--DOCUMENT READY FUNCTION END

//--WINDOW LOADED FUNCTION BEGIN
jQuery(window).bind("load", function () {

});
//--WINDOW LOADED FUNCTION END

//--WINDOW RESIZE FUNCTION BEGIN
jQuery(window).resize(function () {

});


$(window).scroll(function () {
    sticky_header();
    //Back to top
    if ($(this).scrollTop() > 50) {
        $('.cd-top').addClass('cd-is-visible');
    } else {
        $('.cd-top').removeClass('cd-is-visible');
    }
});
