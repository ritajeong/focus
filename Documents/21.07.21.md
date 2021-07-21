## 1단계

https://us-east-2.console.aws.amazon.com/cloudformation/home?region=us-east-2#/

AWS CloudFormation 에서 스택 생성

![Cap_2021-07-21_10-50-00-211](/uploads/b2b495f3a829b537a176836048921900/Cap_2021-07-21_10-50-00-211.jpg)

![Cap_2021-07-21_11-07-04-433](/uploads/b7ad7747f4086b77fd011b0ef6a64e85/Cap_2021-07-21_11-07-04-433.jpg)

https://s3-eu-west-1.amazonaws.com/aws.kurento.org/KMS-Coturn-cfn-6.16.0.yaml

위의 url을 Amazon S3 URL 에 입력합니다.  (Kurento Media Server 템플릿 사용)

참고)공식문서 가이드

[Installation Guide — Kurento 6.16.1-dev documentation (doc-kurento.readthedocs.io)](https://doc-kurento.readthedocs.io/en/latest/user/installation.html#installation-guide)

![Cap_2021-07-21_10-50-48-219](/uploads/15ba7254aec08796c0ccb5f89e50c56f/Cap_2021-07-21_10-50-48-219.jpg)


![Cap_2021-07-21_10-50-49-552](/uploads/9f7116cc252af8d3c93c6fd75f7f325a/Cap_2021-07-21_10-50-49-552.jpg)

TurnUser와 TurnPassword는 default로 입력되는 kurento에 kurento로 진행했습니다. 

AWS 프리티어를 사용중이라 t2.micro로 설정했고, 추가로 자신의 KeyName을 선택했습니다. 



![Cap_2021-07-21_10-51-05-588](/uploads/8fe4b3bc16ba5b03a4527b411571d8fd/Cap_2021-07-21_10-51-05-588.jpg)

새로고침 버튼을 눌러서 CREATE_COMPLETE가 나오면 성공입니다.

![Cap_2021-07-21_10-51-07-972](/uploads/3e0f30942b4b19cb05adb389dfe3a41d/Cap_2021-07-21_10-51-07-972.jpg)







## 2단계 AWS  EC2 인스턴스에 연결 -명세서  20p 하단부터 21p 중반까지

❗이 부분은 자신의 방식에 맞게 진행하세요(putty, terminal 등)

자신의 key를 저장한 경로로 가서 권한을 설정합니다. 

![Cap_2021-07-21_10-52-02-267](/uploads/c69b5b523830463d8dca199f0425c9a5/Cap_2021-07-21_10-52-02-267.jpg)

윈도우, 터미널에서 SSH 권한을 변경합니다.

https://techsoda.net/windows10-pem-file-permission-settings/

키가 저장된 경로로 이동한 다음 터미널에서 EC2 인스턴스로 접속합니다. 

![image-20210721131343207](/uploads/6b72e7959e24caba30bd3d826609555d/image-20210721131343207.png)

먼저 EC2의 인스턴스 목록에서, 1단계에서 생성한 스택을 클릭합니다. 우측의 연결 버튼을 눌러서 터미널에 복붙합니다.

![Cap_2021-07-21_13-14-59-317](/uploads/3fd80bdccb5b2be2ded3e19125328033/Cap_2021-07-21_13-14-59-317.jpg)

![Cap_2021-07-21_10-54-21-180](/uploads/385e958f58999bc80ea71a7910f55a79/Cap_2021-07-21_10-54-21-180.jpg)

인스턴스에 연결 성공했습니다. 



아래 공식문서를 참고하여 EC2 인스턴스에 Docker와 Docker Compose의 설치를 수행합니다.

[Install Docker Engine on Ubuntu | Docker Documentation](https://docs.docker.com/engine/install/ubuntu/)



```
sudo apt-get update
 
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
    
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

echo \
  "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
  
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io

# 여기까지 공식문서에서 실행, 아래 두 줄은 명세서상 코드

sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

```



![Cap_2021-07-21_10-55-54-375](/uploads/053c796d00b12fe0915280a222e90e7d/Cap_2021-07-21_10-55-54-375.jpg)

![Cap_2021-07-21_10-56-28-559](/uploads/679e7318100911e821db8dedd73fb4c4/Cap_2021-07-21_10-56-28-559.jpg)

![Cap_2021-07-21_10-56-52-693](/uploads/fbf5fb5ab40eb947b232391c86b8cd95/Cap_2021-07-21_10-56-52-693.jpg)





### 실행 화면 예시

![Cap_2021-07-21_10-58-47-530](/uploads/22b4619c4d891bb7f31e65caf1052d78/Cap_2021-07-21_10-58-47-530.jpg)

![Cap_2021-07-21_10-58-56-790](/uploads/4fce5c93e350d442e229b0929f5805da/Cap_2021-07-21_10-58-56-790.jpg)

![Cap_2021-07-21_10-59-08-569](/uploads/325fd005e34a43ed82a00de62d41252f/Cap_2021-07-21_10-59-08-569.jpg)

![Cap_2021-07-21_10-59-17-758](/uploads/4d4ccfb2ddfd64abbcaca31aa3855d9c/Cap_2021-07-21_10-59-17-758.jpg)

![Cap_2021-07-21_10-59-23-845](/uploads/0cf46a5155b93bd1b6aa4f70e1f67763/Cap_2021-07-21_10-59-23-845.jpg)



## 3단계 Kurento 실행 (docker pull) - 명세서 21페이지 하단

```
docker pull kurento/kurento-media-server:latest

docker run -d --name kms --network host \ 
kurento/kurento-media-server:latest

```

Got permission denied while trying to connect to the Docker daemon socket

에러 발생시 아래 url 참고

https://seulcode.tistory.com/557



### 실행화면 예시

![Cap_2021-07-21_11-00-04-137](/uploads/1d4a38213cacd010c31f6c265253cfe7/Cap_2021-07-21_11-00-04-137.jpg)

![Cap_2021-07-21_11-02-44-879](/uploads/650059abea2f2263ad29a020040ff06e/Cap_2021-07-21_11-02-44-879.jpg)

docker run


![image-20210721111023328](/uploads/4bcb61a6d3b2f565b18b1288c9e1f159/image-20210721111023328.png)


도커 설정이 완료되었습니다.

도커에 접속하기 전에, coturn 서버를 마저 세팅하겠습니다. 



## 4단계 coturn 서버 준비

[Frequently Asked Questions — Kurento 6.16.1-dev documentation (doc-kurento.readthedocs.io)](https://doc-kurento.readthedocs.io/en/latest/user/faq.html#faq-coturn-install)

```
sudo apt-get update && sudo apt-get install --no-install-recommends --yes \
    coturn
    
sudo vi /etc/turnserver.conf

###############
# /etc/turnserver.conf 내용입니다.
listening-port=3478
tls-listening-port=5439
listening-ip={private-ip}
external-ip={publie-ip}/{private-ip}
relay-ip={private-ip}
fingerprint
user=kurento:kurento
lt=cred-mech
realm=kurento.org
log-file=/var/log/turnserver/turnserver.log
simple-log
###############

sudo vi /etc/default/coturn

###############
# /etc/default/coturn  내용입니다.
TURNSERVER_ENABLED=1
###############


sudo service coturn restart

```


![Cap_2021-07-21_11-12-10-545](/uploads/6cbbe930b4727d609bd655be9bfe81ca/Cap_2021-07-21_11-12-10-545.jpg)

![Cap_2021-07-21_11-12-22-083](/uploads/c7a298bdfcd67362f67664f907e8bde1/Cap_2021-07-21_11-12-22-083.jpg)

### 실행화면 예시입니다.


![image-20210721111845538](/uploads/8c1ce1a307dd75d02c6ca6b222ec2be1/image-20210721111845538.png)

![Cap_2021-07-21_11-16-39-355](/uploads/ad8455e38e6a0a5b4b431a770b31922f/Cap_2021-07-21_11-16-39-355.jpg)


## 5단계 Kurento Docker 컨테이너 접속

```
docker ps -a
docker exec -it {도커 컨테이너 ID} /bin/bash
```



![Cap_2021-07-21_11-19-28-318](/uploads/e8009e26015f9df0986f627405d3bbb6/Cap_2021-07-21_11-19-28-318.jpg)

![Cap_2021-07-21_11-19-46-559](/uploads/b81e43f86c26ef3bd68adb9120bc6be2/Cap_2021-07-21_11-19-46-559.jpg)

EC2의 인스턴스인 ubuntu@ip-172-31-20-7 상에서, docker에 접속하여 root로 변경되었습니다. 

docekr상에 vim이 없으므로 먼저 설치를 해야합니다.

### docker 컨테이너에 vim 설치

- apt-get update

- apt-get install vim

### kurento endpoint 설정파일 

vi /etc/kurento/modules/kurento/WebRtcEndpoint.conf.ini

![Cap_2021-07-21_11-23-21-684](/uploads/94ad271ca979109caaf7c2aa18703512/Cap_2021-07-21_11-23-21-684.jpg)

## 6단계 hello world 예제

```
git clone https://github.com/Kurento/kurento-tutorial-java.git
cd kurento-tutorial-java/kurento-hello-world
git checkout master
mvn -U clean spring-boot:run \
    -Dspring-boot.run.jvmArguments="-Dkms.url=ws://{KMS_HOST}:8888/kurento"
```

{KMS_HOST}에 EC2 인스턴스의 퍼블릭 IP를 추가해줍니다.

https://doc-kurento.readthedocs.io/en/latest/tutorials/java/tutorial-helloworld.html#quick-start

![image-20210721132125201](/uploads/b3fb6083b20d69ca6743e794406c9e30/image-20210721132125201.png)



로컬에서 git clone을 받고 , 차례로 수행합니다.



## git bash - build success





![Cap_2021-07-21_11-41-20-159](/uploads/9e90ee03fbccad55d3c575a897a299c5/Cap_2021-07-21_11-41-20-159.jpg)

![Cap_2021-07-21_11-41-32-778](/uploads/d6e0b4bc4b6c9994e122b3f6358de043/Cap_2021-07-21_11-41-32-778.jpg)



![Cap_2021-07-21_11-42-46-581](/uploads/fcc41541c8cf02be6d0b0cadbb03cf9e/Cap_2021-07-21_11-42-46-581.jpg)









## Group call 예제

이건 로컬에서 진행하는 것이 아닌, EC2 인스턴스상 우분투에 접속해서 진행합니다!!

따라서 우분투에 미리 git, maven, openjdk 를 설치해줘야합니다. 

```
sudo apt-get update
sudo apt-get install git
sudo apt-get install maven
sudo apt-get install openjdk-8-jdk
```

이후에 적당한 디렉터리를 만들고(mkdir github)

```
mkdir github
cd github
```



github repository를 클론받아서 실행합니다. 

https://doc-kurento.readthedocs.io/en/latest/tutorials/java/tutorial-groupcall.html

```
git clone https://github.com/Kurento/kurento-tutorial-java.git
cd kurento-tutorial-java/kurento-group-call
git checkout master
mvn -U clean spring-boot:run
```



실행은 https://{EC2의 퍼블릭ip주소}:8443

으로 접속합니다 예시) https://3.334.332.335:8443

![Cap_2021-07-21_14-17-22-359](/uploads/0a10ef1e1c8c08b72bd5a578fb991c8f/Cap_2021-07-21_14-17-22-359.jpg)
