## 21.08.02
## OpenVidu 환경 만들기
### 도커 설치하기
```
$ sudo apt-get update

$ sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common

$ sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

$ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

$ sudo apt-get update
$ sudo apt-get install docker-ce docker-ce-cli containerd.io

# 테스트
$ sudo docker run hello-world
```
### Turn 서버 설정하기
```
$ sudo apt-get -y install coturn

$ sudo apt-get -y install redis-server

$ cd /etc

$ sudo vi turnserver.conf
```

 * turnserver.conf에 아래 내용 넣기

 ```
external-ip=<Public IP>/<Private IP>
listening-port=3478
listening-ip=<Private IP>
relay-ip=<Private IP>
fingerprint
lt-cred-mech
max-port=65535
min-port=40000
pidfile="/var/run/turnserver.pid"
user=myuser:mypassword
realm=openvidu
simple-log
redis-userdb="ip=127.0.0.1 dbname=0 password=turn connect_timeout=30"
verbose
```

* Public IP 주소, Private IP 주소 파악하는 방법
    * Public IP 주소
    ```
    > curl ifconfig.me
    ```
    ![image](https://user-images.githubusercontent.com/39117025/127866642-87182ecb-63cd-4e58-b412-91aff23304dd.png)

    * Private IP 주소
        * ubuntu@ip-172-26-12-67 에서 뒤의 ```172.26.12.67``` 입니다.
    
### coturn 설정하기
```
$ cd /etc/defualt

$ sudo vi coturn
```
![image](https://user-images.githubusercontent.com/39117025/127867144-b6e5ba42-f0f5-489d-8052-a3457e50179f.png)

* Coturn, Redis 서버 초기화
```
$ sudo service redis-server restart
$ sudo service coturn restart
```

### OpenVidu 서버 실행하기
```
## local 에서 실행시
docker run -p 4443:4443 --rm -e OPENVIDU_SECRET=MY_SECRET openvidu/openvidu-server-kms:2.19.0

## ubuntu 서버에서 실행시
$ sudo docker run -p 4443:4443 -e OPENVIDU_SECRET=MY_SECRET -e DOMAIN_OR_PUBLIC_IP={Public-IP} -e KMS_STUN_IP={Public-IP} -e KMS_STUN_PORT=3478 -e KMS_TURN_URL=myuser:mypassword@{Public-IP}:3478?transport=udp openvidu/openvidu-server-kms:2.19.0

## 싸피 AWS IP주소로 한 예시
$ sudo docker run -p 4443:4443 -e OPENVIDU_SECRET=MY_SECRET -e DOMAIN_OR_PUBLIC_IP=52.79.80.65 -e KMS_STUN_IP=52.79.80.65 -e KMS_STUN_PORT=3478 -e KMS_TURN_URL=myuser:mypassword@52.79.80.65:3478?transport=udp openvidu/openvidu-server-kms:2.19.0
```

### OpenVidu 예제 실행하기
```
$ sudo apt install maven

$ git clone https://github.com/OpenVidu/openvidu-tutorials.git -b v2.19.0

$ cd openvidu-tutorials/openvidu-mvc-java

$ mvn package exec:java
```

### 접속하기
* local
    * https://localhost:5000

* ubuntu
    * https://{퍼블릭 ip}:5000
    * SSAFY AWS
        * https://52.79.80.65:5000


### 참고자료
* https://jade-puffin-ae5.notion.site/OpenVidu-527e485fddd54beb8640404b961cf756
* https://docs.openvidu.io/en/2.19.0/tutorials/openvidu-js-java/
