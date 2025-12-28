<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>用户列表</title>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/jquery-3.5.1.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
	</head>

	<body>
		<div class="container">
			<h2 style="text-align: center;position: relative;">
				<span >欢迎您: ${name}</span>

				<a href="login.jsp" class="btn btn-danger" style="position: absolute;right: 0;">注销</a>
			</h2>
				
				<h5 class="page-header alert-info" style="margin:0px; padding:10px; margin-bottom:10px;">
					员工列表
				</h5>
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入用户名...">
					</div>
					<button type="submit" class="btn btn-primary">搜索</button>
				</form>
				<div class="row" style="padding:15px;">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>用户名</th>
								<th>员工姓名</th>
								<th>头像</th>
								<th>出生日期</th>
								<th>电子邮件</th>
								<th>籍贯</th>
								<th>员工性别</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${users}" var="user" varStatus="status">
							<tr class="${status.index % 2 == 0 ? 'active' : ''}">
								<td>${user.username}</td>
								<td><a href="detail?id=${user.id}">${user.name}</a></td>
								<td><img src="https://www.baidu.com/favicon.ico" alt="" width="20" height="20"></td>
								<td>${user.birthday}</td>
								<td>${user.email}</td>
								<td>${user.address}</td>
								<td>${user.gender == 1 ? '男' : '女'}</td>
								<td>
									<a href="update?id=${user.id}">修改</a>
									<a href="javascript:void(0);" onclick="deleteUser(${user.id})">删除</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<nav class="text-center" aria-label="Page navigation">
						<ul class="pagination">
							<li>
								<a href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li class="active"><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li>
								<a href="#" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</ul>
					</nav>
				</div>
		</div>
	</body>

	<script>

	</script>
</html>
