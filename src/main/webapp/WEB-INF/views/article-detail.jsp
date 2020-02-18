<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${article.title }</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">



</head>
<body>
	<div class="header">
		<ul class="nav">
			<li class="nav-item" style="margin-left: 12px;">
				<a	class="navbar-brand" href="#"> 
					<img src="https://v4.bootcss.com/docs/4.3/assets/brand/bootstrap-solid.svg"	width="30" height="30" alt="">
				</a>
			</li>
			<li class="nav-item"><a class="nav-link active" href="/">首页</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">个人中心</a></li>
			<li class="nav-item"><a class="nav-link" href="#">登录</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#"
				tabindex="-1" aria-disabled="true">退出</a></li>
		</ul>
	</div>
	<div class="container-fluid" style="margin-top: 6px;">
		<div class="row offset-1">
			<div class="col-6">
				<h1>${article.title }</h1>
				<h3 style="color: #777;">${article.nickname }    发布时间：<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd日"/>
				</h3><h5><c:if test="${null!=collect}">
				   <a href="javascript:collect(1)"><span style="color: red">★ (已收藏)</span></a>
				
				</c:if>
				<c:if test="${null==collect}">
				 <a href="javascript:collect(0)">☆ (未收藏)</a>
				
				</c:if>  </h5>
				
				<div>
					<div class="article-content">
					 <div class="carousel-inner">
    <div class="carousel-item active">
    	 ${article.content } 
    </div>
    <div class="carousel-item">
   
    </div>   
  </div>
					
					
					</div>
				</div>
			</div>
			
			<div class="col-3">
				<div class="right">
					<div>相关文章</div>
					<ul class="list-unstyled">
						<li class="media"><img
							src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87"
							style="height: 72px; width: 72px;" class="mr-3" alt="...">
							<div class="media-body">
								<h5 class="mt-0 mb-1">电子眼全面升级，新增5项功能！</h5>
							</div></li>
						<li class="media"><img
							src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87"
							style="height: 72px; width: 72px;" class="mr-3" alt="...">
							<div class="media-body">
								<h5 class="mt-0 mb-1">电子眼全面升级，新增5项功能！</h5>
							</div></li>
						<li class="media"><img
							src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87"
							style="height: 72px; width: 72px;" class="mr-3" alt="...">
							<div class="media-body">
								<h5 class="mt-0 mb-1">电子眼全面升级，新增5项功能！</h5>
							</div></li>
						<li class="media"><img
							src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87"
							style="height: 72px; width: 72px;" class="mr-3" alt="...">
							<div class="media-body">
								<h5 class="mt-0 mb-1">电子眼全面升级，新增5项功能！</h5>
							</div></li>
						<li class="media"><img
							src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87"
							style="height: 72px; width: 72px;" class="mr-3" alt="...">
							<div class="media-body">
								<h5 class="mt-0 mb-1">电子眼全面升级，新增5项功能！</h5>
							</div></li>
						<li class="media"><img
							src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87"
							style="height: 72px; width: 72px;" class="mr-3" alt="...">
							<div class="media-body">
								<h5 class="mt-0 mb-1">电子眼全面升级，新增5项功能！</h5>
							</div></li>
						<li class="media"><img
							src="http://p1.pstatp.com/large/pgc-image/4ab237b9682f4c0286da5c5e6823dc87"
							style="height: 72px; width: 72px;" class="mr-3" alt="...">
							<div class="media-body">
								<h5 class="mt-0 mb-1">电子眼全面升级，新增5项功能！</h5>
							</div></li>
					</ul>
				</div>
			</div>

		</div>
	</div>
	<script src="/public/js/jquery.min.1.12.4.js"></script>
	<script src="/public/js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">

//收藏或取消收藏
function collect(flag){
	//获取当前文章的url
	var url =window.location.href;
	var text ='${article.title}';//文件的标题

	if(flag==0){//收藏
		$.post("/collect",{url:url,text:text},function(result){
		 	if(result){
		 		alert("收藏成功");
		 		location.reload();//刷新当前页面
		 		
		 	}else{
		 		alert("收藏失败")
		 	}
		})
	}else{//取消收藏
		var id='${collect.id}';
        $.post("/unCollect",{id:id},function(result){
        	if(result){
		 		alert("取消收藏成功")
		 			location.reload();	//刷新当前页面
		 	}else{
		 		alert("取消收藏失败")
		 	}
		})	
	}
	
}




</script>



</html>