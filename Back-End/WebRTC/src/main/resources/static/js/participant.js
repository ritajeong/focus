const PARTICIPANT_MAIN_CLASS = 'participant main';
const PARTICIPANT_CLASS = 'participant';

/**
 * Creates a video element for a new participant
 *
 * @param {String} name - the name of the new participant, to be used as tag
 *                        name of the video element.
 *                        The tag of the new element will be 'video<name>'
 * @return
 */
function Participant(name) {
	this.name = name;
	var container = document.createElement('div');
	container.className = isPresentMainParticipant() ? PARTICIPANT_CLASS : PARTICIPANT_MAIN_CLASS;
	container.id = name;
	var span = document.createElement('span');
	var localVideo = document.createElement('video');
	var remoteVideo = document.createElement('video');
	var rtcPeer;

	container.appendChild(localVideo);
	container.appendChild(remoteVideo);
	container.appendChild(span);
	container.onclick = switchContainerClass;
	document.getElementById('participants').appendChild(container);

	span.appendChild(document.createTextNode(name));

	remoteVideo.id = 'video-' + name;
	remoteVideo.autoplay = true;
	remoteVideo.controls = false;
	remoteVideo.style.display = 'none';

	localVideo.id = 'localVideo-' + name;
	localVideo.autoplay = true;
	localVideo.controls = false;
	// localVideo.style.display = 'none';


	this.getElement = function() {
		return container;
	}

	this.getLocalVideoElement = function() {
		return localVideo;
	}

	this.getRemoteVideoElement = function() {
		return remoteVideo;
	}

	function switchContainerClass() {
		if (container.className === PARTICIPANT_CLASS) {
			var elements = Array.prototype.slice.call(document.getElementsByClassName(PARTICIPANT_MAIN_CLASS));
			elements.forEach(function(item) {
					item.className = PARTICIPANT_CLASS;
				});

				container.className = PARTICIPANT_MAIN_CLASS;
			} else {
			container.className = PARTICIPANT_CLASS;
		}
	}

	function isPresentMainParticipant() {
		return ((document.getElementsByClassName(PARTICIPANT_MAIN_CLASS)).length != 0);
	}

	this.offerToReceiveVideo = function(error, offerSdp, wp){
		if (error) return console.error ("sdp offer error")
		console.log('Invoking SDP offer callback function');
		var msg =  { id : "receiveVideoFrom",
				sender : name,
				sdpOffer : offerSdp
			};
		sendMessage(msg);
	}


	this.onIceCandidate = function (candidate, wp) {
		  console.log("Local candidate" + JSON.stringify(candidate));

		  var message = {
		    id: 'onIceCandidate',
		    candidate: candidate,
		    name: name
		  };
		  sendMessage(message);
	}

	Object.defineProperty(this, 'rtcPeer', { writable: true});

	this.dispose = function() {
		console.log('Disposing participant ' + this.name);
		this.rtcPeer.dispose();
		container.parentNode.removeChild(container);
	};
}
