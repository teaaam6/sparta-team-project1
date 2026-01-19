## End-point: 로그인
### Method: POST
>```
>/api/admin/login
>```
### Body (**raw**)

```json
{
    "email" : "super@gmail.com",
    "password" : "super1234"
}
```

### Response: undefined
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json

```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 회원가입
### Method: POST
>```
>/api/admin/admins
>```
### Body (**raw**)

```json
{
    "name" : "newAdmin",
    "email" : "newAdmin@naver.com",
    "password" : "12345678",
    "phoneNumber" : "010-1111-2222",
    "role" : "ADMIN"
}
```

### Response: 201
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "name": "newAdmin",
    "email": "newAdmin@naver.com",
    "phoneNumber": "010-1111-2222",
    "createdAt": "2026-01-19T16:34:50.4127712"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 전체 조회
### Method: GET
>```
>/api/admin/admins?pageSize=10&pageNumber=1&sortBy=createdAt&asc=true
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "email" : "super@gmail.com",
    "password" : "super1234"
}
```

### Query Params

|Param|value|
|---|---|
|pageSize|10|
|pageNumber|1|
|sortBy|createdAt|
|asc|true|
|status|WAITING|
|email|super@gmail.com|
|name|super|


### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "content": [
        {
            "id": 1,
            "name": "super",
            "email": "super@gmail.com",
            "phoneNumber": "010-1111-2222",
            "role": "SUPER_ADMIN",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:30:58.910963",
            "approvedAt": "2026-01-19T16:30:58.854904"
        },
        {
            "id": 2,
            "name": "activeUser",
            "email": "active1@gmail.com",
            "phoneNumber": "010-1234-5678",
            "role": "ADMIN",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:30:58.965063",
            "approvedAt": "2026-01-19T16:30:58.854904"
        },
        {
            "id": 3,
            "name": "momo",
            "email": "momo@gmail.com",
            "phoneNumber": "010-1111-1111",
            "role": "ADMIN",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:30:59.01564",
            "approvedAt": "2026-01-19T16:30:58.966061"
        },
        {
            "id": 4,
            "name": "kiki",
            "email": "kiki@naver.com",
            "phoneNumber": "010-2222-2222",
            "role": "ADMIN",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:30:59.068446",
            "approvedAt": "2026-01-19T16:30:58.966061"
        },
        {
            "id": 5,
            "name": "baba",
            "email": "baba@daum.com",
            "phoneNumber": "010-3333-3333",
            "role": "ADMIN",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:30:59.12158",
            "approvedAt": "2026-01-19T16:30:58.966061"
        },
        {
            "id": 6,
            "name": "newAdmin",
            "email": "newAdmin@naver.com",
            "phoneNumber": "010-1111-2222",
            "role": "ADMIN",
            "status": "WAITING",
            "createdAt": "2026-01-19T16:34:50.412771",
            "approvedAt": null
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 6,
    "totalPages": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 6,
    "empty": false
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 상세 조회
### Method: GET
>```
>/api/admin/admins/1
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "id": 1,
    "name": "super",
    "email": "super@gmail.com",
    "phoneNumber": "010-1111-2222",
    "role": "SUPER_ADMIN",
    "status": "ACTIVE",
    "createdAt": "2026-01-19T16:30:58.910963",
    "approvedAt": "2026-01-19T16:30:58.854904"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 자신정보 조회
### Method: GET
>```
>/api/admin/self
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "name": "super",
    "email": "super@gmail.com",
    "phoneNumber": "010-1111-2222"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 정보 수정
### Method: PATCH
>```
>/api/admin/admins/1
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "name" : "new super",
    "email" : "newSuper@daum.com",
    "phoneNumber" : "010-6666-9999"
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "name": "new super",
    "email": "newSuper@daum.com",
    "phoneNumber": "010-6666-9999"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 역할 변경
### Method: PATCH
>```
>/api/admin/admins/1/role
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "role" : "SUPER_ADMIN"
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "id": 1,
    "name": "new super",
    "email": "newSuper@daum.com",
    "role": "SUPER_ADMIN",
    "status": "ACTIVE"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 비밀번호 변경
### Method: PATCH
>```
>/api/admin/admins/1/password
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "oldPassword" : "super1234",
    "newPassword" : "super5678"
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "message": "비밀번호 변경이 완료되었습니다."
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 자신정보 수정
### Method: PATCH
>```
>/api/admin/admins/self
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "name" : "new admin",
    "email" : "new@gamil.com",
    "phoneNumber" : "010-6666-7777"
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "id": 1,
    "name": "new admin",
    "email": "new@gamil.com",
    "phoneNumber": "010-6666-7777"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 삭제
### Method: DELETE
>```
>/api/admin/admins/2
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Response: 204
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json

```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 신청 승인
### Method: POST
>```
>/api/admin/admins/6/approve
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "id": 6,
    "name": "newAdmin",
    "status": "ACTIVE",
    "approvedAt": "2026-01-19T16:55:12.7210787"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 관리자 신청 거부
### Method: POST
>```
>/api/admin/admins/6/deny
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "deniedReason" : "그냥"
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "id": 6,
    "name": "newAdmin",
    "status": "DENIED",
    "deniedAt": "2026-01-19T16:57:03.4336698",
    "deniedReason": "그냥"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 고객 전체 조회
### Method: GET
>```
>/api/admin/customers?pageSize=5&status=ACTIVE
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Query Params

|Param|value|
|---|---|
|pageSize|5|
|status|ACTIVE|


### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "content": [
        {
            "id": 1,
            "name": "customer1",
            "phoneNumber": "010-1234-1234",
            "email": "customer1@gmail.com",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:55:32.514691"
        },
        {
            "id": 4,
            "name": "customer4",
            "phoneNumber": "010-1234-1234",
            "email": "customer4@gmail.com",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:55:32.51669"
        },
        {
            "id": 7,
            "name": "customer7",
            "phoneNumber": "010-1234-1234",
            "email": "customer7@gmail.com",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:55:32.518692"
        },
        {
            "id": 10,
            "name": "customer10",
            "phoneNumber": "010-1234-1234",
            "email": "customer10@gmail.com",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:55:32.520194"
        },
        {
            "id": 13,
            "name": "customer13",
            "phoneNumber": "010-1234-1234",
            "email": "customer13@gmail.com",
            "status": "ACTIVE",
            "createdAt": "2026-01-19T16:55:32.522199"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 5,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalElements": 10,
    "totalPages": 2,
    "first": true,
    "numberOfElements": 5,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "empty": false
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 고객 세부 조회
### Method: GET
>```
>/api/admin/customers/1
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "email" : "super@gmail.com",
    "password" : "super1234"
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "id": 1,
    "name": "customer1",
    "phoneNumber": "010-1234-1234",
    "email": "customer1@gmail.com",
    "status": "ACTIVE",
    "createdAt": "2026-01-19T16:55:32.514691"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 고객 정보 수정
### Method: PATCH
>```
>/api/admin/customers/1
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "name" : "new customer",
    "email" : "newCustomer@daum.com",
    "phoneNumber": "010-1111-2222"
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "name": "new customer",
    "email": "newCustomer@daum.com",
    "phoneNumber": "010-1111-2222",
    "status": "ACTIVE",
    "createdAt": "2026-01-19T16:55:32.514691"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 고객 상태 수정
### Method: PATCH
>```
>/api/admin/customers/1/status
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "status" : "STOP"
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "name": "new customer",
    "email": "newCustomer@daum.com",
    "phoneNumber": "010-1111-2222",
    "status": "STOP",
    "createdAt": "2026-01-19T16:55:32.514691"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 고객 삭제
### Method: DELETE
>```
>/api/admin/customers/1
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "name" : "update test",
    "email" : "test@daum.com",
    "phoneNumber": "010-1111-2222"
}
```

### Response: 204
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json

```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 상품 등록
### Method: POST
>```
>/api/admin/items
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
    "name" : "포카칩",
    "category" : "FOOD",
    "price" : 1000,
    "stock" : 20,
    "status" : "ON_SALE"
}
```

### Response: 201
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "id": 31,
    "name": "포카칩",
    "category": "FOOD",
    "price": 1000,
    "stock": 20,
    "status": "ON_SALE",
    "createdAt": "2026-01-19T17:14:21.5982219",
    "adminId": 1,
    "adminName": "super",
    "adminEmail": "super@gmail.com"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 상품 전체 조회
### Method: GET
>```
>/api/admin/items?pageSize=10&category=FOOD&name=칩
>```
### Query Params

|Param|value|
|---|---|
|pageSize|10|
|category|FOOD|
|name|칩|


### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "content": [
        {
            "id": 31,
            "name": "포카칩",
            "category": "FOOD",
            "price": 1000,
            "stock": 20,
            "status": "ON_SALE",
            "createdAt": "2026-01-19T17:16:41.720649",
            "adminId": 4,
            "adminName": "super"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalElements": 1,
    "totalPages": 1,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 상품 상세 조회
### Method: GET
>```
>/api/admin/items/31
>```
### Body (**raw**)

```json
{
    "name" : "포카칩",

    "category" : "FOOD",

    "price" : 500,

    "stock" : 20,

    "status" : "ON_SALE"
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "id": 31,
    "name": "포카칩",
    "category": "FOOD",
    "price": 1000,
    "stock": 20,
    "status": "ON_SALE",
    "createdAt": "2026-01-19T17:16:41.720649",
    "adminId": 4,
    "adminName": "super",
    "adminEmail": "super@gmail.com"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 상품 정보 변경
### Method: PATCH
>```
>/api/admin/items/1/info
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
	"name" : "레고",
	"category" : "TOY",
	"price" : 10000
}
```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "name": "레고",
    "category": "TOY",
    "price": 10000
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 상품 재고 변경
### Method: PATCH
>```
>/api/admin/items/1/stock
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
	"stock" : 0
}

```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "stock": 0,
    "status": "SOLD_OUT"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 상품 상태 변경
### Method: PATCH
>```
>/api/admin/items/1/status
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Body (**raw**)

```json
{
	"status" : "ON_SALE"
}

```

### Response: 200
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json
{
    "status": "ON_SALE"
}
```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: 상품 삭제
### Method: DELETE
>```
>/api/admin/items/1
>```
### Headers

|Content-Type|Value|
|---|---|
|Authorization|{{authToken}}|


### Response: 204
<details open style="width: fit-content; max-height: 600px; overflow: auto">
<summary>Response example:</summary>

```json

```
</details>


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
_________________________________________________
Powered By: [postman-to-markdown](https://github.com/bautistaj/postman-to-markdown/)
