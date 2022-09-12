var sendData = JSON.stringify({
    email: $('#email').val(),
    password: $('#password').val(),
    userName: $('#userName').val(),
    phoneNumber: $('#phoneNumber').val()
});

$.ajax({
    type: "POST",
    url : "<c:url value='/join'/>",
    data: sendData,
    dataType: "json",
    contentType:"application/json;charset=UTF-8",
    success : function(msg) {
        if(msg.result == "success"){
            alert("회원가입에 성공하셨습니다.");
        } else if(msg.result != "success"){
            alert("처리를 실패하였습니다.");
        }

        location.href="<c:url value='/'>"
    }
});
