// 중복확인 여부 플래그
let emailChecked = false;
let nicknameChecked = false;

function checkEmail() {
    const email = document.getElementById("email").value.trim();
    if (email === "") {
        alert("이메일을 입력하세요!");
        return;
    }

    $.ajax({
        url: contextPath + "/register/emailCheck.do",
        type: "GET",
        data: { email: email },
        success: function (response) {
            if (response === "DUPLICATE") {
                alert("이미 사용 중인 이메일입니다.");
                emailChecked = false; // ❌ 사용 불가
            } else if (response === "AVAILABLE") {
                alert("사용 가능한 이메일입니다.");
                emailChecked = true;  // ✅ 사용 가능
            } else {
                alert("서버 응답 오류");
                emailChecked = false;
            }
        },
        error: function () {
            alert("서버 요청 중 오류가 발생했습니다.");
            emailChecked = false;
        }
    });
}

function checkNickname() {
    const nickname = document.getElementById("nickname").value.trim();
    if (nickname === "") {
        alert("닉네임을 입력하세요!");
        return;
    }

    $.ajax({
        url: contextPath + "/register/nicknameCheck.do",
        type: "GET",
        data: { nickname: nickname },
        success: function (response) {
            if (response === "DUPLICATE") {
                alert("이미 사용 중인 닉네임입니다.");
                nicknameChecked = false;
            } else if (response === "AVAILABLE") {
                alert("사용 가능한 닉네임입니다.");
                nicknameChecked = true;
            } else {
                alert("서버 응답 오류");
                nicknameChecked = false;
            }
        },
        error: function () {
            alert("서버 요청 중 오류가 발생했습니다.");
            nicknameChecked = false;
        }
    });
}

function validateRegisterForm() {
    const form = document.f;

    if (form.email.value.trim() === "") {
        alert("이메일을 입력하세요!");
        form.email.focus();
        return false;
    }
    if (form.password.value.trim() === "") {
        alert("비밀번호를 입력하세요!");
        form.password.focus();
        return false;
    }
    if (form.passwordConfirm.value.trim() === "") {
        alert("비밀번호 확인을 입력하세요!");
        form.passwordConfirm.focus();
        return false;
    }
    if (form.password.value !== form.passwordConfirm.value) {
        alert("비밀번호가 일치하지 않습니다!");
        form.passwordConfirm.focus();
        return false;
    }
    if (form.nickname.value.trim() === "") {
        alert("닉네임을 입력하세요!");
        form.nickname.focus();
        return false;
    }
    if (form.name.value.trim() === "") {
        alert("이름을 입력하세요!");
        form.name.focus();
        return false;
    }
    if (form.gender.value.trim() === "") {
        alert("성별을 입력하세요!");
        form.gender.focus();
        return false;
    }
    if (form.birth.value.trim() === "") {
        alert("생년월일을 입력하세요!");
        form.birth.focus();
        return false;
    }
    if (form.phone.value.trim() === "") {
        alert("전화번호를 입력하세요!");
        form.phone.focus();
        return false;
    }

    // 전화번호 형식 검사
    const phonePattern = /^010-\d{4}-\d{4}$/;
    if (!phonePattern.test(form.phone.value.trim())) {
        alert("전화번호는 010-XXXX-XXXX 형식으로 입력해야 합니다.");
        form.phone.focus();
        return false;
    }

    // 중복확인 여부 검사
    if (!emailChecked) {
        alert("이메일 중복확인을 먼저 해주세요!");
        return false;
    }
    if (!nicknameChecked) {
        alert("닉네임 중복확인을 먼저 해주세요!");
        return false;
    }

    return true; // 모든 검증 통과 시 제출 진행
}
