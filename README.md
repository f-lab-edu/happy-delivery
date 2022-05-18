# happy-delivery
배달의 민족, 요기요와 같은 배달 서비스


## 목표
* 배달 서비스의 기능들을 직접 구현함으로써 Backend System을 이해한다.
* 대규모 트래픽에도 안정적인 어플리케이션을 구현한다.
* 나쁜 코드에 대해 지속적으로 리팩토링한다.
* ex) 배달의 민족과 같은 배달 서비스

## 사용기술
* Spring Boot, Java11, Gradle, Mybatis, Mysql, JPA, Redis ,Docker 등

## Code Convention
* [Google code Style](https://google.github.io/styleguide/javaguide.html) 준수
* pre-commit과 github-action에 checkStyle을 적용하여 위반한 것이 없는지 확인

## APM(Application Performance Management)
* APM도구로 Elastic APM을 사용
* 팀원과 구성을 공통적으로 하기 위해 docker-compose를 이용하기로 결정
* [elastic APM의 docker-compose 파일 예시](https://www.elastic.co/guide/en/apm/get-started/current/quick-start-overview.html)
* [docker를 사용하지 않는 elastic APM 설정 예시](https://oopsys.tistory.com/m/284)
* 버전은 7.17.1 사용
* VM options 설정
  ```
  -javaagent:./elastic-apm-agent-1.29.0.jar
  -Delastic.apm.service_name=happy-delivery
  -Delastic.apm.server_urls=http://localhost:8200
  -Delastic.apm.application_packages=com.happy.delivery
  -Delastic.apm.transaction_sample_rate=1
  -Delastic.apm.trace_methods=com.happy.*
  ```

## CI
* pre-commit을 이용해 code convention 확인
* github-action을 이용하여 pr 시 build와 code convention 확인 (더블체크)

## ERD
![HappyDeliveryERD](https://user-images.githubusercontent.com/91924087/167845486-a58ca3d8-46b1-41a4-ab3f-4bea6cf1478e.jpg)

## 기능정의
* [기능명세서](https://github.com/f-lab-edu/happy-delivery/wiki/기능-명세서)
