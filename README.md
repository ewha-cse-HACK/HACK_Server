# 💥HACK💥
### 🌈무지개편지
펫로스 극복을 위한 챗봇 서비스

저희 서비스 “무지개편지”의 목표는 펫로스 증후군을 앓는 사람들을 위해 챗봇이 주는 위로를 통해 극복의 토대를 제공하는 것입니다. 사용자들은 다른 사람에게 솔직히 말하기 어렵거나 시간이 많이 지나도 털어내지 못한 감정을 반려동물과의 가상 대화를 통해 부담 없이 털어놓을 수 있고, 이는 펫로스 증후군 극복에 긍정적인 작용을 할 수 있습니다. 시간이 흘러도 여전히 그리운, 당신의 가족이자 기억하고 싶은 이들에게 전하고 싶은 말을 모아 직접 보내보세요. “무지개편지”는 그 말들이 닿길 기원하며 가상의 답변을 보내드립니다.
<br>
<br>

### ☝️How to use
<h3>Install</h3>

~~~
git clone https://github.com/ewha-cse-HACK/server_dev.git
~~~

<h3>API</h3>
<table>
  <thead>
    <tr>
        <th align=center>기능</td>
        <th align=center>Method</td>
        <th align=center>URL</td>
        <th align=center>Request Body</td>
    </tr>
  </thead>
    <tr>
        <td align=center>글 작성</td>
        <td align=center>POST</td>
        <td align=center>/posts/</td>
        <td align=center>{
          "title": string,
          "content": string,
          "author": string
          }</td>
    </tr>
    <tr>
        <td align=center>글 수정</td>
        <td align=center>PATCH</td>
        <td align=center>/posts/{id}</td>
        <td align=center>{
                "title": string,
                "content": string,
                "author": string
                }</td>
    </tr>
    <tr>
        <td align=center>글 삭제</td>
        <td align=center>DELETE</td>
        <td align=center>/posts/{id}</td>
        <td align=center></td>
    </tr>
    <tr>
        <td align=center>특정 글 불러오기</td>
        <td align=center>GET</td>
        <td align=center>/posts/{id}</td>
        <td align=center></td>
    </tr>
    <tr>
        <td align=center>전체 글 불러오기</td>
        <td align=center>GET</td>
        <td align=center>/posts/</td>
        <td align=center></td>
    </tr>
    <tr>
        <td align=center>좋아요</td>
        <td align=center>GET</td>
        <td align=center>/posts/{id}/dolike</td>
        <td align=center></td>
    </tr>
    <tr>
        <td align=center>좋아요 취소</td>
        <td align=center>GET</td>
        <td align=center>/posts/{id}/undolike</td>
        <td align=center></td>
    </tr>
    <tr>
        <td align=center>댓글 작성</td>
        <td align=center>POST</td>
        <td align=center>/posts/{id}/comment</td>
        <td align=center>{
”comment”: string
}</td>
    </tr>
    
</table>
<br>


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


### 🦶Project Log
[notion] https://www.notion.so/HACK-4af07ba7630a4a4895472163585a872c
