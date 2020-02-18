<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div >
		<h2>我的收藏夹</h2>
		<hr color="red">
		<form id="queryForm">
		<input type="text" name="text">
		<input type="hidden" id="pageNum" name="pageNum" value="${pageInfo.pageNum }">
		<button type="button" class="btn btn-primary mb-2" onclick="query();">查询</button>
	</form>
		<div>
			<c:forEach items="${pageInfo.list}" var="a">
			<input type="hidden" id="pageNum" name="pageNum" value="${pageInfo.pageNum }">
				<p style="color: blue">${a.text}</p>
				<p>
					时间:
					<fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd" />
					<span>删除</span>
				</p>
			</c:forEach>
			<div class="col-9">
				
				<jsp:include page="../common/page.jsp"></jsp:include>
				<script type="text/javascript" src="/public/js/checkbox.js" ></script>
			</div>
		</div>
<script type="text/javascript">
		
		
		function query(){
			var params = $("#queryForm").serialize();
			reload(params)
		}
		
		function gotoPage(pageNum){
			$("#pageNum").val(pageNum);
			query();
		}
		
		
		
	</script>