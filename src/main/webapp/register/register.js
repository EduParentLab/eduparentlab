function checkEmail() {
    const email = document.getElementById("email").value.trim();
    if(email === ""){
        alert("이메일을 입력하세요!");
        return;
    }

    $.ajax({
        url: contextPath + "/register/emailCheck.do", // JSP에서 contextPath를 전역 변수로 받아옴
        type: "GET",
        data: { email: email },
        success: function(response){
            if(response === "DUPLICATE"){
                alert("이미 사용 중인 이메일입니다.");
            }else if(response === "AVAILABLE"){
                alert("사용 가능한 이메일입니다.");
            }else{
                alert("서버 응답 오류");
            }
        },
        error: function(){
            alert("서버 요청 중 오류가 발생했습니다.");
        }
    });
}