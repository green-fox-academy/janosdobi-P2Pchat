var stompClient = null;

function connect() {
    var socket = new SockJS('/server');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/socket', function () {
            location.reload();
        });
    });
}

connect();