<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>用户登录</title>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/jquery-3.5.1.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
	</head>

	<body>

		<div class="container">
			<form class="form-horizontal" action="/login">

				<h5 class="page-header alert-info" style=" padding:10px; text-align: center;">
					用户登录
				</h5>

				<div class="form-group">
					<label for="username" class="col-sm-4 control-label">用户名：</label>
					<div class="col-sm-4">
						<input type="text" id="username" class="form-control" required name="username" placeholder="请输入用户名" />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-4 control-label">密码：</label>
					<div class="col-sm-4">
						<input type="password" id="password" class="form-control" required name="password" placeholder="请输入密码" />
					</div>
				</div>

				<div class="form-group">
					<label for="checkCode" class="col-sm-4 control-label">验证码：</label>
					<div class="col-sm-2">
						<input type="text" id="checkCode" class="form-control" required name="checkCode" placeholder="请输入验证码" />
					</div>
					<div class="col-sm-1">
						<a href="javascript:location.reload()"><img src="checkCode.jsp" alt=""></a>
					</div>

				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-10">
						<div class="checkbox">
							<label>
								<input type="checkbox" id="rememberMe" name="rememberMe"  value="1"> 记住我
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-10">
						<input type="submit" class="btn btn-success" value="登录">
						<input type="reset" class="btn  btn-primary" value="重置">
						<input type="button" onclick="location.href='register.jsp'" class="btn  btn-warning col-sm-offset-1" value="注册">
					</div>

				</div>


			</form>
		</div>

	</body>

	<script>
		$(".form-horizontal").on("submit", function (e) {
			e.preventDefault();
			$.ajax({
				url: "/login",
				type: "POST",
				data: $(this).serialize(),
				success: function(response) {
					let result = JSON.parse(response);
					if (result.success) {
						window.location.href = "/list.jsp";
					} else {
						alert(result.message);
					}
				},
				error: function() {
					alert("something went wrong");
				}
			});
		});
	</script>
</html>
