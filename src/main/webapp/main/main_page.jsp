<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="../css/layout.css" />
  <link rel="stylesheet" href="../css/main_page.css" />
</head>


<body>
  <div class="wrapper">
    <div id="headerArea"></div>
    <main>
      <div class="center-wrapper">
        <div class="section-banner">
          <a href="#">
           <img src="./assets/banner.jpg" style="width:100%; height:auto;display: block;" />
          </a>
        </div>
        <div class="section-popular-recent-posts">
             <div class="popular-board">
                <h3>인기 게시물</h3>
				<c:forEach var="popular" items="${popularList}">
	                <div class="board-item">
		                <div class="board-title">
		                  <a href="${pageContext.request.contextPath}/post.do?m=content&seq=${popular.post_num}">${popular.post_subject}</a>
		                </div>
		                <div class="meta-info">
		                    <span>${popular.nickname}</span>
		                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" />${popular.post_date}</span>
		                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" />${popular.post_view}</span>
		                </div>
	                </div>
			    </c:forEach>
            </div>


            <div class="popular-board">
                <h3>최신 게시물</h3>
                <c:forEach var="latest" items="${latestList}">
	                <div class="board-item">
		                <div class="board-title">
		                  <a href="${pageContext.request.contextPath}/post.do?m=content&seq=${latest.post_num}">${latest.post_subject}</a>
		                </div>
		                <div class="meta-info">
		                    <span>${latest.nickname}</span>
		                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" />${latest.post_date}</span>
		                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" />${latest.post_view}</span>
		                </div>
	                </div>
			    </c:forEach>
            </div>





        </div>
        <div class="section-notice">
	        <c:forEach var="news" items="${news}">
	           <div style="display:flex; justify-content:center; width: 5%;">
	            <label style="border:2px solid red; color:red; font-weight: bold;">공지</label>
	           </div>
	           <div style="display:flex; justify-content:flex-start; width: 50%;">
	            <a href="${pageContext.request.contextPath}/post.do?m=content&seq=${news.post_num}" style="font-weight:bold">${news.post_subject}</a>
	           </div>
	           <div style="display:flex; justify-content:flex-end; width: 40%;">
	            <label>${news.ppost_date}</label>
	           </div>
	        </c:forEach>
        </div>



        <div class="section-popular-recent-posts">
           <div class="popular-board">
                <h3>인기 게시물</h3>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>금영</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>민영</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>진석</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>민영</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>연희</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>
            </div>

            <div class="popular-board">
                <h3>인기 게시물</h3>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>금영</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>민영</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>진석</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>민영</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>

                <div class="board-item">
                <div class="board-title">
                  <a href="#">2025년 내신 3등급 대학 리스트</a>
                </div>
                <div class="meta-info">
                    <span>연희</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/847/847969.png" alt="clock" /> 8일전</span>
                    <span><img src="https://cdn-icons-png.flaticon.com/512/889/889140.png" alt="like" /> 24</span>
                </div>
                </div>
            </div>
        </div>
        <div class="section-title">
           <p style="font-size:35px; font-weight:bold" >학원소식 타이틀</p> 
        </div>
        <div class="section-academy-news">
           
            <div style="width:25%">
              <a href="#">
                <img src="./assets/academynews.jpg" style="width:100%; height:auto;" />
              </a>
            </div>
           <div style="width:25%">
              <a href="#">
                <img src="./assets/academynews.jpg" style="width:100%; height:auto;" />
              </a>
            </div>
           <div style="width:25%">
              <a href="#">
                <img src="./assets/academynews.jpg" style="width:100%; height:auto;" />
              </a>
            </div>
           <div style="width:25%">
              <a href="#">
                <img src="./assets/academynews.jpg" style="width:100%; height:auto;" />
              </a>
            </div>
        </div>
        
        </div>


      </div>
    </main>


    <footer>
      <p>회사소개 | 이용약관 | 개인정보처리방침 등등</p>
      <p>© 1999 - 2025 dcinside. All rights reserved.</p>
    </footer>
  </div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="../js/main_page.js"></script>



</body>
</html>
