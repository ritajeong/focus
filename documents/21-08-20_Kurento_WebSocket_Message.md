# Kurento WebSocket Message

# Kurento receive

- joinRoom

    ```
    {
        "id": "joinRoom",
        "room": "roomName",
        "name" : "userName"
    }
    ```

- receiveVideoFrom

    ```
    {
        "id": "receiveVideoFrom",
        "sender": "sender",
        "sdpOffer": "sdpOffer"
    }
    ```

- leaveRoom

    ```
    {
        "id": "leaveRoom"
    }
    ```

- onIceCandidate

    ```
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

    ```
    {
        "id": "setPresenter",
        "presenter": "presenterName"
    }
    ```

- changeContent

    ```
    {
        "id": "changeContent",
        "presentationUserId": "presentationUserId"
    }
    ```

- changePresentation

    ```
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

    ```
    {
        "id": "newParticipantArrived",
        "name": "userName"
    }
    ```

- existingParticipants (to newParticipant)

    ```
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

    ```
    {
        "id": "receiveVideoAnswer",
        "name": "senderName",
        "sdpAnswer": "ipSdpAnswer"
    }
    ```

- participantLeft (to everyone)

    ```
    {
        "id": "participantLeft",
        "name": "userName"
    }
    ```

- changePresenter (to everyone)

    ```
    {
        "id": "changePresenter",
        "presenter" : "presenterName"
    }
    ```

- changeContent (to everyone)

    ```
    {
        "id": "changeContent",
        "presentationUserId": "presentationUserId"
    }
    ```

- changePresentation (to everyone)

    ```
    {
        "id": "changePresentation",
        "transition": "presentationTransition",
        "currentPage": "presentationCurrentPage",
        "location": "presentationLocation",
        "size": "presentationSize"
    }
    ```