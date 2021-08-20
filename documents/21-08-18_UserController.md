# com.ssafy.example.demo

## api.controller.UserController

### 발표자료를 관리하는 API

#### GET

/users/{email}

- 참가자 추가용 - 이메일로 사용자 검색 

- INPUT

  ```plaintext
  "email": "유저 이메일"
  ```

- OUTPUT

  ```plaintext
  {
    "email": "유저 이메일",
    "name": "유저 이름",
    "userId": "유저 아이디"
  }
  ```



/users/check/{email}

- 회원가입용 - 이메일로 사용자 검색

- INPUT

  ```plaintext
  "email": "유저 이메일"
  ```

- OUTPUT

  ```plaintext
  (200, "성공했습니다.")
  (500, "Fail")
  ```



/users/search/{keyword}

- 이메일 중 일부 키워드로 검색된 사용자 정보 가져오기

- INPUT

  ```plaintext
  "keyword": "일부 키워드"
  ```

- OUTPUT

  ```plaintext
  [
    {
      "email": "유저 이메일",
      "name": "유저 이름",
      "userId": "유저 아이디"
    }
  ]
  ```



#### Delete

/users/delete/{userId}

- 사용자 삭제

- INPUT

  ```plaintext
  "userId": "유저 id"
  ```

- OUTPUT

  ```plaintext
  (200, "성공했습니다.")
  (500, "Fail")
  ```





#### POST



/users/login

- login.

- INPUT

  ```plaintext
  {
    "email": "유저 이메일",
    "password": "유저 비밀번호"
  }
  ```

- OUTPUT

  ```plaintext
  {
    "email": "유저 이메일",
    "name": "유저 이름",
    "userId": "유저 아이디"
  }
  ```



/users/register

- 회원가입

- INPUT

  ```plaintext
  {
    "email": "사용자 이메일",
    "name": "사용자 이름",
    "password": "사용자 비밀번호",
    "userId": "사용자 아이디"
  }
  ```

- OUTPUT

  ```plaintext
  (200, "성공했습니다.")
  (500, "Fail")
  ```



#### Put

/users/update/name

- 이름 변경

- INPUT

  ```plaintext
  {
    "currentPassword": "현재 비밀번호",
    "email": "사용자 이메일",
    "name": "사용자 이름",
    "newPassword": "새 비밀번호",
    "user_id": "사용자 아이디"
  }
  ```

- OUTPUT

  ```plaintext
  (200, "성공했습니다.")
  (500, "Fail")
  ```





/users/update/password

- 비밀번호 변경

- INPUT

  ```plaintext
  {
    "currentPassword": "현재 비밀번호",
    "email": "사용자 이메일",
    "name": "사용자 이름",
    "newPassword": "새 비밀번호",
    "user_id": "사용자 아이디"
  }
  ```

- OUTPUT

  ```plaintext
  (200, "성공했습니다.")
  (500, "Fail")
  ```



