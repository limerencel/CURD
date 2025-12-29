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
		<span>欢迎您: ${name}</span>
		<a href="login.jsp" class="btn btn-danger" style="position: absolute;right: 0;">注销</a>
	</h2>

	<h5 class="page-header alert-info" style="margin:0px; padding:10px; margin-bottom:10px;">
		员工列表
	</h5>

	<!-- 搜索表单 -->
	<form class="navbar-form navbar-left" id="searchForm">
		<div class="form-group">
			<!-- 修复1: 给输入框添加 id 和 name -->
			<input type="text" id="searchInput" name="keyword" class="form-control" placeholder="请输入用户名...">
		</div>
		<!-- 修复2: 按钮类型改为 button，不用 submit -->
		<button type="button" class="btn btn-primary" id="searchBtn">搜索</button>
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
			<!-- 修复3: 给 tbody 添加 id -->
			<tbody id="userTableBody">

			<!-- 如果没有数据 -->
			<c:if test="${empty users}">
				<tr>
					<td colspan="8" style="text-align: center;">没有找到相关数据</td>
				</tr>
			</c:if>
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
	// 搜索功能
	function searchUsers() {
		let keyword = $("#searchInput").val().trim();

		// 注意：Servlet URL 要对
		$.get("${pageContext.request.contextPath}/search", {keyword: keyword}, function(users) {
			console.log("收到的JSON数据:", users);
			renderTable(users);
		});
	}

	// 渲染表格
	function renderTable(users) {
		let html = "";

		if (!users || users.length === 0) {
			html = '<tr><td colspan="8" style="text-align: center;">没有找到相关数据</td></tr>';
		} else {
			for (let i = 0; i < users.length; i++) {
				let user = users[i];
				let rowClass = i % 2 === 0 ? 'active' : '';
				let gender = user.gender === 1 ? '男' : '女';

				let birthday = user.birthday || "";

				html += `
                    <tr class="\${rowClass}">
                        <td>\${user.username}</td>
                        <td><a href="detail?id=\${user.id}">\${user.name}</a></td>
                        <td><img src="\${user.pic}" alt="" width="20" height="20"></td>
                        <td>\${birthday}</td>
                        <td>\${user.email}</td>
                        <td>\${user.address}</td>
                        <td>\${gender}</td>
                        <td>
                            <a href="update?id=\${user.id}">修改</a>
                            <a href="javascript:void(0);" onclick="deleteUser(\${user.id})">删除</a>
                        </td>
                    </tr>
                `;
			}
		}

		$("#userTableBody").html(html);
	}

	// 页面加载完成后绑定事件
	$(document).ready(function() {
        // render table when page is loaded
        searchUsers();
		// 绑定搜索按钮
		$("#searchBtn").on("click", searchUsers);

		// 绑定输入框的回车和实时输入
		$("#searchInput").on("keypress", function(e) {
			if (e.which === 13) {
				e.preventDefault();
				searchUsers();
			}
		}).on("input", function() {
			searchUsers();
		});
	});

	// delete user
	function deleteUser(id) {
		if (confirm("确定要删除这个用户吗？")) {
			$.post("${pageContext.request.contextPath}/delete", {id: id}, function(response) {
				alert("删除成功");
				// 删除后重新执行一次搜索（如果框里有字，就按字搜；没字就是搜全部）
				searchUsers();
			});
		}
	}
</script>
</html>