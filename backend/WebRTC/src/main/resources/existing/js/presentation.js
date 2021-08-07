<<<<<<< HEAD:backend/WebRTC/src/main/resources/existing/js/presentation.js
function presenterSet(){
var presenter=document.getElementById('presenter').value;
console.log("presenter 지정 presenter: ", presenter)
var message = {
			id : 'presenterSet',
			presenter: presenter
		}
		sendMessage(message);
=======
this.offerToReceiveVideo = function(error, offerSdp, wp){
    if (error) return console.error ("sdp offer error")
    console.log('Invoking SDP offer callback function');
    var msg =  {
            id : "receiveVideoFrom",
            sender : name,
            sdpOffer : offerSdp
        };
    sendMessage(msg);
}

function presenterSet(isPresenter){
    var presenter = document.getElementById('presenter').value;
    console.log("presenter 지정 presenter: ", presenter)
>>>>>>> feature/for-pjt-ppt:backend/WebRTC/src/main/resources/static/js/presentation.js

    var message = {
        id : 'presenterSet',
        presenter: presenter,
        isPresenter: isPresenter,
    }
    sendMessage(message);
}

function start(){
    console.log("start presentation")
    var message = {
                id : 'start'
            }
            sendMessage(message);

}

function stop(){
console.log("stop presentation")
var message = {
			id : 'stop'
		}
		sendMessage(message);

}

function prev(){
console.log("prev image")
var message = {
			id : 'prev'
		}
		sendMessage(message);

}

function next(){
console.log("next image")
var message = {
			id : 'next'
		}
		sendMessage(message);

}

function full(){
    console.log("full toggle");
    var message={
        id:'full'
    }
    sendMessage(message);
}

function moveRight(){
    console.log("move right");
    var message={
        id:'moveImage',
        location:'right'
    }
    sendMessage(message);
}

function moveLeft(){
    console.log("move left");
    var message={
        id:'moveImage',
        location:'left'
    }
    sendMessage(message);
}

function moveTop(){
    console.log("move top");
    var message={
        id:'moveImage',
        location:'top'
    }
    sendMessage(message);
}