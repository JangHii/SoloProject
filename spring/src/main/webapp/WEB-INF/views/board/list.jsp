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

<!-- 페이징 라인 -->

<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">

		<!-- 이전 -->
		<c:if test="${ph.prev }">
			<li class="page-item"><a class="page-link"
				href="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>

		<!-- 페이지번호 -->
		<c:forEach begin="${ph.startPage}" end="${ph.endPage }" var="i">
			<li class="page-item ${ph.pgvo.pageNo == i ? 'active' : '' }">
			<a class="page-link"  href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}">${i}</a></li>
		</c:forEach>



		<!-- 다음 -->
		<c:if test="${ph.next }">
			<li class="page-item"><a class="page-link"
				href="/board/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>

		<li class="page-item"><a class="page-link" href="/board/list">전체보기</a>
		</li>
	</ul>
</nav>
    

<jsp:include page="../layout/footer.jsp"></jsp:include>