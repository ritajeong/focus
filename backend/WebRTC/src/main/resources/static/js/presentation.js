function presenterSet(){

var presenter=document.getElementById('presenter').value;
console.log("presenter 지정 presenter: ", presenter)
var message = {
			id : 'presenterSet',
			presenter: presenter
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