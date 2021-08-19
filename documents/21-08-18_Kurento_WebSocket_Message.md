# Kurento WebSocket Message

# Kurento receive

- joinRoom

    ```json
    {
    	"id": "joinRoom",
    	"room": "roomName",
    	"name" : "userName"
    }
    ```

- receiveVideoFrom

    ```json
    {
    	"id": "receiveVideoFrom",
    	"sender": "sender",
    	"sdpOffer": "sdpOffer"
    }
    ```

- leaveRoom

    ```json
    {
    	"id": "leaveRoom"
    }
    ```

- onIceCandidate

    ```json
    {
    	"id": "onIceCandidate",
    	"candidate": {
                        "candidate": "candidate",
                        "sdpMid": "sdpMid",
                        "sdpMLineIndex": "sdpMLineIndex"
        			 }
    }
    ```

- setPresenter

    ```json
    {
    	"id": "setPresenter",
    	"presenter": "presenterName"
    }
    ```

- changeContent

    ```json
    {
    	"id": "changeContent",
    	"presentationUserId": "presentationUserId"
    }
    ```

- changePresentation

    ```json
    {
    	"id": "changePresenter",
    	"transition": "presentationTransition",
    	"currentPage": "presentationCurrentPage",
    	"location": "presentationLocation",
    	"size": "presentationSize"
    }
    ```

# Kurento send

- newParticipantArrived (to everyone but newParticipant)

    ```json
    {
    	"id": "newParticipantArrived",
    	"name": "userName"
    }
    ```

- existingParticipants (to newParticipant)

    ```json
    {
    	"id": "exisitingParticipants",
    	"data": "participantsArray",
    	"presenter": "persenterName",
    	"presentationUserId": "presentationUserId",
    	"transition": "presentationTransition",
    	"currentPage": "presentationCurrentPage",
    	"location": "presentationLocation",
    	"size": "presentationSize"
    }
    ```

- receiveVideoAnswer (to the sender)

    ```json
    {
    	"id": "receiveVideoAnswer",
    	"name": "senderName",
    	"sdpAnswer": "ipSdpAnswer"
    }
    ```

- participantLeft (to everyone)

    ```json
    {
    	"id": "participantLeft",
    	"name": "userName"
    }
    ```

- changePresenter (to everyone)

    ```json
    {
    	"id": "changePresenter",
    	"presenter" : "presenterName"
    }
    ```

- changeContent (to everyone)

    ```json
    {
    	"id": "changeContent",
    	"presentationUserId": "presentationUserId"
    }
    ```

- changePresentation (to everyone)

    ```json
    {
    	"id": "changePresentation",
    	"transition": "presentationTransition",
    	"currentPage": "presentationCurrentPage",
    	"location": "presentationLocation",
    	"size": "presentationSize"
    }
    ```