<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>       



<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
	<h2>로그인 페이지</h2>
	
	<form action="/member/login" method="post">
	
	
	<div class="mb-3">
			<label for="e" class="form-label">아이디</label> 
			<input type="text" name="email" class="form-control" id="e" placeholder="아이디를 입력해주세요..">
		</div>
	
		<div class="mb-3">
			<label for="p" class="form-label">비밀번호</label> 
			<input type="password" name="pwd" class="form-control" id="p" placeholder="비밀번호를 입력해주세요..">
		</div>
		
		<c:if test="${not empty param.errMsg}">
	<div class="mb-3 text-donger" >
		<c:choose>
			<c:when test="${param.errMsg eq 'Bad credentials' }">
				<c:set value="이메일 & 비밀번확 일치하지 않습니다." var="errText"></c:set>
			</c:when>
			<c:otherwise>
				<c:set value="관리자에게 문의해주세요." var="errText"></c:set>
			</c:otherwise>
		</c:choose>
		${errText }
	</div>
</c:if>
		
		<a href="/"><button type="button" class="btn btn-secondary">취소</button></a>
		<button type="submit" class="btn btn-secondary">로그인하기</button>
	
	</form>

	
</div>




<jsp:include page="../layout/footer.jsp"></jsp:include>