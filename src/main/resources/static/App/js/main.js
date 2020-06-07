//BODY PADDING TOP
function body_padding_top() {
    $("#site-content").css("padding-top", parseInt($("header").outerHeight()));

}

//var hT = $('.header-top').offset().top;
var hT = $('.header-top').outerHeight();
var hH = $('.header').outerHeight();
$('.fix-header').css('height', hH);

function sticky_header() {
    var wH = $(window).height(),
        wS = $(window).scrollTop();
    if (wS > hT) {
        $('header').addClass('sticky');
        $('.fix-header').css('display', 'block');
    } else {
        $('header').removeClass('sticky');
        $('.fix-header').css('display', 'none');
    }

}


//--DOCUMENT READY FUNCTION BEGIN
$(document).ready(function () {

    $(document).on('click', '.company-item', function () {
        var url = $(this).attr('data-href');
        if (typeof url !== typeof undefined && url !== false) {
            window.location.href = url;
        }
    });


    var reviewId = '';
    $(document).on('click', '.link-comment', function (e) {
        e.preventDefault();
        reviewId = $(this).attr('review-id');
        console.log(reviewId);
        $('#Write-comment').modal();
    });
    var reaction = '';
    $(document).on('click', '.link-reaction', function (e) {
        e.preventDefault();
        reaction = $(this).attr('data-reaction');
        reviewId = $(this).attr('review-id');
        console.log(reaction);
        console.log(reviewId);
        $('#Reaction-review').modal();
    });

    $('#Write-comment').on('show.bs.modal', function (e) {
        $('#review-reaction').find('option[value="' + reaction + '"]').prop("selected", true);
        $('#Write-comment form input[name="review-id"]').val(reviewId);
    });

    $('#Reaction-review').on('show.bs.modal', function (e) {
        $('#Reaction-review form input[name="review-id"]').val(reviewId);
        $('#Reaction-review form input[name="reaction"]').val(reaction);
    });

    $(document).on('click', '.see-more__btn', function (e) {
        e.preventDefault();
        $(this).next('.see-more__expand').show();
        $(this).prev('.see-more__dots').hide();
        $(this).hide();
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
    $('.language-toolbox .language-selector').click(function (e) {
        e.preventDefault();
        $(this).parents(".language-toolbox").toggleClass("active");
    });

});
//--DOCUMENT READY FUNCTION END

//--WINDOW LOADED FUNCTION BEGIN
$(window).bind("load", function () {

});
//--WINDOW LOADED FUNCTION END

//--WINDOW RESIZE FUNCTION BEGIN
$(window).resize(function () {

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
