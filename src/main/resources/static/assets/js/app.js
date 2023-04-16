var stompClient = null;

function resetProperties(connected) {
    $("#button-start").prop("disabled", connected);
    $("#button-stop").prop("disabled", !connected);
    if (connected) {
        $("#input-group").show();
        $("#infotext").html("You are connected");
    }
    else {
        $("#input-group").hide();
        $("#infotext").html("You are not connected");
    }

}

function connect() {
    alert("Test");
    //var socket = new SockJS('http://www.gestdp.com/gestpann/chat-websocket');
    var socket = new SockJS('http://localhost:8080/gestpann/chat-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        resetProperties(true);
        console.log("reset...");
        //stompClient.subscribe('http://www.gestdp.com/gestpann/chat/sendMessage', function (greeting) {
        stompClient.subscribe('http://localhost:8080/chat/sendMessage', function (greeting) {
            showMessageOnPage(JSON.parse(greeting.body).message);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    resetProperties(false);
}

function sendMessage() {
    //stompClient.send("http://www.gestdp.com/gestpann/chat/sendMessage", {}, JSON.stringify({'message': $("#message-input").val()}));
    stompClient.send("http://localhost:8080/gestpann/chat/sendMessage", {}, JSON.stringify({'message': $("#message-input").val()}));
}

function showMessageOnPage(message) {
    $("#message").append("" + message + "");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    resetProperties(false);
    $( "#button-start" ).click(function() { connect(); });
    $( "#button-stop" ).click(function() { disconnect(); });
    $( "#button-send" ).click(function() { sendMessage(); });
});