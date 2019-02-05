/**
 *
 */
$(function () {
    $('.direction-button').each(function (index, elt) {
        $(this).on("click", function () {
            var targetQuote = $(this).data('target-index');
            loadQuote(targetQuote);
        });
    });
    refreshButtonState();
});

function loadQuote(targetQuote) {
    $.ajax({
        url: '/rest/quotes/' + targetQuote,
        dataType: 'json'
    }).done(onLoadSuccess).fail(onLoadFailure);
}

function onLoadSuccess(data) {
    $('#number').text(data.number);
    $('#content').text(data.content);
    $('#author').text(data.author);
    $.each(data.directions, function (index, element) {
        var button = $('#' + element.id);
        button.data('enabled', element.enabled);
        button.data('target-index', element.targetNumber);
    });
    refreshButtonState();
}

function refreshButtonState() {
    $('.direction-button').each(function (index, elt) {
        $(this).prop('disabled', $(this).data('enabled') === false);
    });
}

function onLoadFailure(jqXHR, textStatus, errorThrow) {
    $('#content').text(errorThrown);
    $('#author').text(textStatus);
    // activer seulement le premier
}