# Movie-AI-Recommendation

## ✔ Commit Message

| emoji | message | description |
| --- | --- | --- |
| ⭐ | feat | 새로운 기능 추가 |
| 🛠 | fix | 기능에 대한 버그 수정 |
| ⌨ | refact | 기능 변화가 아닌 코드 리팩터링 |
| 📑                 | merge   | 병합      |
| 🚑 | hotfix | main 브랜치로 긴급 수정 |
| 💻 | realese | 새 버젼 배포 시행 |

---

# 프로젝트 실행 방법

이 프로젝트는 Spring Boot와 Gradle을 기반으로 하며, Java 21 ver 및 MySQL 8.0 ver 이상 버전이 설치된 환경에서 실행됩니다.

## 📌 실행 시 준비해야 할 사항


### 1. Java 21 설치


### 2. MySQL 설치


### 3. applicaion.properties 설정
- src/main/resources에 있는 application.properties 클릭
- 설정 안에 MySQL Connect 부분 확인
- 🚨 spring.datasource.username={사용자 데이터베이스 유저네임}
- 🚨 spring.datasource.password={사용자 데이터베이스 비밀번호}


### 4. 위의 사항들이 모두 준비가 된 후에 MovieAiRecommendApplication.java 실행
![image](https://github.com/user-attachments/assets/c9dd1d8a-668d-4065-8068-4e859b6249a2)
이렇게 표시되면 제대로 실행된 것!


---


## ✅ 서비스 이용 방법

1. 웹 브라우저에 접속 후 주소창에 localhost:8080 입력
2. 회원가입 / 로그인 페이지가 제대로 접속이 되었다면 이름, 이메일 입력 후 인증번호 발송 버튼 클릭!
- 가입하려는 이메일로 인증번호 전송
 
![image](https://github.com/user-attachments/assets/228be1bf-4b3d-4668-a6fb-92d353940c1d)

3. 다시 회원가입 / 로그인 페이지로 접속 후 인증번호 입력한 뒤 비밀번호까지 입력 마친 후 회원가입 버튼 누르기
4. 성공적으로 회원가입이 되었다면 로그인 후 서비스 이용


---


## 🚨 주의 사항
1. 꼭 선호도 조사 완료 후 영화 추천 받기 서비스 이용하기!
2. 영화 추천 받기 서비스 내의 영화 추천 받기 버튼 클릭 후 새로고침 꼭 하기!


---


## 🧩 Mov-mend ERD

![image](https://github.com/user-attachments/assets/6dcccda0-c7f3-4afe-9664-7eb2318d2440)





