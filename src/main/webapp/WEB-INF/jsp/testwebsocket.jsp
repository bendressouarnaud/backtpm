<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
    <!--
    <script src="<c:url value='/assets/js/app.js' />" type="text/javascript"></script>
    -->
    <script type="text/javascript">
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
            //var socket = new SockJS('http://www.gestdp.com/gestpann/chat-websocket');
            var mtoken = document.getElementById("mtoken").value;
            alert("mtoken : "+mtoken);
            //var socket = new SockJS('/gestpann/secured/chat');
            var socket = new SockJS('/gestpann/secured/chat'+'?access__token='+mtoken);
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                resetProperties(true);
                console.log("reset...");
                //stompClient.subscribe('http://www.gestdp.com/gestpann/chat/sendMessage', function (greeting) {
                stompClient.subscribe('/gestpann/secured/history', function (greeting) {
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
            stompClient.send("/gestpann/secured/history", {}, JSON.stringify({'message': $("#message-input").val()}));
        }

        function showMessageOnPage(message) {
            alert("reception : "+message);
            $("#message").append("" + message + "");
        }

        $(function () {
            //alert("ok0");
            $("form").on('submit', function (e) {
                e.preventDefault();
            });
            //alert("ok1");
            resetProperties(false);
            $( "#button-start" ).click(function() { connect(); });
            $( "#button-stop" ).click(function() { disconnect(); });
            $( "#button-send" ).click(function() { sendMessage(); });
        });
    </script>

</head>
<body>
<div class="container">
    <div>
        <label>Press start to connect and stop to disconnect :</label>
    </div>
    <div style="margin-top: 5px;">
        <button id="button-start" type="button" class="btn btn-primary">
            Start
        </button>
        <button id="button-stop" type="button" class="btn btn-danger">
            Stop
        </button>
    </div>
    <div style="margin-top: 15px;">
        <p id="infotext" class="text-info">You are not connected</p>
    </div>
    <div id="input-group" class="input-group mb-3" >
        <input
                id= "message-input"
                type="text"
                class="form-control"
                placeholder="Enter a message here"
                aria-describedby="button-addon2"
        />
        <div class="input-group-append">
            <button
                    class="btn btn-outline-secondary"
                    type="button"
                    id="button-send"
            >
                Send
            </button>
        </div>
    </div>
    <p id="message" class="text-success"></p>
    <input type="hidden" id="mtoken"
                            value="${_csrf.token}" />
</div>
</body>
</html>