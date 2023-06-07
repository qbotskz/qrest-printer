// ws = new WebSocket("ws://localhost:8080/pong");
ws = new WebSocket("ws://192.168.0.138:8080/pong");

ws.onopen = function (){
    action('Open connection!');
}

ws.onmessage = function (ev){
    action(ev.data);
}

ws.onclose= function (){
    action('Connection close!');
}

function action(message){
    var output = document.getElementById('stack');

    var newP = document.createElement('p');
    newP.appendChild(document.createTextNode(message));
    output.appendChild(newP);
}

function ping(){
    var message = document.getElementById('message').value;
    action('sent ' + message);
    ws.send(message);
}