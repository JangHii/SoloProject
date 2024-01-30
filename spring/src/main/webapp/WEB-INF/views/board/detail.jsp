<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<div class="container-md">

	<c:set value="${bdto.bvo }" var="bvo"></c:set>

	<br>
	<h2>상세페이지</h2>
	<br>
	<div class="mb-3">
		<label for="bno" class="form-label">번호</label> <input type="text"
			name="bno" class="form-control" id="bno" value="${bvo.bno }"
			readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="title" class="form-label">제목</label> <input type="text"
			name="title" class="form-control" id="title" placeholder="title"
			value="${bvo.title }">
	</div>
	<div class="mb-3">
		<label for="writer" class="form-label">작성자</label> <input
			type="text" name="writer" class="form-control" id="writer"
			value="${bvo.writer }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="regAt" class="form-label">작성일</label> <input
			type="text" name="regAt" class="form-control" id="regAt"
			value="${bvo.regAt }" readonly="readonly"> <span
			class="badge bg-secondary">${bvo.readCount}</span>
	</div>
	<div class="mb-3">
		<label for="content" class="form-label">내용</label>
		<textarea class="form-control" name="content" id="content" rows="3">${bvo.content }</textarea>
	</div>
	
	<!-- 파일 표시 라인 -->
	<c:set value="${bdto.flist}" var="flist"></c:set>
		<div class="mb-3">
			<label for="f" class="form-label">File</label>
				<ul class="list-group list-group-flush">
					<c:forEach items="${flist}" var="fvo">
  						<li class="list-group-item">
  						
  							<c:choose>
  								<c:when test="${fvo.fileType == 1}">
  									<img alt="" src="/upload/${fvo.saveDir}/${fvo.uuid}_th_${fvo.fileName}">
  								</c:when>
  								
  								<c:otherwise>
  									<div>
  										<!-- 일반 파일 표시할 아이콘 -->
  										<a href="/upload/${fvo.saveDir}/${fvo.uuid }_${fvo.fileName}">
  											<i class="bi bi-file-earmark-arrow-down"></i>
										</a>
  									</div>
  								</c:otherwise>
  							</c:choose>
  							
  							<div class="ms-2 me-auto">
  								<div class="fw-bold">${fvo.fileName }</div>
  								<span class="badge text-bg-secondary">${fvo.fileSize } </span>
  							</div>
  						</li>
  					</c:forEach>
				</ul>
		</div>
	
	
	
	
	<div class="position-relative">
	<div class="position-absolute bottom-0 end-0">
		<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-primary">게시글수정</button></a> 
		<a href="/board/list"><button type="button" class="btn btn-secondary">리스트로..</button></a>
	</div>
	</div>
	<br>
	
	<!-- 댓글 등록 라인 -->
	<div class="input-group mb-3">
		<span class="input-group-text" id="cmtWriter">Writer</span> 
		<input type="text" class="form-control" id="cmtText" aria-label="Amount (to the nearest dollar)">
		<button type="button" class="btn btn-success" id="cmtPostBtn">등록</button>
	</div>

	<!-- 댓글 표시 라인 -->
	<ul class="list-group list-group-flush" id="cmtListArea">
		<li class="list-group-item">
			<div class="mb-3">
				<div class="fw-bold">Writer <span class="badge rounded-pill text-bg-warning">modAt</span></div>
				content
			</div>
		</li>
	</ul>
	
	<!-- 댓글 더보기 라인  -->
	<div>
		<button type="button" id="moreBtn" data-page="1"
			    class="btn btn-outline-dark" style="visibility: hidden;">댓글 더보기</button>
	</div>
	
	<!-- 모달창 라인 -->
	<div>
			<div class="modal" id="myModal" tabindex="-1">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title">Writer</h5>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
      			<div class="modal-body">
        			<div class="input-group mb-3">
        				<input type="text" class="form-control" id="cmtModText">
        				<button type="button" class="btn btn-primary" id="cmtModBtn">수정완료</button>
        			</div>
      			</div>
    		</div>
  		</div>
			</div>
	</div>
	
	

</div>
<script src="/resources/js/boardComment.js"></script>
<script>
	let bnoVal = `<c:out value="${bvo.bno}"/>`;
</script>

<script>
	spreadCommentList(bnoVal);
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>