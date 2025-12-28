<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
						<tr class="active">
							<td>lisi</td>
							<td><a href="detail.jsp">李四 </a></td>
							<td><img src="https://www.baidu.com/favicon.ico" alt="" width="20" height="20"></td>
							<td>1998-09-21</td>
							<td>110@zhangsan.com</td>
							<td>山西太原</td>
							<td>女</td>
							<td>
								<a href="update.jsp">修改</a>
								<a href="#">删除</a>
							</td>
						</tr>
						<tr>
							<td>wangwu</td>
							<td><a href="detail.jsp">王五 </a></td>
							<td><img src="https://www.mi.com/favicon.ico" alt="" width="20" height="20"></td>
							<td>1998-09-21</td>
							<td>110@zhangsan.com</td>
							<td>陕西西安</td>
							<td>男</td>
							<td>
								<a href="update.jsp">修改</a>
								<a href="#">删除</a>
							</td>
						</tr>
						<tr class="active">
							<td>zhaoliu</td>
							<td><a href="detail.jsp">赵六 </a></td>
							<td><img src="https://www.taobao.com/favicon.ico" alt="" width="20" height="20"></td>
							<td>1998-09-21</td>
							<td>110@zhangsan.com</td>
							<td>河北石家庄</td>
							<td>男</td>
							<td>
								<a href="update.jsp">修改</a>
								<a href="#">删除</a>
							</td>
						</tr>
						<tr>
							<td>zhangsan123</td>
							<td><a href="detail.jsp">张三 </a></td>
							<td><img src="https://www.vivo.com/favicon.ico" alt="" width="20" height="20"></td>
							<td>1998-09-21</td>
							<td>110@zhangsan.com</td>
							<td>山东济南</td>
							<td>男</td>
							<td>
								<a href="update.jsp">修改</a>
								<a href="#">删除</a>
							</td>
						</tr>
						<tr class="active">
							<td>zhaoliu</td>
							<td><a href="detail.jsp">赵六 </a></td>
							<td class="jqzoom" ><img src="https://www.taobao.com/favicon.ico" alt="" width="20" height="20"></td>
							<td>1998-09-21</td>
							<td>110@zhangsan.com</td>
							<td>河北石家庄</td>
							<td>男</td>
							<td>
								<a href="update.jsp">修改</a>
								<a href="#">删除</a>
							</td>
						</tr>
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
