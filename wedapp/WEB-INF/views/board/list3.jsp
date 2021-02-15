<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

			
			
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="aside">
			<h2>게시판</h2>
			<ul>
				<li><a href="">일반게시판</a></li>
				<li><a href="">댓글게시판</a></li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="${pageContext.request.contextPath }/board/list3" method="get">
						<div class="form-group text-right">
							<input type="text" name="keyword">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pMap.boardList}" var="Vo">
								<tr>
									<td>${Vo.no }</td>
									<td class="text-left"><a href="${pageContext.request.contextPath }/board/read?no=${Vo.no }">${Vo.title}</a></td>
									<td>${Vo.name}</td>
									<td>${Vo.hit}</td>
									<td>${Vo.egdate}</td>
									<td>
										<c:if test="${Vo.userno == authUser.no }">
											<a href="${pageContext.request.contextPath }/board/delete?no=${Vo.no }">[삭제]</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		
					<div id="paging">
						<ul>
							<c:if test="${pMap.prev==true}">
								<li><a href="${pageContext.request.contextPath }/board/list3?crtPage=${pMap.startPageBtnNo-1}&keyword=${param.keyword}">◀</a></li>
							</c:if>
							<c:forEach begin="${pMap.startPageBtnNo}" end="${pMap.endPageBtnNo}" step="1" var="page">
								<li><a href="${pageContext.request.contextPath }/board/list3?crtPage=${page}&keyword=${param.keyword}">${page}</a></li>
							</c:forEach>
						
							<c:if test="${pMap.next==true}">
							<li><a href="${pageContext.request.contextPath }/board/list3?crtPage=${pMap.endPageBtnNo+1}&keyword=${param.keyword}">▶</a></li>
							</c:if>
						</ul>
						
						
						<div class="clear"></div>
					</div>
					<a id="btn_write" href="${pageContext.request.contextPath }/board/writeForm">글쓰기</a>
				
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
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

</html>
