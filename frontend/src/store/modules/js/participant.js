import store from '@/store/index.js';

export default function Participant(name) {
  this.name = name;
  // rtcPeer 초기화
  // var rtcPeer;
  Object.defineProperty(this, 'rtcPeer', { writable: true });

  // video 엘리먼트와 이에 접근하는 메서드 생성
  var video = document.createElement('video');
  video.id = 'video-' + name;
  video.autoplay = true;
  video.controls = false;

  this.getVideoElement = function () {
    return video;
  };

  // 웹소켓을 통해
  this.offerToReceiveVideo = function (error, offerSdp) {
    //wp
    if (error) return console.error('sdp offer error');
    // console.log('Invoking participant.offerToReceiveVideo method')
    let message = {
      id: 'receiveVideoFrom',
      sender: name,
      sdpOffer: offerSdp,
    };
    store.dispatch('meetingRoom/sendMessage', message);
  };

  //
  this.onIceCandidate = function (candidate) {
    //wp
    // console.log("Local Participant candidate" + JSON.stringify(candidate))
    let message = {
      id: 'onIceCandidate',
      candidate: candidate,
      name: name,
    };
    // vuex store의 sendmessage 이용
    store.dispatch('meetingRoom/sendMessage', message);
  };

  // 소멸 메서드: participants에서 삭제 해줘야함
  this.dispose = function () {
    this.rtcPeer.dispose();
    store.dispatch('meetingRoom/disposeParticipant', this.name);
  };
}
