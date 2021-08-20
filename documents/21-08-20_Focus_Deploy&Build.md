# Focus Deploy & Build

# Front-End Build 및 EC2에 업로드

1. Front-End Node Modules Install
    1. /frontend에서 npm install
2. Front-End Build into /backend/webrtc/src/main/resources/static
    1. vue.config.js의 outputDir 확인
    2. npm build
3. WinSCP를 이용해서 /backend/webrtc 프로젝트 EC2에 업로드
4. WinSCP를 이용해서 /backend/springboot 프로젝트 EC2에 업로드

# Kurento Media Server 설정

1. EC2에서 docker를 이용하여 kms 기본 설정

    [Installation Guide - Kurento 6.16.0 documentation](https://doc-kurento.readthedocs.io/en/stable/user/installation.html#docker-image)

2. kms container 실행
    1. docker ps -a
    2. docker container start {kms container id}
3. kms server에 인증 키 정보 저장하기
    1. WinSCP를 이용해서 /backend/webrtc/server.pem을 EC2에 업로드
    2. EC2의 server.pem을 kms container의 kurenot.conf.json과 같은 경로에 업로드
        1. dokcer cp server.pem {kms container id}:/etc/kurento/
    3. docker exec -it {kms container id} /bin/bash
    4. vi /etc/kurento/kurento.conf.json
        1. mediaServer > net > websocket > secure > port,certificate,password 주석 해제
        2. certificate의 값을 server.pem으로 변경후 저장 (password는 없으므로 "")
    5. container에서 나와서 kms container restart
        1. docker container restart {kms container id}

[Securing Kurento Applications - Kurento 6.16.0 documentation](https://doc-kurento.readthedocs.io/en/stable/features/security.html)

# EC2의 보안 규칙 변경

1. 22번 포트 (putty ssh 접속), 80번 포트(http), 443번 포트(https) 허용
    1. sudo ufw allow {port#}
    2. sudo ufw allow {port#}/tcp
    3. sudo ufw allow {port#}/udp
2. EC2 방화벽 실행
    1. sudo ufw enable

# Port Forwarding

1. 80번, 443번 포트를  8443번 포트로 Port Forwarding
    1. sudo iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8443
    2. sudo iptables -t nat -A PREROUTING -p tcp --dport 443 -j REDIRECT --to-port 8443
2. iptables의 정보를 영구적으로 저장
    1. sudo apt install netfilter-persistent iptables-persistent
    2. sudo netfilter-persistent save
3. EC2 재부팅
    1. sudo reboot

[Port forwarding with iptables](https://www.cogini.com/blog/port-forwarding-with-iptables/)

# Build (Build와 Service 중 한 가지 선택)

1. kms container 실행
    1. docker ps -a
    2. docker container start {kms container id}
2. springboot 프로젝트 실행
    1. cd ~/focus/springboot
    2. mvn spring-boot:run &
    (&을 붙이면 background에서 실행)
3. webrtc 프로젝트 실행
    1. cd ~/focus/webrtc
    2. mvn -U clean spring-boot:run -Dspring-boot.run.jvmArguments="-Dkms.url=wss://localhost:8433/kurento" &
    (&을 붙이면 background에서 실행)
4. 브라우저를 통해 접속 (크롬 부라우저 권장)

    [](https://i5a107.p.ssafy.io)

# Service (Build와 Service 중 한 가지 선택)

1. kms container 실행
    1. docker ps -a
    2. docker container start {kms container id}
2. Spring Boot 프로젝트 .jar로 packaging
    1. cd ~/focus/springboot
    2. mvn package
    3. cd ~/focus/webrtc
    4. mvn package
3. Service 등록을 위해 .service 작성
    1. vi /etc/systemd/focus-api.service
    2. 아래 내용을 작성

        ```bash
        # focus-api.service
        [Unit]
        Description=Focus Api Server
        After=network.target

        [Service]
        ExecStart=/bin/bash -c "exec java -jar /home/ubuntu/focus/springboot/target/demo-0.0.1-SNAPSHOT.jar"

        User=root
        Group=root

        [Install]
        WantedBy=multi-user.target
        ```

    3. vi /etc/systemd/focus-kurento.service
    4. 아래 내용을 작성

        ```bash
        # focus-kurento.service
        [Unit]
        Description=Focus Kurento Server
        After=network.target

        [Service]
        ExecStart=/bin/bash -c "exec java -jar -Dkms.url=wss://localhost:8433/kurento /home/ubuntu/focus/webrtc/target/WebRtc-0.0.1-SNAPSHOT.jar"

        User=root
        Group=root

        [Install]
        WantedBy=multi-user.target
        ```

4. 서비스에 등록
    1. sudo systemctl enable focus-api.service
    2. sudo systemctl enable focus-kurento.service
5. 서비스 실행
    1. sudo service focus-api start
    2. sudo service focus-kurento start
6. 서비스 상태 확인
    1. sudo service focus-api status
    2. sudo service focus-kurento status
7. 브라우저를 통해 접속 (크롬 부라우저 권장)

    [](https://i5a107.p.ssafy.io)