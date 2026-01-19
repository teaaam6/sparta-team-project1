# toy back office

Spring을 배우기 위한 toy back office

# 프로젝트 개요

Spring을 배우기 위해 만든 간단한 toy back office 서버입니다.

현재

- 관리자
- 상품
- 고객

을 유지관리 하는 기능을 가지고 있습니다.

인증및 인가는 JWT를 활용하고 있으며

관리자는 `SUPER_ADMIN`, `ADMIN`, `CS_MANAGER`로 나뉘어져 있고, 저희 서버를 이용하여 관리자는 상품과 고객을 관리할 수 있습니다.

# 개발환경
- IDE: IntelliJ IDEA

# 기술 스택
- Language: Java 17
- Framework: Spring Boot 3.5.9, JPA, querydsl, spring-security

# 빌드

빌드를 위해서는 JDK 17이 필요합니다.

이 명령어를 입력해 주세요
```
gradlew build
```

리눅스에서는 이렇게 해야 합니다.
```
./gradlew build
```

# 데모를 위한 실행

데모를 위해 [h2](https://www.h2database.com/html/main.html) 데이터베이스를 쓰는 설정 파일이 있습니다. 이를 사용해 실행 하실려면 이 명령어를 입력해 주세요.
```
gradlew bootRun --args="--spring.config.location=file:.\\application-demo.properties"
```

# API 명세서

더 자세한 API는 `API_DETAILS.md` 에서 확인 하실 수 있습니다.

## 관리자 관련 API

| method | path                                 | 설명                 | 권한          |
| -      | -                                    | -                    | -             |
| POST   | /api/admin/login                     | 관리자 로그인        | 누구나        |
| -      | -                                    | -                    | -             |
| POST   | /api/admin/admins                    | 관리자 회원가입      | 누구나        |
| -      | -                                    | -                    | -             |
| GET    | /api/admin/admins                    | 관리자 전체 조회     | 관리자만      |
| GET    | /api/admin/admins/{adminId}          | 관리자 상세 조회     | 관리자만      |
| GET    | /api/admin/self                      | 관리자 자신정보 조회 | 관리자만      |
| -      | -                                    | -                    | -             |
| PATCH  | /api/admin/admins/{adminId}          | 관리자 정보 수정     | `SUPER_ADMIN` |
| PATCH  | /api/admin/admins/{adminId}/role     | 관리자 역할 변경     | `SUPER_ADMIN` |
| PATCH  | /api/admin/admins/{adminId}/status   | 관리자 상태 변경     | `SUPER_ADMIN` |
| PATCH  | /api/admin/admins/{adminId}/password | 관리자 비밀번호 변경 | 관리자만      |
| -      | -                                    | -                    | -             |
| PATCH  | /api/admin/admins/self               | 관리자 자신정보 수정 | 관리자만      |
| -      | -                                    | -                    | -             |
| DELETE | /api/admin/admins/{adminId}          | 관리자 삭제          | `SUPER_ADMIN` |
| -      | -                                    | -                    | -             |
| POST   | /api/admin/admins/{adminId}/approve  | 관리자 신청 승인     | `SUPER_ADMIN` |
| POST   | /api/admin/admins/{adminId}/deny     | 관리자 신청 거부     | `SUPER_ADMIN` |

## 고객 관련 API

| method | path                                     | 설명           | 권한          |
| -      | -                                        | -              | -             |
| GET    | /api/admin/customers                     | 고객 전체 조회 | 관리자만      |
| GET    | /api/admin/customers/{customerId}        | 고객 세부 조회 | 관리자만      |
| -      | -                                        | -              | -             |
| PATCH  | /api/admin/customers/{customerId}        | 고객 정보 수정 | `SUPER_ADMIN` |
| PATCH  | /api/admin/customers/{customerId}/status | 고객 상태 변경 | `SUPER_ADMIN` |
| -      | -                                        | -              | -             |
| DELETE | /api/admin/customers/{customerId}        | 고객 정보 삭제 | `SUPER_ADMIN` |

## 상품 관련 API

| method | path                             | 설명           | 권한                   |
| -      | -                                | -              | -                      |
| POST   | /api/admin/items                 | 상품 등록      | `SUPER_ADMIN`, `ADMIN` |
| -      | -                                | -              | -                      |
| GET    | /api/admin/items                 | 상품 전체 조회 | 누구나                 |
| GET    | /api/admin/items/{itemId}        | 상품 상세 조회 | 누구나                 |
| -      | -                                | -              | -                      |
| PATCH  | /api/admin/items/{itemId}/info   | 상품 정보 변경 | `SUPER_ADMIN`, `ADMIN` |
| PATCH  | /api/admin/items/{itemId}/stock  | 상품 재고 변경 | `SUPER_ADMIN`, `ADMIN` |
| PATCH  | /api/admin/items/{itemId}/status | 상품 상태 변경 | `SUPER_ADMIN`, `ADMIN` |
| -      | -                                | -              | -                      |
| DELETE | /api/admin/items/{itemId}        | 상품 삭제      | `SUPER_ADMIN`, `ADMIN` |

# secret.properties

시작할 시 실행 폴더에 secret.properties라는 파일이 있을 경우 불러오도록 설정되어 있습니다.

이곳에 비밀번호와 같이 민감한 설정이나 개인 설정을 추가 하실 수 있습니다.

secret.properties 예시

```
# 실행시 테스트 관리자들을 추가
app.add-test-admins=true
# 실행시 테스트 상품들을 추가
app.add-test-items=true
# 실행시 테스트 고객들을 추가
app.add-test-customers=true

# 데이터베이스 설정
spring.datasource.url=jdbc:mysql://localhost:3306/db

spring.datasource.username=name
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=create
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# jwt secret key
spring.jwt.secret="길면 길수록 좋은 secret key 입니다. 아무 값이나 넣어주세요 (저희는 HS256 를 쓰기 때문에 32 byte 이상이어야 합니다)"
```

# 테스트 데이터 추가

개발 목적을 위해 테스트 데이터를 추가 할 수 있습니다.

- app.add-test-admins=true : 테스트 관리자들을 추가합니다
- app.add-test-items=true : 테스트 상품들을 추가합니다
- app.add-test-customers=true : 테스트 고객들을 추가합니다.

자세한 사항은 src/main/java/sparta/spartateamproject1/dev 폴더를 참고해 주세요.

# TIL 및 후기

- 0xc0de1dea [https://urgenius.tistory.com/entry/내배캠-본캠프-29일차-TIL](https://urgenius.tistory.com/entry/%EB%82%B4%EB%B0%B0%EC%BA%A0-%EB%B3%B8%EC%BA%A0%ED%94%84-29%EC%9D%BC%EC%B0%A8-TIL)
- Spring2LEE [https://blog.naver.com/ljs50807/224152013586](https://blog.naver.com/ljs50807/224152013586)
- hhjo96 [https://velog.io/@qnpfrqnpfr96/스프링-이커머스-백오피스-과제-til](https://velog.io/@qnpfrqnpfr96/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9D%B4%EC%BB%A4%EB%A8%B8%EC%8A%A4-%EB%B0%B1%EC%98%A4%ED%94%BC%EC%8A%A4-%EA%B3%BC%EC%A0%9C-til)
- imprity [https://velog.io/@imprity/Sparta-이커머스-백-오피스-과제-TIL](https://velog.io/@imprity/Sparta-%EC%9D%B4%EC%BB%A4%EB%A8%B8%EC%8A%A4-%EB%B0%B1-%EC%98%A4%ED%94%BC%EC%8A%A4-%EA%B3%BC%EC%A0%9C-TIL)
- sylee09 [https://velog.io/@sy99/고객-및-상품-관리-시스템-프로젝트](https://velog.io/@sy99/%EA%B3%A0%EA%B0%9D-%EB%B0%8F-%EC%83%81%ED%92%88-%EA%B4%80%EB%A6%AC-%EC%8B%9C%EC%8A%A4%ED%85%9C-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)

