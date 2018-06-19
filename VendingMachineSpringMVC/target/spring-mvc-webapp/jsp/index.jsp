<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <script>
    </script>
    <style>

        body {
            background: rgb(185, 197, 201);


        }

        .col-md-3 {
            background-color: white;
            height: 30vmin !important;
            padding:1px;
            
           
        }
        .col-md-3:hover{
            border-style: groove;
          
            border-color: lime;
        }
        
        .item0:hover{
            border-color: firebrick;
        }

        #itemName {
            text-align: center !important;
        }

        #moneyOut {
            width: 120px !important;
            height: 30px !important;
        }

        #message-box {
            border: 2px solid black;
            height: 40px !important;
            width: 60% !important;
            background: white;

        }
        #change-box {
            border: 2px solid black;
            height: 40px !important;
            width: 60% !important;
            background: white;

        }
        p{
            -webkit-margin-before: 0em !important;
            -webkit-margin-after: 0em !important;
        }
        .item0:hover{
            border-style: groove !important;
            border-color: red !important;
        }

    </style>
    <body>
        <div class="container">
            <div class='container' style="opactiy: 1">
                <h1 style="text-align: center; text-shadow: 2px 2px ivory; font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; font-size: 40pt">Vending Machine</h1>
                <hr/>

                <div class="row">
                    <div class="col-md-8" id="vendingMachineItemsDiv" style="margin-bottom: 5%">

                        <c:forEach var="item" items="${items}">
                            <form action="selectItem/${item.id}" method="POST">
                                <button type ="sumbit"class="col-md-3 item${item.quantity}" style="border-radius: 50%; border-width: 3px; border-style: outset; height: 10px; margin-left: 7%; margin-bottom: 3%;">


                                    <p class ="itemId" style="font-size: 1.50em; font-weight: bold; float: top; color: blue">
                                        ${item.id} 
                                    </p>
                                    <img style="width: 50%; height: 40%; display: block; margin-left: auto; margin-right: auto;" src="images/${item.id}.jpg"/>

                                    <p class ="itemPrice" style="text-align: center; font-size: 1.15em; color: green">
                                        <em>
                                            <b> 
                                                $${item.price}
                                            </b>
                                        </em>
                                    </p>
                                    <p class ="itemQuantity" style="text-align: center; text-decoration: underline; padding-top: -10px">
                                        <b> Quantity Left: 
                                            <span style="color: red">
                                                ${item.quantity}
                                            </span>
                                        </b>
                                    </p>
                                </button>

                            </form>

                        </c:forEach>

                    </div>

                    <div class="col-md-4" style="margin-top: -20px">
                        <center><img style="pull: right; height: 110px; width: 110px;" src="images/insertcoin.jpg"/>
                        <!--                        <h3 style="float: right">-->
                        <b style="font-size: 25px; text-align:center">Total $ In</b>
</center> 

                        <div id="moneyOut" style="margin-left: auto; margin-right: auto; border: 2px solid black; background-color: black; color: green; text-align: right; font-weight: bold; font-size: 20px; padding-right: 5px">
                            ${money}
                        </div>
                        <form action="addMoney/Quarter" method="POST">
                            <button type="submit" style="margin-right: 35px;margin-top: 5%; width: 130px; float: right" display="inline" class="btn btn-default" value="Add Quarter">Add Quarter</button>

                        </form>
                        <form action="addMoney/Dollar" method="POST" >
                            <button type="submit" style="margin-left: 10%; margin-top: 5%; width: 130px"  class="btn btn-default" value="Add Dollar">Add Dollar</button>

                        </form>
                        <form action="addMoney/Nickel" method="POST">
                            <button type="submit" style="margin-right: 35px;margin-top: 5%; width: 130px; float: right" display="inline" class="btn btn-default" value="Add Nickel">Add Nickel</button>

                        </form>
                        <form action="addMoney/Dime" method="POST">
                            <button type="submit" style="margin-left: 10%; margin-top: 5%; width: 130px"  class="btn btn-default" value="Add Dime">Add Dime</button>
                            <hr/>
                        </form>

                        <center>
                            <div class="form-horizontal" >
                                <h3 style="text-align: center">
                                    <b> Messages </b>
                                </h3>
                                <div id="message-box" type="text" style="margin-bottom: 10px" placeholder="Please Select An Item">
                                    ${message}
                                </div>
                            </div>

                        </center>

                        <div class="form-group">
                            <center>
                                <div class="col-md-1"></div>
                                <label for="purchase-item" class="col-md-4 control-label" style="padding-left: 70px; font-size: 18px; font-weight: bold; margin-top: 3%; float: center">
                                    Item:
                                </label>
                                <div class="col-md-5">
                                    <p type="text" class="form-control" style="border: 2px solid black; color: blue; font-size: 16px; margin-top: 10px" placeholder="0" required >
                                        <b> ${clickedItem} </b>
                                    </p>
                                </div>

                                <form action="makePurchase/${clickedItem}" method="POST" class="col-md-12">
                                    <button type="submit" id="purchase-button" style="width: 200px; margin-top: 2%" class="btn btn-default">
                                        Make Purchase
                                    </button>
                                    <hr/>

                                </form>
                            </center>

                        </div>
                        <div class="col-md-12" style="margin-top: -25px; margin-bottom: 30px;">
                            <center>
                                <h3 style="text-align: center">
                                    <b> Change </b>
                                </h3>
                                <div id="change-box">
                                    ${change}
                                </div>
                                <form 
                                    <form action="getChange" method = "POST" class="col-md-12">
                                        <button type="submit" id="change-button" style="width: 150px; margin-top: 2%" class="btn btn-default">
                                            Dispense Change
                                        </button>
                                        <br>

                                    </form>

                            </center>
                        </div>

                    </div>

                </div>
            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

