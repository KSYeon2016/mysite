<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/include/header.jsp'/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<input type="text" id="kwd" name="kwd" value="${kwd }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach var='vo' items='${pageList }'>
						<tr>
							<td>${vo.no }</td>
							<td><a href="/mysite/board?a=view&no=${vo.no }">${vo.title }</a></td>
							<td>${vo.writer }</td>
							<td>${vo.viewCount }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:if test='${vo.userNo == authUser.no }'>
									<a href="/mysite/board?a=delete&no=${vo.no }" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<!-- begin:paging -->
	            <div class="pager">
	               <ul>
	               <c:if test='${countPage > boardAccount }'>
	               		<li>
		               		<c:if test='${(page != null) && (page > boardAccount) }'>
		                  		<a href="/mysite/board?page=${page-boardAccount }&kwd=${kwd }">◀</a>
							</c:if>
						</li>
	               </c:if>
	                  <c:forEach begin='${minPage }' end='${maxPage }' step='1' var='i'>
		            	  <c:choose>
		            	  	<c:when test='${page == i}'>
		            	  		<li class="selected">
		            	  	</c:when>
		            	  	<c:otherwise>
		            	  		<li>
		            	  	</c:otherwise>
		            	  </c:choose>
		            	  <a href="/mysite/board?page=${i }&kwd=${kwd }">${i }</a></li>
	                  </c:forEach>
	               <c:if test='${countPage > boardAccount }'>
	               		<c:if test='${(page != null) && (page <= countPage-boardAccount) }'>
	                  		<li><a href="/mysite/board?page=${page+boardAccount }&kwd=${kwd }">▶</a></li>
	                  	</c:if>
	               </c:if>
	               </ul>
	            </div>
	            <!-- end:paging -->
            
            	<c:if test='${authUser != null }'>
					<div class="bottom">
						<a href="/mysite/board?a=writeform" id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>
		<c:import url='/WEB-INF/views/include/navi.jsp'></c:import>
		<c:import url='/WEB-INF/views/include/footer.jsp'></c:import>
	</div>
</body>
</html>