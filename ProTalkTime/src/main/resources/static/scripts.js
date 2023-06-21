var stompClient = null;
var notificationCount = 0;

$(document).ready(function() {
  console.log("Index page is ready");
  connect();

  $("#send").click(function() {
    sendMessage();
  });

});

function connect() {
  var socket = new SockJS('/ws-stomp');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    updateNotificationDisplay();

    stompClient.subscribe('/sub/chat/room/1', function (message) {
      showMessage(JSON.parse(message.body));
    });

  });
}

function showMessage(givenMessage) {
  try {
    var messageContent = givenMessage.message;
    var type = givenMessage.type;
    // var roomId = parsedMessage.roomId;
    // var sender = parsedMessage.sender;
    $("#messages").append("<tr><td>" + messageContent + "</td></tr>");
    $("#messages").append("<tr><td>" + type + "</td></tr>");
  } catch (error) {
    console.error("Error parsing JSON:", error);
  }
}

function sendMessage() {
  console.log("sending message");
  var messageContent = $("#message").val();
  var message = {
    message: messageContent,
    type: "TEXT"
  };
  stompClient.send("/sub/chat/room/1", {}, JSON.stringify(message));
  // console.log("sending message");
  // stompClient.send("/sub/chat/room/1", {}, JSON.stringify({'message': $("#message").val()}));
}


function updateNotificationDisplay() {
  if (notificationCount == 0) {
    $('#notifications').hide();
  } else {
    $('#notifications').show();
    $('#notifications').text(notificationCount);
  }

}

