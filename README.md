# 🌈 무지개편지
<b>펫로스 극복을 위한 챗봇 서비스</b>

저희 서비스 “무지개편지”의 목표는 펫로스 증후군을 앓는 사람들을 위해 챗봇이 주는 위로를 통해 극복의 토대를 제공하는 것입니다. 사용자들은 다른 사람에게 솔직히 말하기 어렵거나 시간이 많이 지나도 털어내지 못한 감정을 반려동물과의 가상 대화를 통해 부담 없이 털어놓을 수 있고, 이는 펫로스 증후군 극복에 긍정적인 작용을 할 수 있습니다. 시간이 흘러도 여전히 그리운, 당신의 가족이자 기억하고 싶은 이들에게 전하고 싶은 말을 모아 직접 보내보세요. “무지개 편지”는 그 말들이 닿길 기원하며 가상의 답변을 보내드립니다.
<br>
<br>
## 🖥️ Demo video
[![Video Label](http://img.youtube.com/vi/8pW3X7zxmLs/0.jpg)](https://youtu.be/8pW3X7zxmLs)
<br>
<br>

## 🖼️ Project Architecture
![image](https://github.com/ewha-cse-HACK/HACK_Server/assets/67634926/99676a61-6f99-48af-8b07-4c52f4c09ec5)


## 💪 Teck stack
<b>Server</b> <br>
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black"> 
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> 
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/OpenAI-412991?style=for-the-badge&logo=OpenAI&logoColor=white">
<img src="https://img.shields.io/badge/Naver-03C75A?style=for-the-badge&logo=Naver&logoColor=white">

## 🌱 Server 구성요소
- java 17 : 사용한 프로그래밍 언어
- springboot (3.1.1) : 프레임워크
- JWT : 회원가입 및 로그인시 사용하는 토큰
- Open AI (GPT-4, DALL-E) : 텍스트 생성을 위한 GPT 4, 이미지 생성을 위한 DALL-E 3 API 활용
- EC2 : 서버
- RDS : 데이터베이스
- S3: 이미지 저장

<br>


## 🧩 사용한 오픈소스
1. Open AI DALL-E
<pre><code>implementation 'com.theokanning.openai-gpt3-java:client:0.17.0'
implementation 'com.theokanning.openai-gpt3-java:service:0.17.0'</code></pre>

참고
[https://velog.io/@minji1289/Springboot-스프링부트로-DALL-E-API-호출하기](https://velog.io/@minji1289/Springboot-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%EB%A1%9C-DALL-E-API-%ED%98%B8%EC%B6%9C%ED%95%98%EA%B8%B0)
<br>
<br>
2. 로그인 JWT
<pre><code>implementation 'io.jsonwebtoken:jjwt-api:0.11.5’</code></pre>

<br>

## 🌟 How to install
1. AWS EC2 생성
<table>
    <tr>
        <td align=center>EC2 name</td>
        <td align=center>hack_server</td>
    </tr>
    <tr>
        <td align=center>AMI</td>
        <td align=center>Amazon Linux 2023 AMI</td>
    </tr>
   <tr>
        <td align=center>Instance Type</td>
        <td align=center>t2 micro (free tier eligible)</td>
    </tr>
   <tr>
        <td align=center>Key Pair</td>
        <td align=center>Create new key pair</td>
    </tr>
   <tr>
        <td align=center>VPC</td>
        <td align=center>Default</td>
    </tr>
   <tr>
        <td align=center>Subnet</td>
        <td align=center>Default</td>
    </tr>
  <tr>
        <td align=center>Auto-assign public IP</td>
        <td align=center>Enable</td>
    </tr>
  <tr>
        <td align=center>Security group</td>
        <td align=center>SSH, HTTP, HTTPS, 8080</td>
    </tr>
</table>

<br>
2. RDS 설정
<table>
    <tr>
        <td align=center>Create method</td>
        <td align=center>Standard create</td>
    </tr>
    <tr>
        <td align=center>Engine option</td>
        <td align=center>MySQL Community</td>
    </tr>
   <tr>
        <td align=center>Templates</td>
        <td align=center>free tier</td>
    </tr>
   <tr>
        <td align=center>DB instance identifier</td>
        <td align=center>hack-db</td>
    </tr>
   <tr>
        <td align=center>Computer Resource</td>
        <td align=center>Don’t connect to an EC2 compute resource</td>
    </tr>
   <tr>
        <td align=center>Public access </td>
        <td align=center>Yes</td>
    </tr>
</table>
<br>
3. S3 설정
<table>
    <tr>
        <td align=center>Bucket name</td>
        <td align=center>hack-s3bucket</td>
    </tr>
    <tr>
        <td align=center>ACLs</td>
        <td align=center>enable</td>
    </tr>
   <tr>
        <td align=center>Block Public Access</td>
        <td align=center>disable</td>
    </tr>
</table>
   DeleteObject, GetObject, PutObject에 대해 Allow 권한 설정   
<br>
<br>
4. 도메인 설정
    <br>
   aws route53에서 도메인 구매 후 api.rainbow-letter.com과 rainbow-letter.com 등록
<br>
<br>
<br>
5. EC2 서버에 ssh 접속
   <br>
   <code>ssh -i {pem_key경로} ec2-user@{public_ip}</code>
   <br>
   <br>
6. java 17 설치 및 타임존 변경
<pre><code>## ec2에 java 17 설치
sudo yum install java-17-amazon-corretto

java -version //openjdk "17.0.7"


sudo rm /etc/localtime
sudo ln -s /usr/share/zoneinfo/Asia/Seoul /etc/localtime
date #타임존 확인</code></pre>

<br>
7. git 설치
<br>
<pre><code>sudo yum install git //git 설치
git --version //설치된 git 버전 확인</code></pre>

<br>
8. MySQL 설치
    <pre><code>sudo yum localinstall https://dev.mysql.com/get/mysql80-community-release-el9-3.noarch.rpm
    # [제일 최신 버전으로 설치]
sudo yum install mysql-community-server
sudo systemctl start mysqld
sudo systemctl status mysqld</code></pre>
<br>

9. EC2 서버에 application.properties, build.gradle, gradlew, gradle 폴더 추가 <br>
      a. open AI AP key <br>
      b. RDS 엔드포인트 및 패스워드  <br>
      c. papago API key <br>
    <br>
10. 배포 script 작성 후 ./deploy.sh or sh deploy.sh 로 실행
   ~~~
#!/bin/bash
REPOSITORY=/home/ec2-user/app/step1
PROJECT_NAME=HACK_Server
cd $REPOSITORY/$PROJECT_NAME/
echo "> Git Pull"
git pull
echo "> 프로젝트 Build 시작"
./gradlew build
echo "> 디렉토리로 이동"
cd $REPOSITORY
echo "> Build 파일 복사"
cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/
echo "> 현재 구동중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fl wow_server.*.jar | grep java | awk '{print $1}')
echo "> 현재 구동중인 애플리케이션pid: $CURRENT_PID"
if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi
echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | head -n 1)
echo "> JAR Name: $JAR_NAME"
nohup java -jar $JAR_NAME &
~~~
<br>
11. github action을 이용한 자동 배포 <br>
        a. Github Repository의 Settings > Secrets and variables > Actions에 “Repository secrets" 3가지 추가 <br>
                    - HOST = EC2 인스턴스의 public IP <br>
                    - USER = 사용자 (보통은 ec2-user)  <br>
                    - SSH_PRIVATE_KEY = `.pem` 키 파일 내용 <br>
                <br>
       b. github/workflows 폴더 생성 후 main.yml 파일 아래와 같이 추가 <br>

   <pre><code>
name: WORKFLOW_NAME # workflow 이름 설정

# workflow를 run하는 조건 설정
on:
    push:
        branches: [ main ]  #설정한 workflow run 조건: branch [main]에 push를 할때마다

# workflow가 run할때 실행하는 내용 정의
jobs:
    SSH:
        runs-on: ubuntu-latest # OS(workflow label)

       steps:
         - uses: actions/checkout@v3 #Repository 참고
         - name: ssh to ec2 #EC2에 접속
           uses: appleboy/ssh-action@master #접속 제공 Repository
           with:
             key: ${{ secrets.SSH_PRIVATE_KEY }}    #Repository secrets 사용
             host: ${{ secrets.HOST }}    #Repository secrets 사용
             username: ${{ secrets.USER }}    #Repository secrets 사용
             script: | #실행할 코드
               cd /home/ec2-user/app/step1    #배포 스크립트 파일이 있는 폴더로 이동
               sh deploy.sh    #배포 스크립트 파일 실행
            
               #기존 deploy.sh 파일에서 nohup 명령어를 포함한 line을 지우고, 아래와 같이 `.yml` 스크립트 파일에 추가
               nohup java -jar wow_server-0.0.1-SNAPSHOT.jar > nohup.out 2> nohup.err < /dev/null &
   </code></pre>
   <br>
    
   c. 기존 deploy.sh 파일에서 nohup 부분 주석 처리
      <br>
      <pre><code> ## nohup java -jar $JAR_NAME &</code></pre>
     <br>
      
    
## 🌤️ How to build
1. git clone https://github.com/ewha-cse-HACK/HACK_Server.git
2. [application.properties](http://application.properties) 파일 설정 <br>
    a. openAI api key <br>
    b. rds 혹은 local db 엔드포인트 및 패스워드 <br>
    c. papago api key <br>
3. 어플리케이션 실행
4. postman에서 <code>http://localhost:8080/{테스트할_URL}</code> 을 통해 API 조회
5. postman 혹은 chrome에서 <code>https://api.rainbow-letter.com/{테스트할_URL} </code>을 통해 API 조회
<br>

## 🎮 How to test
[API 명세서](https://sour-actress-dea.notion.site/API-9cae104034e5467fbb01377553c0cd4c?pvs=4)
<br>
1. postman에서 [http://localhost:8080/](http://localhost:8080/community/1){테스트하고_싶은_api} 를 통해 API 조회
2. postman 혹은 chrome에서 [https://api.rainbow-letter.com/](https://api.rainbow-letter.com/community?page=1){테스트할_URL}을 통해 API 조회

 
<br>


## 🌳 Source Code tree

~~~
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── hack
    │   │           └── hack_server
    │   │               ├── Authentication
    │   │               │   ├── Controller
    │   │               │   │   └── UserController.java
    │   │               │   ├── Dto
    │   │               │   │   ├── JoinRequestDto.java
    │   │               │   │   └── LoginRequestDto.java
    │   │               │   ├── JwtAuthenticationFilter.java
    │   │               │   ├── JwtAuthorizationFilter.java
    │   │               │   ├── JwtProvider.java
    │   │               │   ├── PrincipalDetails.java
    │   │               │   └── Service
    │   │               │       ├── PrincipalDetailsService.java
    │   │               │       └── UserService.java
    │   │               ├── ChatGpt
    │   │               │   ├── ChatGptConfig.java
    │   │               │   ├── Controller
    │   │               │   │   └── ChatGptController.java
    │   │               │   ├── Dto
    │   │               │   │   ├── ChatGptAnswerResponseDto.java
    │   │               │   │   ├── ChatGptRequestDto.java
    │   │               │   │   ├── ChatGptResponseDto.java
    │   │               │   │   ├── Choice.java
    │   │               │   │   ├── DalleAnswerResponseDto.java
    │   │               │   │   ├── MessageRequestDto.java
    │   │               │   │   ├── MessageResponseDto.java
    │   │               │   │   └── QuestionRequestDto.java
    │   │               │   └── Service
    │   │               │       └── ChatGptService.java
    │   │               ├── Community
    │   │               │   ├── Comment
    │   │               │   │   ├── Controller
    │   │               │   │   │   └── CommentController.java
    │   │               │   │   ├── Dto
    │   │               │   │   │   ├── CommentSaveRequestDto.java
    │   │               │   │   │   └── CommentUpdateRequestDto.java
    │   │               │   │   └── Service
    │   │               │   │       └── CommentService.java
    │   │               │   └── Post
    │   │               │       ├── Controller
    │   │               │       │   └── PostController.java
    │   │               │       ├── Dto
    │   │               │       │   ├── CommentDto.java
    │   │               │       │   ├── PostAddRequestDto.java
    │   │               │       │   ├── PostDetailResponseDto.java
    │   │               │       │   ├── PostImageDto.java
    │   │               │       │   ├── PostListResponseDto.java
    │   │               │       │   ├── PostModifyRequestDto.java
    │   │               │       │   └── PostResponseDto.java
    │   │               │       └── Service
    │   │               │           └── PostService.java
    │   │               ├── Dalle
    │   │               │   ├── Controller
    │   │               │   │   └── ImageGeneratorController.java
    │   │               │   ├── Dto
    │   │               │   │   ├── JournalCommentDto.java
    │   │               │   │   ├── JournalCommentResponseDto.java
    │   │               │   │   ├── JournalListDto.java
    │   │               │   │   ├── JournalListPageResponseDto.java
    │   │               │   │   ├── JournalListResponseDto.java
    │   │               │   │   └── JournalResponseDto.java
    │   │               │   ├── Service
    │   │               │   │   ├── AIService.java
    │   │               │   │   └── JournalService.java
    │   │               │   └── ServicesConfig.java
    │   │               ├── Entity
    │   │               │   ├── BaseTimeEntity.java
    │   │               │   ├── Comment.java
    │   │               │   ├── Heart.java
    │   │               │   ├── Journal.java
    │   │               │   ├── JournalComment.java
    │   │               │   ├── Pet.java
    │   │               │   ├── Post.java
    │   │               │   ├── PostImage.java
    │   │               │   └── User.java
    │   │               ├── Global
    │   │               │   ├── Config
    │   │               │   │   ├── JpaConfig.java
    │   │               │   │   └── SecurityConfig.java
    │   │               │   └── S3
    │   │               │       ├── S3Config.java
    │   │               │       ├── S3ImageController.java
    │   │               │       └── S3Uploader.java
    │   │               ├── HackServerApplication.java
    │   │               ├── MyPage
    │   │               │   ├── Controller
    │   │               │   │   └── MyPageController.java
    │   │               │   ├── Dto
    │   │               │   │   ├── MyPageRequestDto.java
    │   │               │   │   ├── NickNameModifyRequestDto.java
    │   │               │   │   └── ProfileImageModifyRequestDto.java
    │   │               │   └── Service
    │   │               │       └── MyPageService.java
    │   │               ├── Papago
    │   │               │   ├── NaverTransService.java
    │   │               │   └── PapagoController.java
    │   │               ├── Persona
    │   │               │   ├── Controller
    │   │               │   │   └── PersonaController.java
    │   │               │   ├── Dto
    │   │               │   │   ├── PersonaDto.java
    │   │               │   │   ├── PersonaListRequestDto.java
    │   │               │   │   ├── PetRequestDto.java
    │   │               │   │   └── SpeciesRequestDto.java
    │   │               │   └── Service
    │   │               │       └── PersonaService.java
    │   │               └── Repository
    │   │                   ├── CommentRepository.java
    │   │                   ├── HeartRepository.java
    │   │                   ├── JournalCommentRepository.java
    │   │                   ├── JournalRepository.java
    │   │                   ├── PetRepository.java
    │   │                   ├── PostImageRepository.java
    │   │                   ├── PostRepository.java
    │   │                   └── UserRepository.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
~~~

- Authentication: 로그인/회원가입 <br>
- ChatGpt: 채팅 <br>
- Community: 커뮤니티 <br>
- Dalle: 일기 훔쳐보기 <br>
- MyPage: 마이페이지 <br>
- Papago: Papago API 호출 <br>
- Persona: 페르소나 생성 <br>



## 👍 Developers
<table>
  <thead>
    <tr>
        <th align=center><a href="https://github.com/minji1289">김민지</a></td>
        <th align=center><a href="https://github.com/ehvzmf">최유나</a></td>
        <th align=center><a href="https://github.com/yunji118">하윤지</a></td>
    </tr>
  </thead>
    <tr>
        <td align=center><img src = "https://github.com/ewha-cse-HACK/HACK_Server/assets/67634926/b9b5e64d-45d7-4a6d-af6f-7d7456c10da8"></td>
        <td align=center><img src = "https://github.com/ewha-cse-HACK/HACK_Server/assets/67634926/5b560b07-6128-4b81-aa64-e3093d7f77ed"></td>
        <td align=center><img src = "https://github.com/ewha-cse-HACK/HACK_Server/assets/67634926/5580da4f-6b71-4d2e-9a25-96889a4c48ca"></td>
    </tr>
    <tr>
        <td align=center>Server<br>AI</td>
        <td align=center>Client<br>Design</td>
        <td align=center>Server<br>AI</td>
    </tr>
</table>
<br>


