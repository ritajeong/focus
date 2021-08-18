![waving](https://capsule-render.vercel.app/api?type=waving&height=200&text=Focus:on-your-presentation&fontSize=50&fontAlignY=35&color=gradient)

## 👨‍🏫 FOCUS
### 프로젝트 소개
  * https://i5a107.p.ssafy.io
  * [Notion 개발 WIKI](https://www.notion.so/7-FOCUS-a4db4765db0d47059f1a743e2e87347c)
  * ```FOCUS``` 는 온라인 발표환경과 오프라인 발표 환경의 차이를 줄이기 위해 화상영상에 발표자료를 함께 띄워서 관리하는 서비스입니다.

### 주요 기능
  * 발표자료 컨트롤
  * 방 관리
### 실행화면
### 향후 계획
  * Secure 코딩
  * 다양한 상황에 대처하는 오류처리
  * 코드 스타일 개선

## 📌 목차
- [시작하기](#시작하기)   
  - [설치하기](#설치하기)
  - [실행하기](#실행하기)
  - [배포환경](#배포환경)
  - [배포하기](#배포하기)
- [디렉토리 구조](#디렉토리-구조)   
- [프로젝트 명세](#프로젝트-명세)
  - [개발 환경](#개발-환경)
  - [역할](#역할)
  - [핵심 라이브러리](#핵심-라이브러리)
  - [시스템 아키텍처](#시스템-아키텍처)
- [서비스 상세](#서비스-상세)
  - [사용자 시나리오](#사용자-시나리오)

 
<br>

<br>

## 시작하기
### 설치하기
1. 깃헙의 레포지토리를 클론합니다.
    ```
    $ git clone https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A107.git
    ```
2. frontend 폴더 내부에서 node_modules 를 설치합니다.
    ```
    $ npm install
    ```

### 실행하기
1. kms container 실행
    ```    
    $ docker ps -a
    $ docker container start {kms container id}
    ```
2. springboot 프로젝트 실행
    ```
    $ cd ~/springboot
    $ mvn spring-boot:run &
    (&을 붙이면 background에서 실행)
    ```
3. webrtc 프로젝트 실행
    ```
    $ cd ~/webrtc
    $ mvn -U clean spring-boot:run -Dspring-boot.run.jvmArguments="-Dkms.url=wss://localhost:8433/kurento" & 

    # (&을 붙이면 background에서 실행)
    ```
    
4. 브라우저를 통해 접속 (크롬 부라우저 권장)

### 배포환경
- __URL__ : https://i5a107.p.ssafy.io
- __배포 여부__ : O 
- __접속 가능__ : 접속 가능
- __HTTPS 적용__ : O 
- __PORT__ : 8443
### 배포하기

#### 1. Front-End Node Modules Install
* /frontend에서 npm install
#### 2. Front-End Build into /backend/webrtc/src/main/resources/static
* vue.config.js의 outputDir 확인
* npm build
#### 3. WinSCP를 이용해서 /backend/webrtc 프로젝트 EC2에 업로드
#### 4. WinSCP를 이용해서 /backend/springboot 프로젝트 EC2에 업로드

#### 5. Kurento Media Server 설정

* 5-1. EC2에서 docker를 이용하여 kms를 기본 설정합니다.

    [Installation Guide - Kurento 6.16.0 documentation](https://doc-kurento.readthedocs.io/en/stable/user/installation.html#docker-image)

* 5-2. kms container를 실행합니다.
    ```
    $ docker ps -a
    $ docker container start {kms container id} 
    ```
* 5-3. kms server에 인증 키 정보를 저장합니다.
    (1) WinSCP를 이용해서 /backend/webrtc/server.pem을 EC2에 업로드
    (2) EC2의 server.pem을 kms container의 kurenot.conf.json과 같은 경로에 업로드
        ```
         $ dokcer cp server.pem {kms container id}:/etc/kurento/
         ```
    (3) docker exec -it {kms container id} /bin/bash
    (4) vi /etc/kurento/kurento.conf.json
        * mediaServer > net > websocket > secure > port,certificate,password 주석 해제
        * certificate의 값을 server.pem으로 변경후 저장 (password는 없으므로 "")
    (5) container에서 나와서 kms container restart
    ```
         $ docker container restart {kms container id}
    ```
#### 참고
 * [Securing Kurento Applications - Kurento 6.16.0 documentation](https://doc-kurento.readthedocs.io/en/stable/features/security.html)

#### 6. EC2의 보안 규칙 변경

* 6-1. 22번 포트 (putty ssh 접속), 80번 포트(http), 443번 포트(https) 허용
    ```
    $ sudo ufw allow {port#}
    $ sudo ufw allow {port#}/tcp
    $ sudo ufw allow {port#}/udp
    ```
* 6-2. EC2 방화벽 실행
    ```
    $ sudo ufw enable
    ```

#### 7. Port Forwarding

* 7-1. 80번, 443번 포트를  8443번 포트로 Port Forwarding
    ```
    $ sudo iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8443
    $ sudo iptables -t nat -A PREROUTING -p tcp --dport 443 -j REDIRECT --to-port 8443
    ```
* 7-2. iptables의 정보를 영구적으로 저장
    ```
    $ sudo apt install netfilter-persistent iptables-persistent
    $ sudo netfilter-persistent save
    ```
* 7-3. EC2 재부팅
    ```
    $ sudo reboot
    ```
##### 참고
* [Port forwarding with iptables](https://www.cogini.com/blog/port-forwarding-with-iptables/)



## 역할
<br>

## 프로젝트 명세

### 개발 환경
#### Front-end

- HTML5, CSS3, JS(ES6)
- Bootstrap5
- Vue.js, Vuex
- Visual Studio Code


#### Back-end
- Java
- Spring Boot, Spring MVC, Spring Data JPA
- JPA, JPQL
- Maven
- MySQL
- Intellij, Eclipse STS

#### DevOps

- AWS EC2
- Docker


#### Collaboration
- Jira
- Gitlab, Sourcetree, GithubDesktop
- Mattermost
- [Notion](https://www.notion.so/7-FOCUS-a4db4765db0d47059f1a743e2e87347c)
<br>



### 시스템 아키텍처

### 데이터베이스

### 핵심 라이브러리
- __Kurento MCU 서버__
  - __링크__ : https://doc-kurento.readthedocs.io/en/latest/tutorials/java/tutorial-groupcall.html
  - __소개__ : Kurento는 WebRTC 미디어 서버이자 WWW 및 스마트폰 플랫폼용 고급 비디오 애플리케이션 개발을 간단하게 해주는 클라이언트 API입니다.
  - __사용 기능__ : 그룹콜 기능
  - __담당자__ : 김민지, 손동우
- __kurento-utils__
    -  브라우저의 WebRTC API를 제어하기 위해 RTCPeerConnection 객체의 생성 및 처리를 단순화하는 데 사용된 자바스크립트 기반의 Kurento 유틸리티 라이브러리.


<br>

## 디렉토리 구조
### Frontend > [보러가기✔]()
### Backend > [보러가기✔]()




<br>

## 서비스 상세
### Controller > [보러가기✔]()
### WebSocket > [보러가기✔]()
### Component > [보러가기✔]()
### 사용자 시나리오 > [보러가기✔]()
### 기타 라이브러리 > [보러가기✔]()

<br>


