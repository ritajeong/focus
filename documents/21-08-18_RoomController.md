# com.ssafy.example.demo

## api.controller.RoomController

### 방을 관리하는 API 

#### GET

/rooms

* 전체 방 정보를 보여줍니다.
* INPUT

* OUTPUT

  ```
  [
    {
      "description": "방 이름",
      "endTime": "방 종료 시간",
      "manager_id": "방장 아이디",
      "manager_name": "방장 이름",
      "name": "방 이름",
      "participants": "참가자 목록",
      "room_id": "방 아이디",
      "startTime": "방 시작 시간"
    }
  ]
  ```



/rooms/{roomId}

* 방 1개의 정보를 보여줍니다.

* INPUT

  ```
   "roomId": "방 아이디"
  ```

* OUTPUT

  ```
  {
    "description": "방 이름",
    "endTime": "방 종료 시간",
    "manager_id": "방장 아이디",
    "manager_name": "방장 이름",
    "name": "방 이름",
    "participants": "참가자 목록",
    "room_id": "방 아이디",
    "startTime": "방 시작 시간"
  }
  ```

/rooms/onlive/{roomId}

* 방이 현재 활동중인지 보여줍니다.

* INPUT

  ```
   "roomId": "방 아이디"
  ```

* OUTPUT

  ```
  (200, "방이 활동중입니다.")
  (404, "방이 활동중이지 않습니다.")
  (500, "Fail")
  ```

/rooms/user/{userId}

* 사용자 아이디가 참가자로 포함된 전체 방 정보를 보여줍니다.

* INPUT

  ```
   "userId": "사용자 아이디"
  ```

* OUTPUT

  ```
  [
    {
      "description": "방 이름",
      "endTime": "방 종료 시간",
      "manager_id": "방장 아이디",
      "manager_name": "방장 이름",
      "name": "방 이름",
      "participants": "참가자 목록",
      "room_id": "방 아이디",
      "startTime": "방 시작 시간"
    }
  ]
  ```

  
#### POST

/rooms/create

* 방을 생성합니다.

* INPUT

  ```
  {
    "description": "방 설명",
    "email": "방장 이메일",
    "name": "방 이름",
    "participants": "참가자 목록",
    "startTime": "시작 시간"
  }
  ```

* OUTPUT

  ```
  (code = 200, message = "성공"),
  (code = 500, message = "서버 오류")
  ```





#### PUT

/rooms/onlive

* 방의 활동 상태를 true/false로 변경합니다.

* INPUT

  ```
  {
    "on_live": false,
    "room_id": "방 아이디"
  }
  ```

  

* OUTPUT

  ```
  (code = 200, message = "성공"),
  (code = 500, message = "서버 오류")
  ```

  

/rooms/update/{roomId}

* 방의정보를 수정합니다.

* INPUT

  ```
  {
    "description": "방 설명",
    "name": "방 이름",
    "participants": "참가자 목록",
    "room_id": "방 아이디",
    "startTime": "방 시작 시간",
    "user_id": "방장 아이디"
  }
  ```

  

* OUTPUT

  ```
  (code = 200, message = "성공"),
  (code = 500, message = "서버 오류")
  ```

  

#### DELETE

/rooms/delete/{roomId}

* 방을 삭제합니다.

* INPUT

  ```
   "roomId": "방 아이디"
  ```

  

* OUTPUT

  ```
  (code = 200, message = "성공"),
  (code = 404, message = "방이 없습니다.")
  ```

  

## api.controller.RoomController

### 방을 관리하는 API 

#### GET

/rooms

* 전체 방 정보를 보여줍니다.
* INPUT

* OUTPUT

  ```
  [
    {
      "description": "방 이름",
      "endTime": "방 종료 시간",
      "manager_id": "방장 아이디",
      "manager_name": "방장 이름",
      "name": "방 이름",
      "participants": "참가자 목록",
      "room_id": "방 아이디",
      "startTime": "방 시작 시간"
    }
  ]
  ```



/rooms/{roomId}

* 방 1개의 정보를 보여줍니다.

* INPUT

  ```
   "roomId": "방 아이디"
  ```

* OUTPUT

  ```
  {
    "description": "방 이름",
    "endTime": "방 종료 시간",
    "manager_id": "방장 아이디",
    "manager_name": "방장 이름",
    "name": "방 이름",
    "participants": "참가자 목록",
    "room_id": "방 아이디",
    "startTime": "방 시작 시간"
  }
  ```

/rooms/onlive/{roomId}

* 방이 현재 활동중인지 보여줍니다.

* INPUT

  ```
   "roomId": "방 아이디"
  ```

* OUTPUT

  ```
  (200, "방이 활동중입니다.")
  (404, "방이 활동중이지 않습니다.")
  (500, "Fail")
  ```

/rooms/user/{userId}

* 사용자 아이디가 참가자로 포함된 전체 방 정보를 보여줍니다.

* INPUT

  ```
   "userId": "사용자 아이디"
  ```

* OUTPUT

  ```
  [
    {
      "description": "방 이름",
      "endTime": "방 종료 시간",
      "manager_id": "방장 아이디",
      "manager_name": "방장 이름",
      "name": "방 이름",
      "participants": "참가자 목록",
      "room_id": "방 아이디",
      "startTime": "방 시작 시간"
    }
  ]
  ```

  
#### POST

/rooms/create

* 방을 생성합니다.

* INPUT

  ```
  {
    "description": "방 설명",
    "email": "방장 이메일",
    "name": "방 이름",
    "participants": "참가자 목록",
    "startTime": "시작 시간"
  }
  ```

* OUTPUT

  ```
  (code = 200, message = "성공"),
  (code = 500, message = "서버 오류")
  ```





#### PUT

/rooms/onlive

* 방의 활동 상태를 true/false로 변경합니다.

* INPUT

  ```
  {
    "on_live": false,
    "room_id": "방 아이디"
  }
  ```

  

* OUTPUT

  ```
  (code = 200, message = "성공"),
  (code = 500, message = "서버 오류")
  ```

  

/rooms/update/{roomId}

* 방의정보를 수정합니다.

* INPUT

  ```
  {
    "description": "방 설명",
    "name": "방 이름",
    "participants": "참가자 목록",
    "room_id": "방 아이디",
    "startTime": "방 시작 시간",
    "user_id": "방장 아이디"
  }
  ```

  

* OUTPUT

  ```
  (code = 200, message = "성공"),
  (code = 500, message = "서버 오류")
  ```

  

#### DELETE

/rooms/delete/{roomId}

* 방을 삭제합니다.

* INPUT

  ```
   "roomId": "방 아이디"
  ```

  

* OUTPUT

  ```
  (code = 200, message = "성공"),
  (code = 404, message = "방이 없습니다.")
  ```

  

