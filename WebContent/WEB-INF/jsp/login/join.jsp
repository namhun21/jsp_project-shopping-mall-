<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>야사자 | 회원가입</title>

<link rel="stylesheet" href="css/join-style.css">


</head>
<body>
<script type="text/javascript">
//아이디 유효성 검사(1 = 중복 / 0 != 중복)
var idJ = /^[a-z0-9]{4,12}$/;
$("#userid").blur(function() {
	
	var userid = $('#userid').val();
	$.ajax({
		url : "idcheck?userid="+ userid,
		type : "get",
		data : {
			userid : userid
		},
		dataType : "json",
		success : function(data) {
			console.log("1 = 중복o / 0 = 중복x : "+ data);							
			
			if (data == 1) {
					// 1 : 아이디가 중복되는 문구
					$("#id_check").text("사용중인 아이디입니다 :p");
					$("#id_check").css("color", "red");
					$("#btnJoin").attr("disabled", true);
				} else {
					
					if(idJ.test(user_id)){
						// 0 : 아이디 길이 / 문자열 검사
						$("#id_check").text("");
						$("#btnJoin").attr("disabled", false);
			
					} else if(user_id == ""){
						
						$('#id_check').text('아이디를 입력해주세요 :)');
						$('#id_check').css('color', 'red');
						$("#btnJoin").attr("disabled", true);				
						
					} else {
						
						$('#id_check').text("아이디는 소문자와 숫자 4~12자리만 가능합니다 :) :)");
						$('#id_check').css('color', 'red');
						$("btnJoin").attr("disabled", true);
					}
					
				}
			}, error : function() {
					console.log("실패");
			}
		});
	});
</script>
  <form action="joinok" method="post" class="checkout-form">
	<div id="header">
		<h1>회원가입</h1>
	</div>
	<div id="wrapper">
		<div id="content">
			<div>
				<h3 class="join_title">
					<label for="id">아이디</label>
				</h3>
				<span class="box int_id"> <input type="text" id="userid" name="userid"
					class="int" maxlength="20" placeholder="아이디 입력" required>
				</span>
				  
				<div id = "id_check"></div>							
				
			</div>
			<div>
				<h3 class="join_title">
					<label for="pswd">비밀번호</label>
				</h3>
				<span class="box int_pass"> <input type="password" id="userpw" name="userpw"
					class="int" maxlength="20" placeholder="비밀번호 입력">
				</span> <span class="error_next_box"></span>
			</div>
			<div>
				<h3 class="join_title">
					<label for="name">이름</label>
				</h3>
				<span class="box int_name"> <input type="text" id="name" name="username"
					class="int" maxlength="20" placeholder="이름 입력">
				</span> <span class="error_next_box"></span>
			</div>
			<div>
				<h3 class="join_title">
					<label for="address">주소</label>
				</h3>
				<span class="box int_email"> <input type="text" id="useraddress" name="useraddress"
					class="int" maxlength="500" placeholder="주소 입력">
				</span>
			</div>

			<div>
				<h3 class="join_title">
					<label for="email">이메일</label>
				</h3>
				<span class="box int_email"> <input type="text" id="useremail" name="useremail"
					class="int" maxlength="50" placeholder="이메일 입력">
				</span>
			</div>

			<div>
				<h3 class="join_title">
					<label for="phoneNo">전화번호</label>
				</h3>
				<span class="box int_mobile"> <input type="tel" id="userphnumber" name="userphnumber"
					class="int" maxlength="20" placeholder="전화번호 입력">
				</span> <span class="error_next_box"></span>
			</div>

			<div class="btn_area">
				<button type="submit" id="btnJoin">
					<span>가입하기</span>
				</button>
			</div>

		</div>
	</div>
	</form>
</body>
</html>
