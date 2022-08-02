# api-jpa-toy
SPRING BOOT + REST API  + JPA + SECURITY + JWT + TESET 연습



## 앞으로 해야할일

- 미완성 된 부분 완성하기(Security, JWT)
- 테스트 코트 작성(단위, 통합)

## OO 회사 과제

REST API 개발 (문서 포함) -> Security, JWT 공부 필요 

## ■ 요구 사항

- 클라이언트(ios, android, web app)에서 사용할 다음 API를 개발하세요.
    - 다음 객체의 속성을 참고
      
    - 회원 가입
    - 회원 로그인(인증)
    - 회원 로그아웃
    - 단일 회원 상세 정보 조회
    - 단일 회원의 주문 목록 조회
    - 여러 회원 목록 조회 :
        - 페이지네이션으로 일정 단위로 조회합니다.
        - 이름, 이메일을 이용하여 검색 기능이 필요합니다.
        - 각 회원의 마지막 주문 정보

- API를 쉽게 이해할 수 있는 문서를 작성해 주세요.
- 데이터베이스는 MySQL을 사용하는 것으로 가정합니다.
    - MySQL 엔진은 innodb로 되어 있습니다.
    - MySQL은 쓰기 전용 서버와 읽기 전용 서버로 replication 설정이 되어 있습니다.
    - MySQL을 구성할 필요는 없습니다.
- 데이터베이스 테이블 설계는 회원 속성을 충족한다면 자유롭게 하시면 됩니다.
- 테이블 생성 쿼리문도 소스코드에 포함해서 제출해 주세요.
- 상업적으로 사용 가능한 오픈소스 소프트웨어 사용도 가능합니다.
- 위 항목을 모두 개발할 필요가 없습니다. 개발 가능한 부분까지 수행한 후 제출해 주세요.
- 소스코드는 github, bitbucket, gitlab 등의 링크로 제출해 주세요.
- **과제는 안내 메일에 기재된 기한까지 제출해주세요.**


## Java 과제 (Java 지원자)

- 위 요구 사항을 java와 Spring-boot를 사용하여 개발해 주세요.
- Spring-boot 2.3 이상 이용하여 주세요.
- Gradle을 사용하여 빌드 스크립트 작성해 주세요.
- API문서는 Swagger-ui를 사용하여 제공해 주세요.
- 이외 프레임워크 선택은 자유롭게 하시면 됩니다.
