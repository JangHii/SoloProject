<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../layout/header.jsp"></jsp:include>
<div class="container-md">

<h1>자유게시판</h1>
<br>
<br>

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

<br>

<!-- 페이징 라인 -->

<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">

		<!-- 이전 -->
		<c:if test="${ph.prev }">
			<li class="page-item"><a class="page-link"
				href="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>

		<!-- 페이지번호 -->
		<c:forEach begin="${ph.startPage}" end="${ph.endPage }" var="i">
			<li class="page-item ${ph.pgvo.pageNo == i ? 'active' : '' }"><a
				class="page-link"
				href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&keyword=${ph.pgvo.keyword}">${i}</a></li>
		</c:forEach>



		<!-- 다음 -->
		<c:if test="${ph.next }">
			<li class="page-item"><a class="page-link"
				href="/board/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>

		<li class="page-item"><a class="page-link" href="/board/list">전체보기</a>
		</li>
	</ul>
</nav>



<!-- 검색라인 -->


	<br>
	<div class="col-sm-12 col-md-6">
		<form action="/board/list" method="get">
			<div class="input-group mb-3">
				<c:set value="${ph.pgvo.type }" var="typed"></c:set>
				<select class="form-select" name="type" id="inputGroupSelect01">
					<option ${typed eq null ? 'selected' : '' }>선택</option>
					<option value ="t" ${typed eq 't' ? 'selected' : '' }>제목</option>
					<option value ="c" ${typed eq 'c' ? 'selected' : '' }>내용</option>
					<option value ="w" ${typed eq 'w' ? 'selected' : '' }>작성자</option>
					<option value ="wc" ${typed eq 'wc' ? 'selected' : '' }>제목+내용</option>
					<option value ="twc" ${typed eq 'twc' ? 'selected' : '' }>전체</option>
				</select>
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="qty" value="${ph.pgvo.qty }">
				<input type="search" name="keyword" value="${ph.pgvo.keyword }"
					class="form-control me-2"  placeholder="검색">
				<button type="submit" class="btn btn-outline-success">검색
					<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
						${ph.totalCount }
						<span class="visually-hidden">unread messages</span>
					</span>
				</button> 
			</div>
		</form>
	</div>

</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>