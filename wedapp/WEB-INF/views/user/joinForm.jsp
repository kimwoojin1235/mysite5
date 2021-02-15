<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>


<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<div id="header">
			<h1>
				<a href="">MySite</a>
			</h1>

			<ul>
				<li><a href="">로그인</a></li>
				<li><a href="">회원가입</a></li>
			</ul>
		</div>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a href="">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- //nav -->

		<div id="aside">
			<h2>회원</h2>
			<ul>
				<li>회원정보</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>회원가입</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>회원</li>
            			<li class="last">회원가입</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="user">
				<div>
					<form id="joinForm" action="${pageContext.request.contextPath}/user/join" method="" >

						<!-- 아이디 -->
						<div class="form-group">
							<label class="form-text" for="input-uid">아이디</label> 
							<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
							<button type="button" id="btnCheck">중복체크</button>	
						</div>
							<p id="msg">
								<!-- 아이디 사용가능 여부 메세지 -->
								
							</p>
						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="form-text" for="input-pass">패스워드</label> 
							<input type="text" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
						</div>

						<!-- 이메일 -->
						<div class="form-group">
							<label class="form-text" for="input-name">이름</label> 
							<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
						</div>

						<!-- //나이 -->
						<div class="form-group">
							<span class="form-text">성별</span> 
							
							<label for="rdo-male">남</label> 
							<input type="radio" id="rdo-male" name="gender" value="male" > 
							
							<label for="rdo-female">여</label> 
							<input type="radio" id="rdo-female" name="gender" value="feale" > 

						</div>

						<!-- 약관동의 -->
						<div class="form-group">
							<span class="form-text">약관동의</span> 
							
							<input type="checkbox" id="chk-agree" value="" name="">
							<label for="chk-agree">서비스 약관에 동의합니다.</label> 
						</div>
						
						<!-- 버튼영역 -->
						
		                <div class="button-area">
		                    <button type="submit" id="btn-submit">회원가입</button>
		                </div> 
						
					</form>
				</div>
				<!-- //joinForm -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<div id="footer">
			Copyright ⓒ 2020 황일영. All right reserved
		</div>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>
<script type="text/javascript">
	$("#btnCheck").on("click",function(){
		
		var uid = $("#input-uid").val();
		console.log("${pageContext.request.contextPath }/user/idcheck?id=" +uid);
		//ajax 데이터만 가지고 와줘
		$.ajax({
			//주소창에 뜨는 내용
			url : "${pageContext.request.contextPath }/user/idcheck?id=" +uid ,		
			type : "post",
			//contentType : "application/json",
			//data : {id:uid}, //url뒤에 붙을 데이터를 붙여준다.

			dataType : "text",
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				if(result == 'can'){
					console.log("can");
					$("#msg").html("사용가능한 아이디 입니다.")
				}else{
					console.log("cant");
					$("#msg").html("사용불가능한 아이디 입니다.")
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	

	});
	//폼을 submit할때--> id="joinForme되기전
	$("#joinForm").on("submit",function(){
		//동의 여부 체크
		var check=$("#chk-agree").is(":checked");//false --체크안함
		console.log(check);
		//패스워드 체크
		var pw = $("#input-pass").val();
		console.log(pw.length);
		var gen = $("[name='gender']").is(":checked");
		if(pw.length < 8){
			//패스워드 체크 alert(패스워드는 8클자 이상입니다.)
			alert("패스워드는 8글자 이상입니다.");
			return false;
		}
		if(!check){
			//
			alert("약관에 동의해 주세요");
			return false;
		}
		if(!gen){
			//
			alert("성별을 선택해주세요");
			return false;
		}
		//위를 다 통과 하면 됨
		 return true;
	});

</script>

</html>