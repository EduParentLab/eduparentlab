<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>학부모정보통</title>
  <link rel="stylesheet" href="../main/layout.css" />
  <link rel="stylesheet" href="admin_notice.css" />
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.2.0/dist/chartjs-plugin-datalabels.min.js"></script>
</head>
<body>
  <div class="wrapper">
  <div id="headerArea"></div>
    <main>
        <div class="center-wrapper">
            <div class="box box-left" id="leftBox">
                <div class="left-box-section section-toggle">
                    <div class="menu-icon" style="padding:15px; font-size:25px;">☰</div>
                </div>
                <div class="left-box-section section-profile">
                    <div class="profile-image-wrapper">
                        <svg class="profile-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <circle cx="12" cy="8" r="4" fill="#999"/>
                        <path d="M4 20c0-4 4-6 8-6s8 2 8 6" fill="#999"/>
                        </svg>
                    </div>
                </div>
                <div class="left-box-section section-profile-name" style="font-size:20px; display: flex; justify-content: center; align-items: center;">
                ${loginOkUser.name}님
                </div>
                <div class="left-box-section section-menu menu-1" data-page="notice">
                <svg class="menu-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path d="M3 10V6a1 1 0 011-1h2V3h2v2h3v2H8v2h4v2H8v2h3v2H8v2h2v2H8v-2H6v-2H4a1 1 0 01-1-1v-2z" fill="black"/>
                </svg>
                <span class="menu-text">공지사항</span>
                </div>
                <div class="left-box-section section-menu menu-2" data-page="user_list">
                <svg class="menu-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path d="M5 8a3 3 0 1 1 6 0 3 3 0 0 1-6 0zm8 0a3 3 0 1 1 6 0 3 3 0 0 1-6 0zM2 20v-2a4 4 0 0 1 4-4h2a4 4 0 0 1 4 4v2H2zm12 0v-2a4 4 0 0 1 4-4h0a4 4 0 0 1 4 4v2h-8z" fill="black"/>
                </svg>
                <span class="menu-text">사용자 목록</span>
                </div>
                <div class="left-box-section section-menu menu-3" data-page="withdrawn_list">
                <svg class="menu-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path d="M12 12a5 5 0 100-10 5 5 0 000 10zM3 21v-2a6 6 0 0112 0v2H3zm13-6l5 5m0-5l-5 5" stroke="black" stroke-width="2" fill="none"/>
                </svg>
                <span class="menu-text">탈퇴 회원</span>
                </div>
                <div class="left-box-section section-menu menu-4" data-page="statistics">
                <svg class="menu-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path d="M4 17h2v3H4v-3zm4-6h2v9H8v-9zm4-5h2v14h-2V6zm4 8h2v6h-2v-6z" fill="black"/>
                </svg>
                <span class="menu-text">통계</span>
                </div>
            </div>
            <div class="box box-right">             
            </div>
        </div>
    </main>
    <div id="footerArea"></div>
  </div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="admin_notice.js"></script>
</body>
</html>


