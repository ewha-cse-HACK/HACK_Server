# 🌈무지개편지
<b>펫로스 극복을 위한 챗봇 서비스</b>

저희 서비스 “무지개편지”의 목표는 펫로스 증후군을 앓는 사람들을 위해 챗봇이 주는 위로를 통해 극복의 토대를 제공하는 것입니다. 사용자들은 다른 사람에게 솔직히 말하기 어렵거나 시간이 많이 지나도 털어내지 못한 감정을 반려동물과의 가상 대화를 통해 부담 없이 털어놓을 수 있고, 이는 펫로스 증후군 극복에 긍정적인 작용을 할 수 있습니다. 시간이 흘러도 여전히 그리운, 당신의 가족이자 기억하고 싶은 이들에게 전하고 싶은 말을 모아 직접 보내보세요. “무지개편지”는 그 말들이 닿길 기원하며 가상의 답변을 보내드립니다.
<br>
<br>
### 🌱Server 구성요소
- java 17 : 사용한 프로그래밍 언어
- springboot (3.1.1) : 프레임워크
- JWT : 회원가입 및 로그인시 사용하는 토큰
- Open AI (GPT-4, DALL-E) : 텍스트 생성을 위한 GPT 4, 이미지 생성을 위한 DALL-E 3 API 활용
- EC2 : 서버
- RDS : 데이터베이스
- S3: 이미지 저장

<br>

### 🌟How to install
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
      <code> ## nohup java -jar $JAR_NAME & </code>
      


~~~
git clone https://github.com/ewha-cse-HACK/HACK_Server.git
~~~


 



<img src = "https://github.com/ewha-cse-HACK/server_dev/assets/67634926/4ab5ee50-fc68-4f65-9fcd-7cd9c11aab10" width = 400 height = 300>

### 🛠️Project Architecture
<img src = "https://github.com/ewha-cse-HACK/server_dev/assets/67634926/e9c95b0d-8170-428d-90e8-a281188d8c74" width = 500 height = 400>
<img src = "https://github.com/ewha-cse-HACK/server_dev/assets/67634926/ddb06cdf-21e6-430b-ae30-e0b0b8f44260" width = 500 height = 400>
<br>

### 🖥️Demo video
[youtube] https://youtu.be/NFmeN01eHdY
<br>
<br>

### 🎨Wire frame
<img src = "https://github.com/ewha-cse-HACK/server_dev/assets/67634926/7ae13ccb-6b58-4710-b653-8538ce48a265">
<br>
<br>

### 💪Teck stack
backend <br>
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/spring_boot-6DB33F?style=for-the-badge&logo=spring_boot&logoColor=white"> <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black"> <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=aws&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

frontend <br>
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"/></a> <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=AmazonEC2&logoColor=white"/></a>
<br>
<br>


### 👍Contributor
<table>
  <thead>
    <tr>
        <th align=center><a href="https://github.com/minji1289">김민지</a></td>
        <th align=center><a href="https://github.com/ehvzmf">최유나</a></td>
        <th align=center><a href="https://github.com/yunji118">하윤지</a></td>
    </tr>
  </thead>
    <tr>
        <td align=center><img src = "https://github.com/ewha-cse-HACK/server_dev/assets/67634926/a65400ba-bb73-4cda-98f8-029c4108ea1f"></td>
        <td align=center><img src = "https://github.com/ewha-cse-HACK/server_dev/assets/67634926/c25b45bb-a42a-4359-8b00-85fb916745ef"></td>
        <td align=center><img src = "https://github.com/ewha-cse-HACK/server_dev/assets/67634926/6102194b-87d0-468b-bdcc-0b93a37a35ef"></td>
    </tr>
    <tr>
        <td align=center>Leader<br>back-end<br>AI</td>
        <td align=center>Front-end<br>AI</td>
        <td align=center>Back-end<br>AI</td>
    </tr>
</table>
<br>


