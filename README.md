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
* **Config Server에서 Encrypt 관련 기능 작업할 때, bootstrap.yml을 사용했는데, 역시 그냥 책 따라하면 bootstrap.yml이 인식되지 않는다. 마찬가지로 spring-cloud-starter-bootstrap를 추가하면 된다. **
* 아마도 관련 문서: https://spring.io/blog/2020/10/07/spring-cloud-2020-0-0-m4-aka-ilford-is-available
