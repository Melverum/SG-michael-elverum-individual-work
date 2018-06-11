$(document).ready(function () {
    $('#letterToGuess').hide();
    $('#startGame').click(function () {
        $('#letterToGuess').show();
        $('.btn').show();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/start",
            success: function (data) {
                updatePage();
            },
            error: function (xhr, status) {
                console.log(xhr);
            }
        });

        

    });
/*
    // $('#c-butt').click(function(){
    //     var ch =  'A'
    //     $.ajax({
    //         type: "POST",
    //         url: "http://localhost:8080/api/guess?ch=" + ch,
    //         success: function (data) {
    //             $('#a-butt').hide();
    //             updatePage();
    //         },
    //         error: function (xhr, status) {
    //             console.log(xhr);
    //         }
    //     });
    // });
*/
    $('#makeGuess').click(function () {
        var ch = $('#letterToGuess').val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/guess?ch=" + ch,
            success: function (data) {
                updatePage();
            },
            error: function (xhr, status) {
                console.log(xhr);
            }
        });
    });
});

function updatePage() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/",
        success: function (data) {
//           console.log(data);
            var guesses = data.remainingGuesses;

            $('#guesses').text("Guesses: " + guesses);

            updatePicture(guesses);
            
            var guessedLetters = '';
            $.each(data.guessedLetters, function (index, letter) {
                guessedLetters += letter + ' ';
            });
            $('#guessedLetters').text("Guessed Letters: " + guessedLetters);

            $('#word').text(data.displayWord);
            if (data.hasWon) {
                $('#status').text("You won!");
            }
            if (data.hasLost) {
                $('#status').text("You lost, the word was " + data.word);
            }
        },
        error: function (xhr, status) {
            console.log(xhr);
        }
    });
}

function updatePicture(guesses){

    $('#picture').empty();

    var imageLocation = './img/' + (6-guesses) +'.jpg';
    $('#picture').append('<img src=' + imageLocation + '></img>');

    
}

function guessLetter(ch) {
    var ch1 = ch.toLowerCase();
    $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/guess?ch=" + ch1,
            success: function (data) {
                updatePage();
                $('#'+ch+'-butt').hide();
            },
            error: function (xhr, status) {
                console.log(xhr);
            }
        });
}