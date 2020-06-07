function getFormData($form) {
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function resetAllRecaptcha() {
    var c = $('.g-recaptcha').length;
    for (var i = 0; i < c; i++)
        grecaptcha.reset(i);
}

function handleSubmitWriteNewReview() {
    var $writeReviewForm = $("#write-review-form");

    var postUrl = $writeReviewForm.attr("action");
    var requestMethod = $writeReviewForm.attr("method");

    var formData = getFormData($writeReviewForm);

    var gRecaptchaResponse = formData['g-recaptcha-response'];
    var name = formData['name'];
    var position = formData['position'];
    var content = formData['content'];
    var rating = formData['rating'];

    // verify data
    if (!gRecaptchaResponse) {
        alert("Bạn phải vượt qua recaptcha!")
        return;
    }

    if (!content || content.length < 10) {
        alert("Review phải có độ dài hơn 10 ký tự");
        return;
    }

    if (!name) {
        name = null;
    }

    if (!position) {
        position = null;
    }

    // send data
    $.ajax({
        url: postUrl,
        type: requestMethod,
        headers: {
            'Content-Type': 'application/json',
            'g-recaptcha-response': gRecaptchaResponse
        },
        data: JSON.stringify({
            "name": name,
            "position": position,
            "content": content,
            "rating": rating
        }),
        dataType: 'json'

    }).done(function (response) {
        console.log(response);
        window.location.reload();
    }).fail(function (response) {
        if (response.status === 400) {
            var errorMessage = "Bad request\n";
            if (response.hasOwnProperty("responseJSON")) {
                if (response.responseJSON.hasOwnProperty("errors")) {
                    response.responseJSON.errors.forEach(function (e) {
                        errorMessage += e.defaultMessage + "\n";
                    });
                }
                if (response.responseJSON.hasOwnProperty("message")) {
                    errorMessage += response.responseJSON.message
                }
            }
            alert(errorMessage)
        } else {
            alert("Có lỗi xảy ra!")
        }
    }).always(function () {
        resetAllRecaptcha()
    })

}