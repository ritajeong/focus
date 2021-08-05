# WebRTC

- SSAFY(Samsung Software Academy For Youth) 공통 프로젝트
- 2021.07.12 ~ 2021.08.20

# 실행 방법

## 개인 EC2 활용

1. EC2에서 docker를 이용해 kurento-media-server 실행
   - docker exec -it {kurento-media-server} /bin/bash
2. CLI를 이용해 Spring Boot 프로젝트 실행
   - mvn -U clean spring-boot:run -Dspring-boot.run.jvmArguments="-Dkms.url=ws://{EC2 Public IP}:8888/kurento"
3. 브라우저에서 접속
   - https://localhost:8443

# Stack

- Spring Boot(Java)
- Kurento