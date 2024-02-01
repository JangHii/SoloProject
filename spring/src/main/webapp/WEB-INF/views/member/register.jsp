<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container my-3">
	<h4 class="mb-3">회원가입</h4><br>
	<form action="/member/register" method="post">
		<div class="mb-3">
			<label for="e" class="form-label">이메일</label> 
			<input type="email" name="email" class="form-control" id="e" placeholder="aaaa@example.com">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">비밀번호</label> 
			<input type="password" name="pwd" class="form-control" id="p" placeholder="password">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">닉네임</label> 
			<input type="text" name="nickName" class="form-control" id="n">
		</div>
		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>



<jsp:include page="../layout/footer.jsp"></jsp:include>