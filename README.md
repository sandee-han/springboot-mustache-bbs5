# 전국 병의원 데이터 게시판 프로젝트

전국 병의원 정보 조회, 회원가입 및 게시판 글 작성, 수정이 가능한 웹페이지

[배포주소](http://ec2-15-164-233-75.ap-northeast-2.compute.amazonaws.com:8080/articles/list)
***

## 🔗ERD
![img.png](img.png)

***

## 💻개발 환경

### BackEnd
- Java 11
- SpringBoot 2.7.5
- Spring Security
- Gradle
- MySQL
- JPA

### FrontEnd
- mustache

### CI/CD
- AWS EC2
- Docker

***

## 🕹구현 기능

[회원가입, 로그인](https://github.com/sandee-han/springboot-mustache-bbs5/blob/main/src/main/java/com/mustache/bbs5/service/UserService.java)
- 회원 가입한 회원의 비밀번호를 `BCryptPasswordEncoder`를 통해 암호화 후 DB에 저장
- JWT의 `setExpiration` 을 사용해 로그인 시 JWT 만료시간 설정

[병의원 데이터 조회](https://github.com/sandee-han/springboot-mustache-bbs5/blob/main/src/main/java/com/mustache/bbs5/service/HospitalService.java)
- 전국 약 12만개의 병의원 데이터 조회
- `Pageable` 을 통해 1페이지 당 10개씩 조회
- id를 통해 1개 조회

[게시판 작성 및 조회](https://github.com/sandee-han/springboot-mustache-bbs5/blob/main/src/main/java/com/mustache/bbs5/service/ArticleService.java)
- 회원가입 된 ID와 비밀번호로 게시글 작성
- 작성된 게시글 수정

[병의원 데이터 파싱](https://github.com/sandee-han/crud-parser-practice)
- 전국 병의원 정보가 있는 CSV 파일에서 필요한 데이터 추출 및 가공

***

## 📖참조
### 병의원 데이터 출처
<https://www.localdata.go.kr/devcenter/dataDown.do?menuNo=20001>