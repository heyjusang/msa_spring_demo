# msa_spring_demo
* Juan Antonio Medina Iglesias, HANDS-ON MICROSERVICES WITH KOTLIN(코틀린 마이크로서비스 개발), 에이콘
* **Spring boot 2.1.2 대신 2.4.2 사용했음**

## chapter1
* 없음. 마이크로서비스 이론 설명

## chapter2
* 환경설정 및 spring boot 간단한 예제

## chapter3
* Restful service (POST, GET, PUT, DELETE)
* Json Request/Response handling
* Error handling

## chapter4
* Reactive Microservice
* Spring webflux
* Functional Router

## chapter5
* MongoDB
* Spring Reactive MongoDB
* ReactiveMongoTemplate

## chapter6
* Cloud
* Config Server, Spring Boot Actuator, Discovery Server (Netflix-Eureka), Gateway (Netflix-Zuul)
* **Gateway의 경우 현재 Spring boot 2.4.2에서 Zuul을 지원 안해서, Spring boot 2.3.8 사용**
* **책 따라하면 Instance에서 Config Server 인식이 안됐다. spring-cloud-starter-bootstrap를 dependency로 추가하니 됐다.**
* **Config Server에서 Encrypt 관련 기능 작업할 때, bootstrap.yml을 사용했는데, 책 따라하면 bootstrap.yml이 인식되지 않는다. 마찬가지로 spring-cloud-starter-bootstrap를 추가하면 된다.**
* 아마도 관련 문서: https://spring.io/blog/2020/10/07/spring-cloud-2020-0-0-m4-aka-ilford-is-available

## chatper7
* Docker
* 기존 코드 사용하기 위해 msatest, msatest-configserver, msatest-discoveryserver, msatest-gateway 그대로 유지하고, 각각 docker 설정
* docker_script에 필요한 기능들 script로 만들어둠
  * package.sh
  * build_image.sh
  * run.sh
  * stop.sh
* Mac Silicon M1에서 안되는게 많다.
  * Docker Desktop for Mac 사용 불가. Mac M1용 Preview 버전 설치
    * https://www.docker.com/blog/download-and-try-the-tech-preview-of-docker-desktop-for-m1/
  * openjdk:alpine 사용 불가. openjdk:oracle-linux 대신 사용함.
    * https://hub.docker.com/_/openjdk
  * mongodb가 로컬머신에 있어서, container에서 로컬머신으로 접근이 필요함. localhost는 사용불가라서, host.docker.internal 사용해야하는데, 지원안함.
    * docker run 할 때, --add-host=host.docker.internal:host-gateway 추가 해주어야함.
    * https://docs.docker.com/docker-for-mac/apple-m1/#known-issues
  * msatest와 msa-configserver를 각각 띄웠는데, 커넥션 실패함. host.docker.internal 사용해도 안되고, 온갖것들을 다 해도 안됨.. (아니면 그냥 내가 못하는거일수도)
    * msa-configserver를 docker말고 로컬에서 띄우고, host.docker.internal 사용해도 안됨. http 접근이 안되는것같다.
    * https://docs.docker.com/docker-for-mac/apple-m1/#known-issues 에 The HTTP proxy is not enabled 라 써져있는데, 이것과 연관있나...
    * 일단은 configserver 연결안되면 msatest 자체가 안떠버려서, greeting 메시지 default value msatest쪽에 설정
  * io.fabric8:docker-maven-plugin이 등록 안됨. not found 뜨면서 plugin 등록 불가.. 그래서 이 부분은 제외. 뭐가 문제인지 모르겠다.  
