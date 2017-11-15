function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/api/message/receive', function (message) {
            console.log(message);
            window.location.reload();
        });
    });
}

connect();