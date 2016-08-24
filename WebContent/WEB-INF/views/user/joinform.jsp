<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mysite/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	$("#join-form").submit(function(){
		console.log("폼 체크");
		
		// 이름 체크
		if($("#name").val() == ""){
			alert("이름은 필수 입력사항입니다.");
			$("#name").focus();
			return false;
		}
		
		// 이메일 체크
		if($("#email").val() == ""){
			alert("이메일은 필수 입력사항입니다.");
			$("#email").focus();
			return false;
		}
		
		if($("#image-checked").is(":visible") == false){
			alert("이메일 중복 체크를 해 주세요.");
			return false;
		}
		
		// 패스워드 체크
		if($("input[type='password']").val() == ""){
			alert("비밀번호는 필수 입력사항입니다.");
			$("input[type='password']").focus();
			return false;
		}
		
		// 약관동의 체크
		if($("#agree-prov").is(":checked") == false){
			alert("약관 동의가 필요합니다.");
			return false;
		}
		
		return true;
	});
	
	$("#email").change(function(){
        $("#image-checked").hide();
        $("#btn-checkemail").show();
	});
	
	$("#btn-checkemail").click(function(){
		var email = $("#email").val();
		if(email == ""){
			return;
		}
		
		$.ajax({
			"url":"/mysite/user?a=checkemail&email=" + email,
			"type":"get",
			"dataType":"json",
			"data":"",
			"success":function(response) {
				if(response.result != "success") {
					console.error("error:" + response.message);
					return;
				}
	            
				if(response.data == true) {
					alert("이미 존재하는 이메일 입니다. 다른 이메일을 사용해 주세요");
					$("#email").val("").focus();
					return;
				}
	            
				$("#image-checked").show();
				$("#btn-checkemail").hide();
			},
			"error":function(jsXHR, status, e) {
				console.error("error:" + status + ":" + e);
			}
		});
		console.log("clicked");
	});
})
</script>
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/include/header.jsp'/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="/mysite/user">
					<input type="hidden" name="a" value="join"/>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<img id="image-checked" style="width:16px; display:none" src="/mysite/assets/images/check.png"/>
					<input type="button" id="btn-checkemail" value="id 중복체크">
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="FEMALE" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="MALE">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/include/navi.jsp'></c:import>
		<c:import url='/WEB-INF/views/include/footer.jsp'></c:import>
	</div>
</body>
</html>