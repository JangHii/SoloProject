<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../layout/header.jsp"></jsp:include>


<form action="/board/modify" method="post">
<div class="container-md">
<h2>수정페이지</h2>
<br>


<div class="mb-3">
  <label for="bno" class="form-label">번호</label>
  <input type="text" name="bno" class="form-control " id="bno" value="${bvo.bno }" readonly="readonly" style="background-color: gray;">
</div>
<div class="mb-3">
  <label for="title" class="form-label">제목</label>
  <input type="text" name="title" class="form-control" id="title" value="${bvo.title }">
</div>
<div class="mb-3">
  <label for="writer" class="form-label">작성자</label>
  <input type="text" name="writer" class="form-control" id="writer" value="${bvo.writer }" readonly="readonly" style="background-color: gray;">
</div>
<div class="mb-3">
  <label for="content" class="form-label">내용</label>
  <input type="text" name="content" class="form-control" id="content" value="${bvo.content }">
</div>
<div class="mb-3">
  <label for="regAt" class="form-label">작성일</label>
  <input type="text" name="regAt" class="form-control" id="regAt" value="${bvo.regAt }" readonly="readonly" style="background-color: gray;">
</div>
<div class="mb-3">
  <label for="readCount" class="form-label">조회수</label>
  <input type="text" name="readCount" class="form-control" id="readCount" value="${bvo.readCount }" readonly="readonly" style="background-color: gray;">
</div>


<!-- 파일 등록 라인 -->
<div class="mb-3">
  <input type="file" name="files" class="form-control" id="files" multiple="multiple" style="display:none;"> 
  
  <!-- 파일 버튼 트리거 사용하기 위해서 주는 버튼 -->
  <button type="button" class="btn btn-secondary" id="trigger">파일업로드</button>
</div>

<!-- 파일 목록 표시라인 -->
<div class="mb-3" id="fileZone"></div>	


<a href="/board/list"><button type="button" class="btn btn-primary">리스트</button></a>
<button type="submit" class="btn btn-secondary">수정완료</button>
<a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-primary">삭제하기</button></a> 



</div>
</form>


<jsp:include page="../layout/footer.jsp"></jsp:include>