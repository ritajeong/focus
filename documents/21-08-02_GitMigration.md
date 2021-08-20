## 21.08.02
### Git Migration
* 기존 GIT 레포지토리에서 신규 GIT 레포지토리로 마이그레이션 하기

    * 작업할 임시 디렉토리 만들기
    * 구 레포지토리 주소 클론하기

        ```> git clone --mirror {구 레포지토리 주소}.git```
    * 신규 레포지토리 설정하기

        ```> git remote set-url --push origin {신규 레포지토리 주소}.git```

    * 신규 레포지토리에 PUSH 하기

        ```> git push --mirror```

    * 정상적으로 복사됐는지 확인하기

        ```> git remote -v```

### 참고자료
https://suji-developer.tistory.com/1