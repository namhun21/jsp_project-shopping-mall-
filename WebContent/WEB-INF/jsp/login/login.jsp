<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>야사자 | 로그인 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/login-style.css">
<script type="jquery-3.3.1.min.js"></script>
</head>
<body>
	<section class="login-form">
		<h1>야사자 로그인</h1>
		<form action="loginok" method="post">
			<div class="int-area">
				<input type="text" name="userid" id="id" autocomplete="off" required>
				<label for="id">ID</label>
			</div>
			<div class="int-area">
				<input type="password" name="userpw" id="pw" autocomplete="off" required>
				<label for="pw">PASSWORD</label>
			</div>
			<div class="btn-area">
				<button id='btn_login' type="submit">LOGIN</button>
			</div>
		</form>
		<form action = "join" method = "post">
			<div class="btn-area">
				<button id='btn_join' type="submit">Sign up</button>
			</div>
		</form>
	</section>

	<script>
	let id = $('#id');	
  	let pw = $('#pw');
  	let btn = $('#btn_login');

  	$(btn).on('click', function() {
  		if($(id).val() == "") {
  			$(id).next('label').addClass('warning');
  			setTimeout(function() {
  				$('label').removeClass('warning');
  			}, 1500);
  		}
  		else if($(pw).val() == "") {
  			$(pw).next('label').addClass('warning');
  			setTimeout(function() {
  				$('label').removeClass('warning');
  			}, 1500);
  		}
  	});
  </script>
</body>
</html>