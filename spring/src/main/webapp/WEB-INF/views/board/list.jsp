<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
<jsp:include page="../layout/header.jsp"></jsp:include>
    
    <table class="table">

	<thead>
		<tr>
			<th scope="col">글번호</th>
			<th scope="col">글제목</th>
			<th scope="col">작성자</th>
			<th scope="col">댓글수</th>
			<th scope="col">파일수</th>
			<th scope="col">작성날짜</th>
			<th scope="col">조회수</th>
		</tr>
	</thead>
	<tbody>
	<!-- items : BoardController에 있는 List<> 변수명을 적어줘야한다 -->
	<!-- var : items안에 있는 list를 var안에 하나씩 넣어준다 -->
		<c:forEach items="${list }" var="bvo">
			<tr>
				<th scope="row">${bvo.bno }</th>
				<!-- ?뒤에있는 URL을 선택하는용도 -->
				<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
				<td>${bvo.writer }</td>
				<td>${bvo.cmtQty }</td>
				<td>${bvo.hasFile}</td>
				<td>${bvo.regAt}</td>
				<td>${bvo.readCount }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
    
    
    
    
    
    
    
    
    
<jsp:include page="../layout/footer.jsp"></jsp:include>