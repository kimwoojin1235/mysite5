<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">


			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //nav -->

		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li>일반방명록</li>
				<li>ajax방명록</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>일반방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">일반방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="guestbook">
				<!-- <form action="" method=""> -->
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label></td>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label></td>
								<td><input id="input-pass"type="password" name="pass"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button id="btnSubmit" type="submit">등록</button></td>
							</tr>
						</tbody>
						
					</table>
					<!-- //guestWrite -->
					<input type="hidden" name="action" value="add">
					
				<!-- </form>	 -->
				
				<div id="guestbooklistArea">
					<!-- 리스트 글 출력영역 -->
				</div>
				
				
			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<div id="footer">
			Copyright ⓒ 2020 황일영. All right reserved
		</div>
		<!-- //footer -->

	</div>
	<!-- //wrap -->
	
	<!-- 모달창영역 -->
		<div class="modal fade" id="deletModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">방명록 삭제</h4>
	      </div>
	      <div class="modal-body">
	      
	      	<label>비밀번호</label>
	      	<input id="modalPassword" type="text" name="Password"value=""><br>
	      	<!-- no히든처리 -->
	      	<input id="modalNo" type="text" name="no"value="">
	      
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
	        <button id="modaBtnlDel" type="button" class="btn btn-primary">삭제</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
</body>
<script type="text/javascript">
	//DOM이 생성이되면
	$("#document").ready(function(){
		console.log("ready");
		
		//리스트 출력
		fechList();
	});
	//모달창 삭제버튼 클릭시 -->삭제
	$("#modaBtnlDel").on("click",function(){
	 	console.log("삭제버튼 클릭");
	 	var no=	$("#modalNo").val();
	 	var guestbookVo = {
	 		password:$("#modalPassword").val(),
	 		no:	$("#modalNo").val()
	 	}
		//모달창 비번, no수집
		console.log(guestbookVo);
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/remove",		
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,

			dataType : "json",
			success : function(conut){
				/*성공시 처리해야될 코드 작성*/
				console.log(conut);
				
				if(conut==1){
					//모달창닫기
					$("#deletModal").modal("hide");
					//no 테이블 화면에서 안보이게 처리
					$("#t-"+no).remove();
				}else{
					//모달창 닫기
					alert("비밀번호가 틀렸습니다.")
					$("#modalPassword").val("");
					//$("#deletModal").modal("hide");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
	
	//삭제버튼 클릭시
	$("#guestbooklistArea").on("click","a",function(){
		//위임 
		event.preventDefault();
		console.log("모달창 호출");
		//비밀번호 필드 초기화
		$("#modalPassword").val("");

		var no = $(this).data("no");
		//modalNo이라는 아이디를 가진 창에 다가 no라는 값을 넣는다.
		$("#modalNo").val(no);		
		//모달창호출
		$("#deletModal").modal();
	});
	
	$("#btnSubmit").on("click",function(){
		console.log("등록버튼 클릭");
		//방명록 데이터 수집
		var guestbookVo={
			name:$("[name='name']").val(),
			password: $("[name='pass']").val(),
			content:$("[name='content']").val()
		}
		
		console.log(guestbookVo);
		
		//ajax방식으로 요청(저장)
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/write2",		
			type : "post",
			contentType : "application/json",
			data :JSON.stringify(guestbookVo),

			dataType : "json",
			success : function(guestVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestVo);
				//{no: 28, name: "김우진", password: null, content: "4545418", regdate: "2021-02-09 16:09:25.0"}
				reder(guestVo,"up");//게스트북 정보 출력
				
				//입력폼 비우기
				$("[name='name']").val("");
				$("[name='pass']").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		
	});
	
//방명록글 정보+ html 조합하여 화면애 출력
function reder(guestVo,updown){
	
	var str="";
	str +='<table id="t-'+guestVo.no+'" class="guestRead">';
	str +='		<colgroup>';
	str +='			<col style="width: 10%;">';
	str +='			<col style="width: 40%;">';
	str +='			<col style="width: 40%;">';
	str +='			<col style="width: 10%;">';
	str +='		</colgroup>';
	str +='		<tr>';
	str +='			<td>'+guestVo.no+'</td>';
	str +='			<td>'+guestVo.name+'</td>';
	str +='			<td>'+guestVo.regdate+'</td>';
	str +='			<td><a href="" data-no="'+guestVo.no+'">[삭제]</a></td>';
	str +='		</tr>';
	str +='		<tr>';
	str +='			<td colspan=4 class="text-left">'+guestVo.content+'</td>';
	str +='		</tr>';
	str +='</table>';
	//이 위에 들어가는 data는 소문자만 인식을 할수가 있음
	if(updown=="down"){
		$("#guestbooklistArea").append(str);
	}else if(updown=="up"){
		$("#guestbooklistArea").prepend(str);	
	}else{
		console.log("방향미지정")
	}
	
	
}

//전체리스트 출력
function fechList(){
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/list",		
		type : "post",
		//contentType : "application/json",
		//data : {name: "홍길동"},

		dataType : "json",
		success : function(guestlist){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestlist);
			for(var i=0;i<guestlist.length;i++){
				reder(guestlist[i],"up");	
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

}


</script>

</html>