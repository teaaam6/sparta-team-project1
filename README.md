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
