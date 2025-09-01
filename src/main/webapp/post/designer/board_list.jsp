<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="layout.css" />
  <link rel="stylesheet" href="board_list.css" />
</head>


<body>
  <div class="wrapper">


    <header>
      <div class="logo">
        <a href="index.html">
          <img src="./assets/logoremoveback.png" alt="학부모정보통 로고" />
        </a>
      </div>
      <div class="search-container">
        <div class="search-bar">
          <div class="search-logo">N</div>
          <input type="text" id="searchInput" placeholder="검색어를 입력해 주세요." />
          <button class="search-btn">🔍</button>
        </div>
        <!-- ▼ 아래 추천 검색어 목록 ▼ -->
        <div class="search-dropdown">
          <div class="search-section-title">검색 추천</div>
          <ul class="search-list">
            <li>교육</li>
            <li>탐구</li>
            <li>연구소</li>
          </ul>
        </div>
      </div>
      <div class="login"><button>로그인</button><button>마이페이지</button></div>
      
    </header>
    <div class="navigation-button-container">
    <a href="/page3.html" class="navigation-button">이동</a>
    <a href="/page3.html" class="navigation-button">이동</a>
    <a href="/page3.html" class="navigation-button">이동</a>
    <a href="/page3.html" class="navigation-button">이동</a>
    <a href="/page3.html" class="navigation-button">이동</a>
    </div>



    <main>
      <div class="center-wrapper">


        <div class="section-title">
           <p style="font-size:35px; font-weight:bold" >공지사항</p> 
        </div>
        <div class="section-discription">
            본 게시판은 입시와 관련된 게시판입니다.
        </div>
        <div class="section-pagingfilter">
            <select id="row-count-select" class="dropdown-select">
            <option value="10">10개</option>
            <option value="20">20개</option>
            <option value="30">30개</option>
            <option value="50" selected>50개</option>
            <option value="100">100개</option>
            </select>
        </div>
        <div class="section-board-list">
            <table class="board-table">
                <thead>
                    <tr>
                    <th style="width: 50%;">제목</th>
                    <th style="width: 10%;">글쓴이</th>
                    <th style="width: 20%;">작성일</th>
                    <th style="width: 10%;">조회수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                    <td>오늘 저녁에는 국밥을</td>
                    <td>건태윤</td>
                    <td>2025.08.10</td>
                    <td>39</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                    <td>오늘 저녁에는 국밥을</td>
                    <td>건태윤</td>
                    <td>2025.08.10</td>
                    <td>39</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                    <td>오늘 저녁에는 국밥을</td>
                    <td>건태윤</td>
                    <td>2025.08.10</td>
                    <td>39</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                    <td>오늘 저녁에는 국밥을</td>
                    <td>건태윤</td>
                    <td>2025.08.10</td>
                    <td>39</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                    <td>오늘 저녁에는 국밥을</td>
                    <td>건태윤</td>
                    <td>2025.08.10</td>
                    <td>39</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                    <td>오늘 저녁에는 국밥을</td>
                    <td>건태윤</td>
                    <td>2025.08.10</td>
                    <td>39</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                    <td>오늘 저녁에는 국밥을</td>
                    <td>건태윤</td>
                    <td>2025.08.10</td>
                    <td>39</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                    <td>오늘 저녁에는 국밥을</td>
                    <td>건태윤</td>
                    <td>2025.08.10</td>
                    <td>39</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                    <td>오늘 저녁에는 국밥을</td>
                    <td>건태윤</td>
                    <td>2025.08.10</td>
                    <td>39</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="section-write-btn" style="gap:10px">
                <img src="./assets/plus-circle.svg" class="icon" alt="글쓰기 아이콘" style="width: 40px; height: 40px;"/>
            <button class="write-btn" style="background-color: rgb(164, 183, 247); padding:10px; border-radius: 10px;">글쓰기</button>
        </div>
        <div class="section-searchbar">
            <div class="search-bar-container">
            <!-- 드롭다운 버튼 -->
            <div class="dropdown-wrap">
                <button class="dropdown-btn">
                <span class="selected-text">제목 + 내용</span> ▼
                </button>
                <ul class="dropdown-list">
                <li data-value="title">제목</li>
                <li data-value="content">내용</li>
                <li data-value="title_content">제목 + 내용</li>
                </ul>
            </div>

            <!-- 검색 입력창 -->
            <input type="text" class="search-input" placeholder="검색어를 입력하세요" />

            <!-- 검색 버튼 -->
            <button class="search-button">
                <img src="./assets/search-icon.png" alt="검색" class="search-icon" />
            </button>
            </div>

        </div>
        <div class="section-paging">
            <div class="pagination">
                    <a href="#" class="page current">1</a>
                    <a href="#" class="page">2</a>
                    <a href="#" class="page">3</a>
                    <a href="#" class="page">4</a>
                    <a href="#" class="page">5</a>
                    <a href="#" class="page">6</a>
                    <a href="#" class="page">7</a>
                    <a href="#" class="page">8</a>
                    <a href="#" class="page">9</a>
                    <a href="#" class="page">10</a>
                    <a href="#" class="page">11</a>
                    <a href="#" class="page">12</a>
                    <a href="#" class="page">13</a>
                    <a href="#" class="page">14</a>
                    <a href="#" class="page">15</a>
                    <span class="next-disabled">▶▶</span>
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
<script src="board_list.js"></script>



</body>
</html>
