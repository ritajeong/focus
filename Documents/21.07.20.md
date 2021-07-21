# 21.07.20

## Kurento 튜토리얼 Hello World 실행하기
### 목표: Hello world 예제 성공하기
- remote stream 화면에 local stream 화면에서 촬영되고 있는 영상이 나오면 됩니다.
![image](https://user-images.githubusercontent.com/39117025/126308261-f0580033-94e6-4bd3-a7a5-eb0af2d7746a.png)


### 과정
- [AWS CloudFormation](https://us-east-2.console.aws.amazon.com/cloudformation/home?region=us-east-2) > 스택 생성 > 새 리소스 사용(표준) 클릭합니다.


![image](https://user-images.githubusercontent.com/39117025/126309176-87cdeaad-f173-4940-8e5b-4a653544c615.png)

- Amazon S3 URL 에 ``` https://s3-eu-west-1.amazonaws.com/aws.kurento.org/KMS-Coturn-cfn-6.16.0.yaml ``` 를 입력하고 '다음' 을 누릅니다.
- 스택 세부 정보 지정에서 아래 정보를 입력합니다.
    - 스택 이름
    - InstanceType : t2.micro (무료)
    - KeyName : 없으면 [키 페어 생성](https://us-east-2.console.aws.amazon.com/ec2/v2/home?region=us-east-2#KeyPairs:) 여기서 생성합니다.
        - .pem 으로 만들기

- 모두 '다음' 눌러서 완료합니다.

- 스택의 리소스의 상태가 모두 'CREATE_COMPLETE' 가 될 때까지 기다립니다.

- [AWS 인스턴스](https://us-east-2.console.aws.amazon.com/ec2/v2/home?region=us-east-2#Instances:instanceState=running)에서 생성된 인스턴스를 클릭한 후 '퍼블릭 IPv4 주소' 를 이용해 ubuntu 에 접속합니다.

- PJT2 명세서 21p ~ 24p 까지 진행합니다.

- 로컬 cmd 창을 연 후 ``` mvn -version``` 을 확인합니다.
- [Hello World](https://doc-kurento.readthedocs.io/en/latest/tutorials/java/tutorial-helloworld.html) 링크를 참고해서 실행합니다.
    ```
    > git clone https://github.com/Kurento/kurento-tutorial-java.git
    > cd kurento-tutorial-java/kurento-hello-world
    > git checkout master
    > mvn -U clean spring-boot:run \
    -Dspring-boot.run.jvmArguments="-Dkms.url=ws://{KMS_HOST = 퍼블릭 IPv4 주소 }:8888/kurento"
    ```

- WebRTC 호환 브라우저인 Chrome 또는 Firefox로 데모 페이지를 열 수 있습니다. : https://localhost:8443/

    - '시작' 클릭 -> 데모를 시작

    - 웹캠에 대한 액세스 권한을 부여합니다.

    - 로컬, 리모트 video 창에 영상이 뜨는지 확인합니다.

    - '중지' 클릭 -> 데모 끝