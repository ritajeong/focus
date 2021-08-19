# com.ssafy.example.demo

## api.controller.FileController

### 발표자료를 관리하는 API

#### GET

/board/image/{roomId}/{userId}

- 현재 발표 자료 모두 보기 base64

- INPUT

  ```plaintext
   "roomId": "방 아이디"
   "userId": "발표자 아이디"
  ```

- OUTPUT

  ```plaintext
  [
  	"string":"발표자료"
  ]
  ```



/board/image/{roomId}/{userId}/{currentPage}

- 현재 발표 자료 한 장 보기

- INPUT

  ```plaintext
   "roomId": "방 아이디"
  ```

- OUTPUT

  ```plaintext
  [
    {
      "file_size": "파일 개수",
      "user_id": "유저 아이디"
    }
  ]
  ```



/board/room/{roomId}

- 방 하나의 파일 전체 리스트업

- INPUT

  ```plaintext
   "roomId": "방 아이디"
   "userId": "발표자 아이디"
   "files": "파일 리스트"
  ```

- OUTPUT

  ```plaintext
  [
    {
      "file_size": "파일 개수",
      "user_id": "유저 아이디"
    }
  ]
  ```



#### POST



/board/delete

- 발표자료 삭제

- INPUT

  ```plaintext
   "roomId": "방 아이디"
   "userId": "발표자 아이디"
   "files": "파일 리스트"
  ```

- OUTPUT

  ```plaintext
  (200, "삭제 되었습니다.")
  (500, "Fail")
  ```



/board/detail

- 파일 선택하면 발표자료 자세히 보기

- INPUT

  ```plaintext
   "roomId": "방 아이디"
   "userId": "발표자 아이디"
   "files": "파일 리스트"
  ```

- OUTPUT

  ```plaintext
  (200, "성공했습니다.")
  (500, "Fail")
  ```



/board/down

- 파일 저장하기

- INPUT

  ```plaintext
   "roomId": "방 아이디"
   "userId": "발표자 아이디"
   "files": "파일 리스트"
  ```

- OUTPUT

  ```plaintext
  (200, "저장 되었습니다.")
  (500, "Fail")
  ```



/board/updatefile

파일 수정

- INPUT

  ```plaintext
   "roomId": "방 아이디"
   "userId": "발표자 아이디"
   "files": "파일 리스트"
  ```

- OUTPUT

  ```plaintext
  (200, "수정 되었습니다.")
  (500, "Fail")
  ```

