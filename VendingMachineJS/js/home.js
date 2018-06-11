$(document).ready(function () {

    $('#change-button').hide();
    loadItems();
    
    // $('.itemName').css('text-align', 'center');
});

function loadItems() {
   
        
        var startingVal = 0;
        setMoneyOut(startingVal.toFixed(2));
        // clearProductDivs();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function(itemArray) {
            $.each(itemArray, function(index, item) {
                // $('#item' + (index + 1).empty();
                var id = item.id;
                var name = item.name;
                var price = item.price.toFixed(2);
                var quantity = item.quantity;
                itemDiv = '<div class="col-md-3" style="border-width: 3px; border-style: outset; height: 10px; margin-left: 7%; margin-bottom: 3%" onclick="displayItem(' + id +')">';
                imageLocation = "./img/";

                itemDiv += '<p class ="itemId" style="font-size: 1.25em; font-weight: bold;">' + id + '</p>';
                itemDiv += '<img style="width: 60%; height: 40%; display: block; margin-left: auto; margin-right: auto;" src=' + imageLocation + id + '.jpg></img>';
                itemDiv += '<p class ="itemPrice" style="text-align: center; font-size: 1.15em; color: green"> <em><b> $' + price + '</b></em></p>';
                itemDiv += '<p class ="itemQuantity" style="text-align: center; text-decoration: underline; padding-top 10px"> <b> Quantity Left: <span style="color: red">' + quantity + '</span></b></p></div>';
               
                $('#vendingMachineItemsDiv').append(itemDiv);

            });
        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service.  Please try again later.'));
        }

    });
}

function addMoney(amount){
    
    $('#change-button').show();
    var moneyOut = getMoneyOut();
    // alert('moneyOut =' + moneyOut + ' amount =' + amount );
    var total = (+moneyOut) + (+amount);
    // alert('moneyOut=' + total);
    setMoneyOut(total.toFixed(2));
    
}

function displayItem(itemNumber){
    $('#purchase-item').val(itemNumber);
}

function getMoneyOut(){
    
    return $('#moneyOut').text();
    
}

function setMoneyOut(value){
    
    var moneyOut = $('#moneyOut');
    moneyOut.text(value);
    
}

function makeSelection() {

    var userMoney = getMoneyOut();
    var userSelection = $('#purchase-item').val();
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/money/' + userMoney + '/item/' + userSelection,
        success: function(change) {
            displayChange(change);
            var displayZero = 0;
            setMoneyOut(displayZero.toFixed(2));
            $('#message-box').text('Thank You!');
            
            loadItems();
        },
        error: function(xhr, status, errorThrown) {
            
            
            var message = JSON.parse(xhr.responseText).message;

            $('#message-box').text(message);
        }

    });

}

function round(value, decimals) {
    return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
}


function returnChange(){

    var userMoney = getMoneyOut();
    userMoney = Number(round(userMoney, 2)).toFixed(2);
    
    quarters = 0;
    dimes = 0;
    nickels = 0;
    pennies = 0;
    var out ='';
    var startingVal = 0;

    while(userMoney > 0){
        if((userMoney - .25) >= 0){
            quarters++;
            userMoney -= .25;
        }
        else if((userMoney - .10) >= 0){
            dimes++;
            userMoney -= .10;
        }
        else if((userMoney - .05) >= 0){
            nickels++;
            userMoney -= .05;
        }
        else {
            break;
        }
         
        }

    if(quarters > 0){
        out += 'Quarters: ' + quarters + ' '; 
    }
    if(dimes > 0){
        out += 'Dimes: ' + dimes + ' '; 
    }
    if(nickels > 0){
        out += 'Nickels: ' + nickels + ' '; 
    }
    // if(pennies > 0){
    //     out += 'Pennies: ' + pennies + ' '; 
    // }

    $('#change-box').text(out);
    setMoneyOut(startingVal.toFixed(2));

}

function displayChange(change){
    var quarters = change.quarters;
    var dimes = change.dimes;
    var nickels = change.nickels;
    var pennies = change.pennies;
    var out = '';

    if(quarters > 0){
        out += 'Quarters: ' + quarters + ' '; 
    }
    if(dimes > 0){
        out += 'Dimes: ' + dimes + ' '; 
    }
    if(nickels > 0){
        out += 'Nickels: ' + nickels + ' '; 
    }
    if(pennies > 0){
        out += 'Pennies: ' + pennies + ' '; 
    }


    $('#change-box').text(out);
}